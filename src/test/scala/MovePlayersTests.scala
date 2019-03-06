
import entities.{Player, Position}
import infrastructure.{CommandParser, DataBase}
import org.scalatest.FlatSpec
import org.scalatest._
import Matchers._

class MovePlayersTests extends FlatSpec {
  implicit val ctx: DataBase = new DataBase()

  behavior of "If there are two participants \"Pippo\" and \"Pluto\" on space \"Start\" the system"
  val commandText = "move Pippo 4, 2"
  val response = "Pippo rolls 4, 2. Pippo moves from Start to 6"
  it should s"responds: $response if the user writes: $commandText" in {
    val command = CommandParser getCommandFrom commandText
    val pippo = Player("Pippo")
    val pluto = Player("Pluto")

    ctx.players += pippo
    ctx.players += pluto
    ctx.positions+= Position(pluto,0)
    ctx.positions+= Position(pippo,0)

    command.execute should contain (response)
    ctx.positions should contain only (Position(pippo, 6),Position(pluto, 0))
  }

}
