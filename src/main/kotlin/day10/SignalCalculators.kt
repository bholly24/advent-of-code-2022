package day10

object SignalCalculators {
    fun getSignalStrengthAtInterval(instructions: List<Instruction>): Int {
        var cyclesCompleted = 0
        val valueAtInterval = mutableListOf<Int>()
        var currentValue = 1
        instructions.forEach {
            for (c in it.cycles downTo 1) {
                cyclesCompleted += 1
                if (cyclesCompleted.rem(20) == 0 && (cyclesCompleted / 20).rem(2) != 0) valueAtInterval.add(currentValue * cyclesCompleted)
                if (c == 1) currentValue += it.signalChange
            }
        }
        val total = valueAtInterval.sum()
        println("Total signal strength for every 20 + 40x cycle is $total")
        return total
    }

    fun printSpritePosition(instructions: List<Instruction>) {
        var cyclesCompleted = 0
        var spritePixels = listOf(0, 1, 2)
        val imageBuffer = mutableListOf<MutableList<String>>()
        var lineBuffer = mutableListOf<String>()
        instructions.forEach {
            for (i in it.cycles downTo 1) {
                cyclesCompleted += 1
                val adjustedCyclePosition = cyclesCompleted - 1 - (40 * imageBuffer.size)
                if (spritePixels.contains(adjustedCyclePosition)) lineBuffer.add("#") else lineBuffer.add(".")
                if (cyclesCompleted != 0 && (cyclesCompleted).rem(40) == 0) {
                    imageBuffer.add(lineBuffer)
                    lineBuffer = mutableListOf()
                }
                if (i == 1) {
                    spritePixels = spritePixels.map { pos -> pos + it.signalChange }
                }
            }
        }
        imageBuffer.forEach { println(it.joinToString("")) }
    }
}

