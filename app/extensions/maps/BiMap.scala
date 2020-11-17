package extensions.maps

case class BiMap[A, B] private (from: Map[A, B], to: Map[B, A])

object BiMap {

  def empty[A, B]: BiMap[A, B] = BiMap()

  def apply[A, B](values: (A, B)*): BiMap[A, B] = {
    BiMap(
      from = Map(values: _*),
      to   = Map(values.map(_.swap): _*)
    )
  }

}
