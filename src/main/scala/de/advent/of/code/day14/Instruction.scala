package de.advent.of.code.day14

sealed trait Instruction

final case class Mask(bitMask: String) extends Instruction

final case class Mem(address: Int,
                     value  : Long) extends Instruction

object Instruction {

  def getAll(input: Vector[String]): Vector[Instruction] = input.map {
    case line if line.startsWith("mask") => createMask(line)
    case line if line.startsWith("mem") => createMem(line)
    case _ => throw new RuntimeException("Help!")
  }

  private def createMask(line: String) = {
    val bitMask = line.split("=")(1).trim

    Mask(bitMask)
  }

  private def createMem(line: String) = {
    val parts   = line.split("=")
    val value   = parts(1).trim.toLong
    val address = parts(0).trim.substring(4, parts(0).trim.length - 1).toInt

    Mem(address, value)
  }
}
