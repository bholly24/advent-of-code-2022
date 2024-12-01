package day202305

import java.lang.Exception

class AlmanacRangeParser {
    private var seedRanges: MutableList<LongRange> = mutableListOf()
    private var lowestSeed: Long = Long.MAX_VALUE
    private val ranges: MutableList<List<SeedMap>> = mutableListOf()

    fun getLowestLocation(lines: List<String>): Long {
        parseData(lines)
        var seedsConsidered = 0
        seedRanges.forEach { seedRange ->
            println("Another seed processed")
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

                seedsConsidered += 1
                if (updatedSeed < lowestSeed) {
                    println("Is updated $updatedSeed actually less than $lowestSeed")
                    lowestSeed = updatedSeed
                }
            }
        }

        println("Seeds considered $seedsConsidered")
        println("The lowest location for starting seeds is $lowestSeed")
        return lowestSeed
    }

//    private suspend fun processSeedRange(seedRange: LongRange): Long {
//        var minSeed = Long.M
//        seedRange.forEach { seed ->
//            var updatedSeed = seed
//            ranges.forEach { range ->
//                val map = range.find { seedMap -> seedMap.containsSeed(updatedSeed) }
//                if (map != null) {
//                    updatedSeed = map.getMappedValue(updatedSeed)
//                } else {
//                    // No need to update again
//                }
//            }
//            minSeed = minSeed ?: updatedSeed
//            if (updatedSeed < minSeed!!) {
//                minSeed = updatedSeed
//            }
//        }
//        return minSeed ?: Long.MAX_VALUE
//    }

    private fun parseData(lines: List<String>) {
        var rangeInProgress: MutableList<SeedMap> = mutableListOf()
        lines.forEach {
            if (it.contains("seeds:")) {
                it.split(": ")[1].split(" ")
                    .map { s -> s.toLong() }
                    .chunked(2)
                    .forEach {
                        val t = it[0]..(it[0] + it[1] - 1)
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
