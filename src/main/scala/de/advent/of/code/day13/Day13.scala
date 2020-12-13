package de.advent.of.code.day13

import de.advent.of.code.utils.{FileReader, StopWatch}

object Day13 {
  def main(args: Array[String]): Unit = {
    val input = FileReader.asStrings("day13/puzzle.txt")

    puzzleOne(input)
  }

  private def puzzleOne(input: Vector[String]): Unit = {
    val result = StopWatch.measure(() => GetBusId.get(input))
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }
}
