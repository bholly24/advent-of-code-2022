package day202305

import java.lang.Exception

class AlmanacParser {
    private var seeds: List<Long> = listOf()
    private val ranges: MutableList<List<SeedMap>> = mutableListOf()

    fun getLowestLocation(lines: List<String>): Long {
        parseData(lines)

            ranges.forEach { range ->
                seeds = seeds.map { seed ->
                    val map = range.find { seedMap -> seedMap.containsSeed(seed) }
                    if (map != null) {
                        map.getMappedValue(seed)
                    } else {
                        seed
                    }
                }
            }

        println("The lowest location for starting seeds is ${seeds.min()}")
        return seeds.min()
    }

    private fun parseData(lines: List<String>) {
        var rangeInProgress: MutableList<SeedMap> = mutableListOf()
        lines.forEach {
            if (it.contains("seeds:")) {
                seeds = it.split(": ")[1].split(" ").map { s -> s.toLong() }
            } else if (it.contains("-to-")) {
                if (rangeInProgress.isNotEmpty()) {
                    ranges.add(rangeInProgress)
                    rangeInProgress = mutableListOf()
                }
            } else if (it.contains("[0-9]".toRegex())) {
                rangeInProgress.add(getRange(it.split(" ").map { s -> s.toLong()}))
            }
        }
        if (rangeInProgress.isNotEmpty()) {
            ranges.add(rangeInProgress)
            rangeInProgress = mutableListOf()
        }
//        println("Seeds are $seeds")
//        println("Ranges are $ranges")
    }

    private fun getRange(input: List<Long>): SeedMap {
        if (input.size != 3) {
            throw Exception("Disallowed input length. Should be 3")
        }
        return SeedMap(input[1], input[0], input[2])
    }
}

data class SeedMap(val start: Long, val mapper: Long, val range: Long) {
    private val max = start + range - 1
    private val diff = mapper - start

    fun containsSeed(seed: Long) = seed in start..max

    fun getMappedValue(seed: Long): Long {
        return seed + diff
    }
}
