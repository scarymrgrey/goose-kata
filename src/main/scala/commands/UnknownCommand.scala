package commands

import infrastructure.DataBase

case class UnknownCommand() extends CommandBase[Either[String, String]] {
  override def execute()(implicit ctx: DataBase): Either[String, String] = Left("Unknown command")
}
