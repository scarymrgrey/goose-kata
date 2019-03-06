package commands

import entities.{Cell, Player, Position}
import infrastructure.DataBase

case class AddPlayerCommand(name: String) extends CommandBase[Option[String]] {
  override def execute()(implicit ctx: DataBase): Option[String] =
  {
    require(!ctx.players.contains(Player(name)), s"$name: already existing player")
    val player = Player(name)
    ctx.players += player
    ctx.positions += Position(player,Cell(0))
    Some(s"players: ${ctx.players.map(_.name).mkString(", ")}")
  }
}
