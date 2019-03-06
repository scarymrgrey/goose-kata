package commands

import entities.Player
import infrastructure.DataBase

case class AddPlayerCommand(name: String) extends CommandBase[Option[String]] {
  override def execute()(implicit ctx: DataBase): Option[String] =
    if (ctx.players.contains(Player(name))) {
      Some(s"$name: already existing player")
    } else {
      val player = Player(name)
      ctx.players += player
      ctx.positions += ((player,0))
      Some(s"players: ${ctx.players.map(_.name).mkString(", ")}")
    }

}
