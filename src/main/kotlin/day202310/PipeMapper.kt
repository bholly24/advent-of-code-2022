package day202310

class PipeMapper {
    private val pipeTiles = listOf('|', '-', 'L', 'J', 'F', '7', '.', 'S')
    private val verticalBottom = listOf('|', 'F', '7')
    private val verticalTop = listOf('|', 'J', 'L')
    private val horizontalLeft = listOf('-', 'J', '7')
    private val horizontalRight = listOf('-', 'F', 'L')
    private val dirMap = mapOf(
        PipeDirection.Top to verticalTop,
        PipeDirection.Bottom to verticalBottom,
        PipeDirection.Left to horizontalRight,
        PipeDirection.Right to horizontalLeft
    )
    private val dirCoordMap = mapOf<PipeDirection, Coord>(
        PipeDirection.Top to Coord(0, 1),
        PipeDirection.Bottom to Coord(0, -1),
        PipeDirection.Left to Coord(-1, 0),
        PipeDirection.Right to Coord(1, 0)
    )

    fun getTotalPipeLength(input: List<String>): Int {
        var start: Pair<Int, Int>
        val pipeMap = input.mapIndexed { i, it ->
            it.toList().mapIndexed { index, c -> {
                val p = pipeBuilder(c)
                if (p.char == 'S') {
                    start = Pair(i, index)
                }
                p
            } }
        }

        val xMaxIndex = pipeMap[0].size - 1
        val yMaxIndexed = pipeMap.size - 1

        return 2
    }

    private fun findPath(l: List<List<Pipe>>, currentY: Int, currentX: Int, xmax: Int, ymax: Int): List<Pipe> {
        val p = l[currentY][currentX]
    val coord = getNewCoord(currentX, currentY, dirCoordMap[p.connectionTwo.first])
        if (coord != null && coord.x <= xmax && coord.y <= ymax) {

        }

    }

    private fun getNewCoord(currentX: Int, currentY: Int, updateCoord: Coord): Int {

    }

    private fun pipeBuilder(char: Char): Pipe {
        return when(char) {
            '|' -> VertPipe()
            '-' -> HorizontalPipe()
            'L' -> LPipe()
            'J' -> JPipe()
            '7' -> SevenPipe()
            'F' -> FPipe()
            'S' -> Start()
            else -> Ground()
        }
    }
}

class VertPipe : Pipe {
    override val char = '|'
    override var connectionOne: Pair<PipeDirection, Pipe?> = Pair(PipeDirection.Top, null)
    override var connectionTwo: Pair<PipeDirection, Pipe?> = Pair(PipeDirection.Bottom, null)
}

class Start: Pipe {
    override val char = 'S'
    override var connectionOne: Pair<PipeDirection, Pipe?> = Pair(PipeDirection.Unknown, null)
    override var connectionTwo: Pair<PipeDirection, Pipe?> = Pair(PipeDirection.Unknown, null)
}

class Ground: Pipe {
    override val char = '.'
    override var connectionOne: Pair<PipeDirection, Pipe?> = Pair(PipeDirection.None, null)
    override var connectionTwo: Pair<PipeDirection, Pipe?> = Pair(PipeDirection.None, null)
}

class HorizontalPipe : Pipe {
    override val char = '-'
    override var connectionOne: Pair<PipeDirection, Pipe?> = Pair(PipeDirection.Left, null)
    override var connectionTwo: Pair<PipeDirection, Pipe?> = Pair(PipeDirection.Right, null)
}

class JPipe : Pipe {
    override val char = 'J'
    override var connectionOne: Pair<PipeDirection, Pipe?> = Pair(PipeDirection.Left, null)
    override var connectionTwo: Pair<PipeDirection, Pipe?> = Pair(PipeDirection.Top, null)
}

class LPipe : Pipe {
    override val char = 'L'
    override var connectionOne: Pair<PipeDirection, Pipe?> = Pair(PipeDirection.Top, null)
    override var connectionTwo: Pair<PipeDirection, Pipe?> = Pair(PipeDirection.Right, null)
}

class SevenPipe : Pipe {
    override val char = '7'
    override var connectionOne: Pair<PipeDirection, Pipe?> = Pair(PipeDirection.Left, null)
    override var connectionTwo: Pair<PipeDirection, Pipe?> = Pair(PipeDirection.Bottom, null)
}

class FPipe : Pipe {
    override val char = 'F'
    override var connectionOne: Pair<PipeDirection, Pipe?> = Pair(PipeDirection.Right, null)
    override var connectionTwo: Pair<PipeDirection, Pipe?> = Pair(PipeDirection.Bottom, null)
}

//data class VertPipe(val char: Char) : Pipe {
//    private val topConnectors = listOf('F', '7')
//    private val bottomConnectors = listOf('J', 'L')
//    override fun canConnect(direction: PipeDirection, c: Char): Boolean {
//        return when (direction) {
//            PipeDirection.Top -> topConnectors.contains(c)
//            PipeDirection.Bottom -> bottomConnectors.contains(c)
//            else -> false
//        }
//    }
//}


interface Pipe {
    val char: Char
    var connectionOne: Pair<PipeDirection, Pipe?>
    var connectionTwo: Pair<PipeDirection, Pipe?>
}

enum class PipeDirection {
    Top, Bottom, Left, Right, None, Unknown
}

//class VertPipe {
//    var pipeOne: PipeDirection
//    var pipeTwo: DirectionalPipe
//}

data class Coord(val x: Int, val y: Int)