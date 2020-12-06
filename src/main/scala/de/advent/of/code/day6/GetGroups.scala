package de.advent.of.code.day6

import scala.annotation.tailrec

object GetGroups {

  def all(input: Vector[String]): Vector[Vector[String]] = {
    @tailrec
    def helper(remainingLines: Vector[String],
               currentGroup  : Vector[String],
               allGroups     : Vector[Vector[String]]): Vector[Vector[String]] = {
      if (remainingLines.isEmpty) {
        allGroups
      } else {
        val currentLine = remainingLines.head
        if (currentLine.isEmpty) { // had to insert one empty line at the bottom of puzzle input :/
          helper(remainingLines.tail, Vector.empty, allGroups :+ currentGroup)
        } else {
          helper(remainingLines.tail, currentGroup :+ currentLine, allGroups)
        }
      }
    }

    helper(input, Vector.empty, Vector.empty)
  }
}
