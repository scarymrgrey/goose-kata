package infrastructure

import commands.{AddPlayerCommand, CommandBase, MovePlayerCommand, UnknownCommand}

object CommandParser {
  def getCommandFrom(input: String): CommandBase[Option[String]] = {
    val addPlayerPattern = """add\s+player\s+(\w+)\s*""".r
    val movePlayerPattern = """move\s+(\w+)\s+(\d)\s*,\s*(\d)""".r
    input match {
      case addPlayerPattern(name) => AddPlayerCommand(name)
      case movePlayerPattern(name,dice1,dice2) => MovePlayerCommand(name,dice1.toInt,dice2.toInt)
      case _ => UnknownCommand()
    }
  }
}
