package de.advent.of.code.day7

import scala.annotation.tailrec

object HowManyDifferentSolutions {


  def calculate(input: Map[String, Set[String]]): Set[String] = {
    @tailrec
    def helper(previousSize       : Int,
               previousAddedColors: Set[String],
               accumulator        : Set[String]): Set[String] = {
      if (previousSize == accumulator.size) {
        accumulator
      } else {
        val newColors = input.filter(entry => entry._2.exists(color => previousAddedColors.contains(color)))
        helper(accumulator.size, newColors.keySet, accumulator ++ newColors.keySet)
      }
    }

    val start = input.filter(item => item._2.contains("shiny gold")).keySet
    helper(0, start, start)
  }
}
