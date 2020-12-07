package de.advent.of.code.day7

import scala.annotation.tailrec

final case class ColorBag(name: String, amount: Int)

object ColorBag {

  def getAll(input: Vector[String]): Map[String, Set[String]] = {
    @tailrec
    def helper(remainingLines: Vector[String],
               accumulator   : Map[String, Set[String]]): Map[String, Set[String]] = {
      if (remainingLines.isEmpty) {
        accumulator
      } else {
        val currentLine   = remainingLines.head
        val keyValueParts = currentLine.split("contain")
        val key           = keyValueParts(0).substring(0, keyValueParts(0).length - 5).trim
        val valueParts    = keyValueParts(1).split(",").map(_.trim)
        val values        = createValues(valueParts)

        helper(remainingLines.tail, accumulator + (key -> values))
      }
    }

    helper(input, Map.empty)
  }

  private def createValues(items: Array[String]) = {
    items
      .map(item => {
        val parts = item.split(" ")
        if (parts(0) == "no") {
          ""
        } else
          parts(1) + " " + parts(2)
      })
      .toSet
  }
}
