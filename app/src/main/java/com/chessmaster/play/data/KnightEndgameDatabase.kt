package com.chessmaster.play.data

import com.chessmaster.play.model.EndgameLesson

object KnightEndgameDatabase {

    private data class RawLesson(
        val subtitle: String,
        val fen: String,
        val explanation: String,
        val hint: String,
        val moves: List<String>
    )

    private val pool = listOf(
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/1p3KN1/1k6/8/2p2P2/6P1/8/8 b - - 1 54", "Execute the precise endgame technique with c4c3 to secure victory.", "Look for the key move c4c3.", listOf("c4c3", "g7e6", "c3c2", "g3g4", "c2c1q")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "6k1/6pp/4p3/2Pp4/8/1n3PP1/7P/6K1 w - - 0 39", "Execute the precise endgame technique with c5c6 to secure victory.", "Look for the key move c5c6.", listOf("c5c6", "b3d2", "c6c7")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/8/8/8/p3N3/1k2K3/8/8 b - - 1 71", "Execute the precise endgame technique with a4a3 to secure victory.", "Look for the key move a4a3.", listOf("a4a3", "e3d4", "a3a2")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/8/4k3/4P3/1PNK1pPp/5P2/8/8 b - - 2 42", "Execute the precise endgame technique with h4h3 to secure victory.", "Look for the key move h4h3.", listOf("h4h3", "c4b2", "h3h2", "b2d3", "h2h1q")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/8/8/1k1pK3/3P2P1/2n5/8/8 w - - 1 46", "Execute the precise endgame technique with g4g5 to secure victory.", "Look for the key move g4g5.", listOf("g4g5", "c3e4", "g5g6")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/8/2k4K/4n1P1/8/4P3/8/8 w - - 1 57", "Execute the precise endgame technique with g5g6 to secure victory.", "Look for the key move g5g6.", listOf("g5g6", "c6d5", "g6g7")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "2n5/3k2p1/3p3p/3Kp2P/1N2P1P1/3P4/8/8 b - - 0 54", "Execute the precise endgame technique with c8b6 to secure victory.", "Look for the key move c8b6.", listOf("c8b6")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "4kn2/6K1/5P2/5P2/8/8/8/8 w - - 7 54", "Execute the precise endgame technique with f6f7 to secure victory.", "Look for the key move f6f7.", listOf("f6f7", "e8e7", "f5f6", "e7e6", "g7f8")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/8/1p1k1n2/4p2p/1PP2pp1/3K4/3N1PPP/8 w - - 0 38", "Execute the precise endgame technique with d2e4 to secure victory.", "Look for the key move d2e4.", listOf("d2e4", "f6e4", "d3e4")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/2p5/p2k1n2/1p6/2p2P2/P4K2/1PPN4/8 w - - 0 34", "Execute the precise endgame technique with d2e4 to secure victory.", "Look for the key move d2e4.", listOf("d2e4", "f6e4", "f3e4")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "1k6/8/8/6p1/1pp4P/p4PP1/N1P5/7K b - - 0 38", "Execute the precise endgame technique with b4b3 to secure victory.", "Look for the key move b4b3.", listOf("b4b3", "c2b3", "c4b3")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/8/4kn1N/2Kp4/3P2p1/5pP1/5P2/8 b - - 2 55", "Execute the precise endgame technique with f6e4 to secure victory.", "Look for the key move f6e4.", listOf("f6e4", "c5c6", "e4f2")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/pp1k4/3P2P1/2PK1n2/P5N1/7p/8/8 b - - 4 44", "Execute the precise endgame technique with f5e3 to secure victory.", "Look for the key move f5e3.", listOf("f5e3", "g4e3", "h3h2", "d5d4", "h2h1q")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/1p1k1n1p/p7/4PPP1/P4K2/2P5/8/8 w - - 1 44", "Execute the precise endgame technique with e5e6 to secure victory.", "Look for the key move e5e6.", listOf("e5e6", "d7e7", "e6f7")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/4NP1p/8/6p1/6n1/5k1K/7P/8 b - - 0 51", "Execute the precise endgame technique with g4f2 to secure victory.", "Look for the key move g4f2.", listOf("g4f2")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/7p/4k3/4N1p1/P5P1/1pP5/4K3/8 b - - 0 51", "Execute the precise endgame technique with b3b2 to secure victory.", "Look for the key move b3b2.", listOf("b3b2", "e5f3", "b2b1q")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/8/5p1N/6pP/4n1P1/1p4k1/8/2K5 w - - 3 67", "Execute the precise endgame technique with h6f5 to secure victory.", "Look for the key move h6f5.", listOf("h6f5", "g3g4", "h5h6", "g4f5", "h6h7")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/8/8/P1k3p1/3p4/3K1p1P/2N5/8 b - - 3 53", "Execute the precise endgame technique with f3f2 to secure victory.", "Look for the key move f3f2.", listOf("f3f2", "d3e2", "d4d3", "e2d3", "f2f1q")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/8/k7/P7/2p1N1K1/7p/8/8 b - - 0 59", "Execute the precise endgame technique with h3h2 to secure victory.", "Look for the key move h3h2.", listOf("h3h2", "e4f2", "c4c3")),
        RawLesson("Fork Technique in Knight Endgame", "8/p6p/1p1k4/1P1pppP1/N6P/3K1N2/5Pn1/8 b - - 3 37", "Execute the precise endgame technique with e5e4 to secure victory.", "Look for the key move e5e4.", listOf("e5e4", "d3d4", "e4f3")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "2k5/2n5/1p1n4/1p1PN3/2pKN3/P1P5/1P6/8 b - - 4 51", "Execute the precise endgame technique with d6f5 to secure victory.", "Look for the key move d6f5.", listOf("d6f5")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/3K1kp1/7p/4P2P/3nN3/8/8/8 w - - 5 52", "Execute the precise endgame technique with e4d6 to secure victory.", "Look for the key move e4d6.", listOf("e4d6", "f7f8", "e5e6", "d4e6", "d7e6")),
        RawLesson("Defensive Move Technique in Knight Endgame", "8/7p/p1k2p2/1p2pP2/1P2P2P/3NK3/8/3n4 w - - 4 46", "Execute the precise endgame technique with e3d2 to secure victory.", "Look for the key move e3d2.", listOf("e3d2", "d1e3", "d2e3")),
        RawLesson("Attraction Technique in Knight Endgame", "8/8/5k2/5pnp/8/5NKP/6P1/8 w - - 12 67", "Execute the precise endgame technique with f3g5 to secure victory.", "Look for the key move f3g5.", listOf("f3g5", "f6g5", "h3h4", "g5f6", "g3f4")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/8/6p1/1p1k1p1p/1Pn2P1P/2K1N3/5P2/8 b - - 7 46", "Execute the precise endgame technique with c4e3 to secure victory.", "Look for the key move c4e3.", listOf("c4e3", "f2e3", "d5e4", "c3d2", "e4f3")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/3k4/7p/p1KPPp1P/2p2N2/P1P5/1n6/8 b - - 2 50", "Execute the precise endgame technique with b2d3 to secure victory.", "Look for the key move b2d3.", listOf("b2d3", "c5c4", "d3f4")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/1P6/2n5/p1P5/P2p1K2/7k/8/8 w - - 0 63", "Execute the precise endgame technique with f4e4 to secure victory.", "Look for the key move f4e4.", listOf("f4e4", "h3g4", "e4d5", "d4d3", "d5c6")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/6p1/7p/4N3/8/2kpp2P/6P1/3K4 b - - 3 52", "Execute the precise endgame technique with e3e2 to secure victory.", "Look for the key move e3e2.", listOf("e3e2", "d1e1", "d3d2", "e1e2", "c3c2")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/8/8/8/1pN5/pP1P4/k1P2PP1/3K4 b - - 1 41", "Execute the precise endgame technique with a2b1 to secure victory.", "Look for the key move a2b1.", listOf("a2b1", "c2c3", "a3a2", "c3b4", "a2a1q")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/6p1/4k3/6KP/3nP3/3P1P2/8/8 w - - 1 51", "Execute the precise endgame technique with g5g6 to secure victory.", "Look for the key move g5g6.", listOf("g5g6", "d4f3", "g6g7")),
        RawLesson("Fork Technique in Knight Endgame", "8/2p3pp/N1p3p1/1n1k4/8/1P5P/2P2PP1/3K4 w - - 1 34", "Execute the precise endgame technique with c2c4 to secure victory.", "Look for the key move c2c4.", listOf("c2c4", "d5d6", "c4b5")),
        RawLesson("Defensive Move Technique in Knight Endgame", "8/1p3p1p/2k2Np1/5p2/3P4/5P2/7P/7K b - - 2 39", "Execute the precise endgame technique with b7b5 to secure victory.", "Look for the key move b7b5.", listOf("b7b5", "h1g2", "b5b4")),
        RawLesson("Deflection Technique in Knight Endgame", "2K5/7p/3Pk1p1/1N2np2/p1P5/6P1/5PP1/8 b - - 0 40", "Execute the precise endgame technique with e5c4 to secure victory.", "Look for the key move e5c4.", listOf("e5c4", "d6d7", "c4b6", "c8b7", "b6d7")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "1n6/1P6/5pp1/2k5/6PP/4P3/3K4/8 w - - 1 52", "Execute the precise endgame technique with h4h5 to secure victory.", "Look for the key move h4h5.", listOf("h4h5", "g6h5", "g4h5", "c5d6", "h5h6")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/1n6/1P3N2/3p4/pP3p1k/P4Kp1/8/8 w - - 2 61", "Execute the precise endgame technique with f6d5 to secure victory.", "Look for the key move f6d5.", listOf("f6d5", "g3g2", "f3g2")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/1p4k1/6P1/1p1KN3/1Pn5/8/8/8 w - - 1 68", "Execute the precise endgame technique with e5c4 to secure victory.", "Look for the key move e5c4.", listOf("e5c4", "b5c4", "d5c4")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/8/N1k5/p7/2p5/1P4P1/P6P/6K1 b - - 0 51", "Execute the precise endgame technique with c4c3 to secure victory.", "Look for the key move c4c3.", listOf("c4c3", "b3b4", "c3c2")),
        RawLesson("Defensive Move Technique in Knight Endgame", "8/2p5/pp1p4/3P1N2/PPP1Pp2/5n1p/5K1k/8 w - - 0 47", "Execute the precise endgame technique with f2f3 to secure victory.", "Look for the key move f2f3.", listOf("f2f3", "h2g1", "f3f4", "h3h2", "f5g3")),
        RawLesson("Defensive Move Technique in Knight Endgame", "k7/P7/1K6/1N6/2n5/4p1p1/8/8 w - - 2 60", "Execute the precise endgame technique with b6a6 to secure victory.", "Look for the key move b6a6.", listOf("b6a6", "e3e2", "b5c7")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/2Nn2pk/3P3p/8/pp6/8/P3K1PP/8 b - - 1 39", "Execute the precise endgame technique with b4b3 to secure victory.", "Look for the key move b4b3.", listOf("b4b3", "a2b3", "a4a3", "c7e6", "a3a2")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/2p5/1p1pNPk1/p1nP4/P1P2K2/8/8/8 b - - 3 48", "Execute the precise endgame technique with c5e6 to secure victory.", "Look for the key move c5e6.", listOf("c5e6", "d5e6", "g6f6")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/5n2/p3p3/1p6/1Pk3N1/2P4p/P2K1P2/8 w - - 0 45", "Execute the precise endgame technique with g4e3 to secure victory.", "Look for the key move g4e3.", listOf("g4e3")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/2P1n3/6PK/3p1k2/8/1p6/8/8 w - - 0 45", "Execute the precise endgame technique with g6g7 to secure victory.", "Look for the key move g6g7.", listOf("g6g7", "f5e5", "c7c8q", "e7c8", "g7g8q")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/8/7p/N5p1/1p4P1/2kp3P/8/2K5 b - - 1 42", "Execute the precise endgame technique with b4b3 to secure victory.", "Look for the key move b4b3.", listOf("b4b3", "a5b3", "c3b3")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/8/1p1k1n2/p2p1P2/PP1K1N2/8/8/8 w - - 0 49", "Execute the precise endgame technique with b4b5 to secure victory.", "Look for the key move b4b5.", listOf("b4b5", "f6e4", "f4d5")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "7k/5p1p/2p1p1p1/p1K1P3/P1N4n/8/8/8 w - - 0 47", "Execute the precise endgame technique with c4a5 to secure victory.", "Look for the key move c4a5.", listOf("c4a5", "h4f3", "a5c6")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/n7/P7/K2k4/P7/7P/5PP1/8 b - - 1 53", "Execute the precise endgame technique with d5c5 to secure victory.", "Look for the key move d5c5.", listOf("d5c5", "f2f3", "a7c6")),
        RawLesson("Defensive Move Technique in Knight Endgame", "8/2p3k1/1p1p2Pn/p2P1K1P/P1P5/1P6/8/8 w - - 3 54", "Execute the precise endgame technique with f5e6 to secure victory.", "Look for the key move f5e6.", listOf("f5e6", "h6g8", "e6d7")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/p7/1p6/3n1P2/1P1k1K2/P2pN3/8/8 w - - 5 56", "Execute the precise endgame technique with e3d5 to secure victory.", "Look for the key move e3d5.", listOf("e3d5", "d4d5", "f4e3", "d3d2", "e3d2")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/8/4k2p/6pP/3KP1n1/4N3/8/8 b - - 1 47", "Execute the precise endgame technique with g4e3 to secure victory.", "Look for the key move g4e3.", listOf("g4e3", "d4e3", "e6e5", "e3f3", "e5d4")),
        RawLesson("Deflection Technique in Knight Endgame", "8/p5p1/5pp1/5k2/PpNP1n1P/5K2/5P2/8 w - - 0 43", "Execute the precise endgame technique with c4e3 to secure victory.", "Look for the key move c4e3.", listOf("c4e3", "f5e6", "f3f4", "g6g5", "h4g5")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "4k3/8/8/4N3/1pPP4/p6P/2Kn2P1/8 b - - 3 51", "Execute the precise endgame technique with b4b3 to secure victory.", "Look for the key move b4b3.", listOf("b4b3", "c2d2", "a3a2", "d2c3", "a2a1q")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/1p6/1p1pN3/1P1Pn2k/P1PK1p2/8/8/8 w - - 2 62", "Execute the precise endgame technique with e6f4 to secure victory.", "Look for the key move e6f4.", listOf("e6f4", "h5g5", "f4e6", "g5f6", "e6d8", "f6e7", "d8b7")),
        RawLesson("Defensive Move Technique in Knight Endgame", "8/8/8/5p2/4pKp1/7k/8/6N1 b - - 11 60", "Execute the precise endgame technique with h3g2 to secure victory.", "Look for the key move h3g2.", listOf("h3g2", "g1e2", "g2f2", "e2c3", "g4g3")),
        RawLesson("Hanging Piece Technique in Knight Endgame", "6k1/1p4P1/5K2/2P1n3/8/p3N3/8/8 w - - 0 57", "Execute the precise endgame technique with f6e5 to secure victory.", "Look for the key move f6e5.", listOf("f6e5", "a3a2", "e3c2", "a2a1q", "c2a1")),
        RawLesson("Hanging Piece Technique in Knight Endgame", "8/1p6/p2k1N2/8/2Pn2p1/PP2Kp2/8/8 w - - 2 47", "Execute the precise endgame technique with e3d4 to secure victory.", "Look for the key move e3d4.", listOf("e3d4", "f3f2", "f6e4")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/8/8/7P/2n5/2K3P1/p1N5/1k6 b - - 0 63", "Execute the precise endgame technique with c4e3 to secure victory.", "Look for the key move c4e3.", listOf("c4e3", "c2e3", "a2a1q")),
        RawLesson("Defensive Move Technique in Knight Endgame", "5k2/8/1p1p2K1/3P4/3n1N1P/8/8/8 w - - 4 59", "Execute the precise endgame technique with f4e6 to secure victory.", "Look for the key move f4e6.", listOf("f4e6", "d4e6", "d5e6", "f8e7", "h4h5")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/8/1n4kP/1P2Kp2/3P4/2p1N3/8/8 b - - 5 49", "Execute the precise endgame technique with b6c4 to secure victory.", "Look for the key move b6c4.", listOf("b6c4", "e3c4", "c3c2")),
        RawLesson("Deflection Technique in Knight Endgame", "8/p7/1p3N1P/4n1K1/1k1p4/1P6/1P6/8 b - - 0 49", "Execute the precise endgame technique with e5f7 to secure victory.", "Look for the key move e5f7.", listOf("e5f7", "g5f4", "f7h6")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/k1p5/P7/3N4/3P2pp/4K1n1/6P1/8 b - - 2 47", "Execute the precise endgame technique with h4h3 to secure victory.", "Look for the key move h4h3.", listOf("h4h3", "e3f2", "h3h2", "f2g3", "h2h1q")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/8/N7/4kp2/2P4p/1P3K2/P1n2P2/8 b - - 1 46", "Execute the precise endgame technique with c2e1 to secure victory.", "Look for the key move c2e1.", listOf("c2e1", "f3e2", "h4h3")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/2n5/3K4/Pkp5/2N5/8/8/8 b - - 8 57", "Execute the precise endgame technique with b5c4 to secure victory.", "Look for the key move b5c4.", listOf("b5c4", "d6c7", "c4b5", "a5a6", "b5a6")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/8/7P/3N4/k1nP4/1p6/1p6/1K6 b - - 0 57", "Execute the precise endgame technique with a4a3 to secure victory.", "Look for the key move a4a3.", listOf("a4a3", "h6h7", "c4d2")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/8/3p1p2/p2P1kP1/P7/2N2K2/1p6/8 b - - 0 50", "Execute the precise endgame technique with f6g5 to secure victory.", "Look for the key move f6g5.", listOf("f6g5", "c3b1", "f5e5")),
        RawLesson("Defensive Move Technique in Knight Endgame", "8/1k6/5K2/8/2n4P/8/8/8 w - - 0 56", "Execute the precise endgame technique with h4h5 to secure victory.", "Look for the key move h4h5.", listOf("h4h5", "c4e3", "f6g5")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/n6p/P1pk1pp1/5P2/1PPK2P1/7P/8/8 w - - 0 37", "Execute the precise endgame technique with f5g6 to secure victory.", "Look for the key move f5g6.", listOf("f5g6", "h7g6", "h3h4", "c6c5", "b4c5")),
        RawLesson("Hanging Piece Technique in Knight Endgame", "8/8/1n2P3/8/pNk3K1/6P1/1P6/8 b - - 0 66", "Execute the precise endgame technique with c4b4 to secure victory.", "Look for the key move c4b4.", listOf("c4b4", "e6e7", "b6d5")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/p2n1k2/5P2/5KPP/2P5/2n5/8/8 w - - 2 50", "Execute the precise endgame technique with g5g6 to secure victory.", "Look for the key move g5g6.", listOf("g5g6", "f7f8", "h5h6")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/3k2p1/1p1Np2p/1P2PnPP/8/5K2/8/8 w - - 5 46", "Execute the precise endgame technique with d6f5 to secure victory.", "Look for the key move d6f5.", listOf("d6f5", "e6f5", "f3f4", "d7e6", "g5g6", "e6e7", "f4f5")),
        RawLesson("Defensive Move Technique in Knight Endgame", "8/1p1k4/4p3/3pPn2/1PpN1P2/2P5/2K5/8 w - - 1 37", "Execute the precise endgame technique with d4f5 to secure victory.", "Look for the key move d4f5.", listOf("d4f5", "e6f5", "c2d2", "d5d4", "c3d4", "b7b5", "d4d5")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/8/3N4/5pk1/1PpP4/2n2K2/8/8 w - - 0 57", "Execute the precise endgame technique with b4b5 to secure victory.", "Look for the key move b4b5.", listOf("b4b5", "c3d5", "d6c4", "d5c7", "b5b6")),
        RawLesson("Defensive Move Technique in Knight Endgame", "8/8/8/1p4p1/6Pp/4NK2/Pk6/8 b - - 6 45", "Execute the precise endgame technique with b2a2 to secure victory.", "Look for the key move b2a2.", listOf("b2a2", "e3c2", "a2b2", "c2d4", "b5b4")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/2p5/pp1p4/P2Pk2p/1PP1p2P/2n1K2P/3N4/8 w - - 0 46", "Execute the precise endgame technique with b4b5 to secure victory.", "Look for the key move b4b5.", listOf("b4b5", "c3d1", "e3e2", "a6b5", "a5a6")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/8/8/1k6/1p2K3/2pN4/8/8 b - - 1 69", "Execute the precise endgame technique with b4b3 to secure victory.", "Look for the key move b4b3.", listOf("b4b3", "e4d4", "b3b2", "d4c3", "b2b1q")),
        RawLesson("Fork Technique in Knight Endgame", "8/8/5kpp/5p2/3n1K1P/5PN1/6P1/8 b - - 0 52", "Execute the precise endgame technique with d4e6 to secure victory.", "Look for the key move d4e6.", listOf("d4e6", "f4e3", "f5f4", "e3f2", "f4g3")),
        RawLesson("Defensive Move Technique in Knight Endgame", "8/8/8/4k3/6p1/6P1/5NK1/3n4 b - - 3 47", "Execute the precise endgame technique with d1f2 to secure victory.", "Look for the key move d1f2.", listOf("d1f2", "g2f2", "e5d4", "f2e2", "d4e4")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/8/3p4/3Pp1n1/4Pp1N/3k1P1P/6K1/8 b - - 0 57", "Execute the precise endgame technique with d3e2 to secure victory.", "Look for the key move d3e2.", listOf("d3e2", "h4f5", "g5f3")),
        RawLesson("Hanging Piece Technique in Knight Endgame", "8/1n6/1PK5/8/2N3k1/8/5p2/8 w - - 0 53", "Execute the precise endgame technique with c6b7 to secure victory.", "Look for the key move c6b7.", listOf("c6b7", "g4f3", "c4d2", "f3e2", "d2e4")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/k2n4/3p1p1p/K2PpPp1/N3P1P1/7P/8/8 w - - 3 49", "Execute the precise endgame technique with a4c3 to secure victory.", "Look for the key move a4c3.", listOf("a4c3", "a7b8", "c3b5", "d7c5", "b5d6", "b8c7", "d6e8")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/8/6p1/p1N1p3/P1PpPkPp/3P3P/1P6/2K1n3 b - - 2 49", "Execute the precise endgame technique with f4g3 to secure victory.", "Look for the key move f4g3.", listOf("f4g3", "b2b4", "g3h3")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/p7/1p3k2/2n5/2K1P3/3N4/P7/8 w - - 1 45", "Execute the precise endgame technique with d3c5 to secure victory.", "Look for the key move d3c5.", listOf("d3c5", "b6c5", "c4d5")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "6n1/6P1/6N1/8/1p5P/1k6/5K2/8 b - - 0 58", "Execute the precise endgame technique with b3c3 to secure victory.", "Look for the key move b3c3.", listOf("b3c3", "h4h5", "b4b3", "g6e5", "b3b2")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "2n5/2k5/P7/3Pp3/5pP1/5P2/5K2/8 w - - 0 51", "Execute the precise endgame technique with g4g5 to secure victory.", "Look for the key move g4g5.", listOf("g4g5", "c8b6", "a6a7", "c7b7", "d5d6", "b6d7", "g5g6", "b7a7", "g6g7", "a7b6", "g7g8q")),
        RawLesson("Defensive Move Technique in Knight Endgame", "8/8/4p3/3p4/3K1pk1/8/8/4N3 b - - 7 59", "Execute the precise endgame technique with g4g3 to secure victory.", "Look for the key move g4g3.", listOf("g4g3", "e1d3", "f4f3", "d4e3", "e6e5")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/8/4Kpp1/7p/3N2kP/8/8/8 b - - 3 62", "Execute the precise endgame technique with g6g5 to secure victory.", "Look for the key move g6g5.", listOf("g6g5", "h4g5", "f6g5", "e6d5", "h5h4", "d5e4", "h4h3", "d4f3", "g4g3")),
        RawLesson("Defensive Move Technique in Knight Endgame", "8/2n1k3/8/1K6/P7/8/1P6/8 w - - 1 58", "Execute the precise endgame technique with b5c6 to secure victory.", "Look for the key move b5c6.", listOf("b5c6", "e7d8", "c6b7", "d8d7", "a4a5")),
        RawLesson("Defensive Move Technique in Knight Endgame", "8/3k4/1p1n4/p2N4/2P1p3/PP2K3/8/8 w - - 5 47", "Execute the precise endgame technique with d5b6 to secure victory.", "Look for the key move d5b6.", listOf("d5b6", "d7c6", "b6a4")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/5kp1/4p2p/4Pn1K/8/4N3/6PP/8 w - - 3 36", "Execute the precise endgame technique with e3f5 to secure victory.", "Look for the key move e3f5.", listOf("e3f5", "e6f5", "h2h4", "f5f4", "h5g4")),
        RawLesson("Defensive Move Technique in Knight Endgame", "8/8/Pk6/1pnP3p/1N1K1ppP/8/6P1/8 w - - 0 44", "Execute the precise endgame technique with d5d6 to secure victory.", "Look for the key move d5d6.", listOf("d5d6", "c5d7", "d4e4", "f4f3", "g2f3", "g4g3", "e4e3", "d7e5", "e3e2")),
        RawLesson("Defensive Move Technique in Knight Endgame", "8/8/8/3kn1K1/7P/6P1/8/8 w - - 5 58", "Execute the precise endgame technique with g5f5 to secure victory.", "Look for the key move g5f5.", listOf("g5f5", "e5c4", "h4h5", "c4d6", "f5g6")),
        RawLesson("Defensive Move Technique in Knight Endgame", "8/p4p2/1p6/8/2k1K1P1/8/N7/8 b - - 2 54", "Execute the precise endgame technique with a7a5 to secure victory.", "Look for the key move a7a5.", listOf("a7a5", "e4e3", "c4b3", "a2c1", "b3c2", "c1d3", "c2c3", "d3f4", "a5a4")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "4k3/8/3KP3/5P2/8/5n2/5P2/8 w - - 8 53", "Execute the precise endgame technique with f5f6 to secure victory.", "Look for the key move f5f6.", listOf("f5f6", "e8f8", "f6f7", "f3h4", "e6e7", "f8f7", "d6d7")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/6K1/5p1p/6nP/2k1Pp2/5P2/6P1/8 w - - 1 51", "Execute the precise endgame technique with g7h6 to secure victory.", "Look for the key move g7h6.", listOf("g7h6", "c4d4", "h6g7", "d4e5", "h5h6", "e5e6", "h6h7", "g5h7", "g7h7")),
        RawLesson("Defensive Move Technique in Knight Endgame", "8/8/8/2p1p3/2N2k2/5p2/8/5K2 b - - 1 57", "Execute the precise endgame technique with f4e4 to secure victory.", "Look for the key move f4e4.", listOf("f4e4", "f1f2", "e4d4", "c4d2", "e5e4", "d2b3", "d4c4")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/p7/1p1Pk1p1/8/4KPPP/8/nP6/8 w - - 1 36", "Execute the precise endgame technique with h4h5 to secure victory.", "Look for the key move h4h5.", listOf("h4h5", "g6h5", "g4h5", "a2b4", "d6d7", "b4c6", "h5h6", "e6f7", "e4d5")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "2k5/2p2p2/P1N3p1/1PK2n2/8/7p/2P2P2/8 w - - 0 42", "Execute the precise endgame technique with b5b6 to secure victory.", "Look for the key move b5b6.", listOf("b5b6", "f5d6", "c6e7", "c8d7", "a6a7")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/8/P1k4p/K2p1pp1/3P4/1N5P/6n1/8 w - - 0 45", "Execute the precise endgame technique with b3c5 to secure victory.", "Look for the key move b3c5.", listOf("b3c5", "g2e3", "a6a7", "e3c4", "a5a6", "c4b6", "c5d3")),
        RawLesson("Knight Endgame Technique in Knight Endgame", "8/4k3/4p3/3pP2K/3P2PP/4P3/8/3n4 b - - 0 41", "Execute the precise endgame technique with e7f7 to secure victory.", "Look for the key move e7f7.", listOf("e7f7", "h5g5", "d1e3")),
        RawLesson("Advanced Pawn Technique in Knight Endgame", "8/2p3p1/p7/P7/3NP1k1/1P1Pn2p/8/6K1 b - - 0 39", "Execute the precise endgame technique with c7c5 to secure victory.", "Look for the key move c7c5.", listOf("c7c5", "d4e6", "g4g3", "e6g7", "h3h2", "g1h1", "e3d1", "g7h5", "g3g4", "h1h2", "g4h5"))
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
                id = "e_knight_endgame_$level",
                title = "Level $level ($tier difficulty) • ${base.subtitle}",
                category = "Knight Endgame",
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
