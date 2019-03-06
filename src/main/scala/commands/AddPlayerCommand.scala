package commands

import entities.User
import infrastructure.DataBase

case class AddPlayerCommand(name: String) extends CommandBase[Option[String]] {
  override def execute()(implicit ctx: DataBase): Option[String] = {
    ctx.users += User(name)
    Some(s"Added player: $name")
  }
}
