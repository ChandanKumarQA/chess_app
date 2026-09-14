package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object ForkDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("5k2/5p1p/1p1P2p1/1B6/4KP2/1P4P1/n6P/8 b - - 1 38", listOf("a2c3", "e4d4", "c3b5"), "Fork Combination"),
        RawTacticalPuzzle("6k1/6pp/p1N5/1pP2bp1/5P2/8/PPP5/3K4 w - - 0 29", listOf("c6e7", "g8f7", "e7f5"), "Fork Combination"),
        RawTacticalPuzzle("5rk1/5ppp/4p3/4N3/8/1Pn5/5PPP/2R3K1 b - - 1 28", listOf("c3e2", "g1f1", "e2c1"), "Fork Combination"),
        RawTacticalPuzzle("1k6/pp6/4nNp1/P3r2p/3p4/7P/3R1PPK/8 w - - 1 41", listOf("f6d7", "b8c7", "d7e5"), "Fork Combination"),
        RawTacticalPuzzle("8/4k3/1p1p4/rP2p1p1/P2nP1P1/3B4/3K4/R7 b - - 1 35", listOf("d4b3", "d2c3", "b3a1"), "Fork Combination"),
        RawTacticalPuzzle("6k1/5pp1/p1N5/1n1b3p/1B4P1/P4P1P/2K5/8 w - - 3 43", listOf("c6e7", "g8h7", "e7d5"), "Fork Combination"),
        RawTacticalPuzzle("8/8/8/2Pk4/pK4p1/3N4/4bP2/8 w - - 8 60", listOf("d3f4", "d5c6", "f4e2"), "Fork Combination"),
        RawTacticalPuzzle("r4rk1/3nqpp1/4p2p/1p2P3/2pn1Q2/P6N/1P3PPP/1B1R1RK1 b - - 1 24", listOf("d4e2", "g1h1", "e2f4"), "Fork Combination"),
        RawTacticalPuzzle("b7/2k1pp2/ppn2qp1/8/4B3/1PNR4/P1P2PP1/5K2 w - - 0 27", listOf("c3d5", "c7b8", "d5f6"), "Fork Combination"),
        RawTacticalPuzzle("3rr1k1/p4pp1/1pp4p/3pPQ2/1P3P2/2P2RqP/P2R2P1/6K1 b - - 2 24", listOf("g3e1", "g1h2", "e1d2"), "Fork Combination"),
        RawTacticalPuzzle("6rk/1pR3p1/6Bp/2b4P/8/pP3PK1/P1P5/8 b - - 0 32", listOf("c5d6", "f3f4", "d6c7"), "Fork Combination"),
        RawTacticalPuzzle("2r3k1/6p1/R6p/3P1N2/8/3K4/5b2/8 w - - 6 58", listOf("f5e7", "g8f7", "e7c8"), "Fork Combination"),
        RawTacticalPuzzle("3k4/ppp2p1r/4p2P/5n2/3PK1N1/2P5/P1P3P1/7R b - - 0 30", listOf("f5g3", "e4f4", "g3h1"), "Fork Combination"),
        RawTacticalPuzzle("r6k/pp2n1pp/2nN4/4p1r1/1PB5/2P4b/P3Nb1P/R2R3K w - - 0 23", listOf("d6f7", "h8g8", "f7g5"), "Discovered Attack Combination"),
        RawTacticalPuzzle("8/2kn1p2/8/3P4/R7/2PKN3/1r6/8 b - - 14 70", listOf("d7c5", "d3d4", "c5a4"), "Fork Combination"),
        RawTacticalPuzzle("3q2k1/3r4/pp3p1Q/2b1n3/P3N3/2P5/1P4PP/R6K w - - 1 25", listOf("e4f6", "d8f6", "h6f6"), "Fork Combination"),
        RawTacticalPuzzle("5r2/5p1k/2ppq1p1/4p1b1/4N2P/3P4/1P1R1P2/4K1R1 w - - 0 30", listOf("e4g5", "h7h6", "g5e6"), "Fork Combination"),
        RawTacticalPuzzle("1rr3k1/4ppbp/3p1np1/1b1N4/P2BP3/5P2/P2R2PP/R5K1 w - - 0 22", listOf("d5e7", "g8f8", "e7c8"), "Fork Combination"),
        RawTacticalPuzzle("R7/5pk1/4pn1p/8/3NP3/5P2/6PP/2rB2K1 b - - 0 31", listOf("c1d1", "g1f2", "d1d4"), "Fork Combination"),
        RawTacticalPuzzle("1r1q1rk1/1b4b1/4p1Bp/3pP3/p2B2P1/7Q/PPP5/2KR3R b - - 0 23", listOf("d8g5", "c1b1", "g5g6"), "Fork Combination"),
        RawTacticalPuzzle("r3q3/1ppknr2/p5pB/2QPn3/4P3/8/PP4BP/2K2R1R b - - 0 22", listOf("e5d3", "c1c2", "d3c5"), "Fork Combination"),
        RawTacticalPuzzle("r2q1r2/p3ppkp/3p2P1/1N2n3/4P1P1/5P2/PPPQ4/R3K2R b KQ - 0 16", listOf("e5f3", "e1e2", "f3d2"), "Fork in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("6k1/6pp/1p6/p1n5/4q3/1P2pN2/P4PPP/3Q2K1 w - - 0 29", listOf("d1d8", "g8f7", "f3g5", "f7g6", "g5e4"), "Fork Combination"),
        RawTacticalPuzzle("3r2k1/1B3p1p/6p1/3N4/3p2r1/8/5KP1/3R4 w - - 0 36", listOf("d5f6", "g8g7", "f6g4"), "Fork Combination"),
        RawTacticalPuzzle("r3kb1r/p4ppp/b3p3/2pq4/3Q4/4BN2/PPP2PPP/R3K2R w KQkq - 0 12", listOf("d4a4", "a6b5", "a4b5"), "Fork in French Defense French"),
        RawTacticalPuzzle("r4rk1/p3ppbp/Pp1q1np1/3PpbB1/2B5/2N2P2/1PPQ2PP/3RR1K1 b - - 0 18", listOf("d6c5", "g1h1", "c5c4"), "Fork in Pirc Defense Pirc"),
        RawTacticalPuzzle("6k1/Q2bqr1p/2rpp1pR/p7/Pp2P3/1B3P2/1PP3P1/2KR4 b - - 7 22", listOf("e7g5", "c1b1", "g5h6"), "Fork Combination"),
        RawTacticalPuzzle("r1bqk2r/pp1nbppp/3p4/1B1p4/3P1B2/5N2/PPP2PPP/R2QK2R b KQkq - 3 9", listOf("d8a5", "d1d2", "a5b5"), "Fork in Czech Defense Czech"),
        RawTacticalPuzzle("7R/1p2k2p/p2n2p1/4K3/8/6P1/P6P/8 b - - 11 37", listOf("d6f7", "e5e4", "f7h8"), "Fork Combination"),
        RawTacticalPuzzle("6k1/1p3pp1/pB2q2p/2P1b3/1P6/6QP/4r1P1/3R3K w - - 5 34", listOf("d1d8", "g8h7", "g3d3", "e6g6", "d3e2"), "Fork Combination"),
        RawTacticalPuzzle("3k3r/rp1qb3/2p2nQ1/p2p2N1/3P2pP/P3P1P1/1P3P2/1K1R3R w - - 7 27", listOf("g5f7", "d8c7", "f7h8"), "Fork Combination"),
        RawTacticalPuzzle("r4rk1/1Q2bppp/p1N1p3/1p1q4/2pP1n2/2P5/PP3PPP/R4RK1 w - - 2 19", listOf("c6e7", "g8h8", "e7d5"), "Fork in Queens Pawn Game"),
        RawTacticalPuzzle("r1b2r2/pp1n2k1/2p3pp/4Np2/2BP4/8/PP4PP/2KRR3 w - - 1 19", listOf("e5d7", "c8d7", "e1e7", "g7f6", "e7d7"), "Clearance in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("rn3rk1/p5pp/3N4/4np1q/5Q2/1P6/PB1P1KP1/2R4R b - - 1 25", listOf("e5d3", "f2e3", "d3f4", "h1h5", "f4h5"), "Fork Combination"),
        RawTacticalPuzzle("5qk1/pQ3p2/7p/b2N1bp1/P3r3/5K2/7P/R4B2 w - - 0 25", listOf("d5f6", "g8h8", "f6e4"), "Fork Combination"),
        RawTacticalPuzzle("3r1q1k/p1pb2pp/1pnp4/6N1/5B2/1Q4P1/PP4PP/4R2K w - - 0 25", listOf("g5f7", "f8f7", "b3f7"), "Fork Combination"),
        RawTacticalPuzzle("8/pp1k1p2/4p3/2ppPr1r/7P/2P2pP1/P1P5/2K1RR2 w - - 0 23", listOf("g3g4", "f5e5", "g4h5"), "Fork Combination"),
        RawTacticalPuzzle("3r1rk1/1p2q1pp/5p2/8/1P1n4/6Q1/PPBB1PPP/R4RK1 b - - 0 20", listOf("d4e2", "g1h1", "e2g3", "f2g3", "d8d2"), "Fork Combination"),
        RawTacticalPuzzle("3R4/8/8/KB2b3/1p6/1P2k3/3p4/8 b - - 0 58", listOf("e5c7", "a5b4", "c7d8"), "Fork Combination"),
        RawTacticalPuzzle("6k1/5r1p/4b1pQ/5q2/3B3P/2P5/4R1P1/6K1 b - - 8 36", listOf("f5f1", "g1h2", "f1e2"), "Fork Combination"),
        RawTacticalPuzzle("r2qr1k1/ppp2ppp/4P3/8/1nP2Q2/2N2N1P/PP3KP1/R4R2 b - - 0 15", listOf("b4d3", "f2g1", "d3f4"), "Fork in Kings Gambit Accepted"),
        RawTacticalPuzzle("5rk1/1p2p2p/p2p4/2pPb2R/2P1P3/1P1BKPrR/8/8 w - - 5 31", listOf("h3g3", "e5g3", "h5g5", "g8f7", "g5g3"), "Fork Combination"),
        RawTacticalPuzzle("6k1/6pp/p7/2N1bp2/P1P5/1r6/6PP/2R3K1 b - - 2 33", listOf("e5d4", "g1f1", "d4c5"), "Fork Combination"),
        RawTacticalPuzzle("3r2k1/1p3p2/p1n2P2/2P3P1/1PR1p3/P2pP3/3B4/6K1 b - - 1 33", listOf("c6e5", "c4e4", "e5f3", "g1f2", "f3d2"), "Fork Combination"),
        RawTacticalPuzzle("8/p7/1p4k1/2pN2p1/4Pp2/P1P4r/1P2K1R1/8 b - - 7 50", listOf("f4f3", "e2f2", "f3g2"), "Fork Combination"),
        RawTacticalPuzzle("4k3/1R6/6N1/5p1p/7P/2r3PK/5b2/8 b - - 0 47", listOf("c3g3", "h3h2", "g3g6"), "Fork Combination"),
        RawTacticalPuzzle("2k3nr/ppp2ppp/2n5/8/8/1Q2P3/q4rPP/1R2KB1R w K - 0 16", listOf("b3b7", "c8d7", "b1d1"), "Fork in Englund Gambit Englund"),
        RawTacticalPuzzle("r3brk1/5pp1/p2qpn1p/P2pn3/2pP4/2P1PN2/5PPP/RB1QK2R w KQ - 0 17", listOf("d4e5", "d6e7", "e5f6"), "Fork in Queens Pawn Game"),
        RawTacticalPuzzle("r6k/6bp/p7/2Q5/8/3b1P2/5KPP/8 w - - 3 38", listOf("c5d5", "a8f8", "d5d3"), "Fork Combination"),
        RawTacticalPuzzle("4rk1r/1pp2p2/p2p3p/3N4/3P2q1/8/PPP5/1K2Q1NR w - - 2 24", listOf("e1e8", "f8e8", "d5f6", "e8e7", "f6g4"), "Attraction Combination"),
        RawTacticalPuzzle("3r4/p5k1/1p1qpr1p/1Q1pn1p1/3P1pP1/1PP5/P5PP/4RRK1 w - - 0 30", listOf("d4e5", "d6c5", "b5c5", "b6c5", "e5f6"), "Fork Combination"),
        RawTacticalPuzzle("3r2k1/1b4bR/p2P2p1/3p2N1/2p5/2P2N2/PP6/2K5 w - - 0 29", listOf("h7g7", "g8g7", "g5e6", "g7g8", "e6d8"), "Attraction Combination"),
        RawTacticalPuzzle("3r3k/pp4bp/3Bn1p1/7n/8/8/PP3P1K/3R1RN1 b - - 2 27", listOf("d8d6", "d1d6", "g7e5", "h2h1", "e5d6"), "Attraction Combination"),
        RawTacticalPuzzle("r3r1k1/ppp2ppp/2nnq3/8/3P4/P1P1P1P1/2Q3BP/R1B1KR2 w Q - 5 17", listOf("d4d5", "e6e5", "d5c6"), "Fork in Englund Gambit Declined"),
        RawTacticalPuzzle("r1b3rk/2q2p2/1n2p2n/p2pP1NP/P1pP1QP1/1pP5/1P3PB1/R3R1K1 w - - 5 27", listOf("f4f6", "g8g7", "f6h6"), "Fork Combination"),
        RawTacticalPuzzle("r2qk3/5p1r/p1p1p3/1p1pP1N1/P1nP2Pn/2P3B1/2P2P2/R1QR2K1 b q - 0 21", listOf("d8g5", "c1g5", "h4f3", "g1g2", "f3g5"), "Attraction Combination"),
        RawTacticalPuzzle("r1bqk2r/pp3ppp/4p3/3pPn2/1b1P1P2/2N5/PP4PP/R1BQKB1R w KQkq - 3 10", listOf("d1a4", "c8d7", "a4b4"), "Fork in French Defense French"),
        RawTacticalPuzzle("2r5/4ppkp/6p1/1p6/1P6/P3B3/1br2PPP/1R1R2K1 w - - 3 23", listOf("b1b2", "c2b2", "e3d4", "f7f6", "d4b2"), "Attraction Combination"),
        RawTacticalPuzzle("4Q3/ppkr1pp1/2p3n1/2PP3p/1P6/4K3/P5P1/8 b - - 0 33", listOf("d7e7", "e8e7", "g6e7"), "Fork Combination"),
        RawTacticalPuzzle("1k5r/n1r3pp/5p2/ppN5/5P2/8/2R3PP/1R4K1 w - - 4 29", listOf("c5a6", "b8b7", "a6c7"), "Fork Combination"),
        RawTacticalPuzzle("5r2/pp1k4/4p1b1/3pP1Np/3P1P1K/8/P7/2R5 w - - 8 43", listOf("c1c7", "d7c7", "g5e6", "c7b6", "e6f8"), "Attraction Combination"),
        RawTacticalPuzzle("8/2Q5/2p2nk1/4K3/2r5/8/8/8 b - - 5 71", listOf("c4e4", "e5d6", "f6e8", "d6d7", "e8c7"), "Fork Combination"),
        RawTacticalPuzzle("2rr2k1/5ppp/p7/1p1nP3/1B1P1PP1/PK5P/2R5/3R4 b - - 5 30", listOf("c8c2", "b3c2", "d5e3", "c2d3", "e3d1"), "Attraction Combination"),
        RawTacticalPuzzle("3qk2r/1p1bbppp/4pn2/1BPp1n2/3P4/4PN2/4QPPP/BN2K2R b Kk - 2 15", listOf("d8a5", "a1c3", "a5b5"), "Fork in Polish Opening Polish"),
        RawTacticalPuzzle("6k1/pp2R3/6pp/8/2r5/P4BbP/1P4P1/6K1 w - - 4 39", listOf("f3d5", "g8f8", "e7f7", "f8e8", "d5c4"), "Fork Combination"),
        RawTacticalPuzzle("1k1r1r2/pp4p1/6q1/2Qp4/5NP1/2P4p/PPN4P/R4R1K b - - 0 30", listOf("g6e4", "h1g1", "f8f4"), "Fork Combination"),
        RawTacticalPuzzle("r2qk2r/pp2ppbp/1n1p2p1/3P4/2n5/2NBBP1P/PP3P2/R2QK2R w KQkq - 0 13", listOf("d3c4", "b6c4", "d1a4", "d8d7", "a4c4"), "Fork in Alekhine Defense Alekhine"),
        RawTacticalPuzzle("r1bq1rk1/pp2bppp/2pp1n2/8/5P2/2N2N2/PBPPB1PP/R2Q1RK1 b - - 6 11", listOf("d8b6", "g1h1", "b6b2", "a1b1", "b2a3"), "Fork in Polish Opening Polish"),
        RawTacticalPuzzle("5r1k/1pN3p1/7p/pP6/P3P3/2PP3P/3BnbPK/R7 b - - 0 27", listOf("f2g3", "h2h1", "g3c7"), "Fork Combination"),
        RawTacticalPuzzle("6k1/5p1p/4p3/4q3/3n4/2Q3P1/PP1N1P1P/6K1 b - - 3 37", listOf("d4e2", "g1f1", "e2c3"), "Fork Combination"),
        RawTacticalPuzzle("r3kbnr/ppp1qppp/2n5/1B1pP3/5B2/4PQ2/PPP2PPP/RN2K2R b KQkq - 2 7", listOf("e7b4", "b1c3", "b4b2"), "Fork in Queens Pawn Game"),
        RawTacticalPuzzle("4r1k1/5pp1/7R/1p6/8/1PP3QP/2q2PP1/6K1 b - - 0 29", listOf("c2c1", "g1h2", "c1h6"), "Fork Combination"),
        RawTacticalPuzzle("1r2r1k1/ppp1q1pp/4b3/4P3/1Q1R1P2/8/P5PP/R1B3K1 b - - 0 19", listOf("c7c5", "b4a3", "c5d4", "a3e7", "e8e7"), "Fork in Italian Game Italian"),
        RawTacticalPuzzle("2rqr1k1/B2b1ppp/5n2/8/7Q/3B4/P1P2PPP/R3R1K1 b - - 4 22", listOf("e8e1", "a1e1", "d8a5", "e1a1", "a5a7"), "Fork Combination"),
        RawTacticalPuzzle("5Q1R/5p1p/1b3qp1/p6k/P2P4/8/1P2rPPP/5RK1 b - - 8 32", listOf("f6f2", "f1f2", "e2e1", "f2f1", "b6d4", "g1h1", "e1f1"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("5r1k/4n2p/1p4p1/pP5Q/P2pB2K/6P1/2P4P/4q3 w - - 0 38", listOf("h5e5", "h8g8", "e4d5", "e7d5", "e5e1"), "Discovered Attack Combination"),
        RawTacticalPuzzle("3r1b1R/1pq3p1/p1k3P1/3p4/1P1Q4/4PP2/P1P5/2K5 w - - 0 27", listOf("h8f8", "d8f8", "d4c5", "c6d7", "c5f8"), "Attraction Combination"),
        RawTacticalPuzzle("6k1/p1Q2p1p/4p1p1/3p4/1r6/7P/q4PP1/5RK1 w - - 0 27", listOf("c7c8", "g8g7", "c8c3", "d5d4", "c3b4"), "Fork Combination"),
        RawTacticalPuzzle("8/1N3k2/6p1/8/2P3P1/pr6/R7/5K2 b - - 2 56", listOf("b3b1", "f1e2", "b1b2", "e2d1", "b2a2"), "Fork Combination"),
        RawTacticalPuzzle("r1b2k1r/pp3ppp/2p5/2bp4/5Pnq/1B2BN2/PPP3PP/RN1QR2K b - - 5 15", listOf("c5e3", "f3h4", "g4f2", "h1g1", "f2d1"), "Discovered Attack in Scotch Game Scotch"),
        RawTacticalPuzzle("1rr3k1/4Qppp/q3p3/p2pn3/3N4/4P2P/5PP1/RR4K1 w - - 0 30", listOf("b1b8", "c8b8", "e7c7", "b8c8", "c7e5"), "Fork Combination"),
        RawTacticalPuzzle("r2q1rk1/p1p2pp1/3bbn1p/4N3/8/1P4P1/PBQPPP1P/RN2K2R b KQ - 2 12", listOf("d6e5", "b2e5", "d8d5", "f2f3", "d5e5"), "Clearance in English Opening English"),
        RawTacticalPuzzle("r1b2rk1/1p2b1p1/pq2p1P1/3pn3/1P1P4/P1N1P3/6P1/R2QK2R w KQ - 0 18", listOf("h1h8", "g8h8", "d1h5", "h8g8", "h5h7"), "Attraction in French Defense French"),
        RawTacticalPuzzle("1r3k2/5p1p/2p1pp2/P2n4/r3N3/P4PK1/2R2P1P/2R5 w - - 10 30", listOf("e4c5", "a4a5", "c5d7", "f8g7", "d7b8"), "Fork Combination"),
        RawTacticalPuzzle("r2qkb1r/pp1nppp1/2p2n1p/3p1b2/2PP4/BP2PN2/P4PPP/RN1QKB1R b KQkq - 0 7", listOf("f5b1", "a1b1", "d8a5", "b3b4", "a5a3"), "Fork in Nimzo-Larsen Attack Nimzo-Larsen"),
        RawTacticalPuzzle("r1b1B2k/pp4pp/2pb4/4q3/8/6P1/PPP4P/R2Q1R1K b - - 0 17", listOf("e5e4", "d1f3", "e4e8"), "Fork in Blackmar-Diemer Gambit Accepted"),
        RawTacticalPuzzle("3r4/5p2/k3p1p1/2B1Pr2/2P3nN/1p4Pp/4QP1P/6K1 b - - 1 34", listOf("f5e5", "e2f1", "e5c5"), "Fork Combination"),
        RawTacticalPuzzle("7k/pb1qn1rn/1p2R2Q/2p2p2/2Pp4/3B4/PP3P1P/4RK2 w - - 2 28", listOf("h6g7", "h8g7", "e6e7", "d7e7", "e1e7"), "Attraction Combination"),
        RawTacticalPuzzle("4R3/1p4k1/1q3bpp/3B4/4Np1P/p4P2/3RK1P1/8 b - - 5 42", listOf("b6b5", "d2d3", "b5e8"), "Fork Combination"),
        RawTacticalPuzzle("2r3nr/p5pp/b3k3/3p1p2/4p3/BP3P2/P1PN2PP/2KR3R w - - 0 19", listOf("f3e4", "f5e4", "d2e4", "d5e4", "d1d6", "e6f7", "d6a6"), "Clearance in Nimzo-Larsen Attack Nimzo-Larsen"),
        RawTacticalPuzzle("r3r1k1/p1p4p/3b4/6qN/4p3/1P1b1Q2/P2P1PP1/B5KR w - - 0 23", listOf("h5f6", "g8f8", "f6h7", "f8e7", "a1f6", "g5f6", "f3f6"), "Deflection Combination"),
        RawTacticalPuzzle("4Rrk1/p6p/1pp2rp1/8/5B1q/4QP1P/P1P2PK1/8 w - - 3 29", listOf("f4g5", "h4g5", "e3g5"), "Fork Combination"),
        RawTacticalPuzzle("4rrk1/ppp2pp1/7p/3n4/3P3q/1P2p2P/PB4P1/R2QRBK1 b - - 3 23", listOf("h4f2", "g1h2", "f2b2"), "Fork Combination"),
        RawTacticalPuzzle("1k1rr3/ppp1q3/8/4nQ1p/8/2P2N1P/PP4P1/2K1R2R b - - 1 25", listOf("e5d3", "f5d3", "d8d3", "e1e7", "e8e7"), "Fork Combination"),
        RawTacticalPuzzle("r5k1/ppp4p/2P3r1/8/4P3/B1P5/P1Q1K3/6N1 b - - 0 31", listOf("g6g2", "e2d3", "a8d8", "d3c4", "g2c2"), "Fork Combination"),
        RawTacticalPuzzle("r2qk2r/ppp2ppp/2n5/3pPb2/1b1n1B2/2NB1N2/PP3PPP/R2QK2R w KQkq - 4 10", listOf("f3d4", "c6d4", "d1a4", "d4c6", "d3f5"), "Deflection in Kings Pawn Game"),
        RawTacticalPuzzle("r1b1kb1r/pppp1ppp/2n1p3/4N3/3P2q1/4n3/PPP1BPPP/RN1Q1RK1 w kq - 0 9", listOf("f2e3", "g4g5", "e5f7", "g5e3", "g1h1"), "Attacking F2 F7 in French Defense French"),
        RawTacticalPuzzle("r7/p4kp1/1p4p1/2qNn3/Q7/4PP2/PP3K2/6R1 w - - 1 26", listOf("a4f4", "f7e6", "f4e4", "a8f8", "g1g6"), "Fork Combination"),
        RawTacticalPuzzle("5r1k/7p/p2pB3/3Pb1p1/4pP1q/1P2B3/P1Q5/2R3K1 b - f3 0 31", listOf("h4g3", "g1h1", "g3f3", "c2g2", "f3e3"), "Fork Combination"),
        RawTacticalPuzzle("r4q1k/2bn2p1/2p3pp/1pP5/1P1B2P1/1Q1P1B1P/5P2/4RRK1 b - - 0 27", listOf("f8f4", "d4g7", "h8h7", "e1e5", "d7e5", "g7e5", "c7e5", "f1e1", "f4f3"), "Fork Combination")
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
            val cleanTheme = "Fork"
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
