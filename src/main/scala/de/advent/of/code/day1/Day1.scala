package de.advent.of.code.day1

import de.advent.of.code.utils.{FileReader, StopWatch}

object Day1 {

  def main(args: Array[String]): Unit = {
    val input = FileReader.asInts("day1/puzzle1.txt")

    puzzleOne(input)
    puzzleOneFaster(input)

    puzzleTwo(input)
    puzzleTwoFaster(input)
  }

  private def puzzleOne(input: Vector[Int]): Unit = {
    val result = StopWatch.measure(() => Pair.find(input))
    println(s"Result = '${result.result.getResult}' - took ${result.executionTime} ms")
  }

  private def puzzleOneFaster(input: Vector[Int]): Unit = {
    val result = StopWatch.measure(() => Pair.findFasterButNotFunctional(input))
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }

  private def puzzleTwo(input: Vector[Int]): Unit = {
    val result = StopWatch.measure(() => Triple.find(input))
    println(s"Result = '${result.result.getResult}' - took ${result.executionTime} ms")
  }

  private def puzzleTwoFaster(input: Vector[Int]): Unit = {
    val result = StopWatch.measure(() => Triple.findFasterButNotFunctional(input))
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }
}
