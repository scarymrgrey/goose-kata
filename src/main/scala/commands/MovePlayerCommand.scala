package commands

import entities.{Position}
import infrastructure.DataBase
import entities.BoardMap._

case class MovePlayerCommand(name: String, dice1: Int, dice2: Int) extends CommandBase[Option[String]] {
  override def execute()(implicit ctx: DataBase): Option[String] = {
    val player = ctx.players.filter(r => r.name == name).headOption
    require(player.isDefined, s"no player with name $name")


    replacePosition(name)(x => Position(x.player, x.cell + dice1 + dice2)) match {
      case Some((oldPos, newPos)) =>
        Some(s"${newPos.player.name} rolls $dice1, $dice2. ${newPos.player.name} moves from ${oldPos.cell} to ${newPos.cell}")

      case _ => Some("Can not make a move")
    }
  }

  def replacePosition(name: String)(f: Position => Position)(implicit ctx: DataBase): Option[(Position, Position)] = {
    ctx.positions.filter(oldPos => oldPos.player.name == name).headOption map {
      case pl =>
        val newPos = f(pl)
        ctx.positions.update(ctx.positions.indexOf(pl), newPos)
        (pl, newPos)
    }
  }
}
