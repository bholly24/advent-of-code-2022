package fileHelper

object FileHelper {
    fun testFileForDay(day: Int): String = "src/test/kotlin/day${getDay(day)}/input.txt"
    fun puzzleFileForDay(day: Int): String = "src/main/kotlin/day${getDay(day)}/input.txt"
    private fun getDay(day: Int): String = "${if(day < 10) "0$day" else day}"
}