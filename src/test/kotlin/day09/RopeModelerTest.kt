package day09

import fileHelper.FileHelper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class RopeModelerTest {
    private val ropeModeler = RopeModeler(FileHelper.testFileForDay(9))

    @Test
    fun getTotalListOfPositions() {
        assertEquals(13, ropeModeler.getUniquePositionsVisitedForRope(2))
    }

    @Test
    fun getTotalPositionsForLengthNine() {
        assertEquals(1, ropeModeler.getUniquePositionsVisitedForRope(10))
    }

    @Test
    fun getTotalPositionsForNineLarger() {
        val longRopeModeler = RopeModeler(FileHelper.getAdditionalTestFile(9, "input-long"))
        assertEquals(36, longRopeModeler.getUniquePositionsVisitedForRope(10))
    }
}