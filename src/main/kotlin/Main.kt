import day01.CalorieItemizer
import day02.RockPaperScissorsCalculator
import day03.RucksackReorganizer
import printer.AdventPrinter

fun main(args: Array<String>) {
    AdventPrinter.introduction()

    AdventPrinter.partOne(1)
    val itemizer = CalorieItemizer("src/main/kotlin/day01/input.txt")
    itemizer.getTotalCaloriesForTopElves(1)
    AdventPrinter.partTwo(1)
    itemizer.getTotalCaloriesForTopElves(3)

    AdventPrinter.partOne(2)
    val rockPaperScissorsCalculator = RockPaperScissorsCalculator("src/main/kotlin/day02/input.txt")
    rockPaperScissorsCalculator.scoreSimpleSuggestions()
    AdventPrinter.partTwo(2)
    rockPaperScissorsCalculator.scoreReactiveResponses()

    AdventPrinter.partOne(3)
    val rucksackReorganizer = RucksackReorganizer("src/main/kotlin/day03/input.txt")
    rucksackReorganizer.getCostOfMispackedItem()
    AdventPrinter.partTwo(3)
    rucksackReorganizer.getCostOfBadges()
}
