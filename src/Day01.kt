fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { calibration(it) }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

private fun calibration(input: String): Int {
    val first = input.first { it.isDigit() }
    val last = input.last { it.isDigit() }
    return "$first$last".toInt()
}
