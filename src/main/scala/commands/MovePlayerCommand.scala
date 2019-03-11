package commands

import entities.{BasePosition, Position, WinPosition}
import infrastructure.DataBase

case class MovePlayerCommand(name: String, dice1: Int, dice2: Int) extends CommandBase[Either[String, String]] {
  override def execute()(implicit ctx: DataBase): Either[String, String] = {
    val player = ctx.players.find(r => r.name == name)
    require(player.isDefined, s"no player with name $name")

    val value = s"$name rolls $dice1, $dice2. "
    (replacePosition(name)(x => x.moveOn(dice1 + dice2)) map {
      case (_: WinPosition, strRes) =>
        Right(value.concat(strRes))
      case (_: Position, strRes) =>
        Left(value.concat(strRes))
      case _ => Right("Can not make a move")
    }).get
  }

  def replacePosition(name: String)(f: BasePosition => (BasePosition, String))(implicit ctx: DataBase): Option[(BasePosition, String)] = {
    ctx.positions.find(oldPos => oldPos.player.name == name) map {
      pl =>
        val newPos = f(pl)
        ctx.positions.update(ctx.positions.indexOf(pl), newPos._1)
        newPos
    }
  }
}
