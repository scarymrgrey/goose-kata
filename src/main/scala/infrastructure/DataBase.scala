package infrastructure

import entities.{Player, Position}

import scala.collection.mutable.ArrayBuffer

class DataBase {
  val players: ArrayBuffer[Player] = scala.collection.mutable.ArrayBuffer.empty[Player]
  val positions: ArrayBuffer[Position] = scala.collection.mutable.ArrayBuffer.empty[Position]
}

object DataBaseInstance extends DataBase

