package de.advent.of.code.day6

object Count {

  def anyoneYes(group: Vector[String]): Int = {
    group
      .flatMap(line => line.toCharArray)
      .toSet
      .size
  }

  def everyoneYes(group: Vector[String]): Int = {
    group
      .map(line => line.toCharArray.toSet)
      .reduceLeft((x1, x2) => x1.intersect(x2))
      .size
  }
}
