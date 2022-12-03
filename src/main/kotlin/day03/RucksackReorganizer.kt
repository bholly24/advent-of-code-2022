package day03

import java.io.File

class RucksackReorganizer(filePath: String) {
    private val rucksacks: List<List<Char>>

    init {
        rucksacks = getRucksacks(filePath)
    }

    fun getCostOfMispackedItem(): Int {
        val totalCost = rucksacks.sumOf {
            val compartments = splitLine(it)
            getLetterCostFromAscii(compartments[0].intersect(compartments[1]).first())
        }
        println("Total cost of reorganization due to mispacked items is $totalCost")
        return totalCost
    }

    fun getCostOfBadges(): Int {
        val totalCost = rucksacks
            .chunked(3)
            .sumOf { getLetterCostFromAscii(it[0].intersect(it[1]).intersect(it[2]).first()) }
        println("Total cost of reorganization due to badges is $totalCost")
        return totalCost
    }

    private fun getLetterCostFromAscii(letter: Char): Int {
        return if (letter.isLowerCase()) letter.code - 96 else letter.code - 38
    }

    private fun getRucksacks(filePath: String): List<List<Char>> {
        return File(filePath).readLines()
            .map { line -> line.toList()}
    }

    private fun splitLine(line: List<Char>): List<List<Char>> {
        val indexMidpoint = (line.size / 2) - 1
        return listOf(line.slice(0..indexMidpoint), line.slice(indexMidpoint + 1 until line.size))
    }
}