package de.advent.of.code.day6

import de.advent.of.code.day5.{BoardingPass, Seat}
import de.advent.of.code.utils.{FileReader, StopWatch}

object Day6 {

  def main(args: Array[String]): Unit = {
    val input = FileReader.asStrings("day6/puzzle.txt")

    /*
      For time measurements, it makes no sense to run the puzzles/tasks "in a sequence". I'm not sure why but every following
      task is faster (perhaps the GarbageCollector doesn't cleanup everything and something is reused... but honestly
      I don't know). So if time should be measured, comment out the other task
    */

    puzzleOne(input)
    puzzleTwo(input)
  }

  private def puzzleOne(input: Vector[String]): Unit = {
    val result = StopWatch.measure(() => GetGroups
      .all(input)
      .map(group => Count.anyoneYes(group))
      .sum)
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }

  private def puzzleTwo(input: Vector[String]): Unit = {
    val result = StopWatch.measure(() => GetGroups
      .all(input)
      .map(group => Count.everyoneYes(group))
      .sum)
    println(s"Result = '${result.result}' - took ${result.executionTime} ms")
  }
}
