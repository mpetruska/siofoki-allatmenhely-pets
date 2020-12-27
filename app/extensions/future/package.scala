package extensions

import extensions.aliases._
import scalaz.syntax.std.boolean._
import scalaz.syntax.std.option._

import scala.concurrent.{ExecutionContext, Future}

package object future {
  
  def fail[A](errorMessage: String):   Future[A] = Future.failed(new Exception(errorMessage))
  def failIA[A](errorMessage: String): Future[A] = Future.failed(new IllegalArgumentException(errorMessage))

  def failIfNone[A](x: Option[A],         errorMessage: String): Future[A]                                      = x.cata(some = Future.successful, none = failIA(errorMessage))
  def failIfNone[A](x: Future[Option[A]], errorMessage: String)(implicit executor: ExecutionContext): Future[A] = x.flatMap(failIfNone(_, errorMessage))
  def failIfNone[A](x: Option[A],         error: Throwable    ): Future[A]                                      = x.cata(some = Future.successful, none = Future.failed(error))
  def failIfNone[A](x: Future[Option[A]], error: Throwable    )(implicit executor: ExecutionContext): Future[A] = x.flatMap(failIfNone(_, error))

  def failIfFalse(x: Boolean,         errorMessage: String): Future[I]                                      = x.fold(t = Future.successful(tt), f = failIA(errorMessage))
  def failIfFalse(x: Future[Boolean], errorMessage: String)(implicit executor: ExecutionContext): Future[I] = x.flatMap(failIfFalse(_, errorMessage))
  def failIfFalse(x: Boolean,         error: Throwable): Future[I]                                          = x.fold(t = Future.successful(tt), f = Future.failed(error))
  def failIfFalse(x: Future[Boolean], error: Throwable)(implicit executor: ExecutionContext): Future[I]     = x.flatMap(failIfFalse(_, error))

}
