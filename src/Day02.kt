fun main() {
    fun totalScoreOfGivenStrategy(input: List<String>): Int {
        /**
         * A for Rock, B for Paper, and C for Scissors
         * X for Rock, Y for Paper, and Z for Scissors
         *
         * 1 for Rock, 2 for Paper, and 3 for Scissors
         * 0 if you lost, 3 if the round was a draw, and 6 if you won
         */
        val battleDictionary: HashMap<String, Int> = hashMapOf(
            "A X" to 1 + 3,
            "A Y" to 2 + 6,
            "A Z" to 3 + 0,
            "B X" to 1 + 0,
            "B Y" to 2 + 3,
            "B Z" to 3 + 6,
            "C X" to 1 + 6,
            "C Y" to 2 + 0,
            "C Z" to 3 + 3,
        )
        var totalScore = 0
        input.forEach { line ->
            totalScore += battleDictionary[line] ?: 0
        }
        return totalScore
    }

    fun totalScoreBasedOnRequiredOutput(input: List<String>): Int {
        /**
         * A for Rock, B for Paper, and C for Scissors
         * X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win
         *
         * 1 for Rock, 2 for Paper, and 3 for Scissors
         * 0 if you lost, 3 if the round was a draw, and 6 if you won
         */
        val battleDictionary: HashMap<String, Int> = hashMapOf(
            "A X" to 0 + 3, // rock > scissors
            "A Y" to 3 + 1, // draw
            "A Z" to 6 + 2, // rock < paper
            "B X" to 0 + 1, // paper > rock
            "B Y" to 3 + 2, // draw
            "B Z" to 6 + 3, // paper < scissors
            "C X" to 0 + 2, // scissors > paper
            "C Y" to 3 + 3, // draw
            "C Z" to 6 + 1, // scissors < rock
        )
        var totalScore = 0
        input.forEach { line ->
            totalScore += battleDictionary[line] ?: 0
        }
        return totalScore
    }

    val inputTest = readInput("day02/input_test")
    val input = readInput("day02/input")

    println(totalScoreOfGivenStrategy(inputTest))
    println(totalScoreOfGivenStrategy(input))

    println(totalScoreBasedOnRequiredOutput(inputTest))
    println(totalScoreBasedOnRequiredOutput(input))
}