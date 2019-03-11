package commands

import entities.Dice
import infrastructure.DataBase

case class MoveWithDiceCommand(name: String,dice: Dice) extends CommandBase[Either[String, String]] {

  override def execute()(implicit ctx: DataBase): Either[String, String] = {
    val sides = dice.getSides
    MovePlayerCommand(name, sides._1, sides._2) execute
  }

}
