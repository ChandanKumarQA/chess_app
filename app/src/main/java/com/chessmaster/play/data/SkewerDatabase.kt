package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object SkewerDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("8/pR6/3k4/3Nn3/4P3/1KP5/8/4r3 b - - 0 46", listOf("e1b1", "b3c2", "b1b7"), "Skewer Combination"),
        RawTacticalPuzzle("r5k1/6pp/p1R5/4Pp2/1P3P2/4P3/1P3P1r/R4K2 b - - 2 28", listOf("h2h1", "f1g2", "h1a1"), "Skewer Combination"),
        RawTacticalPuzzle("8/3n1k2/p3p1p1/3p2P1/2pP2N1/PrP2P2/1P1K2R1/8 b - - 5 41", listOf("b3b2", "d2e1", "b2g2"), "Skewer Combination"),
        RawTacticalPuzzle("8/3R2p1/2p2pkp/1bB5/pP1K2PP/P4P2/4r3/8 b - - 0 39", listOf("e2d2", "d4c3", "d2d7"), "Skewer Combination"),
        RawTacticalPuzzle("3rk1nr/p1p2ppp/2p5/4N3/4P1b1/2P5/PPP3PP/R1B1K2R b KQk - 0 10", listOf("d8d1", "e1f2", "d1h1"), "Skewer in Vienna Gambit with"),
        RawTacticalPuzzle("8/pR6/8/P7/2P2rk1/1P1K4/8/6b1 w - - 1 40", listOf("b7g7", "g4f5", "g7g1"), "Skewer Combination"),
        RawTacticalPuzzle("6R1/8/Kpk1p3/1p1pP3/6P1/PPr5/8/8 w - - 0 41", listOf("g8c8", "c6d7", "c8c3"), "Skewer Combination"),
        RawTacticalPuzzle("6R1/6p1/p1k4p/1p1p4/PP2p1P1/2b1P3/6K1/8 w - - 0 38", listOf("g8c8", "c6b7", "c8c3"), "Skewer Combination"),
        RawTacticalPuzzle("8/2k1b3/5p2/RBpK1Pp1/P2p2P1/1p1P4/2r5/8 w - - 0 46", listOf("a5a7", "c7b8", "a7e7"), "Skewer Combination"),
        RawTacticalPuzzle("8/7k/6pP/5p2/r4P2/4K2R/8/8 b - - 5 58", listOf("a4a3", "e3d4", "a3h3"), "Skewer Combination"),
        RawTacticalPuzzle("6R1/p7/5k2/P7/6KP/8/8/5r2 b - - 6 53", listOf("f1g1", "g4f4", "g1g8"), "Skewer Combination"),
        RawTacticalPuzzle("4k3/4bppp/pp6/4P3/P2PP3/4K1P1/7P/2B5 b - - 4 28", listOf("e7g5", "e3e2", "g5c1"), "Skewer Combination"),
        RawTacticalPuzzle("r4rk1/pp3pbp/5pp1/7q/1Q1PP1P1/3B1N2/PP1B1P2/R3K2R b KQ - 0 18", listOf("h5h1", "e1e2", "h1a1"), "Fork in Slav Defense Slav"),
        RawTacticalPuzzle("8/8/5k2/R2K4/2P4r/8/P7/8 b - - 2 43", listOf("h4h5", "d5d6", "h5a5"), "Skewer Combination"),
        RawTacticalPuzzle("4b3/2k5/2p1K3/2Pp4/3P2N1/8/8/8 b - - 16 56", listOf("e8d7", "e6e5", "d7g4"), "Skewer Combination"),
        RawTacticalPuzzle("r4k1r/pppq1p2/3pb2p/6p1/2nQ4/8/PPP2PPP/R4RK1 w - - 0 17", listOf("d4h8", "f8e7", "h8a8"), "Hanging Piece in Four Knights Game"),
        RawTacticalPuzzle("8/p2r3p/4k3/8/4P3/3p2P1/PP3K1P/3B4 w - - 4 33", listOf("d1g4", "e6e5", "g4d7"), "Skewer Combination"),
        RawTacticalPuzzle("1r3k2/1p1q1p2/p2p2p1/2pP2bp/2P1n1n1/1PQ3P1/P3N1K1/3N1R1R w - - 0 29", listOf("c3h8", "f8e7", "h8b8"), "Skewer Combination"),
        RawTacticalPuzzle("8/8/2k3r1/R7/P2p3p/3K4/8/8 w - - 0 46", listOf("a5a6", "c6d7", "a6g6"), "Skewer Combination"),
        RawTacticalPuzzle("4R3/6p1/6pp/Bp1kn3/2p3PP/2P3K1/3r4/8 w - - 9 42", listOf("e8d8", "d5c6", "d8d2"), "Skewer Combination"),
        RawTacticalPuzzle("8/5k2/6RK/8/8/3r4/8/8 b - - 2 58", listOf("d3h3", "h6g5", "h3g3", "g5f4", "g3g6"), "Deflection Combination"),
        RawTacticalPuzzle("8/r4k2/7R/3n1PK1/8/8/8/8 w - - 4 57", listOf("h6h7", "f7f8", "h7a7"), "Skewer Combination"),
        RawTacticalPuzzle("8/7R/r3k3/4p2p/3b2p1/3K4/8/5R2 w - - 0 56", listOf("h7h6", "e6d5", "h6a6"), "Skewer Combination"),
        RawTacticalPuzzle("r1b5/ppr2k1p/5p2/5p2/8/2P3P1/P4PP1/4RK1R w - - 2 24", listOf("h1h7", "f7g6", "h7c7"), "Skewer Combination"),
        RawTacticalPuzzle("8/p6r/3kp3/R2p1pp1/3P4/4PPP1/8/5K2 w - - 1 36", listOf("a5a6", "d6e7", "a6a7", "e7f6", "a7h7"), "Skewer Combination"),
        RawTacticalPuzzle("6Q1/p7/8/1p6/1P3PK1/P2kq3/8/8 b - - 2 58", listOf("e3g1", "g4f5", "g1g8"), "Skewer Combination"),
        RawTacticalPuzzle("5rk1/6pp/pqp1p3/4P3/1PK5/2P3P1/P3Q2P/3R4 b - - 0 28", listOf("b6b5", "c4b3", "b5e2"), "Skewer Combination"),
        RawTacticalPuzzle("4r3/pp5p/4k3/2p1r3/3p4/PP1P4/3KR2P/5R2 w - - 2 32", listOf("e2e5", "e6e5", "f1e1", "e5d6", "e1e8"), "Attraction Combination"),
        RawTacticalPuzzle("r1b2rk1/5ppp/4p3/1B6/1P6/5P2/2PR2PP/2K4R b - - 0 18", listOf("a8a1", "c1b2", "a1h1"), "Skewer in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("8/8/2k5/P2RK1p1/8/5P2/r7/8 b - - 6 57", listOf("a2e2", "e5d4", "e2d2", "d4e3", "d2d5"), "Deflection Combination"),
        RawTacticalPuzzle("7R/8/8/8/r2k4/4p3/8/4K3 w - - 2 58", listOf("h8h4", "d4c3", "h4a4"), "Skewer Combination"),
        RawTacticalPuzzle("6k1/7r/4P1p1/Q7/3PK3/1r6/6P1/8 b - - 10 51", listOf("h7h4", "e4d5", "h4h5", "d5c4", "h5a5"), "Skewer Combination"),
        RawTacticalPuzzle("4r1k1/1p3p1p/p3p1pQ/3b4/7R/1P1P1P2/2r3PP/q3NK1R w - - 0 25", listOf("h6h7", "g8f8", "h7h8", "a1h8", "h4h8", "f8g7", "h8e8"), "Skewer Combination"),
        RawTacticalPuzzle("2k4r/p2p4/8/1pp2p2/5P2/3P1Q2/qP5P/5R1K w - - 0 33", listOf("f3a8", "c8c7", "a8h8", "a2d5", "h1g1"), "Skewer Combination"),
        RawTacticalPuzzle("8/5p2/Q6p/1p2k2q/3pp3/2r5/6R1/6K1 w - - 2 42", listOf("a6b5", "e5d6", "b5h5"), "Skewer Combination"),
        RawTacticalPuzzle("8/3R2pp/2rP1pk1/2b5/8/P6P/1P3P2/1K6 w - - 1 45", listOf("d7c7", "c6d6", "c7c5"), "Skewer Combination"),
        RawTacticalPuzzle("rb4k1/2q3pp/P3pp2/3p4/1Q1P4/3NP3/2P2PPP/R5K1 b - - 3 26", listOf("c7h2", "g1f1", "h2h1", "f1e2", "h1a1"), "Skewer Combination"),
        RawTacticalPuzzle("r3k1nr/1pp2ppp/1pnp4/4p1q1/2B1P3/3P1Q1P/PPP2PP1/R4RK1 w kq - 0 12", listOf("f3f7", "e8d8", "f7f8", "d8d7", "f8a8"), "Attacking F2 F7 in Italian Game Italian"),
        RawTacticalPuzzle("8/1R2B3/5p2/4bP1p/p1k4r/5K2/8/8 w - - 8 46", listOf("b7b4", "c4d5", "b4h4"), "Skewer Combination"),
        RawTacticalPuzzle("2k3r1/p4prp/8/2p5/2N5/1PPp1P2/PB1P1K1P/4R3 b - - 7 23", listOf("g7g2", "f2e3", "g8e8", "e3d3", "e8e1"), "Skewer Combination"),
        RawTacticalPuzzle("1R2b3/4P3/R7/3p1p1k/pK1P1P2/r7/8/8 b - - 5 56", listOf("a3b3", "b4c5", "b3b8"), "Skewer Combination"),
        RawTacticalPuzzle("1r5r/p3kp2/4p2p/4P3/R4Pp1/6P1/P1P4P/4K2R b K - 2 25", listOf("b8b1", "e1f2", "b1h1", "a4a7", "e7f8"), "Skewer Combination"),
        RawTacticalPuzzle("8/7Q/3p1kp1/1p6/2b5/2q4P/5PPK/8 w - - 0 37", listOf("h7h8", "f6e6", "h8c3"), "Skewer Combination"),
        RawTacticalPuzzle("8/r4pp1/2k1p2p/8/pK1PP3/5PP1/6P1/1R6 b - - 2 35", listOf("a7b7", "b4a4", "b7b1"), "Skewer Combination"),
        RawTacticalPuzzle("8/6p1/2R4p/p7/1p1q3P/1P3BP1/5PK1/4k3 w - - 15 46", listOf("c6c1", "e1d2", "c1d1", "d2c3", "d1d4"), "Skewer Combination"),
        RawTacticalPuzzle("8/1kp5/4n3/3p4/r2P3R/2K2N1P/8/8 b - - 6 48", listOf("a4a3", "c3c2", "a3f3"), "Skewer Combination"),
        RawTacticalPuzzle("6k1/r1b1q3/2p3p1/2Pp4/1P2p1n1/2B1P3/NQ6/2K4R w - - 2 37", listOf("h1h8", "g8f7", "h8h7", "f7e8", "h7e7"), "Skewer Combination"),
        RawTacticalPuzzle("8/4n1k1/4P3/3p2PP/rp1P4/3K4/P4R2/8 b - - 1 47", listOf("a4a3", "d3c2", "a3a2", "c2b3", "a2f2"), "Skewer Combination"),
        RawTacticalPuzzle("3b4/3P1k2/2pB4/1p1bK1p1/2p3B1/2P5/P4P2/8 b - - 3 47", listOf("d8f6", "e5f5", "d5e6", "f5e4", "e6g4"), "Deflection Combination"),
        RawTacticalPuzzle("8/KP2r1pk/5p2/7p/4Q2P/8/8/8 b - - 0 72", listOf("e7e4", "b7b8q", "e4a4", "a7b7", "a4b4", "b7c8", "b4b8"), "Skewer Combination"),
        RawTacticalPuzzle("2k3r1/1p1q3p/1p2p3/1NbpQr2/P1p2P2/6P1/6KP/R4R2 w - - 1 32", listOf("b5a7", "c8d8", "e5b8", "d8e7", "b8g8"), "Skewer Combination"),
        RawTacticalPuzzle("8/4R3/p4kpp/3B4/5q2/8/5P1P/6K1 w - - 6 41", listOf("e7f7", "f6e5", "f7f4"), "Skewer Combination"),
        RawTacticalPuzzle("8/2p1b3/q1Pp2k1/3Pp3/4Pr2/1Q3PK1/3N2P1/4R3 b - - 2 47", listOf("e7h4", "g3h2", "h4e1"), "Skewer Combination"),
        RawTacticalPuzzle("6k1/p2R4/6p1/5p2/3KbP2/r7/P3R3/8 b - - 5 41", listOf("a3d3", "d4c4", "d3d7"), "Skewer Combination"),
        RawTacticalPuzzle("5Q2/8/1bk1p1p1/5p2/3p4/5qPK/7P/8 w - - 2 52", listOf("f8a8", "c6d6", "a8f3"), "Skewer Combination"),
        RawTacticalPuzzle("8/8/1r6/p3p3/2P1N1Pk/1P2KR1n/8/8 b - - 1 47", listOf("b6b3", "e3d2", "b3f3"), "Deflection Combination"),
        RawTacticalPuzzle("4r3/5p2/1Q6/3pk3/7K/P5PN/7q/8 w - - 12 50", listOf("b6e3", "e5d6", "e3e8"), "Skewer Combination"),
        RawTacticalPuzzle("5Q2/7K/8/8/4p2P/3k2q1/8/8 w - - 2 54", listOf("f8a3", "d3d2", "a3g3"), "Skewer Combination"),
        RawTacticalPuzzle("1r6/1n4p1/1P1p1kp1/4p3/2K1P3/5P1P/5BP1/2R5 b - - 12 46", listOf("b8c8", "c4b5", "c8c1"), "Skewer Combination"),
        RawTacticalPuzzle("r1b5/1pp4p/p2p2pk/3Pr3/1PP5/4qNP1/PQ4P1/R4R1K b - - 2 23", listOf("e5h5", "f3h4", "e3g3"), "Pin Combination"),
        RawTacticalPuzzle("r1b2nk1/2q4p/2p1rQp1/p1b5/1pP1p3/1P2PBP1/PB3P1P/3R1RK1 w - - 2 21", listOf("f6h8", "g8f7", "h8g7", "f7e8", "g7c7"), "Skewer Combination"),
        RawTacticalPuzzle("5k2/3Q4/p1P4p/7P/P2P2KP/5p2/5Pb1/7r b - - 0 41", listOf("g2h3", "g4f3", "h3d7", "c6d7", "f8e7", "d7d8r", "e7d8"), "Skewer Combination"),
        RawTacticalPuzzle("1r3k2/3brqb1/3p3B/1p1Pp3/p3P2Q/PpP2P2/1P3KP1/R6R w - - 9 40", listOf("h6g7", "f7g7", "h4h8", "f8f7", "h8b8"), "Clearance Combination"),
        RawTacticalPuzzle("3rk3/R4p2/p6p/1p4pB/8/1b2P3/1P2KPPP/3R4 b - - 0 26", listOf("b3d1", "e2e1", "d1h5"), "Skewer Combination"),
        RawTacticalPuzzle("8/3Q4/6p1/3pk1P1/3qpp1p/1P5P/P4PK1/8 w - - 6 44", listOf("d7g7", "e5f5", "g7d4"), "Deflection Combination"),
        RawTacticalPuzzle("6B1/p2R4/5p1p/2r3k1/4b3/6P1/P5PK/8 w - - 10 45", listOf("d7g7", "g5f5", "g8h7", "f5e6", "h7e4"), "Deflection Combination"),
        RawTacticalPuzzle("6k1/p3b1p1/1p6/7R/K1P5/1P4B1/P3rPPP/8 b - - 2 29", listOf("e2a2", "a4b5", "a2a5", "b5c6", "a5h5"), "Skewer Combination"),
        RawTacticalPuzzle("7r/6k1/2b1Rp2/8/P1N3p1/5nP1/5P2/Q4K2 b - - 0 38", listOf("h8h1", "f1e2", "h1a1"), "Skewer Combination"),
        RawTacticalPuzzle("2R5/8/p7/3R4/4p2P/1k6/r1p2P2/2K5 b - - 0 51", listOf("a2a1", "c1d2", "a1d1", "d2e3", "d1d5"), "Skewer Combination"),
        RawTacticalPuzzle("6k1/R3K3/8/1r6/5r2/p4N2/8/5R2 b - - 4 50", listOf("f4f7", "e7d6", "f7a7"), "Skewer Combination"),
        RawTacticalPuzzle("r4rk1/p2b1pp1/3bp2p/3pq3/2pN4/P1P1B1P1/1P1Q1P1P/R4RK1 w - - 0 21", listOf("e3f4", "e5f6", "f4d6"), "Skewer Combination"),
        RawTacticalPuzzle("r5k1/6pR/3prPP1/8/1pp1P3/p7/2P5/1K6 w - - 0 39", listOf("f6f7", "g8f8", "h7h8", "f8e7", "h8a8"), "Skewer Combination"),
        RawTacticalPuzzle("r7/pp1Qp1B1/3b4/3k4/4q3/8/PP3PP1/5K2 w - - 5 35", listOf("d7b7", "d5e6", "b7e4"), "Deflection Combination"),
        RawTacticalPuzzle("8/p2R4/3B2pk/3p1p1p/4p2q/PP2b3/5P2/6K1 w - - 0 34", listOf("d6f8", "h6g5", "f8e7", "g5g4", "e7h4"), "Skewer Combination"),
        RawTacticalPuzzle("r7/2p2pk1/q4np1/3PQ3/4P3/5PPK/2R2N1P/8 b - - 0 34", listOf("a6f1", "h3h4", "a8h8", "h4g5", "h8h5", "g5f4", "h5e5"), "Skewer Combination"),
        RawTacticalPuzzle("4R3/pp3ppk/7p/1qpQ3b/5B2/8/PPP2PP1/2K5 b - - 0 24", listOf("b5f1", "c1d2", "f1d1", "d2c3", "d1d5"), "Skewer Combination"),
        RawTacticalPuzzle("2k1r2r/ppq2p2/2pb4/2p2P1p/4P2P/1PQ2RP1/P1P2P2/R1B3K1 b - - 0 23", listOf("d6e5", "c3e1", "e5a1"), "Skewer Combination"),
        RawTacticalPuzzle("1r6/1pk4p/pR4n1/2p5/6P1/2B4P/P4K2/8 w - - 3 36", listOf("b6g6", "h7g6", "c3e5", "c7d7", "e5b8"), "Sacrifice Combination"),
        RawTacticalPuzzle("r2q1rk1/pQ1nppNp/5np1/8/2p5/6P1/PB2PPBP/R4RK1 b - - 0 14", listOf("a8b8", "b7a7", "b8b2"), "Skewer in Queens Pawn Game"),
        RawTacticalPuzzle("8/8/8/3p4/5p1R/r3k3/6K1/8 w - - 0 52", listOf("h4h3", "e3e2", "h3a3", "d5d4", "a3a2"), "Skewer Combination"),
        RawTacticalPuzzle("1r2q1k1/1rR2pp1/1p1np2p/3pN3/3P4/P3P2P/1PQ2PP1/2R3K1 b - - 0 27", listOf("b7c7", "c2c7", "b8c8", "c7d6", "c8c1"), "Skewer Combination"),
        RawTacticalPuzzle("7r/1p2Rpk1/pP1p2p1/P2P4/3r4/2p5/2P5/2K2R2 w - - 0 39", listOf("e7f7", "g7h6", "f1h1", "h6g5", "h1h8"), "Skewer Combination"),
        RawTacticalPuzzle("2b5/2Q2p2/k7/1q2P3/1p6/1N3P2/1PP5/2K5 b - - 0 38", listOf("b5f1", "c1d2", "f1f2", "d2d3", "c8f5", "d3c4", "f2c2", "c4b4", "c2c7"), "Deflection Combination"),
        RawTacticalPuzzle("7k/2p3p1/5p1p/2Qq4/3Pr3/4P3/P4P1P/1R4K1 b - - 3 26", listOf("e4g4", "g1f1", "d5h1", "f1e2", "h1b1"), "Skewer Combination"),
        RawTacticalPuzzle("8/1p2rk2/p4q2/7p/5Q2/P5BP/1P4PK/8 w - - 2 39", listOf("f4f6", "f7f6", "g3h4", "f6e5", "h4e7"), "Attraction Combination"),
        RawTacticalPuzzle("3qr1k1/1b3pp1/p2p1b2/1p6/1P2PB2/P1pP1PQ1/B5PP/4R1K1 b - - 1 21", listOf("f6h4", "g3g4", "h4e1"), "Skewer Combination"),
        RawTacticalPuzzle("r2q1rk1/4bppp/p3bn2/1p2N3/5B2/2N5/PP3PPP/R2QR1K1 w - - 2 20", listOf("e5c6", "d8e8", "c6e7", "e8e7", "f4d6", "e7d8", "d6f8"), "Skewer in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("6k1/4pp2/p5pB/2p4n/3pP1Q1/P2P2qP/1r4P1/5RK1 w - - 2 31", listOf("g4c8", "g8h7", "f1f7", "h5g7", "f7g7", "h7h6", "c8h8", "h6g5", "g7g6", "g5g6", "h8g8", "g6f6", "g8g3"), "Attraction Combination"),
        RawTacticalPuzzle("1r1qr1k1/p1p2ppp/1p1b1n2/3N4/3Q4/1P4P1/PBP1PPKP/R3R3 b - - 2 15", listOf("d6e5", "d5f6", "d8f6", "d4c4", "e5b2"), "Skewer in Zukertort Opening Zukertort"),
        RawTacticalPuzzle("k1r5/n1N3p1/Q2p4/P2Pp2p/q3Pp1P/2RK4/5PP1/8 b - - 0 36", listOf("c8c7", "c3c7", "a4d1", "d3c3", "d1c1", "c3b3", "c1c7"), "Sacrifice Combination"),
        RawTacticalPuzzle("r3r2Q/p2k1pn1/5q2/3p4/1p5P/3B1PR1/PPP2nP1/1K2R3 w - - 7 28", listOf("d3b5", "d7d6", "b5e8", "a8e8", "e1e8"), "Skewer Combination"),
        RawTacticalPuzzle("2r5/pp5p/3kpQp1/8/2q5/2Pr2P1/P6P/3R1RK1 w - - 9 30", listOf("f6f4", "d6c5", "f4c4", "c5c4", "f1f4", "c4c3", "d1c1", "c3b2", "c1c8"), "Attraction Combination"),
        RawTacticalPuzzle("4B3/1b4kp/p7/3p3R/6P1/1P6/KPrn1r2/7R w - - 3 35", listOf("h5h7", "g7f8", "h7b7"), "Skewer Combination"),
        RawTacticalPuzzle("8/7R/8/5p2/4bk1P/8/2r5/5KR1 b - - 8 51", listOf("f4f3", "f1e1", "c2c1", "e1d2", "c1g1"), "Skewer Combination"),
        RawTacticalPuzzle("5r1k/2p3p1/p1pp3p/8/Pb1PP3/1Q2KP2/1P2R2q/R1B5 b - - 4 25", listOf("f8f3", "e3f3", "h2h3", "f3f2", "h3b3"), "Attraction Combination"),
        RawTacticalPuzzle("8/5k2/7p/p1P1bPpP/Pp2P3/1P1p1K2/5B2/8 b - - 2 47", listOf("g5g4", "f3e3", "e5d4", "e3d3", "d4f2"), "Deflection Combination"),
        RawTacticalPuzzle("3k3r/pp1P1ppp/r1p2q2/1N3n2/6n1/8/PP3PPP/2RQR1K1 w - - 3 20", listOf("d1g4", "c6b5", "c1c8", "d8d7", "c8h8"), "Skewer in Kings Pawn Game"),
        RawTacticalPuzzle("6k1/4rRp1/p6p/7P/q1pP4/2Pb2Q1/P4RP1/6K1 b - - 0 31", listOf("e7f7", "g3b8", "g8h7", "f2f7", "a4d1", "g1f2", "d1f1", "f2g3", "f1f7"), "Skewer Combination"),
        RawTacticalPuzzle("r4rk1/p4pp1/4b2p/2bp4/5B1Q/6R1/PP2qPPP/5RK1 w - - 0 22", listOf("g3g7", "g8g7", "f4h6", "g7h7", "h6f8", "h7g8", "f8c5"), "Attraction Combination"),
        RawTacticalPuzzle("3r2k1/pb2q1r1/4p3/2p2p1Q/3p4/3R3R/PPP2P1P/5K2 w - - 2 27", listOf("d3g3", "e7f6", "g3g7", "g8g7", "h5h7", "g7f8", "h7b7"), "Attraction Combination")
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
            val cleanTheme = "Skewer"
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
