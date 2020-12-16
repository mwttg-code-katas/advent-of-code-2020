package de.advent.of.code.day16

import de.advent.of.code.utils.{FileReader, StopWatch}

object Day16 {

  def main(args: Array[String]): Unit = {
    val input = FileReader.asStrings("day16/puzzle.txt")

    puzzleOne(input)
  }

  private def puzzleOne(input: Vector[String]): Unit = {
    val result = StopWatch.measure(() => TicketScanningErrorRate.from(input, 20))
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }
}
