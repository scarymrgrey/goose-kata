package commands

import entities.{ Player, Position}
import infrastructure.DataBase

case class AddPlayerCommand(name: String) extends CommandBase[Either[String, String]] {
  override def execute()(implicit ctx: DataBase): Either[String, String] =
  {
    require(!ctx.players.contains(Player(name)), s"$name: already existing player")
    val player = Player(name)
    ctx.players += player
    ctx.positions += Position(player,0)
    Left(s"players: ${ctx.players.map(_.name).mkString(", ")}")
  }
}
