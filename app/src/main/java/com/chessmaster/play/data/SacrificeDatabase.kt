package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object SacrificeDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("r5k1/p4p1p/1p3ppB/8/2pP4/4QP1q/P1n2P2/4R1K1 w - - 0 22", listOf("e3e8", "a8e8", "e1e8"), "Mate In2 Combination"),
        RawTacticalPuzzle("r6k/2p2Qpp/p7/4b3/8/8/1PP1KR2/2q5 w - - 1 25", listOf("f7f8", "a8f8", "f2f8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("4r1k1/5ppp/B1R2b2/5P2/2Q2p1P/pP2qP2/P1P5/1K5R b - - 0 28", listOf("e3e1", "h1e1", "e8e1"), "Mate In2 Combination"),
        RawTacticalPuzzle("1k1r4/1p3Qp1/p2q3p/8/1N6/8/PP3PPP/2R3K1 b - - 0 22", listOf("d6d1", "c1d1", "d8d1"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("r5k1/pp3ppp/1qn2b2/3r4/4QB2/1P3N2/P4PPP/4R1K1 w - - 0 19", listOf("e4e8", "a8e8", "e1e8"), "Back Rank Mate in Queens Pawn Game"),
        RawTacticalPuzzle("2k3r1/pppb1prp/1q6/8/Q7/2P1R1P1/P4P1P/4R1K1 w - - 4 24", listOf("e3e8", "d7e8", "e1e8", "g8e8", "a4e8"), "Mate In3 Combination"),
        RawTacticalPuzzle("5r2/R2R1pk1/P7/5p2/6p1/1KP5/5r2/8 w - - 0 45", listOf("d7f7", "f8f7", "a7f7", "g7f7", "a6a7"), "Sacrifice Combination"),
        RawTacticalPuzzle("1k3r2/q5r1/2Qp3p/3Bp3/2N1P1p1/P7/1PP2PPP/R5K1 b - - 13 35", listOf("a7f2", "g1h1", "f2f1", "a1f1", "f8f1"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("r7/pp4kp/6p1/2P2q1n/1PBb1p2/P4P2/3B2PP/2Q1R2K b - - 2 28", listOf("h5g3", "h2g3", "f5h5"), "Clearance Combination"),
        RawTacticalPuzzle("3r2k1/4ppnp/pp4p1/2p5/4q3/3rP3/P2P1QPP/3R1RK1 w - - 0 29", listOf("f2f7", "g8h8", "f7f8", "d8f8", "f1f8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("5r2/p1k2qpp/2n5/2N1p3/2b5/1PP1B3/P2Q2PP/4R1K1 b - - 0 24", listOf("f7f1", "e1f1", "f8f1"), "Mate In2 Combination"),
        RawTacticalPuzzle("5k2/5r1p/pp2Q3/8/8/1P6/P1q2PPP/4R1K1 b - - 0 32", listOf("c2f2", "g1h1", "f2f1", "e1f1", "f7f1"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("2r3k1/1Q3ppp/2pB4/2Pp1b1n/3p3K/P4P2/6qP/R3R3 w - - 0 25", listOf("b7c8", "f5c8", "e1e8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("1n5k/6p1/p2q1rPp/1ppB4/8/3P4/PPP1rPQ1/2K4R w - - 0 26", listOf("h1h6", "g7h6", "g6g7", "h8h7", "g7g8q"), "Mate In3 Combination"),
        RawTacticalPuzzle("2kr3r/2p2p2/p4q2/1p6/P2PQ3/1PP3pP/6P1/3RR1K1 b - - 0 28", listOf("f6f2", "g1h1", "h8h3", "g2h3", "f2h2"), "Mate In3 Combination"),
        RawTacticalPuzzle("r2q1rk1/pp2bpp1/2p1b2p/4P3/3PNn2/1P1Q3P/P4BP1/1BR2RK1 w - - 5 26", listOf("e4f6", "g7f6", "d3h7"), "Clearance Combination"),
        RawTacticalPuzzle("r4rk1/6pp/1p1Bp3/p7/P1PP4/4nqP1/Q2R3P/4R1K1 b - - 2 25", listOf("f3f1", "e1f1", "f8f1"), "Mate In2 Combination"),
        RawTacticalPuzzle("4rr1k/p1Qn2pp/3p1q2/8/8/2P5/PP3PPP/RN3RK1 b - - 0 16", listOf("f6f2", "f1f2", "e8e1", "f2f1", "e1f1"), "Mate In3 in Ruy Lopez Ruy"),
        RawTacticalPuzzle("1r3rk1/q5pp/2R5/3P4/2Q5/1p2NpPb/1P3P1P/3R2K1 b - - 1 32", listOf("a7e3", "f2e3", "f3f2"), "Sacrifice Combination"),
        RawTacticalPuzzle("2r5/3k1p2/5Pp1/PPBpP1P1/1K5P/5p2/8/8 b - - 2 45", listOf("c8c5", "b4c5", "f3f2"), "Sacrifice Combination"),
        RawTacticalPuzzle("3r2k1/6pp/8/5Q2/2pP4/2q3P1/5RKP/8 w - - 0 36", listOf("f5f7", "g8h8", "f7f8", "d8f8", "f2f8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("1k1rR3/p1p3pp/Pp3r2/3p4/Q2N4/8/1PPq1PPP/4R1K1 b - - 4 25", listOf("d2f2", "g1h1", "f2f1", "e1f1", "f6f1"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("r4k1r/p3nppp/3Qp3/8/B7/P1q5/2b2PPP/3R1RK1 w - - 0 19", listOf("d6d8", "a8d8", "d1d8"), "Back Rank Mate in Scandinavian Defense Scandinavian"),
        RawTacticalPuzzle("r5k1/1p3p2/2p1ppp1/3p4/2nP4/1QB4P/rPP1BPP1/qNKR3R b - - 4 22", listOf("a1b1", "c1b1", "a2a1"), "Attraction Combination"),
        RawTacticalPuzzle("r6r/pp2kb2/3p1p2/1N1Pp3/3bP3/P2B2P1/1P1Q2PP/7K b - - 7 28", listOf("h8h2", "h1h2", "a8h8", "d2h6", "h8h6"), "Attraction Combination"),
        RawTacticalPuzzle("r2q1rk1/1pp2ppp/8/p2pn3/P3N3/3Bb2Q/1P3PPP/R4RK1 w - - 0 18", listOf("e4f6", "g7f6", "h3h7"), "Mate In2 in Italian Game Italian"),
        RawTacticalPuzzle("2r5/2r3k1/1p2Qp1p/p2p2p1/3P4/4P1P1/PPq2P1R/KR6 b - - 0 30", listOf("c2b1", "a1b1", "c7c1"), "Attraction Combination"),
        RawTacticalPuzzle("r3qr1k/1p2b1p1/4p1Qp/2ppP3/p4P2/2P1P1P1/PP4P1/1K1R3R w - - 1 21", listOf("h1h6", "g7h6", "g6h6", "h8g8", "d1h1"), "Sacrifice Combination"),
        RawTacticalPuzzle("5rk1/R5p1/5q1p/8/3p2Q1/1P6/P3rPPP/5RK1 b - - 3 38", listOf("f6f2", "f1f2", "e2e1", "f2f1", "e1f1"), "Mate In3 Combination"),
        RawTacticalPuzzle("r1b2rk1/p4ppp/2p5/6q1/6P1/3p1Q1P/PPP5/1K2RR2 w - - 0 18", listOf("f3f7", "f8f7", "e1e8", "f7f8", "f1f8"), "Mate In3 in Kings Knight Opening"),
        RawTacticalPuzzle("1q5r/p3kpp1/2Q1p2p/3pP2P/2n3P1/2N2P2/PrP1N3/K3R2R b - - 1 26", listOf("b2a2", "c3a2", "b8b2"), "Clearance Combination"),
        RawTacticalPuzzle("5rk1/pR1Q1ppp/4N3/3p1n2/8/7P/q4rP1/4R1K1 w - - 0 29", listOf("d7f7", "f8f7", "b7b8", "f7f8", "b8f8"), "Mate In3 Combination"),
        RawTacticalPuzzle("2k4r/ppp1q1p1/3b2p1/3Q4/1P2NPn1/P1P5/6P1/R1B2RKN b - - 0 21", listOf("h8h1", "g1h1", "e7h4", "h1g1", "h4h2"), "Attraction Combination"),
        RawTacticalPuzzle("r3k1nr/pp3ppp/1q2b3/8/1n1B4/6P1/P2QPPBP/R3K1NR b KQkq - 0 13", listOf("b6d4", "d2d4", "b4c2", "e1f1", "c2d4"), "Attraction in Queens Gambit Declined"),
        RawTacticalPuzzle("5k2/3b2q1/pn4p1/1rp2p2/8/8/1P2Q1P1/1K2R2R w - - 4 33", listOf("h1h8", "g7h8", "e2e7", "f8g8", "e7d8"), "Sacrifice Combination"),
        RawTacticalPuzzle("3r2k1/pR3pp1/8/5p1p/5q2/5n2/PP4R1/6QK w - - 0 30", listOf("g2g7", "g8h8", "g7h7", "h8h7", "b7f7", "h7h8", "g1g7"), "Attraction Combination"),
        RawTacticalPuzzle("5k2/2p5/N1PpQp2/1p2p1b1/1P2P1p1/r5Pq/5P2/R2R2K1 b - - 0 37", listOf("a3g3", "f2g3", "g5e3"), "Mate In2 Combination"),
        RawTacticalPuzzle("8/6QR/pr5p/6p1/5p1k/q6P/2P2PPK/8 b - - 7 39", listOf("a3g3", "f2g3", "f4g3", "h2g1", "b6b1"), "Mate In3 Combination"),
        RawTacticalPuzzle("r1b1k2r/p4p1p/2p1p3/1pRnPp2/q2P4/2Q2N1P/5PP1/1B3RK1 w kq - 1 20", listOf("c5d5", "c6d5", "c3c6"), "Clearance in Queens Gambit Declined"),
        RawTacticalPuzzle("3r4/ppN1kppp/5n2/8/1n3Nb1/4P3/1B3PPP/2R1KB1R b K - 1 18", listOf("b4c2", "c1c2", "d8d1"), "Mate In2 in Tarrasch Defense Tarrasch"),
        RawTacticalPuzzle("8/8/3RP3/2Pn4/ppN1k2p/8/rP1K4/8 w - - 0 41", listOf("d6d5", "e4d5", "e6e7"), "Sacrifice Combination"),
        RawTacticalPuzzle("r2k1bnr/p1pp1Bpp/1p6/4N1q1/3p4/2P5/PP2QPbP/RN2K2R w KQ - 0 11", listOf("e5c6", "g2c6", "e2e8"), "Clearance in Kings Pawn Game"),
        RawTacticalPuzzle("7k/p5pp/8/3Q4/8/5r1b/PP3q1P/3N2RK b - - 1 28", listOf("f2g1", "h1g1", "f3f1"), "Attraction Combination"),
        RawTacticalPuzzle("4rrk1/1ppq2p1/p3Rp1p/3P4/2P2Q2/3B3P/PP4PK/8 w - - 2 26", listOf("f4f5", "e8e6", "f5h7"), "Sacrifice Combination"),
        RawTacticalPuzzle("3r4/6p1/5p1p/r1kp4/Pp3q2/2pR3P/2Q2PP1/3R2K1 w - - 5 40", listOf("d3c3", "b4c3", "c2c3", "c5d6", "c3a5"), "Fork Combination"),
        RawTacticalPuzzle("2r5/8/8/pNp1r2p/4NppP/1P1k4/2R2K2/8 w - - 0 43", listOf("c2d2", "d3e4", "b5d6"), "Mate In2 Combination"),
        RawTacticalPuzzle("8/4rkb1/R5R1/5P1p/P6P/6P1/1r2p3/4K3 w - - 0 45", listOf("g6g7", "f7g7", "f5f6", "g7f7", "f6e7"), "Attraction Combination"),
        RawTacticalPuzzle("5r2/pp3pk1/2p1pRp1/6Qp/2P5/P7/1q1r2PP/5R1K w - - 2 32", listOf("f6g6", "f7g6", "g5e7", "g7h6", "e7f8", "b2g7", "f8f4", "h6h7", "f4d2"), "Clearance Combination"),
        RawTacticalPuzzle("rn3rk1/4pp1p/3p2pB/2q4P/3QP1b1/Pp6/1P2B3/1K1R2NR b - - 0 20", listOf("c5c2", "b1a1", "a8a3", "b2a3", "c2a2"), "Mate In3 Combination"),
        RawTacticalPuzzle("r3brk1/1pqnb2p/p3ppp1/2ppN2Q/5P2/1P1PP1R1/PBPN2PP/R5K1 w - - 0 16", listOf("e5g6", "e8g6", "g3g6", "h7g6", "h5g6"), "Sacrifice in Bird Opening Bird"),
        RawTacticalPuzzle("r4rk1/1q1p2pp/p3p3/2p1Q3/8/1B6/PPP2PbP/3RR1K1 w - - 0 22", listOf("d1d7", "b7d7", "b3e6", "g8h8", "e6d7"), "Attraction Combination"),
        RawTacticalPuzzle("8/p7/5P2/PP1bp3/4N3/K7/2kp4/8 w - - 0 49", listOf("e4d2", "c2d2", "b5b6", "a7b6", "a5b6"), "Sacrifice Combination"),
        RawTacticalPuzzle("2kr3r/1pp2p2/p2p3p/3P1bpB/2P2q2/1P6/P5PP/R2QR2K w - - 0 19", listOf("e1f1", "f4e5", "f1f5", "e5f5", "h5g4", "f5g4", "d1g4"), "Pin in English Opening English"),
        RawTacticalPuzzle("8/8/1n4kP/1P2Kp2/3P4/2p1N3/8/8 b - - 5 49", listOf("b6c4", "e3c4", "c3c2"), "Sacrifice Combination"),
        RawTacticalPuzzle("2r3k1/2r4p/4p1p1/1p1q1pP1/p2P1P1Q/P6R/4bB2/2R3K1 w - - 6 35", listOf("h4h7", "c7h7", "c1c8", "g8g7", "c8c7"), "Deflection Combination"),
        RawTacticalPuzzle("2k5/pp3p2/5q2/2bNp1p1/4P1Pr/1n3BK1/PPP3P1/R2Q4 b - - 4 23", listOf("f6f4", "d5f4", "e5f4"), "Mate In2 Combination"),
        RawTacticalPuzzle("7k/p3b2p/Pp1n4/1PpPp3/2B1PpP1/1P6/5BK1/8 w - - 1 34", listOf("f2c5", "b6c5", "b5b6", "a7b6", "a6a7"), "Sacrifice Combination"),
        RawTacticalPuzzle("2R5/3Nb1pk/1r5p/2pP4/1p2P3/3P4/4K1PP/8 b - - 2 39", listOf("b4b3", "d5d6", "e7d6", "d7b6", "b3b2", "b6c4", "b2b1q"), "Sacrifice Combination"),
        RawTacticalPuzzle("k1r1b3/p1r1nppp/Bp1qpn2/2Np4/1P1P4/PQR1PN2/5PPP/2R3K1 b - - 1 19", listOf("b6c5", "a6c8", "c5c4"), "Sacrifice in Slav Defense Slav"),
        RawTacticalPuzzle("r2q3k/5P2/2n3Bp/3p2pP/pp1b2Q1/6B1/1PP5/6K1 w - - 0 40", listOf("g4d4", "c6d4", "g3e5", "d8f6", "e5f6"), "Fork Combination"),
        RawTacticalPuzzle("8/4r2k/6p1/8/4qPPp/1P2pR1P/P3Q2K/8 b - g3 0 49", listOf("e4f3", "e2f3", "e3e2", "f3c3", "e2e1q", "c3e1", "e7e1"), "Sacrifice Combination"),
        RawTacticalPuzzle("r2k2nr/p3qBb1/1p1p3p/Q5p1/3n1B2/2N2R2/PPP3P1/R5K1 w - - 0 19", listOf("a5d5", "d4f3", "g2f3", "a8c8", "f4d6"), "Sacrifice in Kings Gambit Accepted"),
        RawTacticalPuzzle("2kr3r/pp2nppp/4p3/1BPpP3/bn1N2Q1/q1N5/2PB1PPP/1K1R3R b - - 5 14", listOf("a4c2", "d4c2", "a3b3", "b1a1", "b4c2"), "Deflection in French Defense French"),
        RawTacticalPuzzle("6k1/5p1p/B2bp2q/5p2/6rP/1P4P1/1Q3P2/3R3K b - - 4 34", listOf("g4h4", "g3h4", "h6h4", "h1g2", "h4g4", "g2f1", "g4d1"), "Fork Combination"),
        RawTacticalPuzzle("2r2r1k/7n/p6q/1p1nNb2/7P/3P2Q1/PPP2P2/1K4RR w - - 1 31", listOf("g3g8", "f8g8", "e5f7"), "Mate In2 Combination"),
        RawTacticalPuzzle("1r6/pp2kppQ/2n1p1n1/3p2P1/5P2/2PqP3/PP1N4/2KR3R b - - 4 27", listOf("c6b4", "c3b4", "b8c8", "d2c4", "c8c4"), "Mate In3 Combination"),
        RawTacticalPuzzle("2Qqk3/5pp1/2pr4/2Np4/3P4/7P/P1P3P1/6K1 w - - 5 35", listOf("c5b7", "d8c8", "b7d6", "e8d7", "d6c8"), "Fork Combination"),
        RawTacticalPuzzle("1k5r/ppq2p2/2pN4/4PBb1/2P2pp1/1P5r/P1Q3KP/3R3R b - - 6 28", listOf("f4f3", "g2f1", "g4g3", "f5h3", "h8h3"), "Sacrifice Combination"),
        RawTacticalPuzzle("2rk4/6R1/p3p3/2B1P2p/2pP3P/2b5/5PP1/6K1 b - - 0 27", listOf("c8c5", "d4c5", "c3b2"), "Sacrifice Combination"),
        RawTacticalPuzzle("r5k1/2p1pp2/pp4p1/1q5r/5P2/2QP2R1/PP6/1K4R1 w - - 1 33", listOf("g3g6", "f7g6", "g1g6", "g8f7", "c3g7", "f7e8", "g7g8", "e8d7", "g8e6", "d7d8", "g6g8"), "Sacrifice Combination"),
        RawTacticalPuzzle("r3k2r/1p1bbpp1/pq3p1p/3p4/3P4/2N2N2/PPP1QPPP/R3K2R w KQkq - 2 12", listOf("e2e7", "e8e7", "c3d5", "e7d6", "d5b6"), "Attraction in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("2k2rr1/1p1q3p/p1nb4/2p1p3/2N1R3/3P1QP1/PPP5/R1B3K1 w - - 5 23", listOf("f3f8", "g8f8", "c4b6", "c8c7", "b6d7"), "Fork Combination"),
        RawTacticalPuzzle("8/8/1pr3kp/pbPpp1pN/3Pp1P1/1P2K3/P6P/2R5 w - - 0 30", listOf("c5b6", "c6c1", "b6b7", "c1e1", "e3d2"), "Sacrifice Combination"),
        RawTacticalPuzzle("1r6/6R1/3p4/KP1Rp3/2r1P3/2k2P2/8/8 b - - 2 45", listOf("c4a4", "a5a4", "b8a8", "g7a7", "a8a7"), "Attraction Combination"),
        RawTacticalPuzzle("r1br2k1/qp3pp1/p3p2p/8/8/P5P1/1PBB2P1/2RQ3K w - - 1 22", listOf("d2e3", "d8d1", "c1d1", "g8f8", "e3a7", "a8a7", "d1d8", "f8e7", "d8c8"), "Fork Combination"),
        RawTacticalPuzzle("4k3/5P1r/4P1N1/p5KP/8/p1P5/2P4P/8 b - - 0 48", listOf("h7f7", "e6f7", "e8f7", "h5h6", "f7g8", "g6e5", "g8h7"), "Sacrifice Combination"),
        RawTacticalPuzzle("8/4kppR/r1p1p2p/p3P2P/4P1P1/1pP2P2/3K2B1/8 b - - 1 29", listOf("a5a4", "g2f1", "a4a3", "f1a6", "a3a2"), "Sacrifice Combination"),
        RawTacticalPuzzle("r1b1kb1r/1pqpnppp/p1n1p3/8/2BNP3/P1N5/1PP2PPP/R1BQK2R w KQkq - 1 8", listOf("d4b5", "a6b5", "c3b5", "c7b8", "b5d6"), "Sacrifice in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("1r2q1k1/2R4p/3pNp2/3P4/1p3pbb/1BP5/1P3PPK/Q4R2 b - - 0 28", listOf("h4g3", "h2g1", "e8h5", "c7g7", "g8h8", "f2g3", "f4g3", "f1f3", "h5h2"), "Sacrifice Combination"),
        RawTacticalPuzzle("r1bn1k2/p2pbP2/1p4pr/1P5p/2B5/P1B3N1/2P3PP/1K2R3 w - - 1 24", listOf("e1e7", "f8e7", "c3b4", "d7d6", "b4d6"), "Attraction Combination"),
        RawTacticalPuzzle("5rk1/5p1p/1qp2p2/p2pb2Q/P7/1r5P/4R1P1/4RN1K w - - 0 33", listOf("e2e5", "f6e5", "h5g5", "g8h8", "g5f6"), "Sacrifice Combination"),
        RawTacticalPuzzle("6k1/1p1R3p/p5p1/P1B1bpK1/4r2P/6P1/2P2P2/8 b - - 4 34", listOf("e4g4", "g5h6", "g4h4", "g3h4", "e5f4"), "Deflection Combination"),
        RawTacticalPuzzle("rk5r/1b3R2/pp2p2q/4P2p/B6B/4p2P/PP4P1/5Q1K w - - 0 28", listOf("f7b7", "b8b7", "f1f7", "b7b8", "h4e7"), "Attraction Combination"),
        RawTacticalPuzzle("5rk1/pR5p/2r1pBp1/4P3/2b5/3p4/P4PPP/4R1K1 b - - 1 27", listOf("f8f6", "e5f6", "d3d2", "e1d1", "c4e2"), "Clearance Combination"),
        RawTacticalPuzzle("6k1/p4p1p/4bp2/1p2p3/3r4/KP1nQPN1/P7/5B2 b - - 1 33", listOf("d4a4", "b3a4", "b5b4"), "Mate In2 Combination"),
        RawTacticalPuzzle("r3k1r1/pp3pP1/2p1p3/6Bb/4P2P/3Q4/PpP2qB1/1K2R3 w q - 0 24", listOf("d3d6", "f2e1", "b1b2"), "Sacrifice Combination"),
        RawTacticalPuzzle("8/1P2pk2/R2b4/N4pp1/8/3K2P1/1r4P1/8 w - - 3 41", listOf("a6d6", "e7d6", "a5c6", "b2b7", "c6d8", "f7f6", "d8b7"), "Fork Combination"),
        RawTacticalPuzzle("6r1/pp1qbpk1/8/2pPp1r1/2P1Pp2/P1N2QpR/3B2K1/R7 b - - 1 31", listOf("d7h3", "g2h3", "g3g2"), "Sacrifice Combination"),
        RawTacticalPuzzle("4r1k1/p3qpp1/1bQ5/5p1p/1P5P/8/PBP2PP1/3R2K1 b - - 0 24", listOf("b6f2", "g1f2", "e7e3", "f2f1", "e3e2", "f1g1", "e2d1"), "Attraction Combination"),
        RawTacticalPuzzle("r1bqkr2/1pp3pN/p1np4/4pp2/2B1n3/P1NPK2P/1PP3P1/R1BQ3R b q - 1 11", listOf("f5f4", "e3e4", "c8f5", "e4f3", "d8h4"), "Fork in Kings Knight Opening"),
        RawTacticalPuzzle("6rr/pp1k1p2/1q2p1b1/2bpQ3/5B1P/2N2P2/PPn5/2KR2NR w - - 0 21", listOf("c3d5", "e6d5", "d1d5", "d7c6", "d5c5", "b6c5", "e5c7", "c6b5", "a2a4", "b5c4", "b2b3", "c4b3", "c7c5"), "Deflection Combination"),
        RawTacticalPuzzle("1r4k1/5p2/2q3bp/4R3/p1r5/P1N5/KPP1Q2P/2R5 b - - 3 33", listOf("c4c3", "b2c3", "f7f6"), "Sacrifice Combination"),
        RawTacticalPuzzle("4r3/p6k/1p5P/1P2P1K1/8/5N2/8/8 w - - 1 43", listOf("g5h5", "h7g8", "f3g5", "e8e5", "h5g6", "e5b5", "h6h7"), "Sacrifice Combination"),
        RawTacticalPuzzle("2r5/7R/2P2p2/1P3P2/7k/6pp/1K6/8 b - - 3 48", listOf("h4g4", "c6c7", "c8c7", "h7c7", "h3h2", "b5b6", "h2h1q"), "Sacrifice Combination"),
        RawTacticalPuzzle("3r2k1/pp1P1p1p/6p1/2R1P2r/5P2/6Pq/3Q3P/3R2K1 b - - 2 33", listOf("d8d7", "d2d7", "h3h2", "g1f1", "h2g3", "d7d8", "g8g7", "d8f6", "g7h6", "f6g5", "h5g5"), "Deflection Combination"),
        RawTacticalPuzzle("r3r1k1/p4ppp/2p2n2/1p6/3P1qb1/2NQ2R1/PPB2PP1/R1B3K1 b - - 6 18", listOf("e8e1", "g1h2", "e1c1", "a1c1", "f4h6", "h2g1", "h6c1"), "Attraction in French Defense French"),
        RawTacticalPuzzle("r2q2k1/pp1n2bp/3P2b1/6N1/6Q1/P3P3/6P1/4K2R w K - 2 22", listOf("g4c4", "g8h8", "h1h7", "g6h7", "g5f7"), "Sacrifice Combination"),
        RawTacticalPuzzle("r6r/pp2kB2/1bp2p2/4p1p1/6b1/1QP3p1/PP1R1PP1/4R1K1 b - - 3 23", listOf("b6f2", "d2f2", "h8h1", "g1h1", "g3f2"), "Fork Combination"),
        RawTacticalPuzzle("2r3k1/pb3qN1/1p2p2p/2b5/6n1/2Pp2B1/PP1Q1PP1/1BR1R1K1 b - - 1 28", listOf("c5f2", "g3f2", "f7c7", "d2h6", "g4h6"), "Fork Combination"),
        RawTacticalPuzzle("r1b2r2/1p5k/2Pp2pp/p2Bb3/2Pp1p2/P2P2Pq/3B1P1P/1R1QR1K1 b - - 0 26", listOf("f4g3", "f2g3", "f8f2", "g1f2", "h3h2"), "Attraction Combination")
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
