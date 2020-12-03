package de.advent.of.code.day3

import scala.annotation.tailrec

final case class Slope(right: Int,
                       down : Int)

object TreeEncounter {

  def countTrees(lines: Vector[String], slope: Slope): Int = {
    val length = lines.head.length

    @tailrec
    def helper(remainingLines: Vector[String], previousXPosition: Int, downIndex: Int, trees: Int): Int = {
      if (remainingLines.isEmpty) {
        trees
      } else {
        if (downIndex % slope.down == 0) {
          val posX = normalizedXPosition(previousXPosition, length, slope)
          val line = remainingLines.head
          val isTree = line.charAt(posX) == '#'
          val newTreeAmount = if(isTree) trees + 1 else trees

          helper(remainingLines.tail, posX, downIndex + 1, newTreeAmount)
        } else {

          helper(remainingLines.tail, previousXPosition, downIndex + 1, trees)
        }
      }
    }

    helper(lines.tail, 0, 1, 0)
  }

  private def normalizedXPosition(posX: Int, length: Int, slope: Slope) = {
    val newPosX = posX + slope.right
    if (newPosX <= length - 1) newPosX else newPosX - length
  }
}
