import kotlin.math.sign

private data class Position(val x: Int, val y: Int) {
    operator fun plus(other: Position) = Position(x = x + other.x, y = y + other.y)
    operator fun minus(other: Position) = Position(x = x - other.x, y = y - other.y)
    fun isNeighbour(other: Position): Boolean {
        val diff = this - other
        return diff.x in -1..1 && diff.y in -1..1
    }
    fun move(other: Position) = this + Position((other.x - x).sign, (other.y - y).sign)
}

private fun String.toStep() = when (this) {
    "R" -> Position(1, 0)
    "L" -> Position(-1, 0)
    "U" -> Position(0, 1)
    "D" -> Position(0, -1)
    else -> Position(0, 0)
}

fun main() {
    fun part1(input: List<String>) {
        val visitedPositions: MutableSet<Position> = mutableSetOf(Position(0, 0))
        var currHead = Position(0, 0)
        var currTail = Position(0, 0)

        fun moveAndFollow(step: Position, times: Int) {
            repeat(times) {
                val prevHead = currHead
                currHead += step
                if (currHead.isNeighbour(currTail)) return@repeat
                currTail += step
                currTail += (prevHead - currTail) // jump if diagonal
                visitedPositions += currTail
            }
        }

        input.forEach { line ->
            val cmd = line.split(' ')
            moveAndFollow(step = cmd[0].toStep(), times = cmd[1].toInt())
        }

        println(visitedPositions.size)
    }

    fun part2(input: List<String>) {
        val visitedPositions: MutableSet<Position> = mutableSetOf(Position(0, 0))
        val knots = MutableList(10) { Position(0, 0) }

        fun moveAndFollow(step: Position, times: Int) {
            repeat(times) {
                knots[0] += step // always move head
                knots.indices.windowed(2) { (head, tail) ->
                    if (!knots[tail].isNeighbour(knots[head])) {
                        knots[tail] = knots[tail].move(knots[head])
                    }
                }
                visitedPositions += knots.last()
            }
        }

        input.forEach { line ->
            val cmd = line.split(' ')
            moveAndFollow(step = cmd[0].toStep(), times = cmd[1].toInt())
        }
        println(visitedPositions.size)
    }


    val inputTest = readInput("day09/input_test")
    val input = readInput("day09/input")

    println(part1(inputTest))
    println(part1(input)) // 5513
    println(part2(inputTest))
    println(part2(input)) // 2427
}