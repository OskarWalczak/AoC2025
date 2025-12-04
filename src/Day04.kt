fun main() {

    fun readArray(input: List<String>): Array<Array<Char>> {
        val x = input.first().length
        val y = input.size
        val result = Array(x) { Array(y) { '.' } }

        for (i in input.indices) {
            for (j in input[i].indices) {
                result[i][j] = input[i][j]
            }
        }

        return result
    }

    fun createNeighborArray(array: Array<Array<Char>>): Array<Array<Int>> {
        val x = array[0].size
        val y = array.size
        val neighborArray: Array<Array<Int>> = Array(x) { Array(y) { 0 } }

        for (i in 0 until x) {
            for (j in 0 until y) {
                for (ni in i-1..i+1) {
                    for (nj in j-1..j+1) {
                        if (ni in 0 until x && nj in 0 until y && !(ni == i && nj == j) && array[i][j] == '@')
                            neighborArray[ni][nj]++
                    }
                }
            }
        }

        return neighborArray
    }

    fun part1(input: List<String>): Int {
        val array = readArray(input)

        val x = array[0].size
        val y = array.size

        val neighborArray = createNeighborArray(array)

        var result = 0

        for (i in 0 until x) {
            for (j in 0 until y) {
                if (array[i][j] == '@' && neighborArray[i][j] < 4) {
                    result++
                }
            }
        }

        return result
    }

    fun part2(input: List<String>): Int {
        var sumOfRemoved = 0
        val array = readArray(input)
        val x = array[0].size
        val y = array.size

        var lastRemoved = 100

        while (lastRemoved > 0) {
            lastRemoved = 0
            val neighborArray = createNeighborArray(array)

            for (i in 0 until x) {
                for (j in 0 until y) {
                    if (array[i][j] == '@' && neighborArray[i][j] < 4) {
                        array[i][j] = '.'
                        sumOfRemoved++
                        lastRemoved++
                    }
                }
            }
        }

        return sumOfRemoved
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)
    part2(testInput).println()

//    readArray(testInput).forEach { println(it.joinToString("")) }

    println("=========")
    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
