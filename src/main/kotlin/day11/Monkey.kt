package day11

class Monkey(
    private val idNumber: Int,
    private val items: MutableList<Item>,
    private val worryModifier: (worry: Long) -> Long,
    private val reliefModifier: (worry: Long) -> Long,
    private val arithmeticTest: (worry: Long) -> Boolean
) {
    private lateinit var monkeyIfTrue: Monkey
    private lateinit var monkeyIfFalse: Monkey
    private var itemsInspected = 0

    fun tossAllItems() {
        for (i in 0..items.size) { tossItem() }
    }

    fun getItemsInspected() = itemsInspected

    fun getItemLists() {
        items.forEach { println("$idNumber: ${it.lastIds}")}
    }

    private fun tossItem() {
        if (items.size > 0) {
            val item = items.first()
            items.removeAt(0)
            itemsInspected += 1
                item.value = worryModifier(item.value)
                item.value = reliefModifier(item.value)
            if (arithmeticTest(item.value)) {
                monkeyIfTrue.addItem(item)
                item.moveToMonkey(monkeyIfTrue.idNumber)
            } else {
                monkeyIfFalse.addItem(item)
                item.moveToMonkey(monkeyIfFalse.idNumber)
            }
        }
    }

    fun setMonkeyIfTrue(monkey: Monkey) {
        monkeyIfTrue = monkey
    }

    fun setMonkeyIfFalse(monkey: Monkey) {
        monkeyIfFalse = monkey
    }

    fun getId() = idNumber

    private fun addItem(item: Item) {
        items.add(item)
    }
}

class Item(var value: Long) {
    var lastIds = mutableListOf<Int>()
    fun moveToMonkey(id: Int) {
         lastIds.add(id)
    }
}