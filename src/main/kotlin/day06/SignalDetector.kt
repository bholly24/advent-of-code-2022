package day06

import java.io.File

class SignalDetector(filePath: String) {
    private val signal: String

    init {
        signal = getSignal(filePath)
    }

    fun detectUniqueSequenceOfFour(): Int {
        return detectUniqueSequence(4)
    }

    fun detectUniqueSequenceOfFourteen(): Int {
        return detectUniqueSequence(14)
    }

    private fun detectUniqueSequence(uniqueLength: Int): Int {
        var digitsChecked = 0
        val runningSequence = mutableListOf<Char>()
        signal.forEach {
            if (runningSequence.size < uniqueLength) {
                runningSequence.add(it)
            } else {
                runningSequence.removeAt(0)
                runningSequence.add(it)
            }
            digitsChecked += 1
            if (runningSequence.distinct().size == uniqueLength) {
                println("The first unique signal of $uniqueLength is $digitsChecked")
                return digitsChecked
            }
        }
        return digitsChecked
    }

    private fun getSignal(filePath: String): String {
        return File(filePath).readLines().joinToString("")
    }
}