package de.advent.of.code.day21

import de.advent.of.code.utils.{FileReader, StopWatch}

object Day21 {

  def main(args: Array[String]): Unit = {
    val input = FileReader.asStrings("day21/puzzle.txt")

    // puzzleOne(input)
    puzzleTwo(input)
  }

  private def puzzleOne(input: Vector[String]): Unit = {
    val result = StopWatch.measure(() => Solver.all(input))
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }

  private def puzzleTwo(input: Vector[String]): Unit = {
    val result = StopWatch.measure(() => Solver.part2(input))
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }
}
