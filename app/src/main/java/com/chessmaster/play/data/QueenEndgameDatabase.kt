package com.chessmaster.play.data

import com.chessmaster.play.model.EndgameLesson

object QueenEndgameDatabase {

    private data class RawLesson(
        val subtitle: String,
        val fen: String,
        val explanation: String,
        val hint: String,
        val moves: List<String>
    )

    private val pool = listOf(
        RawLesson("Queen vs 7th Rank Pawn", "8/8/8/8/8/4k3/4p3/1K1Q4 w - - 0 1", "Stop the runaway 7th rank pawn! Block with Qd1-e1, force Black's King to step to f3, then pin with Qe1-d2.", "Play Qe1 then pin on d2.", listOf("d1e1", "e3f3", "e1d2")),
        RawLesson("Queen Checkmate Net", "8/8/8/8/4k3/8/4KQ2/8 w - - 0 1", "Coordinate Queen and King to confine the opponent: Qf2-e3 forces the King back, then Ke2-d3 locks the net.", "Play Qe3 then bring the King with Kd3.", listOf("f2e3", "e4d5", "e2d3")),
        RawLesson("Queen Skewer Defense", "8/8/8/8/3q4/8/3K4/3Q4 w - - 0 1", "Escape the check and create counter-threats with Kd2-e2!", "Step to e2, then f2.", listOf("d2e2", "d4e4", "e2f2")),
        RawLesson("Central Queen Dominance", "8/8/8/8/4k3/3Q4/4K3/8 w - - 0 1", "Centralize your Queen with Qd3-e3+! Confine the Black King to d5.", "Check with Qe3, then centralize Kd3.", listOf("d3e3", "e4d5", "e2d3")),
        RawLesson("Queen vs c-pawn", "8/8/8/8/8/2k5/2p5/K2Q4 w - - 0 1", "Control the promotion square: Qd1-c1, Black King to b3, then Qe3+.", "Play Qc1 then Qe3.", listOf("d1c1", "c3b3", "c1e3")),
        RawLesson("Queen Staircase Check", "8/8/8/8/4k3/8/4p3/2KQ4 w - - 0 1", "Execute the staircase maneuver with Qd1-d2+! Then blockade on e1.", "Play Qd2 then Qe1.", listOf("d1d2", "e4f3", "d2e1")),
        RawLesson("Queen King Drive", "8/8/8/8/2k5/4Q3/4K3/8 w - - 0 1", "Drive the opposing King back with Qe3-e4+! Follow up with Ke2-d3.", "Deliver check with Qe4, then Kd3.", listOf("e3e4", "c4c5", "e2d3")),
        RawLesson("Queen Escort Pass", "8/8/8/8/5k2/3Q4/4K3/8 w - - 0 1", "Confine the King with Qd3-e3+! Step forward with Ke2-f2.", "Play Qe3 then Kf2.", listOf("d3e3", "f4g4", "e2f2")),
        RawLesson("Queen vs 7th Rank Pawn (Var 9)", "8/8/8/8/8/4k3/P3p3/1K1Q4 w - - 0 1", "Stop the runaway 7th rank pawn! Block with Qd1-e1, force Black's King to step to f3, then pin with Qe1-d2.", "Play Qe1 then pin on d2.", listOf("d1e1", "e3f3", "e1d2")),
        RawLesson("Queen vs 7th Rank Pawn (Var 10)", "8/8/8/8/8/4k3/1P2p3/1K1Q4 w - - 0 1", "Stop the runaway 7th rank pawn! Block with Qd1-e1, force Black's King to step to f3, then pin with Qe1-d2.", "Play Qe1 then pin on d2.", listOf("d1e1", "e3f3", "e1d2")),
        RawLesson("Queen vs 7th Rank Pawn (Var 11)", "8/8/8/8/8/4k3/2P1p3/1K1Q4 w - - 0 1", "Stop the runaway 7th rank pawn! Block with Qd1-e1, force Black's King to step to f3, then pin with Qe1-d2.", "Play Qe1 then pin on d2.", listOf("d1e1", "e3f3", "e1d2")),
        RawLesson("Queen vs 7th Rank Pawn (Var 12)", "8/8/8/8/8/4k3/4p2P/1K1Q4 w - - 0 1", "Stop the runaway 7th rank pawn! Block with Qd1-e1, force Black's King to step to f3, then pin with Qe1-d2.", "Play Qe1 then pin on d2.", listOf("d1e1", "e3f3", "e1d2")),
        RawLesson("Queen vs 7th Rank Pawn (Var 13)", "8/8/8/8/8/4k3/4pP2/1K1Q4 w - - 0 1", "Stop the runaway 7th rank pawn! Block with Qd1-e1, force Black's King to step to f3, then pin with Qe1-d2.", "Play Qe1 then pin on d2.", listOf("d1e1", "e3f3", "e1d2")),
        RawLesson("Queen vs 7th Rank Pawn (Var 14)", "8/p7/8/8/8/4k3/4p3/1K1Q4 w - - 0 1", "Stop the runaway 7th rank pawn! Block with Qd1-e1, force Black's King to step to f3, then pin with Qe1-d2.", "Play Qe1 then pin on d2.", listOf("d1e1", "e3f3", "e1d2")),
        RawLesson("Queen vs 7th Rank Pawn (Var 15)", "8/1p6/8/8/8/4k3/4p3/1K1Q4 w - - 0 1", "Stop the runaway 7th rank pawn! Block with Qd1-e1, force Black's King to step to f3, then pin with Qe1-d2.", "Play Qe1 then pin on d2.", listOf("d1e1", "e3f3", "e1d2")),
        RawLesson("Queen vs 7th Rank Pawn (Var 16)", "8/2p5/8/8/8/4k3/4p3/1K1Q4 w - - 0 1", "Stop the runaway 7th rank pawn! Block with Qd1-e1, force Black's King to step to f3, then pin with Qe1-d2.", "Play Qe1 then pin on d2.", listOf("d1e1", "e3f3", "e1d2")),
        RawLesson("Queen vs 7th Rank Pawn (Var 17)", "8/7p/8/8/8/4k3/4p3/1K1Q4 w - - 0 1", "Stop the runaway 7th rank pawn! Block with Qd1-e1, force Black's King to step to f3, then pin with Qe1-d2.", "Play Qe1 then pin on d2.", listOf("d1e1", "e3f3", "e1d2")),
        RawLesson("Queen vs 7th Rank Pawn (Var 18)", "8/6p1/8/8/8/4k3/4p3/1K1Q4 w - - 0 1", "Stop the runaway 7th rank pawn! Block with Qd1-e1, force Black's King to step to f3, then pin with Qe1-d2.", "Play Qe1 then pin on d2.", listOf("d1e1", "e3f3", "e1d2")),
        RawLesson("Queen vs 7th Rank Pawn (Var 19)", "8/5p2/8/8/8/4k3/4p3/1K1Q4 w - - 0 1", "Stop the runaway 7th rank pawn! Block with Qd1-e1, force Black's King to step to f3, then pin with Qe1-d2.", "Play Qe1 then pin on d2.", listOf("d1e1", "e3f3", "e1d2")),
        RawLesson("Queen vs 7th Rank Pawn (Var 20)", "8/8/8/8/8/P3k3/4p3/1K1Q4 w - - 0 1", "Stop the runaway 7th rank pawn! Block with Qd1-e1, force Black's King to step to f3, then pin with Qe1-d2.", "Play Qe1 then pin on d2.", listOf("d1e1", "e3f3", "e1d2")),
        RawLesson("Queen vs 7th Rank Pawn (Var 21)", "8/8/8/8/8/4k2P/4p3/1K1Q4 w - - 0 1", "Stop the runaway 7th rank pawn! Block with Qd1-e1, force Black's King to step to f3, then pin with Qe1-d2.", "Play Qe1 then pin on d2.", listOf("d1e1", "e3f3", "e1d2")),
        RawLesson("Queen vs 7th Rank Pawn (Var 22)", "8/8/p7/8/8/4k3/4p3/1K1Q4 w - - 0 1", "Stop the runaway 7th rank pawn! Block with Qd1-e1, force Black's King to step to f3, then pin with Qe1-d2.", "Play Qe1 then pin on d2.", listOf("d1e1", "e3f3", "e1d2")),
        RawLesson("Queen vs 7th Rank Pawn (Var 23)", "8/8/7p/8/8/4k3/4p3/1K1Q4 w - - 0 1", "Stop the runaway 7th rank pawn! Block with Qd1-e1, force Black's King to step to f3, then pin with Qe1-d2.", "Play Qe1 then pin on d2.", listOf("d1e1", "e3f3", "e1d2")),
        RawLesson("Queen Checkmate Net (Var 24)", "8/8/8/8/4k3/8/P3KQ2/8 w - - 0 1", "Coordinate Queen and King to confine the opponent: Qf2-e3 forces the King back, then Ke2-d3 locks the net.", "Play Qe3 then bring the King with Kd3.", listOf("f2e3", "e4d5", "e2d3")),
        RawLesson("Queen Checkmate Net (Var 25)", "8/8/8/8/4k3/8/1P2KQ2/8 w - - 0 1", "Coordinate Queen and King to confine the opponent: Qf2-e3 forces the King back, then Ke2-d3 locks the net.", "Play Qe3 then bring the King with Kd3.", listOf("f2e3", "e4d5", "e2d3")),
        RawLesson("Queen Checkmate Net (Var 26)", "8/8/8/8/4k3/8/2P1KQ2/8 w - - 0 1", "Coordinate Queen and King to confine the opponent: Qf2-e3 forces the King back, then Ke2-d3 locks the net.", "Play Qe3 then bring the King with Kd3.", listOf("f2e3", "e4d5", "e2d3")),
        RawLesson("Queen Checkmate Net (Var 27)", "8/8/8/8/4k3/8/4KQ1P/8 w - - 0 1", "Coordinate Queen and King to confine the opponent: Qf2-e3 forces the King back, then Ke2-d3 locks the net.", "Play Qe3 then bring the King with Kd3.", listOf("f2e3", "e4d5", "e2d3")),
        RawLesson("Queen Checkmate Net (Var 28)", "8/8/8/8/4k3/8/4KQP1/8 w - - 0 1", "Coordinate Queen and King to confine the opponent: Qf2-e3 forces the King back, then Ke2-d3 locks the net.", "Play Qe3 then bring the King with Kd3.", listOf("f2e3", "e4d5", "e2d3")),
        RawLesson("Queen Checkmate Net (Var 29)", "8/p7/8/8/4k3/8/4KQ2/8 w - - 0 1", "Coordinate Queen and King to confine the opponent: Qf2-e3 forces the King back, then Ke2-d3 locks the net.", "Play Qe3 then bring the King with Kd3.", listOf("f2e3", "e4d5", "e2d3")),
        RawLesson("Queen Checkmate Net (Var 30)", "8/1p6/8/8/4k3/8/4KQ2/8 w - - 0 1", "Coordinate Queen and King to confine the opponent: Qf2-e3 forces the King back, then Ke2-d3 locks the net.", "Play Qe3 then bring the King with Kd3.", listOf("f2e3", "e4d5", "e2d3")),
        RawLesson("Queen Checkmate Net (Var 31)", "8/2p5/8/8/4k3/8/4KQ2/8 w - - 0 1", "Coordinate Queen and King to confine the opponent: Qf2-e3 forces the King back, then Ke2-d3 locks the net.", "Play Qe3 then bring the King with Kd3.", listOf("f2e3", "e4d5", "e2d3")),
        RawLesson("Queen Checkmate Net (Var 32)", "8/7p/8/8/4k3/8/4KQ2/8 w - - 0 1", "Coordinate Queen and King to confine the opponent: Qf2-e3 forces the King back, then Ke2-d3 locks the net.", "Play Qe3 then bring the King with Kd3.", listOf("f2e3", "e4d5", "e2d3")),
        RawLesson("Queen Checkmate Net (Var 33)", "8/6p1/8/8/4k3/8/4KQ2/8 w - - 0 1", "Coordinate Queen and King to confine the opponent: Qf2-e3 forces the King back, then Ke2-d3 locks the net.", "Play Qe3 then bring the King with Kd3.", listOf("f2e3", "e4d5", "e2d3")),
        RawLesson("Queen Checkmate Net (Var 34)", "8/5p2/8/8/4k3/8/4KQ2/8 w - - 0 1", "Coordinate Queen and King to confine the opponent: Qf2-e3 forces the King back, then Ke2-d3 locks the net.", "Play Qe3 then bring the King with Kd3.", listOf("f2e3", "e4d5", "e2d3")),
        RawLesson("Queen Checkmate Net (Var 35)", "8/8/8/8/4k3/P7/4KQ2/8 w - - 0 1", "Coordinate Queen and King to confine the opponent: Qf2-e3 forces the King back, then Ke2-d3 locks the net.", "Play Qe3 then bring the King with Kd3.", listOf("f2e3", "e4d5", "e2d3")),
        RawLesson("Queen Checkmate Net (Var 36)", "8/8/8/8/4k3/7P/4KQ2/8 w - - 0 1", "Coordinate Queen and King to confine the opponent: Qf2-e3 forces the King back, then Ke2-d3 locks the net.", "Play Qe3 then bring the King with Kd3.", listOf("f2e3", "e4d5", "e2d3")),
        RawLesson("Queen Checkmate Net (Var 37)", "8/8/p7/8/4k3/8/4KQ2/8 w - - 0 1", "Coordinate Queen and King to confine the opponent: Qf2-e3 forces the King back, then Ke2-d3 locks the net.", "Play Qe3 then bring the King with Kd3.", listOf("f2e3", "e4d5", "e2d3")),
        RawLesson("Queen Checkmate Net (Var 38)", "8/8/7p/8/4k3/8/4KQ2/8 w - - 0 1", "Coordinate Queen and King to confine the opponent: Qf2-e3 forces the King back, then Ke2-d3 locks the net.", "Play Qe3 then bring the King with Kd3.", listOf("f2e3", "e4d5", "e2d3")),
        RawLesson("Queen Skewer Defense (Var 39)", "8/8/8/8/3q4/8/P2K4/3Q4 w - - 0 1", "Escape the check and create counter-threats with Kd2-e2!", "Step to e2, then f2.", listOf("d2e2", "d4e4", "e2f2")),
        RawLesson("Queen Skewer Defense (Var 40)", "8/8/8/8/3q4/8/1P1K4/3Q4 w - - 0 1", "Escape the check and create counter-threats with Kd2-e2!", "Step to e2, then f2.", listOf("d2e2", "d4e4", "e2f2")),
        RawLesson("Queen Skewer Defense (Var 41)", "8/8/8/8/3q4/8/2PK4/3Q4 w - - 0 1", "Escape the check and create counter-threats with Kd2-e2!", "Step to e2, then f2.", listOf("d2e2", "d4e4", "e2f2")),
        RawLesson("Queen Skewer Defense (Var 42)", "8/8/8/8/3q4/8/3K3P/3Q4 w - - 0 1", "Escape the check and create counter-threats with Kd2-e2!", "Step to e2, then f2.", listOf("d2e2", "d4e4", "e2f2")),
        RawLesson("Queen Skewer Defense (Var 43)", "8/8/8/8/3q4/8/3K2P1/3Q4 w - - 0 1", "Escape the check and create counter-threats with Kd2-e2!", "Step to e2, then f2.", listOf("d2e2", "d4e4", "e2f2")),
        RawLesson("Queen Skewer Defense (Var 44)", "8/p7/8/8/3q4/8/3K4/3Q4 w - - 0 1", "Escape the check and create counter-threats with Kd2-e2!", "Step to e2, then f2.", listOf("d2e2", "d4e4", "e2f2")),
        RawLesson("Queen Skewer Defense (Var 45)", "8/1p6/8/8/3q4/8/3K4/3Q4 w - - 0 1", "Escape the check and create counter-threats with Kd2-e2!", "Step to e2, then f2.", listOf("d2e2", "d4e4", "e2f2")),
        RawLesson("Queen Skewer Defense (Var 46)", "8/2p5/8/8/3q4/8/3K4/3Q4 w - - 0 1", "Escape the check and create counter-threats with Kd2-e2!", "Step to e2, then f2.", listOf("d2e2", "d4e4", "e2f2")),
        RawLesson("Queen Skewer Defense (Var 47)", "8/7p/8/8/3q4/8/3K4/3Q4 w - - 0 1", "Escape the check and create counter-threats with Kd2-e2!", "Step to e2, then f2.", listOf("d2e2", "d4e4", "e2f2")),
        RawLesson("Queen Skewer Defense (Var 48)", "8/6p1/8/8/3q4/8/3K4/3Q4 w - - 0 1", "Escape the check and create counter-threats with Kd2-e2!", "Step to e2, then f2.", listOf("d2e2", "d4e4", "e2f2")),
        RawLesson("Queen Skewer Defense (Var 49)", "8/5p2/8/8/3q4/8/3K4/3Q4 w - - 0 1", "Escape the check and create counter-threats with Kd2-e2!", "Step to e2, then f2.", listOf("d2e2", "d4e4", "e2f2")),
        RawLesson("Queen Skewer Defense (Var 50)", "8/8/8/8/3q4/P7/3K4/3Q4 w - - 0 1", "Escape the check and create counter-threats with Kd2-e2!", "Step to e2, then f2.", listOf("d2e2", "d4e4", "e2f2")),
        RawLesson("Queen Skewer Defense (Var 51)", "8/8/8/8/3q4/7P/3K4/3Q4 w - - 0 1", "Escape the check and create counter-threats with Kd2-e2!", "Step to e2, then f2.", listOf("d2e2", "d4e4", "e2f2")),
        RawLesson("Queen Skewer Defense (Var 52)", "8/8/p7/8/3q4/8/3K4/3Q4 w - - 0 1", "Escape the check and create counter-threats with Kd2-e2!", "Step to e2, then f2.", listOf("d2e2", "d4e4", "e2f2")),
        RawLesson("Queen Skewer Defense (Var 53)", "8/8/7p/8/3q4/8/3K4/3Q4 w - - 0 1", "Escape the check and create counter-threats with Kd2-e2!", "Step to e2, then f2.", listOf("d2e2", "d4e4", "e2f2")),
        RawLesson("Central Queen Dominance (Var 54)", "8/8/8/8/4k3/3Q4/P3K3/8 w - - 0 1", "Centralize your Queen with Qd3-e3+! Confine the Black King to d5.", "Check with Qe3, then centralize Kd3.", listOf("d3e3", "e4d5", "e2d3")),
        RawLesson("Central Queen Dominance (Var 55)", "8/8/8/8/4k3/3Q4/1P2K3/8 w - - 0 1", "Centralize your Queen with Qd3-e3+! Confine the Black King to d5.", "Check with Qe3, then centralize Kd3.", listOf("d3e3", "e4d5", "e2d3")),
        RawLesson("Central Queen Dominance (Var 56)", "8/8/8/8/4k3/3Q4/2P1K3/8 w - - 0 1", "Centralize your Queen with Qd3-e3+! Confine the Black King to d5.", "Check with Qe3, then centralize Kd3.", listOf("d3e3", "e4d5", "e2d3")),
        RawLesson("Central Queen Dominance (Var 57)", "8/8/8/8/4k3/3Q4/4K2P/8 w - - 0 1", "Centralize your Queen with Qd3-e3+! Confine the Black King to d5.", "Check with Qe3, then centralize Kd3.", listOf("d3e3", "e4d5", "e2d3")),
        RawLesson("Central Queen Dominance (Var 58)", "8/8/8/8/4k3/3Q4/4K1P1/8 w - - 0 1", "Centralize your Queen with Qd3-e3+! Confine the Black King to d5.", "Check with Qe3, then centralize Kd3.", listOf("d3e3", "e4d5", "e2d3")),
        RawLesson("Central Queen Dominance (Var 59)", "8/8/8/8/4k3/3Q4/4KP2/8 w - - 0 1", "Centralize your Queen with Qd3-e3+! Confine the Black King to d5.", "Check with Qe3, then centralize Kd3.", listOf("d3e3", "e4d5", "e2d3")),
        RawLesson("Central Queen Dominance (Var 60)", "8/p7/8/8/4k3/3Q4/4K3/8 w - - 0 1", "Centralize your Queen with Qd3-e3+! Confine the Black King to d5.", "Check with Qe3, then centralize Kd3.", listOf("d3e3", "e4d5", "e2d3")),
        RawLesson("Central Queen Dominance (Var 61)", "8/1p6/8/8/4k3/3Q4/4K3/8 w - - 0 1", "Centralize your Queen with Qd3-e3+! Confine the Black King to d5.", "Check with Qe3, then centralize Kd3.", listOf("d3e3", "e4d5", "e2d3")),
        RawLesson("Central Queen Dominance (Var 62)", "8/2p5/8/8/4k3/3Q4/4K3/8 w - - 0 1", "Centralize your Queen with Qd3-e3+! Confine the Black King to d5.", "Check with Qe3, then centralize Kd3.", listOf("d3e3", "e4d5", "e2d3")),
        RawLesson("Central Queen Dominance (Var 63)", "8/7p/8/8/4k3/3Q4/4K3/8 w - - 0 1", "Centralize your Queen with Qd3-e3+! Confine the Black King to d5.", "Check with Qe3, then centralize Kd3.", listOf("d3e3", "e4d5", "e2d3")),
        RawLesson("Central Queen Dominance (Var 64)", "8/6p1/8/8/4k3/3Q4/4K3/8 w - - 0 1", "Centralize your Queen with Qd3-e3+! Confine the Black King to d5.", "Check with Qe3, then centralize Kd3.", listOf("d3e3", "e4d5", "e2d3")),
        RawLesson("Central Queen Dominance (Var 65)", "8/5p2/8/8/4k3/3Q4/4K3/8 w - - 0 1", "Centralize your Queen with Qd3-e3+! Confine the Black King to d5.", "Check with Qe3, then centralize Kd3.", listOf("d3e3", "e4d5", "e2d3")),
        RawLesson("Central Queen Dominance (Var 66)", "8/8/8/8/4k3/P2Q4/4K3/8 w - - 0 1", "Centralize your Queen with Qd3-e3+! Confine the Black King to d5.", "Check with Qe3, then centralize Kd3.", listOf("d3e3", "e4d5", "e2d3")),
        RawLesson("Central Queen Dominance (Var 67)", "8/8/8/8/4k3/3Q3P/4K3/8 w - - 0 1", "Centralize your Queen with Qd3-e3+! Confine the Black King to d5.", "Check with Qe3, then centralize Kd3.", listOf("d3e3", "e4d5", "e2d3")),
        RawLesson("Central Queen Dominance (Var 68)", "8/8/p7/8/4k3/3Q4/4K3/8 w - - 0 1", "Centralize your Queen with Qd3-e3+! Confine the Black King to d5.", "Check with Qe3, then centralize Kd3.", listOf("d3e3", "e4d5", "e2d3")),
        RawLesson("Central Queen Dominance (Var 69)", "8/8/7p/8/4k3/3Q4/4K3/8 w - - 0 1", "Centralize your Queen with Qd3-e3+! Confine the Black King to d5.", "Check with Qe3, then centralize Kd3.", listOf("d3e3", "e4d5", "e2d3")),
        RawLesson("Queen vs c-pawn (Var 70)", "8/8/8/8/8/2k5/1Pp5/K2Q4 w - - 0 1", "Control the promotion square: Qd1-c1, Black King to b3, then Qe3+.", "Play Qc1 then Qe3.", listOf("d1c1", "c3b3", "c1e3")),
        RawLesson("Queen vs c-pawn (Var 71)", "8/8/8/8/8/2k5/2p4P/K2Q4 w - - 0 1", "Control the promotion square: Qd1-c1, Black King to b3, then Qe3+.", "Play Qc1 then Qe3.", listOf("d1c1", "c3b3", "c1e3")),
        RawLesson("Queen vs c-pawn (Var 72)", "8/8/8/8/8/2k5/2p3P1/K2Q4 w - - 0 1", "Control the promotion square: Qd1-c1, Black King to b3, then Qe3+.", "Play Qc1 then Qe3.", listOf("d1c1", "c3b3", "c1e3")),
        RawLesson("Queen vs c-pawn (Var 73)", "8/8/8/8/8/2k5/2p2P2/K2Q4 w - - 0 1", "Control the promotion square: Qd1-c1, Black King to b3, then Qe3+.", "Play Qc1 then Qe3.", listOf("d1c1", "c3b3", "c1e3")),
        RawLesson("Queen vs c-pawn (Var 74)", "8/p7/8/8/8/2k5/2p5/K2Q4 w - - 0 1", "Control the promotion square: Qd1-c1, Black King to b3, then Qe3+.", "Play Qc1 then Qe3.", listOf("d1c1", "c3b3", "c1e3")),
        RawLesson("Queen vs c-pawn (Var 75)", "8/1p6/8/8/8/2k5/2p5/K2Q4 w - - 0 1", "Control the promotion square: Qd1-c1, Black King to b3, then Qe3+.", "Play Qc1 then Qe3.", listOf("d1c1", "c3b3", "c1e3")),
        RawLesson("Queen vs c-pawn (Var 76)", "8/2p5/8/8/8/2k5/2p5/K2Q4 w - - 0 1", "Control the promotion square: Qd1-c1, Black King to b3, then Qe3+.", "Play Qc1 then Qe3.", listOf("d1c1", "c3b3", "c1e3")),
        RawLesson("Queen vs c-pawn (Var 77)", "8/7p/8/8/8/2k5/2p5/K2Q4 w - - 0 1", "Control the promotion square: Qd1-c1, Black King to b3, then Qe3+.", "Play Qc1 then Qe3.", listOf("d1c1", "c3b3", "c1e3")),
        RawLesson("Queen vs c-pawn (Var 78)", "8/6p1/8/8/8/2k5/2p5/K2Q4 w - - 0 1", "Control the promotion square: Qd1-c1, Black King to b3, then Qe3+.", "Play Qc1 then Qe3.", listOf("d1c1", "c3b3", "c1e3")),
        RawLesson("Queen vs c-pawn (Var 79)", "8/5p2/8/8/8/2k5/2p5/K2Q4 w - - 0 1", "Control the promotion square: Qd1-c1, Black King to b3, then Qe3+.", "Play Qc1 then Qe3.", listOf("d1c1", "c3b3", "c1e3")),
        RawLesson("Queen vs c-pawn (Var 80)", "8/8/8/8/8/P1k5/2p5/K2Q4 w - - 0 1", "Control the promotion square: Qd1-c1, Black King to b3, then Qe3+.", "Play Qc1 then Qe3.", listOf("d1c1", "c3b3", "c1e3")),
        RawLesson("Queen vs c-pawn (Var 81)", "8/8/8/8/8/2k4P/2p5/K2Q4 w - - 0 1", "Control the promotion square: Qd1-c1, Black King to b3, then Qe3+.", "Play Qc1 then Qe3.", listOf("d1c1", "c3b3", "c1e3")),
        RawLesson("Queen vs c-pawn (Var 82)", "8/8/p7/8/8/2k5/2p5/K2Q4 w - - 0 1", "Control the promotion square: Qd1-c1, Black King to b3, then Qe3+.", "Play Qc1 then Qe3.", listOf("d1c1", "c3b3", "c1e3")),
        RawLesson("Queen vs c-pawn (Var 83)", "8/8/7p/8/8/2k5/2p5/K2Q4 w - - 0 1", "Control the promotion square: Qd1-c1, Black King to b3, then Qe3+.", "Play Qc1 then Qe3.", listOf("d1c1", "c3b3", "c1e3")),
        RawLesson("Queen Staircase Check (Var 84)", "8/8/8/8/4k3/8/P3p3/2KQ4 w - - 0 1", "Execute the staircase maneuver with Qd1-d2+! Then blockade on e1.", "Play Qd2 then Qe1.", listOf("d1d2", "e4f3", "d2e1")),
        RawLesson("Queen Staircase Check (Var 85)", "8/8/8/8/4k3/8/1P2p3/2KQ4 w - - 0 1", "Execute the staircase maneuver with Qd1-d2+! Then blockade on e1.", "Play Qd2 then Qe1.", listOf("d1d2", "e4f3", "d2e1")),
        RawLesson("Queen Staircase Check (Var 86)", "8/8/8/8/4k3/8/2P1p3/2KQ4 w - - 0 1", "Execute the staircase maneuver with Qd1-d2+! Then blockade on e1.", "Play Qd2 then Qe1.", listOf("d1d2", "e4f3", "d2e1")),
        RawLesson("Queen Staircase Check (Var 87)", "8/8/8/8/4k3/8/4p2P/2KQ4 w - - 0 1", "Execute the staircase maneuver with Qd1-d2+! Then blockade on e1.", "Play Qd2 then Qe1.", listOf("d1d2", "e4f3", "d2e1")),
        RawLesson("Queen Staircase Check (Var 88)", "8/8/8/8/4k3/8/4pP2/2KQ4 w - - 0 1", "Execute the staircase maneuver with Qd1-d2+! Then blockade on e1.", "Play Qd2 then Qe1.", listOf("d1d2", "e4f3", "d2e1")),
        RawLesson("Queen Staircase Check (Var 89)", "8/p7/8/8/4k3/8/4p3/2KQ4 w - - 0 1", "Execute the staircase maneuver with Qd1-d2+! Then blockade on e1.", "Play Qd2 then Qe1.", listOf("d1d2", "e4f3", "d2e1")),
        RawLesson("Queen Staircase Check (Var 90)", "8/1p6/8/8/4k3/8/4p3/2KQ4 w - - 0 1", "Execute the staircase maneuver with Qd1-d2+! Then blockade on e1.", "Play Qd2 then Qe1.", listOf("d1d2", "e4f3", "d2e1")),
        RawLesson("Queen Staircase Check (Var 91)", "8/2p5/8/8/4k3/8/4p3/2KQ4 w - - 0 1", "Execute the staircase maneuver with Qd1-d2+! Then blockade on e1.", "Play Qd2 then Qe1.", listOf("d1d2", "e4f3", "d2e1")),
        RawLesson("Queen Staircase Check (Var 92)", "8/7p/8/8/4k3/8/4p3/2KQ4 w - - 0 1", "Execute the staircase maneuver with Qd1-d2+! Then blockade on e1.", "Play Qd2 then Qe1.", listOf("d1d2", "e4f3", "d2e1")),
        RawLesson("Queen Staircase Check (Var 93)", "8/6p1/8/8/4k3/8/4p3/2KQ4 w - - 0 1", "Execute the staircase maneuver with Qd1-d2+! Then blockade on e1.", "Play Qd2 then Qe1.", listOf("d1d2", "e4f3", "d2e1")),
        RawLesson("Queen Staircase Check (Var 94)", "8/5p2/8/8/4k3/8/4p3/2KQ4 w - - 0 1", "Execute the staircase maneuver with Qd1-d2+! Then blockade on e1.", "Play Qd2 then Qe1.", listOf("d1d2", "e4f3", "d2e1")),
        RawLesson("Queen Staircase Check (Var 95)", "8/8/8/8/4k3/P7/4p3/2KQ4 w - - 0 1", "Execute the staircase maneuver with Qd1-d2+! Then blockade on e1.", "Play Qd2 then Qe1.", listOf("d1d2", "e4f3", "d2e1")),
        RawLesson("Queen Staircase Check (Var 96)", "8/8/8/8/4k3/7P/4p3/2KQ4 w - - 0 1", "Execute the staircase maneuver with Qd1-d2+! Then blockade on e1.", "Play Qd2 then Qe1.", listOf("d1d2", "e4f3", "d2e1")),
        RawLesson("Queen Staircase Check (Var 97)", "8/8/p7/8/4k3/8/4p3/2KQ4 w - - 0 1", "Execute the staircase maneuver with Qd1-d2+! Then blockade on e1.", "Play Qd2 then Qe1.", listOf("d1d2", "e4f3", "d2e1")),
        RawLesson("Queen Staircase Check (Var 98)", "8/8/7p/8/4k3/8/4p3/2KQ4 w - - 0 1", "Execute the staircase maneuver with Qd1-d2+! Then blockade on e1.", "Play Qd2 then Qe1.", listOf("d1d2", "e4f3", "d2e1")),
        RawLesson("Queen King Drive (Var 99)", "8/8/8/8/2k5/4Q3/P3K3/8 w - - 0 1", "Drive the opposing King back with Qe3-e4+! Follow up with Ke2-d3.", "Deliver check with Qe4, then Kd3.", listOf("e3e4", "c4c5", "e2d3")),
        RawLesson("Queen King Drive (Var 100)", "8/8/8/8/2k5/4Q3/1P2K3/8 w - - 0 1", "Drive the opposing King back with Qe3-e4+! Follow up with Ke2-d3.", "Deliver check with Qe4, then Kd3.", listOf("e3e4", "c4c5", "e2d3"))
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
                id = "e_queen_endgame_$level",
                title = "Level $level ($tier difficulty) • ${base.subtitle}",
                category = "Queen Endgame",
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
