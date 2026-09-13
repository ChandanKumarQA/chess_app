package com.chessmaster.play.data

import com.chessmaster.play.model.EndgameLesson

object EndgameStrategyDatabase {

    private data class RawLesson(
        val subtitle: String,
        val fen: String,
        val explanation: String,
        val hint: String,
        val moves: List<String>
    )

    private val pool = listOf(
        RawLesson("Mutual Zugzwang (Trebuchet)", "8/8/8/4p3/3kP3/8/4K3/8 w - - 0 1", "Defend your pawn with Ke2-f3! When Black retreats, step forward with Kf3-f2.", "Play Kf3 then Kf2.", listOf("e2f3", "d4d3", "f3f2")),
        RawLesson("Shoulder-Charging Infiltration", "8/8/8/8/3k4/4P3/8/3K4 w - - 0 1", "Use your King to shoulder-charge the opponent King: Kd1-d2, then Ke2.", "Play Kd2 then Ke2.", listOf("d1d2", "d4e4", "d2e2")),
        RawLesson("Penetrating the Defense", "8/8/8/3k4/8/3P4/3K4/8 w - - 0 1", "Penetrate the center with Kd2-e3! Follow up by marching forward with Ke3-f3.", "Play Ke3 then Kf3.", listOf("d2e3", "d5e5", "e3f3")),
        RawLesson("Rook Pawn Blockade", "8/8/8/P7/8/k7/8/K7 w - - 0 1", "Keep the passed pawn moving with a5-a6! When Black attacks, push a6-a7.", "Advance a6 then a7.", listOf("a5a6", "a3b4", "a6a7")),
        RawLesson("Passed Pawn Support", "8/8/8/4k3/8/4K3/8/8 w - - 0 1", "Seize the opposition with Ke3-f3! Step to g3 to control the key flank.", "Play Kf3 then Kg3.", listOf("e3f3", "e5f5", "f3g3")),
        RawLesson("Mutual Zugzwang (Trebuchet) (Var 6)", "8/8/8/4p3/3kP3/8/P3K3/8 w - - 0 1", "Defend your pawn with Ke2-f3! When Black retreats, step forward with Kf3-f2.", "Play Kf3 then Kf2.", listOf("e2f3", "d4d3", "f3f2")),
        RawLesson("Mutual Zugzwang (Trebuchet) (Var 7)", "8/8/8/4p3/3kP3/8/1P2K3/8 w - - 0 1", "Defend your pawn with Ke2-f3! When Black retreats, step forward with Kf3-f2.", "Play Kf3 then Kf2.", listOf("e2f3", "d4d3", "f3f2")),
        RawLesson("Mutual Zugzwang (Trebuchet) (Var 8)", "8/8/8/4p3/3kP3/8/4K2P/8 w - - 0 1", "Defend your pawn with Ke2-f3! When Black retreats, step forward with Kf3-f2.", "Play Kf3 then Kf2.", listOf("e2f3", "d4d3", "f3f2")),
        RawLesson("Mutual Zugzwang (Trebuchet) (Var 9)", "8/8/8/4p3/3kP3/8/4K1P1/8 w - - 0 1", "Defend your pawn with Ke2-f3! When Black retreats, step forward with Kf3-f2.", "Play Kf3 then Kf2.", listOf("e2f3", "d4d3", "f3f2")),
        RawLesson("Mutual Zugzwang (Trebuchet) (Var 10)", "8/p7/8/4p3/3kP3/8/4K3/8 w - - 0 1", "Defend your pawn with Ke2-f3! When Black retreats, step forward with Kf3-f2.", "Play Kf3 then Kf2.", listOf("e2f3", "d4d3", "f3f2")),
        RawLesson("Mutual Zugzwang (Trebuchet) (Var 11)", "8/1p6/8/4p3/3kP3/8/4K3/8 w - - 0 1", "Defend your pawn with Ke2-f3! When Black retreats, step forward with Kf3-f2.", "Play Kf3 then Kf2.", listOf("e2f3", "d4d3", "f3f2")),
        RawLesson("Mutual Zugzwang (Trebuchet) (Var 12)", "8/2p5/8/4p3/3kP3/8/4K3/8 w - - 0 1", "Defend your pawn with Ke2-f3! When Black retreats, step forward with Kf3-f2.", "Play Kf3 then Kf2.", listOf("e2f3", "d4d3", "f3f2")),
        RawLesson("Mutual Zugzwang (Trebuchet) (Var 13)", "8/7p/8/4p3/3kP3/8/4K3/8 w - - 0 1", "Defend your pawn with Ke2-f3! When Black retreats, step forward with Kf3-f2.", "Play Kf3 then Kf2.", listOf("e2f3", "d4d3", "f3f2")),
        RawLesson("Mutual Zugzwang (Trebuchet) (Var 14)", "8/6p1/8/4p3/3kP3/8/4K3/8 w - - 0 1", "Defend your pawn with Ke2-f3! When Black retreats, step forward with Kf3-f2.", "Play Kf3 then Kf2.", listOf("e2f3", "d4d3", "f3f2")),
        RawLesson("Mutual Zugzwang (Trebuchet) (Var 15)", "8/5p2/8/4p3/3kP3/8/4K3/8 w - - 0 1", "Defend your pawn with Ke2-f3! When Black retreats, step forward with Kf3-f2.", "Play Kf3 then Kf2.", listOf("e2f3", "d4d3", "f3f2")),
        RawLesson("Mutual Zugzwang (Trebuchet) (Var 16)", "8/8/8/4p3/3kP3/P7/4K3/8 w - - 0 1", "Defend your pawn with Ke2-f3! When Black retreats, step forward with Kf3-f2.", "Play Kf3 then Kf2.", listOf("e2f3", "d4d3", "f3f2")),
        RawLesson("Mutual Zugzwang (Trebuchet) (Var 17)", "8/8/8/4p3/3kP3/7P/4K3/8 w - - 0 1", "Defend your pawn with Ke2-f3! When Black retreats, step forward with Kf3-f2.", "Play Kf3 then Kf2.", listOf("e2f3", "d4d3", "f3f2")),
        RawLesson("Mutual Zugzwang (Trebuchet) (Var 18)", "8/8/p7/4p3/3kP3/8/4K3/8 w - - 0 1", "Defend your pawn with Ke2-f3! When Black retreats, step forward with Kf3-f2.", "Play Kf3 then Kf2.", listOf("e2f3", "d4d3", "f3f2")),
        RawLesson("Mutual Zugzwang (Trebuchet) (Var 19)", "8/8/7p/4p3/3kP3/8/4K3/8 w - - 0 1", "Defend your pawn with Ke2-f3! When Black retreats, step forward with Kf3-f2.", "Play Kf3 then Kf2.", listOf("e2f3", "d4d3", "f3f2")),
        RawLesson("Shoulder-Charging Infiltration (Var 20)", "8/8/8/8/3k4/4P3/P7/3K4 w - - 0 1", "Use your King to shoulder-charge the opponent King: Kd1-d2, then Ke2.", "Play Kd2 then Ke2.", listOf("d1d2", "d4e4", "d2e2")),
        RawLesson("Shoulder-Charging Infiltration (Var 21)", "8/8/8/8/3k4/4P3/1P6/3K4 w - - 0 1", "Use your King to shoulder-charge the opponent King: Kd1-d2, then Ke2.", "Play Kd2 then Ke2.", listOf("d1d2", "d4e4", "d2e2")),
        RawLesson("Shoulder-Charging Infiltration (Var 22)", "8/8/8/8/3k4/4P3/2P5/3K4 w - - 0 1", "Use your King to shoulder-charge the opponent King: Kd1-d2, then Ke2.", "Play Kd2 then Ke2.", listOf("d1d2", "d4e4", "d2e2")),
        RawLesson("Shoulder-Charging Infiltration (Var 23)", "8/8/8/8/3k4/4P3/7P/3K4 w - - 0 1", "Use your King to shoulder-charge the opponent King: Kd1-d2, then Ke2.", "Play Kd2 then Ke2.", listOf("d1d2", "d4e4", "d2e2")),
        RawLesson("Shoulder-Charging Infiltration (Var 24)", "8/8/8/8/3k4/4P3/6P1/3K4 w - - 0 1", "Use your King to shoulder-charge the opponent King: Kd1-d2, then Ke2.", "Play Kd2 then Ke2.", listOf("d1d2", "d4e4", "d2e2")),
        RawLesson("Shoulder-Charging Infiltration (Var 25)", "8/8/8/8/3k4/4P3/5P2/3K4 w - - 0 1", "Use your King to shoulder-charge the opponent King: Kd1-d2, then Ke2.", "Play Kd2 then Ke2.", listOf("d1d2", "d4e4", "d2e2")),
        RawLesson("Shoulder-Charging Infiltration (Var 26)", "8/p7/8/8/3k4/4P3/8/3K4 w - - 0 1", "Use your King to shoulder-charge the opponent King: Kd1-d2, then Ke2.", "Play Kd2 then Ke2.", listOf("d1d2", "d4e4", "d2e2")),
        RawLesson("Shoulder-Charging Infiltration (Var 27)", "8/1p6/8/8/3k4/4P3/8/3K4 w - - 0 1", "Use your King to shoulder-charge the opponent King: Kd1-d2, then Ke2.", "Play Kd2 then Ke2.", listOf("d1d2", "d4e4", "d2e2")),
        RawLesson("Shoulder-Charging Infiltration (Var 28)", "8/2p5/8/8/3k4/4P3/8/3K4 w - - 0 1", "Use your King to shoulder-charge the opponent King: Kd1-d2, then Ke2.", "Play Kd2 then Ke2.", listOf("d1d2", "d4e4", "d2e2")),
        RawLesson("Shoulder-Charging Infiltration (Var 29)", "8/7p/8/8/3k4/4P3/8/3K4 w - - 0 1", "Use your King to shoulder-charge the opponent King: Kd1-d2, then Ke2.", "Play Kd2 then Ke2.", listOf("d1d2", "d4e4", "d2e2")),
        RawLesson("Shoulder-Charging Infiltration (Var 30)", "8/6p1/8/8/3k4/4P3/8/3K4 w - - 0 1", "Use your King to shoulder-charge the opponent King: Kd1-d2, then Ke2.", "Play Kd2 then Ke2.", listOf("d1d2", "d4e4", "d2e2")),
        RawLesson("Shoulder-Charging Infiltration (Var 31)", "8/5p2/8/8/3k4/4P3/8/3K4 w - - 0 1", "Use your King to shoulder-charge the opponent King: Kd1-d2, then Ke2.", "Play Kd2 then Ke2.", listOf("d1d2", "d4e4", "d2e2")),
        RawLesson("Shoulder-Charging Infiltration (Var 32)", "8/8/8/8/3k4/P3P3/8/3K4 w - - 0 1", "Use your King to shoulder-charge the opponent King: Kd1-d2, then Ke2.", "Play Kd2 then Ke2.", listOf("d1d2", "d4e4", "d2e2")),
        RawLesson("Shoulder-Charging Infiltration (Var 33)", "8/8/8/8/3k4/4P2P/8/3K4 w - - 0 1", "Use your King to shoulder-charge the opponent King: Kd1-d2, then Ke2.", "Play Kd2 then Ke2.", listOf("d1d2", "d4e4", "d2e2")),
        RawLesson("Shoulder-Charging Infiltration (Var 34)", "8/8/p7/8/3k4/4P3/8/3K4 w - - 0 1", "Use your King to shoulder-charge the opponent King: Kd1-d2, then Ke2.", "Play Kd2 then Ke2.", listOf("d1d2", "d4e4", "d2e2")),
        RawLesson("Shoulder-Charging Infiltration (Var 35)", "8/8/7p/8/3k4/4P3/8/3K4 w - - 0 1", "Use your King to shoulder-charge the opponent King: Kd1-d2, then Ke2.", "Play Kd2 then Ke2.", listOf("d1d2", "d4e4", "d2e2")),
        RawLesson("Penetrating the Defense (Var 36)", "8/8/8/3k4/8/3P4/P2K4/8 w - - 0 1", "Penetrate the center with Kd2-e3! Follow up by marching forward with Ke3-f3.", "Play Ke3 then Kf3.", listOf("d2e3", "d5e5", "e3f3")),
        RawLesson("Penetrating the Defense (Var 37)", "8/8/8/3k4/8/3P4/1P1K4/8 w - - 0 1", "Penetrate the center with Kd2-e3! Follow up by marching forward with Ke3-f3.", "Play Ke3 then Kf3.", listOf("d2e3", "d5e5", "e3f3")),
        RawLesson("Penetrating the Defense (Var 38)", "8/8/8/3k4/8/3P4/2PK4/8 w - - 0 1", "Penetrate the center with Kd2-e3! Follow up by marching forward with Ke3-f3.", "Play Ke3 then Kf3.", listOf("d2e3", "d5e5", "e3f3")),
        RawLesson("Penetrating the Defense (Var 39)", "8/8/8/3k4/8/3P4/3K3P/8 w - - 0 1", "Penetrate the center with Kd2-e3! Follow up by marching forward with Ke3-f3.", "Play Ke3 then Kf3.", listOf("d2e3", "d5e5", "e3f3")),
        RawLesson("Penetrating the Defense (Var 40)", "8/8/8/3k4/8/3P4/3K2P1/8 w - - 0 1", "Penetrate the center with Kd2-e3! Follow up by marching forward with Ke3-f3.", "Play Ke3 then Kf3.", listOf("d2e3", "d5e5", "e3f3")),
        RawLesson("Penetrating the Defense (Var 41)", "8/8/8/3k4/8/3P4/3K1P2/8 w - - 0 1", "Penetrate the center with Kd2-e3! Follow up by marching forward with Ke3-f3.", "Play Ke3 then Kf3.", listOf("d2e3", "d5e5", "e3f3")),
        RawLesson("Penetrating the Defense (Var 42)", "8/p7/8/3k4/8/3P4/3K4/8 w - - 0 1", "Penetrate the center with Kd2-e3! Follow up by marching forward with Ke3-f3.", "Play Ke3 then Kf3.", listOf("d2e3", "d5e5", "e3f3")),
        RawLesson("Penetrating the Defense (Var 43)", "8/1p6/8/3k4/8/3P4/3K4/8 w - - 0 1", "Penetrate the center with Kd2-e3! Follow up by marching forward with Ke3-f3.", "Play Ke3 then Kf3.", listOf("d2e3", "d5e5", "e3f3")),
        RawLesson("Penetrating the Defense (Var 44)", "8/2p5/8/3k4/8/3P4/3K4/8 w - - 0 1", "Penetrate the center with Kd2-e3! Follow up by marching forward with Ke3-f3.", "Play Ke3 then Kf3.", listOf("d2e3", "d5e5", "e3f3")),
        RawLesson("Penetrating the Defense (Var 45)", "8/7p/8/3k4/8/3P4/3K4/8 w - - 0 1", "Penetrate the center with Kd2-e3! Follow up by marching forward with Ke3-f3.", "Play Ke3 then Kf3.", listOf("d2e3", "d5e5", "e3f3")),
        RawLesson("Penetrating the Defense (Var 46)", "8/6p1/8/3k4/8/3P4/3K4/8 w - - 0 1", "Penetrate the center with Kd2-e3! Follow up by marching forward with Ke3-f3.", "Play Ke3 then Kf3.", listOf("d2e3", "d5e5", "e3f3")),
        RawLesson("Penetrating the Defense (Var 47)", "8/5p2/8/3k4/8/3P4/3K4/8 w - - 0 1", "Penetrate the center with Kd2-e3! Follow up by marching forward with Ke3-f3.", "Play Ke3 then Kf3.", listOf("d2e3", "d5e5", "e3f3")),
        RawLesson("Penetrating the Defense (Var 48)", "8/8/8/3k4/8/P2P4/3K4/8 w - - 0 1", "Penetrate the center with Kd2-e3! Follow up by marching forward with Ke3-f3.", "Play Ke3 then Kf3.", listOf("d2e3", "d5e5", "e3f3")),
        RawLesson("Penetrating the Defense (Var 49)", "8/8/8/3k4/8/3P3P/3K4/8 w - - 0 1", "Penetrate the center with Kd2-e3! Follow up by marching forward with Ke3-f3.", "Play Ke3 then Kf3.", listOf("d2e3", "d5e5", "e3f3")),
        RawLesson("Penetrating the Defense (Var 50)", "8/8/p7/3k4/8/3P4/3K4/8 w - - 0 1", "Penetrate the center with Kd2-e3! Follow up by marching forward with Ke3-f3.", "Play Ke3 then Kf3.", listOf("d2e3", "d5e5", "e3f3")),
        RawLesson("Penetrating the Defense (Var 51)", "8/8/7p/3k4/8/3P4/3K4/8 w - - 0 1", "Penetrate the center with Kd2-e3! Follow up by marching forward with Ke3-f3.", "Play Ke3 then Kf3.", listOf("d2e3", "d5e5", "e3f3")),
        RawLesson("Rook Pawn Blockade (Var 52)", "8/8/8/P7/8/k7/P7/K7 w - - 0 1", "Keep the passed pawn moving with a5-a6! When Black attacks, push a6-a7.", "Advance a6 then a7.", listOf("a5a6", "a3b4", "a6a7")),
        RawLesson("Rook Pawn Blockade (Var 53)", "8/8/8/P7/8/k7/1P6/K7 w - - 0 1", "Keep the passed pawn moving with a5-a6! When Black attacks, push a6-a7.", "Advance a6 then a7.", listOf("a5a6", "a3b4", "a6a7")),
        RawLesson("Rook Pawn Blockade (Var 54)", "8/8/8/P7/8/k7/2P5/K7 w - - 0 1", "Keep the passed pawn moving with a5-a6! When Black attacks, push a6-a7.", "Advance a6 then a7.", listOf("a5a6", "a3b4", "a6a7")),
        RawLesson("Rook Pawn Blockade (Var 55)", "8/8/8/P7/8/k7/7P/K7 w - - 0 1", "Keep the passed pawn moving with a5-a6! When Black attacks, push a6-a7.", "Advance a6 then a7.", listOf("a5a6", "a3b4", "a6a7")),
        RawLesson("Rook Pawn Blockade (Var 56)", "8/8/8/P7/8/k7/6P1/K7 w - - 0 1", "Keep the passed pawn moving with a5-a6! When Black attacks, push a6-a7.", "Advance a6 then a7.", listOf("a5a6", "a3b4", "a6a7")),
        RawLesson("Rook Pawn Blockade (Var 57)", "8/8/8/P7/8/k7/5P2/K7 w - - 0 1", "Keep the passed pawn moving with a5-a6! When Black attacks, push a6-a7.", "Advance a6 then a7.", listOf("a5a6", "a3b4", "a6a7")),
        RawLesson("Rook Pawn Blockade (Var 58)", "8/1p6/8/P7/8/k7/8/K7 w - - 0 1", "Keep the passed pawn moving with a5-a6! When Black attacks, push a6-a7.", "Advance a6 then a7.", listOf("a5a6", "a3b4", "a6a7")),
        RawLesson("Rook Pawn Blockade (Var 59)", "8/2p5/8/P7/8/k7/8/K7 w - - 0 1", "Keep the passed pawn moving with a5-a6! When Black attacks, push a6-a7.", "Advance a6 then a7.", listOf("a5a6", "a3b4", "a6a7")),
        RawLesson("Rook Pawn Blockade (Var 60)", "8/7p/8/P7/8/k7/8/K7 w - - 0 1", "Keep the passed pawn moving with a5-a6! When Black attacks, push a6-a7.", "Advance a6 then a7.", listOf("a5a6", "a3b4", "a6a7")),
        RawLesson("Rook Pawn Blockade (Var 61)", "8/6p1/8/P7/8/k7/8/K7 w - - 0 1", "Keep the passed pawn moving with a5-a6! When Black attacks, push a6-a7.", "Advance a6 then a7.", listOf("a5a6", "a3b4", "a6a7")),
        RawLesson("Rook Pawn Blockade (Var 62)", "8/5p2/8/P7/8/k7/8/K7 w - - 0 1", "Keep the passed pawn moving with a5-a6! When Black attacks, push a6-a7.", "Advance a6 then a7.", listOf("a5a6", "a3b4", "a6a7")),
        RawLesson("Rook Pawn Blockade (Var 63)", "8/8/8/P7/8/k6P/8/K7 w - - 0 1", "Keep the passed pawn moving with a5-a6! When Black attacks, push a6-a7.", "Advance a6 then a7.", listOf("a5a6", "a3b4", "a6a7")),
        RawLesson("Rook Pawn Blockade (Var 64)", "8/8/7p/P7/8/k7/8/K7 w - - 0 1", "Keep the passed pawn moving with a5-a6! When Black attacks, push a6-a7.", "Advance a6 then a7.", listOf("a5a6", "a3b4", "a6a7")),
        RawLesson("Passed Pawn Support (Var 65)", "8/8/8/4k3/8/4K3/P7/8 w - - 0 1", "Seize the opposition with Ke3-f3! Step to g3 to control the key flank.", "Play Kf3 then Kg3.", listOf("e3f3", "e5f5", "f3g3")),
        RawLesson("Passed Pawn Support (Var 66)", "8/8/8/4k3/8/4K3/1P6/8 w - - 0 1", "Seize the opposition with Ke3-f3! Step to g3 to control the key flank.", "Play Kf3 then Kg3.", listOf("e3f3", "e5f5", "f3g3")),
        RawLesson("Passed Pawn Support (Var 67)", "8/8/8/4k3/8/4K3/2P5/8 w - - 0 1", "Seize the opposition with Ke3-f3! Step to g3 to control the key flank.", "Play Kf3 then Kg3.", listOf("e3f3", "e5f5", "f3g3")),
        RawLesson("Passed Pawn Support (Var 68)", "8/8/8/4k3/8/4K3/7P/8 w - - 0 1", "Seize the opposition with Ke3-f3! Step to g3 to control the key flank.", "Play Kf3 then Kg3.", listOf("e3f3", "e5f5", "f3g3")),
        RawLesson("Passed Pawn Support (Var 69)", "8/8/8/4k3/8/4K3/6P1/8 w - - 0 1", "Seize the opposition with Ke3-f3! Step to g3 to control the key flank.", "Play Kf3 then Kg3.", listOf("e3f3", "e5f5", "f3g3")),
        RawLesson("Passed Pawn Support (Var 70)", "8/8/8/4k3/8/4K3/5P2/8 w - - 0 1", "Seize the opposition with Ke3-f3! Step to g3 to control the key flank.", "Play Kf3 then Kg3.", listOf("e3f3", "e5f5", "f3g3")),
        RawLesson("Passed Pawn Support (Var 71)", "8/p7/8/4k3/8/4K3/8/8 w - - 0 1", "Seize the opposition with Ke3-f3! Step to g3 to control the key flank.", "Play Kf3 then Kg3.", listOf("e3f3", "e5f5", "f3g3")),
        RawLesson("Passed Pawn Support (Var 72)", "8/1p6/8/4k3/8/4K3/8/8 w - - 0 1", "Seize the opposition with Ke3-f3! Step to g3 to control the key flank.", "Play Kf3 then Kg3.", listOf("e3f3", "e5f5", "f3g3")),
        RawLesson("Passed Pawn Support (Var 73)", "8/2p5/8/4k3/8/4K3/8/8 w - - 0 1", "Seize the opposition with Ke3-f3! Step to g3 to control the key flank.", "Play Kf3 then Kg3.", listOf("e3f3", "e5f5", "f3g3")),
        RawLesson("Passed Pawn Support (Var 74)", "8/7p/8/4k3/8/4K3/8/8 w - - 0 1", "Seize the opposition with Ke3-f3! Step to g3 to control the key flank.", "Play Kf3 then Kg3.", listOf("e3f3", "e5f5", "f3g3")),
        RawLesson("Passed Pawn Support (Var 75)", "8/6p1/8/4k3/8/4K3/8/8 w - - 0 1", "Seize the opposition with Ke3-f3! Step to g3 to control the key flank.", "Play Kf3 then Kg3.", listOf("e3f3", "e5f5", "f3g3")),
        RawLesson("Passed Pawn Support (Var 76)", "8/5p2/8/4k3/8/4K3/8/8 w - - 0 1", "Seize the opposition with Ke3-f3! Step to g3 to control the key flank.", "Play Kf3 then Kg3.", listOf("e3f3", "e5f5", "f3g3")),
        RawLesson("Passed Pawn Support (Var 77)", "8/8/8/4k3/8/P3K3/8/8 w - - 0 1", "Seize the opposition with Ke3-f3! Step to g3 to control the key flank.", "Play Kf3 then Kg3.", listOf("e3f3", "e5f5", "f3g3")),
        RawLesson("Passed Pawn Support (Var 78)", "8/8/8/4k3/8/4K2P/8/8 w - - 0 1", "Seize the opposition with Ke3-f3! Step to g3 to control the key flank.", "Play Kf3 then Kg3.", listOf("e3f3", "e5f5", "f3g3")),
        RawLesson("Passed Pawn Support (Var 79)", "8/8/p7/4k3/8/4K3/8/8 w - - 0 1", "Seize the opposition with Ke3-f3! Step to g3 to control the key flank.", "Play Kf3 then Kg3.", listOf("e3f3", "e5f5", "f3g3")),
        RawLesson("Passed Pawn Support (Var 80)", "8/8/7p/4k3/8/4K3/8/8 w - - 0 1", "Seize the opposition with Ke3-f3! Step to g3 to control the key flank.", "Play Kf3 then Kg3.", listOf("e3f3", "e5f5", "f3g3")),
        RawLesson("Mutual Zugzwang (Trebuchet)", "8/8/8/4p3/3kP3/8/4K3/8 w - - 0 1", "Defend your pawn with Ke2-f3! When Black retreats, step forward with Kf3-f2.", "Play Kf3 then Kf2.", listOf("e2f3", "d4d3", "f3f2")),
        RawLesson("Shoulder-Charging Infiltration", "8/8/8/8/3k4/4P3/8/3K4 w - - 0 1", "Use your King to shoulder-charge the opponent King: Kd1-d2, then Ke2.", "Play Kd2 then Ke2.", listOf("d1d2", "d4e4", "d2e2")),
        RawLesson("Penetrating the Defense", "8/8/8/3k4/8/3P4/3K4/8 w - - 0 1", "Penetrate the center with Kd2-e3! Follow up by marching forward with Ke3-f3.", "Play Ke3 then Kf3.", listOf("d2e3", "d5e5", "e3f3")),
        RawLesson("Rook Pawn Blockade", "8/8/8/P7/8/k7/8/K7 w - - 0 1", "Keep the passed pawn moving with a5-a6! When Black attacks, push a6-a7.", "Advance a6 then a7.", listOf("a5a6", "a3b4", "a6a7")),
        RawLesson("Passed Pawn Support", "8/8/8/4k3/8/4K3/8/8 w - - 0 1", "Seize the opposition with Ke3-f3! Step to g3 to control the key flank.", "Play Kf3 then Kg3.", listOf("e3f3", "e5f5", "f3g3")),
        RawLesson("Mutual Zugzwang (Trebuchet)", "8/8/8/4p3/3kP3/8/4K3/8 w - - 0 1", "Defend your pawn with Ke2-f3! When Black retreats, step forward with Kf3-f2.", "Play Kf3 then Kf2.", listOf("e2f3", "d4d3", "f3f2")),
        RawLesson("Shoulder-Charging Infiltration", "8/8/8/8/3k4/4P3/8/3K4 w - - 0 1", "Use your King to shoulder-charge the opponent King: Kd1-d2, then Ke2.", "Play Kd2 then Ke2.", listOf("d1d2", "d4e4", "d2e2")),
        RawLesson("Penetrating the Defense", "8/8/8/3k4/8/3P4/3K4/8 w - - 0 1", "Penetrate the center with Kd2-e3! Follow up by marching forward with Ke3-f3.", "Play Ke3 then Kf3.", listOf("d2e3", "d5e5", "e3f3")),
        RawLesson("Rook Pawn Blockade", "8/8/8/P7/8/k7/8/K7 w - - 0 1", "Keep the passed pawn moving with a5-a6! When Black attacks, push a6-a7.", "Advance a6 then a7.", listOf("a5a6", "a3b4", "a6a7")),
        RawLesson("Passed Pawn Support", "8/8/8/4k3/8/4K3/8/8 w - - 0 1", "Seize the opposition with Ke3-f3! Step to g3 to control the key flank.", "Play Kf3 then Kg3.", listOf("e3f3", "e5f5", "f3g3")),
        RawLesson("Mutual Zugzwang (Trebuchet)", "8/8/8/4p3/3kP3/8/4K3/8 w - - 0 1", "Defend your pawn with Ke2-f3! When Black retreats, step forward with Kf3-f2.", "Play Kf3 then Kf2.", listOf("e2f3", "d4d3", "f3f2")),
        RawLesson("Shoulder-Charging Infiltration", "8/8/8/8/3k4/4P3/8/3K4 w - - 0 1", "Use your King to shoulder-charge the opponent King: Kd1-d2, then Ke2.", "Play Kd2 then Ke2.", listOf("d1d2", "d4e4", "d2e2")),
        RawLesson("Penetrating the Defense", "8/8/8/3k4/8/3P4/3K4/8 w - - 0 1", "Penetrate the center with Kd2-e3! Follow up by marching forward with Ke3-f3.", "Play Ke3 then Kf3.", listOf("d2e3", "d5e5", "e3f3")),
        RawLesson("Rook Pawn Blockade", "8/8/8/P7/8/k7/8/K7 w - - 0 1", "Keep the passed pawn moving with a5-a6! When Black attacks, push a6-a7.", "Advance a6 then a7.", listOf("a5a6", "a3b4", "a6a7")),
        RawLesson("Passed Pawn Support", "8/8/8/4k3/8/4K3/8/8 w - - 0 1", "Seize the opposition with Ke3-f3! Step to g3 to control the key flank.", "Play Kf3 then Kg3.", listOf("e3f3", "e5f5", "f3g3")),
        RawLesson("Mutual Zugzwang (Trebuchet)", "8/8/8/4p3/3kP3/8/4K3/8 w - - 0 1", "Defend your pawn with Ke2-f3! When Black retreats, step forward with Kf3-f2.", "Play Kf3 then Kf2.", listOf("e2f3", "d4d3", "f3f2")),
        RawLesson("Shoulder-Charging Infiltration", "8/8/8/8/3k4/4P3/8/3K4 w - - 0 1", "Use your King to shoulder-charge the opponent King: Kd1-d2, then Ke2.", "Play Kd2 then Ke2.", listOf("d1d2", "d4e4", "d2e2")),
        RawLesson("Penetrating the Defense", "8/8/8/3k4/8/3P4/3K4/8 w - - 0 1", "Penetrate the center with Kd2-e3! Follow up by marching forward with Ke3-f3.", "Play Ke3 then Kf3.", listOf("d2e3", "d5e5", "e3f3")),
        RawLesson("Rook Pawn Blockade", "8/8/8/P7/8/k7/8/K7 w - - 0 1", "Keep the passed pawn moving with a5-a6! When Black attacks, push a6-a7.", "Advance a6 then a7.", listOf("a5a6", "a3b4", "a6a7")),
        RawLesson("Passed Pawn Support", "8/8/8/4k3/8/4K3/8/8 w - - 0 1", "Seize the opposition with Ke3-f3! Step to g3 to control the key flank.", "Play Kf3 then Kg3.", listOf("e3f3", "e5f5", "f3g3"))
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
                id = "e_endgame_strategy_$level",
                title = "Level $level ($tier difficulty) • ${base.subtitle}",
                category = "Endgame Strategy",
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
