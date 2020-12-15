package de.advent.of.code.day15

import de.advent.of.code.utils.StopWatch

object Day15 {
  def main(args: Array[String]): Unit = {
    val input = Vector(6, 4, 12, 1, 20, 0, 16)

    // puzzleOne(input)
    puzzleTwo(input)
  }

  private def puzzleOne(input: Vector[Int]): Unit = {
    val result = StopWatch.measure(() => SpokenNumber.ofIndex(2020, input))
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }

  private def puzzleTwo(input: Vector[Int]): Unit = {
    val result = StopWatch.measure(() => SpokenNumber.ofIndex(30000000, input))
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }
}
