class SequenceEvaluator {
    fun getNextValues(input: List<String>): Int {
        val sequences = input.map { it.split(" ").map { it.toInt() } }
        return sequences.sumOf { getNextValue(it) }
    }

    private fun getNextValue(sequence: List<Int>): Int {
        var difference = 1
        val sequenceComponents = mutableListOf<MutableList<Int>>(sequence.toMutableList())
        var nextSequencePointer = 0
        while (difference != 0) {
            var nextStep = mutableListOf<Int>()
            for (i in sequenceComponents[nextSequencePointer]) {
                var next = i + 1
                if (next < sequence.size) {
                    nextStep.add(sequenceComponents[nextSequencePointer][next] - i)
                }
            }
            sequenceComponents.add(nextStep)
            difference = nextStep.last()
            nextSequencePointer += 1
        }

        sequenceComponents.reverse()

        nextSequencePointer = 0
        for (l in sequenceComponents) {
            if (nextSequencePointer  > 1) {
                var nextValue = l.last()
                for (i in 1..nextSequencePointer) {
                    nextValue += sequenceComponents[i].last()
                }
                l.add(nextValue)
            }
            nextSequencePointer += 1
        }
        return sequenceComponents.last().last()
    }
}