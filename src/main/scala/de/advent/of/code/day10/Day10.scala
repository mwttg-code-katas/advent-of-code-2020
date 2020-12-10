package de.advent.of.code.day10

import de.advent.of.code.utils.{FileReader, StopWatch}

object Day10 {

  def main(args: Array[String]): Unit = {
    val input = FileReader.asInts("day10/puzzle.txt")

    puzzleOne(input)
  }

  private def puzzleOne(input: Vector[Int]): Unit = {
    val result = StopWatch.measure(() => Differences.getDifferences(input))
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }

}
