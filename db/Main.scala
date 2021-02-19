import java.net.URI

import slick.basic.DatabaseConfig
import slick.model._
import slick.codegen._
import slick.dbio.DBIO
import slick.model.Model
import slick.jdbc.JdbcProfile
import slick.jdbc.meta.MTable

import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration.Duration

object Main extends App {

  val profile    = "slick.jdbc.MySQLProfile"
  val jdbcDriver = "com.mysql.cj.jdbc.Driver"
  val url        = "jdbc:mysql://localhost:3306/pets?zeroDateTimeBehavior=convertToNull"
  val outputDir  = "../app/"
  val pkg        = "models.db"
  val user       = "pets-user"
  val password   = "pets-password"

  val profileInstance = Class.forName(profile + "$").getField("MODULE$").get(null).asInstanceOf[JdbcProfile]
  val dbFactory       = profileInstance.api.Database

  val db     = dbFactory.forURL(url, driver = jdbcDriver, user = user, password = password, keepAliveConnection = true)
  val models = Await.result(db.run(profileInstance.createModel(Some(MTable.getTables(None, Some("dbo"), None, Some(Seq("TABLE", "VIEW")))), true)(ExecutionContext.global).withPinnedSession), Duration.Inf)

  val generator = new SourceCodeGenerator(models)

  println("Generating Slick models...")
  generator.writeToMultipleFiles(profile, outputDir, pkg)
  println("Slick models generated!")
}
