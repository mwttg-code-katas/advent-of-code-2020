package de.advent.of.code.day4

object StrictValidation {

  private val EyeColor = Set("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
  private val Hex      = Set('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')

  def isValid(passport: Passport): Boolean = {
    validBirthYear(passport.birthYear) &&
      validIssueYear(passport.issueYear) &&
      validExpirationYear(passport.expirationYear) &&
      validHeight(passport.height) &&
      validHairColor(passport.hairColor) &&
      validEyeColor(passport.eyeColor) &&
      validPassportId(passport.passportId)
  }


  private def validBirthYear(birthYear: String): Boolean = {
    if (birthYear == null || birthYear.isEmpty || birthYear.length != 4) return false

    val year = birthYear.toInt
    year >= 1920 && year <= 2002
  }

  private def validIssueYear(issueYear: String): Boolean = {
    if (issueYear == null || issueYear.isEmpty || issueYear.length != 4) return false

    val year = issueYear.toInt
    year >= 2010 && year <= 2020

  }

  private def validExpirationYear(expirationYear: String): Boolean = {
    if (expirationYear == null || expirationYear.isEmpty || expirationYear.length != 4) return false

    val year = expirationYear.toInt
    year >= 2020 && year <= 2030
  }

  private def validHeight(height: String): Boolean = {
    if (height == null || height.isEmpty) return false

    val tuple = height.splitAt(height.length - 2)
    if (tuple._1.isEmpty || tuple._2.isEmpty) return false

    val value = tuple._1.toInt
    val unit  = tuple._2
    unit match {
      case "cm" => if (value >= 150 && value <= 193) true else false
      case "in" => if (value >= 59 && value <= 76) true else false
      case _ => false
    }
  }

  private def validHairColor(hairColor: String): Boolean = {
    if (hairColor == null || hairColor.isEmpty || hairColor.length != 7) return false

    hairColor.toCharArray match {
      case Array(s, x1, x2, x3, x4, x5, x6) if s == '#' && Hex.contains(x1) && Hex.contains(x2) && Hex.contains(x3) && Hex.contains(x4) && Hex.contains(x5) && Hex.contains(x6) => true
      case _ => false
    }
  }

  private def validEyeColor(eyeColor: String): Boolean = {
    if (eyeColor == null || eyeColor.isEmpty) return false
    EyeColor.contains(eyeColor)
  }

  private def validPassportId(passportId: String): Boolean = {
    if (passportId == null || passportId.isEmpty || passportId.length != 9) return false

    passportId.toCharArray match {
      case Array(x1, x2, x3, x4, x5, x6, x7, x8, x9) if x1.isDigit && x2.isDigit && x3.isDigit && x4.isDigit && x5.isDigit && x6.isDigit && x7.isDigit && x8.isDigit && x9.isDigit => true
      case _ => false
    }
  }
}
