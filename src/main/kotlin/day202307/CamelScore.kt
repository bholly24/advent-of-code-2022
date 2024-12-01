package day202307


data class CamelScore(private val cards: List<Char>, override val wager: Int): CamelCard {
    override val score = scoreCard(cards)
    override val tieBreakScore = getTieBreakScore(cards)

    private fun scoreCard(cards: List<Char>): Score {
        val distinctCards = cards.distinct()
        when (distinctCards.size) {
            5 -> {
                return Score.HighCard
            }

            4 -> {
                return Score.OnePair
            }

            3 -> {
                val checkOne = cards.filter { it == distinctCards.first() }.size
                val checkTwo = cards.filter { it == distinctCards[1] }.size
                return if (checkOne == 2 || checkTwo == 2) {
                    Score.TwoPair
                } else {
                    Score.ThreeOfAKind
                }
            }

            2 -> {
                val check = cards.filter { it == distinctCards.first() }.size
                return if (check == 1 || check == 4) {
                    Score.FourOfAKind
                } else {
                    Score.FullHouse
                }
            }

            1 -> {
                return Score.FiveOfAKind
            }

            else -> {
                return Score.HighCard
            }
        }
    }

    private fun getTieBreakScore(cards: List<Char>): List<Int> {
        return cards.map { convertCharToInt(it) }
    }

    private fun convertCharToInt(char: Char): Int {
        return if (char.isDigit()) {
            char.digitToInt()
        } else {
            when (char) {
                'A' -> 14
                'K' -> 13
                'Q' -> 12
                'J' -> 11
                else -> 10
            }
        }
    }
}