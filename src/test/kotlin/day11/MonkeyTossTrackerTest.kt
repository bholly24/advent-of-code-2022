package day11

import fileHelper.FileHelper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MonkeyTossTrackerTest {
    private val monkeyTossTracker = MonkeyTossTracker(FileHelper.testFileForDay(11))
    @Test
    fun findMostMonkeyBusinessTwentyRounds() {
       assertEquals(10605, monkeyTossTracker.findMostMonkeyBusinessOverTwentyRounds())
    }

    @Test
    fun findMostMonkeyBusinessTenThousandRounds() {
        assertEquals(2713310158, monkeyTossTracker.findMostMonkeyBusinessOverTenThousandRounds())
    }
}