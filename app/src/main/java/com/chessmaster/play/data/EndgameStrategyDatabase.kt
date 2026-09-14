package com.chessmaster.play.data

import com.chessmaster.play.model.EndgameLesson

object EndgameStrategyDatabase {

    private data class RawLesson(
        val subtitle: String,
        val fen: String,
        val explanation: String,
        val hint: String,
        val moves: List<String>
    )

    private val pool = listOf(
        RawLesson("Mate Technique in Endgame Strategy", "8/6pp/6k1/5pN1/5P2/5rPb/4R2P/6K1 b - - 1 35", "Execute the precise endgame technique with f3f1 to secure victory.", "Look for the key move f3f1.", listOf("f3f1")),
        RawLesson("Mate Technique in Endgame Strategy", "Q4rk1/p1p3p1/6P1/8/3P4/7P/q3r3/B4RK1 w - - 2 35", "Execute the precise endgame technique with a8f8 to secure victory.", "Look for the key move a8f8.", listOf("a8f8")),
        RawLesson("Mate Technique in Endgame Strategy", "6k1/p4pp1/1p5p/4b3/4B3/4P1P1/P1R2PKP/1q1r4 w - - 0 31", "Execute the precise endgame technique with c2c8 to secure victory.", "Look for the key move c2c8.", listOf("c2c8", "d1d8", "c8d8")),
        RawLesson("Back Rank Mate Technique in Endgame Strategy", "5r1k/pp4pp/2p5/6q1/5R2/2P5/P1P2PPP/3rR1K1 w - - 0 28", "Execute the precise endgame technique with f4f8 to secure victory.", "Look for the key move f4f8.", listOf("f4f8")),
        RawLesson("Back Rank Mate Technique in Endgame Strategy", "5k2/p2r3p/1p4pP/3r1q2/4R3/2P5/PP3PQ1/K3R3 b - - 0 33", "Execute the precise endgame technique with d5d1 to secure victory.", "Look for the key move d5d1.", listOf("d5d1", "e1d1", "d7d1")),
        RawLesson("Fork Technique in Endgame Strategy", "6k1/6pp/p1N5/1pP2bp1/5P2/8/PPP5/3K4 w - - 0 29", "Execute the precise endgame technique with c6e7 to secure victory.", "Look for the key move c6e7.", listOf("c6e7", "g8f7", "e7f5")),
        RawLesson("Back Rank Mate Technique in Endgame Strategy", "1k6/ppp3p1/8/1P5p/8/P3n2P/2P1r1P1/B2rNRK1 w - - 5 32", "Execute the precise endgame technique with f1f8 to secure victory.", "Look for the key move f1f8.", listOf("f1f8", "d1d8", "f8d8")),
        RawLesson("Fork Technique in Endgame Strategy", "5rk1/5ppp/4p3/4N3/8/1Pn5/5PPP/2R3K1 b - - 1 28", "Execute the precise endgame technique with c3e2 to secure victory.", "Look for the key move c3e2.", listOf("c3e2", "g1f1", "e2c1")),
        RawLesson("Back Rank Mate Technique in Endgame Strategy", "r6k/2q3pp/8/2p5/R1np4/7P/2PB1PP1/6K1 w - - 0 33", "Execute the precise endgame technique with a4a8 to secure victory.", "Look for the key move a4a8.", listOf("a4a8", "c7b8", "a8b8")),
        RawLesson("Fork Technique in Endgame Strategy", "1k6/pp6/4nNp1/P3r2p/3p4/7P/3R1PPK/8 w - - 1 41", "Execute the precise endgame technique with f6d7 to secure victory.", "Look for the key move f6d7.", listOf("f6d7", "b8c7", "d7e5")),
        RawLesson("Fork Technique in Endgame Strategy", "8/4k3/1p1p4/rP2p1p1/P2nP1P1/3B4/3K4/R7 b - - 1 35", "Execute the precise endgame technique with d4b3 to secure victory.", "Look for the key move d4b3.", listOf("d4b3", "d2c3", "b3a1")),
        RawLesson("Mate Technique in Endgame Strategy", "5kr1/ppR3p1/3R3p/1n6/1r6/8/1P3PPP/2K5 w - - 5 32", "Execute the precise endgame technique with d6d8 to secure victory.", "Look for the key move d6d8.", listOf("d6d8")),
        RawLesson("Back Rank Mate Technique in Endgame Strategy", "4r3/3R1pkp/6p1/1P6/1b6/5B2/1P1R1PPP/6K1 b - - 0 36", "Execute the precise endgame technique with e8e1 to secure victory.", "Look for the key move e8e1.", listOf("e8e1")),
        RawLesson("Mate Technique in Endgame Strategy", "6k1/pp3pp1/2p1q1Pp/3b4/8/6Q1/PB3Pp1/3r1NK1 w - - 0 28", "Execute the precise endgame technique with g3b8 to secure victory.", "Look for the key move g3b8.", listOf("g3b8", "e6e8", "b8e8")),
        RawLesson("Master Technique in Endgame Strategy", "6k1/pp3pp1/2p5/7b/4r2P/2P2N2/PP4K1/3R4 w - - 0 25", "Execute the precise endgame technique with d1d8 to secure victory.", "Look for the key move d1d8.", listOf("d1d8", "g8h7", "f3g5")),
        RawLesson("Master Technique in Endgame Strategy", "4r1k1/pp1qr1p1/7p/2pPR3/2P2p2/1P3P2/P2Q2PP/4R1K1 b - - 4 33", "Execute the precise endgame technique with e7e5 to secure victory.", "Look for the key move e7e5.", listOf("e7e5", "e1e5", "e8e5")),
        RawLesson("Mate Technique in Endgame Strategy", "r5k1/pp4pp/4p1q1/4p3/3n4/P3Q1P1/1PP4P/2KR1R2 b - - 5 24", "Execute the precise endgame technique with g6c2 to secure victory.", "Look for the key move g6c2.", listOf("g6c2")),
        RawLesson("Master Technique in Endgame Strategy", "r5r1/pp1k1p2/2p5/3pQ3/3P4/2NB4/PPP2q2/1K6 w - - 2 29", "Execute the precise endgame technique with d3f5 to secure victory.", "Look for the key move d3f5.", listOf("d3f5", "f2f5", "e5f5")),
        RawLesson("Mate Technique in Endgame Strategy", "6k1/2q2p1p/4pPp1/4P3/p1pP1P2/r1P5/6QP/4B1K1 w - - 0 34", "Execute the precise endgame technique with g2a8 to secure victory.", "Look for the key move g2a8.", listOf("g2a8", "c7b8", "a8b8")),
        RawLesson("Mate Technique in Endgame Strategy", "6k1/2R3pp/2p4q/1p1p4/3P4/P7/1PP2R2/1K1Nr3 w - - 4 33", "Execute the precise endgame technique with c7c8 to secure victory.", "Look for the key move c7c8.", listOf("c7c8", "e1e8", "c8e8")),
        RawLesson("Mate Technique in Endgame Strategy", "8/3k1p2/4p3/p2p4/3P1P2/q3P1rP/7r/1QR2K2 w - - 2 35", "Execute the precise endgame technique with b1b7 to secure victory.", "Look for the key move b1b7.", listOf("b1b7", "d7e8", "c1c8")),
        RawLesson("Mate Technique in Endgame Strategy", "4r1k1/p4p1p/1p6/6B1/3P2n1/P4Q2/1P4P1/7K b - - 0 34", "Execute the precise endgame technique with e8e1 to secure victory.", "Look for the key move e8e1.", listOf("e8e1", "f3f1", "e1f1")),
        RawLesson("Mate Technique in Endgame Strategy", "2r3k1/3R1ppp/p1q5/2p2Q2/P7/7P/5PP1/6K1 w - - 4 27", "Execute the precise endgame technique with f5f7 to secure victory.", "Look for the key move f5f7.", listOf("f5f7", "g8h8", "f7g7")),
        RawLesson("Fork Technique in Endgame Strategy", "7R/1p2k2p/p2n2p1/4K3/8/6P1/P6P/8 b - - 11 37", "Execute the precise endgame technique with d6f7 to secure victory.", "Look for the key move d6f7.", listOf("d6f7", "e5e4", "f7h8")),
        RawLesson("Mate Technique in Endgame Strategy", "4r2k/3q3r/1p4pQ/p1pP4/2P4P/1N4p1/PP3RK1/8 w - - 2 38", "Execute the precise endgame technique with f2f8 to secure victory.", "Look for the key move f2f8.", listOf("f2f8", "e8f8", "h6f8")),
        RawLesson("Master Technique in Endgame Strategy", "6k1/5p1p/1p4p1/p1bN1p2/2Pq1P2/1PQ4P/1P4P1/7K b - - 2 32", "Execute the precise endgame technique with d4g1 to secure victory.", "Look for the key move d4g1.", listOf("d4g1")),
        RawLesson("Deflection Technique in Endgame Strategy", "8/1R6/p1pk4/2q3bp/1QP5/P7/KP6/3r4 w - - 3 45", "Execute the precise endgame technique with b7d7 to secure victory.", "Look for the key move b7d7.", listOf("b7d7", "d6d7", "b4c5")),
        RawLesson("Deflection Technique in Endgame Strategy", "8/5kp1/p3pb2/8/6Pp/1P4qP/P2R2Q1/7K b - - 3 34", "Execute the precise endgame technique with g3e1 to secure victory.", "Look for the key move g3e1.", listOf("g3e1", "g2g1", "e1d2")),
        RawLesson("Hanging Piece Technique in Endgame Strategy", "8/7p/4k3/pb1p1pPB/1n1P3P/N1p1P3/4K3/8 w - - 2 43", "Execute the precise endgame technique with a3b5 to secure victory.", "Look for the key move a3b5.", listOf("a3b5", "c3c2", "e2d2")),
        RawLesson("Fork Technique in Endgame Strategy", "3R4/8/8/KB2b3/1p6/1P2k3/3p4/8 b - - 0 58", "Execute the precise endgame technique with e5c7 to secure victory.", "Look for the key move e5c7.", listOf("e5c7", "a5b4", "c7d8")),
        RawLesson("Back Rank Mate Technique in Endgame Strategy", "6k1/5ppp/r1p5/p1n1rP2/8/2P2N1P/2P3P1/3R2K1 w - - 0 22", "Execute the precise endgame technique with d1d8 to secure victory.", "Look for the key move d1d8.", listOf("d1d8", "e5e8", "d8e8")),
        RawLesson("Skewer Technique in Endgame Strategy", "8/7Q/3p1kp1/1p6/2b5/2q4P/5PPK/8 w - - 0 37", "Execute the precise endgame technique with h7h8 to secure victory.", "Look for the key move h7h8.", listOf("h7h8", "f6e6", "h8c3")),
        RawLesson("Master Technique in Endgame Strategy", "8/6kp/4b1q1/1p6/1PpPN2Q/2P1P3/r5P1/5RK1 b - - 0 34", "Execute the precise endgame technique with g6g2 to secure victory.", "Look for the key move g6g2.", listOf("g6g2")),
        RawLesson("Fork Technique in Endgame Strategy", "5rk1/1p2p2p/p2p4/2pPb2R/2P1P3/1P1BKPrR/8/8 w - - 5 31", "Execute the precise endgame technique with h3g3 to secure victory.", "Look for the key move h3g3.", listOf("h3g3", "e5g3", "h5g5", "g8f7", "g5g3")),
        RawLesson("Defensive Move Technique in Endgame Strategy", "3N1r2/R7/kp6/p2pPp1Q/2pP2P1/2q5/2P5/2K5 b - - 1 38", "Execute the precise endgame technique with a6a7 to secure victory.", "Look for the key move a6a7.", listOf("a6a7", "h5h7", "a7a6")),
        RawLesson("Deflection Technique in Endgame Strategy", "7Q/2p5/1p2prp1/p4k1p/q4p1P/8/6RK/8 w - - 0 38", "Execute the precise endgame technique with g2g5 to secure victory.", "Look for the key move g2g5.", listOf("g2g5", "f5e4", "h8f6")),
        RawLesson("Mate Technique in Endgame Strategy", "8/1p4p1/pb2pp1p/3n1k2/3P4/P3BN1P/1P2KPP1/8 w - - 1 27", "Execute the precise endgame technique with f3h4 to secure victory.", "Look for the key move f3h4.", listOf("f3h4", "f5e4", "f2f3")),
        RawLesson("Mate Technique in Endgame Strategy", "6nr/p4p1p/k1p5/1p6/1QN5/2P1P3/4KPqP/8 w - - 0 27", "Execute the precise endgame technique with b4a5 to secure victory.", "Look for the key move b4a5.", listOf("b4a5", "a6b7", "c4d6", "b7b8", "a5d8")),
        RawLesson("Mate Technique in Endgame Strategy", "1r4k1/p4ppp/2Q5/3pq3/8/P6P/2PR1PP1/1R4K1 b - - 0 26", "Execute the precise endgame technique with b8b1 to secure victory.", "Look for the key move b8b1.", listOf("b8b1", "d2d1", "b1d1")),
        RawLesson("Master Technique in Endgame Strategy", "6k1/p4p2/1p5p/4r3/P3B3/1P2KP2/2P3PP/8 b - - 1 29", "Execute the precise endgame technique with f7f5 to secure victory.", "Look for the key move f7f5.", listOf("f7f5", "g2g4", "f5e4")),
        RawLesson("Mate Technique in Endgame Strategy", "1r6/5k2/2Q1pNp1/p5Pp/1p2P2P/2P4R/KP3P2/3q4 b - - 0 31", "Execute the precise endgame technique with b4b3 to secure victory.", "Look for the key move b4b3.", listOf("b4b3", "a2a3", "d1a1")),
        RawLesson("Mate Technique in Endgame Strategy", "5Q2/pbp3np/1p1pq1pk/1P6/P6P/6K1/8/8 w - - 0 33", "Execute the precise endgame technique with f8f4 to secure victory.", "Look for the key move f8f4.", listOf("f8f4", "g6g5", "f4g5")),
        RawLesson("Master Technique in Endgame Strategy", "8/4R3/p4kpp/3B4/5q2/8/5P1P/6K1 w - - 6 41", "Execute the precise endgame technique with e7f7 to secure victory.", "Look for the key move e7f7.", listOf("e7f7", "f6e5", "f7f4")),
        RawLesson("Hanging Piece Technique in Endgame Strategy", "4r1k1/1p2R1p1/p2p2Pp/P1pP4/8/1R3p2/1P1q3P/5B1K w - - 0 35", "Execute the precise endgame technique with e7e8 to secure victory.", "Look for the key move e7e8.", listOf("e7e8")),
        RawLesson("Mate Technique in Endgame Strategy", "1R2R3/p7/1p1k3p/1Pb5/P5p1/6P1/5r1P/7K b - - 7 41", "Execute the precise endgame technique with f2f1 to secure victory.", "Look for the key move f2f1.", listOf("f2f1", "h1g2", "f1g1")),
        RawLesson("Discovered Attack Technique in Endgame Strategy", "3r4/4kp1p/1PQ1p1p1/p3b3/1p2P2P/1P5K/6P1/8 b - - 2 36", "Execute the precise endgame technique with d8d3 to secure victory.", "Look for the key move d8d3.", listOf("d8d3", "g2g3", "d3g3", "h3h2", "g3c3")),
        RawLesson("Skewer Technique in Endgame Strategy", "5Q2/8/1bk1p1p1/5p2/3p4/5qPK/7P/8 w - - 2 52", "Execute the precise endgame technique with f8a8 to secure victory.", "Look for the key move f8a8.", listOf("f8a8", "c6d6", "a8f3")),
        RawLesson("Master Technique in Endgame Strategy", "2r3k1/4R1pp/p1p2p2/2N5/2P5/1Pb4P/P4PP1/6K1 b - - 0 25", "Execute the precise endgame technique with c3b4 to secure victory.", "Look for the key move c3b4.", listOf("c3b4", "e7a7", "b4c5")),
        RawLesson("Hanging Piece Technique in Endgame Strategy", "3b4/3P4/pp2Pk2/5Q2/P6p/8/8/7K b - - 0 43", "Execute the precise endgame technique with f6f5 to secure victory.", "Look for the key move f6f5.", listOf("f6f5", "e6e7", "d8e7", "d7d8q", "e7d8")),
        RawLesson("Hanging Piece Technique in Endgame Strategy", "8/pkp5/2p1p3/3p4/N2q4/1P4Q1/1PP3Pr/K2R4 b - - 0 29", "Execute the precise endgame technique with d4d1 to secure victory.", "Look for the key move d4d1.", listOf("d4d1", "a1a2", "h2h1", "a4c5", "b7b6")),
        RawLesson("Mate Technique in Endgame Strategy", "4rk2/p4q2/1p3Q1b/8/1p5N/2P1p3/P3P3/2K5 w - - 1 44", "Execute the precise endgame technique with h4g6 to secure victory.", "Look for the key move h4g6.", listOf("h4g6", "f8g8", "f6h8")),
        RawLesson("Corner Mate Technique in Endgame Strategy", "3r4/R7/2p5/p1P2p2/1p4k1/nP2K3/P3NP2/8 b - - 4 41", "Execute the precise endgame technique with a3c2 to secure victory.", "Look for the key move a3c2.", listOf("a3c2")),
        RawLesson("Master Technique in Endgame Strategy", "4r1k1/ppq3pp/2p2p2/4r3/4p1Q1/P5RP/1P3PP1/3R2K1 w - - 4 35", "Execute the precise endgame technique with d1d7 to secure victory.", "Look for the key move d1d7.", listOf("d1d7", "c7d7", "g4d7")),
        RawLesson("Attraction Technique in Endgame Strategy", "3r2k1/1b4bR/p2P2p1/3p2N1/2p5/2P2N2/PP6/2K5 w - - 0 29", "Execute the precise endgame technique with h7g7 to secure victory.", "Look for the key move h7g7.", listOf("h7g7", "g8g7", "g5e6", "g7g8", "e6d8")),
        RawLesson("Master Technique in Endgame Strategy", "r6k/3NR1p1/4n2p/5b1P/p7/6R1/8/6K1 b - - 0 43", "Execute the precise endgame technique with a4a3 to secure victory.", "Look for the key move a4a3.", listOf("a4a3", "g3a3", "a8a3", "e7e8", "h8h7")),
        RawLesson("Mate Technique in Endgame Strategy", "4r3/1k6/pp3P2/1b5p/3R1p2/P1R2P2/1P4PP/6K1 b - - 0 35", "Execute the precise endgame technique with e8e1 to secure victory.", "Look for the key move e8e1.", listOf("e8e1", "g1f2", "e1f1")),
        RawLesson("Back Rank Mate Technique in Endgame Strategy", "3r2k1/1q3ppp/p3p3/Qp1r4/7P/P4P2/1PP3P1/1K1R3R w - - 0 22", "Execute the precise endgame technique with a5d8 to secure victory.", "Look for the key move a5d8.", listOf("a5d8", "d5d8", "d1d8")),
        RawLesson("Attraction Technique in Endgame Strategy", "2r5/4ppkp/6p1/1p6/1P6/P3B3/1br2PPP/1R1R2K1 w - - 3 23", "Execute the precise endgame technique with b1b2 to secure victory.", "Look for the key move b1b2.", listOf("b1b2", "c2b2", "e3d4", "f7f6", "d4b2")),
        RawLesson("Mate Technique in Endgame Strategy", "2q3k1/4br2/6pQ/1p1n2p1/7P/1P4P1/1B2PP2/6K1 w - - 0 28", "Execute the precise endgame technique with h6h8 to secure victory.", "Look for the key move h6h8.", listOf("h6h8")),
        RawLesson("Master Technique in Endgame Strategy", "2KQ4/8/5b2/p1B5/P7/3k4/6p1/8 b - - 0 77", "Execute the precise endgame technique with f6d8 to secure victory.", "Look for the key move f6d8.", listOf("f6d8", "c8d8", "d3c4")),
        RawLesson("Attraction Technique in Endgame Strategy", "5r2/pp1k4/4p1b1/3pP1Np/3P1P1K/8/P7/2R5 w - - 8 43", "Execute the precise endgame technique with c1c7 to secure victory.", "Look for the key move c1c7.", listOf("c1c7", "d7c7", "g5e6", "c7b6", "e6f8")),
        RawLesson("Mate Technique in Endgame Strategy", "3r3k/6p1/4Q3/4B3/1p3P2/4PKP1/3q4/8 w - - 18 52", "Execute the precise endgame technique with e6h6 to secure victory.", "Look for the key move e6h6.", listOf("e6h6", "h8g8", "h6g7")),
        RawLesson("Skewer Technique in Endgame Strategy", "7r/6k1/2b1Rp2/8/P1N3p1/5nP1/5P2/Q4K2 b - - 0 38", "Execute the precise endgame technique with h8h1 to secure victory.", "Look for the key move h8h1.", listOf("h8h1", "f1e2", "h1a1")),
        RawLesson("Master Technique in Endgame Strategy", "8/6p1/2B2n2/3b2k1/3B4/6K1/4P3/8 w - - 5 45", "Execute the precise endgame technique with d4f6 to secure victory.", "Look for the key move d4f6.", listOf("d4f6", "g5f5", "c6d5")),
        RawLesson("Master Technique in Endgame Strategy", "5rk1/1p3ppp/pq1Q1b2/8/8/1P3N2/P4PPP/3R2K1 b - - 3 27", "Execute the precise endgame technique with f8d8 to secure victory.", "Look for the key move f8d8.", listOf("f8d8", "d6d8", "f6d8")),
        RawLesson("Back Rank Mate Technique in Endgame Strategy", "2r5/pR5p/5p1k/4p3/4R3/B4nPP/PP3P2/1K6 b - - 0 27", "Execute the precise endgame technique with f3d2 to secure victory.", "Look for the key move f3d2.", listOf("f3d2", "b1a1", "c8c1")),
        RawLesson("Deflection Technique in Endgame Strategy", "5rk1/R4pp1/1p5p/3Q4/1PPp2q1/3P2P1/5P2/4K3 b - - 0 34", "Execute the precise endgame technique with f8e8 to secure victory.", "Look for the key move f8e8.", listOf("f8e8", "e1f1", "g4h3", "d5g2", "e8e1", "f1e1", "h3g2")),
        RawLesson("Fork Technique in Endgame Strategy", "6k1/5p1p/4p3/4q3/3n4/2Q3P1/PP1N1P1P/6K1 b - - 3 37", "Execute the precise endgame technique with d4e2 to secure victory.", "Look for the key move d4e2.", listOf("d4e2", "g1f1", "e2c3")),
        RawLesson("Discovered Attack Technique in Endgame Strategy", "3Q4/p1p2ppp/4k3/8/5P2/4P3/Prqn2PP/3R1RK1 b - - 0 22", "Execute the precise endgame technique with d2f3 to secure victory.", "Look for the key move d2f3.", listOf("d2f3", "g1h1", "c2g2")),
        RawLesson("Master Technique in Endgame Strategy", "2Q2bk1/5p1p/p5p1/2p3P1/4B3/7P/qPr2P2/2K4R w - - 0 33", "Execute the precise endgame technique with e4c2 to secure victory.", "Look for the key move e4c2.", listOf("e4c2", "a2a1", "c2b1")),
        RawLesson("Master Technique in Endgame Strategy", "8/1q3kpp/1P2p3/4Q3/5P2/4B2P/2r3PK/8 w - - 1 44", "Execute the precise endgame technique with e5h5 to secure victory.", "Look for the key move e5h5.", listOf("e5h5", "f7e7", "e3c5", "c2c5", "h5c5")),
        RawLesson("Mate Technique in Endgame Strategy", "8/3pk3/R7/1R2PK1p/2PPn1r1/8/8/8 b - - 0 43", "Execute the precise endgame technique with e4g3 to secure victory.", "Look for the key move e4g3.", listOf("e4g3")),
        RawLesson("Master Technique in Endgame Strategy", "r2q1rk1/p4p1p/1p3Qp1/2p4P/3p4/5R2/PPP2PP1/4R1K1 w - - 1 23", "Execute the precise endgame technique with e1e7 to secure victory.", "Look for the key move e1e7.", listOf("e1e7", "d8e7", "f6e7")),
        RawLesson("Master Technique in Endgame Strategy", "4qk2/1b3R2/p7/1p2Q3/4P2P/P2P3K/2r5/3R4 b - - 0 41", "Execute the precise endgame technique with e8f7 to secure victory.", "Look for the key move e8f7.", listOf("e8f7", "e5h8", "f8e7")),
        RawLesson("Attraction Technique in Endgame Strategy", "5rk1/5ppp/1p6/1q3P1Q/2pp3P/6R1/6PK/8 w - - 0 31", "Execute the precise endgame technique with g3g7 to secure victory.", "Look for the key move g3g7.", listOf("g3g7", "g8g7", "f5f6", "g7f6", "h5b5")),
        RawLesson("Master Technique in Endgame Strategy", "8/1bpp2k1/1p5r/1P2P3/3P1P2/2P1n1K1/Q5P1/8 b - - 1 30", "Execute the precise endgame technique with h6g6 to secure victory.", "Look for the key move h6g6.", listOf("h6g6", "g3f2", "g6g2")),
        RawLesson("Exposed King Technique in Endgame Strategy", "8/1N3k2/6p1/8/2P3P1/pr6/R7/5K2 b - - 2 56", "Execute the precise endgame technique with b3b1 to secure victory.", "Look for the key move b3b1.", listOf("b3b1", "f1e2", "b1b2", "e2d1", "b2a2")),
        RawLesson("Hanging Piece Technique in Endgame Strategy", "8/pp1r2kp/q2P1ppb/4N3/4P3/1Q5P/PPR2PP1/6K1 b - - 0 32", "Execute the precise endgame technique with f6e5 to secure victory.", "Look for the key move f6e5.", listOf("f6e5", "c2c7", "a6d6")),
        RawLesson("Fork Technique in Endgame Strategy", "1r3k2/5p1p/2p1pp2/P2n4/r3N3/P4PK1/2R2P1P/2R5 w - - 10 30", "Execute the precise endgame technique with e4c5 to secure victory.", "Look for the key move e4c5.", listOf("e4c5", "a4a5", "c5d7", "f8g7", "d7b8")),
        RawLesson("Master Technique in Endgame Strategy", "r7/6pk/p2Q4/2p1p1qp/1pP1PrP1/1P3P1P/1P4K1/R4R2 b - - 0 25", "Execute the precise endgame technique with a8d8 to secure victory.", "Look for the key move a8d8.", listOf("a8d8", "d6d8", "g5d8")),
        RawLesson("Master Technique in Endgame Strategy", "7r/pppk4/2pN1r2/8/3P2p1/2P5/PP2RPP1/4R1K1 b - - 0 26", "Execute the precise endgame technique with f6h6 to secure victory.", "Look for the key move f6h6.", listOf("f6h6", "f2f4", "g4g3", "e2e7", "d7d6", "g1f1", "h6h1")),
        RawLesson("Master Technique in Endgame Strategy", "k3r3/p3q3/1pp5/3pnB2/1P1Q4/1KPP4/P3R3/8 b - - 1 41", "Execute the precise endgame technique with e5f3 to secure victory.", "Look for the key move e5f3.", listOf("e5f3", "e2e7", "f3d4", "c3d4", "e8e7")),
        RawLesson("Sacrifice Technique in Endgame Strategy", "r5k1/2p1pp2/pp4p1/1q5r/5P2/2QP2R1/PP6/1K4R1 w - - 1 33", "Execute the precise endgame technique with g3g6 to secure victory.", "Look for the key move g3g6.", listOf("g3g6", "f7g6", "g1g6", "g8f7", "c3g7", "f7e8", "g7g8", "e8d7", "g8e6", "d7d8", "g6g8")),
        RawLesson("Hanging Piece Technique in Endgame Strategy", "6k1/5p2/4p3/P1B5/2P4P/4Pnp1/Rb2r3/5K2 w - - 0 34", "Execute the precise endgame technique with f1e2 to secure victory.", "Look for the key move f1e2.", listOf("f1e2", "g3g2", "e3e4", "f3d4", "e2f2")),
        RawLesson("Master Technique in Endgame Strategy", "2nk4/8/2PBp3/1pK1P1p1/1P4Pn/8/8/8 w - - 3 43", "Execute the precise endgame technique with c5b5 to secure victory.", "Look for the key move c5b5.", listOf("c5b5", "h4g2", "b5a6", "g2e3", "a6b7")),
        RawLesson("Quiet Move Technique in Endgame Strategy", "6k1/4p1bp/6p1/1p1pP3/qPpPp3/2P1P3/Q2B1KPP/8 w - - 3 24", "Execute the precise endgame technique with a2a4 to secure victory.", "Look for the key move a2a4.", listOf("a2a4", "b5a4", "b4b5", "g8f7", "b5b6")),
        RawLesson("Master Technique in Endgame Strategy", "6k1/1p4pp/p5n1/5Q2/3BpP2/1P2PP1K/P1q4P/7r w - - 2 34", "Execute the precise endgame technique with f5d5 to secure victory.", "Look for the key move f5d5.", listOf("f5d5", "g8f8", "d4c5", "c2c5", "d5c5")),
        RawLesson("Master Technique in Endgame Strategy", "8/6pp/3Bp2k/p2pP2P/P3p1PK/8/r4b2/5R2 w - - 3 38", "Execute the precise endgame technique with f1f2 to secure victory.", "Look for the key move f1f2.", listOf("f1f2", "g7g5", "h4g3", "a2f2", "g3f2")),
        RawLesson("Exposed King Technique in Endgame Strategy", "8/7R/8/5p2/4bk1P/8/2r5/5KR1 b - - 8 51", "Execute the precise endgame technique with f4f3 to secure victory.", "Look for the key move f4f3.", listOf("f4f3", "f1e1", "c2c1", "e1d2", "c1g1")),
        RawLesson("Interference Technique in Endgame Strategy", "7r/p4pk1/1pp3p1/8/6q1/4Q3/PP1R1P1r/5KN1 w - - 0 39", "Execute the precise endgame technique with e3e5 to secure victory.", "Look for the key move e3e5.", listOf("e3e5", "f7f6", "e5c7", "g7h6", "c7h2")),
        RawLesson("Master Technique in Endgame Strategy", "8/2k3n1/K2p2p1/2pP2Pp/2P4P/7B/8/8 b - - 1 57", "Execute the precise endgame technique with c7d8 to secure victory.", "Look for the key move c7d8.", listOf("c7d8", "a6b5", "g7f5")),
        RawLesson("Master Technique in Endgame Strategy", "2r3rk/5p2/4p2p/4q3/1Q6/8/1P3PPP/2R2RK1 b - - 1 31", "Execute the precise endgame technique with e5g5 to secure victory.", "Look for the key move e5g5.", listOf("e5g5", "g2g3", "c8c1")),
        RawLesson("Defensive Move Technique in Endgame Strategy", "8/8/1p1k1p1p/3np3/2B2p2/PP1K1PP1/7P/8 w - - 0 37", "Execute the precise endgame technique with c4d5 to secure victory.", "Look for the key move c4d5.", listOf("c4d5", "f4g3", "h2g3", "d6d5", "g3g4")),
        RawLesson("Pin Technique in Endgame Strategy", "6k1/pp1r1pp1/2p1p2p/4P2P/3q1Q2/1P4R1/P1Pr1PP1/R5K1 w - - 5 24", "Execute the precise endgame technique with f4f6 to secure victory.", "Look for the key move f4f6.", listOf("f4f6", "d4f2", "f6f2", "d2f2", "g1f2")),
        RawLesson("Skewer Technique in Endgame Strategy", "6k1/4rRp1/p6p/7P/q1pP4/2Pb2Q1/P4RP1/6K1 b - - 0 31", "Execute the precise endgame technique with e7f7 to secure victory.", "Look for the key move e7f7.", listOf("e7f7", "g3b8", "g8h7", "f2f7", "a4d1", "g1f2", "d1f1", "f2g3", "f1f7")),
        RawLesson("Clearance Technique in Endgame Strategy", "6k1/p3b2p/1p1pP3/2P3P1/2np3B/P6P/3Q3K/8 b - - 0 38", "Execute the precise endgame technique with c4d2 to secure victory.", "Look for the key move c4d2.", listOf("c4d2", "c5c6", "d6d5", "g5g6", "e7d6")),
        RawLesson("Clearance Technique in Endgame Strategy", "1k6/1p3p2/p1p1p3/2P1b3/PP6/1KB5/4q3/1R1R4 w - - 2 39", "Execute the precise endgame technique with d1d8 to secure victory.", "Look for the key move d1d8.", listOf("d1d8", "b8a7", "b1e1", "e2e1", "c3e1")),
        RawLesson("Pin Technique in Endgame Strategy", "2kr3r/p4p2/1p2p2p/1N1p2p1/3Q4/1P1P4/2q2PPP/5RK1 w - - 0 21", "Execute the precise endgame technique with d4a1 to secure victory.", "Look for the key move d4a1.", listOf("d4a1", "a7a5", "f1c1")),
        RawLesson("Quiet Move Technique in Endgame Strategy", "4r3/1p3R1p/1pb2R1B/8/6k1/8/r6P/6K1 w - - 0 34", "Execute the precise endgame technique with f7g7 to secure victory.", "Look for the key move f7g7.", listOf("f7g7", "g4h4", "h6d2", "e8e1", "d2e1")),
        RawLesson("Master Technique in Endgame Strategy", "r4r2/1p3pkp/p7/3R1p1Q/3P4/8/P1q2P2/3R2K1 w - - 0 26", "Execute the precise endgame technique with d5c5 to secure victory.", "Look for the key move d5c5.", listOf("d5c5", "c2e4", "h5g5", "g7h8", "g5f6"))
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
                id = "e_endgame_strategy_$level",
                title = "Level $level ($tier difficulty) • ${base.subtitle}",
                category = "Endgame Strategy",
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
