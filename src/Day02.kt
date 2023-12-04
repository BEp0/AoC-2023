import java.lang.RuntimeException

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { validGame(it) }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

private fun getGameId(game: String) = game.replace("Game ", "").toInt()

private fun checkIsValidGame(subsets: List<String>): Boolean {
    return subsets.filter {
        val cubesSet = it.split(",")
        hasInvalidSet(cubesSet)
    }.isEmpty()
}

fun hasInvalidSet(cubesSet: List<String>): Boolean {
    return cubesSet.filter {
        val cube = it.trim().split(" ")

        val colorSize = cube[0].toInt()
        val color = cube[1]

        val maxColorSize = getColorSize(color)

        colorSize > maxColorSize
    }.isNotEmpty()
}

private fun validGame(gameInput: String): Int {
    val split = gameInput.split(":")
    val game = split[0]
    val subsets = split[1]

    val gameId = getGameId(game)
    val isValidGame = checkIsValidGame(subsets.split(";"))
    if (isValidGame)
        return gameId

    return 0
}

fun getColorSize(color: String): Int = when(color) {
    "red" -> 12
    "green" -> 13
    "blue" -> 14
    else -> throw RuntimeException("Invalid COLOR: $color")
}