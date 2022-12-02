package day01

import java.io.File
import java.io.InputStream
import java.util.*

class CalorieItemizer(filePath: String) {
    private val elves: List<List<Int>>

    init {
        elves = getElves(filePath)
    }

    fun getTotalCaloriesForTopElves(numberOfElves: Int): Int {
        val elfTotals = elves.map { it.sum() }
        val calorieTotal = elfTotals
            .sortedDescending()
            .slice(0 until numberOfElves)
            .sum()
        printAnswer(numberOfElves, calorieTotal)
        return calorieTotal
    }

    private fun printAnswer(numberOfElves: Int, total: Int) {
        val displayNumber = if (numberOfElves == 1) "elf" else "$numberOfElves elves"
        println("The total calories carried by the top $displayNumber is $total")
    }

    private fun getElves(filePath: String): List<List<Int>> {
        val inputStream: InputStream = File(filePath).inputStream()
        val elves = mutableListOf<List<Int>>()
        var calorieList = mutableListOf<Int>()

        inputStream.bufferedReader().forEachLine {
            val parseAttempt = it.toIntOrNull()
            if (parseAttempt == null) {
                elves.add(calorieList)
                calorieList = mutableListOf()
            } else {
                calorieList.add(parseAttempt)
            }
        }.also {
            if (calorieList.isNotEmpty()) {
                elves.add(calorieList)
            }
        }

        return Collections.unmodifiableList(elves)
    }
}