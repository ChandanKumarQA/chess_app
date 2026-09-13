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
        RawLesson("Rule of the Square", "8/8/8/4k3/P7/8/8/2K5 w - - 0 1", "The pawn is outside Black King's square! Push a4-a5 and march to queen uncontested.", "Push a4-a5 immediately.", listOf("a4a5", "e5d6", "a5a6")),
        RawLesson("Rule of the Square (h-file)", "8/8/8/3k4/7P/8/8/2K5 w - - 0 1", "Push the h-pawn with h4-h5! Black King cannot catch the pawn.", "Push h4-h5.", listOf("h4h5", "d5e6", "h5h6")),
        RawLesson("Connected Passed Pawns", "8/8/4k3/8/4PP2/4K3/8/8 w - - 0 1", "Advance e4-e5 to coordinate your connected pawns! Connected pawns support each other.", "Push e5 to restrict Black's King.", listOf("e4e5", "e6f5", "e3f3")),
        RawLesson("Connected Passed Pawns (c & d)", "8/8/3k4/8/2PP4/3K4/8/8 w - - 0 1", "Advance c4-c5! Secure control of central squares.", "Push c5.", listOf("c4c5", "d6e6", "d3e4")),
        RawLesson("Pawn Breakthrough", "8/8/ppp5/8/PPP5/8/8/4K2k w - - 0 1", "Classic 3 vs 3 pawn breakthrough: push b4-b5! Sacrifice one pawn to create an unstoppable passed pawn.", "Break the barrier with b5!", listOf("b4b5", "c6b5", "c4c5")),
        RawLesson("Outside Passed Pawn Escort", "8/8/4k3/8/P7/4K3/8/8 w - - 0 1", "Push the outside passed pawn a4-a5, then centralize your King with Ke3-d4.", "Advance a5 then centralize Kd4.", listOf("a4a5", "e6d6", "e3d4")),
        RawLesson("Escorting the Passed Pawn", "8/8/8/4P3/4K3/8/8/4k3 w - - 0 1", "Move your King in front of the pawn with Ke4-d5 to escort it to the 8th rank.", "Clear the way with Kd5.", listOf("e4d5", "e1d2", "e5e6")),
        RawLesson("Protected Passed Pawn", "8/8/4k3/4p3/4P3/4KP2/8/8 w - - 0 1", "Challenge Black's center with f3-f4! Create an active passed pawn.", "Strike with f4.", listOf("f3f4", "e6f6", "f4e5")),
        RawLesson("Pawn Breakthrough (2 vs 2)", "8/8/pp6/8/PP6/8/8/4K2k w - - 0 1", "Sacrifice with b4-b5! Create an outside runner with a4-a5.", "Push b5 then a5.", listOf("b4b5", "a6b5", "a4a5")),
        RawLesson("Rule of the Square (Var 10)", "8/8/8/4k3/P7/8/P7/2K5 w - - 0 1", "The pawn is outside Black King's square! Push a4-a5 and march to queen uncontested.", "Push a4-a5 immediately.", listOf("a4a5", "e5d6", "a5a6")),
        RawLesson("Rule of the Square (Var 11)", "8/8/8/4k3/P7/8/1P6/2K5 w - - 0 1", "The pawn is outside Black King's square! Push a4-a5 and march to queen uncontested.", "Push a4-a5 immediately.", listOf("a4a5", "e5d6", "a5a6")),
        RawLesson("Rule of the Square (Var 12)", "8/8/8/4k3/P7/8/2P5/2K5 w - - 0 1", "The pawn is outside Black King's square! Push a4-a5 and march to queen uncontested.", "Push a4-a5 immediately.", listOf("a4a5", "e5d6", "a5a6")),
        RawLesson("Rule of the Square (Var 13)", "8/8/8/4k3/P7/8/7P/2K5 w - - 0 1", "The pawn is outside Black King's square! Push a4-a5 and march to queen uncontested.", "Push a4-a5 immediately.", listOf("a4a5", "e5d6", "a5a6")),
        RawLesson("Rule of the Square (Var 14)", "8/8/8/4k3/P7/8/6P1/2K5 w - - 0 1", "The pawn is outside Black King's square! Push a4-a5 and march to queen uncontested.", "Push a4-a5 immediately.", listOf("a4a5", "e5d6", "a5a6")),
        RawLesson("Rule of the Square (Var 15)", "8/8/8/4k3/P7/8/5P2/2K5 w - - 0 1", "The pawn is outside Black King's square! Push a4-a5 and march to queen uncontested.", "Push a4-a5 immediately.", listOf("a4a5", "e5d6", "a5a6")),
        RawLesson("Rule of the Square (Var 16)", "8/p7/8/4k3/P7/8/8/2K5 w - - 0 1", "The pawn is outside Black King's square! Push a4-a5 and march to queen uncontested.", "Push a4-a5 immediately.", listOf("a4a5", "e5d6", "a5a6")),
        RawLesson("Rule of the Square (Var 17)", "8/1p6/8/4k3/P7/8/8/2K5 w - - 0 1", "The pawn is outside Black King's square! Push a4-a5 and march to queen uncontested.", "Push a4-a5 immediately.", listOf("a4a5", "e5d6", "a5a6")),
        RawLesson("Rule of the Square (Var 18)", "8/2p5/8/4k3/P7/8/8/2K5 w - - 0 1", "The pawn is outside Black King's square! Push a4-a5 and march to queen uncontested.", "Push a4-a5 immediately.", listOf("a4a5", "e5d6", "a5a6")),
        RawLesson("Rule of the Square (Var 19)", "8/7p/8/4k3/P7/8/8/2K5 w - - 0 1", "The pawn is outside Black King's square! Push a4-a5 and march to queen uncontested.", "Push a4-a5 immediately.", listOf("a4a5", "e5d6", "a5a6")),
        RawLesson("Rule of the Square (Var 20)", "8/6p1/8/4k3/P7/8/8/2K5 w - - 0 1", "The pawn is outside Black King's square! Push a4-a5 and march to queen uncontested.", "Push a4-a5 immediately.", listOf("a4a5", "e5d6", "a5a6")),
        RawLesson("Rule of the Square (Var 21)", "8/5p2/8/4k3/P7/8/8/2K5 w - - 0 1", "The pawn is outside Black King's square! Push a4-a5 and march to queen uncontested.", "Push a4-a5 immediately.", listOf("a4a5", "e5d6", "a5a6")),
        RawLesson("Rule of the Square (Var 22)", "8/8/8/4k3/P7/P7/8/2K5 w - - 0 1", "The pawn is outside Black King's square! Push a4-a5 and march to queen uncontested.", "Push a4-a5 immediately.", listOf("a4a5", "e5d6", "a5a6")),
        RawLesson("Rule of the Square (Var 23)", "8/8/8/4k3/P7/7P/8/2K5 w - - 0 1", "The pawn is outside Black King's square! Push a4-a5 and march to queen uncontested.", "Push a4-a5 immediately.", listOf("a4a5", "e5d6", "a5a6")),
        RawLesson("Rule of the Square (Var 24)", "8/8/7p/4k3/P7/8/8/2K5 w - - 0 1", "The pawn is outside Black King's square! Push a4-a5 and march to queen uncontested.", "Push a4-a5 immediately.", listOf("a4a5", "e5d6", "a5a6")),
        RawLesson("Rule of the Square (h-file) (Var 25)", "8/8/8/3k4/7P/8/P7/2K5 w - - 0 1", "Push the h-pawn with h4-h5! Black King cannot catch the pawn.", "Push h4-h5.", listOf("h4h5", "d5e6", "h5h6")),
        RawLesson("Rule of the Square (h-file) (Var 26)", "8/8/8/3k4/7P/8/1P6/2K5 w - - 0 1", "Push the h-pawn with h4-h5! Black King cannot catch the pawn.", "Push h4-h5.", listOf("h4h5", "d5e6", "h5h6")),
        RawLesson("Rule of the Square (h-file) (Var 27)", "8/8/8/3k4/7P/8/2P5/2K5 w - - 0 1", "Push the h-pawn with h4-h5! Black King cannot catch the pawn.", "Push h4-h5.", listOf("h4h5", "d5e6", "h5h6")),
        RawLesson("Rule of the Square (h-file) (Var 28)", "8/8/8/3k4/7P/8/7P/2K5 w - - 0 1", "Push the h-pawn with h4-h5! Black King cannot catch the pawn.", "Push h4-h5.", listOf("h4h5", "d5e6", "h5h6")),
        RawLesson("Rule of the Square (h-file) (Var 29)", "8/8/8/3k4/7P/8/6P1/2K5 w - - 0 1", "Push the h-pawn with h4-h5! Black King cannot catch the pawn.", "Push h4-h5.", listOf("h4h5", "d5e6", "h5h6")),
        RawLesson("Rule of the Square (h-file) (Var 30)", "8/8/8/3k4/7P/8/5P2/2K5 w - - 0 1", "Push the h-pawn with h4-h5! Black King cannot catch the pawn.", "Push h4-h5.", listOf("h4h5", "d5e6", "h5h6")),
        RawLesson("Rule of the Square (h-file) (Var 31)", "8/p7/8/3k4/7P/8/8/2K5 w - - 0 1", "Push the h-pawn with h4-h5! Black King cannot catch the pawn.", "Push h4-h5.", listOf("h4h5", "d5e6", "h5h6")),
        RawLesson("Rule of the Square (h-file) (Var 32)", "8/1p6/8/3k4/7P/8/8/2K5 w - - 0 1", "Push the h-pawn with h4-h5! Black King cannot catch the pawn.", "Push h4-h5.", listOf("h4h5", "d5e6", "h5h6")),
        RawLesson("Rule of the Square (h-file) (Var 33)", "8/2p5/8/3k4/7P/8/8/2K5 w - - 0 1", "Push the h-pawn with h4-h5! Black King cannot catch the pawn.", "Push h4-h5.", listOf("h4h5", "d5e6", "h5h6")),
        RawLesson("Rule of the Square (h-file) (Var 34)", "8/7p/8/3k4/7P/8/8/2K5 w - - 0 1", "Push the h-pawn with h4-h5! Black King cannot catch the pawn.", "Push h4-h5.", listOf("h4h5", "d5e6", "h5h6")),
        RawLesson("Rule of the Square (h-file) (Var 35)", "8/6p1/8/3k4/7P/8/8/2K5 w - - 0 1", "Push the h-pawn with h4-h5! Black King cannot catch the pawn.", "Push h4-h5.", listOf("h4h5", "d5e6", "h5h6")),
        RawLesson("Rule of the Square (h-file) (Var 36)", "8/5p2/8/3k4/7P/8/8/2K5 w - - 0 1", "Push the h-pawn with h4-h5! Black King cannot catch the pawn.", "Push h4-h5.", listOf("h4h5", "d5e6", "h5h6")),
        RawLesson("Rule of the Square (h-file) (Var 37)", "8/8/8/3k4/7P/P7/8/2K5 w - - 0 1", "Push the h-pawn with h4-h5! Black King cannot catch the pawn.", "Push h4-h5.", listOf("h4h5", "d5e6", "h5h6")),
        RawLesson("Rule of the Square (h-file) (Var 38)", "8/8/8/3k4/7P/7P/8/2K5 w - - 0 1", "Push the h-pawn with h4-h5! Black King cannot catch the pawn.", "Push h4-h5.", listOf("h4h5", "d5e6", "h5h6")),
        RawLesson("Rule of the Square (h-file) (Var 39)", "8/8/p7/3k4/7P/8/8/2K5 w - - 0 1", "Push the h-pawn with h4-h5! Black King cannot catch the pawn.", "Push h4-h5.", listOf("h4h5", "d5e6", "h5h6")),
        RawLesson("Connected Passed Pawns (Var 40)", "8/8/4k3/8/4PP2/4K3/P7/8 w - - 0 1", "Advance e4-e5 to coordinate your connected pawns! Connected pawns support each other.", "Push e5 to restrict Black's King.", listOf("e4e5", "e6f5", "e3f3")),
        RawLesson("Connected Passed Pawns (Var 41)", "8/8/4k3/8/4PP2/4K3/1P6/8 w - - 0 1", "Advance e4-e5 to coordinate your connected pawns! Connected pawns support each other.", "Push e5 to restrict Black's King.", listOf("e4e5", "e6f5", "e3f3")),
        RawLesson("Connected Passed Pawns (Var 42)", "8/8/4k3/8/4PP2/4K3/2P5/8 w - - 0 1", "Advance e4-e5 to coordinate your connected pawns! Connected pawns support each other.", "Push e5 to restrict Black's King.", listOf("e4e5", "e6f5", "e3f3")),
        RawLesson("Connected Passed Pawns (Var 43)", "8/8/4k3/8/4PP2/4K3/7P/8 w - - 0 1", "Advance e4-e5 to coordinate your connected pawns! Connected pawns support each other.", "Push e5 to restrict Black's King.", listOf("e4e5", "e6f5", "e3f3")),
        RawLesson("Connected Passed Pawns (Var 44)", "8/8/4k3/8/4PP2/4K3/6P1/8 w - - 0 1", "Advance e4-e5 to coordinate your connected pawns! Connected pawns support each other.", "Push e5 to restrict Black's King.", listOf("e4e5", "e6f5", "e3f3")),
        RawLesson("Connected Passed Pawns (Var 45)", "8/8/4k3/8/4PP2/4K3/5P2/8 w - - 0 1", "Advance e4-e5 to coordinate your connected pawns! Connected pawns support each other.", "Push e5 to restrict Black's King.", listOf("e4e5", "e6f5", "e3f3")),
        RawLesson("Connected Passed Pawns (Var 46)", "8/p7/4k3/8/4PP2/4K3/8/8 w - - 0 1", "Advance e4-e5 to coordinate your connected pawns! Connected pawns support each other.", "Push e5 to restrict Black's King.", listOf("e4e5", "e6f5", "e3f3")),
        RawLesson("Connected Passed Pawns (Var 47)", "8/1p6/4k3/8/4PP2/4K3/8/8 w - - 0 1", "Advance e4-e5 to coordinate your connected pawns! Connected pawns support each other.", "Push e5 to restrict Black's King.", listOf("e4e5", "e6f5", "e3f3")),
        RawLesson("Connected Passed Pawns (Var 48)", "8/2p5/4k3/8/4PP2/4K3/8/8 w - - 0 1", "Advance e4-e5 to coordinate your connected pawns! Connected pawns support each other.", "Push e5 to restrict Black's King.", listOf("e4e5", "e6f5", "e3f3")),
        RawLesson("Connected Passed Pawns (Var 49)", "8/7p/4k3/8/4PP2/4K3/8/8 w - - 0 1", "Advance e4-e5 to coordinate your connected pawns! Connected pawns support each other.", "Push e5 to restrict Black's King.", listOf("e4e5", "e6f5", "e3f3")),
        RawLesson("Connected Passed Pawns (Var 50)", "8/6p1/4k3/8/4PP2/4K3/8/8 w - - 0 1", "Advance e4-e5 to coordinate your connected pawns! Connected pawns support each other.", "Push e5 to restrict Black's King.", listOf("e4e5", "e6f5", "e3f3")),
        RawLesson("Connected Passed Pawns (Var 51)", "8/5p2/4k3/8/4PP2/4K3/8/8 w - - 0 1", "Advance e4-e5 to coordinate your connected pawns! Connected pawns support each other.", "Push e5 to restrict Black's King.", listOf("e4e5", "e6f5", "e3f3")),
        RawLesson("Connected Passed Pawns (Var 52)", "8/8/4k3/8/4PP2/P3K3/8/8 w - - 0 1", "Advance e4-e5 to coordinate your connected pawns! Connected pawns support each other.", "Push e5 to restrict Black's King.", listOf("e4e5", "e6f5", "e3f3")),
        RawLesson("Connected Passed Pawns (Var 53)", "8/8/4k3/8/4PP2/4K2P/8/8 w - - 0 1", "Advance e4-e5 to coordinate your connected pawns! Connected pawns support each other.", "Push e5 to restrict Black's King.", listOf("e4e5", "e6f5", "e3f3")),
        RawLesson("Connected Passed Pawns (Var 54)", "8/8/p3k3/8/4PP2/4K3/8/8 w - - 0 1", "Advance e4-e5 to coordinate your connected pawns! Connected pawns support each other.", "Push e5 to restrict Black's King.", listOf("e4e5", "e6f5", "e3f3")),
        RawLesson("Connected Passed Pawns (Var 55)", "8/8/4k2p/8/4PP2/4K3/8/8 w - - 0 1", "Advance e4-e5 to coordinate your connected pawns! Connected pawns support each other.", "Push e5 to restrict Black's King.", listOf("e4e5", "e6f5", "e3f3")),
        RawLesson("Connected Passed Pawns (c & d) (Var 56)", "8/8/3k4/8/2PP4/3K4/P7/8 w - - 0 1", "Advance c4-c5! Secure control of central squares.", "Push c5.", listOf("c4c5", "d6e6", "d3e4")),
        RawLesson("Connected Passed Pawns (c & d) (Var 57)", "8/8/3k4/8/2PP4/3K4/1P6/8 w - - 0 1", "Advance c4-c5! Secure control of central squares.", "Push c5.", listOf("c4c5", "d6e6", "d3e4")),
        RawLesson("Connected Passed Pawns (c & d) (Var 58)", "8/8/3k4/8/2PP4/3K4/2P5/8 w - - 0 1", "Advance c4-c5! Secure control of central squares.", "Push c5.", listOf("c4c5", "d6e6", "d3e4")),
        RawLesson("Connected Passed Pawns (c & d) (Var 59)", "8/8/3k4/8/2PP4/3K4/7P/8 w - - 0 1", "Advance c4-c5! Secure control of central squares.", "Push c5.", listOf("c4c5", "d6e6", "d3e4")),
        RawLesson("Connected Passed Pawns (c & d) (Var 60)", "8/8/3k4/8/2PP4/3K4/6P1/8 w - - 0 1", "Advance c4-c5! Secure control of central squares.", "Push c5.", listOf("c4c5", "d6e6", "d3e4")),
        RawLesson("Connected Passed Pawns (c & d) (Var 61)", "8/8/3k4/8/2PP4/3K4/5P2/8 w - - 0 1", "Advance c4-c5! Secure control of central squares.", "Push c5.", listOf("c4c5", "d6e6", "d3e4")),
        RawLesson("Connected Passed Pawns (c & d) (Var 62)", "8/p7/3k4/8/2PP4/3K4/8/8 w - - 0 1", "Advance c4-c5! Secure control of central squares.", "Push c5.", listOf("c4c5", "d6e6", "d3e4")),
        RawLesson("Connected Passed Pawns (c & d) (Var 63)", "8/1p6/3k4/8/2PP4/3K4/8/8 w - - 0 1", "Advance c4-c5! Secure control of central squares.", "Push c5.", listOf("c4c5", "d6e6", "d3e4")),
        RawLesson("Connected Passed Pawns (c & d) (Var 64)", "8/2p5/3k4/8/2PP4/3K4/8/8 w - - 0 1", "Advance c4-c5! Secure control of central squares.", "Push c5.", listOf("c4c5", "d6e6", "d3e4")),
        RawLesson("Connected Passed Pawns (c & d) (Var 65)", "8/7p/3k4/8/2PP4/3K4/8/8 w - - 0 1", "Advance c4-c5! Secure control of central squares.", "Push c5.", listOf("c4c5", "d6e6", "d3e4")),
        RawLesson("Connected Passed Pawns (c & d) (Var 66)", "8/6p1/3k4/8/2PP4/3K4/8/8 w - - 0 1", "Advance c4-c5! Secure control of central squares.", "Push c5.", listOf("c4c5", "d6e6", "d3e4")),
        RawLesson("Connected Passed Pawns (c & d) (Var 67)", "8/5p2/3k4/8/2PP4/3K4/8/8 w - - 0 1", "Advance c4-c5! Secure control of central squares.", "Push c5.", listOf("c4c5", "d6e6", "d3e4")),
        RawLesson("Connected Passed Pawns (c & d) (Var 68)", "8/8/3k4/8/2PP4/P2K4/8/8 w - - 0 1", "Advance c4-c5! Secure control of central squares.", "Push c5.", listOf("c4c5", "d6e6", "d3e4")),
        RawLesson("Connected Passed Pawns (c & d) (Var 69)", "8/8/3k4/8/2PP4/3K3P/8/8 w - - 0 1", "Advance c4-c5! Secure control of central squares.", "Push c5.", listOf("c4c5", "d6e6", "d3e4")),
        RawLesson("Connected Passed Pawns (c & d) (Var 70)", "8/8/p2k4/8/2PP4/3K4/8/8 w - - 0 1", "Advance c4-c5! Secure control of central squares.", "Push c5.", listOf("c4c5", "d6e6", "d3e4")),
        RawLesson("Connected Passed Pawns (c & d) (Var 71)", "8/8/3k3p/8/2PP4/3K4/8/8 w - - 0 1", "Advance c4-c5! Secure control of central squares.", "Push c5.", listOf("c4c5", "d6e6", "d3e4")),
        RawLesson("Pawn Breakthrough (Var 72)", "8/8/ppp5/8/PPP5/8/P7/4K2k w - - 0 1", "Classic 3 vs 3 pawn breakthrough: push b4-b5! Sacrifice one pawn to create an unstoppable passed pawn.", "Break the barrier with b5!", listOf("b4b5", "c6b5", "c4c5")),
        RawLesson("Pawn Breakthrough (Var 73)", "8/8/ppp5/8/PPP5/8/1P6/4K2k w - - 0 1", "Classic 3 vs 3 pawn breakthrough: push b4-b5! Sacrifice one pawn to create an unstoppable passed pawn.", "Break the barrier with b5!", listOf("b4b5", "c6b5", "c4c5")),
        RawLesson("Pawn Breakthrough (Var 74)", "8/8/ppp5/8/PPP5/8/2P5/4K2k w - - 0 1", "Classic 3 vs 3 pawn breakthrough: push b4-b5! Sacrifice one pawn to create an unstoppable passed pawn.", "Break the barrier with b5!", listOf("b4b5", "c6b5", "c4c5")),
        RawLesson("Pawn Breakthrough (Var 75)", "8/8/ppp5/8/PPP5/8/7P/4K2k w - - 0 1", "Classic 3 vs 3 pawn breakthrough: push b4-b5! Sacrifice one pawn to create an unstoppable passed pawn.", "Break the barrier with b5!", listOf("b4b5", "c6b5", "c4c5")),
        RawLesson("Pawn Breakthrough (Var 76)", "8/8/ppp5/8/PPP5/8/6P1/4K2k w - - 0 1", "Classic 3 vs 3 pawn breakthrough: push b4-b5! Sacrifice one pawn to create an unstoppable passed pawn.", "Break the barrier with b5!", listOf("b4b5", "c6b5", "c4c5")),
        RawLesson("Pawn Breakthrough (Var 77)", "8/8/ppp5/8/PPP5/8/5P2/4K2k w - - 0 1", "Classic 3 vs 3 pawn breakthrough: push b4-b5! Sacrifice one pawn to create an unstoppable passed pawn.", "Break the barrier with b5!", listOf("b4b5", "c6b5", "c4c5")),
        RawLesson("Pawn Breakthrough (Var 78)", "8/p7/ppp5/8/PPP5/8/8/4K2k w - - 0 1", "Classic 3 vs 3 pawn breakthrough: push b4-b5! Sacrifice one pawn to create an unstoppable passed pawn.", "Break the barrier with b5!", listOf("b4b5", "c6b5", "c4c5")),
        RawLesson("Pawn Breakthrough (Var 79)", "8/1p6/ppp5/8/PPP5/8/8/4K2k w - - 0 1", "Classic 3 vs 3 pawn breakthrough: push b4-b5! Sacrifice one pawn to create an unstoppable passed pawn.", "Break the barrier with b5!", listOf("b4b5", "c6b5", "c4c5")),
        RawLesson("Pawn Breakthrough (Var 80)", "8/2p5/ppp5/8/PPP5/8/8/4K2k w - - 0 1", "Classic 3 vs 3 pawn breakthrough: push b4-b5! Sacrifice one pawn to create an unstoppable passed pawn.", "Break the barrier with b5!", listOf("b4b5", "c6b5", "c4c5")),
        RawLesson("Pawn Breakthrough (Var 81)", "8/7p/ppp5/8/PPP5/8/8/4K2k w - - 0 1", "Classic 3 vs 3 pawn breakthrough: push b4-b5! Sacrifice one pawn to create an unstoppable passed pawn.", "Break the barrier with b5!", listOf("b4b5", "c6b5", "c4c5")),
        RawLesson("Pawn Breakthrough (Var 82)", "8/6p1/ppp5/8/PPP5/8/8/4K2k w - - 0 1", "Classic 3 vs 3 pawn breakthrough: push b4-b5! Sacrifice one pawn to create an unstoppable passed pawn.", "Break the barrier with b5!", listOf("b4b5", "c6b5", "c4c5")),
        RawLesson("Pawn Breakthrough (Var 83)", "8/5p2/ppp5/8/PPP5/8/8/4K2k w - - 0 1", "Classic 3 vs 3 pawn breakthrough: push b4-b5! Sacrifice one pawn to create an unstoppable passed pawn.", "Break the barrier with b5!", listOf("b4b5", "c6b5", "c4c5")),
        RawLesson("Pawn Breakthrough (Var 84)", "8/8/ppp5/8/PPP5/P7/8/4K2k w - - 0 1", "Classic 3 vs 3 pawn breakthrough: push b4-b5! Sacrifice one pawn to create an unstoppable passed pawn.", "Break the barrier with b5!", listOf("b4b5", "c6b5", "c4c5")),
        RawLesson("Pawn Breakthrough (Var 85)", "8/8/ppp5/8/PPP5/7P/8/4K2k w - - 0 1", "Classic 3 vs 3 pawn breakthrough: push b4-b5! Sacrifice one pawn to create an unstoppable passed pawn.", "Break the barrier with b5!", listOf("b4b5", "c6b5", "c4c5")),
        RawLesson("Pawn Breakthrough (Var 86)", "8/8/ppp4p/8/PPP5/8/8/4K2k w - - 0 1", "Classic 3 vs 3 pawn breakthrough: push b4-b5! Sacrifice one pawn to create an unstoppable passed pawn.", "Break the barrier with b5!", listOf("b4b5", "c6b5", "c4c5")),
        RawLesson("Outside Passed Pawn Escort (Var 87)", "8/8/4k3/8/P7/4K3/P7/8 w - - 0 1", "Push the outside passed pawn a4-a5, then centralize your King with Ke3-d4.", "Advance a5 then centralize Kd4.", listOf("a4a5", "e6d6", "e3d4")),
        RawLesson("Outside Passed Pawn Escort (Var 88)", "8/8/4k3/8/P7/4K3/1P6/8 w - - 0 1", "Push the outside passed pawn a4-a5, then centralize your King with Ke3-d4.", "Advance a5 then centralize Kd4.", listOf("a4a5", "e6d6", "e3d4")),
        RawLesson("Outside Passed Pawn Escort (Var 89)", "8/8/4k3/8/P7/4K3/2P5/8 w - - 0 1", "Push the outside passed pawn a4-a5, then centralize your King with Ke3-d4.", "Advance a5 then centralize Kd4.", listOf("a4a5", "e6d6", "e3d4")),
        RawLesson("Outside Passed Pawn Escort (Var 90)", "8/8/4k3/8/P7/4K3/7P/8 w - - 0 1", "Push the outside passed pawn a4-a5, then centralize your King with Ke3-d4.", "Advance a5 then centralize Kd4.", listOf("a4a5", "e6d6", "e3d4")),
        RawLesson("Outside Passed Pawn Escort (Var 91)", "8/8/4k3/8/P7/4K3/6P1/8 w - - 0 1", "Push the outside passed pawn a4-a5, then centralize your King with Ke3-d4.", "Advance a5 then centralize Kd4.", listOf("a4a5", "e6d6", "e3d4")),
        RawLesson("Outside Passed Pawn Escort (Var 92)", "8/8/4k3/8/P7/4K3/5P2/8 w - - 0 1", "Push the outside passed pawn a4-a5, then centralize your King with Ke3-d4.", "Advance a5 then centralize Kd4.", listOf("a4a5", "e6d6", "e3d4")),
        RawLesson("Outside Passed Pawn Escort (Var 93)", "8/p7/4k3/8/P7/4K3/8/8 w - - 0 1", "Push the outside passed pawn a4-a5, then centralize your King with Ke3-d4.", "Advance a5 then centralize Kd4.", listOf("a4a5", "e6d6", "e3d4")),
        RawLesson("Outside Passed Pawn Escort (Var 94)", "8/1p6/4k3/8/P7/4K3/8/8 w - - 0 1", "Push the outside passed pawn a4-a5, then centralize your King with Ke3-d4.", "Advance a5 then centralize Kd4.", listOf("a4a5", "e6d6", "e3d4")),
        RawLesson("Outside Passed Pawn Escort (Var 95)", "8/2p5/4k3/8/P7/4K3/8/8 w - - 0 1", "Push the outside passed pawn a4-a5, then centralize your King with Ke3-d4.", "Advance a5 then centralize Kd4.", listOf("a4a5", "e6d6", "e3d4")),
        RawLesson("Outside Passed Pawn Escort (Var 96)", "8/7p/4k3/8/P7/4K3/8/8 w - - 0 1", "Push the outside passed pawn a4-a5, then centralize your King with Ke3-d4.", "Advance a5 then centralize Kd4.", listOf("a4a5", "e6d6", "e3d4")),
        RawLesson("Outside Passed Pawn Escort (Var 97)", "8/6p1/4k3/8/P7/4K3/8/8 w - - 0 1", "Push the outside passed pawn a4-a5, then centralize your King with Ke3-d4.", "Advance a5 then centralize Kd4.", listOf("a4a5", "e6d6", "e3d4")),
        RawLesson("Outside Passed Pawn Escort (Var 98)", "8/5p2/4k3/8/P7/4K3/8/8 w - - 0 1", "Push the outside passed pawn a4-a5, then centralize your King with Ke3-d4.", "Advance a5 then centralize Kd4.", listOf("a4a5", "e6d6", "e3d4")),
        RawLesson("Outside Passed Pawn Escort (Var 99)", "8/8/4k3/8/P7/P3K3/8/8 w - - 0 1", "Push the outside passed pawn a4-a5, then centralize your King with Ke3-d4.", "Advance a5 then centralize Kd4.", listOf("a4a5", "e6d6", "e3d4")),
        RawLesson("Outside Passed Pawn Escort (Var 100)", "8/8/4k3/8/P7/4K2P/8/8 w - - 0 1", "Push the outside passed pawn a4-a5, then centralize your King with Ke3-d4.", "Advance a5 then centralize Kd4.", listOf("a4a5", "e6d6", "e3d4"))
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
