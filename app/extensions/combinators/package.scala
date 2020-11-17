package extensions

import slick.dbio.DBIO

import scalaz._
import scalaz.syntax.either._
import scalaz.syntax.std.option._

import scala.concurrent.Future

package object combinators {
  
  type FOption[F[_]]    = { type λ[A] = F[Option[A]] }
  type FSeq   [F[_]]    = { type λ[A] = F[   Seq[A]] }
  type FEither[F[_], E] = { type λ[A] = F[  E \/ A ] }

  val dbio:   TransformerCombinators[DBIO,   DBIO]   = new TransformerCombinators[DBIO,   DBIO]  (NaturalTransformation.refl)
  val future: TransformerCombinators[Future, Future] = new TransformerCombinators[Future, Future](NaturalTransformation.refl)
  
  // --- implicits

  implicit def functorFOption[F[_]](implicit M: Functor[F]): Functor[FOption[F]#λ] = new Functor[FOption[F]#λ] {
    def map[A, B](fa: F[Option[A]])(f: A => B): F[Option[B]] = M.map(fa)(_.map(f))
  }

  implicit def applicativeFOption[F[_]](implicit M: Applicative[F]): Applicative[FOption[F]#λ] = new Applicative[FOption[F]#λ] {
    def point[A](a: => A): F[Option[A]] = M.point(a.some)
    def ap[A, B](fa: => F[Option[A]])(f: => F[Option[A => B]]): F[Option[B]] = M.ap(fa)(M.map(f)(fs => _.flatMap(a => fs.map(_.apply(a)))))
  }

  implicit val functorSeq: Functor[Seq] = new Functor[Seq] {
    def map[A, B](fa: Seq[A])(f: A => B): Seq[B] = fa.map(f)
  }
  
  implicit def functorFSeq[F[_]](implicit M: Functor[F]): Functor[FSeq[F]#λ] = new Functor[FSeq[F]#λ] {
    def map[A, B](fa: F[Seq[A]])(f: A => B): F[Seq[B]] = M.map(fa)(_.map(f))
  }

  implicit def applicativeFSeq[F[_]](implicit M: Applicative[F]): Applicative[FSeq[F]#λ] = new Applicative[FSeq[F]#λ] {
    def point[A](a: => A): F[Seq[A]] = M.point(Seq(a))
    def ap[A, B](fa: => F[Seq[A]])(f: => F[Seq[A => B]]): F[Seq[B]] = M.ap(fa)(M.map(f)(fs => _.flatMap(a => fs.map(_.apply(a)))))
  }

  implicit def functorFEither[F[_], E](implicit M: Functor[F]): Functor[FEither[F, E]#λ] = new Functor[FEither[F, E]#λ] {
    def map[A, B](fa: F[E \/ A])(f: A => B): F[E \/ B] = M.map(fa)(_.map(f))
  }

  implicit def applicativeFEither[F[_], E](implicit M: Applicative[F]): Applicative[FEither[F, E]#λ] = new Applicative[FEither[F, E]#λ] {
    def point[A](a: => A): F[E \/ A] = M.point(a.right)
    def ap[A, B](fa: => F[E \/ A])(f: => F[E \/ (A => B)]): F[E \/ B] = M.ap(fa)(M.map(f)(fs => _.flatMap(a => fs.map(_.apply(a)))))
  }

}
