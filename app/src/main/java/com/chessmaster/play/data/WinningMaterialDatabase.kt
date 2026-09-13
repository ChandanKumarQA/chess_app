package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object WinningMaterialDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("6k1/6pp/8/8/q7/8/1P6/R5K1 w - - 0 1", listOf("a1a4"), "Winning Hanging Queen on a4 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/q7/8/8/1P6/R5K1 w - - 0 1", listOf("a1a5"), "Winning Hanging Queen on a5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/1q6/8/P7/1R4K1 w - - 0 1", listOf("b1b4"), "Winning Hanging Queen on b4 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/1q6/8/8/P7/1R4K1 w - - 0 1", listOf("b1b5"), "Winning Hanging Queen on b5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/2q5/8/P7/2R3K1 w - - 0 1", listOf("c1c4"), "Winning Hanging Queen on c4 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/7q/8/P7/6KR w - - 0 1", listOf("h1h4"), "Winning Hanging Queen on h4 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/7q/8/8/P7/6KR w - - 0 1", listOf("h1h5"), "Winning Hanging Queen on h5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/3q4/8/P7/3R2K1 w - - 0 1", listOf("d1d4"), "Winning Hanging Queen on d4 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/4q3/8/P7/4R1K1 w - - 0 1", listOf("e1e4"), "Winning Hanging Queen on e4 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/5q2/8/P7/5RK1 w - - 0 1", listOf("f1f4"), "Winning Hanging Queen on f4 with Rook"),
        RawTacticalPuzzle("4k3/8/8/8/7r/8/PB6/4K3 w - - 0 1", listOf("b2f6", "e8d7", "f6h4"), "Bishop Fork from b2 Winning Hanging Rook"),
        RawTacticalPuzzle("4k3/8/8/1r6/8/8/6P1/Q5K1 w - - 0 1", listOf("a1e5", "e8d7", "e5b5"), "Queen Fork from a1 Winning b5 Rook"),
        RawTacticalPuzzle("4k2r/8/8/4N3/8/8/P7/4K3 w - - 0 1", listOf("e5f7", "e8e7", "f7h8"), "Knight Fork from e5 Winning Rook on h8"),
        RawTacticalPuzzle("4k2r/8/8/6N1/8/8/P7/4K3 w - - 0 1", listOf("g5f7", "e8e7", "f7h8"), "Knight Fork from g5 Winning Rook on h8"),
        RawTacticalPuzzle("6k1/6pp/8/2q5/8/8/P7/2R3K1 w - - 0 1", listOf("c1c5"), "Winning Hanging Queen on c5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/3q4/8/8/P7/3R2K1 w - - 0 1", listOf("d1d5"), "Winning Hanging Queen on d5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/4q3/8/8/P7/4R1K1 w - - 0 1", listOf("e1e5"), "Winning Hanging Queen on e5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/5q2/8/8/P7/5RK1 w - - 0 1", listOf("f1f5"), "Winning Hanging Queen on f5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/q7/8/7P/R5K1 w - - 0 1", listOf("a1a4"), "Winning Hanging Queen on a4 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/q7/8/8/7P/R5K1 w - - 0 1", listOf("a1a5"), "Winning Hanging Queen on a5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/1q6/8/7P/1R4K1 w - - 0 1", listOf("b1b4"), "Winning Hanging Queen on b4 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/1q6/8/8/7P/1R4K1 w - - 0 1", listOf("b1b5"), "Winning Hanging Queen on b5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/2q5/8/1P6/2R3K1 w - - 0 1", listOf("c1c4"), "Winning Hanging Queen on c4 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/7q/8/1P6/6KR w - - 0 1", listOf("h1h4"), "Winning Hanging Queen on h4 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/7q/8/8/1P6/6KR w - - 0 1", listOf("h1h5"), "Winning Hanging Queen on h5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/3q4/8/1P6/3R2K1 w - - 0 1", listOf("d1d4"), "Winning Hanging Queen on d4 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/4q3/8/1P6/4R1K1 w - - 0 1", listOf("e1e4"), "Winning Hanging Queen on e4 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/5q2/8/1P6/5RK1 w - - 0 1", listOf("f1f4"), "Winning Hanging Queen on f4 with Rook"),
        RawTacticalPuzzle("4k3/8/8/8/7r/8/1B4P1/4K3 w - - 0 1", listOf("b2f6", "e8d7", "f6h4"), "Bishop Fork from b2 Winning Hanging Rook"),
        RawTacticalPuzzle("4k3/8/8/1r6/8/8/7P/Q5K1 w - - 0 1", listOf("a1e5", "e8d7", "e5b5"), "Queen Fork from a1 Winning b5 Rook"),
        RawTacticalPuzzle("4k2r/8/8/4N3/8/8/1P6/4K3 w - - 0 1", listOf("e5f7", "e8e7", "f7h8"), "Knight Fork from e5 Winning Rook on h8"),
        RawTacticalPuzzle("4k2r/8/8/6N1/8/8/1P6/4K3 w - - 0 1", listOf("g5f7", "e8e7", "f7h8"), "Knight Fork from g5 Winning Rook on h8"),
        RawTacticalPuzzle("6k1/6pp/8/2q5/8/8/1P6/2R3K1 w - - 0 1", listOf("c1c5"), "Winning Hanging Queen on c5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/3q4/8/8/1P6/3R2K1 w - - 0 1", listOf("d1d5"), "Winning Hanging Queen on d5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/4q3/8/8/1P6/4R1K1 w - - 0 1", listOf("e1e5"), "Winning Hanging Queen on e5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/5q2/8/8/1P6/5RK1 w - - 0 1", listOf("f1f5"), "Winning Hanging Queen on f5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/q7/8/6P1/R5K1 w - - 0 1", listOf("a1a4"), "Winning Hanging Queen on a4 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/q7/8/8/6P1/R5K1 w - - 0 1", listOf("a1a5"), "Winning Hanging Queen on a5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/1q6/8/6P1/1R4K1 w - - 0 1", listOf("b1b4"), "Winning Hanging Queen on b4 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/1q6/8/8/6P1/1R4K1 w - - 0 1", listOf("b1b5"), "Winning Hanging Queen on b5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/2q5/8/7P/2R3K1 w - - 0 1", listOf("c1c4"), "Winning Hanging Queen on c4 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/7q/8/6P1/6KR w - - 0 1", listOf("h1h4"), "Winning Hanging Queen on h4 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/7q/8/8/6P1/6KR w - - 0 1", listOf("h1h5"), "Winning Hanging Queen on h5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/3q4/8/7P/3R2K1 w - - 0 1", listOf("d1d4"), "Winning Hanging Queen on d4 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/4q3/8/7P/4R1K1 w - - 0 1", listOf("e1e4"), "Winning Hanging Queen on e4 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/5q2/8/7P/5RK1 w - - 0 1", listOf("f1f4"), "Winning Hanging Queen on f4 with Rook"),
        RawTacticalPuzzle("4k3/8/8/8/7r/8/1B5P/4K3 w - - 0 1", listOf("b2f6", "e8d7", "f6h4"), "Bishop Fork from b2 Winning Hanging Rook"),
        RawTacticalPuzzle("4k3/8/8/1r6/8/8/P7/Q5K1 w - - 0 1", listOf("a1e5", "e8d7", "e5b5"), "Queen Fork from a1 Winning b5 Rook"),
        RawTacticalPuzzle("4k2r/8/8/4N3/8/8/7P/4K3 w - - 0 1", listOf("e5f7", "e8e7", "f7h8"), "Knight Fork from e5 Winning Rook on h8"),
        RawTacticalPuzzle("4k2r/8/8/6N1/8/8/7P/4K3 w - - 0 1", listOf("g5f7", "e8e7", "f7h8"), "Knight Fork from g5 Winning Rook on h8"),
        RawTacticalPuzzle("6k1/6pp/8/2q5/8/8/7P/2R3K1 w - - 0 1", listOf("c1c5"), "Winning Hanging Queen on c5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/3q4/8/8/7P/3R2K1 w - - 0 1", listOf("d1d5"), "Winning Hanging Queen on d5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/4q3/8/8/7P/4R1K1 w - - 0 1", listOf("e1e5"), "Winning Hanging Queen on e5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/5q2/8/8/7P/5RK1 w - - 0 1", listOf("f1f5"), "Winning Hanging Queen on f5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/q7/8/2P5/R5K1 w - - 0 1", listOf("a1a4"), "Winning Hanging Queen on a4 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/7q/8/2P5/6KR w - - 0 1", listOf("h1h4"), "Winning Hanging Queen on h4 with Rook"),
        RawTacticalPuzzle("4k3/8/8/8/7r/8/1B3P2/4K3 w - - 0 1", listOf("b2f6", "e8d7", "f6h4"), "Bishop Fork from b2 Winning Hanging Rook"),
        RawTacticalPuzzle("4k3/8/8/1r6/8/8/2P5/Q5K1 w - - 0 1", listOf("a1e5", "e8d7", "e5b5"), "Queen Fork from a1 Winning b5 Rook"),
        RawTacticalPuzzle("4k2r/8/8/4N3/8/8/6P1/4K3 w - - 0 1", listOf("e5f7", "e8e7", "f7h8"), "Knight Fork from e5 Winning Rook on h8"),
        RawTacticalPuzzle("4k2r/8/8/6N1/8/8/6P1/4K3 w - - 0 1", listOf("g5f7", "e8e7", "f7h8"), "Knight Fork from g5 Winning Rook on h8"),
        RawTacticalPuzzle("6k1/6pp/8/2q5/8/8/6P1/2R3K1 w - - 0 1", listOf("c1c5"), "Winning Hanging Queen on c5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/3q4/8/8/6P1/3R2K1 w - - 0 1", listOf("d1d5"), "Winning Hanging Queen on d5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/4q3/8/8/6P1/4R1K1 w - - 0 1", listOf("e1e5"), "Winning Hanging Queen on e5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/5q2/8/8/6P1/5RK1 w - - 0 1", listOf("f1f5"), "Winning Hanging Queen on f5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/q7/8/8/2P5/R5K1 w - - 0 1", listOf("a1a5"), "Winning Hanging Queen on a5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/4q3/8/6P1/4R1K1 w - - 0 1", listOf("e1e4"), "Winning Hanging Queen on e4 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/5q2/8/6P1/5RK1 w - - 0 1", listOf("f1f4"), "Winning Hanging Queen on f4 with Rook"),
        RawTacticalPuzzle("4k3/8/8/8/7r/8/1BP5/4K3 w - - 0 1", listOf("b2f6", "e8d7", "f6h4"), "Bishop Fork from b2 Winning Hanging Rook"),
        RawTacticalPuzzle("6k1/6pp/8/4q3/8/8/2P5/4R1K1 w - - 0 1", listOf("e1e5"), "Winning Hanging Queen on e5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/5q2/8/8/2P5/5RK1 w - - 0 1", listOf("f1f5"), "Winning Hanging Queen on f5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/1q6/8/2P5/1R4K1 w - - 0 1", listOf("b1b4"), "Winning Hanging Queen on b4 with Rook"),
        RawTacticalPuzzle("4k3/8/8/1r6/8/8/5P2/Q5K1 w - - 0 1", listOf("a1e5", "e8d7", "e5b5"), "Queen Fork from a1 Winning b5 Rook"),
        RawTacticalPuzzle("6k1/6pp/8/1q6/8/8/2P5/1R4K1 w - - 0 1", listOf("b1b5"), "Winning Hanging Queen on b5 with Rook"),
        RawTacticalPuzzle("4k3/8/8/2n5/8/8/P7/2R3K1 w - - 0 1", listOf("c1c5"), "Rook Capture on c5 Winning Free Knight"),
        RawTacticalPuzzle("6k1/6pp/8/3q4/8/8/2P5/3R2K1 w - - 0 1", listOf("d1d5"), "Winning Hanging Queen on d5 with Rook"),
        RawTacticalPuzzle("4k3/8/8/4n3/8/8/P7/4R1K1 w - - 0 1", listOf("e1e5"), "Rook Capture on e5 Winning Free Knight"),
        RawTacticalPuzzle("4k3/8/8/5n2/8/8/P7/5RK1 w - - 0 1", listOf("f1f5"), "Rook Capture on f5 Winning Free Knight"),
        RawTacticalPuzzle("6k1/6pp/8/8/2q5/8/6P1/2R3K1 w - - 0 1", listOf("c1c4"), "Winning Hanging Queen on c4 with Rook"),
        RawTacticalPuzzle("4k3/8/8/4n3/8/8/1P6/4R1K1 w - - 0 1", listOf("e1e5"), "Rook Capture on e5 Winning Free Knight"),
        RawTacticalPuzzle("4k3/8/8/5n2/8/8/1P6/5RK1 w - - 0 1", listOf("f1f5"), "Rook Capture on f5 Winning Free Knight"),
        RawTacticalPuzzle("6k1/6pp/8/7q/8/8/2P5/6KR w - - 0 1", listOf("h1h5"), "Winning Hanging Queen on h5 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/3q4/8/6P1/3R2K1 w - - 0 1", listOf("d1d4"), "Winning Hanging Queen on d4 with Rook"),
        RawTacticalPuzzle("6k1/6pp/8/8/4q3/8/2P5/4R1K1 w - - 0 1", listOf("e1e4"), "Winning Hanging Queen on e4 with Rook"),
        RawTacticalPuzzle("4k2r/8/8/6N1/8/8/2P5/4K3 w - - 0 1", listOf("g5f7", "e8e7", "f7h8"), "Knight Fork from g5 Winning Rook on h8"),
        RawTacticalPuzzle("4k3/8/8/2n5/8/8/1P6/2R3K1 w - - 0 1", listOf("c1c5"), "Rook Capture on c5 Winning Free Knight"),
        RawTacticalPuzzle("4k3/8/8/3n4/8/8/P7/3R2K1 w - - 0 1", listOf("d1d5"), "Rook Capture on d5 Winning Free Knight"),
        RawTacticalPuzzle("4k3/8/8/4n3/8/8/6P1/4R1K1 w - - 0 1", listOf("e1e5"), "Rook Capture on e5 Winning Free Knight"),
        RawTacticalPuzzle("4k3/8/8/5n2/8/8/6P1/5RK1 w - - 0 1", listOf("f1f5"), "Rook Capture on f5 Winning Free Knight"),
        RawTacticalPuzzle("6k1/6pp/8/8/3q4/8/2P5/3R2K1 w - - 0 1", listOf("d1d4"), "Winning Hanging Queen on d4 with Rook"),
        RawTacticalPuzzle("4k3/8/8/4n3/8/8/7P/4R1K1 w - - 0 1", listOf("e1e5"), "Rook Capture on e5 Winning Free Knight"),
        RawTacticalPuzzle("6k1/6pp/8/8/5q2/8/2P5/5RK1 w - - 0 1", listOf("f1f4"), "Winning Hanging Queen on f4 with Rook"),
        RawTacticalPuzzle("4k2r/8/8/6N1/8/8/5P2/4K3 w - - 0 1", listOf("g5f7", "e8e7", "f7h8"), "Knight Fork from g5 Winning Rook on h8"),
        RawTacticalPuzzle("4k2r/8/8/4N3/8/8/2P5/4K3 w - - 0 1", listOf("e5f7", "e8e7", "f7h8"), "Knight Fork from e5 Winning Rook on h8"),
        RawTacticalPuzzle("4k3/8/8/2n5/8/8/6P1/2R3K1 w - - 0 1", listOf("c1c5"), "Rook Capture on c5 Winning Free Knight"),
        RawTacticalPuzzle("4k3/8/8/3n4/8/8/1P6/3R2K1 w - - 0 1", listOf("d1d5"), "Rook Capture on d5 Winning Free Knight"),
        RawTacticalPuzzle("4k3/8/8/5n2/8/8/7P/5RK1 w - - 0 1", listOf("f1f5"), "Rook Capture on f5 Winning Free Knight"),
        RawTacticalPuzzle("4k2r/8/8/4N3/8/8/5P2/4K3 w - - 0 1", listOf("e5f7", "e8e7", "f7h8"), "Knight Fork from e5 Winning Rook on h8"),
        RawTacticalPuzzle("4k3/8/8/3n4/8/8/6P1/3R2K1 w - - 0 1", listOf("d1d5"), "Rook Capture on d5 Winning Free Knight"),
        RawTacticalPuzzle("4k3/8/8/2n5/8/8/7P/2R3K1 w - - 0 1", listOf("c1c5"), "Rook Capture on c5 Winning Free Knight"),
        RawTacticalPuzzle("4k3/8/8/3n4/8/8/7P/3R2K1 w - - 0 1", listOf("d1d5"), "Rook Capture on d5 Winning Free Knight")
    )

    private var cachedPuzzles: List<Puzzle>? = null

    fun getPuzzles(): List<Puzzle> {
        cachedPuzzles?.let { return it }
        val puzzles = (1..100).map { i ->
            val base = pool[i - 1]
            val tierRating = when {
                i <= 35 -> 650 + (i * 10)
                i <= 70 -> 1100 + ((i - 35) * 15)
                else -> 1700 + ((i - 70) * 20)
            }
            val cleanTheme = "Winning Material"
            val idPrefix = cleanTheme.lowercase().replace(" ", "_")
            Puzzle(
                id = "tac_${idPrefix}_$i",
                fen = base.fen,
                solutionMoves = base.solutionMoves,
                rating = tierRating,
                theme = "$cleanTheme - ${base.motif}",
                xpReward = 15 + (i / 10),
                coinsReward = 8 + (i / 20)
            )
        }
        cachedPuzzles = puzzles
        return puzzles
    }
}
