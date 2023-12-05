import java.lang.RuntimeException

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { validGame(it) }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { getCubePower(it) }
    }

    val testInput = readInput("day02_test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("day02")
    part1(input).println()
    part2(input).println()
}

private fun getCubePower(cubeSets: String): Int {

    val cubesMaxPowers: MutableMap<String, Int> = mutableMapOf(
        "blue" to 1,
        "red" to 1,
        "green" to 1
    )

    val possiblePowers = cubeSets.replace("Game .*:".toRegex(), "")
        .split("[;,]".toRegex())
        .map { it.trim() }

    possiblePowers.forEach {

        val cube = it.split(" ")
        val cubePower: Int = cube[0].toInt()
        val cubeColor = cube[1]

        if (cubePower > cubesMaxPowers[cubeColor]!!) {
            cubesMaxPowers[cubeColor] = cubePower
        }
    }

    val maxPowerBlue = cubesMaxPowers["blue"]!!
    val maxPowerRed = cubesMaxPowers["red"]!!
    val maxPowerGreen = cubesMaxPowers["green"]!!

    return maxPowerBlue * maxPowerRed * maxPowerGreen
}

private fun getGameId(game: String) = game.replace("Game ", "").toInt()

private fun checkIsValidGame(subsets: List<String>): Boolean {
    return subsets.filter {
        val cubesSet = it.split(",")
        hasInvalidSet(cubesSet)
    }.isEmpty()
}

private fun hasInvalidSet(cubesSet: List<String>): Boolean {
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

private fun getColorSize(color: String): Int = when(color) {
    "red" -> 12
    "green" -> 13
    "blue" -> 14
    else -> throw RuntimeException("Invalid COLOR: $color")
}