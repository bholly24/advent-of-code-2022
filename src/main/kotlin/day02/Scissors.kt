package day02

object Scissors : Choice {
    override val score = 3
    override val beats = Paper
    override val losesTo = Rock
}
