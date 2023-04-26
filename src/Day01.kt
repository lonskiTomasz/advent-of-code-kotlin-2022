fun main() {

    fun maxCalories(input: List<String>): Int {
        var maximum = 0
        var current = 0

        input.forEach { line ->
            if (line.isBlank()) {
                maximum = maxOf(maximum, current)
                current = 0
            } else {
                current += line.toInt()
            }
        }

        return maximum
    }

    fun totalCaloriesOfTopThreeElves(input: List<String>): Int {
        val topThree = mutableListOf(0, 0, 0)
        var current = 0

        input.forEach { line ->
            if (line.isBlank()) {
                when {
                    current > topThree[0] -> {
                        topThree[2] = topThree[1]
                        topThree[1] = topThree[0]
                        topThree[0] = current
                    }
                    current > topThree[1] -> {
                        topThree[2] = topThree[1]
                        topThree[1] = current
                    }
                    current > topThree[2] -> {
                        topThree[2] = current
                    }
                }
                current = 0
            } else {
                current += line.toInt()
            }
        }

        return topThree.sum()
    }

    val inputTest = readInput("day01/input_test")
    val input = readInput("day01/input")

    println(maxCalories(inputTest))
    println(maxCalories(input))

    println(totalCaloriesOfTopThreeElves(inputTest))
    println(totalCaloriesOfTopThreeElves(input))
}

/**
 * 24000
 * 68467
 * 45000
 * 203420
 */