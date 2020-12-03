package de.advent.of.code.day2

import de.advent.of.code.utils.{FileReader, StopWatch}

object Day2 {

  def main(args: Array[String]): Unit = {
    val lines = FileReader.asStrings("day2/puzzle1.txt")

    /*
      For time measurements, it makes no sense to run the puzzles/tasks "stand alone". I'm not sure why but every following
      task is faster (perhaps the GarbageCollector doesn't cleanup everything and something is reused... but honestly
      I don't know). So if time should be measured, comment out the other 3 tasks
    */

    puzzleOne(lines)
    puzzleOneDirty(lines)
    puzzleTwo(lines)
    puzzleTwoDirty(lines)
  }

  private def puzzleOne(input: Vector[String]): Unit = {
    val result = StopWatch.measure(() => Validate.findValid(input, Validate.ByAmount))
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }

  private def puzzleOneDirty(input: Vector[String]): Unit = {
    val result = StopWatch.measure(() => Validate.dirty(input))
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }

  private def puzzleTwo(input: Vector[String]): Unit = {
    val result = StopWatch.measure(() => Validate.findValid(input, Validate.ByPosition))
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }

  private def puzzleTwoDirty(input: Vector[String]): Unit = {
    val result = StopWatch.measure(() => Validate.dirty2(input))
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }
}
