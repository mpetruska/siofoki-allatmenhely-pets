package controllers

import java.util.UUID

import dataaccess._
import extensions.aliases._
import extensions.future._
import extensions.uuid._
import javax.inject._
import models.forms.UserLogin
import org.mindrot.jbcrypt.BCrypt
import play.api.i18n.I18nSupport
import play.api.mvc._
import scalaz.syntax.std.option._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserController @Inject()(
  controllerComponents: PetsControllerComponents,
  userAction:           UserAction,
  userDatabase:         UserDatabase,
  sessionDatabase:      SessionDatabase,
) extends AbstractController(controllerComponents) with I18nSupport {

  import UserController._

  implicit val executor: ExecutionContext = controllerComponents.executionContext

  def showLoginForm() = Action { implicit request =>
    Ok(views.html.admin.login(UserLogin.form))
  }

  def login() = Action.async(parse.anyContent) { implicit request =>
    UserLogin.form.bindFromRequest().fold(
      hasErrors = { formWithErrors =>
        Future.successful(BadRequest(views.html.admin.login(formWithErrors)))
      },
      success = { userLogin =>
        (for {
          userRow        <- failIfNone (userDatabase.findByUsername(userLogin.username),    Errors.LoginFailed)
          hashedPassword <- failIfNone (userRow.password,                                   Errors.LoginFailed)
          _              <- failIfFalse(BCrypt.checkpw(userLogin.password, hashedPassword), Errors.LoginFailed)
          userId         <- failIfNone (uuidFromBlob(userRow.id),                           Errors.InternalServerError)
          sessionId      =  UUID.randomUUID()
          _              <- sessionDatabase.createSession(sessionId, userId, controllerComponents.mockableTime.currentTime(), userLogin.isPermanent)
        } yield Redirect(routes.HomeController.index()).withSession("sessionId" -> sessionId.toString()))
          .recoverWith {
            case error: IllegalArgumentException =>
              val form = UserLogin.form.fill(userLogin.copy(password = "")).withGlobalError(error.getMessage())
              Future.successful(BadRequest(views.html.admin.login(form)))
          }
      }
    )
  }

  def logout() = userAction.async(parse.anyContent) { request =>
    request.sessionRow
      .map(_.id)
      .flatMap(uuidFromBlob)
      .cata(some = sessionDatabase.deleteSession, none = Future.successful(tt))
      .map(_ => Redirect(routes.HomeController.index()).withNewSession)
  }

  def listUsers() = TODO

  def createUser() = TODO

  def updateUser() = TODO

  def deleteUser() = TODO
  
}

object UserController {

  object Errors extends CommonErrors {
    val LoginFailed = "loginfailed"
  }

}
