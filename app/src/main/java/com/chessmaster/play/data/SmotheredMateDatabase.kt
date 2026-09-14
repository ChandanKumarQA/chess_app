package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object SmotheredMateDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("r5nk/p1p3pp/1p1q4/n3r1N1/8/8/PPPP1PPP/R1B2RK1 w - - 0 19", listOf("g5f7"), "Mate In1 in Italian Game Italian"),
        RawTacticalPuzzle("r5rk/1p1qb1pp/p1np1n2/6N1/8/P1N5/BP3PPP/R1B3K1 w - - 0 20", listOf("g5f7"), "Mate In1 Combination"),
        RawTacticalPuzzle("r4rk1/1ppb2bp/3p2p1/3Ppp2/p1P1P3/PN3P1n/1P4PP/1RBQ1BNK b - - 0 24", listOf("h3f2"), "Mate In1 Combination"),
        RawTacticalPuzzle("r4rk1/pp2bppp/2p1pnb1/8/1P2PP1N/2P3Pn/P1QN2BP/R1B3RK b - - 0 19", listOf("h3f2"), "Mate In1 in Hungarian Opening Hungarian"),
        RawTacticalPuzzle("7k/p5rp/P1b2p2/1p2p3/2p5/B1PnPN2/6PP/R5RK b - - 0 30", listOf("d3f2"), "Mate In1 Combination"),
        RawTacticalPuzzle("1r4nk/p3brpp/b1p4N/q2p4/5P2/1P1pP3/PBP2RPP/R5K1 w - - 0 22", listOf("h6f7"), "Hanging Piece Combination"),
        RawTacticalPuzzle("8/1pN2pk1/p5p1/8/1b6/3n4/PP3QPP/6RK b - - 0 30", listOf("d3f2"), "Hanging Piece Combination"),
        RawTacticalPuzzle("r2q2rk/p3b1pp/np5N/8/3P1P2/8/PP4PP/R1B2RK1 w - - 0 21", listOf("h6f7"), "Mate In1 Combination"),
        RawTacticalPuzzle("r2qkb1r/pp1nnbpp/2p2p2/4p3/2NPP3/2N1B3/PP2BPPP/R2Q1RK1 w kq - 2 11", listOf("c4d6"), "Mate In1 in Queens Gambit Accepted"),
        RawTacticalPuzzle("3r1r1k/6bp/2p2np1/pb2p1B1/4P3/P4N1n/BPQN2PP/R5RK b - - 0 24", listOf("h3f2"), "Mate In1 Combination"),
        RawTacticalPuzzle("r2q2rk/ppp3pp/2n5/4N3/3P4/2P5/P4PPP/R3K2R w KQ - 0 17", listOf("e5f7"), "Mate In1 in Danish Gambit Danish"),
        RawTacticalPuzzle("r1bQ1bk1/pp3p1p/2p3p1/2P5/1P6/N5Pn/P5BP/R5RK b - - 0 22", listOf("h3f2"), "Mate In1 Combination"),
        RawTacticalPuzzle("2r3k1/5ppp/2P5/8/5P2/P5Pn/6BP/R5RK b - - 0 32", listOf("h3f2"), "Mate In1 Combination"),
        RawTacticalPuzzle("r5rk/pp4pp/2n5/q3p1N1/2B3b1/bRP1B3/P4PPP/5K1R w - - 0 20", listOf("g5f7"), "Mate In1 Combination"),
        RawTacticalPuzzle("1r4rk/6pp/7N/q4p2/6bb/P2PP1P1/5P1P/2B2KR1 w - - 0 29", listOf("h6f7"), "Mate In1 Combination"),
        RawTacticalPuzzle("r4rkn/1b3ppp/pq2n3/4pN1P/4P1K1/1Pp2NP1/P1B5/R1Q1R3 w - - 1 29", listOf("f5e7"), "Mate In1 Combination"),
        RawTacticalPuzzle("r1b3rk/2qnb1pp/2p2p1N/p1p5/2P1NP2/2BP4/PP4PP/R4R1K w - - 0 21", listOf("h6f7"), "Mate In1 Combination"),
        RawTacticalPuzzle("5rk1/Q4ppp/1p2p3/8/8/5P1n/6PP/1R4RK b - - 0 30", listOf("h3f2"), "Mate In1 Combination"),
        RawTacticalPuzzle("r6r/N2k1pQ1/4p2p/1Nbp4/P3n3/8/2P3PP/R5RK b - - 0 26", listOf("e4f2"), "Mate In1 Combination"),
        RawTacticalPuzzle("r2qkb1r/1b1pnppp/p3p3/n3P3/2p1N3/5N2/PPPPQPPP/R1B2RK1 w kq - 3 11", listOf("e4d6"), "Mate In1 in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("rn4rk/p5pp/q1p2p1N/2R5/3P4/4P3/PP3PPP/2R3K1 w - - 0 23", listOf("h6f7"), "Mate In1 Combination"),
        RawTacticalPuzzle("r5k1/6p1/2N3pp/3p4/P2P4/1P5n/6PP/6RK b - - 0 34", listOf("h3f2"), "Mate In1 Combination"),
        RawTacticalPuzzle("r4rk1/pp2b1pp/2p1b3/3pQ3/1P6/2P3Pn/PB2P1BP/R5RK b - - 0 21", listOf("h3f2"), "Mate In1 Combination"),
        RawTacticalPuzzle("r1bqk2r/pp1p1pbp/6p1/2pPp3/1n6/BP2P3/P2PNPPP/R2QKB1R b KQkq - 2 9", listOf("b4d3"), "Mate In1 in English Opening English"),
        RawTacticalPuzzle("4r1rk/pbp3pp/1pn4N/8/3q2PP/3B4/PPP5/1K1R1R2 w - - 0 24", listOf("h6f7"), "Mate In1 Combination"),
        RawTacticalPuzzle("r4rk1/pp1n2bp/2p1b1p1/4pp2/2P1P3/1PN2PPn/P1QBN1BP/R5RK b - - 0 19", listOf("h3f2"), "Mate In1 Combination"),
        RawTacticalPuzzle("r3r1k1/2q2pp1/p2b3p/3Bn3/1p6/3P4/1PPB1PQ1/1NKR3R b - - 2 21", listOf("e5d3"), "Mate In1 Combination"),
        RawTacticalPuzzle("B1b2rk1/p1p2ppp/8/3p4/3bn3/8/PPP3PP/RNBQ2RK b - - 0 14", listOf("e4f2"), "Mate In1 in Italian Game Italian"),
        RawTacticalPuzzle("r2qkbnr/1p1bnppp/p2p4/1Np5/Q3P3/2PB4/PP3PPP/RNB1K2R w KQkq - 0 10", listOf("b5d6"), "Mate In1 in Ruy Lopez Ruy"),
        RawTacticalPuzzle("kr5r/pp6/N4b2/5P2/3p4/Pq3NP1/1P4KP/1RB4R w - - 0 36", listOf("a6c7"), "Mate In1 Combination"),
        RawTacticalPuzzle("5r2/pp2p1kp/3p2p1/3P4/4P3/P1N4n/1P4PP/2Q3RK b - - 0 24", listOf("h3f2"), "Mate In1 Combination"),
        RawTacticalPuzzle("r4r2/p3p2k/2p1B1p1/2p4p/2P1P3/1P5n/P5PP/2B1R1RK b - - 0 27", listOf("h3f2"), "Mate In1 Combination"),
        RawTacticalPuzzle("rnbk3r/pppp1Qpp/3b4/8/2B2P2/7n/PPPP2PP/RNB3RK b - - 0 12", listOf("h3f2"), "Mate In1 in Russian Game Russian"),
        RawTacticalPuzzle("2b3rk/3p2pp/p5nN/p1P5/1P5q/P4p2/5PPP/3R1RK1 w - - 0 31", listOf("h6f7"), "Mate In1 Combination"),
        RawTacticalPuzzle("7k/5ppp/3N3n/2r5/p2K1PP1/P1r4P/2P5/4R3 w - - 6 36", listOf("e1e8", "h6g8", "d6f7"), "Deflection Combination"),
        RawTacticalPuzzle("r5k1/5p1p/p5p1/8/2Q5/1P4Nn/P5PP/6RK b - - 0 30", listOf("h3f2"), "Mate In1 Combination"),
        RawTacticalPuzzle("r1b2rk1/pp3ppp/2n5/3Q4/1P6/2P2N1n/P3B1PP/RN4RK b - - 0 17", listOf("h3f2"), "Mate In1 in French Defense French"),
        RawTacticalPuzzle("5rkr/pp3ppp/5nN1/3pb3/8/8/PP3PPP/4R1K1 w - - 0 28", listOf("g6e7"), "Mate In1 Combination"),
        RawTacticalPuzzle("r2qkb1r/1b1pnpp1/p1n1p2p/1pp1P3/4NP2/3PBN2/PPP3PP/R2QKB1R w KQkq - 2 9", listOf("e4d6"), "Mate In1 in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("r4rk1/1p1n1pp1/p3p2p/3PPn2/4pP1q/8/PP2B1PP/R2Q1RBK b - - 0 18", listOf("f5g3"), "Mate In1 in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("3q2rk/pb2nppp/1p2pn2/1P2N3/1BP5/4P3/2Q1BPPP/6K1 w - - 0 22", listOf("e5f7"), "Mate In1 Combination"),
        RawTacticalPuzzle("r5rk/1bp3pp/pbq2N2/np2p1N1/8/1BPP3P/PP3PP1/R1B1R1K1 w - - 0 19", listOf("g5f7"), "Mate In1 in Ruy Lopez Ruy"),
        RawTacticalPuzzle("1r5r/3b4/pk2P3/1p2Q1Np/4BP2/1P5n/P5PP/6RK b - - 0 36", listOf("h3f2"), "Mate In1 Combination"),
        RawTacticalPuzzle("r3kb1r/ppp1pppp/5n2/6N1/6P1/2N4n/PPP3PP/R1BQ2RK b kq - 0 13", listOf("h3f2"), "Mate In1 in Zukertort Opening Zukertort"),
        RawTacticalPuzzle("4r1k1/p2Q2bp/4p1p1/1p2r1B1/8/1P5n/P5PP/4R1RK b - - 0 26", listOf("h3f2"), "Mate In1 Combination"),
        RawTacticalPuzzle("1nkr3r/1ppn1pp1/p2p3p/3N4/P3PP2/3BB1bP/1PP5/2KR3R w - - 0 19", listOf("d5e7"), "Mate In1 in Vienna Game Vienna"),
        RawTacticalPuzzle("2r3rk/6pp/p6N/1p1p4/2pq4/P6P/1P3PP1/4R1K1 w - - 0 37", listOf("h6f7"), "Mate In1 Combination"),
        RawTacticalPuzzle("6rk/pp1R2pp/2n2b1N/2p5/4r3/6P1/Pq2PP1P/5RK1 w - - 0 23", listOf("h6f7"), "Mate In1 Combination"),
        RawTacticalPuzzle("1bkr3r/ppqn1ppp/2p2nb1/5N2/2B5/2NPBQ2/PPP3PP/R4R1K w - - 1 16", listOf("f5e7"), "Mate In1 in Scandinavian Defense Scandinavian"),
        RawTacticalPuzzle("r1b2rk1/ppp3pp/6n1/3N4/4p3/Q6n/PPPP2PP/R1B3RK b - - 0 20", listOf("h3f2"), "Mate In1 Combination"),
        RawTacticalPuzzle("r2qkb1r/pp1bnppp/2n1p3/1B6/3QN3/5N2/PPP2PPP/R1B1K2R w KQkq - 1 9", listOf("e4d6"), "Mate In1 in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("Q1b2rk1/3p1p2/p1p4p/1p4p1/8/1BP3Pn/PP4PP/RN4RK b - - 0 21", listOf("h3f2"), "Mate In1 Combination"),
        RawTacticalPuzzle("rn4rk/pp4pp/2p4N/q3p3/8/1PP1n3/P5PP/2KR2NR w - - 0 21", listOf("h6f7"), "Mate In1 Combination"),
        RawTacticalPuzzle("r2qkbnr/pp1npppb/2p4p/7P/2P1N3/3B1N2/PP2QPP1/R1B1K2R w KQkq - 1 11", listOf("e4d6"), "Mate In1 in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("3r2k1/pQ3pp1/7p/2p5/5P2/1P5n/P5PP/6RK b - - 0 28", listOf("h3f2"), "Mate In1 Combination"),
        RawTacticalPuzzle("2r1kb1r/5p2/1n5p/p1n1P3/1p1NP2P/8/PPPB4/1NKR3R b k - 0 24", listOf("c5d3"), "Mate In1 Combination"),
        RawTacticalPuzzle("r2qkbnr/pp2pppp/8/2p1n3/3P4/5P2/PP1PNP1P/R1BQKB1R b KQkq - 0 8", listOf("e5f3"), "Mate In1 Combination"),
        RawTacticalPuzzle("3rkb1r/pp1bnppp/2n5/2pBP3/4NP2/5N2/PP4PP/R1B1K2R w KQk - 7 13", listOf("e4d6"), "Mate In1 in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("r1b2rk1/pp3ppp/2p1pn2/8/2P5/n5Q1/PP1BN1PP/KR3B1R b - - 0 22", listOf("a3c2"), "Mate In1 Combination"),
        RawTacticalPuzzle("r1b1k1nr/1pp1qppp/1p6/4n3/1P1B4/P4N2/3NPPPP/R2QKB1R b KQkq - 0 11", listOf("e5d3"), "Mate In1 in Queens Gambit Declined"),
        RawTacticalPuzzle("r1bqkb1r/pppp2pp/5n2/3Pnp2/2P1p3/2N1P3/PP1BNPPP/R2QKB1R b KQkq - 4 7", listOf("e5d3"), "Mate In1 Combination"),
        RawTacticalPuzzle("3qkb1r/1b1pppp1/pp3n2/nN4B1/2r5/P3QNP1/1P2PPB1/R4RK1 w k - 0 16", listOf("b5d6"), "Mate In1 in Zukertort Opening Zukertort"),
        RawTacticalPuzzle("r4rk1/5p1p/p5p1/2pq4/2pNn3/P1P1P2P/2QN2RB/6RK b - - 1 29", listOf("e4f2"), "Mate In1 Combination"),
        RawTacticalPuzzle("3qkb1r/p2rnppp/2Q1p3/1NPpP3/8/2P1B3/P4PK1/1R3R2 w k - 1 20", listOf("b5d6"), "Mate In1 Combination"),
        RawTacticalPuzzle("1q4rk/4B1bp/6pN/p1pN4/8/8/P1P2PPP/1r1R2K1 w - - 0 26", listOf("h6f7"), "Mate In1 Combination"),
        RawTacticalPuzzle("r1bqkb1r/pp1pnpp1/4p3/1B2P1p1/3PN3/8/PP3PPP/R2Q1RK1 w kq - 0 12", listOf("e4d6"), "Mate In1 in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("1n1k3r/6Qp/pq2p3/1p6/8/PB6/1P3nPP/R4RK1 b - - 2 20", listOf("f2h3", "g1h1", "b6g1", "f1g1", "h3f2"), "Discovered Check Combination"),
        RawTacticalPuzzle("r2qkb1r/pp1bpppp/8/1Np5/Q1P5/8/PP1P1PPP/R1B1R1K1 w kq - 0 14", listOf("b5d6"), "Mate In1 in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("4rrk1/ppp2Npp/4Q3/2q5/3p4/8/PPP2PPP/1K1R4 w - - 5 22", listOf("f7h6", "g8h8", "e6g8", "f8g8", "h6f7"), "Discovered Check Combination"),
        RawTacticalPuzzle("r4rk1/1p2pp2/3p1Bpp/p2P4/2P1RP2/7n/PP4PP/R1Q2BNK b - - 0 23", listOf("h3f2"), "Mate In1 Combination"),
        RawTacticalPuzzle("r1qkr3/bbppn2p/p1nN1p2/1p6/3PP3/1BN5/PPP3PP/R1B1K2R w KQ - 0 16", listOf("d6f7"), "Mate In1 in Owen Defense Owen"),
        RawTacticalPuzzle("r1b2rk1/p4Nbp/2p3p1/4p3/8/1Q4P1/P1PqpP2/5RK1 w - - 2 27", listOf("f7h6", "g8h8", "b3g8", "f8g8", "h6f7"), "Discovered Check Combination"),
        RawTacticalPuzzle("3Q4/6pk/4pp1p/pPq5/P1p5/6R1/5nPP/5RK1 b - - 8 48", listOf("f2h3", "g1h1", "c5g1", "f1g1", "h3f2"), "Discovered Check Combination"),
        RawTacticalPuzzle("r2qkb1r/pp1nnp1p/2p3p1/3p1N2/3P4/3QR3/PPP2PPP/RN4K1 w kq - 1 12", listOf("f5d6"), "Mate In1 in Queens Pawn Game"),
        RawTacticalPuzzle("3r1r1k/5Qpp/2p3n1/p3p1N1/P2q4/3P4/B1P2P2/R4K2 w - - 0 26", listOf("f7g8", "f8g8", "g5f7"), "Mate In2 Combination"),
        RawTacticalPuzzle("r4rk1/1p3Npp/pq3b2/3Q4/8/P2P4/1PP3PP/R1B4K w - - 0 19", listOf("f7h6", "g8h8", "d5g8", "f8g8", "h6f7"), "Discovered Check in Bishops Opening Bishops"),
        RawTacticalPuzzle("r7/ppp2Q2/8/4k3/3nb3/5P2/PPq3PP/K2RR3 b - - 0 24", listOf("c2b1", "d1b1", "d4c2"), "Mate In2 Combination"),
        RawTacticalPuzzle("6rk/r3q1nn/1p2p3/p1ppPpNP/3P1N2/2P2P2/PP6/R3K2R w KQ - 0 24", listOf("f4g6"), "Mate In1 Combination"),
        RawTacticalPuzzle("5rk1/1p3Nbp/8/r2Q4/2p5/4q1n1/P1P2RPN/6K1 w - - 0 30", listOf("f7h6", "g8h8", "d5g8", "f8g8", "h6f7"), "Discovered Check Combination"),
        RawTacticalPuzzle("5rk1/1b1n2pp/p3R3/1p6/3Q4/2Nn4/PPP3PP/7K b - - 0 24", listOf("f8f1", "d4g1", "d3f2"), "Mate In2 Combination"),
        RawTacticalPuzzle("r1b1k2N/pppp2pp/8/2q1p3/2B1n3/5n2/PPP3PP/RNBQ1R1K b q - 2 11", listOf("c5g1", "f1g1", "e4f2"), "Mate In2 in Italian Game Italian"),
        RawTacticalPuzzle("r2qkb1r/pp1nnppp/2p1p3/4P3/2BPN3/5b2/PP3PPP/R1BQ1RK1 w kq - 0 10", listOf("e4d6"), "Mate In1 in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("r1b1k2r/ppppqppp/8/4n3/1bP5/1P3N1P/P2NPPP1/R2QKB1R b KQkq - 4 9", listOf("e5d3"), "Mate In1 in Indian Defense Indian"),
        RawTacticalPuzzle("k3r2r/ppQ1n1pp/2n2p2/1N1p4/3P4/2PB2B1/PPK2P1P/q7 w - - 3 21", listOf("c7b8", "c6b8", "b5c7"), "Mate In2 Combination"),
        RawTacticalPuzzle("r1b2rk1/ppp2ppp/8/1B1p4/3bn3/5N2/PPP2qPP/RNBQR2K b - - 1 12", listOf("f2g1", "f3g1", "e4f2"), "Mate In2 in Italian Game Italian"),
        RawTacticalPuzzle("r6k/ppp2Rpp/3p4/7P/1nBPb1Q1/8/PPq3P1/K6R b - - 0 28", listOf("c2b1", "h1b1", "b4c2"), "Mate In2 Combination"),
        RawTacticalPuzzle("rnb1k3/ppp4p/3p2N1/2b1p2B/3qn3/8/PPPP2PP/RNBQ1R1K b q - 0 11", listOf("d4g1", "f1g1", "e4f2"), "Mate In2 in Kings Gambit Declined"),
        RawTacticalPuzzle("r2q1r1k/1b1np1bp/p2pQ1p1/1p4N1/3n4/1B5P/PP3PP1/RNBR2K1 w - - 0 16", listOf("e6g8", "f8g8", "g5f7"), "Mate In2 in Sicilian Defense Sicilian"),
        RawTacticalPuzzle("r1bqrbkn/pp3ppp/2p2p2/5N1P/3P2Q1/3B4/PPP2PP1/R1B2RK1 w - - 1 14", listOf("f5h6"), "Mate In1 in Caro-Kann Defense Caro-Kann"),
        RawTacticalPuzzle("r2q1r1k/4N1bp/p2p2p1/2p3N1/Pp4P1/1Q5P/1P1n1P2/5RK1 w - - 1 22", listOf("b3g8", "f8g8", "g5f7"), "Mate In2 Combination"),
        RawTacticalPuzzle("r5k1/pbp3pp/1p1ppr2/5p1q/2PPn3/P1PBP2P/1BQ2RPN/6RK b - - 0 18", listOf("e4g3"), "Mate In1 in English Defense English"),
        RawTacticalPuzzle("r4r1k/ppp3pp/2n5/3Q2N1/3b1P2/1B4P1/PPPBq2P/R4b1K w - - 0 20", listOf("d5g8", "f8g8", "g5f7"), "Mate In2 Combination"),
        RawTacticalPuzzle("r1b1k1r1/1p3p1Q/p3p3/2b5/P1Bq1Bn1/2N5/1P4PP/3R1R1K b q - 0 21", listOf("d4g1", "f1g1", "g4f2"), "Mate In2 Combination"),
        RawTacticalPuzzle("r2qkb1Q/pp1npp1p/2p3pB/3n4/2b1N3/8/PP3PPP/R3R1K1 w q - 0 15", listOf("e4d6"), "Mate In1 in Scandinavian Defense Scandinavian"),
        RawTacticalPuzzle("6k1/1p2Bqpp/p7/8/P3p1n1/1PQP4/2P1N1PP/6K1 b - - 0 25", listOf("f7f2", "g1h1", "f2f1", "e2g1", "g4f2"), "Fork Combination"),
        RawTacticalPuzzle("6k1/pb3ppp/1q1p4/8/N7/PP1Q1P1n/6PP/1R3R1K b - - 0 28", listOf("b6g1", "f1g1", "h3f2"), "Mate In2 Combination"),
        RawTacticalPuzzle("1rb3k1/pQ4pp/1p3n2/q3N3/3P4/P7/1B3PPP/6K1 w - - 0 28", listOf("b7f7", "g8h8", "f7f8", "f6g8", "e5f7"), "Mate In3 Combination"),
        RawTacticalPuzzle("2rqkbQ1/1p1bn2N/2n5/1pp2r2/3p4/3P4/PPP2PPP/R3R1K1 w - - 2 18", listOf("g8g6", "f5f7", "h7f6"), "Mate In2 in French Defense French"),
        RawTacticalPuzzle("r4q1k/1p4pp/1n2Q1pN/p3P3/Pbp1P3/8/1P2p1PP/5RK1 w - - 0 26", listOf("f1f8", "a8f8", "e6g8", "f8g8", "h6f7"), "Mate In3 Combination"),
        RawTacticalPuzzle("r3k2r/1p2ppbp/pq1p2p1/3N4/3nPPn1/3B4/PPPQ2PP/R1B2RK1 b kq - 3 16", listOf("d4e2", "g1h1", "b6g1", "f1g1", "g4f2"), "Discovered Check in Pirc Defense Pirc")
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
            val cleanTheme = "Smothered Mate"
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
