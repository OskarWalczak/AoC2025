fun main() {

    fun parseInputPart1(input: List<String>): List<Pair<List<Int>, String>> {
        val result: MutableList<Pair<MutableList<Int>, String>> = mutableListOf()

        for (line in input) {
            val content = line.split(" ").filter { it.isNotBlank() }
            if (content[0] == "*" || content[0] == "+"){
                for (i in 0 until content.size) {
                    if (result.size <= i) {
                        result.add(Pair(mutableListOf(), content[i]))
                    }
                    else {
                        result[i] = Pair(result[i].first, content[i])
                    }
                }
            }
            else {
                for (i in 0 until content.size) {
                    if (result.size <= i) {
                        result.add(Pair(mutableListOf(content[i].toInt()), ""))
                    }
                    else {
                        result[i].first.add(content[i].toInt())
                    }
                }
            }
        }

        return result
    }

    fun parseInputPart2(input: List<String>): List<Pair<List<Int>, String>> {
        val result: MutableList<Pair<MutableList<Int>, String>> = mutableListOf()

        val x = input.first().length
        val y = input.size
        val charArray = Array(y) { Array(x) { "." } }

        for (i in 0 until y) {
            for (j in 0 until x) {
                charArray[i][j] = input[i][j].toString()
            }
        }

        val numbers: MutableList<Int> = mutableListOf()
        for (i in x-1 downTo 0) {
            var sign: String = ""
            var currentNumber = ""
            for (j in 0 until y) {
                val char = charArray[j][i]
                if (char == " ") {
                    if (j == y -1) {
                        if (currentNumber.isNotEmpty()) {
                            numbers.add(currentNumber.toInt())
                        }
                    }
                }
                else if (char.toIntOrNull() != null) {
                    currentNumber += char
                }
                else if (char == "+" || char == "*") {
                    sign = char
                    numbers.add(currentNumber.toInt())
                    result.add(Pair(numbers.toMutableList(), sign))
                    numbers.clear()
                }
            }
        }

        return result
    }

    fun calculate(data: List<Pair<List<Int>, String>>): Long {
        var sum = 0L

        for (problem in data) {
            var partialResult = if (problem.second == "*") 1L else 0L
            for (number in problem.first) {
                if (problem.second == "+") {
                    partialResult += number
                }
                else if (problem.second == "*") {
                    partialResult *= number
                }
            }
            sum += partialResult
        }

        return sum
    }

    fun part1(input: List<String>): Long {
        val data = parseInputPart1(input)

        return calculate(data)
    }

    fun part2(input: List<String>): Long {
        val data = parseInputPart2(input)

        return calculate(data)
    }

    //WARNING: to work properly, .trim() must be removed from Utils.readInput()
    
    
    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 4277556L)
    part2(testInput).println()

    println("=========")
    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
