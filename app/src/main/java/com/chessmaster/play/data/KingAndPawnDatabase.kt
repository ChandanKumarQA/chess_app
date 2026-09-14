package com.chessmaster.play.data

import com.chessmaster.play.model.EndgameLesson

object KingAndPawnDatabase {

    private data class RawLesson(
        val subtitle: String,
        val fen: String,
        val explanation: String,
        val hint: String,
        val moves: List<String>
    )

    private val pool = listOf(
        RawLesson("Advanced Pawn Technique in King + Pawn", "8/8/1K6/P7/6kp/8/8/8 b - - 1 55", "Execute the precise endgame technique with h4h3 to secure victory.", "Look for the key move h4h3.", listOf("h4h3", "a5a6", "h3h2", "b6a7", "h2h1q")),
        RawLesson("Advanced Pawn Technique in King + Pawn", "8/1K6/8/6kp/8/8/P7/8 b - - 1 46", "Execute the precise endgame technique with h5h4 to secure victory.", "Look for the key move h5h4.", listOf("h5h4", "a2a4", "h4h3", "a4a5", "h3h2", "b7b8", "h2h1q")),
        RawLesson("Advanced Pawn Technique in King + Pawn", "8/8/1p2K3/p4p2/4k2P/1P6/P7/8 w - - 1 45", "Execute the precise endgame technique with h4h5 to secure victory.", "Look for the key move h4h5.", listOf("h4h5", "f5f4", "h5h6", "f4f3", "h6h7", "f3f2", "h7h8q")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/6K1/8/5k1p/7P/8/8/8 b - - 0 54", "Execute the precise endgame technique with f5g4 to secure victory.", "Look for the key move f5g4.", listOf("f5g4", "g7f6", "g4h4", "f6f5", "h4g3")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/p1p5/Pp1p3p/1P1Pk1P1/2P3P1/4K3/8 b - - 0 50", "Execute the precise endgame technique with h5g4 to secure victory.", "Look for the key move h5g4.", listOf("h5g4", "e2d2", "e4f3")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/p4k2/P2p3p/3P3P/5K2/8/8 b - - 5 48", "Execute the precise endgame technique with f6f5 to secure victory.", "Look for the key move f6f5.", listOf("f6f5", "f3e3", "f5g4")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/8/1p1k4/1P3Kp1/p5Pp/P6P/8 b - - 6 48", "Execute the precise endgame technique with d5c4 to secure victory.", "Look for the key move d5c4.", listOf("d5c4", "f4g4", "c4b4")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/6p1/5p1p/1k3P1P/3K1P2/8/8 w - - 4 41", "Execute the precise endgame technique with d3d4 to secure victory.", "Look for the key move d3d4.", listOf("d3d4", "b4b3", "d4e5", "b3c4", "e5f6")),
        RawLesson("Advanced Pawn Technique in King + Pawn", "6k1/5p2/1K3P2/6p1/P7/8/8/8 b - - 1 56", "Execute the precise endgame technique with g5g4 to secure victory.", "Look for the key move g5g4.", listOf("g5g4", "b6c5", "g4g3", "c5d4", "g3g2")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/8/8/1p5p/1P3P1k/4K3/8/8 b - - 0 61", "Execute the precise endgame technique with h4g4 to secure victory.", "Look for the key move h4g4.", listOf("h4g4", "e3e4", "h5h4")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/8/1p1K2p1/pP4k1/P5Pp/7P/8 b - - 1 44", "Execute the precise endgame technique with g4f3 to secure victory.", "Look for the key move g4f3.", listOf("g4f3", "d5c5", "f3g2")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/4P1k1/8/3K4/6P1/p7/8/8 b - - 0 75", "Execute the precise endgame technique with g7f7 to secure victory.", "Look for the key move g7f7.", listOf("g7f7", "e7e8q", "f7e8")),
        RawLesson("Advanced Pawn Technique in King + Pawn", "8/8/p7/6p1/P1P5/8/5K1k/8 b - - 0 50", "Execute the precise endgame technique with g5g4 to secure victory.", "Look for the key move g5g4.", listOf("g5g4", "c4c5", "g4g3", "f2f3", "g3g2")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/4k3/1p4pp/3P1p2/2p2P1P/2P1K3/8/8 w - - 0 49", "Execute the precise endgame technique with e3d4 to secure victory.", "Look for the key move e3d4.", listOf("e3d4", "e7d7", "d4c4")),
        RawLesson("Master Technique in King + Pawn", "8/8/8/p5p1/1p2k3/1P4KP/P7/8 w - - 1 40", "Execute the precise endgame technique with g3g4 to secure victory.", "Look for the key move g3g4.", listOf("g3g4", "e4e5", "g4g5")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/4k1p1/1p1p3p/pP3P1P/P1P2K2/8/8 b - - 4 52", "Execute the precise endgame technique with e6f5 to secure victory.", "Look for the key move e6f5.", listOf("e6f5", "f3e3", "f5g4")),
        RawLesson("Advanced Pawn Technique in King + Pawn", "8/8/8/1K6/8/pPk5/P7/8 b - - 2 53", "Execute the precise endgame technique with c3b2 to secure victory.", "Look for the key move c3b2.", listOf("c3b2", "b5c4", "b2a2", "c4c3", "a2b1", "b3b4", "a3a2")),
        RawLesson("Advanced Pawn Technique in King + Pawn", "8/8/p7/2K1p3/1pP5/1P1k4/P7/8 b - - 0 42", "Execute the precise endgame technique with e5e4 to secure victory.", "Look for the key move e5e4.", listOf("e5e4", "c5b6", "e4e3", "c4c5", "e3e2")),
        RawLesson("Master Technique in King + Pawn", "8/8/5p2/7k/4KP2/8/8/8 w - - 1 66", "Execute the precise endgame technique with e4f5 to secure victory.", "Look for the key move e4f5.", listOf("e4f5", "h5h6", "f5f6")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/7p/pk3p1P/3K1Pp1/1P4P1/8/8 b - - 6 52", "Execute the precise endgame technique with b5b4 to secure victory.", "Look for the key move b5b4.", listOf("b5b4", "d4e5", "b4b3")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/2p5/1p1p4/p2Pk1p1/P1P1P1P1/3K4/8/8 b - - 0 36", "Execute the precise endgame technique with e5f4 to secure victory.", "Look for the key move e5f4.", listOf("e5f4", "d3d4", "f4g4")),
        RawLesson("Advanced Pawn Technique in King + Pawn", "8/8/8/4p3/PK5p/5k1P/8/8 b - - 0 41", "Execute the precise endgame technique with e5e4 to secure victory.", "Look for the key move e5e4.", listOf("e5e4", "a4a5", "e4e3", "a5a6", "e3e2")),
        RawLesson("Attraction Technique in King + Pawn", "8/8/5k2/1p3pp1/p1p2P1p/P1P1KP1P/1P6/8 w - - 0 47", "Execute the precise endgame technique with f4g5 to secure victory.", "Look for the key move f4g5.", listOf("f4g5", "f6g5", "f3f4", "g5g6", "e3d4")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/8/8/p4p2/2pPkP2/2K5/1P6/8 w - - 1 38", "Execute the precise endgame technique with c3c4 to secure victory.", "Look for the key move c3c4.", listOf("c3c4", "e4f4", "d4d5", "f4e5", "c4c5", "f5f4", "d5d6", "e5e6", "c5c6")),
        RawLesson("Advanced Pawn Technique in King + Pawn", "8/8/5p2/1p5p/2k1KP2/P7/7P/8 b - - 0 36", "Execute the precise endgame technique with c4b3 to secure victory.", "Look for the key move c4b3.", listOf("c4b3", "e4f5", "b3a3", "f5f6", "b5b4", "f6e7", "b4b3", "f4f5", "b3b2", "f5f6", "b2b1q")),
        RawLesson("Mate Technique in King + Pawn", "8/8/8/7p/5K1k/7p/6P1/8 w - - 0 55", "Execute the precise endgame technique with g2g3 to secure victory.", "Look for the key move g2g3.", listOf("g2g3")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/5p1p/3p1kp1/3P4/5PPP/8/3K4/8 b - - 0 44", "Execute the precise endgame technique with h7h5 to secure victory.", "Look for the key move h7h5.", listOf("h7h5", "g4g5", "f6f5")),
        RawLesson("Advanced Pawn Technique in King + Pawn", "8/7K/4k3/6P1/pp6/4P3/P7/8 b - - 1 51", "Execute the precise endgame technique with b4b3 to secure victory.", "Look for the key move b4b3.", listOf("b4b3", "a2b3", "a4b3", "g5g6", "b3b2", "g6g7", "b2b1q")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/6k1/4p3/6Kp/3P4/5P2/8/8 w - - 1 45", "Execute the precise endgame technique with g5h5 to secure victory.", "Look for the key move g5h5.", listOf("g5h5", "g7f6", "h5g4")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/2p2p2/3p2kp/p2P4/P1P1KpP1/7P/8/8 w - - 0 38", "Execute the precise endgame technique with e4f4 to secure victory.", "Look for the key move e4f4.", listOf("e4f4", "f7f6", "h3h4")),
        RawLesson("Deflection Technique in King + Pawn", "8/8/2p1k1p1/p1KpP3/P4PP1/8/8/8 b - - 1 39", "Execute the precise endgame technique with g6g5 to secure victory.", "Look for the key move g6g5.", listOf("g6g5", "f4g5", "e6e5")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/7p/1p5K/2P5/p2Pk3/P7/8/8 b - - 0 41", "Execute the precise endgame technique with b6c5 to secure victory.", "Look for the key move b6c5.", listOf("b6c5", "d4c5", "e4d5", "c5c6", "d5c6")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/p4pp1/7p/4k3/1p1pP1P1/1Pp2PP1/P1PK4/8 w - - 0 32", "Execute the precise endgame technique with d2d3 to secure victory.", "Look for the key move d2d3.", listOf("d2d3", "g7g5", "d3c4")),
        RawLesson("Advanced Pawn Technique in King + Pawn", "8/8/2p5/P7/1k6/3K1p2/8/8 w - - 0 51", "Execute the precise endgame technique with a5a6 to secure victory.", "Look for the key move a5a6.", listOf("a5a6", "f3f2", "d3e2", "b4c5", "a6a7")),
        RawLesson("Master Technique in King + Pawn", "8/5p2/4k3/6p1/4P1P1/3K4/8/8 b - - 6 56", "Execute the precise endgame technique with e6e5 to secure victory.", "Look for the key move e6e5.", listOf("e6e5", "d3e3", "f7f6", "e3f3", "e5d4")),
        RawLesson("Master Technique in King + Pawn", "8/8/1Pk2p2/4p2p/2K5/5PP1/8/8 b - - 0 43", "Execute the precise endgame technique with f6f5 to secure victory.", "Look for the key move f6f5.", listOf("f6f5", "b6b7", "c6b7")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/8/6p1/3kP3/8/4K3/8/8 b - - 2 56", "Execute the precise endgame technique with d5e5 to secure victory.", "Look for the key move d5e5.", listOf("d5e5", "e3f3", "e5f5", "f3g3", "f5g5")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/8/pp6/4k1p1/P1P3P1/1P2K3/8 b - - 3 53", "Execute the precise endgame technique with a5a4 to secure victory.", "Look for the key move a5a4.", listOf("a5a4", "b2b3", "a4b3")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/8/p7/8/3K4/1P6/k7/8 b - - 1 47", "Execute the precise endgame technique with a2b3 to secure victory.", "Look for the key move a2b3.", listOf("a2b3", "d4d3", "a6a5", "d3d2", "b3b2")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/p2p4/1ppPpk2/2P5/PP1P1K2/8/8 w - - 0 45", "Execute the precise endgame technique with c4b5 to secure victory.", "Look for the key move c4b5.", listOf("c4b5", "a6b5", "a3a4", "b5a4", "b3a4")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "4k3/4P2p/5K2/7P/ppp3P1/8/PPP5/8 b - - 0 36", "Execute the precise endgame technique with b4b3 to secure victory.", "Look for the key move b4b3.", listOf("b4b3", "a2b3", "c4c3", "b2c3", "a4a3")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/1k3p2/1P3P1p/8/4K3/8/8/8 b - - 4 56", "Execute the precise endgame technique with b7b6 to secure victory.", "Look for the key move b7b6.", listOf("b7b6", "e4d5", "b6c7")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/8/8/3pkPK1/4p2P/8/8/8 w - - 1 48", "Execute the precise endgame technique with f5f6 to secure victory.", "Look for the key move f5f6.", listOf("f5f6", "e5e6", "g5g6")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/6K1/1p4p1/p4k1p/P6P/6P1/1P6/8 b - - 7 49", "Execute the precise endgame technique with g6g5 to secure victory.", "Look for the key move g6g5.", listOf("g6g5", "h4g5", "f5g5")),
        RawLesson("Advanced Pawn Technique in King + Pawn", "8/pp6/2p5/PP3pp1/3k3p/4p2P/7P/4K3 w - - 0 43", "Execute the precise endgame technique with a5a6 to secure victory.", "Look for the key move a5a6.", listOf("a5a6", "b7a6", "b5c6", "d4c5", "c6c7")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/2p5/1kP2p2/1P1pp3/1K1P1P2/8/8 w - - 0 37", "Execute the precise endgame technique with d3e4 to secure victory.", "Look for the key move d3e4.", listOf("d3e4", "f5e4", "f3e4")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/6p1/7p/6P1/4KPk1/8/8/8 w - - 0 52", "Execute the precise endgame technique with g5h6 to secure victory.", "Look for the key move g5h6.", listOf("g5h6", "g7h6", "f4f5", "g4g5", "e4e5", "h6h5", "f5f6")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/8/8/pp1p1k2/6pP/PPP3K1/8/8 b - - 0 41", "Execute the precise endgame technique with b5b4 to secure victory.", "Look for the key move b5b4.", listOf("b5b4", "c3b4", "a5b4", "a3a4", "d5d4")),
        RawLesson("Attraction Technique in King + Pawn", "8/8/6k1/p1p2ppp/2P2P2/5KPP/P7/8 w - - 0 38", "Execute the precise endgame technique with f4g5 to secure victory.", "Look for the key move f4g5.", listOf("f4g5", "g6g5", "h3h4")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "6k1/5p2/p4P2/1p1pP3/2pP2P1/P7/1P6/7K b - - 0 50", "Execute the precise endgame technique with a6a5 to secure victory.", "Look for the key move a6a5.", listOf("a6a5", "h1g2", "b5b4", "a3b4", "a5b4")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/p2p1ppp/2k5/2p2K1P/P1P2PP1/1P6/8 w - - 1 40", "Execute the precise endgame technique with h4h5 to secure victory.", "Look for the key move h4h5.", listOf("h4h5", "g6h5", "f4f5")),
        RawLesson("Advanced Pawn Technique in King + Pawn", "8/8/6p1/p2p4/2k4P/1P6/P2K4/8 b - - 0 46", "Execute the precise endgame technique with c4b4 to secure victory.", "Look for the key move c4b4.", listOf("c4b4", "d2c2", "b4a3", "c2b1", "d5d4", "b1a1", "d4d3", "a1b1", "d3d2", "b1c2", "a3a2")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/1ppk3p/p5pP/P1PK2P1/1P6/8/8 w - - 2 40", "Execute the precise endgame technique with c4c5 to secure victory.", "Look for the key move c4c5.", listOf("c4c5", "b6c5", "d4c4", "d6e7", "c4c5")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/p1k2pp1/4p1p1/KP2P3/5PPP/8/8 w - - 5 51", "Execute the precise endgame technique with a4a5 to secure victory.", "Look for the key move a4a5.", listOf("a4a5", "c6b7", "b4b5")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/5p2/4pk2/8/3p1P2/5P1P/4K3/8 b - - 1 56", "Execute the precise endgame technique with f6f5 to secure victory.", "Look for the key move f6f5.", listOf("f6f5", "e2d3", "f5f4", "h3h4", "e6e5")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/7p/5p2/p1k3p1/2P2P2/P2K2P1/8/8 w - - 0 47", "Execute the precise endgame technique with f4g5 to secure victory.", "Look for the key move f4g5.", listOf("f4g5", "f6g5", "g3g4", "a5a4", "d3c3")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/7p/7p/4p2k/4P2P/6P1/6K1/8 b - - 2 48", "Execute the precise endgame technique with h5g4 to secure victory.", "Look for the key move h5g4.", listOf("h5g4", "g2f2", "g4h3", "f2f3", "h6h5", "f3f2", "h3h2")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/p6K/1p6/2k5/8/2P5/PP6/8 b - - 0 53", "Execute the precise endgame technique with c5c4 to secure victory.", "Look for the key move c5c4.", listOf("c5c4", "h7g6", "c4d3", "c3c4", "d3c4")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/8/p2k2pp/1p1p1p2/PP1K2P1/1P3P2/7P/8 w - - 0 37", "Execute the precise endgame technique with g4f5 to secure victory.", "Look for the key move g4f5.", listOf("g4f5", "g6f5", "f3f4")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/6p1/8/3K2k1/5pP1/5P2/8/8 b - - 0 43", "Execute the precise endgame technique with g5h4 to secure victory.", "Look for the key move g5h4.", listOf("g5h4", "g4g5", "h4g3", "g5g6", "g3f3")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/8/2p2k2/p5p1/Pp1P1pPP/1P3K2/8/8 b - - 0 37", "Execute the precise endgame technique with g5h4 to secure victory.", "Look for the key move g5h4.", listOf("g5h4", "f3f4", "f6g6")),
        RawLesson("Master Technique in King + Pawn", "8/1p6/p7/P2k1pp1/8/1P2P2P/4K3/8 b - - 2 39", "Execute the precise endgame technique with d5e4 to secure victory.", "Look for the key move d5e4.", listOf("d5e4", "e2f2", "e4d3")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/8/6p1/K1pP1k2/2P5/8/8/8 b - - 0 46", "Execute the precise endgame technique with f5e5 to secure victory.", "Look for the key move f5e5.", listOf("f5e5", "a5b5", "e5d6", "b5a4", "g6g5", "a4b3", "g5g4")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/6p1/5p2/2k1pP1P/6P1/4K3/8 b - - 4 55", "Execute the precise endgame technique with c4d4 to secure victory.", "Look for the key move c4d4.", listOf("c4d4", "g3g4", "f5g4", "f4f5", "g6f5", "h4h5", "d4e5", "h5h6", "e5f6", "h6h7", "f6g7", "h7h8r", "g7h8")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/5p2/3k2p1/p1p1p1P1/2P1KP2/1P6/8/8 w - - 0 40", "Execute the precise endgame technique with f4f5 to secure victory.", "Look for the key move f4f5.", listOf("f4f5", "a5a4", "b3a4", "g6f5", "e4f5")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/p1p2p1p/4k1p1/PPp5/3KP1P1/6P1/8 w - - 0 35", "Execute the precise endgame technique with d3c4 to secure victory.", "Look for the key move d3c4.", listOf("d3c4", "e5d6", "g3g4", "f6f5", "g4f5")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/1k6/1P1Kp3/5p1p/6p1/5P1P/5P2/8 w - - 0 49", "Execute the precise endgame technique with f3g4 to secure victory.", "Look for the key move f3g4.", listOf("f3g4", "f5g4", "h3h4")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/7p/4kp2/1p1p2p1/1P3P2/2PK2P1/7P/8 w - - 0 40", "Execute the precise endgame technique with f4g5 to secure victory.", "Look for the key move f4g5.", listOf("f4g5", "f6g5", "d3d4", "g5g4", "d4c5")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/p7/4pp2/p2pPk2/P4P1p/1P2PK2/6P1/8 w - - 0 36", "Execute the precise endgame technique with e5f6 to secure victory.", "Look for the key move e5f6.", listOf("e5f6", "f5f6", "f3g4", "e6e5", "g4h4")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/1p6/2p2k2/8/p4P2/P3K3/1P6/8 b - - 2 50", "Execute the precise endgame technique with f6f5 to secure victory.", "Look for the key move f6f5.", listOf("f6f5", "e3d4", "f5f4")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/8/8/pp1p1k2/2pP2p1/P1P3K1/1P4P1/8 b - - 1 36", "Execute the precise endgame technique with f5g5 to secure victory.", "Look for the key move f5g5.", listOf("f5g5", "g3f2", "g5f4")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/7p/1k6/2p3p1/4p1P1/1PK1Pp1P/5P2/8 w - - 1 47", "Execute the precise endgame technique with c3c4 to secure victory.", "Look for the key move c3c4.", listOf("c3c4", "b6c6", "b3b4", "c5b4", "c4b4", "c6d5", "b4b5")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/8/4k2p/1p1pp1p1/pPpP1P2/P1P1K1PP/8/8 b - - 0 48", "Execute the precise endgame technique with g5f4 to secure victory.", "Look for the key move g5f4.", listOf("g5f4", "g3f4", "e5e4")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/p7/1p6/8/PPK1k3/8/8 w - - 1 58", "Execute the precise endgame technique with c3b4 to secure victory.", "Look for the key move c3b4.", listOf("c3b4", "e3d2", "b4a5", "b5b4", "a5b4")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/8/8/2pkpP1p/p1p4P/P1P1K3/1P6/8 w - - 0 35", "Execute the precise endgame technique with e3f3 to secure victory.", "Look for the key move e3f3.", listOf("e3f3", "e5e4", "f3f4", "e4e3", "f4e3", "d5e5", "f5f6", "e5f6", "e3f4")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/8/4kpp1/8/5PP1/6K1/8/8 b - - 2 52", "Execute the precise endgame technique with e6d5 to secure victory.", "Look for the key move e6d5.", listOf("e6d5", "f4f5", "g6g5", "g3f3", "d5d4")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/pp1k1p2/3P1ppp/PP1K1P2/6P1/6P1/8 w - - 0 36", "Execute the precise endgame technique with d4c4 to secure victory.", "Look for the key move d4c4.", listOf("d4c4", "b6b5", "a4b5", "a6b5", "c4b5")),
        RawLesson("Advanced Pawn Technique in King + Pawn", "8/8/2p3p1/p1P2p1p/kPK2P1P/6P1/8/8 w - - 0 42", "Execute the precise endgame technique with b4b5 to secure victory.", "Look for the key move b4b5.", listOf("b4b5", "c6b5", "c4d3", "b5b4", "c5c6", "b4b3", "c6c7", "b3b2", "d3c2")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/1p2k2p/p3p3/PP2K2P/6P1/8/8 w - - 0 47", "Execute the precise endgame technique with b4a5 to secure victory.", "Look for the key move b4a5.", listOf("b4a5", "b6a5", "g3g4", "e6f6", "e4d5")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/5p2/k1p2p1p/2P1p2P/2K1P1P1/5P2/8/8 w - - 2 44", "Execute the precise endgame technique with f3f4 to secure victory.", "Look for the key move f3f4.", listOf("f3f4", "e5f4", "e4e5", "f4f3", "c4d3", "f6e5", "g4g5")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/8/5k1p/5pP1/6PK/7P/8/8 b - - 0 61", "Execute the precise endgame technique with h6g5 to secure victory.", "Look for the key move h6g5.", listOf("h6g5", "h4g3", "f5f4", "g3f2", "f6e6")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/8/p1ppk1pp/8/PPP2KPP/8/8 b - - 2 33", "Execute the precise endgame technique with d5d4 to secure victory.", "Look for the key move d5d4.", listOf("d5d4", "f3e2", "d4c3")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/8/3p1p1p/1p1k2p1/1P4PP/2PK2P1/8/8 w - - 2 36", "Execute the precise endgame technique with h4h5 to secure victory.", "Look for the key move h4h5.", listOf("h4h5", "d5e5", "c3c4", "b5c4", "d3c4", "d6d5", "c4d3")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/6k1/6Pp/p1p1pP2/PpPp2K1/1P1P4/8/8 b - - 1 66", "Execute the precise endgame technique with e5e4 to secure victory.", "Look for the key move e5e4.", listOf("e5e4", "d3e4", "d4d3", "g4f3", "h6h5", "f3e3", "h5h4")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/1p6/p7/4k2p/P2p1p2/1P1K1P1P/6P1/8 w - - 0 37", "Execute the precise endgame technique with a4a5 to secure victory.", "Look for the key move a4a5.", listOf("a4a5", "e5d5", "h3h4", "d5c5", "d3e4", "d4d3", "e4d3")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/2p3p1/2k1pp1p/1p2P3/1PPK1PP1/8/7P/8 b - - 1 29", "Execute the precise endgame technique with f6e5 to secure victory.", "Look for the key move f6e5.", listOf("f6e5", "f4e5", "b5c4", "d4c4", "c6b6")),
        RawLesson("Master Technique in King + Pawn", "8/6p1/4pp2/1p2P2k/p4P2/P1P2KP1/8/8 b - - 3 44", "Execute the precise endgame technique with f6e5 to secure victory.", "Look for the key move f6e5.", listOf("f6e5", "f4e5", "g7g5", "f3e2", "h5g4", "e2f2", "g4f5")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/5p1p/p4k2/1p1K2p1/2pP2P1/2P4P/P7/8 w - - 2 33", "Execute the precise endgame technique with a2a3 to secure victory.", "Look for the key move a2a3.", listOf("a2a3", "a6a5", "d5c5")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/1p1k2pp/p2p4/3K1PPP/1PP5/8/8 w - - 1 38", "Execute the precise endgame technique with c3c4 to secure victory.", "Look for the key move c3c4.", listOf("c3c4", "d5c4", "d4c4")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/8/p1pp4/4kPp1/1K4P1/P1P5/8 w - - 0 36", "Execute the precise endgame technique with a2a3 to secure victory.", "Look for the key move a2a3.", listOf("a2a3", "e4f5", "b3a4")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/2p5/p2k1p2/2p1p1p1/1PP1P1P1/P2K1P2/8/8 b - - 1 40", "Execute the precise endgame technique with c5b4 to secure victory.", "Look for the key move c5b4.", listOf("c5b4", "a3b4", "d6c6", "d3c3", "c6b6", "b4b5", "a6b5")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/6p1/pp5p/2p5/P1P1K1Pk/1P5P/8/8 b - - 0 49", "Execute the precise endgame technique with a6a5 to secure victory.", "Look for the key move a6a5.", listOf("a6a5", "e4d5", "h4h3", "d5c6", "h3g4", "c6b6", "h6h5")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/pp1k4/2pp4/2PPp2p/1PK1P2P/P7/8 w - - 0 35", "Execute the precise endgame technique with d4c5 to secure victory.", "Look for the key move d4c5.", listOf("d4c5", "b6c5", "c4d5", "d6d5", "b3b4")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/8/5k1p/3K1Pp1/6P1/8/8 b - - 1 65", "Execute the precise endgame technique with h5h4 to secure victory.", "Look for the key move h5h4.", listOf("h5h4", "g3h4", "g4g3", "d4e3", "f5g4", "f4f5", "g4h3")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/6p1/2pk1p2/p1p2P2/P3KPP1/1P6/8/8 w - - 0 32", "Execute the precise endgame technique with e4d3 to secure victory.", "Look for the key move e4d3.", listOf("e4d3", "d6d5", "g4g5", "c5c4", "b3c4", "d5c5", "d3c3")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/1p4p1/p2p4/3k4/1P3PP1/P2K4/8 b - - 0 36", "Execute the precise endgame technique with b6b5 to secure victory.", "Look for the key move b6b5.", listOf("b6b5", "a2a3", "b5b4", "a3a4", "g6g5", "d2e2", "d4c3")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/7p/K7/1p2pkp1/8/1P6/P5PP/8 w - - 0 35", "Execute the precise endgame technique with a6b5 to secure victory.", "Look for the key move a6b5.", listOf("a6b5", "e5e4", "b5c4", "f5f4", "b3b4")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/8/8/1k1K1ppp/4p3/4P1P1/7P/8 w - - 2 41", "Execute the precise endgame technique with d5d4 to secure victory.", "Look for the key move d5d4.", listOf("d5d4", "b5b4", "h2h4", "g5h4", "g3h4", "f5f4", "e3f4")),
        RawLesson("Pawn Endgame Technique in King + Pawn", "8/1p6/8/p5pp/Pk4P1/1P5P/2K5/8 b - - 0 41", "Execute the precise endgame technique with h5h4 to secure victory.", "Look for the key move h5h4.", listOf("h5h4", "c2b2", "b4c5", "b2c3", "c5d5", "b3b4", "a5b4")),
        RawLesson("Defensive Move Technique in King + Pawn", "8/8/p7/1ppp2k1/4p1P1/P2P3K/1PP4P/8 b - - 0 41", "Execute the precise endgame technique with e4d3 to secure victory.", "Look for the key move e4d3.", listOf("e4d3", "c2d3", "b5b4", "a3b4", "c5b4", "h3g3", "a6a5"))
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
                id = "e_king_and_pawn_$level",
                title = "Level $level ($tier difficulty) • ${base.subtitle}",
                category = "King + Pawn",
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
