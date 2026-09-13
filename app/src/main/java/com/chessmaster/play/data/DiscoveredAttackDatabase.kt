package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object DiscoveredAttackDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("6k1/8/2q5/8/8/8/2B5/2R3K1 w - - 0 1", listOf("c2h7", "g8h8", "c1c6"), "Bishop Discovered Attack on Queen via h7"),
        RawTacticalPuzzle("4k3/8/2q5/8/8/8/2B5/2R3K1 w - - 0 1", listOf("c2g6", "e8f8", "c1c6"), "Bishop Discovered Attack on Queen via g6"),
        RawTacticalPuzzle("2k5/8/2q5/8/8/8/2B5/2R3K1 w - - 0 1", listOf("c2f5", "c8d8", "c1c6"), "Bishop Discovered Attack on Queen via f5"),
        RawTacticalPuzzle("k7/8/2q5/8/8/8/2B5/2R3K1 w - - 0 1", listOf("c2e4", "a8b8", "c1c6"), "Bishop Discovered Attack on Queen via e4"),
        RawTacticalPuzzle("5k2/8/2q5/8/8/2B5/8/2R3K1 w - - 0 1", listOf("c3b4", "f8g8", "c1c6"), "Bishop Discovered Attack on Queen via b4"),
        RawTacticalPuzzle("3k4/8/2q5/8/8/2B5/8/2R3K1 w - - 0 1", listOf("c3a5", "d8e8", "c1c6"), "Bishop Discovered Attack on Queen via a5"),
        RawTacticalPuzzle("4k3/8/2q5/8/2B5/8/8/2R3K1 w - - 0 1", listOf("c4b5", "e8f8", "c1c6"), "Bishop Discovered Attack on Queen via b5"),
        RawTacticalPuzzle("2k5/8/2q5/8/2B5/8/8/2R3K1 w - - 0 1", listOf("c4a6", "c8d8", "c1c6"), "Bishop Discovered Attack on Queen via a6"),
        RawTacticalPuzzle("k7/8/2q5/8/2B5/8/8/2R3K1 w - - 0 1", listOf("c4d5", "a8b8", "c1c6"), "Bishop Discovered Attack on Queen via d5"),
        RawTacticalPuzzle("6k1/2q5/8/8/8/8/2B5/2R3K1 w - - 0 1", listOf("c2h7", "g8h8", "c1c7"), "Bishop Discovered Attack on Queen via h7"),
        RawTacticalPuzzle("4k3/2q5/8/8/8/8/2B5/2R3K1 w - - 0 1", listOf("c2g6", "e8f8", "c1c7"), "Bishop Discovered Attack on Queen via g6"),
        RawTacticalPuzzle("2k5/2q5/8/8/8/8/2B5/2R3K1 w - - 0 1", listOf("c2f5", "c8d8", "c1c7"), "Bishop Discovered Attack on Queen via f5"),
        RawTacticalPuzzle("k7/2q5/8/8/8/8/2B5/2R3K1 w - - 0 1", listOf("c2e4", "a8b8", "c1c7"), "Bishop Discovered Attack on Queen via e4"),
        RawTacticalPuzzle("5k2/2q5/8/8/8/2B5/8/2R3K1 w - - 0 1", listOf("c3b4", "f8g8", "c1c7"), "Bishop Discovered Attack on Queen via b4"),
        RawTacticalPuzzle("3k4/2q5/8/8/8/2B5/8/2R3K1 w - - 0 1", listOf("c3a5", "d8e8", "c1c7"), "Bishop Discovered Attack on Queen via a5"),
        RawTacticalPuzzle("4k3/2q5/8/8/2B5/8/8/2R3K1 w - - 0 1", listOf("c4b5", "e8f8", "c1c7"), "Bishop Discovered Attack on Queen via b5"),
        RawTacticalPuzzle("2k5/2q5/8/8/2B5/8/8/2R3K1 w - - 0 1", listOf("c4a6", "c8d8", "c1c7"), "Bishop Discovered Attack on Queen via a6"),
        RawTacticalPuzzle("k7/2q5/8/8/2B5/8/8/2R3K1 w - - 0 1", listOf("c4d5", "a8b8", "c1c7"), "Bishop Discovered Attack on Queen via d5"),
        RawTacticalPuzzle("2q3k1/8/8/8/8/8/2B5/2R3K1 w - - 0 1", listOf("c2h7", "g8h8", "c1c8"), "Bishop Discovered Attack on Queen via h7"),
        RawTacticalPuzzle("2q1k3/8/8/8/8/8/2B5/2R3K1 w - - 0 1", listOf("c2g6", "e8f8", "c1c8"), "Bishop Discovered Attack on Queen via g6"),
        RawTacticalPuzzle("2q5/8/8/8/8/8/2B5/2R3K1 w - - 0 1", listOf("c2f5", "c8d8", "c1c8"), "Bishop Discovered Attack on Queen via f5"),
        RawTacticalPuzzle("k1q5/8/8/8/8/8/2B5/2R3K1 w - - 0 1", listOf("c2e4", "a8b8", "c1c8"), "Bishop Discovered Attack on Queen via e4"),
        RawTacticalPuzzle("2q2k2/8/8/8/8/2B5/8/2R3K1 w - - 0 1", listOf("c3b4", "f8g8", "c1c8"), "Bishop Discovered Attack on Queen via b4"),
        RawTacticalPuzzle("2qk4/8/8/8/8/2B5/8/2R3K1 w - - 0 1", listOf("c3a5", "d8e8", "c1c8"), "Bishop Discovered Attack on Queen via a5"),
        RawTacticalPuzzle("2q1k3/8/8/8/2B5/8/8/2R3K1 w - - 0 1", listOf("c4b5", "e8f8", "c1c8"), "Bishop Discovered Attack on Queen via b5"),
        RawTacticalPuzzle("2q5/8/8/8/2B5/8/8/2R3K1 w - - 0 1", listOf("c4a6", "c8d8", "c1c8"), "Bishop Discovered Attack on Queen via a6"),
        RawTacticalPuzzle("k1q5/8/8/8/2B5/8/8/2R3K1 w - - 0 1", listOf("c4d5", "a8b8", "c1c8"), "Bishop Discovered Attack on Queen via d5"),
        RawTacticalPuzzle("5k2/8/3q4/8/8/8/3B4/3R2K1 w - - 0 1", listOf("d2b4", "f8g8", "d1d6"), "Bishop Discovered Attack on Queen via b4"),
        RawTacticalPuzzle("3k4/8/3q4/8/8/8/3B4/3R2K1 w - - 0 1", listOf("d2a5", "d8e8", "d1d6"), "Bishop Discovered Attack on Queen via a5"),
        RawTacticalPuzzle("7k/8/3q4/8/8/8/3B4/3R2K1 w - - 0 1", listOf("d2c3", "h8g8", "d1d6"), "Bishop Discovered Attack on Queen via c3"),
        RawTacticalPuzzle("6k1/8/3q4/8/8/3B4/8/3R2K1 w - - 0 1", listOf("d3h7", "g8h8", "d1d6"), "Bishop Discovered Attack on Queen via h7"),
        RawTacticalPuzzle("4k3/8/3q4/8/8/3B4/8/3R2K1 w - - 0 1", listOf("d3g6", "e8f8", "d1d6"), "Bishop Discovered Attack on Queen via g6"),
        RawTacticalPuzzle("2k5/8/3q4/8/8/3B4/8/3R2K1 w - - 0 1", listOf("d3f5", "c8d8", "d1d6"), "Bishop Discovered Attack on Queen via f5"),
        RawTacticalPuzzle("k7/8/3q4/8/8/3B4/8/3R2K1 w - - 0 1", listOf("d3e4", "a8b8", "d1d6"), "Bishop Discovered Attack on Queen via e4"),
        RawTacticalPuzzle("5k2/8/3q4/8/3B4/8/8/3R2K1 w - - 0 1", listOf("d4c5", "f8g8", "d1d6"), "Bishop Discovered Attack on Queen via c5"),
        RawTacticalPuzzle("3k4/8/3q4/8/3B4/8/8/3R2K1 w - - 0 1", listOf("d4f6", "d8e8", "d1d6"), "Bishop Discovered Attack on Queen via f6"),
        RawTacticalPuzzle("5k2/3q4/8/8/8/8/3B4/3R2K1 w - - 0 1", listOf("d2b4", "f8g8", "d1d7"), "Bishop Discovered Attack on Queen via b4"),
        RawTacticalPuzzle("3k4/3q4/8/8/8/8/3B4/3R2K1 w - - 0 1", listOf("d2a5", "d8e8", "d1d7"), "Bishop Discovered Attack on Queen via a5"),
        RawTacticalPuzzle("7k/3q4/8/8/8/8/3B4/3R2K1 w - - 0 1", listOf("d2c3", "h8g8", "d1d7"), "Bishop Discovered Attack on Queen via c3"),
        RawTacticalPuzzle("6k1/3q4/8/8/8/3B4/8/3R2K1 w - - 0 1", listOf("d3h7", "g8h8", "d1d7"), "Bishop Discovered Attack on Queen via h7"),
        RawTacticalPuzzle("4k3/3q4/8/8/8/3B4/8/3R2K1 w - - 0 1", listOf("d3g6", "e8f8", "d1d7"), "Bishop Discovered Attack on Queen via g6"),
        RawTacticalPuzzle("2k5/3q4/8/8/8/3B4/8/3R2K1 w - - 0 1", listOf("d3f5", "c8d8", "d1d7"), "Bishop Discovered Attack on Queen via f5"),
        RawTacticalPuzzle("k7/3q4/8/8/8/3B4/8/3R2K1 w - - 0 1", listOf("d3e4", "a8b8", "d1d7"), "Bishop Discovered Attack on Queen via e4"),
        RawTacticalPuzzle("5k2/3q4/8/8/3B4/8/8/3R2K1 w - - 0 1", listOf("d4c5", "f8g8", "d1d7"), "Bishop Discovered Attack on Queen via c5"),
        RawTacticalPuzzle("3k4/3q4/8/8/3B4/8/8/3R2K1 w - - 0 1", listOf("d4f6", "d8e8", "d1d7"), "Bishop Discovered Attack on Queen via f6"),
        RawTacticalPuzzle("3q1k2/8/8/8/8/8/3B4/3R2K1 w - - 0 1", listOf("d2b4", "f8g8", "d1d8"), "Bishop Discovered Attack on Queen via b4"),
        RawTacticalPuzzle("3q4/8/8/8/8/8/3B4/3R2K1 w - - 0 1", listOf("d2a5", "d8e8", "d1d8"), "Bishop Discovered Attack on Queen via a5"),
        RawTacticalPuzzle("3q3k/8/8/8/8/8/3B4/3R2K1 w - - 0 1", listOf("d2c3", "h8g8", "d1d8"), "Bishop Discovered Attack on Queen via c3"),
        RawTacticalPuzzle("3q2k1/8/8/8/8/3B4/8/3R2K1 w - - 0 1", listOf("d3h7", "g8h8", "d1d8"), "Bishop Discovered Attack on Queen via h7"),
        RawTacticalPuzzle("3qk3/8/8/8/8/3B4/8/3R2K1 w - - 0 1", listOf("d3g6", "e8f8", "d1d8"), "Bishop Discovered Attack on Queen via g6"),
        RawTacticalPuzzle("k2q4/8/8/8/8/3B4/8/3R2K1 w - - 0 1", listOf("d3e4", "a8b8", "d1d8"), "Bishop Discovered Attack on Queen via e4"),
        RawTacticalPuzzle("3q1k2/8/8/8/3B4/8/8/3R2K1 w - - 0 1", listOf("d4c5", "f8g8", "d1d8"), "Bishop Discovered Attack on Queen via c5"),
        RawTacticalPuzzle("3q4/8/8/8/3B4/8/8/3R2K1 w - - 0 1", listOf("d4f6", "d8e8", "d1d8"), "Bishop Discovered Attack on Queen via f6"),
        RawTacticalPuzzle("4k3/8/4q3/8/8/8/4B3/4R1K1 w - - 0 1", listOf("e2b5", "e8f8", "e1e6"), "Bishop Discovered Attack on Queen via b5"),
        RawTacticalPuzzle("2k5/8/4q3/8/8/8/4B3/4R1K1 w - - 0 1", listOf("e2a6", "c8d8", "e1e6"), "Bishop Discovered Attack on Queen via a6"),
        RawTacticalPuzzle("6k1/8/4q3/8/8/8/4B3/4R1K1 w - - 0 1", listOf("e2c4", "g8h8", "e1e6"), "Bishop Discovered Attack on Queen via c4"),
        RawTacticalPuzzle("k7/8/4q3/8/8/8/4B3/4R1K1 w - - 0 1", listOf("e2f3", "a8b8", "e1e6"), "Bishop Discovered Attack on Queen via f3"),
        RawTacticalPuzzle("5k2/8/4q3/8/8/4B3/8/4R1K1 w - - 0 1", listOf("e3c5", "f8g8", "e1e6"), "Bishop Discovered Attack on Queen via c5"),
        RawTacticalPuzzle("6k1/8/4q3/8/4B3/8/8/4R1K1 w - - 0 1", listOf("e4h7", "g8h8", "e1e6"), "Bishop Discovered Attack on Queen via h7"),
        RawTacticalPuzzle("4k3/8/4q3/8/4B3/8/8/4R1K1 w - - 0 1", listOf("e4g6", "e8f8", "e1e6"), "Bishop Discovered Attack on Queen via g6"),
        RawTacticalPuzzle("2k5/8/4q3/8/4B3/8/8/4R1K1 w - - 0 1", listOf("e4f5", "c8d8", "e1e6"), "Bishop Discovered Attack on Queen via f5"),
        RawTacticalPuzzle("3k4/8/4q3/4B3/8/8/8/4R1K1 w - - 0 1", listOf("e5f6", "d8e8", "e1e6"), "Bishop Discovered Attack on Queen via f6"),
        RawTacticalPuzzle("4k3/4q3/8/8/8/8/4B3/4R1K1 w - - 0 1", listOf("e2b5", "e8f8", "e1e7"), "Bishop Discovered Attack on Queen via b5"),
        RawTacticalPuzzle("2k5/4q3/8/8/8/8/4B3/4R1K1 w - - 0 1", listOf("e2a6", "c8d8", "e1e7"), "Bishop Discovered Attack on Queen via a6"),
        RawTacticalPuzzle("6k1/4q3/8/8/8/8/4B3/4R1K1 w - - 0 1", listOf("e2c4", "g8h8", "e1e7"), "Bishop Discovered Attack on Queen via c4"),
        RawTacticalPuzzle("k7/4q3/8/8/8/8/4B3/4R1K1 w - - 0 1", listOf("e2f3", "a8b8", "e1e7"), "Bishop Discovered Attack on Queen via f3"),
        RawTacticalPuzzle("5k2/4q3/8/8/8/4B3/8/4R1K1 w - - 0 1", listOf("e3c5", "f8g8", "e1e7"), "Bishop Discovered Attack on Queen via c5"),
        RawTacticalPuzzle("6k1/4q3/8/8/4B3/8/8/4R1K1 w - - 0 1", listOf("e4h7", "g8h8", "e1e7"), "Bishop Discovered Attack on Queen via h7"),
        RawTacticalPuzzle("4k3/4q3/8/8/4B3/8/8/4R1K1 w - - 0 1", listOf("e4g6", "e8f8", "e1e7"), "Bishop Discovered Attack on Queen via g6"),
        RawTacticalPuzzle("2k5/4q3/8/8/4B3/8/8/4R1K1 w - - 0 1", listOf("e4f5", "c8d8", "e1e7"), "Bishop Discovered Attack on Queen via f5"),
        RawTacticalPuzzle("3k4/4q3/8/4B3/8/8/8/4R1K1 w - - 0 1", listOf("e5f6", "d8e8", "e1e7"), "Bishop Discovered Attack on Queen via f6"),
        RawTacticalPuzzle("4q3/8/8/8/8/8/4B3/4R1K1 w - - 0 1", listOf("e2b5", "e8f8", "e1e8"), "Bishop Discovered Attack on Queen via b5"),
        RawTacticalPuzzle("2k1q3/8/8/8/8/8/4B3/4R1K1 w - - 0 1", listOf("e2a6", "c8d8", "e1e8"), "Bishop Discovered Attack on Queen via a6"),
        RawTacticalPuzzle("4q1k1/8/8/8/8/8/4B3/4R1K1 w - - 0 1", listOf("e2c4", "g8h8", "e1e8"), "Bishop Discovered Attack on Queen via c4"),
        RawTacticalPuzzle("k3q3/8/8/8/8/8/4B3/4R1K1 w - - 0 1", listOf("e2f3", "a8b8", "e1e8"), "Bishop Discovered Attack on Queen via f3"),
        RawTacticalPuzzle("4qk2/8/8/8/8/4B3/8/4R1K1 w - - 0 1", listOf("e3c5", "f8g8", "e1e8"), "Bishop Discovered Attack on Queen via c5"),
        RawTacticalPuzzle("4q1k1/8/8/8/4B3/8/8/4R1K1 w - - 0 1", listOf("e4h7", "g8h8", "e1e8"), "Bishop Discovered Attack on Queen via h7"),
        RawTacticalPuzzle("4q3/8/8/8/4B3/8/8/4R1K1 w - - 0 1", listOf("e4g6", "e8f8", "e1e8"), "Bishop Discovered Attack on Queen via g6"),
        RawTacticalPuzzle("2k1q3/8/8/8/4B3/8/8/4R1K1 w - - 0 1", listOf("e4f5", "c8d8", "e1e8"), "Bishop Discovered Attack on Queen via f5"),
        RawTacticalPuzzle("5k2/8/5q2/8/8/8/5B2/5RK1 w - - 0 1", listOf("f2c5", "f8g8", "f1f6"), "Bishop Discovered Attack on Queen via c5"),
        RawTacticalPuzzle("1k6/8/5q2/8/8/8/5B2/5RK1 w - - 0 1", listOf("f2g3", "b8c8", "f1f6"), "Bishop Discovered Attack on Queen via g3"),
        RawTacticalPuzzle("3k4/8/5q2/8/8/8/5B2/5RK1 w - - 0 1", listOf("f2h4", "d8e8", "f1f6"), "Bishop Discovered Attack on Queen via h4"),
        RawTacticalPuzzle("6k1/8/5q2/8/8/5B2/8/5RK1 w - - 0 1", listOf("f3d5", "g8h8", "f1f6"), "Bishop Discovered Attack on Queen via d5"),
        RawTacticalPuzzle("2k5/8/5q2/8/8/5B2/8/5RK1 w - - 0 1", listOf("f3g4", "c8d8", "f1f6"), "Bishop Discovered Attack on Queen via g4"),
        RawTacticalPuzzle("4k3/8/5q2/8/8/5B2/8/5RK1 w - - 0 1", listOf("f3h5", "e8f8", "f1f6"), "Bishop Discovered Attack on Queen via h5"),
        RawTacticalPuzzle("6k1/8/5q2/5B2/8/8/8/5RK1 w - - 0 1", listOf("f5h7", "g8h8", "f1f6"), "Bishop Discovered Attack on Queen via h7"),
        RawTacticalPuzzle("4k3/8/5q2/5B2/8/8/8/5RK1 w - - 0 1", listOf("f5g6", "e8f8", "f1f6"), "Bishop Discovered Attack on Queen via g6"),
        RawTacticalPuzzle("k7/8/5q2/5B2/8/8/8/5RK1 w - - 0 1", listOf("f5e4", "a8b8", "f1f6"), "Bishop Discovered Attack on Queen via e4"),
        RawTacticalPuzzle("5k2/5q2/8/8/8/8/5B2/5RK1 w - - 0 1", listOf("f2c5", "f8g8", "f1f7"), "Bishop Discovered Attack on Queen via c5"),
        RawTacticalPuzzle("1k6/5q2/8/8/8/8/5B2/5RK1 w - - 0 1", listOf("f2g3", "b8c8", "f1f7"), "Bishop Discovered Attack on Queen via g3"),
        RawTacticalPuzzle("3k4/5q2/8/8/8/8/5B2/5RK1 w - - 0 1", listOf("f2h4", "d8e8", "f1f7"), "Bishop Discovered Attack on Queen via h4"),
        RawTacticalPuzzle("6k1/5q2/8/8/8/5B2/8/5RK1 w - - 0 1", listOf("f3d5", "g8h8", "f1f7"), "Bishop Discovered Attack on Queen via d5"),
        RawTacticalPuzzle("2k5/5q2/8/8/8/5B2/8/5RK1 w - - 0 1", listOf("f3g4", "c8d8", "f1f7"), "Bishop Discovered Attack on Queen via g4"),
        RawTacticalPuzzle("4k3/5q2/8/8/8/5B2/8/5RK1 w - - 0 1", listOf("f3h5", "e8f8", "f1f7"), "Bishop Discovered Attack on Queen via h5"),
        RawTacticalPuzzle("6k1/5q2/8/5B2/8/8/8/5RK1 w - - 0 1", listOf("f5h7", "g8h8", "f1f7"), "Bishop Discovered Attack on Queen via h7"),
        RawTacticalPuzzle("4k3/5q2/8/5B2/8/8/8/5RK1 w - - 0 1", listOf("f5g6", "e8f8", "f1f7"), "Bishop Discovered Attack on Queen via g6"),
        RawTacticalPuzzle("k7/5q2/8/5B2/8/8/8/5RK1 w - - 0 1", listOf("f5e4", "a8b8", "f1f7"), "Bishop Discovered Attack on Queen via e4"),
        RawTacticalPuzzle("1k3q2/8/8/8/8/8/5B2/5RK1 w - - 0 1", listOf("f2g3", "b8c8", "f1f8"), "Bishop Discovered Attack on Queen via g3"),
        RawTacticalPuzzle("3k1q2/8/8/8/8/8/5B2/5RK1 w - - 0 1", listOf("f2h4", "d8e8", "f1f8"), "Bishop Discovered Attack on Queen via h4"),
        RawTacticalPuzzle("5qk1/8/8/8/8/5B2/8/5RK1 w - - 0 1", listOf("f3d5", "g8h8", "f1f8"), "Bishop Discovered Attack on Queen via d5")
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
            val cleanTheme = "Discovered Attack"
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
