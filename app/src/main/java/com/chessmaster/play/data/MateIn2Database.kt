package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object MateIn2Database {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("6k1/5ppp/3rp3/8/8/8/1P3PPP/2R3K1 w - - 0 35", listOf("c1c8", "d6d8", "c8d8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("4r1k1/1p2r3/p1p5/3p1p2/PP1P1P1p/2Q3pP/5PP1/4R1K1 b - - 0 39", listOf("e7e1", "c3e1", "e8e1"), "Mate In2 Combination"),
        RawTacticalPuzzle("rn1q3k/pp4pp/1b2B3/4N3/5Q2/2p5/PP4PP/RN5K b - - 0 16", listOf("d8d1", "f4f1", "d1f1"), "Mate In2 in Kings Gambit Accepted"),
        RawTacticalPuzzle("1R6/6kp/3p1pp1/2r1p3/PP6/8/2r2PPP/1R4K1 b - - 0 30", listOf("c2c1", "b1c1", "c5c1"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("2r5/1p2k1pp/4p3/1N3p2/5P2/P2nP3/3R2PP/6K1 b - - 1 24", listOf("c8c1", "d2d1", "c1d1"), "Mate In2 Combination"),
        RawTacticalPuzzle("6k1/5ppp/5n2/pp6/4b1rP/5N1Q/Pq2r1P1/3R2RK w - - 5 33", listOf("d1d8", "f6e8", "d8e8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("q4r1k/1p3Qpp/1n6/3P2pP/2PP2P1/1P6/2K5/5R2 w - - 0 30", listOf("f7f8", "a8f8", "f1f8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("3r2k1/4nppp/pq3b2/1p2p3/2r2P2/2P1NR2/PP1Q2BP/3R2K1 w - - 0 25", listOf("d2d8", "b6d8", "d1d8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("6k1/p4pp1/1p5p/4b3/4B3/4P1P1/P1R2PKP/1q1r4 w - - 0 31", listOf("c2c8", "d1d8", "c8d8"), "Mate In2 Combination"),
        RawTacticalPuzzle("7k/pb3rpp/2p5/5p2/3P4/8/PPP2PPP/4R1K1 w - - 0 22", listOf("e1e8", "f7f8", "e8f8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("2n3k1/p4ppp/2p1p3/P1NrP3/1N1r4/8/5PPP/1R1R2K1 b - - 0 29", listOf("d4d1", "b1d1", "d5d1"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("6k1/ppR2pp1/4p1p1/4P1N1/3r2P1/1P4K1/P3r3/8 w - - 6 31", listOf("c7c8", "d4d8", "c8d8"), "Mate In2 Combination"),
        RawTacticalPuzzle("r5k1/pbp2ppp/6q1/3p4/3p3r/2P1R1P1/PP2QP1P/R5K1 w - - 0 21", listOf("e3e8", "a8e8", "e2e8"), "Mate In2 Combination"),
        RawTacticalPuzzle("7k/6p1/1r5p/P4p2/3Rp3/4P3/5PPP/6K1 b - - 0 39", listOf("b6b1", "d4d1", "b1d1"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("2k2r2/pp5p/3p4/3Nb1p1/8/1P1P3P/P1PR4/1K3R2 b - - 0 28", listOf("f8f1", "d2d1", "f1d1"), "Hanging Piece Combination"),
        RawTacticalPuzzle("5k2/p2r3p/1p4pP/3r1q2/4R3/2P5/PP3PQ1/K3R3 b - - 0 33", listOf("d5d1", "e1d1", "d7d1"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("2kr3r/p1p1Rpp1/2p2n1p/8/8/1P6/P1P2PPP/RNB3K1 b - - 0 16", listOf("d8d1", "e7e1", "d1e1"), "Back Rank Mate in Kings Pawn Game"),
        RawTacticalPuzzle("r1n3k1/3R1ppp/2p5/5P2/8/1P2r3/P7/5RK1 w - - 0 34", listOf("d7d8", "e3e8", "d8e8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("6k1/B7/2p2pK1/3nr2p/8/1Q3PP1/P6r/8 w - - 1 32", listOf("b3b8", "e5e8", "b8e8"), "Mate In2 Combination"),
        RawTacticalPuzzle("1k6/ppp3p1/8/1P5p/8/P3n2P/2P1r1P1/B2rNRK1 w - - 5 32", listOf("f1f8", "d1d8", "f8d8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("rnbqk1nr/ppppbppp/4p3/8/5PP1/5N2/PPPPP2P/RNBQKB1R b KQkq - 0 3", listOf("e7h4", "f3h4", "d8h4"), "Mate In2 in Bird Opening Bird"),
        RawTacticalPuzzle("r6k/2q3pp/8/2p5/R1np4/7P/2PB1PP1/6K1 w - - 0 33", listOf("a4a8", "c7b8", "a8b8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("r5k1/pp3ppp/8/3p4/2qP4/4R2P/2P1QPPK/8 w - - 2 22", listOf("e3e8", "a8e8", "e2e8"), "Mate In2 Combination"),
        RawTacticalPuzzle("5rk1/p2q2p1/1p2p1Np/3p3P/3Pb1P1/2P5/PP3R2/6K1 w - - 0 34", listOf("f2f8", "g8h7", "f8h8"), "Hook Mate Combination"),
        RawTacticalPuzzle("2k2br1/1pprn3/p4p2/4p2Q/4P2P/2N1q3/PP4PK/3R4 w - - 0 23", listOf("h5e8", "d7d8", "d1d8"), "Mate In2 Combination"),
        RawTacticalPuzzle("5bk1/2R4p/6p1/8/4NP1P/3bP1K1/r7/8 w - - 3 46", listOf("e4f6", "g8h8", "c7h7"), "Arabian Mate Combination"),
        RawTacticalPuzzle("6k1/pp3pp1/2p1q1Pp/3b4/8/6Q1/PB3Pp1/3r1NK1 w - - 0 28", listOf("g3b8", "e6e8", "b8e8"), "Mate In2 Combination"),
        RawTacticalPuzzle("rn1qr1k1/1p3ppp/2p2b2/p2p4/3P4/2N2N2/PPP1QPPP/2KRR3 w - - 0 13", listOf("e2e8", "d8e8", "e1e8"), "Back Rank Mate in French Defense French"),
        RawTacticalPuzzle("3r2k1/pp3ppp/2p3b1/2n3P1/2B2q1P/5N2/PPP1QP2/4R1K1 w - - 0 24", listOf("e2e8", "d8e8", "e1e8"), "Mate In2 Combination"),
        RawTacticalPuzzle("8/5p2/1R6/6pk/8/3r2PP/5K2/8 w - - 4 41", listOf("g3g4", "h5h4", "b6h6"), "Mate In2 Combination"),
        RawTacticalPuzzle("R7/1p3kp1/2pK3p/3p1PP1/3r2nP/8/1P6/8 w - - 0 40", listOf("g5g6", "f7f6", "a8f8"), "Mate In2 Combination"),
        RawTacticalPuzzle("r1bqr1k1/pp1nbpp1/2p5/3n2P1/2BP4/P7/1PQNNPP1/R3K2R w KQ - 1 14", listOf("c2h7", "g8f8", "h7h8"), "Mate In2 in French Defense French"),
        RawTacticalPuzzle("r3r1k1/6b1/p2Nn2p/1P1Qp3/6nq/2P3P1/1PB2P2/R1B1R1K1 b - - 0 30", listOf("h4h2", "g1f1", "h2f2"), "Mate In2 Combination"),
        RawTacticalPuzzle("1R6/6pk/2p4p/3bP2r/5B1P/2P1RqP1/P4P1Q/6K1 b - - 3 40", listOf("f3d1", "e3e1", "d1e1"), "Mate In2 Combination"),
        RawTacticalPuzzle("rn1qrk2/ppp3pQ/3p1pP1/3Pp3/2P1P3/8/PP3PP1/R1B1K3 w Q - 3 17", listOf("h7h8", "f8e7", "h8g7"), "Deflection in Three Knights Opening"),
        RawTacticalPuzzle("8/1p2rppk/5q1p/Q4R2/2P5/PP5P/5PP1/5K2 b - - 0 32", listOf("f6a1", "a5e1", "e7e1"), "Mate In2 Combination"),
        RawTacticalPuzzle("8/8/p2k4/1pp5/7P/1PKP1RP1/7r/8 b - - 1 46", listOf("b5b4", "c3c4", "h2c2"), "Mate In2 Combination"),
        RawTacticalPuzzle("3r4/2k2p1p/pp2pN2/2p5/3n4/6P1/PPP2PRP/2K5 b - - 0 23", listOf("d4e2", "c1b1", "d8d1"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("Q7/5qk1/p2p4/b1p1pr2/P7/6P1/4KP1R/8 w - - 4 39", listOf("a8h8", "g7g6", "h8h6"), "Mate In2 Combination"),
        RawTacticalPuzzle("6k1/2q2p1p/4pPp1/4P3/p1pP1P2/r1P5/6QP/4B1K1 w - - 0 34", listOf("g2a8", "c7b8", "a8b8"), "Mate In2 Combination"),
        RawTacticalPuzzle("r6r/1bpnk3/1p1pB3/pP1P4/P3PQP1/2b2N1q/2P2P2/R3R1K1 w - - 0 25", listOf("f4f7", "e7d8", "f7d7"), "Mate In2 Combination"),
        RawTacticalPuzzle("3r1r1k/p6p/1p4pP/2p5/2PbBQ2/2q5/P1P1K3/5R2 w - - 1 35", listOf("f4f8", "d8f8", "f1f8"), "Mate In2 Combination"),
        RawTacticalPuzzle("6k1/2R3pp/2p4q/1p1p4/3P4/P7/1PP2R2/1K1Nr3 w - - 4 33", listOf("c7c8", "e1e8", "c8e8"), "Mate In2 Combination"),
        RawTacticalPuzzle("6k1/4qpp1/3p3p/8/2BP4/1PQ5/3n1PPP/6K1 b - - 0 29", listOf("e7e1", "c4f1", "e1f1"), "Mate In2 Combination"),
        RawTacticalPuzzle("8/3k1p2/4p3/p2p4/3P1P2/q3P1rP/7r/1QR2K2 w - - 2 35", listOf("b1b7", "d7e8", "c1c8"), "Mate In2 Combination"),
        RawTacticalPuzzle("4r1k1/p4p1p/1p6/6B1/3P2n1/P4Q2/1P4P1/7K b - - 0 34", listOf("e8e1", "f3f1", "e1f1"), "Mate In2 Combination"),
        RawTacticalPuzzle("2r3k1/3R1ppp/p1q5/2p2Q2/P7/7P/5PP1/6K1 w - - 4 27", listOf("f5f7", "g8h8", "f7g7"), "Mate In2 Combination"),
        RawTacticalPuzzle("6k1/3P1pp1/4p3/p7/3QR3/3q1PP1/1P3PK1/2r5 b - - 1 37", listOf("d3f1", "g2h2", "f1h1"), "Mate In2 Combination"),
        RawTacticalPuzzle("5r1k/pp4pp/5p2/1BbQp1r1/7K/7P/1PP3P1/3R3R b - - 3 26", listOf("c5f2", "g2g3", "f2g3"), "Mate In2 Combination"),
        RawTacticalPuzzle("4r2k/3q3r/1p4pQ/p1pP4/2P4P/1N4p1/PP3RK1/8 w - - 2 38", listOf("f2f8", "e8f8", "h6f8"), "Mate In2 Combination"),
        RawTacticalPuzzle("kr6/1pR4p/p4R2/n7/P3p3/3rB3/6PP/6K1 w - - 1 39", listOf("f6a6", "b7a6", "c7a7"), "Mate In2 Combination"),
        RawTacticalPuzzle("1k5r/ppp1R2p/r4p2/5Q2/3p4/2qP4/2P2PPP/2K1R3 w - - 6 27", listOf("e7e8", "h8e8", "e1e8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("1rb3k1/q4rP1/4p2p/3p3p/3P1P2/2P5/2QK3P/3R2R1 w - - 1 30", listOf("c2h7", "g8h7", "g7g8q"), "Attraction Combination"),
        RawTacticalPuzzle("5rk1/1bR3q1/pQ6/8/6P1/4R3/P7/6K1 b - - 0 28", listOf("g7g4", "e3g3", "g4g3"), "Mate In2 Combination"),
        RawTacticalPuzzle("3q1r1k/p3r1pp/1p1b1p2/2p5/3pR2N/1QPn2P1/PP1B1P1P/R5K1 w - - 0 22", listOf("h4g6", "h7g6", "e4h4"), "Clearance Combination"),
        RawTacticalPuzzle("3br1kr/7p/4p1pQ/P5P1/1B5P/P6q/5R2/6K1 w - - 2 36", listOf("f2f8", "e8f8", "h6f8"), "Mate In2 Combination"),
        RawTacticalPuzzle("3rk2r/2qn2p1/p1Q1p3/3n3p/8/8/PP4PP/5R1K w k - 0 24", listOf("c6e6", "d5e7", "e6f7"), "Fork Combination"),
        RawTacticalPuzzle("6k1/5ppp/r1p5/p1n1rP2/8/2P2N1P/2P3P1/3R2K1 w - - 0 22", listOf("d1d8", "e5e8", "d8e8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("2RQ4/p4pp1/4p1kp/8/6PP/4qPK1/1r6/8 w - - 0 36", listOf("h4h5", "g6h7", "d8g8"), "Mate In2 Combination"),
        RawTacticalPuzzle("1q3r2/p5k1/1p2pbpp/2p5/2P1p3/2P2PQP/PP3P2/6RK w - - 0 30", listOf("g3g6", "g7h8", "g6h6"), "Deflection Combination"),
        RawTacticalPuzzle("8/1p4p1/pb2pp1p/3n1k2/3P4/P3BN1P/1P2KPP1/8 w - - 1 27", listOf("f3h4", "f5e4", "f2f3"), "Mate In2 Combination"),
        RawTacticalPuzzle("1r4k1/p4ppp/2Q5/3pq3/8/P6P/2PR1PP1/1R4K1 b - - 0 26", listOf("b8b1", "d2d1", "b1d1"), "Mate In2 Combination"),
        RawTacticalPuzzle("1r6/5k2/2Q1pNp1/p5Pp/1p2P2P/2P4R/KP3P2/3q4 b - - 0 31", listOf("b4b3", "a2a3", "d1a1"), "Mate In2 Combination"),
        RawTacticalPuzzle("1rr3k1/5p1p/p5pQ/4p3/4q3/B1P1P3/PP1R1PPP/2KR4 b - - 0 27", listOf("c8c3", "b2c3", "b8b1"), "Mate In2 Combination"),
        RawTacticalPuzzle("3r4/4kp1r/p2Np1p1/3bP3/P2n4/8/1P3RPP/5RK1 w - - 5 26", listOf("f2f7", "h7f7", "f1f7"), "Hook Mate Combination"),
        RawTacticalPuzzle("5Q2/pbp3np/1p1pq1pk/1P6/P6P/6K1/8/8 w - - 0 33", listOf("f8f4", "g6g5", "f4g5"), "Mate In2 Combination"),
        RawTacticalPuzzle("r1bq1k1r/ppppn1pp/2n5/b5N1/4P3/B1P5/P4PPP/RN1QK2R w KQ - 2 10", listOf("d1f3", "f8e8", "f3f7"), "Mate In2 in Scotch Game Scotch"),
        RawTacticalPuzzle("2Q5/p5pk/1r2p3/K2pP2p/3P4/2P4R/Pq6/8 w - - 0 42", listOf("h3h5", "h7g6", "c8e8"), "Mate In2 Combination"),
        RawTacticalPuzzle("1R2R3/p7/1p1k3p/1Pb5/P5p1/6P1/5r1P/7K b - - 7 41", listOf("f2f1", "h1g2", "f1g1"), "Mate In2 Combination"),
        RawTacticalPuzzle("3r1k2/p2n3p/1p2Bpp1/2r2N2/4q3/6QP/P5P1/5R1K w - - 2 41", listOf("g3d6", "f8e8", "d6e7"), "Mate In2 Combination"),
        RawTacticalPuzzle("4rk2/pbp2pp1/1p1N4/3P1q2/QPBP4/1KP2P2/P5r1/R3R3 b - - 0 25", listOf("f5c2", "b3a3", "c2b2"), "Mate In2 Combination"),
        RawTacticalPuzzle("4rk2/p4q2/1p3Q1b/8/1p5N/2P1p3/P3P3/2K5 w - - 1 44", listOf("h4g6", "f8g8", "f6h8"), "Mate In2 Combination"),
        RawTacticalPuzzle("7k/p5pp/2r2q2/2p4Q/8/8/P5PP/3r1R1K w - - 0 30", listOf("h5e8", "f6f8", "e8f8"), "Mate In2 Combination"),
        RawTacticalPuzzle("4r3/7P/1p4Q1/1b4B1/1k4n1/8/1P2n1P1/4R2K b - - 3 41", listOf("e2g3", "h1g1", "e8e1"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r2n2k1/1bq2rpp/1p6/3P4/p3Q3/B3P3/PP3PPP/3R2K1 w - - 0 21", listOf("e4e8", "f7f8", "e8f8"), "Mate In2 Combination"),
        RawTacticalPuzzle("5rk1/5ppp/1r6/3Qp3/2B1P3/2q3P1/3R1PKP/8 w - - 0 28", listOf("d5f7", "f8f7", "d2d8"), "Clearance Combination"),
        RawTacticalPuzzle("4r3/1k6/pp3P2/1b5p/3R1p2/P1R2P2/1P4PP/6K1 b - - 0 35", listOf("e8e1", "g1f2", "e1f1"), "Mate In2 Combination"),
        RawTacticalPuzzle("8/p1p2k2/1r2rp2/3p1Q2/2qP2R1/2P4P/6PK/4q3 w - - 0 34", listOf("f5h7", "f7f8", "g4g8"), "Mate In2 Combination"),
        RawTacticalPuzzle("3r2k1/1q3ppp/p3p3/Qp1r4/7P/P4P2/1PP3P1/1K1R3R w - - 0 22", listOf("a5d8", "d5d8", "d1d8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("6k1/1b3pbp/6p1/1N4q1/1Q1p4/P7/5PPP/2r1RBK1 w - - 1 28", listOf("e1e8", "g7f8", "b4f8"), "Mate In2 Combination"),
        RawTacticalPuzzle("r5k1/pp3ppp/2p5/4pb2/2n2q2/P1P2P2/1P1Q3P/3R1R1K w - - 0 23", listOf("d2d8", "a8d8", "d1d8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("8/6pk/1Q1p2n1/4p3/2P3P1/P2PPK1P/1B6/4q3 b - - 2 35", listOf("g6h4", "f3e4", "e1h1"), "Mate In2 Combination"),
        RawTacticalPuzzle("3r3k/6p1/4Q3/4B3/1p3P2/4PKP1/3q4/8 w - - 18 52", listOf("e6h6", "h8g8", "h6g7"), "Mate In2 Combination"),
        RawTacticalPuzzle("r1bq3r/pp1nbkp1/2p1p2p/8/2BP4/1PN3P1/P3QP1P/3R1RK1 w - - 0 20", listOf("e2e6", "f7f8", "e6f7"), "Mate In2 in Horwitz Defense Horwitz"),
        RawTacticalPuzzle("1r5r/5pk1/4p3/3p2PP/N1nP4/n1P5/P3B3/K1R4R b - - 0 34", listOf("b8b1", "c1b1", "a3c2"), "Mate In2 Combination"),
        RawTacticalPuzzle("6k1/3R3p/1p5q/3P4/3QP1pN/6P1/PPr3B1/5K2 b - - 0 25", listOf("h6c1", "d4d1", "c1d1"), "Mate In2 Combination"),
        RawTacticalPuzzle("2r5/pR5p/5p1k/4p3/4R3/B4nPP/PP3P2/1K6 b - - 0 27", listOf("f3d2", "b1a1", "c8c1"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("6k1/2P3pp/1P6/4b3/3p4/Br5P/4prP1/R5RK b - - 0 30", listOf("b3h3", "g2h3", "f2h2"), "Mate In2 Combination"),
        RawTacticalPuzzle("4r1k1/pp1qn3/2p4R/6p1/3P2r1/3Q2P1/PP3P1P/6K1 w - - 0 32", listOf("d3h7", "g8f8", "h6f6"), "Mate In2 Combination"),
        RawTacticalPuzzle("r4r2/2q1Nb2/5Qpk/2n4p/pp5P/8/1PP2PP1/2KR3R w - - 0 29", listOf("e7f5", "h6h7", "f6g7"), "Mate In2 Combination"),
        RawTacticalPuzzle("3Q4/p1p2ppp/4k3/8/5P2/4P3/Prqn2PP/3R1RK1 b - - 0 22", listOf("d2f3", "g1h1", "c2g2"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r1bq3Q/1np3p1/p5k1/1p1Pp3/1Pn2BP1/2b2P2/P3K3/R4N2 w - - 0 36", listOf("h8h5", "g6f6", "f4g5"), "Mate In2 Combination"),
        RawTacticalPuzzle("3k2q1/p2p3p/1p1P4/2p5/2P2Q1K/8/P5b1/5R2 w - - 3 37", listOf("f4f8", "g8f8", "f1f8"), "Mate In2 Combination"),
        RawTacticalPuzzle("8/n7/P7/K2k4/P7/7P/5PP1/8 b - - 1 53", listOf("d5c5", "f2f3", "a7c6"), "Mate In2 Combination"),
        RawTacticalPuzzle("8/1P6/8/7p/3P4/3k1p1P/4p3/4K3 b - - 1 49", listOf("d3e3", "h3h4", "f3f2"), "Mate In2 Combination"),
        RawTacticalPuzzle("8/2p1r1kp/5pp1/3P3r/4P3/2N1QbPq/PPP2R1P/5RK1 b - - 4 23", listOf("h3g3", "h2g3", "h5h1"), "Clearance Combination"),
        RawTacticalPuzzle("8/2pR3p/pb4pk/8/5p1P/B6K/P1r5/6r1 w - - 4 40", listOf("a3f8", "h6h5", "d7h7"), "Deflection Combination"),
        RawTacticalPuzzle("4qr2/pR4pk/2b2p1p/4pPP1/3bB3/3P3Q/P1r4P/4BR1K w - - 1 25", listOf("h3h6", "h7g8", "h6g7"), "Mate In2 Combination"),
        RawTacticalPuzzle("r1b1k1nr/pp2p2p/2q1Npp1/2n1p3/2B5/2N3B1/PPPR1PPP/2K4R w kq - 0 15", listOf("d2d8", "e8f7", "d8f8"), "Mate In2 in Indian Defense Indian"),
        RawTacticalPuzzle("3r1rk1/4Qppp/8/1ppb4/2Pn1B1n/2N3P1/PP3P2/R2R1K2 b - - 0 21", listOf("d5g2", "f1e1", "h4f3"), "Mate In2 Combination")
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
