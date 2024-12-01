package day202307

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class CardParserTest {

    private val testData = listOf(
        "32T3K 765",
        "T55J5 684",
        "KK677 28",
        "KTJJT 220",
        "QQQJA 483"
    )

    @Test
    fun getHandsByRank() {
        val cardParser = CardParser()
        assertEquals(6440, cardParser.getHandsByRank(testData))
    }

    @Test
    fun getJokerHandsByRank() {
        val cardParser = CardParser()
        assertEquals(5905, cardParser.getJokerHandsByRank(testData))
    }
}