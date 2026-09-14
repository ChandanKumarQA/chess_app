package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object MateIn1Database {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("8/6pp/6k1/5pN1/5P2/5rPb/4R2P/6K1 b - - 1 35", listOf("f3f1"), "Mate In1 Combination"),
        RawTacticalPuzzle("1r3rk1/2p1qppb/p2n4/1p2p1Pp/4Qn1P/2P1N3/PPB2P1K/3R2R1 w - - 0 29", listOf("e4h7"), "Mate In1 Combination"),
        RawTacticalPuzzle("r1b1k1nr/1pp2p2/p7/1q5p/6p1/4PP2/PPPQ1P2/2KR3R w kq - 0 18", listOf("d2d8"), "Mate In1 in Queens Pawn Game"),
        RawTacticalPuzzle("Q4rk1/p1p3p1/6P1/8/3P4/7P/q3r3/B4RK1 w - - 2 35", listOf("a8f8"), "Mate In1 Combination"),
        RawTacticalPuzzle("r2q1rk1/1b3ppp/p2p1b2/1p1Pn3/1P2Q3/P1NB3P/1B3PP1/R4RK1 w - - 3 18", listOf("e4h7"), "Mate In1 in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("6k1/2p2ppp/pnp5/B7/2P3PP/1P2PPR1/r3b2r/3R2K1 w - - 2 30", listOf("d1d8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("2r3k1/5p1p/4pP2/3p3P/8/5P2/p5P1/1bR3K1 w - - 1 31", listOf("c1c8"), "Hanging Piece Combination"),
        RawTacticalPuzzle("r2r2k1/2q1bpp1/3p3p/1ppn4/1P1BP3/P5Q1/4RPPP/R5K1 w - - 0 21", listOf("g3g7"), "Mate In1 Combination"),
        RawTacticalPuzzle("6k1/6pp/p2B4/2pP4/P1q5/6P1/2P1p2P/5RK1 w - - 0 27", listOf("f1f8"), "Mate In1 Combination"),
        RawTacticalPuzzle("r3kb1r/p4pp1/b1p4p/n3pQ2/4N3/2Nq4/PP1P1PPP/R1B2RK1 b kq - 1 14", listOf("d3f1"), "Mate In1 in Italian Game Italian"),
        RawTacticalPuzzle("r1b2rk1/ppb5/2p4p/2Ppqpp1/1P6/2NBP3/P4PPP/2RQ1RK1 b - - 1 16", listOf("e5h2"), "Mate In1 in Dutch Defense Dutch"),
        RawTacticalPuzzle("rn2q1k1/pp3ppp/2pb4/3p1B2/2PN4/1Q6/PP3PPP/R1B4K b - - 0 15", listOf("e8e1"), "Mate In1 in Petrovs Defense Petrovs"),
        RawTacticalPuzzle("6k1/p1p3pp/4N3/1p6/2q1r1n1/2B5/PP4PP/3R1R1K w - - 0 29", listOf("f1f8"), "Mate In1 Combination"),
        RawTacticalPuzzle("8/6k1/1R5p/5p1P/5P1K/6P1/8/r7 b - - 3 58", listOf("a1h1"), "Mate In1 Combination"),
        RawTacticalPuzzle("5r1k/pp4pp/2p5/6q1/5R2/2P5/P1P2PPP/3rR1K1 w - - 0 28", listOf("f4f8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("1Q6/5ppp/8/8/8/2pk3P/3p2P1/3K4 b - - 0 52", listOf("c3c2"), "Mate In1 Combination"),
        RawTacticalPuzzle("2kr2r1/ppb2ppp/3qNn2/3p2B1/P7/2P2Q1P/1PB2PP1/R4RK1 b - - 0 18", listOf("d6h2"), "Mate In1 in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("3r1k1r/5p1p/b2q1p2/P3p3/Bp2P3/2N2P2/3Q2PP/1R2K2R b K - 0 24", listOf("d6d2"), "Mate In1 Combination"),
        RawTacticalPuzzle("2kr1b1r/p1p2pp1/2pqN3/7p/6n1/2NPB3/PPP2PPP/R2Q1RK1 b - - 0 13", listOf("d6h2"), "Mate In1 in Scandinavian Defense Scandinavian"),
        RawTacticalPuzzle("3r1n2/1bp1bkpp/p1q2n2/1p6/3P4/P1N3B1/1PP1QPPP/R3R1K1 b - - 6 18", listOf("c6g2"), "Mate In1 in Scandinavian Defense Scandinavian"),
        RawTacticalPuzzle("r4rk1/pb3ppp/1p2p3/3q4/3N4/2P4P/PPQ2PP1/R4RK1 b - - 2 18", listOf("d5g2"), "Mate In1 in Scandinavian Defense Scandinavian"),
        RawTacticalPuzzle("r2q1rk1/ppp2ppp/2n5/3p2N1/3P4/1B5P/P1Q2PP1/R1B2bK1 w - - 0 17", listOf("c2h7"), "Mate In1 in Italian Game Italian"),
        RawTacticalPuzzle("r6r/ppp1n1p1/3bBk1p/4nP2/3p4/8/PPPN1P1P/R1B1K2R w KQ - 2 16", listOf("d2e4"), "Mate In1 in Scotch Game Scotch"),
        RawTacticalPuzzle("r4rk1/1bp2ppp/p1q1pn2/2P1N3/8/3B4/P1P1QPPP/R4RK1 b - - 1 16", listOf("c6g2"), "Mate In1 in French Defense French"),
        RawTacticalPuzzle("7r/ppp2kp1/2nb1pp1/3p3r/3P2P1/2PQB3/PP3PP1/R3R1K1 b - - 0 18", listOf("h5h1"), "Mate In1 in Scandinavian Defense Scandinavian"),
        RawTacticalPuzzle("2k2bn1/ppp2Nrp/2b1p3/3q2BQ/8/2N5/PP3PPP/R4RK1 b - - 6 18", listOf("d5g2"), "Mate In1 in Ponziani Opening Ponziani"),
        RawTacticalPuzzle("3r1k1r/p1p2pp1/1p6/2pQ1b2/2Pn1P2/8/PP1P1KBq/R1B1R3 w - - 4 26", listOf("d5d8"), "Hanging Piece Combination"),
        RawTacticalPuzzle("6k1/5ppp/5Bq1/8/p3R3/P6P/5rB1/R5K1 w - - 0 30", listOf("e4e8"), "Mate In1 Combination"),
        RawTacticalPuzzle("rqnr2k1/5ppp/p7/4p3/8/1P5P/PBP2PP1/R2R2K1 w - - 0 24", listOf("d1d8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("2rq1rk1/1p3p1p/p1pn2p1/P1Np4/1P1PnP2/4P3/5PBP/R1Q3RK b - - 3 22", listOf("e4f2"), "Mate In1 Combination"),
        RawTacticalPuzzle("6k1/pb2r1pN/1n4Bp/3p4/1P2pR2/P7/5PPP/2rR2K1 b - - 3 30", listOf("c1d1"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("6r1/7p/2pk1p2/P2p4/P2KbP2/2N1P3/5R1P/8 b - - 2 35", listOf("c6c5"), "Mate In1 Combination"),
        RawTacticalPuzzle("2kr3r/p2n2pp/2pB1bp1/5q2/2B5/8/PPP2PPP/3R1RK1 w - - 0 18", listOf("c4a6"), "Boden Mate in Elephant Gambit Elephant"),
        RawTacticalPuzzle("5kr1/ppR3p1/3R3p/1n6/1r6/8/1P3PPP/2K5 w - - 5 32", listOf("d6d8"), "Mate In1 Combination"),
        RawTacticalPuzzle("r1b1kb1Q/ppp4p/6pB/3P4/2pn4/8/PPP1qPPP/RNK4R b q - 3 13", listOf("e2c2"), "Mate In1 in Italian Game Italian"),
        RawTacticalPuzzle("4r3/3R1pkp/6p1/1P6/1b6/5B2/1P1R1PPP/6K1 b - - 0 36", listOf("e8e1"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("8/5k2/1P4RK/6P1/1r6/8/8/8 b - - 2 58", listOf("b4h4"), "Mate In1 Combination"),
        RawTacticalPuzzle("rn2kb1r/pQ2pppp/2p2n2/8/3q2b1/8/PPP2PPP/RNB1KBNR b KQkq - 0 7", listOf("d4d1"), "Mate In1 in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("2k4r/pp3pp1/4pn2/2np2p1/8/1B1P1Pq1/PPPN3R/R2Q3K b - - 7 20", listOf("g3h2"), "Mate In1 Combination"),
        RawTacticalPuzzle("rn3q1r/4pk2/2pp1npp/p5Q1/1p1PPNP1/5P2/PPP5/R4KNR w - - 0 18", listOf("g5g6"), "Mate In1 in Pirc Defense Pirc"),
        RawTacticalPuzzle("4r1k1/2Q4p/pp6/2p2n2/P2P1P1q/2P4P/2PB2b1/4R1K1 w - - 0 30", listOf("e1e8"), "Mate In1 Combination"),
        RawTacticalPuzzle("7k/1pq3p1/2p2r1p/3pPQ2/1p1P4/7P/1rB4K/5R2 w - - 0 36", listOf("f5h7"), "Mate In1 Combination"),
        RawTacticalPuzzle("q5kr/p4p2/4b1p1/4B2p/5n2/2P5/P1Q2PPP/3R1RK1 b - - 0 21", listOf("a8g2"), "Mate In1 Combination"),
        RawTacticalPuzzle("N6r/1p1k1ppp/2np4/b3p3/4P1b1/N1Q5/P4PPP/R3KB1R b KQ - 0 18", listOf("a5c3"), "Boden Mate in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("r4rk1/pbp3pp/1p1pp3/6B1/2PPp2q/3BP2P/PP3P2/R2QK1R1 b Q - 0 16", listOf("h4f2"), "Attacking F2 F7 in Dutch Defense Dutch"),
        RawTacticalPuzzle("2kr4/1pp5/p1b4R/2PpP3/3B2p1/2P1Q1Pp/PPq4P/5RK1 b - - 0 27", listOf("c2g2"), "Mate In1 Combination"),
        RawTacticalPuzzle("r1bk3r/ppp1np1p/3P2pP/1N4q1/2BP1n2/8/PPP3P1/R1BQ2KR b - - 0 14", listOf("g5g2"), "Mate In1 in Three Knights Opening"),
        RawTacticalPuzzle("r4rk1/pp3ppp/3b4/2p1pPB1/7N/2PP3n/PP4PP/R2Q2RK b - - 0 18", listOf("h3f2"), "Mate In1 in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("r6k/1b3pp1/p1q1pn1p/2p5/P1B5/1PN4Q/2P1RP1P/R5K1 b - - 0 26", listOf("c6h1"), "Mate In1 Combination"),
        RawTacticalPuzzle("r3k2r/ppp2p1p/2n1pp2/7q/2PN2P1/2BP2b1/PP2B1P1/R2Q1RK1 b kq - 0 16", listOf("h5h2"), "Mate In1 in Scandinavian Defense Scandinavian"),
        RawTacticalPuzzle("r4rk1/2q2ppp/3pp3/4Pb1N/1p6/1p4Q1/PPP3PP/1K1RR3 w - - 0 22", listOf("g3g7"), "Mate In1 Combination"),
        RawTacticalPuzzle("rnbk1r2/pppp1Bpp/8/5p2/4p3/2PP4/P1P2PPP/R1B1K2R w KQ - 1 14", listOf("c1g5"), "Mate In1 in Bishops Opening Bishops"),
        RawTacticalPuzzle("8/8/5pkp/1RP5/1P3PKP/r7/8/8 b - - 0 48", listOf("f6f5"), "Mate In1 Combination"),
        RawTacticalPuzzle("4r2k/p4R1p/1p6/2p5/2P5/1P4R1/r5PP/2K5 b - - 1 32", listOf("e8e1"), "Mate In1 Combination"),
        RawTacticalPuzzle("7k/6p1/8/4p3/Pp1Q4/1P3b1q/6P1/5RK1 b - - 0 45", listOf("h3g2"), "Mate In1 Combination"),
        RawTacticalPuzzle("rn2kb1r/pp2pp1p/2p2np1/4q3/8/2N4Q/PPPPBPPP/R1B1K2R w KQkq - 0 9", listOf("h3c8"), "Mate In1 in Scandinavian Defense Scandinavian"),
        RawTacticalPuzzle("rn5Q/4kp2/2p1p1r1/1q4p1/8/8/4NPPP/3R1K1R w - - 6 24", listOf("h8d8"), "Mate In1 Combination"),
        RawTacticalPuzzle("4r3/pp1b2pp/8/2pPkpK1/2P1p2P/2P3P1/P3R3/2B5 w - - 3 27", listOf("c1f4"), "Mate In1 Combination"),
        RawTacticalPuzzle("r1b2k1r/pp4p1/2pq2p1/3p4/3Q4/1N6/PPP2PPP/R4RK1 b - - 0 17", listOf("d6h2"), "Mate In1 in Italian Game Italian"),
        RawTacticalPuzzle("3r1rk1/5pp1/7p/8/b2Qp1n1/1P6/PB1q1PP1/R5K1 w - - 0 26", listOf("d4g7"), "Mate In1 Combination"),
        RawTacticalPuzzle("2r3k1/7p/6q1/p1Np4/Qp2pr2/P4P2/1PR2P1K/6R1 b - - 1 36", listOf("f4h4"), "Mate In1 Combination"),
        RawTacticalPuzzle("4R3/4R3/1k1K2p1/1P6/1P6/2rp3r/8/8 w - - 4 46", listOf("e8b8"), "Mate In1 Combination"),
        RawTacticalPuzzle("r5k1/pp4pp/4p1q1/4p3/3n4/P3Q1P1/1PP4P/2KR1R2 b - - 5 24", listOf("g6c2"), "Mate In1 Combination"),
        RawTacticalPuzzle("2r3kq/Q7/8/1brpN3/5Pp1/4P1P1/6K1/1B6 w - - 0 44", listOf("a7f7"), "Mate In1 Combination"),
        RawTacticalPuzzle("5rk1/p4ppp/4p3/1Q6/1P1BN1b1/8/Pq3PPP/2R1KB1R b K - 0 18", listOf("b2c1"), "Mate In1 in Scandinavian Defense Scandinavian"),
        RawTacticalPuzzle("rnb2rk1/pp3p1p/3p2Pb/4p1q1/3pQ3/5N2/PPP1PPP1/RN2KB1R b KQ - 2 12", listOf("g5c1"), "Mate In1 in Indian Defense Indian"),
        RawTacticalPuzzle("7k/p4R1p/3p3B/2pN1n2/2PbB1b1/3P2P1/P3r3/5R1K b - - 0 28", listOf("f5g3"), "Mate In1 Combination"),
        RawTacticalPuzzle("r2q1rk1/2p3pn/2pbp2p/p2p1p2/P4PQ1/1P1PP3/1BPN2PP/4RR1K w - - 0 16", listOf("g4g7"), "Mate In1 in Bird Opening Bird"),
        RawTacticalPuzzle("r6r/1q2bpk1/7p/p1p1pPpn/Pp2P1nP/1P1B1N2/1BP3P1/3RR1QK b - - 8 30", listOf("h5g3"), "Mate In1 Combination"),
        RawTacticalPuzzle("3r2k1/pQ4pp/4p1n1/2q5/2P5/2B3P1/P4PBP/6K1 w - - 0 25", listOf("b7g7"), "Mate In1 Combination"),
        RawTacticalPuzzle("2r2rk1/5ppp/bq2p3/p2pP1N1/Pb1p2P1/1P2P2P/2QN4/2R1K2R w K - 0 19", listOf("c2h7"), "Mate In1 in French Defense French"),
        RawTacticalPuzzle("5rk1/pp4pR/4p1r1/2qp4/8/2P4Q/PP3RPP/6K1 w - - 1 23", listOf("h7h8"), "Mate In1 Combination"),
        RawTacticalPuzzle("2kr1br1/ppBb1ppp/8/3P2Q1/6n1/5n2/PP3qPP/RN2R2K w - - 0 17", listOf("g5d8"), "Mate In1 in Italian Game Italian"),
        RawTacticalPuzzle("r1bqkb1r/pp1pnppp/2n5/1N2p3/5B2/8/PPP1PPPP/R2QKBNR w KQkq - 0 7", listOf("b5d6"), "Mate In1 in Horwitz Defense Horwitz"),
        RawTacticalPuzzle("b4b1r/3k1ppp/p2p4/1p2p3/3Pq3/N3B3/PP3PPP/R2Q1RK1 b - - 0 16", listOf("e4g2"), "Mate In1 in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("r4rk1/1pp2ppp/p2p4/2bPp3/2P1PB1q/P1N2B2/1P3P2/R2QK1R1 b Q - 0 15", listOf("h4f2"), "Attacking F2 F7 in Mexican Defense Mexican"),
        RawTacticalPuzzle("6k1/5p1p/1p4p1/p1bN1p2/2Pq1P2/1PQ4P/1P4P1/7K b - - 2 32", listOf("d4g1"), "Mate In1 Combination"),
        RawTacticalPuzzle("2k5/1pp2pp1/p3p1p1/3pP2r/Q7/2P1N1P1/PP4P1/2Kn3r w - - 0 21", listOf("a4e8"), "Mate In1 Combination"),
        RawTacticalPuzzle("1qr2rk1/1p1p1ppp/pB2p1n1/7n/2P1P3/1Q2NP1P/PP2BKPb/3R1R2 b - - 2 20", listOf("b8g3"), "Mate In1 Combination"),
        RawTacticalPuzzle("r3k2r/pb1p1ppp/1b4q1/1Q2P3/8/2NP1PP1/PP4P1/R1B2R1K b kq - 0 17", listOf("g6h5"), "Mate In1 in Ruy Lopez Ruy"),
        RawTacticalPuzzle("1rb2k2/p4ppp/2B5/2pr1NP1/2P5/P7/7P/4R1K1 w - - 0 28", listOf("e1e8"), "Mate In1 Combination"),
        RawTacticalPuzzle("8/6kp/4b1q1/1p6/1PpPN2Q/2P1P3/r5P1/5RK1 b - - 0 34", listOf("g6g2"), "Mate In1 Combination"),
        RawTacticalPuzzle("2rq1rk1/1b5p/p3p3/1p1pBpp1/2nP2N1/1RP1PP2/P1Q3PP/3R2K1 w - - 0 23", listOf("g4h6"), "Mate In1 Combination"),
        RawTacticalPuzzle("3k2r1/pR5R/3r4/4p3/7q/3Pn1PP/PP5K/8 w - - 0 28", listOf("b7b8"), "Mate In1 Combination"),
        RawTacticalPuzzle("1rb4r/pp1nb1pp/6k1/3np3/2q1P3/2N2Q2/PPP2PPP/R1B1K2R w KQ - 0 13", listOf("f3f5"), "Mate In1 in Philidor Defense Philidor"),
        RawTacticalPuzzle("r4rk1/4p1bp/3p2p1/q1pP1P2/4QP2/4B3/1pK4P/1B1R3R b - - 1 22", listOf("a5c3"), "Mate In1 Combination"),
        RawTacticalPuzzle("rn3k1r/pp2bp1p/2p1pNp1/6B1/5P2/7P/PPP4P/2K1RR2 w - - 4 18", listOf("g5h6"), "Mate In1 in Pirc Defense Pirc"),
        RawTacticalPuzzle("4r1k1/1p2R1p1/p2p2Pp/P1pP4/8/1R3p2/1P1q3P/5B1K w - - 0 35", listOf("e7e8"), "Hanging Piece Combination"),
        RawTacticalPuzzle("r1bqkb1r/pp2pppp/2p2n2/2P1N3/2BP1n2/8/PP3PPP/RN1QR1K1 w kq - 0 13", listOf("c4f7"), "Attacking F2 F7 in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("3r4/R7/2p5/p1P2p2/1p4k1/nP2K3/P3NP2/8 b - - 4 41", listOf("a3c2"), "Mate In1 Combination"),
        RawTacticalPuzzle("r3r3/1kpR1qpp/p1n2p2/Qp2P2P/1N6/4Pb2/PPP3P1/2K2R2 w - - 1 23", listOf("a5c7"), "Mate In1 Combination"),
        RawTacticalPuzzle("Q4n1k/p2b2pp/3b4/2p5/4Nq2/2Pn3P/PP2BPP1/R1B2RK1 b - - 0 23", listOf("f4h2"), "Mate In1 Combination"),
        RawTacticalPuzzle("r3kb1r/ppqN1ppp/4pn2/1Q3b2/3P4/8/PP2PPPP/RNB1KB1R b KQkq - 0 9", listOf("c7c1"), "Hanging Piece in Queens Pawn Game"),
        RawTacticalPuzzle("2q3k1/4br2/6pQ/1p1n2p1/7P/1P4P1/1B2PP2/6K1 w - - 0 28", listOf("h6h8"), "Mate In1 Combination"),
        RawTacticalPuzzle("3r4/1p4p1/2pB1bBp/p1Pk4/3rp3/P7/1PK2P2/4R3 w - - 2 32", listOf("g6f7"), "Mate In1 Combination"),
        RawTacticalPuzzle("5r1k/8/2b2rQp/1p1p2p1/1q4P1/8/8/1B3R1K w - - 0 37", listOf("g6h7"), "Mate In1 Combination"),
        RawTacticalPuzzle("2k4r/Q1p2pp1/5n2/3rNq2/4p3/6pb/PPP1BP1P/R3R1K1 w - - 0 23", listOf("a7a8"), "Mate In1 Combination"),
        RawTacticalPuzzle("4Q3/6pk/p3p2p/5P2/1p1P4/4q2P/2B1n2B/7K b - - 0 35", listOf("e3f3"), "Mate In1 Combination"),
        RawTacticalPuzzle("8/3B2pp/p5k1/6P1/1ppp1K2/8/1P6/8 w - - 0 39", listOf("d7e8"), "Mate In1 Combination"),
        RawTacticalPuzzle("8/3pk3/R7/1R2PK1p/2PPn1r1/8/8/8 b - - 0 43", listOf("e4g3"), "Mate In1 Combination")
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
            val cleanTheme = "Mate in 1"
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
