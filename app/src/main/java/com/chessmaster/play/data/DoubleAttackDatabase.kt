package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object DoubleAttackDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("r3k3/8/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1a4", "e8d8", "a4a8"), "Queen Double Attack from d1 to a4"),
        RawTacticalPuzzle("4k3/r7/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1a4", "e8d8", "a4a7"), "Queen Double Attack from d1 to a4"),
        RawTacticalPuzzle("4k3/8/r7/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1a4", "e8d8", "a4a6"), "Queen Double Attack from d1 to a4"),
        RawTacticalPuzzle("r4k2/8/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1a4", "f8g8", "a4a8"), "Queen Double Attack from d1 to a4"),
        RawTacticalPuzzle("5k2/r7/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1a4", "f8g8", "a4a7"), "Queen Double Attack from d1 to a4"),
        RawTacticalPuzzle("5k2/8/r7/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1a4", "f8g8", "a4a6"), "Queen Double Attack from d1 to a4"),
        RawTacticalPuzzle("r1k5/8/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1a4", "c8b8", "a4a8"), "Queen Double Attack from d1 to a4"),
        RawTacticalPuzzle("2k5/r7/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1a4", "c8b8", "a4a7"), "Queen Double Attack from d1 to a4"),
        RawTacticalPuzzle("2k5/8/r7/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1a4", "c8b8", "a4a6"), "Queen Double Attack from d1 to a4"),
        RawTacticalPuzzle("4k2r/8/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1d4", "e8f8", "d4h8"), "Queen Double Attack from d1 to d4"),
        RawTacticalPuzzle("5k1r/8/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1d4", "f8g8", "d4h8"), "Queen Double Attack from d1 to d4"),
        RawTacticalPuzzle("2k4r/8/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1d4", "c8b8", "d4h8"), "Queen Double Attack from d1 to d4"),
        RawTacticalPuzzle("2r1k3/8/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1g4", "e8d8", "g4c8"), "Queen Double Attack from d1 to g4"),
        RawTacticalPuzzle("4k1r1/8/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1g4", "e8d8", "g4g8"), "Queen Double Attack from d1 to g4"),
        RawTacticalPuzzle("4k3/6r1/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1g4", "e8d8", "g4g7"), "Queen Double Attack from d1 to g4"),
        RawTacticalPuzzle("5k2/6r1/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1g4", "f8g8", "g4g7"), "Queen Double Attack from d1 to g4"),
        RawTacticalPuzzle("2r5/8/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1g4", "c8b8", "g4c8"), "Queen Double Attack from d1 to g4"),
        RawTacticalPuzzle("2k3r1/8/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1g4", "c8b8", "g4g8"), "Queen Double Attack from d1 to g4"),
        RawTacticalPuzzle("2k5/6r1/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1g4", "c8b8", "g4g7"), "Queen Double Attack from d1 to g4"),
        RawTacticalPuzzle("4k3/1r6/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1d5", "e8f8", "d5b7"), "Queen Double Attack from d1 to d5"),
        RawTacticalPuzzle("2k5/1r6/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1d5", "c8b8", "d5b7"), "Queen Double Attack from d1 to d5"),
        RawTacticalPuzzle("4k3/7r/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1d7", "e8f8", "d7h7"), "Queen Double Attack from d1 to d7"),
        RawTacticalPuzzle("2r2k2/8/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1d7", "f8g8", "d7c8"), "Queen Double Attack from d1 to d7"),
        RawTacticalPuzzle("5k2/1r6/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1d7", "f8g8", "d7b7"), "Queen Double Attack from d1 to d7"),
        RawTacticalPuzzle("5k2/7r/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1d7", "f8g8", "d7h7"), "Queen Double Attack from d1 to d7"),
        RawTacticalPuzzle("2k5/7r/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1d7", "c8b8", "d7h7"), "Queen Double Attack from d1 to d7"),
        RawTacticalPuzzle("4k3/8/7r/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1h5", "e8d8", "h5h6"), "Queen Double Attack from d1 to h5"),
        RawTacticalPuzzle("5k2/8/7r/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1h5", "f8g8", "h5h6"), "Queen Double Attack from d1 to h5"),
        RawTacticalPuzzle("2k5/8/7r/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1h5", "c8b8", "h5h6"), "Queen Double Attack from d1 to h5"),
        RawTacticalPuzzle("1r1k4/8/8/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1b4", "d8c8", "b4b8"), "Queen Double Attack from e1 to b4"),
        RawTacticalPuzzle("3k4/1r6/8/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1b4", "d8c8", "b4b7"), "Queen Double Attack from e1 to b4"),
        RawTacticalPuzzle("1r3k2/8/8/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1b4", "f8g8", "b4b8"), "Queen Double Attack from e1 to b4"),
        RawTacticalPuzzle("5k2/1r6/8/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1b4", "f8g8", "b4b7"), "Queen Double Attack from e1 to b4"),
        RawTacticalPuzzle("2k5/1r6/8/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1b4", "c8b8", "b4b7"), "Queen Double Attack from e1 to b4"),
        RawTacticalPuzzle("1rk5/8/8/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1b4", "c8d7", "b4b8"), "Queen Double Attack from e1 to b4"),
        RawTacticalPuzzle("r2k4/8/8/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1e4", "d8c8", "e4a8"), "Queen Double Attack from e1 to e4"),
        RawTacticalPuzzle("3k4/7r/8/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1e4", "d8c8", "e4h7"), "Queen Double Attack from e1 to e4"),
        RawTacticalPuzzle("r4k2/8/8/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1e4", "f8g8", "e4a8"), "Queen Double Attack from e1 to e4"),
        RawTacticalPuzzle("5k2/7r/8/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1e4", "f8g8", "e4h7"), "Queen Double Attack from e1 to e4"),
        RawTacticalPuzzle("r1k5/8/8/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1e4", "c8b8", "e4a8"), "Queen Double Attack from e1 to e4"),
        RawTacticalPuzzle("2k5/7r/8/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1e4", "c8b8", "e4h7"), "Queen Double Attack from e1 to e4"),
        RawTacticalPuzzle("3k3r/8/8/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1h4", "d8c8", "h4h8"), "Queen Double Attack from e1 to h4"),
        RawTacticalPuzzle("3k4/8/7r/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1h4", "d8c8", "h4h6"), "Queen Double Attack from e1 to h4"),
        RawTacticalPuzzle("5k1r/8/8/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1h4", "f8g8", "h4h8"), "Queen Double Attack from e1 to h4"),
        RawTacticalPuzzle("5k2/8/7r/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1h4", "f8g8", "h4h6"), "Queen Double Attack from e1 to h4"),
        RawTacticalPuzzle("2k4r/8/8/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1h4", "c8b8", "h4h8"), "Queen Double Attack from e1 to h4"),
        RawTacticalPuzzle("2k5/8/7r/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1h4", "c8b8", "h4h6"), "Queen Double Attack from e1 to h4"),
        RawTacticalPuzzle("3k4/r7/8/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1e7", "d8c8", "e7a7"), "Queen Double Attack from e1 to e7"),
        RawTacticalPuzzle("5k2/r7/8/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1e7", "f8g8", "e7a7"), "Queen Double Attack from e1 to e7"),
        RawTacticalPuzzle("2k5/r7/8/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1e7", "c8b8", "e7a7"), "Queen Double Attack from e1 to e7"),
        RawTacticalPuzzle("3k4/8/r7/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1a5", "d8c8", "a5a6"), "Queen Double Attack from e1 to a5"),
        RawTacticalPuzzle("5k2/8/r7/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1a5", "f8g8", "a5a6"), "Queen Double Attack from e1 to a5"),
        RawTacticalPuzzle("2k5/8/r7/8/8/8/8/4Q1K1 w - - 0 1", listOf("e1a5", "c8b8", "a5a6"), "Queen Double Attack from e1 to a5"),
        RawTacticalPuzzle("2r1k3/8/8/8/8/8/8/2Q3K1 w - - 0 1", listOf("c1c4", "e8d8", "c4c8"), "Queen Double Attack from c1 to c4"),
        RawTacticalPuzzle("4k3/8/r7/8/8/8/8/2Q3K1 w - - 0 1", listOf("c1c4", "e8d8", "c4a6"), "Queen Double Attack from c1 to c4"),
        RawTacticalPuzzle("2rk4/8/8/8/8/8/8/2Q3K1 w - - 0 1", listOf("c1c4", "d8e7", "c4c8"), "Queen Double Attack from c1 to c4"),
        RawTacticalPuzzle("3k4/8/r7/8/8/8/8/2Q3K1 w - - 0 1", listOf("c1c4", "d8e7", "c4a6"), "Queen Double Attack from c1 to c4"),
        RawTacticalPuzzle("2r5/8/8/8/8/8/8/2Q3K1 w - - 0 1", listOf("c1c4", "c8b8", "c4c8"), "Queen Double Attack from c1 to c4"),
        RawTacticalPuzzle("1r2k3/8/8/8/8/8/8/2Q3K1 w - - 0 1", listOf("c1f4", "e8d8", "f4b8"), "Queen Double Attack from c1 to f4"),
        RawTacticalPuzzle("4k3/8/7r/8/8/8/8/2Q3K1 w - - 0 1", listOf("c1f4", "e8d8", "f4h6"), "Queen Double Attack from c1 to f4"),
        RawTacticalPuzzle("1r1k4/8/8/8/8/8/8/2Q3K1 w - - 0 1", listOf("c1f4", "d8c8", "f4b8"), "Queen Double Attack from c1 to f4"),
        RawTacticalPuzzle("3k4/8/7r/8/8/8/8/2Q3K1 w - - 0 1", listOf("c1f4", "d8c8", "f4h6"), "Queen Double Attack from c1 to f4"),
        RawTacticalPuzzle("1r3k2/8/8/8/8/8/8/2Q3K1 w - - 0 1", listOf("c1f4", "f8g8", "f4b8"), "Queen Double Attack from c1 to f4"),
        RawTacticalPuzzle("5k2/8/7r/8/8/8/8/2Q3K1 w - - 0 1", listOf("c1f4", "f8g8", "f4h6"), "Queen Double Attack from c1 to f4"),
        RawTacticalPuzzle("4k3/r7/8/8/8/8/8/2Q3K1 w - - 0 1", listOf("c1c5", "e8d8", "c5a7"), "Queen Double Attack from c1 to c5"),
        RawTacticalPuzzle("2r2k2/8/8/8/8/8/8/2Q3K1 w - - 0 1", listOf("c1c5", "f8g8", "c5c8"), "Queen Double Attack from c1 to c5"),
        RawTacticalPuzzle("5k2/r7/8/8/8/8/8/2Q3K1 w - - 0 1", listOf("c1c5", "f8g8", "c5a7"), "Queen Double Attack from c1 to c5"),
        RawTacticalPuzzle("4k1r1/8/8/8/8/8/8/2Q3K1 w - - 0 1", listOf("c1g5", "e8f8", "g5g8"), "Queen Double Attack from c1 to g5"),
        RawTacticalPuzzle("4k3/6r1/8/8/8/8/8/2Q3K1 w - - 0 1", listOf("c1g5", "e8f8", "g5g7"), "Queen Double Attack from c1 to g5"),
        RawTacticalPuzzle("3k2r1/8/8/8/8/8/8/2Q3K1 w - - 0 1", listOf("c1g5", "d8c8", "g5g8"), "Queen Double Attack from c1 to g5"),
        RawTacticalPuzzle("3k4/6r1/8/8/8/8/8/2Q3K1 w - - 0 1", listOf("c1g5", "d8c8", "g5g7"), "Queen Double Attack from c1 to g5"),
        RawTacticalPuzzle("5k2/6r1/8/8/8/8/8/2Q3K1 w - - 0 1", listOf("c1g5", "f8g8", "g5g7"), "Queen Double Attack from c1 to g5"),
        RawTacticalPuzzle("2r1k3/8/8/8/8/8/8/5QK1 w - - 0 1", listOf("f1c4", "e8d8", "c4c8"), "Queen Double Attack from f1 to c4"),
        RawTacticalPuzzle("4k3/8/r7/8/8/8/8/5QK1 w - - 0 1", listOf("f1c4", "e8d8", "c4a6"), "Queen Double Attack from f1 to c4"),
        RawTacticalPuzzle("2rk4/8/8/8/8/8/8/5QK1 w - - 0 1", listOf("f1c4", "d8e7", "c4c8"), "Queen Double Attack from f1 to c4"),
        RawTacticalPuzzle("3k4/8/r7/8/8/8/8/5QK1 w - - 0 1", listOf("f1c4", "d8e7", "c4a6"), "Queen Double Attack from f1 to c4"),
        RawTacticalPuzzle("2r5/8/8/8/8/8/8/5QK1 w - - 0 1", listOf("f1c4", "c8b8", "c4c8"), "Queen Double Attack from f1 to c4"),
        RawTacticalPuzzle("2k5/8/r7/8/8/8/8/5QK1 w - - 0 1", listOf("f1c4", "c8b8", "c4a6"), "Queen Double Attack from f1 to c4"),
        RawTacticalPuzzle("1r2k3/8/8/8/8/8/8/5QK1 w - - 0 1", listOf("f1f4", "e8d8", "f4b8"), "Queen Double Attack from f1 to f4"),
        RawTacticalPuzzle("4k3/8/7r/8/8/8/8/5QK1 w - - 0 1", listOf("f1f4", "e8d8", "f4h6"), "Queen Double Attack from f1 to f4"),
        RawTacticalPuzzle("1r1k4/8/8/8/8/8/8/5QK1 w - - 0 1", listOf("f1f4", "d8c8", "f4b8"), "Queen Double Attack from f1 to f4"),
        RawTacticalPuzzle("3k4/8/7r/8/8/8/8/5QK1 w - - 0 1", listOf("f1f4", "d8c8", "f4h6"), "Queen Double Attack from f1 to f4"),
        RawTacticalPuzzle("1rk5/8/8/8/8/8/8/5QK1 w - - 0 1", listOf("f1f4", "c8d7", "f4b8"), "Queen Double Attack from f1 to f4"),
        RawTacticalPuzzle("2k5/8/7r/8/8/8/8/5QK1 w - - 0 1", listOf("f1f4", "c8d7", "f4h6"), "Queen Double Attack from f1 to f4"),
        RawTacticalPuzzle("4k3/7r/8/8/8/8/8/5QK1 w - - 0 1", listOf("f1f5", "e8d8", "f5h7"), "Queen Double Attack from f1 to f5"),
        RawTacticalPuzzle("3k4/7r/8/8/8/8/8/5QK1 w - - 0 1", listOf("f1f5", "d8e7", "f5h7"), "Queen Double Attack from f1 to f5"),
        RawTacticalPuzzle("2k5/7r/8/8/8/8/8/5QK1 w - - 0 1", listOf("f1f5", "c8b8", "f5h7"), "Queen Double Attack from f1 to f5"),
        RawTacticalPuzzle("4k3/r7/8/8/8/8/8/5QK1 w - - 0 1", listOf("f1f7", "e8d8", "f7a7"), "Queen Double Attack from f1 to f7"),
        RawTacticalPuzzle("4k3/1r6/8/8/8/8/8/5QK1 w - - 0 1", listOf("f1f7", "e8d8", "f7b7"), "Queen Double Attack from f1 to f7"),
        RawTacticalPuzzle("3k4/r7/8/8/8/8/8/5QK1 w - - 0 1", listOf("f1f7", "d8c8", "f7a7"), "Queen Double Attack from f1 to f7"),
        RawTacticalPuzzle("3k4/1r6/8/8/8/8/8/5QK1 w - - 0 1", listOf("f1f7", "d8c8", "f7b7"), "Queen Double Attack from f1 to f7"),
        RawTacticalPuzzle("2k5/r7/8/8/8/8/8/5QK1 w - - 0 1", listOf("f1f7", "c8b8", "f7a7"), "Queen Double Attack from f1 to f7"),
        RawTacticalPuzzle("2k5/1r6/8/8/8/8/8/5QK1 w - - 0 1", listOf("f1f7", "c8b8", "f7b7"), "Queen Double Attack from f1 to f7"),
        RawTacticalPuzzle("r3k3/8/8/8/8/8/8/Q5K1 w - - 0 1", listOf("a1a4", "e8d8", "a4a8"), "Queen Double Attack from a1 to a4"),
        RawTacticalPuzzle("4k3/r7/8/8/8/8/8/Q5K1 w - - 0 1", listOf("a1a4", "e8d8", "a4a7"), "Queen Double Attack from a1 to a4"),
        RawTacticalPuzzle("4k3/8/r7/8/8/8/8/Q5K1 w - - 0 1", listOf("a1a4", "e8d8", "a4a6"), "Queen Double Attack from a1 to a4"),
        RawTacticalPuzzle("r2k4/8/8/8/8/8/8/Q5K1 w - - 0 1", listOf("a1a4", "d8c8", "a4a8"), "Queen Double Attack from a1 to a4"),
        RawTacticalPuzzle("3k4/r7/8/8/8/8/8/Q5K1 w - - 0 1", listOf("a1a4", "d8c8", "a4a7"), "Queen Double Attack from a1 to a4"),
        RawTacticalPuzzle("3k4/8/r7/8/8/8/8/Q5K1 w - - 0 1", listOf("a1a4", "d8c8", "a4a6"), "Queen Double Attack from a1 to a4"),
        RawTacticalPuzzle("r4k2/8/8/8/8/8/8/Q5K1 w - - 0 1", listOf("a1a4", "f8g8", "a4a8"), "Queen Double Attack from a1 to a4")
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
            val cleanTheme = "Double Attack"
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
