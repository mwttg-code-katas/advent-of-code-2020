package de.advent.of.code.day5

import scala.annotation.tailrec

object FindMissingSeatId {

  def find(input: Vector[String]): Int = {
    val idsOnList  = BoardingPass
      .getAll(input)
      .map(boardingPass => Seat.getSeatId(boardingPass))
      .toSet
    val allIds     = Set.tabulate(128, 8)((row, column) => row * 8 + column).flatten
    val missingIds = allIds.diff(idsOnList).toVector.sorted
    findMissingId(missingIds)
  }

  private def findMissingId(missingIds: Vector[Int]) = {
    @tailrec
    def helper(remaining: Vector[Int],
               lastSeatId: Int,
               mySeatId  : Option[Int]): Int = {
      if (mySeatId.isDefined) {
        mySeatId.get
      } else {
        val seatId = remaining.head
        if (seatId == lastSeatId + 1) {
          helper(remaining.tail, seatId, None)
        } else {
          helper(remaining.tail, seatId, Some(seatId))
        }
      }
    }

    helper(missingIds, -1, None)
  }
}
