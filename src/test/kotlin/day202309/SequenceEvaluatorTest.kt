package day202309

import day202309.SequenceEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SequenceEvaluatorTest {

    @Test
    fun getNextValues() {
        val testInput = listOf(
            "0 3 6 9 12 15",
            "1 3 6 10 15 21",
            "10 13 16 21 30 45",
            "-4 -6 -8 -10 -12"
        )

        val sequenceEvaluator = SequenceEvaluator()
        assertEquals(114L - 14L, sequenceEvaluator.getNextValues(testInput))
    }

    @Test
    fun getNextValuesWithRecursion() {
        val testInput = listOf(
            "0 3 6 9 12 15",
            "1 3 6 10 15 21",
            "10 13 16 21 30 45",
            "-4 -6 -8 -10 -12"
        )

        val sequenceEvaluator = SequenceEvaluator()
        assertEquals(114L - 14L, sequenceEvaluator.getNextValuesWithRecursion(testInput))
    }

    @Test
    fun getPriorValuesWithRecursion() {
        val testInput = listOf(
            "0 3 6 9 12 15",
            "1 3 6 10 15 21",
            "10 13 16 21 30 45",
            "-4 -6 -8 -10 -12"
        )

        val sequenceEvaluator = SequenceEvaluator()
        assertEquals(-3L + -2L + 7L + -2L, sequenceEvaluator.getPriorValuesWithRecursion(testInput))
    }
}