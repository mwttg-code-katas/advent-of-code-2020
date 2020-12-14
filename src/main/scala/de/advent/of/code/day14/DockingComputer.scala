package de.advent.of.code.day14

import scala.annotation.tailrec

object DockingComputer {

  def initializeProgramMemory(input: Vector[String]): Long = {
    @tailrec
    def helper(remaining: Vector[Instruction],
               currentBitMask: String,
               result        : Map[Int, Long]): Map[Int, Long] = {
      if (remaining.isEmpty) {
        result
      } else {
        remaining.head match {
          case Mask(bitMask) => helper(remaining.tail, bitMask, result)
          case Mem(add, value) => helper(remaining.tail, currentBitMask, executeMemInstruction(add, value, currentBitMask, result))
          case _ => throw new RuntimeException("Oh nooooo!")
        }
      }
    }

    val instructions = Instruction.getAll(input)
    val result       = helper(instructions, "", Map.empty)

    result.values.sum
  }

  private def executeMemInstruction(address: Int,
                                    value  : Long,
                                    bitMask: String,
                                    memory : Map[Int, Long]) = {
    val binaryValue = value.toBinaryString.reverse
    val result      = applyMask(binaryValue, bitMask.reverse)

    memory + (address -> result)
  }

  private def applyMask(value: String,
                        bitMask: String) = {
    @tailrec
    def helper(remaining: Array[Char],
               index    : Int,
               result   : String): Long = {
      if (remaining.isEmpty) {
        java.lang.Long.parseLong(result.reverse, 2)
      } else {
        val mask = remaining.head
        val bit  = if (index <= value.length - 1) value.charAt(index) else "0"

        mask match {
          case 'X' => helper(remaining.tail, index + 1, result + bit)
          case '1' => helper(remaining.tail, index + 1, result + '1')
          case '0' => helper(remaining.tail, index + 1, result + '0')
        }
      }
    }

    helper(bitMask.toCharArray, 0, "")
  }
}
