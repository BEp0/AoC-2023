import days.*

fun main() {

    val days = listOf(Day01(), Day02(), Day03(), Day04(), Day05())
    days.forEach { day -> day.exec() }

}