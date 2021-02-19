package models.json

import extensions.enumeration._
import play.api.libs.json._

object HairStyle extends Enumeration with EnumNameParsing {

  val MediumCoated, WireCoated, LongCoated = Value

  implicit val jsonFormat: Format[Value] = enumFormat(HairStyle)

}
