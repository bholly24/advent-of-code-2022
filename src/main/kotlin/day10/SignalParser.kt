package day10

import java.io.File

class SignalParser(filePath: String) {
    private val instructions = getInstructionSet(filePath)

    fun getInstructions(): List<Instruction> {
        return instructions
    }

    private fun getInstructionSet(filePath: String): List<Instruction> {
       return File(filePath).readLines().map { parseInstruction(it) }
    }

    private fun parseInstruction(s: String): Instruction {
        return if (s == "noop") {
            Instruction(1, 0)
        } else {
            Instruction(2, s.split(" ").last().toInt())
        }
    }
}

data class Instruction(val cycles: Int, val signalChange: Int)