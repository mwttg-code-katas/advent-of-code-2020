package de.advent.of.code.day21

import scala.annotation.tailrec

object Solver {

  def part2(input: Vector[String]): String = {
    val foods                  = Food.getAll(input)
    val ingredientsByAllergens = mapAllergens(foods)
    val reduced                = reduce(ingredientsByAllergens)
    val allergenPerFood        = reduced.map(r => r._1 -> r._2.head)
    allergenPerFood.keys.toVector.sorted.map(a => allergenPerFood(a)).mkString(",")
  }



  def all(input: Vector[String]): Int = {
    val foods                  = Food.getAll(input)
    val ingredientsByAllergens = mapAllergens(foods)
    val reduced                = reduce(ingredientsByAllergens)
    val allergenPerFood        = reduced.map(r => r._1 -> r._2.head)
    val allergenFood           = allergenPerFood.values.toSet
    val food                   = foods.flatMap(f => f.ingredients).toSet
    val cleanFood              = food.diff(allergenFood)
    count(foods, cleanFood)
  }


  private def count(foods: Vector[Food],
                    cleanFood: Set[String]) = {
    foods
      .map(f1 => f1.ingredients
        .foldLeft(0) { (acc, word) => if (cleanFood.contains(word)) acc + 1 else acc })
      .sum
  }

  private def reduce(input: Map[String, Set[String]]) = {

    @tailrec
    def loop(remaining: Map[String, Set[String]],
             result   : Map[String, Set[String]]): Map[String, Set[String]] = {
      if (remaining.isEmpty) {
        result
      } else {
        val x            = remaining.find(r => r._2.size == 1)
        val word         = x.get._1
        val newResult    = result + (x.get._1 -> x.get._2)
        val newRemaining = remaining.map(entry => (entry._1 -> entry._2.diff(Set(x.get._2.head))))
        val y            = newRemaining.filterNot(r => r._2.isEmpty)

        loop(y, newResult)
      }
    }

    loop(input, Map.empty)
  }

  private def mapAllergens(foods: Vector[Food]) = {
    @tailrec
    def loop(remaining: Vector[Food],
             result   : Map[String, Set[String]]): Map[String, Set[String]] = {
      if (remaining.isEmpty) {
        result
      } else {
        val food        = remaining.head
        val allergens   = food.allergens
        val ingredients = food.ingredients

        val entries   = allergens.map(allergen => allergen -> ingredients.toSet)
        val newResult = entries.map(entry => {
          if (result.contains(entry._1)) {
            val value = result(entry._1).intersect(entry._2)
            entry._1 -> value
          } else {
            entry._1 -> entry._2
          }
        }).toMap
        loop(remaining.tail, result ++ newResult)
      }
    }

    loop(foods, Map.empty)
  }
}
