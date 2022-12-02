package day02

object Rock : Choice {
    override val score = 1
    override val beats = Scissors
    override val losesTo = Paper
}
