package entities

import entities.BoardMap._

sealed abstract class BasePosition {
  val player: Player
  val cell: Int

  override def toString: String = translate(cell)

  override def equals(obj: Any): Boolean = obj match {
    case bp: BasePosition => this.player.name == bp.player.name && this.cell == bp.cell
    case _ => super.equals(obj)
  }

  def moveOn(n: Int): (BasePosition, String) = {
    val text = s"$player moves from ${translate(cell)} to ${translate(Math.min(cell + n, 63))}"

    def moveOnInner(prev: Int, n: Int, innerText: String): (BasePosition, String) = prev + n match {
      case 63 => (WinPosition(player, 63), innerText.concat(s". $player Wins!!"))
      case a if a > 63 => (Position(player, 63 - (a - 63)), s"${innerText}. $player bounces! $player returns to ${63 - (a - 63)}")
      case 6 => (Position(player, 12), s"${innerText}. $player jumps to 12")
      case sum@(5 | 9 | 14 | 18 | 23 | 27) => moveOnInner(sum, n, s"${innerText}, The Goose. $player moves again and goes to ${sum + n}")
      case x => (Position(player, x), innerText)
    }

    moveOnInner(cell, n, text)
  }
}

case class Position(player: Player, cell: Int) extends BasePosition

case class WinPosition(player: Player, cell: Int) extends BasePosition
