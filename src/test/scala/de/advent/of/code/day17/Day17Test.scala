package de.advent.of.code.day17

import org.scalatest.{Matchers, WordSpec}

class Day17Test extends WordSpec with Matchers {

  "Day 17" should {
    "example 1" in {
      val input = Vector(
        ".#.",
        "..#",
        "###")
      val actual = CycleSimulator.execute(input, 6)
      actual.activeCubes.size shouldBe 112
    }
  }
}
