package day04

import java.io.File

class CleanupCoordinator(filePath: String) {
    private val assignments: List<List<IntRange>>

    init {
        assignments = getAssignments(filePath)
    }

    fun countTotallyOverlappingAssignments(): Int {
        val total = getTotalByOperation {
                elfOne, elfTwo -> elfOne.subtract(elfTwo).isEmpty() || elfTwo.subtract(elfOne).isEmpty()
        }
        println("The number of completely overlapping assignments is $total")
        return total
    }

    fun countOverlappingAssignments(): Int {
        val total = getTotalByOperation { elfOne, elfTwo -> elfOne.intersect(elfTwo).isNotEmpty() }
        println("The number of assignments with some overlap is $total")
        return total
    }

    private fun getTotalByOperation(setOperation: (elfOne: IntRange, elfTwo: IntRange) -> Boolean): Int {
        return assignments.sumOf { it ->
            it.chunked(2).map { if (setOperation(it[0], it[1])) 1 else 0 }.sum()
        }
    }

    private fun getAssignments(filePath: String): List<List<IntRange>> {
        return File(filePath).readLines()
            .map { line -> line.split(",").map { parseRange(it) } }
    }

    private fun parseRange(range: String): IntRange {
        val split = range.split("-")
        val i = split.first().toIntOrNull() ?: 0
        val j = split.last().toIntOrNull() ?: 0
        return i..j
    }
}