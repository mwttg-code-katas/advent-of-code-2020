package de.advent.of.code.utils

final case class StopWatch[T](result: T,
                              executionTime: Long)

object StopWatch {

  def measure[T](inputFunction: () => T): StopWatch[T] = {
    val start         = System.currentTimeMillis()
    val result        = inputFunction.apply()
    val stop          = System.currentTimeMillis()
    val executionTime = stop - start

    StopWatch(result, executionTime)
  }
}
