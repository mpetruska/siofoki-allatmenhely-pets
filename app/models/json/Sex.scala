package models.json

import extensions.enumeration._
import extensions.maps.BiMap
import play.api.libs.json._

object Sex extends Enumeration {

  val MALE, FEMALE = Value

  val dbValueMap: BiMap[String, Value] = BiMap(
    "SE0" -> MALE,
    "SE1" -> FEMALE,
  )

  implicit val jsonFormat: Format[Value] = enumFormat(Sex)

  def fromDbValue(x: String): Option[Value]  = dbValueMap.from.get(x)
  def toDbValue  (x: Value):  Option[String] = dbValueMap.to  .get(x)

}
