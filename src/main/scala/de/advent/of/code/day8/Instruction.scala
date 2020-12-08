package de.advent.of.code.day8

final case class Instruction(operation: String,
                             argument: Int)

object Instruction {

  private val SeparationToken = " "
  private val Operations      = Set("nop", "jmp", "acc")

  def createInstructions(linesOfCode: Vector[String]): Vector[Instruction] = {
    linesOfCode
      .map(codeLine => codeLine.split(SeparationToken))
      .flatMap {
        case Array(op, arg) if Operations.contains(op) => Some(Instruction(op, arg.toInt))
        case _ => None
      }
  }
}
