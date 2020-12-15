package de.advent.of.code.day15

import org.scalatest.{Matchers, WordSpec}

class SpokenNumberTest extends WordSpec with Matchers {

  "Day 15" should {
    "example 1" in {
      val input  = Vector(0, 3, 6)
      val actual = SpokenNumber.ofIndex(10, input)

      actual shouldBe 0
    }
    "example 2" in {
      val input  = Vector(0, 3, 6)
      val actual = SpokenNumber.ofIndex(2020, input)

      actual shouldBe 436
    }
    "example 3" in {
      val input  = Vector(1, 3, 2)
      val actual = SpokenNumber.ofIndex(2020, input)

      actual shouldBe 1
    }
    "example 4" in {
      val input  = Vector(2, 1, 3)
      val actual = SpokenNumber.ofIndex(2020, input)

      actual shouldBe 10
    }
    "example 5" in {
      val input  = Vector(1, 2, 3)
      val actual = SpokenNumber.ofIndex(2020, input)

      actual shouldBe 27
    }
    "example 6" in {
      val input  = Vector(2, 3, 1)
      val actual = SpokenNumber.ofIndex(2020, input)

      actual shouldBe 78
    }
    "example 7" in {
      val input  = Vector(3, 2, 1)
      val actual = SpokenNumber.ofIndex(2020, input)

      actual shouldBe 438
    }
    "example 8" in {
      val input  = Vector(3, 1, 2)
      val actual = SpokenNumber.ofIndex(2020, input)

      actual shouldBe 1836
    }
  }
}
