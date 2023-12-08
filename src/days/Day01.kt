package days

import readInput

class Day01(
    override val day: Int = 1,
    override val part1ExpectationTest: Int = 142,
    override val part2ExpectationTest: Int = 281,
    override val inputPart: List<String> = readInput("day01"),
    override val inputTest1: List<String> = readInput("day01_test"),
    override val inputTest2: List<String> = readInput("day01_test_p2")
) : Day<Int, List<String>> {
    override fun part2(input: List<String>): Int {
        return input.sumOf { calibrationV2(it) }
    }

    override fun part1(input: List<String>): Int {
        return input.sumOf { calibration(it) }
    }

    private fun calibration(input: String): Int {
        val first = input.first { it.isDigit() }
        val last = input.last { it.isDigit() }
        return "$first$last".toInt()
    }

    private fun calibrationV2(input: String): Int {
        val regex = "one|two|three|four|five|six|seven|eight|nine|[0-9]".toRegex()
        val regexReversed = "eno|owt|eerht|ruof|evif|xis|neves|thgie|enin|[0-9]".toRegex()
        val reversed = input.reversed()

        val firstFound = regex.find(input)?.value
        val lastFound = regexReversed.find(reversed)?.value?.reversed()

        val first = convert(firstFound!!)
        val last = convert(lastFound!!)
        return "$first$last".toInt()
    }

    private fun convert(input: String): String {
        return when (input.lowercase()) {
            "one" -> "1"
            "two" -> "2"
            "three" -> "3"
            "four" -> "4"
            "five" -> "5"
            "six" -> "6"
            "seven" -> "7"
            "eight" -> "8"
            "nine" -> "9"
            else -> input
        }
    }
}