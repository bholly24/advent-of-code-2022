package day202308

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MultipleInstructionParserTest {

    @Test
    fun getTotalInstructionsToZZZ() {
        val testInput = listOf(
            "LR",
            "   ",
            "11A = (11B, XXX)",
            "11B = (XXX, 11Z)",
            "11Z = (11B, XXX)",
            "22A = (22B, XXX)",
            "22B = (22C, 22C)",
            "22C = (22Z, 22Z)",
            "22Z = (22B, 22B)",
            "XXX = (XXX, XXX)"
        )
        val multipleInstructionParser = MultipleInstructionParser()
        assertEquals(6L, multipleInstructionParser.getTotalInstructionsToZZZ(testInput))
    }
}