package de.advent.of.code.day16

object TicketScanningErrorRate {

  def from(input    : Vector[String],
           rulesSize: Int): Int = {
    val puzzleInput     = PuzzleInput.get(input, rulesSize)
    val validationRules = puzzleInput.rules
    val nearbyTickets   = puzzleInput.otherTickets
    val wrongTickets    = nearbyTickets.map(ticket => getInvalidValuesForTicket(validationRules, ticket))

    wrongTickets.map(item => item.sum).sum
  }

  private def getInvalidValuesForTicket(rules : Vector[ValidationRule],
                                        ticket: Array[Int]) = {
    ticket
      .flatMap(value =>
        rules
          .map(rule => if ((value >= rule.min1 && value <= rule.max1) || (value >= rule.min2 && value <= rule.max2)) None else Some(value))
          .reduceLeft((accumulator, next) => if (next.isEmpty || accumulator.isEmpty) None else Some(value))
      )
  }
}
