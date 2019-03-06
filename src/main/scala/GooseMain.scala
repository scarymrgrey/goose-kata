import infrastructure.{CommandParser, DataBase, DataBaseInstance}
import scala.util.{Failure, Success, Try}

object GooseMain extends App {

  implicit val ctx: DataBase = DataBaseInstance

  while (execute(scala.io.StdIn.readLine())) {}

  def execute(input: String): Boolean =
    (Try(CommandParser.getCommandFrom(input) execute) match {
      case Success(x) =>
        println(x)
        true
      case Failure(x) => false
    }).get
}
