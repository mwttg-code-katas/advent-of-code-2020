package de.advent.of.code.day16

import org.scalatest.{Matchers, WordSpec}

class Day16Test extends WordSpec with Matchers {

  "Day 16" should {
    "example 1" in {
      val input = Vector(
        "class: 1-3 or 5-7",
        "row: 6-11 or 33-44",
        "seat: 13-40 or 45-50",
        "",
        "your ticket:",
        "7,1,14",
        "",
        "nearby tickets:",
        "7,3,47",
        "40,4,50",
        "55,2,20",
        "38,6,12")

      val actual = TicketScanningErrorRate.from(input, 3)
      actual shouldBe 71
    }
  }

}
