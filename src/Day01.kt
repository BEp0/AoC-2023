fun main() {
    fun part1(input: List<String>): Int {

        val numbers = ArrayList<Int>()

        input.forEach {
            val lineNumbers = it.replace("[^0-9]".toRegex(), "")
            val firstNumber = lineNumbers[0].toString()
            val lastNumber = lineNumbers[lineNumbers.length - 1].toString()
            val result = firstNumber.plus(lastNumber).toInt()
            numbers.add(result)
        }

        return numbers.sum()

    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 1)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
