package day202303

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SchematicParserTest {

    // Test is modified to express wrap around issue
    private val testData = listOf<String>(
        "467..114..",
        "...*......",
        "..35..633.",
        "......#...",
        "617*......",
        ".....+.58.",
        "..592.....",
        "......755.",
        ".....*....",
        "/664.598.^",
        ".........6",
        "6*........"
    )

    @Test
    fun findRelevantNumbers() {
        val parser = SchematicParser(testData)
//        assertEquals(4373, parser.findRelevantNumbers())
        assertEquals(4373, parser.testThirty())
    }

    @Test
    fun findSumOfRatios() {
        val parser = SchematicParser(testData)
        assertEquals(467835, parser.findGears())
        assertEquals(223, parser.testGearSum())
    }
}