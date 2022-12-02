package day02

import java.io.File

class RockPaperScissorsCalculator(filePath: String) {
    private val suggestions: List<List<String>>

    init {
        suggestions = getStrategyGuide(filePath)
    }

    fun scoreSimpleSuggestions(): Int {
        val totalScore = suggestions.sumOf { simpleScore(getOpponentChoice(it.first()), getOpponentChoice(it.last())) }
        println("Total score is $totalScore")
        return totalScore
    }

    fun scoreReactiveResponses(): Int {
        val totalScore = suggestions.sumOf { getReactiveScore(getOpponentChoice(it.first()), getOutcome(it.last())) }
        println("Total score is $totalScore")
        return totalScore
    }

    private fun getReactiveScore(opponent: Choice, you: PartTwoOutcome): Int {
       return when (you) {
           PartTwoOutcome.Win -> 6 + opponent.losesTo.score
           PartTwoOutcome.Lose -> opponent.beats.score
           else -> 3 + opponent.score
       }
    }

    private fun simpleScore(opponent: Choice, you: Choice) : Int {
        return if (you == opponent) {
            you.score + 3
        } else if (you.beats == opponent) {
            you.score + 6
        } else {
            you.score
        }
    }

    private fun getStrategyGuide(filePath: String): List<List<String>> {
        return File(filePath).readLines()
            .map { line -> line.split(" ") }
    }


    private fun getOutcome(input: String): PartTwoOutcome {
        return when (input) {
            "X" -> PartTwoOutcome.Lose
            "Y" -> PartTwoOutcome.Tie
            else -> PartTwoOutcome.Win
        }
    }

    private fun getOpponentChoice(choice: String): Choice {
        return if (listOf("A", "X").contains(choice)) {
            Rock
        } else if (listOf("B", "Y").contains(choice)) {
            Paper
        } else {
            Scissors
        }
    }
}