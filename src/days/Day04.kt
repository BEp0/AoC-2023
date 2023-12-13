package days

import readInput

private const val delimiterCards = "|"

private const val delimiterNumbers = " "

class Day04(
    override val day: Int = 4,
    override val part1ExpectationTest: Int = 13,
    override val part2ExpectationTest: Int = 30,
    override val inputPart: List<String> = readInput("day04"),
    override val inputTest1: List<String> = readInput("day04_test"),
    override val inputTest2: List<String> = readInput("day04_test"),
) : Day<Int, List<String>> {

    override fun part2(input: List<String>): Int {
        return mountCards(input)
            .scratchcards()
            .calculateScratchcards()
            .sum()
    }

    override fun part1(input: List<String>): Int {
        return mountCards(input)
            .sumOf { it.getPoints() }
    }

    private fun mountCards(input: List<String>): List<Card> {

        val cards = input.map {
            it.replace("Card .*:".toRegex(), "")
                .split(delimiterCards)
        }

        return cards.mapIndexed { index, card ->
            val numbers = card.splitToNumbers(0)
            val winners = card.splitToNumbers(1)
            Card(index = index, numbers = numbers, winnerNumbers = winners.toSet())
        }
    }
}

private fun List<String>.splitToNumbers(index: Int): List<Int> {
    val split = this[index].trim().split(delimiterNumbers)
    return split
        .filter { it.isNotEmpty() }
        .map { it.toInt() }
        .toList()
}

data class Card(val index: Int, val numbers: List<Int>, val winnerNumbers: Set<Int>) {

    val scratchcards = Scratchcards()
    private val scores = mapOf(
        0 to 0,
        1 to 1,
        2 to 2,
        3 to 4,
        4 to 8,
        5 to 16,
        6 to 32,
        7 to 64,
        8 to 128,
        9 to 256,
        10 to 512,
    )

    fun getPoints(): Int {
        val matches = getMatches()
        return scores[matches] ?: 0
    }

    fun getMatches() = numbers.count { winnerNumbers.contains(it) }
}

fun List<Card>.scratchcards(): List<Card> {
    this.forEachIndexed { index, card ->
        val points = card.getMatches()
        if (points > 0) {

            var gap = points

            val nextCard = index + 1

            if (nextCard + gap > this.size)
                gap = this.size
            else
                gap += nextCard

            val scratchcards = this.subList(nextCard, gap)
            card.scratchcards.addAll(scratchcards)
        }
    }
    return this
}

fun List<Card>.calculateScratchcards(): MutableMap<Int, Int> {

    val scratchcards = mutableMapOf<Int, Int>()

    this.forEach { scratchcards[it.index] = 1 }

    this.forEach { originalCard ->
        if (originalCard.scratchcards.isNotEmpty()) {
            val scratchFromScratchcards = originalCard.scratchcards.calculateScratchcards()
            if (scratchFromScratchcards.isNotEmpty())
                scratchFromScratchcards.forEach {
                    if(scratchcards.containsKey(it.key))
                        scratchcards[it.key] = scratchcards[it.key]!! + scratchFromScratchcards[it.key]!!
                    else
                        scratchcards[it.key] = scratchFromScratchcards[it.key]!!
                }
        }
    }

    return scratchcards
}

fun MutableMap<Int, Int>.sum(): Int {
    return this.map { (_, value) -> value }.sum()
}

typealias Scratchcards = ArrayList<Card>