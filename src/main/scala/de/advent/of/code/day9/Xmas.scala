package de.advent.of.code.day9

import scala.annotation.tailrec

object Xmas {

  def findFirstNumberWithoutPair(input       : Vector[Long],
                                 preambleSize: Int): Long = {
    val (preambleInit, rest) = input.splitAt(preambleSize)
    val preamble             = Preamble.init25(preambleInit)

    @tailrec
    def helper(remaining : Vector[Long],
               preamble  : Preamble,
               result    : Option[Long]): Long = {
      if (result.isDefined) {
        result.get
      } else {
        val currentNumber = remaining.head
        val currentPairs  = preamble.findPairsFor(currentNumber)

        if (currentPairs.isEmpty) {
          helper(remaining.tail, preamble, Some(currentNumber))
        } else {
          helper(remaining.tail, preamble.addNumber(currentNumber), None)
        }
      }
    }

    helper(rest, preamble, None)
  }

  def findEncryptionWeakness(input: Vector[Long],
                             seed : Long): Long = {
    @tailrec
    def helper(remaining: Vector[Long],
               result   : Vector[Long]): Vector[Long] = {
      if (remaining.isEmpty || result.nonEmpty) {
        result
      } else {
        val currentNumber = remaining.head
        val maybe         = sumUp(currentNumber, remaining.tail, seed)
        helper(remaining.tail, maybe)
      }
    }

    val result = helper(input, Vector.empty)
    result.min + result.max
  }

  private def sumUp(number: Long,
                    numbers: Vector[Long],
                    seed   : Long) = {
    @tailrec
    def helper(remaining: Vector[Long],
               result   : Vector[Long]): Vector[Long] = {
      if (result.sum + number == seed) {
        result :+ number
      } else if (result.sum > seed) {
        Vector.empty
      } else {
        helper(remaining.tail, result :+ remaining.head)
      }
    }

    helper(numbers, Vector.empty)
  }
}
