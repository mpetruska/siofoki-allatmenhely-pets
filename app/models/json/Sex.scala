package models.json

import extensions.enumeration._
import play.api.libs.json._

object Sex extends Enumeration with EnumNameParsing {

  val Male, Female = Value

  implicit val jsonFormat: Format[Value] = enumFormat(Sex)

}
