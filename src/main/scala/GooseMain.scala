import infrastructure.{CommandParser, CommandParserFactory, DataBase, DataBaseInstance}

import scala.util.{Failure, Success, Try}

object GooseMain extends App {

  implicit val ctx: DataBase = DataBaseInstance

  while (execute(scala.io.StdIn.readLine())) {}

  def execute(input: String): Boolean = {
    val commandResult = Try(CommandParserFactory.default.getCommandFrom(input) execute) recover {
      case exception: Exception => Left(exception.getMessage)
    }

    commandResult get match {
      case Left(x) => println(x); true
      case Right(x) => println(x); false
    }
  }
}
