package models.forms

import play.api.data._
import play.api.data.Forms._

case class UpdateUser(
  fullName:       Option[String],
  isAdmin:        Boolean,
  isEnabled:      Boolean,
)

object UpdateUser {
  
  val form: Form[UpdateUser] = Form(
    mapping(
      "fullName"       -> optional(text),
      "isAdmin"        -> boolean,
      "isEnabled"      -> boolean,
    )(apply)(unapply)
  )

}
