package days

import readInput

class Day05(
    override val day: Int = 5,
    override val part1ExpectationTest: Int = 33,
    override val part2ExpectationTest: Int = 33,
    override val inputPart: List<String> = readInput("day05"),
    override val inputTest1: List<String> = readInput("day05_test"),
    override val inputTest2: List<String> = readInput("day05_test"),
) : Day<Int, List<String>> {
    override fun part1(input: List<String>): Int {
        return input.size
    }

    override fun part2(input: List<String>): Int {
        return input.size
    }
}