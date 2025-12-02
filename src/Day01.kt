fun main() {
    fun part1(input: List<String>): Int {
        var counter = 0

        var curr_position = 50

        for (line in input) {
            val trimmed = line.trim()
            val dir = if (trimmed.startsWith("R")) 1 else -1
            curr_position = (curr_position + dir * (trimmed.substring(1).toIntOrNull() ?: error("Invalid input: $line"))) % 100

            if (curr_position == 0) counter++
        }

        return counter
    }

    fun part2(input: List<String>): Int {
        var counter = 0

        var currPosition = 50
        var previousPosition = 50

        for (line in input) {
            previousPosition = currPosition
            val trimmed = line.trim()
            val dir = if (trimmed.startsWith("R")) 1 else -1
            var steps = (trimmed.substring(1).toIntOrNull() ?: error("Invalid input: $line"))

            counter += steps.div(100)
            steps = steps.mod(100)
            currPosition += + dir * steps

            if ((currPosition > 99 || currPosition < 0) && !(previousPosition == 0 && dir == -1)) {
                counter++
            }
            else if (currPosition == 0)
                counter++

            currPosition = currPosition.mod(100)
        }

        return counter
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 3)
    part2(testInput).println()

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
