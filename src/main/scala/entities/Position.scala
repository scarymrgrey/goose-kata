package entities

import entities.BoardMap._

sealed abstract class BasePosition {
  val player: Player
  val cell: Int

  override def toString: String = translate(cell)

  override def equals(obj: Any): Boolean = obj match {
    case bp : BasePosition => this.player.name == bp.player.name
    case _ => super.equals(obj)
  }

  def moveOn(n: Int): (BasePosition, String) = {
    val text = s"$player moves from ${translate(cell)} to ${translate(cell + n)}"

    def moveOnInner(n: Int) = cell + n match {
      case 63 => (WinPosition(player, 63), text.concat(s". $player Wins!!"))
      case a if a > 63 => (Position(player, 63), text)
      case 6 => (Position(player, 12), text.concat(s". $player jumps to 12"))
      case x => (Position(player, x), text)
    }

    moveOnInner(n)
  }
}

case class Position(player: Player, cell: Int) extends BasePosition

case class WinPosition(player: Player, cell: Int) extends BasePosition
