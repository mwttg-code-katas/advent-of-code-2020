package de.advent.of.code.day12

object NavigationComputer {

  def executeInstructions(input: Vector[String]): Int = {
    val ship = input.foldLeft(Ship.init) { (ship, instruction) => {
      val command = instruction.head
      val value   = instruction.tail.toInt

      ship.executeInstruction(command, value)
    }
    }
    Math.abs(ship.x) + Math.abs(ship.y)
  }
}
