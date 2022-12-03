import day01.CalorieItemizer
import day02.RockPaperScissorsCalculator
import day03.RucksackReorganizer
import fileHelper.FileHelper
import printer.AdventPrinter

fun main() {
    AdventPrinter.introduction()

    AdventPrinter.partOne(1)
    val itemizer = CalorieItemizer(FileHelper.puzzleFileForDay(1))
    itemizer.getTotalCaloriesForTopElves(1)
    AdventPrinter.partTwo(1)
    itemizer.getTotalCaloriesForTopElves(3)

    AdventPrinter.partOne(2)
    val rockPaperScissorsCalculator = RockPaperScissorsCalculator(FileHelper.puzzleFileForDay(2))
    rockPaperScissorsCalculator.scoreSimpleSuggestions()
    AdventPrinter.partTwo(2)
    rockPaperScissorsCalculator.scoreReactiveResponses()

    AdventPrinter.partOne(3)
    val rucksackReorganizer = RucksackReorganizer(FileHelper.puzzleFileForDay(3))
    rucksackReorganizer.getCostOfMispackedItem()
    AdventPrinter.partTwo(3)
    rucksackReorganizer.getCostOfBadges()
}
