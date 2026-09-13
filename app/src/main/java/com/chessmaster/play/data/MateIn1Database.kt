package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object MateIn1Database {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("6k1/5ppp/8/8/8/8/8/R5K1 w - - 0 1", listOf("a1a8"), "A-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/8/8/R7/6K1 w - - 0 1", listOf("a2a8"), "A-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/8/R7/8/6K1 w - - 0 1", listOf("a3a8"), "A-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/R7/8/8/6K1 w - - 0 1", listOf("a4a8"), "A-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/R7/8/8/8/6K1 w - - 0 1", listOf("a5a8"), "A-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/R7/8/8/8/8/6K1 w - - 0 1", listOf("a6a8"), "A-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/R4ppp/8/8/8/8/8/6K1 w - - 0 1", listOf("a7a8"), "A-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/8/8/8/1R4K1 w - - 0 1", listOf("b1b8"), "B-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/8/8/1R6/6K1 w - - 0 1", listOf("b2b8"), "B-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/8/1R6/8/6K1 w - - 0 1", listOf("b3b8"), "B-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/1R6/8/8/6K1 w - - 0 1", listOf("b4b8"), "B-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/1R6/8/8/8/6K1 w - - 0 1", listOf("b5b8"), "B-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/1R6/8/8/8/8/6K1 w - - 0 1", listOf("b6b8"), "B-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/1R3ppp/8/8/8/8/8/6K1 w - - 0 1", listOf("b7b8"), "B-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/8/8/8/2R3K1 w - - 0 1", listOf("c1c8"), "C-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/8/8/2R5/6K1 w - - 0 1", listOf("c2c8"), "C-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/8/2R5/8/6K1 w - - 0 1", listOf("c3c8"), "C-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/2R5/8/8/6K1 w - - 0 1", listOf("c4c8"), "C-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/2R5/8/8/8/6K1 w - - 0 1", listOf("c5c8"), "C-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/2R5/8/8/8/8/6K1 w - - 0 1", listOf("c6c8"), "C-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/2R2ppp/8/8/8/8/8/6K1 w - - 0 1", listOf("c7c8"), "C-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/8/8/8/3R2K1 w - - 0 1", listOf("d1d8"), "D-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/8/8/3R4/6K1 w - - 0 1", listOf("d2d8"), "D-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/8/3R4/8/6K1 w - - 0 1", listOf("d3d8"), "D-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/3R4/8/8/6K1 w - - 0 1", listOf("d4d8"), "D-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/3R4/8/8/8/6K1 w - - 0 1", listOf("d5d8"), "D-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/3R4/8/8/8/8/6K1 w - - 0 1", listOf("d6d8"), "D-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/3R1ppp/8/8/8/8/8/6K1 w - - 0 1", listOf("d7d8"), "D-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e8"), "E-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/8/8/4R3/6K1 w - - 0 1", listOf("e2e8"), "E-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/8/4R3/8/6K1 w - - 0 1", listOf("e3e8"), "E-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/4R3/8/8/6K1 w - - 0 1", listOf("e4e8"), "E-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/4R3/8/8/8/6K1 w - - 0 1", listOf("e5e8"), "E-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/4R3/8/8/8/8/6K1 w - - 0 1", listOf("e6e8"), "E-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/4Rppp/8/8/8/8/8/6K1 w - - 0 1", listOf("e7e8"), "E-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5Rpp/8/8/8/8/8/6K1 w - - 0 1", listOf("f7f8"), "F-file Back-Rank Mate"),
        RawTacticalPuzzle("6k1/5ppR/8/8/8/8/8/6K1 w - - 0 1", listOf("h7h8"), "H-file Back-Rank Mate"),
        RawTacticalPuzzle("1k6/ppp5/8/8/8/8/8/5RK1 w - - 0 1", listOf("f1f8"), "Queenside Back-Rank Mate on b8"),
        RawTacticalPuzzle("1k6/ppp5/8/8/8/8/5R2/6K1 w - - 0 1", listOf("f2f8"), "Queenside Back-Rank Mate on b8"),
        RawTacticalPuzzle("1k6/ppp5/8/8/8/5R2/8/6K1 w - - 0 1", listOf("f3f8"), "Queenside Back-Rank Mate on b8"),
        RawTacticalPuzzle("1k6/ppp5/8/8/5R2/8/8/6K1 w - - 0 1", listOf("f4f8"), "Queenside Back-Rank Mate on b8"),
        RawTacticalPuzzle("1k6/ppp5/8/5R2/8/8/8/6K1 w - - 0 1", listOf("f5f8"), "Queenside Back-Rank Mate on b8"),
        RawTacticalPuzzle("1k6/ppp5/5R2/8/8/8/8/6K1 w - - 0 1", listOf("f6f8"), "Queenside Back-Rank Mate on b8"),
        RawTacticalPuzzle("1k6/ppp5/8/8/8/8/8/6R1 w - - 0 1", listOf("g1g8"), "Queenside Back-Rank Mate on b8"),
        RawTacticalPuzzle("1k6/ppp5/8/8/8/8/6R1/6K1 w - - 0 1", listOf("g2g8"), "Queenside Back-Rank Mate on b8"),
        RawTacticalPuzzle("1k6/ppp5/8/8/8/6R1/8/6K1 w - - 0 1", listOf("g3g8"), "Queenside Back-Rank Mate on b8"),
        RawTacticalPuzzle("1k6/ppp5/8/8/6R1/8/8/6K1 w - - 0 1", listOf("g4g8"), "Queenside Back-Rank Mate on b8"),
        RawTacticalPuzzle("1k6/ppp5/8/6R1/8/8/8/6K1 w - - 0 1", listOf("g5g8"), "Queenside Back-Rank Mate on b8"),
        RawTacticalPuzzle("1k6/ppp5/6R1/8/8/8/8/6K1 w - - 0 1", listOf("g6g8"), "Queenside Back-Rank Mate on b8"),
        RawTacticalPuzzle("1k6/ppp3R1/8/8/8/8/8/6K1 w - - 0 1", listOf("g7g8"), "Queenside Back-Rank Mate on b8"),
        RawTacticalPuzzle("1k6/ppp5/8/8/8/8/8/6KR w - - 0 1", listOf("h1h8"), "Queenside Back-Rank Mate on b8"),
        RawTacticalPuzzle("1k6/ppp5/8/8/8/8/7R/6K1 w - - 0 1", listOf("h2h8"), "Queenside Back-Rank Mate on b8"),
        RawTacticalPuzzle("1k6/ppp5/8/8/8/7R/8/6K1 w - - 0 1", listOf("h3h8"), "Queenside Back-Rank Mate on b8"),
        RawTacticalPuzzle("1k6/ppp5/8/8/7R/8/8/6K1 w - - 0 1", listOf("h4h8"), "Queenside Back-Rank Mate on b8"),
        RawTacticalPuzzle("1k6/ppp5/8/7R/8/8/8/6K1 w - - 0 1", listOf("h5h8"), "Queenside Back-Rank Mate on b8"),
        RawTacticalPuzzle("1k6/ppp5/7R/8/8/8/8/6K1 w - - 0 1", listOf("h6h8"), "Queenside Back-Rank Mate on b8"),
        RawTacticalPuzzle("4k3/3p4/8/8/2B5/5Q2/8/6K1 w - - 0 1", listOf("f3f7"), "Scholar Pattern Queen Mate on f7"),
        RawTacticalPuzzle("4k3/3p4/8/3Q4/2B5/8/8/6K1 w - - 0 1", listOf("d5f7"), "Scholar Pattern Queen Mate on f7"),
        RawTacticalPuzzle("4k3/3p4/8/8/2B5/8/8/6K1 w - - 0 1", listOf("c4f7"), "Scholar Pattern Queen Mate on f7"),
        RawTacticalPuzzle("4k3/3p4/8/8/2B5/8/5Q2/6K1 w - - 0 1", listOf("f2f7"), "Scholar Pattern Queen Mate on f7"),
        RawTacticalPuzzle("4k3/3p4/8/8/2B2Q2/8/8/6K1 w - - 0 1", listOf("f4f7"), "Scholar Pattern Queen Mate on f7"),
        RawTacticalPuzzle("6k1/5p1p/7Q/8/8/8/1B6/6K1 w - - 0 1", listOf("h6g7"), "Fianchetto Battery Queen Mate on g7"),
        RawTacticalPuzzle("6k1/5p1p/8/4Q3/8/8/1B6/6K1 w - - 0 1", listOf("e5g7"), "Fianchetto Battery Queen Mate on g7"),
        RawTacticalPuzzle("6k1/5p1p/8/8/3Q4/8/1B6/6K1 w - - 0 1", listOf("d4g7"), "Fianchetto Battery Queen Mate on g7"),
        RawTacticalPuzzle("6k1/5p1p/8/8/8/2Q5/1B6/6K1 w - - 0 1", listOf("c3g7"), "Fianchetto Battery Queen Mate on g7"),
        RawTacticalPuzzle("6k1/5p1p/8/8/8/8/1B6/6K1 w - - 0 1", listOf("b2g7"), "Fianchetto Battery Queen Mate on g7"),
        RawTacticalPuzzle("6k1/5p1p/5Q2/8/8/8/1B6/6K1 w - - 0 1", listOf("f6g7"), "Fianchetto Battery Queen Mate on g7"),
        RawTacticalPuzzle("6k1/5pp1/8/7Q/8/3B4/8/6K1 w - - 0 1", listOf("h5h7"), "Diagonal Battery Queen Mate on h7"),
        RawTacticalPuzzle("6k1/5pp1/8/8/7Q/3B4/8/6K1 w - - 0 1", listOf("h4h7"), "Diagonal Battery Queen Mate on h7"),
        RawTacticalPuzzle("6k1/5pp1/8/8/8/3B4/8/6K1 w - - 0 1", listOf("d3h7"), "Diagonal Battery Queen Mate on h7"),
        RawTacticalPuzzle("6k1/5pp1/7Q/8/8/3B4/8/6K1 w - - 0 1", listOf("h6h7"), "Diagonal Battery Queen Mate on h7"),
        RawTacticalPuzzle("6k1/5pp1/8/8/4Q3/3B4/8/6K1 w - - 0 1", listOf("e4h7"), "Diagonal Battery Queen Mate on h7"),
        RawTacticalPuzzle("6k1/5pp1/8/5Q2/8/3B4/8/6K1 w - - 0 1", listOf("f5h7"), "Diagonal Battery Queen Mate on h7"),
        RawTacticalPuzzle("2k5/3p4/2B5/8/1Q6/8/8/6K1 w - - 0 1", listOf("b4b7"), "Queenside Battery Queen Mate on b7"),
        RawTacticalPuzzle("2k5/3p4/2B5/1Q6/8/8/8/6K1 w - - 0 1", listOf("b5b7"), "Queenside Battery Queen Mate on b7"),
        RawTacticalPuzzle("1k6/2p5/8/2B5/8/8/8/6K1 w - - 0 1", listOf("c5a7"), "Flank Queen Mate on a7"),
        RawTacticalPuzzle("6rk/6pp/8/4N3/8/8/8/6K1 w - - 0 1", listOf("e5f7"), "Knight Smothered Mate on f7"),
        RawTacticalPuzzle("6rk/6pp/8/6N1/8/8/8/6K1 w - - 0 1", listOf("g5f7"), "Knight Smothered Mate on f7"),
        RawTacticalPuzzle("6rk/6pp/3N4/8/8/8/8/6K1 w - - 0 1", listOf("d6f7"), "Knight Smothered Mate on f7"),
        RawTacticalPuzzle("6rk/6pp/7N/8/8/8/8/6K1 w - - 0 1", listOf("h6f7"), "Knight Smothered Mate on f7"),
        RawTacticalPuzzle("4rk2/5pp1/8/3N4/8/8/8/6K1 w - - 0 1", listOf("d5e7"), "Knight Smothered Mate on e7"),
        RawTacticalPuzzle("4rk2/5pp1/8/5N2/8/8/8/6K1 w - - 0 1", listOf("f5e7"), "Knight Smothered Mate on e7"),
        RawTacticalPuzzle("4rk2/5pp1/2N5/8/8/8/8/6K1 w - - 0 1", listOf("c6e7"), "Knight Smothered Mate on e7"),
        RawTacticalPuzzle("kr6/pp6/8/1N6/8/8/8/6K1 w - - 0 1", listOf("b5c7"), "Knight Smothered Mate on c7"),
        RawTacticalPuzzle("kr6/pp6/8/3N4/8/8/8/6K1 w - - 0 1", listOf("d5c7"), "Knight Smothered Mate on c7"),
        RawTacticalPuzzle("kr6/pp6/N7/8/8/8/8/6K1 w - - 0 1", listOf("a6c7"), "Knight Smothered Mate on c7"),
        RawTacticalPuzzle("kr6/pp6/4N3/8/8/8/8/6K1 w - - 0 1", listOf("e6c7"), "Knight Smothered Mate on c7"),
        RawTacticalPuzzle("2kr4/1pp5/8/N7/8/8/8/6K1 w - - 0 1", listOf("a5b7"), "Knight Smothered Mate on b7"),
        RawTacticalPuzzle("2kr4/1pp5/8/2N5/8/8/8/6K1 w - - 0 1", listOf("c5b7"), "Knight Smothered Mate on b7"),
        RawTacticalPuzzle("6rk/6pp/5N2/8/8/8/8/6K1 w - - 0 1", listOf("f6h7"), "Knight Smothered Mate on h7"),
        RawTacticalPuzzle("6k1/5N2/8/8/8/8/8/6K1 w - - 0 1", listOf("f7h6"), "Knight Smothered Mate on h6"),
        RawTacticalPuzzle("1rk5/3r4/8/8/2B2B2/8/8/6K1 w - - 0 1", listOf("c4a6"), "Boden Diagonal Bishop Mate on a6"),
        RawTacticalPuzzle("1rk5/3r4/8/8/5B2/8/8/5BK1 w - - 0 1", listOf("f1a6"), "Boden Diagonal Bishop Mate on a6"),
        RawTacticalPuzzle("1rk5/3r4/8/8/5B2/8/4B3/6K1 w - - 0 1", listOf("e2a6"), "Boden Diagonal Bishop Mate on a6"),
        RawTacticalPuzzle("1rk5/3r4/8/8/5B2/3B4/8/6K1 w - - 0 1", listOf("d3a6"), "Boden Diagonal Bishop Mate on a6"),
        RawTacticalPuzzle("1rk5/3r4/8/8/5B2/8/8/6K1 w - - 0 1", listOf("f4g5"), "Boden Diagonal Bishop Mate on g5"),
        RawTacticalPuzzle("7k/8/5PB1/8/8/8/8/6K1 w - - 0 1", listOf("f6f7"), "Pawn Checkmate via f7"),
        RawTacticalPuzzle("4k3/8/3BP3/8/8/8/8/6K1 w - - 0 1", listOf("e6e7"), "Pawn Checkmate via e7"),
        RawTacticalPuzzle("3k4/8/2BP4/8/8/8/8/6K1 w - - 0 1", listOf("d6d7"), "Pawn Checkmate via d7"),
        RawTacticalPuzzle("k7/8/1BP5/8/8/8/8/6K1 w - - 0 1", listOf("c6c7"), "Pawn Checkmate via c7")
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
            val cleanTheme = "Mate in 1"
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
