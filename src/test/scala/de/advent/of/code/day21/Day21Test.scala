package de.advent.of.code.day21

import org.scalatest.{Matchers, WordSpec}


class Day21Test extends WordSpec with Matchers {

  "Day 21" should {

    "parse the input" in {
      val input = Vector(
        "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
        "trh fvjkl sbzzf mxmxvkd (contains dairy)",
        "sqjhc fvjkl (contains soy)",
        "sqjhc mxmxvkd sbzzf (contains fish)")

      val actual = Food.getAll(input)
      actual shouldBe Vector(
        Food(Vector("mxmxvkd", "kfcds", "sqjhc", "nhms"), Vector("dairy", "fish")),
        Food(Vector("trh", "fvjkl", "sbzzf", "mxmxvkd"), Vector("dairy")),
        Food(Vector("sqjhc", "fvjkl"), Vector("soy")),
        Food(Vector("sqjhc", "mxmxvkd", "sbzzf"), Vector("fish")))
    }

    "create a map" in {
      val input = Vector(
        "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
        "trh fvjkl sbzzf mxmxvkd (contains dairy)",
        "sqjhc fvjkl (contains soy)",
        "sqjhc mxmxvkd sbzzf (contains fish)")

//      val foods = Food.getAll(input)
//      val actual = Solver.mapAllergens(foods)
//      actual shouldBe Map()
    }
  }
}
