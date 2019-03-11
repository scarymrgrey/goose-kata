
import entities.{Player, Position}
import infrastructure.{CommandParserFactory, DataBase}
import org.scalatest.Matchers._
import org.scalatest.{FlatSpec, _}

class MovePlayersTests extends FlatSpec {
  private val commandParser = CommandParserFactory.default
  implicit val ctx: DataBase = new DataBase()
  val pippo = Player("Pippo")
  val pluto = Player("Pluto")

  ctx.players += pippo
  ctx.players += pluto
  ctx.positions+= Position(pluto,0)
  ctx.positions+= Position(pippo,0)

  behavior of "If there are two participants \"Pippo\" and \"Pluto\" on space \"Start\" the system"

  val steps = Array(
    ("move Pippo 4, 2","Pippo rolls 4, 2. Pippo moves from Start to The Bridge. Pippo jumps to 12",(12,0)),
    ("move Pluto 2, 2","Pluto rolls 2, 2. Pluto moves from Start to 4",(12,4)),
    ("move Pippo 2, 3","Pippo rolls 2, 3. Pippo moves from 12 to 17",(17,4)),
  )

  for (step <- steps) {
    val commandsText = step._2
    it should s"responds: $commandsText if the user writes: ${step._1}" in {
      val command = commandParser getCommandFrom step._1

      command.execute should equal(Left(commandsText))
      val pipposCell = step._3._1
      val plutossCell = step._3._2
      ctx.positions should contain only (Position(pippo, pipposCell),Position(pluto, plutossCell))
    }
  }

}
