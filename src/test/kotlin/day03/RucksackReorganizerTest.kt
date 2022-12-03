package day03

import fileHelper.FileHelper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class RucksackReorganizerTest {
    private val rucksackReorganizer = RucksackReorganizer(FileHelper.testFileForDay(3))

    @Test
    fun getCostOfMispackedItems() {
        assertEquals(157, rucksackReorganizer.getCostOfMispackedItem())
    }

    @Test
    fun getCostOfBadges() {
        assertEquals(70, rucksackReorganizer.getCostOfBadges())
    }
}