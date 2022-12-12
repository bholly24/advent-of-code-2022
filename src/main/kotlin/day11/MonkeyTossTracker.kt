package day11

import java.io.File
import java.math.BigInteger

class MonkeyTossTracker(filePath: String) {
    private val lines = File(filePath).readLines()

    fun findMostMonkeyBusinessOverTenThousandRounds(): Int {
        val monkeys = getInitialTossState(false)
        for (i in 0 until 10000) { monkeys.forEach { it.tossAllItems() } }
        val topInspections = monkeys.map { it.getItemsInspected() }.sortedDescending()
        val total = topInspections.first() * topInspections[1]
        println("The product of the top two item totals is $total")
        return total
    }

    fun findMostMonkeyBusinessOverTwentyRounds(): Int {
        val monkeys = getInitialTossState(true)
        for (i in 0 until 20) { monkeys.forEach { it.tossAllItems() } }
        val topInspections = monkeys.map { it.getItemsInspected() }.sortedDescending()
        val total = topInspections.first() * topInspections[1]
        println("The product of the top two item totals is $total")
        return total
    }

    private fun getInitialTossState(shouldReduceWorry: Boolean): List<Monkey> {
        val monkeys = mutableListOf<Monkey>()
        val trueFalseMap = mutableMapOf<Int, List<Int>>()
        lines.chunked(7).forEach {
            val monkey = parseMonkeyDetails(it.slice(0..3), shouldReduceWorry)
            monkeys.add(monkey)
            addToTrueFalseMapForMonkey(trueFalseMap, monkey, it.slice(4..5))
        }
        trueFalseMap.forEach { entry ->
            val m = monkeys.find { it.getId() == entry.key}
            val mIfTrue = monkeys.find { it.getId() == entry.value.first() }
            val mIfFalse = monkeys.find { it.getId() == entry.value.last() }
            m!!.addMonkeyIfTrue(mIfTrue!!)
            m.addMonkeyIfFalse(mIfFalse!!)
        }
        return monkeys.sortedBy { it.getId() }
    }

    private fun parseMonkeyDetails(lines: List<String>, shouldReduceWorry: Boolean): Monkey {
        val id = lines.first().filter { it.isDigit() }.toInt()
        val startingItems = lines[1].split(",").map { it.filter { it.isDigit() }.toInt() }
        val worryOperation = parseWorryIncreaseOperation(lines[2])
        // All tests appear to be about being divisible
        val test = lines[3].filter { it.isDigit() }.toInt()
        val reducer: (worry: Int) -> Int = if (shouldReduceWorry) {worry -> worry / 3 } else {worry -> worry}
        return Monkey(id, startingItems.toMutableList(), worryOperation, reducer, { worry -> worry.rem(test) == 0 })
    }

    private fun parseWorryIncreaseOperation(line: String): (old: Int) -> Int {
        val equationDigit = line.filter { it.isDigit() }.toIntOrNull()
        return if (equationDigit != null) {
            when {
                line.contains("+") -> { old: Int -> old.plus(equationDigit) }
                line.contains("*") -> { old: Int -> old.times(equationDigit) }
                else -> { old: Int -> old.plus(equationDigit) }
            }
        } else {
            when {
                line.contains("+") -> { old: Int -> old.plus(old) }
                line.contains("*") -> { old: Int -> old.times(old) }
                else -> { old: Int -> old.times(old) }
            }
        }
    }

    private fun addToTrueFalseMapForMonkey(map: MutableMap<Int, List<Int>>, monkey: Monkey, lines: List<String>) {
        val ifTrue = lines.first().filter { it.isDigit() }.toInt()
        val ifFalse = lines.last().filter { it.isDigit() }.toInt()
        map[monkey.getId()] = listOf(ifTrue, ifFalse)
    }
}
