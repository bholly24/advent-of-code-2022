package fileHelper

import java.io.File

object FileHelper {
    private val testRoot = "src/test/kotlin/"
    private val mainRoot = "src/main/kotlin/"
    fun testFileForDay(day: Int): String = "${testRoot}day${getDay(day)}/input.txt"
    fun puzzleFileForDay(day: Int): String = "${mainRoot}day${getDay(day)}/input.txt"
    fun getAdditionalTestFile(day: Int, file: String) = "${testRoot}day${getDay(day)}/$file.txt"
    fun getLines(filePath: String): List<String> {
        return File(filePath).readLines()
    }
    private fun getDay(day: Int): String = "${if(day < 10) "0$day" else day}"
}