package models.json

import java.time.LocalDate
import java.util.UUID

import extensions.formats._
import extensions.uuid._
import models.db.Tables
import play.api.libs.json._
import scalaz.syntax.std.option._

case class AnimalBaseData(
  id:                    UUID,
  name:                  String,
  sex:                   Option[Sex.Value],
  breed:                 Option[String],
  color:                 Option[String],
  hairStyle:             Option[HairStyle.Value],
  isBirthDateEstimated:  Boolean,
  dateOfBirth:           Option[LocalDate],
  heightAtWithers:       Option[Int],
  chip:                  Option[String],
  vaccinationBookNumber: Option[String],
  placeOfInclusion:      Option[String],
  dateOfInclusion:       Option[LocalDate],
  contextOfInclusion:    Option[String],
  character:             Option[String],
  isNeutered:            Option[Boolean],
  dateOfNeutering:       Option[LocalDate],
  dateOfAdoption:        Option[LocalDate],
  dateOfDeath:           Option[LocalDate],
)

object AnimalBaseData {
  implicit val jsonFormat: OFormat[AnimalBaseData] = Json.format
}

case class AnimalVariables(
  status:  Option[AnimalStatus.Value],
  ownerId: Option[UUID],
  comment: Option[String],
)

object AnimalVariables {
  implicit val jsonFormat: OFormat[AnimalVariables] = Json.format
}


case class Animal(
  baseData:  AnimalBaseData,
  variables: AnimalVariables,
)

object Animal {

  implicit val jsonFormat: OFormat[Animal] = flattenedFormat[Animal](_.baseData, _.variables)(Animal.apply)

  def fromRow(x: Tables.PetsAnimalRow): Option[Animal] = {
    val base = for {
      id <- uuidFromString(x.id)
    } yield AnimalBaseData(
      id                    = id,
      name                  = x.name,
      sex                   = x.sex.flatMap(Sex.parseName),
      breed                 = x.breed,
      color                 = x.color,
      hairStyle             = x.hairStyle.flatMap(HairStyle.parseName),
      isBirthDateEstimated  = x.isBirthDateEstimated,
      dateOfBirth           = x.dateOfBirth.map(_.toLocalDate()),
      heightAtWithers       = x.heightAtWithers,
      chip                  = x.chip,
      vaccinationBookNumber = x.vaccinationBookNumber,
      placeOfInclusion      = x.placeOfInclusion,
      dateOfInclusion       = x.dateOfInclusion.map(_.toLocalDate()),
      contextOfInclusion    = x.contextOfInclusion,
      character             = x.character,
      isNeutered            = x.isNeutered.some,
      dateOfNeutering       = x.dateOfNautering.map(_.toLocalDate()),
      dateOfAdoption        = x.dateOfAdoption.map(_.toLocalDate()),
      dateOfDeath           = x.dateOfDeath.map(_.toLocalDate()),
    )

    val variables = AnimalVariables(
      status  = x.status.flatMap(AnimalStatus.parseName),
      ownerId = x.ownerId.flatMap(uuidFromString),
      comment = x.comment,
    )

    base.map(Animal(_, variables))
  }

}
