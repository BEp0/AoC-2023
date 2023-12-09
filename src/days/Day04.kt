package days

import readInput

class Day04(
    override val day: Int = 4,
    override val part1ExpectationTest: Int = 0,
    override val part2ExpectationTest: Int = 0,
    override val inputPart: List<String> = readInput("day04"),
    override val inputTest1: List<String> = readInput("day04_test"),
    override val inputTest2: List<String> = readInput("day04_test")
) : Day<Int, List<String>> {

    override fun part2(input: List<String>): Int {
        return input.size
    }

    override fun part1(input: List<String>): Int {
        return input.size
    }
}