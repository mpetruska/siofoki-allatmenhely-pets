package extensions.combinators

import scalaz._
import scalaz.Id.Id
import scalaz.syntax.either._
import scalaz.syntax.std.option._

// import scala.language.{higherKinds, reflectiveCalls}

class TransformerCombinators[F[_], G[_]](lam: F ~> G) {

  // --- OptionT

  type OptionTStack[A] = OptionT[G, A]

  def optionT[A]   (x: F[Option[A]]): OptionT[G, A] = OptionT(lam(x))
  def fromOption[A](x: Option[A])(implicit M: Applicative[F]): OptionT[G, A] = OptionT(lam(M.point(x)))
  def some[A]      (x: F[A])     (implicit M: Functor[F]): OptionT[G, A] = OptionT(lam(M.map(x)(_.some)))

  def composeOptionT: TransformerCombinators[FOption[F]#λ, OptionTStack] = new TransformerCombinators[FOption[F]#λ, OptionTStack](new (FOption[F]#λ ~> OptionTStack) {
    def apply[A](x: F[Option[A]]): OptionTStack[A] = optionT(x)
  })

  def composeFromOption(implicit M: Applicative[F]): TransformerCombinators[Option, OptionTStack] = new TransformerCombinators[Option, OptionTStack](new (Option ~> OptionTStack) {
    def apply[A](x: Option[A]): OptionTStack[A] = fromOption(x)
  })

  def composeSome(implicit M: Functor[F]): TransformerCombinators[F, OptionTStack] = new TransformerCombinators[F, OptionTStack](new (F ~> OptionTStack) {
    def apply[A](x: F[A]): OptionTStack[A] = some(x)
  })

  // --- EitherT

  type EitherTStack[E] = { type λ[A] = EitherT[E, G, A] }

  def eitherT[E, A](x: F[E \/ A]): EitherT[E, G, A] = EitherT(lam(x))
  def getOrError[E, A](x: F[Option[A]], error: => E)(implicit M: Functor[F]): EitherT[E, G, A] =
    EitherT(lam(M.map(x)(_.cata(some = _.right, error.left))))
  def eitherFromOption[E, A](x: Option[A], error: => E)(implicit M: Applicative[F]): EitherT[E, G, A] =
    EitherT(lam(M.point(x.cata(_.right, error.left))))
  def right[E, A](x: F[A])(implicit M: Applicative[F], N: Functor[G]): EitherT[E, G, A] = EitherT.rightT[E, G, A](lam(x))

  def composeEitherT[E]: TransformerCombinators[FEither[F, E]#λ, EitherTStack[E]#λ] = new TransformerCombinators[FEither[F, E]#λ, EitherTStack[E]#λ](new (FEither[F, E]#λ ~> EitherTStack[E]#λ) {
    def apply[A](x: F[E \/ A]): EitherT[E, G, A] = eitherT(x)
  })

  def composeGetOrError[E](error: E)(implicit M: Functor[F]): TransformerCombinators[FOption[F]#λ, EitherTStack[E]#λ] = new TransformerCombinators[FOption[F]#λ, EitherTStack[E]#λ](new (FOption[F]#λ ~> EitherTStack[E]#λ) {
    def apply[A](x: F[Option[A]]): EitherT[E, G, A] = getOrError(x, error)
  })

  def composeEitherFromOption[E](error: E)(implicit M: Applicative[F]): TransformerCombinators[Option, EitherTStack[E]#λ] = new TransformerCombinators[Option, EitherTStack[E]#λ](new (Option ~> EitherTStack[E]#λ) {
    def apply[A](x: Option[A]): EitherT[E, G, A] = eitherFromOption(x, error)
  })

  def composeRight[E](implicit M: Applicative[F], N: Functor[G]): TransformerCombinators[F, EitherTStack[E]#λ] = new TransformerCombinators[F, EitherTStack[E]#λ](new (F ~> EitherTStack[E]#λ) {
    def apply[A](x: F[A]): EitherT[E, G, A] = right(x)
  })

  // --- ListT

  type ListTStack[A] = ListT[G, A]

  def listT[A](x: F[Seq[A]])(implicit F: Functor[F],     G: Functor[G]): ListT[G, A] = ListT.fromList(lam(F.map(x)(_.toList)))
  def fromSeq[A](x: Seq[A]) (implicit F: Applicative[F], G: Functor[G]): ListT[G, A] = ListT.fromList(lam(F.point(x.toList)))
  def fromSingle[A](x: F[A])(implicit F: Functor[F],     G: Functor[G]): ListT[G, A] = ListT.fromList(lam(F.map(x)(List(_))))
  def fromSingular[A](x: A) (implicit F: Applicative[F], G: Functor[G]): ListT[G, A] = fromSeq(List(x))

  def composeListT(implicit F: Functor[F], G: Functor[G]): TransformerCombinators[FSeq[F]#λ, ListTStack] = new TransformerCombinators[FSeq[F]#λ, ListTStack](new (FSeq[F]#λ ~> ListTStack) {
    def apply[A](x: F[Seq[A]]): ListTStack[A] = listT[A](x)
  })

  def composeFromSeq(implicit F: Applicative[F], G: Functor[G]): TransformerCombinators[Seq, ListTStack] = new TransformerCombinators[Seq, ListTStack](new (Seq ~> ListTStack) {
    def apply[A](x: Seq[A]): ListTStack[A] = fromSeq(x)
  })
    
  def composeFromSingle(implicit F: Functor[F], G: Functor[G]): TransformerCombinators[F, ListTStack] = new TransformerCombinators[F, ListTStack](new (F ~> ListTStack) {
    def apply[A](x: F[A]): ListTStack[A] = fromSingle(x)
  })

  def composeFromSingular(implicit F: Applicative[F], G: Functor[G]): TransformerCombinators[Id, ListTStack] = new TransformerCombinators[Id, ListTStack](new (Id ~> ListTStack) {
    def apply[A](x: A): ListTStack[A] = fromSingular(x)
  })

}
