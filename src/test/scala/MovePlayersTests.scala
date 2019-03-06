
import entities.{Cell, Player, Position}
import infrastructure.{CommandParser, DataBase}
import org.scalatest.FlatSpec
import org.scalatest._
import Matchers._

class MovePlayersTests extends FlatSpec {
  implicit val ctx: DataBase = new DataBase()
  val pippo = Player("Pippo")
  val pluto = Player("Pluto")

  ctx.players += pippo
  ctx.players += pluto
  ctx.positions+= Position(pluto,Cell(0))
  ctx.positions+= Position(pippo,Cell(0))

  behavior of "If there are two participants \"Pippo\" and \"Pluto\" on space \"Start\" the system"

  val steps = Array(
    ("move Pippo 4, 2","Pippo rolls 4, 2. Pippo moves from Start to 6",(6,0)),
    ("move Pluto 2, 2","Pluto rolls 2, 2. Pluto moves from Start to 4",(6,4)),
    ("move Pippo 2, 3","Pippo rolls 2, 3. Pippo moves from 6 to 11",(11,4)),
  )
  for (step <- steps) {
    val commandsText = step._2
    it should s"responds: ${step._1} if the user writes: ${commandsText}" in {
      val command = CommandParser getCommandFrom step._1

      command.execute should contain (commandsText)
      val pipposCell = Cell(step._3._1)
      val plutossCell = Cell(step._3._2)
      ctx.positions should contain only (Position(pippo, pipposCell),Position(pluto, plutossCell))
    }
  }


}
