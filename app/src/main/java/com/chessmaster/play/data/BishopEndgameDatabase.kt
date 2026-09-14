package com.chessmaster.play.data

import com.chessmaster.play.model.EndgameLesson

object BishopEndgameDatabase {

    private data class RawLesson(
        val subtitle: String,
        val fen: String,
        val explanation: String,
        val hint: String,
        val moves: List<String>
    )

    private val pool = listOf(
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/8/8/3k4/p3pp1p/P1P4P/4K1B1/8 b - - 1 60", "Execute the precise endgame technique with f4f3 to secure victory.", "Look for the key move f4f3.", listOf("f4f3", "g2f3", "e4f3")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "2B4k/p5p1/1b1B4/1b5p/5P2/6P1/P6P/7K b - - 0 27", "Execute the precise endgame technique with b5c6 to secure victory.", "Look for the key move b5c6.", listOf("b5c6")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "8/8/8/KP6/5p1P/5k2/4bP2/8 w - - 1 50", "Execute the precise endgame technique with b5b6 to secure victory.", "Look for the key move b5b6.", listOf("b5b6", "e2d3", "b6b7")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "8/6B1/8/1p4pp/1Pp3P1/5P1k/4K2b/8 w - - 0 52", "Execute the precise endgame technique with g4h5 to secure victory.", "Look for the key move g4h5.", listOf("g4h5", "h3h4", "h5h6", "h4h5", "h6h7")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/1p4k1/1pp4p/1P1b2p1/P1K1Bp2/5P1P/6P1/8 w - - 0 44", "Execute the precise endgame technique with e4d5 to secure victory.", "Look for the key move e4d5.", listOf("e4d5", "c6d5", "c4d5")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "4k3/4bppp/pp6/4P3/P2PP3/4K1P1/7P/2B5 b - - 4 28", "Execute the precise endgame technique with e7g5 to secure victory.", "Look for the key move e7g5.", listOf("e7g5", "e3e2", "g5c1")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/2B5/p1p1kPpp/3p2P1/1P1P1K2/P7/8/7b w - - 0 54", "Execute the precise endgame technique with g5h6 to secure victory.", "Look for the key move g5h6.", listOf("g5h6", "g6g5", "f4g5")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "8/8/5pK1/4kP2/4b1BP/4p1P1/1P6/8 b - - 2 48", "Execute the precise endgame technique with e4f5 to secure victory.", "Look for the key move e4f5.", listOf("e4f5", "g4f5", "e3e2", "h4h5", "e2e1q")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "8/2Pb4/8/Pp6/5p1p/2k2K1P/6P1/8 w - - 0 51", "Execute the precise endgame technique with a5a6 to secure victory.", "Look for the key move a5a6.", listOf("a5a6", "d7c8", "a6a7")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/6k1/2bb2pp/4pP2/6P1/3BBPKP/8/8 b - - 0 38", "Execute the precise endgame technique with e5e4 to secure victory.", "Look for the key move e5e4.", listOf("e5e4", "f3f4", "e4d3")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "8/8/1KpB4/Pb6/1P5p/8/6k1/8 w - - 4 56", "Execute the precise endgame technique with a5a6 to secure victory.", "Look for the key move a5a6.", listOf("a5a6", "g2f3", "a6a7")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/7p/8/5PK1/5BP1/8/2p1b3/3k4 w - - 7 55", "Execute the precise endgame technique with f5f6 to secure victory.", "Look for the key move f5f6.", listOf("f5f6", "c2c1q", "f4c1")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/p7/1b6/1Kp5/2P2pk1/1P3P2/7B/8 b - - 0 37", "Execute the precise endgame technique with g4f3 to secure victory.", "Look for the key move g4f3.", listOf("g4f3", "b3b4", "c5b4")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/1b4p1/1k3pPp/4K2P/4PP2/8/8/8 w - - 0 47", "Execute the precise endgame technique with e5e6 to secure victory.", "Look for the key move e5e6.", listOf("e5e6", "b7e4", "e6f7")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "8/8/2B2p1p/P4Pp1/3p2P1/1b1Pb1kP/8/4K3 w - - 1 51", "Execute the precise endgame technique with a5a6 to secure victory.", "Look for the key move a5a6.", listOf("a5a6", "b3c4", "a6a7")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "3b4/3P1k2/2pB4/1p1bK1p1/2p3B1/2P5/P4P2/8 b - - 3 47", "Execute the precise endgame technique with d8f6 to secure victory.", "Look for the key move d8f6.", listOf("d8f6", "e5f5", "d5e6", "f5e4", "e6g4")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/6k1/3p2p1/p2P3p/4P3/3B2K1/8/8 b - - 1 46", "Execute the precise endgame technique with a5a4 to secure victory.", "Look for the key move a5a4.", listOf("a5a4", "d3c2", "a4a3")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "8/3P4/8/2k2pbP/8/5K2/8/8 w - - 0 52", "Execute the precise endgame technique with h5h6 to secure victory.", "Look for the key move h5h6.", listOf("h5h6", "c5c6", "h6h7", "c6d7", "h7h8q")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "1bK5/pP3p2/B7/1P4p1/6k1/8/7p/8 w - - 0 44", "Execute the precise endgame technique with c8b8 to secure victory.", "Look for the key move c8b8.", listOf("c8b8", "h2h1q", "b8a7", "h1d1", "b7b8q")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/4kp2/1b4p1/p1pBP2p/1pK1PP1P/1P4Pb/P7/2B5 b - - 7 38", "Execute the precise endgame technique with h3f1 to secure victory.", "Look for the key move h3f1.", listOf("h3f1")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/8/8/7p/p1b4k/P6P/2p3P1/2B3K1 w - - 8 47", "Execute the precise endgame technique with g1h2 to secure victory.", "Look for the key move g1h2.", listOf("g1h2", "c4f1", "g2g3")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/8/1k4p1/1P2Bp1p/2K5/4b2P/6P1/8 w - - 2 43", "Execute the precise endgame technique with e5d4 to secure victory.", "Look for the key move e5d4.", listOf("e5d4", "e3d4", "c4d4", "g6g5", "d4e5")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "4b3/2k5/2P5/2K1p1p1/4BpP1/5P2/8/8 w - - 3 53", "Execute the precise endgame technique with c5d5 to secure victory.", "Look for the key move c5d5.", listOf("c5d5", "e8c6", "d5e5")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "4b1B1/5pp1/1k2p2p/pp2P3/1P1K1P1P/P7/5P2/8 w - - 0 37", "Execute the precise endgame technique with b4a5 to secure victory.", "Look for the key move b4a5.", listOf("b4a5", "b6a5", "d4c5")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/4Kbk1/p7/6Pp/4P2P/P1P5/8/8 w - - 7 58", "Execute the precise endgame technique with e4e5 to secure victory.", "Look for the key move e4e5.", listOf("e4e5", "f7c4", "e5e6")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "7k/1p6/4p2p/pPPp4/3PP3/4b1P1/2B5/7K w - - 1 35", "Execute the precise endgame technique with c5c6 to secure victory.", "Look for the key move c5c6.", listOf("c5c6", "b7c6", "b5c6")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "3B4/3k1p2/bP4p1/3pPpKp/3P3P/6P1/8/8 b - - 3 58", "Execute the precise endgame technique with d7d8 to secure victory.", "Look for the key move d7d8.", listOf("d7d8", "g5f6", "d8e8", "e5e6", "f7e6")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/8/3k1K2/p1pBp1p1/1pP3P1/1P3P2/Pb6/8 b - - 0 52", "Execute the precise endgame technique with e5e4 to secure victory.", "Look for the key move e5e4.", listOf("e5e4", "f6f7", "e4e3")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "3b4/8/1pP1k3/P7/6Kp/7P/8/8 w - - 0 46", "Execute the precise endgame technique with a5a6 to secure victory.", "Look for the key move a5a6.", listOf("a5a6", "e6d6", "a6a7")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "2b1k3/2P2p2/3K1P2/P6p/5P1P/8/8/8 w - - 1 47", "Execute the precise endgame technique with d6c6 to secure victory.", "Look for the key move d6c6.", listOf("d6c6", "c8a6", "c6b6", "e8d7", "b6a6")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/8/P4k2/2b3p1/6Pp/4p2P/4pBK1/8 w - - 0 58", "Execute the precise endgame technique with f2e1 to secure victory.", "Look for the key move f2e1.", listOf("f2e1", "f6e5", "g2f3")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/8/p1p5/2B1k2p/3p1p2/1P2b2P/P1P1K1P1/8 b - - 0 37", "Execute the precise endgame technique with d4d3 to secure victory.", "Look for the key move d4d3.", listOf("d4d3", "e2d3", "e3c5")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/8/1p2p3/bk1p1p2/p2P1PpB/4P1P1/PP2K3/8 b - - 5 43", "Execute the precise endgame technique with b5c4 to secure victory.", "Look for the key move b5c4.", listOf("b5c4", "h4d8", "a4a3")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/3B2pp/p5k1/6P1/1ppp1K2/8/1P6/8 w - - 0 39", "Execute the precise endgame technique with d7e8 to secure victory.", "Look for the key move d7e8.", listOf("d7e8")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/4kpp1/4b2p/2PB4/1K6/p5P1/5PP1/8 w - - 7 45", "Execute the precise endgame technique with d5e6 to secure victory.", "Look for the key move d5e6.", listOf("d5e6", "e7e6", "b4a3")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/1p6/p1b5/4k2p/1PB1p2P/P3P1K1/8/8 w - - 3 36", "Execute the precise endgame technique with c4f7 to secure victory.", "Look for the key move c4f7.", listOf("c4f7", "c6d7", "f7h5")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "3k4/p2b4/2p5/1p1p1Bp1/3P2P1/P1P3K1/2P5/8 b - - 2 34", "Execute the precise endgame technique with d7f5 to secure victory.", "Look for the key move d7f5.", listOf("d7f5", "g4f5", "d8e7")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "3b4/3P4/8/1P3p2/2K4k/8/8/8 w - - 0 51", "Execute the precise endgame technique with c4c5 to secure victory.", "Look for the key move c4c5.", listOf("c4c5", "f5f4", "b5b6", "f4f3", "b6b7", "f3f2", "b7b8q")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "8/3K4/B4k2/8/1P4p1/5p2/P7/8 b - - 0 46", "Execute the precise endgame technique with g4g3 to secure victory.", "Look for the key move g4g3.", listOf("g4g3", "b4b5", "g3g2", "b5b6", "g2g1q")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/8/PpB5/2k4p/P4K1P/1P6/4b3/8 b - - 0 52", "Execute the precise endgame technique with c5c6 to secure victory.", "Look for the key move c5c6.", listOf("c5c6", "a6a7", "c6b7")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/1p2k2p/p3P1p1/2PP4/2K1b2P/8/1P3P2/8 w - - 1 36", "Execute the precise endgame technique with c4d4 to secure victory.", "Look for the key move c4d4.", listOf("c4d4", "e4b1", "d4e5")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "8/4k2p/4p2P/2Pb1pP1/3K4/8/8/8 w - f6 0 39", "Execute the precise endgame technique with g5g6 to secure victory.", "Look for the key move g5g6.", listOf("g5g6", "h7g6", "h6h7", "e7f6", "h7h8q")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/8/8/6p1/2pBp3/1p4k1/1P2K3/8 w - - 3 62", "Execute the precise endgame technique with e2e3 to secure victory.", "Look for the key move e2e3.", listOf("e2e3", "g3h3", "e3e4", "c4c3", "d4c3")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "8/8/1B6/1P6/2K1kp2/6p1/8/8 b - - 0 61", "Execute the precise endgame technique with f4f3 to secure victory.", "Look for the key move f4f3.", listOf("f4f3", "b6c7", "f3f2", "c7g3", "f2f1q")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "8/8/8/p3b3/P1B2p1K/1Pk1p2P/6P1/8 b - - 0 50", "Execute the precise endgame technique with c3d2 to secure victory.", "Look for the key move c3d2.", listOf("c3d2", "h4g4", "e3e2", "c4e2", "d2e2")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "8/p7/8/P1p2B2/2P3p1/5k1p/7K/8 b - - 3 47", "Execute the precise endgame technique with g4g3 to secure victory.", "Look for the key move g4g3.", listOf("g4g3", "h2h3", "g3g2", "f5e4", "f3e4")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "4k1K1/4P3/8/B7/ppp3P1/P7/1P6/8 b - - 1 56", "Execute the precise endgame technique with c4c3 to secure victory.", "Look for the key move c4c3.", listOf("c4c3", "b2c3", "b4b3", "a5b4", "b3b2")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "3k4/2b5/1KP5/p4p2/7p/1P5P/P5P1/8 w - - 5 55", "Execute the precise endgame technique with b6b7 to secure victory.", "Look for the key move b6b7.", listOf("b6b7", "c7e5", "a2a3")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/2b5/1Bk3pp/P1p1pp2/8/2PPK2P/6P1/8 w - - 3 33", "Execute the precise endgame technique with b6c7 to secure victory.", "Look for the key move b6c7.", listOf("b6c7", "c6c7", "d3d4", "e5d4", "c3d4")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/p7/5k1K/6pP/5PP1/b7/3p4/3B4 b - - 0 47", "Execute the precise endgame technique with a3f8 to secure victory.", "Look for the key move a3f8.", listOf("a3f8", "h6h7", "g5f4")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "5k2/8/p6K/2pp2pP/P1PpP1P1/3P4/1b6/8 w - - 0 53", "Execute the precise endgame technique with e4d5 to secure victory.", "Look for the key move e4d5.", listOf("e4d5", "b2c1", "h6g6")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "4B3/8/1p5p/2p2bp1/PPkp1p2/2P2P1P/3K2P1/8 w - - 1 41", "Execute the precise endgame technique with e8f7 to secure victory.", "Look for the key move e8f7.", listOf("e8f7", "f5e6", "f7e6")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/8/8/2k1p2p/2PpB3/3K2p1/8/8 b - - 1 43", "Execute the precise endgame technique with h5h4 to secure victory.", "Look for the key move h5h4.", listOf("h5h4", "e4g2", "e5e4", "d3e2", "c5c4")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "6k1/p7/2p3PP/2P2K2/8/2b5/P7/8 w - - 1 43", "Execute the precise endgame technique with f5e6 to secure victory.", "Look for the key move f5e6.", listOf("f5e6", "c3d2", "h6h7")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "8/3K2B1/1bP3kP/p7/5p2/8/8/8 w - - 2 52", "Execute the precise endgame technique with g7d4 to secure victory.", "Look for the key move g7d4.", listOf("g7d4", "b6d4", "c6c7")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "2b5/2P4p/8/4K2p/5P2/4k3/8/8 w - - 0 62", "Execute the precise endgame technique with f4f5 to secure victory.", "Look for the key move f4f5.", listOf("f4f5", "h5h4", "f5f6", "h4h3", "f6f7", "h3h2", "f7f8q", "h2h1q", "f8c8", "h1e4", "e5d6")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "8/8/2k3K1/8/ppP5/5P2/8/B7 b - - 3 46", "Execute the precise endgame technique with a4a3 to secure victory.", "Look for the key move a4a3.", listOf("a4a3", "a1g7", "b4b3", "g7f8", "b3b2", "f8a3", "b2b1q")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/6kp/2b1p3/2ppPpPP/p1p4K/P1P1P3/1P2B3/8 w - - 19 41", "Execute the precise endgame technique with h5h6 to secure victory.", "Look for the key move h5h6.", listOf("h5h6", "g7g8", "e2h5")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/b6p/P1K5/2p1k1p1/6P1/8/1P3P2/8 w - - 2 50", "Execute the precise endgame technique with c6b7 to secure victory.", "Look for the key move c6b7.", listOf("c6b7", "e5d6", "b7a7", "d6c7", "b2b3", "h7h6", "f2f3")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/8/2P1k3/8/8/p2P2Bp/K7/2b5 w - - 6 45", "Execute the precise endgame technique with d3d4 to secure victory.", "Look for the key move d3d4.", listOf("d3d4", "c1b2", "d4d5")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/8/8/2p1K3/p7/1k6/8/2B5 b - - 2 54", "Execute the precise endgame technique with a4a3 to secure victory.", "Look for the key move a4a3.", listOf("a4a3", "c1a3", "b3a3", "e5d5", "a3b4")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/8/2p1p1b1/p1P1Pp1k/Pp1PP1pP/1B4K1/8/8 b - - 2 45", "Execute the precise endgame technique with f5f4 to secure victory.", "Look for the key move f5f4.", listOf("f5f4", "g3f4", "h5h4")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "8/4k2B/6p1/4Kp2/5P1p/4P3/8/8 b - - 1 41", "Execute the precise endgame technique with e7f7 to secure victory.", "Look for the key move e7f7.", listOf("e7f7", "e3e4", "h4h3", "e4f5", "h3h2", "f5g6", "f7g7")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/8/4kpK1/1p2p2P/2p2b2/5P2/1PB5/8 w - - 4 35", "Execute the precise endgame technique with h5h6 to secure victory.", "Look for the key move h5h6.", listOf("h5h6", "f4h6", "g6h6")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/1p6/p1p2p2/P3b3/1PK3P1/2PB4/3k4/8 w - - 4 61", "Execute the precise endgame technique with d3f5 to secure victory.", "Look for the key move d3f5.", listOf("d3f5", "e5c7", "f5c8", "b7b6", "a5b6", "c7b6", "c8a6")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "8/8/1b2p3/1P1p3P/3P2k1/1K6/8/8 w - - 0 47", "Execute the precise endgame technique with h5h6 to secure victory.", "Look for the key move h5h6.", listOf("h5h6", "b6d4", "h6h7", "g4f5", "b5b6", "f5g6", "b6b7")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/8/p3k1p1/1p2Pp1p/1Pb4P/P4B2/5KP1/8 b - - 0 42", "Execute the precise endgame technique with c4d5 to secure victory.", "Look for the key move c4d5.", listOf("c4d5", "f3e2", "e6e5")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/p1p2k1p/Pp5b/1PPP1K2/3P4/8/8/8 w - - 0 46", "Execute the precise endgame technique with d5d6 to secure victory.", "Look for the key move d5d6.", listOf("d5d6", "c7d6", "c5b6")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/8/8/1Pb5/P1k3p1/4p1P1/8/3KB3 b - - 1 49", "Execute the precise endgame technique with c4d3 to secure victory.", "Look for the key move c4d3.", listOf("c4d3", "d1c1", "d3e2")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/p4k1p/6p1/2p5/5p2/1P1KbP2/P1p3PP/2B5 w - - 2 38", "Execute the precise endgame technique with c1e3 to secure victory.", "Look for the key move c1e3.", listOf("c1e3", "f4e3", "d3c2", "e3e2", "c2d2", "e2e1n", "d2e1")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/8/2p3p1/2P2pKb/3Bk3/4P3/7P/8 b - - 16 41", "Execute the precise endgame technique with f5f4 to secure victory.", "Look for the key move f5f4.", listOf("f5f4", "e3f4", "e4d4", "f4f5", "g6f5")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/5p1p/3k4/3Pp3/4K3/3P2P1/1pB4P/b7 w - - 2 37", "Execute the precise endgame technique with c2b1 to secure victory.", "Look for the key move c2b1.", listOf("c2b1", "f7f5", "e4f5")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/4k3/1b2P3/p2K4/1p3P1p/2p4P/P7/2B5 w - - 1 40", "Execute the precise endgame technique with f4f5 to secure victory.", "Look for the key move f4f5.", listOf("f4f5", "b6d8", "c1g5", "e7e8", "f5f6", "d8f6", "g5f6")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/p7/8/1PP1p3/4K3/2b3k1/8/8 w - - 0 49", "Execute the precise endgame technique with b5b6 to secure victory.", "Look for the key move b5b6.", listOf("b5b6", "a7b6", "c5c6")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "2k5/1p6/1B1p2p1/PK1Pppb1/4P2p/5P1P/6P1/8 w - - 1 39", "Execute the precise endgame technique with a5a6 to secure victory.", "Look for the key move a5a6.", listOf("a5a6", "b7a6", "b5c6")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/8/1p6/p3p1p1/P1P2bP1/1P6/1k2K3/3B4 b - - 9 44", "Execute the precise endgame technique with b2c1 to secure victory.", "Look for the key move b2c1.", listOf("b2c1", "b3b4", "a5b4", "c4c5", "b6c5")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "5k2/8/1B2K3/p3P2p/Pb6/8/8/8 w - - 2 47", "Execute the precise endgame technique with e6d7 to secure victory.", "Look for the key move e6d7.", listOf("e6d7", "h5h4", "e5e6", "h4h3", "b6a5")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/7p/p7/P4p1k/1K1B1P2/6P1/p3b2P/8 b - - 7 45", "Execute the precise endgame technique with h5g4 to secure victory.", "Look for the key move h5g4.", listOf("h5g4", "b4a3", "g4h3")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "8/8/BPk3p1/6Pp/p3p1pK/Pb2P3/5P2/8 w - - 3 60", "Execute the precise endgame technique with a6b5 to secure victory.", "Look for the key move a6b5.", listOf("a6b5", "c6b5", "b6b7")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "8/8/4B3/8/5ppk/6p1/6K1/8 b - - 3 55", "Execute the precise endgame technique with f4f3 to secure victory.", "Look for the key move f4f3.", listOf("f4f3", "g2h1", "h4h3", "e6d5", "g3g2", "h1g1", "h3g3", "d5f3", "g3f3")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/2p5/5k2/pp6/2bPBK2/P1P5/1P5p/8 b - - 1 47", "Execute the precise endgame technique with f6e6 to secure victory.", "Look for the key move f6e6.", listOf("f6e6", "f4g3", "c4d5", "g3h2", "d5e4")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/B5p1/1p2kp1p/p1b4P/P1P1K1P1/1P6/8/8 w - - 11 49", "Execute the precise endgame technique with b3b4 to secure victory.", "Look for the key move b3b4.", listOf("b3b4", "a5b4", "a4a5", "b4b3", "e4d3")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/p1k1bp2/1p4pp/4Pp2/2p1K1P1/P1P1B3/1P4P1/8 w - - 0 31", "Execute the precise endgame technique with g4f5 to secure victory.", "Look for the key move g4f5.", listOf("g4f5", "g6f5", "e4f5", "h6h5", "e5e6", "f7e6", "f5e6")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "8/8/8/2p4P/2PpP3/1PbPkP2/6K1/8 b - - 0 60", "Execute the precise endgame technique with e3d3 to secure victory.", "Look for the key move e3d3.", listOf("e3d3", "e4e5", "d3c2", "e5e6", "d4d3", "e6e7", "d3d2", "e7e8q", "d2d1q")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/5k2/7p/p1P1bPpP/Pp2P3/1P1p1K2/5B2/8 b - - 2 47", "Execute the precise endgame technique with g5g4 to secure victory.", "Look for the key move g5g4.", listOf("g5g4", "f3e3", "e5d4", "e3d3", "d4f2")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/8/1p2B3/6k1/1PP2pb1/8/5K2/8 w - - 0 51", "Execute the precise endgame technique with e6g4 to secure victory.", "Look for the key move e6g4.", listOf("e6g4", "g5g4", "b4b5", "g4f5", "c4c5", "f5e5", "c5c6")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "8/p3k3/1p2pp1p/1Pb1P1P1/P1K1B2P/8/8/8 w - - 0 42", "Execute the precise endgame technique with g5h6 to secure victory.", "Look for the key move g5h6.", listOf("g5h6", "e7f7", "h6h7", "f7g7", "e5f6")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "5k2/4bB2/4P3/2p2P2/2b5/8/p7/B6K b - - 5 48", "Execute the precise endgame technique with e7g5 to secure victory.", "Look for the key move e7g5.", listOf("e7g5", "f5f6", "g5e3", "a1e5", "e3f4", "e5f4", "a2a1q")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "b7/p7/5p2/5B1P/3pp1K1/2k5/P1P3P1/8 w - - 0 33", "Execute the precise endgame technique with h5h6 to secure victory.", "Look for the key move h5h6.", listOf("h5h6", "e4e3", "h6h7", "e3e2", "h7h8q")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/8/2bk4/p3pB2/2K2P1p/P3P3/8/8 b - - 2 40", "Execute the precise endgame technique with e5f4 to secure victory.", "Look for the key move e5f4.", listOf("e5f4", "e3f4", "c6d7", "f5d7", "d6d7")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/5k1p/5p2/3pbP2/7P/2B2K2/1P6/8 b - - 6 32", "Execute the precise endgame technique with e5c3 to secure victory.", "Look for the key move e5c3.", listOf("e5c3", "b2c3", "h7h5")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/2B5/p1p3p1/P4pPp/4pP1P/4P3/3k1K2/8 b - - 7 67", "Execute the precise endgame technique with c6c5 to secure victory.", "Look for the key move c6c5.", listOf("c6c5", "c7b6", "c5c4", "b6c5", "c4c3", "c5b4", "d2c2")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/3k4/8/pp1PPK2/6Pb/8/P7/8 w - - 2 44", "Execute the precise endgame technique with g4g5 to secure victory.", "Look for the key move g4g5.", listOf("g4g5", "a5a4", "e5e6", "d7e8", "d5d6")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/2p5/2P2k1p/2K1p3/3p2P1/3B1p2/7P/8 b - - 0 42", "Execute the precise endgame technique with f6g5 to secure victory.", "Look for the key move f6g5.", listOf("f6g5", "c5d5", "g5f4", "h2h4", "f4e3")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/5k2/8/3pPPB1/1p4K1/8/8/3b4 w - - 1 46", "Execute the precise endgame technique with g4h4 to secure victory.", "Look for the key move g4h4.", listOf("g4h4", "d1a4", "e5e6")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/8/3kP1p1/3B2P1/bpK5/8/8/8 b - - 1 53", "Execute the precise endgame technique with a4c6 to secure victory.", "Look for the key move a4c6.", listOf("a4c6", "d5c6", "d6c6", "e6e7", "c6d7", "e7e8r", "d7e8")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "8/8/8/P1K1p3/5k1p/5b2/5P2/5B2 b - - 4 46", "Execute the precise endgame technique with f3a8 to secure victory.", "Look for the key move f3a8.", listOf("f3a8", "c5d6", "e5e4", "f1g2", "f4g4", "a5a6", "h4h3", "g2h1", "e4e3", "h1a8", "e3f2", "a8f3", "g4g3", "a6a7", "f2f1q", "a7a8q", "f1f3")),
        RawLesson("Advanced Pawn Technique in Bishop Endgame", "8/8/3k4/4p3/1PB1K2p/2P4b/8/8 b - - 1 64", "Execute the precise endgame technique with h3e6 to secure victory.", "Look for the key move h3e6.", listOf("h3e6", "c4f1", "h4h3", "e4f3", "h3h2")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/6pp/2p2p2/7P/1k1K4/p7/B4P2/8 b - - 2 42", "Execute the precise endgame technique with g7g5 to secure victory.", "Look for the key move g7g5.", listOf("g7g5", "h5g6", "h7g6", "d4d3", "g6g5", "d3c2", "f6f5")),
        RawLesson("Bishop Endgame Technique in Bishop Endgame", "8/1pp5/8/PP4p1/3k1pP1/3b4/1b1BB3/5K2 w - - 3 42", "Execute the precise endgame technique with e2d3 to secure victory.", "Look for the key move e2d3.", listOf("e2d3", "d4d3", "d2e1", "d3c4", "a5a6", "b7a6", "b5a6", "b2d4", "e1f2"))
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
                id = "e_bishop_endgame_$level",
                title = "Level $level ($tier difficulty) • ${base.subtitle}",
                category = "Bishop Endgame",
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
