package day202304

import kotlin.math.pow

class TicketParser {

    fun getRecursiveTicketValues(ticketInput: List<String>): Int {
        val winningTickets = getWinningTickets(ticketInput)
        val sumOfTickets = List(winningTickets.size) { index ->
            tallyRecursiveTicketWinnings(winningTickets, index)
        }.sum()
        println("Total tickets won was $sumOfTickets")
        return sumOfTickets
    }

    fun getTicketValues(ticketInput: List<String>): Int {
        val sum = getWinningTickets(ticketInput)
            .sumOf {
                if (it == 0) 0 else 2.toFloat().pow(it - 1).toInt()
            }

        println("Ticket sum is $sum")
        return sum
    }

    private fun getWinningTickets(ticketInput: List<String>): List<Int> {
        val tickets: List<List<List<Int>>> = ticketInput.map {
            val split = it.split(":", "|")
            val ticketNumbers = parseNumbers(split[1])

            val winningNumbers = parseNumbers(split[2])
            listOf(ticketNumbers, winningNumbers)
        }
        return tickets.map { getWinners(it[0], it[1]) }
    }

    private fun tallyRecursiveTicketWinnings(ticketWinnings: List<Int>, index: Int): Int {
        var score = 1
        val ticketsWon = ticketWinnings[index]
        val maxPossible = index + ticketsWon
        val max = if (maxPossible < ticketWinnings.size - 1) maxPossible else ticketWinnings.size - 1
        val additionalTicketScores = (index + 1)..max

        for (i in additionalTicketScores) {
            score += tallyRecursiveTicketWinnings(ticketWinnings, i)
        }
        return score
    }

    private fun parseNumbers(s: String): List<Int> {
        return s
            .trim()
            .split(" ")
            .filter { it != "" }
            .map { it.toInt() }
    }

    private fun getWinners(ticketNumbers: List<Int>, winningNumbers: List<Int>): Int {
        return ticketNumbers.sumOf { if (winningNumbers.contains(it)) 1.toInt() else 0 }
    }
}