fun main() {

    fun sumOfFullyContainedAssignments(input: List<String>): Int {
        val inputLineRegex = """(\d+)-(\d+),(\d+)-(\d+)""".toRegex()
        return input.sumOf { line ->
            val (firstStart, firstEnd, secondStart, secondEnd) = inputLineRegex
                .matchEntire(line)
                ?.destructured
                ?: throw IllegalArgumentException("Incorrect input line $line")

            val first = (firstStart.toInt()..firstEnd.toInt()).toList()
            val second = (secondStart.toInt()..secondEnd.toInt()).toList()

            return@sumOf when {
                first.containsAll(second) -> 1
                second.containsAll(first) -> 1
                else -> 0
            }.toInt()
        }
    }

    fun sumOfOverlaps(input: List<String>): Int {
        val inputLineRegex = """(\d+)-(\d+),(\d+)-(\d+)""".toRegex()
        return input.sumOf { line ->
            val (firstStart, firstEnd, secondStart, secondEnd) = inputLineRegex
                .matchEntire(line)
                ?.destructured
                ?: throw IllegalArgumentException("Incorrect input line $line")

            val first = firstStart.toInt()..firstEnd.toInt()
            val second = secondStart.toInt()..secondEnd.toInt()

            return@sumOf (if (first.intersect(second).isNotEmpty()) 1 else 0).toInt()
        }
    }

    val inputTest = readInput("day04/input_test")
    val input = readInput("day04/input")

    println(sumOfFullyContainedAssignments(inputTest))
    println(sumOfFullyContainedAssignments(input))

    println(sumOfOverlaps(inputTest))
    println(sumOfOverlaps(input))
}

/**
 * 2
 * 464
 * 4
 * 770
 */