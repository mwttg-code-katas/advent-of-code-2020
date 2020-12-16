package de.advent.of.code.day16

final case class ValidationRule(name: String,
                                min1: Int,
                                max1: Int,
                                min2: Int,
                                max2: Int)

final case class PuzzleInput(rules       : Vector[ValidationRule],
                             myTicket    : Array[Int],
                             otherTickets: Vector[Array[Int]])

object PuzzleInput {

  def get(lines: Vector[String],
          rulesSize:  Int): PuzzleInput = {
    val (rules, tickets) = lines.splitAt(rulesSize)
    val (yourTicket, otherTickets) = tickets.splitAt(5)

    val validationRules = createValidationRules(rules)
    val myTicket = getTicket(yourTicket(2))
    val nearbyTickets = otherTickets.map(line => getTicket(line))

    PuzzleInput(validationRules, myTicket, nearbyTickets)
  }

  def createValidationRules(rules: Vector[String]): Vector[ValidationRule] = {
    rules
      .map(line => line.split(": "))
      .map(parts => (parts(0), getLimits(parts(1))))
      .map(tuple => ValidationRule(tuple._1, tuple._2._1, tuple._2._2, tuple._2._3, tuple._2._4))

  }

  private def getLimits(str: String) = {
    val limitParts = str.split(" or ")
    val limit1 = limitParts(0).split("-")
    val limit2 = limitParts(1).split("-")

    (limit1(0).toInt, limit1(1).toInt, limit2(0).toInt, limit2(1).toInt)
  }

  private def getTicket(str: String) = str.split(",").map(number => number.toInt)
}
