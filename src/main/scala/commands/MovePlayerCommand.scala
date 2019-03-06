package commands

import entities.{Player, Position}
import infrastructure.DataBase

case class MovePlayerCommand(name: String, dice1: Int, dice2: Int) extends CommandBase[Option[String]] {
  override def execute()(implicit ctx: DataBase): Option[String] =
    if (!ctx.players.contains(Player(name))) {
      Some(s"$name: already existing player")
    } else {
      val player = Player(name)
      ctx.players += player
      ctx.positions += Position(player, 0)
      Some(s"players: ${ctx.players.map(_.name).mkString(", ")}")
    }

}
