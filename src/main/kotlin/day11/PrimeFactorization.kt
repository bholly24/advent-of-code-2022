package day11

fun getPrimeFactors(value: Long): List<Int> {
    val primeFactors = mutableListOf<Int>()
    var dividend = value
    var divisor = 2
    while (dividend != 1L) {
        if (dividend % divisor == 0L) {
            primeFactors.add(divisor)
            dividend /= divisor
        } else {
            divisor += 1
        }
    }
    return primeFactors
}