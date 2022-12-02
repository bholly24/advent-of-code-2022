package day01

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

internal class CalorieItemizerTest {
    private lateinit var itemizer: CalorieItemizer

    @BeforeEach
    fun setup() {
        itemizer = CalorieItemizer("src/test/kotlin/day01/input.txt")
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