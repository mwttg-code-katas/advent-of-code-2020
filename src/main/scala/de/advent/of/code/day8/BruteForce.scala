package de.advent.of.code.day8

import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}

object BruteForce {

  def start(linesOfCode: Vector[String]): State = {
    val jmp2Nop = createVariations(linesOfCode, "jmp", "nop")
    val nop2Jmp = createVariations(linesOfCode, "nop", "jmp")
    val all     = jmp2Nop.appendedAll(nop2Jmp)
    val maybe   = findCorrectCode(all)

    maybe.head
  }

  private def createVariations(linesOfCode: Vector[String],
                               target: String,
                               replacement: String) = {
    val maxLinesOfCode = linesOfCode.size - 1

    @tailrec
    def helper(index: Int,
               result: Vector[Vector[String]]): Vector[Vector[String]] = {
      if (index == maxLinesOfCode) {
        result
      } else {
        val (front, back) = linesOfCode.splitAt(index)
        val replacedLine  = front.last.replace(target, replacement)
        val newCode       = front.dropRight(1) :+ replacedLine :++ back

        helper(index + 1, result :+ newCode)
      }
    }

    helper(1, Vector.empty)
  }

  private def findCorrectCode(variations: Vector[Vector[String]]) = {
    variations
      .map(sourceCode => Try(Handheld.executeProgram(sourceCode, false)))
      .flatMap {
        case Success(state) => Some(state)
        case Failure(_) => None
      }
  }
}
