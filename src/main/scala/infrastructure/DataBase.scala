package infrastructure

import entities.Player

import scala.collection.mutable.ArrayBuffer

class DataBase {
  val players: ArrayBuffer[Player] = scala.collection.mutable.ArrayBuffer.empty[Player]
  val positions: ArrayBuffer[(Player,Int)] = scala.collection.mutable.ArrayBuffer.empty[(Player,Int)]
}

object DataBaseInstance extends DataBase

