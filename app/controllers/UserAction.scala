package controllers

import dataaccess._
import extensions.combinators._
import extensions.uuid._
import javax.inject.Inject
import models.db.Tables.{PetsUserRow, PetsSessionRow}
import play.api.mvc._
import scalaz.std.scalaFuture._

import scala.concurrent.{ExecutionContext, Future}

class UserRequest[A](val sessionRow: Option[PetsSessionRow], val userRow: Option[PetsUserRow], request: Request[A]) extends WrappedRequest[A](request)

class UserAction @Inject() (
  val parser:      BodyParsers.Default,
  userDatabase:    UserDatabase,
  sessionDatabase: SessionDatabase,
)(implicit val executionContext: ExecutionContext) extends ActionBuilder[UserRequest, AnyContent] with ActionTransformer[Request, UserRequest] {

  def transform[A](request: Request[A]): Future[UserRequest[A]] = {
    val session = (for {
      sessionIdText <- future.fromOption(request.session.get("sessionId"))
      sessionId     <- future.fromOption(uuidFromString(sessionIdText))
      sessionRow    <- future.optionT(sessionDatabase.findBySessionId(sessionId))
    } yield sessionRow).run

    val user = (for {
      sessionRow <- future.optionT(session)
      userId     <- future.fromOption(uuidFromBlob(sessionRow.userId))
      userRow    <- future.optionT(userDatabase.findById(userId))
    } yield userRow).run

    for {
      sessionRow <- session
      userRow    <- user
    } yield new UserRequest(sessionRow, userRow, request)
  }

}
