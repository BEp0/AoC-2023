import days.Day

fun main() {

    val days = listOf<Day<Int, List<Int>>>()
    days.forEach { day -> day.exec() }

}