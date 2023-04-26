fun main() {

    /**
     * The ASCII value of lowercase alphabets are from 97 to 122. 'a' (97) to 'z' (122)
     * And, the ASCII value of uppercase alphabets are from 65 to 90. 'A' (65) to 'Z' (90)
     *
     * Lowercase item types a through z have priorities 1 through 26.
     * Uppercase item types A through Z have priorities 27 through 52.
     *
     * Formula: ASCII - x = priority
     * For lowercase: ASCII - 96 = priority, e.g.: 'a' = 1, 97 - 96 = 1
     * For uppercase: ASCII - 38 = priority, e.g.: 'A' = 27, 65 - 38 = 27
     */
    fun Char.toPriority(): Int {
        return when {
            isLowerCase() -> code - 96
            isUpperCase() -> code - 38
            else -> 0
        }
    }

    fun sumOfPrioritiesOfEachRucksack(input: List<String>): Int = input.sumOf { rucksack ->
        val mid: Int = rucksack.length / 2
        rucksack.substring(0, mid).toSet()
            .intersect(rucksack.substring(mid).toSet())
            .sumOf { it.toPriority() }
    }

    fun sumOfPrioritiesOfBadges(input: List<String>): Int {
        val groups = input.map { it.toSet() }.chunked(3)
        return groups.sumOf { group ->
            val commonPart = group[0].intersect(group[1]).intersect(group[2])
            commonPart.sumOf { it.toPriority() }
        }
    }

    val inputTest = readInput("day03/input_test")
    val input = readInput("day03/input")

    println(sumOfPrioritiesOfEachRucksack(inputTest))
    println(sumOfPrioritiesOfEachRucksack(input))

    println(sumOfPrioritiesOfBadges(inputTest))
    println(sumOfPrioritiesOfBadges(input))
}

/**
 * 157
 * 8139
 * 70
 * 2668
 */