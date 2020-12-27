package models.forms

import play.api.data._
import play.api.data.Forms._

case class UserLogin(username: String, password: String, isPermanent: Boolean)

object UserLogin {

  val form: Form[UserLogin] = Form(
    mapping(
      "username"    -> nonEmptyText,
      "password"    -> nonEmptyText,
      "isPermanent" -> boolean,
    )(apply)(unapply)
  )

}