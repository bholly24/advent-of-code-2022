package day11

import java.math.BigInteger

class Monkey(
    private val idNumber: Int,
    private val items: MutableList<Int>,
    private val worryModifier: (worry: Int) -> Int,
    private val reliefModifier: (worry: Int) -> Int,
    private val arithmeticTest: (worry: Int) -> Boolean,
) {
    private lateinit var monkeyWhenTrue: Monkey
    private lateinit var monkeyWhenFalse: Monkey
    private var itemsInspected = 0

    fun tossAllItems() {
        for (i in 0..items.size) { tossItem() }
    }

    fun getItemsInspected() = itemsInspected

    private fun tossItem() {
        if (items.size > 0) {
            var item = items.first()
            items.removeAt(0)
            itemsInspected += 1
            item = worryModifier(item)
            item = reliefModifier(item)
            if (arithmeticTest(item)) monkeyWhenTrue.addItem(item) else monkeyWhenFalse.addItem(item)
        }
    }

    fun addMonkeyIfTrue(monkey: Monkey) {
        monkeyWhenTrue = monkey
    }

    fun addMonkeyIfFalse(monkey: Monkey) {
        monkeyWhenFalse = monkey
    }

    fun getId() = idNumber

    fun addItem(item: Int) {
        items.add(item)
    }
}
