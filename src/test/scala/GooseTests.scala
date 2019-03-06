import entities.User
import infrastructure.{CommandParser, DataBase, DataBaseInstance}
import org.scalatest.FlatSpec

class GooseTests extends FlatSpec {
  implicit val ctx: DataBase = DataBaseInstance

  behavior of "If there is no participant the system"
  it should "responds: \"players: Pippo\" if the user writes: \"add player Pippo\"" in {
    val command = CommandParser getCommand "add player Pippo"
    assert(command.execute contains "players: Pippo")
    assert(ctx.users contains User("Pippo"))
  }

  it should "responds: \"players: Pippo, Pluto\" if the user writes: \"add player Pluto\"" in {
    val command = CommandParser getCommand "add player Pluto"
    assert(command.execute contains "players: Pippo, Pluto")
    assert(ctx.users contains (User("Pippo"),User("Pluto")))
  }

  behavior of "If there is already a participant \"Pippo\" the system"
  it should "responds: \"Pippo: already existing player\" if the user writes: \"add player Pippo\"" in {
    val command = CommandParser getCommand "add player Pippo"
    assert(command.execute contains "Pippo: already existing player")
  }
}
