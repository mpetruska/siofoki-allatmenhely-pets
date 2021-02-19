package dataaccess

import extensions.db.DbRunHelpers
import javax.inject._
import models.db.Tables
import models.db.Tables._
import play.api.db.slick._
import slick.jdbc.JdbcProfile
import slick.lifted

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AnimalDatabase @Inject()
(val dbConfigProvider: DatabaseConfigProvider)
(implicit val ec: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with DbRunHelpers[JdbcProfile] {

  import profile.api._

  val animals = lifted.TableQuery[Tables.PetsAnimal]
  
  def listAllAnimals(): Future[Seq[PetsAnimalRow]] = db.run(dbioListAllAnimals())
  def dbioListAllAnimals(): DBIO[Seq[PetsAnimalRow]] = {
    animals.result
  }
  
}
