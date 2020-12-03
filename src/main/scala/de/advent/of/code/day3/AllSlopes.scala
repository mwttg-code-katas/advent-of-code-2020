package de.advent.of.code.day3

import scala.concurrent.{Await, Future}

object AllSlopes {

  private val AllSlopes = Vector(
    Slope(1, 1),
    Slope(3, 1),
    Slope(5, 1),
    Slope(7, 1),
    Slope(1, 2))

  // not used because it's slower than countTrees2
  def countTrees(input: Vector[String]): Long = {
    import scala.concurrent.ExecutionContext.Implicits.global
    import scala.concurrent.duration.DurationInt

    val futures = AllSlopes
      .map(slope => Future {TreeEncounter.countTrees(input, slope) })
    val seq = Future.sequence(futures)
    Await
      .result(seq, 5.seconds)
      .map(number => number.toLong)
      .product
  }

  def countTrees2(input: Vector[String]): Long = {
    AllSlopes.map(slope => TreeEncounter.countTrees(input, slope)).product
  }
}
