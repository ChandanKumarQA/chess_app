package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object MateIn2Database {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("6k1/5pp1/8/8/8/8/P1B5/3Q2K1 w - - 0 1", listOf("c2h7", "g8h7", "d1h5"), "Greek Gift Mate in 2 (Bc2 & Qd1)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/8/8/P7/1B1Q2K1 w - - 0 1", listOf("b1h7", "g8h7", "d1h5"), "Greek Gift Mate in 2 (Bb1 & Qd1)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/8/3B4/P7/3Q2K1 w - - 0 1", listOf("d3h7", "g8h7", "d1h5"), "Greek Gift Mate in 2 (Bd3 & Qd1)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/4B3/8/P7/3Q2K1 w - - 0 1", listOf("e4h7", "g8h7", "d1h5"), "Greek Gift Mate in 2 (Be4 & Qd1)"),
        RawTacticalPuzzle("5r1k/6pp/8/4N3/8/8/QP6/6K1 w - - 0 1", listOf("a2g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qa2 & Ne5)"),
        RawTacticalPuzzle("5r1k/6pp/8/4N3/2Q5/8/P7/6K1 w - - 0 1", listOf("c4g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qc4 & Ne5)"),
        RawTacticalPuzzle("5r1k/6pp/8/3QN3/8/8/P7/6K1 w - - 0 1", listOf("d5g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qd5 & Ne5)"),
        RawTacticalPuzzle("5r1k/6pp/4Q3/4N3/8/8/P7/6K1 w - - 0 1", listOf("e6g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qe6 & Ne5)"),
        RawTacticalPuzzle("5r1k/6pp/8/4N3/8/1Q6/P7/6K1 w - - 0 1", listOf("b3g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qb3 & Ne5)"),
        RawTacticalPuzzle("8/6pk/8/R4N2/8/8/P7/6K1 w - - 0 1", listOf("f5e7", "h7h8", "a5h5"), "Anastasia Mate in 2 (Ra5 & Nf5)"),
        RawTacticalPuzzle("8/6pk/8/R2N4/8/8/P7/6K1 w - - 0 1", listOf("d5e7", "h7h8", "a5h5"), "Anastasia Mate in 2 (Ra5 & Nd5)"),
        RawTacticalPuzzle("8/6pk/6N1/R7/8/8/P7/6K1 w - - 0 1", listOf("g6e7", "h7h8", "a5h5"), "Anastasia Mate in 2 (Ra5 & Ng6)"),
        RawTacticalPuzzle("8/6pk/2N5/R7/8/8/P7/6K1 w - - 0 1", listOf("c6e7", "h7h8", "a5h5"), "Anastasia Mate in 2 (Ra5 & Nc6)"),
        RawTacticalPuzzle("2N5/6pk/8/R7/8/8/P7/6K1 w - - 0 1", listOf("c8e7", "h7h8", "a5h5"), "Anastasia Mate in 2 (Ra5 & Nc8)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/8/8/1PB5/3Q2K1 w - - 0 1", listOf("c2h7", "g8h7", "d1h5"), "Greek Gift Mate in 2 (Bc2 & Qd1)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/8/8/1P6/1B1Q2K1 w - - 0 1", listOf("b1h7", "g8h7", "d1h5"), "Greek Gift Mate in 2 (Bb1 & Qd1)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/8/3B4/1P6/3Q2K1 w - - 0 1", listOf("d3h7", "g8h7", "d1h5"), "Greek Gift Mate in 2 (Bd3 & Qd1)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/4B3/8/1P6/3Q2K1 w - - 0 1", listOf("e4h7", "g8h7", "d1h5"), "Greek Gift Mate in 2 (Be4 & Qd1)"),
        RawTacticalPuzzle("5r1k/6pp/8/4N3/8/8/Q1P5/6K1 w - - 0 1", listOf("a2g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qa2 & Ne5)"),
        RawTacticalPuzzle("5r1k/6pp/8/4N3/2Q5/8/1P6/6K1 w - - 0 1", listOf("c4g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qc4 & Ne5)"),
        RawTacticalPuzzle("5r1k/6pp/8/3QN3/8/8/1P6/6K1 w - - 0 1", listOf("d5g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qd5 & Ne5)"),
        RawTacticalPuzzle("5r1k/6pp/4Q3/4N3/8/8/1P6/6K1 w - - 0 1", listOf("e6g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qe6 & Ne5)"),
        RawTacticalPuzzle("5r1k/6pp/8/4N3/8/1Q6/1P6/6K1 w - - 0 1", listOf("b3g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qb3 & Ne5)"),
        RawTacticalPuzzle("8/6pk/8/R4N2/8/8/1P6/6K1 w - - 0 1", listOf("f5e7", "h7h8", "a5h5"), "Anastasia Mate in 2 (Ra5 & Nf5)"),
        RawTacticalPuzzle("8/6pk/8/R2N4/8/8/1P6/6K1 w - - 0 1", listOf("d5e7", "h7h8", "a5h5"), "Anastasia Mate in 2 (Ra5 & Nd5)"),
        RawTacticalPuzzle("8/6pk/6N1/R7/8/8/1P6/6K1 w - - 0 1", listOf("g6e7", "h7h8", "a5h5"), "Anastasia Mate in 2 (Ra5 & Ng6)"),
        RawTacticalPuzzle("8/6pk/2N5/R7/8/8/1P6/6K1 w - - 0 1", listOf("c6e7", "h7h8", "a5h5"), "Anastasia Mate in 2 (Ra5 & Nc6)"),
        RawTacticalPuzzle("2N5/6pk/8/R7/8/8/1P6/6K1 w - - 0 1", listOf("c8e7", "h7h8", "a5h5"), "Anastasia Mate in 2 (Ra5 & Nc8)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/8/8/2B2P2/3Q2K1 w - - 0 1", listOf("c2h7", "g8h7", "d1h5"), "Greek Gift Mate in 2 (Bc2 & Qd1)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/8/8/5P2/1B1Q2K1 w - - 0 1", listOf("b1h7", "g8h7", "d1h5"), "Greek Gift Mate in 2 (Bb1 & Qd1)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/8/3B4/2P5/3Q2K1 w - - 0 1", listOf("d3h7", "g8h7", "d1h5"), "Greek Gift Mate in 2 (Bd3 & Qd1)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/4B3/8/2P5/3Q2K1 w - - 0 1", listOf("e4h7", "g8h7", "d1h5"), "Greek Gift Mate in 2 (Be4 & Qd1)"),
        RawTacticalPuzzle("5r1k/6pp/8/4N3/8/8/Q4P2/6K1 w - - 0 1", listOf("a2g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qa2 & Ne5)"),
        RawTacticalPuzzle("5r1k/6pp/8/4N3/2Q5/8/2P5/6K1 w - - 0 1", listOf("c4g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qc4 & Ne5)"),
        RawTacticalPuzzle("5r1k/6pp/8/3QN3/8/8/2P5/6K1 w - - 0 1", listOf("d5g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qd5 & Ne5)"),
        RawTacticalPuzzle("5r1k/6pp/4Q3/4N3/8/8/2P5/6K1 w - - 0 1", listOf("e6g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qe6 & Ne5)"),
        RawTacticalPuzzle("5r1k/6pp/8/4N3/8/1Q6/2P5/6K1 w - - 0 1", listOf("b3g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qb3 & Ne5)"),
        RawTacticalPuzzle("8/6pk/8/R4N2/8/8/2P5/6K1 w - - 0 1", listOf("f5e7", "h7h8", "a5h5"), "Anastasia Mate in 2 (Ra5 & Nf5)"),
        RawTacticalPuzzle("8/6pk/8/R2N4/8/8/2P5/6K1 w - - 0 1", listOf("d5e7", "h7h8", "a5h5"), "Anastasia Mate in 2 (Ra5 & Nd5)"),
        RawTacticalPuzzle("8/6pk/6N1/R7/8/8/2P5/6K1 w - - 0 1", listOf("g6e7", "h7h8", "a5h5"), "Anastasia Mate in 2 (Ra5 & Ng6)"),
        RawTacticalPuzzle("8/6pk/2N5/R7/8/8/2P5/6K1 w - - 0 1", listOf("c6e7", "h7h8", "a5h5"), "Anastasia Mate in 2 (Ra5 & Nc6)"),
        RawTacticalPuzzle("2N5/6pk/8/R7/8/8/2P5/6K1 w - - 0 1", listOf("c8e7", "h7h8", "a5h5"), "Anastasia Mate in 2 (Ra5 & Nc8)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/8/5Q2/P1B5/6K1 w - - 0 1", listOf("c2h7", "g8h7", "f3h5"), "Greek Gift Mate in 2 (Bc2 & Qf3)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/8/5Q2/P7/1B4K1 w - - 0 1", listOf("b1h7", "g8h7", "f3h5"), "Greek Gift Mate in 2 (Bb1 & Qf3)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/8/3B4/5P2/3Q2K1 w - - 0 1", listOf("d3h7", "g8h7", "d1h5"), "Greek Gift Mate in 2 (Bd3 & Qd1)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/4B3/8/5P2/3Q2K1 w - - 0 1", listOf("e4h7", "g8h7", "d1h5"), "Greek Gift Mate in 2 (Be4 & Qd1)"),
        RawTacticalPuzzle("5r1k/6pp/8/4N3/8/8/Q2P4/6K1 w - - 0 1", listOf("a2g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qa2 & Ne5)"),
        RawTacticalPuzzle("5r1k/6pp/8/4N3/2Q5/8/5P2/6K1 w - - 0 1", listOf("c4g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qc4 & Ne5)"),
        RawTacticalPuzzle("5r1k/6pp/8/3QN3/8/8/5P2/6K1 w - - 0 1", listOf("d5g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qd5 & Ne5)"),
        RawTacticalPuzzle("5r1k/6pp/4Q3/4N3/8/8/5P2/6K1 w - - 0 1", listOf("e6g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qe6 & Ne5)"),
        RawTacticalPuzzle("5r1k/6pp/8/4N3/8/1Q6/5P2/6K1 w - - 0 1", listOf("b3g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qb3 & Ne5)"),
        RawTacticalPuzzle("8/6pk/8/R4N2/8/8/5P2/6K1 w - - 0 1", listOf("f5e7", "h7h8", "a5h5"), "Anastasia Mate in 2 (Ra5 & Nf5)"),
        RawTacticalPuzzle("8/6pk/8/R2N4/8/8/5P2/6K1 w - - 0 1", listOf("d5e7", "h7h8", "a5h5"), "Anastasia Mate in 2 (Ra5 & Nd5)"),
        RawTacticalPuzzle("8/6pk/6N1/R7/8/8/5P2/6K1 w - - 0 1", listOf("g6e7", "h7h8", "a5h5"), "Anastasia Mate in 2 (Ra5 & Ng6)"),
        RawTacticalPuzzle("8/6pk/2N5/R7/8/8/5P2/6K1 w - - 0 1", listOf("c6e7", "h7h8", "a5h5"), "Anastasia Mate in 2 (Ra5 & Nc6)"),
        RawTacticalPuzzle("2N5/6pk/8/R7/8/8/5P2/6K1 w - - 0 1", listOf("c8e7", "h7h8", "a5h5"), "Anastasia Mate in 2 (Ra5 & Nc8)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/8/5Q2/1PB5/6K1 w - - 0 1", listOf("c2h7", "g8h7", "f3h5"), "Greek Gift Mate in 2 (Bc2 & Qf3)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/8/5Q2/1P6/1B4K1 w - - 0 1", listOf("b1h7", "g8h7", "f3h5"), "Greek Gift Mate in 2 (Bb1 & Qf3)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/8/3B1Q2/P7/6K1 w - - 0 1", listOf("d3h7", "g8h7", "f3h5"), "Greek Gift Mate in 2 (Bd3 & Qf3)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/4B3/5Q2/P7/6K1 w - - 0 1", listOf("e4h7", "g8h7", "f3h5"), "Greek Gift Mate in 2 (Be4 & Qf3)"),
        RawTacticalPuzzle("5r1k/6pp/8/6N1/8/8/QP6/6K1 w - - 0 1", listOf("a2g8", "f8g8", "g5f7"), "Philidor Mate in 2 (Qa2 & Ng5)"),
        RawTacticalPuzzle("5r1k/6pp/8/4N3/2Q5/8/3P4/6K1 w - - 0 1", listOf("c4g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qc4 & Ne5)"),
        RawTacticalPuzzle("5r1k/6pp/8/3QN3/8/8/3P4/6K1 w - - 0 1", listOf("d5g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qd5 & Ne5)"),
        RawTacticalPuzzle("5r1k/6pp/4Q3/4N3/8/8/3P4/6K1 w - - 0 1", listOf("e6g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qe6 & Ne5)"),
        RawTacticalPuzzle("5r1k/6pp/8/4N3/8/1Q6/3P4/6K1 w - - 0 1", listOf("b3g8", "f8g8", "e5f7"), "Philidor Mate in 2 (Qb3 & Ne5)"),
        RawTacticalPuzzle("8/6pk/8/1R3N2/8/8/P7/6K1 w - - 0 1", listOf("f5e7", "h7h8", "b5h5"), "Anastasia Mate in 2 (Rb5 & Nf5)"),
        RawTacticalPuzzle("8/6pk/8/1R1N4/8/8/P7/6K1 w - - 0 1", listOf("d5e7", "h7h8", "b5h5"), "Anastasia Mate in 2 (Rb5 & Nd5)"),
        RawTacticalPuzzle("8/6pk/6N1/1R6/8/8/P7/6K1 w - - 0 1", listOf("g6e7", "h7h8", "b5h5"), "Anastasia Mate in 2 (Rb5 & Ng6)"),
        RawTacticalPuzzle("8/6pk/2N5/1R6/8/8/P7/6K1 w - - 0 1", listOf("c6e7", "h7h8", "b5h5"), "Anastasia Mate in 2 (Rb5 & Nc6)"),
        RawTacticalPuzzle("2N5/6pk/8/1R6/8/8/P7/6K1 w - - 0 1", listOf("c8e7", "h7h8", "b5h5"), "Anastasia Mate in 2 (Rb5 & Nc8)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/8/5Q2/2B2P2/6K1 w - - 0 1", listOf("c2h7", "g8h7", "f3h5"), "Greek Gift Mate in 2 (Bc2 & Qf3)"),
        RawTacticalPuzzle("5r1k/6pp/4Q3/6N1/8/8/P7/6K1 w - - 0 1", listOf("e6g8", "f8g8", "g5f7"), "Philidor Mate in 2 (Qe6 & Ng5)"),
        RawTacticalPuzzle("5r1k/6pp/8/6N1/8/1Q6/P7/6K1 w - - 0 1", listOf("b3g8", "f8g8", "g5f7"), "Philidor Mate in 2 (Qb3 & Ng5)"),
        RawTacticalPuzzle("8/6pk/8/1R3N2/8/8/1P6/6K1 w - - 0 1", listOf("f5e7", "h7h8", "b5h5"), "Anastasia Mate in 2 (Rb5 & Nf5)"),
        RawTacticalPuzzle("8/6pk/8/1R1N4/8/8/1P6/6K1 w - - 0 1", listOf("d5e7", "h7h8", "b5h5"), "Anastasia Mate in 2 (Rb5 & Nd5)"),
        RawTacticalPuzzle("8/6pk/6N1/1R6/8/8/1P6/6K1 w - - 0 1", listOf("g6e7", "h7h8", "b5h5"), "Anastasia Mate in 2 (Rb5 & Ng6)"),
        RawTacticalPuzzle("8/6pk/2N5/1R6/8/8/1P6/6K1 w - - 0 1", listOf("c6e7", "h7h8", "b5h5"), "Anastasia Mate in 2 (Rb5 & Nc6)"),
        RawTacticalPuzzle("2N5/6pk/8/1R6/8/8/1P6/6K1 w - - 0 1", listOf("c8e7", "h7h8", "b5h5"), "Anastasia Mate in 2 (Rb5 & Nc8)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/8/5Q2/5P2/1B4K1 w - - 0 1", listOf("b1h7", "g8h7", "f3h5"), "Greek Gift Mate in 2 (Bb1 & Qf3)"),
        RawTacticalPuzzle("8/6pk/8/1R3N2/8/8/2P5/6K1 w - - 0 1", listOf("f5e7", "h7h8", "b5h5"), "Anastasia Mate in 2 (Rb5 & Nf5)"),
        RawTacticalPuzzle("8/6pk/8/1R1N4/8/8/2P5/6K1 w - - 0 1", listOf("d5e7", "h7h8", "b5h5"), "Anastasia Mate in 2 (Rb5 & Nd5)"),
        RawTacticalPuzzle("8/6pk/6N1/1R6/8/8/2P5/6K1 w - - 0 1", listOf("g6e7", "h7h8", "b5h5"), "Anastasia Mate in 2 (Rb5 & Ng6)"),
        RawTacticalPuzzle("8/6pk/2N5/1R6/8/8/2P5/6K1 w - - 0 1", listOf("c6e7", "h7h8", "b5h5"), "Anastasia Mate in 2 (Rb5 & Nc6)"),
        RawTacticalPuzzle("2N5/6pk/8/1R6/8/8/2P5/6K1 w - - 0 1", listOf("c8e7", "h7h8", "b5h5"), "Anastasia Mate in 2 (Rb5 & Nc8)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/8/3B1Q2/1P6/6K1 w - - 0 1", listOf("d3h7", "g8h7", "f3h5"), "Greek Gift Mate in 2 (Bd3 & Qf3)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/4B3/5Q2/1P6/6K1 w - - 0 1", listOf("e4h7", "g8h7", "f3h5"), "Greek Gift Mate in 2 (Be4 & Qf3)"),
        RawTacticalPuzzle("5r1k/6pp/8/6N1/8/8/Q1P5/6K1 w - - 0 1", listOf("a2g8", "f8g8", "g5f7"), "Philidor Mate in 2 (Qa2 & Ng5)"),
        RawTacticalPuzzle("5r1k/6pp/8/6N1/2Q5/8/P7/6K1 w - - 0 1", listOf("c4g8", "f8g8", "g5f7"), "Philidor Mate in 2 (Qc4 & Ng5)"),
        RawTacticalPuzzle("5r1k/6pp/8/3Q2N1/8/8/P7/6K1 w - - 0 1", listOf("d5g8", "f8g8", "g5f7"), "Philidor Mate in 2 (Qd5 & Ng5)"),
        RawTacticalPuzzle("5r1k/6pp/4Q3/6N1/8/8/1P6/6K1 w - - 0 1", listOf("e6g8", "f8g8", "g5f7"), "Philidor Mate in 2 (Qe6 & Ng5)"),
        RawTacticalPuzzle("5r1k/6pp/8/6N1/8/1Q6/1P6/6K1 w - - 0 1", listOf("b3g8", "f8g8", "g5f7"), "Philidor Mate in 2 (Qb3 & Ng5)"),
        RawTacticalPuzzle("8/6pk/8/1R3N2/8/8/5P2/6K1 w - - 0 1", listOf("f5e7", "h7h8", "b5h5"), "Anastasia Mate in 2 (Rb5 & Nf5)"),
        RawTacticalPuzzle("8/6pk/8/1R1N4/8/8/5P2/6K1 w - - 0 1", listOf("d5e7", "h7h8", "b5h5"), "Anastasia Mate in 2 (Rb5 & Nd5)"),
        RawTacticalPuzzle("8/6pk/6N1/1R6/8/8/5P2/6K1 w - - 0 1", listOf("g6e7", "h7h8", "b5h5"), "Anastasia Mate in 2 (Rb5 & Ng6)"),
        RawTacticalPuzzle("8/6pk/2N5/1R6/8/8/5P2/6K1 w - - 0 1", listOf("c6e7", "h7h8", "b5h5"), "Anastasia Mate in 2 (Rb5 & Nc6)"),
        RawTacticalPuzzle("2N5/6pk/8/1R6/8/8/5P2/6K1 w - - 0 1", listOf("c8e7", "h7h8", "b5h5"), "Anastasia Mate in 2 (Rb5 & Nc8)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/8/3B1Q2/2P5/6K1 w - - 0 1", listOf("d3h7", "g8h7", "f3h5"), "Greek Gift Mate in 2 (Bd3 & Qf3)"),
        RawTacticalPuzzle("6k1/5pp1/8/8/4B3/5Q2/2P5/6K1 w - - 0 1", listOf("e4h7", "g8h7", "f3h5"), "Greek Gift Mate in 2 (Be4 & Qf3)"),
        RawTacticalPuzzle("5r1k/6pp/8/6N1/8/8/Q4P2/6K1 w - - 0 1", listOf("a2g8", "f8g8", "g5f7"), "Philidor Mate in 2 (Qa2 & Ng5)"),
        RawTacticalPuzzle("5r1k/6pp/8/6N1/2Q5/8/1P6/6K1 w - - 0 1", listOf("c4g8", "f8g8", "g5f7"), "Philidor Mate in 2 (Qc4 & Ng5)")
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
