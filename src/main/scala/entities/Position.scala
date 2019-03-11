package entities

import entities.BoardMap._

sealed abstract class BaseCell {
  val n: Int

  override def toString: String = translate(n)

  def moveOn(n: Int): BaseCell = this
}

case class Position(player: Player, cell: BaseCell) {
  def moveOn(n: Int): (Position, String) = cell.moveOn(n) match {
    case FinishCell => (Position(player, FinishCell), "")
    case `cell` => (Position(player, cell), "")
  }
}

case object FinishCell extends BaseCell {
  val n = 63
}

case class Cell(n: Int) extends BaseCell {
  override def moveOn(n: Int): BaseCell = this.n + n match {
    case FinishCell.n => FinishCell
    case a if a > FinishCell.n => Cell(a % FinishCell.n)
    case 6 => Cell(12)
    case x => Cell(x)
  }
}
