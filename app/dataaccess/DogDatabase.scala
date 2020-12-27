package dataaccess

import extensions.db.DbRunHelpers
import javax.inject._
// import models.db.Tables
// import models.db.Tables._
import play.api.db.slick._
import slick.jdbc.JdbcProfile
// import slick.lifted

// import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.ExecutionContext

@Singleton
class DogDatabase @Inject()
(val dbConfigProvider: DatabaseConfigProvider)
(implicit val ec: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with DbRunHelpers[JdbcProfile] {

  // import profile.api._

  // val dogs = lifted.TableQuery[Tables.Dogs]
  
  // def listAllDogs(): Future[Seq[DogsRow]] = db.run(dbioListAllDogs())
  // def dbioListAllDogs(): DBIO[Seq[DogsRow]] = {
  //   dogs.result
  // }
  
}
