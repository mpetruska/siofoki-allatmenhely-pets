package extensions

package object aliases {
  
  val unit:          Unit = ()
  def void[A](a: A): Unit = unit

  val tt:            I = I()
  def tott[A](a: A): I = tt

  def not(b: Boolean): Boolean = !b
  def any(b: Boolean*): Boolean = b.exists(identity)

  def wholeConst[X, A](x: X): PartialFunction[A, X] = { case _ => x }

}
