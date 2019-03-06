package entities

object BoardMap {
  val mapping = Map((0-> "Start"))
  def translate(n: Int): String = mapping.getOrElse(n,n.toString())
}
