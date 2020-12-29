package controllers

trait CommonErrors {
  val InternalServerError = "internal_server_error"
  val PleaseLogin         = "please_login"
}

object CommonErrors extends CommonErrors
