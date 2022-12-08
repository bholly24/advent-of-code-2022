package day07

import fileHelper.FileHelper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DirectoryParserTest {
    private val directoryParser = DirectoryParser(FileHelper.testFileForDay(7))

    @Test
    fun test() {
        val dir = Directory("/", null)
        val dir2 = Directory("a", dir)
        dir.directories.add(dir2)
        assertEquals("a", dir.directories.first().path)
    }

    // I made the test file more complicated to better challenge recursion depth and troubleshoot
    @Test
    fun getDirectorySizesOfMax() {
        assertEquals(96397, directoryParser.getSize(100000))
    }

    @Test
    fun getDirectorySizeToDelete() {
        assertEquals(24933747, directoryParser.getDirectoryToFreeUpSpace())
    }
}