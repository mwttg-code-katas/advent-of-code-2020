package de.advent.of.code.day8

final case class State(accumulator: Int,
                       lineOfCodePointer: Int) {

  def execute(instruction: Instruction): State = {
    instruction match {
      case Instruction("nop", _) => State(this.accumulator, this.lineOfCodePointer + 1)
      case Instruction("acc", arg) => State(this.accumulator + arg, this.lineOfCodePointer + 1)
      case Instruction("jmp", arg) => State(this.accumulator, this.lineOfCodePointer + arg)
      case _ => throw new RuntimeException("Help!")
    }
  }
}

object State {

  def init: State = State(0, 0)
}
