package de.advent.of.code.day22

import scala.annotation.tailrec

final case class Cards(playerOne: Vector[Int],
                       playerTwo: Vector[Int])

object Cards {

  def game(input: Vector[String],
           cardAmount: Int): Int = {
    @tailrec
    def loop(cards: Cards): Int = {
      if (cards.playerTwo.isEmpty) {
        cards.playerOne.reverse.zipWithIndex.map(tuple => tuple._1 * (tuple._2 + 1)).sum
      } else if (cards.playerOne.isEmpty) {
        cards.playerTwo.reverse.zipWithIndex.map(tuple => tuple._1 * (tuple._2 + 1)).sum
      } else {
        loop(turn(cards))
      }
    }

    val cards = init(input, cardAmount)
    loop(cards)
  }

  private def init(input: Vector[String],
                   cardAmount: Int): Cards = {
    val playerOne = input.tail.take(cardAmount).map(_.toInt)
    val playerTwo = input.tail.drop(cardAmount + 2).map(_.toInt)

    Cards(playerOne, playerTwo)
  }

  private def turn(cards: Cards): Cards = {
    val p1Card = cards.playerOne.head
    val p2Card = cards.playerTwo.head

    if (p1Card < p2Card) {
      Cards(
        playerOne = cards.playerOne.tail,
        playerTwo = cards.playerTwo.tail :+ p2Card :+ p1Card)
    } else {
      Cards(
        playerOne = cards.playerOne.tail :+ p1Card :+ p2Card,
        playerTwo = cards.playerTwo.tail)
    }
  }
}
