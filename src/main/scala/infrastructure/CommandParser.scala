package infrastructure

import commands._
import entities.{Dice, RandomDice}

object CommandParserFactory {
  def default = CommandParser(new RandomDice)
}

case class CommandParser(dice: Dice) {
  def getCommandFrom(input: String): CommandBase[Either[String, String]] = {
    val addPlayerPattern = """add\s+player\s+(\w+)\s*""".r
    val movePlayerPattern = """move\s+(\w+)\s+(\d)\s*,\s*(\d)""".r
    val moveWithDicePattern = """move\s+(\w+)\s*""".r
    val quit = """q""".r
    input match {
      case addPlayerPattern(name) => AddPlayerCommand(name)
      case movePlayerPattern(name, dice1, dice2) => MovePlayerCommand(name, dice1.toInt, dice2.toInt)
      case moveWithDicePattern(name) => MoveWithDiceCommand(name, dice)
      case quit() => QuitCommand()
      case _ => UnknownCommand()
    }
  }
}
