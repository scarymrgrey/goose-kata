import entities.Player
import infrastructure.{CommandParser, DataBase, DataBaseInstance}
import org.scalatest.FlatSpec

class MovePlayersTests extends FlatSpec {
  implicit val ctx: DataBase = DataBaseInstance

  behavior of "If there are two participants \"Pippo\" and \"Pluto\" on space \"Start\" the system"
  val commandText = "move Pippo 4, 2"
  val response = "Pippo rolls 4, 2. Pippo moves from Start to 6"
  it should s"responds: $response if the user writes: $commandText" in {
    val command = CommandParser getCommandFrom commandText
    val pippo = Player("Pippo")
    val pluto = Player("Pluto")

    ctx.players += pippo
    ctx.players += pluto
    ctx.positions+= ((pluto,0))
    ctx.positions+= ((pippo,0))

    assert(command.execute contains response)
    assert(ctx.positions contains ((pippo,6)))
  }

}
