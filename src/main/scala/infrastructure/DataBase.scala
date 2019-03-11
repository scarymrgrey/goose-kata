package infrastructure

import entities.{BasePosition, Player, Position}

import scala.collection.mutable.ArrayBuffer

class DataBase {
  val players: ArrayBuffer[Player] = scala.collection.mutable.ArrayBuffer.empty[Player]
  val positions: ArrayBuffer[BasePosition] = scala.collection.mutable.ArrayBuffer.empty[BasePosition]
}

object DataBaseInstance extends DataBase

