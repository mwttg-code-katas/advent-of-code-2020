package de.advent.of.code.day3

import org.scalatest.{Matchers, WordSpec}

class AllSlopesTest extends WordSpec with Matchers {
  "TestCase" should {
    "return 336" in {
      val input = Vector(
        "..##.......",
        "#...#...#..",
        ".#....#..#.",
        "..#.#...#.#",
        ".#...##..#.",
        "..#.##.....",
        ".#.#.#....#",
        ".#........#",
        "#.##...#...",
        "#...##....#",
        ".#..#...#.#")

      val actual = AllSlopes.countTrees(input)
      actual shouldBe 336
    }
  }
}
