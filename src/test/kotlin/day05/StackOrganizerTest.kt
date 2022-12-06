package day05

import fileHelper.FileHelper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class StackOrganizerTest {
    @Test
    fun organizes() {
        val stackOrganizer = StackOrganizer(FileHelper.testFileForDay(5))
        assertEquals("CMZ", stackOrganizer.organizeOneAtATime())
    }

    @Test
    fun organizesRetainingOrder() {
        val stackOrganizer = StackOrganizer(FileHelper.testFileForDay(5))
        assertEquals("MCD", stackOrganizer.organizeAllAtOnce())
    }

}