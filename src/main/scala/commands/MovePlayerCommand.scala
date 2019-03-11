package commands

import entities.{FinishCell, Position}
import infrastructure.DataBase

case class MovePlayerCommand(name: String, dice1: Int, dice2: Int) extends CommandBase[Option[String]] {
  override def execute()(implicit ctx: DataBase): Option[String] = {
    val player = ctx.players.find(r => r.name == name)
    require(player.isDefined, s"no player with name $name")

    replacePosition(name)(x => x.moveOn(dice1 + dice2)._1) map {

      case (oldPos, newPos) => {
        val value = s"${newPos.player} rolls $dice1, $dice2. ${newPos.player} moves from ${oldPos.cell} to ${newPos.cell}"
        newPos.cell match {
          case FinishCell => value.concat(s". ${newPos.player} Wins!!")
          case _ => value.concat(s". ${newPos.player} Wins!!")
        }
      }
      case _ => "Can not make a move"
    }
  }

  def replacePosition(name: String)(f: Position => Position)(implicit ctx: DataBase): Option[(Position, Position)] = {
    ctx.positions.find(oldPos => oldPos.player.name == name) map {
      pl =>
        val newPos = f(pl)
        ctx.positions.update(ctx.positions.indexOf(pl), newPos)
        (pl, newPos)
    }
  }
}
