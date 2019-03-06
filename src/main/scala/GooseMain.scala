import infrastructure.{CommandParser, DataBase, DataBaseInstance}

object GooseMain extends App {

  while (execute(scala.io.StdIn.readLine())) {}

  def execute(input: String): Boolean = {
    implicit val ctx: DataBase = DataBaseInstance

    CommandParser.getCommand(input) execute match {
      case Some(res) =>
        println(res)
        true
      case None =>
        false
    }

  }

}
