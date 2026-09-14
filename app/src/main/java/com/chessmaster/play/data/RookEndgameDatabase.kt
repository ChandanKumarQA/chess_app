package com.chessmaster.play.data

import com.chessmaster.play.model.EndgameLesson

object RookEndgameDatabase {

    private data class RawLesson(
        val subtitle: String,
        val fen: String,
        val explanation: String,
        val hint: String,
        val moves: List<String>
    )

    private val pool = listOf(
        RawLesson("Back Rank Mate Technique in Rook Endgame", "6k1/5ppp/3rp3/8/8/8/1P3PPP/2R3K1 w - - 0 35", "Execute the precise endgame technique with c1c8 to secure victory.", "Look for the key move c1c8.", listOf("c1c8", "d6d8", "c8d8")),
        RawLesson("Back Rank Mate Technique in Rook Endgame", "5r1k/p5pp/1p2p3/6PP/4P3/8/PPPr4/1K3R2 w - - 0 27", "Execute the precise endgame technique with f1f8 to secure victory.", "Look for the key move f1f8.", listOf("f1f8")),
        RawLesson("Back Rank Mate Technique in Rook Endgame", "1R6/6kp/3p1pp1/2r1p3/PP6/8/2r2PPP/1R4K1 b - - 0 30", "Execute the precise endgame technique with c2c1 to secure victory.", "Look for the key move c2c1.", listOf("c2c1", "b1c1", "c5c1")),
        RawLesson("Master Technique in Rook Endgame", "8/6k1/1R5p/5p1P/5P1K/6P1/8/r7 b - - 3 58", "Execute the precise endgame technique with a1h1 to secure victory.", "Look for the key move a1h1.", listOf("a1h1")),
        RawLesson("Back Rank Mate Technique in Rook Endgame", "4r1k1/p4ppp/8/8/8/8/PP1R1PPP/K7 b - - 0 21", "Execute the precise endgame technique with e8e1 to secure victory.", "Look for the key move e8e1.", listOf("e8e1", "d2d1", "e1d1")),
        RawLesson("Back Rank Mate Technique in Rook Endgame", "7k/6p1/1r5p/P4p2/3Rp3/4P3/5PPP/6K1 b - - 0 39", "Execute the precise endgame technique with b6b1 to secure victory.", "Look for the key move b6b1.", listOf("b6b1", "d4d1", "b1d1")),
        RawLesson("Mate Technique in Rook Endgame", "1r6/p7/1p3k1K/P2p2R1/2p5/2P3P1/2P3P1/1R6 b - - 0 36", "Execute the precise endgame technique with b8h8 to secure victory.", "Look for the key move b8h8.", listOf("b8h8")),
        RawLesson("Mate Technique in Rook Endgame", "6k1/ppp1r1pp/8/8/2r5/2P5/P5PP/3R1RK1 w - - 0 27", "Execute the precise endgame technique with d1d8 to secure victory.", "Look for the key move d1d8.", listOf("d1d8", "e7e8", "d8e8")),
        RawLesson("Mate Technique in Rook Endgame", "8/5k2/1P4RK/6P1/1r6/8/8/8 b - - 2 58", "Execute the precise endgame technique with b4h4 to secure victory.", "Look for the key move b4h4.", listOf("b4h4")),
        RawLesson("Mate Technique in Rook Endgame", "6k1/2r5/4p3/p2pR1pP/R5P1/1P6/PP3r2/1K6 b - - 0 29", "Execute the precise endgame technique with f2f1 to secure victory.", "Look for the key move f2f1.", listOf("f2f1", "e5e1", "f1e1")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "8/5p2/p3kr2/8/8/R1P5/4P3/4K3 w - - 0 53", "Execute the precise endgame technique with a3a6 to secure victory.", "Look for the key move a3a6.", listOf("a3a6", "e6e5", "a6f6")),
        RawLesson("Back Rank Mate Technique in Rook Endgame", "1rR5/3Pkppp/4p3/3p4/8/8/P4PPP/6K1 b - - 1 29", "Execute the precise endgame technique with b8b1 to secure victory.", "Look for the key move b8b1.", listOf("b8b1", "c8c1", "b1c1")),
        RawLesson("Master Technique in Rook Endgame", "6R1/8/Kpk1p3/1p1pP3/6P1/PPr5/8/8 w - - 0 41", "Execute the precise endgame technique with g8c8 to secure victory.", "Look for the key move g8c8.", listOf("g8c8", "c6d7", "c8c3")),
        RawLesson("Mate Technique in Rook Endgame", "8/5p2/1R6/6pk/8/3r2PP/5K2/8 w - - 4 41", "Execute the precise endgame technique with g3g4 to secure victory.", "Look for the key move g3g4.", listOf("g3g4", "h5h4", "b6h6")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "6R1/p7/5k2/P7/6KP/8/8/5r2 b - - 6 53", "Execute the precise endgame technique with f1g1 to secure victory.", "Look for the key move f1g1.", listOf("f1g1", "g4f4", "g1g8")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "8/8/5k2/R2K4/2P4r/8/P7/8 b - - 2 43", "Execute the precise endgame technique with h4h5 to secure victory.", "Look for the key move h4h5.", listOf("h4h5", "d5d6", "h5a5")),
        RawLesson("Mate Technique in Rook Endgame", "8/8/5pkp/1RP5/1P3PKP/r7/8/8 b - - 0 48", "Execute the precise endgame technique with f6f5 to secure victory.", "Look for the key move f6f5.", listOf("f6f5")),
        RawLesson("Mate Technique in Rook Endgame", "4r2k/p4R1p/1p6/2p5/2P5/1P4R1/r5PP/2K5 b - - 1 32", "Execute the precise endgame technique with e8e1 to secure victory.", "Look for the key move e8e1.", listOf("e8e1")),
        RawLesson("Exposed King Technique in Rook Endgame", "6k1/5R2/pp4p1/2p4p/7P/1P6/P2rr1PK/5R2 w - - 1 39", "Execute the precise endgame technique with f7f8 to secure victory.", "Look for the key move f7f8.", listOf("f7f8", "g8h7", "f1f7", "h7h6", "f8h8")),
        RawLesson("Master Technique in Rook Endgame", "4R3/4R3/1k1K2p1/1P6/1P6/2rp3r/8/8 w - - 4 46", "Execute the precise endgame technique with e8b8 to secure victory.", "Look for the key move e8b8.", listOf("e8b8")),
        RawLesson("Defensive Move Technique in Rook Endgame", "8/3K4/r7/6k1/6p1/6Pp/3R3P/8 w - - 3 55", "Execute the precise endgame technique with d2d5 to secure victory.", "Look for the key move d2d5.", listOf("d2d5", "g5g6", "d5d6", "a6d6", "d7d6", "g6f6", "d6d5", "f6f5", "d5d4")),
        RawLesson("Master Technique in Rook Endgame", "8/8/2k3r1/R7/P2p3p/3K4/8/8 w - - 0 46", "Execute the precise endgame technique with a5a6 to secure victory.", "Look for the key move a5a6.", listOf("a5a6", "c6d7", "a6g6")),
        RawLesson("Mate Technique in Rook Endgame", "8/8/p2k4/1pp5/7P/1PKP1RP1/7r/8 b - - 1 46", "Execute the precise endgame technique with b5b4 to secure victory.", "Look for the key move b5b4.", listOf("b5b4", "c3c4", "h2c2")),
        RawLesson("Advanced Pawn Technique in Rook Endgame", "5r2/R2R1pk1/P7/5p2/6p1/1KP5/5r2/8 w - - 0 45", "Execute the precise endgame technique with d7f7 to secure victory.", "Look for the key move d7f7.", listOf("d7f7", "f8f7", "a7f7", "g7f7", "a6a7")),
        RawLesson("Mate Technique in Rook Endgame", "8/6r1/pR6/6pk/8/6PP/6K1/8 w - - 0 41", "Execute the precise endgame technique with g3g4 to secure victory.", "Look for the key move g3g4.", listOf("g3g4", "h5h4", "b6h6")),
        RawLesson("Exposed King Technique in Rook Endgame", "8/3r1ppp/4p3/k3P3/pR2R2P/2P5/3r1PP1/2K5 b - - 5 31", "Execute the precise endgame technique with d2d1 to secure victory.", "Look for the key move d2d1.", listOf("d2d1", "c1b2", "d7d2", "b2a3", "d1a1")),
        RawLesson("Advanced Pawn Technique in Rook Endgame", "8/3KP1k1/8/8/8/8/2p1R3/2r5 b - - 11 59", "Execute the precise endgame technique with c1d1 to secure victory.", "Look for the key move c1d1.", listOf("c1d1", "d7e6", "c2c1q")),
        RawLesson("Advanced Pawn Technique in Rook Endgame", "1R6/1P6/4pkp1/5p2/3P4/3KP2p/8/1r6 w - - 0 44", "Execute the precise endgame technique with b8f8 to secure victory.", "Look for the key move b8f8.", listOf("b8f8", "f6e7", "b7b8q", "b1b8", "f8b8")),
        RawLesson("Deflection Technique in Rook Endgame", "1R6/2P5/p5k1/6pp/1P6/6PK/r6P/8 b - - 0 40", "Execute the precise endgame technique with g5g4 to secure victory.", "Look for the key move g5g4.", listOf("g5g4", "h3h4", "a2h2")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "7R/8/8/8/r2k4/4p3/8/4K3 w - - 2 58", "Execute the precise endgame technique with h8h4 to secure victory.", "Look for the key move h8h4.", listOf("h8h4", "d4c3", "h4a4")),
        RawLesson("Exposed King Technique in Rook Endgame", "8/8/rk5p/3R2p1/4p1P1/4P3/3K4/8 w - - 0 49", "Execute the precise endgame technique with d5d6 to secure victory.", "Look for the key move d5d6.", listOf("d5d6", "b6a5", "d6a6", "a5a6", "d2c3", "a6b7", "c3d4")),
        RawLesson("Fork Technique in Rook Endgame", "8/pp1k1p2/4p3/2ppPr1r/7P/2P2pP1/P1P5/2K1RR2 w - - 0 23", "Execute the precise endgame technique with g3g4 to secure victory.", "Look for the key move g3g4.", listOf("g3g4", "f5e5", "g4h5")),
        RawLesson("Defensive Move Technique in Rook Endgame", "1r5r/p3kp2/4p2p/4P3/R4Pp1/6P1/P1P4P/4K2R b K - 2 25", "Execute the precise endgame technique with b8b1 to secure victory.", "Look for the key move b8b1.", listOf("b8b1", "e1f2", "b1h1", "a4a7", "e7f8")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "8/r4pp1/2k1p2p/8/pK1PP3/5PP1/6P1/1R6 b - - 2 35", "Execute the precise endgame technique with a7b7 to secure victory.", "Look for the key move a7b7.", listOf("a7b7", "b4a4", "b7b1")),
        RawLesson("Mate Technique in Rook Endgame", "8/6P1/5k1K/8/8/3p4/P2R4/6r1 b - - 2 49", "Execute the precise endgame technique with g1h1 to secure victory.", "Look for the key move g1h1.", listOf("g1h1", "d2h2", "h1h2")),
        RawLesson("Mate Technique in Rook Endgame", "8/8/1R3R2/6k1/6P1/4r2P/r7/7K b - - 6 40", "Execute the precise endgame technique with e3e1 to secure victory.", "Look for the key move e3e1.", listOf("e3e1", "f6f1", "e1f1")),
        RawLesson("Advanced Pawn Technique in Rook Endgame", "6k1/5pp1/7p/2p5/2P5/1Rrp2PP/P4P2/5K2 b - - 1 37", "Execute the precise endgame technique with c3c1 to secure victory.", "Look for the key move c3c1.", listOf("c3c1", "f1g2", "d3d2")),
        RawLesson("Exposed King Technique in Rook Endgame", "4r3/p5k1/2R4p/2Pp4/1P1pr1P1/P6P/8/3R3K b - - 0 35", "Execute the precise endgame technique with e4e1 to secure victory.", "Look for the key move e4e1.", listOf("e4e1", "d1e1", "e8e1", "h1g2", "d4d3")),
        RawLesson("Advanced Pawn Technique in Rook Endgame", "8/7k/R6p/3p4/5r2/2P1p2P/P5P1/6K1 b - - 0 40", "Execute the precise endgame technique with e3e2 to secure victory.", "Look for the key move e3e2.", listOf("e3e2", "a6e6", "f4f1")),
        RawLesson("Deflection Technique in Rook Endgame", "8/5r2/7k/6pp/7P/5PK1/R7/8 w - - 0 59", "Execute the precise endgame technique with a2a6 to secure victory.", "Look for the key move a2a6.", listOf("a2a6", "h6g7", "h4g5")),
        RawLesson("Master Technique in Rook Endgame", "8/8/Rp1r4/P1p5/2P1kp2/1P6/4K3/8 b - - 0 57", "Execute the precise endgame technique with f4f3 to secure victory.", "Look for the key move f4f3.", listOf("f4f3", "e2f2", "d6d2")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "7R/8/p3p3/2rk3p/p4K2/6PP/P7/8 w - - 4 40", "Execute the precise endgame technique with h8h5 to secure victory.", "Look for the key move h8h5.", listOf("h8h5", "e6e5", "h5e5")),
        RawLesson("Mate Technique in Rook Endgame", "8/2k3pp/4p3/1R2Kp2/1Pr4P/6P1/5P2/8 b - - 8 37", "Execute the precise endgame technique with c4e4 to secure victory.", "Look for the key move c4e4.", listOf("c4e4")),
        RawLesson("Advanced Pawn Technique in Rook Endgame", "5r2/7P/5K2/8/2P2k2/P7/8/8 w - - 1 69", "Execute the precise endgame technique with f6g7 to secure victory.", "Look for the key move f6g7.", listOf("f6g7", "f8d8", "h7h8q", "d8h8", "g7h8", "f4e4", "a3a4", "e4d4", "a4a5")),
        RawLesson("Advanced Pawn Technique in Rook Endgame", "8/8/8/6R1/2pk2P1/1r5P/6K1/8 b - - 1 48", "Execute the precise endgame technique with c4c3 to secure victory.", "Look for the key move c4c3.", listOf("c4c3", "g5g7", "c3c2")),
        RawLesson("Master Technique in Rook Endgame", "2k5/1p5R/3K4/Pr6/8/1p6/8/8 w - - 2 47", "Execute the precise endgame technique with h7h8 to secure victory.", "Look for the key move h7h8.", listOf("h7h8")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "8/5R2/1p2P3/p4r2/P6p/1P3Pk1/4K3/8 b - - 2 64", "Execute the precise endgame technique with f5e5 to secure victory.", "Look for the key move f5e5.", listOf("f5e5", "e2f1", "e5e6")),
        RawLesson("Defensive Move Technique in Rook Endgame", "3r4/5k2/p4Pp1/2K3Pp/2R5/P7/8/8 b - - 0 51", "Execute the precise endgame technique with d8c8 to secure victory.", "Look for the key move d8c8.", listOf("d8c8", "c5d4", "c8c4", "d4c4", "h5h4")),
        RawLesson("Advanced Pawn Technique in Rook Endgame", "6R1/8/8/2P5/1K6/3r4/PP3kp1/8 b - - 0 52", "Execute the precise endgame technique with d3g3 to secure victory.", "Look for the key move d3g3.", listOf("d3g3", "g8g3", "f2g3", "c5c6", "g2g1q")),
        RawLesson("Advanced Pawn Technique in Rook Endgame", "8/6Rr/7P/3p4/2pk1p2/3R4/6PK/8 b - - 0 57", "Execute the precise endgame technique with c4d3 to secure victory.", "Look for the key move c4d3.", listOf("c4d3", "g7h7", "d3d2", "h7f7", "d2d1q")),
        RawLesson("Mate Technique in Rook Endgame", "8/8/6p1/PR3p2/1P3k1P/8/r5P1/7K b - - 0 39", "Execute the precise endgame technique with f4g3 to secure victory.", "Look for the key move f4g3.", listOf("f4g3", "b5d5", "a2a1", "d5d1", "a1d1")),
        RawLesson("Advanced Pawn Technique in Rook Endgame", "7R/8/8/P5p1/5k2/1KP5/6rp/8 w - - 2 52", "Execute the precise endgame technique with a5a6 to secure victory.", "Look for the key move a5a6.", listOf("a5a6", "g2e2", "a6a7")),
        RawLesson("Master Technique in Rook Endgame", "8/1R5R/4kpp1/4p3/4P2K/5P1P/7r/6r1 w - - 11 41", "Execute the precise endgame technique with b7b6 to secure victory.", "Look for the key move b7b6.", listOf("b7b6")),
        RawLesson("Discovered Attack Technique in Rook Endgame", "3r4/3r2k1/p7/3p2p1/3RP1Pp/1P3K2/P2R4/8 b - - 0 38", "Execute the precise endgame technique with d5e4 to secure victory.", "Look for the key move d5e4.", listOf("d5e4", "f3e3", "d7d4")),
        RawLesson("Advanced Pawn Technique in Rook Endgame", "8/5KP1/R7/2k5/2pp4/8/8/6r1 w - - 2 51", "Execute the precise endgame technique with a6g6 to secure victory.", "Look for the key move a6g6.", listOf("a6g6", "g1g6", "f7g6", "d4d3", "g7g8q")),
        RawLesson("Deflection Technique in Rook Endgame", "8/8/8/4R2p/4p2k/4r2P/6PK/8 w - - 2 51", "Execute the precise endgame technique with g2g3 to secure victory.", "Look for the key move g2g3.", listOf("g2g3", "e3g3", "e5e4", "h4g5", "h2g3")),
        RawLesson("Master Technique in Rook Endgame", "8/2k4p/p2p2p1/2pP1p2/2P4P/3K4/P3RPP1/4r3 b - - 1 28", "Execute the precise endgame technique with e1e2 to secure victory.", "Look for the key move e1e2.", listOf("e1e2", "d3e2", "c7b6", "e2f3", "b6a5", "a2a3", "a5a4")),
        RawLesson("Deflection Technique in Rook Endgame", "8/p1r2p2/4r3/2P1PK2/1P4R1/3R2pk/8/8 b - - 3 46", "Execute the precise endgame technique with e6e5 to secure victory.", "Look for the key move e6e5.", listOf("e6e5", "f5e5", "h3g4")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "8/8/8/3p4/5p1R/r3k3/6K1/8 w - - 0 52", "Execute the precise endgame technique with h4h3 to secure victory.", "Look for the key move h4h3.", listOf("h4h3", "e3e2", "h3a3", "d5d4", "a3a2")),
        RawLesson("Mate Technique in Rook Endgame", "8/2p4r/1p3k2/p2PR1p1/P1P2pP1/1P3P1r/4R1K1/8 b - - 0 46", "Execute the precise endgame technique with h3h2 to secure victory.", "Look for the key move h3h2.", listOf("h3h2", "g2f1", "h2h1", "f1f2", "h7h2")),
        RawLesson("Advanced Pawn Technique in Rook Endgame", "8/6pk/8/R7/5Pp1/p5P1/3K3P/r7 b - - 3 46", "Execute the precise endgame technique with a3a2 to secure victory.", "Look for the key move a3a2.", listOf("a3a2", "d2c3", "a1c1")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "8/4kr2/R2p4/1p1Pp3/5pp1/3K1P2/PPP5/8 w - - 0 40", "Execute the precise endgame technique with a6a7 to secure victory.", "Look for the key move a6a7.", listOf("a6a7", "e7f6", "a7f7", "f6f7", "f3g4")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "8/8/1P5p/3R3P/8/2k5/3p3r/3K4 b - - 0 49", "Execute the precise endgame technique with h2h1 to secure victory.", "Look for the key move h2h1.", listOf("h2h1", "d1e2", "h1e1")),
        RawLesson("Advanced Pawn Technique in Rook Endgame", "8/3k4/3P1K2/p4R2/5r2/5P2/8/8 b - - 2 57", "Execute the precise endgame technique with f4f5 to secure victory.", "Look for the key move f4f5.", listOf("f4f5", "f6f5", "a5a4", "f5e4", "a4a3", "e4d3", "a3a2")),
        RawLesson("Mate Technique in Rook Endgame", "6k1/4P3/p5p1/8/8/2P3PP/1r5r/R3RK2 b - - 0 46", "Execute the precise endgame technique with h2h1 to secure victory.", "Look for the key move h2h1.", listOf("h2h1")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "8/8/5kp1/R7/4p1PP/5p2/2r2P2/6K1 b - - 0 52", "Execute the precise endgame technique with c2c1 to secure victory.", "Look for the key move c2c1.", listOf("c2c1", "g1h2", "e4e3")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "8/5R2/P7/2p2r2/2Pp2k1/3K4/5p2/8 w - - 2 55", "Execute the precise endgame technique with f7f5 to secure victory.", "Look for the key move f7f5.", listOf("f7f5", "g4f5", "d3e2", "f2f1q", "e2f1")),
        RawLesson("Quiet Move Technique in Rook Endgame", "6k1/1p3p1p/4p1p1/1P1pr3/P7/8/7P/1R4K1 w - - 0 34", "Execute the precise endgame technique with a4a5 to secure victory.", "Look for the key move a4a5.", listOf("a4a5", "d5d4", "a5a6", "e5b5", "b1b5")),
        RawLesson("Defensive Move Technique in Rook Endgame", "8/8/1p6/k7/P7/1KR4r/8/8 b - - 27 64", "Execute the precise endgame technique with h3c3 to secure victory.", "Look for the key move h3c3.", listOf("h3c3", "b3c3", "a5a4", "c3b2", "a4b4")),
        RawLesson("Master Technique in Rook Endgame", "3R4/8/8/5p2/8/3p3P/1k3rP1/3K4 w - - 0 49", "Execute the precise endgame technique with d8d3 to secure victory.", "Look for the key move d8d3.", listOf("d8d3", "f2g2", "d3d2", "g2d2", "d1d2")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "8/6k1/5R2/1K4Pp/1PP5/8/6r1/8 b - - 0 63", "Execute the precise endgame technique with g2g5 to secure victory.", "Look for the key move g2g5.", listOf("g2g5", "b5a6", "g7f6")),
        RawLesson("Defensive Move Technique in Rook Endgame", "8/8/2R4r/6k1/4p3/8/4KP2/8 w - - 0 52", "Execute the precise endgame technique with c6h6 to secure victory.", "Look for the key move c6h6.", listOf("c6h6", "g5h6", "e2e3", "h6g5", "e3e4", "g5f6", "e4f4")),
        RawLesson("Defensive Move Technique in Rook Endgame", "5k2/1R6/3pp1P1/2p5/1p2PK2/5P2/8/2r5 w - - 1 61", "Execute the precise endgame technique with f4g5 to secure victory.", "Look for the key move f4g5.", listOf("f4g5", "c1g1", "g5f6")),
        RawLesson("Advanced Pawn Technique in Rook Endgame", "8/p7/6P1/4p3/R5K1/1r2kp1P/8/8 w - - 0 48", "Execute the precise endgame technique with g6g7 to secure victory.", "Look for the key move g6g7.", listOf("g6g7", "b3b8", "a4a3")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "7R/1K3p2/6k1/PP6/6p1/6rp/8/8 w - - 0 48", "Execute the precise endgame technique with a5a6 to secure victory.", "Look for the key move a5a6.", listOf("a5a6", "h3h2", "h8h2")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "8/5p2/5pk1/8/4PPr1/8/5K2/7R w - - 3 47", "Execute the precise endgame technique with f2f3 to secure victory.", "Look for the key move f2f3.", listOf("f2f3", "g4g5", "f4g5")),
        RawLesson("Exposed King Technique in Rook Endgame", "5R2/1p6/p1p1k3/2P1r3/2K3p1/2P1p1P1/1P5P/8 w - - 2 45", "Execute the precise endgame technique with f8e8 to secure victory.", "Look for the key move f8e8.", listOf("f8e8", "e6f5", "e8e5", "f5e5", "c4d3", "e5d5", "d3e3", "a6a5", "e3f4", "d5c4", "f4g4")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "8/1p6/p1p3r1/1kP5/6R1/5P1P/5K2/8 b - - 1 54", "Execute the precise endgame technique with g6g4 to secure victory.", "Look for the key move g6g4.", listOf("g6g4", "f3g4", "b5c5")),
        RawLesson("Discovered Attack Technique in Rook Endgame", "8/4k1K1/4P3/6pp/6rP/4R1P1/8/8 b - - 1 60", "Execute the precise endgame technique with g5h4 to secure victory.", "Look for the key move g5h4.", listOf("g5h4", "g7h6", "h4g3")),
        RawLesson("Quiet Move Technique in Rook Endgame", "8/8/2p5/5k2/2R3p1/1P1P2K1/2r5/8 b - - 1 38", "Execute the precise endgame technique with c2c4 to secure victory.", "Look for the key move c2c4.", listOf("c2c4", "d3c4", "c6c5", "b3b4", "c5b4")),
        RawLesson("Advanced Pawn Technique in Rook Endgame", "k7/7R/p1P5/4K3/1P6/1r5p/8/8 w - - 3 52", "Execute the precise endgame technique with h7h8 to secure victory.", "Look for the key move h7h8.", listOf("h7h8", "a8a7", "c6c7")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "8/3r3p/pp1pk1pP/3r1pP1/P1PRpP2/1P2P3/4K3/8 w - - 0 38", "Execute the precise endgame technique with c4d5 to secure victory.", "Look for the key move c4d5.", listOf("c4d5", "e6e7", "d4c4")),
        RawLesson("Discovered Attack Technique in Rook Endgame", "6r1/4p3/3p1kp1/2pP1P2/1p2pR2/1P6/4RP1P/6K1 b - - 0 40", "Execute the precise endgame technique with g6f5 to secure victory.", "Look for the key move g6f5.", listOf("g6f5", "g1f1", "f6g5", "e2e4", "f5e4")),
        RawLesson("Exposed King Technique in Rook Endgame", "8/5k2/4R2p/p7/5rPK/8/7P/8 w - - 3 43", "Execute the precise endgame technique with e6h6 to secure victory.", "Look for the key move e6h6.", listOf("e6h6", "f4f6", "h6h7", "f7g6", "h7a7")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "8/4k1r1/4pRp1/6Pp/3K1P1P/8/8/8 w - - 1 62", "Execute the precise endgame technique with d4e5 to secure victory.", "Look for the key move d4e5.", listOf("d4e5", "g7g8", "f6e6", "e7f7", "f4f5")),
        RawLesson("Advanced Pawn Technique in Rook Endgame", "8/8/5k1p/6pP/1R4P1/1p2KP2/8/1r6 b - - 0 43", "Execute the precise endgame technique with b3b2 to secure victory.", "Look for the key move b3b2.", listOf("b3b2", "b4b6", "f6e5", "f3f4", "g5f4")),
        RawLesson("Advanced Pawn Technique in Rook Endgame", "8/2p1pk1p/Pp4p1/8/2P2P2/p2r2P1/3PR2P/3K4 w - - 0 34", "Execute the precise endgame technique with a6a7 to secure victory.", "Look for the key move a6a7.", listOf("a6a7", "d3d8", "e2e3", "a3a2", "e3a3")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "8/6pp/8/3kP3/1p1P2P1/1rpK3P/4R3/8 w - - 0 40", "Execute the precise endgame technique with e5e6 to secure victory.", "Look for the key move e5e6.", listOf("e5e6", "c3c2", "d3c2", "b3c3", "c2d2")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "8/2R5/1r2k1pp/1P6/K4p2/4p2P/5PP1/8 w - - 0 44", "Execute the precise endgame technique with f2e3 to secure victory.", "Look for the key move f2e3.", listOf("f2e3", "f4e3", "c7c3")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "3R4/3P4/8/5p2/5kPp/8/3r2P1/6K1 b - - 2 64", "Execute the precise endgame technique with f4g3 to secure victory.", "Look for the key move f4g3.", listOf("f4g3", "g1f1", "f5g4")),
        RawLesson("Hanging Piece Technique in Rook Endgame", "8/1p6/1P3r2/1Rp3K1/3p4/4k3/8/8 w - - 0 45", "Execute the precise endgame technique with g5f6 to secure victory.", "Look for the key move g5f6.", listOf("g5f6", "c5c4", "b5e5")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "8/8/5pp1/5k1p/5P1P/6P1/2r1R3/5K2 b - - 1 43", "Execute the precise endgame technique with c2e2 to secure victory.", "Look for the key move c2e2.", listOf("c2e2", "f1e2", "f5g4", "e2f2", "g4h3")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "8/1pp5/p2p3p/3P1Pk1/P5P1/1P3K1R/8/2r5 b - - 2 39", "Execute the precise endgame technique with c1c3 to secure victory.", "Look for the key move c1c3.", listOf("c1c3", "f3g2", "c3h3", "g2h3", "h6h5", "g4h5", "g5h5")),
        RawLesson("Advanced Pawn Technique in Rook Endgame", "2r3k1/6p1/R3p1r1/8/P2PPp2/2P2P1p/2R3P1/6K1 b - - 0 36", "Execute the precise endgame technique with c8b8 to secure victory.", "Look for the key move c8b8.", listOf("c8b8", "g1h2", "h3g2")),
        RawLesson("Defensive Move Technique in Rook Endgame", "8/6kp/p4r2/1p6/4P3/P3KR2/6P1/8 b - - 2 48", "Execute the precise endgame technique with f6f3 to secure victory.", "Look for the key move f6f3.", listOf("f6f3", "e3f3", "g7f6")),
        RawLesson("Quiet Move Technique in Rook Endgame", "8/4Pkp1/3R1r2/3R2Kp/8/6Pr/8/8 w - - 1 40", "Execute the precise endgame technique with d5e5 to secure victory.", "Look for the key move d5e5.", listOf("d5e5", "h3g3", "g5h4", "g3g4", "h4h5", "f6f5", "e5f5")),
        RawLesson("Quiet Move Technique in Rook Endgame", "7r/6kP/6P1/6K1/p7/8/1p6/6R1 w - - 0 53", "Execute the precise endgame technique with g1d1 to secure victory.", "Look for the key move g1d1.", listOf("g1d1", "h8h7", "d1d7")),
        RawLesson("Rook Endgame Technique in Rook Endgame", "2r1k3/p1p2p2/1p3P2/2prP1PR/7p/1P6/P5P1/6K1 w - - 1 33", "Execute the precise endgame technique with h5h8 to secure victory.", "Look for the key move h5h8.", listOf("h5h8", "e8d7", "h8c8", "d7c8", "e5e6")),
        RawLesson("Advanced Pawn Technique in Rook Endgame", "7R/6rP/8/8/8/5p1K/8/5k2 b - - 6 67", "Execute the precise endgame technique with f3f2 to secure victory.", "Look for the key move f3f2.", listOf("f3f2", "h3h4", "g7e7")),
        RawLesson("Advanced Pawn Technique in Rook Endgame", "4k3/5p2/3r4/p1R3Pp/2P1p3/6P1/1KP4P/8 b - - 0 31", "Execute the precise endgame technique with e4e3 to secure victory.", "Look for the key move e4e3.", listOf("e4e3", "g5g6", "e8f8", "c5a5", "e3e2"))
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
                id = "e_rook_endgame_$level",
                title = "Level $level ($tier difficulty) • ${base.subtitle}",
                category = "Rook Endgame",
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
