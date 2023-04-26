fun main() {

    fun dirs(input: List<String>): MutableMap<String, Long> {
        var curr = "."
        val dirs: MutableMap<String, Long> = mutableMapOf() // path - size

        input.forEach { line ->
            when {
                """\$ cd /""".toRegex().matches(line) -> curr = "."
                """\$ cd \.\.""".toRegex().matches(line) -> curr = curr.substringBeforeLast('/')
                """\$ cd (\w+)""".toRegex().matches(line) -> {
                    val dir = """\$ cd (\w+)""".toRegex()
                        .matchEntire(line)
                        ?.groupValues?.get(1)
                        ?: throw IllegalArgumentException("Incorrect input line $line")
                    curr = if (curr.isEmpty()) dir else "$curr/$dir"
                }
                """(\d+) (.+)""".toRegex().matches(line) -> {
                    val (size, _) = """(\d+) (.+)""".toRegex()
                        .matchEntire(line)
                        ?.destructured
                        ?: throw IllegalArgumentException("Incorrect input line $line")
                    var dir = curr
                    while (true) {
                        dirs[dir] = dirs.getOrDefault(dir, 0) + size.toLong()
                        if (dir == ".") break
                        dir = dir.substringBeforeLast('/')
                    }
                }
            }
        }
        return dirs
    }

    fun part1(input: List<String>) {
        val sum = dirs(input).values.sumOf { if (it <= 100000) it else 0 }
        println(sum)
    }

    fun part2(input: List<String>) {
        val totalSpace = 70000000
        val requiredSpace = 30000000
        val dirs = dirs(input)
        val outermostDirSize = dirs.getValue(".")
        println(dirs.values.filter { totalSpace - (outermostDirSize - it) >= requiredSpace }.min())
    }

    val inputTest = readInput("day07/input_test")
    val input = readInput("day07/input")

    part1(inputTest)
    part1(input)

    part2(inputTest)
    part2(input)
}