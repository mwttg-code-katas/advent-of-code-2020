package de.advent.of.code.day17

import de.advent.of.code.utils.{FileReader, StopWatch}

object Day17 {

  def main(args: Array[String]): Unit = {
    val input = FileReader.asStrings("day17/puzzle.txt")

    puzzleOne(input)
  }

  private def puzzleOne(input: Vector[String]): Unit = {
    val result = StopWatch.measure(() => CycleSimulator.execute(input, 6).activeCubes.size)
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }
}
