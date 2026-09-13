package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object ClearanceDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("4k3/p6q/8/8/8/8/1P6/KB6 w - - 0 1", listOf("b2b3", "a7a6", "b1h7"), "Diagonal Clearance via b3"),
        RawTacticalPuzzle("4k3/p7/7q/8/8/8/1P6/K1B5 w - - 0 1", listOf("b2b3", "a7a6", "c1h6"), "Diagonal Clearance via b3"),
        RawTacticalPuzzle("4k3/p7/7q/8/8/8/1P1B4/K7 w - - 0 1", listOf("b2b3", "a7a6", "d2h6"), "Diagonal Clearance via b3"),
        RawTacticalPuzzle("4k3/p6q/8/8/8/8/1PB5/K7 w - - 0 1", listOf("b2b3", "a7a6", "c2h7"), "Diagonal Clearance via b3"),
        RawTacticalPuzzle("4kq2/p7/8/8/8/B7/1P6/K7 w - - 0 1", listOf("b2b3", "a7a6", "a3f8"), "Diagonal Clearance via b3"),
        RawTacticalPuzzle("2q1k3/p7/8/8/8/7B/1P6/K7 w - - 0 1", listOf("b2b3", "a7a6", "h3c8"), "Diagonal Clearance via b3"),
        RawTacticalPuzzle("4k3/p6q/8/8/8/3B4/1P6/K7 w - - 0 1", listOf("b2b3", "a7a6", "d3h7"), "Diagonal Clearance via b3"),
        RawTacticalPuzzle("4k3/p7/8/8/8/4B3/1P6/K7 w - - 0 1", listOf("b2b3", "a7a6", "e3a7"), "Diagonal Clearance via b3"),
        RawTacticalPuzzle("4k3/p7/7q/8/8/4B3/1P6/K7 w - - 0 1", listOf("b2b3", "a7a6", "e3h6"), "Diagonal Clearance via b3"),
        RawTacticalPuzzle("4k3/p6q/8/8/8/1P6/8/KB6 w - - 0 1", listOf("b3b4", "a7a6", "b1h7"), "Diagonal Clearance via b4"),
        RawTacticalPuzzle("4k3/p7/7q/8/8/1P6/8/K1B5 w - - 0 1", listOf("b3b4", "a7a6", "c1h6"), "Diagonal Clearance via b4"),
        RawTacticalPuzzle("4k3/p7/7q/8/8/1P6/3B4/K7 w - - 0 1", listOf("b3b4", "a7a6", "d2h6"), "Diagonal Clearance via b4"),
        RawTacticalPuzzle("4k3/p6q/8/8/8/1P6/2B5/K7 w - - 0 1", listOf("b3b4", "a7a6", "c2h7"), "Diagonal Clearance via b4"),
        RawTacticalPuzzle("4k2q/p7/8/8/8/1P6/1B6/K7 w - - 0 1", listOf("b3b4", "a7a6", "b2h8"), "Diagonal Clearance via b4"),
        RawTacticalPuzzle("4k3/p5q1/8/8/8/1P6/1B6/K7 w - - 0 1", listOf("b3b4", "a7a6", "b2g7"), "Diagonal Clearance via b4"),
        RawTacticalPuzzle("2q1k3/p7/8/8/8/1P5B/8/K7 w - - 0 1", listOf("b3b4", "a7a6", "h3c8"), "Diagonal Clearance via b4"),
        RawTacticalPuzzle("4k3/p6q/8/8/8/1P1B4/8/K7 w - - 0 1", listOf("b3b4", "a7a6", "d3h7"), "Diagonal Clearance via b4"),
        RawTacticalPuzzle("4k3/p7/8/8/8/1P2B3/8/K7 w - - 0 1", listOf("b3b4", "a7a6", "e3a7"), "Diagonal Clearance via b4"),
        RawTacticalPuzzle("4k3/p7/7q/8/8/1P2B3/8/K7 w - - 0 1", listOf("b3b4", "a7a6", "e3h6"), "Diagonal Clearance via b4"),
        RawTacticalPuzzle("4k3/p6q/8/8/1P6/8/8/KB6 w - - 0 1", listOf("b4b5", "a7a6", "b1h7"), "Diagonal Clearance via b5"),
        RawTacticalPuzzle("4k3/p7/7q/8/1P6/8/8/K1B5 w - - 0 1", listOf("b4b5", "a7a6", "c1h6"), "Diagonal Clearance via b5"),
        RawTacticalPuzzle("4k3/p7/7q/8/1P6/8/3B4/K7 w - - 0 1", listOf("b4b5", "a7a6", "d2h6"), "Diagonal Clearance via b5"),
        RawTacticalPuzzle("4k3/p6q/8/8/1P6/8/2B5/K7 w - - 0 1", listOf("b4b5", "a7a6", "c2h7"), "Diagonal Clearance via b5"),
        RawTacticalPuzzle("4k2q/p7/8/8/1P6/8/1B6/K7 w - - 0 1", listOf("b4b5", "a7a6", "b2h8"), "Diagonal Clearance via b5"),
        RawTacticalPuzzle("4k3/p5q1/8/8/1P6/8/1B6/K7 w - - 0 1", listOf("b4b5", "a7a6", "b2g7"), "Diagonal Clearance via b5"),
        RawTacticalPuzzle("4kq2/p7/8/8/1P6/B7/8/K7 w - - 0 1", listOf("b4b5", "a7a6", "a3f8"), "Diagonal Clearance via b5"),
        RawTacticalPuzzle("2q1k3/p7/8/8/1P6/7B/8/K7 w - - 0 1", listOf("b4b5", "a7a6", "h3c8"), "Diagonal Clearance via b5"),
        RawTacticalPuzzle("4k3/p6q/8/8/1P6/3B4/8/K7 w - - 0 1", listOf("b4b5", "a7a6", "d3h7"), "Diagonal Clearance via b5"),
        RawTacticalPuzzle("4k3/p7/8/8/1P6/4B3/8/K7 w - - 0 1", listOf("b4b5", "a7a6", "e3a7"), "Diagonal Clearance via b5"),
        RawTacticalPuzzle("4k3/p7/7q/8/1P6/4B3/8/K7 w - - 0 1", listOf("b4b5", "a7a6", "e3h6"), "Diagonal Clearance via b5"),
        RawTacticalPuzzle("4k3/p6q/8/8/8/8/2P5/KB6 w - - 0 1", listOf("c2c3", "a7a6", "b1h7"), "Diagonal Clearance via c3"),
        RawTacticalPuzzle("4k3/p7/7q/8/8/8/2P5/K1B5 w - - 0 1", listOf("c2c3", "a7a6", "c1h6"), "Diagonal Clearance via c3"),
        RawTacticalPuzzle("4k3/p7/7q/8/8/8/2PB4/K7 w - - 0 1", listOf("c2c3", "a7a6", "d2h6"), "Diagonal Clearance via c3"),
        RawTacticalPuzzle("4kq2/p7/8/8/8/B7/2P5/K7 w - - 0 1", listOf("c2c3", "a7a6", "a3f8"), "Diagonal Clearance via c3"),
        RawTacticalPuzzle("2q1k3/p7/8/8/8/7B/2P5/K7 w - - 0 1", listOf("c2c3", "a7a6", "h3c8"), "Diagonal Clearance via c3"),
        RawTacticalPuzzle("4k3/p6q/8/8/8/3B4/2P5/K7 w - - 0 1", listOf("c2c3", "a7a6", "d3h7"), "Diagonal Clearance via c3"),
        RawTacticalPuzzle("4k3/p7/8/8/8/4B3/2P5/K7 w - - 0 1", listOf("c2c3", "a7a6", "e3a7"), "Diagonal Clearance via c3"),
        RawTacticalPuzzle("4k3/p7/7q/8/8/4B3/2P5/K7 w - - 0 1", listOf("c2c3", "a7a6", "e3h6"), "Diagonal Clearance via c3"),
        RawTacticalPuzzle("4k3/p6q/8/8/8/2P5/8/KB6 w - - 0 1", listOf("c3c4", "a7a6", "b1h7"), "Diagonal Clearance via c4"),
        RawTacticalPuzzle("4k3/p7/7q/8/8/2P5/8/K1B5 w - - 0 1", listOf("c3c4", "a7a6", "c1h6"), "Diagonal Clearance via c4"),
        RawTacticalPuzzle("4k3/p7/7q/8/8/2P5/3B4/K7 w - - 0 1", listOf("c3c4", "a7a6", "d2h6"), "Diagonal Clearance via c4"),
        RawTacticalPuzzle("4k3/p6q/8/8/8/2P5/2B5/K7 w - - 0 1", listOf("c3c4", "a7a6", "c2h7"), "Diagonal Clearance via c4"),
        RawTacticalPuzzle("4k2q/p7/8/8/8/2P5/1B6/K7 w - - 0 1", listOf("c3c4", "a7a6", "b2h8"), "Diagonal Clearance via c4"),
        RawTacticalPuzzle("4k3/p5q1/8/8/8/2P5/1B6/K7 w - - 0 1", listOf("c3c4", "a7a6", "b2g7"), "Diagonal Clearance via c4"),
        RawTacticalPuzzle("4kq2/p7/8/8/8/B1P5/8/K7 w - - 0 1", listOf("c3c4", "a7a6", "a3f8"), "Diagonal Clearance via c4"),
        RawTacticalPuzzle("2q1k3/p7/8/8/8/2P4B/8/K7 w - - 0 1", listOf("c3c4", "a7a6", "h3c8"), "Diagonal Clearance via c4"),
        RawTacticalPuzzle("4k3/p6q/8/8/8/2PB4/8/K7 w - - 0 1", listOf("c3c4", "a7a6", "d3h7"), "Diagonal Clearance via c4"),
        RawTacticalPuzzle("4k3/p7/8/8/8/2P1B3/8/K7 w - - 0 1", listOf("c3c4", "a7a6", "e3a7"), "Diagonal Clearance via c4"),
        RawTacticalPuzzle("4k3/p7/7q/8/8/2P1B3/8/K7 w - - 0 1", listOf("c3c4", "a7a6", "e3h6"), "Diagonal Clearance via c4"),
        RawTacticalPuzzle("4k3/p6q/8/8/2P5/8/8/KB6 w - - 0 1", listOf("c4c5", "a7a6", "b1h7"), "Diagonal Clearance via c5"),
        RawTacticalPuzzle("4k3/p7/7q/8/2P5/8/8/K1B5 w - - 0 1", listOf("c4c5", "a7a6", "c1h6"), "Diagonal Clearance via c5"),
        RawTacticalPuzzle("4k3/p7/7q/8/2P5/8/3B4/K7 w - - 0 1", listOf("c4c5", "a7a6", "d2h6"), "Diagonal Clearance via c5"),
        RawTacticalPuzzle("4k3/p6q/8/8/2P5/8/2B5/K7 w - - 0 1", listOf("c4c5", "a7a6", "c2h7"), "Diagonal Clearance via c5"),
        RawTacticalPuzzle("4k2q/p7/8/8/2P5/8/1B6/K7 w - - 0 1", listOf("c4c5", "a7a6", "b2h8"), "Diagonal Clearance via c5"),
        RawTacticalPuzzle("4k3/p5q1/8/8/2P5/8/1B6/K7 w - - 0 1", listOf("c4c5", "a7a6", "b2g7"), "Diagonal Clearance via c5"),
        RawTacticalPuzzle("2q1k3/p7/8/8/2P5/7B/8/K7 w - - 0 1", listOf("c4c5", "a7a6", "h3c8"), "Diagonal Clearance via c5"),
        RawTacticalPuzzle("4k3/p6q/8/8/2P5/3B4/8/K7 w - - 0 1", listOf("c4c5", "a7a6", "d3h7"), "Diagonal Clearance via c5"),
        RawTacticalPuzzle("4k3/p7/7q/8/2P5/4B3/8/K7 w - - 0 1", listOf("c4c5", "a7a6", "e3h6"), "Diagonal Clearance via c5"),
        RawTacticalPuzzle("4k3/p7/7q/8/8/8/3P4/K1B5 w - - 0 1", listOf("d2d3", "a7a6", "c1h6"), "Diagonal Clearance via d3"),
        RawTacticalPuzzle("4k2q/p7/8/8/8/8/1B1P4/K7 w - - 0 1", listOf("d2d3", "a7a6", "b2h8"), "Diagonal Clearance via d3"),
        RawTacticalPuzzle("4k3/p5q1/8/8/8/8/1B1P4/K7 w - - 0 1", listOf("d2d3", "a7a6", "b2g7"), "Diagonal Clearance via d3"),
        RawTacticalPuzzle("4kq2/p7/8/8/8/B7/3P4/K7 w - - 0 1", listOf("d2d3", "a7a6", "a3f8"), "Diagonal Clearance via d3"),
        RawTacticalPuzzle("2q1k3/p7/8/8/8/7B/3P4/K7 w - - 0 1", listOf("d2d3", "a7a6", "h3c8"), "Diagonal Clearance via d3"),
        RawTacticalPuzzle("4k3/p7/8/8/8/4B3/3P4/K7 w - - 0 1", listOf("d2d3", "a7a6", "e3a7"), "Diagonal Clearance via d3"),
        RawTacticalPuzzle("4k3/p7/7q/8/8/4B3/3P4/K7 w - - 0 1", listOf("d2d3", "a7a6", "e3h6"), "Diagonal Clearance via d3"),
        RawTacticalPuzzle("4k3/p6q/8/8/8/3P4/8/KB6 w - - 0 1", listOf("d3d4", "a7a6", "b1h7"), "Diagonal Clearance via d4"),
        RawTacticalPuzzle("4k3/p7/7q/8/8/3P4/8/K1B5 w - - 0 1", listOf("d3d4", "a7a6", "c1h6"), "Diagonal Clearance via d4"),
        RawTacticalPuzzle("4k3/p7/7q/8/8/3P4/3B4/K7 w - - 0 1", listOf("d3d4", "a7a6", "d2h6"), "Diagonal Clearance via d4"),
        RawTacticalPuzzle("4k3/p6q/8/8/8/3P4/2B5/K7 w - - 0 1", listOf("d3d4", "a7a6", "c2h7"), "Diagonal Clearance via d4"),
        RawTacticalPuzzle("4kq2/p7/8/8/8/B2P4/8/K7 w - - 0 1", listOf("d3d4", "a7a6", "a3f8"), "Diagonal Clearance via d4"),
        RawTacticalPuzzle("2q1k3/p7/8/8/8/3P3B/8/K7 w - - 0 1", listOf("d3d4", "a7a6", "h3c8"), "Diagonal Clearance via d4"),
        RawTacticalPuzzle("4k3/p7/7q/8/8/3PB3/8/K7 w - - 0 1", listOf("d3d4", "a7a6", "e3h6"), "Diagonal Clearance via d4"),
        RawTacticalPuzzle("4k3/p6q/8/8/3P4/8/8/KB6 w - - 0 1", listOf("d4d5", "a7a6", "b1h7"), "Diagonal Clearance via d5"),
        RawTacticalPuzzle("4k3/p7/7q/8/3P4/8/8/K1B5 w - - 0 1", listOf("d4d5", "a7a6", "c1h6"), "Diagonal Clearance via d5"),
        RawTacticalPuzzle("4k3/p7/7q/8/3P4/8/3B4/K7 w - - 0 1", listOf("d4d5", "a7a6", "d2h6"), "Diagonal Clearance via d5"),
        RawTacticalPuzzle("4k3/p6q/8/8/3P4/8/2B5/K7 w - - 0 1", listOf("d4d5", "a7a6", "c2h7"), "Diagonal Clearance via d5"),
        RawTacticalPuzzle("4k2q/p7/8/8/3P4/8/1B6/K7 w - - 0 1", listOf("d4d5", "a7a6", "b2h8"), "Diagonal Clearance via d5"),
        RawTacticalPuzzle("4k3/p5q1/8/8/3P4/8/1B6/K7 w - - 0 1", listOf("d4d5", "a7a6", "b2g7"), "Diagonal Clearance via d5"),
        RawTacticalPuzzle("4kq2/p7/8/8/3P4/B7/8/K7 w - - 0 1", listOf("d4d5", "a7a6", "a3f8"), "Diagonal Clearance via d5"),
        RawTacticalPuzzle("2q1k3/p7/8/8/3P4/7B/8/K7 w - - 0 1", listOf("d4d5", "a7a6", "h3c8"), "Diagonal Clearance via d5"),
        RawTacticalPuzzle("4k3/p6q/8/8/3P4/3B4/8/K7 w - - 0 1", listOf("d4d5", "a7a6", "d3h7"), "Diagonal Clearance via d5"),
        RawTacticalPuzzle("4k3/p7/8/8/3P4/4B3/8/K7 w - - 0 1", listOf("d4d5", "a7a6", "e3a7"), "Diagonal Clearance via d5"),
        RawTacticalPuzzle("4k3/p7/7q/8/3P4/4B3/8/K7 w - - 0 1", listOf("d4d5", "a7a6", "e3h6"), "Diagonal Clearance via d5"),
        RawTacticalPuzzle("4k3/p6q/8/8/8/8/4P3/KB6 w - - 0 1", listOf("e2e3", "a7a6", "b1h7"), "Diagonal Clearance via e3"),
        RawTacticalPuzzle("4k3/p6q/8/8/8/8/2B1P3/K7 w - - 0 1", listOf("e2e3", "a7a6", "c2h7"), "Diagonal Clearance via e3"),
        RawTacticalPuzzle("4k2q/p7/8/8/8/8/1B2P3/K7 w - - 0 1", listOf("e2e3", "a7a6", "b2h8"), "Diagonal Clearance via e3"),
        RawTacticalPuzzle("4k3/p5q1/8/8/8/8/1B2P3/K7 w - - 0 1", listOf("e2e3", "a7a6", "b2g7"), "Diagonal Clearance via e3"),
        RawTacticalPuzzle("4kq2/p7/8/8/8/B7/4P3/K7 w - - 0 1", listOf("e2e3", "a7a6", "a3f8"), "Diagonal Clearance via e3"),
        RawTacticalPuzzle("2q1k3/p7/8/8/8/7B/4P3/K7 w - - 0 1", listOf("e2e3", "a7a6", "h3c8"), "Diagonal Clearance via e3"),
        RawTacticalPuzzle("4k3/p6q/8/8/8/3B4/4P3/K7 w - - 0 1", listOf("e2e3", "a7a6", "d3h7"), "Diagonal Clearance via e3"),
        RawTacticalPuzzle("4k3/p7/7q/8/8/4P3/8/K1B5 w - - 0 1", listOf("e3e4", "a7a6", "c1h6"), "Diagonal Clearance via e4"),
        RawTacticalPuzzle("4k3/p7/7q/8/8/4P3/3B4/K7 w - - 0 1", listOf("e3e4", "a7a6", "d2h6"), "Diagonal Clearance via e4"),
        RawTacticalPuzzle("4k2q/p7/8/8/8/4P3/1B6/K7 w - - 0 1", listOf("e3e4", "a7a6", "b2h8"), "Diagonal Clearance via e4"),
        RawTacticalPuzzle("4k3/p5q1/8/8/8/4P3/1B6/K7 w - - 0 1", listOf("e3e4", "a7a6", "b2g7"), "Diagonal Clearance via e4"),
        RawTacticalPuzzle("4kq2/p7/8/8/8/B3P3/8/K7 w - - 0 1", listOf("e3e4", "a7a6", "a3f8"), "Diagonal Clearance via e4"),
        RawTacticalPuzzle("2q1k3/p7/8/8/8/4P2B/8/K7 w - - 0 1", listOf("e3e4", "a7a6", "h3c8"), "Diagonal Clearance via e4"),
        RawTacticalPuzzle("4k3/p6q/8/8/4P3/8/8/KB6 w - - 0 1", listOf("e4e5", "a7a6", "b1h7"), "Diagonal Clearance via e5"),
        RawTacticalPuzzle("4k3/p7/7q/8/4P3/8/8/K1B5 w - - 0 1", listOf("e4e5", "a7a6", "c1h6"), "Diagonal Clearance via e5"),
        RawTacticalPuzzle("4k3/p7/7q/8/4P3/8/3B4/K7 w - - 0 1", listOf("e4e5", "a7a6", "d2h6"), "Diagonal Clearance via e5"),
        RawTacticalPuzzle("4k3/p6q/8/8/4P3/8/2B5/K7 w - - 0 1", listOf("e4e5", "a7a6", "c2h7"), "Diagonal Clearance via e5")
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
            val cleanTheme = "Clearance"
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
