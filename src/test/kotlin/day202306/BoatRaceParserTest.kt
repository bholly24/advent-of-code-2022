package day202306

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class BoatRaceParserTest {
    private val testData = listOf(
        "Time:      7  15   30",
        "Distance:  9  40  200"
    )

    @Test
    fun calculateMaxDistance() {
        val boatRaceParser = BoatRaceParser()
        assertEquals(288, boatRaceParser.calculateMaxDistance(testData))
    }

    @Test
    fun calculateMaxDistanceAltogether() {
        val boatRaceParser = BoatRaceParser()
        assertEquals(71503, boatRaceParser.calculateMaxDistanceAltogether(testData))
    }
}