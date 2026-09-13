package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object AttractionDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("3qk3/5p2/8/8/2B5/5N2/8/K7 w - - 0 1", listOf("c4f7", "e8f7", "f3e5"), "Attraction Sacrifice on f7 into e5 Knight Jump"),
        RawTacticalPuzzle("3qk3/5p2/8/8/2B5/7N/8/K7 w - - 0 1", listOf("c4f7", "e8f7", "h3g5"), "Attraction Sacrifice on f7 into g5 Knight Jump"),
        RawTacticalPuzzle("3qk3/5p2/8/8/2BN4/8/8/K7 w - - 0 1", listOf("c4f7", "e8f7", "d4e6"), "Attraction Sacrifice on f7 into e6 Knight Jump"),
        RawTacticalPuzzle("3qk3/5p2/8/8/2B2N2/8/8/K7 w - - 0 1", listOf("c4f7", "e8f7", "f4e6"), "Attraction Sacrifice on f7 into e6 Knight Jump"),
        RawTacticalPuzzle("3qk3/5p2/8/3B4/8/5N2/8/K7 w - - 0 1", listOf("d5f7", "e8f7", "f3e5"), "Attraction Sacrifice on f7 into e5 Knight Jump"),
        RawTacticalPuzzle("3qk3/5p2/8/3B4/8/7N/8/K7 w - - 0 1", listOf("d5f7", "e8f7", "h3g5"), "Attraction Sacrifice on f7 into g5 Knight Jump"),
        RawTacticalPuzzle("3qk3/5p2/8/3B4/3N4/8/8/K7 w - - 0 1", listOf("d5f7", "e8f7", "d4e6"), "Attraction Sacrifice on f7 into e6 Knight Jump"),
        RawTacticalPuzzle("3qk3/5p2/8/3B4/5N2/8/8/K7 w - - 0 1", listOf("d5f7", "e8f7", "f4e6"), "Attraction Sacrifice on f7 into e6 Knight Jump"),
        RawTacticalPuzzle("3qk3/5p2/4B3/8/8/5N2/8/K7 w - - 0 1", listOf("e6f7", "e8f7", "f3e5"), "Attraction Sacrifice on f7 into e5 Knight Jump"),
        RawTacticalPuzzle("3qk3/5p2/4B3/8/8/7N/8/K7 w - - 0 1", listOf("e6f7", "e8f7", "h3g5"), "Attraction Sacrifice on f7 into g5 Knight Jump"),
        RawTacticalPuzzle("3qk3/5p2/4B3/8/3N4/8/8/K7 w - - 0 1", listOf("e6f7", "e8f7", "d4e6"), "Attraction Sacrifice on f7 into e6 Knight Jump"),
        RawTacticalPuzzle("3qk3/5p2/4B3/8/5N2/8/8/K7 w - - 0 1", listOf("e6f7", "e8f7", "f4e6"), "Attraction Sacrifice on f7 into e6 Knight Jump"),
        RawTacticalPuzzle("3qk3/5p2/8/8/8/1B3N2/8/K7 w - - 0 1", listOf("b3f7", "e8f7", "f3e5"), "Attraction Sacrifice on f7 into e5 Knight Jump"),
        RawTacticalPuzzle("3qk3/5p2/8/8/8/1B5N/8/K7 w - - 0 1", listOf("b3f7", "e8f7", "h3g5"), "Attraction Sacrifice on f7 into g5 Knight Jump"),
        RawTacticalPuzzle("3qk3/5p2/8/8/3N4/1B6/8/K7 w - - 0 1", listOf("b3f7", "e8f7", "d4e6"), "Attraction Sacrifice on f7 into e6 Knight Jump"),
        RawTacticalPuzzle("3qk3/5p2/8/8/5N2/1B6/8/K7 w - - 0 1", listOf("b3f7", "e8f7", "f4e6"), "Attraction Sacrifice on f7 into e6 Knight Jump"),
        RawTacticalPuzzle("3qk3/5p2/8/8/8/5N2/B7/K7 w - - 0 1", listOf("a2f7", "e8f7", "f3e5"), "Attraction Sacrifice on f7 into e5 Knight Jump"),
        RawTacticalPuzzle("3qk3/5p2/8/8/8/7N/B7/K7 w - - 0 1", listOf("a2f7", "e8f7", "h3g5"), "Attraction Sacrifice on f7 into g5 Knight Jump"),
        RawTacticalPuzzle("3qk3/5p2/8/8/3N4/8/B7/K7 w - - 0 1", listOf("a2f7", "e8f7", "d4e6"), "Attraction Sacrifice on f7 into e6 Knight Jump"),
        RawTacticalPuzzle("3qk3/5p2/8/8/5N2/8/B7/K7 w - - 0 1", listOf("a2f7", "e8f7", "f4e6"), "Attraction Sacrifice on f7 into e6 Knight Jump"),
        RawTacticalPuzzle("3q2k1/7p/8/8/8/3B1N2/8/K7 w - - 0 1", listOf("d3h7", "g8h7", "f3g5"), "Attraction Sacrifice on h7 into g5 Knight Jump"),
        RawTacticalPuzzle("3q2k1/7p/8/8/8/3B3N/8/K7 w - - 0 1", listOf("d3h7", "g8h7", "h3g5"), "Attraction Sacrifice on h7 into g5 Knight Jump"),
        RawTacticalPuzzle("3q2k1/7p/8/8/8/5N2/2B5/K7 w - - 0 1", listOf("c2h7", "g8h7", "f3g5"), "Attraction Sacrifice on h7 into g5 Knight Jump"),
        RawTacticalPuzzle("3q2k1/7p/8/8/8/7N/2B5/K7 w - - 0 1", listOf("c2h7", "g8h7", "h3g5"), "Attraction Sacrifice on h7 into g5 Knight Jump"),
        RawTacticalPuzzle("3q2k1/7p/8/8/8/5N2/8/KB6 w - - 0 1", listOf("b1h7", "g8h7", "f3g5"), "Attraction Sacrifice on h7 into g5 Knight Jump"),
        RawTacticalPuzzle("3q2k1/7p/8/8/8/7N/8/KB6 w - - 0 1", listOf("b1h7", "g8h7", "h3g5"), "Attraction Sacrifice on h7 into g5 Knight Jump"),
        RawTacticalPuzzle("3q2k1/7p/8/8/4B3/5N2/8/K7 w - - 0 1", listOf("e4h7", "g8h7", "f3g5"), "Attraction Sacrifice on h7 into g5 Knight Jump"),
        RawTacticalPuzzle("3q2k1/7p/8/8/4B3/7N/8/K7 w - - 0 1", listOf("e4h7", "g8h7", "h3g5"), "Attraction Sacrifice on h7 into g5 Knight Jump"),
        RawTacticalPuzzle("3q2k1/7p/8/5B2/8/5N2/8/K7 w - - 0 1", listOf("f5h7", "g8h7", "f3g5"), "Attraction Sacrifice on h7 into g5 Knight Jump"),
        RawTacticalPuzzle("3q2k1/7p/8/5B2/8/7N/8/K7 w - - 0 1", listOf("f5h7", "g8h7", "h3g5"), "Attraction Sacrifice on h7 into g5 Knight Jump"),
        RawTacticalPuzzle("3q2k1/7p/8/5B2/4N3/8/8/K7 w - - 0 1", listOf("f5h7", "g8h7", "e4g5"), "Attraction Sacrifice on h7 into g5 Knight Jump"),
        RawTacticalPuzzle("3q2k1/7p/6B1/8/8/5N2/8/K7 w - - 0 1", listOf("g6h7", "g8h7", "f3g5"), "Attraction Sacrifice on h7 into g5 Knight Jump"),
        RawTacticalPuzzle("3q2k1/7p/6B1/8/8/7N/8/K7 w - - 0 1", listOf("g6h7", "g8h7", "h3g5"), "Attraction Sacrifice on h7 into g5 Knight Jump"),
        RawTacticalPuzzle("3q2k1/7p/6B1/8/4N3/8/8/K7 w - - 0 1", listOf("g6h7", "g8h7", "e4g5"), "Attraction Sacrifice on h7 into g5 Knight Jump"),
        RawTacticalPuzzle("1rk5/8/8/8/8/8/8/QR5K w - - 0 1", listOf("b1b8", "c8b8", "a1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/8/8/8/1R1Q3K w - - 0 1", listOf("b1b8", "c8b8", "d1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/8/8/8/1R2Q2K w - - 0 1", listOf("b1b8", "c8b8", "e1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/8/8/8/1R3Q1K w - - 0 1", listOf("b1b8", "c8b8", "f1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/8/8/8/1R4QK w - - 0 1", listOf("b1b8", "c8b8", "g1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/Q7/8/8/1R5K w - - 0 1", listOf("b1b8", "c8b8", "a4b4"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/3Q4/8/8/1R5K w - - 0 1", listOf("b1b8", "c8b8", "d4a4"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/4Q3/8/8/1R5K w - - 0 1", listOf("b1b8", "c8b8", "e4b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/Q7/8/8/8/1R5K w - - 0 1", listOf("b1b8", "c8b8", "a5a4"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/3Q4/8/8/8/1R5K w - - 0 1", listOf("b1b8", "c8b8", "d5c5"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/8/8/1R6/Q6K w - - 0 1", listOf("b2b8", "c8b8", "a1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/8/8/1R6/3Q3K w - - 0 1", listOf("b2b8", "c8b8", "d1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/8/8/1R6/4Q2K w - - 0 1", listOf("b2b8", "c8b8", "e1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/8/8/1R6/5Q1K w - - 0 1", listOf("b2b8", "c8b8", "f1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/8/8/1R6/6QK w - - 0 1", listOf("b2b8", "c8b8", "g1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/Q7/8/1R6/7K w - - 0 1", listOf("b2b8", "c8b8", "a4b4"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/3Q4/8/1R6/7K w - - 0 1", listOf("b2b8", "c8b8", "d4a4"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/4Q3/8/1R6/7K w - - 0 1", listOf("b2b8", "c8b8", "e4b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/Q7/8/8/1R6/7K w - - 0 1", listOf("b2b8", "c8b8", "a5a4"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/3Q4/8/8/1R6/7K w - - 0 1", listOf("b2b8", "c8b8", "d5c5"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/8/1R6/8/Q6K w - - 0 1", listOf("b3b8", "c8b8", "a1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/8/1R6/8/3Q3K w - - 0 1", listOf("b3b8", "c8b8", "d1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/8/1R6/8/4Q2K w - - 0 1", listOf("b3b8", "c8b8", "e1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/8/1R6/8/5Q1K w - - 0 1", listOf("b3b8", "c8b8", "f1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/8/1R6/8/6QK w - - 0 1", listOf("b3b8", "c8b8", "g1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/Q7/1R6/8/7K w - - 0 1", listOf("b3b8", "c8b8", "a4b4"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/3Q4/1R6/8/7K w - - 0 1", listOf("b3b8", "c8b8", "d4a4"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/4Q3/1R6/8/7K w - - 0 1", listOf("b3b8", "c8b8", "e4b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/Q7/8/1R6/8/7K w - - 0 1", listOf("b3b8", "c8b8", "a5a4"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/3Q4/8/1R6/8/7K w - - 0 1", listOf("b3b8", "c8b8", "d5c5"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/1R6/8/8/Q6K w - - 0 1", listOf("b4b8", "c8b8", "a1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/1R6/8/8/3Q3K w - - 0 1", listOf("b4b8", "c8b8", "d1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/1R6/8/8/4Q2K w - - 0 1", listOf("b4b8", "c8b8", "e1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/1R6/8/8/5Q1K w - - 0 1", listOf("b4b8", "c8b8", "f1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/1R6/8/8/6QK w - - 0 1", listOf("b4b8", "c8b8", "g1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/QR6/8/8/7K w - - 0 1", listOf("b4b8", "c8b8", "a4b4"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/1R1Q4/8/8/7K w - - 0 1", listOf("b4b8", "c8b8", "d4a4"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/8/1R2Q3/8/8/7K w - - 0 1", listOf("b4b8", "c8b8", "e4b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/Q7/1R6/8/8/7K w - - 0 1", listOf("b4b8", "c8b8", "a5a4"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/3Q4/1R6/8/8/7K w - - 0 1", listOf("b4b8", "c8b8", "d5c5"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/1R6/8/8/8/Q6K w - - 0 1", listOf("b5b8", "c8b8", "a1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/1R6/8/8/8/3Q3K w - - 0 1", listOf("b5b8", "c8b8", "d1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/1R6/8/8/8/4Q2K w - - 0 1", listOf("b5b8", "c8b8", "e1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/1R6/8/8/8/5Q1K w - - 0 1", listOf("b5b8", "c8b8", "f1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/1R6/8/8/8/6QK w - - 0 1", listOf("b5b8", "c8b8", "g1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/1R6/Q7/8/8/7K w - - 0 1", listOf("b5b8", "c8b8", "a4b4"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/1R6/3Q4/8/8/7K w - - 0 1", listOf("b5b8", "c8b8", "d4a4"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/1R6/4Q3/8/8/7K w - - 0 1", listOf("b5b8", "c8b8", "e4b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/QR6/8/8/8/7K w - - 0 1", listOf("b5b8", "c8b8", "a5a4"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/8/1R1Q4/8/8/8/7K w - - 0 1", listOf("b5b8", "c8b8", "d5c5"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/1R6/8/8/8/8/Q6K w - - 0 1", listOf("b6b8", "c8b8", "a1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/1R6/8/8/8/8/3Q3K w - - 0 1", listOf("b6b8", "c8b8", "d1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/1R6/8/8/8/8/4Q2K w - - 0 1", listOf("b6b8", "c8b8", "e1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/1R6/8/8/8/8/5Q1K w - - 0 1", listOf("b6b8", "c8b8", "f1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/1R6/8/8/8/8/6QK w - - 0 1", listOf("b6b8", "c8b8", "g1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/1R6/8/Q7/8/8/7K w - - 0 1", listOf("b6b8", "c8b8", "a4b4"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/1R6/8/3Q4/8/8/7K w - - 0 1", listOf("b6b8", "c8b8", "d4a4"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/1R6/8/4Q3/8/8/7K w - - 0 1", listOf("b6b8", "c8b8", "e4b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/1R6/Q7/8/8/8/7K w - - 0 1", listOf("b6b8", "c8b8", "a5a4"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/8/1R6/3Q4/8/8/8/7K w - - 0 1", listOf("b6b8", "c8b8", "d5c5"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/1R6/8/8/8/8/8/Q6K w - - 0 1", listOf("b7b8", "c8b8", "a1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/1R6/8/8/8/8/8/3Q3K w - - 0 1", listOf("b7b8", "c8b8", "d1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/1R6/8/8/8/8/8/4Q2K w - - 0 1", listOf("b7b8", "c8b8", "e1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/1R6/8/8/8/8/8/5Q1K w - - 0 1", listOf("b7b8", "c8b8", "f1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/1R6/8/8/8/8/8/6QK w - - 0 1", listOf("b7b8", "c8b8", "g1b1"), "Attraction Rook Sacrifice on b8"),
        RawTacticalPuzzle("1rk5/1R6/8/8/Q7/8/8/7K w - - 0 1", listOf("b7b8", "c8b8", "a4b4"), "Attraction Rook Sacrifice on b8")
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
            val cleanTheme = "Attraction"
            val idPrefix = cleanTheme.lowercase().replace(" ", "_")
            Puzzle(
                id = "tac_${idPrefix}_${i}",
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
