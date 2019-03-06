package infrastructure

import commands.{AddPlayerCommand, CommandBase, UnknownCommand}

object CommandParser {
  def getCommand(input: String): CommandBase[Option[String]] = {
    val addPlayerPattern = """add\s+player\s+(\w+)\s*""".r
    input match {
      case addPlayerPattern(name) => AddPlayerCommand(name)
      case _ => UnknownCommand()
    }
  }
}
