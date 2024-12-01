package day202308

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class InstructionParserTest {

    @Test
    fun getTotalInstructionsToZZZNoLoops() {
        val testInput = listOf(
            "RL",
            "  ",
            "AAA = (BBB, CCC)",
            "BBB = (DDD, EEE)",
            "CCC = (ZZZ, GGG)",
            "DDD = (DDD, DDD)",
            "EEE = (EEE, EEE)",
            "GGG = (GGG, GGG)",
            "ZZZ = (ZZZ, ZZZ)"
        )

        val iParser = InstructionParser()
        assertEquals(2, iParser.getTotalInstructionsToZZZ(testInput))
    }

    @Test
    fun getTotalInstructionsToZZZWithLoops() {
        val testInput = listOf(
            "LLRLR",
            "  ",
            "AAA = (BBB, BBB)",
            "BBB = (AAA, ZZZ)",
            "ZZZ = (ZZZ, ZZZ)"
        )

        val iParser = InstructionParser()
        assertEquals(8, iParser.getTotalInstructionsToZZZ(testInput))
    }

    @Test
    fun getTotalInstructionsToZZZWithImmediateAbort() {
        val testInput = listOf(
            "LLRLR",
            "  ",
            "AAA = (ZZZ, BBB)",
            "BBB = (AAA, ZZZ)",
            "ZZZ = (ZZZ, ZZZ)"
        )

        val iParser = InstructionParser()
        assertEquals(1, iParser.getTotalInstructionsToZZZ(testInput))
    }
}