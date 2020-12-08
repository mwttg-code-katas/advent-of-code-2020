package de.advent.of.code.day8

import de.advent.of.code.day8.Instruction.createInstructions
import org.scalatest.{Matchers, WordSpec}

class Day8Test extends WordSpec with Matchers {

  "Day8 - TDD sheet" should {

    "create NO instruction for ONE line of unknown code" in {
      val input  = Vector("unknown")
      val actual = createInstructions(input)
      actual shouldBe Vector.empty
    }

    "create NO instructions for multiple lines of unknown code" in {
      val input  = Vector("unknown", "can't read", "wont try")
      val actual = createInstructions(input)
      actual shouldBe Vector.empty
    }

    "create NO instruction if separation between operation and argument is not possible" in {
      val input  = Vector("onlyOperation")
      val actual = createInstructions(input)
      actual shouldBe Vector.empty
    }

    "create NO instruction for NO input" in {
      val input  = Vector()
      val actual = createInstructions(input)
      actual shouldBe Vector.empty
    }

    "convert '+3' to Int" in {
      val number = "+3"
      val actual = number.toInt
      actual shouldBe 3
    }

    "convert '-3' to Int" in {
      val number = "-3"
      val actual = number.toInt
      actual shouldBe -3
    }

    "create a NOP (argument) instruction" in {
      val input  = Vector("nop +0")
      val actual = createInstructions(input)

      actual shouldBe Vector(
        Instruction(
          operation = "nop",
          argument = 0))
    }

    "create a JUMP (argument) instruction" in {
      val input  = Vector("jmp +3")
      val actual = createInstructions(input)

      actual shouldBe Vector(
        Instruction(
          operation = "jmp",
          argument = 3))
    }

    "create a ACC (argument) instruction" in {
      val input  = Vector("acc +5")
      val actual = createInstructions(input)

      actual shouldBe Vector(
        Instruction(
          operation = "acc",
          argument = 5))
    }

    "create multiple instruction from multiple lines of code" in {
      val input  = Vector(
        "nop +0",
        "acc +5",
        "jmp -1",
        "unknown")
      val actual = createInstructions(input)

      actual shouldBe Vector(
        Instruction(
          operation = "nop",
          argument = 0),
        Instruction(
          operation = "acc",
          argument = 5),
        Instruction(
          operation = "jmp",
          argument = -1))
    }

    "execute a NOP instruction" in {
      val instruction = Instruction("nop", 0)
      val state       = State.init
      val actual      = state.execute(instruction)

      actual shouldBe State(
        accumulator = 0,
        lineOfCodePointer = 1)
    }

    "execute a ACC instruction" in {
      val instruction = Instruction("acc", 5)
      val state       = State.init
      val actual      = state.execute(instruction)

      actual shouldBe State(
        accumulator = 5,
        lineOfCodePointer = 1)
    }

    "execute a ACC instruction (with negative argument)" in {
      val instruction = Instruction("acc", -5)
      val state       = State.init
      val actual      = state.execute(instruction)

      actual shouldBe State(
        accumulator = -5,
        lineOfCodePointer = 1)
    }

    "execute a JMP instruction" in {
      val instruction = Instruction("jmp", 5)
      val state       = State.init
      val actual      = state.execute(instruction)

      actual shouldBe State(
        accumulator = 0,
        lineOfCodePointer = 5)
    }

    "execute a JMP instruction (with negative argument)" in {
      val instruction = Instruction("jmp", -5)
      val state       = State.init
      val actual      = state.execute(instruction)

      actual shouldBe State(
        accumulator = 0,
        lineOfCodePointer = -5)
    }

    "Advent of Code example Task 1" in {
      val linesOfCode = Vector(
        "nop +0",
        "acc +1",
        "jmp +4",
        "acc +3",
        "jmp -3",
        "acc -99",
        "acc +1",
        "jmp -4",
        "acc +6")
      the [IllegalStateException] thrownBy {
        Handheld.executeProgram(linesOfCode, false)
      } should have message ("loop detected. state is 'State(5,1)'")
    }

    "Advent of Code example Task 2" in {
      val linesOfCode = Vector(
        "nop +0",
        "acc +1",
        "jmp +4",
        "acc +3",
        "jmp -3",
        "acc -99",
        "acc +1",
        "nop -4",
        "acc +6")
      val actual = Handheld.executeProgram(linesOfCode, false)

      actual shouldBe State(
        accumulator = 8,
        lineOfCodePointer = 9)
    }
  }
}
