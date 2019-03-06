package entities

import entities.BoardMap._

case class Position(player: Player, cell: Cell)

case class Cell(n: Int) {
  override def toString: String = translate(n)

  def +(thatN: Int): Cell = Cell(this.n + thatN)
}
