package de.advent.of.code.day21

import scala.annotation.tailrec

final case class Food(ingredients: Vector[String],
                      allergens  : Vector[String])

object Food {

  def getAll(lines: Vector[String]): Vector[Food] = {
    lines.map(line => parseLine(line))
  }

  private def parseLine(line: String) = {
    val parts = line.split(" ")

    @tailrec
    def loop(remaining  : Array[String],
             isAllergens: Boolean,
             ingredients: Vector[String],
             allergens  : Vector[String]): Food = {
      if (remaining.isEmpty) {
        Food(ingredients, allergens)
      } else {
        remaining.head match {
          case x if x.startsWith("(") => loop(remaining.tail, true, ingredients, allergens)
          case x if x.endsWith(",") => loop(remaining.tail, true, ingredients, allergens :+ x.dropRight(1))
          case x if x.endsWith(")") => loop(remaining.tail, true, ingredients, allergens :+ x.dropRight(1))
          case x if !isAllergens => loop(remaining.tail, isAllergens, ingredients :+ x, allergens)
          case x => loop(remaining.tail, isAllergens, ingredients, allergens :+ x)
          case _ => throw new RuntimeException("Help!")
        }
      }
    }

    loop(parts, false, Vector.empty, Vector.empty)
  }
}
