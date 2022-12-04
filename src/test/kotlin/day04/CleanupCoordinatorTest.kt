package day04

import fileHelper.FileHelper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CleanupCoordinatorTest {
    private val cleanupCoordinator = CleanupCoordinator(FileHelper.testFileForDay(4))

    @Test
    fun countTotallyOverlappingAssignments() {
        assertEquals(2, cleanupCoordinator.countTotallyOverlappingAssignments())
    }

    @Test
    fun countOverlappingAssignments() {
        assertEquals(4, cleanupCoordinator.countOverlappingAssignments())
    }
}