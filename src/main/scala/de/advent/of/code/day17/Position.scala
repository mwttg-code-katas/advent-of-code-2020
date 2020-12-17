package de.advent.of.code.day17

import scala.annotation.tailrec

final case class Position(x: Int,
                          y: Int,
                          z: Int)

object Position {

  def initActiveCubes(input: Vector[String]): Set[Position] = {
    @tailrec
    def loopY(remaining: Vector[String],
              currentY: Int,
              result   : Set[Position]): Set[Position] = {
      if (remaining.isEmpty) {
        result
      } else {
        val row   = remaining.head.toCharArray
        val cubes = loopX(row, 0, currentY, Set.empty)

        loopY(remaining.tail, currentY + 1, result ++ cubes)
      }
    }

    @tailrec
    def loopX(remaining: Array[Char],
              currentX: Int,
              currentY: Int,
              result   : Set[Position]): Set[Position] = {
      if (remaining.isEmpty) {
        result
      } else {
        remaining.head match {
          case '.' => loopX(remaining.tail, currentX + 1, currentY, result)
          case '#' => loopX(remaining.tail, currentX + 1, currentY, result + Position(currentX, currentY, 0))
          case _ => throw new RuntimeException("Help!")
        }
      }
    }

    loopY(input, 0, Set.empty)
  }

  def getActiveNeighbourhoodOf(position: Position,
                         activeCubes: Set[Position]): Set[Position] = {
    val tuples =
      for (x <- -1 to 1;
           y <- -1 to 1;
           z <- -1 to 1) yield {
        val maybePosition = Position(position.x + x, position.y + y, position.z + z)
        Some(maybePosition).filter(activeCubes.contains)
      }

      tuples.flatten.toSet.diff(Set(position))
  }

  def getInactiveNeighbourhoodOf(position: Position,
                               activeCubes: Set[Position]): Set[Position] = {
    val tuples =
      for (x <- -1 to 1;
           y <- -1 to 1;
           z <- -1 to 1) yield {
        val maybePosition = Position(position.x + x, position.y + y, position.z + z)
        Some(maybePosition).filterNot(activeCubes.contains)
      }

    tuples.flatten.toSet
  }
}
