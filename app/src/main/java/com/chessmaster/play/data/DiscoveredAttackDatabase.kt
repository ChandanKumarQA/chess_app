package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object DiscoveredAttackDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("7r/ppp1k2p/2n5/8/8/2P2pP1/P6P/R1Br1BK1 w - - 0 21", listOf("c1g5", "e7e6", "a1d1"), "Discovered Attack Combination"),
        RawTacticalPuzzle("2kr1b1R/p5p1/8/3P1p2/2P5/4B3/PP1K1P2/8 b - - 0 25", listOf("f8b4", "d2d3", "d8h8"), "Discovered Attack Combination"),
        RawTacticalPuzzle("3r4/ppk2pp1/2p1p3/4b3/P3n1P1/8/KPP2PN1/3rBR1R w - - 3 32", listOf("e1a5", "b7b6", "f1d1"), "Discovered Attack Combination"),
        RawTacticalPuzzle("3r3r/pp3pp1/5k2/2Qbq3/3N4/5PP1/PPP1N2p/R3R2K b - - 0 22", listOf("d5f3", "d4f3", "e5c5"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r1b2rk1/4bppp/p1n5/3q4/Pp6/3B1N2/1B3PPP/R2Q1RK1 w - - 0 18", listOf("d3h7", "g8h7", "d1d5"), "Discovered Attack in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("r2q1rk1/ppp1nppp/3b4/1B6/3QP3/2N2P2/PPP2P1P/R1B2RK1 b - - 0 10", listOf("d6h2", "g1h2", "d8d4"), "Discovered Attack in Nimzowitsch Defense Nimzowitsch"),
        RawTacticalPuzzle("5rk1/pp3Npp/3p2r1/8/bnN5/8/5RP1/5RK1 w - - 0 27", listOf("f7h6", "g7h6", "f2f8", "g8g7", "f1f7"), "Discovered Attack Combination"),
        RawTacticalPuzzle("2k2r1r/p2n3p/1pqbQ3/8/3P4/2P2N2/P2B1PPP/R4RK1 b - - 0 19", listOf("d6h2", "f3h2", "c6e6"), "Discovered Attack in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("R7/4k3/5p2/3p2p1/4b2p/2K1PP1P/6P1/8 b - - 0 47", listOf("d5d4", "c3d4", "e4a8"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r5k1/1p1r1pp1/p3pnp1/2qN4/8/1Q5P/PP3PP1/3RR1K1 w - - 0 25", listOf("d5f6", "g7f6", "d1d7"), "Discovered Attack Combination"),
        RawTacticalPuzzle("5k2/p3pp2/7p/2Qp1P2/6q1/8/2K5/8 w - - 0 44", listOf("c5c8", "f8g7", "f5f6", "g7f6", "c8g4"), "Discovered Attack Combination"),
        RawTacticalPuzzle("6k1/6p1/pp2np1p/8/2r1NR1P/6P1/1PP5/1K6 w - - 2 33", listOf("e4f6", "g7f6", "f4c4"), "Discovered Attack Combination"),
        RawTacticalPuzzle("8/p5pk/1p3b1p/3r3P/6P1/3nBN2/P4PK1/3R4 b - - 4 30", listOf("d3f4", "e3f4", "d5d1"), "Discovered Attack Combination"),
        RawTacticalPuzzle("8/8/8/1p6/3k4/2p4P/rP2RK2/8 w - - 2 50", listOf("b2c3", "d4c3", "e2a2"), "Discovered Attack Combination"),
        RawTacticalPuzzle("7k/p5p1/5q1p/8/P1b5/3rQ1RP/6BK/8 w - - 7 43", listOf("e3e8", "c4g8", "g3d3"), "Deflection Combination"),
        RawTacticalPuzzle("3q2k1/r1p3p1/P1Pp3p/3Pp1b1/R4r2/4QN1P/5PP1/R5K1 b - - 2 27", listOf("f4a4", "a1a4", "g5e3"), "Discovered Attack Combination"),
        RawTacticalPuzzle("6k1/1B2ppbp/p2p2p1/3P4/1P2P3/P3B1P1/4qP1P/1Q1b2K1 b - - 0 28", listOf("e2e1", "g1g2", "d1f3", "g2f3", "e1b1"), "Discovered Attack Combination"),
        RawTacticalPuzzle("8/R1b3r1/8/1pPk4/1P2p3/P3B3/8/4K3 b - - 5 42", listOf("c7g3", "e1f1", "g7a7"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r1b2k1r/pppp2pp/5q2/4n3/2BQ4/8/PPP2PPP/RN2R1K1 b - - 0 13", listOf("e5f3", "g2f3", "f6d4"), "Discovered Attack in Italian Game Italian"),
        RawTacticalPuzzle("8/p1q3k1/6r1/3pp1R1/7Q/7P/P5PK/8 b - - 7 54", listOf("e5e4", "h4g3", "c7g3"), "Discovered Attack Combination"),
        RawTacticalPuzzle("rn1q1b1Q/pp1k3p/2p2n2/3p4/4P1pP/2N5/PPP3B1/2KR2NR b - - 0 14", listOf("f8h6", "c1b1", "d8h8"), "Discovered Attack in Queens Pawn Game"),
        RawTacticalPuzzle("2k5/1pp2B2/p1np4/4pR2/PP2P1q1/1NPP2P1/5bK1/8 w - - 0 31", listOf("f7e6", "c8d8", "f5f8", "d8e7", "e6g4"), "Discovered Attack Combination"),
        RawTacticalPuzzle("4Q3/4qppk/2p2b1p/1p2pP2/2r1B3/5P1P/6P1/5R1K w - - 4 34", listOf("e8e7", "f6e7", "f5f6", "c4e4", "f6e7"), "Discovered Attack Combination"),
        RawTacticalPuzzle("5k2/5pp1/8/1p2N3/1P6/7P/3nR1PK/2r5 b - - 2 48", listOf("d2f1", "h2g1", "f1g3", "g1f2", "g3e2"), "Discovered Attack Combination"),
        RawTacticalPuzzle("rnbqk2r/pp3ppp/5n2/4N3/2p5/2P5/P1PPQPPP/R1B1K2R w KQkq - 0 9", listOf("e5c6", "c8e6", "c6d8"), "Discovered Attack in Kings Pawn Game"),
        RawTacticalPuzzle("2r3k1/5ppp/1p3n2/4p3/2Bq4/5P2/PQ4PP/2R3K1 w - - 0 33", listOf("b2d4", "e5d4", "c4f7", "g8f7", "c1c8"), "Discovered Attack Combination"),
        RawTacticalPuzzle("2r5/p2n1pkp/4p1p1/2Np4/1r6/1P1K1P1P/P3R1P1/2R5 w - - 6 37", listOf("c5e6", "f7e6", "c1c8"), "Discovered Attack Combination"),
        RawTacticalPuzzle("2rq1rk1/1b2bppp/pp2p3/8/3N4/P1P1P3/4BPPP/1Q1R1RK1 w - - 0 19", listOf("d4e6", "f7e6", "d1d8"), "Discovered Attack in Queens Gambit Declined"),
        RawTacticalPuzzle("3r4/4kp1p/1PQ1p1p1/p3b3/1p2P2P/1P5K/6P1/8 b - - 2 36", listOf("d8d3", "g2g3", "d3g3", "h3h2", "g3c3"), "Discovered Attack Combination"),
        RawTacticalPuzzle("2r3k1/2r2p2/p5p1/q5Pp/1p2QP1P/8/PPPR4/1K3R2 b - - 1 32", listOf("b4b3", "a2b3", "a5d2"), "Discovered Attack Combination"),
        RawTacticalPuzzle("2b2r2/r5bk/1p1q3p/pPpBppp1/P1P5/5PB1/6PP/R2Q1RK1 w - - 0 22", listOf("d5g8", "f8g8", "d1d6"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r1b2bkN/ppp3p1/3pPq1p/4n3/3Q4/8/PPP2PPP/RNB1K2R b KQ - 0 12", listOf("e5f3", "g2f3", "f6d4"), "Discovered Attack in Scotch Game Scotch"),
        RawTacticalPuzzle("r4r1k/ppp1b1pp/3q1n2/6B1/8/1QN5/PPP2PPP/R4RK1 b - - 4 14", listOf("f6g4", "g2g3", "e7g5"), "Discovered Attack in Italian Game Italian"),
        RawTacticalPuzzle("r2q1rk1/p4pbp/1pp1p1p1/4n3/2PQN3/1P4P1/PB2PP1P/1R3RK1 b - - 0 16", listOf("e5f3", "e2f3", "g7d4"), "Discovered Attack in English Opening English"),
        RawTacticalPuzzle("r5k1/1pp2r2/p3n2q/3pP2p/2P2R1P/8/PP1Q2P1/5RK1 w - - 1 23", listOf("f4g4", "h5g4", "d2h6", "f7f1", "g1f1"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r1q1r1k1/1p3pp1/n1p4p/p2pp2P/P3P3/2P2P2/1P1QBb2/R1BK3R w - - 0 24", listOf("e2a6", "b7a6", "d2f2"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r3r3/1p1n2pk/2p1p2p/2P5/1p2R3/P2Q1N1P/5PP1/q5K1 w - - 0 25", listOf("e4e1", "h7h8", "e1a1"), "Discovered Attack Combination"),
        RawTacticalPuzzle("4R3/5ppk/7p/2p2q2/1B6/3r2P1/5P1P/1Q4K1 b - - 0 35", listOf("d3g3", "f2g3", "f5b1"), "Discovered Attack Combination"),
        RawTacticalPuzzle("2r2rk1/pp3ppp/8/q2p4/3nn3/P1P2B2/2QB1PPP/R1R3K1 w - - 0 17", listOf("c3d4", "c8c2", "d2a5"), "Discovered Attack in Queens Pawn Game"),
        RawTacticalPuzzle("3k2q1/pp3pN1/2b2b2/2pp2Q1/6n1/3P1N2/P1P2PPP/5K2 w - - 4 27", listOf("g7e6", "d8e7", "g5g8"), "Discovered Attack Combination"),
        RawTacticalPuzzle("8/2k5/8/1r2p1R1/6p1/3K4/1p4PP/1N6 b - - 1 41", listOf("e5e4", "d3c2", "b5g5"), "Discovered Attack Combination"),
        RawTacticalPuzzle("5rk1/ppq2ppp/3bp3/3p1nPP/3P4/2P1B3/PP2Q1P1/R4RK1 b - - 0 19", listOf("d6h2", "g1h1", "f5g3", "h1h2", "g3e2"), "Discovered Attack in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("6qk/1p4p1/B1n2rQp/3p4/8/2P2P2/P6P/6RK w - - 1 30", listOf("g6f6", "g7f6", "g1g8", "h8g8", "a6b7"), "Discovered Attack Combination"),
        RawTacticalPuzzle("2r2rk1/1b3ppp/p2pp3/bp1n4/3PQ1Nq/PP1B4/1BP2PPP/R4RK1 w - - 4 18", listOf("g4f6", "d5f6", "e4h4"), "Discovered Attack in Indian Defense Indian"),
        RawTacticalPuzzle("6k1/1p3p2/2p1bQPq/p3Pp2/2P5/1P6/5PK1/8 w - - 1 54", listOf("g6f7", "e6f7", "f6h6"), "Discovered Attack Combination"),
        RawTacticalPuzzle("2k3rB/ppp2p1p/2np4/8/4P3/2NB1b2/PPP2PPP/R4RK1 b - - 0 13", listOf("g8g2", "g1h1", "g2g4"), "Discovered Attack in Kings Pawn Game"),
        RawTacticalPuzzle("r3r1k1/1p3p1p/p1p3p1/8/6bP/Q3b1P1/PP2B3/R3K2R b KQ - 0 20", listOf("g4e2", "e1e2", "e3c5", "e2f3", "c5a3"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r4rk1/5ppp/p3bp2/2q2N1Q/Ppp5/8/1PP2PPP/R2R2K1 w - - 0 22", listOf("f5h6", "g7h6", "h5c5"), "Discovered Attack Combination"),
        RawTacticalPuzzle("5bk1/2Q2p1p/5qp1/p7/P1Bp4/1P5P/2r2PP1/3R2K1 w - - 6 28", listOf("c4f7", "g8g7", "c7c2"), "Discovered Attack Combination"),
        RawTacticalPuzzle("k4r2/pp6/2p3Q1/3p2P1/4r2P/8/PPP2q2/2KR1R2 b - - 2 30", listOf("f2e3", "c1b1", "f8f1", "g6g8", "e4e8"), "Discovered Attack Combination"),
        RawTacticalPuzzle("3r4/3r2k1/p7/3p2p1/3RP1Pp/1P3K2/P2R4/8 b - - 0 38", listOf("d5e4", "f3e3", "d7d4"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r3r1k1/p2Q1p1p/1p2p1p1/8/1P1P4/P3P3/1B2Nb1q/2KR3R b - - 1 19", listOf("f2e3", "c1b1", "h2e2"), "Discovered Attack Combination"),
        RawTacticalPuzzle("2r4k/q2b1Q1p/2p2P2/1p2p3/4P3/p2P3R/4N1K1/R7 b - - 0 38", listOf("d7h3", "g2h3", "a7f7"), "Discovered Attack Combination"),
        RawTacticalPuzzle("3q4/5kpQ/p1p5/1p1n4/2p3P1/P6P/1PPB4/1K6 b - - 0 33", listOf("d5f6", "h7f5", "d8d2"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r2qk2r/1p3pp1/p1np4/3NpP2/4P1Qb/1P1B4/1PP3PK/R1B2R2 b kq - 0 21", listOf("h4f2", "g4h3", "h8h3", "g2h3", "d8h4", "f1f2", "h4f2"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r2qr1k1/pbp2ppp/1p3n2/8/3nN3/5NP1/PP2QPBP/R3R1K1 w - - 0 14", listOf("e4f6", "g7f6", "e2e8", "d8e8", "e1e8", "a8e8", "f3d4"), "Discovered Attack in Queens Indian Defense"),
        RawTacticalPuzzle("r4k2/pp3p2/2pNR2p/5Qp1/1P3n2/2KP4/P1P2qPP/8 b - - 7 31", listOf("f4d5", "c3b3", "f2f5", "d6f5", "f7e6"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r3k2r/ppq1bppp/4pn2/2Ppn3/1P4bP/2P2N2/P3BPP1/RNBQ1RK1 w kq - 3 11", listOf("f3e5", "c7e5", "e2g4"), "Discovered Attack in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("6k1/1p3r2/p1p1p3/4Pqpp/3P4/1P2R1QP/P4PP1/6K1 b - - 1 33", listOf("h5h4", "g3f3", "f5b1", "g1h2", "f7f3"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r2qr1k1/pb2bppp/2p2n2/3pN1B1/2P5/4P3/PPQ1BPPP/3RK2R b K - 4 13", listOf("e7b4", "e1f1", "e8e5"), "Discovered Attack in Queens Gambit Declined"),
        RawTacticalPuzzle("2r2rk1/pbq1bppp/8/8/2p1N3/P1Bn2P1/2Q2PBP/1R3RK1 w - - 4 24", listOf("b1b7", "c7b7", "e4f6", "e7f6", "g2b7"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r2qr1k1/p5bp/1pp1N1p1/8/2Q2p2/2P1n2P/PP3PP1/R1B1RNK1 b - - 0 18", listOf("e3c4", "e6d8", "e8e1"), "Discovered Attack in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("5rk1/5ppp/1p6/1q3P1Q/2pp3P/6R1/6PK/8 w - - 0 31", listOf("g3g7", "g8g7", "f5f6", "g7f6", "h5b5"), "Attraction Combination"),
        RawTacticalPuzzle("4r2k/p3q1p1/7p/2R5/2PpN3/6P1/4Q2P/6K1 b - - 0 39", listOf("e7c5", "e4c5", "e8e2"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r1b4r/pp1k2p1/2nb2qp/1B1p2B1/3p3Q/8/PPP2PPP/3RR1K1 w - - 0 18", listOf("h4g4", "d7c7", "g5d8", "h8d8", "g4g6"), "Discovered Attack in Italian Game Italian"),
        RawTacticalPuzzle("2k2r2/1bNp4/pB1P4/8/P7/5n2/1P2KR1p/5R2 b - - 1 43", listOf("f3g1", "e2d2", "f8f2", "b6f2", "h2h1q"), "Deflection Combination"),
        RawTacticalPuzzle("1r4k1/Q5pp/4p3/1BR2p2/3q4/B3nP1P/P5P1/6K1 b - - 1 35", listOf("e3g4", "g1h1", "d4d1", "b5f1", "d1f1"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r4r1k/pp4pp/2pp1b2/4p3/N2qP3/3Q4/PPP2PPP/3RR1K1 w - - 4 17", listOf("d3b3", "b7b5", "d1d4"), "Discovered Attack in Scotch Game Scotch"),
        RawTacticalPuzzle("6r1/1pp2rpk/p5R1/3qpP1n/8/2P3Q1/PP3P1K/6R1 w - - 0 36", listOf("g6h6", "g7h6", "g3g8"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r1b2r2/pp2n1p1/1qn1ppk1/3pP1N1/3P3P/P7/1P1Q1PP1/RN2K2R w KQ - 0 14", listOf("h4h5", "g6h6", "g5e6", "h6h7", "e6f8"), "Discovered Attack in French Defense French"),
        RawTacticalPuzzle("r1b2rk1/5p1p/p3pq2/1p1p4/7n/1P5P/P1P1QPB1/1K1R2R1 w - - 0 22", listOf("g2d5", "h4g6", "d5a8"), "Discovered Attack Combination"),
        RawTacticalPuzzle("3qr1k1/pb3p2/1p4pp/n1rp4/P1pRnN2/2P2N1P/BPQ2PP1/3R2K1 w - - 3 21", listOf("d4e4", "d5e4", "d1d8"), "Discovered Attack Combination"),
        RawTacticalPuzzle("5r1k/p5p1/1pb4p/3pR3/1Q6/P1P4P/1P3qP1/R6K b - - 1 24", listOf("d5d4", "e5e2", "c6g2"), "Discovered Attack Combination"),
        RawTacticalPuzzle("8/4k1K1/4P3/6pp/6rP/4R1P1/8/8 b - - 1 60", listOf("g5h4", "g7h6", "h4g3"), "Discovered Attack Combination"),
        RawTacticalPuzzle("6r1/4p3/3p1kp1/2pP1P2/1p2pR2/1P6/4RP1P/6K1 b - - 0 40", listOf("g6f5", "g1f1", "f6g5", "e2e4", "f5e4"), "Discovered Attack Combination"),
        RawTacticalPuzzle("5r1k/1p4pp/p5r1/3P4/3Bb2b/2Q5/PP4PP/4RB1K b - - 12 27", listOf("f8f1", "e1f1", "e4g2", "h1g1", "g2f3"), "Discovered Attack Combination"),
        RawTacticalPuzzle("kr6/8/2r5/1q1p3P/N2P4/Q1P5/PP4P1/1K6 w - - 1 47", listOf("a4c5", "c6a6", "a3a6", "b5a6", "c5a6"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r2qrk2/pb2b3/1p2Q1p1/6Bn/2Pp4/8/PP3PPP/3RR1K1 w - - 1 21", listOf("g5h6", "h5g7", "e6g6", "e7f6", "e1e8", "d8e8", "g6f6"), "Discovered Attack Combination"),
        RawTacticalPuzzle("3R4/p4p1p/2p3k1/6p1/1P1B4/r3PP2/2r3PP/5K2 w - - 7 32", listOf("d8d6", "f7f6", "d6f6", "g6g7", "f6c6"), "Discovered Attack Combination"),
        RawTacticalPuzzle("2r1kbnr/pp4pp/4p3/3pq1N1/8/1P2B3/P3Q1PP/nN3RK1 w k - 0 17", listOf("f1f8", "e8f8", "e3c5", "c8c5", "e2e5", "c5c1", "g1f2"), "Attraction in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("1k3b1r/3rq3/QBpp4/4p3/4P2p/5p2/P5PP/4R1K1 w - - 1 29", listOf("e1b1", "d7b7", "b6a7", "b8c8", "a6c6", "e7c7", "c6e8"), "Discovered Attack Combination"),
        RawTacticalPuzzle("1K6/1pr5/pk6/2bB4/8/8/PP6/5R2 b - - 5 41", listOf("c5d6", "f1f6", "c7c6", "f6d6", "c6d6"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r3kr2/p1p2pQp/bp2pP2/8/8/1P4P1/P2KNP1P/R1B4q w q - 1 18", listOf("g7f8", "e8f8", "c1a3", "f8e8", "a1h1"), "Attraction in French Defense French"),
        RawTacticalPuzzle("r1bk1b1r/2q5/p1n1p1Bp/1p1pP3/8/P3p3/1PP2QPP/R4RK1 w - - 0 18", listOf("f2f6", "d8d7", "f6h8", "c8b7", "f1f8", "a8f8", "h8f8"), "Discovered Attack in French Defense French"),
        RawTacticalPuzzle("8/pp5p/8/3N4/2P5/1P1bR2P/P2r1k1K/8 b - - 2 43", listOf("d3e4", "e3e4", "f2f3"), "Discovered Attack Combination"),
        RawTacticalPuzzle("5rk1/2p3b1/3pq3/P4rB1/1PP1p1Q1/7P/5P1K/2R2R2 b - - 0 36", listOf("e6e5", "f2f4", "e4f3"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r6k/1p1R1Q2/p4b2/P4bqp/5Rp1/1P3P2/5P2/5BK1 b - - 2 34", listOf("g4f3", "g1h1", "a8g8", "f4g4", "h5g4"), "Discovered Attack Combination"),
        RawTacticalPuzzle("5rk1/p3ppbp/bq3np1/3N4/3P4/1B3Q2/PrPB1PPP/R3K2R b KQ - 0 15", listOf("f6d5", "f3d5", "g7d4"), "Discovered Attack in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("r1b1qr2/pp2b1pk/2n1pp2/3p4/2pP3N/2P1P1P1/PP1N1PP1/R2QK2R w KQ - 1 14", listOf("h4g6", "h7g8", "h1h8", "g8f7", "d1h5"), "Discovered Attack in Indian Defense Indian"),
        RawTacticalPuzzle("6k1/5p2/4p2p/3pPP2/1R5P/2r5/2B5/2b2K2 w - - 1 47", listOf("b4b8", "g8h7", "f5e6", "c3c2", "e6e7"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r5k1/2p1qp1p/2b1p1pQ/pp5P/3P2P1/3r4/PP2N3/2KR3R w - - 0 24", listOf("d1d3", "c6h1", "h5g6", "f7g6", "h6h1"), "Discovered Attack Combination"),
        RawTacticalPuzzle("7k/pb3Bpp/1p6/2b2p2/N2q4/P2n3P/1P1Q1PP1/3R2K1 b - - 6 27", listOf("d4e4", "d2g5", "c5f2", "g1h1", "f2h4"), "Discovered Attack Combination"),
        RawTacticalPuzzle("8/p3kp2/2pp1nr1/4p1B1/bq2P2Q/bP1P3N/P3N3/1K1R1R2 b - - 1 30", listOf("a4b3", "d1d2", "b3d1", "b1a1", "d1e2"), "Discovered Attack Combination"),
        RawTacticalPuzzle("7r/p4pk1/2pr1nP1/4p1Q1/4P2P/2qB4/b1P3P1/3R1RK1 w - - 1 24", listOf("g6f7", "g7f7", "d3c4", "c3c4", "g5e5", "c4e6", "e5d6"), "Attraction Combination"),
        RawTacticalPuzzle("5r1k/ppp1q2p/8/3Bn3/4Q1n1/2N3K1/PPP2PP1/R4R2 b - - 0 21", listOf("e7g7", "f2f3", "g4f6"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r7/pb1pq1k1/1p3Rp1/3p1r2/8/8/PPP2QPP/5R1K w - - 6 24", listOf("f6f5", "g6f5", "f2g3", "g7h8", "f1f5", "a8e8", "f5h5"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r1b2rk1/3n1ppp/p2pp3/q5b1/2PQ3P/2NB4/PP4PR/3K2N1 w - - 0 17", listOf("d3h7", "g8h7", "h4g5", "h7g6", "d4h4"), "Clearance in English Opening English"),
        RawTacticalPuzzle("r6k/1p2R1b1/3P2P1/8/b1B2p2/K1p2P1P/8/7R b - - 0 34", listOf("a4d1", "c4a6", "c3c2"), "Discovered Attack Combination"),
        RawTacticalPuzzle("1b5k/ppq2p1b/2p1pp1p/2Q5/2N1PP2/PPN3rP/2P3BK/3R4 b - - 0 24", listOf("c7f4", "c3e2", "g3g5", "e2f4", "b8f4", "h2g1", "g5c5"), "Discovered Attack Combination"),
        RawTacticalPuzzle("3R4/1br2pp1/1pn1p1k1/p3P1pN/P1B3P1/1P5P/5P2/6K1 w - - 3 32", listOf("c4d3", "f7f5", "e5f6", "g6f7", "d8h8"), "Discovered Attack Combination")
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
            val cleanTheme = "Discovered Attack"
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
