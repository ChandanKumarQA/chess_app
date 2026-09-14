package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object DoubleAttackDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("2krr3/1pp3p1/p4p2/5P2/5P2/7P/PPQp2R1/1R1KBB2 b - - 0 30", listOf("d2e1q"), "Discovered Check Combination"),
        RawTacticalPuzzle("r2qkbr1/pp1bpp1p/3p4/3Qp3/2B5/2N5/PPP2P1P/2KR3R w q - 2 13", listOf("d5f7"), "Attacking F2 F7 in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("rn3rk1/ppp3p1/4pq1p/8/Q2NB1b1/P1N3P1/1P3PP1/2R1K2R b K - 0 18", listOf("f6f2"), "Attacking F2 F7 in Queens Pawn Game"),
        RawTacticalPuzzle("2rqk2r/3n1ppp/p1pbp3/3pN3/N1PPb1P1/5Q1P/PP1B1P2/2R1R1K1 w k - 1 18", listOf("f3f7"), "Attacking F2 F7 in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("rn1qkb1r/pp2pp1b/2p1Pn1p/3p2pQ/3P3N/8/PPP2PPP/RNB1KB1R w KQkq - 2 8", listOf("h5f7"), "Attacking F2 F7 in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("rnb1k1nr/1ppp1ppp/8/p1bPp3/4P2q/2PB1N2/PP3PPP/RNBQK2R b KQkq - 4 6", listOf("h4f2"), "Attacking F2 F7 in Queens Pawn Game"),
        RawTacticalPuzzle("r2qkb1r/2p1pppp/p4nb1/1N2n3/B2P2P1/7P/PPP5/R1BQK2R w KQkq - 0 12", listOf("b5c7"), "Double Check in Blackmar-Diemer Gambit Accepted"),
        RawTacticalPuzzle("rnb1k2r/ppB2p2/8/3p2p1/3Q2np/2N2N1K/PPP1B1PP/R6R b kq - 1 14", listOf("g4f2"), "Discovered Check in Queens Pawn Game"),
        RawTacticalPuzzle("r2qkb1r/pppnpppp/8/1B6/3n4/2N2Q1P/PPP3P1/R1B2RK1 w kq - 0 10", listOf("f3f7"), "Attacking F2 F7 in Blackmar-Diemer Gambit Accepted"),
        RawTacticalPuzzle("r1b1kb1r/pp2pppp/1qnp4/6B1/4P1n1/1NN5/PPP2PPP/R2QKB1R b KQkq - 7 8", listOf("b6f2"), "Attacking F2 F7 in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("8/pR6/5ppk/8/2P4p/1P3P1P/P4BP1/4rn1K b - - 1 43", listOf("f1g3", "h1h2", "e1h1"), "Discovered Check Combination"),
        RawTacticalPuzzle("r1b2rk1/pp2bppp/4p3/5qN1/3Pn2P/2BB4/PP3PP1/R2QK2R b KQ - 6 15", listOf("f5f2"), "Attacking F2 F7 in Zukertort Opening Zukertort"),
        RawTacticalPuzzle("r1bqk2r/pp2bpp1/2p1p2p/4N3/3PnQ2/2P3P1/PP3PBP/R3K2R w KQkq - 0 14", listOf("f4f7"), "Attacking F2 F7 in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("r4rk1/pp2ppBp/1qnp2p1/8/2P1P1n1/1P3N2/P1B2PPP/R2QK2R b KQ - 0 14", listOf("b6f2"), "Attacking F2 F7 in Pirc Defense Pirc"),
        RawTacticalPuzzle("N1bk3r/pp1p1ppp/2n5/2b1P3/2B1P3/1RP2N2/q4PPP/3QK2R b K - 2 15", listOf("a2f2"), "Attacking F2 F7 in Englund Gambit Complex"),
        RawTacticalPuzzle("rn2k1nr/p1pp1p2/4p3/1p6/4P1pq/5Q2/PPPP2KP/RNB2R2 w kq - 0 12", listOf("f3f7", "e8d8", "f7f8"), "Attacking F2 F7 in Zukertort Opening Zukertort"),
        RawTacticalPuzzle("r3k2r/1bBp1p2/pp2p3/2b3P1/2PnP1p1/2N5/PPB3P1/R2Q1RK1 b kq - 0 16", listOf("d4e2"), "Double Check in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("r3kb1r/pp3pp1/1qn1P1np/3Q4/6PB/1P5P/P4Pp1/R4RK1 w kq - 0 19", listOf("e6f7"), "Attacking F2 F7 in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("r1b1kb1r/p1qnpppp/2p5/6N1/1p1P1p2/1BN5/PPP2PPP/R3K2R w KQkq - 0 12", listOf("b3f7", "e8d8", "g5e6"), "Attacking F2 F7 in Czech Defense Czech"),
        RawTacticalPuzzle("2rQN1k1/1p3pb1/4p1p1/3pP3/3P3p/1B5P/PP3KP1/2r2n2 w - - 6 32", listOf("e8f6"), "Double Check Combination"),
        RawTacticalPuzzle("3RN1k1/4bp1p/6p1/4p1P1/2n1P3/8/1P3r2/2K5 w - - 0 39", listOf("e8f6", "g8g7", "d8g8"), "Discovered Check Combination"),
        RawTacticalPuzzle("r1b1k2r/ppp2pp1/2p2n2/2b4p/3qP3/2N2N1P/PPPP1PP1/R1BQK2R b KQkq - 2 8", listOf("d4f2"), "Attacking F2 F7 in Russian Game Russian"),
        RawTacticalPuzzle("r2qkb1r/pp1npppp/2p2n2/4N3/2B5/6N1/PPPP1PPP/R1BbK2R w KQkq - 0 8", listOf("c4f7"), "Attacking F2 F7 in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("rn1qkbnr/pp2pppp/2p5/4N3/2BP4/8/PPP3PP/RNBbK2R w KQkq - 0 7", listOf("c4f7"), "Attacking F2 F7 in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("r3k2r/1pp5/p1b1pq1b/3pB1Np/3P2nP/8/PPP2PP1/RN1QK2R b KQkq - 1 14", listOf("f6f2"), "Attacking F2 F7 in French Defense French"),
        RawTacticalPuzzle("r1b2r2/ppp1Npbk/3p1np1/6BP/3pqP2/4P2R/PPP1Q3/R3K1N1 w Q - 1 17", listOf("h5g6"), "Discovered Check in Indian Defense Indian"),
        RawTacticalPuzzle("r2qk2r/pp3p1p/2n1p1pB/3pN3/3P4/4PQ2/P2n1PPP/R4RK1 w kq - 4 16", listOf("f3f7"), "Attacking F2 F7 in Slav Defense Slav"),
        RawTacticalPuzzle("r1b1kb1N/ppp3pp/2np1q2/4p3/2B1P1n1/2N3P1/PPPP1P1P/R1BQK2R b KQq - 0 8", listOf("f6f2"), "Attacking F2 F7 in Four Knights Game"),
        RawTacticalPuzzle("r1bqk1r1/ppp2p2/2np1n1p/4p1pQ/2B1P2N/2PPb2P/PP3PP1/RN3RK1 w q - 0 11", listOf("h5f7"), "Attacking F2 F7 in Italian Game Italian"),
        RawTacticalPuzzle("r1b1k1nN/pppp2pp/4nq2/2b5/2B1P3/8/PPP2PPP/RNBQK2R b KQq - 0 7", listOf("f6f2"), "Attacking F2 F7 Combination"),
        RawTacticalPuzzle("r1b1kb1r/pppn1ppp/2n5/4P1B1/1qBp4/1Q3N2/PP3PPP/RN3RK1 w kq - 3 10", listOf("c4f7"), "Attacking F2 F7 in Queens Gambit Accepted"),
        RawTacticalPuzzle("rnbBk2r/ppp3pp/8/2bp4/4n3/2N2N2/PPP1PPPP/R2QKB1R b KQkq - 0 7", listOf("c5f2"), "Attacking F2 F7 in Englund Gambit Englund"),
        RawTacticalPuzzle("r4rk1/1p3pp1/p3bR1p/2q1p3/3nP3/P1BB2QP/1PP3PN/R5K1 b - - 0 19", listOf("d4e2", "g1f1", "e2g3"), "Discovered Check Combination"),
        RawTacticalPuzzle("8/8/5p2/4bPpP/3pp1B1/2kn3K/P7/6Q1 b - - 1 49", listOf("d3f4", "h3g3", "f4e2", "g3g2", "e2g1"), "Discovered Check Combination"),
        RawTacticalPuzzle("r1b1k2r/1pp2pp1/p4n1p/4p2P/P1Pp2nR/1P2PqP1/3P1P2/RN1QKBN1 b Qkq - 5 16", listOf("f3f2"), "Attacking F2 F7 in Kadas Opening Kadas"),
        RawTacticalPuzzle("r2qkb1r/1p2pppp/p2p1n2/2p1N3/P1B1P3/3P3P/1PPN1PP1/R1Bb1RK1 w kq - 0 10", listOf("c4f7"), "Attacking F2 F7 in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("r4rk1/ppp5/2b2pN1/3q4/3PnpQ1/2P5/PP4P1/R3R1K1 w - - 0 21", listOf("g6e7", "g8f7", "e7d5"), "Discovered Check Combination"),
        RawTacticalPuzzle("b6r/4Q1p1/p3p1k1/2p2p1p/6R1/P5BK/1P5P/8 b - - 0 38", listOf("h5g4"), "Discovered Check Combination"),
        RawTacticalPuzzle("r1b2rk1/p4pp1/1bN1p2p/8/1PPq4/P2B3P/5PP1/R2QK2R b KQ - 0 18", listOf("d4f2"), "Attacking F2 F7 in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("rn1qkbnr/ppp1ppp1/3p3p/4N3/2B1P3/8/PPPP1PPP/RNBbK2R w KQkq - 0 5", listOf("c4f7"), "Attacking F2 F7 in Zukertort Opening Zukertort"),
        RawTacticalPuzzle("rnb1r1k1/pp2npp1/7p/q1b4Q/2B5/2N5/PPP1NPPP/R1B1K2R b KQ - 1 12", listOf("c5f2", "e1f2", "a5h5"), "Attacking F2 F7 in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("2b1rr1k/3q2pB/p1p5/PpPp4/1P1b1Np1/8/2Q2P1P/R4RK1 w - - 0 22", listOf("f4g6", "h8h7", "g6f8", "h7g8", "f8d7"), "Discovered Check Combination"),
        RawTacticalPuzzle("rn1qk2r/pb3ppp/5n2/2b5/8/2N1p1P1/PP1PPPNP/R1BQKB1R b KQkq - 1 9", listOf("e3f2"), "Attacking F2 F7 in English Opening English"),
        RawTacticalPuzzle("r2qkb1r/3npppp/p4n2/1p6/2BP2b1/1Q3N2/PP1N1PPP/R1B1K2R w KQkq - 0 10", listOf("c4f7"), "Attacking F2 F7 in Queens Pawn Game"),
        RawTacticalPuzzle("r2qkbnr/1p2pppp/p1n5/4N3/2BP4/8/PP3PPP/RNBb1RK1 w kq - 0 9", listOf("c4f7"), "Attacking F2 F7 in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("r2qkbr1/pp2pp1p/5n2/2p1Nb2/2Q5/4B3/PP1N1PPP/n2K1B1R w q - 0 13", listOf("c4f7"), "Attacking F2 F7 in Borg Defense Borg"),
        RawTacticalPuzzle("rn1qkbnr/ppp2ppp/8/8/2Bp4/1Q2Pb2/PP3PPP/RNB1K2R w KQkq - 0 7", listOf("c4f7", "e8d7", "b3e6"), "Attacking F2 F7 in Queens Gambit Accepted"),
        RawTacticalPuzzle("r3k2r/pp1bbpR1/1q2p2p/3p3Q/4n3/2NB1P1P/PPPB1P2/R3K3 b Qkq - 2 17", listOf("b6f2", "e1d1", "f2d2"), "Attacking F2 F7 in Zukertort Opening Zukertort"),
        RawTacticalPuzzle("r1b2rk1/1p2bppp/pqnp4/3Np3/2B1PP2/PN6/1PP2nPP/R1BQ1RK1 b - - 6 13", listOf("f2h3", "g1h1", "b6g1", "f1g1", "h3f2"), "Discovered Check in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("2r2rk1/3Q1ppp/pq2p3/1p2N3/1P3P2/P1P5/5nPP/RNB2RK1 b - - 4 18", listOf("f2h3", "g1h1", "b6g1", "f1g1", "h3f2"), "Discovered Check in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("rnbqk2r/pp3ppp/3b4/2p5/2B5/2N2Q2/PP1Pn1PP/R1B1K2R w KQkq - 0 10", listOf("f3f7"), "Attacking F2 F7 in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("7r/p6p/1b3Q1p/8/2qn1Nk1/5P2/PP6/R4RK1 b - - 0 27", listOf("d4f3", "g1h1", "c4f4", "f6f4", "g4f4"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r2qbr1k/p1p3pp/5p2/2bN1P1Q/5R2/1B4B1/PPP3PP/7K w - - 3 22", listOf("h5h7", "h8h7", "f4h4", "e8h5", "h4h5", "h7g8", "d5e7"), "Attraction Combination"),
        RawTacticalPuzzle("2r3kb/4pp1p/3p1npB/4PN1P/6P1/Pp3P2/2qQ4/K2R3R b - - 0 26", listOf("b3b2", "a1a2", "b2b1q"), "Discovered Check Combination"),
        RawTacticalPuzzle("r3kb1r/1p2pppp/pqPp4/8/B3n1b1/5N2/PP1B1PPP/RN1QK2R b KQkq - 0 11", listOf("b6f2"), "Attacking F2 F7 in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("r1b2r2/pp2q1pk/2p5/2P1n1Pn/4N2P/4P3/PPQ2P2/2R1K2R w K - 1 20", listOf("e4f6", "h7h8", "c2h7"), "Discovered Check in Queens Gambit Declined"),
        RawTacticalPuzzle("r5r1/1p1k1p2/p3b3/3Bb3/8/P2Q3q/1P3PP1/R4RK1 w - - 0 25", listOf("d5e6", "d7e6", "d3h3"), "Attraction Combination"),
        RawTacticalPuzzle("r1b1k1nr/pppp2pp/3b1p2/3N2B1/3p4/8/PP1KBPPP/4R2R w kq - 0 12", listOf("e2h5", "e8f8", "e1e8"), "Discovered Check in Kings Pawn Game"),
        RawTacticalPuzzle("r3k2r/pp3pbp/2p3p1/4nb2/1qB1QB2/2N1P2P/P1P2PP1/R3K2R w KQkq - 0 14", listOf("c4f7", "e8f7", "e4b4"), "Attacking F2 F7 in Queens Pawn Game"),
        RawTacticalPuzzle("r2qk2r/p4p1b/2n1Pnpp/1p1p4/2pP1NQ1/P1P3P1/1P1B1P1P/R3R2K w kq - 1 18", listOf("e6f7", "e8f7", "g4e6", "f7g7", "e6c6"), "Attacking F2 F7 in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("r2qr1k1/ppp2p1p/5pPB/8/6Q1/2P5/P1p3PP/bN3K1R w - - 0 20", listOf("g6h7", "g8h7", "g4g7"), "Attraction in Kings Knight Opening"),
        RawTacticalPuzzle("r3qr2/2pn3k/p2p1P1b/1p4pp/3PN3/P2Q4/1PP2P2/2K4R w - - 1 23", listOf("e4g5", "h7h8", "d3h7"), "Discovered Check Combination"),
        RawTacticalPuzzle("r1b1r1k1/1pN1bppp/p1n2n2/8/8/P2P3P/BPP2PP1/R1B1K1NR b KQ - 0 12", listOf("e7b4", "e1d1", "e8e1"), "Discovered Check in Alekhine Defense Alekhine"),
        RawTacticalPuzzle("r3k2r/4ppbp/2Pp2p1/1B2n3/1p6/1P3P2/2PQ2PP/B1KR3R b kq - 0 20", listOf("a8a1", "c1b2", "e5c4"), "Discovered Check Combination"),
        RawTacticalPuzzle("r4k1r/1pp2pp1/1b1p1P2/pP6/P2PNnq1/1Q3N1p/5P2/R1B1RB1K b - - 1 21", listOf("g4g2", "f1g2", "h3g2", "h1g1", "h8h1"), "Discovered Check Combination"),
        RawTacticalPuzzle("r3r1k1/pp3pB1/2q3pp/3R4/8/P4Q2/BP3PPP/1b4K1 w - - 0 24", listOf("f3f7", "g8f7", "d5d7"), "Attraction Combination"),
        RawTacticalPuzzle("r1b2rk1/pppp1ppp/8/8/1KBbP3/3Q2N1/PPq4P/R1B4R b - - 1 15", listOf("a7a5", "b4a3", "d4c5", "b2b4", "a5b4"), "Discovered Check in Vienna Game Vienna"),
        RawTacticalPuzzle("r2qkb1r/pp2pppp/1np2n2/8/2BP2b1/2N1BN2/PPP3PP/R2Q1RK1 w kq - 6 9", listOf("c4f7", "e8f7", "f3e5", "f7g8", "e5g4"), "Attacking F2 F7 in Blackmar-Diemer Gambit Blackmar-Diemer"),
        RawTacticalPuzzle("5r1k/p5pp/8/6N1/2Q5/8/Pq3P1P/1b3RK1 w - - 4 31", listOf("g5f7", "h8g8", "f7h6", "g8h8", "c4g8", "f8g8", "h6f7"), "Discovered Check Combination"),
        RawTacticalPuzzle("rn2k1qr/p3bbp1/2p2nQp/1p2N3/3P4/1P6/P1P2PPP/R1B1R1K1 w kq - 6 16", listOf("e5f7", "g8f7", "e1e7", "e8e7", "c1a3", "e7d8", "g6f7"), "Attacking F2 F7 in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("1r3b2/5p1k/3P1qRP/r1n1p3/ppB5/P2Q1P2/1PP5/1K6 w - - 1 36", listOf("g6g7", "h7h6", "d3h7"), "Discovered Check Combination"),
        RawTacticalPuzzle("r5k1/pb1p1R1p/1p4pB/1P6/2Bbq3/8/P5PP/2R4K w - - 1 28", listOf("f7f8"), "Discovered Check Combination"),
        RawTacticalPuzzle("r3k2r/pp1bqppp/5B2/1BbpnQ2/8/2N5/PPP2PPP/R3K1NR b KQkq - 0 11", listOf("e5f3", "e1d1", "e7e1"), "Double Check in French Defense French"),
        RawTacticalPuzzle("2k4r/pp2bpp1/1q2pn2/1b5p/2B2B2/1N3P1P/PP1r1P2/2R2RK1 w - - 0 18", listOf("c4e6", "c8d8", "c1c8"), "Discovered Check in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("r3kb1r/5ppp/p6q/1p3P2/3BB1n1/8/PPP3PP/3RR1K1 w kq - 0 19", listOf("e4c6", "e8d8", "d4b6", "d8c8", "e1e8"), "Double Check in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("r3k3/ppp2p2/1b1p3p/4p2r/2B1P1bq/P1PP1P2/1P4PQ/RN3R1K w q - 3 19", listOf("c4f7", "e8e7", "f7h5"), "Attacking F2 F7 in Italian Game Italian"),
        RawTacticalPuzzle("2rqr3/6k1/1pb1p1pp/p2pPp2/2pP1B1P/P1P2NQ1/1P1R1PP1/6K1 w - - 1 39", listOf("h4h5", "g6g5", "f3g5", "e8g8", "g5e6"), "Discovered Check Combination"),
        RawTacticalPuzzle("r3kb1r/ppp2p1p/5Q2/4p3/2B1P3/3P3P/P1q3PK/8 w kq - 0 16", listOf("c4f7", "e8d7", "f6e6", "d7d8", "e6e8"), "Attacking F2 F7 in Kings Gambit Kings"),
        RawTacticalPuzzle("r2qk2r/pppbppbp/6p1/4n2n/2B2B2/2P3P1/PP3P1P/RN1QK1NR w KQkq - 0 10", listOf("f4e5", "g7e5", "d1d5", "d7g4", "d5f7"), "Attacking F2 F7 in Modern Defense Modern"),
        RawTacticalPuzzle("3r1rk1/2R1Rppp/1p6/pq6/2BQ4/P5Pb/1P3P1P/6K1 w - - 4 23", listOf("e7f7", "d8d4", "f7g7", "g8h8", "g7h7"), "Double Check Combination"),
        RawTacticalPuzzle("r3r1k1/pp2qp1p/3b2p1/3p4/3P2n1/1PN4P/PBP1BPP1/R2QK2R b KQ - 0 15", listOf("g4f2", "e1f2", "e7e3", "f2f1", "d6g3"), "Attacking F2 F7 in Zukertort Opening Zukertort"),
        RawTacticalPuzzle("r1bk1b1r/pp1pqpp1/2n2n1p/1NpQ4/2B1PB2/5N2/PP3PPP/R3K2R w KQ - 6 10", listOf("f4c7", "d8e8", "b5d6", "e7d6", "d5f7"), "Attacking F2 F7 in Center Game Center"),
        RawTacticalPuzzle("r3r3/2q1b1pk/p3P1Np/5Qp1/8/8/PPpp1P2/2R1R1K1 w - - 0 31", listOf("g6f8", "h7g8", "f5h7", "g8f8", "h7h8"), "Discovered Check Combination"),
        RawTacticalPuzzle("r2qkb1r/pp3ppp/1n2pn2/1N3b2/Q2P4/5N2/PP3PPP/R1B1R1K1 w kq - 2 12", listOf("b5d6", "e8e7", "d6f5"), "Discovered Check in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("1R1Q4/5ppk/7p/4Pb2/1P6/2b5/3r3P/2KR4 b - - 4 36", listOf("d2c2", "c1b1", "c2b2", "b1a1", "b2h2"), "Discovered Attack Combination"),
        RawTacticalPuzzle("r4rk1/1pp2p1p/p4Qp1/3n3P/P2Nq3/8/1B4R1/5B1K w - - 0 37", listOf("f6g7", "g8g7", "d4f5", "g7g8", "f5h6"), "Attraction Combination"),
        RawTacticalPuzzle("r1b2rk1/1p5N/1q2p1p1/n2pPB2/5P2/1PP1b3/1BQN2P1/2KR3R b - - 0 19", listOf("a5b3", "c1b1", "a8a1", "b2a1", "b3d2"), "Discovered Check Combination"),
        RawTacticalPuzzle("r3k2r/pp1n1pR1/1q2p3/3pP1Bp/1b1p3P/1P1B1P2/2PQ1P2/1K4R1 w kq - 1 22", listOf("d2f4", "h8f8", "g7f7", "f8f7", "d3g6", "d7e5", "f4e5", "e8d7", "g6f7"), "Attacking F2 F7 Combination"),
        RawTacticalPuzzle("3r4/1p5p/2kb3R/p4NP1/Prp2P2/K3P3/1P6/3R4 b - - 0 34", listOf("b4a4", "a3a4", "b7b5", "a4a5", "d8a8"), "Attraction Combination"),
        RawTacticalPuzzle("3r1rk1/q3bppp/2p1p3/1p2B3/3P4/3n1P2/PPP2P1P/R5RK w - - 0 22", listOf("g1g7", "g8h8", "g7g8", "h8g8", "a1g1", "e7g5", "g1g5"), "Attraction Combination"),
        RawTacticalPuzzle("1k6/1p1q2r1/P2p3p/1NpPpn1Q/5b2/2P5/1P2B1P1/R6K w - - 4 29", listOf("a6a7", "b8a8", "b5c7", "d7c7", "h5e8", "c7b8", "a7b8q"), "Discovered Check Combination"),
        RawTacticalPuzzle("5rk1/5ppb/1pN5/3P1P1P/1PP1n3/4q2P/rQK5/5BR1 w - - 7 32", listOf("g1g7", "g8h8", "g7g8", "h8g8", "c6e7"), "Attraction Combination"),
        RawTacticalPuzzle("r3k2r/1bq2ppp/p2p1n2/1p2p1NQ/3nP3/1BN4P/PP3PP1/R2R2K1 w kq - 3 16", listOf("h5f7", "c7f7", "g5f7"), "Attacking F2 F7 in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("8/6kp/6p1/3qNP2/1p1p1P1Q/1P1P4/2r5/6K1 w - - 0 37", listOf("f5f6", "g7f8", "h4h6", "f8e8", "f6f7", "d5f7", "e5f7"), "Attacking F2 F7 Combination"),
        RawTacticalPuzzle("4r1k1/pp3p2/3p1Ppq/5P2/8/6RP/PP2rQ2/6RK w - - 3 40", listOf("g3g6", "f7g6", "f6f7", "g8f7", "f5g6", "f7e7", "f2e2"), "Attraction Combination"),
        RawTacticalPuzzle("2kr3R/5p2/2pp1qb1/p3p1p1/2P3P1/2N1P1p1/PP1QBP2/R3K3 b Q - 0 26", listOf("f6f2", "e1d1", "d8h8", "d2d6", "h8d8", "d6d8", "c8d8"), "Attacking F2 F7 Combination"),
        RawTacticalPuzzle("r4k2/p3Rpp1/q2B1n1p/1p1b4/8/2PB4/P1Q2PPP/6K1 w - - 7 25", listOf("e7e8", "f8e8", "c2e2", "f6e4", "d3b5"), "Attraction Combination"),
        RawTacticalPuzzle("r2qk2r/ppp2pp1/2p1bn2/2b4p/N3P3/3P3P/PPP2PP1/R1BQKB1R b KQkq - 3 8", listOf("c5f2", "e1f2", "f6e4", "f2e3", "e4g3"), "Attacking F2 F7 in Russian Game Russian"),
        RawTacticalPuzzle("6k1/3r1pbp/p2pb1pB/2p5/4P3/q1N2P2/2KQ2P1/3R1B1R b - - 1 26", listOf("a3b3", "c2d3", "e6c4", "d3e3", "g7c3", "f1c4", "c3d2"), "Deflection Combination"),
        RawTacticalPuzzle("r3kb1r/1b1n1ppp/p3p3/1p2P3/8/4B3/P1B2QPP/qN3RK1 w kq - 0 17", listOf("f2f7", "e8d8", "f1d1", "f8e7", "f7e6"), "Attacking F2 F7 in Sicilian Defense Sicilian")
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
            val cleanTheme = "Double Attack"
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
