package day09

data class Coord(var x: Int, var y: Int) {
    fun update(coordinates: Coord) {
        x += coordinates.x
        y += coordinates.y
    }

    fun getXDistFrom(coordinates: Coord): Int {
        return coordinates.x - x
    }

    fun getYDistFrom(coordinates: Coord): Int {
        return coordinates.y - y
    }

    fun toList(): List<Int> {
        return listOf(x, y)
    }
}