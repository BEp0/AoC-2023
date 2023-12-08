fun main() {
    fun mountEngineSchematic(line: String, row: Int): List<Element> = buildList {
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
            this.add(Number(number = currentNumber.toInt(),start..<line.length, row = row))
        }
    }

    fun List<List<Element>>.findValid(): Set<Number> {
        val numbers = this.flatten().filterIsInstance<Number>()
        val symbols = this.flatten().filterIsInstance<Symbol>()
        return numbers.filter { number ->
            symbols.any { symbol ->
                symbol.column in number.expandedColumn &&
                        symbol.row in number.expandedRow
            }
        }.toSet()
    }

    fun part1(input: List<String>): Int {
        val engineSchematic = input.mapIndexed { index, line -> mountEngineSchematic(line, index) }
        return engineSchematic.findValid()
            .sumOf { it.number }
    }

    fun part2(input: List<String>): Long {
        return input.size.toLong()
    }

    val testInput = readInput("day03_test")
    check(part1(testInput) == 4361)

    val input = readInput("day03")
    part1(input).println()
    part2(input).println()
}

sealed class Element()
data class Number(val number: Int, val xRange: IntRange, val row: Int) : Element() {
    val expandedColumn = xRange.first - 1..xRange.last + 1
    val expandedRow = row - 1..row + 1
}
data class Symbol(val position: Char, val column: Int, val row: Int) : Element()