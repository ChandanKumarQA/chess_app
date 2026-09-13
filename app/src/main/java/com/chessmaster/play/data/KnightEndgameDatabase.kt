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
        RawLesson("Knight Stopping Passed Pawn", "8/8/4k3/8/3p4/8/5N2/4K3 w - - 0 1", "Use your Knight to blockade the passed pawn! Play Nf2-d3, then march your King with Ke1-d2.", "Blockade with Nd3, then bring Kd2.", listOf("f2d3", "e6d5", "e1d2")),
        RawLesson("Knight Outpost & Centralization", "8/8/3k4/8/3N4/8/4K3/8 w - - 0 1", "Reposition your Knight with Nd4-f5+! Then step back to the ideal central outpost e3.", "Check with Nf5+, then centralize Ne3.", listOf("d4f5", "d6e6", "f5e3")),
        RawLesson("Knight Central Dominance", "8/8/4k3/8/4N3/8/4K3/8 w - - 0 1", "Reposition the Knight to c5! Take key squares and retreat to b3.", "Play Nc5 then Nb3.", listOf("e4c5", "e6d5", "c5b3")),
        RawLesson("Knight File Barrier", "8/8/3k4/8/8/3N4/4K3/8 w - - 0 1", "Deploy the Knight to f4! Guard e6 and maintain a solid wall with Ke2-e3.", "Play Nf4 then Ke3.", listOf("d3f4", "d6e5", "e2e3")),
        RawLesson("Knight Corner Domination", "8/8/8/8/8/3N4/4K3/7k w - - 0 1", "Confine the King to the h1 corner with Nd3-f2+! Step forward with Ke2-f3.", "Play Nf2+ then Kf3.", listOf("d3f2", "h1g1", "e2f3")),
        RawLesson("Knight Outpost Jump", "8/8/4k3/8/8/4N3/4K3/8 w - - 0 1", "Jump into the center with Ne3-d5! Support your Knight with Ke2-d3.", "Play Nd5 then Kd3.", listOf("e3d5", "e6d6", "e2d3")),
        RawLesson("Knight Stopping Passed Pawn (Var 7)", "8/8/4k3/8/3p4/8/P4N2/4K3 w - - 0 1", "Use your Knight to blockade the passed pawn! Play Nf2-d3, then march your King with Ke1-d2.", "Blockade with Nd3, then bring Kd2.", listOf("f2d3", "e6d5", "e1d2")),
        RawLesson("Knight Stopping Passed Pawn (Var 8)", "8/8/4k3/8/3p4/8/1P3N2/4K3 w - - 0 1", "Use your Knight to blockade the passed pawn! Play Nf2-d3, then march your King with Ke1-d2.", "Blockade with Nd3, then bring Kd2.", listOf("f2d3", "e6d5", "e1d2")),
        RawLesson("Knight Stopping Passed Pawn (Var 9)", "8/8/4k3/8/3p4/8/2P2N2/4K3 w - - 0 1", "Use your Knight to blockade the passed pawn! Play Nf2-d3, then march your King with Ke1-d2.", "Blockade with Nd3, then bring Kd2.", listOf("f2d3", "e6d5", "e1d2")),
        RawLesson("Knight Stopping Passed Pawn (Var 10)", "8/8/4k3/8/3p4/8/5N1P/4K3 w - - 0 1", "Use your Knight to blockade the passed pawn! Play Nf2-d3, then march your King with Ke1-d2.", "Blockade with Nd3, then bring Kd2.", listOf("f2d3", "e6d5", "e1d2")),
        RawLesson("Knight Stopping Passed Pawn (Var 11)", "8/8/4k3/8/3p4/8/5NP1/4K3 w - - 0 1", "Use your Knight to blockade the passed pawn! Play Nf2-d3, then march your King with Ke1-d2.", "Blockade with Nd3, then bring Kd2.", listOf("f2d3", "e6d5", "e1d2")),
        RawLesson("Knight Stopping Passed Pawn (Var 12)", "8/p7/4k3/8/3p4/8/5N2/4K3 w - - 0 1", "Use your Knight to blockade the passed pawn! Play Nf2-d3, then march your King with Ke1-d2.", "Blockade with Nd3, then bring Kd2.", listOf("f2d3", "e6d5", "e1d2")),
        RawLesson("Knight Stopping Passed Pawn (Var 13)", "8/1p6/4k3/8/3p4/8/5N2/4K3 w - - 0 1", "Use your Knight to blockade the passed pawn! Play Nf2-d3, then march your King with Ke1-d2.", "Blockade with Nd3, then bring Kd2.", listOf("f2d3", "e6d5", "e1d2")),
        RawLesson("Knight Stopping Passed Pawn (Var 14)", "8/2p5/4k3/8/3p4/8/5N2/4K3 w - - 0 1", "Use your Knight to blockade the passed pawn! Play Nf2-d3, then march your King with Ke1-d2.", "Blockade with Nd3, then bring Kd2.", listOf("f2d3", "e6d5", "e1d2")),
        RawLesson("Knight Stopping Passed Pawn (Var 15)", "8/7p/4k3/8/3p4/8/5N2/4K3 w - - 0 1", "Use your Knight to blockade the passed pawn! Play Nf2-d3, then march your King with Ke1-d2.", "Blockade with Nd3, then bring Kd2.", listOf("f2d3", "e6d5", "e1d2")),
        RawLesson("Knight Stopping Passed Pawn (Var 16)", "8/6p1/4k3/8/3p4/8/5N2/4K3 w - - 0 1", "Use your Knight to blockade the passed pawn! Play Nf2-d3, then march your King with Ke1-d2.", "Blockade with Nd3, then bring Kd2.", listOf("f2d3", "e6d5", "e1d2")),
        RawLesson("Knight Stopping Passed Pawn (Var 17)", "8/5p2/4k3/8/3p4/8/5N2/4K3 w - - 0 1", "Use your Knight to blockade the passed pawn! Play Nf2-d3, then march your King with Ke1-d2.", "Blockade with Nd3, then bring Kd2.", listOf("f2d3", "e6d5", "e1d2")),
        RawLesson("Knight Stopping Passed Pawn (Var 18)", "8/8/4k3/8/3p4/P7/5N2/4K3 w - - 0 1", "Use your Knight to blockade the passed pawn! Play Nf2-d3, then march your King with Ke1-d2.", "Blockade with Nd3, then bring Kd2.", listOf("f2d3", "e6d5", "e1d2")),
        RawLesson("Knight Stopping Passed Pawn (Var 19)", "8/8/4k3/8/3p4/7P/5N2/4K3 w - - 0 1", "Use your Knight to blockade the passed pawn! Play Nf2-d3, then march your King with Ke1-d2.", "Blockade with Nd3, then bring Kd2.", listOf("f2d3", "e6d5", "e1d2")),
        RawLesson("Knight Stopping Passed Pawn (Var 20)", "8/8/p3k3/8/3p4/8/5N2/4K3 w - - 0 1", "Use your Knight to blockade the passed pawn! Play Nf2-d3, then march your King with Ke1-d2.", "Blockade with Nd3, then bring Kd2.", listOf("f2d3", "e6d5", "e1d2")),
        RawLesson("Knight Stopping Passed Pawn (Var 21)", "8/8/4k2p/8/3p4/8/5N2/4K3 w - - 0 1", "Use your Knight to blockade the passed pawn! Play Nf2-d3, then march your King with Ke1-d2.", "Blockade with Nd3, then bring Kd2.", listOf("f2d3", "e6d5", "e1d2")),
        RawLesson("Knight Outpost & Centralization (Var 22)", "8/8/3k4/8/3N4/8/P3K3/8 w - - 0 1", "Reposition your Knight with Nd4-f5+! Then step back to the ideal central outpost e3.", "Check with Nf5+, then centralize Ne3.", listOf("d4f5", "d6e6", "f5e3")),
        RawLesson("Knight Outpost & Centralization (Var 23)", "8/8/3k4/8/3N4/8/1P2K3/8 w - - 0 1", "Reposition your Knight with Nd4-f5+! Then step back to the ideal central outpost e3.", "Check with Nf5+, then centralize Ne3.", listOf("d4f5", "d6e6", "f5e3")),
        RawLesson("Knight Outpost & Centralization (Var 24)", "8/8/3k4/8/3N4/8/2P1K3/8 w - - 0 1", "Reposition your Knight with Nd4-f5+! Then step back to the ideal central outpost e3.", "Check with Nf5+, then centralize Ne3.", listOf("d4f5", "d6e6", "f5e3")),
        RawLesson("Knight Outpost & Centralization (Var 25)", "8/8/3k4/8/3N4/8/4K2P/8 w - - 0 1", "Reposition your Knight with Nd4-f5+! Then step back to the ideal central outpost e3.", "Check with Nf5+, then centralize Ne3.", listOf("d4f5", "d6e6", "f5e3")),
        RawLesson("Knight Outpost & Centralization (Var 26)", "8/8/3k4/8/3N4/8/4K1P1/8 w - - 0 1", "Reposition your Knight with Nd4-f5+! Then step back to the ideal central outpost e3.", "Check with Nf5+, then centralize Ne3.", listOf("d4f5", "d6e6", "f5e3")),
        RawLesson("Knight Outpost & Centralization (Var 27)", "8/8/3k4/8/3N4/8/4KP2/8 w - - 0 1", "Reposition your Knight with Nd4-f5+! Then step back to the ideal central outpost e3.", "Check with Nf5+, then centralize Ne3.", listOf("d4f5", "d6e6", "f5e3")),
        RawLesson("Knight Outpost & Centralization (Var 28)", "8/p7/3k4/8/3N4/8/4K3/8 w - - 0 1", "Reposition your Knight with Nd4-f5+! Then step back to the ideal central outpost e3.", "Check with Nf5+, then centralize Ne3.", listOf("d4f5", "d6e6", "f5e3")),
        RawLesson("Knight Outpost & Centralization (Var 29)", "8/1p6/3k4/8/3N4/8/4K3/8 w - - 0 1", "Reposition your Knight with Nd4-f5+! Then step back to the ideal central outpost e3.", "Check with Nf5+, then centralize Ne3.", listOf("d4f5", "d6e6", "f5e3")),
        RawLesson("Knight Outpost & Centralization (Var 30)", "8/2p5/3k4/8/3N4/8/4K3/8 w - - 0 1", "Reposition your Knight with Nd4-f5+! Then step back to the ideal central outpost e3.", "Check with Nf5+, then centralize Ne3.", listOf("d4f5", "d6e6", "f5e3")),
        RawLesson("Knight Outpost & Centralization (Var 31)", "8/7p/3k4/8/3N4/8/4K3/8 w - - 0 1", "Reposition your Knight with Nd4-f5+! Then step back to the ideal central outpost e3.", "Check with Nf5+, then centralize Ne3.", listOf("d4f5", "d6e6", "f5e3")),
        RawLesson("Knight Outpost & Centralization (Var 32)", "8/6p1/3k4/8/3N4/8/4K3/8 w - - 0 1", "Reposition your Knight with Nd4-f5+! Then step back to the ideal central outpost e3.", "Check with Nf5+, then centralize Ne3.", listOf("d4f5", "d6e6", "f5e3")),
        RawLesson("Knight Outpost & Centralization (Var 33)", "8/5p2/3k4/8/3N4/8/4K3/8 w - - 0 1", "Reposition your Knight with Nd4-f5+! Then step back to the ideal central outpost e3.", "Check with Nf5+, then centralize Ne3.", listOf("d4f5", "d6e6", "f5e3")),
        RawLesson("Knight Outpost & Centralization (Var 34)", "8/8/3k4/8/3N4/P7/4K3/8 w - - 0 1", "Reposition your Knight with Nd4-f5+! Then step back to the ideal central outpost e3.", "Check with Nf5+, then centralize Ne3.", listOf("d4f5", "d6e6", "f5e3")),
        RawLesson("Knight Outpost & Centralization (Var 35)", "8/8/3k4/8/3N4/7P/4K3/8 w - - 0 1", "Reposition your Knight with Nd4-f5+! Then step back to the ideal central outpost e3.", "Check with Nf5+, then centralize Ne3.", listOf("d4f5", "d6e6", "f5e3")),
        RawLesson("Knight Outpost & Centralization (Var 36)", "8/8/p2k4/8/3N4/8/4K3/8 w - - 0 1", "Reposition your Knight with Nd4-f5+! Then step back to the ideal central outpost e3.", "Check with Nf5+, then centralize Ne3.", listOf("d4f5", "d6e6", "f5e3")),
        RawLesson("Knight Outpost & Centralization (Var 37)", "8/8/3k3p/8/3N4/8/4K3/8 w - - 0 1", "Reposition your Knight with Nd4-f5+! Then step back to the ideal central outpost e3.", "Check with Nf5+, then centralize Ne3.", listOf("d4f5", "d6e6", "f5e3")),
        RawLesson("Knight Central Dominance (Var 38)", "8/8/4k3/8/4N3/8/P3K3/8 w - - 0 1", "Reposition the Knight to c5! Take key squares and retreat to b3.", "Play Nc5 then Nb3.", listOf("e4c5", "e6d5", "c5b3")),
        RawLesson("Knight Central Dominance (Var 39)", "8/8/4k3/8/4N3/8/1P2K3/8 w - - 0 1", "Reposition the Knight to c5! Take key squares and retreat to b3.", "Play Nc5 then Nb3.", listOf("e4c5", "e6d5", "c5b3")),
        RawLesson("Knight Central Dominance (Var 40)", "8/8/4k3/8/4N3/8/2P1K3/8 w - - 0 1", "Reposition the Knight to c5! Take key squares and retreat to b3.", "Play Nc5 then Nb3.", listOf("e4c5", "e6d5", "c5b3")),
        RawLesson("Knight Central Dominance (Var 41)", "8/8/4k3/8/4N3/8/4K2P/8 w - - 0 1", "Reposition the Knight to c5! Take key squares and retreat to b3.", "Play Nc5 then Nb3.", listOf("e4c5", "e6d5", "c5b3")),
        RawLesson("Knight Central Dominance (Var 42)", "8/8/4k3/8/4N3/8/4K1P1/8 w - - 0 1", "Reposition the Knight to c5! Take key squares and retreat to b3.", "Play Nc5 then Nb3.", listOf("e4c5", "e6d5", "c5b3")),
        RawLesson("Knight Central Dominance (Var 43)", "8/8/4k3/8/4N3/8/4KP2/8 w - - 0 1", "Reposition the Knight to c5! Take key squares and retreat to b3.", "Play Nc5 then Nb3.", listOf("e4c5", "e6d5", "c5b3")),
        RawLesson("Knight Central Dominance (Var 44)", "8/p7/4k3/8/4N3/8/4K3/8 w - - 0 1", "Reposition the Knight to c5! Take key squares and retreat to b3.", "Play Nc5 then Nb3.", listOf("e4c5", "e6d5", "c5b3")),
        RawLesson("Knight Central Dominance (Var 45)", "8/1p6/4k3/8/4N3/8/4K3/8 w - - 0 1", "Reposition the Knight to c5! Take key squares and retreat to b3.", "Play Nc5 then Nb3.", listOf("e4c5", "e6d5", "c5b3")),
        RawLesson("Knight Central Dominance (Var 46)", "8/2p5/4k3/8/4N3/8/4K3/8 w - - 0 1", "Reposition the Knight to c5! Take key squares and retreat to b3.", "Play Nc5 then Nb3.", listOf("e4c5", "e6d5", "c5b3")),
        RawLesson("Knight Central Dominance (Var 47)", "8/7p/4k3/8/4N3/8/4K3/8 w - - 0 1", "Reposition the Knight to c5! Take key squares and retreat to b3.", "Play Nc5 then Nb3.", listOf("e4c5", "e6d5", "c5b3")),
        RawLesson("Knight Central Dominance (Var 48)", "8/6p1/4k3/8/4N3/8/4K3/8 w - - 0 1", "Reposition the Knight to c5! Take key squares and retreat to b3.", "Play Nc5 then Nb3.", listOf("e4c5", "e6d5", "c5b3")),
        RawLesson("Knight Central Dominance (Var 49)", "8/5p2/4k3/8/4N3/8/4K3/8 w - - 0 1", "Reposition the Knight to c5! Take key squares and retreat to b3.", "Play Nc5 then Nb3.", listOf("e4c5", "e6d5", "c5b3")),
        RawLesson("Knight Central Dominance (Var 50)", "8/8/4k3/8/4N3/P7/4K3/8 w - - 0 1", "Reposition the Knight to c5! Take key squares and retreat to b3.", "Play Nc5 then Nb3.", listOf("e4c5", "e6d5", "c5b3")),
        RawLesson("Knight Central Dominance (Var 51)", "8/8/4k3/8/4N3/7P/4K3/8 w - - 0 1", "Reposition the Knight to c5! Take key squares and retreat to b3.", "Play Nc5 then Nb3.", listOf("e4c5", "e6d5", "c5b3")),
        RawLesson("Knight Central Dominance (Var 52)", "8/8/p3k3/8/4N3/8/4K3/8 w - - 0 1", "Reposition the Knight to c5! Take key squares and retreat to b3.", "Play Nc5 then Nb3.", listOf("e4c5", "e6d5", "c5b3")),
        RawLesson("Knight Central Dominance (Var 53)", "8/8/4k2p/8/4N3/8/4K3/8 w - - 0 1", "Reposition the Knight to c5! Take key squares and retreat to b3.", "Play Nc5 then Nb3.", listOf("e4c5", "e6d5", "c5b3")),
        RawLesson("Knight File Barrier (Var 54)", "8/8/3k4/8/8/3N4/P3K3/8 w - - 0 1", "Deploy the Knight to f4! Guard e6 and maintain a solid wall with Ke2-e3.", "Play Nf4 then Ke3.", listOf("d3f4", "d6e5", "e2e3")),
        RawLesson("Knight File Barrier (Var 55)", "8/8/3k4/8/8/3N4/1P2K3/8 w - - 0 1", "Deploy the Knight to f4! Guard e6 and maintain a solid wall with Ke2-e3.", "Play Nf4 then Ke3.", listOf("d3f4", "d6e5", "e2e3")),
        RawLesson("Knight File Barrier (Var 56)", "8/8/3k4/8/8/3N4/2P1K3/8 w - - 0 1", "Deploy the Knight to f4! Guard e6 and maintain a solid wall with Ke2-e3.", "Play Nf4 then Ke3.", listOf("d3f4", "d6e5", "e2e3")),
        RawLesson("Knight File Barrier (Var 57)", "8/8/3k4/8/8/3N4/4K2P/8 w - - 0 1", "Deploy the Knight to f4! Guard e6 and maintain a solid wall with Ke2-e3.", "Play Nf4 then Ke3.", listOf("d3f4", "d6e5", "e2e3")),
        RawLesson("Knight File Barrier (Var 58)", "8/8/3k4/8/8/3N4/4K1P1/8 w - - 0 1", "Deploy the Knight to f4! Guard e6 and maintain a solid wall with Ke2-e3.", "Play Nf4 then Ke3.", listOf("d3f4", "d6e5", "e2e3")),
        RawLesson("Knight File Barrier (Var 59)", "8/8/3k4/8/8/3N4/4KP2/8 w - - 0 1", "Deploy the Knight to f4! Guard e6 and maintain a solid wall with Ke2-e3.", "Play Nf4 then Ke3.", listOf("d3f4", "d6e5", "e2e3")),
        RawLesson("Knight File Barrier (Var 60)", "8/p7/3k4/8/8/3N4/4K3/8 w - - 0 1", "Deploy the Knight to f4! Guard e6 and maintain a solid wall with Ke2-e3.", "Play Nf4 then Ke3.", listOf("d3f4", "d6e5", "e2e3")),
        RawLesson("Knight File Barrier (Var 61)", "8/1p6/3k4/8/8/3N4/4K3/8 w - - 0 1", "Deploy the Knight to f4! Guard e6 and maintain a solid wall with Ke2-e3.", "Play Nf4 then Ke3.", listOf("d3f4", "d6e5", "e2e3")),
        RawLesson("Knight File Barrier (Var 62)", "8/2p5/3k4/8/8/3N4/4K3/8 w - - 0 1", "Deploy the Knight to f4! Guard e6 and maintain a solid wall with Ke2-e3.", "Play Nf4 then Ke3.", listOf("d3f4", "d6e5", "e2e3")),
        RawLesson("Knight File Barrier (Var 63)", "8/7p/3k4/8/8/3N4/4K3/8 w - - 0 1", "Deploy the Knight to f4! Guard e6 and maintain a solid wall with Ke2-e3.", "Play Nf4 then Ke3.", listOf("d3f4", "d6e5", "e2e3")),
        RawLesson("Knight File Barrier (Var 64)", "8/6p1/3k4/8/8/3N4/4K3/8 w - - 0 1", "Deploy the Knight to f4! Guard e6 and maintain a solid wall with Ke2-e3.", "Play Nf4 then Ke3.", listOf("d3f4", "d6e5", "e2e3")),
        RawLesson("Knight File Barrier (Var 65)", "8/5p2/3k4/8/8/3N4/4K3/8 w - - 0 1", "Deploy the Knight to f4! Guard e6 and maintain a solid wall with Ke2-e3.", "Play Nf4 then Ke3.", listOf("d3f4", "d6e5", "e2e3")),
        RawLesson("Knight File Barrier (Var 66)", "8/8/3k4/8/8/P2N4/4K3/8 w - - 0 1", "Deploy the Knight to f4! Guard e6 and maintain a solid wall with Ke2-e3.", "Play Nf4 then Ke3.", listOf("d3f4", "d6e5", "e2e3")),
        RawLesson("Knight File Barrier (Var 67)", "8/8/3k4/8/8/3N3P/4K3/8 w - - 0 1", "Deploy the Knight to f4! Guard e6 and maintain a solid wall with Ke2-e3.", "Play Nf4 then Ke3.", listOf("d3f4", "d6e5", "e2e3")),
        RawLesson("Knight File Barrier (Var 68)", "8/8/p2k4/8/8/3N4/4K3/8 w - - 0 1", "Deploy the Knight to f4! Guard e6 and maintain a solid wall with Ke2-e3.", "Play Nf4 then Ke3.", listOf("d3f4", "d6e5", "e2e3")),
        RawLesson("Knight File Barrier (Var 69)", "8/8/3k3p/8/8/3N4/4K3/8 w - - 0 1", "Deploy the Knight to f4! Guard e6 and maintain a solid wall with Ke2-e3.", "Play Nf4 then Ke3.", listOf("d3f4", "d6e5", "e2e3")),
        RawLesson("Knight Corner Domination (Var 70)", "8/8/8/8/8/3N4/P3K3/7k w - - 0 1", "Confine the King to the h1 corner with Nd3-f2+! Step forward with Ke2-f3.", "Play Nf2+ then Kf3.", listOf("d3f2", "h1g1", "e2f3")),
        RawLesson("Knight Corner Domination (Var 71)", "8/8/8/8/8/3N4/1P2K3/7k w - - 0 1", "Confine the King to the h1 corner with Nd3-f2+! Step forward with Ke2-f3.", "Play Nf2+ then Kf3.", listOf("d3f2", "h1g1", "e2f3")),
        RawLesson("Knight Corner Domination (Var 72)", "8/8/8/8/8/3N4/2P1K3/7k w - - 0 1", "Confine the King to the h1 corner with Nd3-f2+! Step forward with Ke2-f3.", "Play Nf2+ then Kf3.", listOf("d3f2", "h1g1", "e2f3")),
        RawLesson("Knight Corner Domination (Var 73)", "8/8/8/8/8/3N4/4K2P/7k w - - 0 1", "Confine the King to the h1 corner with Nd3-f2+! Step forward with Ke2-f3.", "Play Nf2+ then Kf3.", listOf("d3f2", "h1g1", "e2f3")),
        RawLesson("Knight Corner Domination (Var 74)", "8/8/8/8/8/3N4/4K1P1/7k w - - 0 1", "Confine the King to the h1 corner with Nd3-f2+! Step forward with Ke2-f3.", "Play Nf2+ then Kf3.", listOf("d3f2", "h1g1", "e2f3")),
        RawLesson("Knight Corner Domination (Var 75)", "8/p7/8/8/8/3N4/4K3/7k w - - 0 1", "Confine the King to the h1 corner with Nd3-f2+! Step forward with Ke2-f3.", "Play Nf2+ then Kf3.", listOf("d3f2", "h1g1", "e2f3")),
        RawLesson("Knight Corner Domination (Var 76)", "8/1p6/8/8/8/3N4/4K3/7k w - - 0 1", "Confine the King to the h1 corner with Nd3-f2+! Step forward with Ke2-f3.", "Play Nf2+ then Kf3.", listOf("d3f2", "h1g1", "e2f3")),
        RawLesson("Knight Corner Domination (Var 77)", "8/2p5/8/8/8/3N4/4K3/7k w - - 0 1", "Confine the King to the h1 corner with Nd3-f2+! Step forward with Ke2-f3.", "Play Nf2+ then Kf3.", listOf("d3f2", "h1g1", "e2f3")),
        RawLesson("Knight Corner Domination (Var 78)", "8/7p/8/8/8/3N4/4K3/7k w - - 0 1", "Confine the King to the h1 corner with Nd3-f2+! Step forward with Ke2-f3.", "Play Nf2+ then Kf3.", listOf("d3f2", "h1g1", "e2f3")),
        RawLesson("Knight Corner Domination (Var 79)", "8/6p1/8/8/8/3N4/4K3/7k w - - 0 1", "Confine the King to the h1 corner with Nd3-f2+! Step forward with Ke2-f3.", "Play Nf2+ then Kf3.", listOf("d3f2", "h1g1", "e2f3")),
        RawLesson("Knight Corner Domination (Var 80)", "8/5p2/8/8/8/3N4/4K3/7k w - - 0 1", "Confine the King to the h1 corner with Nd3-f2+! Step forward with Ke2-f3.", "Play Nf2+ then Kf3.", listOf("d3f2", "h1g1", "e2f3")),
        RawLesson("Knight Corner Domination (Var 81)", "8/8/8/8/8/P2N4/4K3/7k w - - 0 1", "Confine the King to the h1 corner with Nd3-f2+! Step forward with Ke2-f3.", "Play Nf2+ then Kf3.", listOf("d3f2", "h1g1", "e2f3")),
        RawLesson("Knight Corner Domination (Var 82)", "8/8/8/8/8/3N3P/4K3/7k w - - 0 1", "Confine the King to the h1 corner with Nd3-f2+! Step forward with Ke2-f3.", "Play Nf2+ then Kf3.", listOf("d3f2", "h1g1", "e2f3")),
        RawLesson("Knight Corner Domination (Var 83)", "8/8/p7/8/8/3N4/4K3/7k w - - 0 1", "Confine the King to the h1 corner with Nd3-f2+! Step forward with Ke2-f3.", "Play Nf2+ then Kf3.", listOf("d3f2", "h1g1", "e2f3")),
        RawLesson("Knight Corner Domination (Var 84)", "8/8/7p/8/8/3N4/4K3/7k w - - 0 1", "Confine the King to the h1 corner with Nd3-f2+! Step forward with Ke2-f3.", "Play Nf2+ then Kf3.", listOf("d3f2", "h1g1", "e2f3")),
        RawLesson("Knight Outpost Jump (Var 85)", "8/8/4k3/8/8/4N3/P3K3/8 w - - 0 1", "Jump into the center with Ne3-d5! Support your Knight with Ke2-d3.", "Play Nd5 then Kd3.", listOf("e3d5", "e6d6", "e2d3")),
        RawLesson("Knight Outpost Jump (Var 86)", "8/8/4k3/8/8/4N3/1P2K3/8 w - - 0 1", "Jump into the center with Ne3-d5! Support your Knight with Ke2-d3.", "Play Nd5 then Kd3.", listOf("e3d5", "e6d6", "e2d3")),
        RawLesson("Knight Outpost Jump (Var 87)", "8/8/4k3/8/8/4N3/2P1K3/8 w - - 0 1", "Jump into the center with Ne3-d5! Support your Knight with Ke2-d3.", "Play Nd5 then Kd3.", listOf("e3d5", "e6d6", "e2d3")),
        RawLesson("Knight Outpost Jump (Var 88)", "8/8/4k3/8/8/4N3/4K2P/8 w - - 0 1", "Jump into the center with Ne3-d5! Support your Knight with Ke2-d3.", "Play Nd5 then Kd3.", listOf("e3d5", "e6d6", "e2d3")),
        RawLesson("Knight Outpost Jump (Var 89)", "8/8/4k3/8/8/4N3/4K1P1/8 w - - 0 1", "Jump into the center with Ne3-d5! Support your Knight with Ke2-d3.", "Play Nd5 then Kd3.", listOf("e3d5", "e6d6", "e2d3")),
        RawLesson("Knight Outpost Jump (Var 90)", "8/8/4k3/8/8/4N3/4KP2/8 w - - 0 1", "Jump into the center with Ne3-d5! Support your Knight with Ke2-d3.", "Play Nd5 then Kd3.", listOf("e3d5", "e6d6", "e2d3")),
        RawLesson("Knight Outpost Jump (Var 91)", "8/p7/4k3/8/8/4N3/4K3/8 w - - 0 1", "Jump into the center with Ne3-d5! Support your Knight with Ke2-d3.", "Play Nd5 then Kd3.", listOf("e3d5", "e6d6", "e2d3")),
        RawLesson("Knight Outpost Jump (Var 92)", "8/1p6/4k3/8/8/4N3/4K3/8 w - - 0 1", "Jump into the center with Ne3-d5! Support your Knight with Ke2-d3.", "Play Nd5 then Kd3.", listOf("e3d5", "e6d6", "e2d3")),
        RawLesson("Knight Outpost Jump (Var 93)", "8/2p5/4k3/8/8/4N3/4K3/8 w - - 0 1", "Jump into the center with Ne3-d5! Support your Knight with Ke2-d3.", "Play Nd5 then Kd3.", listOf("e3d5", "e6d6", "e2d3")),
        RawLesson("Knight Outpost Jump (Var 94)", "8/7p/4k3/8/8/4N3/4K3/8 w - - 0 1", "Jump into the center with Ne3-d5! Support your Knight with Ke2-d3.", "Play Nd5 then Kd3.", listOf("e3d5", "e6d6", "e2d3")),
        RawLesson("Knight Outpost Jump (Var 95)", "8/6p1/4k3/8/8/4N3/4K3/8 w - - 0 1", "Jump into the center with Ne3-d5! Support your Knight with Ke2-d3.", "Play Nd5 then Kd3.", listOf("e3d5", "e6d6", "e2d3")),
        RawLesson("Knight Outpost Jump (Var 96)", "8/5p2/4k3/8/8/4N3/4K3/8 w - - 0 1", "Jump into the center with Ne3-d5! Support your Knight with Ke2-d3.", "Play Nd5 then Kd3.", listOf("e3d5", "e6d6", "e2d3")),
        RawLesson("Knight Outpost Jump (Var 97)", "8/8/4k3/8/8/P3N3/4K3/8 w - - 0 1", "Jump into the center with Ne3-d5! Support your Knight with Ke2-d3.", "Play Nd5 then Kd3.", listOf("e3d5", "e6d6", "e2d3")),
        RawLesson("Knight Outpost Jump (Var 98)", "8/8/4k3/8/8/4N2P/4K3/8 w - - 0 1", "Jump into the center with Ne3-d5! Support your Knight with Ke2-d3.", "Play Nd5 then Kd3.", listOf("e3d5", "e6d6", "e2d3")),
        RawLesson("Knight Outpost Jump (Var 99)", "8/8/p3k3/8/8/4N3/4K3/8 w - - 0 1", "Jump into the center with Ne3-d5! Support your Knight with Ke2-d3.", "Play Nd5 then Kd3.", listOf("e3d5", "e6d6", "e2d3")),
        RawLesson("Knight Outpost Jump (Var 100)", "8/8/4k2p/8/8/4N3/4K3/8 w - - 0 1", "Jump into the center with Ne3-d5! Support your Knight with Ke2-d3.", "Play Nd5 then Kd3.", listOf("e3d5", "e6d6", "e2d3"))
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
            val base = pool[(level - 1) % pool.size]
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
