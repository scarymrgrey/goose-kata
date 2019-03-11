package entities

object BoardMap {
  val mapping: Map[Int, String] = Map(0-> "Start",6 -> "The Bridge")
  def translate(n: Int): String = mapping.getOrElse(n,n.toString)
}
