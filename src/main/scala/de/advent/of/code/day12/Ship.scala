package de.advent.of.code.day12

import de.advent.of.code.day12.Ship.{moveForward, normalizeLeft, normalizeRight}

final case class Ship(x    : Int,
                      y    : Int,
                      angle: Int) {

  def executeInstruction(command: Char,
                         value  : Int): Ship = {
    command match {
      case 'N' => Ship(this.x, this.y + value, this.angle)
      case 'S' => Ship(this.x, this.y - value, this.angle)
      case 'E' => Ship(this.x + value, this.y, this.angle)
      case 'W' => Ship(this.x - value, this.y, this.angle)
      case 'L' => Ship(this.x, this.y, normalizeLeft(this.angle, value))
      case 'R' => Ship(this.x, this.y, normalizeRight(this.angle, value))
      case 'F' => moveForward(this, value)
      case _ => throw new RuntimeException("Help!")
    }
  }
}

object Ship {

  def init: Ship = Ship(0, 0, 90)

  private def normalizeLeft(current: Int,
                            value  : Int) = {
    val result = current - value

    if (result < 0) result + 360 else result
  }

  private def normalizeRight(current: Int,
                             value : Int) = {
    val result = current + value

    if (result >= 360) result - 360 else result
  }

  private def moveForward(ship: Ship,
                          value: Int) = {
    ship.angle match {
      case 0 => Ship(ship.x, ship.y + value, ship.angle)
      case 90 => Ship(ship.x + value, ship.y, ship.angle)
      case 180 => Ship(ship.x, ship.y - value, ship.angle)
      case 270 => Ship(ship.x - value, ship.y, ship.angle)
      case _ => throw new RuntimeException("Houston we got a problem!")
    }
  }
}
