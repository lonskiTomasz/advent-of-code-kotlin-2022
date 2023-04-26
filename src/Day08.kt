fun main() {

    fun part1(input: List<String>) {
        val matrix: List<List<Int>> = input.map { it.map { it.digitToInt() } }
        val lastIndex = matrix.size - 1
        val visibleTrees = matrix.withIndex().sumOf { (rowIndex, row) ->
            when (rowIndex) {
                0, lastIndex -> row.size
                else -> {
                    row.withIndex().sumOf { (index, value) ->
                        when (index) {
                            0, lastIndex -> 1.toInt()
                            else -> {
                                val left = row.take(index)
                                val right = row.takeLast(row.size - index - 1)
                                val column = matrix.map { it[index] }
                                val top = column.take(rowIndex)
                                val bottom = column.takeLast(matrix.size - rowIndex - 1)

                                if (left.all { it < value } || right.all { it < value } ||
                                    top.all { it < value } || bottom.all { it < value }) {
                                    1
                                } else 0
                            }
                        }
                    }
                }
            }
        }
        println(visibleTrees)
    }

    fun part2(input: List<String>) {
        fun List<Int>.visibleTrees(value: Int, withReverse: Boolean): Int {
            var trees = 0
            val list = if (withReverse) reversed() else this
            for (v in list) {
                trees++
                if (v >= value) break
            }
            return trees
        }

        var maxScore = 0
        val matrix: List<List<Int>> = input.map { it.map { it.digitToInt() } }
        val lastIndex = matrix.size - 1
        matrix.forEachIndexed { rowIndex, row ->
            if (rowIndex !in listOf(0, lastIndex)) { // skip edges
                val score = row.withIndex().maxOf { (index, value) ->
                    when (index) {
                        0, lastIndex -> 0 // skip edges
                        else -> {
                            val visibleLeftTrees =
                                row.take(index).visibleTrees(value, withReverse = true)
                            val visibleRightTrees = row.takeLast(row.size - index - 1)
                                .visibleTrees(value, withReverse = false)
                            val column = matrix.map { it[index] }
                            val visibleTopTrees =
                                column.take(rowIndex).visibleTrees(value, withReverse = true)
                            val visibleBottomTrees = column.takeLast(matrix.size - rowIndex - 1)
                                .visibleTrees(value, withReverse = false)

                            visibleLeftTrees * visibleRightTrees * visibleTopTrees * visibleBottomTrees
                        }
                    }
                }
                maxScore = maxOf(score, maxScore)
            }
        }
        println(maxScore)
    }

    val inputTest = readInput("day08/input_test")
    val input = readInput("day08/input")

    part1(inputTest)
    part1(input)

    part2(inputTest)
    part2(input)
}