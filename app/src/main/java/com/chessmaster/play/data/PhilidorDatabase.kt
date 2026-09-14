package com.chessmaster.play.data

import com.chessmaster.play.model.EndgameLesson

object PhilidorDatabase {

    private data class RawLesson(
        val subtitle: String,
        val fen: String,
        val explanation: String,
        val hint: String,
        val moves: List<String>
    )

    private val pool = listOf(
        RawLesson("Defensive Move Technique in Philidor", "2r5/8/5k2/6p1/p1R3Pp/PpK4P/1P6/8 b - - 10 45", "Execute the precise endgame technique with c8c4 to secure victory.", "Look for the key move c8c4.", listOf("c8c4", "c3c4", "f6e5")),
        RawLesson("Defensive Move Technique in Philidor", "8/4k3/6R1/p5K1/5P1P/8/8/r7 b - - 0 45", "Execute the precise endgame technique with a1g1 to secure victory.", "Look for the key move a1g1.", listOf("a1g1", "g5f5", "g1g6", "f5g6", "a5a4")),
        RawLesson("Defensive Move Technique in Philidor", "8/5rk1/4K1p1/3R2P1/8/8/8/8 w - - 10 70", "Execute the precise endgame technique with d5d7 to secure victory.", "Look for the key move d5d7.", listOf("d5d7", "f7d7", "e6d7", "g7f7", "d7d6")),
        RawLesson("Defensive Move Technique in Philidor", "3R4/8/5K2/3k2p1/8/6pP/5pP1/8 b - - 1 101", "Execute the precise endgame technique with d5e4 to secure victory.", "Look for the key move d5e4.", listOf("d5e4", "d8d1", "e4e3", "d1a1", "e3e2")),
        RawLesson("Defensive Move Technique in Philidor", "8/4R2p/8/8/4k1P1/7P/1r3p2/5K2 b - - 6 60", "Execute the precise endgame technique with e4f3 to secure victory.", "Look for the key move e4f3.", listOf("e4f3", "e7f7", "f3g3")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/4pk2/4R1p1/r1PK2P1/4P3/8/8 b - - 4 42", "Execute the precise endgame technique with a4c4 to secure victory.", "Look for the key move a4c4.", listOf("a4c4", "d4c4", "f6e5", "c4d3", "e5d5")),
        RawLesson("Defensive Move Technique in Philidor", "8/1p3Rp1/p1p3r1/P1P5/1P3k1K/7P/8/8 b - - 1 45", "Execute the precise endgame technique with g6f6 to secure victory.", "Look for the key move g6f6.", listOf("g6f6", "f7f6", "g7f6")),
        RawLesson("Defensive Move Technique in Philidor", "8/6p1/r6p/1k2pp2/8/3K1P1P/6P1/R7 w - - 0 53", "Execute the precise endgame technique with a1a6 to secure victory.", "Look for the key move a1a6.", listOf("a1a6", "b5a6", "d3c4")),
        RawLesson("Advanced Pawn Technique in Philidor", "2r5/4k3/1R6/4p3/1p4R1/2p5/P6P/6K1 b - - 0 36", "Execute the precise endgame technique with c3c2 to secure victory.", "Look for the key move c3c2.", listOf("c3c2", "g4g7", "e7f8", "g7c7", "c8c7")),
        RawLesson("Defensive Move Technique in Philidor", "8/6k1/7p/8/1R1K4/1P6/6r1/8 b - - 0 46", "Execute the precise endgame technique with g2g4 to secure victory.", "Look for the key move g2g4.", listOf("g2g4", "d4c5", "g4b4", "c5b4", "h6h5", "b4c3", "h5h4")),
        RawLesson("Defensive Move Technique in Philidor", "1r6/6p1/7p/8/4k3/7P/1RK2PP1/8 b - - 0 45", "Execute the precise endgame technique with b8b2 to secure victory.", "Look for the key move b8b2.", listOf("b8b2", "c2b2", "e4d3")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/8/4R2p/2k3p1/5rPK/8/8 w - - 0 52", "Execute the precise endgame technique with h3h4 to secure victory.", "Look for the key move h3h4.", listOf("h3h4", "c4d4", "e5h5", "f3f8", "h4g4")),
        RawLesson("Defensive Move Technique in Philidor", "r6r/pp6/2pp3p/4pk2/3P1p2/2P2R1P/PP3PP1/3R2K1 b - - 0 21", "Execute the precise endgame technique with e5e4 to secure victory.", "Look for the key move e5e4.", listOf("e5e4", "g2g4", "f5g5")),
        RawLesson("Defensive Move Technique in Philidor", "8/5pk1/p5p1/1p6/1P2p1Pp/P1RrP2P/5PK1/8 w - - 1 39", "Execute the precise endgame technique with c3d3 to secure victory.", "Look for the key move c3d3.", listOf("c3d3", "e4d3", "g2f1")),
        RawLesson("Defensive Move Technique in Philidor", "4R3/1p6/1Pp4K/5k2/P4r2/8/8/8 w - - 0 51", "Execute the precise endgame technique with e8f8 to secure victory.", "Look for the key move e8f8.", listOf("e8f8", "f5e4", "f8f4", "e4f4", "a4a5")),
        RawLesson("Defensive Move Technique in Philidor", "8/7p/rk2ppp1/8/3K1P2/8/R4P1P/8 w - - 0 39", "Execute the precise endgame technique with a2a6 to secure victory.", "Look for the key move a2a6.", listOf("a2a6", "b6a6", "d4c5")),
        RawLesson("Defensive Move Technique in Philidor", "8/4k3/5R2/3pPK2/p1pP4/2P5/r7/8 b - - 0 40", "Execute the precise endgame technique with a2f2 to secure victory.", "Look for the key move a2f2.", listOf("a2f2", "f5g6", "f2f6", "e5f6", "e7f8")),
        RawLesson("Defensive Move Technique in Philidor", "8/p7/1p6/3k4/3P4/P7/1P2rRK1/8 b - - 2 46", "Execute the precise endgame technique with e2f2 to secure victory.", "Look for the key move e2f2.", listOf("e2f2", "g2f2", "d5d4", "f2e2", "d4c4")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/1r4p1/3k1p1p/3P3P/1RK3P1/5P2/8 b - - 0 42", "Execute the precise endgame technique with b6b3 to secure victory.", "Look for the key move b6b3.", listOf("b6b3", "c3b3", "d5d4", "b3c2", "d4e4")),
        RawLesson("Defensive Move Technique in Philidor", "6R1/P7/8/5k2/5pp1/8/1r3K2/8 w - - 1 53", "Execute the precise endgame technique with f2e1 to secure victory.", "Look for the key move f2e1.", listOf("f2e1", "b2b1", "e1d2")),
        RawLesson("Defensive Move Technique in Philidor", "2r5/2Pk1p1p/6p1/5p2/3K1P2/8/6PP/2R5 w - - 3 43", "Execute the precise endgame technique with d4e5 to secure victory.", "Look for the key move d4e5.", listOf("d4e5", "c8e8", "e5f6")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/2R5/8/1pk5/7P/P2r4/6K1 b - - 1 63", "Execute the precise endgame technique with c4b5 to secure victory.", "Look for the key move c4b5.", listOf("c4b5", "c6c8", "d2a2")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/3pkpp1/7r/1PP5/7R/2K4P/8 w - - 0 44", "Execute the precise endgame technique with h3h5 to secure victory.", "Look for the key move h3h5.", listOf("h3h5", "g6h5", "b4b5")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/K4k1p/6pP/6P1/P1r2P2/5R2/8 b - - 0 43", "Execute the precise endgame technique with c3a3 to secure victory.", "Look for the key move c3a3.", listOf("c3a3", "a6b5", "f6e5")),
        RawLesson("Defensive Move Technique in Philidor", "1K6/R2Pk3/5p2/7p/8/p7/5P2/2r5 w - - 0 48", "Execute the precise endgame technique with a7c7 to secure victory.", "Look for the key move a7c7.", listOf("a7c7", "c1e1", "b8c8")),
        RawLesson("Defensive Move Technique in Philidor", "6k1/5p2/6p1/3p1rRp/8/3K4/P1P5/8 w - - 2 32", "Execute the precise endgame technique with g5f5 to secure victory.", "Look for the key move g5f5.", listOf("g5f5", "g6f5", "a2a4")),
        RawLesson("Defensive Move Technique in Philidor", "8/4k1r1/3p2pp/1p2p3/5p2/1P6/P4PPP/2R3K1 w - - 0 31", "Execute the precise endgame technique with c1c7 to secure victory.", "Look for the key move c1c7.", listOf("c1c7", "e7f6", "c7g7", "f6g7", "a2a4", "g7f6", "a4a5")),
        RawLesson("Defensive Move Technique in Philidor", "6k1/1R3Rpp/p3p3/3pP3/P2P4/8/2r2KPP/2r5 w - - 3 27", "Execute the precise endgame technique with f2g3 to secure victory.", "Look for the key move f2g3.", listOf("f2g3", "c1g1", "f7g7")),
        RawLesson("Defensive Move Technique in Philidor", "8/6R1/3rk1p1/8/2K5/6P1/8/8 w - - 0 55", "Execute the precise endgame technique with g7g6 to secure victory.", "Look for the key move g7g6.", listOf("g7g6", "e6e5", "g6d6", "e5d6", "c4d4", "d6e6", "d4e4", "e6f6", "e4f4", "f6g6", "f4g4")),
        RawLesson("Defensive Move Technique in Philidor", "8/6k1/8/1K4pP/1R5r/P7/1P6/8 b - - 0 45", "Execute the precise endgame technique with h4b4 to secure victory.", "Look for the key move h4b4.", listOf("h4b4", "b5b4", "g5g4")),
        RawLesson("Defensive Move Technique in Philidor", "8/5p2/1k2p3/pp1rPp1p/5P1P/1P2K1P1/P2R4/8 w - - 0 33", "Execute the precise endgame technique with d2d5 to secure victory.", "Look for the key move d2d5.", listOf("d2d5", "e6d5", "e3d4", "b6c6", "a2a3")),
        RawLesson("Defensive Move Technique in Philidor", "8/p7/1pRrk2p/6p1/4K1P1/1P6/P7/8 w - - 3 48", "Execute the precise endgame technique with c6d6 to secure victory.", "Look for the key move c6d6.", listOf("c6d6", "e6d6", "e4f5", "d6d5", "f5g6", "d5e4", "g6h6", "e4f4", "h6h5", "a7a5", "a2a4")),
        RawLesson("Defensive Move Technique in Philidor", "8/5Rp1/pr6/7k/5K1P/5P2/8/8 b - - 0 44", "Execute the precise endgame technique with b6f6 to secure victory.", "Look for the key move b6f6.", listOf("b6f6", "f7f6", "g7f6", "f4f5", "a6a5")),
        RawLesson("Defensive Move Technique in Philidor", "8/p7/6p1/P1KRrk1p/1P6/8/8/8 w - - 3 53", "Execute the precise endgame technique with d5e5 to secure victory.", "Look for the key move d5e5.", listOf("d5e5", "f5e5", "b4b5")),
        RawLesson("Defensive Move Technique in Philidor", "2r1rk2/4Rppp/8/p4P2/P2p4/3P4/6PP/4R1K1 w - - 1 30", "Execute the precise endgame technique with e7e8 to secure victory.", "Look for the key move e7e8.", listOf("e7e8", "c8e8", "e1e8", "f8e8", "g1f2")),
        RawLesson("Defensive Move Technique in Philidor", "6R1/6r1/5pk1/5p1p/7P/6P1/5PK1/8 w - - 4 46", "Execute the precise endgame technique with g8g7 to secure victory.", "Look for the key move g8g7.", listOf("g8g7", "g6g7", "g2f3")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/8/6K1/pr5p/6kP/R5P1/8 b - - 1 58", "Execute the precise endgame technique with b4b5 to secure victory.", "Look for the key move b4b5.", listOf("b4b5", "g5f6", "b5a5")),
        RawLesson("Defensive Move Technique in Philidor", "8/1p6/p3p3/3p3P/4pkPK/2P2r2/PP6/4R3 w - - 3 35", "Execute the precise endgame technique with h5h6 to secure victory.", "Look for the key move h5h6.", listOf("h5h6", "f3f2", "e1h1")),
        RawLesson("Defensive Move Technique in Philidor", "8/4Rp1p/5Pp1/5kP1/2K4P/8/r7/8 w - - 4 49", "Execute the precise endgame technique with e7f7 to secure victory.", "Look for the key move e7f7.", listOf("e7f7", "a2a4", "c4d5")),
        RawLesson("Defensive Move Technique in Philidor", "3r4/1P6/8/6p1/1R1K4/5k2/8/8 w - - 1 54", "Execute the precise endgame technique with d4e5 to secure victory.", "Look for the key move d4e5.", listOf("d4e5", "d8b8", "e5f5", "f3e3", "f5g5")),
        RawLesson("Defensive Move Technique in Philidor", "6k1/1p4pp/8/pP1rR3/5P2/1P4PP/7K/8 b - - 1 35", "Execute the precise endgame technique with d5e5 to secure victory.", "Look for the key move d5e5.", listOf("d5e5", "f4e5", "g8f7")),
        RawLesson("Defensive Move Technique in Philidor", "8/P7/8/4k3/3p3P/rR2pK2/8/8 b - - 0 44", "Execute the precise endgame technique with a3a7 to secure victory.", "Look for the key move a3a7.", listOf("a3a7", "b3b5", "e5d6")),
        RawLesson("Defensive Move Technique in Philidor", "8/6p1/7k/1r4RP/4KP2/8/8/8 b - - 6 62", "Execute the precise endgame technique with b5g5 to secure victory.", "Look for the key move b5g5.", listOf("b5g5", "f4g5", "h6g5", "e4f3", "g5h5", "f3g3", "h5g5")),
        RawLesson("Defensive Move Technique in Philidor", "8/1r6/8/2P5/2K5/1P6/8/5k2 w - - 3 66", "Execute the precise endgame technique with b3b4 to secure victory.", "Look for the key move b3b4.", listOf("b3b4", "b7h7", "c5c6", "f1e2", "b4b5")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/8/Rp2k2P/1P2p1p1/6P1/5K1r/8 w - - 7 48", "Execute the precise endgame technique with f2e3 to secure victory.", "Look for the key move f2e3.", listOf("f2e3", "h2h3", "a5b5")),
        RawLesson("Defensive Move Technique in Philidor", "4R3/8/K7/1P2r3/5k2/8/8/8 b - - 2 65", "Execute the precise endgame technique with e5e8 to secure victory.", "Look for the key move e5e8.", listOf("e5e8", "a6b6", "f4e5")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/1p3r1p/pP3k1P/P1R5/6K1/8/8 w - - 0 52", "Execute the precise endgame technique with c4f4 to secure victory.", "Look for the key move c4f4.", listOf("c4f4", "f5e5", "f4f6", "e5f6", "g3f4", "f6e6", "f4e4")),
        RawLesson("Defensive Move Technique in Philidor", "7k/p4p1p/6p1/3P4/4P3/2r3P1/5P1P/5K1R b - - 0 28", "Execute the precise endgame technique with c3c1 to secure victory.", "Look for the key move c3c1.", listOf("c3c1", "f1e2", "c1h1", "d5d6", "h1b1")),
        RawLesson("Advanced Pawn Technique in Philidor", "8/8/2p1r2k/2P4P/1P1p1RP1/4p3/4K3/8 b - - 2 47", "Execute the precise endgame technique with d4d3 to secure victory.", "Look for the key move d4d3.", listOf("d4d3", "e2e1", "e3e2", "f4d4", "e6e3")),
        RawLesson("Defensive Move Technique in Philidor", "7r/6p1/Pk6/8/1P6/8/R1K5/8 b - - 2 49", "Execute the precise endgame technique with h8h2 to secure victory.", "Look for the key move h8h2.", listOf("h8h2", "c2b3", "h2a2", "b3a2", "g7g5")),
        RawLesson("Defensive Move Technique in Philidor", "8/6p1/7p/8/6P1/8/1kr1r2P/4RK2 w - - 2 64", "Execute the precise endgame technique with e1e2 to secure victory.", "Look for the key move e1e2.", listOf("e1e2", "b2c3", "e2c2", "c3c2", "f1e2")),
        RawLesson("Defensive Move Technique in Philidor", "5k2/pR4pp/5p2/2pK1P2/7P/2rp4/5P2/8 w - - 2 33", "Execute the precise endgame technique with d5e6 to secure victory.", "Look for the key move d5e6.", listOf("d5e6", "g7g6", "e6f6", "f8e8", "f6e6")),
        RawLesson("Defensive Move Technique in Philidor", "8/1p6/1p1k1p1p/1P5P/1PR2KP1/8/8/1r6 w - - 1 50", "Execute the precise endgame technique with f4f5 to secure victory.", "Look for the key move f4f5.", listOf("f4f5", "b1f1", "f5g6", "d6d5", "c4c7")),
        RawLesson("Defensive Move Technique in Philidor", "R7/5ppk/8/1P2p2P/3p4/7r/P2K4/8 w - - 0 34", "Execute the precise endgame technique with b5b6 to secure victory.", "Look for the key move b5b6.", listOf("b5b6", "h3h2", "d2c1")),
        RawLesson("Defensive Move Technique in Philidor", "3K2k1/1p4p1/3P4/5R1p/1P6/P6P/2r2p2/8 b - - 3 49", "Execute the precise endgame technique with g7g6 to secure victory.", "Look for the key move g7g6.", listOf("g7g6", "f5f3", "g6g5")),
        RawLesson("Defensive Move Technique in Philidor", "3k4/4R3/p2P4/1p2P1p1/1P5p/P3r2K/8/8 w - - 6 47", "Execute the precise endgame technique with h3g4 to secure victory.", "Look for the key move h3g4.", listOf("h3g4", "h4h3", "g4f5", "h3h2", "e7h7")),
        RawLesson("Defensive Move Technique in Philidor", "4k3/2R5/4K3/4P3/2pr4/8/8/8 w - - 0 66", "Execute the precise endgame technique with c7c8 to secure victory.", "Look for the key move c7c8.", listOf("c7c8", "d4d8", "c8c4", "d8b8", "c4c6")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/p5R1/r3k1p1/P4p2/1P4K1/5P2/8 w - - 0 42", "Execute the precise endgame technique with g3g4 to secure victory.", "Look for the key move g3g4.", listOf("g3g4", "e5e4", "g6g5", "a5g5", "g4g5")),
        RawLesson("Defensive Move Technique in Philidor", "6RK/7P/8/4r3/8/4p3/8/7k w - - 2 79", "Execute the precise endgame technique with h8g7 to secure victory.", "Look for the key move h8g7.", listOf("h8g7", "e5g5", "g7h6")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/p1R5/1p1pp3/6k1/1PKP4/1PP3r1/8 b - - 0 38", "Execute the precise endgame technique with a6a5 to secure victory.", "Look for the key move a6a5.", listOf("a6a5", "b3b4", "a5a4")),
        RawLesson("Defensive Move Technique in Philidor", "R7/P3k1p1/5p1p/5P2/r2K2P1/8/8/8 w - - 7 63", "Execute the precise endgame technique with d4c5 to secure victory.", "Look for the key move d4c5.", listOf("d4c5", "e7f7", "c5b6")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/R1p1r3/P2p2k1/6p1/2P5/1P4PK/8 b - - 0 44", "Execute the precise endgame technique with g5f4 to secure victory.", "Look for the key move g5f4.", listOf("g5f4", "g2g3", "f4f3", "a6a8", "e6e2")),
        RawLesson("Defensive Move Technique in Philidor", "1R6/8/2P2pk1/1K6/8/1P5p/8/2r5 w - - 0 45", "Execute the precise endgame technique with b8h8 to secure victory.", "Look for the key move b8h8.", listOf("b8h8", "c1c3", "b3b4", "f6f5", "b5b6")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/1R4p1/7p/P4krP/8/7K/8 w - - 4 48", "Execute the precise endgame technique with b6b4 to secure victory.", "Look for the key move b6b4.", listOf("b6b4", "f4f3", "b4g4", "h5g4", "h2g1")),
        RawLesson("Defensive Move Technique in Philidor", "8/R5pk/5r2/5P1p/5KP1/8/8/8 w - - 0 82", "Execute the precise endgame technique with g4g5 to secure victory.", "Look for the key move g4g5.", listOf("g4g5", "f6f8", "f5f6", "f8g8", "f4f5")),
        RawLesson("Defensive Move Technique in Philidor", "1r6/3R4/2r2kp1/4pp1p/3Pp2P/4P1P1/R4PK1/8 w - - 0 42", "Execute the precise endgame technique with d4d5 to secure victory.", "Look for the key move d4d5.", listOf("d4d5", "c6b6", "a2a7")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/p2p4/1p6/1Pr4R/P5k1/3K4/8 w - - 5 64", "Execute the precise endgame technique with h4c4 to secure victory.", "Look for the key move h4c4.", listOf("h4c4", "b5c4", "a3a4")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/1p6/7p/P7/2P4k/1r3R2/6K1 b - - 1 38", "Execute the precise endgame technique with b2b1 to secure victory.", "Look for the key move b2b1.", listOf("b2b1", "f2f1", "b1f1", "g1f1", "h3g3", "c3c4", "h5h4")),
        RawLesson("Defensive Move Technique in Philidor", "r2r4/p5kp/1p2Kpp1/2p5/2P5/1P2P1P1/P4P1P/3R1R2 b - - 0 24", "Execute the precise endgame technique with d8e8 to secure victory.", "Look for the key move d8e8.", listOf("d8e8", "e6d5", "a8c8")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/8/8/P1k2pR1/r7/8/3K4 b - - 2 56", "Execute the precise endgame technique with c4d3 to secure victory.", "Look for the key move c4d3.", listOf("c4d3", "d1e1", "d3e3")),
        RawLesson("Defensive Move Technique in Philidor", "k7/p7/8/6p1/8/6P1/r2RK2P/8 b - - 2 44", "Execute the precise endgame technique with a2d2 to secure victory.", "Look for the key move a2d2.", listOf("a2d2", "e2d2", "g5g4")),
        RawLesson("Defensive Move Technique in Philidor", "4K3/4P3/6k1/1R6/r7/p7/8/8 w - - 1 68", "Execute the precise endgame technique with e8f8 to secure victory.", "Look for the key move e8f8.", listOf("e8f8", "a4f4", "f8g8")),
        RawLesson("Defensive Move Technique in Philidor", "8/p2r1kpp/8/3rp3/1PR1KpP1/1P3P1P/8/2R5 b - - 5 33", "Execute the precise endgame technique with f7e6 to secure victory.", "Look for the key move f7e6.", listOf("f7e6", "c4c6", "d7d6")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/5p2/P7/4KPP1/r6p/1R4k1/8 b - - 1 44", "Execute the precise endgame technique with g2g3 to secure victory.", "Look for the key move g2g3.", listOf("g2g3", "e4f5", "a3a5")),
        RawLesson("Defensive Move Technique in Philidor", "r7/6p1/1K2k2p/R6P/P7/8/8/8 b - - 0 46", "Execute the precise endgame technique with a8a5 to secure victory.", "Look for the key move a8a5.", listOf("a8a5", "b6a5", "g7g5")),
        RawLesson("Defensive Move Technique in Philidor", "R7/6r1/5ppk/7p/5P1P/5KP1/8/8 w - - 6 58", "Execute the precise endgame technique with a8h8 to secure victory.", "Look for the key move a8h8.", listOf("a8h8", "g7h7", "h8h7", "h6h7", "f3e4", "h7g8", "e4d5")),
        RawLesson("Defensive Move Technique in Philidor", "8/6k1/2p1r1p1/p2p4/3P1K2/P1P5/1P3R2/8 b - - 9 51", "Execute the precise endgame technique with e6f6 to secure victory.", "Look for the key move e6f6.", listOf("e6f6", "f4e3", "f6f2", "e3f2", "a5a4")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/p1R5/PpK3r1/1P6/8/3k4/8 w - - 13 67", "Execute the precise endgame technique with c5b6 to secure victory.", "Look for the key move c5b6.", listOf("c5b6", "g5g4", "b6a6", "g4b4", "c6c5")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/5P2/1kr4R/8/6K1/8/8 b - - 0 52", "Execute the precise endgame technique with c5h5 to secure victory.", "Look for the key move c5h5.", listOf("c5h5", "g3g4", "h5h8", "g4f5", "b5c6")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/5k2/3P1ppp/2R2P2/6P1/2p3PK/2r5 b - - 0 45", "Execute the precise endgame technique with g5f4 to secure victory.", "Look for the key move g5f4.", listOf("g5f4", "g3f4", "h5h4", "c4c7", "f6g6")),
        RawLesson("Defensive Move Technique in Philidor", "4K3/4P3/2k5/p7/Pp6/2pR4/2P2r2/8 b - - 8 49", "Execute the precise endgame technique with f2c2 to secure victory.", "Look for the key move f2c2.", listOf("f2c2", "d3e3", "c2h2")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/8/8/P7/2K3p1/2R2rPk/8 b - - 5 56", "Execute the precise endgame technique with h2g2 to secure victory.", "Look for the key move h2g2.", listOf("h2g2", "a4a5", "g2h3")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/3r4/4kp2/8/2K4R/7P/8 b - - 1 56", "Execute the precise endgame technique with e5e4 to secure victory.", "Look for the key move e5e4.", listOf("e5e4", "h3h8", "f5f4")),
        RawLesson("Defensive Move Technique in Philidor", "4k3/7p/r4K2/1R3P2/8/8/8/8 w - - 3 44", "Execute the precise endgame technique with f6g7 to secure victory.", "Look for the key move f6g7.", listOf("f6g7", "a6a7", "g7g8", "e8e7", "b5b6", "a7a8", "g8g7")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/2p2k2/1p1r1p2/p2R2pP/P1P3P1/5PK1/8 w - - 3 48", "Execute the precise endgame technique with d4d5 to secure victory.", "Look for the key move d4d5.", listOf("d4d5", "c6d5", "g2f1")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/2R1k3/5p1p/3pr1pP/1K4P1/2P2P2/8 b - - 1 42", "Execute the precise endgame technique with e6d5 to secure victory.", "Look for the key move e6d5.", listOf("e6d5", "c6f6", "e4e5", "f6f8", "d5e4")),
        RawLesson("Defensive Move Technique in Philidor", "8/3k4/6RP/2p1p3/1pPp2P1/1r6/3K4/8 w - - 0 48", "Execute the precise endgame technique with g6g5 to secure victory.", "Look for the key move g6g5.", listOf("g6g5", "b3b2", "d2c1", "b2h2", "g5h5")),
        RawLesson("Defensive Move Technique in Philidor", "8/6p1/6pp/3RP3/2p3PP/2k5/6r1/4K3 w - - 0 55", "Execute the precise endgame technique with e5e6 to secure victory.", "Look for the key move e5e6.", listOf("e5e6", "g2g1", "e1e2", "g1g4", "d5e5")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/p1r2pk1/2R4p/1P3Pp1/6P1/5KP1/8 b - - 2 43", "Execute the precise endgame technique with c6c5 to secure victory.", "Look for the key move c6c5.", listOf("c6c5", "b4c5", "g6f5", "f2e3", "f5e6", "e3e4", "f6f5", "e4d4", "e6d7")),
        RawLesson("Defensive Move Technique in Philidor", "8/2k5/p1r3p1/R1p2p2/2P2P1P/P2K1P2/8/8 b - - 8 41", "Execute the precise endgame technique with c7b6 to secure victory.", "Look for the key move c7b6.", listOf("c7b6", "a5a4", "c6e6")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/R1k1K3/3p4/P2P4/1rp5/8/8 b - - 1 57", "Execute the precise endgame technique with b3b6 to secure victory.", "Look for the key move b3b6.", listOf("b3b6", "a6a5", "c6c7")),
        RawLesson("Defensive Move Technique in Philidor", "8/6p1/2k4p/p1r5/3K4/1PR2P1P/8/8 b - - 13 46", "Execute the precise endgame technique with c5c3 to secure victory.", "Look for the key move c5c3.", listOf("c5c3", "d4c3", "g7g5")),
        RawLesson("Defensive Move Technique in Philidor", "6R1/8/p5r1/1p3p1k/8/1P4K1/P4P2/8 w - - 2 42", "Execute the precise endgame technique with g8g6 to secure victory.", "Look for the key move g8g6.", listOf("g8g6", "h5g6", "b3b4", "g6g5", "g3f3")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/5pk1/8/4p1pR/2K4p/1P6/8 b - - 1 54", "Execute the precise endgame technique with f6f5 to secure victory.", "Look for the key move f6f5.", listOf("f6f5", "c3d2", "g6g5", "h4h8", "g5f4", "b2b4", "g4g3", "h8h4", "f4f3")),
        RawLesson("Advanced Pawn Technique in Philidor", "8/6k1/6p1/R5Kp/5PPP/p1r5/8/8 w - - 0 47", "Execute the precise endgame technique with a5a7 to secure victory.", "Look for the key move a5a7.", listOf("a5a7", "g7f8", "g5g6", "h5g4", "f4f5", "g4g3", "f5f6", "c3e3", "a7h7", "f8e8", "f6f7", "e8e7", "h7h8", "e3f3", "h8e8", "e7d7", "e8e2")),
        RawLesson("Defensive Move Technique in Philidor", "8/8/1p2p3/pP6/4k3/3Rr3/1P5P/1K6 w - - 0 45", "Execute the precise endgame technique with d3e3 to secure victory.", "Look for the key move d3e3.", listOf("d3e3", "e4e3", "h2h4", "e3f4", "b1c2", "f4g4", "c2d3")),
        RawLesson("Defensive Move Technique in Philidor", "5k2/3R4/1p4p1/P4p2/1P2p3/K6r/2P5/8 w - - 1 37", "Execute the precise endgame technique with a3b2 to secure victory.", "Look for the key move a3b2.", listOf("a3b2", "f8e8", "d7g7", "b6a5", "b4a5")),
        RawLesson("Defensive Move Technique in Philidor", "8/4k3/6R1/1p5R/8/1P2p3/KP4r1/8 b - - 0 46", "Execute the precise endgame technique with g2g6 to secure victory.", "Look for the key move g2g6.", listOf("g2g6", "h5h1", "e7d6")),
        RawLesson("Defensive Move Technique in Philidor", "2K5/2P5/8/8/3r1k2/6p1/8/6R1 w - - 0 50", "Execute the precise endgame technique with g1c1 to secure victory.", "Look for the key move g1c1.", listOf("g1c1", "g3g2", "c8b8")),
        RawLesson("Defensive Move Technique in Philidor", "8/5p2/4p3/p3Pk2/5r1p/1P5K/P4R1P/8 w - - 0 48", "Execute the precise endgame technique with f2f4 to secure victory.", "Look for the key move f2f4.", listOf("f2f4", "f5f4", "h3h4", "f4e5", "h4g3", "e5d4", "g3f2"))
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
                id = "e_philidor_$level",
                title = "Level $level ($tier difficulty) • ${base.subtitle}",
                category = "Philidor",
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
