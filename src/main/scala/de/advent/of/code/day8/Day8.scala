package de.advent.of.code.day8

import de.advent.of.code.utils.{FileReader, StopWatch}

object Day8 {

  def main(args: Array[String]): Unit = {
    val linesOfCode = FileReader.asStrings("day8/puzzle.txt")

    /*
      For time measurements, it makes no sense to run the puzzles/tasks "in a sequence". I'm not sure why but every following
      task is faster (perhaps the GarbageCollector doesn't cleanup everything and something is reused... but honestly
      I don't know). So if time should be measured, comment out the other task
    */

    // puzzleOne(linesOfCode)
    puzzleTwo(linesOfCode)
  }

  private def puzzleOne(input: Vector[String]): Unit = {
    val result = StopWatch.measure(() => Handheld.executeProgram(input, false))
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }

  private def puzzleTwo(input: Vector[String]): Unit = {
    val result = StopWatch.measure(() => BruteForce.start(input))
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }
}
