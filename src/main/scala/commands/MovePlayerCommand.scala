package commands

import entities.{Board, Position, WinPosition}
import infrastructure.DataBase

case class MovePlayerCommand(name: String, dice1: Int, dice2: Int) extends CommandBase[Either[String, String]] {
  override def execute()(implicit ctx: DataBase): Either[String, String] = {
    val player = ctx.players.find(r => r.name == name)
    require(player.isDefined, s"no player with name $name")

    val value = s"$name rolls $dice1, $dice2. "
    val board = new Board(ctx.positions)
    board.move(player.get, dice1 + dice2) match {
      case (_: WinPosition, strRes) => Right(value.concat(strRes))
      case (_: Position, strRes) => Left(value.concat(strRes))
      case _ => Left("Can not make a move")
    }
  }
}
