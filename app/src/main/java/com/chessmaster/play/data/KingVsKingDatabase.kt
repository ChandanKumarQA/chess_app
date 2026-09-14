package com.chessmaster.play.data

import com.chessmaster.play.model.EndgameLesson

object KingVsKingDatabase {

    private data class RawLesson(
        val subtitle: String,
        val fen: String,
        val explanation: String,
        val hint: String,
        val moves: List<String>
    )

    private val pool = listOf(
        RawLesson("Advanced Pawn Technique in King vs King", "8/8/p7/5p1P/4pk2/8/6P1/3K4 w - - 1 43", "Execute the precise endgame technique with h5h6 to secure victory.", "Look for the key move h5h6.", listOf("h5h6", "f4e5", "h6h7")),
        RawLesson("Master Technique in King vs King", "8/8/8/p6p/P1K2k1P/8/8/8 w - - 6 48", "Execute the precise endgame technique with c4b5 to secure victory.", "Look for the key move c4b5.", listOf("c4b5", "f4e5", "b5a5")),
        RawLesson("Advanced Pawn Technique in King vs King", "3k4/8/3PK3/5p2/5PpP/8/8/8 b - - 0 63", "Execute the precise endgame technique with g4g3 to secure victory.", "Look for the key move g4g3.", listOf("g4g3", "h4h5", "g3g2", "h5h6", "g2g1q")),
        RawLesson("Advanced Pawn Technique in King vs King", "8/8/8/6KP/p7/1k6/8/8 w - - 1 51", "Execute the precise endgame technique with h5h6 to secure victory.", "Look for the key move h5h6.", listOf("h5h6", "a4a3", "h6h7", "b3c2", "h7h8q")),
        RawLesson("Master Technique in King vs King", "8/8/1p1P1k2/1p5K/pP6/P7/8/8 b - - 1 42", "Execute the precise endgame technique with f6e6 to secure victory.", "Look for the key move f6e6.", listOf("f6e6", "d6d7", "e6d7")),
        RawLesson("Defensive Move Technique in King vs King", "8/8/p1k3p1/P1P2P2/2K4p/7P/8/8 b - - 0 51", "Execute the precise endgame technique with g6f5 to secure victory.", "Look for the key move g6f5.", listOf("g6f5", "c4d4", "f5f4")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/8/5k2/p1p5/P1P3K1/1P6/8/8 b - - 6 59", "Execute the precise endgame technique with f6e5 to secure victory.", "Look for the key move f6e5.", listOf("f6e5", "g4g5", "e5d4", "g5f4", "d4c3")),
        RawLesson("Mate Technique in King vs King", "8/p4p1p/2p5/4pp2/5k1P/5P2/PPP2KP1/8 w - - 0 28", "Execute the precise endgame technique with g2g3 to secure victory.", "Look for the key move g2g3.", listOf("g2g3")),
        RawLesson("Advanced Pawn Technique in King vs King", "8/8/p2K3p/5p1P/P3k3/2P5/8/8 b - - 1 43", "Execute the precise endgame technique with f5f4 to secure victory.", "Look for the key move f5f4.", listOf("f5f4", "c3c4", "f4f3", "c4c5", "f3f2", "c5c6", "f2f1q")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/2p5/3k2p1/1p1P1p2/1P3P2/3K2Pp/7P/8 w - - 2 44", "Execute the precise endgame technique with d3d4 to secure victory.", "Look for the key move d3d4.", listOf("d3d4", "g6g5", "f4g5")),
        RawLesson("Pawn Endgame Technique in King vs King", "7K/8/8/5p2/4kP2/6P1/8/8 b - - 0 62", "Execute the precise endgame technique with e4f3 to secure victory.", "Look for the key move e4f3.", listOf("e4f3", "h8g7", "f3g3", "g7f6", "g3f4")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/4k3/6p1/4PpP1/8/5K2/8/8 b - - 2 60", "Execute the precise endgame technique with e7e6 to secure victory.", "Look for the key move e7e6.", listOf("e7e6", "f3f4", "e6d5")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/p5k1/1pp3p1/4pp1p/5P1P/2P3P1/PP4K1/8 w - - 0 36", "Execute the precise endgame technique with f4e5 to secure victory.", "Look for the key move f4e5.", listOf("f4e5", "g7f7", "g2f3", "f7e6", "f3f4")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/7p/6pP/p2k1p2/1p1P1P2/PP2K3/8/8 w - - 0 50", "Execute the precise endgame technique with a3b4 to secure victory.", "Look for the key move a3b4.", listOf("a3b4", "a5b4", "e3d3", "d5e6", "d3c4")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/8/1p5p/p1k2K2/P7/1P5P/8/8 b - - 2 49", "Execute the precise endgame technique with c5b4 to secure victory.", "Look for the key move c5b4.", listOf("c5b4", "f5g6", "b4b3", "g6h6", "b3a4")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/1k6/1P1Kp1p1/3pP3/7P/8/8/8 b - - 2 48", "Execute the precise endgame technique with d5d4 to secure victory.", "Look for the key move d5d4.", listOf("d5d4", "d6e6", "d4d3")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/4K3/8/5k1p/7P/8/8/8 b - - 2 62", "Execute the precise endgame technique with f5g4 to secure victory.", "Look for the key move f5g4.", listOf("f5g4", "e7e6", "g4h4")),
        RawLesson("Defensive Move Technique in King vs King", "8/p6p/6p1/1P2p3/Pk2K3/5P2/6PP/8 b - - 5 34", "Execute the precise endgame technique with b4a4 to secure victory.", "Look for the key move b4a4.", listOf("b4a4", "e4e5", "a4b5", "f3f4", "a7a5")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/7p/4K2P/8/5k2/8/8/8 w - - 5 54", "Execute the precise endgame technique with e6f6 to secure victory.", "Look for the key move e6f6.", listOf("e6f6", "f4e4", "f6g7", "e4f5", "g7h7", "f5f6", "h7g8")),
        RawLesson("Pawn Endgame Technique in King vs King", "4k3/p1pp3p/1p3K2/8/5P2/8/8/8 w - - 5 45", "Execute the precise endgame technique with f6g7 to secure victory.", "Look for the key move f6g7.", listOf("f6g7", "h7h5", "f4f5", "h5h4", "f5f6")),
        RawLesson("Advanced Pawn Technique in King vs King", "8/7p/3p1p2/3P4/P1p1k3/1P1p4/6P1/4K3 w - - 0 38", "Execute the precise endgame technique with b3c4 to secure victory.", "Look for the key move b3c4.", listOf("b3c4", "e4d4", "a4a5", "d4c4", "a5a6", "c4d5", "a6a7")),
        RawLesson("Master Technique in King vs King", "8/8/8/p1k4p/P1p4P/2K2P2/8/8 w - - 1 40", "Execute the precise endgame technique with f3f4 to secure victory.", "Look for the key move f3f4.", listOf("f3f4", "c5d5", "f4f5", "d5e5", "c3c4", "e5f5", "c4b5", "f5e6", "b5a5")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/7p/6p1/3k4/5KPP/8/8/8 w - - 1 51", "Execute the precise endgame technique with f4g5 to secure victory.", "Look for the key move f4g5.", listOf("f4g5", "d5e4", "g5h6")),
        RawLesson("Defensive Move Technique in King vs King", "8/8/8/pK2k3/P7/8/8/8 w - - 2 60", "Execute the precise endgame technique with b5a5 to secure victory.", "Look for the key move b5a5.", listOf("b5a5", "e5d6", "a5b6", "d6d7", "b6b7")),
        RawLesson("Mate Technique in King vs King", "8/3k4/3p1p2/2p2P2/p1Kp2P1/P2P4/2P5/8 b - - 1 34", "Execute the precise endgame technique with d7c6 to secure victory.", "Look for the key move d7c6.", listOf("d7c6", "c2c3", "d6d5")),
        RawLesson("Defensive Move Technique in King vs King", "8/8/k6p/p4p1P/K1p1pP2/2P1P3/1P6/8 b - - 0 49", "Execute the precise endgame technique with a6b6 to secure victory.", "Look for the key move a6b6.", listOf("a6b6", "a4a3", "b6b5", "a3a2", "b5a4")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/1p6/p5p1/2P2p2/kP2P3/P2K4/6P1/8 b - - 0 38", "Execute the precise endgame technique with f5e4 to secure victory.", "Look for the key move f5e4.", listOf("f5e4", "d3e4", "a4a3")),
        RawLesson("Master Technique in King vs King", "8/7p/4K3/6p1/3k2P1/8/5P2/8 w - - 1 44", "Execute the precise endgame technique with e6f5 to secure victory.", "Look for the key move e6f5.", listOf("e6f5", "h7h6", "f5g6", "d4e4", "g6h6")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/8/5p2/1p1p1k1p/p1pP3P/P1P1K1P1/1P6/8 b - - 1 51", "Execute the precise endgame technique with f5g4 to secure victory.", "Look for the key move f5g4.", listOf("f5g4", "e3f2", "f6f5", "f2g2", "f5f4", "g3f4", "g4f4")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/8/1p2pk1p/pP4P1/P2Pp1P1/4P3/3K4/8 b - - 0 43", "Execute the precise endgame technique with f6g5 to secure victory.", "Look for the key move f6g5.", listOf("f6g5", "d4d5", "e6d5")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/8/5k2/5p1p/7P/4K1P1/8/8 w - - 1 46", "Execute the precise endgame technique with e3f4 to secure victory.", "Look for the key move e3f4.", listOf("e3f4", "f6e6", "f4g5", "e6e5", "g5h5", "e5f6", "h5h6", "f5f4", "g3f4")),
        RawLesson("Advanced Pawn Technique in King vs King", "8/8/8/3K2kp/2P5/4P3/8/8 b - - 0 50", "Execute the precise endgame technique with h5h4 to secure victory.", "Look for the key move h5h4.", listOf("h5h4", "d5e4", "g5g4", "c4c5", "h4h3", "e4e5", "h3h2", "c5c6", "h2h1q")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/8/6k1/3p1p2/3P2p1/4PPK1/8/8 w - - 1 56", "Execute the precise endgame technique with f3g4 to secure victory.", "Look for the key move f3g4.", listOf("f3g4", "g6h6", "g4f5")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/8/4k1p1/2KpP2P/5P2/8/8/8 b - - 0 53", "Execute the precise endgame technique with g6h5 to secure victory.", "Look for the key move g6h5.", listOf("g6h5", "f4f5", "e6e5", "f5f6", "e5f6")),
        RawLesson("Advanced Pawn Technique in King vs King", "5k2/5P2/4K3/4p3/ppp1P1P1/2P1P3/PP6/8 b - - 0 50", "Execute the precise endgame technique with a4a3 to secure victory.", "Look for the key move a4a3.", listOf("a4a3", "c3b4", "a3b2")),
        RawLesson("Defensive Move Technique in King vs King", "8/7K/6p1/5p2/4p3/1k4P1/5P1P/8 b - - 0 44", "Execute the precise endgame technique with g6g5 to secure victory.", "Look for the key move g6g5.", listOf("g6g5", "g3g4", "f5f4")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/8/6p1/7p/p2k3P/PP3pP1/2K2P2/8 b - - 0 64", "Execute the precise endgame technique with a4b3 to secure victory.", "Look for the key move a4b3.", listOf("a4b3", "c2b2", "d4d3", "a3a4", "d3e2", "a4a5", "e2f2")),
        RawLesson("Defensive Move Technique in King vs King", "8/pp6/2p1kpp1/3p2P1/3P1P1p/1P3K2/P1P4P/8 b - - 0 31", "Execute the precise endgame technique with f6g5 to secure victory.", "Look for the key move f6g5.", listOf("f6g5", "f3g4", "g5f4", "g4f4", "e6f6")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/1k1K4/pp6/1P6/8/P7/8/8 w - - 0 56", "Execute the precise endgame technique with a3a4 to secure victory.", "Look for the key move a3a4.", listOf("a3a4", "a6b5", "a4b5")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/8/1p2k1p1/4P2p/P2K1p1P/5P2/8/8 b - - 0 66", "Execute the precise endgame technique with g6g5 to secure victory.", "Look for the key move g6g5.", listOf("g6g5", "d4e4", "g5h4")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/8/2p4K/1p3kp1/1P6/2P4P/8/8 b - - 0 52", "Execute the precise endgame technique with g5g4 to secure victory.", "Look for the key move g5g4.", listOf("g5g4", "h3g4", "f5g4")),
        RawLesson("Advanced Pawn Technique in King vs King", "8/1P6/8/7p/3P4/3k1p1P/4p3/4K3 b - - 1 49", "Execute the precise endgame technique with d3e3 to secure victory.", "Look for the key move d3e3.", listOf("d3e3", "h3h4", "f3f2")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/8/7p/pp3k2/6p1/PP2K1P1/7P/8 w - - 0 56", "Execute the precise endgame technique with e3d4 to secure victory.", "Look for the key move e3d4.", listOf("e3d4", "b5b4", "a3b4")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/p5p1/1p3pk1/4p1p1/3p2P1/1P1P1K1P/P1P2P2/8 w - - 1 38", "Execute the precise endgame technique with f3e4 to secure victory.", "Look for the key move f3e4.", listOf("f3e4", "g6f7", "e4d5")),
        RawLesson("Advanced Pawn Technique in King vs King", "8/8/1p1p4/p2P3p/P3pP2/4K1k1/8/8 w - - 0 45", "Execute the precise endgame technique with f4f5 to secure victory.", "Look for the key move f4f5.", listOf("f4f5", "h5h4", "f5f6", "h4h3", "f6f7")),
        RawLesson("Defensive Move Technique in King vs King", "8/5p2/4p1p1/1pp1PpP1/P2k1P2/1P4P1/3K4/8 b - - 0 36", "Execute the precise endgame technique with b5a4 to secure victory.", "Look for the key move b5a4.", listOf("b5a4", "b3a4", "d4c4", "a4a5", "c4b5", "d2c3", "b5a5", "c3c4", "a5b6")),
        RawLesson("En Passant Technique in King vs King", "8/8/3p4/2kP2P1/1p6/2pK4/P7/8 b - - 0 42", "Execute the precise endgame technique with c5d5 to secure victory.", "Look for the key move c5d5.", listOf("c5d5", "g5g6", "d5e6", "g6g7", "e6f7", "a2a4", "b4a3", "g7g8r", "f7g8")),
        RawLesson("Defensive Move Technique in King vs King", "8/1p6/6K1/2k4p/7P/5P2/8/8 w - - 1 51", "Execute the precise endgame technique with f3f4 to secure victory.", "Look for the key move f3f4.", listOf("f3f4", "c5d6", "f4f5")),
        RawLesson("Master Technique in King vs King", "8/6p1/8/8/4P2k/5KpP/8/8 w - - 4 47", "Execute the precise endgame technique with f3g2 to secure victory.", "Look for the key move f3g2.", listOf("f3g2", "h4g5", "g2g3")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/8/2k5/1p6/1Pp1K3/2P5/8/8 w - - 3 55", "Execute the precise endgame technique with e4e5 to secure victory.", "Look for the key move e4e5.", listOf("e4e5", "c6d7", "e5d5")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/pp6/2p1k3/2Pp2pp/1P1P1p2/P2K1PP1/7P/8 b - - 0 42", "Execute the precise endgame technique with f4g3 to secure victory.", "Look for the key move f4g3.", listOf("f4g3", "h2g3", "h5h4", "g3h4", "g5h4")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/1p3k2/p1p3pK/P1P5/1P5P/8/8/8 b - - 10 60", "Execute the precise endgame technique with f7f6 to secure victory.", "Look for the key move f7f6.", listOf("f7f6", "h6h7", "g6g5", "h4g5", "f6g5")),
        RawLesson("Defensive Move Technique in King vs King", "8/1p6/1p1p1k2/2p1p2K/P1P1P3/1P1P4/8/8 w - - 1 39", "Execute the precise endgame technique with h5h6 to secure victory.", "Look for the key move h5h6.", listOf("h5h6", "f6e6", "h6g6", "e6e7", "g6g7")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/7p/2k3p1/p5P1/P2K4/8/8/8 w - - 5 48", "Execute the precise endgame technique with d4c4 to secure victory.", "Look for the key move d4c4.", listOf("d4c4", "c6d6", "c4b5")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/1p1k4/8/p1p5/P4P2/5K2/5P2/8 b - - 1 43", "Execute the precise endgame technique with b7b5 to secure victory.", "Look for the key move b7b5.", listOf("b7b5", "a4b5", "a5a4", "f3e4", "a4a3")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/8/7p/3k3P/5Kp1/6P1/8/8 w - - 6 47", "Execute the precise endgame technique with f4f5 to secure victory.", "Look for the key move f4f5.", listOf("f4f5", "d5d4", "f5g6")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/8/8/8/8/3K1p2/5P1k/8 w - - 4 64", "Execute the precise endgame technique with d3e4 to secure victory.", "Look for the key move d3e4.", listOf("d3e4", "h2g2", "e4e3")),
        RawLesson("Advanced Pawn Technique in King vs King", "8/8/3K4/4Pk2/p7/1pP5/1P6/8 b - - 1 45", "Execute the precise endgame technique with a4a3 to secure victory.", "Look for the key move a4a3.", listOf("a4a3", "e5e6", "a3b2", "e6e7", "b2b1q")),
        RawLesson("Master Technique in King vs King", "8/8/p7/1p2kp1p/4pp2/1PP2PP1/P3K2P/8 b - - 0 30", "Execute the precise endgame technique with f4g3 to secure victory.", "Look for the key move f4g3.", listOf("f4g3", "h2g3", "f5f4")),
        RawLesson("Pawn Endgame Technique in King vs King", "6k1/7p/6p1/5p2/5PP1/7P/1K6/8 b - - 0 42", "Execute the precise endgame technique with f5g4 to secure victory.", "Look for the key move f5g4.", listOf("f5g4", "h3g4", "h7h5", "g4h5", "g6h5")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/8/5pk1/1p5p/1P4p1/2P3PP/3K4/8 w - - 0 40", "Execute the precise endgame technique with h3g4 to secure victory.", "Look for the key move h3g4.", listOf("h3g4", "h5h4", "g3h4")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/8/2p3p1/p6p/Pk2Pp1P/3K1P2/8/8 w - - 2 42", "Execute the precise endgame technique with e4e5 to secure victory.", "Look for the key move e4e5.", listOf("e4e5", "b4c5", "d3e4")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/1p6/8/3kp2p/4p1pP/4K1P1/PP4P1/8 b - h3 0 36", "Execute the precise endgame technique with b7b5 to secure victory.", "Look for the key move b7b5.", listOf("b7b5", "a2a3", "d5c4", "e3e4", "c4b3")),
        RawLesson("Defensive Move Technique in King vs King", "8/pp6/8/2p2k1P/3p4/2P3K1/PP6/8 w - - 0 40", "Execute the precise endgame technique with c3d4 to secure victory.", "Look for the key move c3d4.", listOf("c3d4", "c5d4", "g3f3")),
        RawLesson("Master Technique in King vs King", "1K6/8/2k5/pp6/P1p5/2P5/1P6/8 w - - 0 48", "Execute the precise endgame technique with b8a7 to secure victory.", "Look for the key move b8a7.", listOf("b8a7", "b5b4", "a7a6")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/8/p5pp/2k5/2p3PP/2K2P2/P7/8 b - - 0 41", "Execute the precise endgame technique with g6g5 to secure victory.", "Look for the key move g6g5.", listOf("g6g5", "h4g5", "h6g5")),
        RawLesson("Master Technique in King vs King", "8/pp6/4k3/PP3p2/2P2K2/8/8/8 b - - 0 50", "Execute the precise endgame technique with e6d6 to secure victory.", "Look for the key move e6d6.", listOf("e6d6", "f4f5", "d6c5", "f5e6", "c5c4", "b5b6", "a7b6")),
        RawLesson("Deflection Technique in King vs King", "8/2k4p/p3P3/1p3p2/1P1Kp3/P7/6PP/8 w - - 0 37", "Execute the precise endgame technique with g2g4 to secure victory.", "Look for the key move g2g4.", listOf("g2g4", "f5g4", "d4e4")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/1k6/5p1p/1P4p1/4p3/7P/5PP1/5K2 w - - 0 41", "Execute the precise endgame technique with g2g4 to secure victory.", "Look for the key move g2g4.", listOf("g2g4", "f6f5", "g4f5")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/p7/5k2/8/3P1K1p/P7/8/8 b - - 3 46", "Execute the precise endgame technique with f6e6 to secure victory.", "Look for the key move f6e6.", listOf("f6e6", "f4g4", "e6d5", "a3a4", "d5d4")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/8/1pp1k1p1/4Pp1p/2PK1P1P/pP6/P7/8 w - - 2 36", "Execute the precise endgame technique with b3b4 to secure victory.", "Look for the key move b3b4.", listOf("b3b4", "e6e7", "b4b5", "c6b5", "c4b5")),
        RawLesson("Advanced Pawn Technique in King vs King", "8/8/p3k3/1p1pP3/5P2/1P3K2/8/8 b - - 1 53", "Execute the precise endgame technique with a6a5 to secure victory.", "Look for the key move a6a5.", listOf("a6a5", "f3e3", "a5a4", "b3a4", "b5a4", "e3d4", "a4a3", "d4c3", "d5d4", "c3d4", "a3a2")),
        RawLesson("Defensive Move Technique in King vs King", "8/6p1/8/pp2k1p1/2p2p2/2P2PPP/PP3K2/8 b - - 1 37", "Execute the precise endgame technique with f4g3 to secure victory.", "Look for the key move f4g3.", listOf("f4g3", "f2g3", "e5f5")),
        RawLesson("Defensive Move Technique in King vs King", "8/8/8/4k1pp/5p2/4KPPP/8/8 w - - 0 36", "Execute the precise endgame technique with g3f4 to secure victory.", "Look for the key move g3f4.", listOf("g3f4", "g5f4", "e3d3", "e5f6", "h3h4")),
        RawLesson("Defensive Move Technique in King vs King", "8/8/4pk2/p1p4p/Pp1pKP2/1P1P3P/2P5/8 b - - 0 38", "Execute the precise endgame technique with h5h4 to secure victory.", "Look for the key move h5h4.", listOf("h5h4", "f4f5", "e6e5", "e4d5", "f6f5", "d5c5", "f5f4")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/7K/pp3k2/2p4P/P7/2P5/8/8 b - - 1 44", "Execute the precise endgame technique with c5c4 to secure victory.", "Look for the key move c5c4.", listOf("c5c4", "h7g8", "f6g5", "g8f7", "b6b5", "a4b5", "a6b5", "f7e6", "b5b4")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/2p5/8/2pPk2p/8/3K3P/6P1/8 b - - 2 42", "Execute the precise endgame technique with h5h4 to secure victory.", "Look for the key move h5h4.", listOf("h5h4", "d3c4", "e5d6")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/2p5/3p4/ppk5/2P1K3/PP1P4/8/8 b - - 1 39", "Execute the precise endgame technique with b5c4 to secure victory.", "Look for the key move b5c4.", listOf("b5c4", "d3c4", "a5a4", "b3b4", "c5c4")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/4k3/2p3p1/1pP1ppP1/1P1P1P2/4K3/8/8 b - - 1 58", "Execute the precise endgame technique with e5f4 to secure victory.", "Look for the key move e5f4.", listOf("e5f4", "e3f4", "e7e6", "f4f3", "e6d5")),
        RawLesson("Master Technique in King vs King", "8/p7/1p2k1pp/5p2/P2P1P2/4K3/1P5P/8 b - - 0 37", "Execute the precise endgame technique with g6g5 to secure victory.", "Look for the key move g6g5.", listOf("g6g5", "f4g5", "h6g5")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/2k4p/2P1K1p1/5p2/5P2/8/7P/8 w - - 0 49", "Execute the precise endgame technique with e6d5 to secure victory.", "Look for the key move e6d5.", listOf("e6d5", "g6g5", "f4g5", "f5f4", "d5e4", "c7c6", "e4f4")),
        RawLesson("Defensive Move Technique in King vs King", "8/5ppp/8/5KPP/5P2/5k2/8/8 w - - 1 42", "Execute the precise endgame technique with h5h6 to secure victory.", "Look for the key move h5h6.", listOf("h5h6", "g7h6", "g5h6", "f3g3", "f5g5")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/8/2p2p2/p4P1p/Pk5P/3K2P1/8/8 b - - 2 39", "Execute the precise endgame technique with c6c5 to secure victory.", "Look for the key move c6c5.", listOf("c6c5", "d3e2", "c5c4", "g3g4", "h5g4")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/5k2/5Ppp/5pK1/7P/8/6P1/8 w - - 0 51", "Execute the precise endgame technique with g5h6 to secure victory.", "Look for the key move g5h6.", listOf("g5h6", "f7f6", "g2g3", "f6f7", "h6h7")),
        RawLesson("Master Technique in King vs King", "8/p7/1p3p2/4pP1p/kP2P2P/P7/1K6/8 b - - 7 45", "Execute the precise endgame technique with a7a6 to secure victory.", "Look for the key move a7a6.", listOf("a7a6", "b2c3", "a4a3")),
        RawLesson("Advanced Pawn Technique in King vs King", "8/8/8/ppp2kPp/P6P/5K2/8/8 w - - 0 44", "Execute the precise endgame technique with a4b5 to secure victory.", "Look for the key move a4b5.", listOf("a4b5", "a5a4", "b5b6", "a4a3", "b6b7", "a3a2", "b7b8q", "a2a1q", "b8c8", "f5g6", "c8g8")),
        RawLesson("Master Technique in King vs King", "8/1p3p2/4p1k1/P2pPpPp/1p1P3K/1P3P1P/8/8 b - - 0 43", "Execute the precise endgame technique with f5f4 to secure victory.", "Look for the key move f5f4.", listOf("f5f4", "a5a6", "f7f6", "e5f6", "b7a6")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/7p/2k1p1p1/K1p5/5PP1/1P5P/P7/8 b - - 0 34", "Execute the precise endgame technique with g6g5 to secure victory.", "Look for the key move g6g5.", listOf("g6g5", "f4f5", "e6f5")),
        RawLesson("Defensive Move Technique in King vs King", "8/2p5/5ppp/1k1P4/p4PPP/P1K5/8/8 w - - 0 40", "Execute the precise endgame technique with c3d4 to secure victory.", "Look for the key move c3d4.", listOf("c3d4", "c7c5", "d5c6", "b5c6", "d4c4")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/6pp/8/3k1P2/2p3PP/2K5/8/8 b - - 0 50", "Execute the precise endgame technique with d5e5 to secure victory.", "Look for the key move d5e5.", listOf("d5e5", "h4h5", "h7h6", "g4g5", "h6g5")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/p1k5/P1P2pp1/2K1p2p/5P1P/6P1/8/8 w - - 0 49", "Execute the precise endgame technique with c5d5 to secure victory.", "Look for the key move c5d5.", listOf("c5d5", "e5e4", "d5e4", "c7c6", "f4f5")),
        RawLesson("Defensive Move Technique in King vs King", "6k1/8/2p3p1/3pp2p/1P4PP/2P2P2/8/6K1 b - - 0 34", "Execute the precise endgame technique with g6g5 to secure victory.", "Look for the key move g6g5.", listOf("g6g5", "h4g5", "h5h4")),
        RawLesson("Master Technique in King vs King", "8/8/8/ppp1k1K1/3p4/P2P4/1PP5/8 b - - 0 45", "Execute the precise endgame technique with a5a4 to secure victory.", "Look for the key move a5a4.", listOf("a5a4", "g5g4", "c5c4")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/5ppp/8/4PpP1/2k1K2P/8/8/8 w - - 0 42", "Execute the precise endgame technique with e4f5 to secure victory.", "Look for the key move e4f5.", listOf("e4f5", "c4d5", "h4h5", "h7h6", "g5h6", "g7h6", "f5f6", "d5e4", "f6g7", "e4e5", "g7h6")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/1p6/7p/3k1pP1/1P6/3K1P2/6P1/8 b - - 0 39", "Execute the precise endgame technique with h6g5 to secure victory.", "Look for the key move h6g5.", listOf("h6g5", "d3c3", "d5e5", "c3c4", "e5f4", "c4c5", "f4g3")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/8/ppp1kpKp/4p2P/PPP5/3P4/8/8 w - - 3 39", "Execute the precise endgame technique with g6h6 to secure victory.", "Look for the key move g6h6.", listOf("g6h6", "e6f7", "b4b5", "a6b5", "c4b5", "c6c5", "a4a5")),
        RawLesson("Master Technique in King vs King", "4K3/5p2/5kpp/8/7P/6P1/5P2/8 w - - 0 56", "Execute the precise endgame technique with f2f3 to secure victory.", "Look for the key move f2f3.", listOf("f2f3", "f6g7", "e8e7")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/5p2/3k3p/p2Pp1pP/2p3P1/5K2/PP3P2/8 w - - 3 46", "Execute the precise endgame technique with f3e4 to secure victory.", "Look for the key move f3e4.", listOf("f3e4", "a5a4", "a2a3", "f7f6", "e4f5", "d6d5", "f5g6", "d5d4", "g6h6")),
        RawLesson("Pawn Endgame Technique in King vs King", "8/7p/1pk3p1/p2p1pP1/P4P1P/1PPK4/8/8 w - - 1 33", "Execute the precise endgame technique with d3d4 to secure victory.", "Look for the key move d3d4.", listOf("d3d4", "c6d6", "c3c4", "d5c4", "b3c4", "d6c6", "d4e5", "c6c5", "e5f6", "c5c4", "f6g7")),
        RawLesson("Defensive Move Technique in King vs King", "8/8/p3p3/3p2p1/1P1Ppk2/2P4P/5PK1/8 b - - 4 42", "Execute the precise endgame technique with e6e5 to secure victory.", "Look for the key move e6e5.", listOf("e6e5", "d4e5", "f4e5", "g2f1", "e5f5", "f1e1", "g5g4", "h3h4", "f5g6"))
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
                id = "e_king_vs_king_$level",
                title = "Level $level ($tier difficulty) • ${base.subtitle}",
                category = "King vs King",
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
