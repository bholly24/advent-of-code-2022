package day00

import java.io.File

class LineCounter {
    init {

    }
    fun countLines(lines: List<String>): Int {
//        val lines = getLines(filePath)
        return lines.sumOf {
            val digits = it.filter { c -> c.isDigit() }
            "${digits.first()}${digits.last()}".toInt()
        }
    }

    // Replace in place while keeping the last letter to allow "oneeight" to be converted to "one1eight8"
    fun convertLinesToNumbers(lines: List<String>): List<String> {
        return lines.map {
            it
                .replace("one", "one1e")
                .replace("two", "two2o")
                .replace("three", "three3e")
                .replace("four", "four4r")
                .replace("five", "five5e")
                .replace("six", "six6x")
                .replace("seven", "seven7")
                .replace("eight", "eight8t")
                .replace("nine", "nine9e")
        }
    }
}