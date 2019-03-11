
import entities.{Player, Position}
import infrastructure.{CommandParserFactory, DataBase}
import org.scalatest.Matchers._
import org.scalatest.{FlatSpec, _}

class WinsPlayersTests extends FlatSpec {
  val pippo = Player("Pippo")

  def fixture =
    new {
      val commandParser = CommandParserFactory.default
      val ctx: DataBase = new DataBase()

      ctx.players += pippo
      ctx.positions += Position(pippo, 60)
    }


  behavior of "If there is one participant \"Pippo\" on space \"60\" the system"
  val cases = Array(
    ("move Pippo 1, 2", "Pippo rolls 1, 2. Pippo moves from 60 to 63. Pippo Wins!!",63),
    ("move Pippo 3, 2", "Pippo rolls 3, 2. Pippo moves from 60 to 63. Pippo bounces! Pippo returns to 61",61)
  )

  for (onecase <- cases) {
    val responseText = onecase._2.toString
    it should s"responds: $responseText  when the user writes: ${onecase._1}" in {
      val f = fixture
      implicit val ctx = f.ctx
      val command = f.commandParser getCommandFrom onecase._1
      val value = command.execute match {case Left(value) => value case Right(value) => value}
      value should equal(responseText)

      ctx.positions should contain only Position(pippo, onecase._3)
    }
  }
}
