fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { calibration(it) }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { calibrationV2(it) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    val testInput2 = readInput("Day01_test_p2")
    check(part1(testInput) == 142)
    check(part2(testInput2) > 0)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

private fun calibration(input: String): Int {
    val first = input.first { it.isDigit() }
    val last = input.last { it.isDigit() }
    return "$first$last".toInt()
}

fun calibrationV2(input: String): Int {
    val regex = "one|two|three|four|five|six|seven|eight|nine|[0-9]".toRegex()
    val regexReversed = "eno|owt|eerht|ruof|evif|xis|neves|thgie|enin|[0-9]".toRegex()
    val reversed = input.reversed()

    val firstFound = regex.find(input)?.value
    val lastFound = regexReversed.find(reversed)?.value?.reversed()

    val first = convert(firstFound!!)
    val last = convert(lastFound!!)
    return "$first$last".toInt()
}

private fun convert(input: String) : String {
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