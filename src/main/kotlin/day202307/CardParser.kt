package day202307

class CardParser {
    fun getHandsByRank(input: List<String>): Int {
        val cardList = input.map {
            val s = it.split(" ")
            CamelScore(s[0].toList(), s[1].toInt())
        }

        val score = scoreCards(cardList)
        println("Total score is $score")
        return score
    }
    fun getJokerHandsByRank(input: List<String>): Int {
        val cardList = input.map {
            val s = it.split(" ")
            JokerCamelHand(s[0].toList(), s[1].toInt())
        }
        val score = scoreCards(cardList)
        println("Total score with tricky jokers is is $score")
        return score
    }

    private fun scoreCards(cards: List<CamelCard>): Int {
        val scoreSort = cards
            .sortedWith(
                compareBy(
                    { it.score },
                    { it.tieBreakScore[0] },
                    { it.tieBreakScore[1] },
                    { it.tieBreakScore[2] },
                    { it.tieBreakScore[3] },
                    { it.tieBreakScore[4] }
                )
            )
        return scoreSort.foldIndexed(0) {i, acc, camelScore -> acc + camelScore.wager * (i + 1) }

    }
}
