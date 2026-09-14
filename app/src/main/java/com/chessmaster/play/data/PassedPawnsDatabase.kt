package com.chessmaster.play.data

import com.chessmaster.play.model.EndgameLesson

object PassedPawnsDatabase {

    private data class RawLesson(
        val subtitle: String,
        val fen: String,
        val explanation: String,
        val hint: String,
        val moves: List<String>
    )

    private val pool = listOf(
        RawLesson("Advanced Pawn Technique in Passed Pawns", "6r1/p6k/1p4rp/3Q4/8/P7/4Rp1P/5R1K b - - 0 37", "Execute the precise endgame technique with g6g1 to secure victory.", "Look for the key move g6g1.", listOf("g6g1", "f1g1", "f2g1q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/5ppk/7p/2pr4/4p3/4P3/1Qp2PPP/R5K1 b - - 2 36", "Execute the precise endgame technique with d5d1 to secure victory.", "Look for the key move d5d1.", listOf("d5d1", "a1d1", "c2d1q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "r5k1/2PR1ppp/8/8/7P/8/P3pqPK/8 w - - 0 34", "Execute the precise endgame technique with d7d8 to secure victory.", "Look for the key move d7d8.", listOf("d7d8", "a8d8", "c7d8q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "6k1/8/1R2p1pp/4P3/p1N2P2/6PK/7P/1r6 b - - 0 48", "Execute the precise endgame technique with b1b6 to secure victory.", "Look for the key move b1b6.", listOf("b1b6", "c4b6", "a4a3", "b6d7", "a3a2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/pb3k2/3P4/3N2p1/4p3/2K4P/8/8 w - - 2 46", "Execute the precise endgame technique with d6d7 to secure victory.", "Look for the key move d6d7.", listOf("d6d7", "b7d5", "d7d8q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/2Q2pkp/6p1/8/4Pp2/7P/r1p3P1/7K b - - 1 40", "Execute the precise endgame technique with a2a1 to secure victory.", "Look for the key move a2a1.", listOf("a2a1", "h1h2", "c2c1q", "c7c1", "a1c1")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "r5k1/P4pp1/4p2p/3p4/4n3/2q3P1/2P1QP1P/1R5K w - - 3 25", "Execute the precise endgame technique with b1b8 to secure victory.", "Look for the key move b1b8.", listOf("b1b8", "a8b8", "a7b8q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "4r2k/4P1p1/1p1p3p/p3pQ2/P3n3/7P/1P3PPK/2r5 w - - 0 30", "Execute the precise endgame technique with f5f8 to secure victory.", "Look for the key move f5f8.", listOf("f5f8", "e8f8", "e7f8q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "1rb3k1/q4rP1/4p2p/3p3p/3P1P2/2P5/2QK3P/3R2R1 w - - 1 30", "Execute the precise endgame technique with c2h7 to secure victory.", "Look for the key move c2h7.", listOf("c2h7", "g8h7", "g7g8q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/8/RP6/pkb5/r7/8/5PP1/5K2 w - - 1 41", "Execute the precise endgame technique with b6b7 to secure victory.", "Look for the key move b6b7.", listOf("b6b7", "b5a6", "b7b8q", "a4a1", "f1e2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/1k6/1pn5/8/Pp2R1p1/4P3/5PK1/8 b - - 1 51", "Execute the precise endgame technique with b4b3 to secure victory.", "Look for the key move b4b3.", listOf("b4b3", "e4g4", "b3b2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "5rk1/n2P3p/1p2p1pQ/pP6/q3p1p1/8/5PPP/5RK1 w - - 0 35", "Execute the precise endgame technique with h6f8 to secure victory.", "Look for the key move h6f8.", listOf("h6f8", "g8f8", "d7d8q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "N1b4r/pp2kppp/4p3/8/3P4/Q1q1P3/2pN1PPP/R3KB1R b KQ - 7 19", "Execute the precise endgame technique with c3a3 to secure victory.", "Look for the key move c3a3.", listOf("c3a3", "a1a3", "c2c1q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "1R6/P7/6p1/7p/2q5/7k/K7/8 w - - 2 44", "Execute the precise endgame technique with b8b3 to secure victory.", "Look for the key move b8b3.", listOf("b8b3", "h3g4", "a7a8q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "6k1/8/2P3rp/1p1pp3/1P1N4/2P2Pn1/1P4P1/1K3n2 w - - 0 36", "Execute the precise endgame technique with c6c7 to secure victory.", "Look for the key move c6c7.", listOf("c6c7", "e5d4", "c7c8q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "1n5k/6p1/p2q1rPp/1ppB4/8/3P4/PPP1rPQ1/2K4R w - - 0 26", "Execute the precise endgame technique with h1h6 to secure victory.", "Look for the key move h1h6.", listOf("h1h6", "g7h6", "g6g7", "h8h7", "g7g8q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/2p5/1p2B3/p7/5r2/P4p2/1PP1N2k/1K4R1 b - - 2 59", "Execute the precise endgame technique with f3e2 to secure victory.", "Look for the key move f3e2.", listOf("f3e2", "g1e1", "f4f1", "b1c1", "f1e1")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/p1p1k2p/4P3/2PP1p1P/1r3r2/5B2/P3RK2/8 w - - 4 39", "Execute the precise endgame technique with d5d6 to secure victory.", "Look for the key move d5d6.", listOf("d5d6", "c7d6", "c5d6", "e7d6", "e6e7")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "B2Qnk1r/p1p2pp1/6q1/2b5/1P3B2/2P2Np1/P3P2P/R4R1K b - - 0 19", "Execute the precise endgame technique with g3g2 to secure victory.", "Look for the key move g3g2.", listOf("g3g2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/p7/1p4k1/2pN2p1/4Pp2/P1P4r/1P2K1R1/8 b - - 7 50", "Execute the precise endgame technique with f4f3 to secure victory.", "Look for the key move f4f3.", listOf("f4f3", "e2f2", "f3g2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "4Q3/4qppk/2p2b1p/1p2pP2/2r1B3/5P1P/6P1/5R1K w - - 4 34", "Execute the precise endgame technique with e8e7 to secure victory.", "Look for the key move e8e7.", listOf("e8e7", "f6e7", "f5f6", "c4e4", "f6e7")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "rn1qk2r/pb3ppp/5n2/2b5/8/2N1p1P1/PP1PPPNP/R1BQKB1R b KQkq - 1 9", "Execute the precise endgame technique with e3f2 to secure victory.", "Look for the key move e3f2.", listOf("e3f2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/7R/5p2/p7/7P/2p5/3k2N1/1K6 b - - 0 48", "Execute the precise endgame technique with c3c2 to secure victory.", "Look for the key move c3c2.", listOf("c3c2", "b1a2", "c2c1q", "h7d7", "d2e2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "2rknb2/3q1pp1/p1pP3r/1pQ1P3/5P1p/1P4P1/PB4K1/3R4 w - - 0 28", "Execute the precise endgame technique with c5b6 to secure victory.", "Look for the key move c5b6.", listOf("c5b6", "e8c7", "d6c7")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "1r3rk1/q5pp/2R5/3P4/2Q5/1p2NpPb/1P3P1P/3R2K1 b - - 1 32", "Execute the precise endgame technique with a7e3 to secure victory.", "Look for the key move a7e3.", listOf("a7e3", "f2e3", "f3f2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "1r4k1/4Pp1p/p5pb/2q5/p2n4/P2Q2B1/r2N1PPP/3KR2R w - - 1 28", "Execute the precise endgame technique with e7e8q to secure victory.", "Look for the key move e7e8q.", listOf("e7e8q", "b8e8", "e1e8")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/8/2p5/6R1/2B4P/KP2knp1/P1P5/8 b - - 1 45", "Execute the precise endgame technique with f3g5 to secure victory.", "Look for the key move f3g5.", listOf("f3g5", "h4g5", "g3g2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "2r5/3k1p2/5Pp1/PPBpP1P1/1K5P/5p2/8/8 b - - 2 45", "Execute the precise endgame technique with c8c5 to secure victory.", "Look for the key move c8c5.", listOf("c8c5", "b4c5", "f3f2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/7p/1P6/7P/2n1p3/8/2pBK3/1k6 w - - 2 48", "Execute the precise endgame technique with b6b7 to secure victory.", "Look for the key move b6b7.", listOf("b6b7", "c2c1q", "d2c1")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "1r6/p2kp1P1/3p4/2pP4/2P2B2/1N6/p4PK1/8 w - - 0 42", "Execute the precise endgame technique with b3c5 to secure victory.", "Look for the key move b3c5.", listOf("b3c5", "d6c5", "f4b8", "a2a1q", "g7g8q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "r7/p2k1pp1/p1p1pn2/3p4/3P4/P3PQp1/1PP2P1R/2K5 b - - 0 20", "Execute the precise endgame technique with g3h2 to secure victory.", "Look for the key move g3h2.", listOf("g3h2", "f3h3", "f6g4")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/6kp/p2P2b1/2p5/2P5/P5P1/2rR1K2/8 w - - 0 41", "Execute the precise endgame technique with d2c2 to secure victory.", "Look for the key move d2c2.", listOf("d2c2", "g6c2", "d6d7")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/8/4n3/4BK2/2k5/1p6/5P2/8 b - - 1 56", "Execute the precise endgame technique with e6d4 to secure victory.", "Look for the key move e6d4.", listOf("e6d4", "f5e4", "b3b2", "e5d4", "b2b1q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "5R2/8/p5pp/2B5/5b1P/P3p3/8/3K1k2 b - - 3 46", "Execute the precise endgame technique with e3e2 to secure victory.", "Look for the key move e3e2.", listOf("e3e2", "d1c2", "e2e1q", "f8f4", "f1g2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "R7/P4p2/7p/q3k1n1/5R2/7P/5PK1/8 w - - 3 47", "Execute the precise endgame technique with a8e8 to secure victory.", "Look for the key move a8e8.", listOf("a8e8", "e5f4", "a7a8q", "a5a8", "e8a8")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "4r1k1/8/2PP1b2/p5p1/1p5p/1P2p3/4K2B/8 w - - 0 41", "Execute the precise endgame technique with d6d7 to secure victory.", "Look for the key move d6d7.", listOf("d6d7", "e8d8", "c6c7", "d8d7", "c7c8q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "3r2k1/1bqPn1pp/p3p3/1pp2pPQ/8/8/PPP4P/1K1R1R2 w - - 0 29", "Execute the precise endgame technique with h5e8 to secure victory.", "Look for the key move h5e8.", listOf("h5e8", "d8e8", "d7e8q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "6k1/1p3p2/2p1bQPq/p3Pp2/2P5/1P6/5PK1/8 w - - 1 54", "Execute the precise endgame technique with g6f7 to secure victory.", "Look for the key move g6f7.", listOf("g6f7", "e6f7", "f6h6")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "4B1k1/2p2p1p/1p1p1P1K/p2P2P1/2P5/1n6/8/8 w - - 0 40", "Execute the precise endgame technique with e8f7 to secure victory.", "Look for the key move e8f7.", listOf("e8f7", "g8f7", "h6h7", "b3d2", "g5g6", "f7f6", "g6g7")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/7p/6p1/2Q5/8/2pK1PP1/1r5P/1k6 b - - 11 58", "Execute the precise endgame technique with c3c2 to secure victory.", "Look for the key move c3c2.", listOf("c3c2", "c5c2", "b2c2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "5r2/1p2Q3/6K1/pk3r2/5P2/3P3P/8/8 b - - 3 46", "Execute the precise endgame technique with f8f6 to secure victory.", "Look for the key move f8f6.", listOf("f8f6", "e7f6", "f5f6", "g6f6", "a5a4", "f4f5", "a4a3", "f6g6", "a3a2", "f5f6", "a2a1q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "6rk/5R2/3p1q1p/3P2pQ/1P2P3/3P1p1P/P1r3PB/1R5K b - - 1 28", "Execute the precise endgame technique with f3g2 to secure victory.", "Look for the key move f3g2.", listOf("f3g2", "h1g1", "f6d4", "f7f2", "d4f2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/8/8/6p1/5N2/3p3P/5kP1/3K4 b - - 0 56", "Execute the precise endgame technique with g5f4 to secure victory.", "Look for the key move g5f4.", listOf("g5f4", "h3h4", "f2g2", "h4h5", "f4f3", "d1d2", "f3f2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "r5k1/pb2r1p1/1p2pQ2/2b1N3/5P2/PP5P/4p2K/4R1R1 b - - 1 33", "Execute the precise endgame technique with c5g1 to secure victory.", "Look for the key move c5g1.", listOf("c5g1", "e1g1", "e2e1q", "f6e6", "e7e6")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "r5k1/2B2pp1/1P2p1p1/r2q4/3Pp3/R6P/5PP1/1R4K1 w - - 0 33", "Execute the precise endgame technique with a3a5 to secure victory.", "Look for the key move a3a5.", listOf("a3a5", "a8a5", "b6b7", "d5b7", "b1b7", "a5a1", "g1h2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "1Q6/K4k2/8/5p2/8/8/1R2p3/2r5 b - - 0 56", "Execute the precise endgame technique with c1a1 to secure victory.", "Look for the key move c1a1.", listOf("c1a1", "a7b6", "e2e1q", "b8c7", "e1e7", "c7e7", "f7e7")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "3r1rk1/p1p2p2/2Q4p/2P1q1p1/3p2P1/1P2P2P/P1P2P2/2K1RR2 b - - 3 23", "Execute the precise endgame technique with d4e3 to secure victory.", "Look for the key move d4e3.", listOf("d4e3", "c1b1", "e3e2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/3r4/P1k2p1p/1pq1pNp1/2b1P1P1/2P2P1P/3r2BK/R5Q1 w - - 5 41", "Execute the precise endgame technique with g1c5 to secure victory.", "Look for the key move g1c5.", listOf("g1c5", "c6c5", "a6a7", "d7a7", "a1a7", "c4f1", "f5e3", "f1g2", "e3g2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/8/1k6/8/p7/1p1N2P1/5KP1/r3R3 b - - 1 55", "Execute the precise endgame technique with a1e1 to secure victory.", "Look for the key move a1e1.", listOf("a1e1", "f2e1", "a4a3", "e1d2", "a3a2", "d2c3", "a2a1q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/5p1k/pp3B2/3bPq2/6R1/6RP/1P1p2PK/8 b - - 1 47", "Execute the precise endgame technique with f5g4 to secure victory.", "Look for the key move f5g4.", listOf("f5g4", "g3g4", "d2d1q", "g4g7", "h7h6")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/8/3RP3/2Pn4/ppN1k2p/8/rP1K4/8 w - - 0 41", "Execute the precise endgame technique with d6d5 to secure victory.", "Look for the key move d6d5.", listOf("d6d5", "e4d5", "e6e7")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/1pp3k1/3p2P1/p2P4/2P1r2N/1PK3P1/r7/5R2 w - - 0 35", "Execute the precise endgame technique with f1f7 to secure victory.", "Look for the key move f1f7.", listOf("f1f7", "g7h6", "f7h7", "h6g5", "g6g7")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/4bkp1/R6P/4p3/Pp2P3/1P2r3/6P1/6K1 w - - 0 34", "Execute the precise endgame technique with h6h7 to secure victory.", "Look for the key move h6h7.", listOf("h6h7", "e3e1", "g1h2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "4rR2/3NP1kp/2p3p1/5p2/8/2rp3P/5PP1/4R1K1 b - - 3 34", "Execute the precise endgame technique with d3d2 to secure victory.", "Look for the key move d3d2.", listOf("d3d2", "e1d1", "c3c1", "g1h2", "c1d1")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/1b4k1/3Pq1pp/2Q5/8/5P2/6PK/8 w - - 5 42", "Execute the precise endgame technique with c5c7 to secure victory.", "Look for the key move c5c7.", listOf("c5c7", "g7f6", "d6d7", "e6d7", "c7d7")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/4rkb1/R5R1/5P1p/P6P/6P1/1r2p3/4K3 w - - 0 45", "Execute the precise endgame technique with g6g7 to secure victory.", "Look for the key move g6g7.", listOf("g6g7", "f7g7", "f5f6", "g7f7", "f6e7")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "2kr4/2pR4/2P1K1P1/8/8/4n3/p7/8 w - - 0 50", "Execute the precise endgame technique with d7d8 to secure victory.", "Look for the key move d7d8.", listOf("d7d8", "c8d8", "g6g7", "a2a1q", "g7g8q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "2k2r2/1bNp4/pB1P4/8/P7/5n2/1P2KR1p/5R2 b - - 1 43", "Execute the precise endgame technique with f3g1 to secure victory.", "Look for the key move f3g1.", listOf("f3g1", "e2d2", "f8f2", "b6f2", "h2h1q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "6k1/8/4p1p1/1P5p/1B2PP2/3pbK1P/2p5/4R3 b - - 3 36", "Execute the precise endgame technique with d3d2 to secure victory.", "Look for the key move d3d2.", listOf("d3d2", "b4d2", "e3d2", "b5b6", "d2e1")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "7k/p3b2p/Pp1n4/1PpPp3/2B1PpP1/1P6/5BK1/8 w - - 1 34", "Execute the precise endgame technique with f2c5 to secure victory.", "Look for the key move f2c5.", listOf("f2c5", "b6c5", "b5b6", "a7b6", "a6a7")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "2R5/3Nb1pk/1r5p/2pP4/1p2P3/3P4/4K1PP/8 b - - 2 39", "Execute the precise endgame technique with b4b3 to secure victory.", "Look for the key move b4b3.", listOf("b4b3", "d5d6", "e7d6", "d7b6", "b3b2", "b6c4", "b2b1q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/P1q4p/5pk1/6p1/5P2/6KP/5QP1/7r w - - 0 52", "Execute the precise endgame technique with a7a8q to secure victory.", "Look for the key move a7a8q.", listOf("a7a8q", "c7c3", "a8f3")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "4r3/6K1/8/4R1P1/1PpP4/P1P3k1/8/4b3 b - - 6 52", "Execute the precise endgame technique with e8e5 to secure victory.", "Look for the key move e8e5.", listOf("e8e5", "d4e5", "e1c3", "g7g6", "c3e5", "g6f5", "c4c3", "f5e5", "c3c2", "g5g6", "c2c1q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "2r3k1/p4p1p/Pn2p1p1/3pP1P1/5P2/4B1P1/2r4R/R6K w - - 0 29", "Execute the precise endgame technique with h2c2 to secure victory.", "Look for the key move h2c2.", listOf("h2c2", "c8c2", "e3b6", "a7b6", "a6a7")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "1rr3k1/5p2/3p2p1/3PP3/1p5R/2p2P2/2Q3PP/6K1 b - - 1 32", "Execute the precise endgame technique with b4b3 to secure victory.", "Look for the key move b4b3.", listOf("b4b3", "c2c1", "b3b2", "c1h6", "b2b1q", "g1f2", "b8b2", "f2g3", "b1e1", "g3h3", "e1h4")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "7Q/5R2/3k2p1/4p3/4P1P1/8/p4p1K/r7 b - - 0 46", "Execute the precise endgame technique with a1h1 to secure victory.", "Look for the key move a1h1.", listOf("a1h1", "h2h1", "a2a1q", "h1g2", "a1g1")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/4r2k/6p1/8/4qPPp/1P2pR1P/P3Q2K/8 b - g3 0 49", "Execute the precise endgame technique with e4f3 to secure victory.", "Look for the key move e4f3.", listOf("e4f3", "e2f3", "e3e2", "f3c3", "e2e1q", "c3e1", "e7e1")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/p2k4/2p5/8/Q7/2BK1p2/PP3q2/8 b - - 1 40", "Execute the precise endgame technique with f2e2 to secure victory.", "Look for the key move f2e2.", listOf("f2e2", "d3d4", "f3f2", "a4a7", "d7e6")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "6r1/3k4/1K1P4/2P5/R7/5b2/8/8 w - - 1 69", "Execute the precise endgame technique with a4a7 to secure victory.", "Look for the key move a4a7.", listOf("a4a7", "d7e6", "a7e7", "e6d5", "d6d7")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "6k1/1N3ppp/p1PK4/8/8/4p2P/8/1r6 w - - 0 44", "Execute the precise endgame technique with c6c7 to secure victory.", "Look for the key move c6c7.", listOf("c6c7", "b1d1", "d6c6", "h7h6", "c7c8q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "r5k1/p2Q1pp1/1p2P2p/2p5/P1Pp4/5q2/1P3PrP/3RRK2 w - - 1 32", "Execute the precise endgame technique with d7f7 to secure victory.", "Look for the key move d7f7.", listOf("d7f7", "f3f7", "e6f7", "g8f7", "f1g2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/8/KPk5/2rp1p1P/6p1/2n1p3/2R3B1/8 w - - 0 52", "Execute the precise endgame technique with g2f1 to secure victory.", "Look for the key move g2f1.", listOf("g2f1", "c3e4", "f1b5", "c6d6", "b6b7")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/6k1/8/1K1np1p1/P5P1/1B3r2/1PP5/8 w - - 2 37", "Execute the precise endgame technique with b3d5 to secure victory.", "Look for the key move b3d5.", listOf("b3d5", "f3f4", "a4a5", "e5e4", "a5a6", "e4e3", "a6a7")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "7R/6P1/p3p2P/1p2k3/8/2Ppp3/r5r1/3R3K w - - 0 41", "Execute the precise endgame technique with g7g8q to secure victory.", "Look for the key move g7g8q.", listOf("g7g8q", "g2g8", "h8g8", "e3e2", "d1e1")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/8/1pr3kp/pbPpp1pN/3Pp1P1/1P2K3/P6P/2R5 w - - 0 30", "Execute the precise endgame technique with c5b6 to secure victory.", "Look for the key move c5b6.", listOf("c5b6", "c6c1", "b6b7", "c1e1", "e3d2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "6k1/R4p1p/6P1/3p4/8/2b5/1r3rPP/5RK1 w - - 0 27", "Execute the precise endgame technique with g6f7 to secure victory.", "Look for the key move g6f7.", listOf("g6f7", "g8f8", "a7a8")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "1k5r/pp4p1/7r/3p2p1/3P2R1/2P1pP1P/PP3b2/K4B1R b - - 1 38", "Execute the precise endgame technique with h6h3 to secure victory.", "Look for the key move h6h3.", listOf("h6h3", "f1h3", "e3e2", "h1b1", "h8h3")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/4kppR/r1p1p2p/p3P2P/4P1P1/1pP2P2/3K2B1/8 b - - 1 29", "Execute the precise endgame technique with a5a4 to secure victory.", "Look for the key move a5a4.", listOf("a5a4", "g2f1", "a4a3", "f1a6", "a3a2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "1k6/1p1q2r1/P2p3p/1NpPpn1Q/5b2/2P5/1P2B1P1/R6K w - - 4 29", "Execute the precise endgame technique with a6a7 to secure victory.", "Look for the key move a6a7.", listOf("a6a7", "b8a8", "b5c7", "d7c7", "h5e8", "c7b8", "a7b8q")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "5rk1/q4ppp/p3Pb2/3nN3/P2P4/7Q/1r4PP/1R3RK1 w - - 0 25", "Execute the precise endgame technique with e6f7 to secure victory.", "Look for the key move e6f7.", listOf("e6f7", "f8f7", "h3c8", "f7f8", "c8e6")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "3r4/P1R5/7P/4p3/8/4K3/2p5/1k6 b - - 0 55", "Execute the precise endgame technique with c2c1q to secure victory.", "Look for the key move c2c1q.", listOf("c2c1q", "c7c1", "b1c1", "e3e4", "d8e8")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "5rk1/pR5p/2r1pBp1/4P3/2b5/3p4/P4PPP/4R1K1 b - - 1 27", "Execute the precise endgame technique with f8f6 to secure victory.", "Look for the key move f8f6.", listOf("f8f6", "e5f6", "d3d2", "e1d1", "c4e2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "1k6/pp5P/2p5/8/5P2/2P3bq/PP6/3R1RK1 w - - 5 39", "Execute the precise endgame technique with d1d8 to secure victory.", "Look for the key move d1d8.", listOf("d1d8", "b8c7", "h7h8q", "g3h2", "g1f2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/8/6pR/3P2P1/1kn5/1p2P3/8/2K5 b - - 5 45", "Execute the precise endgame technique with b4c3 to secure victory.", "Look for the key move b4c3.", listOf("b4c3", "h6h2", "b3b2", "h2b2", "c4b2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "6k1/5p2/4p2p/3pPP2/1R5P/2r5/2B5/2b2K2 w - - 1 47", "Execute the precise endgame technique with b4b8 to secure victory.", "Look for the key move b4b8.", listOf("b4b8", "g8h7", "f5e6", "c3c2", "e6e7")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "r3r1k1/p4ppp/8/3Q4/3P4/Ppp2N2/2b2PPP/K2R3R b - - 1 24", "Execute the precise endgame technique with b3b2 to secure victory.", "Look for the key move b3b2.", listOf("b3b2", "a1a2", "c2d1", "h1d1", "c3c2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "6r1/pp1qbpk1/8/2pPp1r1/2P1Pp2/P1N2QpR/3B2K1/R7 b - - 1 31", "Execute the precise endgame technique with d7h3 to secure victory.", "Look for the key move d7h3.", listOf("d7h3", "g2h3", "g3g2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "4r1k1/pp3p2/3p1Ppq/5P2/8/6RP/PP2rQ2/6RK w - - 3 40", "Execute the precise endgame technique with g3g6 to secure victory.", "Look for the key move g3g6.", listOf("g3g6", "f7g6", "f6f7", "g8f7", "f5g6", "f7e7", "f2e2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "7r/p4pk1/2pr1nP1/4p1Q1/4P2P/2qB4/b1P3P1/3R1RK1 w - - 1 24", "Execute the precise endgame technique with g6f7 to secure victory.", "Look for the key move g6f7.", listOf("g6f7", "g7f7", "d3c4", "c3c4", "g5e5", "c4e6", "e5d6")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "1r4k1/2pr1ppp/1pP5/pQ6/8/q7/5PPP/3R2K1 w - - 0 29", "Execute the precise endgame technique with c6d7 to secure victory.", "Look for the key move c6d7.", listOf("c6d7", "a3e7", "g1f1")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "4r3/p6k/1p5P/1P2P1K1/8/5N2/8/8 w - - 1 43", "Execute the precise endgame technique with g5h5 to secure victory.", "Look for the key move g5h5.", listOf("g5h5", "h7g8", "f3g5", "e8e5", "h5g6", "e5b5", "h6h7")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "1r6/k2qn1b1/p1N1p1p1/2PpPpN1/2n2P1P/p4B2/1PP2Q2/1K1R3R b - - 0 32", "Execute the precise endgame technique with e7c6 to secure victory.", "Look for the key move e7c6.", listOf("e7c6", "b2b3", "a3a2", "b1a1", "c4e5", "f4e5", "g7e5", "a1a2", "b8b5")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "r6k/1p2R1b1/3P2P1/8/b1B2p2/K1p2P1P/8/7R b - - 0 34", "Execute the precise endgame technique with a4d1 to secure victory.", "Look for the key move a4d1.", listOf("a4d1", "c4a6", "c3c2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "6k1/pp4pp/r2P4/5q2/2PR4/B2RK1P1/P5bP/8 w - - 4 33", "Execute the precise endgame technique with d6d7 to secure victory.", "Look for the key move d6d7.", listOf("d6d7", "a6e6", "e3d2", "f5a5", "d2c2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/p4p1k/1p4p1/3pQr2/3P3p/5qP1/PP3P1K/3B1R2 b - - 7 35", "Execute the precise endgame technique with h4g3 to secure victory.", "Look for the key move h4g3.", listOf("h4g3", "h2g1", "g3f2", "g1h2", "f3d3", "d1e2", "d3d2", "e5f5", "g6f5")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "4b1k1/4Pr2/3R2pp/1ppBP2q/8/PP4P1/2P4P/3R3K w - - 3 39", "Execute the precise endgame technique with d1f1 to secure victory.", "Look for the key move d1f1.", listOf("d1f1", "g8g7", "d5f7", "e8f7", "d6d8", "h5e5", "f1f7", "g7f7", "e7e8q", "e5e8", "d8e8")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "r6r/pp2kB2/1bp2p2/4p1p1/6b1/1QP3p1/PP1R1PP1/4R1K1 b - - 3 23", "Execute the precise endgame technique with b6f2 to secure victory.", "Look for the key move b6f2.", listOf("b6f2", "d2f2", "h8h1", "g1h1", "g3f2")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "1kq1r2r/p1p2pp1/1pPb4/3P4/Q1B2nbp/4B3/P2N1PPP/1R2R1K1 w - - 0 21", "Execute the precise endgame technique with c4a6 to secure victory.", "Look for the key move c4a6.", listOf("c4a6", "c8f5", "e3b6", "e8e1", "b1e1", "c7b6", "c6c7", "d6c7", "e1e8", "h8e8", "a4e8", "f5c8", "a6c8")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "8/5p1k/1P4pp/3Qn3/4BP2/6P1/1p2PK1P/2q5 b - - 2 34", "Execute the precise endgame technique with b2b1q to secure victory.", "Look for the key move b2b1q.", listOf("b2b1q", "e4b1", "e5g4", "f2f3", "c1h1")),
        RawLesson("Advanced Pawn Technique in Passed Pawns", "1R6/8/8/p1p5/PbP3p1/1P3pk1/8/5K2 b - - 5 44", "Execute the precise endgame technique with b4c3 to secure victory.", "Look for the key move b4c3.", listOf("b4c3", "b8f8", "g3h3", "f8f7", "g4g3", "f7f3", "h3h2", "f1e2", "g3g2", "f3c3", "g2g1q"))
    )

    private var cachedLessons: List<EndgameLesson>? = null

    fun getLessons(): List<EndgameLesson> {
        cachedLessons?.let { return it }
        val lessons = (1..100).map { level ->
            val tier = when {
                level <= 35 -> "Easy"
                level <= 70 -> "Moderate"
                else -> "Hard"
            }
            val base = pool[level - 1]
            EndgameLesson(
                id = "e_passed_pawns_$level",
                title = "Level $level ($tier difficulty) • ${base.subtitle}",
                category = "Passed Pawns",
                fen = base.fen,
                explanation = "Level $level ($tier difficulty): ${base.explanation}",
                hint = base.hint,
                solutionMoves = base.moves
            )
        }
        cachedLessons = lessons
        return lessons
    }
}
