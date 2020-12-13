package de.advent.of.code.day13

object GetBusId {

  def get(input: Vector[String]): Int = {
    val timestamp = getTimestamp(input)
    val busIds    = getBusIds(input)

    val busIdAndTimestamp = busIds
      .map(busId => (busId, timestamp / busId))
      .map(tuple => (tuple._1, (tuple._2 + 1) * tuple._1))
      .minBy(tuple => tuple._2)

    val minutesToWait = busIdAndTimestamp._2 - timestamp

    minutesToWait * busIdAndTimestamp._1
  }

  private def getTimestamp(input: Vector[String]) = input.head.toInt

  private def getBusIds(input: Vector[String]) =
    input
      .tail
      .head
      .split(",")
      .filter(item => item != "x")
      .map(id => id.toInt)
      .toVector
}
