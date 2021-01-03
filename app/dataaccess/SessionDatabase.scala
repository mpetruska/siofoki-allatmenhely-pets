package dataaccess

import java.time.OffsetDateTime
import java.util.UUID

import extensions.aliases._
import extensions.db.DbRunHelpers
import extensions.time._
import javax.inject._
import models.db.Tables._
import play.api.db.slick._
import slick.jdbc.JdbcProfile
import slick.lifted

import scala.concurrent.ExecutionContext

@Singleton
class SessionDatabase @Inject()
(val dbConfigProvider: DatabaseConfigProvider)
(implicit val ec: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with DbRunHelpers[JdbcProfile] {

  import profile.api._

  val sessions = lifted.TableQuery[PetsSession]

  val t = offsetDateTimeToUtcTimestamp _

  def findBySessionId = dbRun(dbioFindBySessionId _) _
  def dbioFindBySessionId(sessionId: UUID): DBIO[Option[PetsSessionRow]] = {
    sessions
      .filter(_.id === sessionId.toString)
      .take(1)
      .result
      .headOption
  }

  def createSession = dbRun(dbioCreateSession _) _
  def dbioCreateSession(sessionId: UUID, userId: UUID, created: OffsetDateTime, isPermanent: Boolean): DBIO[I] = {
    sessions
      .map(r => (r.id,               r.userId,          r.createdUtc, r.lastAccessedUtc, r.isPermanent))
      .+= (     (sessionId.toString,   userId.toString, t(created),   t(created),          isPermanent))
      .map(tott)
  }

  def deleteSession = dbRun(dbioDeleteSession _) _
  def dbioDeleteSession(sessionId: UUID): DBIO[I] = {
    sessions
      .filter(_.id === sessionId.toString)
      .delete
      .map(tott)
  }
  
}
