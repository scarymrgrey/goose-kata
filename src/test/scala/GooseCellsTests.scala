
import entities.{Player, Position}
import infrastructure.{CommandParser, DataBase}
import org.scalatest.FlatSpec
import org.scalatest.Matchers._


class GooseCellsTests extends FlatSpec {
  implicit val ctx: DataBase = new DataBase()
  val pippo = Player("Pippo")

  ctx.players += pippo
  ctx.positions += Position(pippo, 3)

  behavior of "If there is one participant \"Pippo\" on space \"3\" " +
    "assuming that the dice get 1 and 1 " +
    "the system"

  val steps = Array(
    ("move Pippo 1, 1", "Pippo rolls 1, 1. Pippo moves from 3 to 5, The Goose. Pippo moves again and goes to 7")
  )

  for (step <- steps) {
    val commandText = step._2.toString
    it should s"responds: $commandText  if the user writes: ${step._1}" in {
      val command = CommandParser getCommandFrom step._1
      command.execute should equal(Right(commandText))

      ctx.positions should contain only Position(pippo, 63)
    }
  }

  ctx.players += pippo
  ctx.positions.clear()
  ctx.positions += Position(pippo, 10)
  behavior of "If there is one participant \"Pippo\" on space \"10\" " +
    "assuming that the dice get 2 and 2 " +
    "the system"
  val testText =
    ("move Pippo 2, 2", "Pippo rolls 2, 2. Pippo moves from 10 to 14, The Goose. Pippo moves again and goes to 18, The Goose. Pippo moves again and goes to 22Pippo rolls 1, 1. Pippo moves from 3 to 5, The Goose. Pippo moves again and goes to 7")

    val commandText = testText._2.toString
    it should s"responds: $commandText  if the user writes: ${testText._1}" in {
      val command = CommandParser getCommandFrom testText._1
      command.execute should equal(Right(commandText))

      ctx.positions should contain only Position(pippo, 63)
    }

}
