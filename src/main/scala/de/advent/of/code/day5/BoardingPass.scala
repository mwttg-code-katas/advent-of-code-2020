package de.advent.of.code.day5

final case class BoardingPass(row: String, column: String)

object BoardingPass {

  def getAll(input: Vector[String]): Vector[BoardingPass] = {
    input.map(line => createBoardingPass(line))
  }

  private def createBoardingPass(line: String) = {
    val tuple = line.splitAt(7)
    BoardingPass(tuple._1, tuple._2)
  }
}
