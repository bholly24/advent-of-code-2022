package day202309

class SequenceEvaluator {
    fun getNextValues(input: List<String>): Long {
        val sequences = parseSequences(input)
        val total = sequences.fold(0L) { acc, seq -> acc + getNextValue(seq) }
        println("The total sum of sequences is $total")
        return total
    }

    fun getNextValuesWithRecursion(input: List<String>): Long {
        val sequences = parseSequences(input)
        val total = sequences.fold(0L) { acc, seq -> acc + seq.last() + getNextValueRecursion(seq) }
        println("The total sum of the next term in the sequences is $total")
        return total
    }

    fun getPriorValuesWithRecursion(input: List<String>): Long {
        val sequences = parseSequences(input)
        val total = sequences.fold(0L) { acc, seq -> acc + seq.first() - getPriorValueRecursion(seq) }
        println("The total sum of the prior term in the sequences is $total")
        return total
    }

    private fun getPriorValueRecursion(sequence: List<Int>): Long {
        val s = mutableListOf<Int>()
        for (i in 0 until sequence.size - 1) {
                s.add(sequence[i + 1] - sequence[i])
        }
        return if (s.any { it != 0 }) s.first() - getPriorValueRecursion(s) else 0
    }

    private fun getNextValueRecursion(sequence: List<Int>): Long {
        val s = mutableListOf<Int>()
        for (i in 0 until sequence.size - 1) {
                s.add(sequence[i + 1] - sequence[i])
        }
        return if (s.any { it != 0 }) s.last() + getNextValueRecursion(s) else 0
    }

    // Not sure why this is slightly off...
    private fun getNextValue(sequence: List<Int>): Long {
        var difference = 1
        val sequenceComponents = mutableListOf(sequence.toMutableList())
        var nextSequencePointer = 0
        while (difference != 0) {
            val nextStep = mutableListOf<Int>()
            for (i in 0..sequenceComponents[nextSequencePointer].size) {
                val next = i + 1
                if (next < sequenceComponents[nextSequencePointer].size) {
                    nextStep.add(sequenceComponents[nextSequencePointer][next] - sequenceComponents[nextSequencePointer][i])
                }
            }
            sequenceComponents.add(nextStep)
            difference = nextStep.max()
            nextSequencePointer += 1
        }

        sequenceComponents.reverse()

        nextSequencePointer = 0
        for (l in sequenceComponents) {
            if (nextSequencePointer  > 0) {
                val nextValue = l.last()
                val nextToLast = sequenceComponents[nextSequencePointer - 1].last()
                l.add(nextValue + nextToLast)
            }
            nextSequencePointer += 1
        }
        return sequenceComponents.last().last().toLong()
    }

    private fun parseSequences(input: List<String>): List<List<Int>> {
        return input.map { it.split(" ").map { it.toInt() } }
    }
}