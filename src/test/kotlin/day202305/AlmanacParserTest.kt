package day202305

import fileHelper.FileHelper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AlmanacParserTest {
    private val testInput = FileHelper.getLines(FileHelper.testFileForDay(202305))

    @Test
    fun getLowestLocation() {
        val almanacParser = AlmanacParser()
        assertEquals(35, almanacParser.getLowestLocation(testInput))
    }

    @Test
    fun getLowestRangeLocation() {
        val almanacParser = AlmanacRangeParser()
        assertEquals(46, almanacParser.getLowestLocation(testInput))
    }
}