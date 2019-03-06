
import entities.{Cell, Player, Position}
import infrastructure.{CommandParser, DataBase}
import org.scalatest.Matchers._
import org.scalatest.{FlatSpec, _}

class WinsPlayersTests extends FlatSpec {
  implicit val ctx: DataBase = new DataBase()
  val pippo = Player("Pippo")

  ctx.players += pippo
  ctx.positions+= Position(pippo,Cell(60))

  behavior of "If there is one participant \"Pippo\" on space \"60\" the system"

  val steps = Array(
    ("move Pippo 1, 2","Pippo rolls 1, 2. Pippo moves from 60 to 63. Pippo Wins!!",63)
  )

  for (step <- steps) {
    val commandText = step._2.toString
    it should s"responds: ${step._1} if the user writes: ${commandText}" in {
      val command = CommandParser getCommandFrom step._1

      command.execute should contain (commandText)
      val pipposCell = Cell(step._3)
      ctx.positions should contain only (Position(pippo, pipposCell))
    }
  }

}
