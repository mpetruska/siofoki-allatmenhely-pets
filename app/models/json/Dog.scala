package models.json

import java.time.LocalDate

import extensions.formats._
import models.db.Tables
import play.api.libs.json._
import scalaz.syntax.std.option._

case class DogBaseData(
  id:                    Int,
  name:                  String,
  sex:                   Option[Sex.Value],
  breed:                 String,
  color:                 String,
  hairStyle:             Option[HairStyle.Value],
  isBirthDateEstimated:  Boolean,
  dateOfBirth:           Option[LocalDate],
  heightAtWithers:       Option[Int],
  chip:                  String,
  vaccinationBookNumber: String,
  placeOfInclusion:      String,
  dateOfInclusion:       Option[LocalDate],
  contextOfInclusion:    String,
  character:             String,
  isNeutered:            Boolean,
  dateOfNeutering:       Option[LocalDate],
  dateOfAdoption:        Option[LocalDate],
  dateOfDeath:           Option[LocalDate],
)

object DogBaseData {
  implicit val jsonFormat: OFormat[DogBaseData] = Json.format
}

case class DogVariables(
  status:  Option[AnimalStatus.Value],
  ownerId: Option[Int],
  comment: String,
  web:     String,
)

object DogVariables {
  implicit val jsonFormat: OFormat[DogVariables] = Json.format
}


case class Dog(
  baseData:  DogBaseData,
  variables: DogVariables,
)

object Dog {

  implicit val jsonFormat: OFormat[Dog] = flattenedFormat[Dog](_.baseData, _.variables)(Dog.apply)

  def fromRow(x: Tables.DogsRow): Dog = {
    val base = DogBaseData(
      id                    = x.dogId,
      name                  = x.dogName,
      sex                   = Sex.fromDbValue(x.sex),
      breed                 = x.breed,
      color                 = x.colour,
      hairStyle             = HairStyle.fromDbValue(x.hair),
      isBirthDateEstimated  = x.estimated,
      dateOfBirth           = x.bDate.map(_.toLocalDate()),
      heightAtWithers       = x.marm.some,
      chip                  = x.chip,
      vaccinationBookNumber = x.bookNbr,
      placeOfInclusion      = x.befPlace,
      dateOfInclusion       = x.befDate.map(_.toLocalDate()),
      contextOfInclusion    = x.befCond,
      character             = x.charact,
      isNeutered            = x.iv,
      dateOfNeutering       = x.ivDate.map(_.toLocalDate()),
      dateOfAdoption        = x.adoptDate.map(_.toLocalDate()),
      dateOfDeath           = x.dDate.map(_.toLocalDate()),
    )

    val variables = DogVariables(
      status  = AnimalStatus.fromDbValue(x.status),
      ownerId = x.ownerId.some,
      comment = x.comment,
      web     = x.web,
    )

    Dog(base, variables)
  }

}
