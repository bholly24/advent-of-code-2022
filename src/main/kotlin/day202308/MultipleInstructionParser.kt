package day202308

class MultipleInstructionParser {

    fun getTotalInstructionsToZZZ(input: List<String>): Long {
        val instructions = input[0].toList()
        val nodes = parseNodes(input.filter { it.contains("=") })
        var startingNodes = nodes.keys.filter { it[2] == 'A' }
        val finalResults = mutableListOf<Int>()
        var results = mutableListOf<SearchOutput>()
        var totalInstructions = 0

        while (startingNodes.isNotEmpty()) {
            startingNodes.forEach {
                results.add(getInstructionsToZZ(instructions, nodes, it))
            }
            finalResults.addAll(results.filter { it.finalNode[2] == 'Z' }.map { it.instructionsPassed + totalInstructions })
            totalInstructions += results.map { it.instructionsPassed }.max()
            startingNodes = results.filter { it.finalNode[2] != 'Z' }.map { it.finalNode }
            results = mutableListOf()
        }


        println("The total number of instructions so that all end in Z is $finalResults")
        return 8
    }

    private fun getInstructionsToZZ(
        instructions: List<Char>,
        nodes: Map<String, Pair<String, String>>,
        startingNode: String
    ): SearchOutput {
        var instructionsPassed = 0
        var currentNode = startingNode
        for (i in instructions) {
            if (i == 'L') {
                currentNode = nodes[currentNode]!!.first
            } else if (i == 'R') {
                currentNode = nodes[currentNode]!!.second
            }
            instructionsPassed += 1
            if (currentNode[2] == 'Z') {
                return SearchOutput(currentNode, instructionsPassed)
            }
        }
        return SearchOutput(currentNode, instructionsPassed)
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

data class SearchOutput(val finalNode: String, val instructionsPassed: Int)
