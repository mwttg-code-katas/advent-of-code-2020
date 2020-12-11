package de.advent.of.code.day11

import scala.annotation.tailrec

final case class Seat(x: Int,
                      y: Int,
                      isOccupied: Boolean)

object WaitingArea {

  def simulate(input: Vector[String]): Int = {
    @tailrec
    def helper(seats: Vector[Seat],
               isStable: Boolean): Vector[Seat] = {
      if (isStable) {
        seats
      } else {
        println("#")
        val nextStage = nextStep(seats)

        println("!")
        val stable = seats.toSet == nextStage.toSet
        println("?")

        helper(nextStage, stable)
      }
    }

    val seats  = WaitingArea.init(input)
    val result = helper(seats, false)

    result.count(seat => seat.isOccupied)
  }

  private def nextStep(seats: Vector[Seat]): Vector[Seat] = {
    seats.map(seat => {
      (seat, getOccupiedNeighbours(seat, seats)) match {
        case (Seat(x, y, false), amount) if amount == 0 => Seat(x, y, true)
        case (Seat(x, y, true), amount) if amount >= 4 => Seat(x, y, false)
        case (Seat(x, y, isOccupied), _) => Seat(x, y, isOccupied)
      }
    })
  }

  private def getOccupiedNeighbours(seat     : Seat,
                                    seats    : Vector[Seat]) = {
    val topLeft     = seats.find(r => r.x == seat.x - 1 && r.y == seat.y - 1 && r.isOccupied)
    val top         = seats.find(r => r.x == seat.x && r.y == seat.y - 1 && r.isOccupied)
    val topRight    = seats.find(r => r.x == seat.x + 1 && r.y == seat.y - 1 && r.isOccupied)
    val left        = seats.find(r => r.x == seat.x - 1 && r.y == seat.y && r.isOccupied)
    val right       = seats.find(r => r.x == seat.x + 1 && r.y == seat.y && r.isOccupied)
    val bottomLeft  = seats.find(r => r.x == seat.x - 1 && r.y == seat.y + 1 && r.isOccupied)
    val bottom      = seats.find(r => r.x == seat.x && r.y == seat.y + 1 && r.isOccupied)
    val bottomRight = seats.find(r => r.x == seat.x + 1 && r.y == seat.y + 1 && r.isOccupied)

    Set(topLeft, top, topRight, left, right, bottomLeft, bottom, bottomRight)
      .flatten
      .size
  }

  def init(input: Vector[String]): Vector[Seat] = {
    @tailrec
    def helper(remaining: Vector[String],
               y        : Int,
               result   : Vector[Seat]): Vector[Seat] = {
      if (remaining.isEmpty) {
        result
      } else {
        val currentLine = remaining.head
        val seats       = createRow(currentLine, y)

        helper(remaining.tail, y + 1, result :++ seats)
      }
    }

    helper(input, 0, Vector.empty)
  }

  private def createRow(input: String,
                        y    : Int) = {
    @tailrec
    def helper(remaining: Array[Char],
               x        : Int,
               result   : Vector[Seat]): Vector[Seat] = {
      if (remaining.isEmpty) {
        result
      } else {
        val maybe     = remaining.head match {
          case 'L' => Some(Seat(x, y, false))
          case _ => None
        }
        val newResult = if (maybe.isDefined) result :+ maybe.get else result

        helper(remaining.tail, x + 1, newResult)
      }
    }

    val chars = input.toCharArray
    helper(chars, 0, Vector.empty)
  }
}
