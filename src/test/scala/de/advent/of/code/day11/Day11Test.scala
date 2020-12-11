package de.advent.of.code.day11

import org.scalatest.{Matchers, WordSpec}

class Day11Test extends WordSpec with Matchers {

  "Day 11" should {
    "count occupied seats - puzzle 1" in {
      val input = Vector(
        "L.LL.LL.LL",
        "LLLLLLL.LL",
        "L.L.L..L..",
        "LLLL.LL.LL",
        "L.LL.LL.LL",
        "L.LLLLL.LL",
        "..L.L.....",
        "LLLLLLLLLL",
        "L.LLLLLL.L",
        "L.LLLLL.LL")
      val actual = WaitingArea.simulate(input)
      actual shouldBe 37
    }
  }
}
