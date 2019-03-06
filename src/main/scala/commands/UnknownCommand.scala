package commands

import infrastructure.DataBase

case class UnknownCommand() extends CommandBase[Option[String]] {
  override def execute()(implicit ctx: DataBase): Option[String] = Some("Unknown command")
}
