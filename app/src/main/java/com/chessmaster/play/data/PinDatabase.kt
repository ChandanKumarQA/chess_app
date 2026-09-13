package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object PinDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("4k3/p7/2n5/8/8/8/8/5BK1 w - - 0 1", listOf("f1b5", "a7a6", "b5c6"), "Bishop Pin on c6"),
        RawTacticalPuzzle("4k3/7p/2n5/8/8/8/8/5BK1 w - - 0 1", listOf("f1b5", "h7h6", "b5c6"), "Bishop Pin on c6"),
        RawTacticalPuzzle("4k3/6p1/2n5/8/8/8/8/5BK1 w - - 0 1", listOf("f1b5", "g7g6", "b5c6"), "Bishop Pin on c6"),
        RawTacticalPuzzle("4k3/1p6/2n5/8/8/8/8/5BK1 w - - 0 1", listOf("f1b5", "b7b6", "b5c6"), "Bishop Pin on c6"),
        RawTacticalPuzzle("4k3/p7/2n5/8/8/3B4/8/6K1 w - - 0 1", listOf("d3b5", "a7a6", "b5c6"), "Bishop Pin on c6"),
        RawTacticalPuzzle("4k3/7p/2n5/8/8/3B4/8/6K1 w - - 0 1", listOf("d3b5", "h7h6", "b5c6"), "Bishop Pin on c6"),
        RawTacticalPuzzle("4k3/6p1/2n5/8/8/3B4/8/6K1 w - - 0 1", listOf("d3b5", "g7g6", "b5c6"), "Bishop Pin on c6"),
        RawTacticalPuzzle("4k3/1p6/2n5/8/8/3B4/8/6K1 w - - 0 1", listOf("d3b5", "b7b6", "b5c6"), "Bishop Pin on c6"),
        RawTacticalPuzzle("4k3/p7/2n5/8/8/8/4B3/6K1 w - - 0 1", listOf("e2b5", "a7a6", "b5c6"), "Bishop Pin on c6"),
        RawTacticalPuzzle("4k3/7p/2n5/8/8/8/4B3/6K1 w - - 0 1", listOf("e2b5", "h7h6", "b5c6"), "Bishop Pin on c6"),
        RawTacticalPuzzle("4k3/6p1/2n5/8/8/8/4B3/6K1 w - - 0 1", listOf("e2b5", "g7g6", "b5c6"), "Bishop Pin on c6"),
        RawTacticalPuzzle("4k3/1p6/2n5/8/8/8/4B3/6K1 w - - 0 1", listOf("e2b5", "b7b6", "b5c6"), "Bishop Pin on c6"),
        RawTacticalPuzzle("4k3/p7/2n5/8/B7/8/8/6K1 w - - 0 1", listOf("a4b5", "a7a6", "b5c6"), "Bishop Pin on c6"),
        RawTacticalPuzzle("4k3/7p/2n5/8/B7/8/8/6K1 w - - 0 1", listOf("a4b5", "h7h6", "b5c6"), "Bishop Pin on c6"),
        RawTacticalPuzzle("4k3/6p1/2n5/8/B7/8/8/6K1 w - - 0 1", listOf("a4b5", "g7g6", "b5c6"), "Bishop Pin on c6"),
        RawTacticalPuzzle("4k3/1p6/2n5/8/B7/8/8/6K1 w - - 0 1", listOf("a4b5", "b7b6", "b5c6"), "Bishop Pin on c6"),
        RawTacticalPuzzle("4k3/p7/2n5/8/2B5/8/8/6K1 w - - 0 1", listOf("c4b5", "a7a6", "b5c6"), "Bishop Pin on c6"),
        RawTacticalPuzzle("4k3/7p/2n5/8/2B5/8/8/6K1 w - - 0 1", listOf("c4b5", "h7h6", "b5c6"), "Bishop Pin on c6"),
        RawTacticalPuzzle("4k3/6p1/2n5/8/2B5/8/8/6K1 w - - 0 1", listOf("c4b5", "g7g6", "b5c6"), "Bishop Pin on c6"),
        RawTacticalPuzzle("4k3/1p6/2n5/8/2B5/8/8/6K1 w - - 0 1", listOf("c4b5", "b7b6", "b5c6"), "Bishop Pin on c6"),
        RawTacticalPuzzle("4k3/p7/5n2/8/8/8/8/2B3K1 w - - 0 1", listOf("c1g5", "a7a6", "g5f6"), "Bishop Pin on f6"),
        RawTacticalPuzzle("4k3/7p/5n2/8/8/8/8/2B3K1 w - - 0 1", listOf("c1g5", "h7h6", "g5f6"), "Bishop Pin on f6"),
        RawTacticalPuzzle("4k3/6p1/5n2/8/8/8/8/2B3K1 w - - 0 1", listOf("c1g5", "g7g6", "g5f6"), "Bishop Pin on f6"),
        RawTacticalPuzzle("4k3/1p6/5n2/8/8/8/8/2B3K1 w - - 0 1", listOf("c1g5", "b7b6", "g5f6"), "Bishop Pin on f6"),
        RawTacticalPuzzle("4k3/p7/5n2/8/8/8/3B4/6K1 w - - 0 1", listOf("d2g5", "a7a6", "g5f6"), "Bishop Pin on f6"),
        RawTacticalPuzzle("4k3/7p/5n2/8/8/8/3B4/6K1 w - - 0 1", listOf("d2g5", "h7h6", "g5f6"), "Bishop Pin on f6"),
        RawTacticalPuzzle("4k3/6p1/5n2/8/8/8/3B4/6K1 w - - 0 1", listOf("d2g5", "g7g6", "g5f6"), "Bishop Pin on f6"),
        RawTacticalPuzzle("4k3/1p6/5n2/8/8/8/3B4/6K1 w - - 0 1", listOf("d2g5", "b7b6", "g5f6"), "Bishop Pin on f6"),
        RawTacticalPuzzle("4k3/p7/5n2/8/8/4B3/8/6K1 w - - 0 1", listOf("e3g5", "a7a6", "g5f6"), "Bishop Pin on f6"),
        RawTacticalPuzzle("4k3/7p/5n2/8/8/4B3/8/6K1 w - - 0 1", listOf("e3g5", "h7h6", "g5f6"), "Bishop Pin on f6"),
        RawTacticalPuzzle("4k3/6p1/5n2/8/8/4B3/8/6K1 w - - 0 1", listOf("e3g5", "g7g6", "g5f6"), "Bishop Pin on f6"),
        RawTacticalPuzzle("4k3/1p6/5n2/8/8/4B3/8/6K1 w - - 0 1", listOf("e3g5", "b7b6", "g5f6"), "Bishop Pin on f6"),
        RawTacticalPuzzle("4k3/p7/5n2/8/5B2/8/8/6K1 w - - 0 1", listOf("f4g5", "a7a6", "g5f6"), "Bishop Pin on f6"),
        RawTacticalPuzzle("4k3/7p/5n2/8/5B2/8/8/6K1 w - - 0 1", listOf("f4g5", "h7h6", "g5f6"), "Bishop Pin on f6"),
        RawTacticalPuzzle("4k3/6p1/5n2/8/5B2/8/8/6K1 w - - 0 1", listOf("f4g5", "g7g6", "g5f6"), "Bishop Pin on f6"),
        RawTacticalPuzzle("4k3/1p6/5n2/8/5B2/8/8/6K1 w - - 0 1", listOf("f4g5", "b7b6", "g5f6"), "Bishop Pin on f6"),
        RawTacticalPuzzle("4k3/p7/5n2/8/7B/8/8/6K1 w - - 0 1", listOf("h4g5", "a7a6", "g5f6"), "Bishop Pin on f6"),
        RawTacticalPuzzle("4k3/7p/5n2/8/7B/8/8/6K1 w - - 0 1", listOf("h4g5", "h7h6", "g5f6"), "Bishop Pin on f6"),
        RawTacticalPuzzle("4k3/6p1/5n2/8/7B/8/8/6K1 w - - 0 1", listOf("h4g5", "g7g6", "g5f6"), "Bishop Pin on f6"),
        RawTacticalPuzzle("4k3/1p6/5n2/8/7B/8/8/6K1 w - - 0 1", listOf("h4g5", "b7b6", "g5f6"), "Bishop Pin on f6"),
        RawTacticalPuzzle("4k3/p3q3/8/8/8/8/8/R5K1 w - - 0 1", listOf("a1e1", "a7a6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/4q2p/8/8/8/8/8/R5K1 w - - 0 1", listOf("a1e1", "h7h6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/4q1p1/8/8/8/8/8/R5K1 w - - 0 1", listOf("a1e1", "g7g6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/1p2q3/8/8/8/8/8/R5K1 w - - 0 1", listOf("a1e1", "b7b6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/p3q3/8/8/8/8/8/1R4K1 w - - 0 1", listOf("b1e1", "a7a6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/4q2p/8/8/8/8/8/1R4K1 w - - 0 1", listOf("b1e1", "h7h6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/4q1p1/8/8/8/8/8/1R4K1 w - - 0 1", listOf("b1e1", "g7g6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/1p2q3/8/8/8/8/8/1R4K1 w - - 0 1", listOf("b1e1", "b7b6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/p3q3/8/8/8/8/8/2R3K1 w - - 0 1", listOf("c1e1", "a7a6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/4q2p/8/8/8/8/8/2R3K1 w - - 0 1", listOf("c1e1", "h7h6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/4q1p1/8/8/8/8/8/2R3K1 w - - 0 1", listOf("c1e1", "g7g6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/1p2q3/8/8/8/8/8/2R3K1 w - - 0 1", listOf("c1e1", "b7b6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/p3q3/8/8/8/8/8/3R2K1 w - - 0 1", listOf("d1e1", "a7a6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/4q2p/8/8/8/8/8/3R2K1 w - - 0 1", listOf("d1e1", "h7h6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/4q1p1/8/8/8/8/8/3R2K1 w - - 0 1", listOf("d1e1", "g7g6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/1p2q3/8/8/8/8/8/3R2K1 w - - 0 1", listOf("d1e1", "b7b6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/p3q3/8/8/8/8/8/5RK1 w - - 0 1", listOf("f1e1", "a7a6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/4q2p/8/8/8/8/8/5RK1 w - - 0 1", listOf("f1e1", "h7h6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/4q1p1/8/8/8/8/8/5RK1 w - - 0 1", listOf("f1e1", "g7g6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/1p2q3/8/8/8/8/8/5RK1 w - - 0 1", listOf("f1e1", "b7b6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/p3q3/8/8/8/8/8/6R1 w - - 0 1", listOf("g1e1", "a7a6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/4q2p/8/8/8/8/8/6R1 w - - 0 1", listOf("g1e1", "h7h6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/4q1p1/8/8/8/8/8/6R1 w - - 0 1", listOf("g1e1", "g7g6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("4k3/1p2q3/8/8/8/8/8/6R1 w - - 0 1", listOf("g1e1", "b7b6", "e1e7"), "Rook Pin on e7"),
        RawTacticalPuzzle("3k4/p2q4/8/8/8/8/8/R5K1 w - - 0 1", listOf("a1d1", "a7a6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/3q3p/8/8/8/8/8/R5K1 w - - 0 1", listOf("a1d1", "h7h6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/3q2p1/8/8/8/8/8/R5K1 w - - 0 1", listOf("a1d1", "g7g6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/3q1p2/8/8/8/8/8/R5K1 w - - 0 1", listOf("a1d1", "f7f6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/p2q4/8/8/8/8/8/1R4K1 w - - 0 1", listOf("b1d1", "a7a6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/3q3p/8/8/8/8/8/1R4K1 w - - 0 1", listOf("b1d1", "h7h6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/3q2p1/8/8/8/8/8/1R4K1 w - - 0 1", listOf("b1d1", "g7g6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/3q1p2/8/8/8/8/8/1R4K1 w - - 0 1", listOf("b1d1", "f7f6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/p2q4/8/8/8/8/8/2R3K1 w - - 0 1", listOf("c1d1", "a7a6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/3q3p/8/8/8/8/8/2R3K1 w - - 0 1", listOf("c1d1", "h7h6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/3q2p1/8/8/8/8/8/2R3K1 w - - 0 1", listOf("c1d1", "g7g6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/3q1p2/8/8/8/8/8/2R3K1 w - - 0 1", listOf("c1d1", "f7f6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/p2q4/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1d1", "a7a6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/3q3p/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1d1", "h7h6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/3q2p1/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1d1", "g7g6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/3q1p2/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1d1", "f7f6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/p2q4/8/8/8/8/8/5RK1 w - - 0 1", listOf("f1d1", "a7a6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/3q3p/8/8/8/8/8/5RK1 w - - 0 1", listOf("f1d1", "h7h6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/3q2p1/8/8/8/8/8/5RK1 w - - 0 1", listOf("f1d1", "g7g6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/3q1p2/8/8/8/8/8/5RK1 w - - 0 1", listOf("f1d1", "f7f6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/p2q4/8/8/8/8/8/6R1 w - - 0 1", listOf("g1d1", "a7a6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/3q3p/8/8/8/8/8/6R1 w - - 0 1", listOf("g1d1", "h7h6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/3q2p1/8/8/8/8/8/6R1 w - - 0 1", listOf("g1d1", "g7g6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("3k4/3q1p2/8/8/8/8/8/6R1 w - - 0 1", listOf("g1d1", "f7f6", "d1d7"), "Rook Pin on d7"),
        RawTacticalPuzzle("2k5/p1q5/8/8/8/8/8/R5K1 w - - 0 1", listOf("a1c1", "a7a6", "c1c7"), "Rook Pin on c7"),
        RawTacticalPuzzle("2k5/2q4p/8/8/8/8/8/R5K1 w - - 0 1", listOf("a1c1", "h7h6", "c1c7"), "Rook Pin on c7"),
        RawTacticalPuzzle("2k5/2q3p1/8/8/8/8/8/R5K1 w - - 0 1", listOf("a1c1", "g7g6", "c1c7"), "Rook Pin on c7"),
        RawTacticalPuzzle("2k5/2q2p2/8/8/8/8/8/R5K1 w - - 0 1", listOf("a1c1", "f7f6", "c1c7"), "Rook Pin on c7"),
        RawTacticalPuzzle("2k5/p1q5/8/8/8/8/8/1R4K1 w - - 0 1", listOf("b1c1", "a7a6", "c1c7"), "Rook Pin on c7"),
        RawTacticalPuzzle("2k5/2q4p/8/8/8/8/8/1R4K1 w - - 0 1", listOf("b1c1", "h7h6", "c1c7"), "Rook Pin on c7"),
        RawTacticalPuzzle("2k5/2q3p1/8/8/8/8/8/1R4K1 w - - 0 1", listOf("b1c1", "g7g6", "c1c7"), "Rook Pin on c7"),
        RawTacticalPuzzle("2k5/2q2p2/8/8/8/8/8/1R4K1 w - - 0 1", listOf("b1c1", "f7f6", "c1c7"), "Rook Pin on c7"),
        RawTacticalPuzzle("2k5/p1q5/8/8/8/8/8/3R2K1 w - - 0 1", listOf("d1c1", "a7a6", "c1c7"), "Rook Pin on c7"),
        RawTacticalPuzzle("2k5/2q4p/8/8/8/8/8/3R2K1 w - - 0 1", listOf("d1c1", "h7h6", "c1c7"), "Rook Pin on c7"),
        RawTacticalPuzzle("2k5/2q3p1/8/8/8/8/8/3R2K1 w - - 0 1", listOf("d1c1", "g7g6", "c1c7"), "Rook Pin on c7"),
        RawTacticalPuzzle("2k5/2q2p2/8/8/8/8/8/3R2K1 w - - 0 1", listOf("d1c1", "f7f6", "c1c7"), "Rook Pin on c7")
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
            val cleanTheme = "Pin"
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
