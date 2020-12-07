package de.advent.of.code.day7

import de.advent.of.code.utils.FileReader

object Day7 {

  def main(args: Array[String]): Unit = {
    val input = FileReader.asStrings("day7/puzzle.txt")

    val x = ColorBag.getAll(input)

//    println(x)


    val y = HowManyDifferentSolutions.calculate(x)

    println(y)
    println(y.size)
  }
}
