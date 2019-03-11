package entities

import scala.collection.mutable.ArrayBuffer

class Board(val positions: ArrayBuffer[BasePosition]) {

  def move(player: Player, n: Int): (BasePosition, String) =
    positions.find(r => r.player == player).map(oldPos => {
      val newPos = oldPos.moveOn(n)
      updatePos(oldPos, newPos._1)
      positions.find(r => r.player != player && r.cell == newPos._1.cell) match {
        case Some(collision) =>
          updatePos(collision, Position(collision.player, oldPos.cell))
          (newPos._1, s"${newPos._2}. On ${newPos._1.cell} there is ${collision.player.name}, who returns to ${oldPos.cell}")
        case None => newPos
      }
    }).get

  private def updatePos(oldP: BasePosition, newP: BasePosition): Unit = {
    positions.update(positions.indexOf(oldP), newP)
  }
}
