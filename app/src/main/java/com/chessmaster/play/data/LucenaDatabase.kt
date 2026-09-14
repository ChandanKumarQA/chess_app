package com.chessmaster.play.data

import com.chessmaster.play.model.EndgameLesson

object LucenaDatabase {

    private data class RawLesson(
        val subtitle: String,
        val fen: String,
        val explanation: String,
        val hint: String,
        val moves: List<String>
    )

    private val pool = listOf(
        RawLesson("Advanced Pawn Technique in Lucena", "5R2/8/5K1k/3pP3/2pP4/1pP5/1r6/8 b - - 1 71", "Execute the precise endgame technique with b2f2 to secure victory.", "Look for the key move b2f2.", listOf("b2f2", "f6e7", "f2f8", "e7f8", "b3b2")),
        RawLesson("Advanced Pawn Technique in Lucena", "6k1/7p/2P3p1/8/2K2pP1/5r2/8/8 w - - 0 44", "Execute the precise endgame technique with c6c7 to secure victory.", "Look for the key move c6c7.", listOf("c6c7", "f3e3", "c7c8q")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/p2k4/1p4R1/2p2Pp1/2P3P1/3p1P2/PP4K1/7r b - - 1 38", "Execute the precise endgame technique with d3d2 to secure victory.", "Look for the key move d3d2.", listOf("d3d2", "g2h1", "d2d1q")),
        RawLesson("Advanced Pawn Technique in Lucena", "3r3k/4R1pp/p2P1p2/8/6P1/8/P6P/6K1 w - - 0 35", "Execute the precise endgame technique with d6d7 to secure victory.", "Look for the key move d6d7.", listOf("d6d7", "h8g8", "e7e8", "g8f7", "e8d8")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/6pk/p2r3p/4R3/3pP3/P6P/5PP1/6K1 b - - 0 34", "Execute the precise endgame technique with d4d3 to secure victory.", "Look for the key move d4d3.", listOf("d4d3", "e5d5", "d6d5", "e4d5", "d3d2")),
        RawLesson("Advanced Pawn Technique in Lucena", "6k1/5p2/6p1/K1R2r1p/1P6/8/8/8 w - - 0 44", "Execute the precise endgame technique with c5f5 to secure victory.", "Look for the key move c5f5.", listOf("c5f5", "g6f5", "b4b5", "f5f4", "b5b6", "g8g7", "b6b7")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/8/1p6/pr1k1PR1/6P1/5K2/8 w - - 0 49", "Execute the precise endgame technique with f4f5 to secure victory.", "Look for the key move f4f5.", listOf("f4f5", "d4c3", "g4b4", "c3b4", "f5f6", "a4a3", "f6f7", "a3a2", "f7f8q")),
        RawLesson("Advanced Pawn Technique in Lucena", "6k1/p4p1p/6p1/3r4/5P2/PPprR1P1/5P1P/2R3K1 b - - 0 34", "Execute the precise endgame technique with d3d1 to secure victory.", "Look for the key move d3d1.", listOf("d3d1", "c1d1", "d5d1", "g1g2", "c3c2")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/p2P2k1/1pp5/2r3K1/7p/P7/3R4 w - - 1 48", "Execute the precise endgame technique with g4h3 to secure victory.", "Look for the key move g4h3.", listOf("g4h3", "c4f4", "d6d7")),
        RawLesson("Advanced Pawn Technique in Lucena", "6k1/ppKPR3/8/1P5p/P4P2/2p5/7P/3r4 b - - 2 50", "Execute the precise endgame technique with c3c2 to secure victory.", "Look for the key move c3c2.", listOf("c3c2", "c7b7", "c2c1q")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/p1pR4/1pk2p1P/6r1/P1p3P1/7K/8/8 w - - 1 49", "Execute the precise endgame technique with h6h7 to secure victory.", "Look for the key move h6h7.", listOf("h6h7", "c6d7", "h7h8q")),
        RawLesson("Advanced Pawn Technique in Lucena", "6k1/8/8/1R5K/1P5p/6r1/8/8 b - - 3 55", "Execute the precise endgame technique with h4h3 to secure victory.", "Look for the key move h4h3.", listOf("h4h3", "h5h4", "h3h2", "h4g3", "h2h1q")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/2RPr3/pk4p1/8/5PK1/8/8 w - - 1 48", "Execute the precise endgame technique with d6d7 to secure victory.", "Look for the key move d6d7.", listOf("d6d7", "e6c6", "d7d8q")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/R2P4/5p2/p2rk1p1/8/8/6P1/5K2 w - - 0 43", "Execute the precise endgame technique with a7a5 to secure victory.", "Look for the key move a7a5.", listOf("a7a5", "d5a5", "d7d8q")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/P7/8/r3p1R1/5pP1/3pk2P/6K1/8 w - - 0 48", "Execute the precise endgame technique with g5e5 to secure victory.", "Look for the key move g5e5.", listOf("g5e5", "a5e5", "a7a8q")),
        RawLesson("Advanced Pawn Technique in Lucena", "6rk/4P1R1/1p4K1/7p/r4P2/8/8/8 w - - 1 45", "Execute the precise endgame technique with g7g8 to secure victory.", "Look for the key move g7g8.", listOf("g7g8", "h8g8", "e7e8q")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/7R/P7/1K4p1/1P5r/6kp/8/8 w - - 1 57", "Execute the precise endgame technique with h7h4 to secure victory.", "Look for the key move h7h4.", listOf("h7h4", "g3h4", "a6a7")),
        RawLesson("Advanced Pawn Technique in Lucena", "6k1/8/5PK1/4R3/5P1P/1p6/8/1r6 b - - 1 53", "Execute the precise endgame technique with b1g1 to secure victory.", "Look for the key move b1g1.", listOf("b1g1", "g6h6", "b3b2")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/p1p5/2p1kr1p/8/4PR2/1P1PKp2/P1P5/8 b - - 3 36", "Execute the precise endgame technique with f6f4 to secure victory.", "Look for the key move f6f4.", listOf("f6f4", "e3f4", "f3f2")),
        RawLesson("Advanced Pawn Technique in Lucena", "2r5/4r3/3kP2p/pppPRRpP/3pKpP1/1P3P2/1P6/8 w - - 2 47", "Execute the precise endgame technique with f5f7 to secure victory.", "Look for the key move f5f7.", listOf("f5f7", "e7f7", "e6f7")),
        RawLesson("Advanced Pawn Technique in Lucena", "1r6/pr4pp/1Ppkp3/3p4/p2P4/5P2/PR4PP/1R4K1 w - - 1 28", "Execute the precise endgame technique with b6a7 to secure victory.", "Look for the key move b6a7.", listOf("b6a7", "b7a7", "b2b8")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/5k2/7p/1R3Kp1/4P3/5P1P/1pr5/8 b - - 3 51", "Execute the precise endgame technique with c2c5 to secure victory.", "Look for the key move c2c5.", listOf("c2c5", "b5c5", "b2b1q")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/k3Rp2/P3rP1P/K7/4p3/8/8 b - - 3 57", "Execute the precise endgame technique with e5e6 to secure victory.", "Look for the key move e5e6.", listOf("e5e6", "f5e6", "e3e2", "h5h6", "e2e1q")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/2P1k3/P3p3/2r4p/4K2p/7P/6P1/8 w - - 2 48", "Execute the precise endgame technique with a6a7 to secure victory.", "Look for the key move a6a7.", listOf("a6a7", "c5c7", "a7a8q")),
        RawLesson("Advanced Pawn Technique in Lucena", "6k1/2R5/1p4PP/8/8/8/1rp2P2/6K1 b - - 0 44", "Execute the precise endgame technique with b2b1 to secure victory.", "Look for the key move b2b1.", listOf("b2b1", "g1g2", "c2c1q", "c7c1", "b1c1")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/5p1p/5R2/2K5/2P3k1/P4r2/8 w - - 2 47", "Execute the precise endgame technique with f5f2 to secure victory.", "Look for the key move f5f2.", listOf("f5f2", "g3f2", "a2a4", "f6f5", "a4a5", "f5f4", "a5a6", "f4f3", "a6a7")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/8/8/1p6/1kpp4/8/R1K5 b - - 2 52", "Execute the precise endgame technique with d3d2 to secure victory.", "Look for the key move d3d2.", listOf("d3d2", "c1d1", "b3b2", "a1a5", "c3c2")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/3R4/p1k1P3/1p2P3/r7/3K4/8 w - - 0 46", "Execute the precise endgame technique with d6d5 to secure victory.", "Look for the key move d6d5.", listOf("d6d5", "c5c4", "e5e6", "a3h3", "e6e7", "h3h8", "d5d8")),
        RawLesson("Advanced Pawn Technique in Lucena", "4k3/R5R1/5K1p/1Pp3r1/8/6P1/4p2P/8 b - - 0 53", "Execute the precise endgame technique with g5g7 to secure victory.", "Look for the key move g5g7.", listOf("g5g7", "f6g7", "e2e1q")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/8/p1p5/1k4P1/1r3R2/6PK/8 w - - 0 58", "Execute the precise endgame technique with f3b3 to secure victory.", "Look for the key move f3b3.", listOf("f3b3", "b4b3", "g4g5", "c5c4", "g5g6", "c4c3", "g6g7", "b3b2", "g7g8q")),
        RawLesson("Advanced Pawn Technique in Lucena", "7r/6RP/2p5/7K/2k5/1p6/5P2/8 b - - 1 50", "Execute the precise endgame technique with b3b2 to secure victory.", "Look for the key move b3b2.", listOf("b3b2", "g7b7", "h8h7", "b7h7", "b2b1q")),
        RawLesson("Advanced Pawn Technique in Lucena", "4r3/5kpp/8/8/8/7P/3p1PP1/1RrR1K2 b - - 3 36", "Execute the precise endgame technique with e8e1 to secure victory.", "Look for the key move e8e1.", listOf("e8e1", "d1e1", "d2e1q")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/8/3P3R/8/pP1kP1K1/1r6/8 b - - 0 42", "Execute the precise endgame technique with a3a2 to secure victory.", "Look for the key move a3a2.", listOf("a3a2", "h5h1", "b2b1")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/2R5/7P/8/3K4/pr4P1/1k3P2/8 b - - 0 42", "Execute the precise endgame technique with a3a2 to secure victory.", "Look for the key move a3a2.", listOf("a3a2", "c7a7", "b3a3", "a7a3", "b2a3")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/6p1/2p5/R7/8/8/kp1K4/8 b - - 1 43", "Execute the precise endgame technique with a2b3 to secure victory.", "Look for the key move a2b3.", listOf("a2b3", "a5a7", "b2b1q", "a7b7", "b3a2")),
        RawLesson("Advanced Pawn Technique in Lucena", "6r1/8/8/2P4R/1K6/P5k1/2P4p/8 b - - 1 43", "Execute the precise endgame technique with g8g4 to secure victory.", "Look for the key move g8g4.", listOf("g8g4", "b4b5", "g4h4", "h5h4", "g3h4", "c5c6", "h2h1q")),
        RawLesson("Advanced Pawn Technique in Lucena", "4k3/4r3/5R2/3p1p1p/1P1PpP1P/2K3P1/8/8 b - - 0 49", "Execute the precise endgame technique with e4e3 to secure victory.", "Look for the key move e4e3.", listOf("e4e3", "f6a6", "e3e2", "a6a8", "e8f7")),
        RawLesson("Advanced Pawn Technique in Lucena", "2kr4/pp4R1/2p1p1P1/4P3/4p3/2P5/PP6/1K6 b - - 0 28", "Execute the precise endgame technique with e4e3 to secure victory.", "Look for the key move e4e3.", listOf("e4e3", "g7h7", "e3e2", "h7h1", "d8d1")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/6p1/3r2kp/8/p2pR3/2P2R1P/P2pKPP1/8 b - - 0 38", "Execute the precise endgame technique with d4c3 to secure victory.", "Look for the key move d4c3.", listOf("d4c3", "f3d3", "d6d3", "e2d3", "d2d1q")),
        RawLesson("Advanced Pawn Technique in Lucena", "3R4/8/8/4p3/1p2k3/3p4/6PK/8 b - - 1 60", "Execute the precise endgame technique with b4b3 to secure victory.", "Look for the key move b4b3.", listOf("b4b3", "d8b8", "d3d2")),
        RawLesson("Advanced Pawn Technique in Lucena", "r5k1/6pR/3prPP1/8/1pp1P3/p7/2P5/1K6 w - - 0 39", "Execute the precise endgame technique with f6f7 to secure victory.", "Look for the key move f6f7.", listOf("f6f7", "g8f8", "h7h8", "f8e7", "h8a8")),
        RawLesson("Advanced Pawn Technique in Lucena", "4k3/R3P3/1p3Kpp/2p5/2P5/4r3/4p1P1/8 w - - 3 38", "Execute the precise endgame technique with a7a8 to secure victory.", "Look for the key move a7a8.", listOf("a7a8", "e8d7", "a8d8", "d7c7", "e7e8q", "e3e8", "d8e8")),
        RawLesson("Advanced Pawn Technique in Lucena", "5k2/7p/3P2p1/1R6/8/7r/K7/8 w - - 1 44", "Execute the precise endgame technique with b5b8 to secure victory.", "Look for the key move b5b8.", listOf("b5b8", "f8f7", "d6d7")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/7r/5k2/8/1P1p1P2/6K1/1P5R/8 b - - 0 39", "Execute the precise endgame technique with h7h2 to secure victory.", "Look for the key move h7h2.", listOf("h7h2", "g3h2", "d4d3", "h2g1", "d3d2")),
        RawLesson("Advanced Pawn Technique in Lucena", "3R4/8/8/6k1/8/4P2p/r4P2/5K2 b - - 3 51", "Execute the precise endgame technique with a2a1 to secure victory.", "Look for the key move a2a1.", listOf("a2a1", "f1e2", "h3h2")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/6k1/R7/8/8/P3pr2/1P6/2K5 b - - 1 38", "Execute the precise endgame technique with f3f1 to secure victory.", "Look for the key move f3f1.", listOf("f3f1", "c1c2", "e3e2")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/p7/2PRp3/5k1p/1P3p2/P2KpP2/1r5P/8 w - - 0 39", "Execute the precise endgame technique with c6c7 to secure victory.", "Look for the key move c6c7.", listOf("c6c7", "b2d2", "d3c3", "d2d6", "c7c8q")),
        RawLesson("Advanced Pawn Technique in Lucena", "4r3/8/2kp4/5R2/4pp2/5P1P/5P1K/8 b - - 0 40", "Execute the precise endgame technique with e4e3 to secure victory.", "Look for the key move e4e3.", listOf("e4e3", "f5f4", "e3e2")),
        RawLesson("Advanced Pawn Technique in Lucena", "4k3/1R2P1p1/7p/5P2/1p4P1/P6P/1r6/4K3 b - - 0 41", "Execute the precise endgame technique with b4a3 to secure victory.", "Look for the key move b4a3.", listOf("b4a3", "b7a7", "a3a2", "a7a8", "e8e7")),
        RawLesson("Advanced Pawn Technique in Lucena", "1k6/p7/Pp2p3/3P4/1R2P2p/2PP4/1K6/6r1 b - - 0 42", "Execute the precise endgame technique with h4h3 to secure victory.", "Look for the key move h4h3.", listOf("h4h3", "e4e5", "h3h2")),
        RawLesson("Advanced Pawn Technique in Lucena", "4R3/8/6P1/3k1p1p/5P2/p7/1r2p3/4K3 w - - 0 45", "Execute the precise endgame technique with g6g7 to secure victory.", "Look for the key move g6g7.", listOf("g6g7", "b2b1", "e1e2")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/6R1/r3p1pP/3k4/8/3p4/5P1K/8 w - - 0 50", "Execute the precise endgame technique with h6h7 to secure victory.", "Look for the key move h6h7.", listOf("h6h7", "a6a8", "g7g8")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/2P5/5k2/p7/P3K3/8/2r2Rp1/8 b - - 0 38", "Execute the precise endgame technique with c2f2 to secure victory.", "Look for the key move c2f2.", listOf("c2f2", "c7c8q", "g2g1q", "c8f8", "f6e6")),
        RawLesson("Advanced Pawn Technique in Lucena", "6k1/7p/3p2pP/3P4/2PPp3/8/1KR2r2/8 b - - 1 44", "Execute the precise endgame technique with e4e3 to secure victory.", "Look for the key move e4e3.", listOf("e4e3", "b2c3", "e3e2", "c2c1", "f2f1", "c1e1", "f1e1")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/p2R2r1/2P3p1/P7/1K2pk1P/8/8 b - - 1 39", "Execute the precise endgame technique with e3e2 to secure victory.", "Look for the key move e3e2.", listOf("e3e2", "d6g6", "e2e1q")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/4kP1R/8/8/2pP3P/2P3p1/7r/4K3 b - - 1 47", "Execute the precise endgame technique with h2h1 to secure victory.", "Look for the key move h2h1.", listOf("h2h1", "e1e2", "g3g2", "f7f8q", "e7f8")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/kp2R3/3p4/P2P1p2/K1P5/8/5r2 b - - 0 46", "Execute the precise endgame technique with f4f3 to secure victory.", "Look for the key move f4f3.", listOf("f4f3", "e6f6", "f3f2", "a3b2", "a6a5", "f6f4", "a5a4", "f4f8", "a4b5")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/2R5/8/3k1PK1/rp6/8/8 b - - 1 58", "Execute the precise endgame technique with b3b2 to secure victory.", "Look for the key move b3b2.", listOf("b3b2", "c6b6", "d4c3")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/1P6/1KR5/4kp2/8/8/r7 w - - 2 64", "Execute the precise endgame technique with c5c4 to secure victory.", "Look for the key move c5c4.", listOf("c5c4", "e4f5", "b6b7", "a1b1", "c4b4", "b1b4", "b5b4")),
        RawLesson("Advanced Pawn Technique in Lucena", "2r5/p4k1p/1p4p1/2P1pp2/8/1P2K3/P4PPP/2R5 w - - 1 28", "Execute the precise endgame technique with c5b6 to secure victory.", "Look for the key move c5b6.", listOf("c5b6", "c8c1", "b6b7")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/8/1R3pk1/1P6/P1p3r1/8/4K3 b - - 0 46", "Execute the precise endgame technique with g3g1 to secure victory.", "Look for the key move g3g1.", listOf("g3g1", "e1e2", "c3c2")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/6K1/5p2/4kP1P/R2p2P1/6r1/8 b - - 0 50", "Execute the precise endgame technique with g2g3 to secure victory.", "Look for the key move g2g3.", listOf("g2g3", "g6f6", "d3d2")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/6RP/8/1p1kr3/4p3/4K3/8 w - - 1 61", "Execute the precise endgame technique with h6h7 to secure victory.", "Look for the key move h6h7.", listOf("h6h7", "e4h4", "g6g4", "h4g4", "h7h8q")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/p5k1/2p1R1P1/3p4/3P4/r7/2r5/5RK1 w - - 6 33", "Execute the precise endgame technique with f1f7 to secure victory.", "Look for the key move f1f7.", listOf("f1f7", "g7h6", "g6g7", "h6h7", "e6h6", "h7h6", "g7g8q")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/4P1k1/8/p6R/2P4p/PP2r3/2K5 b - - 3 37", "Execute the precise endgame technique with h3h2 to secure victory.", "Look for the key move h3h2.", listOf("h3h2", "e6e7", "g6f7", "e7e8r", "f7e8")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/5Pk1/6P1/1r6/1p4K1/5R2/8 b - - 8 73", "Execute the precise endgame technique with b3b2 to secure victory.", "Look for the key move b3b2.", listOf("b3b2", "f6f7", "b2b1q", "f7f8q", "b1g1")),
        RawLesson("Advanced Pawn Technique in Lucena", "6k1/R4p2/P3p2p/3p1rp1/8/8/1r6/R5K1 w - - 6 43", "Execute the precise endgame technique with a7b7 to secure victory.", "Look for the key move a7b7.", listOf("a7b7", "b2e2", "a6a7", "f5f2", "a7a8q")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/3R4/p1p1KP2/1pP5/1Pk5/8/8/4r3 w - - 5 67", "Execute the precise endgame technique with e6d6 to secure victory.", "Look for the key move e6d6.", listOf("e6d6", "e1f1", "f6f7", "c4b4", "d6c6")),
        RawLesson("Advanced Pawn Technique in Lucena", "4k3/7r/p1PPK3/4P2p/8/1p6/7P/8 w - - 0 52", "Execute the precise endgame technique with d6d7 to secure victory.", "Look for the key move d6d7.", listOf("d6d7", "e8d8", "e6d6", "h7h6", "e5e6", "h6e6", "d6e6", "d8c7", "e6e7")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/p2k2P1/4R2K/2pP3P/1pr3r1/8/8/8 w - - 0 44", "Execute the precise endgame technique with e6g6 to secure victory.", "Look for the key move e6g6.", listOf("e6g6", "g4g6", "h5g6", "c4h4", "h6g5", "h4e4", "g7g8q")),
        RawLesson("Advanced Pawn Technique in Lucena", "r7/6P1/8/8/5p2/5P1p/3R4/2k1K3 b - - 2 51", "Execute the precise endgame technique with a8e8 to secure victory.", "Look for the key move a8e8.", listOf("a8e8", "d2e2", "h3h2", "e2e8", "h2h1q")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/p7/2p5/2P5/1P3k1K/Ppr2P2/6P1/4R3 b - - 2 43", "Execute the precise endgame technique with b3b2 to secure victory.", "Look for the key move b3b2.", listOf("b3b2", "g2g3", "f4f5", "g3g4", "f5f4")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/P7/1K3pk1/6r1/4R2p/8/8 w - - 0 52", "Execute the precise endgame technique with a6a7 to secure victory.", "Look for the key move a6a7.", listOf("a6a7", "g4e4", "e3e4", "f5e4", "a7a8q")),
        RawLesson("Advanced Pawn Technique in Lucena", "7k/8/2r2PRp/1p6/4K3/8/8/8 w - - 1 52", "Execute the precise endgame technique with g6h6 to secure victory.", "Look for the key move g6h6.", listOf("g6h6", "h8g8", "f6f7", "g8f7", "h6c6")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/6R1/4P3/5K2/8/r7/5kp1/8 b - - 0 51", "Execute the precise endgame technique with a3f3 to secure victory.", "Look for the key move a3f3.", listOf("a3f3", "f5e5", "f3g3", "g7g3", "f2g3", "e6e7", "g2g1q")),
        RawLesson("Advanced Pawn Technique in Lucena", "6k1/8/5PPR/5K2/r4P2/8/4p2P/8 b - - 0 41", "Execute the precise endgame technique with a4a5 to secure victory.", "Look for the key move a4a5.", listOf("a4a5", "f5g4", "e2e1q", "f6f7", "g8g7", "h6h7", "g7g6")),
        RawLesson("Advanced Pawn Technique in Lucena", "4R3/2k5/p4P2/8/8/8/rp4PK/8 w - - 0 39", "Execute the precise endgame technique with f6f7 to secure victory.", "Look for the key move f6f7.", listOf("f6f7", "b2b1q", "e8e7", "c7b6", "f7f8q")),
        RawLesson("Advanced Pawn Technique in Lucena", "1R6/1P3pk1/4p1p1/4P1P1/1K3P1p/8/8/7r b - - 0 42", "Execute the precise endgame technique with h1b1 to secure victory.", "Look for the key move h1b1.", listOf("h1b1", "b4c3", "h4h3", "b8a8", "h3h2", "b7b8q", "b1b8")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/7P/2p5/6R1/2k3P1/1p6/r4P2/5K2 b - - 0 39", "Execute the precise endgame technique with b3b2 to secure victory.", "Look for the key move b3b2.", listOf("b3b2", "f1g2", "b2b1q", "h7h8q", "b1e4", "g2g3", "a2a3", "g3h2", "e4f4", "h2g2", "f4g5")),
        RawLesson("Advanced Pawn Technique in Lucena", "5R2/8/kp6/p3K1P1/r4p2/8/8/8 w - - 0 55", "Execute the precise endgame technique with g5g6 to secure victory.", "Look for the key move g5g6.", listOf("g5g6", "f4f3", "g6g7")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/8/P7/4kp2/K7/R7/r7 b - - 4 56", "Execute the precise endgame technique with a1a2 to secure victory.", "Look for the key move a1a2.", listOf("a1a2", "a3a2", "e4d5", "a5a6", "d5c6", "a6a7", "c6b7", "a7a8r", "b7a8", "a2b1", "f4f3", "b1c2", "f3f2")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/RP3pk1/6p1/2P5/1rP5/K6p/8/8 b - - 2 42", "Execute the precise endgame technique with b4b1 to secure victory.", "Look for the key move b4b1.", listOf("b4b1", "a3a2", "h3h2", "a2b1", "h2h1q")),
        RawLesson("Advanced Pawn Technique in Lucena", "3r4/8/5P1p/4K2k/2pp2R1/7P/8/8 w - - 0 54", "Execute the precise endgame technique with f6f7 to secure victory.", "Look for the key move f6f7.", listOf("f6f7", "d8f8", "e5f6", "d4d3", "g4c4")),
        RawLesson("Advanced Pawn Technique in Lucena", "r7/P4R2/3p4/8/2P5/5p2/P3k3/6K1 b - - 1 61", "Execute the precise endgame technique with a8a7 to secure victory.", "Look for the key move a8a7.", listOf("a8a7", "f7a7", "f3f2", "g1h2", "f2f1q")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/6p1/5p1p/5P1P/R2k1K1P/1p1p4/8/8 b - - 1 50", "Execute the precise endgame technique with d4c3 to secure victory.", "Look for the key move d4c3.", listOf("d4c3", "f4e3", "d3d2", "a4d4", "b3b2")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/6k1/2P3P1/p2K1P2/6rp/8/3R4 b - - 0 46", "Execute the precise endgame technique with a4a3 to secure victory.", "Look for the key move a4a3.", listOf("a4a3", "c5c6", "h3h2", "d4e5", "g3g1")),
        RawLesson("Advanced Pawn Technique in Lucena", "4k3/pp4p1/3PKp2/7r/6p1/P2R1P2/8/8 w - - 0 37", "Execute the precise endgame technique with d6d7 to secure victory.", "Look for the key move d6d7.", listOf("d6d7", "e8d8", "d3c3", "h5e5", "e6d6", "e5d5", "d6d5")),
        RawLesson("Advanced Pawn Technique in Lucena", "2r5/8/4P3/5PK1/4k3/6P1/8/8 w - - 3 71", "Execute the precise endgame technique with e6e7 to secure victory.", "Look for the key move e6e7.", listOf("e6e7", "e4e5", "g5g6", "c8g8", "g6f7")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/2P5/P7/1r6/5pkP/8/2R2K2 b - - 1 54", "Execute the precise endgame technique with b4b2 to secure victory.", "Look for the key move b4b2.", listOf("b4b2", "c6c7", "b2h2", "f1e1", "f3f2")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/4k3/4pppp/1r2P2P/1P3PK1/8/8/1R6 w - - 0 40", "Execute the precise endgame technique with h5g6 to secure victory.", "Look for the key move h5g6.", listOf("h5g6", "f6e5", "g4h5", "e5f4", "h5h6", "f4f3", "g6g7", "e7f7", "b1f1")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/7k/5K2/4P3/6r1/R6p/8/8 b - - 1 51", "Execute the precise endgame technique with g4g6 to secure victory.", "Look for the key move g4g6.", listOf("g4g6", "f6f7", "h3h2", "a3h3", "g6h6", "h3h2", "h6h2", "e5e6", "h2f2")),
        RawLesson("Advanced Pawn Technique in Lucena", "7r/8/6P1/5P2/2k5/1p6/1Kp5/2R5 w - - 1 54", "Execute the precise endgame technique with g6g7 to secure victory.", "Look for the key move g6g7.", listOf("g6g7", "h8a8", "c1a1", "c2c1b", "a1c1")),
        RawLesson("Advanced Pawn Technique in Lucena", "2r5/7R/2P2p2/1P3P2/7k/6pp/1K6/8 b - - 3 48", "Execute the precise endgame technique with h4g4 to secure victory.", "Look for the key move h4g4.", listOf("h4g4", "c6c7", "c8c7", "h7c7", "h3h2", "b5b6", "h2h1q")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/5pk1/1PK5/R5pp/8/8/8/2r5 w - - 2 41", "Execute the precise endgame technique with a5c5 to secure victory.", "Look for the key move a5c5.", listOf("a5c5", "c1e1", "b6b7", "e1e6", "c6b5", "e6e8", "c5c8")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/3R1p2/P4kp1/2K4p/7r/8/8/8 w - - 0 38", "Execute the precise endgame technique with a6a7 to secure victory.", "Look for the key move a6a7.", listOf("a6a7", "h4a4", "c5b5", "a4a7", "d7a7")),
        RawLesson("Advanced Pawn Technique in Lucena", "1R6/5p2/8/2p4P/1pk5/6P1/2r2P2/4K3 w - - 1 39", "Execute the precise endgame technique with e1f1 to secure victory.", "Look for the key move e1f1.", listOf("e1f1", "c2a2", "h5h6", "a2a6", "h6h7")),
        RawLesson("Advanced Pawn Technique in Lucena", "8/8/1p3p2/1P2p3/4P1pk/4K2p/5r2/7R w - - 0 56", "Execute the precise endgame technique with e3f2 to secure victory.", "Look for the key move e3f2.", listOf("e3f2", "f6f5", "e4f5", "g4g3", "f2f3", "e5e4", "f3f4", "h3h2", "f5f6", "h4h3", "f6f7")),
        RawLesson("Advanced Pawn Technique in Lucena", "5k2/6R1/7K/7P/5rP1/p7/8/8 b - - 0 49", "Execute the precise endgame technique with f4a4 to secure victory.", "Look for the key move f4a4.", listOf("f4a4", "g7g5", "a3a2")),
        RawLesson("Advanced Pawn Technique in Lucena", "4r3/p2RP3/2kP4/7p/8/5pr1/5R2/1K6 w - - 1 53", "Execute the precise endgame technique with d7d8 to secure victory.", "Look for the key move d7d8.", listOf("d7d8", "g3g1", "b1a2", "g1g2", "f2g2", "f3g2", "d8e8", "g2g1q", "e8c8", "c6d6", "e7e8q")),
        RawLesson("Advanced Pawn Technique in Lucena", "2k5/p1p5/P7/1PP1r3/5p1p/6pP/6P1/2R3K1 w - - 0 40", "Execute the precise endgame technique with b5b6 to secure victory.", "Look for the key move b5b6.", listOf("b5b6", "c8b8", "b6b7", "e5e6", "c1a1"))
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
                id = "e_lucena_$level",
                title = "Level $level ($tier difficulty) • ${base.subtitle}",
                category = "Lucena",
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
