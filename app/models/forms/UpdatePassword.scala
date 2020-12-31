package models.forms

import play.api.data._
import play.api.data.Forms._

case class UpdatePassword(
  password:       String,
  passwordRepeat: String,
)

object UpdatePassword {

  val form: Form[UpdatePassword] = Form(
    mapping(
      "password"       -> nonEmptyText,
      "passwordRepeat" -> nonEmptyText,
    )(apply)(unapply)
  )

}