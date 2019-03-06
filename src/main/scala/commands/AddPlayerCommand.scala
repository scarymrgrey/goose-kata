package commands

import entities.User
import infrastructure.DataBase

case class AddPlayerCommand(name: String) extends CommandBase[Option[String]] {
  override def execute()(implicit ctx: DataBase): Option[String] =
    if (ctx.users.contains(User(name))) {
      Some(s"$name: already existing player")
    } else {
      ctx.users += User(name)
      Some(s"players: ${ctx.users.map(_.name).mkString(", ")}")
    }

}
