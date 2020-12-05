package de.advent.of.code.day5

final case class Range(min: Int,
                       max: Int)

object Seat {

  private val MaxRows    = 128
  private val MaxColumns = 8

  def getSeatId(boardingPass: BoardingPass): Int = {
    val row    = getRow(boardingPass.row)
    val column = getColumn(boardingPass.column)
    row.min * 8 + column.min
  }

  private def getRow(input: String) = {
    val range = Range(0, MaxRows)
    input.foldLeft(range) { (accumulator, char) => {
      char match {
        case 'F' => accumulator.copy(min = accumulator.min, max = accumulator.max - ((accumulator.max - accumulator.min) / 2))
        case 'B' => accumulator.copy(min = accumulator.min + ((accumulator.max - accumulator.min) / 2), accumulator.max)
      }
    }
    }
  }

  private def getColumn(input: String) = {
    val range = Range(0, MaxColumns)
    input.foldLeft(range) { (accumulator, char) => {
      char match {
        case 'R' => accumulator.copy(min = accumulator.min + ((accumulator.max - accumulator.min) / 2), accumulator.max)
        case 'L' => accumulator.copy(min = accumulator.min, max = accumulator.max - ((accumulator.max - accumulator.min) / 2))
      }
    }
    }
  }
}
