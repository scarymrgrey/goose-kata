package infrastructure

import entities.User

import scala.collection.mutable.ArrayBuffer

class DataBase {
  val users: ArrayBuffer[User] = scala.collection.mutable.ArrayBuffer.empty[User]
}

object DataBaseInstance extends DataBase
