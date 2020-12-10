package de.advent.of.code.day10

final case class Differences(lastAdapter: Int,
                             oneJoltAmounts             : Int,
                             threeJoltAmounts           : Int) {

  def getDifferences: Int = this.oneJoltAmounts * (this.threeJoltAmounts + 1)
}

object Differences {

  def init: Differences = Differences(0, 0, 0)

  def getDifferences(adapters: Vector[Int]): Int = adapters
    .sorted
    .foldLeft(Differences.init) { (differences, adapter) => {
      val diff = adapter - differences.lastAdapter
      diff match {
        case 1 => Differences(adapter, differences.oneJoltAmounts + 1, differences.threeJoltAmounts)
        case 3 => Differences(adapter, differences.oneJoltAmounts, differences.threeJoltAmounts + 1)
        case _ => throw new RuntimeException("Help!")
      }
    }
    }
    .getDifferences
}
