package de.advent.of.code.day1

import scala.annotation.tailrec

final case class Triple(first: Int,
                        second: Int,
                        third : Int) {

  def getResult: Int = first * second * third
}

object Triple {

  def find(numbers : Vector[Int]): Triple = {
    @tailrec
    def helper(remaining        : Vector[Int],
               checkAgainstList1: Vector[Int],
               checkAgainstList2: Vector[Int],
               maybeSolution: Option[Triple]): Triple = {
      if (maybeSolution.isDefined) {
        maybeSolution.get
      } else {
        val number      = remaining.head
        val maybeTriple = findMaybeTriple(number, checkAgainstList1, checkAgainstList2)

        helper(remaining.tail, checkAgainstList1.tail, checkAgainstList2.tail, maybeTriple)
      }
    }

    helper(numbers, numbers.tail, numbers.tail, Option.empty)
  }

  private def findMaybeTriple(number1          : Int,
                              checkAgainstList1: Vector[Int],
                              checkAgainstList2: Vector[Int]) = {
    @tailrec
    def helper(remaining: Vector[Int],
               maybeTriple: Option[Triple]): Option[Triple] = {
      if (remaining.isEmpty || maybeTriple.isDefined) {
        maybeTriple
      } else {
        val number2     = remaining.head
        val maybeTriple = checkAgainstList2
          .find(number3 => number1 + number2 + number3 == 2020)
          .map(tripleMates => Triple(number1, number2, tripleMates))

        helper(remaining.tail, maybeTriple)
      }
    }

    helper(checkAgainstList1, Option.empty)
  }

  // ----------- Here begins the NOT functional (dirty) part

  def findFasterButNotFunctional(numbers: Vector[Int]): Int = {
    var index1 = 0
    do {
      val number1 = numbers(index1)
      var index2  = index1 + 1

      do {
        val number2 = numbers(index2)
        var index3  = index1 + 1

        do {
          val number3 = numbers(index3)
          if (number1 + number2 + number3 == 2020) {
            return number1 * number2 * number3
          }
          index3 = index3 + 1
        } while (index3 != numbers.size)

        index2 = index2 + 1
      } while (index2 != numbers.size)

      index1 = index1 + 1
    } while (index1 != numbers.size)

    -1
  }
}
