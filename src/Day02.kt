fun main() {
    fun part1(input: List<String>): Long {
        var sum = 0L
        val line = input.first()
        val ranges = line.split(",")

        for (range in ranges) {
            val (firstId, lastId) = range.split("-").map { it.toLong() }

            for (id in firstId..lastId) {
                val strId = id.toString()
                if (strId.length % 2 == 0) {
                    val firstHalf = strId.take(strId.length / 2)
                    val secondHalf = strId.takeLast(strId.length / 2)
                    if (firstHalf == secondHalf) {
                        sum += id
                    }
                }
            }
        }

        return sum
    }

    fun part2(input: List<String>): Long {
        var sum = 0L
        val line = input.first()
        val ranges = line.split(",")

        for (range in ranges) {
            val (firstId, lastId) = range.split("-").map { it.toLong() }

            for (id in firstId..lastId) {
                val strId = id.toString()

                for (len in 1..strId.length / 2) {
                    if (strId.length % len != 0) continue
                    val slices: MutableList<String> = mutableListOf()
                    for (i in 0 until strId.length / len) {
                        slices.add(strId.substring(i * len, (i + 1) * len))
                    }

                    var areAllEqual = true
                    for (slice in slices) {
                        if (slice != slices[0]) {
                            areAllEqual = false
                            break
                        }
                    }

                    if (areAllEqual) {
                        sum += id
                        break
                    }
                }
            }
        }

        return sum
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 1227775554L)
    part2(testInput).println()

    println("=========")
    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
