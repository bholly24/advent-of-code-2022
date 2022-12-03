package day01

import fileHelper.FileHelper
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

internal class CalorieItemizerTest {
    private lateinit var itemizer: CalorieItemizer

    @BeforeEach
    fun setup() {
        itemizer = CalorieItemizer(FileHelper.testFileForDay(1))
    }

    @Test
    fun findMaximumCalories() {
        assertEquals(24000, itemizer.getTotalCaloriesForTopElves(1))
    }

    @Test
    fun findTopThreeCalories() {
        assertEquals(45000, itemizer.getTotalCaloriesForTopElves(3))
    }
}