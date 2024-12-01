import day00.LineCounter
import day01.CalorieItemizer
import day02.RockPaperScissorsCalculator
import day03.RucksackReorganizer
import day04.CleanupCoordinator
import day05.StackOrganizer
import day06.SignalDetector
import day07.DirectoryParser
import day08.TreeCounter
import day09.RopeModeler
import day10.SignalCalculators
import day10.SignalParser
import day11.MonkeyTossTracker
import day202302.MarbleCounter
import day202303.SchematicParser
import day202304.TicketParser
import day202305.AlmanacParser
import day202305.AlmanacRangeParser
import day202306.BoatRaceParser
import day202307.CardParser
import day202308.InstructionParser
import day202308.MultipleInstructionParser
import day202309.SequenceEvaluator
import fileHelper.FileHelper
import printer.AdventPrinter

fun main() {
    AdventPrinter.introduction()

    AdventPrinter.partOne(1)
    val t = LineCounter()
//    val lines = t.countLines(t.getLines(FileHelper.puzzleFileForDay(0)))
//    print("Lines total $lines")
    val linesAfterConversion = t.countLines(t.convertLinesToNumbers(FileHelper.getLines(FileHelper.puzzleFileForDay(0))))
    println("Lines total after conversion $linesAfterConversion")

    val counter = MarbleCounter()
    val linesForCounter = counter.getLines(FileHelper.puzzleFileForDay(202302))
    val gamesPossible = counter.determinePossibleGames(linesForCounter)
    println("Possible games added by id is $gamesPossible")

    val powerSum = counter.calculateCubePower(linesForCounter)
    println("Total power sum is $powerSum")

    val schematicReader = SchematicParser(FileHelper.getLines(FileHelper.puzzleFileForDay(202303)))
    schematicReader.testThirty()
    schematicReader.findGears()

    val ticketParser = TicketParser()
    ticketParser.getTicketValues(FileHelper.getLines(FileHelper.puzzleFileForDay(202304)))
    ticketParser.getRecursiveTicketValues(FileHelper.getLines(FileHelper.puzzleFileForDay(202304)))

    // Comment out for speed
//    val almanacParser = AlmanacParser()
//    almanacParser.getLowestLocation(FileHelper.getLines(FileHelper.puzzleFileForDay(202305)))
//
//    val almanacRangeParser = AlmanacRangeParser()
//    almanacRangeParser.getLowestLocation(FileHelper.getLines(FileHelper.puzzleFileForDay(202305)))

    val boatRaceParser = BoatRaceParser()
    boatRaceParser.calculateMaxDistance(FileHelper.getLines(FileHelper.puzzleFileForDay(202306)))
    boatRaceParser.calculateMaxDistanceAltogether(FileHelper.getLines(FileHelper.puzzleFileForDay(202306)))

    val cardParser = CardParser()
    cardParser.getHandsByRank(FileHelper.getLines(FileHelper.puzzleFileForDay(202307)))
    cardParser.getJokerHandsByRank(FileHelper.getLines(FileHelper.puzzleFileForDay(202307)))

    val instructionParser = InstructionParser()
    instructionParser.getTotalInstructionsToZZZ(FileHelper.getLines(FileHelper.puzzleFileForDay(202308)))

    val multipleInstructionParser = MultipleInstructionParser()
    multipleInstructionParser.getTotalInstructionsToZZZ(FileHelper.getLines(FileHelper.puzzleFileForDay(202308)))

    val sequenceEvaluator = SequenceEvaluator()
    sequenceEvaluator.getNextValues(FileHelper.getLines(FileHelper.puzzleFileForDay(202309)))
    sequenceEvaluator.getNextValuesWithRecursion(FileHelper.getLines(FileHelper.puzzleFileForDay(202309)))
    sequenceEvaluator.getPriorValuesWithRecursion(FileHelper.getLines(FileHelper.puzzleFileForDay(202309)))

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

    AdventPrinter.partOne(7)
    val directoryParser = DirectoryParser(FileHelper.puzzleFileForDay(7))
    directoryParser.getSize(100000)
    AdventPrinter.partTwo(7)
    directoryParser.getDirectoryToFreeUpSpace()

    AdventPrinter.partOne(8)
    val treeCounter = TreeCounter(FileHelper.puzzleFileForDay(8))
    treeCounter.getTotalVisibleTrees()
    AdventPrinter.partTwo(8)
    treeCounter.getMaxScenicScore()

    AdventPrinter.partOne(9)
    val ropeModeler = RopeModeler(FileHelper.puzzleFileForDay(9))
    ropeModeler.getUniquePositionsVisitedForRope(2)
    AdventPrinter.partTwo(9)
    ropeModeler.getUniquePositionsVisitedForRope(10)

    AdventPrinter.partOne(10)
    val signalDecoder = SignalParser(FileHelper.puzzleFileForDay(10))
    SignalCalculators.getSignalStrengthAtInterval(signalDecoder.getInstructions())
    AdventPrinter.partTwo(10)
    SignalCalculators.printSpritePosition(signalDecoder.getInstructions())

    AdventPrinter.partTwo(11)
    val monkeyTossTracker = MonkeyTossTracker(FileHelper.puzzleFileForDay(11))
    monkeyTossTracker.findMostMonkeyBusinessOverTwentyRounds()
}
