
import entities.{Dice, Player, Position}
import infrastructure.{CommandParser, DataBase}
import org.scalatest.FlatSpec
import org.scalatest.Matchers._

class PrankTests extends FlatSpec {
  val commandParser =  CommandParser(new Dice {
    override def getSides: (Int, Int) = (1,1)
  })
  implicit val ctx: DataBase = new DataBase()
  val pippo = Player("Pippo")
  val pluto = Player("Pluto")

  ctx.players += pippo
  ctx.players += pluto
  ctx.positions+= Position(pluto,17)
  ctx.positions+= Position(pippo,15)

  behavior of "If there are two participants \"Pippo\" and \"Pluto\" respectively on spaces \"15\" and \"17\""
  val cases = Array(
    ("move Pippo", "Pippo rolls 1, 1. Pippo moves from 15 to 17. On 17 there is Pluto, who returns to 15",63)
  )

  for (onecase <- cases) {
    val responseText = onecase._2.toString
    it should s"responds: $responseText  when the user writes: ${onecase._1}" in {
      val command = commandParser getCommandFrom onecase._1
      val value = command.execute match {case Left(value) => value case Right(value) => value}
      value should equal(responseText)
    }
  }
}
