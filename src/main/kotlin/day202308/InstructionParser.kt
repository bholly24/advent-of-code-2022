package day202308

class InstructionParser {
    private val terminalNode = "ZZZ"

    fun getTotalInstructionsToZZZ(input: List<String>): Int {
        val instructions = input[0].toList()
        val nodes = parseNodes(input.filter { it.contains("=") })
        var startingNode = "AAA"
        var totalInstructions = 0

        while (startingNode != terminalNode) {
            val i = getInstructionsToZZ(instructions, nodes, startingNode)
            startingNode = i.first
            totalInstructions += i.second
        }

        println("The total number of instructions to $terminalNode was $totalInstructions")
        return totalInstructions
    }

    private fun getInstructionsToZZ(instructions: List<Char>, nodes: Map<String, Pair<String, String>>, startingNode: String): Pair<String, Int> {
        var instructionsPassed = 0
        var currentNode = startingNode
        for (i in instructions) {
            if (i == 'L') {
                currentNode = nodes[currentNode]!!.first
            } else if (i == 'R') {
                currentNode = nodes[currentNode]!!.second
            }
            instructionsPassed += 1
            if (currentNode == terminalNode) {
                break
            }
        }
        return Pair(currentNode, instructionsPassed)
    }

    private fun parseNodes(nodes: List<String>): Map<String, Pair<String, String>> {
        val map = mutableMapOf<String, Pair<String, String>>()
        nodes.forEach { node ->
            val split = node.split(" = ")
            val rlParse = split[1].split(", ")
                .map { it.replace(Regex("\\s|\\(|\\)"), "") }
            map[split[0]] = Pair(rlParse[0], rlParse[1])
        }
        return map
    }
}