import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0

        for (line in input) {
            var largest = 0
            var largestIndex = 0

            for (i in 0 until line.length-1) {
                if (line[i].toString().toInt() > largest) {
                    largest = line[i].toString().toInt()
                    largestIndex = i
                }
            }

            var secondLargest = 0
            for (i in (largestIndex+1) until line.length) {
                if (line[i].toString().toInt() > secondLargest) {
                    secondLargest = line[i].toString().toInt()
                }
            }

            sum += largest * 10 + secondLargest
        }

        return sum
    }

    fun part2(input: List<String>): Long {
        var sum = 0L

        for (line in input) {
            val digits: MutableList<Int> = mutableListOf()

            var borderIndex = -1
            for (i in 11 downTo 0) {
                var largest = 0
                for (j in (borderIndex+1) until line.length-i) {
                    if (line[j].toString().toInt() > largest) {
                        largest = line[j].toString().toInt()
                        borderIndex = j
                    }
                }
                digits.add(largest)
            }

            var num = 0L
            for (i in digits.indices) {
                num += digits[i].toLong() * (10.0.pow(11-i)).toLong()
            }

            sum += num
        }

        return sum
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 357)
    part2(testInput).println()

    println("=========")
    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
