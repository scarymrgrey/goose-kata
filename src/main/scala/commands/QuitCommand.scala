package commands

import infrastructure.DataBase

case class QuitCommand() extends CommandBase[Either[String, String]] {
  override def execute()(implicit ctx: DataBase): Either[String, String] = Right("Bye")
}
