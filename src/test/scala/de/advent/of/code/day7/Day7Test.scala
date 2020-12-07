package de.advent.of.code.day7

import org.scalatest.{Matchers, WordSpec}

class Day7Test extends WordSpec with Matchers {

  "Day 7" should {
    "number of bag colors is 4" in {
      val input = Vector(
        "light red bags contain 1 bright white bag, 2 muted yellow bags.",
        "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
        "bright white bags contain 1 shiny gold bag.",
        "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
        "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
        "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
        "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
        "faded blue bags contain no other bags.",
        "dotted black bags contain no other bags."
      )

      val colorBags = ColorBag.getAll(input)
      val actual    = HowManyDifferentSolutions.calculate(colorBags).size

      actual shouldBe 4
    }

    "read correctly the input ô.Ô" in {
      val input = Vector("striped coral bags contain 1 posh brown bag, 4 drab green bags, 1 pale white bag, 1 posh bronze bag.")
      val actual = ColorBag.getAll(input)

      actual shouldBe Map("striped coral" -> Set("posh brown", "drab green", "pale white", "posh bronze"))
    }
     "read correctly part 2" in {
       val input = Vector("faded olive bags contain 2 shiny turquoise bags, 5 drab green bags, 4 light maroon bags.")
       val actual = ColorBag.getAll(input)

       actual shouldBe Map("faded olive" -> Set("shiny turquoise", "drab green", "light maroon"))
     }
  }
}
