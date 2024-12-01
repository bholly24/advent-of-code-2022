package day00

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class LineCounterTest {

    @Test
    fun countLines() {
        val lc = LineCounter()
        assertEquals(142, lc.countLines(listOf("1abc2",
                "pqr3stu8vwx",
                "a1b2c3d4e5f",
                "treb7uchet")))
    }

    @Test
    fun countLinesAfterConversion() {
        val lc = LineCounter()
        assertEquals(281, lc.countLines(lc.convertLinesToNumbers(listOf(
            "two1nine",
                    "eightwothree",
                    "abcone2threexyz",
                    "xtwone3four",
                    "4nineeightseven2",
                    "zoneight234",
            "7pqrstsixteen",
            "one"
        ))))
    }
}