package de.advent.of.code.utils

import scala.io.Source
import scala.util.{Failure, Success, Try}

object FileReader {

  def asStrings(filename: String): Vector[String] =
    Try(Source.fromResource(filename).getLines().toVector) match {
      case Success(lines) => lines
      case Failure(exception) =>
        exception.printStackTrace()
        Vector.empty
    }

  def asInts(filename: String): Vector[Int] =
    Try(Source.fromResource(filename).getLines().toVector) match {
      case Success(lines) => lines.map(number => number.toInt)
      case Failure(exception) =>
        exception.printStackTrace()
        Vector.empty
    }

  def asLongs(filename: String): Vector[Long] =
    Try(Source.fromResource(filename).getLines().toVector) match {
      case Success(lines) => lines.map(number => number.toLong)
      case Failure(exception) =>
        exception.printStackTrace()
        Vector.empty
    }
}
