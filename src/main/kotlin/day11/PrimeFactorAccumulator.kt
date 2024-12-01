package day11

class PrimeFactorAccumulator {
    private var currentMax = 0
    private var factorMap = mutableMapOf<Int,Int>()

    fun updateToIncludeValue(value: Int) {
        if (!canBeExpressedFromCurrentSet(value) && value > 1) {
            val valueFactorMap = getPrimeFactors(value.toLong()).groupingBy { it }.eachCount()
            valueFactorMap.entries.forEach {
                if (factorMap.getOrDefault(it.key, 0) < it.value) {
                    factorMap.set(it.key, it.value)
                }
            }
            currentMax = mapToList().reduce { acc, i -> acc * i }
        }
    }

    fun canBeExpressedFromCurrentSet(value: Int): Boolean {
        return currentMax != 0 && currentMax % value == 0
    }

    private fun mapToList(): List<Int> {
        val list = mutableListOf<Int>()
        factorMap.entries.forEach {
            repeat(it.value) { _ -> list.add(it.key) }
        }
        list.sort()
        return list
    }
}