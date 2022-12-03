package day02

import fileHelper.FileHelper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class RockPaperScissorsCalculatorTest {
    private lateinit var rockPaperScissorsCalculator: RockPaperScissorsCalculator

    @BeforeEach
    fun setup() {
        rockPaperScissorsCalculator = RockPaperScissorsCalculator(FileHelper.testFileForDay(2))
    }

    @Test
    fun scoreSuggestions() {
        assertEquals(15, rockPaperScissorsCalculator.scoreSimpleSuggestions())
    }

    @Test
    fun scoreReactiveSuggestions() {
        assertEquals(12, rockPaperScissorsCalculator.scoreReactiveResponses())
    }
}