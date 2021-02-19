package models.json

import extensions.enumeration._
import play.api.libs.json._

object AnimalStatus extends Enumeration with EnumNameParsing {

  val Quarantine, SearchingOriginalOwner, Adoptable, HasPotentialAdopter, Adopted = Value

  implicit val jsonFormat: Format[Value] = enumFormat(AnimalStatus)

}