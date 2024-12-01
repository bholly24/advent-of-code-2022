package day202302

import java.io.File

class MarbleCounter {
    fun determinePossibleGames(gameInput: List<String>): Int {
        var total = 0
        gameInput.forEach {
            val inputSplit = it.split(":")
            val gameId: Int = inputSplit.first().filter { gameId -> gameId.isDigit() }.toInt()
            val values: List<Pair<Color, Int>> = inputSplit[1].split(",", ";").map { value -> getColorAndValue(value) }
            if (values.all { v -> isColorPossible(v) }) {
                total += gameId
            }
        }
        return total
    }

    fun calculateCubePower(gameInput: List<String>): Int {
        var powerSum = 0
        gameInput.forEach {
            val inputSplit = it.split(":")
            val cubeMap = mutableMapOf<Color, Int>(Color.Red to 0, Color.Green to 0, Color.Blue to 0)
            val values: List<Pair<Color, Int>> = inputSplit[1].split(",", ";").map { value -> getColorAndValue(value) }
            values.forEach { pair ->
                if (pair.second > cubeMap.getOrDefault(pair.first, 0)) {
                    cubeMap[pair.first] = pair.second
                }
            }
            powerSum += cubeMap.getOrDefault(Color.Blue, 0) * cubeMap.getOrDefault(Color.Red, 0) * cubeMap.getOrDefault(Color.Green,  0)
        }
        return powerSum
    }

    fun getLines(filePath: String): List<String> {
        return File(filePath).readLines()
    }

    private fun getColorAndValue(colorString: String): Pair<Color, Int> {
        val color = if (colorString.contains("blue")) {
            Color.Blue
            } else if (colorString.contains("red")) {
                Color.Red
        } else Color.Green
        val number = colorString.filter { it.isDigit() }.toInt()
        return Pair(color, number)
    }

    private fun isColorPossible(valueToCheck: Pair<Color, Int>): Boolean {
        return if (valueToCheck.first == Color.Red && valueToCheck.second > 12) {
            false
        } else if (valueToCheck.first == Color.Green && valueToCheck.second > 13) {
            false
        } else !(valueToCheck.first == Color.Blue && valueToCheck.second > 14)
    }
}

enum class Color {
    Red,
    Blue,
    Green
}