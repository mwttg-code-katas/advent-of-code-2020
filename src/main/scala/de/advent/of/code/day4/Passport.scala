package de.advent.of.code.day4

import scala.annotation.tailrec

final case class Passport(birthYear: String,
                          issueYear: String,
                          expirationYear: String,
                          height: String,
                          hairColor: String,
                          eyeColor: String,
                          passportId: String,
                          countryId: String) {

  def isValid: Boolean =
    this.birthYear != null &&
      this.issueYear != null &&
      this.expirationYear != null &&
      this.height != null &&
      this.hairColor != null &&
      this.eyeColor != null &&
      this.passportId != null

  def isStrictValid : Boolean =
    StrictValidation.isValid(this)
}

object Passport {

  private val Space = " "
  private val KeyValueSeparationToken = ":"

  def init: Passport = Passport(null, null, null, null, null, null, null, null)

  def getAll(input: Vector[String]): Vector[Passport] = {
    @tailrec
    def helper(remainingLines: Vector[String],
               currentPassportData: Vector[String],
               rawPassports       : Vector[Passport]): Vector[Passport] = {
      if (remainingLines.isEmpty) {
        rawPassports
      } else {
        val line = remainingLines.head
        if (line.isEmpty) { // I had to enter an empty line to the puzzle.txt or last passport is missing :/
          val passport = createPassport(currentPassportData)
          helper(remainingLines.tail, Vector.empty, rawPassports :+ passport)
        } else {
          helper(remainingLines.tail, currentPassportData :+ line, rawPassports)
        }
      }
    }

    helper(input, Vector.empty, Vector.empty)
  }

  private def createPassport(rawData: Vector[String]) = {
    val data  = rawData.mkString(Space)
    val items = data.split(Space);
    mapFields(items)
  }

  private def mapFields(fields: Array[String]) = {
    @tailrec
    def helper(remainingFields: Array[String],
               passport: Passport): Passport = {
      if (remainingFields.isEmpty) {
        passport
      } else {
        val currentField = remainingFields.head
        val keyValue     = currentField.split(KeyValueSeparationToken)
        keyValue(0).toLowerCase match {
          case "byr" => helper(remainingFields.tail, passport.copy(birthYear = keyValue(1)))
          case "iyr" => helper(remainingFields.tail, passport.copy(issueYear = keyValue(1)))
          case "eyr" => helper(remainingFields.tail, passport.copy(expirationYear = keyValue(1)))
          case "hgt" => helper(remainingFields.tail, passport.copy(height = keyValue(1)))
          case "hcl" => helper(remainingFields.tail, passport.copy(hairColor = keyValue(1)))
          case "ecl" => helper(remainingFields.tail, passport.copy(eyeColor = keyValue(1)))
          case "pid" => helper(remainingFields.tail, passport.copy(passportId = keyValue(1)))
          case "cid" => helper(remainingFields.tail, passport.copy(countryId = keyValue(1)))
          case _ => throw new RuntimeException("Help")
        }
      }
    }

    helper(fields, Passport.init)
  }
}
