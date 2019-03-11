import infrastructure.{CommandParser, CommandParserFactory, DataBase, DataBaseInstance}

import scala.util.{Failure, Success, Try}

object GooseMain extends App {

  implicit val ctx: DataBase = DataBaseInstance
  println(">the goose game (q for quit)<")
  while (execute(scala.io.StdIn.readLine("command: "))) {}
  println("exiting...")

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
