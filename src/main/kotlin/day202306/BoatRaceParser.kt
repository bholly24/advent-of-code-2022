package day202306

class BoatRaceParser {

    fun calculateMaxDistance(input: List<String>): Int {
        val data = parseData(input)
        val totalWins = data.map {
            val initialTestPoint = it.second - 1
            (1..initialTestPoint)
                .toList()
                .count { speed ->
                    val speed = calculateDistance(speed, it.second - speed)
                    val s = speed > it.first
                    s
                }
        }.reduce { acc, i -> acc * i }

        println("Win multiplier is $totalWins")
        return totalWins
    }

    fun calculateMaxDistanceAltogether(input: List<String>): Int {
        val data = parseDataAltogether(input)
        val initialTestPoint = data.second - 1
        val totalWins = (1L..initialTestPoint)
            .toList()
            .count { speed ->
                val speed = speed * (data.second - speed)
                val s = speed > data.first
                s
            }
        println("Win multiplier altogether is $totalWins")
        return totalWins
    }

    private fun parseData(input: List<String>): List<Pair<Int, Int>> {
        val times = parseInts(input[1])
        val records = parseInts(input[0])
        return times.mapIndexed { i, t -> Pair(t, records[i]) }
    }

    private fun parseDataAltogether(input: List<String>): Pair<Long, Long> {
        val times = input[1].filter { it.isDigit() }.toLong()
        val records = input[0].filter { it.isDigit() }.toLong()
        return Pair(times, records)
    }

    private fun parseInts(input: String): List<Int> {
        return input.split(": ")[1].trim()
            .split(" ")
            .filter { s -> s.trim() != "" }
            .map { it.trim().toInt() }
    }

    private fun calculateDistance(speed: Int, timeForSpeed: Int): Int {
        return speed * timeForSpeed
    }
}
