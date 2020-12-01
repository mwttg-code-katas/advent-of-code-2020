package de.advent.of.code.day1

import scala.annotation.tailrec


final case class Pair(first: Int,
                      second: Int) {

  def getResult: Int = this.first * this.second
}

object Pair {

  def find(numbers: Vector[Int]): Pair = {
    @tailrec
    def helper(remaining: Vector[Int],
               checkAgainst: Vector[Int],
               maybeSolution: Option[Pair]): Pair = {
      if (maybeSolution.isDefined) {
        maybeSolution.get
      } else {
        val number        = remaining.head
        val maybePairMate = findPair(checkAgainst, number)

        helper(remaining.tail, checkAgainst.tail, maybePairMate)
      }
    }

    helper(numbers, numbers.tail, Option.empty)
  }

  private def findPair(checkAgainst: Vector[Int],
                       number: Int) = {
    checkAgainst
      .find(otherNumber => otherNumber + number == 2020)
      .map(pairMate => Pair(number, pairMate))
  }

  // ----------- Here the fast, but not so nice/clean part

  def findFasterButNotFunctional(numbers: Vector[Int]): Int = {
    var index1 = 0
    do {
      val number1 = numbers(index1)
      var index2  = index1 + 1

      do {
        val number2 = numbers(index2)
        if (number1 + number2 == 2020) {
          return number1 * number2
        }
        index2 = index2 + 1
      } while (index2 != numbers.size)

      index1 = index1 + 1
    } while (index1 != numbers.size)

    -1
  }
}
