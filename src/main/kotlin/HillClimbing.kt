import java.io.File

class HillClimbing(filePath: String) {
    private val terrain: List<List<Option>> = parseTerrain(filePath)
    private val xMax = terrain.first().size - 1
    private val yMax = terrain.size - 1
    private val pathAttempts = mutableListOf<Option>()

    fun findShortestPath() {
        var shortestPath = 100000000
    }

    fun follow(option: Option) {
        val nextOptions = getOptions(option)
        if (getOptions(option).isEmpty() && !option.isEnd) {
            // Finished
        } else {
            nextOptions.forEach {
                follow(it)
            }
        }
    }

    fun getOptions(option: Option): List<Option> {
        val options = mutableListOf<Option>()
        mutableListOf(option.y + 1, option.y - 1)
            .filter {
                if (it in 0..yMax) {
                    val o = terrain[it][option.x]
                    !o.hasVisited() && o.option.code in option.option.code - 1..option.option.code + 1
                } else {
                    false
                }
            }
            .forEach { options.add(terrain[it][option.x]) }

        mutableListOf(option.x + 1, option.y - 1)
            .filter {
                if (it in 0..xMax) {
                    val o = terrain[option.y][it]
                    !o.hasVisited() && o.option.code in option.option.code - 1..option.option.code + 1
                } else {
                    false
                }
            }
            .forEach { options.add(terrain[option.y][it]) }
        return options
    }

    private fun parseTerrain(filePath: String): List<List<Option>> {
        return File(filePath).readLines().mapIndexed { x, string -> string.toList().mapIndexed { y, value -> Option(value, x, y) } }
    }
}

data class Option(val option: Char, val x: Int, val y: Int) {
    val isStart = option == 'S'
    val isEnd = option == 'E'
    private var visited = false
    fun visit() {
        visited = true
    }

    fun hasVisited() = visited
}