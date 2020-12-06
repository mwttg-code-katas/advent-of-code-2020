package de.advent.of.code.day6

import org.scalatest.{Matchers, WordSpec}

class Day6Test extends WordSpec with Matchers {

  "Day 6" should {
    "return 3 for abc - everyone yes" in {
      val input  = Vector("abc", "")
      val actual = GetGroups.all(input).map(group => Count.everyoneYes(group)).sum
      actual shouldBe 3
    }

    "return 0 for a, b, c - everyone yes" in {
      val input  = Vector("a", "b", "c", "")
      val actual = GetGroups.all(input).map(group => Count.everyoneYes(group)).sum
      actual shouldBe 0
    }

    "return 1 for ab, ac - everyone yes" in {
      val input  = Vector("ab", "ac", "")
      val actual = GetGroups.all(input).map(group => Count.everyoneYes(group)).sum
      actual shouldBe 1
    }

    "return 1 for a, a, a, a - everyone yes" in {
      val input  = Vector("a", "a", "a", "a", "")
      val actual = GetGroups.all(input).map(group => Count.everyoneYes(group)).sum
      actual shouldBe 1
    }



  }

}
