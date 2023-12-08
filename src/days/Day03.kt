package days

import readInput
import java.lang.RuntimeException

class Day03(
    override val day: Int = 3,
    override val part1ExpectationTest: Int = 4361,
    override val part2ExpectationTest: Int = 10, // 467835
    override val inputPart: List<String> = readInput("day03"),
    override val inputTest1: List<String> = readInput("day03_test"),
    override val inputTest2: List<String> = readInput("day03_test")
) : Day<Int, List<String>> {
    override fun part2(input: List<String>): Int {
        return input.size
    }

    override fun part1(input: List<String>): Int {
        val engineSchematic = input.mapIndexed { index, line -> mountEngineSchematic(line, index) }
        return engineSchematic.findValid()
            .sumOf { it.number }
    }

    private fun mountEngineSchematic(line: String, row: Int): List<Element> = buildList {
        var start = -1
        var currentNumber = ""

        line.forEachIndexed { index, c ->
            when {
                c.isDigit() -> {
                    currentNumber += c
                    if (start == -1) start = index
                }

                (c != '.') -> {
                    this.add(Symbol(c, index, row))
                    if (currentNumber.isNotEmpty()) {
                        this.add(Number(currentNumber.toInt(), start..<index, row))
                        currentNumber = ""
                        start = -1
                    }
                }

                currentNumber.isNotBlank() -> {
                    this.add(Number(currentNumber.toInt(), start..<index, row))
                    currentNumber = ""
                    start = -1
                }
            }
        }

        if (currentNumber.isNotEmpty()) {
            this.add(Number(number = currentNumber.toInt(), start..<line.length, row = row))
        }
    }

    private fun List<List<Element>>.findValid(): Set<Number> {
        val numbers = this.flatten().filterIsInstance<Number>()
        val symbols = this.flatten().filterIsInstance<Symbol>()
        return numbers.filter { number ->
            symbols.any { symbol ->
                symbol.column in number.expandedColumn &&
                        symbol.row in number.expandedRow
            }
        }.toSet()
    }

    sealed class Element()
    data class Number(val number: Int, val xRange: IntRange, val row: Int) : Element() {
        val expandedColumn = xRange.first - 1..xRange.last + 1
        val expandedRow = row - 1..row + 1
    }

    data class Symbol(val position: Char, val column: Int, val row: Int) : Element()
}
