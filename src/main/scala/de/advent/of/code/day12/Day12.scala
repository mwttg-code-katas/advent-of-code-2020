package de.advent.of.code.day12

import de.advent.of.code.utils.{FileReader, StopWatch}

object Day12 {

  def main(args: Array[String]): Unit = {
    val input = FileReader.asStrings("day12/puzzle.txt")

    puzzleOne(input)
  }

  private def puzzleOne(input: Vector[String]): Unit = {
    val result = StopWatch.measure(() => NavigationComputer.executeInstructions(input))
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }
}
