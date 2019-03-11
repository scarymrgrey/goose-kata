
import entities.{Player, Position}
import infrastructure.{CommandParser, DataBase}
import org.scalatest.Matchers._
import org.scalatest.{FlatSpec, _}

import scala.collection.mutable.ArrayBuffer

class WinsPlayersTests extends FlatSpec {
  implicit val ctx: DataBase = new DataBase()
  val pippo = Player("Pippo")

  ctx.players += pippo
  ctx.positions += Position(pippo, 60)

  behavior of "If there is one participant \"Pippo\" on space \"60\" the system"

  val steps = Array(
    ("move Pippo 1, 2", "Pippo rolls 1, 2. Pippo moves from 60 to 63. Pippo Wins!!")
  )

  for (step <- steps) {
    val commandText = step._2.toString
    it should s"responds: $commandText  if the user writes: ${step._1}" in {
      val command = CommandParser getCommandFrom step._1
      command.execute should equal(Right(commandText))

      ctx.positions should contain only Position(pippo, 63)
    }
  }

}
