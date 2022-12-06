import day01.CalorieItemizer
import day02.RockPaperScissorsCalculator
import day03.RucksackReorganizer
import day04.CleanupCoordinator
import day05.StackOrganizer
import day06.SignalDetector
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

    AdventPrinter.partOne(4)
    val cleanupCoordinator = CleanupCoordinator(FileHelper.puzzleFileForDay(4))
    cleanupCoordinator.countTotallyOverlappingAssignments()
    AdventPrinter.partTwo(4)
    cleanupCoordinator.countOverlappingAssignments()

    AdventPrinter.partOne(5)
    val stackOrganizer = StackOrganizer(FileHelper.puzzleFileForDay(5))
    stackOrganizer.organizeOneAtATime()
    AdventPrinter.partTwo(5)
    stackOrganizer.organizeAllAtOnce()

    AdventPrinter.partOne(6)
    val signalDetector = SignalDetector(FileHelper.puzzleFileForDay(6))
    signalDetector.detectUniqueSequenceOfFour()
    AdventPrinter.partTwo(6)
    signalDetector.detectUniqueSequenceOfFourteen()
}
