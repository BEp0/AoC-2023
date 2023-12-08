package days

import java.time.LocalTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.measureTime

interface Day<T, I> {
    val day: Int
    val part1ExpectationTest: T
    val part2ExpectationTest: T
    val inputPart: I
    val inputTest1: I
    val inputTest2: I
    fun part1(input: I): T
    fun part2(input: I): T
    fun run() {

        val test1 = part1(inputTest1)
        val test2 = part2(inputTest2)
        check(test1 == part1ExpectationTest)
        check(test2 == part2ExpectationTest)

        println("=== Day :: $day ===")

        var part1: T
        val time1 = measureTime {
            part1 = part1(inputPart)
        }.inWholeMilliseconds

        println("First part: $part1 $time1 ms")

        var part2: T
        val time2 = measureTime {
            part2 = part2(inputPart)
        }.inWholeMilliseconds

        println("Second part: $part2 $time2 ms")
    }
}