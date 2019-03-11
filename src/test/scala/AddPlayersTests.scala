import entities.Player
import infrastructure.{CommandParser, CommandParserFactory, DataBase, DataBaseInstance}
import org.scalatest.FlatSpec

class AddPlayersTests extends FlatSpec {
  implicit val ctx: DataBase = new DataBase()
  private val commandParser = CommandParserFactory.default
  behavior of "If there is no participant the system"
  it should "responds: \"players: Pippo\" if the user writes: \"add player Pippo\"" in {
    val command = commandParser getCommandFrom "add player Pippo"
    assert(command.execute equals Left("players: Pippo"))
    assert(ctx.players contains Player("Pippo"))
  }

  it should "responds: \"players: Pippo, Pluto\" if the user writes: \"add player Pluto\"" in {
    val command = commandParser getCommandFrom "add player Pluto"
    assert(command.execute equals Left("players: Pippo, Pluto"))
    assert(ctx.players map (_.name) containsSlice Array("Pippo", "Pluto"))
  }

  behavior of "If there is already a participant \"Pippo\" the system"
  val existMessage = "Pippo: already existing player"
  it should s"responds: $existMessage if the user writes: add player Pippo" in {
    val command = commandParser getCommandFrom "add player Pippo"
    val ex = intercept[IllegalArgumentException] {
      command execute
    }
    assert(ex.getMessage contains existMessage)
  }
}
