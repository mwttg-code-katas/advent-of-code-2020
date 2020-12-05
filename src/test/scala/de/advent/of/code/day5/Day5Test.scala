package de.advent.of.code.day5

import org.scalatest.{Matchers, WordSpec}

class Day5Test extends WordSpec with Matchers {

  "Day5" should {
    "row: 70 column: 7 for BFFFBBFRRR" in {
      val input        = "BFFFBBFRRR"
      val boardingPass = BoardingPass.getAll(Vector(input))
      val seatId       = Seat.getSeatId(boardingPass(0))

      seatId shouldBe 567
    }

    "row: 14 column: 7 for FFFBBBFRRR" in {
      val input        = "FFFBBBFRRR"
      val boardingPass = BoardingPass.getAll(Vector(input))
      val seatId       = Seat.getSeatId(boardingPass(0))

      seatId shouldBe 119
    }

    "row: 102 column: 4 for BBFFBBFRLL" in {
      val input        = "BBFFBBFRLL"
      val boardingPass = BoardingPass.getAll(Vector(input))
      val seatId       = Seat.getSeatId(boardingPass(0))

      seatId shouldBe 820
    }
  }
}
