fun main() {
    fun part1(input: List<String>): Int {
        val ranges: MutableList<LongRange> = mutableListOf()
        var wasBreak = false
        var numberOfFresh = 0
        for (line in input) {
            if (line.isEmpty()) {
                wasBreak = true
                continue
            }

            if (!wasBreak) {
                val (min, max) = line.split("-").map { it.toLong() }
                ranges.add(min..max)
            }
            else {
                val num = line.toLong()
                for (range in ranges) {
                    if (num in range) {
                        numberOfFresh++
                        break
                    }
                }
            }
        }

        return numberOfFresh
    }

    fun combineRanges(ranges: List<LongRange>): List<LongRange> {
        var result: MutableList<LongRange> = ranges.toMutableList()
        var wereChanges = false

        do {
            wereChanges = false
            val newRanges: MutableList<LongRange> = mutableListOf()
            val combinedRanges: MutableList<Int> = mutableListOf()
            for (i in result.indices) {
                if (i in combinedRanges) continue
                for (j in i+1 until result.size) {
                    if (j in combinedRanges) continue

                    val range1 = result[i]
                    val range2 = result[j]
                    if (range1.last >= range2.first && range1.first <= range2.last) {
                        newRanges.add(minOf(range1.first, range2.first)..maxOf(range1.last, range2.last))
                        combinedRanges.add(i)
                        combinedRanges.add(j)
                        wereChanges = true
                        break
                    }
                }
                if (i !in combinedRanges)
                    newRanges.add(result[i])
            }
            if(wereChanges)
                result = newRanges.toMutableList()
        } while (wereChanges)

        return result
    }

    fun part2(input: List<String>): Long {
        val ranges: MutableList<LongRange> = mutableListOf()
        var numberOfFresh = 0L
        for (line in input) {
            if (line.isEmpty()) {
                break
            }

            val (min, max) = line.split("-").map { it.toLong() }
            ranges.add(min..max)
        }

        val newRanges = combineRanges(ranges)

        for(range in newRanges) {
            numberOfFresh += (range.last - range.first + 1)
        }

        return numberOfFresh
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 3)
    part2(testInput).println()

    println("=========")
    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
