interface Element {
    fun getValue(): Int
}

fun main() {

    data class Number(val value: String, val xRange: IntRange, val row: Int) : Element {
        val expandedColumn = xRange.first - 1..xRange.last + 1
        val expandedRow = row - 1..row + 1
        override fun getValue(): Int {
            return value.toInt()
        }
    }

    data class Symbol(val position: Char, val column: Int, val row: Int) : Element {
        override fun getValue(): Int {
            TODO("Not yet implemented")
        }
    }


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
                        this.add(Number(currentNumber, start..<index, row))
                        currentNumber = ""
                        start = -1
                    }
                }

                else -> {
                    if (currentNumber.isNotBlank()) {
                        this.add(Number(currentNumber, start..<index, row))
                        currentNumber = ""
                        start = -1
                    }
                }
            }
        }
    }

    fun List<List<Element>>.findValid(): Set<Element> {
        val parts = mutableSetOf<Number>()
        this.windowed(2).map { twoRows ->
            val numbers = twoRows.flatten().filterIsInstance<Number>()
            val symbols = twoRows.flatten().filterIsInstance<Symbol>()
            numbers.filter { number ->
                symbols.any { symbol: Symbol ->
                    symbol.column in number.expandedColumn &&
                            symbol.row in number.expandedRow
                }
            }.forEach { parts.add(it) }
        }
        return parts
    }

    fun part1(input: List<String>): Int {
        val engineSchematic = input.mapIndexed { index, line -> mountEngineSchematic(line, index) }
        return engineSchematic.findValid()
            .sumOf { it.getValue() }
    }

    fun part2(input: List<String>): Long {
        return input.size.toLong()
    }

//    val testInput = readInput("day03_test")
//    check(part1(testInput) == 3611)

    val input = readInput("day03")
    part1(input).println()
    part2(input).println()
}