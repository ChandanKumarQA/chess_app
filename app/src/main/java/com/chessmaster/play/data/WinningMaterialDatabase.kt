package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object WinningMaterialDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("4k3/7p/8/8/8/8/Q7/K1r5 w - - 0 1", listOf("a2b1", "h7h6", "b1c1"), "Winning Material via Queen on c1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/1Q6/K1r5 w - - 0 1", listOf("b2b1", "h7h6", "b1c1"), "Winning Material via Queen on c1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/2Q5/K1r5 w - - 0 1", listOf("c2b1", "h7h6", "b1c1"), "Winning Material via Queen on c1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/1Q6/8/K1r5 w - - 0 1", listOf("b3b1", "h7h6", "b1c1"), "Winning Material via Queen on c1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/3Q4/8/K1r5 w - - 0 1", listOf("d3b1", "h7h6", "b1c1"), "Winning Material via Queen on c1"),
        RawTacticalPuzzle("4k3/7p/8/8/1Q6/8/8/K1r5 w - - 0 1", listOf("b4b1", "h7h6", "b1c1"), "Winning Material via Queen on c1"),
        RawTacticalPuzzle("4k3/7p/8/5Q2/8/8/8/K1r5 w - - 0 1", listOf("f5b1", "h7h6", "b1c1"), "Winning Material via Queen on c1"),
        RawTacticalPuzzle("4k3/7p/1Q6/8/8/8/8/K1r5 w - - 0 1", listOf("b6b1", "h7h6", "b1c1"), "Winning Material via Queen on c1"),
        RawTacticalPuzzle("4k3/1Q5p/8/8/8/8/8/K1r5 w - - 0 1", listOf("b7b1", "h7h6", "b1c1"), "Winning Material via Queen on c1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/8/K1Qr4 w - - 0 1", listOf("c1b1", "h7h6", "b1d1"), "Winning Material via Queen on d1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/Q7/K2r4 w - - 0 1", listOf("a2b1", "h7h6", "b1d1"), "Winning Material via Queen on d1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/1Q6/K2r4 w - - 0 1", listOf("b2b1", "h7h6", "b1d1"), "Winning Material via Queen on d1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/2Q5/K2r4 w - - 0 1", listOf("c2b1", "h7h6", "b1d1"), "Winning Material via Queen on d1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/1Q6/8/K2r4 w - - 0 1", listOf("b3b1", "h7h6", "b1d1"), "Winning Material via Queen on d1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/3Q4/8/K2r4 w - - 0 1", listOf("d3b1", "h7h6", "b1d1"), "Winning Material via Queen on d1"),
        RawTacticalPuzzle("4k3/7p/8/8/1Q6/8/8/K2r4 w - - 0 1", listOf("b4b1", "h7h6", "b1d1"), "Winning Material via Queen on d1"),
        RawTacticalPuzzle("4k3/7p/8/5Q2/8/8/8/K2r4 w - - 0 1", listOf("f5b1", "h7h6", "b1d1"), "Winning Material via Queen on d1"),
        RawTacticalPuzzle("4k3/7p/1Q6/8/8/8/8/K2r4 w - - 0 1", listOf("b6b1", "h7h6", "b1d1"), "Winning Material via Queen on d1"),
        RawTacticalPuzzle("4k3/1Q5p/8/8/8/8/8/K2r4 w - - 0 1", listOf("b7b1", "h7h6", "b1d1"), "Winning Material via Queen on d1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/8/KQ1r4 w - - 0 1", listOf("b1c1", "h7h6", "c1d1"), "Winning Material via Queen on d1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/3Q4/K2r4 w - - 0 1", listOf("d2c1", "h7h6", "c1d1"), "Winning Material via Queen on d1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/Q7/8/K2r4 w - - 0 1", listOf("a3c1", "h7h6", "c1d1"), "Winning Material via Queen on d1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/2Q5/8/K2r4 w - - 0 1", listOf("c3c1", "h7h6", "c1d1"), "Winning Material via Queen on d1"),
        RawTacticalPuzzle("4k3/7p/8/8/2Q5/8/8/K2r4 w - - 0 1", listOf("c4c1", "h7h6", "c1d1"), "Winning Material via Queen on d1"),
        RawTacticalPuzzle("4k3/7p/8/8/5Q2/8/8/K2r4 w - - 0 1", listOf("f4c1", "h7h6", "c1d1"), "Winning Material via Queen on d1"),
        RawTacticalPuzzle("4k3/7p/8/2Q5/8/8/8/K2r4 w - - 0 1", listOf("c5c1", "h7h6", "c1d1"), "Winning Material via Queen on d1"),
        RawTacticalPuzzle("4k3/7p/8/6Q1/8/8/8/K2r4 w - - 0 1", listOf("g5c1", "h7h6", "c1d1"), "Winning Material via Queen on d1"),
        RawTacticalPuzzle("4k3/7p/7Q/8/8/8/8/K2r4 w - - 0 1", listOf("h6c1", "h7h6", "c1d1"), "Winning Material via Queen on d1"),
        RawTacticalPuzzle("4k3/2Q4p/8/8/8/8/8/K2r4 w - - 0 1", listOf("c7c1", "h7h6", "c1d1"), "Winning Material via Queen on d1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/8/K1Q1r3 w - - 0 1", listOf("c1b1", "h7h6", "b1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/8/K2Qr3 w - - 0 1", listOf("d1b1", "h7h6", "b1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/Q7/K3r3 w - - 0 1", listOf("a2b1", "h7h6", "b1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/1Q6/K3r3 w - - 0 1", listOf("b2b1", "h7h6", "b1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/2Q5/K3r3 w - - 0 1", listOf("c2b1", "h7h6", "b1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/1Q6/8/K3r3 w - - 0 1", listOf("b3b1", "h7h6", "b1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/3Q4/8/K3r3 w - - 0 1", listOf("d3b1", "h7h6", "b1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/8/8/1Q6/8/8/K3r3 w - - 0 1", listOf("b4b1", "h7h6", "b1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/8/5Q2/8/8/8/K3r3 w - - 0 1", listOf("f5b1", "h7h6", "b1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/1Q6/8/8/8/8/K3r3 w - - 0 1", listOf("b6b1", "h7h6", "b1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/1Q5p/8/8/8/8/8/K3r3 w - - 0 1", listOf("b7b1", "h7h6", "b1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/8/KQ2r3 w - - 0 1", listOf("b1c1", "h7h6", "c1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/3Q4/K3r3 w - - 0 1", listOf("d2c1", "h7h6", "c1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/Q7/8/K3r3 w - - 0 1", listOf("a3c1", "h7h6", "c1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/2Q5/8/K3r3 w - - 0 1", listOf("c3c1", "h7h6", "c1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/8/8/2Q5/8/8/K3r3 w - - 0 1", listOf("c4c1", "h7h6", "c1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/8/8/5Q2/8/8/K3r3 w - - 0 1", listOf("f4c1", "h7h6", "c1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/8/2Q5/8/8/8/K3r3 w - - 0 1", listOf("c5c1", "h7h6", "c1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/8/6Q1/8/8/8/K3r3 w - - 0 1", listOf("g5c1", "h7h6", "c1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/7Q/8/8/8/8/K3r3 w - - 0 1", listOf("h6c1", "h7h6", "c1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/2Q4p/8/8/8/8/8/K3r3 w - - 0 1", listOf("c7c1", "h7h6", "c1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/5Q2/8/K3r3 w - - 0 1", listOf("f3d1", "h7h6", "d1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/8/8/3Q4/8/8/K3r3 w - - 0 1", listOf("d4d1", "h7h6", "d1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/8/8/6Q1/8/8/K3r3 w - - 0 1", listOf("g4d1", "h7h6", "d1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/8/3Q4/8/8/8/K3r3 w - - 0 1", listOf("d5d1", "h7h6", "d1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/3Q4/8/8/8/8/K3r3 w - - 0 1", listOf("d6d1", "h7h6", "d1e1"), "Winning Material via Queen on e1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/8/K1Q2r2 w - - 0 1", listOf("c1b1", "h7h6", "b1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/8/K2Q1r2 w - - 0 1", listOf("d1b1", "h7h6", "b1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/Q7/K4r2 w - - 0 1", listOf("a2b1", "h7h6", "b1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/1Q6/K4r2 w - - 0 1", listOf("b2b1", "h7h6", "b1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/2Q5/K4r2 w - - 0 1", listOf("c2b1", "h7h6", "b1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/1Q6/8/K4r2 w - - 0 1", listOf("b3b1", "h7h6", "b1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/3Q4/8/K4r2 w - - 0 1", listOf("d3b1", "h7h6", "b1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/8/8/1Q6/8/8/K4r2 w - - 0 1", listOf("b4b1", "h7h6", "b1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/8/5Q2/8/8/8/K4r2 w - - 0 1", listOf("f5b1", "h7h6", "b1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/1Q6/8/8/8/8/K4r2 w - - 0 1", listOf("b6b1", "h7h6", "b1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/1Q5p/8/8/8/8/8/K4r2 w - - 0 1", listOf("b7b1", "h7h6", "b1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/8/KQ3r2 w - - 0 1", listOf("b1c1", "h7h6", "c1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/3Q4/K4r2 w - - 0 1", listOf("d2c1", "h7h6", "c1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/Q7/8/K4r2 w - - 0 1", listOf("a3c1", "h7h6", "c1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/2Q5/8/K4r2 w - - 0 1", listOf("c3c1", "h7h6", "c1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/8/8/2Q5/8/8/K4r2 w - - 0 1", listOf("c4c1", "h7h6", "c1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/8/8/5Q2/8/8/K4r2 w - - 0 1", listOf("f4c1", "h7h6", "c1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/8/2Q5/8/8/8/K4r2 w - - 0 1", listOf("c5c1", "h7h6", "c1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/8/6Q1/8/8/8/K4r2 w - - 0 1", listOf("g5c1", "h7h6", "c1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/7Q/8/8/8/8/K4r2 w - - 0 1", listOf("h6c1", "h7h6", "c1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/2Q4p/8/8/8/8/8/K4r2 w - - 0 1", listOf("c7c1", "h7h6", "c1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/5Q2/8/K4r2 w - - 0 1", listOf("f3d1", "h7h6", "d1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/8/8/3Q4/8/8/K4r2 w - - 0 1", listOf("d4d1", "h7h6", "d1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/8/8/6Q1/8/8/K4r2 w - - 0 1", listOf("g4d1", "h7h6", "d1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/8/3Q4/8/8/8/K4r2 w - - 0 1", listOf("d5d1", "h7h6", "d1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/3Q4/8/8/8/8/K4r2 w - - 0 1", listOf("d6d1", "h7h6", "d1f1"), "Winning Material via Queen on f1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/8/K1Q3r1 w - - 0 1", listOf("c1b1", "h7h6", "b1g1"), "Winning Material via Queen on g1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/8/K2Q2r1 w - - 0 1", listOf("d1b1", "h7h6", "b1g1"), "Winning Material via Queen on g1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/8/K4Qr1 w - - 0 1", listOf("f1b1", "h7h6", "b1g1"), "Winning Material via Queen on g1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/Q7/K5r1 w - - 0 1", listOf("a2b1", "h7h6", "b1g1"), "Winning Material via Queen on g1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/1Q6/K5r1 w - - 0 1", listOf("b2b1", "h7h6", "b1g1"), "Winning Material via Queen on g1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/2Q5/K5r1 w - - 0 1", listOf("c2b1", "h7h6", "b1g1"), "Winning Material via Queen on g1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/1Q6/8/K5r1 w - - 0 1", listOf("b3b1", "h7h6", "b1g1"), "Winning Material via Queen on g1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/3Q4/8/K5r1 w - - 0 1", listOf("d3b1", "h7h6", "b1g1"), "Winning Material via Queen on g1"),
        RawTacticalPuzzle("4k3/7p/8/8/1Q6/8/8/K5r1 w - - 0 1", listOf("b4b1", "h7h6", "b1g1"), "Winning Material via Queen on g1"),
        RawTacticalPuzzle("4k3/7p/8/5Q2/8/8/8/K5r1 w - - 0 1", listOf("f5b1", "h7h6", "b1g1"), "Winning Material via Queen on g1"),
        RawTacticalPuzzle("4k3/7p/1Q6/8/8/8/8/K5r1 w - - 0 1", listOf("b6b1", "h7h6", "b1g1"), "Winning Material via Queen on g1"),
        RawTacticalPuzzle("4k3/1Q5p/8/8/8/8/8/K5r1 w - - 0 1", listOf("b7b1", "h7h6", "b1g1"), "Winning Material via Queen on g1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/8/KQ4r1 w - - 0 1", listOf("b1c1", "h7h6", "c1g1"), "Winning Material via Queen on g1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/8/3Q4/K5r1 w - - 0 1", listOf("d2c1", "h7h6", "c1g1"), "Winning Material via Queen on g1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/Q7/8/K5r1 w - - 0 1", listOf("a3c1", "h7h6", "c1g1"), "Winning Material via Queen on g1"),
        RawTacticalPuzzle("4k3/7p/8/8/8/2Q5/8/K5r1 w - - 0 1", listOf("c3c1", "h7h6", "c1g1"), "Winning Material via Queen on g1"),
        RawTacticalPuzzle("4k3/7p/8/8/2Q5/8/8/K5r1 w - - 0 1", listOf("c4c1", "h7h6", "c1g1"), "Winning Material via Queen on g1"),
        RawTacticalPuzzle("4k3/7p/8/8/5Q2/8/8/K5r1 w - - 0 1", listOf("f4c1", "h7h6", "c1g1"), "Winning Material via Queen on g1"),
        RawTacticalPuzzle("4k3/7p/8/2Q5/8/8/8/K5r1 w - - 0 1", listOf("c5c1", "h7h6", "c1g1"), "Winning Material via Queen on g1")
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
