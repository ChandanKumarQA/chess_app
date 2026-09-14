package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object AttractionDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("r5r1/ppp2p1k/2np1P1q/6p1/4PNP1/1N6/PPP5/2KR3Q w - - 0 30", listOf("h1h6", "h7h6", "d1h1"), "Attraction Combination"),
        RawTacticalPuzzle("r3k2r/pp5p/2q1p1p1/2bN4/P5Pn/8/RPP4P/2BQR2K w kq - 1 20", listOf("e1e6", "c6e6", "d5c7", "e8f7", "c7e6"), "Attraction Combination"),
        RawTacticalPuzzle("2k3rr/1pp1nB2/p2qp2p/3p3Q/8/2N1P2P/PPPP2Rb/R1B2K2 b - - 1 24", listOf("g8g2", "f1g2", "d6g3"), "Attraction Combination"),
        RawTacticalPuzzle("5rk1/1p3rpp/p5p1/2pBq3/2Pb4/1Q4P1/PP3PP1/4RRK1 b - - 11 26", listOf("d4f2", "f1f2", "e5e1", "g1h2", "e1f2"), "Attraction Combination"),
        RawTacticalPuzzle("2r3k1/5ppp/p3nb2/3q4/1Q6/P3B2P/2R2PP1/2R3K1 b - - 4 30", listOf("c8c2", "c1c2", "d5d1", "g1h2", "d1c2"), "Attraction Combination"),
        RawTacticalPuzzle("5rk1/5ppp/rn6/q1Q3P1/1p5P/1P6/P3R3/1K1R4 w - - 3 28", listOf("c5f8", "g8f8", "d1d8"), "Attraction Combination"),
        RawTacticalPuzzle("rn2kbnr/pp6/2p2p2/4P3/4P1pq/2N3N1/PPPB1KB1/R2Q1R2 b kq - 2 17", listOf("f8c5", "d2e3", "c5e3", "f2e3", "h4g3"), "Attraction in Pirc Defense Pirc"),
        RawTacticalPuzzle("8/8/8/P1k3p1/3p4/3K1p1P/2N5/8 b - - 3 53", listOf("f3f2", "d3e2", "d4d3", "e2d3", "f2f1q"), "Attraction Combination"),
        RawTacticalPuzzle("5rk1/n2P3p/1p2p1pQ/pP6/q3p1p1/8/5PPP/5RK1 w - - 0 35", listOf("h6f8", "g8f8", "d7d8q"), "Attraction Combination"),
        RawTacticalPuzzle("3r2r1/2p4k/1pq2pp1/p3R2p/2P5/1P2QP2/P5P1/4R1K1 w - - 0 37", listOf("e5e7", "g8g7", "e7g7", "h7g7", "e3e7", "g7h6", "e7d8"), "Attraction Combination"),
        RawTacticalPuzzle("1r3r2/Q1qk3p/2pp1npb/2p2P2/8/2N5/PP3PPP/4RRK1 w - - 1 20", listOf("e1e7", "d7e7", "a7c7"), "Attraction Combination"),
        RawTacticalPuzzle("8/6p1/5bk1/N6p/8/1P6/6PP/3rRK2 b - - 2 45", listOf("d1e1", "f1e1", "f6c3", "e1d1", "c3a5"), "Attraction Combination"),
        RawTacticalPuzzle("3r2k1/2R1qpp1/7p/8/5Q2/1PP5/5PPP/3r1RK1 b - - 0 26", listOf("d1f1", "g1f1", "d8d1"), "Attraction Combination"),
        RawTacticalPuzzle("8/8/5k2/1p3pp1/p1p2P1p/P1P1KP1P/1P6/8 w - - 0 47", listOf("f4g5", "f6g5", "f3f4", "g5g6", "e3d4"), "Attraction Combination"),
        RawTacticalPuzzle("1r4r1/2q2ppk/2pb1n2/1p2pN1p/4P3/4Q1RP/PP3PP1/R5K1 w - - 0 26", listOf("g3g7", "g8g7", "e3h6", "h7g8", "h6g7"), "Attraction Combination"),
        RawTacticalPuzzle("4r1k1/5pp1/pp5p/2nRB3/8/2P2P2/PP3KPP/8 b - - 1 30", listOf("e8e5", "d5e5", "c5d3", "f2e3", "d3e5"), "Attraction Combination"),
        RawTacticalPuzzle("4r1k1/pp2rp1p/5Rp1/1q6/P7/1P4Q1/5PPP/5RK1 b - - 0 22", listOf("b5f1", "g1f1", "e7e1"), "Attraction Combination"),
        RawTacticalPuzzle("3b1r2/1r1P2pk/7p/4R3/P6P/4RpP1/5P2/6K1 w - - 4 45", listOf("e5e8", "f8g8", "e8g8", "h7g8", "e3e8", "g8f7", "e8d8"), "Attraction Combination"),
        RawTacticalPuzzle("3r3r/4kp2/4p1p1/4P3/2Q2P2/4q3/P5PP/2R2R1K b - - 2 27", listOf("h8h2", "h1h2", "d8h8"), "Attraction Combination"),
        RawTacticalPuzzle("8/1R2bk2/1Q2qnp1/pp6/2p2P2/P7/6PP/5RK1 b - - 2 32", listOf("e6b6", "b7b6", "e7c5", "g1h1", "c5b6"), "Attraction Combination"),
        RawTacticalPuzzle("5rk1/pR3ppp/5q2/2p1p3/2P5/2QPP1Pb/P4PBP/6K1 b - - 2 23", listOf("h3g2", "g1g2", "f6c6", "e3e4", "c6b7"), "Attraction Combination"),
        RawTacticalPuzzle("5k2/6pp/p5p1/1r2N3/1P5n/P1P2P2/5P1P/4R1K1 b - - 0 35", listOf("b5e5", "e1e5", "h4f3", "g1f1", "f3e5"), "Attraction Combination"),
        RawTacticalPuzzle("6rk/1R3Qp1/6Pp/p6P/8/2Pq1p2/K7/8 w - - 0 51", listOf("f7g8", "h8g8", "b7b8", "d3d8", "b8d8"), "Attraction Combination"),
        RawTacticalPuzzle("r6k/3nq1pp/3pBp2/2p1nP2/1p2R2Q/1P5P/r5P1/2B1R1K1 w - - 7 32", listOf("h4h7", "h8h7", "e4h4"), "Attraction Combination"),
        RawTacticalPuzzle("1k2r3/1p3ppp/3p4/2n5/1rPN4/1P3P2/P5PP/2KRR3 b - - 2 23", listOf("e8e1", "d1e1", "c5d3", "c1d2", "d3e1"), "Attraction Combination"),
        RawTacticalPuzzle("2r5/kpQ2p2/r2R3p/4pb2/2N1q3/8/PP3P1P/K1R5 w - - 4 28", listOf("d6a6", "a7a6", "c7a5"), "Attraction Combination"),
        RawTacticalPuzzle("r5rk/7p/1np1Pp2/1p1p1N2/3P4/q1P4R/5PPP/q1RQ2K1 w - - 2 36", listOf("h3h7", "h8h7", "d1h5"), "Attraction Combination"),
        RawTacticalPuzzle("8/5p1k/6p1/3pn2p/Pq1N4/1P4PP/5P1K/3Q4 b - - 2 31", listOf("b4d4", "d1d4", "e5f3", "h2h1", "f3d4"), "Attraction Combination"),
        RawTacticalPuzzle("r4rk1/BRp2pp1/3b3p/2P5/5P2/6P1/P6P/3R2K1 b - - 0 23", listOf("a8a7", "b7a7", "d6c5", "g1f1", "c5a7"), "Attraction Combination"),
        RawTacticalPuzzle("3R1rk1/1b3pp1/4pb1p/p1r5/Pp3B2/1p2PN2/1P3PPP/R5K1 w - - 0 24", listOf("d8f8", "g8f8", "f4d6", "f6e7", "d6c5"), "Attraction Combination"),
        RawTacticalPuzzle("8/1pQ2rk1/3p3R/1P3pq1/Pn1P4/1r2P1P1/5PK1/8 w - - 2 43", listOf("h6h7", "g7h7", "c7f7", "g5g7", "f7b3"), "Attraction Combination"),
        RawTacticalPuzzle("1k1r1b1r/npp2pp1/5n1p/1N6/8/1P3N2/1P2qPPP/R2R2K1 w - - 0 17", listOf("d1d8", "a7c8", "a1a8", "b8a8", "d8c8"), "Attraction in Danish Gambit Danish"),
        RawTacticalPuzzle("2r5/1p4pp/p3p1k1/n1N5/P7/4P3/1P1K2PP/2R5 b - - 8 26", listOf("c8c5", "c1c5", "a5b3", "d2c3", "b3c5"), "Attraction Combination"),
        RawTacticalPuzzle("r4r1k/pppbq1pp/1b3p2/3Bp2R/3P4/2P3P1/PP3P1P/R1BQ2K1 w - - 1 17", listOf("h5h7", "h8h7", "d1h5"), "Attraction in French Defense French"),
        RawTacticalPuzzle("3rk2r/pp2qpp1/2b1pn2/6Np/2p4P/2N1RP2/PPPQ2P1/2KR4 w k - 2 18", listOf("d2d8", "e7d8", "d1d8", "e8d8", "g5f7"), "Attraction in Zukertort Opening Zukertort"),
        RawTacticalPuzzle("1r6/RP4k1/2Q2bp1/2p1p1p1/2Pp2q1/3P1pP1/1P3P1K/7R b - - 4 36", listOf("b8h8", "h2g1", "h8h1", "g1h1", "g4h3", "h1g1", "h3g2"), "Attraction Combination"),
        RawTacticalPuzzle("1r4k1/5p1p/4p1p1/p1p1P3/2Pn1P2/PPR3P1/5Q1q/1R3K2 b - - 5 30", listOf("h2h1", "f2g1", "h1g1", "f1g1", "d4e2", "g1h2", "e2c3"), "Attraction Combination"),
        RawTacticalPuzzle("6k1/2p1r3/1p1p2p1/p2P2N1/P1P3PQ/3bq2P/5R1K/8 w - - 1 41", listOf("f2f8", "g8f8", "h4h8"), "Attraction Combination"),
        RawTacticalPuzzle("4R3/r1pk1p2/1pbp4/6qp/3N4/7P/PPP5/1K2R3 w - - 0 33", listOf("e1e7", "g5e7", "e8e7", "d7e7", "d4c6", "e7d7", "c6a7"), "Attraction Combination"),
        RawTacticalPuzzle("2kr4/2p2pp1/8/1R2PP1p/6n1/8/1Q4PP/3q2BK b - - 2 28", listOf("d1g1", "h1g1", "d8d1"), "Attraction Combination"),
        RawTacticalPuzzle("2r4r/3b1k2/2p2N2/1p1q2p1/1P1P4/P1QBPpP1/1B5P/2R3K1 b - - 0 41", listOf("f3f2", "g1f2", "h8h2"), "Attraction Combination"),
        RawTacticalPuzzle("1n3r1k/p5pp/8/7Q/4P3/2NPB3/PPP3qP/R2KRr2 b - - 4 21", listOf("f1e1", "d1e1", "f8f1"), "Attraction Combination"),
        RawTacticalPuzzle("6k1/1p4pp/pqp5/3pN1p1/1P1Pn1P1/4P2P/7B/5RK1 w - - 1 32", listOf("f1f8", "g8f8", "e5d7", "f8e7", "d7b6"), "Attraction Combination"),
        RawTacticalPuzzle("2kr1Q2/pR4pp/2p1r3/N2n4/3q4/6P1/P4PKP/8 w - - 12 32", listOf("b7b8", "c8b8", "f8d8"), "Attraction Combination"),
        RawTacticalPuzzle("2R2b2/5k1p/6p1/3p1p2/1r1Bp3/4P1P1/5PKP/8 w - - 2 37", listOf("c8f8", "f7f8", "d4c5", "f8f7", "c5b4"), "Attraction Combination"),
        RawTacticalPuzzle("1kr4r/pp4pp/3b1p2/1N6/P2Pqp2/2Q2N1P/5PP1/2R3K1 w - - 4 25", listOf("c3c8", "h8c8", "c1c8", "b8c8", "b5d6", "c8d7", "d6e4"), "Attraction Combination"),
        RawTacticalPuzzle("r3rk2/ppp1n1pQ/6P1/4p1P1/4Ppq1/1PP5/P4P2/RNB1K3 w Q - 1 21", listOf("h7h8", "e7g8", "c1a3", "e8e7", "a3e7", "f8e7", "h8g7"), "Attraction Combination"),
        RawTacticalPuzzle("4r3/K1B4R/P1kP2p1/8/8/r5P1/7P/8 b - - 0 41", listOf("a3a6", "a7a6", "e8a8"), "Attraction Combination"),
        RawTacticalPuzzle("2kr4/1rp1Q3/5p1p/4bN2/2b1p1P1/4P2P/4qP2/R1R3K1 w - - 6 37", listOf("a1a8", "b7b8", "a8b8", "c8b8", "e7d8"), "Attraction Combination"),
        RawTacticalPuzzle("2r3k1/6pp/2b5/3R4/3P4/2R1P1BP/5P1K/1r6 b - - 4 34", listOf("b1h1", "h2h1", "c6d5", "h1h2", "c8c3"), "Attraction Combination"),
        RawTacticalPuzzle("8/1p1b2pk/1p5r/8/3B1K2/1P5P/P2R4/8 w - - 3 40", listOf("d4g7", "h7g7", "d2d7"), "Attraction Combination"),
        RawTacticalPuzzle("2r1r1k1/1b3p1p/p2q1Bp1/3P2P1/1p4R1/3p3Q/PPP4P/1K6 w - - 0 24", listOf("h3h7", "g8h7", "g4h4", "h7g8", "h4h8"), "Attraction Combination"),
        RawTacticalPuzzle("8/pp2Q1pk/1qp3p1/3p2Pn/8/1P6/PBPN1r1P/3R3K b - - 5 27", listOf("f2h2", "h1h2", "b6f2", "h2h3", "f2g3"), "Attraction Combination"),
        RawTacticalPuzzle("3rkb1r/p3pppp/5q2/3b4/8/3Q1P2/PPP3PP/2KR1B1R w k - 0 14", listOf("d3b5", "d5c6", "d1d8", "e8d8", "b5b8"), "Attraction in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("5R2/p5pk/b3p2p/1q1pP1P1/8/BP2Q2P/2r5/6K1 w - - 1 35", listOf("g5g6", "h7g6", "e3g3", "g6h7", "f8f7"), "Attraction Combination"),
        RawTacticalPuzzle("6k1/pp4p1/7p/1PPp4/P2b2rq/5QN1/6PP/4R2K b - - 0 30", listOf("h4h2", "h1h2", "g4h4"), "Attraction Combination"),
        RawTacticalPuzzle("6k1/5p1p/1r3p2/8/3P4/3N2P1/p4P1P/RrR3K1 b - - 4 31", listOf("b1a1", "c1a1", "b6b1", "g1g2", "b1a1"), "Attraction Combination"),
        RawTacticalPuzzle("r5k1/pQp2ppp/5q2/2P5/7r/P3B3/1P2nPPP/R4R1K b - - 0 19", listOf("h4h2", "h1h2", "f6h4"), "Anastasia Mate Combination"),
        RawTacticalPuzzle("5k2/2Q2p1p/4pnp1/8/P1KP4/2N2q2/2P4P/8 b - - 0 23", listOf("f3c3", "c4c3", "f6d5", "c3d2", "d5c7"), "Attraction Combination"),
        RawTacticalPuzzle("r1bq1r1k/2p1b2p/p1np2p1/1p4P1/n3Pp2/P1PP1Q2/BP3P2/RNB1K2R w KQ - 0 16", listOf("h1h7", "h8h7", "f3h1", "c8h3", "h1h3", "h7g7", "h3h6"), "Attraction in Bishops Opening Bishops"),
        RawTacticalPuzzle("r3n2k/2q1b2p/3p2p1/2n2N2/2B1P3/8/1Pp2B2/1NKR3R w - - 2 30", listOf("h1h7", "h8h7", "d1h1", "e7h4", "h1h4"), "Attraction Combination"),
        RawTacticalPuzzle("2r1r3/2R2pkp/4p1p1/pR4P1/5P2/B1b5/P6P/5K2 w - - 5 29", listOf("c7c3", "c8c3", "a3b2", "g7f8", "b2c3"), "Attraction Combination"),
        RawTacticalPuzzle("4R3/6Q1/2p5/7p/1P4nq/3P3P/2Pk1rP1/5RK1 b - - 2 35", listOf("f2f1", "g1f1", "h4f2"), "Attraction Combination"),
        RawTacticalPuzzle("2kr4/ppp2pr1/4pp2/2n5/2P1q3/P4Q2/5PPP/R2R2K1 w - - 0 21", listOf("d1d8", "c8d8", "f3f6", "d8e8", "f6g7"), "Attraction Combination"),
        RawTacticalPuzzle("r5k1/p4pn1/4pBp1/1p1r4/3P1Q1R/2q4P/6P1/6K1 w - - 4 36", listOf("h4h8", "g8h8", "f4h6", "h8g8", "h6g7"), "Attraction Combination"),
        RawTacticalPuzzle("3r1rk1/ppp2pp1/1b6/7p/4P1R1/2Pq3P/P5P1/R1Q1B2K w - - 0 24", listOf("g4g7", "g8g7", "c1g5"), "Attraction Combination"),
        RawTacticalPuzzle("1r3rk1/p1p1bppp/3q1n2/2n5/2Q5/2N2NP1/PP1PPP1P/R1B1K2R b KQ - 6 13", listOf("b8b4", "c4b4", "c5d3", "e2d3", "d6b4"), "Attraction in Hungarian Opening Hungarian"),
        RawTacticalPuzzle("rn1b1rk1/6p1/3p4/2pPpp1Q/Np5q/1P1B4/P4P2/1K1R2R1 w - - 0 24", listOf("g1g7", "g8g7", "d1g1", "h4g5", "g1g5"), "Attraction Combination"),
        RawTacticalPuzzle("8/8/1p2p1kp/1P1n2p1/8/2p5/5RPP/5K2 b - - 3 34", listOf("c3c2", "f2c2", "d5e3", "f1f2", "e3c2"), "Attraction Combination"),
        RawTacticalPuzzle("q4r1k/5pbp/2Np1np1/3b2B1/pQ6/5B2/P4PPP/4R1K1 w - - 0 24", listOf("g5f6", "d5c6", "f6g7", "h8g7", "b4c3"), "Attraction Combination"),
        RawTacticalPuzzle("7Q/5R2/3k2p1/4p3/4P1P1/8/p4p1K/r7 b - - 0 46", listOf("a1h1", "h2h1", "a2a1q", "h1g2", "a1g1"), "Attraction Combination"),
        RawTacticalPuzzle("r2q1rk1/pbppnppp/8/2b1p3/8/2PB3N/2PP1PPP/R1BQK2R w KQ - 1 10", listOf("d3h7", "g8h7", "d1h5", "h7g8", "h3g5"), "Attraction in Kings Pawn Game"),
        RawTacticalPuzzle("1r4k1/pp2R1p1/3p3p/3P1p2/2nN1q2/1N5P/PP2R1P1/7K w - - 2 30", listOf("e7g7", "g8g7", "d4e6", "g7f6", "e6f4"), "Attraction Combination"),
        RawTacticalPuzzle("2r3k1/pp3ppp/5n2/3p4/3P4/1P3QP1/PB3RBK/4q3 b - - 4 23", listOf("e1f2", "f3f2", "f6g4", "h2g1", "g4f2"), "Attraction Combination"),
        RawTacticalPuzzle("r7/1pp4k/p2p3p/3Ppq2/2P2bb1/1N2R3/PPQ5/1K1N2R1 b - - 9 27", listOf("f5c2", "b1c2", "g4d1", "g1d1", "f4e3"), "Attraction Combination"),
        RawTacticalPuzzle("8/6bk/6p1/3QP1Bp/3N4/Pp6/1q4PP/2r3RK b - - 0 40", listOf("c1g1", "h1g1", "b2a1", "g1f2", "b3b2"), "Attraction Combination"),
        RawTacticalPuzzle("2r2rk1/5p1p/3p3P/p2Npbq1/1p2Q3/8/PPP5/1K1R3R w - - 2 24", listOf("e4f5", "g5f5", "d5e7", "g8h8", "e7f5"), "Attraction Combination"),
        RawTacticalPuzzle("1r2r1k1/3nbp1p/p1q1p1p1/3pR2Q/3B4/3B4/PPP2PPP/R5K1 w - - 0 20", listOf("h5h7", "g8h7", "e5h5", "h7g8", "h5h8"), "Attraction in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("1nkr1qnr/1pp5/p2b1p2/3N2p1/2B1P1p1/4B3/PPPNQPP1/R3R1K1 b - - 1 15", listOf("h8h1", "g1h1", "f8h6", "h1g1", "h6h2", "g1f1", "h2h1"), "Attraction in Philidor Defense Philidor"),
        RawTacticalPuzzle("4r1k1/2R3p1/p3r2p/1p2P3/1q6/1P4P1/P3Q2P/4R1K1 b - - 0 31", listOf("e6e5", "e2e5", "e8e5", "e1e5", "b4d4", "g1f1", "d4e5"), "Attraction Combination"),
        RawTacticalPuzzle("2kr2r1/3q1p2/pp1B1n1p/2pP4/2Pp4/PQ1P3P/4B1PK/R4R2 b - - 0 21", listOf("d7d6", "h2g1", "g8g2", "g1g2", "d8g8", "e2g4", "f6g4"), "Attraction Combination"),
        RawTacticalPuzzle("r4rk1/pp6/2p1p1pQ/3pP2n/3P1q2/2PB1p2/PP5R/7K w - - 0 24", listOf("h6g6", "h5g7", "h2h8", "g8h8", "g6h7"), "Attraction Combination"),
        RawTacticalPuzzle("1r1r2k1/5p1p/p4qp1/3bnNR1/4p2Q/8/PPP1B2P/1K1R4 w - - 3 28", listOf("g5g6", "f6g6", "f5e7", "g8g7", "e7g6"), "Attraction Combination"),
        RawTacticalPuzzle("5r1k/1N4pp/p7/4P3/3p4/3Q1nPb/PP3P1P/4R2K b - - 2 31", listOf("h3g2", "h1g2", "f3e1", "g2f1", "e1d3"), "Attraction Combination"),
        RawTacticalPuzzle("8/7R/3k4/2pnp3/P1B5/3KP3/1r6/8 b - - 2 43", listOf("e5e4", "d3e4", "d5f6"), "Attraction Combination"),
        RawTacticalPuzzle("2r3k1/1p3p1p/p4Bp1/3bPp2/1q1P3Q/6R1/Pr4PP/6RK w - - 4 28", listOf("h4h7", "g8h7", "g3h3", "h7g8", "h3h8"), "Attraction Combination"),
        RawTacticalPuzzle("4r1k1/8/8/p5pR/2PnB3/3P2p1/P7/6K1 b - - 2 38", listOf("d4e2", "g1f1", "g3g2", "f1g2", "e2f4", "g2f3", "f4h5"), "Attraction Combination"),
        RawTacticalPuzzle("3r2k1/p1p3pp/2Q5/8/1q6/3r3P/PB4P1/2R4K w - - 1 32", listOf("c6e6", "g8h8", "b2g7", "h8g7", "c1c7", "d3d7", "c7d7", "d8d7", "e6d7"), "Attraction Combination"),
        RawTacticalPuzzle("2r4r/pp2q1k1/2pp1bp1/5p2/2P2N2/1P4PP/P1Q3K1/3R1R2 w - - 1 27", listOf("f4g6", "g7g6", "c2f5"), "Attraction Combination"),
        RawTacticalPuzzle("r1b2rk1/4Qppp/p2p4/1p1P4/5R2/3q4/P5PP/5R1K w - - 0 23", listOf("e7f8", "g8f8", "f4f7", "f8e8", "f7f8", "e8d7", "f1f7"), "Attraction Combination"),
        RawTacticalPuzzle("2rn1rk1/5pp1/5q1p/1p3P1Q/pb1pB2P/3P4/1BP2P2/2KR2R1 w - - 0 25", listOf("g1g7", "g8g7", "d1g1", "g7h7", "b2d4"), "Attraction Combination"),
        RawTacticalPuzzle("5r2/2q1Qpk1/6p1/1p1p1bNn/3P3P/pPr2P2/P3N3/K2R2R1 w - - 4 28", listOf("g5e6", "f5e6", "g1g6", "g7h7", "g6h6", "h7h6", "e7g5", "h6h7", "g5h5"), "Attraction Combination"),
        RawTacticalPuzzle("r1b4r/ppk2ppp/2p5/6B1/2P5/2n3P1/P1P1B2P/2KR3R w - - 1 17", listOf("g5f4", "c7b6", "c4c5", "b6c5", "f4e3"), "Attraction in Philidor Defense Philidor"),
        RawTacticalPuzzle("2r1rn1k/1p5B/p7/6R1/3b1q2/2B5/PP1Q2P1/2K4R b - - 2 28", listOf("f4d2", "c1d2", "d4c3", "b2c3", "f8h7"), "Attraction Combination"),
        RawTacticalPuzzle("6k1/1p2rpp1/p2q1n1p/3P4/1P6/P1NQ3P/5PP1/R4K2 b - - 1 24", listOf("d6h2", "c3e2", "e7e2", "f1e2", "h2e5"), "Attraction Combination"),
        RawTacticalPuzzle("1r3rk1/2pqp1b1/2p3pp/p3ppR1/3PN2Q/8/PPP2P2/2K4R w - - 0 20", listOf("g5g6", "d7d4", "g6g7", "g8g7", "h4h6"), "Attraction Combination"),
        RawTacticalPuzzle("1r2rknQ/pR3p2/2pq4/3p1BP1/3P4/4P3/P7/1K5R w - - 3 32", listOf("h8g7", "f8g7", "h1h7", "g7f8", "h7f7"), "Attraction Combination"),
        RawTacticalPuzzle("4Q3/5p1k/p2p2pp/1prq1P2/8/1P6/P5PP/5RK1 w - - 2 37", listOf("f5g6", "h7g6", "e8g8", "g6h5", "h2h3", "d5d4", "g1h1"), "Attraction Combination"),
        RawTacticalPuzzle("1k3q1r/1p3P2/p1p1Q3/3pb3/4p3/8/PP4PP/1R3R1K b - - 2 28", listOf("h8h2", "h1g1", "h2g2", "g1g2", "f8g7"), "Attraction Combination"),
        RawTacticalPuzzle("5r2/1bq4Q/p2kpr2/1p1n1pN1/5Pn1/8/PP4PP/3RRBK1 w - - 3 28", listOf("e1e6", "f6e6", "h7c7", "d6c7", "g5e6", "c7d7", "e6f8"), "Attraction Combination")
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
            val cleanTheme = "Attraction"
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
