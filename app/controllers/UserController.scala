package controllers

import java.util.UUID

import dataaccess._
import extensions.aliases._
import extensions.combinators._
import extensions.future._
import extensions.uuid._
import javax.inject._
import models.forms._
import org.mindrot.jbcrypt.BCrypt
import play.api.i18n.I18nSupport
import play.api.mvc._
import scalaz.std.scalaFuture._
import scalaz.syntax.either._
import scalaz.syntax.std.boolean._
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
          _              <- failIfFalse(userRow.isEnabled,                                  Errors.LoginFailed)
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
    (for {
      sessionIdText <- future.fromOption(request.session.get("sessionId"))
      sessionId     <- future.fromOption(uuidFromString(sessionIdText))
      _             <- future.some(sessionDatabase.deleteSession(sessionId))
    } yield tt)
      .run
      .map(_ => Redirect(routes.HomeController.index()).withNewSession)
  }

  def listUsers() = userAction.async(parse.anyContent) { implicit request =>
    val loggedInUser = request.userRow
    val users = (for {
      userRow <- future.listT(userDatabase.getAll().map(_.sortBy(_.username)))
      id      <- future.fromSeq(uuidFromBlob(userRow.id).toSeq)
      user    =  UserListRow(
        id                = id,
        username          = userRow.username,
        fullName          = userRow.fullName,
        isAdmin           = userRow.isAdmin,
        isEnabled         = userRow.isEnabled,
        canUpdatePassword = loggedInUser.isAdmin,
        canDelete         = loggedInUser.isAdmin && (userRow.username != "root"),
        canUpdate         = loggedInUser.isAdmin,
      )
    } yield user).run

    users
      .map(_.toList)
      .map(views.html.admin.listusers(_))
      .map(Ok(_))
  }

  def showCreateUserForm() = userAction { implicit request =>
    request.userRow.isAdmin.fold(
      t = Ok(views.html.admin.createuser(CreateUser.form)),
      f = Forbidden(Errors.NotAuthorized)
    )
  }

  def createUser() = userAction.async(parse.anyContent) { implicit request =>
    request.userRow.isAdmin.fold(
      t =
        CreateUser.form.bindFromRequest().fold(
          hasErrors = { formWithErrors =>
            Future.successful(BadRequest(views.html.admin.createuser(formWithErrors)))
          },
          success = { createUser =>
            (for {
              _        <- failIfFalse(createUser.password == createUser.passwordRepeat, Errors.PasswordMismatch)
              fullName =  createUser.fullName.filter(_.nonEmpty)
              password =  BCrypt.hashpw(createUser.password, BCrypt.gensalt())
              _        <- userDatabase.createUser(
                UUID.randomUUID(),
                createUser.username,
                fullName,
                password.some,
                createUser.isAdmin,
                createUser.isEnabled,
              )
            } yield Redirect(routes.UserController.listUsers()))
              .recoverWith {
                case error: IllegalArgumentException =>
                  val form = CreateUser.form.fill(createUser).withGlobalError(error.getMessage())
                  Future.successful(BadRequest(views.html.admin.createuser(form)))
              }
          }
        ),
      f = Future.successful(Forbidden(Errors.NotAuthorized))
    )
  }

  def showUpdatePasswordForm(userId: String) = userAction { implicit request =>
    val uuidOption        = uuidFromString(userId)
    val canUpdatePassword =
      uuidOption.isDefined &&
      ((uuidFromBlob(request.userRow.id).map(_.toString) == uuidFromString(userId)) ||
       request.userRow.isAdmin)

    canUpdatePassword.fold(
      t = Ok(views.html.admin.updatepassword(userId, UpdatePassword.form)),
      f = Forbidden(Errors.NotAuthorized)
    )
  }

  def updatePassword(userId: String) = userAction.async(parse.anyContent) { implicit request =>
    val uuidOption        = uuidFromString(userId)
    val canUpdatePassword =
      uuidOption.isDefined &&
      ((uuidFromBlob(request.userRow.id).map(_.toString) == uuidFromString(userId)) ||
       request.userRow.isAdmin)

    uuidOption.filter(Function.const(canUpdatePassword)).cata(
      some = id =>
        UpdatePassword.form.bindFromRequest().fold(
          hasErrors = { formWithErrors =>
            Future.successful(BadRequest(views.html.admin.updatepassword(userId, formWithErrors)))
          },
          success = { updatePassword =>
            (for {
              _        <- failIfFalse(updatePassword.password == updatePassword.passwordRepeat, Errors.PasswordMismatch)
              password =  BCrypt.hashpw(updatePassword.password, BCrypt.gensalt())
              _        <- userDatabase.updateUser(id)(_.copy(password = password.some))
            } yield Redirect(routes.UserController.listUsers()))
              .recoverWith {
                case error: IllegalArgumentException =>
                  val form = UpdatePassword.form.fill(updatePassword).withGlobalError(error.getMessage())
                  Future.successful(BadRequest(views.html.admin.updatepassword(userId, form)))
              }
          }),
      none = Future.successful(Forbidden(Errors.NotAuthorized))
    )
  }

  def showUpdateUserForm(userId: String) = TODO

  def updateUser(userId: String) = TODO

  def showDeleteUserForm(userId: String) = userAction.async(parse.anyContent) { implicit request =>
    request.userRow.isAdmin.fold(
      t = {
        (for {
          uuid <- failIfNone(uuidFromString(userId),      Errors.UserNotFound)
          user <- failIfNone(userDatabase.findById(uuid), Errors.UserNotFound)
        } yield Ok(views.html.admin.deleteuser(userId, user.right)))
          .recover {
            case error =>
              Ok(views.html.admin.deleteuser(userId, error.getMessage.left))
          }
      },
      f = Future.successful(Forbidden(Errors.NotAuthorized))
    )
  }

  def deleteUser(userId: String) = userAction.async(parse.anyContent) { implicit request =>
    request.userRow.isAdmin.fold(
      t = {
        (for {
          uuid <- failIfNone(uuidFromString(userId), Errors.UserNotFound)
          user <- userDatabase.deleteUser(uuid)
        } yield Redirect(routes.UserController.listUsers()))
          .recover {
            case error =>
              Ok(views.html.admin.deleteuser(userId, error.getMessage.left))
          }
      },
      f = Future.successful(Forbidden(Errors.NotAuthorized))
    )
  }
  
}

object UserController {

  object Errors extends CommonErrors {
    val LoginFailed      = "login_failed"
    val NotAuthorized    = "not_authorized"
    val PasswordMismatch = "password_mismatch"
    val UserNotFound     = "user_not_found"
  }

}
