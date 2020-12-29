package controllers

import dataaccess._
import extensions.combinators._
import extensions.uuid._
import javax.inject.Inject
import models.db.Tables.{PetsUserRow, PetsSessionRow}
import play.api.mvc._
import scalaz.std.scalaFuture._
import scalaz.syntax.std.option._

import scala.concurrent.{ExecutionContext, Future}

class UserRequest[A](val sessionRow: PetsSessionRow, val userRow: PetsUserRow, request: Request[A]) extends WrappedRequest[A](request)

class UserAction @Inject() (
  val parser:      BodyParsers.Default,
  userDatabase:    UserDatabase,
  sessionDatabase: SessionDatabase,
)(implicit val executionContext: ExecutionContext) extends ActionBuilder[UserRequest, AnyContent] with ActionRefiner[Request, UserRequest] {

  def refine[A](request: Request[A]): Future[Either[Result, UserRequest[A]]] = {
    (for {
      sessionIdText <- future.fromOption(request.session.get("sessionId"))
      sessionId     <- future.fromOption(uuidFromString(sessionIdText))
      sessionRow    <- future.optionT(sessionDatabase.findBySessionId(sessionId))
      userId        <- future.fromOption(uuidFromBlob(sessionRow.userId))
      userRow       <- future.optionT(userDatabase.findById(userId))
    } yield new UserRequest(sessionRow, userRow, request))
      .run
      .map(_.cata(
        some = Right(_),
        none = Left(Results.Unauthorized(CommonErrors.PleaseLogin))
      ))
  }

}
