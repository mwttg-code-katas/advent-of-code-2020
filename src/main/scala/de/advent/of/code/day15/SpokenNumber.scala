package de.advent.of.code.day15

import scala.annotation.tailrec

final case class SpokenNumber(previousLastIndex: Int,
                              lastIndex        : Int) {

  def update(turn: Int): SpokenNumber = {
    SpokenNumber(this.lastIndex, turn)
  }
}

object SpokenNumber {

  def ofIndex(finishTurn: Int,
              init      : Vector[Int]): Int = {
    val initNumberByLastSpokenIndex = createNumberByIndex(init)
    val initLastSpokenNumber        = init.last
    val initTurn                    = initNumberByLastSpokenIndex.keySet.size + 1

    @tailrec
    def helper(turn: Int,
               numbersByIndex: Map[Int, SpokenNumber],
               lastSpokenNumber: Int): Int = {
      if (turn == finishTurn + 1) {
        lastSpokenNumber
      } else {
        val spokenNumber                = getSpokenNumber(numbersByIndex, lastSpokenNumber)
        val newNumbersByLastSpokenIndex = updateNumbersByIndex(numbersByIndex, spokenNumber, turn)

        helper(turn + 1, newNumbersByLastSpokenIndex, spokenNumber)
      }
    }

    val result = helper(initTurn, initNumberByLastSpokenIndex, initLastSpokenNumber)
    result
  }

  private def updateNumbersByIndex(numbersByIndex: Map[Int, SpokenNumber],
                                   spokenNumber: Int,
                                   turn: Int) =
    if (!numbersByIndex.contains(spokenNumber)) {
      numbersByIndex + (spokenNumber -> SpokenNumber(0, turn))
    } else {
      val item = numbersByIndex(spokenNumber)
      numbersByIndex + (spokenNumber -> item.update(turn))
    }

  private def getSpokenNumber(numbersByLastSpokenIndex: Map[Int, SpokenNumber],
                              lastSpokenNumber        : Int) =
    if (firstSpoken(numbersByLastSpokenIndex, lastSpokenNumber)) {
      0
    } else {
      val numberIndex = numbersByLastSpokenIndex(lastSpokenNumber)
      numberIndex.lastIndex - numberIndex.previousLastIndex
    }

  private def firstSpoken(numbersByLastSpokenIndex: Map[Int, SpokenNumber],
                          lastSpokenNumber: Int) =
    (numbersByLastSpokenIndex.contains(lastSpokenNumber) && numbersByLastSpokenIndex(lastSpokenNumber).previousLastIndex == 0) || !numbersByLastSpokenIndex.contains(lastSpokenNumber)

  private def createNumberByIndex(init: Vector[Int]) =
    init.zipWithIndex.map(tuple => (tuple._1, SpokenNumber(0, tuple._2 + 1))).toMap
}
