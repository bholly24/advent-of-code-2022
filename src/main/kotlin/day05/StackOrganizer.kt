package day05

import java.io.File

class StackOrganizer(filePath: String) {
    private val lines: List<String>

    init {
        lines = File(filePath).readLines()
    }

    fun organizeOneAtATime(): String = organize(false)
    fun organizeAllAtOnce(): String = organize(true)

    private fun organize(retainOrder: Boolean = false): String {
        val stack = parseStack()
        val directions = parseDirections()
        directions.forEach {
            val slice = it[0]
            val from = it[1] - 1
            val to = it[2] - 1
            val addThese = stack[from].slice(0 until slice)
            stack[from] = stack[from].slice(slice until stack[from].size).toMutableList()
            stack[to].addAll(0, if (retainOrder) addThese else addThese.reversed())
        }
        return solve(stack)
    }

    private fun solve(stack: MutableList<MutableList<Char>>): String {
        val solution = stack.map { it.first() }.joinToString("")
        println("The solution is $solution")
        return solution
    }

    private fun parseDirections(): List<List<Int>> {
        return lines
            .filter { it.contains("move") }
            .map {
                it
                    .split("from", "to")
                    .map { it.filter { it.isDigit() }.toInt() }
            }
    }

    private fun parseStack(): MutableList<MutableList<Char>> {
        return toStack(
            lines
                .filter { it.contains("[") }
                .map {
                    it
                        .replace("[", "")
                        .replace("]", "")
                        .replace("  ", " ")
                        .toMutableList()
                }
        )
    }

    private fun toStack(l: List<List<Char>>): MutableList<MutableList<Char>> {
        val stack = mutableListOf<MutableList<Char>>()
        l.forEach { line ->
            var index = 0
            var extraSpace = true
            line.forEach {
                if (!extraSpace) {
                    extraSpace = true
                } else {
                    val listAtIndex = stack.getOrNull(index)
                    if (it.isLetter()) {
                        if (listAtIndex == null) {
                            stack.add(mutableListOf(it))
                        } else {
                            listAtIndex.add(it)
                        }
                    } else {
                        if (listAtIndex == null) {
                            stack.add(mutableListOf())
                        }
                    }
                    extraSpace = false
                    index += 1
                }
            }
        }
        return stack
    }
}
