package commands

import infrastructure.DataBase

trait CommandBase[T] {
  def execute()(implicit ctx: DataBase): T
}
