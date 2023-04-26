fun main() {

    data class Monkey(
        val id: String,
        val items: MutableList<Long>,
        val inspection: (Long) -> Long,
        val test: Int,
        val ifTrue: String,
        val ifFalse: String
    ) {
        var inspectedItems = 0L
        fun inspectItems(afterInspection: (Long) -> Long): List<Pair<String, Long>> {
            return items.map { item -> inspect(item, afterInspection) }.also {
                inspectedItems += items.size
                items.clear()
            }
        }

        private fun inspect(item: Long, afterInspection: (Long) -> Long): Pair<String, Long> {
            val worryLevel: Long = afterInspection(inspection(item))
            return (if ((worryLevel % test) == 0L) ifTrue else ifFalse) to worryLevel
        }
    }

    fun List<String>.toMonkey(): Monkey {
        val id: String = get(0).removePrefix("Monkey ").dropLast(1)
        val items: MutableList<Long> = get(1)
            .removePrefix("  Starting items: ")
            .split(", ")
            .map { it.toLong() }
            .toMutableList()

        val operation: (Long) -> Long = get(2)
            .removePrefix("  Operation: new = old ")
            .split(' ')
            .let { (operator, n) ->
                when {
                    n == "old" -> { old -> old * old }
                    operator == "*" -> { old -> old * n.toLong() }
                    operator == "+" -> { old -> old + n.toLong() }
                    else -> error("")
                }
            }

        val denominator: Int = get(3).removePrefix("  Test: divisible by ").toInt()
        val ifTrue: String = get(4).removePrefix("    If true: throw to monkey ")
        val ifFalse: String = get(5).removePrefix("    If false: throw to monkey ")

        return Monkey(id, items, operation, denominator, ifTrue, ifFalse)
    }

    fun List<String>.toMonkeys(): List<Monkey> = chunked(7).map { it.toMonkey() }

    fun round(monkeys: List<Monkey>, afterInspection: (Long) -> Long): List<Monkey> {
        val iterator = monkeys.iterator()
        while (iterator.hasNext()) {
            iterator.next().inspectItems(afterInspection).forEach { (id, item) ->
                monkeys.find { it.id == id }?.items?.add(item)
            }
        }
        return monkeys
    }

    fun List<Monkey>.getResult() {
        val result = map { it.inspectedItems }.sortedDescending().take(2)
        println(result)
        println(result[0] * result[1])
    }

    fun part1(input: List<String>) {
        var monkeys = input.toMonkeys()
        repeat(20) { monkeys = round(monkeys, afterInspection = { it / 3 }) }
        monkeys.getResult()
    }

    fun part2(input: List<String>) {
        var monkeys = input.toMonkeys()
        val mod = monkeys.map { it.test }.reduce { acc, i -> acc * i }
        repeat(10000) { monkeys = round(monkeys, afterInspection = { it % mod }) }
        monkeys.getResult()
    }

    val inputTest = readInput("day11/input_test")
    val input = readInput("day11/input")

    println(part1(inputTest))
    println(part1(input))

    println(part2(inputTest))
    println(part2(input))
}