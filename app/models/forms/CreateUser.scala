package models.forms

import play.api.data._
import play.api.data.Forms._

case class CreateUser(
  username:       String,
  fullName:       Option[String],
  password:       String,
  passwordRepeat: String,
  isAdmin:        Boolean,
  isEnabled:      Boolean,
)

object CreateUser {
  
  val form: Form[CreateUser] = Form(
    mapping(
      "username"       -> nonEmptyText,
      "fullName"       -> optional(text),
      "password"       -> nonEmptyText,
      "passwordRepeat" -> nonEmptyText,
      "isAdmin"        -> boolean,
      "isEnabled"      -> boolean,
    )(apply)(unapply)
  )

}