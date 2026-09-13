package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object DeflectionDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("1k1r4/ppp5/3n4/8/8/8/5PPP/3RQ1K1 w - - 0 1", listOf("e1e8", "d6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/5n2/8/8/8/5PPP/3RQ1K1 w - - 0 1", listOf("e1e8", "f6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp3n1/8/8/8/8/5PPP/3RQ1K1 w - - 0 1", listOf("e1e8", "g7e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/3n4/8/8/8/4QPPP/3R2K1 w - - 0 1", listOf("e2e8", "d6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/5n2/8/8/8/4QPPP/3R2K1 w - - 0 1", listOf("e2e8", "f6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp3n1/8/8/8/8/4QPPP/3R2K1 w - - 0 1", listOf("e2e8", "g7e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/3n4/8/8/4Q3/5PPP/3R2K1 w - - 0 1", listOf("e3e8", "d6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/5n2/8/8/4Q3/5PPP/3R2K1 w - - 0 1", listOf("e3e8", "f6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp3n1/8/8/8/4Q3/5PPP/3R2K1 w - - 0 1", listOf("e3e8", "g7e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/3n4/8/Q7/8/5PPP/3R2K1 w - - 0 1", listOf("a4e8", "d6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/5n2/8/Q7/8/5PPP/3R2K1 w - - 0 1", listOf("a4e8", "f6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp3n1/8/8/Q7/8/5PPP/3R2K1 w - - 0 1", listOf("a4e8", "g7e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/3n4/8/4Q3/8/5PPP/3R2K1 w - - 0 1", listOf("e4e8", "d6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/5n2/8/4Q3/8/5PPP/3R2K1 w - - 0 1", listOf("e4e8", "f6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp3n1/8/8/4Q3/8/5PPP/3R2K1 w - - 0 1", listOf("e4e8", "g7e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/3n4/1Q6/8/8/5PPP/3R2K1 w - - 0 1", listOf("b5e8", "d6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/5n2/1Q6/8/8/5PPP/3R2K1 w - - 0 1", listOf("b5e8", "f6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp3n1/8/1Q6/8/8/5PPP/3R2K1 w - - 0 1", listOf("b5e8", "g7e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/3n4/4Q3/8/8/5PPP/3R2K1 w - - 0 1", listOf("e5e8", "d6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/5n2/4Q3/8/8/5PPP/3R2K1 w - - 0 1", listOf("e5e8", "f6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp3n1/8/4Q3/8/8/5PPP/3R2K1 w - - 0 1", listOf("e5e8", "g7e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/3n4/7Q/8/8/5PPP/3R2K1 w - - 0 1", listOf("h5e8", "d6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/5n2/7Q/8/8/5PPP/3R2K1 w - - 0 1", listOf("h5e8", "f6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp3n1/8/7Q/8/8/5PPP/3R2K1 w - - 0 1", listOf("h5e8", "g7e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/2Qn4/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("c6e8", "d6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/2Q2n2/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("c6e8", "f6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp3n1/2Q5/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("c6e8", "g7e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/3nQ3/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("e6e8", "d6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/4Qn2/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("e6e8", "f6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp3n1/4Q3/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("e6e8", "g7e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/3n2Q1/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("g6e8", "d6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/5nQ1/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("g6e8", "f6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp3n1/6Q1/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("g6e8", "g7e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/pppQ4/3n4/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("d7e8", "d6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/pppQ4/5n2/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("d7e8", "f6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/pppQ2n1/8/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("d7e8", "g7e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp1Q3/3n4/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("e7e8", "d6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp1Q3/5n2/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("e7e8", "f6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp1Q1n1/8/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("e7e8", "g7e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp2Q2/3n4/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("f7e8", "d6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp2Q2/5n2/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("f7e8", "f6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp2Qn1/8/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("f7e8", "g7e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r1Q2/ppp5/3n4/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("f8e8", "d6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r1Q2/ppp5/5n2/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("f8e8", "f6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r1Q2/ppp3n1/8/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("f8e8", "g7e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r2Q1/ppp5/3n4/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("g8e8", "d6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r2Q1/ppp5/5n2/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("g8e8", "f6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r2Q1/ppp3n1/8/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("g8e8", "g7e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r3Q/ppp5/3n4/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("h8e8", "d6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r3Q/ppp5/5n2/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("h8e8", "f6e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r3Q/ppp3n1/8/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("h8e8", "g7e8", "d1d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/3n4/8/8/8/3R1PPP/4Q1K1 w - - 0 1", listOf("e1e8", "d6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/5n2/8/8/8/3R1PPP/4Q1K1 w - - 0 1", listOf("e1e8", "f6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp3n1/8/8/8/8/3R1PPP/4Q1K1 w - - 0 1", listOf("e1e8", "g7e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/3n4/8/8/8/3RQPPP/6K1 w - - 0 1", listOf("e2e8", "d6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/5n2/8/8/8/3RQPPP/6K1 w - - 0 1", listOf("e2e8", "f6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp3n1/8/8/8/8/3RQPPP/6K1 w - - 0 1", listOf("e2e8", "g7e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/3n4/8/8/4Q3/3R1PPP/6K1 w - - 0 1", listOf("e3e8", "d6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/5n2/8/8/4Q3/3R1PPP/6K1 w - - 0 1", listOf("e3e8", "f6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp3n1/8/8/8/4Q3/3R1PPP/6K1 w - - 0 1", listOf("e3e8", "g7e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/3n4/8/Q7/8/3R1PPP/6K1 w - - 0 1", listOf("a4e8", "d6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/5n2/8/Q7/8/3R1PPP/6K1 w - - 0 1", listOf("a4e8", "f6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp3n1/8/8/Q7/8/3R1PPP/6K1 w - - 0 1", listOf("a4e8", "g7e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/3n4/8/4Q3/8/3R1PPP/6K1 w - - 0 1", listOf("e4e8", "d6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/5n2/8/4Q3/8/3R1PPP/6K1 w - - 0 1", listOf("e4e8", "f6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp3n1/8/8/4Q3/8/3R1PPP/6K1 w - - 0 1", listOf("e4e8", "g7e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/3n4/1Q6/8/8/3R1PPP/6K1 w - - 0 1", listOf("b5e8", "d6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/5n2/1Q6/8/8/3R1PPP/6K1 w - - 0 1", listOf("b5e8", "f6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp3n1/8/1Q6/8/8/3R1PPP/6K1 w - - 0 1", listOf("b5e8", "g7e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/3n4/4Q3/8/8/3R1PPP/6K1 w - - 0 1", listOf("e5e8", "d6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/5n2/4Q3/8/8/3R1PPP/6K1 w - - 0 1", listOf("e5e8", "f6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp3n1/8/4Q3/8/8/3R1PPP/6K1 w - - 0 1", listOf("e5e8", "g7e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/3n4/7Q/8/8/3R1PPP/6K1 w - - 0 1", listOf("h5e8", "d6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/5n2/7Q/8/8/3R1PPP/6K1 w - - 0 1", listOf("h5e8", "f6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp3n1/8/7Q/8/8/3R1PPP/6K1 w - - 0 1", listOf("h5e8", "g7e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/2Qn4/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("c6e8", "d6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/2Q2n2/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("c6e8", "f6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp3n1/2Q5/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("c6e8", "g7e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/3nQ3/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("e6e8", "d6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/4Qn2/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("e6e8", "f6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp3n1/4Q3/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("e6e8", "g7e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/3n2Q1/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("g6e8", "d6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp5/5nQ1/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("g6e8", "f6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp3n1/6Q1/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("g6e8", "g7e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/pppQ4/3n4/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("d7e8", "d6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/pppQ4/5n2/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("d7e8", "f6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/pppQ2n1/8/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("d7e8", "g7e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp1Q3/3n4/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("e7e8", "d6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp1Q3/5n2/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("e7e8", "f6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp1Q1n1/8/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("e7e8", "g7e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp2Q2/3n4/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("f7e8", "d6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp2Q2/5n2/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("f7e8", "f6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r4/ppp2Qn1/8/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("f7e8", "g7e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r1Q2/ppp5/3n4/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("f8e8", "d6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r1Q2/ppp5/5n2/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("f8e8", "f6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r1Q2/ppp3n1/8/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("f8e8", "g7e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r2Q1/ppp5/3n4/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("g8e8", "d6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r2Q1/ppp5/5n2/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("g8e8", "f6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r2Q1/ppp3n1/8/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("g8e8", "g7e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8"),
        RawTacticalPuzzle("1k1r3Q/ppp5/3n4/8/8/8/3R1PPP/6K1 w - - 0 1", listOf("h8e8", "d6e8", "d2d8"), "Deflection of Knight to e8 for Mate on d8")
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
            val cleanTheme = "Deflection"
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
