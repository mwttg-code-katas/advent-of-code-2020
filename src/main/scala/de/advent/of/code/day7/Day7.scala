package de.advent.of.code.day7

import de.advent.of.code.utils.{FileReader, StopWatch}

object Day7 {

  def main(args: Array[String]): Unit = {
    val input = FileReader.asStrings("day7/puzzle.txt")

    puzzleOne(input)
  }

  private def puzzleOne(input: Vector[String]): Unit = {
    val result = StopWatch.measure(() => HowManyDifferentSolutions.calculate(ColorBag.getAll(input)).size)
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }
}
