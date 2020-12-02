package de.advent.of.code.day2

final case class Triple(token1   : Int,
                        token2   : Int,
                        character: Char,
                        password : String) {
def validByAmount: Boolean = {
  val amount = password.count(c => c == this.character)
  if (amount >= this.token1 && amount <= this.token2) true else false
}

  def validByPosition: Boolean = {
    val pos1 = this.token1 - 1
    val pos2 = this.token2 - 1
      if (password.charAt(pos1) == this.character ^ password.charAt(pos2) == character) true else false
  }
}

object Validate {

  val ByAmount: Triple => Boolean = triple => triple.validByAmount
  val ByPosition: Triple => Boolean = triple => triple.validByPosition

  private val Token = " "

  def findValid(lines: Vector[String], validateFunc: Triple => Boolean): Int = {
    lines
      .map(line => transformLine(line))
      .count(triple => validateFunc(triple))
  }

  private def transformLine(line: String) = {
    val items = line.split(Token)
    createTriple(items)
  }

  private def createTriple(items: Array[String]) = {
    val minMax    = items(0).split("-")
    val character = items(1).charAt(0)
    val password  = items(2)

    Triple(minMax(0).toInt, minMax(1).toInt, character, password)
  }

  // ----------- Here begins the NOT functional (dirty) part

  def dirty(lines: Vector[String]): Int = {
    var index1 = 0
    var validPasswords = 0
    do {
      val line = lines(index1)
      val items = line.split(" ")
      val minMax = items(0).split("-")
      val min = minMax(0).toInt
      val max = minMax(1).toInt
      val character = items(1).charAt(0)
      val password = items(2)
      val amountOfCharacter = password.count(c => c == character)
      if (amountOfCharacter >= min && amountOfCharacter <= max) {
        validPasswords = validPasswords + 1
      }

      index1 = index1 + 1
    } while (index1 != lines.size)

    validPasswords
  }

  def dirty2(lines: Vector[String]): Int = {
    var index1 = 0
    var validPasswords = 0
    do {
      val line = lines(index1)
      val items = line.split(" ")
      val minMax = items(0).split("-")
      val pos1 = minMax(0).toInt - 1
      val pos2 = minMax(1).toInt - 1
      val character = items(1).charAt(0)
      val password = items(2)
      if (password.charAt(pos1).equals(character) ^ password.charAt(pos2).equals(character)) {
        validPasswords = validPasswords + 1
      }

      index1 = index1 + 1
    } while (index1 != lines.size)

    validPasswords
  }
}
