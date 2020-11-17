package extensions

import play.api.libs.json._
import scalaz.Applicative

import scala.concurrent.Future

package object formats {

  implicit val jsResultApplicative: Applicative[JsResult] = new Applicative[JsResult] {
    def point[A](a: => A): JsResult[A] = JsSuccess(a)
    def ap[A, B](fa: => JsResult[A])(f: => JsResult[A => B]): JsResult[B] = fa.flatMap(v => f.map(_.apply(v)))
  }

  implicit class JsResultOps[A](val x: JsResult[A]) extends AnyVal {
    def asFuture: Future[A] = Future.fromTry(JsResult.toTry(x))
  }

  def read[A](x: JsValue)(implicit format: OFormat[A]): JsResult[A] = format.reads(x)
  def write[A](a: A)(implicit format: OFormat[A]): JsObject = format.writes(a)

  def flattenedFormat[J]: FlattenedFormatBuilder[J] = new FlattenedFormatBuilder

}
