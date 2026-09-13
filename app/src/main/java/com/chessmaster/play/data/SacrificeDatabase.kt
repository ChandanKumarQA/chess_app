package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object SacrificeDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("1r4k1/5ppp/8/8/8/8/3Q4/K2R4 w - - 0 1", listOf("d2d8", "b8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d2"),
        RawTacticalPuzzle("1r4k1/5ppp/8/8/8/3Q4/8/K2R4 w - - 0 1", listOf("d3d8", "b8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d3"),
        RawTacticalPuzzle("1r4k1/5ppp/8/8/3Q4/8/8/K2R4 w - - 0 1", listOf("d4d8", "b8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d4"),
        RawTacticalPuzzle("1r4k1/5ppp/8/8/7Q/8/8/K2R4 w - - 0 1", listOf("h4d8", "b8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from h4"),
        RawTacticalPuzzle("1r4k1/5ppp/8/Q7/8/8/8/K2R4 w - - 0 1", listOf("a5d8", "b8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from a5"),
        RawTacticalPuzzle("1r4k1/5ppp/8/3Q4/8/8/8/K2R4 w - - 0 1", listOf("d5d8", "b8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d5"),
        RawTacticalPuzzle("1r4k1/5ppp/8/6Q1/8/8/8/K2R4 w - - 0 1", listOf("g5d8", "b8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from g5"),
        RawTacticalPuzzle("1r4k1/5ppp/1Q6/8/8/8/8/K2R4 w - - 0 1", listOf("b6d8", "b8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from b6"),
        RawTacticalPuzzle("1r4k1/5ppp/3Q4/8/8/8/8/K2R4 w - - 0 1", listOf("d6d8", "b8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d6"),
        RawTacticalPuzzle("1r4k1/5ppp/5Q2/8/8/8/8/K2R4 w - - 0 1", listOf("f6d8", "b8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from f6"),
        RawTacticalPuzzle("1r4k1/2Q2ppp/8/8/8/8/8/K2R4 w - - 0 1", listOf("c7d8", "b8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from c7"),
        RawTacticalPuzzle("1r4k1/3Q1ppp/8/8/8/8/8/K2R4 w - - 0 1", listOf("d7d8", "b8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d7"),
        RawTacticalPuzzle("1r4k1/4Qppp/8/8/8/8/8/K2R4 w - - 0 1", listOf("e7d8", "b8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from e7"),
        RawTacticalPuzzle("1r4k1/5ppp/8/8/8/8/4Q3/K3R3 w - - 0 1", listOf("e2e8", "b8e8", "e1e8"), "Queen Back-Rank Sacrifice on e8 from e2"),
        RawTacticalPuzzle("1r4k1/5ppp/8/8/8/4Q3/8/K3R3 w - - 0 1", listOf("e3e8", "b8e8", "e1e8"), "Queen Back-Rank Sacrifice on e8 from e3"),
        RawTacticalPuzzle("1r4k1/5ppp/8/8/Q7/8/8/K3R3 w - - 0 1", listOf("a4e8", "b8e8", "e1e8"), "Queen Back-Rank Sacrifice on e8 from a4"),
        RawTacticalPuzzle("1r4k1/5ppp/8/8/4Q3/8/8/K3R3 w - - 0 1", listOf("e4e8", "b8e8", "e1e8"), "Queen Back-Rank Sacrifice on e8 from e4"),
        RawTacticalPuzzle("1r4k1/5ppp/8/1Q6/8/8/8/K3R3 w - - 0 1", listOf("b5e8", "b8e8", "e1e8"), "Queen Back-Rank Sacrifice on e8 from b5"),
        RawTacticalPuzzle("1r4k1/5ppp/8/4Q3/8/8/8/K3R3 w - - 0 1", listOf("e5e8", "b8e8", "e1e8"), "Queen Back-Rank Sacrifice on e8 from e5"),
        RawTacticalPuzzle("1r4k1/5ppp/2Q5/8/8/8/8/K3R3 w - - 0 1", listOf("c6e8", "b8e8", "e1e8"), "Queen Back-Rank Sacrifice on e8 from c6"),
        RawTacticalPuzzle("1r4k1/5ppp/4Q3/8/8/8/8/K3R3 w - - 0 1", listOf("e6e8", "b8e8", "e1e8"), "Queen Back-Rank Sacrifice on e8 from e6"),
        RawTacticalPuzzle("1r4k1/3Q1ppp/8/8/8/8/8/K3R3 w - - 0 1", listOf("d7e8", "b8e8", "e1e8"), "Queen Back-Rank Sacrifice on e8 from d7"),
        RawTacticalPuzzle("1r4k1/4Qppp/8/8/8/8/8/K3R3 w - - 0 1", listOf("e7e8", "b8e8", "e1e8"), "Queen Back-Rank Sacrifice on e8 from e7"),
        RawTacticalPuzzle("1r4k1/5ppp/8/8/8/8/2Q5/K1R5 w - - 0 1", listOf("c2c8", "b8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from c2"),
        RawTacticalPuzzle("1r4k1/5ppp/8/8/8/2Q5/8/K1R5 w - - 0 1", listOf("c3c8", "b8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from c3"),
        RawTacticalPuzzle("1r4k1/5ppp/8/8/8/7Q/8/K1R5 w - - 0 1", listOf("h3c8", "b8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from h3"),
        RawTacticalPuzzle("1r4k1/5ppp/8/8/2Q5/8/8/K1R5 w - - 0 1", listOf("c4c8", "b8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from c4"),
        RawTacticalPuzzle("1r4k1/5ppp/8/8/6Q1/8/8/K1R5 w - - 0 1", listOf("g4c8", "b8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from g4"),
        RawTacticalPuzzle("1r4k1/5ppp/8/2Q5/8/8/8/K1R5 w - - 0 1", listOf("c5c8", "b8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from c5"),
        RawTacticalPuzzle("1r4k1/5ppp/8/5Q2/8/8/8/K1R5 w - - 0 1", listOf("f5c8", "b8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from f5"),
        RawTacticalPuzzle("1r4k1/5ppp/Q7/8/8/8/8/K1R5 w - - 0 1", listOf("a6c8", "b8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from a6"),
        RawTacticalPuzzle("1r4k1/5ppp/2Q5/8/8/8/8/K1R5 w - - 0 1", listOf("c6c8", "b8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from c6"),
        RawTacticalPuzzle("1r4k1/5ppp/4Q3/8/8/8/8/K1R5 w - - 0 1", listOf("e6c8", "b8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from e6"),
        RawTacticalPuzzle("1r4k1/1Q3ppp/8/8/8/8/8/K1R5 w - - 0 1", listOf("b7c8", "b8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from b7"),
        RawTacticalPuzzle("1r4k1/2Q2ppp/8/8/8/8/8/K1R5 w - - 0 1", listOf("c7c8", "b8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from c7"),
        RawTacticalPuzzle("1r4k1/3Q1ppp/8/8/8/8/8/K1R5 w - - 0 1", listOf("d7c8", "b8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from d7"),
        RawTacticalPuzzle("2r3k1/5ppp/8/8/8/8/3Q4/K2R4 w - - 0 1", listOf("d2d8", "c8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d2"),
        RawTacticalPuzzle("2r3k1/5ppp/8/8/8/3Q4/8/K2R4 w - - 0 1", listOf("d3d8", "c8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d3"),
        RawTacticalPuzzle("2r3k1/5ppp/8/8/3Q4/8/8/K2R4 w - - 0 1", listOf("d4d8", "c8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d4"),
        RawTacticalPuzzle("2r3k1/5ppp/8/8/7Q/8/8/K2R4 w - - 0 1", listOf("h4d8", "c8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from h4"),
        RawTacticalPuzzle("2r3k1/5ppp/8/Q7/8/8/8/K2R4 w - - 0 1", listOf("a5d8", "c8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from a5"),
        RawTacticalPuzzle("2r3k1/5ppp/8/3Q4/8/8/8/K2R4 w - - 0 1", listOf("d5d8", "c8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d5"),
        RawTacticalPuzzle("2r3k1/5ppp/8/6Q1/8/8/8/K2R4 w - - 0 1", listOf("g5d8", "c8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from g5"),
        RawTacticalPuzzle("2r3k1/5ppp/1Q6/8/8/8/8/K2R4 w - - 0 1", listOf("b6d8", "c8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from b6"),
        RawTacticalPuzzle("2r3k1/5ppp/3Q4/8/8/8/8/K2R4 w - - 0 1", listOf("d6d8", "c8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d6"),
        RawTacticalPuzzle("2r3k1/5ppp/5Q2/8/8/8/8/K2R4 w - - 0 1", listOf("f6d8", "c8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from f6"),
        RawTacticalPuzzle("2r3k1/2Q2ppp/8/8/8/8/8/K2R4 w - - 0 1", listOf("c7d8", "c8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from c7"),
        RawTacticalPuzzle("2r3k1/3Q1ppp/8/8/8/8/8/K2R4 w - - 0 1", listOf("d7d8", "c8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d7"),
        RawTacticalPuzzle("2r3k1/4Qppp/8/8/8/8/8/K2R4 w - - 0 1", listOf("e7d8", "c8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from e7"),
        RawTacticalPuzzle("2r3k1/5ppp/8/8/8/8/4Q3/K3R3 w - - 0 1", listOf("e2e8", "c8e8", "e1e8"), "Queen Back-Rank Sacrifice on e8 from e2"),
        RawTacticalPuzzle("2r3k1/5ppp/8/8/8/4Q3/8/K3R3 w - - 0 1", listOf("e3e8", "c8e8", "e1e8"), "Queen Back-Rank Sacrifice on e8 from e3"),
        RawTacticalPuzzle("2r3k1/5ppp/8/8/Q7/8/8/K3R3 w - - 0 1", listOf("a4e8", "c8e8", "e1e8"), "Queen Back-Rank Sacrifice on e8 from a4"),
        RawTacticalPuzzle("2r3k1/5ppp/8/8/4Q3/8/8/K3R3 w - - 0 1", listOf("e4e8", "c8e8", "e1e8"), "Queen Back-Rank Sacrifice on e8 from e4"),
        RawTacticalPuzzle("2r3k1/5ppp/8/1Q6/8/8/8/K3R3 w - - 0 1", listOf("b5e8", "c8e8", "e1e8"), "Queen Back-Rank Sacrifice on e8 from b5"),
        RawTacticalPuzzle("2r3k1/5ppp/8/4Q3/8/8/8/K3R3 w - - 0 1", listOf("e5e8", "c8e8", "e1e8"), "Queen Back-Rank Sacrifice on e8 from e5"),
        RawTacticalPuzzle("2r3k1/5ppp/2Q5/8/8/8/8/K3R3 w - - 0 1", listOf("c6e8", "c8e8", "e1e8"), "Queen Back-Rank Sacrifice on e8 from c6"),
        RawTacticalPuzzle("2r3k1/5ppp/4Q3/8/8/8/8/K3R3 w - - 0 1", listOf("e6e8", "c8e8", "e1e8"), "Queen Back-Rank Sacrifice on e8 from e6"),
        RawTacticalPuzzle("2r3k1/3Q1ppp/8/8/8/8/8/K3R3 w - - 0 1", listOf("d7e8", "c8e8", "e1e8"), "Queen Back-Rank Sacrifice on e8 from d7"),
        RawTacticalPuzzle("2r3k1/4Qppp/8/8/8/8/8/K3R3 w - - 0 1", listOf("e7e8", "c8e8", "e1e8"), "Queen Back-Rank Sacrifice on e8 from e7"),
        RawTacticalPuzzle("4r1k1/5ppp/8/8/8/8/3Q4/K2R4 w - - 0 1", listOf("d2d8", "e8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d2"),
        RawTacticalPuzzle("4r1k1/5ppp/8/8/8/3Q4/8/K2R4 w - - 0 1", listOf("d3d8", "e8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d3"),
        RawTacticalPuzzle("4r1k1/5ppp/8/8/3Q4/8/8/K2R4 w - - 0 1", listOf("d4d8", "e8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d4"),
        RawTacticalPuzzle("4r1k1/5ppp/8/8/7Q/8/8/K2R4 w - - 0 1", listOf("h4d8", "e8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from h4"),
        RawTacticalPuzzle("4r1k1/5ppp/8/Q7/8/8/8/K2R4 w - - 0 1", listOf("a5d8", "e8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from a5"),
        RawTacticalPuzzle("4r1k1/5ppp/8/3Q4/8/8/8/K2R4 w - - 0 1", listOf("d5d8", "e8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d5"),
        RawTacticalPuzzle("4r1k1/5ppp/8/6Q1/8/8/8/K2R4 w - - 0 1", listOf("g5d8", "e8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from g5"),
        RawTacticalPuzzle("4r1k1/5ppp/1Q6/8/8/8/8/K2R4 w - - 0 1", listOf("b6d8", "e8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from b6"),
        RawTacticalPuzzle("4r1k1/5ppp/3Q4/8/8/8/8/K2R4 w - - 0 1", listOf("d6d8", "e8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d6"),
        RawTacticalPuzzle("4r1k1/5ppp/5Q2/8/8/8/8/K2R4 w - - 0 1", listOf("f6d8", "e8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from f6"),
        RawTacticalPuzzle("4r1k1/2Q2ppp/8/8/8/8/8/K2R4 w - - 0 1", listOf("c7d8", "e8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from c7"),
        RawTacticalPuzzle("4r1k1/3Q1ppp/8/8/8/8/8/K2R4 w - - 0 1", listOf("d7d8", "e8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d7"),
        RawTacticalPuzzle("4r1k1/4Qppp/8/8/8/8/8/K2R4 w - - 0 1", listOf("e7d8", "e8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from e7"),
        RawTacticalPuzzle("Q3r1k1/5ppp/8/8/8/8/8/K2R4 w - - 0 1", listOf("a8d8", "e8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from a8"),
        RawTacticalPuzzle("1Q2r1k1/5ppp/8/8/8/8/8/K2R4 w - - 0 1", listOf("b8d8", "e8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from b8"),
        RawTacticalPuzzle("2Q1r1k1/5ppp/8/8/8/8/8/K2R4 w - - 0 1", listOf("c8d8", "e8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from c8"),
        RawTacticalPuzzle("4r1k1/5ppp/8/8/8/8/2Q5/K1R5 w - - 0 1", listOf("c2c8", "e8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from c2"),
        RawTacticalPuzzle("4r1k1/5ppp/8/8/8/2Q5/8/K1R5 w - - 0 1", listOf("c3c8", "e8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from c3"),
        RawTacticalPuzzle("4r1k1/5ppp/8/8/8/7Q/8/K1R5 w - - 0 1", listOf("h3c8", "e8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from h3"),
        RawTacticalPuzzle("4r1k1/5ppp/8/8/2Q5/8/8/K1R5 w - - 0 1", listOf("c4c8", "e8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from c4"),
        RawTacticalPuzzle("4r1k1/5ppp/8/8/6Q1/8/8/K1R5 w - - 0 1", listOf("g4c8", "e8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from g4"),
        RawTacticalPuzzle("4r1k1/5ppp/8/2Q5/8/8/8/K1R5 w - - 0 1", listOf("c5c8", "e8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from c5"),
        RawTacticalPuzzle("4r1k1/5ppp/8/5Q2/8/8/8/K1R5 w - - 0 1", listOf("f5c8", "e8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from f5"),
        RawTacticalPuzzle("4r1k1/5ppp/Q7/8/8/8/8/K1R5 w - - 0 1", listOf("a6c8", "e8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from a6"),
        RawTacticalPuzzle("4r1k1/5ppp/2Q5/8/8/8/8/K1R5 w - - 0 1", listOf("c6c8", "e8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from c6"),
        RawTacticalPuzzle("4r1k1/5ppp/4Q3/8/8/8/8/K1R5 w - - 0 1", listOf("e6c8", "e8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from e6"),
        RawTacticalPuzzle("4r1k1/1Q3ppp/8/8/8/8/8/K1R5 w - - 0 1", listOf("b7c8", "e8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from b7"),
        RawTacticalPuzzle("4r1k1/2Q2ppp/8/8/8/8/8/K1R5 w - - 0 1", listOf("c7c8", "e8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from c7"),
        RawTacticalPuzzle("4r1k1/3Q1ppp/8/8/8/8/8/K1R5 w - - 0 1", listOf("d7c8", "e8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from d7"),
        RawTacticalPuzzle("Q3r1k1/5ppp/8/8/8/8/8/K1R5 w - - 0 1", listOf("a8c8", "e8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from a8"),
        RawTacticalPuzzle("1Q2r1k1/5ppp/8/8/8/8/8/K1R5 w - - 0 1", listOf("b8c8", "e8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from b8"),
        RawTacticalPuzzle("3Qr1k1/5ppp/8/8/8/8/8/K1R5 w - - 0 1", listOf("d8c8", "e8c8", "c1c8"), "Queen Back-Rank Sacrifice on c8 from d8"),
        RawTacticalPuzzle("5rk1/5ppp/8/8/8/8/3Q4/K2R4 w - - 0 1", listOf("d2d8", "f8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d2"),
        RawTacticalPuzzle("5rk1/5ppp/8/8/8/3Q4/8/K2R4 w - - 0 1", listOf("d3d8", "f8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d3"),
        RawTacticalPuzzle("5rk1/5ppp/8/8/3Q4/8/8/K2R4 w - - 0 1", listOf("d4d8", "f8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d4"),
        RawTacticalPuzzle("5rk1/5ppp/8/8/7Q/8/8/K2R4 w - - 0 1", listOf("h4d8", "f8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from h4"),
        RawTacticalPuzzle("5rk1/5ppp/8/Q7/8/8/8/K2R4 w - - 0 1", listOf("a5d8", "f8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from a5"),
        RawTacticalPuzzle("5rk1/5ppp/8/3Q4/8/8/8/K2R4 w - - 0 1", listOf("d5d8", "f8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d5"),
        RawTacticalPuzzle("5rk1/5ppp/8/6Q1/8/8/8/K2R4 w - - 0 1", listOf("g5d8", "f8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from g5"),
        RawTacticalPuzzle("5rk1/5ppp/1Q6/8/8/8/8/K2R4 w - - 0 1", listOf("b6d8", "f8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from b6"),
        RawTacticalPuzzle("5rk1/5ppp/3Q4/8/8/8/8/K2R4 w - - 0 1", listOf("d6d8", "f8d8", "d1d8"), "Queen Back-Rank Sacrifice on d8 from d6")
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
            val cleanTheme = "Sacrifice"
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
