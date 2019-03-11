package entities

trait Dice {
  def getSides: (Int, Int)
}

class RandomDice extends Dice {
  override def getSides: (Int, Int) = {
    (between(1, 6), between(1, 6))
  }

  private def between(n1: Int, n2: Int): Int = {
    val r = new scala.util.Random
    n1 + r.nextInt((n2 - n1) + 1)
  }
}
