package models.json

import extensions.enumeration._
import extensions.maps.BiMap
import play.api.libs.json._

object HairStyle extends Enumeration {

  val MEDIUM_COATED, WIRE_COATED, LONG_COATED = Value

  val dbValueMap: BiMap[String, Value] = BiMap(
    "H0" -> MEDIUM_COATED,
    "H1" -> WIRE_COATED,
    "H2" -> LONG_COATED,
  )

  implicit val jsonFormat: Format[Value] = enumFormat(HairStyle)

  def fromDbValue(x: String): Option[Value]  = dbValueMap.from.get(x)
  def toDbValue  (x: Value):  Option[String] = dbValueMap.to  .get(x)

}
