package controllers

import dataaccess.DogDatabase
// import extensions.combinators._
import javax.inject._
import models.json.Dog
import play.api.libs.json.Json
import play.api.mvc._
// import scalaz.std.scalaFuture._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SearchController @Inject()(
  controllerComponents: PetsControllerComponents,
  dogsDatabase:         DogDatabase,
) extends AbstractController(controllerComponents) {

  implicit val executor: ExecutionContext = controllerComponents.executionContext

  def listAllDogs() = Action.async {
    // (for {
    //   dogRow <- future.listT(dogsDatabase.listAllDogs())
    //   dog    =  Dog.fromRow(dogRow)
    // } yield dog).run
    //   .map(result => Ok(Json.toJson(result.toList)))
    Future.successful(Ok(Json.toJson(Seq.empty[Dog])))
  }

}
