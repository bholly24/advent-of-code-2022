package day202303

class SchematicParser(inputData: List<String>) {
    private val data: List<List<Char>> = inputData.map { it.toList() }
    private val xMax: Int = data.first().size - 1
    private val yMax: Int = data.size - 1
    private val numbers: List<List<Coord>> = findNumbers()
    private val gearNumbers: List<GearNumber> = findGearNumbers()

    fun findRelevantNumbers(): Int {
        var sum = 0
        numbers.forEach { coordList ->
            val hasAdjacentSymbol =
                coordList.any { numberCoord -> getValidNeighbors(numberCoord).any { isSymbol(data[it.y][it.x]) } }
            if (hasAdjacentSymbol) {
                sum += getNumberFromCoordList(coordList)
            }
        }

        println("The sum of numbers from the engine schematic is $sum")

        return sum
    }

    fun testThirty(): Int {
        val sum = gearNumbers.filter { it.symbols.isNotEmpty() }.sumOf { it.value }
        println("The sum of numbers from the engine schematic is $sum")
        return sum
    }

    fun testGearSum(): Int {
        val starMap = mutableMapOf<Coord, Pair<Int, Int>>()
        val gearStars = mutableListOf<GearSymbol>()
        gearNumbers.forEach {
            val stars = it.symbols.filter { s -> s.symbol == '*' }
            stars.forEach { star ->
               val value = starMap.getOrDefault(star.coordinate, Pair(0, 0))
                val multiplication = if (value.second > 0) value.second * it.value else it.value
                starMap[star.coordinate] = Pair(value.first + 1, multiplication)
//                if (!(gearStars.map { gs -> gs.coordinate }.any { c -> star.coordinate.isEqualTo(c) })) {
//                    gearStars.add(star)
//                }
            }
        }
//
//        val nWithS = gearNumbers
//            .filter { it.symbols.any { s -> s.symbol == '*' } }
//
//        nWithS.forEach {
//            it.symbols.filter { s -> s.symbol == '*' }
//
        var sum = 0
        starMap.forEach {
            if (it.value.first == 2) {
                sum += it.value.second
            }
        }
        println("Gear sum for ratios is $sum")
        return sum
    }

    fun findGears(): Int {
        val gears = mutableListOf<Coord>()
        for (y in 0..yMax) {
            for (x in 0..xMax) {
                if (data[y][x] == '*') {
                    gears.add(Coord(x, y))
                }
            }
        }

        var sumRatios = 0

        gears.forEach { gearCoord ->

            val neighbors = getValidNeighbors(gearCoord)
            val surroundingNumbers = mutableSetOf<Int>()
            neighbors.forEach { neighCoord ->
                numbers.forEach { n ->
                    if (n.any { numberCoord -> numberCoord.isEqualTo(neighCoord) }) {
                        surroundingNumbers.add(getNumberFromCoordList(n))
                    }
                }
            }
            val s = surroundingNumbers.toList()
            if (s.size == 2) {
                sumRatios += s[0] * s[1]
            }
        }

        println("Sum of gear ratios is $sumRatios")

        return sumRatios
    }

    private fun getNumberFromCoordList(n: List<Coord>) = n.map { data[it.y][it.x] }.joinToString("").toInt()

    private fun findGearNumbers(): List<GearNumber> {
        val numberList = mutableListOf<List<Coord>>()
        val nl = mutableListOf<GearNumber>()
        var currentNumberCoords = mutableListOf<Coord>()
        var currentNumberSymbols = mutableListOf<GearSymbol>()

        var newRow = false
        for (y in 0..yMax) {
            newRow = true
            for (x in 0..xMax) {
                val value = data[y][x]

                // reset on new row
                if (newRow && currentNumberCoords.isNotEmpty()) {
                    numberList.add(currentNumberCoords)
                    nl.add(
                        GearNumber(
                            currentNumberCoords,
                            currentNumberSymbols,
                            getNumberFromCoordList(currentNumberCoords)
                        )
                    )
                    currentNumberCoords = mutableListOf()
                    currentNumberSymbols = mutableListOf()
                }

                if (value.isDigit()) {
                    val numberCoord = Coord(x, y)
                    currentNumberCoords.add(numberCoord)
                    currentNumberSymbols.addAll(
                        getValidNeighbors(numberCoord)
                            .filter { isSymbol(data[it.y][it.x]) }
                            .map { GearSymbol(Coord(it.x, it.y), data[it.y][it.x]) }
                    )
                } else if (currentNumberCoords.isNotEmpty()) {
                    numberList.add(currentNumberCoords)
                    nl.add(
                        GearNumber(
                            currentNumberCoords,
                            currentNumberSymbols,
                            getNumberFromCoordList(currentNumberCoords)
                        )
                    )
                    currentNumberCoords = mutableListOf()
                    currentNumberSymbols = mutableListOf()
                } else {
                    currentNumberCoords = mutableListOf()
                    currentNumberSymbols = mutableListOf()
                }
                newRow = false
            }
        }
        return nl
    }

