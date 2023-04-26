fun main() {
    fun part1(input: List<String>): Int {
        var cycle = 0
        var x = 1
        var sum = 0

        fun cycle() {
            cycle += 1
            if (cycle in listOf(20, 60, 100, 140, 180, 220)) sum += x * cycle
        }

        input.forEach { line ->
            when {
                line == "noop" -> cycle()
                line.startsWith("addx") -> {
                    repeat(2) { cycle() }
                    x += line.removePrefix("addx ").toInt()
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): String {
        var cycle = 0
        var x = 1
        var CRT = ""

        fun cycle() {
            cycle += 1
            CRT += if (cycle in x..x+2) "#" else "."
            if (cycle == 40) {
                cycle = 0
                CRT += "\n"
            }
        }

        input.forEach { line ->
            when {
                line == "noop" -> cycle()
                line.startsWith("addx") -> {
                    repeat(2) { cycle() }
                    x += line.removePrefix("addx ").toInt()
                }
            }
        }

        return CRT
    }

    val inputTest = readInput("day10/input_test")
    val input = readInput("day10/input")

    println(part1(inputTest))
    println(part1(input))

    println(part2(input))
}