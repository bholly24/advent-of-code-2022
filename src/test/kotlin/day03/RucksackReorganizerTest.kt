package day03

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class RucksackReorganizerTest {
    private val rucksackReorganizer = RucksackReorganizer("src/test/kotlin/day03/input.txt")

    @Test
    fun getCostOfMispackedItems() {
        assertEquals(157, rucksackReorganizer.getCostOfMispackedItem())
    }

    @Test
    fun getCostOfBadges() {
        assertEquals(70, rucksackReorganizer.getCostOfBadges())
    }
}