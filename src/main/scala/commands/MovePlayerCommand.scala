package commands

import entities.{Player, Position}
import infrastructure.DataBase

case class MovePlayerCommand(name: String, dice1: Int, dice2: Int) extends CommandBase[Option[String]] {
  override def execute()(implicit ctx: DataBase): Option[String] = {
    val player = ctx.players.filter(r => r.name == name).headOption
    require(player.isDefined, s"no player with name $name")

    updatePosition(name)(x => Position(x.player, dice1 + dice2)) match {
      case p: Position => Some(s"${p.player.name} rolls $dice1, $dice2. Pippo moves from Start to ${p.cell}")
    }
  }

  def updatePosition(name: String)(f: Position => Position)(implicit ctx: DataBase): Position = {
    ctx.positions.filter(oldPos => oldPos.player.name == name).headOption match {
      case Some(pl) =>
        val newPos = f(pl)
        ctx.positions.update(ctx.positions.indexOf(pl), newPos)
        newPos
    }
  }
}
