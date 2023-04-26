fun main() {
    fun bothParts(input: List<String>, reverse: Boolean = true): List<String> {
        val crates = input.takeWhile { it.isNotEmpty() }.dropLast(1)
            .map { it.replace("    ", " ") }
        val procedures = input.dropWhile { it.isNotEmpty() }.drop(1)

        val stacks = sortedMapOf<Int, List<String>>()
        crates.forEach { line ->
            line.split(" ").forEachIndexed { index, s ->
                if (s.isEmpty()) return@forEachIndexed
                else stacks[index + 1] = (stacks[index + 1] ?: listOf()) + s
            }
        }

        procedures.forEach { line ->
            // move 1 from 2 to 1
            val (amount, from, to) = line.split(" ").mapNotNull { it.toIntOrNull() }

            var newCrates: List<String> = stacks[from]?.take(amount) ?: listOf()
            if (reverse) newCrates = newCrates.reversed()
            val prevStack: List<String> = stacks[to] ?: listOf()
            stacks[to] = newCrates + prevStack

            stacks[from] = stacks[from]?.drop(amount)
        }

        return stacks.map { it.value.first() }
    }

    val inputTest = readInput("day05/input_test")
    val input = readInput("day05/input")

    println(bothParts(inputTest))
    println(bothParts(input))

    println(bothParts(inputTest, reverse = false))
    println(bothParts(input, reverse = false))
}