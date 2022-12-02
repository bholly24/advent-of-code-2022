package day02

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class RockPaperScissorsCalculatorTest {
    private lateinit var rockPaperScissorsCalculator: RockPaperScissorsCalculator

    @BeforeEach
    fun setup() {
        rockPaperScissorsCalculator = RockPaperScissorsCalculator("src/test/kotlin/day02/input.txt")
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