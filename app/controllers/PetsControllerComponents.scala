package controllers

import javax.inject.Inject
import play.api.http.FileMimeTypes
import play.api.i18n._
import play.api.mvc._
import services.MockableTime

import scala.concurrent.ExecutionContext

case class PetsControllerComponents @Inject() (
  // Default
  actionBuilder:    DefaultActionBuilder,
  parsers:          PlayBodyParsers,
  messagesApi:      MessagesApi,
  langs:            Langs,
  fileMimeTypes:    FileMimeTypes,
  executionContext: ExecutionContext,
  // Extended
  mockableTime:     MockableTime,
) extends ControllerComponents
