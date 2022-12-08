package day08

import fileHelper.FileHelper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class TreeCounterTest {
    private val treeCounter = TreeCounter(FileHelper.testFileForDay(8))

    @Test
    fun countVisibleTrees() {
        assertEquals(21, treeCounter.getTotalVisibleTrees())
    }

    @Test
    fun getMaxVisibilityScore() {
        assertEquals(8, treeCounter.getMaxScenicScore())
    }
}