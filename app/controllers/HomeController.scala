package controllers

import javax.inject._
import play.api.mvc._
import scalaz.syntax.std.option._

import scala.concurrent.ExecutionContext

@Singleton
class HomeController @Inject()(
  controllerComponents: PetsControllerComponents,
  userAction:           UserAction,
) extends AbstractController(controllerComponents) {

  implicit val executor: ExecutionContext = controllerComponents.executionContext

  def index() = Action.async(parse.anyContent) { implicit request =>
    userAction.refine(request)
      .map(_.fold(
        fa = _ => Ok(views.html.index(None)),
        fb = { userRequest =>
          Ok(views.html.index((userRequest.userRow.fullName getOrElse userRequest.userRow.username).some))
        }
      ))
  }
}
