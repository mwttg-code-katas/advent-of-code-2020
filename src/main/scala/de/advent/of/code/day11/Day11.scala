package de.advent.of.code.day11

import de.advent.of.code.utils.{FileReader, StopWatch}

object Day11 {

  def main(args: Array[String]): Unit = {
    val input = FileReader.asStrings("day11/puzzle.txt")

    puzzleOne(input)
  }

  private def puzzleOne(input: Vector[String]): Unit = {
    val result = StopWatch.measure(() => WaitingArea.simulate(input))
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }
}
