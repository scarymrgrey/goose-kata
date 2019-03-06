package commands

import entities.{Player, Position}
import infrastructure.DataBase

case class MovePlayerCommand(name: String, dice1: Int, dice2: Int) extends CommandBase[Option[String]] {
  override def execute()(implicit ctx: DataBase): Option[String] = {
    require(ctx.players.contains(Player(name)), s"no player with name $name")

    val player = Player(name)
    ctx.players += player
    ctx.positions += Position(player, dice1+dice2)
    Some(s"players: ${ctx.players.map(_.name).mkString(", ")}")
  }
}
