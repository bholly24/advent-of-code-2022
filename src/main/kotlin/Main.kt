import day01.CalorieItemizer
import printer.AdventPrinter

fun main(args: Array<String>) {
    AdventPrinter.introduction()

    AdventPrinter.partOne(1)
    val itemizer = CalorieItemizer("src/main/kotlin/day01/input.txt")
    itemizer.findTotalMaximumCaloriesForTopElves(1)
    AdventPrinter.partTwo(1)
    itemizer.findTotalMaximumCaloriesForTopElves(3)

}
