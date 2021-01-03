package dataaccess

import java.util.UUID

import extensions.aliases._
import extensions.combinators._
import extensions.db.DbRunHelpers
import javax.inject._
import models.db.Tables._
import play.api.db.slick._
import scalaz.DBIOInstances._
import slick.jdbc.JdbcProfile
import slick.lifted

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserDatabase @Inject()
(val dbConfigProvider: DatabaseConfigProvider)
(implicit val ec: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with DbRunHelpers[JdbcProfile] {

  import profile.api._

  val users = lifted.TableQuery[PetsUser]

  def findById = dbRun(dbioFindById _) _
  def dbioFindById(userId: UUID): DBIO[Option[PetsUserRow]] = {
    users
      .filter(_.id === userId.toString)
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
      .map(r => (r.id,          r.username, r.fullName, r.password, r.isAdmin, r.isEnabled))
      .+= (     (  id.toString,   username,   fullName,   password,   isAdmin,   isEnabled))
      .map(tott)
  }

  def updateUser(id: UUID)(f: PetsUserRow => PetsUserRow): Future[I] = db.run(dbioUpdateUser(id)(f))
  def dbioUpdateUser(id: UUID)(f: PetsUserRow => PetsUserRow): DBIO[I] = {
    (for {
      oldUser <- dbio.optionT(dbioFindById(id))
      newUser =  f(oldUser).copy(id = oldUser.id)
      _       <- dbio.some(users.insertOrUpdate(newUser))
    } yield tt).run.transactionally.map(tott)
  }

  def deleteUser = dbRun(dbioDeleteUser _) _
  def dbioDeleteUser(userId: UUID): DBIO[I] = {
    users
      .filter(_.id === userId.toString)
      .delete
      .map(tott)
  }
  
}
