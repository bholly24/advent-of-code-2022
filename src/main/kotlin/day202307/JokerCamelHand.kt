package day202307

data class JokerCamelHand(private val cards: List<Char>, override val wager: Int) : CamelCard {
    override val score = scoreCard()
    override val tieBreakScore = getTieBreakScore(cards)

    private fun scoreCard(): Score {
        val jokers = cards.count { it == 'J' }
        if (jokers > 0) {
            return scoreCardWithJokers(cards, jokers)
        } else {
            return scoreCardRegularly(cards)
        }
    }
    private fun scoreCardWithJokers(cards: List<Char>, jokers: Int): Score {
        val cardsWithoutJokers = cards.filter { it != 'J' }
        val distinctCardsWithoutJokers = cardsWithoutJokers.distinct()
        if (distinctCardsWithoutJokers.size <= 1) {
            return Score.FiveOfAKind
        } else if (jokers == 3) {
            return Score.FourOfAKind
        } else if (jokers == 2) {
            return if (distinctCardsWithoutJokers.size == 3) {
                Score.ThreeOfAKind
            } else {
                Score.FourOfAKind
            }
            // If 3 of a kind it's just five
        } else {
            when (distinctCardsWithoutJokers.size) {
                4 -> {
                    return Score.OnePair
                }
                3 -> {
                    return Score.ThreeOfAKind
                }
                // 2
                else -> {
                    val check = cardsWithoutJokers.filter { it == distinctCardsWithoutJokers.first() }.size
                    return if (check == 1 || check == 3) {
                        Score.FourOfAKind
                    } else {
                        Score.FullHouse
                    }
                }
            }
        }
    }

    private fun scoreCardRegularly(cards: List<Char>): Score {
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
                'J' -> 1
                else -> 10
            }
        }
    }
}