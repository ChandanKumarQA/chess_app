package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object WinningMaterialDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("4rr1k/1pR3bp/p4npN/2p2pB1/3p4/3P3Q/PPP2PPP/4R1K1 b - - 0 24", listOf("e8e1"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("5r1k/6pp/3p2p1/pppP2q1/5RP1/3P4/PPPQ4/2K5 w - - 1 26", listOf("f4f8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("5q1k/7p/3Np1bP/4P3/3P2P1/2n5/8/5QK1 w - - 0 46", listOf("f1f8"), "Hanging Piece Combination"),
        RawTacticalPuzzle("3Qq2k/6pp/8/2p5/P3p3/3p3P/1rr2PP1/1R3RK1 w - - 4 30", listOf("d8e8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("R2r2k1/6pp/3N4/1nP5/6P1/1P3P2/P2K2n1/8 w - - 2 31", listOf("a8d8"), "Hanging Piece Combination"),
        RawTacticalPuzzle("r1bk4/pppp3p/2n5/2b1prN1/8/1B6/PPPP2PP/RNB2R1K b - - 2 14", listOf("f5f1"), "Back Rank Mate in Italian Game Italian"),
        RawTacticalPuzzle("rn3k1r/pp2p2p/2p3p1/8/2B1n3/4q3/PQP3PP/RN3K1R w - - 4 15", listOf("b2h8"), "Hanging Piece in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("5r1k/1b4pp/4B3/2Qp4/2r1p3/6P1/4qP1P/2B2RK1 w - - 4 31", listOf("c5f8"), "Hanging Piece Combination"),
        RawTacticalPuzzle("6k1/2p2pp1/4p1np/1R6/3Pb3/2P3B1/5PPP/r3N1K1 b - - 2 24", listOf("a1e1"), "Hanging Piece Combination"),
        RawTacticalPuzzle("1r6/1r3pkp/R3p1p1/2pP4/4P3/5P2/6PP/1R5K b - - 0 27", listOf("b7b1"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("r6r/ppp1kBp1/2np4/4p3/4P1Q1/P2P3q/1PPN1P2/R4RK1 b - - 0 17", listOf("h3g4"), "Hanging Piece in Philidor Defense Philidor"),
        RawTacticalPuzzle("8/6p1/5p1p/R2b2kP/6P1/1r3PK1/8/8 w - - 5 62", listOf("a5d5", "f6f5", "d5f5"), "Hanging Piece Combination"),
        RawTacticalPuzzle("r2q2rk/pp3p1n/2p1b2b/3p3P/3P1Q2/2NB1P2/PPP3P1/2KR3R w - - 3 22", listOf("f4h6", "d8g5", "h6g5"), "Hanging Piece Combination"),
        RawTacticalPuzzle("R1b3k1/4pp2/3p2p1/2pP3p/2P1PP2/2Q2B1P/5qPK/1r6 w - - 0 28", listOf("a8c8", "g8h7", "c3h8"), "Hanging Piece Combination"),
        RawTacticalPuzzle("2k2n1r/prp3pp/8/Q1RP4/2N1Pq2/P7/1P3PPP/2R3K1 b - - 4 28", listOf("f4c1", "a5e1", "c1e1"), "Hanging Piece Combination"),
        RawTacticalPuzzle("5r1k/Q6p/1pb3p1/4q3/4p3/1BP4P/PP4p1/5RK1 w - - 0 31", listOf("f1f8"), "Hanging Piece Combination"),
        RawTacticalPuzzle("6k1/1Q4p1/p1p4p/3pP3/P3bq2/2N4P/1P4P1/5B1K b - - 2 26", listOf("f4f1", "h1h2", "f1g2"), "Hanging Piece Combination"),
        RawTacticalPuzzle("r1bq1rk1/p4pbp/5np1/4p1B1/3pN3/8/PPPQBPPP/2R2RK1 b - - 1 14", listOf("f6e4", "g5d8", "e4d2"), "Hanging Piece in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("4r2k/p4r1p/2pp1b2/2p5/5P2/3P2R1/PqPB2PP/4RK2 w - - 0 22", listOf("e1e8", "f7f8", "e8f8"), "Hanging Piece Combination"),
        RawTacticalPuzzle("8/pk1r2R1/1p2b3/8/2P2N2/1P6/1K6/8 w - - 22 61", listOf("f4e6", "d7g7", "e6g7"), "Hanging Piece Combination"),
        RawTacticalPuzzle("4r1k1/pppq2b1/2bp1Npp/5p2/2P4N/7P/PP1BQPP1/4R1K1 b - - 1 24", listOf("g7f6", "e2e8", "d7e8", "e1e8", "c6e8"), "Hanging Piece Combination"),
        RawTacticalPuzzle("r7/1p4pk/2p1n3/p2p1nPp/7P/3P1P1B/PP6/2K4R w - - 0 24", listOf("h3f5", "g7g6", "f5e6"), "Fork Combination"),
        RawTacticalPuzzle("3r2k1/pp3ppp/4p3/2N5/7q/1Q3P2/PP1R2PP/4R2K b - - 0 24", listOf("h4e1"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("3q4/1p6/p4N2/5QP1/4Pn1k/2P4r/1P3K2/8 w - - 5 41", listOf("f5f4"), "Hanging Piece Combination"),
        RawTacticalPuzzle("2r1k3/2p1rpp1/1p1p1b1B/p2P1P1P/P1P5/3K4/5PR1/6R1 b - - 0 30", listOf("g7h6", "g2g8", "e8d7", "g8c8", "d7c8"), "Hanging Piece Combination"),
        RawTacticalPuzzle("3r2k1/p1p2ppp/2B1b3/4q3/N4n1Q/8/P4PPP/5RK1 w - - 6 21", listOf("h4d8"), "Back Rank Mate Combination"),
        RawTacticalPuzzle("rn1qk2r/ppp2ppp/5b2/3P4/4P3/5Q1P/PPP2PP1/RN2KB1R b KQkq - 0 10", listOf("f6b2", "f3b3", "b2a1"), "Trapped Piece in Elephant Gambit Elephant"),
        RawTacticalPuzzle("r3kb1r/pp1q1ppp/4p3/4PR2/3pN3/2P4P/PP4P1/R1BQ2K1 b kq - 0 15", listOf("e6f5", "e4d6", "f8d6"), "Hanging Piece in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("8/7p/4k3/pb1p1pPB/1n1P3P/N1p1P3/4K3/8 w - - 2 43", listOf("a3b5", "c3c2", "e2d2"), "Hanging Piece Combination"),
        RawTacticalPuzzle("8/kb5R/2pp1p2/4qP2/3pPN2/1QbP4/B1P5/2K5 b - - 2 38", listOf("e5f4", "c1b1", "f4f1"), "Hanging Piece Combination"),
        RawTacticalPuzzle("3N1r2/R7/kp6/p2pPp1Q/2pP2P1/2q5/2P5/2K5 b - - 1 38", listOf("a6a7", "h5h7", "a7a6"), "Hanging Piece Combination"),
        RawTacticalPuzzle("3r1rk1/p2nppbp/2p2np1/q1Pp4/p2P1B2/PP1BP2P/5PP1/R2Q1RK1 w - - 0 16", listOf("b3b4", "a5b5", "d3b5"), "Trapped Piece in Queens Pawn Game"),
        RawTacticalPuzzle("8/2p5/1p2B3/p7/5r2/P4p2/1PP1N2k/1K4R1 b - - 2 59", listOf("f3e2", "g1e1", "f4f1", "b1c1", "f1e1"), "Hanging Piece Combination"),
        RawTacticalPuzzle("r2qb2r/6k1/p2p1p2/1ppN1Pp1/4P1Q1/2PP2PP/P7/3R1RK1 b - - 0 29", listOf("e8h5", "g4h5", "h8h5"), "Trapped Piece Combination"),
        RawTacticalPuzzle("8/1q1r1k1p/p1p1p1p1/2p2pRn/Q1P2P1P/3PPP2/P2rK3/6R1 w - - 0 27", listOf("e2d2", "b7b2", "a4c2"), "Hanging Piece Combination"),
        RawTacticalPuzzle("r3k2r/p1bN2pp/2p1Rp2/3p3b/3P1q2/2N4P/PPPQ1PP1/R5K1 b kq - 0 16", listOf("e8d7", "d2f4", "c7f4"), "Hanging Piece in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("1k6/p1p5/P2p4/3P4/1PK2r1p/4P3/8/4B3 w - - 0 58", listOf("e3f4", "h4h3", "e1g3"), "Hanging Piece Combination"),
        RawTacticalPuzzle("3b4/3P4/pp2Pk2/5Q2/P6p/8/8/7K b - - 0 43", listOf("f6f5", "e6e7", "d8e7", "d7d8q", "e7d8"), "Hanging Piece Combination"),
        RawTacticalPuzzle("8/pkp5/2p1p3/3p4/N2q4/1P4Q1/1PP3Pr/K2R4 b - - 0 29", listOf("d4d1", "a1a2", "h2h1", "a4c5", "b7b6"), "Hanging Piece Combination"),
        RawTacticalPuzzle("r2qk2r/pp1n1ppp/4pn2/3N2B1/1b1P4/4QN1P/PPb1BPP1/R4RK1 b kq - 0 12", listOf("f6d5", "g5d8", "d5e3"), "Hanging Piece in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("2rqk2r/3n4/1p2p3/p3N2R/Q1PP2p1/P2BP3/3K1Pb1/R7 b k - 0 22", listOf("h8h5", "d3g6", "e8e7", "a4d7", "d8d7"), "Hanging Piece Combination"),
        RawTacticalPuzzle("r3r1k1/1p3pnp/p1pq2p1/3pN3/3P2b1/1PPQ4/P1B2PPP/R3R1K1 w - - 4 28", listOf("e5g4", "e8e1", "a1e1"), "Hanging Piece Combination"),
        RawTacticalPuzzle("r1b1k1nr/ppp2pbp/3p1qp1/4p3/2BnP3/N2P2QP/PPP2PP1/R1B1K2R w KQkq - 0 10", listOf("c1g5", "f6g5", "g3g5"), "Trapped Piece in Pirc Defense Pirc"),
        RawTacticalPuzzle("r1bq1rk1/pp1p1ppp/4pn2/4n3/2B5/2B5/PPP2PPP/R2QK1NR w KQ - 0 10", listOf("c3e5", "d8a5", "e5c3"), "Hanging Piece in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("3r3r/pQNk1ppp/1qnR1n2/1B6/8/8/PPP3PP/5R1K b - - 0 19", listOf("d7d6", "b7b6", "a7b6"), "Hanging Piece Combination"),
        RawTacticalPuzzle("r3r1k1/ppp2ppp/2nbpq2/5b2/3P4/2P1BN2/P1PQBPPP/3R1RK1 w - - 8 12", listOf("e3g5", "f6g6", "f3h4", "h7h6", "h4g6"), "Trapped Piece in Scandinavian Defense Scandinavian"),
        RawTacticalPuzzle("r2qr1k1/pp3ppp/2pb1nb1/3p4/3P2PN/2PBB2P/PPQN1n2/2KR3R w - - 0 15", listOf("e3f2", "g6d3", "c2d3"), "Hanging Piece in French Defense French"),
        RawTacticalPuzzle("r5k1/5pp1/p3qb1p/1pp5/8/P1N1PQ2/1P1r1PPP/3R2K1 w - - 0 25", listOf("f3a8", "d2d8", "d1d8"), "Hanging Piece Combination"),
        RawTacticalPuzzle("r1b2bnr/ppQp3p/2n2q1k/6pP/2B1P3/2PP4/PP3PP1/RNB1K2R b KQ - 0 11", listOf("f8d6", "c7d6", "f6d6"), "Trapped Piece in Kings Pawn Game"),
        RawTacticalPuzzle("r6k/6p1/p6r/1p1Qp3/7n/2P1qP1b/PP3R1P/3RN2K w - - 0 34", listOf("d5a8", "h8h7", "a8e4", "e3e4", "f3e4"), "Hanging Piece Combination"),
        RawTacticalPuzzle("8/2p5/pp1p4/3P1N2/PPP1Pp2/5n1p/5K1k/8 w - - 0 47", listOf("f2f3", "h2g1", "f3f4", "h3h2", "f5g3"), "Hanging Piece Combination"),
        RawTacticalPuzzle("r2qk2r/2pn1p1n/pp1p2Bp/3Pp1b1/PPP1P3/2N1B3/3N2PP/R2Q1RK1 b kq - 0 17", listOf("g5e3", "g1h1", "f7g6"), "Hanging Piece in St George Defense"),
        RawTacticalPuzzle("r2qkbr1/pp2pp1p/2n2p2/3p4/3P4/2PB1b2/PP2N1PP/RN1QK1R1 w Qq - 0 11", listOf("g2f3", "g8g1", "e2g1"), "Hanging Piece in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("4r1k1/1ppbrppB/7p/p2P4/3q1P2/P4P2/1P1Q2PP/3RR2K b - - 6 26", listOf("g8h7", "e1e7", "d4d2", "d1d2", "e8e7"), "Hanging Piece Combination"),
        RawTacticalPuzzle("r3kb1r/1p4p1/p1p1bp2/4p1Bp/4P3/2NQ1PP1/PPP2qBP/R6K w kq - 0 17", listOf("g5e3", "e6c4", "e3f2", "c4d3", "c2d3"), "Trapped Piece in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("B4rk1/p3b1pp/1q2bp2/1p2p3/2p2P2/3PR1P1/PPPN3P/R1BQ2K1 b - - 0 16", listOf("b6e3", "g1g2", "f8a8"), "Hanging Piece in Kings Indian Attack"),
        RawTacticalPuzzle("r3k2r/pppqbppp/2n1b3/3p2PQ/PP1Pp2P/2P1P3/5PB1/RNB1K1NR b KQkq - 0 11", listOf("e6g4", "g2h3", "g4h5", "h3d7", "e8d7"), "Trapped Piece in Grob Opening Grob"),
        RawTacticalPuzzle("3r2k1/pp4bp/4qpp1/3Pp3/8/4Q2P/4B1P1/2rR3K w - - 0 27", listOf("d5e6", "d8d1", "e2d1", "c1d1", "h1h2"), "Hanging Piece Combination"),
        RawTacticalPuzzle("8/ppp1kpp1/3b2np/3p3b/3P1B2/2P4P/PPBN1PP1/5K2 w - - 1 24", listOf("f4d6", "e7d6", "g2g4", "g6f4", "g4h5"), "Trapped Piece Combination"),
        RawTacticalPuzzle("3r1rk1/pp2bppp/2ppnn2/8/N1P1P3/q1P4P/P2N2PB/R2Q1R1K w - - 1 17", listOf("d2b1", "a3a4", "d1a4"), "Trapped Piece in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("2r3k1/1p3ppp/4p3/Q7/3q4/P1B5/1Pr2PPP/R5K1 w - - 0 23", listOf("c3d4", "c2c1", "a5e1"), "Hanging Piece Combination"),
        RawTacticalPuzzle("5rk1/5ppp/4b3/1p1pPpPP/2pP4/b1P5/rqNQKP2/2RRN3 w - - 6 24", listOf("c1b1", "b2b3", "b1b3"), "Trapped Piece Combination"),
        RawTacticalPuzzle("4r1k1/4Pp2/pQ3b1p/6pP/q1p5/P4P2/5BPK/8 w - - 3 32", listOf("b6f6", "c4c3", "f2d4", "a4d4", "f6d4"), "Hanging Piece Combination"),
        RawTacticalPuzzle("r4rk1/pp3pBp/4p3/3p2qB/Q1p5/2PbP3/PP1N1P1P/R3K2R b KQ - 0 15", listOf("g5h5", "a4d1", "h5d1", "a1d1", "g8g7"), "Hanging Piece in Queens Pawn Game"),
        RawTacticalPuzzle("k1Q4r/3n1p1p/1p1Bpnp1/q7/2BP4/2P5/1PK2P1P/3R2R1 b - - 0 29", listOf("h8c8", "d1a1", "c8c4", "a1a5", "b6a5"), "Hanging Piece Combination"),
        RawTacticalPuzzle("Qr5k/5pp1/5N1p/4q3/4P3/8/7P/R6K b - - 7 36", listOf("g7f6", "a8b8", "e5b8"), "Hanging Piece Combination"),
        RawTacticalPuzzle("rnbqk2r/ppp2ppp/3bp3/8/4n3/1BN5/PPPP2PP/R1BQK1NR w KQkq - 0 7", listOf("c3e4", "d8h4", "e4f2"), "Hanging Piece in French Defense French"),
        RawTacticalPuzzle("8/pp1r2kp/q2P1ppb/4N3/4P3/1Q5P/PPR2PP1/6K1 b - - 0 32", listOf("f6e5", "c2c7", "a6d6"), "Hanging Piece Combination"),
        RawTacticalPuzzle("rnbqk1nr/ppp1b1pp/3p4/4N3/2B1P3/8/PPPP2PP/RNBQK2R b KQkq - 0 6", listOf("d6e5", "d1h5", "g7g6", "h5e5", "g8f6"), "Hanging Piece in Kings Gambit Kings"),
        RawTacticalPuzzle("r6k/pp2r2p/4Rp1Q/3p4/8/1N1P2b1/PqP3PP/7K w - - 0 25", listOf("e6e7", "b2b1", "b3c1", "b1c1", "h6c1"), "Hanging Piece Combination"),
        RawTacticalPuzzle("8/5p2/5pk1/8/4PPr1/8/5K2/7R w - - 3 47", listOf("f2f3", "g4g5", "f4g5"), "Trapped Piece Combination"),
        RawTacticalPuzzle("4r3/4bpkp/3P2pN/6P1/1p2qn1P/5N2/1P1Q1P2/3R2K1 b - - 0 30", listOf("e4f3", "d2d4", "f7f6"), "Hanging Piece Combination"),
        RawTacticalPuzzle("4r1k1/p5pp/1p3n2/8/1q3B2/3Q1P2/PP3P1P/2R3K1 b - - 6 24", listOf("b4f4", "d3c4", "f4c4"), "Hanging Piece Combination"),
        RawTacticalPuzzle("3r2k1/p3ppb1/b3q1pp/2p5/2N5/1r1RB3/P4PPP/2R1N1K1 w - - 0 22", listOf("d3d8", "g8h7", "a2b3"), "Hanging Piece Combination"),
        RawTacticalPuzzle("r1bq3k/pppnp2r/5nQb/3p2N1/8/3B4/PPPP1PPP/RNB1K2R b KQ - 3 11", listOf("d7e5", "g6h6", "h7h6"), "Trapped Piece in Duras Gambit Duras"),
        RawTacticalPuzzle("8/b6p/P1K5/2p1k1p1/6P1/8/1P3P2/8 w - - 2 50", listOf("c6b7", "e5d6", "b7a7", "d6c7", "b2b3", "h7h6", "f2f3"), "Trapped Piece Combination"),
        RawTacticalPuzzle("r1b2rk1/pp3ppp/5n2/1N1p4/B7/7P/q4PP1/2RQR1K1 w - - 0 19", listOf("e1e2", "a2e2", "d1e2"), "Trapped Piece in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("3r1rk1/1b3qn1/pp1Q2p1/2p1n1Pp/P1P1Pp1N/2P2B1P/5B1K/R4R2 w - - 2 26", listOf("d6e5", "d8e8", "e5d6"), "Hanging Piece Combination"),
        RawTacticalPuzzle("1Q2n1kr/B4ppp/q7/bb6/4p3/2P1P3/P3NPPP/RN2K2R b KQ - 0 21", listOf("a5c7", "b8a8", "b5c6", "a8c6", "a6c6"), "Trapped Piece Combination"),
        RawTacticalPuzzle("7k/ppp1nrp1/3pN3/7p/3P3P/2P2RK1/PP3QP1/1q5r w - - 0 27", listOf("f3f7", "b1d3", "f2f3"), "Hanging Piece Combination"),
        RawTacticalPuzzle("1N4k1/7p/PR1n2r1/1b6/1P3R2/3N3r/1P4P1/6K1 b - - 0 45", listOf("h3d3", "a6a7", "d3d1"), "Hanging Piece Combination"),
        RawTacticalPuzzle("3r4/ppp1Q3/1b3P2/3k4/4qp2/P1Pr4/1P3P2/1K2N3 w - - 6 32", listOf("e7d8", "d5c6", "e1d3"), "Hanging Piece Combination"),
        RawTacticalPuzzle("3q1rk1/3R1ppp/r5b1/p1bN4/6P1/5N1P/P1P2P2/3QR1K1 b - - 2 18", listOf("d8d7", "d5f6", "a6f6", "d1d7", "f6f3"), "Hanging Piece in French Defense French"),
        RawTacticalPuzzle("6rr/1p1k1p2/3b4/3p4/3q1P2/2N4P/2QB1RPK/R7 b - - 3 32", listOf("d4f2", "c2f5", "d7c7", "c3d5", "c7b8"), "Hanging Piece Combination"),
        RawTacticalPuzzle("r3k2r/p3pp1p/6pb/qp6/P1pnQ2N/2n3B1/1P3PPP/R3KB1R w KQkq - 0 17", listOf("e4a8", "e8d7", "a8b7"), "Hanging Piece in Queens Pawn Game"),
        RawTacticalPuzzle("r3rRk1/pp6/3p1B1P/2pP1b2/1qn5/2N5/PP2QPP1/2K4R b - - 2 24", listOf("g8f8", "h6h7", "f5h7"), "Hanging Piece Combination"),
        RawTacticalPuzzle("6k1/5p2/4p3/P1B5/2P4P/4Pnp1/Rb2r3/5K2 w - - 0 34", listOf("f1e2", "g3g2", "e3e4", "f3d4", "e2f2"), "Hanging Piece Combination"),
        RawTacticalPuzzle("8/6k1/8/1K1np1p1/P5P1/1B3r2/1PP5/8 w - - 2 37", listOf("b3d5", "f3f4", "a4a5", "e5e4", "a5a6", "e4e3", "a6a7"), "Hanging Piece Combination"),
        RawTacticalPuzzle("2k5/1pp1r2Q/pr2p3/3p1p2/q2b4/P2NP3/1PP3PP/K3RR2 w - - 0 26", listOf("h7e7", "d4b2", "d3b2"), "Hanging Piece Combination"),
        RawTacticalPuzzle("r1b4r/pppkn2p/3pNQ2/8/2BpP2p/8/PPPq2PP/R5K1 w - - 3 22", listOf("f6h8", "d7c6", "h8e8"), "Hanging Piece Combination"),
        RawTacticalPuzzle("3r3r/Qbk5/Rp1p3p/1P1qpp2/6p1/2P2NP1/3N1PP1/5RK1 b - - 2 25", listOf("d5b5", "a6a4", "d8a8", "a4c4", "b5c4", "a7b7", "c7b7"), "Trapped Piece Combination"),
        RawTacticalPuzzle("8/3k2P1/3PQ3/p1q2p2/1p6/1P4r1/P1P5/1K1R4 b - - 0 39", listOf("d7e6", "d6d7", "g3g2", "g7g8b", "g2g8"), "Hanging Piece Combination"),
        RawTacticalPuzzle("8/1p6/1P3r2/1Rp3K1/3p4/4k3/8/8 w - - 0 45", listOf("g5f6", "c5c4", "b5e5"), "Hanging Piece Combination"),
        RawTacticalPuzzle("2rq1rk1/7p/1n4pb/1R2Q3/pPpP1P2/P1B5/3N2PP/2R3K1 b - - 0 31", listOf("f8e8", "e5e8", "d8e8"), "Trapped Piece Combination"),
        RawTacticalPuzzle("2r3k1/p4pp1/Qq2p2p/b1Np4/2nP1P2/4P1P1/5K1P/2B1N3 w - - 4 34", listOf("a6c8", "g8h7", "c8b7"), "Hanging Piece Combination"),
        RawTacticalPuzzle("1r2q1k1/b1p2p1p/p4p2/2Pp1b2/2nP1Q2/P1N2PP1/1B4BP/5RK1 b - - 1 25", listOf("b8b2", "f4f5", "b2g2"), "Hanging Piece Combination"),
        RawTacticalPuzzle("r2q1rk1/2B2nbp/3p2p1/4p1N1/4P1P1/1bN4P/PP1Q1P2/2KR3R b - - 0 18", listOf("d8c7", "a2b3", "f7g5"), "Hanging Piece in Queens Pawn Game"),
        RawTacticalPuzzle("r4r2/ppk4p/n2N2p1/2p1P3/5b2/2P4P/PP2B2R/1K1R4 b - - 0 25", listOf("f4h2", "d6b5", "c7b6", "d1d6", "b6a5", "b5a3", "f8f4"), "Hanging Piece Combination"),
        RawTacticalPuzzle("r2qr1k1/pn1p2pp/bp3R2/2p1N3/2P5/1PB3Q1/P1P3PP/R5K1 b - - 0 18", listOf("d8f6", "a1f1", "f6h6", "e5g4", "h6g6", "c3g7", "e8e4", "g4f6", "g8g7"), "Hanging Piece in Bishops Opening Bishops"),
        RawTacticalPuzzle("4r1k1/2q1Ppp1/2p2R2/p4NpP/8/PQ6/2P5/2K5 b - - 0 31", listOf("g7f6", "f5h6", "g8h8", "h6f7", "h8g7", "h5h6", "g7g6"), "Hanging Piece Combination")
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
            val cleanTheme = "Winning Material"
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
