package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object SmotheredMateDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("8/8/8/8/8/8/pp6/kbN4K w - - 0 1", listOf("c1b3"), "Pure Smothered Mate on a1"),
        RawTacticalPuzzle("8/8/8/8/8/8/pp1N4/kb5K w - - 0 1", listOf("d2b3"), "Pure Smothered Mate on a1"),
        RawTacticalPuzzle("8/8/8/8/3N4/8/pp6/kb5K w - - 0 1", listOf("d4b3"), "Pure Smothered Mate on a1"),
        RawTacticalPuzzle("8/8/8/N7/8/8/pp6/kb5K w - - 0 1", listOf("a5b3"), "Pure Smothered Mate on a1"),
        RawTacticalPuzzle("8/8/8/2N5/8/8/pp6/kb5K w - - 0 1", listOf("c5b3"), "Pure Smothered Mate on a1"),
        RawTacticalPuzzle("8/8/8/8/8/8/ppp5/rkbN3K w - - 0 1", listOf("d1c3"), "Pure Smothered Mate on b1"),
        RawTacticalPuzzle("8/8/8/8/8/8/ppp1N3/rkb4K w - - 0 1", listOf("e2c3"), "Pure Smothered Mate on b1"),
        RawTacticalPuzzle("8/8/8/8/N7/8/ppp5/rkb4K w - - 0 1", listOf("a4c3"), "Pure Smothered Mate on b1"),
        RawTacticalPuzzle("8/8/8/8/4N3/8/ppp5/rkb4K w - - 0 1", listOf("e4c3"), "Pure Smothered Mate on b1"),
        RawTacticalPuzzle("8/8/8/1N6/8/8/ppp5/rkb4K w - - 0 1", listOf("b5c3"), "Pure Smothered Mate on b1"),
        RawTacticalPuzzle("8/8/8/3N4/8/8/ppp5/rkb4K w - - 0 1", listOf("d5c3"), "Pure Smothered Mate on b1"),
        RawTacticalPuzzle("8/8/8/8/8/8/2ppp3/KNbkr3 w - - 0 1", listOf("b1c3"), "Pure Smothered Mate on d1"),
        RawTacticalPuzzle("8/8/8/8/8/8/N1ppp3/K1bkr3 w - - 0 1", listOf("a2c3"), "Pure Smothered Mate on d1"),
        RawTacticalPuzzle("8/8/8/8/8/8/3ppp2/K1Nbkr2 w - - 0 1", listOf("c1d3"), "Pure Smothered Mate on e1"),
        RawTacticalPuzzle("8/8/8/8/8/8/1N1ppp2/K2bkr2 w - - 0 1", listOf("b2d3"), "Pure Smothered Mate on e1"),
        RawTacticalPuzzle("8/8/8/8/1N6/8/3ppp2/K2bkr2 w - - 0 1", listOf("b4d3"), "Pure Smothered Mate on e1"),
        RawTacticalPuzzle("8/8/8/8/5N2/8/3ppp2/K2bkr2 w - - 0 1", listOf("f4d3"), "Pure Smothered Mate on e1"),
        RawTacticalPuzzle("8/8/8/2N5/8/8/3ppp2/K2bkr2 w - - 0 1", listOf("c5d3"), "Pure Smothered Mate on e1"),
        RawTacticalPuzzle("8/8/8/4N3/8/8/3ppp2/K2bkr2 w - - 0 1", listOf("e5d3"), "Pure Smothered Mate on e1"),
        RawTacticalPuzzle("8/8/8/8/8/8/4ppp1/K2Nbkr1 w - - 0 1", listOf("d1e3"), "Pure Smothered Mate on f1"),
        RawTacticalPuzzle("8/8/8/8/8/8/2N1ppp1/K3bkr1 w - - 0 1", listOf("c2e3"), "Pure Smothered Mate on f1"),
        RawTacticalPuzzle("8/8/8/8/2N5/8/4ppp1/K3bkr1 w - - 0 1", listOf("c4e3"), "Pure Smothered Mate on f1"),
        RawTacticalPuzzle("8/8/8/8/6N1/8/4ppp1/K3bkr1 w - - 0 1", listOf("g4e3"), "Pure Smothered Mate on f1"),
        RawTacticalPuzzle("8/8/8/3N4/8/8/4ppp1/K3bkr1 w - - 0 1", listOf("d5e3"), "Pure Smothered Mate on f1"),
        RawTacticalPuzzle("8/8/8/5N2/8/8/4ppp1/K3bkr1 w - - 0 1", listOf("f5e3"), "Pure Smothered Mate on f1"),
        RawTacticalPuzzle("8/8/8/8/8/8/5ppp/K3Nbkr w - - 0 1", listOf("e1f3"), "Pure Smothered Mate on g1"),
        RawTacticalPuzzle("8/8/8/8/8/8/3N1ppp/K4bkr w - - 0 1", listOf("d2f3"), "Pure Smothered Mate on g1"),
        RawTacticalPuzzle("8/8/8/8/3N4/8/5ppp/K4bkr w - - 0 1", listOf("d4f3"), "Pure Smothered Mate on g1"),
        RawTacticalPuzzle("8/8/8/8/7N/8/5ppp/K4bkr w - - 0 1", listOf("h4f3"), "Pure Smothered Mate on g1"),
        RawTacticalPuzzle("8/8/8/4N3/8/8/5ppp/K4bkr w - - 0 1", listOf("e5f3"), "Pure Smothered Mate on g1"),
        RawTacticalPuzzle("8/8/8/6N1/8/8/5ppp/K4bkr w - - 0 1", listOf("g5f3"), "Pure Smothered Mate on g1"),
        RawTacticalPuzzle("8/8/8/8/8/8/6pp/K4Nbk w - - 0 1", listOf("f1g3"), "Pure Smothered Mate on h1"),
        RawTacticalPuzzle("8/8/8/8/8/8/4N1pp/K5bk w - - 0 1", listOf("e2g3"), "Pure Smothered Mate on h1"),
        RawTacticalPuzzle("8/8/8/8/4N3/8/6pp/K5bk w - - 0 1", listOf("e4g3"), "Pure Smothered Mate on h1"),
        RawTacticalPuzzle("8/8/8/5N2/8/8/6pp/K5bk w - - 0 1", listOf("f5g3"), "Pure Smothered Mate on h1"),
        RawTacticalPuzzle("8/8/8/7N/8/8/6pp/K5bk w - - 0 1", listOf("h5g3"), "Pure Smothered Mate on h1"),
        RawTacticalPuzzle("8/8/8/8/8/pp6/kpN5/rb5K w - - 0 1", listOf("c2b4"), "Pure Smothered Mate on a2"),
        RawTacticalPuzzle("8/8/8/8/8/pp1N4/kp6/rb5K w - - 0 1", listOf("d3b4"), "Pure Smothered Mate on a2"),
        RawTacticalPuzzle("8/8/8/3N4/8/pp6/kp6/rb5K w - - 0 1", listOf("d5b4"), "Pure Smothered Mate on a2"),
        RawTacticalPuzzle("8/8/N7/8/8/pp6/kp6/rb5K w - - 0 1", listOf("a6b4"), "Pure Smothered Mate on a2"),
        RawTacticalPuzzle("8/8/2N5/8/8/pp6/kp6/rb5K w - - 0 1", listOf("c6b4"), "Pure Smothered Mate on a2"),
        RawTacticalPuzzle("8/8/8/8/8/6pp/5Npk/K5br w - - 0 1", listOf("f2g4"), "Pure Smothered Mate on h2"),
        RawTacticalPuzzle("8/8/8/8/8/4N1pp/6pk/K5br w - - 0 1", listOf("e3g4"), "Pure Smothered Mate on h2"),
        RawTacticalPuzzle("8/8/8/4N3/8/6pp/6pk/K5br w - - 0 1", listOf("e5g4"), "Pure Smothered Mate on h2"),
        RawTacticalPuzzle("8/8/5N2/8/8/6pp/6pk/K5br w - - 0 1", listOf("f6g4"), "Pure Smothered Mate on h2"),
        RawTacticalPuzzle("8/8/7N/8/8/6pp/6pk/K5br w - - 0 1", listOf("h6g4"), "Pure Smothered Mate on h2"),
        RawTacticalPuzzle("8/8/8/8/6pp/6pk/4N1pp/K7 w - - 0 1", listOf("e2f4"), "Pure Smothered Mate on h3"),
        RawTacticalPuzzle("8/8/8/8/6pp/3N2pk/6pp/K7 w - - 0 1", listOf("d3f4"), "Pure Smothered Mate on h3"),
        RawTacticalPuzzle("8/8/8/3N4/6pp/6pk/6pp/K7 w - - 0 1", listOf("d5f4"), "Pure Smothered Mate on h3"),
        RawTacticalPuzzle("8/8/8/7N/6pp/6pk/6pp/K7 w - - 0 1", listOf("h5f4"), "Pure Smothered Mate on h3"),
        RawTacticalPuzzle("8/8/4N3/8/6pp/6pk/6pp/K7 w - - 0 1", listOf("e6f4"), "Pure Smothered Mate on h3"),
        RawTacticalPuzzle("8/8/6N1/8/6pp/6pk/6pp/K7 w - - 0 1", listOf("g6f4"), "Pure Smothered Mate on h3"),
        RawTacticalPuzzle("8/8/8/8/6pp/5Npk/6pp/K7 w - - 0 1", listOf("f3g5"), "Pure Smothered Mate on h3"),
        RawTacticalPuzzle("8/8/8/8/4N1pp/6pk/6pp/K7 w - - 0 1", listOf("e4g5"), "Pure Smothered Mate on h3"),
        RawTacticalPuzzle("8/5N2/8/8/6pp/6pk/6pp/K7 w - - 0 1", listOf("f7g5"), "Pure Smothered Mate on h3"),
        RawTacticalPuzzle("8/7N/8/8/6pp/6pk/6pp/K7 w - - 0 1", listOf("h7g5"), "Pure Smothered Mate on h3"),
        RawTacticalPuzzle("8/8/8/pp6/kp6/pp1N4/8/K7 w - - 0 1", listOf("d3c5"), "Pure Smothered Mate on a4"),
        RawTacticalPuzzle("8/8/8/pp6/kp2N3/pp6/8/K7 w - - 0 1", listOf("e4c5"), "Pure Smothered Mate on a4"),
        RawTacticalPuzzle("8/8/N7/pp6/kp6/pp6/8/K7 w - - 0 1", listOf("a6c5"), "Pure Smothered Mate on a4"),
        RawTacticalPuzzle("8/8/4N3/pp6/kp6/pp6/8/K7 w - - 0 1", listOf("e6c5"), "Pure Smothered Mate on a4"),
        RawTacticalPuzzle("8/1N6/8/pp6/kp6/pp6/8/K7 w - - 0 1", listOf("b7c5"), "Pure Smothered Mate on a4"),
        RawTacticalPuzzle("8/3N4/8/pp6/kp6/pp6/8/K7 w - - 0 1", listOf("d7c5"), "Pure Smothered Mate on a4"),
        RawTacticalPuzzle("8/8/8/pp6/kpN5/pp6/8/K7 w - - 0 1", listOf("c4b6"), "Pure Smothered Mate on a4"),
        RawTacticalPuzzle("8/8/8/pp1N4/kp6/pp6/8/K7 w - - 0 1", listOf("d5b6"), "Pure Smothered Mate on a4"),
        RawTacticalPuzzle("N7/8/8/pp6/kp6/pp6/8/K7 w - - 0 1", listOf("a8b6"), "Pure Smothered Mate on a4"),
        RawTacticalPuzzle("2N5/8/8/pp6/kp6/pp6/8/K7 w - - 0 1", listOf("c8b6"), "Pure Smothered Mate on a4"),
        RawTacticalPuzzle("8/8/8/6pp/6pk/4N1pp/8/K7 w - - 0 1", listOf("e3f5"), "Pure Smothered Mate on h4"),
        RawTacticalPuzzle("8/8/8/6pp/3N2pk/6pp/8/K7 w - - 0 1", listOf("d4f5"), "Pure Smothered Mate on h4"),
        RawTacticalPuzzle("8/8/3N4/6pp/6pk/6pp/8/K7 w - - 0 1", listOf("d6f5"), "Pure Smothered Mate on h4"),
        RawTacticalPuzzle("8/8/7N/6pp/6pk/6pp/8/K7 w - - 0 1", listOf("h6f5"), "Pure Smothered Mate on h4"),
        RawTacticalPuzzle("8/4N3/8/6pp/6pk/6pp/8/K7 w - - 0 1", listOf("e7f5"), "Pure Smothered Mate on h4"),
        RawTacticalPuzzle("8/6N1/8/6pp/6pk/6pp/8/K7 w - - 0 1", listOf("g7f5"), "Pure Smothered Mate on h4"),
        RawTacticalPuzzle("8/8/8/6pp/5Npk/6pp/8/K7 w - - 0 1", listOf("f4g6"), "Pure Smothered Mate on h4"),
        RawTacticalPuzzle("8/8/8/4N1pp/6pk/6pp/8/K7 w - - 0 1", listOf("e5g6"), "Pure Smothered Mate on h4"),
        RawTacticalPuzzle("5N2/8/8/6pp/6pk/6pp/8/K7 w - - 0 1", listOf("f8g6"), "Pure Smothered Mate on h4"),
        RawTacticalPuzzle("7N/8/8/6pp/6pk/6pp/8/K7 w - - 0 1", listOf("h8g6"), "Pure Smothered Mate on h4"),
        RawTacticalPuzzle("8/8/pp6/kp6/pp1N4/8/8/K7 w - - 0 1", listOf("d4c6"), "Pure Smothered Mate on a5"),
        RawTacticalPuzzle("8/8/pp6/kp2N3/pp6/8/8/K7 w - - 0 1", listOf("e5c6"), "Pure Smothered Mate on a5"),
        RawTacticalPuzzle("8/N7/pp6/kp6/pp6/8/8/K7 w - - 0 1", listOf("a7c6"), "Pure Smothered Mate on a5"),
        RawTacticalPuzzle("8/4N3/pp6/kp6/pp6/8/8/K7 w - - 0 1", listOf("e7c6"), "Pure Smothered Mate on a5"),
        RawTacticalPuzzle("1N6/8/pp6/kp6/pp6/8/8/K7 w - - 0 1", listOf("b8c6"), "Pure Smothered Mate on a5"),
        RawTacticalPuzzle("3N4/8/pp6/kp6/pp6/8/8/K7 w - - 0 1", listOf("d8c6"), "Pure Smothered Mate on a5"),
        RawTacticalPuzzle("8/8/pp6/kpN5/pp6/8/8/K7 w - - 0 1", listOf("c5b7"), "Pure Smothered Mate on a5"),
        RawTacticalPuzzle("8/8/pp1N4/kp6/pp6/8/8/K7 w - - 0 1", listOf("d6b7"), "Pure Smothered Mate on a5"),
        RawTacticalPuzzle("8/8/6pp/6pk/4N1pp/8/8/K7 w - - 0 1", listOf("e4f6"), "Pure Smothered Mate on h5"),
        RawTacticalPuzzle("8/8/6pp/3N2pk/6pp/8/8/K7 w - - 0 1", listOf("d5f6"), "Pure Smothered Mate on h5"),
        RawTacticalPuzzle("8/3N4/6pp/6pk/6pp/8/8/K7 w - - 0 1", listOf("d7f6"), "Pure Smothered Mate on h5"),
        RawTacticalPuzzle("8/7N/6pp/6pk/6pp/8/8/K7 w - - 0 1", listOf("h7f6"), "Pure Smothered Mate on h5"),
        RawTacticalPuzzle("4N3/8/6pp/6pk/6pp/8/8/K7 w - - 0 1", listOf("e8f6"), "Pure Smothered Mate on h5"),
        RawTacticalPuzzle("6N1/8/6pp/6pk/6pp/8/8/K7 w - - 0 1", listOf("g8f6"), "Pure Smothered Mate on h5"),
        RawTacticalPuzzle("8/8/6pp/5Npk/6pp/8/8/K7 w - - 0 1", listOf("f5g7"), "Pure Smothered Mate on h5"),
        RawTacticalPuzzle("8/8/4N1pp/6pk/6pp/8/8/K7 w - - 0 1", listOf("e6g7"), "Pure Smothered Mate on h5"),
        RawTacticalPuzzle("8/pp6/kp6/pp1N4/8/8/8/K7 w - - 0 1", listOf("d5c7"), "Pure Smothered Mate on a6"),
        RawTacticalPuzzle("8/pp6/kp2N3/pp6/8/8/8/K7 w - - 0 1", listOf("e6c7"), "Pure Smothered Mate on a6"),
        RawTacticalPuzzle("N7/pp6/kp6/pp6/8/8/8/K7 w - - 0 1", listOf("a8c7"), "Pure Smothered Mate on a6"),
        RawTacticalPuzzle("4N3/pp6/kp6/pp6/8/8/8/K7 w - - 0 1", listOf("e8c7"), "Pure Smothered Mate on a6"),
        RawTacticalPuzzle("8/pp6/kpN5/pp6/8/8/8/K7 w - - 0 1", listOf("c6b8"), "Pure Smothered Mate on a6"),
        RawTacticalPuzzle("8/pp1N4/kp6/pp6/8/8/8/K7 w - - 0 1", listOf("d7b8"), "Pure Smothered Mate on a6"),
        RawTacticalPuzzle("8/6pp/6pk/4N1pp/8/8/8/K7 w - - 0 1", listOf("e5f7"), "Pure Smothered Mate on h6"),
        RawTacticalPuzzle("8/6pp/3N2pk/6pp/8/8/8/K7 w - - 0 1", listOf("d6f7"), "Pure Smothered Mate on h6")
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
            val cleanTheme = "Smothered Mate"
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
