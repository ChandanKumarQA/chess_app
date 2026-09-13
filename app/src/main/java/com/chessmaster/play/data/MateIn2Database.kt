package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object MateIn2Database {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("r5k1/5ppp/8/8/8/1R6/1R6/1K6 w - - 0 1", listOf("b3b8", "a8b8", "b2b8"), "Back-Rank Mate in 2 via b8"),
        RawTacticalPuzzle("r5k1/5ppp/8/8/1R6/8/1R6/1K6 w - - 0 1", listOf("b4b8", "a8b8", "b2b8"), "Back-Rank Mate in 2 via b8"),
        RawTacticalPuzzle("r5k1/5ppp/8/8/1R6/1R6/8/1K6 w - - 0 1", listOf("b4b8", "a8b8", "b3b8"), "Back-Rank Mate in 2 via b8"),
        RawTacticalPuzzle("r5k1/5ppp/8/1R6/8/8/1R6/1K6 w - - 0 1", listOf("b5b8", "a8b8", "b2b8"), "Back-Rank Mate in 2 via b8"),
        RawTacticalPuzzle("r5k1/5ppp/8/1R6/8/1R6/8/1K6 w - - 0 1", listOf("b5b8", "a8b8", "b3b8"), "Back-Rank Mate in 2 via b8"),
        RawTacticalPuzzle("r5k1/5ppp/8/1R6/1R6/8/8/1K6 w - - 0 1", listOf("b5b8", "a8b8", "b4b8"), "Back-Rank Mate in 2 via b8"),
        RawTacticalPuzzle("r5k1/5ppp/8/8/8/8/2R5/1KR5 w - - 0 1", listOf("c2c8", "a8c8", "c1c8"), "Back-Rank Mate in 2 via c8"),
        RawTacticalPuzzle("r5k1/5ppp/8/8/8/2R5/8/1KR5 w - - 0 1", listOf("c3c8", "a8c8", "c1c8"), "Back-Rank Mate in 2 via c8"),
        RawTacticalPuzzle("r5k1/5ppp/8/8/8/2R5/2R5/1K6 w - - 0 1", listOf("c3c8", "a8c8", "c2c8"), "Back-Rank Mate in 2 via c8"),
        RawTacticalPuzzle("r5k1/5ppp/8/8/2R5/8/8/1KR5 w - - 0 1", listOf("c4c8", "a8c8", "c1c8"), "Back-Rank Mate in 2 via c8"),
        RawTacticalPuzzle("r5k1/5ppp/8/8/2R5/8/2R5/1K6 w - - 0 1", listOf("c4c8", "a8c8", "c2c8"), "Back-Rank Mate in 2 via c8"),
        RawTacticalPuzzle("r5k1/5ppp/8/8/2R5/2R5/8/1K6 w - - 0 1", listOf("c4c8", "a8c8", "c3c8"), "Back-Rank Mate in 2 via c8"),
        RawTacticalPuzzle("r5k1/5ppp/8/2R5/8/8/8/1KR5 w - - 0 1", listOf("c5c8", "a8c8", "c1c8"), "Back-Rank Mate in 2 via c8"),
        RawTacticalPuzzle("r5k1/5ppp/8/2R5/8/8/2R5/1K6 w - - 0 1", listOf("c5c8", "a8c8", "c2c8"), "Back-Rank Mate in 2 via c8"),
        RawTacticalPuzzle("r5k1/5ppp/8/2R5/8/2R5/8/1K6 w - - 0 1", listOf("c5c8", "a8c8", "c3c8"), "Back-Rank Mate in 2 via c8"),
        RawTacticalPuzzle("r5k1/5ppp/8/2R5/2R5/8/8/1K6 w - - 0 1", listOf("c5c8", "a8c8", "c4c8"), "Back-Rank Mate in 2 via c8"),
        RawTacticalPuzzle("r5k1/5ppp/8/8/8/8/3R4/1K1R4 w - - 0 1", listOf("d2d8", "a8d8", "d1d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("r5k1/5ppp/8/8/8/3R4/8/1K1R4 w - - 0 1", listOf("d3d8", "a8d8", "d1d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("r5k1/5ppp/8/8/8/3R4/3R4/1K6 w - - 0 1", listOf("d3d8", "a8d8", "d2d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("r5k1/5ppp/8/8/3R4/8/8/1K1R4 w - - 0 1", listOf("d4d8", "a8d8", "d1d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("r5k1/5ppp/8/8/3R4/8/3R4/1K6 w - - 0 1", listOf("d4d8", "a8d8", "d2d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("r5k1/5ppp/8/8/3R4/3R4/8/1K6 w - - 0 1", listOf("d4d8", "a8d8", "d3d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("r5k1/5ppp/8/3R4/8/8/8/1K1R4 w - - 0 1", listOf("d5d8", "a8d8", "d1d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("r5k1/5ppp/8/3R4/8/8/3R4/1K6 w - - 0 1", listOf("d5d8", "a8d8", "d2d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("r5k1/5ppp/8/3R4/8/3R4/8/1K6 w - - 0 1", listOf("d5d8", "a8d8", "d3d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("r5k1/5ppp/8/3R4/3R4/8/8/1K6 w - - 0 1", listOf("d5d8", "a8d8", "d4d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("2r3k1/5ppp/8/8/8/8/3R4/1K1R4 w - - 0 1", listOf("d2d8", "c8d8", "d1d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("2r3k1/5ppp/8/8/8/3R4/8/1K1R4 w - - 0 1", listOf("d3d8", "c8d8", "d1d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("2r3k1/5ppp/8/8/8/3R4/3R4/1K6 w - - 0 1", listOf("d3d8", "c8d8", "d2d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("2r3k1/5ppp/8/8/3R4/8/8/1K1R4 w - - 0 1", listOf("d4d8", "c8d8", "d1d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("2r3k1/5ppp/8/8/3R4/8/3R4/1K6 w - - 0 1", listOf("d4d8", "c8d8", "d2d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("2r3k1/5ppp/8/8/3R4/3R4/8/1K6 w - - 0 1", listOf("d4d8", "c8d8", "d3d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("2r3k1/5ppp/8/3R4/8/8/8/1K1R4 w - - 0 1", listOf("d5d8", "c8d8", "d1d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("2r3k1/5ppp/8/3R4/8/8/3R4/1K6 w - - 0 1", listOf("d5d8", "c8d8", "d2d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("2r3k1/5ppp/8/3R4/8/3R4/8/1K6 w - - 0 1", listOf("d5d8", "c8d8", "d3d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("2r3k1/5ppp/8/3R4/3R4/8/8/1K6 w - - 0 1", listOf("d5d8", "c8d8", "d4d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("r5k1/5ppp/8/8/8/8/4R3/1K2R3 w - - 0 1", listOf("e2e8", "a8e8", "e1e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("r5k1/5ppp/8/8/8/4R3/8/1K2R3 w - - 0 1", listOf("e3e8", "a8e8", "e1e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("r5k1/5ppp/8/8/8/4R3/4R3/1K6 w - - 0 1", listOf("e3e8", "a8e8", "e2e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("r5k1/5ppp/8/8/4R3/8/8/1K2R3 w - - 0 1", listOf("e4e8", "a8e8", "e1e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("r5k1/5ppp/8/8/4R3/8/4R3/1K6 w - - 0 1", listOf("e4e8", "a8e8", "e2e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("r5k1/5ppp/8/8/4R3/4R3/8/1K6 w - - 0 1", listOf("e4e8", "a8e8", "e3e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("r5k1/5ppp/8/4R3/8/8/8/1K2R3 w - - 0 1", listOf("e5e8", "a8e8", "e1e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("r5k1/5ppp/8/4R3/8/8/4R3/1K6 w - - 0 1", listOf("e5e8", "a8e8", "e2e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("r5k1/5ppp/8/4R3/8/4R3/8/1K6 w - - 0 1", listOf("e5e8", "a8e8", "e3e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("r5k1/5ppp/8/4R3/4R3/8/8/1K6 w - - 0 1", listOf("e5e8", "a8e8", "e4e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("2r3k1/5ppp/8/8/8/8/4R3/1K2R3 w - - 0 1", listOf("e2e8", "c8e8", "e1e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("2r3k1/5ppp/8/8/8/4R3/8/1K2R3 w - - 0 1", listOf("e3e8", "c8e8", "e1e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("2r3k1/5ppp/8/8/8/4R3/4R3/1K6 w - - 0 1", listOf("e3e8", "c8e8", "e2e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("2r3k1/5ppp/8/8/4R3/8/8/1K2R3 w - - 0 1", listOf("e4e8", "c8e8", "e1e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("2r3k1/5ppp/8/8/4R3/8/4R3/1K6 w - - 0 1", listOf("e4e8", "c8e8", "e2e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("2r3k1/5ppp/8/8/4R3/4R3/8/1K6 w - - 0 1", listOf("e4e8", "c8e8", "e3e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("2r3k1/5ppp/8/4R3/8/8/8/1K2R3 w - - 0 1", listOf("e5e8", "c8e8", "e1e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("2r3k1/5ppp/8/4R3/8/8/4R3/1K6 w - - 0 1", listOf("e5e8", "c8e8", "e2e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("2r3k1/5ppp/8/4R3/8/4R3/8/1K6 w - - 0 1", listOf("e5e8", "c8e8", "e3e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("2r3k1/5ppp/8/4R3/4R3/8/8/1K6 w - - 0 1", listOf("e5e8", "c8e8", "e4e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("3r2k1/5ppp/8/8/8/8/4R3/1K2R3 w - - 0 1", listOf("e2e8", "d8e8", "e1e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("3r2k1/5ppp/8/8/8/4R3/8/1K2R3 w - - 0 1", listOf("e3e8", "d8e8", "e1e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("3r2k1/5ppp/8/8/8/4R3/4R3/1K6 w - - 0 1", listOf("e3e8", "d8e8", "e2e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("3r2k1/5ppp/8/8/4R3/8/8/1K2R3 w - - 0 1", listOf("e4e8", "d8e8", "e1e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("3r2k1/5ppp/8/8/4R3/8/4R3/1K6 w - - 0 1", listOf("e4e8", "d8e8", "e2e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("3r2k1/5ppp/8/8/4R3/4R3/8/1K6 w - - 0 1", listOf("e4e8", "d8e8", "e3e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("3r2k1/5ppp/8/4R3/8/8/8/1K2R3 w - - 0 1", listOf("e5e8", "d8e8", "e1e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("3r2k1/5ppp/8/4R3/8/8/4R3/1K6 w - - 0 1", listOf("e5e8", "d8e8", "e2e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("3r2k1/5ppp/8/4R3/8/4R3/8/1K6 w - - 0 1", listOf("e5e8", "d8e8", "e3e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("3r2k1/5ppp/8/4R3/4R3/8/8/1K6 w - - 0 1", listOf("e5e8", "d8e8", "e4e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("1k2r3/ppp5/8/8/8/8/3R4/3R2K1 w - - 0 1", listOf("d2d8", "e8d8", "d1d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k2r3/ppp5/8/8/8/3R4/8/3R2K1 w - - 0 1", listOf("d3d8", "e8d8", "d1d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k2r3/ppp5/8/8/8/3R4/3R4/6K1 w - - 0 1", listOf("d3d8", "e8d8", "d2d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k2r3/ppp5/8/8/3R4/8/8/3R2K1 w - - 0 1", listOf("d4d8", "e8d8", "d1d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k2r3/ppp5/8/8/3R4/8/3R4/6K1 w - - 0 1", listOf("d4d8", "e8d8", "d2d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k2r3/ppp5/8/8/3R4/3R4/8/6K1 w - - 0 1", listOf("d4d8", "e8d8", "d3d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k2r3/ppp5/8/3R4/8/8/8/3R2K1 w - - 0 1", listOf("d5d8", "e8d8", "d1d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k2r3/ppp5/8/3R4/8/8/3R4/6K1 w - - 0 1", listOf("d5d8", "e8d8", "d2d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k2r3/ppp5/8/3R4/8/3R4/8/6K1 w - - 0 1", listOf("d5d8", "e8d8", "d3d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k2r3/ppp5/8/3R4/3R4/8/8/6K1 w - - 0 1", listOf("d5d8", "e8d8", "d4d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k3r2/ppp5/8/8/8/8/3R4/3R2K1 w - - 0 1", listOf("d2d8", "f8d8", "d1d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k3r2/ppp5/8/8/8/3R4/8/3R2K1 w - - 0 1", listOf("d3d8", "f8d8", "d1d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k3r2/ppp5/8/8/8/3R4/3R4/6K1 w - - 0 1", listOf("d3d8", "f8d8", "d2d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k3r2/ppp5/8/8/3R4/8/8/3R2K1 w - - 0 1", listOf("d4d8", "f8d8", "d1d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k3r2/ppp5/8/8/3R4/8/3R4/6K1 w - - 0 1", listOf("d4d8", "f8d8", "d2d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k3r2/ppp5/8/8/3R4/3R4/8/6K1 w - - 0 1", listOf("d4d8", "f8d8", "d3d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k3r2/ppp5/8/3R4/8/8/8/3R2K1 w - - 0 1", listOf("d5d8", "f8d8", "d1d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k3r2/ppp5/8/3R4/8/8/3R4/6K1 w - - 0 1", listOf("d5d8", "f8d8", "d2d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k3r2/ppp5/8/3R4/8/3R4/8/6K1 w - - 0 1", listOf("d5d8", "f8d8", "d3d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k3r2/ppp5/8/3R4/3R4/8/8/6K1 w - - 0 1", listOf("d5d8", "f8d8", "d4d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k5r/ppp5/8/8/8/8/3R4/3R2K1 w - - 0 1", listOf("d2d8", "h8d8", "d1d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k5r/ppp5/8/8/8/3R4/8/3R2K1 w - - 0 1", listOf("d3d8", "h8d8", "d1d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k5r/ppp5/8/8/8/3R4/3R4/6K1 w - - 0 1", listOf("d3d8", "h8d8", "d2d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k5r/ppp5/8/8/3R4/8/8/3R2K1 w - - 0 1", listOf("d4d8", "h8d8", "d1d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k5r/ppp5/8/8/3R4/8/3R4/6K1 w - - 0 1", listOf("d4d8", "h8d8", "d2d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k5r/ppp5/8/8/3R4/3R4/8/6K1 w - - 0 1", listOf("d4d8", "h8d8", "d3d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k5r/ppp5/8/3R4/8/8/8/3R2K1 w - - 0 1", listOf("d5d8", "h8d8", "d1d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k5r/ppp5/8/3R4/8/8/3R4/6K1 w - - 0 1", listOf("d5d8", "h8d8", "d2d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k5r/ppp5/8/3R4/8/3R4/8/6K1 w - - 0 1", listOf("d5d8", "h8d8", "d3d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k5r/ppp5/8/3R4/3R4/8/8/6K1 w - - 0 1", listOf("d5d8", "h8d8", "d4d8"), "Back-Rank Mate in 2 via d8"),
        RawTacticalPuzzle("1k3r2/ppp5/8/8/8/8/4R3/4R1K1 w - - 0 1", listOf("e2e8", "f8e8", "e1e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("1k3r2/ppp5/8/8/8/4R3/8/4R1K1 w - - 0 1", listOf("e3e8", "f8e8", "e1e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("1k3r2/ppp5/8/8/8/4R3/4R3/6K1 w - - 0 1", listOf("e3e8", "f8e8", "e2e8"), "Back-Rank Mate in 2 via e8"),
        RawTacticalPuzzle("1k3r2/ppp5/8/8/4R3/8/8/4R1K1 w - - 0 1", listOf("e4e8", "f8e8", "e1e8"), "Back-Rank Mate in 2 via e8")
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
            val cleanTheme = "Mate in 2"
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
