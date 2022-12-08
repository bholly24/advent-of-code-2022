package day08

import java.io.File

class TreeCounter(filePath: String) {
    private val trees: List<List<Int>>
    private val xMax: Int
    private val yMax: Int

    init {
        trees = getTreeRows(filePath)
        xMax = trees.first().size - 1
        yMax = trees.size - 1
    }

    fun getMaxScenicScore(): Int {
        var maxScenicScore = 0
        for (y in 0..yMax) {
            for (x in 0..xMax) {
                val currentValue = trees[y][x]
                if (!isEdge(x, y)) {
                    val score = getTreesFromEdges(x, y)
                        .map { l ->
                            var isVisible = true
                            var totalVisible = 0
                            var index = 0
                            val max = l.size - 1
                            while (isVisible && index <= max) {
                                isVisible = currentValue > l[index]
                                totalVisible += 1
                                index += 1
                            }
                            totalVisible
                        }
                        .reduce { acc, i -> acc * i }

                    if (score > maxScenicScore) {
                        maxScenicScore = score
                    }
                }
            }
        }
        println("Maximum scenic score is $maxScenicScore")
        return maxScenicScore
    }

    fun getTotalVisibleTrees(): Int {
        var totalVisible = 0
        for (y in 0..yMax) {
            for (x in 0..xMax) {
                val currentValue = trees[y][x]
                if (isEdge(x, y)) {
                    totalVisible += 1
                } else {
                    if (getTreesFromEdges(x, y).any { l -> l.filter { currentValue <= it }.isEmpty() }) {
                        totalVisible += 1
                    }
                }
            }
        }
        println("Total visible trees are $totalVisible")
        return totalVisible
    }

    private fun getTreesFromEdges(x: Int, y: Int): List<List<Int>> {
        val treesFromEdges = mutableListOf<List<Int>>()
        treesFromEdges.add(trees[y].slice(0 until x).reversed())
        treesFromEdges.add(trees[y].slice((x + 1)..xMax))
        treesFromEdges.add(trees.slice(0 until y).map { it[x] }.reversed())
        treesFromEdges.add(trees.slice((y + 1)..yMax).map { it[x] })
        return treesFromEdges
    }

    private fun isEdge(x: Int, y: Int): Boolean {
        return x == 0 || x == xMax || y == 0 || y == yMax
    }

    private fun getTreeRows(filePath: String): List<List<Int>> {
        return File(filePath).readLines().map { it.toCharArray().map { it.digitToInt() } }
    }
}