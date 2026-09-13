package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object SkewerDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("r7/8/8/8/k7/8/8/1R5K w - - 0 1", listOf("b1a1", "a4b5", "a1a8"), "Vertical Rook Skewer on A-file"),
        RawTacticalPuzzle("r7/8/8/k7/8/8/8/1R5K w - - 0 1", listOf("b1a1", "a5b6", "a1a8"), "Vertical Rook Skewer on A-file"),
        RawTacticalPuzzle("r7/8/8/8/k7/8/8/2R4K w - - 0 1", listOf("c1a1", "a4b5", "a1a8"), "Vertical Rook Skewer on A-file"),
        RawTacticalPuzzle("r7/8/8/k7/8/8/8/2R4K w - - 0 1", listOf("c1a1", "a5b6", "a1a8"), "Vertical Rook Skewer on A-file"),
        RawTacticalPuzzle("r7/8/8/8/k7/8/8/3R3K w - - 0 1", listOf("d1a1", "a4b5", "a1a8"), "Vertical Rook Skewer on A-file"),
        RawTacticalPuzzle("r7/8/8/k7/8/8/8/3R3K w - - 0 1", listOf("d1a1", "a5b6", "a1a8"), "Vertical Rook Skewer on A-file"),
        RawTacticalPuzzle("r7/8/8/8/k7/8/8/4R2K w - - 0 1", listOf("e1a1", "a4b5", "a1a8"), "Vertical Rook Skewer on A-file"),
        RawTacticalPuzzle("r7/8/8/k7/8/8/8/4R2K w - - 0 1", listOf("e1a1", "a5b6", "a1a8"), "Vertical Rook Skewer on A-file"),
        RawTacticalPuzzle("r7/8/8/8/k7/8/8/5R1K w - - 0 1", listOf("f1a1", "a4b5", "a1a8"), "Vertical Rook Skewer on A-file"),
        RawTacticalPuzzle("r7/8/8/k7/8/8/8/5R1K w - - 0 1", listOf("f1a1", "a5b6", "a1a8"), "Vertical Rook Skewer on A-file"),
        RawTacticalPuzzle("r7/8/8/8/k7/8/8/6RK w - - 0 1", listOf("g1a1", "a4b5", "a1a8"), "Vertical Rook Skewer on A-file"),
        RawTacticalPuzzle("r7/8/8/k7/8/8/8/6RK w - - 0 1", listOf("g1a1", "a5b6", "a1a8"), "Vertical Rook Skewer on A-file"),
        RawTacticalPuzzle("1r6/8/8/8/1k6/8/8/R6K w - - 0 1", listOf("a1b1", "b4c5", "b1b8"), "Vertical Rook Skewer on B-file"),
        RawTacticalPuzzle("1r6/8/8/1k6/8/8/8/R6K w - - 0 1", listOf("a1b1", "b5c6", "b1b8"), "Vertical Rook Skewer on B-file"),
        RawTacticalPuzzle("1r6/8/8/8/1k6/8/8/2R4K w - - 0 1", listOf("c1b1", "b4c5", "b1b8"), "Vertical Rook Skewer on B-file"),
        RawTacticalPuzzle("1r6/8/8/1k6/8/8/8/2R4K w - - 0 1", listOf("c1b1", "b5c6", "b1b8"), "Vertical Rook Skewer on B-file"),
        RawTacticalPuzzle("1r6/8/8/8/1k6/8/8/3R3K w - - 0 1", listOf("d1b1", "b4c5", "b1b8"), "Vertical Rook Skewer on B-file"),
        RawTacticalPuzzle("1r6/8/8/1k6/8/8/8/3R3K w - - 0 1", listOf("d1b1", "b5c6", "b1b8"), "Vertical Rook Skewer on B-file"),
        RawTacticalPuzzle("1r6/8/8/8/1k6/8/8/4R2K w - - 0 1", listOf("e1b1", "b4c5", "b1b8"), "Vertical Rook Skewer on B-file"),
        RawTacticalPuzzle("1r6/8/8/1k6/8/8/8/4R2K w - - 0 1", listOf("e1b1", "b5c6", "b1b8"), "Vertical Rook Skewer on B-file"),
        RawTacticalPuzzle("1r6/8/8/8/1k6/8/8/5R1K w - - 0 1", listOf("f1b1", "b4c5", "b1b8"), "Vertical Rook Skewer on B-file"),
        RawTacticalPuzzle("1r6/8/8/1k6/8/8/8/5R1K w - - 0 1", listOf("f1b1", "b5c6", "b1b8"), "Vertical Rook Skewer on B-file"),
        RawTacticalPuzzle("1r6/8/8/8/1k6/8/8/6RK w - - 0 1", listOf("g1b1", "b4c5", "b1b8"), "Vertical Rook Skewer on B-file"),
        RawTacticalPuzzle("1r6/8/8/1k6/8/8/8/6RK w - - 0 1", listOf("g1b1", "b5c6", "b1b8"), "Vertical Rook Skewer on B-file"),
        RawTacticalPuzzle("2r5/8/8/8/2k5/8/8/R6K w - - 0 1", listOf("a1c1", "c4d5", "c1c8"), "Vertical Rook Skewer on C-file"),
        RawTacticalPuzzle("2r5/8/8/2k5/8/8/8/R6K w - - 0 1", listOf("a1c1", "c5d6", "c1c8"), "Vertical Rook Skewer on C-file"),
        RawTacticalPuzzle("2r5/8/8/8/2k5/8/8/1R5K w - - 0 1", listOf("b1c1", "c4d5", "c1c8"), "Vertical Rook Skewer on C-file"),
        RawTacticalPuzzle("2r5/8/8/2k5/8/8/8/1R5K w - - 0 1", listOf("b1c1", "c5d6", "c1c8"), "Vertical Rook Skewer on C-file"),
        RawTacticalPuzzle("2r5/8/8/8/2k5/8/8/3R3K w - - 0 1", listOf("d1c1", "c4d5", "c1c8"), "Vertical Rook Skewer on C-file"),
        RawTacticalPuzzle("2r5/8/8/2k5/8/8/8/3R3K w - - 0 1", listOf("d1c1", "c5d6", "c1c8"), "Vertical Rook Skewer on C-file"),
        RawTacticalPuzzle("2r5/8/8/8/2k5/8/8/4R2K w - - 0 1", listOf("e1c1", "c4d5", "c1c8"), "Vertical Rook Skewer on C-file"),
        RawTacticalPuzzle("2r5/8/8/2k5/8/8/8/4R2K w - - 0 1", listOf("e1c1", "c5d6", "c1c8"), "Vertical Rook Skewer on C-file"),
        RawTacticalPuzzle("2r5/8/8/8/2k5/8/8/5R1K w - - 0 1", listOf("f1c1", "c4d5", "c1c8"), "Vertical Rook Skewer on C-file"),
        RawTacticalPuzzle("2r5/8/8/2k5/8/8/8/5R1K w - - 0 1", listOf("f1c1", "c5d6", "c1c8"), "Vertical Rook Skewer on C-file"),
        RawTacticalPuzzle("2r5/8/8/8/2k5/8/8/6RK w - - 0 1", listOf("g1c1", "c4d5", "c1c8"), "Vertical Rook Skewer on C-file"),
        RawTacticalPuzzle("2r5/8/8/2k5/8/8/8/6RK w - - 0 1", listOf("g1c1", "c5d6", "c1c8"), "Vertical Rook Skewer on C-file"),
        RawTacticalPuzzle("3r4/8/8/8/3k4/8/8/R6K w - - 0 1", listOf("a1d1", "d4e5", "d1d8"), "Vertical Rook Skewer on D-file"),
        RawTacticalPuzzle("3r4/8/8/3k4/8/8/8/R6K w - - 0 1", listOf("a1d1", "d5e6", "d1d8"), "Vertical Rook Skewer on D-file"),
        RawTacticalPuzzle("3r4/8/8/8/3k4/8/8/1R5K w - - 0 1", listOf("b1d1", "d4e5", "d1d8"), "Vertical Rook Skewer on D-file"),
        RawTacticalPuzzle("3r4/8/8/3k4/8/8/8/1R5K w - - 0 1", listOf("b1d1", "d5e6", "d1d8"), "Vertical Rook Skewer on D-file"),
        RawTacticalPuzzle("3r4/8/8/8/3k4/8/8/2R4K w - - 0 1", listOf("c1d1", "d4e5", "d1d8"), "Vertical Rook Skewer on D-file"),
        RawTacticalPuzzle("3r4/8/8/3k4/8/8/8/2R4K w - - 0 1", listOf("c1d1", "d5e6", "d1d8"), "Vertical Rook Skewer on D-file"),
        RawTacticalPuzzle("3r4/8/8/8/3k4/8/8/4R2K w - - 0 1", listOf("e1d1", "d4e5", "d1d8"), "Vertical Rook Skewer on D-file"),
        RawTacticalPuzzle("3r4/8/8/3k4/8/8/8/4R2K w - - 0 1", listOf("e1d1", "d5e6", "d1d8"), "Vertical Rook Skewer on D-file"),
        RawTacticalPuzzle("3r4/8/8/8/3k4/8/8/5R1K w - - 0 1", listOf("f1d1", "d4e5", "d1d8"), "Vertical Rook Skewer on D-file"),
        RawTacticalPuzzle("3r4/8/8/3k4/8/8/8/5R1K w - - 0 1", listOf("f1d1", "d5e6", "d1d8"), "Vertical Rook Skewer on D-file"),
        RawTacticalPuzzle("3r4/8/8/8/3k4/8/8/6RK w - - 0 1", listOf("g1d1", "d4e5", "d1d8"), "Vertical Rook Skewer on D-file"),
        RawTacticalPuzzle("3r4/8/8/3k4/8/8/8/6RK w - - 0 1", listOf("g1d1", "d5e6", "d1d8"), "Vertical Rook Skewer on D-file"),
        RawTacticalPuzzle("4r3/8/8/8/4k3/8/8/R6K w - - 0 1", listOf("a1e1", "e4f5", "e1e8"), "Vertical Rook Skewer on E-file"),
        RawTacticalPuzzle("4r3/8/8/4k3/8/8/8/R6K w - - 0 1", listOf("a1e1", "e5f6", "e1e8"), "Vertical Rook Skewer on E-file"),
        RawTacticalPuzzle("4r3/8/8/8/4k3/8/8/1R5K w - - 0 1", listOf("b1e1", "e4f5", "e1e8"), "Vertical Rook Skewer on E-file"),
        RawTacticalPuzzle("4r3/8/8/4k3/8/8/8/1R5K w - - 0 1", listOf("b1e1", "e5f6", "e1e8"), "Vertical Rook Skewer on E-file"),
        RawTacticalPuzzle("4r3/8/8/8/4k3/8/8/2R4K w - - 0 1", listOf("c1e1", "e4f5", "e1e8"), "Vertical Rook Skewer on E-file"),
        RawTacticalPuzzle("4r3/8/8/4k3/8/8/8/2R4K w - - 0 1", listOf("c1e1", "e5f6", "e1e8"), "Vertical Rook Skewer on E-file"),
        RawTacticalPuzzle("4r3/8/8/8/4k3/8/8/3R3K w - - 0 1", listOf("d1e1", "e4f5", "e1e8"), "Vertical Rook Skewer on E-file"),
        RawTacticalPuzzle("4r3/8/8/4k3/8/8/8/3R3K w - - 0 1", listOf("d1e1", "e5f6", "e1e8"), "Vertical Rook Skewer on E-file"),
        RawTacticalPuzzle("4r3/8/8/8/4k3/8/8/5R1K w - - 0 1", listOf("f1e1", "e4f5", "e1e8"), "Vertical Rook Skewer on E-file"),
        RawTacticalPuzzle("4r3/8/8/4k3/8/8/8/5R1K w - - 0 1", listOf("f1e1", "e5f6", "e1e8"), "Vertical Rook Skewer on E-file"),
        RawTacticalPuzzle("4r3/8/8/8/4k3/8/8/6RK w - - 0 1", listOf("g1e1", "e4f5", "e1e8"), "Vertical Rook Skewer on E-file"),
        RawTacticalPuzzle("4r3/8/8/4k3/8/8/8/6RK w - - 0 1", listOf("g1e1", "e5f6", "e1e8"), "Vertical Rook Skewer on E-file"),
        RawTacticalPuzzle("5r2/8/8/8/5k2/8/8/R6K w - - 0 1", listOf("a1f1", "f4g5", "f1f8"), "Vertical Rook Skewer on F-file"),
        RawTacticalPuzzle("5r2/8/8/5k2/8/8/8/R6K w - - 0 1", listOf("a1f1", "f5g6", "f1f8"), "Vertical Rook Skewer on F-file"),
        RawTacticalPuzzle("5r2/8/8/8/5k2/8/8/1R5K w - - 0 1", listOf("b1f1", "f4g5", "f1f8"), "Vertical Rook Skewer on F-file"),
        RawTacticalPuzzle("5r2/8/8/5k2/8/8/8/1R5K w - - 0 1", listOf("b1f1", "f5g6", "f1f8"), "Vertical Rook Skewer on F-file"),
        RawTacticalPuzzle("5r2/8/8/8/5k2/8/8/2R4K w - - 0 1", listOf("c1f1", "f4g5", "f1f8"), "Vertical Rook Skewer on F-file"),
        RawTacticalPuzzle("5r2/8/8/5k2/8/8/8/2R4K w - - 0 1", listOf("c1f1", "f5g6", "f1f8"), "Vertical Rook Skewer on F-file"),
        RawTacticalPuzzle("5r2/8/8/8/5k2/8/8/3R3K w - - 0 1", listOf("d1f1", "f4g5", "f1f8"), "Vertical Rook Skewer on F-file"),
        RawTacticalPuzzle("5r2/8/8/5k2/8/8/8/3R3K w - - 0 1", listOf("d1f1", "f5g6", "f1f8"), "Vertical Rook Skewer on F-file"),
        RawTacticalPuzzle("5r2/8/8/8/5k2/8/8/4R2K w - - 0 1", listOf("e1f1", "f4g5", "f1f8"), "Vertical Rook Skewer on F-file"),
        RawTacticalPuzzle("5r2/8/8/5k2/8/8/8/4R2K w - - 0 1", listOf("e1f1", "f5g6", "f1f8"), "Vertical Rook Skewer on F-file"),
        RawTacticalPuzzle("5r2/8/8/8/5k2/8/8/6RK w - - 0 1", listOf("g1f1", "f4g5", "f1f8"), "Vertical Rook Skewer on F-file"),
        RawTacticalPuzzle("5r2/8/8/5k2/8/8/8/6RK w - - 0 1", listOf("g1f1", "f5g6", "f1f8"), "Vertical Rook Skewer on F-file"),
        RawTacticalPuzzle("6r1/8/8/8/6k1/8/8/R6K w - - 0 1", listOf("a1g1", "g4h5", "g1g8"), "Vertical Rook Skewer on G-file"),
        RawTacticalPuzzle("6r1/8/8/6k1/8/8/8/R6K w - - 0 1", listOf("a1g1", "g5h6", "g1g8"), "Vertical Rook Skewer on G-file"),
        RawTacticalPuzzle("6r1/8/8/8/6k1/8/8/1R5K w - - 0 1", listOf("b1g1", "g4h5", "g1g8"), "Vertical Rook Skewer on G-file"),
        RawTacticalPuzzle("6r1/8/8/6k1/8/8/8/1R5K w - - 0 1", listOf("b1g1", "g5h6", "g1g8"), "Vertical Rook Skewer on G-file"),
        RawTacticalPuzzle("6r1/8/8/8/6k1/8/8/2R4K w - - 0 1", listOf("c1g1", "g4h5", "g1g8"), "Vertical Rook Skewer on G-file"),
        RawTacticalPuzzle("6r1/8/8/6k1/8/8/8/2R4K w - - 0 1", listOf("c1g1", "g5h6", "g1g8"), "Vertical Rook Skewer on G-file"),
        RawTacticalPuzzle("6r1/8/8/8/6k1/8/8/3R3K w - - 0 1", listOf("d1g1", "g4h5", "g1g8"), "Vertical Rook Skewer on G-file"),
        RawTacticalPuzzle("6r1/8/8/6k1/8/8/8/3R3K w - - 0 1", listOf("d1g1", "g5h6", "g1g8"), "Vertical Rook Skewer on G-file"),
        RawTacticalPuzzle("6r1/8/8/8/6k1/8/8/4R2K w - - 0 1", listOf("e1g1", "g4h5", "g1g8"), "Vertical Rook Skewer on G-file"),
        RawTacticalPuzzle("6r1/8/8/6k1/8/8/8/4R2K w - - 0 1", listOf("e1g1", "g5h6", "g1g8"), "Vertical Rook Skewer on G-file"),
        RawTacticalPuzzle("6r1/8/8/8/6k1/8/8/5R1K w - - 0 1", listOf("f1g1", "g4h5", "g1g8"), "Vertical Rook Skewer on G-file"),
        RawTacticalPuzzle("6r1/8/8/6k1/8/8/8/5R1K w - - 0 1", listOf("f1g1", "g5h6", "g1g8"), "Vertical Rook Skewer on G-file"),
        RawTacticalPuzzle("7r/8/8/8/7k/8/8/KR6 w - - 0 1", listOf("b1h1", "h4g5", "h1h8"), "Vertical Rook Skewer on H-file"),
        RawTacticalPuzzle("7r/8/8/7k/8/8/8/KR6 w - - 0 1", listOf("b1h1", "h5g6", "h1h8"), "Vertical Rook Skewer on H-file"),
        RawTacticalPuzzle("7r/8/8/8/7k/8/8/K1R5 w - - 0 1", listOf("c1h1", "h4g5", "h1h8"), "Vertical Rook Skewer on H-file"),
        RawTacticalPuzzle("7r/8/8/7k/8/8/8/K1R5 w - - 0 1", listOf("c1h1", "h5g6", "h1h8"), "Vertical Rook Skewer on H-file"),
        RawTacticalPuzzle("7r/8/8/8/7k/8/8/K2R4 w - - 0 1", listOf("d1h1", "h4g5", "h1h8"), "Vertical Rook Skewer on H-file"),
        RawTacticalPuzzle("7r/8/8/7k/8/8/8/K2R4 w - - 0 1", listOf("d1h1", "h5g6", "h1h8"), "Vertical Rook Skewer on H-file"),
        RawTacticalPuzzle("7r/8/8/8/7k/8/8/K3R3 w - - 0 1", listOf("e1h1", "h4g5", "h1h8"), "Vertical Rook Skewer on H-file"),
        RawTacticalPuzzle("7r/8/8/7k/8/8/8/K3R3 w - - 0 1", listOf("e1h1", "h5g6", "h1h8"), "Vertical Rook Skewer on H-file"),
        RawTacticalPuzzle("7r/8/8/8/7k/8/8/K4R2 w - - 0 1", listOf("f1h1", "h4g5", "h1h8"), "Vertical Rook Skewer on H-file"),
        RawTacticalPuzzle("7r/8/8/7k/8/8/8/K4R2 w - - 0 1", listOf("f1h1", "h5g6", "h1h8"), "Vertical Rook Skewer on H-file"),
        RawTacticalPuzzle("7r/8/8/8/7k/8/8/K5R1 w - - 0 1", listOf("g1h1", "h4g5", "h1h8"), "Vertical Rook Skewer on H-file"),
        RawTacticalPuzzle("7r/8/8/7k/8/8/8/K5R1 w - - 0 1", listOf("g1h1", "h5g6", "h1h8"), "Vertical Rook Skewer on H-file"),
        RawTacticalPuzzle("3k3r/8/8/8/8/8/8/R5K1 w - - 0 1", listOf("a1a8", "d8d7", "a8h8"), "Horizontal Rook Skewer on 8th Rank"),
        RawTacticalPuzzle("3k3r/8/8/8/8/8/8/1R4K1 w - - 0 1", listOf("b1b8", "d8d7", "b8h8"), "Horizontal Rook Skewer on 8th Rank"),
        RawTacticalPuzzle("3k3r/8/8/8/8/8/8/2R3K1 w - - 0 1", listOf("c1c8", "d8d7", "c8h8"), "Horizontal Rook Skewer on 8th Rank"),
        RawTacticalPuzzle("4k2r/8/8/8/8/8/8/R5K1 w - - 0 1", listOf("a1a8", "e8e7", "a8h8"), "Horizontal Rook Skewer on 8th Rank")
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
            val cleanTheme = "Skewer"
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
