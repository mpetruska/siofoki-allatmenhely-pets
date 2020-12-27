package controllers

import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class HomeController @Inject()(
  controllerComponents: PetsControllerComponents,
  userAction:           UserAction,
) extends AbstractController(controllerComponents) {

  implicit val executor: ExecutionContext = controllerComponents.executionContext

  def index() = userAction(parse.anyContent) { implicit request =>
    Ok(views.html.index(request.userRow
      .map(user => user.fullName getOrElse user.username)
    ))
  }
}
