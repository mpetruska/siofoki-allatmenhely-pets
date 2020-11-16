package extensions

import play.api.libs.json._
import scalaz.syntax.std.option._

package object enumeration {
  
  def enumReads[E <: Enumeration](enum: E): Reads[E#Value] = new Reads[E#Value] {
    def reads(json: JsValue): JsResult[E#Value] = json match {
      case JsString(s) =>
        enum.values.find(_.toString() == s).cata(
          some = JsSuccess.apply(_),
          none = JsError(s"Enumeration expected of type: '${enum.getClass}', but it does not appear to contain the value: '$s'")
        )
      case _           => JsError("String value expected")
    }
  }

  def enumWrites[E <: Enumeration]: Writes[E#Value] = new Writes[E#Value] {
    def writes(v: E#Value): JsValue = JsString(v.toString)
  }

  def enumFormat[E <: Enumeration](enum: E): Format[E#Value] = Format(enumReads(enum), enumWrites)

}