    private fun findNumbers(): List<List<Coord>> {
        val numberList = mutableListOf<List<Coord>>()
        val nl = mutableListOf<GearNumber>()
        var currentNumberCoords = mutableListOf<Coord>()
        var currentNumberSymbols = mutableListOf<GearSymbol>()

        var newRow = false
        for (y in 0..yMax) {
            newRow = true
            for (x in 0..xMax) {
                val value = data[y][x]

                // reset on new row
                if (newRow && currentNumberCoords.isNotEmpty()) {
                    numberList.add(currentNumberCoords)
                    nl.add(
                        GearNumber(
                            currentNumberCoords,
                            currentNumberSymbols,
                            getNumberFromCoordList(currentNumberCoords)
                        )
                    )
                    currentNumberCoords = mutableListOf()
                    currentNumberSymbols = mutableListOf()
                }

                if (value.isDigit()) {
                    val numberCoord = Coord(x, y)
                    currentNumberCoords.add(numberCoord)
                    currentNumberSymbols.addAll(
                        getValidNeighbors(numberCoord)
                            .filter { isSymbol(data[y][x]) }
                            .map { GearSymbol(Coord(it.x, it.y), data[y][x]) }
                    )
                } else if (currentNumberCoords.isNotEmpty()) {
                    numberList.add(currentNumberCoords)
                    currentNumberCoords = mutableListOf()
                    currentNumberSymbols = mutableListOf()
                } else {
                    currentNumberCoords = mutableListOf()
                    currentNumberSymbols = mutableListOf()
                }
                newRow = false
            }
        }
        return numberList
    }

    private fun getValidNeighbors(coord: Coord): List<Coord> {
        return listOf(
            Coord(coord.x - 1, coord.y - 1),
            Coord(coord.x - 1, coord.y),
            Coord(coord.x - 1, coord.y + 1),
            Coord(coord.x, coord.y - 1),
            Coord(coord.x, coord.y + 1),
            Coord(coord.x + 1, coord.y),
            Coord(coord.x + 1, coord.y - 1),
            Coord(coord.x + 1, coord.y + 1)
        )
            .filter { !isInvalid(it.x, it.y) }
    }

    private fun isSymbol(c: Char): Boolean = !c.isDigit() && c != '.'

    private fun isInvalid(x: Int, y: Int): Boolean {
        return x < 0 || x > xMax || y < 0 || y > yMax
    }

    fun findRelevantNumbersOld(): Int {
        var number: String = ""
        var sum = 0
        var nextToSymbol = false
        var newRow = false
        for (y in 0..yMax) {
            newRow = true
            for (x in 0..xMax) {
                val value = data[y][x]

                // reset row
                if (newRow && number != "") {
                    if (nextToSymbol) {
                        sum += number.toInt()
                    }
                    number = ""
                    nextToSymbol = false
                }

                if (value.isDigit()) {
                    number += value
                    if (!nextToSymbol) {
                        nextToSymbol = getValidNeighbors(Coord(x, y))
                            .any { isSymbol(data[it.y][it.x]) }
                    }
                } else if (number != "" && nextToSymbol) {
                    sum += number.toInt()
                    number = ""
                    nextToSymbol = false
                } else {
                    number = ""
                    nextToSymbol = false
                }
                newRow = false
            }
        }

        println("The sum of numbers from the engine schematic is $sum")
        return sum
    }
}

data class Coord(val x: Int, val y: Int) {
    fun isEqualTo(coord: Coord) = x == coord.x && y == coord.y
}

data class GearSymbol(val coordinate: Coord, val symbol: Char)

data class GearNumber(val coordinates: List<Coord>, val symbols: List<GearSymbol>, val value: Int)
