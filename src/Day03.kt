fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readInput("day03_test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("day03")
    part1(input).println()
    part2(input).println()
}