package de.advent.of.code.day9

import de.advent.of.code.utils.{FileReader, StopWatch}

object Day9 {

  def main(args: Array[String]): Unit = {
    val input = FileReader.asLongs("day9/puzzle.txt")

    puzzleOne(input)
    puzzleTwo(input)
  }

  private def puzzleOne(input: Vector[Long]): Unit = {
    val result = StopWatch.measure(() => Xmas.findFirstNumberWithoutPair(input, 25))
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }

  private def puzzleTwo(input: Vector[Long]): Unit = {
    val result = StopWatch.measure(() => Xmas.findEncryptionWeakness(input, 10884537))
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }
}
