fun main() {

    fun bothParts(input: List<String>, distinctChars: Int): List<Int> {
        val results = mutableListOf<Int>()
        input.forEach { line ->
            for (index in 0..line.length - distinctChars) {
                if (line.substring(index, index + distinctChars).toSet().size == distinctChars) {
                    results.add(index + distinctChars)
                    break
                }
            }
        }
        return results
    }

    val inputTest = readInput("day06/input_test")
    val input = readInput("day06/input")

    println(bothParts(inputTest, distinctChars = 4))
    println(bothParts(input, distinctChars = 4))

    println(bothParts(inputTest, distinctChars = 14))
    println(bothParts(input, distinctChars = 14))
}