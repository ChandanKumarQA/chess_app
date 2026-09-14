package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object PinDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("2r1kb1r/1p3ppp/p7/3ppP2/8/1P1PBP2/P4RPP/2R3K1 b k - 2 21", listOf("c8c1", "e3c1", "f8c5"), "Pin Combination"),
        RawTacticalPuzzle("2kr3r/ppp3pp/6q1/3Pnp2/1PPQp3/7P/P3NPP1/R4RK1 b - - 2 17", listOf("e5f3", "g1h1", "f3d4"), "Pin in Scandinavian Defense Scandinavian"),
        RawTacticalPuzzle("6k1/1pp2ppp/p3n1q1/4PN2/2P2Q2/1P1rBP1P/r4P1K/4R3 w - - 6 31", listOf("f5e7", "g8f8", "e7g6"), "Fork Combination"),
        RawTacticalPuzzle("r6r/ppk3pp/2pb4/4P3/8/6PQ/PP3PKP/R1Bq4 w - - 0 25", listOf("e5d6", "d1d6", "c1f4", "d6f4", "g3f4"), "Pin Combination"),
        RawTacticalPuzzle("5r2/1Pk5/8/K2B4/8/1P6/8/8 b - - 2 47", listOf("f8f5", "b7b8b", "c7b8", "a5b5", "f5d5"), "Pin Combination"),
        RawTacticalPuzzle("r6r/4kppp/2pNpnq1/p1P1n3/8/B3P3/PP1Q1PPP/3R1RK1 b - - 8 20", listOf("e5f3", "g1h1", "f3d2"), "Pin Combination"),
        RawTacticalPuzzle("8/pk1r1Rp1/1p2p2p/4P1bP/4N3/8/PP6/1K1r1R2 w - - 5 34", listOf("f1d1", "d7f7", "e4d6", "b7c7", "d6f7"), "Fork Combination"),
        RawTacticalPuzzle("6k1/5rpp/1q1p4/2pB4/6Q1/2b4P/Pr4P1/6K1 w - - 0 30", listOf("g4c8", "b6d8", "c8d8"), "Mate In2 Combination"),
        RawTacticalPuzzle("r3r1k1/ppp2R2/1b5Q/3PP2n/2B3bq/2N5/PP4PP/R6K b - - 0 18", listOf("h5g3"), "Mate In1 in Italian Game Italian"),
        RawTacticalPuzzle("1k4r1/7p/1p4r1/pPp1qp2/P1Pp1R2/3Q1RNP/2P4K/8 b - - 5 32", listOf("g6g3", "f3g3", "e5f4"), "Deflection Combination"),
        RawTacticalPuzzle("r1b2r2/pp2bpkp/1qn1p2p/3pP3/3P4/5NN1/PP1QBPPP/R3K2R b KQ - 7 12", listOf("e7b4", "e1c1", "b4d2"), "Pin in French Defense French"),
        RawTacticalPuzzle("2r3k1/2r1bppp/3ppn2/qp6/4P3/1Q2B1P1/PP3PBP/1RR3K1 b - - 6 21", listOf("c7c1", "b1c1", "c8c1", "e3c1", "a5e1", "g2f1", "e1c1"), "Fork Combination"),
        RawTacticalPuzzle("6k1/5pb1/p6p/1p4P1/8/5BQ1/PP1q1PP1/6K1 b - - 0 36", listOf("d2e1", "g1h2", "g7e5"), "Pin Combination"),
        RawTacticalPuzzle("2R5/5pk1/pb4p1/1p1q4/3P1P1p/P3r2P/1PQ3P1/3R3K b - - 7 45", listOf("e3h3", "h1g1", "b6d4", "d1d4", "d5d4"), "Pin Combination"),
        RawTacticalPuzzle("5rk1/p4p1p/4p1p1/5nq1/8/5QPP/5PK1/1R1R4 b - - 7 35", listOf("f5h4", "g2f1", "h4f3"), "Pin Combination"),
        RawTacticalPuzzle("2rknb2/3q1pp1/p1pP3r/1pQ1P3/5P1p/1P4P1/PB4K1/3R4 w - - 0 28", listOf("c5b6", "e8c7", "d6c7"), "Pin Combination"),
        RawTacticalPuzzle("6r1/2R3pk/1n2p2p/p6N/6PP/8/P7/6K1 w - - 1 41", listOf("h5f6", "h7h8", "f6g8"), "Pin Combination"),
        RawTacticalPuzzle("8/p6p/3b2pk/5p1n/2B4q/1P3P2/P5QP/3R3K b - - 11 35", listOf("h5g3", "g2g3", "d6g3"), "Pin Combination"),
        RawTacticalPuzzle("6rk/pp6/2n5/3ppn1p/3p4/2P2P1q/PP3QNB/R5RK b - - 3 29", listOf("f5g3", "f2g3", "g8g3"), "Pin Combination"),
        RawTacticalPuzzle("1Q6/3kr3/2q4p/2p1pp1P/2Bb4/1P6/P6K/4R3 w - - 5 44", listOf("c4b5", "c6b5", "b8b5"), "Pin Combination"),
        RawTacticalPuzzle("1k1r3r/1Bq1b1p1/p2p4/2n1p1P1/4P3/4BP2/1PPQN3/2KR3R b - - 0 24", listOf("c5b3", "c1b1", "b3d2"), "Pin Combination"),
        RawTacticalPuzzle("1r3k1r/pNqnppb1/6pn/2p3Np/7P/2P2Q2/PP3PP1/R1B1K2R w KQ - 3 16", listOf("g5e6", "f8g8", "e6c7"), "Pin in Modern Defense Modern"),
        RawTacticalPuzzle("b6k/8/1Q4p1/4q2p/5r1P/P1N3R1/1PP3PK/8 b - - 4 35", listOf("f4h4", "h2g1", "e5e1"), "Mate In2 Combination"),
        RawTacticalPuzzle("r5k1/1pn2p1p/p2p2p1/2pP3P/P1Pb2q1/3B2B1/1P2QP2/R4RK1 b - - 0 21", listOf("g4g3", "g1h1", "g3h3"), "Pin Combination"),
        RawTacticalPuzzle("1r4k1/p5pp/5p2/2Rb4/1P4P1/P7/3r3P/3B1RK1 w - - 0 35", listOf("c5d5", "d2d5", "d1b3", "g8f8", "b3d5"), "Attraction Combination"),
        RawTacticalPuzzle("2r1r1k1/2q4p/4pp1Q/p2p3R/3P4/2P4P/P4PP1/6K1 w - - 1 28", listOf("h6f6", "c7g7", "h5g5", "g7g5", "f6g5"), "Pin Combination"),
        RawTacticalPuzzle("2r3k1/2r3p1/p3pqQ1/1p1p4/nP1P4/2P4R/P4PPP/2R3K1 w - - 1 31", listOf("g6h7", "g8f7", "h3f3", "f6f3", "g2f3"), "Pin Combination"),
        RawTacticalPuzzle("1k2r3/pp2r2p/2pqbpp1/3n4/3P1p2/1B3N1P/PPQB1PP1/1K1RR3 b - - 3 22", listOf("e6f5", "c2f5", "g6f5"), "Pin Combination"),
        RawTacticalPuzzle("2r2r2/1b4pk/p5qp/2p2p1Q/4pP2/2P1R2N/PP4PP/2R3K1 w - - 5 33", listOf("h3g5", "g6g5", "f4g5"), "Pin Combination"),
        RawTacticalPuzzle("2rqrbk1/pp3ppp/8/3p1N2/3NnnQ1/2P4P/PP3PP1/R4RK1 w - - 0 23", listOf("f5h6", "g8h8", "h6f7"), "Deflection Combination"),
        RawTacticalPuzzle("7Q/5p2/6p1/R3pk1p/P2q2PK/7P/5P2/7r b - - 0 41", listOf("d4g4"), "Mate In1 Combination"),
        RawTacticalPuzzle("8/5p1k/5Ppb/2p3P1/qp6/8/KB5Q/8 w - - 5 59", listOf("a2b1", "a4d1", "b2c1"), "Pin Combination"),
        RawTacticalPuzzle("2N3k1/1p5p/BP4p1/3n1p2/n3p3/6P1/5P1P/6K1 w - - 6 38", listOf("a6c4", "g8g7", "c4d5"), "Pin Combination"),
        RawTacticalPuzzle("r1r3k1/4pp1p/3p2p1/8/q2RPP2/1N6/1PP2QPP/1K3R2 b - - 0 26", listOf("a4a2", "b1c1", "a2b3"), "Pin Combination"),
        RawTacticalPuzzle("5q1k/pr4pp/2p4B/4Q3/7P/2P5/P1PK4/4R3 b - - 6 30", listOf("b7d7", "d2e2", "d7e7", "e5e7", "f8e7"), "Pin Combination"),
        RawTacticalPuzzle("1r3k2/p4ppp/2r5/5N2/P2P4/8/2P2PPP/3Q2K1 b - - 4 23", listOf("c6b6", "g1f1", "b6b1", "d1b1", "b8b1"), "Pin Combination"),
        RawTacticalPuzzle("r1bq4/p3p1bk/2p3p1/3pn2p/8/2NB3P/PPPBQ1P1/5RK1 w - - 2 18", listOf("e2h5", "h7g8", "d3g6", "e5g6", "h5g6"), "Deflection in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("5rk1/R5bp/q2p4/3Ppn1p/4Q3/4B2P/5PP1/5RK1 b - - 0 24", listOf("a6f1", "g1f1", "f5g3", "f1e1", "g3e4"), "Attraction Combination"),
        RawTacticalPuzzle("8/2p5/p1n5/1pP1p2k/1P1p4/P2P3q/5P2/R4KR1 w - - 0 35", listOf("f1e2", "e5e4", "g1h1", "e4d3", "e2d2"), "Pin Combination"),
        RawTacticalPuzzle("3r1rk1/1p3p1p/p1q3p1/4Pb1P/2p2P2/P5R1/2Q1B1P1/3R2K1 w - - 2 26", listOf("c2f5", "d8d1", "e2d1"), "Pin Combination"),
        RawTacticalPuzzle("4r1k1/p5bp/1q2N1p1/2pPrp2/1pP5/6PP/P4QB1/1R3RK1 b - - 3 25", listOf("e5e6", "d5e6", "g7d4"), "Clearance Combination"),
        RawTacticalPuzzle("8/pp4k1/2b2pr1/3R4/PP1Q4/8/7P/7K b - - 0 31", listOf("g6g5", "h2h4", "g5d5", "d4d5", "c6d5"), "Pin Combination"),
        RawTacticalPuzzle("r4bk1/2rqp2p/n1p3p1/3p1p2/3P1P1B/pP1BP3/P1Q2PRP/1KR5 w - - 1 27", listOf("d3f5", "e7e6", "f5g6"), "Pin Combination"),
        RawTacticalPuzzle("2k3rr/ppp2p2/3B1p2/2pP1q1p/2P5/2N2B1b/PP1Q1PP1/R3R1K1 b - - 0 19", listOf("f5f3", "e1e8", "c8d7"), "Pin Combination"),
        RawTacticalPuzzle("3r2r1/Qpqkbp2/p1p1p2p/3nP3/8/2P1NP1N/PP6/1K1R3R w - - 4 26", listOf("c3c4", "b7b6", "a7c7", "d7c7", "c4d5"), "Pin Combination"),
        RawTacticalPuzzle("r1b2rk1/1p3ppp/2p5/pq6/3b4/1BP2Q2/PP3PPP/R3R1K1 w - - 0 18", listOf("f3f7", "f8f7", "e1e8"), "Mate In2 in Ruy Lopez Ruy"),
        RawTacticalPuzzle("r1b3k1/pp4b1/n1pqprQ1/4p3/2P2PP1/P2B4/1P6/R1B1K2R w KQ - 2 20", listOf("h1h8", "g8h8", "g6h7"), "Attraction Combination"),
        RawTacticalPuzzle("r6k/q1p2p1p/1b2bPr1/p1ppP2Q/3P2p1/4B3/PP2NRPP/3R2K1 w - - 2 26", listOf("e2f4", "c5d4", "f4g6", "f7g6", "h5h6"), "Pin Combination"),
        RawTacticalPuzzle("1r1q1rk1/1b2bppp/p7/1p2pP2/3pp1Q1/P2P2P1/BPP4P/R1B2RK1 w - - 0 17", listOf("c1h6", "e7f6", "h6g7", "f6g7", "f5f6", "d8f6", "f1f6"), "Pin in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("3r1rk1/pp6/5pb1/5q2/2p5/P2P4/1PP4Q/2K3RR w - - 0 28", listOf("h2h7"), "Mate In1 Combination"),
        RawTacticalPuzzle("2r2rk1/6pp/3Q1q2/8/3N1B2/6P1/PP1K3P/5R2 b - - 0 24", listOf("f6d6", "f4d6", "f8f1"), "Discovered Attack Combination"),
        RawTacticalPuzzle("2k4r/ppp2ppr/2n5/3b1N2/3p1bP1/5NP1/PPP1QPK1/R3R3 b - - 2 20", listOf("h7h2", "g2f1", "h2h1", "f3g1", "h1g1", "f1g1", "h8h1"), "Attraction Combination"),
        RawTacticalPuzzle("5r2/5p1k/6pp/ppqp1P2/7Q/5N2/6PP/5N1K w - - 0 32", listOf("f3g5", "h7g7", "f5f6", "g7f6", "g5e4"), "Attraction Combination"),
        RawTacticalPuzzle("5rk1/pp1n1r2/3p2N1/2pQ4/2P5/4P1P1/qP2KPP1/7R w - - 2 25", listOf("g6e7", "g8g7", "d5g5"), "Mate In2 Combination"),
        RawTacticalPuzzle("r2q1rk1/p3bpp1/2pp2b1/4p1Q1/4n3/1B3N1P/PPP3P1/R1B2RK1 w - - 1 19", listOf("g5g6", "d8b6", "g1h2"), "Pin in Czech Defense Czech"),
        RawTacticalPuzzle("2r2rk1/pp3p2/4p3/3p2q1/3P4/P1N4Q/1PP5/1R4K1 w - - 0 31", listOf("g1h1", "g5g6", "b1g1", "g8g7", "g1g6"), "Clearance Combination"),
        RawTacticalPuzzle("8/5ppk/P5qp/2p1p3/2B4Q/1P2PbP1/5P1P/6K1 b - - 0 37", listOf("g6b1", "c4f1", "f3e2", "h4h3", "e2a6"), "Pin Combination"),
        RawTacticalPuzzle("1Qbk1b1r/3r1pp1/p1BP3p/8/3q4/6P1/1PP3PP/1K5R w - - 1 34", listOf("c6d7", "d4c5", "d7c8"), "Pin Combination"),
        RawTacticalPuzzle("8/p1B4p/1p3pk1/6p1/3PQ1P1/2P1nP1K/P3q2P/8 b - - 10 36", listOf("g6h6", "e4e3", "e2e3"), "Pin Combination"),
        RawTacticalPuzzle("5rk1/1r4p1/p3pnQp/3pN1p1/P1pPq3/2P2R1P/1P4P1/5R1K w - - 10 30", listOf("f3f6", "f8f6", "f1f6", "e4g6", "f6g6"), "Pin Combination"),
        RawTacticalPuzzle("4r1k1/5pp1/5n1p/P5r1/7q/1QP1p1PP/1P3P1K/R3RB2 b - - 1 27", listOf("f6g4", "h2g2", "g4f2"), "Pin Combination"),
        RawTacticalPuzzle("r4k2/ppp3p1/3p3r/2q5/1PNnP3/2Q2PBp/P4R1P/5RK1 b - - 0 25", listOf("d4e2", "g1h1", "c5f2", "c3g7", "f8g7"), "Deflection Combination"),
        RawTacticalPuzzle("2k3rr/ppp2p1p/2b5/4Nn2/3pPQ2/1P4Pq/P1PP1P1P/RN3RK1 b - - 0 18", listOf("f5h4", "f4h4", "h3h4"), "Pin in Vant Kruijs Opening"),
        RawTacticalPuzzle("rnbq3r/1p3kpp/p4n2/2b5/2pNP3/2N5/PPP3PP/R1BQ1RK1 w - - 2 12", listOf("d1h5", "f7g8", "h5c5"), "Pin in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("6k1/p2Q4/3p2p1/8/3qPn2/5P2/PP3RPP/6K1 b - - 3 37", listOf("f4e2", "g1h1", "d4d1", "f2f1", "d1f1"), "Mate In3 Combination"),
        RawTacticalPuzzle("2r5/1b4k1/2q1p1pr/2p5/1p1pR1QP/pP1P1PP1/P1P5/4R1K1 w - - 1 31", listOf("e4e6", "c6f3", "e6e7", "g7f8", "e1f1", "f3f1", "g1f1", "f8e7", "g4g5", "e7d7", "g5h6"), "Fork Combination"),
        RawTacticalPuzzle("4r1k1/ppqb4/6p1/3pb2Q/8/2P5/PP1B2PP/5RK1 w - - 0 21", listOf("h5g6", "e5g7", "f1f7", "c7c5", "g1h1", "c5f8", "f7f8"), "Pin Combination"),
        RawTacticalPuzzle("1k1r1b1r/n1p3p1/p3n1p1/1p2P3/P2B2Pq/2P2Q1P/1PB1K3/R6R w - - 1 25", listOf("d4a7", "b8a7", "a4b5", "d8d2", "e2d2"), "Pin Combination"),
        RawTacticalPuzzle("8/3N2pk/4Q1bp/1p1p4/3q2P1/7P/6K1/5R2 b - - 1 45", listOf("g6e4", "f1f3", "d4d2", "g2g3", "d2e1", "g3f4", "g7g5", "f4e5", "e4f3"), "Discovered Attack Combination"),
        RawTacticalPuzzle("7r/Bp1k1ppp/b7/4P3/1b6/8/PPN2rPP/R2K3R b - - 1 19", listOf("f2d2", "d1c1", "h8c8", "h1d1", "c8c2"), "Pin Combination"),
        RawTacticalPuzzle("2r1kb2/5p1p/p3p3/1p2P3/1Pq2Q2/P1p3P1/2P4P/2KR1R2 b - - 1 28", listOf("f8h6", "c1b1", "h6f4"), "Pin Combination"),
        RawTacticalPuzzle("5rk1/7p/p2R2p1/1p2n1P1/2q1Q1P1/PBP5/Kr6/8 w - - 0 41", listOf("a2b2", "f8f2", "e4c2", "f2c2", "b2c2"), "Hanging Piece Combination"),
        RawTacticalPuzzle("7r/pkp5/1pN3rb/3Q1n1q/3P4/2P3PN/PP4BP/5RK1 b - - 0 25", listOf("h6e3", "f1f2", "h5d1"), "Pin Combination"),
        RawTacticalPuzzle("r5rk/p2nq2p/2ppp2b/1p2p1Pn/7P/1PN5/PBPRQ3/2K3R1 w - - 0 21", listOf("e2h5", "h6g7", "c3e4", "d6d5", "g5g6", "h7h6", "e4g5"), "Pin Combination"),
        RawTacticalPuzzle("3Q4/3qrRbk/p2p2p1/1p2r2p/2p1P1bP/6N1/PP4P1/1B3R1K w - - 12 38", listOf("d8f8", "e7f7", "f1f7", "d7f7", "f8f7"), "Pin Combination"),
        RawTacticalPuzzle("8/6p1/4pk2/P2n1p1Q/2pP1Pr1/2P5/2qB2RP/R5K1 b - - 0 33", listOf("g4g2", "g1g2", "d5f4", "g2g1", "f4h5"), "Attraction Combination"),
        RawTacticalPuzzle("2r2rk1/p4pp1/1p2p2p/4N1P1/q2P3P/P7/1PnQ1P2/1K1R2R1 b - - 0 26", listOf("a4b3", "g1g3", "c2a3", "b1a1", "a3c2"), "Pin Combination"),
        RawTacticalPuzzle("r4r1k/6p1/b3p1nN/p1pp4/1p3P1q/3P1Q1B/PPP2PK1/R6R w - - 1 27", listOf("h6f7", "f8f7", "h3e6"), "Pin Combination"),
        RawTacticalPuzzle("r1b5/pppqNkp1/3p3p/4r2n/1PP2p1N/P5P1/4Q2P/4RRK1 w - - 4 23", listOf("e2h5", "e5h5", "f1f4", "f7e8", "e7g6"), "Discovered Attack Combination"),
        RawTacticalPuzzle("3r1k2/p6p/1p1q4/4p3/P1P1p1Qb/1PB1P3/5rPP/R5K1 w - - 2 26", listOf("g4h4", "f2f5", "c3b4", "d6b4", "h4d8"), "Deflection Combination"),
        RawTacticalPuzzle("r7/4n1k1/2b1R1n1/1p4R1/1P1p3p/3P1PpP/2N3P1/6K1 w - - 1 41", listOf("e6e7", "g7f6", "g5g6", "f6e7", "g6c6", "e7d7", "c6c5"), "Pin Combination"),
        RawTacticalPuzzle("2kr2r1/1bp4n/1pq1p2p/p1P5/1P3B2/P6P/5RP1/RB3QK1 b - - 4 26", listOf("d8d1", "f1d1", "g8g2", "g1f1", "g2g1", "f1e2", "g1d1"), "Deflection Combination"),
        RawTacticalPuzzle("7Q/p3nkpp/3p2q1/3P4/4P3/B1r2B1b/P4PPP/R4RK1 b - - 0 20", listOf("c3f3", "g2g3", "f3a3"), "Pin Combination"),
        RawTacticalPuzzle("2r3k1/6p1/R3p1r1/8/P2PPp2/2P2P1p/2R3P1/6K1 b - - 0 36", listOf("c8b8", "g1h2", "h3g2"), "Pin Combination"),
        RawTacticalPuzzle("3r4/p4R2/1pb2Pp1/n1p1Qqkp/8/P7/1P4PP/6RK w - - 3 33", listOf("e5e3", "f5f4", "h2h4", "g5f5", "e3h3"), "Pin Combination"),
        RawTacticalPuzzle("6k1/5pp1/2R1p2p/8/P1B5/1P4P1/1q3QKP/3r4 b - - 2 35", listOf("d1d2", "c6c8", "g8h7", "c4d3", "f7f5", "c8c2", "d2f2"), "Pin Combination"),
        RawTacticalPuzzle("r3kb1r/1p1b1p2/p2ppp1p/2q2P1B/2nNP3/2N3R1/P1PQ2PP/1R4K1 w kq - 1 20", listOf("d2f2", "e6e5", "d4e6", "c5f2", "g1f2"), "Pin in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("r3rk2/pbq5/1p2pp1p/2p1N3/2PP2Q1/1P1B4/P5PP/R5K1 w - - 2 25", listOf("a1f1", "c7g7", "f1f6", "g7f6", "e5d7"), "Pin Combination"),
        RawTacticalPuzzle("6k1/pp6/3br2p/6pq/3R2RP/1P6/P1P2KQ1/8 b - - 0 30", listOf("h5f7", "g2f3", "e6f6", "f3f6", "f7f6"), "Pin Combination"),
        RawTacticalPuzzle("r5kr/pp1qb1p1/2p4p/3pP2Q/3Pb3/2P1B3/PP4PP/R4RK1 w - - 2 18", listOf("h5f7", "g8h7", "f1f6", "e7f6", "f7d7"), "Pin in Russian Game Russian"),
        RawTacticalPuzzle("6k1/pp1r1pp1/2p1p2p/4P2P/3q1Q2/1P4R1/P1Pr1PP1/R5K1 w - - 5 24", listOf("f4f6", "d4f2", "f6f2", "d2f2", "g1f2"), "Pin Combination"),
        RawTacticalPuzzle("r2q1k1r/p1p2ppp/1p6/8/1Q1n2b1/5NP1/PP1N1PP1/3RKB1R b K - 3 15", listOf("c7c5", "b4c4", "d8e7", "f1e2", "b6b5", "c4d3", "g4f5", "d3d4", "c5d4"), "Pin in Queens Pawn Game"),
        RawTacticalPuzzle("3r2k1/2r2p2/4p2p/4N1pQ/1p3P2/4P3/np3P1P/2q2BRK w - - 2 33", listOf("h5h6", "b2b1q", "h6g5"), "Pin Combination"),
        RawTacticalPuzzle("8/5Rpk/3p3p/p1qPp3/P7/5N1P/1Q3nPK/2r5 w - - 0 36", listOf("b2b7", "c1h1", "h2g3", "f2e4", "g3g4", "e4f6", "f7f6", "c5b4", "b7b4"), "Pin Combination"),
        RawTacticalPuzzle("3rnrk1/1b3pp1/4pb2/p3q3/1p1N4/3B2R1/PPPQN2P/1K4R1 w - - 2 24", listOf("d2h6", "g7g6", "g3h3"), "Pin Combination"),
        RawTacticalPuzzle("1r6/k2qn1b1/p1N1p1p1/2PpPpN1/2n2P1P/p4B2/1PP2Q2/1K1R3R b - - 0 32", listOf("e7c6", "b2b3", "a3a2", "b1a1", "c4e5", "f4e5", "g7e5", "a1a2", "b8b5"), "Pin Combination"),
        RawTacticalPuzzle("4r1k1/1rp2ppp/5q2/2R5/8/P2P4/R2Q1PPP/6K1 b - - 0 24", listOf("b7b1", "c5c1", "e8e2", "d2e2", "b1c1"), "Deflection Combination"),
        RawTacticalPuzzle("4b1k1/4Pr2/3R2pp/1ppBP2q/8/PP4P1/2P4P/3R3K w - - 3 39", listOf("d1f1", "g8g7", "d5f7", "e8f7", "d6d8", "h5e5", "f1f7", "g7f7", "e7e8q", "e5e8", "d8e8"), "Attraction Combination"),
        RawTacticalPuzzle("2kr3r/p4p2/1p2p2p/1N1p2p1/3Q4/1P1P4/2q2PPP/5RK1 w - - 0 21", listOf("d4a1", "a7a5", "f1c1"), "Pin Combination"),
        RawTacticalPuzzle("r4rk1/1pq2ppp/p2p2n1/3Bp3/4P1R1/P3QPR1/1P3P2/7K w - - 1 25", listOf("g4g6", "h7g6", "e3g5", "c7d8", "g5g6"), "Pin Combination")
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
            val cleanTheme = "Pin"
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
