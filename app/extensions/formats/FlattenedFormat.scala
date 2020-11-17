package extensions.formats

import extensions.formats._
import play.api.libs.json._
import scalaz.syntax.applicative._

class FlattenedFormatBuilder[J] {

  def apply[A, B]      (fa: J => A, fb: J => B)                        : FlattenedFormatBuilder2[J, A, B]       = new FlattenedFormatBuilder2(fa, fb)
  def apply[A, B, C, D](fa: J => A, fb: J => B, fc: J => C, fd: J => D): FlattenedFormatBuilder4[J, A, B, C, D] = new FlattenedFormatBuilder4(fa, fb, fc, fd)

}

class FlattenedFormatBuilder2[J, A, B](fa: J => A, fb: J => B) {

  def apply(combine: (A, B) => J)(implicit A: OFormat[A], B: OFormat[B]): OFormat[J] = OFormat.apply[J](
    read  = (x: JsValue) => (read[A](x) |@| read[B](x))(combine),
    write = (x: J)       => Seq(write(fa(x)), write(fb(x))).foldLeft(JsObject.empty)(_ ++ _)
  )

}

class FlattenedFormatBuilder4[J, A, B, C, D](fa: J => A, fb: J => B, fc: J => C, fd: J => D) {

  def apply(combine: (A, B, C, D) => J)(implicit A: OFormat[A], B: OFormat[B], C: OFormat[C], D: OFormat[D]): OFormat[J] = OFormat.apply[J](
    read  = (x: JsValue) => (read[A](x) |@| read[B](x) |@| read[C](x) |@| read[D](x))(combine),
    write = (x: J)       => Seq(write(fa(x)), write(fb(x)), write(fc(x)), write(fd(x))).foldLeft(JsObject.empty)(_ ++ _)
  )

}
