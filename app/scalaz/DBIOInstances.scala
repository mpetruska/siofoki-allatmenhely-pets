package scalaz

import slick.dbio.DBIO

import scala.concurrent.ExecutionContext

object DBIOInstances {

  implicit def dbioInstance(implicit executor: ExecutionContext): Monad[DBIO] = new Monad[DBIO] {

    def point[A](a: => A): DBIO[A] = DBIO.successful(a)
    def bind[A, B](fa: DBIO[A])(f: A => DBIO[B]) = fa.flatMap(f)

  }

}
