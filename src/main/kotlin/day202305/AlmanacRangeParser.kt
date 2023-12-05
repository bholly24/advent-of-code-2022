package day202305

import java.lang.Exception

class AlmanacRangeParser {
    private var seedRanges: MutableList<LongRange> = mutableListOf()
    private var lowestSeed: Long = 0
    private val ranges: MutableList<List<SeedMap>> = mutableListOf()

    fun getLowestLocation(lines: List<String>): Long {
        parseData(lines)
        seedRanges.forEach { seedRange ->
            seedRange.forEach { seed ->
                var updatedSeed = seed
                ranges.forEach { range ->
                    val map = range.find { seedMap -> seedMap.containsSeed(updatedSeed) }
                    if (map != null) {
                        updatedSeed = map.getMappedValue(updatedSeed)
                    } else {
                        // No need to update again
                    }
                }
                if (lowestSeed == 0L) {
                    lowestSeed = updatedSeed
                } else if (updatedSeed < lowestSeed) {
                    lowestSeed = updatedSeed
                }
            }
        }

        println("The lowest location for starting seeds is ${lowestSeed}")
        return lowestSeed
    }

    private fun parseData(lines: List<String>) {
        var rangeInProgress: MutableList<SeedMap> = mutableListOf()
        lines.forEach {
            if (it.contains("seeds:")) {
                val seedAggregator = mutableListOf<Long>()
                it.split(": ")[1].split(" ")
                    .map { s -> s.toLong() }
                    .chunked(2)
                    .forEach {
                        val t = it[0]..(it[0] + it[1])
                        seedRanges.add(t)
                    }

            } else if (it.contains("-to-")) {
                if (rangeInProgress.isNotEmpty()) {
                    ranges.add(rangeInProgress)
                    rangeInProgress = mutableListOf()
                }
            } else if (it.contains("[0-9]".toRegex())) {
                rangeInProgress.add(getRange(it.split(" ").map { s -> s.toLong() }))
            }
        }
        if (rangeInProgress.isNotEmpty()) {
            ranges.add(rangeInProgress)
            rangeInProgress = mutableListOf()
        }
    }

    private fun getRange(input: List<Long>): SeedMap {
        if (input.size != 3) {
            throw Exception("Disallowed input length. Should be 3")
        }
        return SeedMap(input[1], input[0], input[2])
    }
}
