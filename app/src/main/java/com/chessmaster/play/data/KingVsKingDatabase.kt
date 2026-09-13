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
        RawLesson("Direct Opposition (e-file)", "8/8/4k3/8/3K4/4P3/8/8 w - - 0 1", "Take direct opposition with Kd4-e4! When Black steps to f6, outflank with Ke4-d5 to control promotion squares.", "Match your King's file with Ke4.", listOf("d4e4", "e6f6", "e4d5")),
        RawLesson("Direct Opposition (d-file)", "8/8/3k4/8/2K5/3P4/8/8 w - - 0 1", "Play c4-d4 taking direct opposition on the d-file! Then outflank Black on c5 when he retreats.", "Step into opposition with Kd4.", listOf("c4d4", "d6e6", "d4c5")),
        RawLesson("Direct Opposition (f-file)", "8/8/5k2/8/4K3/5P2/8/8 w - - 0 1", "Move e4-f4 to take opposition on the f-file and escort your passed pawn forward.", "Occupy f4 directly opposite Black's King.", listOf("e4f4", "f6g6", "f4e5")),
        RawLesson("Direct Opposition (c-file)", "8/8/2k5/8/1K6/2P5/8/8 w - - 0 1", "Take direct opposition on the c-file with b4-c4! Follow up by outflanking on b5.", "Play Kc4 to seize opposition.", listOf("b4c4", "c6d6", "c4b5")),
        RawLesson("Key Squares (6th Rank e-file)", "4k3/8/4K3/4P3/8/8/8/8 w - - 0 1", "With your King firmly on the 6th rank in front of the pawn, step to d6 to clear the promotion path.", "Step to the side with Kd6, then advance the pawn.", listOf("e6d6", "e8d8", "e5e6")),
        RawLesson("Key Squares (6th Rank d-file)", "3k4/8/3K4/3P4/8/8/8/8 w - - 0 1", "With your King on d6 in front of the pawn, step to c6 to clear the promotion path.", "Step to the side with Kc6.", listOf("d6c6", "d8c8", "d5d6")),
        RawLesson("Outflanking Technique", "8/8/3k4/8/3K4/4P3/8/8 w - - 0 1", "When Black King holds opposition, outflank by playing Kd4-e4! If Black retreats, seize the 5th rank with Ke5.", "Step aside with Ke4 to create a path forward.", listOf("d4e4", "d6e7", "e4e5")),
        RawLesson("Body Checking (Shoulder-Charging)", "8/8/8/3k4/8/4K3/5P2/8 w - - 0 1", "Use your King as a shield with Ke3-f4! Block Black's King from approaching your pawn, then infiltrate with Kf4-g5.", "Play Kf4 to cut off Black's King.", listOf("e3f4", "d5e6", "f4g5")),
        RawLesson("Distant Opposition (3 Squares)", "8/8/4k3/8/8/8/4K3/4P3 w - - 0 1", "Maintain distant opposition with Ke2-e3! An odd number of squares between kings preserves the advantage.", "Play Ke3 to take opposition.", listOf("e2e3", "e6e5", "e3d3")),
        RawLesson("Outside Passed Pawn Deflection", "8/8/4k3/3p4/5K1P/8/8/8 w - - 0 1", "Push the outside passed pawn h4-h5! Black's King must chase it, allowing your King to penetrate into White's center.", "Decoy Black's king with h5.", listOf("h4h5", "e6f6", "f4e3")),
        RawLesson("Reti's Dual Threat Maneuver", "7K/8/2P5/7p/8/8/8/k7 w - - 0 1", "The famous Reti endgame idea: Move Kh8-g7! Your King simultaneously threatens to escort c6 and catch Black's h-pawn.", "Play Kg7 to pursue both objectives at once.", listOf("h8g7", "h5h4", "g7f6")),
        RawLesson("Trebuchet / Mutual Zugzwang", "8/8/3k4/3p4/5K2/4P3/8/8 w - - 0 1", "Penetrate with Kf4-f5! Attack the base of Black's pawn chain and force Black's King to step back.", "Invade with Kf5.", listOf("f4f5", "d6e7", "f5e5")),
        RawLesson("Diagonal Infiltration", "8/8/5k2/8/8/2K5/2P5/8 w - - 0 1", "Infiltrate with Kc3-d4! Control the center and step into c5.", "Centralize with Kd4.", listOf("c3d4", "f6e6", "d4c5")),
        RawLesson("Direct Opposition (e-file) (Var 14)", "8/8/4k3/8/3K4/4P3/P7/8 w - - 0 1", "Take direct opposition with Kd4-e4! When Black steps to f6, outflank with Ke4-d5 to control promotion squares.", "Match your King's file with Ke4.", listOf("d4e4", "e6f6", "e4d5")),
        RawLesson("Direct Opposition (e-file) (Var 15)", "8/8/4k3/8/3K4/4P3/1P6/8 w - - 0 1", "Take direct opposition with Kd4-e4! When Black steps to f6, outflank with Ke4-d5 to control promotion squares.", "Match your King's file with Ke4.", listOf("d4e4", "e6f6", "e4d5")),
        RawLesson("Direct Opposition (e-file) (Var 16)", "8/8/4k3/8/3K4/4P3/2P5/8 w - - 0 1", "Take direct opposition with Kd4-e4! When Black steps to f6, outflank with Ke4-d5 to control promotion squares.", "Match your King's file with Ke4.", listOf("d4e4", "e6f6", "e4d5")),
        RawLesson("Direct Opposition (e-file) (Var 17)", "8/8/4k3/8/3K4/4P3/7P/8 w - - 0 1", "Take direct opposition with Kd4-e4! When Black steps to f6, outflank with Ke4-d5 to control promotion squares.", "Match your King's file with Ke4.", listOf("d4e4", "e6f6", "e4d5")),
        RawLesson("Direct Opposition (e-file) (Var 18)", "8/8/4k3/8/3K4/4P3/6P1/8 w - - 0 1", "Take direct opposition with Kd4-e4! When Black steps to f6, outflank with Ke4-d5 to control promotion squares.", "Match your King's file with Ke4.", listOf("d4e4", "e6f6", "e4d5")),
        RawLesson("Direct Opposition (e-file) (Var 19)", "8/8/4k3/8/3K4/4P3/5P2/8 w - - 0 1", "Take direct opposition with Kd4-e4! When Black steps to f6, outflank with Ke4-d5 to control promotion squares.", "Match your King's file with Ke4.", listOf("d4e4", "e6f6", "e4d5")),
        RawLesson("Direct Opposition (e-file) (Var 20)", "8/p7/4k3/8/3K4/4P3/8/8 w - - 0 1", "Take direct opposition with Kd4-e4! When Black steps to f6, outflank with Ke4-d5 to control promotion squares.", "Match your King's file with Ke4.", listOf("d4e4", "e6f6", "e4d5")),
        RawLesson("Direct Opposition (e-file) (Var 21)", "8/1p6/4k3/8/3K4/4P3/8/8 w - - 0 1", "Take direct opposition with Kd4-e4! When Black steps to f6, outflank with Ke4-d5 to control promotion squares.", "Match your King's file with Ke4.", listOf("d4e4", "e6f6", "e4d5")),
        RawLesson("Direct Opposition (e-file) (Var 22)", "8/2p5/4k3/8/3K4/4P3/8/8 w - - 0 1", "Take direct opposition with Kd4-e4! When Black steps to f6, outflank with Ke4-d5 to control promotion squares.", "Match your King's file with Ke4.", listOf("d4e4", "e6f6", "e4d5")),
        RawLesson("Direct Opposition (e-file) (Var 23)", "8/7p/4k3/8/3K4/4P3/8/8 w - - 0 1", "Take direct opposition with Kd4-e4! When Black steps to f6, outflank with Ke4-d5 to control promotion squares.", "Match your King's file with Ke4.", listOf("d4e4", "e6f6", "e4d5")),
        RawLesson("Direct Opposition (e-file) (Var 24)", "8/6p1/4k3/8/3K4/4P3/8/8 w - - 0 1", "Take direct opposition with Kd4-e4! When Black steps to f6, outflank with Ke4-d5 to control promotion squares.", "Match your King's file with Ke4.", listOf("d4e4", "e6f6", "e4d5")),
        RawLesson("Direct Opposition (e-file) (Var 25)", "8/5p2/4k3/8/3K4/4P3/8/8 w - - 0 1", "Take direct opposition with Kd4-e4! When Black steps to f6, outflank with Ke4-d5 to control promotion squares.", "Match your King's file with Ke4.", listOf("d4e4", "e6f6", "e4d5")),
        RawLesson("Direct Opposition (e-file) (Var 26)", "8/8/4k3/8/3K4/P3P3/8/8 w - - 0 1", "Take direct opposition with Kd4-e4! When Black steps to f6, outflank with Ke4-d5 to control promotion squares.", "Match your King's file with Ke4.", listOf("d4e4", "e6f6", "e4d5")),
        RawLesson("Direct Opposition (e-file) (Var 27)", "8/8/4k3/8/3K4/4P2P/8/8 w - - 0 1", "Take direct opposition with Kd4-e4! When Black steps to f6, outflank with Ke4-d5 to control promotion squares.", "Match your King's file with Ke4.", listOf("d4e4", "e6f6", "e4d5")),
        RawLesson("Direct Opposition (e-file) (Var 28)", "8/8/p3k3/8/3K4/4P3/8/8 w - - 0 1", "Take direct opposition with Kd4-e4! When Black steps to f6, outflank with Ke4-d5 to control promotion squares.", "Match your King's file with Ke4.", listOf("d4e4", "e6f6", "e4d5")),
        RawLesson("Direct Opposition (e-file) (Var 29)", "8/8/4k2p/8/3K4/4P3/8/8 w - - 0 1", "Take direct opposition with Kd4-e4! When Black steps to f6, outflank with Ke4-d5 to control promotion squares.", "Match your King's file with Ke4.", listOf("d4e4", "e6f6", "e4d5")),
        RawLesson("Direct Opposition (d-file) (Var 30)", "8/8/3k4/8/2K5/3P4/P7/8 w - - 0 1", "Play c4-d4 taking direct opposition on the d-file! Then outflank Black on c5 when he retreats.", "Step into opposition with Kd4.", listOf("c4d4", "d6e6", "d4c5")),
        RawLesson("Direct Opposition (d-file) (Var 31)", "8/8/3k4/8/2K5/3P4/1P6/8 w - - 0 1", "Play c4-d4 taking direct opposition on the d-file! Then outflank Black on c5 when he retreats.", "Step into opposition with Kd4.", listOf("c4d4", "d6e6", "d4c5")),
        RawLesson("Direct Opposition (d-file) (Var 32)", "8/8/3k4/8/2K5/3P4/2P5/8 w - - 0 1", "Play c4-d4 taking direct opposition on the d-file! Then outflank Black on c5 when he retreats.", "Step into opposition with Kd4.", listOf("c4d4", "d6e6", "d4c5")),
        RawLesson("Direct Opposition (d-file) (Var 33)", "8/8/3k4/8/2K5/3P4/7P/8 w - - 0 1", "Play c4-d4 taking direct opposition on the d-file! Then outflank Black on c5 when he retreats.", "Step into opposition with Kd4.", listOf("c4d4", "d6e6", "d4c5")),
        RawLesson("Direct Opposition (d-file) (Var 34)", "8/8/3k4/8/2K5/3P4/6P1/8 w - - 0 1", "Play c4-d4 taking direct opposition on the d-file! Then outflank Black on c5 when he retreats.", "Step into opposition with Kd4.", listOf("c4d4", "d6e6", "d4c5")),
        RawLesson("Direct Opposition (d-file) (Var 35)", "8/8/3k4/8/2K5/3P4/5P2/8 w - - 0 1", "Play c4-d4 taking direct opposition on the d-file! Then outflank Black on c5 when he retreats.", "Step into opposition with Kd4.", listOf("c4d4", "d6e6", "d4c5")),
        RawLesson("Direct Opposition (d-file) (Var 36)", "8/p7/3k4/8/2K5/3P4/8/8 w - - 0 1", "Play c4-d4 taking direct opposition on the d-file! Then outflank Black on c5 when he retreats.", "Step into opposition with Kd4.", listOf("c4d4", "d6e6", "d4c5")),
        RawLesson("Direct Opposition (d-file) (Var 37)", "8/1p6/3k4/8/2K5/3P4/8/8 w - - 0 1", "Play c4-d4 taking direct opposition on the d-file! Then outflank Black on c5 when he retreats.", "Step into opposition with Kd4.", listOf("c4d4", "d6e6", "d4c5")),
        RawLesson("Direct Opposition (d-file) (Var 38)", "8/2p5/3k4/8/2K5/3P4/8/8 w - - 0 1", "Play c4-d4 taking direct opposition on the d-file! Then outflank Black on c5 when he retreats.", "Step into opposition with Kd4.", listOf("c4d4", "d6e6", "d4c5")),
        RawLesson("Direct Opposition (d-file) (Var 39)", "8/7p/3k4/8/2K5/3P4/8/8 w - - 0 1", "Play c4-d4 taking direct opposition on the d-file! Then outflank Black on c5 when he retreats.", "Step into opposition with Kd4.", listOf("c4d4", "d6e6", "d4c5")),
        RawLesson("Direct Opposition (d-file) (Var 40)", "8/6p1/3k4/8/2K5/3P4/8/8 w - - 0 1", "Play c4-d4 taking direct opposition on the d-file! Then outflank Black on c5 when he retreats.", "Step into opposition with Kd4.", listOf("c4d4", "d6e6", "d4c5")),
        RawLesson("Direct Opposition (d-file) (Var 41)", "8/5p2/3k4/8/2K5/3P4/8/8 w - - 0 1", "Play c4-d4 taking direct opposition on the d-file! Then outflank Black on c5 when he retreats.", "Step into opposition with Kd4.", listOf("c4d4", "d6e6", "d4c5")),
        RawLesson("Direct Opposition (d-file) (Var 42)", "8/8/3k4/8/2K5/P2P4/8/8 w - - 0 1", "Play c4-d4 taking direct opposition on the d-file! Then outflank Black on c5 when he retreats.", "Step into opposition with Kd4.", listOf("c4d4", "d6e6", "d4c5")),
        RawLesson("Direct Opposition (d-file) (Var 43)", "8/8/3k4/8/2K5/3P3P/8/8 w - - 0 1", "Play c4-d4 taking direct opposition on the d-file! Then outflank Black on c5 when he retreats.", "Step into opposition with Kd4.", listOf("c4d4", "d6e6", "d4c5")),
        RawLesson("Direct Opposition (d-file) (Var 44)", "8/8/p2k4/8/2K5/3P4/8/8 w - - 0 1", "Play c4-d4 taking direct opposition on the d-file! Then outflank Black on c5 when he retreats.", "Step into opposition with Kd4.", listOf("c4d4", "d6e6", "d4c5")),
        RawLesson("Direct Opposition (d-file) (Var 45)", "8/8/3k3p/8/2K5/3P4/8/8 w - - 0 1", "Play c4-d4 taking direct opposition on the d-file! Then outflank Black on c5 when he retreats.", "Step into opposition with Kd4.", listOf("c4d4", "d6e6", "d4c5")),
        RawLesson("Direct Opposition (f-file) (Var 46)", "8/8/5k2/8/4K3/5P2/P7/8 w - - 0 1", "Move e4-f4 to take opposition on the f-file and escort your passed pawn forward.", "Occupy f4 directly opposite Black's King.", listOf("e4f4", "f6g6", "f4e5")),
        RawLesson("Direct Opposition (f-file) (Var 47)", "8/8/5k2/8/4K3/5P2/1P6/8 w - - 0 1", "Move e4-f4 to take opposition on the f-file and escort your passed pawn forward.", "Occupy f4 directly opposite Black's King.", listOf("e4f4", "f6g6", "f4e5")),
        RawLesson("Direct Opposition (f-file) (Var 48)", "8/8/5k2/8/4K3/5P2/2P5/8 w - - 0 1", "Move e4-f4 to take opposition on the f-file and escort your passed pawn forward.", "Occupy f4 directly opposite Black's King.", listOf("e4f4", "f6g6", "f4e5")),
        RawLesson("Direct Opposition (f-file) (Var 49)", "8/8/5k2/8/4K3/5P2/7P/8 w - - 0 1", "Move e4-f4 to take opposition on the f-file and escort your passed pawn forward.", "Occupy f4 directly opposite Black's King.", listOf("e4f4", "f6g6", "f4e5")),
        RawLesson("Direct Opposition (f-file) (Var 50)", "8/8/5k2/8/4K3/5P2/6P1/8 w - - 0 1", "Move e4-f4 to take opposition on the f-file and escort your passed pawn forward.", "Occupy f4 directly opposite Black's King.", listOf("e4f4", "f6g6", "f4e5")),
        RawLesson("Direct Opposition (f-file) (Var 51)", "8/8/5k2/8/4K3/5P2/5P2/8 w - - 0 1", "Move e4-f4 to take opposition on the f-file and escort your passed pawn forward.", "Occupy f4 directly opposite Black's King.", listOf("e4f4", "f6g6", "f4e5")),
        RawLesson("Direct Opposition (f-file) (Var 52)", "8/p7/5k2/8/4K3/5P2/8/8 w - - 0 1", "Move e4-f4 to take opposition on the f-file and escort your passed pawn forward.", "Occupy f4 directly opposite Black's King.", listOf("e4f4", "f6g6", "f4e5")),
        RawLesson("Direct Opposition (f-file) (Var 53)", "8/1p6/5k2/8/4K3/5P2/8/8 w - - 0 1", "Move e4-f4 to take opposition on the f-file and escort your passed pawn forward.", "Occupy f4 directly opposite Black's King.", listOf("e4f4", "f6g6", "f4e5")),
        RawLesson("Direct Opposition (f-file) (Var 54)", "8/2p5/5k2/8/4K3/5P2/8/8 w - - 0 1", "Move e4-f4 to take opposition on the f-file and escort your passed pawn forward.", "Occupy f4 directly opposite Black's King.", listOf("e4f4", "f6g6", "f4e5")),
        RawLesson("Direct Opposition (f-file) (Var 55)", "8/7p/5k2/8/4K3/5P2/8/8 w - - 0 1", "Move e4-f4 to take opposition on the f-file and escort your passed pawn forward.", "Occupy f4 directly opposite Black's King.", listOf("e4f4", "f6g6", "f4e5")),
        RawLesson("Direct Opposition (f-file) (Var 56)", "8/6p1/5k2/8/4K3/5P2/8/8 w - - 0 1", "Move e4-f4 to take opposition on the f-file and escort your passed pawn forward.", "Occupy f4 directly opposite Black's King.", listOf("e4f4", "f6g6", "f4e5")),
        RawLesson("Direct Opposition (f-file) (Var 57)", "8/5p2/5k2/8/4K3/5P2/8/8 w - - 0 1", "Move e4-f4 to take opposition on the f-file and escort your passed pawn forward.", "Occupy f4 directly opposite Black's King.", listOf("e4f4", "f6g6", "f4e5")),
        RawLesson("Direct Opposition (f-file) (Var 58)", "8/8/5k2/8/4K3/P4P2/8/8 w - - 0 1", "Move e4-f4 to take opposition on the f-file and escort your passed pawn forward.", "Occupy f4 directly opposite Black's King.", listOf("e4f4", "f6g6", "f4e5")),
        RawLesson("Direct Opposition (f-file) (Var 59)", "8/8/5k2/8/4K3/5P1P/8/8 w - - 0 1", "Move e4-f4 to take opposition on the f-file and escort your passed pawn forward.", "Occupy f4 directly opposite Black's King.", listOf("e4f4", "f6g6", "f4e5")),
        RawLesson("Direct Opposition (f-file) (Var 60)", "8/8/p4k2/8/4K3/5P2/8/8 w - - 0 1", "Move e4-f4 to take opposition on the f-file and escort your passed pawn forward.", "Occupy f4 directly opposite Black's King.", listOf("e4f4", "f6g6", "f4e5")),
        RawLesson("Direct Opposition (f-file) (Var 61)", "8/8/5k1p/8/4K3/5P2/8/8 w - - 0 1", "Move e4-f4 to take opposition on the f-file and escort your passed pawn forward.", "Occupy f4 directly opposite Black's King.", listOf("e4f4", "f6g6", "f4e5")),
        RawLesson("Direct Opposition (c-file) (Var 62)", "8/8/2k5/8/1K6/2P5/P7/8 w - - 0 1", "Take direct opposition on the c-file with b4-c4! Follow up by outflanking on b5.", "Play Kc4 to seize opposition.", listOf("b4c4", "c6d6", "c4b5")),
        RawLesson("Direct Opposition (c-file) (Var 63)", "8/8/2k5/8/1K6/2P5/1P6/8 w - - 0 1", "Take direct opposition on the c-file with b4-c4! Follow up by outflanking on b5.", "Play Kc4 to seize opposition.", listOf("b4c4", "c6d6", "c4b5")),
        RawLesson("Direct Opposition (c-file) (Var 64)", "8/8/2k5/8/1K6/2P5/2P5/8 w - - 0 1", "Take direct opposition on the c-file with b4-c4! Follow up by outflanking on b5.", "Play Kc4 to seize opposition.", listOf("b4c4", "c6d6", "c4b5")),
        RawLesson("Direct Opposition (c-file) (Var 65)", "8/8/2k5/8/1K6/2P5/7P/8 w - - 0 1", "Take direct opposition on the c-file with b4-c4! Follow up by outflanking on b5.", "Play Kc4 to seize opposition.", listOf("b4c4", "c6d6", "c4b5")),
        RawLesson("Direct Opposition (c-file) (Var 66)", "8/8/2k5/8/1K6/2P5/6P1/8 w - - 0 1", "Take direct opposition on the c-file with b4-c4! Follow up by outflanking on b5.", "Play Kc4 to seize opposition.", listOf("b4c4", "c6d6", "c4b5")),
        RawLesson("Direct Opposition (c-file) (Var 67)", "8/8/2k5/8/1K6/2P5/5P2/8 w - - 0 1", "Take direct opposition on the c-file with b4-c4! Follow up by outflanking on b5.", "Play Kc4 to seize opposition.", listOf("b4c4", "c6d6", "c4b5")),
        RawLesson("Direct Opposition (c-file) (Var 68)", "8/p7/2k5/8/1K6/2P5/8/8 w - - 0 1", "Take direct opposition on the c-file with b4-c4! Follow up by outflanking on b5.", "Play Kc4 to seize opposition.", listOf("b4c4", "c6d6", "c4b5")),
        RawLesson("Direct Opposition (c-file) (Var 69)", "8/1p6/2k5/8/1K6/2P5/8/8 w - - 0 1", "Take direct opposition on the c-file with b4-c4! Follow up by outflanking on b5.", "Play Kc4 to seize opposition.", listOf("b4c4", "c6d6", "c4b5")),
        RawLesson("Direct Opposition (c-file) (Var 70)", "8/2p5/2k5/8/1K6/2P5/8/8 w - - 0 1", "Take direct opposition on the c-file with b4-c4! Follow up by outflanking on b5.", "Play Kc4 to seize opposition.", listOf("b4c4", "c6d6", "c4b5")),
        RawLesson("Direct Opposition (c-file) (Var 71)", "8/7p/2k5/8/1K6/2P5/8/8 w - - 0 1", "Take direct opposition on the c-file with b4-c4! Follow up by outflanking on b5.", "Play Kc4 to seize opposition.", listOf("b4c4", "c6d6", "c4b5")),
        RawLesson("Direct Opposition (c-file) (Var 72)", "8/6p1/2k5/8/1K6/2P5/8/8 w - - 0 1", "Take direct opposition on the c-file with b4-c4! Follow up by outflanking on b5.", "Play Kc4 to seize opposition.", listOf("b4c4", "c6d6", "c4b5")),
        RawLesson("Direct Opposition (c-file) (Var 73)", "8/5p2/2k5/8/1K6/2P5/8/8 w - - 0 1", "Take direct opposition on the c-file with b4-c4! Follow up by outflanking on b5.", "Play Kc4 to seize opposition.", listOf("b4c4", "c6d6", "c4b5")),
        RawLesson("Direct Opposition (c-file) (Var 74)", "8/8/2k5/8/1K6/P1P5/8/8 w - - 0 1", "Take direct opposition on the c-file with b4-c4! Follow up by outflanking on b5.", "Play Kc4 to seize opposition.", listOf("b4c4", "c6d6", "c4b5")),
        RawLesson("Direct Opposition (c-file) (Var 75)", "8/8/2k5/8/1K6/2P4P/8/8 w - - 0 1", "Take direct opposition on the c-file with b4-c4! Follow up by outflanking on b5.", "Play Kc4 to seize opposition.", listOf("b4c4", "c6d6", "c4b5")),
        RawLesson("Direct Opposition (c-file) (Var 76)", "8/8/2k4p/8/1K6/2P5/8/8 w - - 0 1", "Take direct opposition on the c-file with b4-c4! Follow up by outflanking on b5.", "Play Kc4 to seize opposition.", listOf("b4c4", "c6d6", "c4b5")),
        RawLesson("Key Squares (6th Rank e-file) (Var 77)", "4k3/8/4K3/4P3/8/8/P7/8 w - - 0 1", "With your King firmly on the 6th rank in front of the pawn, step to d6 to clear the promotion path.", "Step to the side with Kd6, then advance the pawn.", listOf("e6d6", "e8d8", "e5e6")),
        RawLesson("Key Squares (6th Rank e-file) (Var 78)", "4k3/8/4K3/4P3/8/8/1P6/8 w - - 0 1", "With your King firmly on the 6th rank in front of the pawn, step to d6 to clear the promotion path.", "Step to the side with Kd6, then advance the pawn.", listOf("e6d6", "e8d8", "e5e6")),
        RawLesson("Key Squares (6th Rank e-file) (Var 79)", "4k3/8/4K3/4P3/8/8/2P5/8 w - - 0 1", "With your King firmly on the 6th rank in front of the pawn, step to d6 to clear the promotion path.", "Step to the side with Kd6, then advance the pawn.", listOf("e6d6", "e8d8", "e5e6")),
        RawLesson("Key Squares (6th Rank e-file) (Var 80)", "4k3/8/4K3/4P3/8/8/7P/8 w - - 0 1", "With your King firmly on the 6th rank in front of the pawn, step to d6 to clear the promotion path.", "Step to the side with Kd6, then advance the pawn.", listOf("e6d6", "e8d8", "e5e6")),
        RawLesson("Key Squares (6th Rank e-file) (Var 81)", "4k3/8/4K3/4P3/8/8/6P1/8 w - - 0 1", "With your King firmly on the 6th rank in front of the pawn, step to d6 to clear the promotion path.", "Step to the side with Kd6, then advance the pawn.", listOf("e6d6", "e8d8", "e5e6")),
        RawLesson("Key Squares (6th Rank e-file) (Var 82)", "4k3/8/4K3/4P3/8/8/5P2/8 w - - 0 1", "With your King firmly on the 6th rank in front of the pawn, step to d6 to clear the promotion path.", "Step to the side with Kd6, then advance the pawn.", listOf("e6d6", "e8d8", "e5e6")),
        RawLesson("Key Squares (6th Rank e-file) (Var 83)", "4k3/p7/4K3/4P3/8/8/8/8 w - - 0 1", "With your King firmly on the 6th rank in front of the pawn, step to d6 to clear the promotion path.", "Step to the side with Kd6, then advance the pawn.", listOf("e6d6", "e8d8", "e5e6")),
        RawLesson("Key Squares (6th Rank e-file) (Var 84)", "4k3/1p6/4K3/4P3/8/8/8/8 w - - 0 1", "With your King firmly on the 6th rank in front of the pawn, step to d6 to clear the promotion path.", "Step to the side with Kd6, then advance the pawn.", listOf("e6d6", "e8d8", "e5e6")),
        RawLesson("Key Squares (6th Rank e-file) (Var 85)", "4k3/7p/4K3/4P3/8/8/8/8 w - - 0 1", "With your King firmly on the 6th rank in front of the pawn, step to d6 to clear the promotion path.", "Step to the side with Kd6, then advance the pawn.", listOf("e6d6", "e8d8", "e5e6")),
        RawLesson("Key Squares (6th Rank e-file) (Var 86)", "4k3/6p1/4K3/4P3/8/8/8/8 w - - 0 1", "With your King firmly on the 6th rank in front of the pawn, step to d6 to clear the promotion path.", "Step to the side with Kd6, then advance the pawn.", listOf("e6d6", "e8d8", "e5e6")),
        RawLesson("Key Squares (6th Rank e-file) (Var 87)", "4k3/5p2/4K3/4P3/8/8/8/8 w - - 0 1", "With your King firmly on the 6th rank in front of the pawn, step to d6 to clear the promotion path.", "Step to the side with Kd6, then advance the pawn.", listOf("e6d6", "e8d8", "e5e6")),
        RawLesson("Key Squares (6th Rank e-file) (Var 88)", "4k3/8/4K3/4P3/8/P7/8/8 w - - 0 1", "With your King firmly on the 6th rank in front of the pawn, step to d6 to clear the promotion path.", "Step to the side with Kd6, then advance the pawn.", listOf("e6d6", "e8d8", "e5e6")),
        RawLesson("Key Squares (6th Rank e-file) (Var 89)", "4k3/8/4K3/4P3/8/7P/8/8 w - - 0 1", "With your King firmly on the 6th rank in front of the pawn, step to d6 to clear the promotion path.", "Step to the side with Kd6, then advance the pawn.", listOf("e6d6", "e8d8", "e5e6")),
        RawLesson("Key Squares (6th Rank e-file) (Var 90)", "4k3/8/p3K3/4P3/8/8/8/8 w - - 0 1", "With your King firmly on the 6th rank in front of the pawn, step to d6 to clear the promotion path.", "Step to the side with Kd6, then advance the pawn.", listOf("e6d6", "e8d8", "e5e6")),
        RawLesson("Key Squares (6th Rank e-file) (Var 91)", "4k3/8/4K2p/4P3/8/8/8/8 w - - 0 1", "With your King firmly on the 6th rank in front of the pawn, step to d6 to clear the promotion path.", "Step to the side with Kd6, then advance the pawn.", listOf("e6d6", "e8d8", "e5e6")),
        RawLesson("Key Squares (6th Rank d-file) (Var 92)", "3k4/8/3K4/3P4/8/8/P7/8 w - - 0 1", "With your King on d6 in front of the pawn, step to c6 to clear the promotion path.", "Step to the side with Kc6.", listOf("d6c6", "d8c8", "d5d6")),
        RawLesson("Key Squares (6th Rank d-file) (Var 93)", "3k4/8/3K4/3P4/8/8/1P6/8 w - - 0 1", "With your King on d6 in front of the pawn, step to c6 to clear the promotion path.", "Step to the side with Kc6.", listOf("d6c6", "d8c8", "d5d6")),
        RawLesson("Key Squares (6th Rank d-file) (Var 94)", "3k4/8/3K4/3P4/8/8/2P5/8 w - - 0 1", "With your King on d6 in front of the pawn, step to c6 to clear the promotion path.", "Step to the side with Kc6.", listOf("d6c6", "d8c8", "d5d6")),
        RawLesson("Key Squares (6th Rank d-file) (Var 95)", "3k4/8/3K4/3P4/8/8/7P/8 w - - 0 1", "With your King on d6 in front of the pawn, step to c6 to clear the promotion path.", "Step to the side with Kc6.", listOf("d6c6", "d8c8", "d5d6")),
        RawLesson("Key Squares (6th Rank d-file) (Var 96)", "3k4/8/3K4/3P4/8/8/6P1/8 w - - 0 1", "With your King on d6 in front of the pawn, step to c6 to clear the promotion path.", "Step to the side with Kc6.", listOf("d6c6", "d8c8", "d5d6")),
        RawLesson("Key Squares (6th Rank d-file) (Var 97)", "3k4/8/3K4/3P4/8/8/5P2/8 w - - 0 1", "With your King on d6 in front of the pawn, step to c6 to clear the promotion path.", "Step to the side with Kc6.", listOf("d6c6", "d8c8", "d5d6")),
        RawLesson("Key Squares (6th Rank d-file) (Var 98)", "3k4/p7/3K4/3P4/8/8/8/8 w - - 0 1", "With your King on d6 in front of the pawn, step to c6 to clear the promotion path.", "Step to the side with Kc6.", listOf("d6c6", "d8c8", "d5d6")),
        RawLesson("Key Squares (6th Rank d-file) (Var 99)", "3k4/2p5/3K4/3P4/8/8/8/8 w - - 0 1", "With your King on d6 in front of the pawn, step to c6 to clear the promotion path.", "Step to the side with Kc6.", listOf("d6c6", "d8c8", "d5d6")),
        RawLesson("Key Squares (6th Rank d-file) (Var 100)", "3k4/7p/3K4/3P4/8/8/8/8 w - - 0 1", "With your King on d6 in front of the pawn, step to c6 to clear the promotion path.", "Step to the side with Kc6.", listOf("d6c6", "d8c8", "d5d6"))
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
