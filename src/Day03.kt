interface Element {
    fun getValue(): Int
}

fun main() {

    data class Number(val value: String, val xRange: IntRange, val row: Int) : Element {
        val expandedColumn = xRange.first - 1..xRange.last + 1
        val expandedRow = row..row + 1
        override fun getValue(): Int {
            TODO("Not yet implemented")
        }
    }

    data class Symbol(val position: Char, val index: Int, val row: Int) : Element {
        override fun getValue(): Int {
            TODO("Not yet implemented")
        }
    }

    fun numberValidInLine(line: String, row: Int): List<Element> = buildList {

        var currentNumber = ""
        var numberStart = -1

        line.forEachIndexed { index, c ->

            when {
                c.isDigit() -> {
                    currentNumber += c
                    if (numberStart == -1) numberStart = index
                }

                else -> {
                    if (c != '.') this.add(Symbol(c, index, row))
                    if (currentNumber.isNotEmpty()) {
                        this.add(Number(currentNumber, numberStart..<index - 1, row))
                    }
                }
            }

        }
    }

    fun part1(input: List<String>): Int {
        return input.mapIndexed { index, line -> numberValidInLine(line, index) }
            .sumOf { elements ->
                elements.sumOf { element ->
                    element.getValue()
                }
            }
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