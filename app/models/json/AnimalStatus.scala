package models.json

import extensions.enumeration._
import extensions.maps.BiMap
import play.api.libs.json._

object AnimalStatus extends Enumeration {

  val QUARANTINE, SEARCHING_ORIGINAL_OWNER, ADOPTABLE, HAS_POTENTIAL_ADPTER, ADOPTED = Value

  val dbValueMap: BiMap[String, Value] = BiMap(
    "ST0" -> QUARANTINE,
    "ST1" -> SEARCHING_ORIGINAL_OWNER,
    "ST2" -> ADOPTABLE,
    "ST3" -> HAS_POTENTIAL_ADPTER,
    "ST4" -> ADOPTED,
  )

  implicit val jsonFormat: Format[Value] = enumFormat(AnimalStatus)

  def fromDbValue(x: String): Option[Value]  = dbValueMap.from.get(x)
  def toDbValue  (x: Value):  Option[String] = dbValueMap.to  .get(x)

}