package de.advent.of.code.day22

import de.advent.of.code.utils.{FileReader, StopWatch}

object Day22 {

  def main(args: Array[String]): Unit = {
    val input = FileReader.asStrings("day22/puzzle.txt")

    puzzleOne(input)
  }

  private def puzzleOne(input: Vector[String]): Unit = {
    val result = StopWatch.measure(() => Cards.game(input, 25))
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }
}
