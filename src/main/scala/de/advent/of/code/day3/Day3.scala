package de.advent.of.code.day3

import de.advent.of.code.utils.{FileReader, StopWatch}

object Day3 {

  def main(args: Array[String]): Unit = {
    val input = FileReader.asStrings("day3/puzzle.txt")

    /*
      For time measurements, it makes no sense to run the puzzles/tasks "stand alone". I'm not sure why but every following
      task is faster (perhaps the GarbageCollector doesn't cleanup everything and something is reused... but honestly
      I don't know). So if time should be measured, comment out the other task
    */

    puzzleOne(input)
    puzzleTwo(input)
  }

  private def puzzleOne(input: Vector[String]): Unit = {
    val slope  = Slope(3, 1)
    val result = StopWatch.measure(() => TreeEncounter.countTrees(input, slope))
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }

  private def puzzleTwo(input: Vector[String]): Unit = {
    val result = StopWatch.measure(() => AllSlopes.countTrees2(input))
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }
}
