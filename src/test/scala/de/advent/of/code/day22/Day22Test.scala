package de.advent.of.code.day22

import org.scalatest.{Matchers, WordSpec}

class Day22Test extends WordSpec with Matchers {

  "Day 22" should {
    "example 1" in {
      val input = Vector(
        "Player 1:",
        "9",
        "2",
        "6",
        "3",
        "1",
        "",
        "Player 2:",
        "5",
        "8",
        "4",
        "7",
        "10")
      val actual = Cards.game(input, 5)
      actual shouldBe 306
    }
  }

}
