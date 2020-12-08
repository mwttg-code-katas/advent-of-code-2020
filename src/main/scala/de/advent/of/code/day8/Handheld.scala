package de.advent.of.code.day8

import de.advent.of.code.day8.State.init

import scala.annotation.tailrec

object Handheld {

  def executeProgram(linesOfCode: Vector[String],
                     isDebug     : Boolean): State = {
    val instructions   = Instruction.createInstructions(linesOfCode)
    val lastLineOfCode = instructions.size - 1

    @tailrec
    def helper(isLoop: Boolean,
               executedLines: Set[Int],
               state : State): State = {
      if (isLoop) {
        // println("loop detected") // comment throw and use these two line for time measurement
        // state
        throw new IllegalStateException(s"loop detected. state is '$state'")
      } else if (executedLines.contains(lastLineOfCode)) {
        // println("terminate...")
        state
      } else {
        val pointer     = state.lineOfCodePointer
        val instruction = instructions(pointer)
        debug(isDebug, state, instruction)
        val maybeLoop = if (executedLines.contains(pointer)) true else false

        if (maybeLoop) {
          helper(isLoop = true, executedLines, state)
        } else {
          helper(isLoop = false, executedLines + pointer, state.execute(instruction))
        }
      }
    }

    val initState = init
    helper(isLoop = false, Set.empty, initState)
  }

  private def debug(debug: Boolean,
                    state: State,
                    instruction: Instruction): Unit = {
    if (debug) {
      println(s"[STATE| acc='${state.accumulator}', pointer='${state.lineOfCodePointer}'] ==[execute]==> [INSTRUCTION| op='${instruction.operation}', arg='${instruction.argument}']")
    }
  }
}
