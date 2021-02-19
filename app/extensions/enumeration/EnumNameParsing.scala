package extensions.enumeration

trait EnumNameParsing {

  type Value
  def values: Set[Value]

  def parseName(x: String): Option[Value] = values.find(_.toString == x)
  def parseNameCaseInsensitive(x: String): Option[Value] = values.find(_.toString.toUpperCase() == x.toUpperCase())

}
