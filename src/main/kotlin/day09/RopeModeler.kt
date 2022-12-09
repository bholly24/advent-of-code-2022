package day09

import java.io.File
import kotlin.math.abs

class RopeModeler(filePath: String) {
    private val ropeMovements: List<List<Coord>>
    private var tailVisitedCoords: MutableSet<List<Int>> = mutableSetOf(Coord(0, 0).toList())

    init {
        ropeMovements = readInRopeMovements(filePath)
    }

    fun getTotalListOfPositionsForLength(length: Int): Int {
        tailVisitedCoords = mutableSetOf(Coord(0, 0).toList())
        val positionsToModel = MutableList(length) {Coord(0, 0)}
        ropeMovements.forEach { moveHead(positionsToModel, it) }
        println("Total unique positions seen by the tail for a rope of length $length is ${tailVisitedCoords.size}")
        return tailVisitedCoords.size
    }

    private fun moveHead(positions: List<Coord>, move: List<Coord>) {
        move.forEach {
            positions.forEachIndexed { index, coord ->
                if (index == 0) {
                    coord.update(it)
                } else {
                    resolvePositionFromPrevious(coord, positions[index - 1])
                }
            }
            tailVisitedCoords.add(positions.last().toList())
        }
    }

    private fun resolvePositionFromPrevious(position: Coord, previous: Coord) {
        val tailXDist = position.getXDistFrom(previous)
        val tailYDist = position.getYDistFrom(previous)

        var xMove = getInitialMoveFromDist(tailXDist)
        var yMove = getInitialMoveFromDist(tailYDist)

        if (xMove != 0 && abs(tailYDist) == 1) {
            yMove = tailYDist
        } else if (yMove != 0 && abs(tailXDist) == 1) {
            xMove = tailXDist
        }

        position.update(Coord(xMove, yMove))
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
