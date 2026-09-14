package com.chessmaster.play.data

import com.chessmaster.play.model.EndgameLesson

object QueenEndgameDatabase {

    private data class RawLesson(
        val subtitle: String,
        val fen: String,
        val explanation: String,
        val hint: String,
        val moves: List<String>
    )

    private val pool = listOf(
        RawLesson("Mate Technique in Queen Endgame", "3Q4/P6p/8/6pk/8/5pPK/5P1P/8 b - - 0 60", "Execute the precise endgame technique with g5g4 to secure victory.", "Look for the key move g5g4.", listOf("g5g4")),
        RawLesson("Advanced Pawn Technique in Queen Endgame", "1Q6/5ppp/8/8/8/2pk3P/3p2P1/3K4 b - - 0 52", "Execute the precise endgame technique with c3c2 to secure victory.", "Look for the key move c3c2.", listOf("c3c2")),
        RawLesson("Advanced Pawn Technique in Queen Endgame", "8/1k6/p7/3Q3P/p2K1P2/1q6/8/8 b - - 5 55", "Execute the precise endgame technique with b3d5 to secure victory.", "Look for the key move b3d5.", listOf("b3d5", "d4d5", "a4a3", "h5h6", "a3a2")),
        RawLesson("Mate Technique in Queen Endgame", "8/5p2/2p1p3/3pPq1p/kPpP1P2/2P3K1/3Q4/8 w - - 0 57", "Execute the precise endgame technique with d2a2 to secure victory.", "Look for the key move d2a2.", listOf("d2a2", "a4b5", "a2a5")),
        RawLesson("Mate Technique in Queen Endgame", "2k5/8/2KQ4/2P5/8/8/8/1q6 b - - 14 67", "Execute the precise endgame technique with b1b7 to secure victory.", "Look for the key move b1b7.", listOf("b1b7")),
        RawLesson("Advanced Pawn Technique in Queen Endgame", "8/3q4/k7/1p3KP1/8/1Q6/8/8 w - - 3 74", "Execute the precise endgame technique with b3e6 to secure victory.", "Look for the key move b3e6.", listOf("b3e6", "d7e6", "f5e6", "b5b4", "g5g6", "b4b3", "g6g7", "b3b2", "g7g8q")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "1Q6/5pk1/p4p2/Pq1p4/5P2/4P3/6PK/8 w - - 0 39", "Execute the precise endgame technique with b8b5 to secure victory.", "Look for the key move b8b5.", listOf("b8b5", "a6b5", "a5a6")),
        RawLesson("Master Technique in Queen Endgame", "8/1p5k/p3p1q1/3pP3/2p3PQ/2P5/PP6/2K5 b - - 4 33", "Execute the precise endgame technique with g6h6 to secure victory.", "Look for the key move g6h6.", listOf("g6h6", "h4h6", "h7h6")),
        RawLesson("Mate Technique in Queen Endgame", "8/8/5p1p/5Q2/6p1/6Pk/2pq1P1P/6K1 w - - 0 53", "Execute the precise endgame technique with f5h5 to secure victory.", "Look for the key move f5h5.", listOf("f5h5")),
        RawLesson("Mate Technique in Queen Endgame", "8/8/7p/4Q1pk/6q1/6P1/7K/8 w - - 1 66", "Execute the precise endgame technique with e5e8 to secure victory.", "Look for the key move e5e8.", listOf("e5e8")),
        RawLesson("Master Technique in Queen Endgame", "8/7p/1p3Qpk/1Pp5/2Pp4/5PPK/2q4P/8 b - - 2 41", "Execute the precise endgame technique with c2f5 to secure victory.", "Look for the key move c2f5.", listOf("c2f5", "f6f5", "g6f5")),
        RawLesson("Mate Technique in Queen Endgame", "8/8/p7/2Q5/k1K5/8/8/7q w - - 2 53", "Execute the precise endgame technique with c5b4 to secure victory.", "Look for the key move c5b4.", listOf("c5b4")),
        RawLesson("Mate Technique in Queen Endgame", "8/P6p/6p1/1k6/3Qq3/1PP5/6PP/6K1 b - - 0 35", "Execute the precise endgame technique with e4e1 to secure victory.", "Look for the key move e4e1.", listOf("e4e1")),
        RawLesson("Mate Technique in Queen Endgame", "8/7k/2Q2q2/1P6/4p3/5p2/5P2/5K2 b - - 0 54", "Execute the precise endgame technique with f6a1 to secure victory.", "Look for the key move f6a1.", listOf("f6a1", "c6c1", "a1c1")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "6Q1/p7/8/1p6/1P3PK1/P2kq3/8/8 b - - 2 58", "Execute the precise endgame technique with e3g1 to secure victory.", "Look for the key move e3g1.", listOf("e3g1", "g4f5", "g1g8")),
        RawLesson("Mate Technique in Queen Endgame", "1Q6/2Pk4/p1q1pp1p/6p1/1p6/1P2P1P1/5P1P/6K1 w - - 3 43", "Execute the precise endgame technique with b8d8 to secure victory.", "Look for the key move b8d8.", listOf("b8d8")),
        RawLesson("Mate Technique in Queen Endgame", "8/pQ3p1k/3q1P1p/6p1/P2p2K1/1P1P3P/2P3P1/8 b - - 0 37", "Execute the precise endgame technique with d6f4 to secure victory.", "Look for the key move d6f4.", listOf("d6f4", "g4h5", "f4h4")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "8/p2Q4/2P2kp1/7p/7P/1q4PK/8/8 b - - 0 60", "Execute the precise endgame technique with b3e6 to secure victory.", "Look for the key move b3e6.", listOf("b3e6", "d7e6", "f6e6", "c6c7", "e6d7", "c7c8q", "d7c8")),
        RawLesson("Master Technique in Queen Endgame", "8/8/8/8/8/4K3/5Q2/1qk5 w - - 6 54", "Execute the precise endgame technique with f2d2 to secure victory.", "Look for the key move f2d2.", listOf("f2d2")),
        RawLesson("Hanging Piece Technique in Queen Endgame", "1Q1q1k2/1p3pp1/p1p4p/4P3/PP3PP1/7P/8/6K1 w - - 4 31", "Execute the precise endgame technique with b8d8 to secure victory.", "Look for the key move b8d8.", listOf("b8d8")),
        RawLesson("Master Technique in Queen Endgame", "8/6pk/7p/Q7/6PK/3q4/1P5P/8 b - - 2 45", "Execute the precise endgame technique with g7g5 to secure victory.", "Look for the key move g7g5.", listOf("g7g5", "a5g5", "h6g5")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "5Q2/7p/6p1/7k/8/1Pq3pP/6P1/7K w - - 2 46", "Execute the precise endgame technique with f8f3 to secure victory.", "Look for the key move f8f3.", listOf("f8f3", "c3f3", "g2f3")),
        RawLesson("Discovered Attack Technique in Queen Endgame", "5k2/p3pp2/7p/2Qp1P2/6q1/8/2K5/8 w - - 0 44", "Execute the precise endgame technique with c5c8 to secure victory.", "Look for the key move c5c8.", listOf("c5c8", "f8g7", "f5f6", "g7f6", "c8g4")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "8/8/3q3k/8/4pQ2/4P2P/5pK1/8 b - - 1 42", "Execute the precise endgame technique with d6f4 to secure victory.", "Look for the key move d6f4.", listOf("d6f4", "e3f4", "e4e3")),
        RawLesson("Master Technique in Queen Endgame", "6k1/p4p2/P3pQ2/1p4P1/6q1/1KP5/1P6/8 b - - 1 46", "Execute the precise endgame technique with g4a4 to secure victory.", "Look for the key move g4a4.", listOf("g4a4")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "4k3/1q3p2/6pp/8/P1Q5/5KPP/8/8 w - - 3 50", "Execute the precise endgame technique with c4e4 to secure victory.", "Look for the key move c4e4.", listOf("c4e4", "b7e4", "f3e4")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "8/8/3p1k1p/3Pqpp1/2Q5/6PP/5PK1/8 b - - 4 34", "Execute the precise endgame technique with e5e4 to secure victory.", "Look for the key move e5e4.", listOf("e5e4", "c4e4", "f5e4")),
        RawLesson("Master Technique in Queen Endgame", "8/7p/p4p1k/3q2p1/5Q2/1P5P/6PK/8 w - - 0 40", "Execute the precise endgame technique with f4f6 to secure victory.", "Look for the key move f4f6.", listOf("f4f6", "h6h5", "g2g4", "h5h4", "f6f2")),
        RawLesson("Defensive Move Technique in Queen Endgame", "2k5/6K1/2q2Q2/p7/8/6P1/7P/8 b - - 0 66", "Execute the precise endgame technique with c6f6 to secure victory.", "Look for the key move c6f6.", listOf("c6f6", "g7f6", "a5a4")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "6k1/6p1/8/Q4K2/P4P2/6P1/8/3q4 b - - 6 44", "Execute the precise endgame technique with d1h5 to secure victory.", "Look for the key move d1h5.", listOf("d1h5", "f5e4", "h5a5")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "8/6pk/8/8/Q2Pp3/PKP5/1P2q3/8 b - - 2 40", "Execute the precise endgame technique with e2d1 to secure victory.", "Look for the key move e2d1.", listOf("e2d1", "b3b4", "d1a4", "b4a4", "e4e3")),
        RawLesson("Mate Technique in Queen Endgame", "8/2p3k1/2Pp2P1/2pP1p2/5P1Q/P2q4/7K/4q3 w - - 0 58", "Execute the precise endgame technique with h4h7 to secure victory.", "Look for the key move h4h7.", listOf("h4h7", "g7f6", "h7f7")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "2k5/p7/8/4P2p/2Q5/1PPqP1PK/7P/8 b - - 4 44", "Execute the precise endgame technique with d3c4 to secure victory.", "Look for the key move d3c4.", listOf("d3c4", "b3c4", "a7a5", "h3h4", "a5a4", "h4h5", "a4a3")),
        RawLesson("Mate Technique in Queen Endgame", "8/3Q2pp/6k1/3K4/6P1/8/7P/q7 w - - 10 47", "Execute the precise endgame technique with d7f5 to secure victory.", "Look for the key move d7f5.", listOf("d7f5", "g6h6", "f5h5")),
        RawLesson("Mate Technique in Queen Endgame", "8/5p2/6pk/3PQp2/8/4PP2/p5PK/4q3 w - - 0 40", "Execute the precise endgame technique with e5h8 to secure victory.", "Look for the key move e5h8.", listOf("e5h8", "h6g5", "f3f4", "g5g4", "h8h3")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "6k1/1p3p2/p1p5/4P2Q/1P3P1P/P4K2/8/6q1 b - - 2 40", "Execute the precise endgame technique with g1d1 to secure victory.", "Look for the key move g1d1.", listOf("g1d1", "f3g2", "d1h5")),
        RawLesson("Master Technique in Queen Endgame", "Q7/6pk/7p/6PP/8/1q6/p4PK1/8 w - - 0 40", "Execute the precise endgame technique with g5g6 to secure victory.", "Look for the key move g5g6.", listOf("g5g6")),
        RawLesson("Mate Technique in Queen Endgame", "8/8/5p1p/6pk/8/7P/2Q3PK/q7 w - - 4 53", "Execute the precise endgame technique with g2g4 to secure victory.", "Look for the key move g2g4.", listOf("g2g4", "h5h4", "c2f2")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "6k1/6p1/5p1p/8/8/6QP/Pq4PK/8 w - - 9 44", "Execute the precise endgame technique with g3b3 to secure victory.", "Look for the key move g3b3.", listOf("g3b3", "b2b3", "a2b3")),
        RawLesson("Master Technique in Queen Endgame", "2Q5/1p5k/1p3K2/8/5q2/P1P5/8/8 w - - 3 50", "Execute the precise endgame technique with c8f5 to secure victory.", "Look for the key move c8f5.", listOf("c8f5", "f4f5", "f6f5")),
        RawLesson("Advanced Pawn Technique in Queen Endgame", "8/1p6/k7/1q5P/p7/6P1/4Q2K/8 w - - 6 78", "Execute the precise endgame technique with e2b5 to secure victory.", "Look for the key move e2b5.", listOf("e2b5", "a6b5", "h5h6", "a4a3", "h6h7")),
        RawLesson("Mate Technique in Queen Endgame", "8/5p2/8/2Q3pk/PP4qp/6P1/5PKP/8 b - - 3 48", "Execute the precise endgame technique with h4h3 to secure victory.", "Look for the key move h4h3.", listOf("h4h3", "g2f1", "g4d1")),
        RawLesson("Dovetail Mate Technique in Queen Endgame", "8/7p/3p2p1/3Pp3/3qk3/7P/6PK/3Q4 w - - 0 47", "Execute the precise endgame technique with d1f3 to secure victory.", "Look for the key move d1f3.", listOf("d1f3")),
        RawLesson("Master Technique in Queen Endgame", "8/8/5K2/5Q2/3k1p2/5Pp1/6P1/1q6 b - - 3 58", "Execute the precise endgame technique with b1f5 to secure victory.", "Look for the key move b1f5.", listOf("b1f5", "f6f5", "d4e3")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "5Q2/7K/8/8/4p2P/3k2q1/8/8 w - - 2 54", "Execute the precise endgame technique with f8a3 to secure victory.", "Look for the key move f8a3.", listOf("f8a3", "d3d2", "a3g3")),
        RawLesson("Master Technique in Queen Endgame", "4K3/1k2P2q/3Q4/8/8/8/8/8 b - - 4 55", "Execute the precise endgame technique with h7g8 to secure victory.", "Look for the key move h7g8.", listOf("h7g8", "e8d7", "g8c8")),
        RawLesson("Mate Technique in Queen Endgame", "8/4Q2p/5ppk/8/5P2/6PK/2pq3P/8 w - - 2 40", "Execute the precise endgame technique with e7f8 to secure victory.", "Look for the key move e7f8.", listOf("e7f8", "h6h5", "g3g4")),
        RawLesson("Advanced Pawn Technique in Queen Endgame", "8/2Q1qkpp/p7/1p1P4/1P6/7P/6PK/8 w - - 5 36", "Execute the precise endgame technique with d5d6 to secure victory.", "Look for the key move d5d6.", listOf("d5d6", "e7c7", "d6c7")),
        RawLesson("Advanced Pawn Technique in Queen Endgame", "5k2/7q/8/1p3Q2/6P1/8/6K1/8 b - - 2 62", "Execute the precise endgame technique with h7f5 to secure victory.", "Look for the key move h7f5.", listOf("h7f5", "g4f5", "b5b4", "g2f1", "b4b3", "f1e1", "b3b2")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "8/1pk5/2p5/8/4p2q/4Q3/PP3P2/1K6 w - - 0 38", "Execute the precise endgame technique with e3g3 to secure victory.", "Look for the key move e3g3.", listOf("e3g3", "h4g3", "f2g3")),
        RawLesson("Deflection Technique in Queen Endgame", "8/3Q4/6p1/3pk1P1/3qpp1p/1P5P/P4PK1/8 w - - 6 44", "Execute the precise endgame technique with d7g7 to secure victory.", "Look for the key move d7g7.", listOf("d7g7", "e5f5", "g7d4")),
        RawLesson("Mate Technique in Queen Endgame", "6k1/8/6p1/2Q4p/P6P/1P3qPK/2P5/8 b - - 0 46", "Execute the precise endgame technique with f3h1 to secure victory.", "Look for the key move f3h1.", listOf("f3h1")),
        RawLesson("Defensive Move Technique in Queen Endgame", "8/6p1/3q3k/7p/5Q1P/6P1/7K/8 b - - 1 50", "Execute the precise endgame technique with d6f4 to secure victory.", "Look for the key move d6f4.", listOf("d6f4", "g3f4", "h6g6")),
        RawLesson("Mate Technique in Queen Endgame", "5Q2/3kp3/1pp4P/8/4p3/pPq1P3/P1P3P1/2K5 b - - 3 37", "Execute the precise endgame technique with c3e1 to secure victory.", "Look for the key move c3e1.", listOf("c3e1")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "6k1/2p4p/p2q1Q2/1p6/4K3/8/PP5P/8 w - - 9 42", "Execute the precise endgame technique with f6d6 to secure victory.", "Look for the key move f6d6.", listOf("f6d6", "c7d6", "e4d5")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "8/6pk/7p/2pq4/3p4/5PP1/P3QK1P/8 w - - 2 41", "Execute the precise endgame technique with e2e4 to secure victory.", "Look for the key move e2e4.", listOf("e2e4", "d5e4", "f3e4")),
        RawLesson("Defensive Move Technique in Queen Endgame", "8/6k1/p7/4P3/2q3Q1/6PK/8/8 b - - 1 49", "Execute the precise endgame technique with c4g4 to secure victory.", "Look for the key move c4g4.", listOf("c4g4", "h3g4", "a6a5", "g4f5", "a5a4")),
        RawLesson("Master Technique in Queen Endgame", "8/1p5p/6p1/2P5/1P2k3/P3qp1Q/8/5K2 w - - 6 44", "Execute the precise endgame technique with h3e6 to secure victory.", "Look for the key move h3e6.", listOf("h3e6", "e4d4", "e6e3", "d4e3", "b4b5", "g6g5", "c5c6")),
        RawLesson("Deflection Technique in Queen Endgame", "7Q/pp3k2/8/3pP1p1/8/6PK/PP2q2P/8 b - - 0 27", "Execute the precise endgame technique with g5g4 to secure victory.", "Look for the key move g5g4.", listOf("g5g4", "h3h4", "e2h2", "h4g4", "h2h8")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "8/p7/3Pk3/1p2P2q/2p5/7Q/PP4PK/8 b - - 6 56", "Execute the precise endgame technique with h5h3 to secure victory.", "Look for the key move h5h3.", listOf("h5h3", "h2h3", "b5b4", "h3g4", "c4c3")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "8/8/p1q2ppQ/1p3k1p/1Pp2P2/P3PP2/6KP/8 b - - 2 36", "Execute the precise endgame technique with c4c3 to secure victory.", "Look for the key move c4c3.", listOf("c4c3", "e3e4", "f5e6", "f4f5", "g6f5")),
        RawLesson("Master Technique in Queen Endgame", "3K4/3Q4/7k/1Pq3p1/8/8/8/8 w - - 0 56", "Execute the precise endgame technique with d7c6 to secure victory.", "Look for the key move d7c6.", listOf("d7c6", "c5c6", "b5c6")),
        RawLesson("Defensive Move Technique in Queen Endgame", "7k/1p4p1/p1p2q2/2P2pQP/PP3P1K/8/8/8 w - - 1 47", "Execute the precise endgame technique with g5f6 to secure victory.", "Look for the key move g5f6.", listOf("g5f6", "g7f6", "h5h6", "h8g8", "h4h5")),
        RawLesson("Mate Technique in Queen Endgame", "5Q2/7p/6p1/8/6Pk/P3q2P/1P5K/3q4 w - - 1 49", "Execute the precise endgame technique with f8f6 to secure victory.", "Look for the key move f8f6.", listOf("f8f6", "e3g5", "f6f2")),
        RawLesson("Master Technique in Queen Endgame", "6k1/1p3p2/2p2Qq1/2Pp1K2/1P1P1p2/8/7P/8 w - - 6 38", "Execute the precise endgame technique with f6g6 to secure victory.", "Look for the key move f6g6.", listOf("f6g6", "f7g6", "f5f4")),
        RawLesson("Advanced Pawn Technique in Queen Endgame", "8/2q2p1k/4p1pp/1p2P3/P3QP2/7P/6PK/8 b - - 0 32", "Execute the precise endgame technique with c7c4 to secure victory.", "Look for the key move c7c4.", listOf("c7c4", "e4c4", "b5c4", "a4a5", "c4c3", "a5a6", "c3c2", "a6a7", "c2c1q", "a7a8q", "c1f4")),
        RawLesson("Defensive Move Technique in Queen Endgame", "1k6/1pp5/p7/5q2/2P2Q2/3P2P1/6K1/8 b - - 1 40", "Execute the precise endgame technique with f5f4 to secure victory.", "Look for the key move f5f4.", listOf("f5f4", "g3f4", "a6a5", "g2f3", "a5a4")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "5Q2/8/p3p1kP/8/3p2PK/4q3/P7/8 b - - 6 49", "Execute the precise endgame technique with e3h6 to secure victory.", "Look for the key move e3h6.", listOf("e3h6", "f8h6", "g6h6")),
        RawLesson("Defensive Move Technique in Queen Endgame", "8/5Q2/3p4/2p3p1/P5k1/1P1q4/8/6K1 w - - 1 49", "Execute the precise endgame technique with f7c4 to secure victory.", "Look for the key move f7c4.", listOf("f7c4", "d3c4", "b3c4", "g4f5", "a4a5")),
        RawLesson("Deflection Technique in Queen Endgame", "6k1/1PQ2p2/6pp/8/5K2/3q2P1/6P1/8 b - - 8 50", "Execute the precise endgame technique with g6g5 to secure victory.", "Look for the key move g6g5.", listOf("g6g5", "f4e5", "d3g3")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "8/5Pk1/p2q2Q1/1p2p2P/2p5/7K/1P3P2/8 b - - 1 48", "Execute the precise endgame technique with d6g6 to secure victory.", "Look for the key move d6g6.", listOf("d6g6", "h5g6", "b5b4", "f7f8q", "g7f8")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "8/5k2/4p1p1/p6p/2Kp1P1P/1P2q3/P2Q4/8 w - - 2 46", "Execute the precise endgame technique with d2e3 to secure victory.", "Look for the key move d2e3.", listOf("d2e3", "d4e3", "c4d3", "e3e2", "d3e2")),
        RawLesson("Defensive Move Technique in Queen Endgame", "3k4/pQ6/2p2p2/2Pqp3/8/6PK/5P1P/8 b - - 2 35", "Execute the precise endgame technique with d5d7 to secure victory.", "Look for the key move d5d7.", listOf("d5d7", "b7d7", "d8d7", "h3g4", "a7a5")),
        RawLesson("Advanced Pawn Technique in Queen Endgame", "2q5/4Q3/4p1k1/3p2P1/3Pp3/p3P3/5PK1/8 w - - 0 53", "Execute the precise endgame technique with e7f6 to secure victory.", "Look for the key move e7f6.", listOf("e7f6", "g6h7", "g5g6", "h7h6", "g6g7", "h6h7", "f6f8")),
        RawLesson("Mate Technique in Queen Endgame", "8/5p2/pq5p/1p6/6k1/6P1/P6P/2Q4K w - - 0 37", "Execute the precise endgame technique with c1f4 to secure victory.", "Look for the key move c1f4.", listOf("c1f4", "g4h5", "f4f5")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "8/5p2/5kpp/8/1p2Q1P1/2q2P2/P5P1/7K b - - 7 48", "Execute the precise endgame technique with c3a1 to secure victory.", "Look for the key move c3a1.", listOf("c3a1", "h1h2", "a1e5")),
        RawLesson("Deflection Technique in Queen Endgame", "8/1q3k2/8/3p1ppp/1QpP3P/P5P1/1P6/2K5 b - - 1 41", "Execute the precise endgame technique with b7b4 to secure victory.", "Look for the key move b7b4.", listOf("b7b4", "a3b4", "f5f4", "g3f4", "g5h4")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "2Q5/1p4pk/p1P4p/8/P5KP/1q4P1/8/8 b - - 0 52", "Execute the precise endgame technique with b3a4 to secure victory.", "Look for the key move b3a4.", listOf("b3a4", "g4h3", "a4c6")),
        RawLesson("Master Technique in Queen Endgame", "5k2/7p/1p1PQ1p1/p1p5/q1P1K2P/6P1/5P2/8 b - - 0 40", "Execute the precise endgame technique with a4e8 to secure victory.", "Look for the key move a4e8.", listOf("a4e8", "e6e7", "e8e7")),
        RawLesson("Master Technique in Queen Endgame", "7k/1Q6/p6p/1q6/8/1K6/P1P5/8 w - - 3 37", "Execute the precise endgame technique with b7b5 to secure victory.", "Look for the key move b7b5.", listOf("b7b5", "a6b5", "c2c4", "b5c4", "b3c4")),
        RawLesson("Mate Technique in Queen Endgame", "8/7p/2p5/5pQ1/4p3/3q1kPP/3P1P2/6K1 w - - 3 36", "Execute the precise endgame technique with g5h5 to secure victory.", "Look for the key move g5h5.", listOf("g5h5")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "8/3Q4/5kp1/1p6/5P2/2p1PK1P/1q6/8 w - - 0 43", "Execute the precise endgame technique with e3e4 to secure victory.", "Look for the key move e3e4.", listOf("e3e4", "c3c2", "e4e5", "b2e5", "f4e5")),
        RawLesson("Master Technique in Queen Endgame", "3k4/3P3p/p2Q1pp1/1p3p2/8/P4P2/1q3PKP/8 w - - 0 36", "Execute the precise endgame technique with d6c6 to secure victory.", "Look for the key move d6c6.", listOf("d6c6", "b2c1", "c6c1")),
        RawLesson("Defensive Move Technique in Queen Endgame", "8/2Q2pk1/8/3pp2P/2q5/2P5/KP6/8 w - - 2 37", "Execute the precise endgame technique with c7c4 to secure victory.", "Look for the key move c7c4.", listOf("c7c4", "d5c4", "b2b4")),
        RawLesson("Defensive Move Technique in Queen Endgame", "8/5pk1/7p/7P/5Pq1/6Q1/6K1/8 b - - 1 55", "Execute the precise endgame technique with g4g3 to secure victory.", "Look for the key move g4g3.", listOf("g4g3", "g2g3", "g7f6", "g3g4", "f6e6")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "8/1pq5/2p3pk/2P2p1p/P1Q4P/4PKP1/8/8 w - - 3 36", "Execute the precise endgame technique with c4f4 to secure victory.", "Look for the key move c4f4.", listOf("c4f4", "c7f4", "f3f4", "h6g7", "f4e5")),
        RawLesson("Advanced Pawn Technique in Queen Endgame", "8/4k3/2q2pp1/P6p/1Q5P/2p1P1P1/5K2/8 b - - 1 51", "Execute the precise endgame technique with e7d7 to secure victory.", "Look for the key move e7d7.", listOf("e7d7", "a5a6", "c3c2")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "8/8/6p1/4qpkp/1Q6/P5PK/6P1/8 w - - 1 52", "Execute the precise endgame technique with b4h4 to secure victory.", "Look for the key move b4h4.", listOf("b4h4", "g5h6", "h4f4", "e5f4", "g3f4")),
        RawLesson("Defensive Move Technique in Queen Endgame", "8/1k6/2p5/p1P5/1p1qp3/1P2Q3/P4P2/5K2 b - - 1 51", "Execute the precise endgame technique with d4e3 to secure victory.", "Look for the key move d4e3.", listOf("d4e3", "f2e3", "b7a6")),
        RawLesson("Pin Technique in Queen Endgame", "5k2/5qp1/3pQp1p/4p2P/4P3/3P2P1/5PK1/8 w - - 1 40", "Execute the precise endgame technique with e6d6 to secure victory.", "Look for the key move e6d6.", listOf("e6d6", "f8g8", "d6d5", "f7d5", "e4d5")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "2Q5/pp6/8/1P2K3/7q/4k3/P7/8 b - - 6 44", "Execute the precise endgame technique with h4f4 to secure victory.", "Look for the key move h4f4.", listOf("h4f4", "e5d5", "f4d4", "d5e6", "d4g4", "e6e7", "g4c8")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "1q6/5pk1/4p1p1/3p2P1/p2P1K2/P3QP2/8/8 w - - 8 45", "Execute the precise endgame technique with e3e5 to secure victory.", "Look for the key move e3e5.", listOf("e3e5", "b8e5", "f4e5", "g7f8", "e5d6")),
        RawLesson("Defensive Move Technique in Queen Endgame", "8/8/3Qp1k1/5qp1/8/7P/5PK1/8 b - - 14 94", "Execute the precise endgame technique with f5d5 to secure victory.", "Look for the key move f5d5.", listOf("f5d5", "d6d5", "e6d5", "g2f3", "g6f5", "f3e3", "f5e5", "e3d3", "d5d4", "f2f3", "e5d5")),
        RawLesson("Defensive Move Technique in Queen Endgame", "8/8/4pp2/2p3k1/2q5/6P1/5PKP/1Q6 w - - 7 35", "Execute the precise endgame technique with b1h7 to secure victory.", "Look for the key move b1h7.", listOf("b1h7", "c4d5", "f2f3", "d5d2", "g2h3", "d2e3", "f3f4")),
        RawLesson("Advanced Pawn Technique in Queen Endgame", "4q3/PP6/K7/8/5k2/8/6p1/8 w - - 1 59", "Execute the precise endgame technique with b7b8q to secure victory.", "Look for the key move b7b8q.", listOf("b7b8q", "e8e5", "a7a8q", "g2g1q", "b8f8")),
        RawLesson("Defensive Move Technique in Queen Endgame", "8/8/7p/Q4P2/2q5/2kp3P/6PK/8 b - - 4 58", "Execute the precise endgame technique with c3c2 to secure victory.", "Look for the key move c3c2.", listOf("c3c2", "f5f6", "c4f4")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "8/2k2p2/p3p1p1/1p1qP1Pp/5P1P/P4Q2/KP6/8 w - - 0 41", "Execute the precise endgame technique with f3d5 to secure victory.", "Look for the key move f3d5.", listOf("f3d5", "e6d5", "a2b3", "c7d7", "b3c3", "d7e6", "c3d4", "e6f5", "d4d5")),
        RawLesson("Advanced Pawn Technique in Queen Endgame", "1Q6/7k/1P6/3q1p2/6p1/8/6PK/8 w - - 0 43", "Execute the precise endgame technique with b8c7 to secure victory.", "Look for the key move b8c7.", listOf("b8c7", "h7g6", "b6b7", "f5f4", "c7c2")),
        RawLesson("Advanced Pawn Technique in Queen Endgame", "8/8/8/1p6/3p4/1kp5/p7/Q1K5 b - - 1 71", "Execute the precise endgame technique with b5b4 to secure victory.", "Look for the key move b5b4.", listOf("b5b4", "c1d1", "b3a3", "a1c1", "a3a4", "d1e2", "b4b3", "c1g1", "a4a3", "g1d4", "a2a1q", "d4a7", "a3b2")),
        RawLesson("Queen Endgame Technique in Queen Endgame", "1Q6/6pk/6qp/1p6/2p1P1P1/5P2/5K1P/8 b - - 1 43", "Execute the precise endgame technique with g6c6 to secure victory.", "Look for the key move g6c6.", listOf("g6c6", "e4e5", "c4c3", "b8d6", "c6c8"))
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
                id = "e_queen_endgame_$level",
                title = "Level $level ($tier difficulty) • ${base.subtitle}",
                category = "Queen Endgame",
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
