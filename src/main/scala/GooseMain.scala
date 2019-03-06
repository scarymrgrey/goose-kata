import infrastructure.{CommandParser, DataBase, DataBaseInstance}

object GooseMain extends App {
  implicit val ctx: DataBase = DataBaseInstance

  while (execute(scala.io.StdIn.readLine())) {}

  def execute(input: String): Boolean = {
    CommandParser.getCommandFrom(input) execute match {
      case Some(res) =>
        println(res)
        true
      case None =>
        false
    }

  }

}
