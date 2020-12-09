package de.advent.of.code.day9

import scala.annotation.tailrec

final case class Pair(numberOne: Long,
                      numberTwo: Long)

final case class Preamble(numbers: Vector[Long],
                          size   : Int) {

  def addNumber(number: Long): Preamble = if (this.numbers.size >= this.size) Preamble(this.numbers.tail :+ number, this.size) else Preamble(this.numbers :+ number, this.size)

  def findPairsFor(number: Long): Vector[Pair] = {
    @tailrec
    def helper(remaining: Vector[Long],
               pairs    : Vector[Pair]): Vector[Pair] = {
      if (remaining.isEmpty) {
        pairs
      } else {
        val one = remaining.head
        val two = number - one

        if (this.numbers.contains(two)) {
          helper(remaining.tail, pairs :+ Pair(one, two))
        } else {
          helper(remaining.tail, pairs)
        }
      }
    }

    helper(this.numbers, Vector.empty)
  }
}

object Preamble {

  def init25: Preamble = Preamble(Vector.empty, 25)

  def init25(numbers: Vector[Long]): Preamble = Preamble(numbers, 25)

  def init5: Preamble = Preamble(Vector.empty, 5)
}
