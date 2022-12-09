package day09

import java.io.File
import kotlin.math.abs

class RopeModeler(filePath: String) {
    private val ropeMovements: List<List<Coord>>
    private var tailVisitedCoords: MutableSet<List<Int>> = mutableSetOf(Coord(0, 0).toList())

    init {
        ropeMovements = readInRopeMovements(filePath)
    }

    fun getUniquePositionsVisitedForRope(length: Int): Int {
        tailVisitedCoords = mutableSetOf(Coord(0, 0).toList())
        val positionsToModel = MutableList(length) {Coord(0, 0)}
        ropeMovements.forEach { updateRopePosition(positionsToModel, it) }
        println("Total unique positions seen by the tail for a rope of length $length is ${tailVisitedCoords.size}")
        return tailVisitedCoords.size
    }

    private fun updateRopePosition(position: List<Coord>, move: List<Coord>) {
        move.forEach {
            position.forEachIndexed { index, coord ->
                if (index == 0) {
                    coord.update(it)
                } else {
                    resolvePositionFromPrevious(coord, position[index - 1])
                }
            }
            tailVisitedCoords.add(position.last().toList())
        }
    }

    private fun resolvePositionFromPrevious(position: Coord, previous: Coord) {
        val xDist = position.getXDistFrom(previous)
        val yDist = position.getYDistFrom(previous)
        var xMove = getInitialMoveFromDist(xDist)
        var yMove = getInitialMoveFromDist(yDist)
        if (ropeIsStretchedDiagonallyForDimension(xDist, yDist)) {
            yMove = yDist
        } else if (ropeIsStretchedDiagonallyForDimension(yDist, xDist)) {
            xMove = xDist
        }

        position.update(Coord(xMove, yMove))
    }

    private fun ropeIsStretchedDiagonallyForDimension(mainDimension: Int, otherDimension: Int): Boolean {
        return mainDimension !in -1..1 && abs(otherDimension) == 1
    }

    private fun getInitialMoveFromDist(dist: Int): Int {
       return when {
            dist > 1 -> dist - 1
            dist < - 1 -> dist + 1
            else -> 0
        }
    }

    private fun readInRopeMovements(filePath: String): List<List<Coord>> {
        return File(filePath).readLines().map { parseMovement(it) }
    }


    private fun parseMovement(order: String): List<Coord> {
        val s = order.split(" ")
        val direction: Coord = when (s.first()) {
            "R" -> Coord(1, 0)
            "L" -> Coord(-1, 0)
            "U" -> Coord(0, 1)
            else ->  Coord(0, -1)
        }
        val times = s.last().toInt()
        return List (times) { direction }
    }
}
