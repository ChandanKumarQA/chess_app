package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object ClearanceDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("6k1/p6p/2p3pB/3q4/3b4/P4QP1/1r5P/5R1K w - - 0 28", listOf("f3d5", "c6d5", "f1f8"), "Clearance Combination"),
        RawTacticalPuzzle("4rrk1/ppp1qpp1/7p/6N1/4N1P1/b2Q3P/P4P2/1R4K1 w - - 0 25", listOf("e4f6", "g7f6", "d3h7"), "Clearance Combination"),
        RawTacticalPuzzle("6R1/ppp3P1/4k3/2qp4/8/1P6/PK6/8 w - - 1 45", listOf("g8e8", "e6d7", "g7g8q"), "Clearance Combination"),
        RawTacticalPuzzle("2r1rbk1/1n3pp1/2b4p/1p2p2P/1P2Nn1q/2PQ2N1/1BB2PP1/R3R1K1 w - - 4 25", listOf("e4f6", "g7f6", "d3h7"), "Clearance Combination"),
        RawTacticalPuzzle("8/3KP1k1/8/8/8/8/2p1R3/2r5 b - - 11 59", listOf("c1d1", "d7e6", "c2c1q"), "Clearance Combination"),
        RawTacticalPuzzle("1R6/1P6/4pkp1/5p2/3P4/3KP2p/8/1r6 w - - 0 44", listOf("b8f8", "f6e7", "b7b8q", "b1b8", "f8b8"), "Clearance Combination"),
        RawTacticalPuzzle("8/pp1k2p1/1b6/P2pr3/8/1NP1p1PP/1P4P1/3R3K b - - 0 26", listOf("e3e2", "d1e1", "b6f2", "e1e2", "e5e2"), "Clearance Combination"),
        RawTacticalPuzzle("2k5/1pp2pp1/4pnp1/2q4r/1p6/2r2PQP/B5P1/R2R3K w - - 4 29", listOf("a2e6", "f7e6", "a1a8"), "Clearance Combination"),
        RawTacticalPuzzle("3r1rk1/ppp1qpp1/1nn1b2p/2b1p3/4N3/2PQ1N1P/PPB2PP1/R1B2RK1 w - - 8 15", listOf("e4f6", "g7f6", "d3h7"), "Clearance in Italian Game Italian"),
        RawTacticalPuzzle("2krr3/1pp2p1p/p1b3p1/6qn/2BbP3/2N2P2/PP1BQ1PP/2RR3K b - - 2 19", listOf("h5g3", "h2g3", "g5h5"), "Clearance in Queens Gambit Accepted"),
        RawTacticalPuzzle("3r2k1/5ppp/bQn1p3/8/8/Rp1q1NB1/3N1PPP/1R4K1 b - - 0 25", listOf("d3b1", "d2b1", "d8d1", "f3e1", "d1e1"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("5r1k/p3Nppp/1p1Np3/1Pr1P3/7P/2P2bq1/P5P1/3R2K1 w - - 0 32", listOf("d6f7", "f8f7", "d1d8", "f7f8", "d8f8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("Q1b2rk1/2q2p1p/1p2pbp1/pP6/2P5/P2B1N2/5PPP/3R1RK1 b - - 0 20", listOf("c8b7", "a8a7", "f8a8", "a7a8", "b7a8"), "Clearance Combination"),
        RawTacticalPuzzle("5rk1/p4ppp/1p6/4p3/3PQP2/2PKP2q/r7/2R1R3 w - - 0 33", listOf("e1h1", "a2h2", "h1h2", "h3h2", "c1h1", "h2h1", "e4h1"), "Clearance Combination"),
        RawTacticalPuzzle("r1b3k1/2p3pp/1p1b4/p2pN3/1n1PP3/1P4qr/PB2BRP1/R4QK1 w - - 0 22", listOf("f2f8", "d6f8", "f1f7", "g8h8", "f7f8"), "Clearance Combination"),
        RawTacticalPuzzle("r6r/p3k1bR/1pBp1q2/3PpPpQ/3pP1P1/P7/1P6/R3K3 b Q - 8 27", listOf("h8h7", "h5h7", "a8h8", "h7h8", "g7h8"), "Clearance Combination"),
        RawTacticalPuzzle("r6k/pbqn2p1/5b1p/1pp2Q2/3P4/8/PPP1RPPP/4R1K1 w - - 0 24", listOf("e2e8", "d7f8", "e8a8", "b7a8", "e1e8"), "Clearance Combination"),
        RawTacticalPuzzle("4r1k1/1p3pp1/p5r1/2P4p/4P3/P2P1Nq1/5Q2/R3RK2 b - - 3 32", listOf("g3h3", "f1e2", "g6g2", "e2e3", "g2f2"), "Clearance Combination"),
        RawTacticalPuzzle("r2qk2r/ppp2pp1/2p2n2/7p/3bP1b1/2N3QP/PPP2PP1/R1B1KB1R b KQkq - 0 9", listOf("d4c3", "g3c3", "d8d1"), "Clearance in Russian Game Russian"),
        RawTacticalPuzzle("5rk1/5r1p/6bQ/p2pBp2/2pP3R/2q1P2P/P5PK/8 w - - 2 43", listOf("h6g6", "h7g6", "h4h8"), "Clearance Combination"),
        RawTacticalPuzzle("3r4/ppp1k3/4p3/2b1Pn1p/3N1R2/2P5/PP1B3P/6K1 w - - 1 26", listOf("f4f5", "e6f5", "d2g5", "e7e8", "g5d8"), "Clearance Combination"),
        RawTacticalPuzzle("2r2r2/1p3ppk/p2p1b2/8/3q4/5R2/P5PP/1R1Q3K w - - 0 27", listOf("f3h3", "h7g8", "d1h5", "d4h4", "h3h4", "f6h4", "h5h4"), "Clearance Combination"),
        RawTacticalPuzzle("1r4k1/2n2p2/2P1p1p1/8/1rR1P2P/5P2/8/B1R2K2 b - - 3 41", listOf("b4c4", "c1c4", "b8b1", "f1f2", "b1a1"), "Clearance Combination"),
        RawTacticalPuzzle("R7/P4p2/7p/q3k1n1/5R2/7P/5PK1/8 w - - 3 47", listOf("a8e8", "e5f4", "a7a8q", "a5a8", "e8a8"), "Clearance Combination"),
        RawTacticalPuzzle("8/2R5/7P/8/3K4/pr4P1/1k3P2/8 b - - 0 42", listOf("a3a2", "c7a7", "b3a3", "a7a3", "b2a3"), "Clearance Combination"),
        RawTacticalPuzzle("6k1/3r2p1/Q1b5/1p1qPp2/5B2/8/1P4PP/6RK b - - 0 44", listOf("d5g2", "g1g2", "d7d1"), "Clearance Combination"),
        RawTacticalPuzzle("3Q4/5pk1/pr6/1r1B1KP1/8/8/8/8 b - - 6 61", listOf("b5d5", "d8d5", "b6b5", "d5b5", "a6b5"), "Clearance Combination"),
        RawTacticalPuzzle("2kr1nQ1/ppp2p2/2n1pq2/3p4/2PP4/4P3/PP1N1PPP/R4RK1 b - - 0 15", listOf("f8g6", "g8h7", "d8h8"), "Clearance in Queens Pawn Game"),
        RawTacticalPuzzle("Q7/4ppbk/6pp/5q2/P3n3/4PNBP/1r3PP1/3R2K1 b - - 0 31", listOf("e4g3", "f2g3", "f5c2"), "Clearance Combination"),
        RawTacticalPuzzle("q3r1k1/5R2/1n2p1pp/1N1pN3/3PBPP1/1P2PK1P/3Q4/r7 b - - 0 29", listOf("d5e4", "f3g3", "a1g1", "g3h2", "a8a1"), "Clearance Combination"),
        RawTacticalPuzzle("B4k1r/p6p/3ppnp1/2p5/3bPP2/2NP1PR1/PPPQ1q1P/R1B2b1K b - - 6 17", listOf("f1g2", "g3g2", "f2f1", "g2g1", "f1g1"), "Clearance in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("r3r1k1/pp5p/2pq2p1/3nNQ1b/3P4/4pP1P/PP2P1B1/1K1R3R w - - 0 20", listOf("f5f7", "g8h8", "f7e8", "a8e8", "e5f7", "h8g7", "f7d6"), "Clearance in Grob Opening Grob"),
        RawTacticalPuzzle("4r2r/p1p2kp1/2p2p2/2Qb4/8/2B3Nq/PPP2R1P/5RK1 b - - 11 30", listOf("h3g3", "h2g3", "h8h1"), "Clearance Combination"),
        RawTacticalPuzzle("8/8/4p2p/2R1P2P/3K2P1/2pP4/1b1k4/8 b - - 0 58", listOf("c3c2", "d4e4", "b2c3", "c5c6", "c2c1q"), "Clearance Combination"),
        RawTacticalPuzzle("2r2rk1/1p1b1ppp/p3p1n1/3pP3/P1q5/R4NP1/1B1Q1P1P/4KB1R b K - 7 20", listOf("c4e4", "f1e2", "c8c2"), "Clearance Combination"),
        RawTacticalPuzzle("3q4/Q1R5/1p2pk1p/1P1p4/2pP4/4P3/7r/4K3 w - - 1 47", listOf("c7f7", "f6g5", "f7g7", "g5h4", "a7f7", "d8g5", "g7g5"), "Clearance Combination"),
        RawTacticalPuzzle("r1bq1r1k/1p3ppB/p1n1p3/4P1b1/3N4/2NQ4/PPP3P1/1K5R w - - 3 20", listOf("h7g8", "g5h4", "d3h7"), "Clearance Combination"),
        RawTacticalPuzzle("3q2k1/5Rp1/pnr1pbQp/1p1p4/3P4/7P/5PP1/4R1K1 w - - 1 29", listOf("f7f6", "d8f6", "g6e8", "g8h7", "e8c6"), "Clearance Combination"),
        RawTacticalPuzzle("2q2rk1/4bppp/8/1p2p3/3pP1b1/1B1P1N2/1P1BQPK1/2R5 b - - 3 22", listOf("g4h3", "g2h2", "c8g4", "c1g1", "g4h5"), "Clearance Combination"),
        RawTacticalPuzzle("4r1k1/p1Q2pp1/1p5p/4P3/5r1q/5R2/P3B1PP/R5K1 b - - 0 22", listOf("f4f3", "g2f3", "h4d4", "g1g2", "d4a1"), "Clearance Combination"),
        RawTacticalPuzzle("1r4k1/r5q1/p2p1pP1/4pn2/2p1P2Q/2Pb1BK1/P6R/2R5 w - - 0 36", listOf("e4f5", "d3f5", "f3d5"), "Clearance Combination"),
        RawTacticalPuzzle("r2q1r2/1n4pk/p6p/1ppN1pb1/3n1B2/P2PN2P/1PPQ1PP1/R3R1K1 b - - 1 21", listOf("g5f4", "d5f4", "d8g5", "d2d1", "g5f4"), "Clearance Combination"),
        RawTacticalPuzzle("4rk1r/pQN2ppp/4p3/3pN3/3qn3/1P4P1/P6P/R6K w - - 2 23", listOf("c7e6", "f7e6", "b7f7"), "Clearance Combination"),
        RawTacticalPuzzle("4r1k1/p6p/1p1r1b1P/5K2/1P3N2/PR3P2/6P1/7R b - - 0 39", listOf("e8e5", "f5g4", "e5g5", "g4h3", "f6e5", "g2g4", "e5f4"), "Clearance Combination"),
        RawTacticalPuzzle("3r1q2/5prk/p3pQpp/1p2P3/2p4R/2P2P1P/PPB2P2/6K1 w - - 3 30", listOf("h4h6", "h7g8", "f6h4"), "Clearance Combination"),
        RawTacticalPuzzle("5k2/3n2pp/p4p2/2q1p3/3p4/PQ1P3P/1B3PP1/6K1 w - - 4 30", listOf("a3a4", "f8e7", "b2a3"), "Clearance Combination"),
        RawTacticalPuzzle("r1b3k1/pp4r1/2pqp3/5n1Q/3P1nP1/7R/PPB2P1P/3R2K1 w - - 5 28", listOf("h5e8", "d6f8", "h3h8", "g8h8", "e8f8"), "Attraction Combination"),
        RawTacticalPuzzle("6k1/5p2/2p1bnp1/p3r3/q3Pp2/Br1B4/1PP3QP/K4RR1 w - - 6 29", listOf("c2b3", "a4b3", "d3b1"), "Clearance Combination"),
        RawTacticalPuzzle("r4k1r/5pp1/p3pn1p/1bNp4/3n1P2/3B3N/q1PQ2PP/1R3RK1 w - - 2 19", listOf("b1a1", "a2b2", "f1b1", "d4f3", "g2f3"), "Clearance in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("8/6kp/2b1p3/2ppPpPP/p1p4K/P1P1P3/1P2B3/8 w - - 19 41", listOf("h5h6", "g7g8", "e2h5"), "Clearance Combination"),
        RawTacticalPuzzle("5b1r/B1R3pp/kp2p3/8/Pp4n1/8/2P2P1P/6K1 w - - 0 25", listOf("a7b8", "b6b5", "c7a7", "a6b6", "a4a5", "b6c5", "a7c7"), "Clearance Combination"),
        RawTacticalPuzzle("2kr1b1r/pp1n1ppp/Q1p1pn2/8/4PBq1/5N2/PPP1BPPP/2KR3R w - - 8 13", listOf("a6c6", "b7c6", "e2a6"), "Clearance in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("r1bqk2r/pp3ppp/4p1n1/2bnP3/8/3B1N2/PP2QPPP/RNB2RK1 w kq - 2 11", listOf("d3g6", "f7g6", "e2b5", "c8d7", "b5c5"), "Clearance in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("5rk1/3q2p1/6Qp/3nB3/1p1P2P1/1R5P/5PK1/8 b - - 1 31", listOf("d5f4", "e5f4", "d7d5", "g2h2", "d5b3"), "Clearance Combination"),
        RawTacticalPuzzle("4rbk1/p4ppp/1p6/2p5/4P1N1/1q3PPQ/2r4P/B3R1K1 w - - 0 31", listOf("g4f6", "g7f6", "h3g4", "f8g7", "a1f6"), "Clearance Combination"),
        RawTacticalPuzzle("5rnk/pppq2rp/4P2N/3p1p1Q/5n2/2P1R3/PP3PPP/R5K1 w - - 1 24", listOf("e6d7", "f4h5", "e3e8", "g7d7", "e8f8"), "Clearance Combination"),
        RawTacticalPuzzle("2r2rk1/pQRb1pp1/5n2/6p1/3N4/4P3/P4PPP/6K1 b - - 2 26", listOf("c8c7", "b7c7", "f8c8", "c7c8", "d7c8"), "Clearance Combination"),
        RawTacticalPuzzle("r4rk1/pp3ppp/8/2P1q3/1QNn4/1P2p3/b1P2PPP/2KR1B1R b - - 1 17", listOf("d4e2", "f1e2", "e5a1"), "Clearance in French Defense French"),
        RawTacticalPuzzle("8/4Qpkp/2B2np1/pp3pb1/5q2/2P2P2/PP3PK1/4R3 b - - 6 28", listOf("f6g4", "e7c5", "f4h2", "g2f1", "g5e3"), "Clearance Combination"),
        RawTacticalPuzzle("6k1/6pp/4q2r/8/8/6P1/5P2/2KQR3 b - - 1 54", listOf("e6c8", "d1c2", "h6c6", "c2c6", "c8c6"), "Clearance Combination"),
        RawTacticalPuzzle("r2q3r/3b1pk1/p2p1n2/npp1pPN1/4P1PQ/PBPP3P/2P5/R4RK1 w - - 3 20", listOf("g5e6", "d7e6", "h4g5", "g7f8", "f5e6"), "Clearance Combination"),
        RawTacticalPuzzle("8/4k3/1b2P3/p2K4/1p3P1p/2p4P/P7/2B5 w - - 1 40", listOf("f4f5", "b6d8", "c1g5", "e7e8", "f5f6", "d8f6", "g5f6"), "Clearance Combination"),
        RawTacticalPuzzle("4r1rk/2R1B1bp/2B3p1/8/p4P2/Pb5P/6P1/2K4R b - - 2 32", listOf("e8c8", "e7d6", "c8c7", "d6c7", "g8c8"), "Clearance Combination"),
        RawTacticalPuzzle("r2qk2r/p1p2ppp/2pb1n2/3p2Q1/2P3b1/1PN1P3/PB3PPP/R3KB1R b KQkq - 5 12", listOf("h7h6", "g5g7", "h8h7", "g7h7", "f6h7"), "Clearance in Nimzo-Larsen Attack Nimzo-Larsen"),
        RawTacticalPuzzle("8/3r1r1k/8/PP4pp/3R4/4P3/3p2PP/1R4K1 b - - 1 35", listOf("d7d4", "e3d4", "f7c7", "b1d1", "c7c1", "g1f2", "c1d1"), "Clearance Combination"),
        RawTacticalPuzzle("6k1/5pb1/p1p1p1pp/P1B2q2/2bP1Q2/1rP1RN1P/5PP1/6K1 w - - 1 24", listOf("f4f5", "g6f5", "f3d2", "f5f4", "e3f3"), "Clearance Combination"),
        RawTacticalPuzzle("r4rk1/pp3ppp/2pp1q2/4p1B1/2BnP3/P2P1b2/1PP2P2/R1Q2RK1 w - - 2 16", listOf("g5f6", "d4e2", "g1h2", "e2c1", "f1g1"), "Clearance in Philidor Defense Philidor"),
        RawTacticalPuzzle("2k2r1r/pp2q3/2p1b3/7p/1P1pPBn1/3B4/P2Q2PP/1RR4K b - - 5 22", listOf("f8f4", "d2f4", "h8f8", "f4f8", "e7f8"), "Clearance Combination"),
        RawTacticalPuzzle("r4rk1/ppbb2pp/2p1p3/2Pp4/3Pp3/2N1P3/PPQ1B1PP/R1B1qRK1 b - - 5 16", listOf("f8f1", "e2f1", "a8f8", "c2e2", "f8f1", "e2f1", "c7h2", "g1h2", "e1f1"), "Clearance in Queens Gambit Declined"),
        RawTacticalPuzzle("5rk1/p1r1b3/1p3pp1/3Rp3/n1P4p/4BB1P/P4PP1/3R2K1 w - - 2 34", listOf("d5d7", "f8c8", "f3b7"), "Clearance Combination"),
        RawTacticalPuzzle("r2q1rk1/ppp2p1p/6p1/2pb2b1/5B1n/2PP1PQ1/PPB3PP/RN2R1K1 b - - 2 16", listOf("h4f3", "g2f3", "g5h4", "b1d2", "h4g3"), "Clearance in Ruy Lopez Ruy"),
        RawTacticalPuzzle("4r1k1/pp3rb1/3pq3/4n1PR/5p2/3B1P2/PP1Q1B2/1K5R b - - 1 29", listOf("e5d3", "d2d3", "e6e2", "d3b3", "e2f2"), "Clearance Combination"),
        RawTacticalPuzzle("r2nqr1k/1Bp4p/p4b2/2N3p1/1P1Pp3/P3Pp1P/1Q3P1P/2R2RK1 b - - 0 24", listOf("d8b7", "c5b7", "e8c8", "g1h1", "c8b7"), "Clearance Combination"),
        RawTacticalPuzzle("rn3rk1/2p2ppp/4p3/pp1b1qB1/1n2P2P/3P1NP1/PP3PB1/1QKR3R b - - 0 18", listOf("d5e4", "d3e4", "f5c5", "c1d2", "c5f2"), "Clearance in Kings Indian Attack"),
        RawTacticalPuzzle("1n3rk1/r3bppp/p2q1n2/1pp1N3/3pPB2/P2P2NP/RPP2PP1/3Q1RK1 b - - 2 15", listOf("d6e6", "b2b3", "e7d6"), "Clearance in Van Geet Opening"),
        RawTacticalPuzzle("1r3r2/5p2/p3pQbk/3pP3/2nq4/6P1/P1B2PP1/2R1R1K1 w - - 4 31", listOf("g1h2", "d4g4", "e1h1", "g4h5", "h2g1"), "Clearance Combination"),
        RawTacticalPuzzle("r3r3/pbp2k2/1pq3pQ/7n/2P5/P1P2P1P/6P1/2BR1RK1 w - - 4 25", listOf("h6h7", "h5g7", "c1h6", "e8g8", "d1d4"), "Clearance Combination"),
        RawTacticalPuzzle("1k1nRb1r/ppp2Npp/3q4/8/2P3Br/3P4/PP3P2/R1BQ2K1 b - - 0 18", listOf("d6h2", "g1f1", "f8c5"), "Clearance in Russian Game Russian"),
        RawTacticalPuzzle("3r3k/pp4pp/8/1Q1pN3/P2P1PPq/2r4P/4R1K1/8 w - - 5 46", listOf("e5f3", "h4f6", "e2e8", "f6f8", "e8f8"), "Clearance Combination"),
        RawTacticalPuzzle("8/pQ3pkp/4r3/2BNb1p1/8/1R4q1/P5P1/5K2 b - - 2 34", listOf("g3g4", "d5e3", "e5g3"), "Clearance Combination"),
        RawTacticalPuzzle("r1bq1rk1/6pp/p2p1bp1/1p6/4Np2/2P2Q2/PP4PP/RN2R1K1 w - - 0 21", listOf("e4f6", "g7f6", "f3d5", "g8g7", "d5a8"), "Clearance Combination"),
        RawTacticalPuzzle("8/8/8/6pp/6k1/4KR2/8/3n4 w - - 1 60", listOf("e3e2", "h5h4", "f3d3", "d1c3", "d3c3"), "Clearance Combination"),
        RawTacticalPuzzle("r4r1k/ppp4p/2n5/2b5/8/6N1/PPPP2PP/R1B2R1K b - - 8 18", listOf("f8f1", "g3f1", "a8f8", "f1g3", "f8e8"), "Clearance in Kings Gambit Kings"),
        RawTacticalPuzzle("r2r4/pp3k2/2p1p2Q/4qp1R/2P5/5BPP/1P3P2/6K1 w - - 2 27", listOf("h5g5", "d8h8", "f3h5", "f7e7", "g5g7", "e5g7", "h6g7"), "Clearance Combination"),
        RawTacticalPuzzle("r2r1bk1/pR4pp/2p2p2/5P2/4p3/2qB2QP/P1P3P1/1R5K w - - 0 23", listOf("b7g7", "f8g7", "b1b7", "c3a1", "d3f1"), "Clearance Combination"),
        RawTacticalPuzzle("5rk1/2p1R1pp/p4q2/3N4/3P4/4N1Pb/PP3r1P/R2Q2K1 b - - 4 23", listOf("f2g2", "g1h1", "f6f2", "d5f6", "g7f6", "d1g1", "g2g1"), "Clearance Combination"),
        RawTacticalPuzzle("2r1kbr1/p4qp1/1p2p2p/3p1p1Q/3B4/2P4P/P3RPP1/R5K1 w - - 2 25", listOf("e2e6", "f8e7", "h5e2"), "Clearance Combination"),
        RawTacticalPuzzle("6k1/p3b2p/1p1pP3/2P3P1/2np3B/P6P/3Q3K/8 b - - 0 38", listOf("c4d2", "c5c6", "d6d5", "g5g6", "e7d6"), "Clearance Combination"),
        RawTacticalPuzzle("2r5/7p/p2Rp1pk/5P2/P3P3/6P1/1qr4P/3RQ1K1 b - - 0 33", listOf("c2g2", "g1f1", "c8c2", "d1d2", "c2d2"), "Clearance Combination"),
        RawTacticalPuzzle("r6k/1R4Rp/p7/3P2B1/2p1p3/1KP4P/P1P2b2/5q2 w - - 0 31", listOf("b3a4", "a8a7", "g5f6", "a7b7", "g7g1"), "Clearance Combination"),
        RawTacticalPuzzle("r2q1rk1/pp2pp2/3p3p/6p1/2P1P3/P2Q1PP1/1P3P2/nB2K2R w K - 0 21", listOf("e4e5", "f7f5", "e5f6", "f8f6", "d3h7", "g8f8", "b1g6", "f6g6", "h7g6"), "Clearance Combination"),
        RawTacticalPuzzle("3q2k1/1Q4pp/3r4/P7/8/1P6/3p1PPP/3R2K1 b - - 0 35", listOf("d6e6", "b7b4", "d8d4", "b4b8", "g8f7", "b8b7", "f7g6", "g2g3", "e6e1"), "Clearance Combination"),
        RawTacticalPuzzle("8/p4rpk/7p/3QB1n1/3P3P/8/PP2PqR1/2K5 b - - 2 34", listOf("f2e3", "c1c2", "f7f1", "d5e4", "e3e4"), "Clearance Combination"),
        RawTacticalPuzzle("8/N1P1kp2/1p3p2/2b5/4P1B1/P5r1/1P3r2/R2K4 w - - 0 33", listOf("a7c8", "e7f8", "g4e2", "f8g7", "c8b6", "c5b6", "c7c8q", "g3g1", "d1d2", "g1a1", "c8g4"), "Clearance Combination"),
        RawTacticalPuzzle("8/5pk1/1PK5/R5pp/8/8/8/2r5 w - - 2 41", listOf("a5c5", "c1e1", "b6b7", "e1e6", "c6b5", "e6e8", "c5c8"), "Clearance Combination"),
        RawTacticalPuzzle("1k6/1p3p2/p1p1p3/2P1b3/PP6/1KB5/4q3/1R1R4 w - - 2 39", listOf("d1d8", "b8a7", "b1e1", "e2e1", "c3e1"), "Clearance Combination"),
        RawTacticalPuzzle("2b2rk1/1pp3b1/n2p2B1/3P2N1/1qP2p2/7P/5PP1/1Q3RK1 w - - 0 25", listOf("g6h7", "g8h8", "b1g6", "b4c3", "g6h5"), "Clearance Combination"),
        RawTacticalPuzzle("8/8/3k1P2/8/1p2PK2/1Br5/8/8 w - - 1 54", listOf("e4e5", "d6d7", "e5e6", "d7d6", "e6e7", "c3c8", "b3f7"), "Clearance Combination"),
        RawTacticalPuzzle("2r1r1k1/5pp1/2p2n1p/2Q5/2PPp3/2P1B2q/4BP2/1R3RK1 b - - 1 23", listOf("e8e6", "e3f4", "e4e3", "f2e3", "f6e4", "c5h5", "e6g6", "h5g6", "f7g6"), "Clearance Combination"),
        RawTacticalPuzzle("4r3/p2RP3/2kP4/7p/8/5pr1/5R2/1K6 w - - 1 53", listOf("d7d8", "g3g1", "b1a2", "g1g2", "f2g2", "f3g2", "d8e8", "g2g1q", "e8c8", "c6d6", "e7e8q"), "Clearance Combination")
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
            val cleanTheme = "Clearance"
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
