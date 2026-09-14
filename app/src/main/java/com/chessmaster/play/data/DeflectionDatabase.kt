package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object DeflectionDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("1R6/6pk/5p1p/2N5/6PK/r6P/8/8 b - - 2 40", listOf("g7g5", "h4h5", "a3h3"), "Deflection Combination"),
        RawTacticalPuzzle("8/3R3p/6pk/5p2/5PP1/7P/rr1B1K2/8 w - - 1 47", listOf("g4g5", "h6h5", "d7h7"), "Deflection Combination"),
        RawTacticalPuzzle("6k1/p7/2p1r1bp/3p2p1/8/1PP3BP/P4P2/4RK2 b - - 1 35", listOf("g6d3", "f1g2", "e6e1"), "Deflection Combination"),
        RawTacticalPuzzle("8/3k1p2/3Pb3/4P1p1/N7/4K3/8/8 w - - 10 55", listOf("a4c5", "d7c6", "c5e6"), "Deflection Combination"),
        RawTacticalPuzzle("5rk1/p3Q1pp/2p5/8/4p1B1/6PP/4P1K1/3q4 w - - 4 32", listOf("g4e6", "g8h8", "e7f8"), "Deflection Combination"),
        RawTacticalPuzzle("7k/2p4p/pb4p1/1p3p2/1P6/2P5/4rPPP/4RRK1 b - - 2 28", listOf("b6f2", "f1f2", "e2e1"), "Deflection Combination"),
        RawTacticalPuzzle("1R6/2P5/p5k1/6pp/1P6/6PK/r6P/8 b - - 0 40", listOf("g5g4", "h3h4", "a2h2"), "Deflection Combination"),
        RawTacticalPuzzle("r1krR3/ppp2B2/5p1p/8/8/8/PPP2PPP/2K5 w - - 2 21", listOf("f7e6", "c8b8", "e8d8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("6k1/pp3pp1/1n4p1/2p5/6P1/1B3PKP/PP1r4/4R3 w - - 6 29", listOf("e1e8", "g8h7", "b3f7"), "Deflection Combination"),
        RawTacticalPuzzle("rnb2r2/ppp2B1k/3p3p/2b4Q/4P3/3P4/Pq1NKPPP/8 w - - 0 15", listOf("h5g6", "h7h8", "g6h6"), "Deflection in Bishops Opening Bishops"),
        RawTacticalPuzzle("3r4/pR4p1/2p2N1k/4p2p/2B1Pn1N/1P6/P1P2PPP/3R2K1 b - - 0 31", listOf("d8d1", "c4f1", "f4e2", "g1h1", "d1f1"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("8/1R6/p1pk4/2q3bp/1QP5/P7/KP6/3r4 w - - 3 45", listOf("b7d7", "d6d7", "b4c5"), "Deflection Combination"),
        RawTacticalPuzzle("8/5kp1/p3pb2/8/6Pp/1P4qP/P2R2Q1/7K b - - 3 34", listOf("g3e1", "g2g1", "e1d2"), "Deflection Combination"),
        RawTacticalPuzzle("8/R1R2ppk/3qp1bp/pB6/P7/4P1QP/6PK/3r4 b - - 12 34", listOf("d1h1", "h2h1", "d6g3"), "Deflection Combination"),
        RawTacticalPuzzle("8/p4bkp/4r3/2Q5/8/N4qPP/PPP1n2K/3R1R2 b - - 0 27", listOf("f3g3", "h2h1", "g3h3"), "Deflection Combination"),
        RawTacticalPuzzle("4Q3/2B3k1/4p2p/2P3p1/3P4/4p3/6PP/4qNK1 b - - 1 34", listOf("e1f2", "g1h1", "f2f1"), "Deflection Combination"),
        RawTacticalPuzzle("r4rk1/pp3ppp/2p2q2/3p4/4n3/1P1BQN2/2K2PPP/3R3R b - - 3 18", listOf("f6c3", "c2b1", "c3b3"), "Deflection in Philidor Defense Philidor"),
        RawTacticalPuzzle("7Q/2p5/1p2prp1/p4k1p/q4p1P/8/6RK/8 w - - 0 38", listOf("g2g5", "f5e4", "h8f6"), "Deflection Combination"),
        RawTacticalPuzzle("2r2r2/2b1qpk1/p1n1p2p/1pPp2pn/1P1P4/P2Q3P/2BB1PP1/R3R1K1 w - - 0 24", listOf("d3h7", "g7f6", "h7h6"), "Deflection Combination"),
        RawTacticalPuzzle("5rk1/5p2/4p1p1/7R/2P5/2n2N2/5r2/2K4R w - - 0 38", listOf("h5h8", "g8g7", "h1h7", "g7f6", "h8f8"), "Deflection Combination"),
        RawTacticalPuzzle("r6r/p1p3k1/1p1qpbp1/8/3P2R1/2P2P2/PPQ2P1P/2KR4 w - - 0 19", listOf("c2g6", "g7f8", "g6f6"), "Deflection in French Defense French"),
        RawTacticalPuzzle("7r/8/3b4/3p1P2/6R1/2kN4/4KP2/8 b - - 0 67", listOf("h8e8", "e2f3", "c3d3"), "Deflection Combination"),
        RawTacticalPuzzle("8/5k2/7p/1R3Kp1/4P3/5P1P/1pr5/8 b - - 3 51", listOf("c2c5", "b5c5", "b2b1q"), "Deflection Combination"),
        RawTacticalPuzzle("8/5r2/7k/6pp/7P/5PK1/R7/8 w - - 0 59", listOf("a2a6", "h6g7", "h4g5"), "Deflection Combination"),
        RawTacticalPuzzle("8/R5p1/5k1p/8/6PK/1r5P/8/8 b - - 0 38", listOf("g7g5", "h4h5", "b3h3"), "Deflection Combination"),
        RawTacticalPuzzle("r3kb1r/ppp2pp1/3p4/1P2p3/2P3Qn/2N1P2q/PB1P1P1N/R4RK1 b kq - 0 15", listOf("h4f3", "h2f3", "h3g4"), "Deflection in Polish Opening Polish"),
        RawTacticalPuzzle("5rk1/b5pp/4p3/pp1N4/8/P2rP3/B5PP/2B2RK1 w - - 0 27", listOf("d5e7", "g8h8", "f1f8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("8/7R/5p2/p7/7P/2p5/3k2N1/1K6 b - - 0 48", listOf("c3c2", "b1a2", "c2c1q", "h7d7", "d2e2"), "Deflection Combination"),
        RawTacticalPuzzle("7r/pp2Rp2/1n4pk/4Q3/3N2q1/8/PP3PPP/5K2 b - - 0 29", listOf("g4d1", "e5e1", "d1d4", "e1e3", "d4e3"), "Deflection Combination"),
        RawTacticalPuzzle("1k4r1/3p1p2/1Bn4p/5q2/5P2/1Q4P1/P7/1R4K1 b - - 3 29", listOf("g8g3", "b3g3", "f5b1"), "Deflection Combination"),
        RawTacticalPuzzle("8/1R3p1k/3q2pp/8/pP6/P1Q3PP/5PK1/3r4 b - - 2 37", listOf("d6d5", "c3f3", "d1g1", "g2g1", "d5f3"), "Deflection Combination"),
        RawTacticalPuzzle("4rrk1/pB4pp/2N2p2/2p5/6b1/1PN3P1/P1P2P1P/4RK2 b - - 0 21", listOf("g4h3", "f1g1", "e8e1"), "Deflection Combination"),
        RawTacticalPuzzle("4Qn1k/6p1/7p/8/1b1P2N1/2q1B2P/6P1/6K1 b - - 1 31", listOf("c3e1", "g1h2", "b4d6", "g4e5", "e1e3"), "Deflection Combination"),
        RawTacticalPuzzle("2kr3r/ppp2ppp/2nbp3/6B1/P2P2n1/2N2N1q/1PP2P2/R2QRBK1 b - - 3 14", listOf("d6h2", "g1h1", "g4f2"), "Deflection in Scandinavian Defense Scandinavian"),
        RawTacticalPuzzle("4k3/5R2/p1p2K2/r4PpP/8/7P/8/8 b - - 0 51", listOf("a5f5", "f6f5", "e8f7"), "Deflection Combination"),
        RawTacticalPuzzle("2r3k1/p4pp1/1p5p/1Q6/2q5/5P2/1P4PP/3R2K1 w - - 4 31", listOf("d1d8", "c8d8", "b5c4", "d8d1", "g1f2"), "Deflection Combination"),
        RawTacticalPuzzle("3qr1k1/p4p1p/6p1/3Q4/8/1P3P2/P5PP/3R2K1 b - - 0 26", listOf("e8e1", "d1e1", "d8d5"), "Deflection Combination"),
        RawTacticalPuzzle("5rk1/4Q1pp/4pp2/pP6/P1BpP1PP/8/7K/3q4 w - - 2 35", listOf("c4e6", "g8h8", "e7f8"), "Deflection Combination"),
        RawTacticalPuzzle("5R2/8/p5pp/2B5/5b1P/P3p3/8/3K1k2 b - - 3 46", listOf("e3e2", "d1c2", "e2e1q", "f8f4", "f1g2"), "Deflection Combination"),
        RawTacticalPuzzle("8/8/2p1k1p1/p1KpP3/P4PP1/8/8/8 b - - 1 39", listOf("g6g5", "f4g5", "e6e5"), "Deflection Combination"),
        RawTacticalPuzzle("3r2k1/1p2R3/7p/P4rpB/8/2n3P1/1q4PP/3R2K1 w - - 2 33", listOf("d1d8", "f5f8", "h5f7", "g8h8", "d8f8"), "Deflection Combination"),
        RawTacticalPuzzle("5k2/6p1/p1b3Pp/2N2P1r/p7/8/1KP5/5R2 w - - 0 37", listOf("c5e6", "f8e7", "e6g7"), "Deflection Combination"),
        RawTacticalPuzzle("6k1/8/p2b1q2/1p1p4/1P1P3p/P4QP1/5PK1/4R3 b - - 1 38", listOf("h4h3", "g2h3", "f6f3"), "Deflection Combination"),
        RawTacticalPuzzle("r1q3k1/3nbppp/pp2p3/4B3/8/2N2Q2/PPPR1PPP/6K1 w - - 1 19", listOf("d2d7", "c8d7", "f3a8"), "Deflection in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("6k1/1p1r1pp1/p6p/3p2q1/PPnBr3/4PQ2/2R2PPP/2R3K1 w - - 1 25", listOf("c2c4", "d5c4", "f3e4"), "Deflection Combination"),
        RawTacticalPuzzle("r6r/1p2k3/p1p1bb1p/2q5/2P2p2/3R4/P3QPPP/3R2K1 w - - 0 24", listOf("d3d7", "e7f8", "e2e6", "c5e7", "d7e7"), "Deflection Combination"),
        RawTacticalPuzzle("6r1/Rb4r1/pPp1p3/2Pp1pk1/3P4/4P1p1/5K1P/6R1 w - - 0 34", listOf("g1g3", "g5f6", "a7b7", "g7b7", "g3g8"), "Deflection Combination"),
        RawTacticalPuzzle("r1b3Q1/5R2/p3p1p1/1p4kp/8/1KPB1n2/PP3q2/8 w - - 0 43", listOf("g8g6", "g5h4", "f7f4", "h4h3", "g6h5"), "Deflection Combination"),
        RawTacticalPuzzle("8/pk5p/1pp1Q3/8/3R2P1/P1P1K2n/2q2r2/8 w - - 0 36", listOf("d4d7", "b7a6", "e6c4", "b6b5", "c4c6", "a6a5", "d7a7"), "Deflection Combination"),
        RawTacticalPuzzle("8/p3Q2p/5qp1/4p1k1/8/8/Pr4PP/6K1 w - - 2 37", listOf("h2h4", "g5f5", "g2g4", "f5g4", "e7f6"), "Deflection Combination"),
        RawTacticalPuzzle("4B1k1/2p2p1p/1p1p1P1K/p2P2P1/2P5/1n6/8/8 w - - 0 40", listOf("e8f7", "g8f7", "h6h7", "b3d2", "g5g6", "f7f6", "g6g7"), "Deflection Combination"),
        RawTacticalPuzzle("8/3R3p/6p1/4k3/2Bnp3/1P5P/P6r/4K3 w - - 2 41", listOf("d7d5", "e5f4", "d5d4"), "Deflection Combination"),
        RawTacticalPuzzle("4k3/R3P3/1p3Kpp/2p5/2P5/4r3/4p1P1/8 w - - 3 38", listOf("a7a8", "e8d7", "a8d8", "d7c7", "e7e8q", "e3e8", "d8e8"), "Deflection Combination"),
        RawTacticalPuzzle("2k1r3/pppn1pp1/3b2b1/3B2Pp/5P2/3P3P/PPPR4/2K3NR b - - 0 18", listOf("e8e1", "d2d1", "d6f4", "c1b1", "e1d1"), "Back Rank Mate in Scandinavian Defense Scandinavian"),
        RawTacticalPuzzle("2r5/1p5k/p2q2pb/3p1bQn/3B4/P1NP1N1P/1P6/K3R3 w - - 1 34", listOf("e1e7", "h7g8", "g5h6"), "Deflection Combination"),
        RawTacticalPuzzle("5rk1/R4pp1/1p5p/3Q4/1PPp2q1/3P2P1/5P2/4K3 b - - 0 34", listOf("f8e8", "e1f1", "g4h3", "d5g2", "e8e1", "f1e1", "h3g2"), "Deflection Combination"),
        RawTacticalPuzzle("6k1/p4rp1/8/2p1Bq1p/2P1b3/2P3QP/P5PK/4R3 b - - 1 30", listOf("h5h4", "g3h4", "f5e5"), "Deflection Combination"),
        RawTacticalPuzzle("6k1/p4pp1/Qp3q1p/2p1R3/8/2P2N1P/P1P2PPK/2r5 b - - 5 24", listOf("f6f4", "g2g3", "f4f3", "e5e8", "g8h7", "a6d3", "f3d3"), "Deflection Combination"),
        RawTacticalPuzzle("8/8/8/4R2p/4p2k/4r2P/6PK/8 w - - 2 51", listOf("g2g3", "e3g3", "e5e4", "h4g5", "h2g3"), "Deflection Combination"),
        RawTacticalPuzzle("3r1bnr/2p2ppp/2bk4/R7/5P2/2N5/4N1PP/1R4K1 w - - 4 22", listOf("b1d1", "d6e7", "a5e5", "e7f6", "d1d8"), "Deflection Combination"),
        RawTacticalPuzzle("r6k/6pp/p7/Qp6/2b5/7P/Pq4P1/R3R2K b - - 1 28", listOf("c4d5", "e1g1", "d5g2", "g1g2", "b2a1"), "Deflection Combination"),
        RawTacticalPuzzle("8/p1r2p2/4r3/2P1PK2/1P4R1/3R2pk/8/8 b - - 3 46", listOf("e6e5", "f5e5", "h3g4"), "Deflection Combination"),
        RawTacticalPuzzle("r3r3/R3nkb1/5p2/3p4/3P2N1/2P1R1PP/5P2/5K2 w - - 11 35", listOf("e3e7", "e8e7", "a7a8"), "Deflection Combination"),
        RawTacticalPuzzle("3r2r1/p3kp1p/2q1p3/2p2p2/5Q2/6N1/PPP3PP/3R2K1 w - - 2 22", listOf("f4h4", "f7f6", "h4h7", "e7f8", "d1d8"), "Deflection Combination"),
        RawTacticalPuzzle("8/pp6/2p5/PP3pp1/3k3p/4p2P/7P/4K3 w - - 0 43", listOf("a5a6", "b7a6", "b5c6", "d4c5", "c6c7"), "Deflection Combination"),
        RawTacticalPuzzle("8/1p3pp1/p1k3p1/3nR3/3P4/Q5P1/P3KP1r/7q w - - 10 33", listOf("a3c5", "c6d7", "e5d5", "h1d5", "c5d5"), "Deflection Combination"),
        RawTacticalPuzzle("r3nbk1/1p6/3p4/p1pP2NQ/P1P3N1/3q3P/1P6/6K1 w - - 0 35", listOf("h5f7", "g8h8", "f7f8"), "Deflection Combination"),
        RawTacticalPuzzle("2r3k1/5p1p/4pQpP/Bb1p4/p2P4/q2BPP2/1rPK2P1/3R3R b - - 0 25", listOf("a3d3", "d2e1", "d3e3"), "Deflection Combination"),
        RawTacticalPuzzle("3rr1k1/1p3pq1/p1n1b2p/2b3p1/QP6/P2BPNB1/5PPP/R2R2K1 b - - 0 20", listOf("d8d3", "d1d3", "g7a1"), "Deflection Combination"),
        RawTacticalPuzzle("8/8/7p/p3r1p1/2PKpkP1/7P/4R3/8 w - - 3 41", listOf("e2f2", "f4g3", "d4e5", "g3f2", "e5e4"), "Deflection Combination"),
        RawTacticalPuzzle("r2q1r2/ppp2p2/3p1nk1/4p1p1/2B1P3/2NP2Q1/PPP5/2K4R w - - 1 20", listOf("g3h3", "f8h8", "h3f5", "g6g7", "f5g5"), "Deflection Combination"),
        RawTacticalPuzzle("8/8/8/2k1p2p/2PpB3/3K2p1/8/8 b - - 1 43", listOf("h5h4", "e4g2", "e5e4", "d3e2", "c5c4"), "Deflection Combination"),
        RawTacticalPuzzle("r6r/pp3k1p/3qpppP/1B1p4/5bQ1/8/PPR2PP1/4R1K1 w - - 4 23", listOf("c2c7", "d6c7", "g4e6", "f7f8", "e6f6"), "Deflection Combination"),
        RawTacticalPuzzle("4r1k1/1q3ppp/3B1n2/p2P4/8/PQ5P/5PPN/1R4K1 b - - 0 27", listOf("e8e1", "b1e1", "b7b3"), "Deflection Combination"),
        RawTacticalPuzzle("5Q2/pp1kpp2/3p1n2/2pP4/2P1P3/2q2PK1/8/5B2 w - - 2 34", listOf("f1h3", "d7c7", "f8e7"), "Deflection Combination"),
        RawTacticalPuzzle("2r2rk1/pp2R3/5p2/3p1q2/3P2P1/4QP1p/PP3R1P/6K1 b - - 0 30", listOf("f5b1", "f2f1", "b1b2", "e3e6", "g8h8"), "Deflection Combination"),
        RawTacticalPuzzle("r4rk1/ppR3pp/8/3pn1B1/8/3B1N1b/PP2NKPb/3QR3 b - - 0 19", listOf("e5g4", "f2f1", "f8f3"), "Deflection Combination"),
        RawTacticalPuzzle("7r/p5pp/4b3/N1pk4/3p4/8/Pr3PPP/2R1R1K1 w - - 0 27", listOf("a5c4", "b2a2", "e1e5", "d5c6", "e5e6"), "Deflection Combination"),
        RawTacticalPuzzle("2r3k1/p7/7p/1p4p1/6R1/3B1q1P/2P3RP/2B4K b - - 7 35", listOf("c8e8", "c1d2", "f3d1", "g2g1", "d1d2"), "Deflection Combination"),
        RawTacticalPuzzle("r1b1r1k1/pp1p1Npp/2p2q2/2bB3Q/8/3P4/PPP2PPP/R1B2RK1 b - - 0 12", listOf("f6f2", "g1h1", "f2f1"), "Deflection in Italian Game Italian"),
        RawTacticalPuzzle("8/7p/p1p4k/1p1p4/3Qpq2/2P3pP/PP1N2K1/8 b - - 1 44", listOf("c6c5", "d4c5", "f4d2"), "Deflection Combination"),
        RawTacticalPuzzle("5r2/p2R4/1P3pk1/1Q4p1/4p1q1/4P1P1/6PK/8 b - - 0 34", listOf("f8h8", "h2g1", "g4g3"), "Deflection Combination"),
        RawTacticalPuzzle("3r2k1/1p5p/pbbqp3/3PR3/P5p1/2B2r1P/1PQ2P1P/3R3K w - - 0 27", listOf("e5g5", "g8f8", "c2h7", "d6h2", "h1h2", "f3h3", "h7h3"), "Deflection Combination"),
        RawTacticalPuzzle("2k4r/B1p2p2/1P1b1pb1/7p/1PB1P1P1/2P2Q1P/1q1r1PK1/R6R b - - 0 23", listOf("d2f2", "f3f2", "g6e4", "g2g1", "b2a1"), "Deflection Combination"),
        RawTacticalPuzzle("8/p1p2k1p/Pp5b/1PPP1K2/3P4/8/8/8 w - - 0 46", listOf("d5d6", "c7d6", "c5b6"), "Deflection Combination"),
        RawTacticalPuzzle("8/2k4p/p3P3/1p3p2/1P1Kp3/P7/6PP/8 w - - 0 37", listOf("g2g4", "f5g4", "d4e4"), "Deflection Combination"),
        RawTacticalPuzzle("8/8/2p3p1/2P2pKb/3Bk3/4P3/7P/8 b - - 16 41", listOf("f5f4", "e3f4", "e4d4", "f4f5", "g6f5"), "Deflection Combination"),
        RawTacticalPuzzle("8/2N5/8/1p4p1/1P3k1p/P4b1P/5K2/8 w - - 0 54", listOf("c7e6", "f4e5", "e6g5", "f3c6", "g5f3", "e5e4", "f3h4"), "Deflection Combination"),
        RawTacticalPuzzle("2R5/p4ppk/1p2q3/6Q1/8/7P/5PPK/4r3 w - - 1 36", listOf("g5h5", "e6h6", "h5f7"), "Deflection Combination"),
        RawTacticalPuzzle("r4rk1/1ppb1pNp/7P/3P4/2PBq1p1/6b1/3Q2P1/2R2K1R w - - 0 29", listOf("g7h5", "g3e5", "d2g5", "e4g6", "g5e5"), "Deflection Combination"),
        RawTacticalPuzzle("4r2r/p1Q2pk1/1pp3p1/6q1/2BP2P1/8/PP2N1R1/6K1 b - - 9 35", listOf("g5e3", "g2f2", "h8h1", "g1h1", "e3f2", "c7f7", "f2f7"), "Deflection Combination"),
        RawTacticalPuzzle("3rr1k1/p4p2/2BBb1p1/4b2p/4P1qP/5PP1/P2Q4/3R1RK1 b - - 0 23", listOf("g4g3", "d2g2", "e5d6", "g2g3", "d6g3", "c6e8", "d8e8"), "Deflection Combination"),
        RawTacticalPuzzle("r2q1b1r/pp3kpp/3pbn2/3Q4/2n1P3/5N2/PP3PPP/RNB1K2R w KQ - 2 11", listOf("f3g5", "f7g6", "d5e6"), "Deflection in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("5k2/4bB2/4P3/2p2P2/2b5/8/p7/B6K b - - 5 48", listOf("e7g5", "f5f6", "g5e3", "a1e5", "e3f4", "e5f4", "a2a1q"), "Deflection Combination"),
        RawTacticalPuzzle("r1b1kb1r/ppNp1ppp/2n4n/4P3/8/6B1/PqP1PPPP/R2QKBNR b KQkq - 0 7", listOf("e8d8", "c7d5", "f8b4", "d5b4", "b2c3", "d1d2", "c3a1"), "Deflection in Englund Gambit Englund"),
        RawTacticalPuzzle("8/6p1/Pk5p/1B1n1P2/K7/8/7P/8 b - - 4 42", listOf("d5c3", "a4b3", "c3b5", "b3c4", "b6c6"), "Deflection Combination"),
        RawTacticalPuzzle("1k6/1p2rp1p/1p4b1/1N1B4/2P2R2/3p3P/P2Rr3/2K5 b - - 0 34", listOf("e2e1", "c1b2", "e7e2", "b2c3", "e1c1", "c3d4", "e2d2"), "Clearance Combination"),
        RawTacticalPuzzle("r4r1k/pp1q1pb1/2np1n1p/2p1p3/4P3/1bPP1Q2/PP3P1N/RNB2KR1 w - - 0 18", listOf("c1h6", "g7h6", "f3f6", "h8h7", "h2g4", "d7e6", "a2b3", "e6f6", "g4f6"), "Deflection in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("8/4kpp1/R2b4/3p4/2np2Q1/4P1P1/3q1P2/6K1 w - - 6 35", listOf("a6a7", "e7f6", "g4f3", "f6g5", "a7f7", "g5h6", "f3g4", "d2d1", "g4d1"), "Deflection Combination"),
        RawTacticalPuzzle("6k1/5pp1/4p2p/1qnpN3/8/4PB1P/Q4PPK/2r5 w - - 4 37", listOf("a2a8", "g8h7", "e5f7", "b5b2", "a8h8", "h7g6", "h8g8", "g6f6", "f3h5"), "Deflection Combination")
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
