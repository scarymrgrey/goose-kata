
import entities.{Dice, Player, Position}
import infrastructure.{CommandParser, CommandParserFactory, DataBase}
import org.scalatest.FlatSpec
import org.scalatest.Matchers._


class GooseCellsTests extends FlatSpec {
  implicit val ctx: DataBase = new DataBase()
  val pippo = Player("Pippo")


  behavior of "If there is one participant \"Pippo\" on space \"3\" " +
    "assuming that the dice get 1 and 1 " +
    "the system"

  {
    implicit val ctx: DataBase = new DataBase()
    ctx.players += pippo
    ctx.positions += Position(pippo, 3)
    val steps = ("move Pippo", "Pippo rolls 1, 1. Pippo moves from 3 to 5, The Goose. Pippo moves again and goes to 7")
    val responseText = steps._2.toString
    it should s"responds: $responseText  if the user writes: ${steps._1}" in {
      val commandParser = CommandParser(new Dice{
        override def getSides: (Int, Int) = (1,1)
      })
      val command = commandParser getCommandFrom steps._1
      command.execute should equal(Left(responseText))

      ctx.positions should contain only Position(pippo, 7)
    }
  }

  {
    implicit val ctx: DataBase = new DataBase()
    ctx.players += pippo
    ctx.positions += Position(pippo, 10)

    behavior of "If there is one participant \"Pippo\" on space \"10\" " +
      "assuming that the dice get 2 and 2 " +
      "the system"
    val testText = ("move Pippo 2, 2", "Pippo rolls 2, 2. Pippo moves from 10 to 14, The Goose. Pippo moves again and goes to 18, The Goose. Pippo moves again and goes to 22")

    val commandText = testText._2.toString
    it should s"responds: $commandText  if the user writes: ${testText._1}" in {
      val commandParser = CommandParser(new Dice{
        override def getSides: (Int, Int) = (2,2)
      })
      val command = commandParser getCommandFrom testText._1
      command.execute should equal(Left(commandText))

      ctx.positions should contain only Position(pippo, 22)
    }
  }

}
