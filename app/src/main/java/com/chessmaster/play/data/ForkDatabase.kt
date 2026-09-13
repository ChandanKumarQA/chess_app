package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object ForkDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("r3k3/8/8/1N6/8/8/8/6K1 w - - 0 1", listOf("b5c7", "e8d8", "c7a8"), "Knight Fork on c7 winning Rook a8"),
        RawTacticalPuzzle("r3k3/8/8/3N4/8/8/8/6K1 w - - 0 1", listOf("d5c7", "e8f8", "c7a8"), "Central Knight Fork on c7 winning Rook a8"),
        RawTacticalPuzzle("r3k3/8/N7/8/8/8/8/6K1 w - - 0 1", listOf("a6c7", "e8d7", "c7a8"), "Flank Knight Fork on c7 winning Rook a8"),
        RawTacticalPuzzle("r3k3/8/4N3/8/8/8/8/6K1 w - - 0 1", listOf("e6c7", "e8f7", "c7a8"), "Infiltration Knight Fork on c7 winning Rook a8"),
        RawTacticalPuzzle("4k2r/8/8/6N1/8/8/8/6K1 w - - 0 1", listOf("g5f7", "e8d7", "f7h8"), "Kingside Knight Fork on f7 winning Rook h8"),
        RawTacticalPuzzle("4k2r/8/8/4N3/8/8/8/6K1 w - - 0 1", listOf("e5f7", "e8f8", "f7h8"), "Central Knight Fork on f7 winning Rook h8"),
        RawTacticalPuzzle("4k2r/8/7N/8/8/8/8/6K1 w - - 0 1", listOf("h6f7", "e8e7", "f7h8"), "Wing Knight Fork on f7 winning Rook h8"),
        RawTacticalPuzzle("4k3/1r6/8/8/2N5/8/8/6K1 w - - 0 1", listOf("c4d6", "e8f8", "d6b7"), "Knight Fork on d6 winning Rook b7"),
        RawTacticalPuzzle("4k3/1r6/8/8/4N3/8/8/6K1 w - - 0 1", listOf("e4d6", "e8d8", "d6b7"), "Knight Jump to d6 winning Rook b7"),
        RawTacticalPuzzle("4k3/1r6/8/1N6/8/8/8/6K1 w - - 0 1", listOf("b5d6", "e8d7", "d6b7"), "Outpost Knight Fork on d6 winning Rook b7"),
        RawTacticalPuzzle("5k2/2r5/8/8/5N2/8/8/6K1 w - - 0 1", listOf("f4e6", "f8g8", "e6c7"), "Knight Fork on e6 winning Rook c7"),
        RawTacticalPuzzle("5k2/2r5/8/8/3N4/8/8/6K1 w - - 0 1", listOf("d4e6", "f8e8", "e6c7"), "Central Knight Fork on e6 winning Rook c7"),
        RawTacticalPuzzle("5k2/2r5/8/6N1/8/8/8/6K1 w - - 0 1", listOf("g5e6", "f8e7", "e6c7"), "Kingside Knight Fork on e6 winning Rook c7"),
        RawTacticalPuzzle("2r3k1/8/8/5N2/8/8/8/6K1 w - - 0 1", listOf("f5e7", "g8h8", "e7c8"), "Knight Fork on e7 winning Rook c8"),
        RawTacticalPuzzle("2r3k1/8/8/3N4/8/8/8/6K1 w - - 0 1", listOf("d5e7", "g8f7", "e7c8"), "Knight Outpost Fork on e7 winning Rook c8"),
        RawTacticalPuzzle("2r3k1/8/2N5/8/8/8/8/6K1 w - - 0 1", listOf("c6e7", "g8h7", "e7c8"), "Knight Strike on e7 winning Rook c8"),
        RawTacticalPuzzle("2r3k1/8/6N1/8/8/8/8/6K1 w - - 0 1", listOf("g6e7", "g8f8", "e7c8"), "Knight Royal Line Fork on e7 winning Rook c8"),
        RawTacticalPuzzle("r1k5/8/8/8/2N5/8/8/6K1 w - - 0 1", listOf("c4b6", "c8d8", "b6a8"), "Knight Fork on b6 winning Rook a8"),
        RawTacticalPuzzle("r1k5/8/8/8/N7/8/8/6K1 w - - 0 1", listOf("a4b6", "c8c7", "b6a8"), "Edge Knight Fork on b6 winning Rook a8"),
        RawTacticalPuzzle("r1k5/3N4/8/8/8/8/8/6K1 w - - 0 1", listOf("d7b6", "c8b8", "b6a8"), "Knight Deep Fork on b6 winning Rook a8"),
        RawTacticalPuzzle("6k1/7r/8/8/4N3/8/8/6K1 w - - 0 1", listOf("e4f6", "g8f8", "f6h7"), "Knight Fork on f6 winning Rook h7"),
        RawTacticalPuzzle("6k1/7r/8/8/6N1/8/8/6K1 w - - 0 1", listOf("g4f6", "g8h8", "f6h7"), "Knight Outpost on f6 winning Rook h7"),
        RawTacticalPuzzle("6k1/7r/8/3N4/8/8/8/6K1 w - - 0 1", listOf("d5f6", "g8g7", "f6h7"), "Center Knight Fork on f6 winning Rook h7"),
        RawTacticalPuzzle("6k1/7r/8/7N/8/8/8/6K1 w - - 0 1", listOf("h5f6", "g8f7", "f6h7"), "Flank Knight Fork on f6 winning Rook h7"),
        RawTacticalPuzzle("4k3/8/4N3/3q4/8/8/8/6K1 w - - 0 1", listOf("e6c7", "e8f8", "c7d5"), "Royal Knight Fork on c7 winning Queen d5"),
        RawTacticalPuzzle("4k3/8/N7/3q4/8/8/8/6K1 w - - 0 1", listOf("a6c7", "e8d8", "c7d5"), "Knight Fork on c7 winning Queen d5"),
        RawTacticalPuzzle("4k3/8/8/1N1q4/8/8/8/6K1 w - - 0 1", listOf("b5c7", "e8e7", "c7d5"), "Wing Knight Fork on c7 winning Queen d5"),
        RawTacticalPuzzle("3qk3/8/8/6N1/8/8/8/6K1 w - - 0 1", listOf("g5f7", "e8e7", "f7d8"), "Knight Fork on f7 winning Queen d8"),
        RawTacticalPuzzle("3qk3/8/8/4N3/8/8/8/6K1 w - - 0 1", listOf("e5f7", "e8d7", "f7d8"), "Knight Strike on f7 winning Queen d8"),
        RawTacticalPuzzle("4k3/5q2/8/8/2N5/8/8/6K1 w - - 0 1", listOf("c4d6", "e8f8", "d6f7"), "Knight Fork on d6 winning Queen f7"),
        RawTacticalPuzzle("4k3/5q2/8/1N6/8/8/8/6K1 w - - 0 1", listOf("b5d6", "e8e7", "d6f7"), "Knight Fork on d6 winning Queen on f7"),
        RawTacticalPuzzle("4k3/5q2/8/8/4N3/8/8/6K1 w - - 0 1", listOf("e4d6", "e8d7", "d6f7"), "Central Knight Fork on d6 winning Queen f7"),
        RawTacticalPuzzle("6k1/8/2N5/3q4/8/8/8/6K1 w - - 0 1", listOf("c6e7", "g8h8", "e7d5"), "Knight Fork on e7 winning Queen d5"),
        RawTacticalPuzzle("6k1/8/6N1/3q4/8/8/8/6K1 w - - 0 1", listOf("g6e7", "g8f7", "e7d5"), "Kingside Knight Fork on e7 winning Queen d5"),
        RawTacticalPuzzle("6k1/8/8/3q1N2/8/8/8/6K1 w - - 0 1", listOf("f5e7", "g8g7", "e7d5"), "Knight Attack on e7 winning Queen d5"),
        RawTacticalPuzzle("2k5/3q4/8/3N4/8/8/8/6K1 w - - 0 1", listOf("d5b6", "c8b8", "b6d7"), "Knight Fork on b6 winning Queen d7"),
        RawTacticalPuzzle("2k5/3q4/8/8/2N5/8/8/6K1 w - - 0 1", listOf("c4b6", "c8c7", "b6d7"), "Knight Infiltration on b6 winning Queen d7"),
        RawTacticalPuzzle("5k2/4q3/8/8/5N2/8/8/6K1 w - - 0 1", listOf("f4g6", "f8g8", "g6e7"), "Knight Fork on g6 winning Queen e7"),
        RawTacticalPuzzle("5k2/4q3/8/8/7N/8/8/6K1 w - - 0 1", listOf("h4g6", "f8e8", "g6e7"), "Edge Knight Fork on g6 winning Queen e7"),
        RawTacticalPuzzle("5k2/4q3/8/4N3/8/8/8/6K1 w - - 0 1", listOf("e5g6", "f8f7", "g6e7"), "Center Knight Fork on g6 winning Queen e7"),
        RawTacticalPuzzle("8/3k4/4q3/8/N7/8/8/6K1 w - - 0 1", listOf("a4c5", "d7c8", "c5e6"), "Knight Fork on c5 winning Queen e6"),
        RawTacticalPuzzle("8/3k4/4q3/8/8/1N6/8/6K1 w - - 0 1", listOf("b3c5", "d7d8", "c5e6"), "Knight Hop to c5 winning Queen e6"),
        RawTacticalPuzzle("8/3k4/4q3/8/8/3N4/8/6K1 w - - 0 1", listOf("d3c5", "d7c7", "c5e6"), "Knight Fork on c5 winning Black Queen"),
        RawTacticalPuzzle("8/4k3/3q4/8/8/4N3/8/6K1 w - - 0 1", listOf("e3f5", "e7e8", "f5d6"), "Knight Fork on f5 winning Queen d6"),
        RawTacticalPuzzle("8/4k3/3q4/8/8/6N1/8/6K1 w - - 0 1", listOf("g3f5", "e7f7", "f5d6"), "Knight Strike on f5 winning Queen d6"),
        RawTacticalPuzzle("8/4k3/3q4/8/7N/8/8/6K1 w - - 0 1", listOf("h4f5", "e7d8", "f5d6"), "Flank Knight Fork on f5 winning Queen d6"),
        RawTacticalPuzzle("8/8/2r1k3/8/8/1N6/8/6K1 w - - 0 1", listOf("b3d4", "e6e7", "d4c6"), "Knight Fork on d4 winning Rook c6"),
        RawTacticalPuzzle("8/3k4/2r5/8/8/5N2/8/6K1 w - - 0 1", listOf("f3e5", "d7e8", "e5c6"), "Knight Central Fork on e5 winning Rook c6"),
        RawTacticalPuzzle("8/8/3k1r2/8/8/2N5/8/6K1 w - - 0 1", listOf("c3e4", "d6e7", "e4f6"), "Knight Fork on e4 winning Rook f6"),
        RawTacticalPuzzle("8/r1k5/8/8/8/N7/8/6K1 w - - 0 1", listOf("a3b5", "c7c8", "b5a7"), "Knight Flank Jump to b5 winning Rook a7"),
        RawTacticalPuzzle("8/5k1r/8/8/8/7N/8/6K1 w - - 0 1", listOf("h3g5", "f7f8", "g5h7"), "Knight Flank Jump to g5 winning Rook h7"),
        RawTacticalPuzzle("4k3/8/2n1n3/8/3P4/8/8/6K1 w - - 0 1", listOf("d4d5", "c6e7", "d5e6"), "Central Pawn Fork on c6 and e6 Knights"),
        RawTacticalPuzzle("4k3/8/3b1n2/8/4P3/8/8/6K1 w - - 0 1", listOf("e4e5", "f6d5", "e5d6"), "Center Pawn Fork on Bishop & Knight"),
        RawTacticalPuzzle("4k3/8/1n1b4/8/2P5/8/8/6K1 w - - 0 1", listOf("c4c5", "b6c8", "c5d6"), "Queenside Pawn Fork on Knight & Bishop"),
        RawTacticalPuzzle("4k3/8/4b1n1/8/5P2/8/8/6K1 w - - 0 1", listOf("f4f5", "g6f8", "f5e6"), "Kingside Pawn Fork on Bishop & Knight"),
        RawTacticalPuzzle("4k3/8/5n1b/8/6P1/8/8/6K1 w - - 0 1", listOf("g4g5", "f6h5", "g5h6"), "Wing Pawn Fork on f6 Knight & h6 Bishop"),
        RawTacticalPuzzle("4k3/8/n1b5/8/1P6/8/8/6K1 w - - 0 1", listOf("b4b5", "a6c7", "b5c6"), "Flank Pawn Fork on a6 Knight & c6 Bishop"),
        RawTacticalPuzzle("4k3/2n1b3/8/3P4/8/8/8/6K1 w - - 0 1", listOf("d5d6", "c7b5", "d6e7"), "Advanced Pawn Fork on c7 & e7"),
        RawTacticalPuzzle("4k3/1n1n4/8/2P5/8/8/8/6K1 w - - 0 1", listOf("c5c6", "b7a5", "c6d7"), "Infiltrating Pawn Fork on b7 & d7"),
        RawTacticalPuzzle("4k3/8/8/3n1b2/8/4P3/8/6K1 w - - 0 1", listOf("e3e4", "d5b6", "e4f5"), "Pawn Strike Fork on d5 Knight & f5 Bishop"),
        RawTacticalPuzzle("4k3/8/8/1n1n4/8/2P5/8/6K1 w - - 0 1", listOf("c3c4", "b5c7", "c4d5"), "Queenside Pawn Thrust Fork"),
        RawTacticalPuzzle("4k3/8/8/5n1b/8/6P1/8/6K1 w - - 0 1", listOf("g3g4", "f5e7", "g4h5"), "G-pawn Thrust Fork on f5 & h5"),
        RawTacticalPuzzle("4k3/8/8/2b1n3/8/8/3P4/6K1 w - - 0 1", listOf("d2d4", "c5d6", "d4e5"), "Double Step Pawn Fork on c5 & e5"),
        RawTacticalPuzzle("4k3/8/8/3b1b2/8/8/4P3/6K1 w - - 0 1", listOf("e2e4", "d5c6", "e4f5"), "Double Step Center Pawn Fork on d5 & f5"),
        RawTacticalPuzzle("4k3/8/8/1n1b4/8/8/2P5/6K1 w - - 0 1", listOf("c2c4", "b5a7", "c4d5"), "Double Step Queenside Pawn Fork"),
        RawTacticalPuzzle("4k3/8/8/5r1n/8/8/6P1/6K1 w - - 0 1", listOf("g2g4", "f5f6", "g4h5"), "Double Step Flank Pawn Fork"),
        RawTacticalPuzzle("4k3/8/8/8/2b5/8/8/3Q2K1 w - - 0 1", listOf("d1a4", "e8d8", "a4c4"), "Queen Check Fork on a4 winning Bishop c4"),
        RawTacticalPuzzle("4k3/8/8/4n3/8/8/8/3Q2K1 w - - 0 1", listOf("d1h5", "e8d7", "h5e5"), "Queen Check Fork on h5 winning Knight e5"),
        RawTacticalPuzzle("4k3/8/8/4n3/8/8/8/1Q4K1 w - - 0 1", listOf("b1b5", "e8d8", "b5e5"), "Flank Queen Fork on b5 winning Knight e5"),
        RawTacticalPuzzle("5b2/4k3/8/8/8/8/8/2Q3K1 w - - 0 1", listOf("c1a3", "e7e8", "a3f8"), "Diagonal Queen Fork on a3 winning Bishop f8"),
        RawTacticalPuzzle("3k4/8/8/1r6/8/8/8/4Q1K1 w - - 0 1", listOf("e1e5", "d8c8", "e5b5"), "Central Queen Fork on e5 winning Rook b5"),
        RawTacticalPuzzle("4k3/1r6/8/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1d5", "e8f8", "d5b7"), "Queen Check on d5 winning Rook b7"),
        RawTacticalPuzzle("4k1n1/8/8/8/8/8/8/5QK1 w - - 0 1", listOf("f1c4", "e8d7", "c4g8"), "Queen Fork on c4 winning Knight g8"),
        RawTacticalPuzzle("8/8/2k5/8/4n3/8/8/3Q2K1 w - - 0 1", listOf("d1a4", "c6b7", "a4e4"), "Queen Angle Fork on a4 winning Knight e4"),
        RawTacticalPuzzle("8/1r6/8/5k2/8/8/8/3Q2K1 w - - 0 1", listOf("d1d7", "f5e4", "d7b7"), "Queen Check on d7 winning Rook b7"),
        RawTacticalPuzzle("8/8/8/6k1/1r6/8/8/4Q1K1 w - - 0 1", listOf("e1e7", "g5h6", "e7b4"), "Queen Check on e7 winning Rook b4"),
        RawTacticalPuzzle("6b1/8/4k3/8/8/8/8/3Q2K1 w - - 0 1", listOf("d1b3", "e6d7", "b3g8"), "Queen Fork on b3 winning Bishop g8"),
        RawTacticalPuzzle("r3k3/8/8/8/8/8/8/2Q3K1 w - - 0 1", listOf("c1c6", "e8f8", "c6a8"), "Queen Invasion Fork on c6 winning Rook a8"),
        RawTacticalPuzzle("8/3k4/8/8/6b1/8/8/Q5K1 w - - 0 1", listOf("a1a4", "d7c8", "a4g4"), "Queen Flank Fork on a4 winning Bishop g4"),
        RawTacticalPuzzle("8/5k2/8/r7/8/8/8/Q5K1 w - - 0 1", listOf("a1e5", "f7g8", "e5a5"), "Queen Center Fork on e5 winning Rook a5"),
        RawTacticalPuzzle("8/1b6/8/3k4/8/8/8/5QK1 w - - 0 1", listOf("f1f7", "d5c6", "f7b7"), "Queen 7th-Rank Fork on f7 winning Bishop b7"),
        RawTacticalPuzzle("8/1b6/5k2/8/8/8/8/1Q4K1 w - - 0 1", listOf("b1e4", "f6g5", "e4b7"), "Queen Long Diagonal Fork on e4 winning Bishop b7"),
        RawTacticalPuzzle("8/3k4/8/5n2/8/8/8/5QK1 w - - 0 1", listOf("f1b5", "d7c8", "b5f5"), "Queen Long Range Fork on b5 winning Knight f5"),
        RawTacticalPuzzle("3q4/4k3/8/8/8/8/8/2B3K1 w - - 0 1", listOf("c1g5", "e7d7", "g5d8"), "Bishop Skewering Fork on g5 winning Queen"),
        RawTacticalPuzzle("4k3/3q4/8/8/8/8/8/5BK1 w - - 0 1", listOf("f1b5", "e8d8", "b5d7"), "Bishop Pin Fork on b5 winning Queen"),
        RawTacticalPuzzle("7r/4k3/8/8/8/8/1B6/6K1 w - - 0 1", listOf("b2f6", "e7d6", "f6h8"), "Bishop Long Fork on f6 winning Rook h8"),
        RawTacticalPuzzle("5r2/4k3/8/8/8/8/8/2B3K1 w - - 0 1", listOf("c1a3", "e7d8", "a3f8"), "Bishop Diagonal Fork on a3 winning Rook f8"),
        RawTacticalPuzzle("8/r3k3/8/8/8/4B3/8/6K1 w - - 0 1", listOf("e3c5", "e7e8", "c5a7"), "Bishop Fork on c5 winning Rook a7"),
        RawTacticalPuzzle("3r4/4k3/8/8/8/8/3B4/6K1 w - - 0 1", listOf("d2a5", "e7e6", "a5d8"), "Bishop Attack on a5 winning Rook d8"),
        RawTacticalPuzzle("1r6/4k3/8/8/5B2/8/8/6K1 w - - 0 1", listOf("f4d6", "e7f7", "d6b8"), "Bishop Royal Fork on d6 winning Rook b8"),
        RawTacticalPuzzle("r7/8/4k3/8/8/8/6B1/6K1 w - - 0 1", listOf("g2d5", "e6e7", "d5a8"), "Bishop Fianchetto Fork on d5 winning Rook a8"),
        RawTacticalPuzzle("8/4k1n1/8/8/8/8/8/R5K1 w - - 0 1", listOf("a1a7", "e7d8", "a7g7"), "Rook 7th-Rank Fork on a7 winning Knight g7"),
        RawTacticalPuzzle("8/2n1k3/8/8/8/8/8/1R4K1 w - - 0 1", listOf("b1b7", "e7f8", "b7c7"), "Rook 7th-Rank Fork on b7 winning Knight c7"),
        RawTacticalPuzzle("8/4k1b1/8/8/8/8/8/2R3K1 w - - 0 1", listOf("c1c7", "e7d6", "c7g7"), "Rook Invasion Fork on c7 winning Bishop g7"),
        RawTacticalPuzzle("8/1b3k2/8/8/8/8/8/3R2K1 w - - 0 1", listOf("d1d7", "f7g8", "d7b7"), "Rook Central Fork on d7 winning Bishop b7"),
        RawTacticalPuzzle("8/2n3k1/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e7", "g7h8", "e7c7"), "Rook Deep Fork on e7 winning Knight c7"),
        RawTacticalPuzzle("8/1n1k4/8/8/8/8/8/5RK1 w - - 0 1", listOf("f1f7", "d7e8", "f7b7"), "Rook Check Fork on f7 winning Knight b7"),
        RawTacticalPuzzle("8/2n1k3/8/8/8/8/8/6KR w - - 0 1", listOf("h1h7", "e7d8", "h7c7"), "Rook Flank Fork on h7 winning Knight c7"),
        RawTacticalPuzzle("4r3/8/4k3/8/8/8/8/R5K1 w - - 0 1", listOf("a1e1", "e6d5", "e1e8"), "Rook Vertical Pin Fork on e1"),
        RawTacticalPuzzle("1r6/8/5k2/8/8/8/8/3R2K1 w - - 0 1", listOf("d1d8", "f6g5", "d8b8"), "Rook Back-Rank Fork on d8 winning Rook")
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
            val cleanTheme = "Fork"
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
