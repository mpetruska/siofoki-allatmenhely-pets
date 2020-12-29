package dataaccess

import java.util.UUID

import extensions.aliases._
import extensions.db.DbRunHelpers
import extensions.uuid._
import javax.inject._
import models.db.Tables._
import play.api.db.slick._
import slick.jdbc.JdbcProfile
import slick.lifted

import scala.concurrent.ExecutionContext

@Singleton
class UserDatabase @Inject()
(val dbConfigProvider: DatabaseConfigProvider)
(implicit val ec: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with DbRunHelpers[JdbcProfile] {

  import profile.api._

  val users = lifted.TableQuery[PetsUser]

  def findById = dbRun(dbioFindById _) _
  def dbioFindById(userId: UUID): DBIO[Option[PetsUserRow]] = {
    users
      .filter(_.id === uuidToBlob(userId))
      .take(1)
      .result
      .headOption
  }

  def findByUsername = dbRun(dbioFindByUsername _) _
  def dbioFindByUsername(username: String): DBIO[Option[PetsUserRow]] = {
    users
      .filter(_.username === username)
      .take(1)
      .result
      .headOption
  }

  def getAll() = db.run(dbioGetAll())
  def dbioGetAll(): DBIO[Seq[PetsUserRow]] = users.result

  def createUser = dbRun(dbioCreateUser _) _
  def dbioCreateUser(id: UUID, username: String, fullName: Option[String], password: Option[String], isAdmin: Boolean, isEnabled: Boolean): DBIO[I] = {
    users
      .map(r => (r.id,             r.username, r.fullName, r.password, r.isAdmin, r.isEnabled))
      .+= (     (  uuidToBlob(id),   username,   fullName,   password,   isAdmin,   isEnabled))
      .map(tott)
  }

  def deleteUser = dbRun(dbioDeleteUser _) _
  def dbioDeleteUser(userId: UUID): DBIO[I] = {
    users
      .filter(_.id === uuidToBlob(userId))
      .delete
      .map(tott)
  }
  
}
