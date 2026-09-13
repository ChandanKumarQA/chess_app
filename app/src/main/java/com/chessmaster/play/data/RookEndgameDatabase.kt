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
        RawLesson("Cutting Off the Enemy King", "8/8/4k3/R7/8/4K3/4P3/8 w - - 0 1", "Cut the enemy King off along the rank! Step up with Ke3-e4, then pin with Ra5-a6.", "Coordinate your King and Rook.", listOf("e3e4", "e6d6", "a5a6")),
        RawLesson("Cutting Off on c-file", "8/8/3k4/2R5/8/3K4/3P4/8 w - - 0 1", "Cut the enemy King off on the c-file! Advance Ke3-e4, then lock with Rc5-c6.", "Advance Kd3-e4.", listOf("d3e4", "d6e6", "c5c6")),
        RawLesson("Active Rook behind Passed Pawn", "8/8/8/8/4k3/4P3/8/4K2R w - - 0 1", "Place your rook actively with Rh1-h4! Keep control of the rank while pushing your pawn.", "Check with Rh4.", listOf("h1h4", "e4d3", "h4a4")),
        RawLesson("Vertical Checking Defense", "4k3/8/8/8/8/4K3/8/4R3 w - - 0 1", "Activate your King and Rook with Ke3-d4, followed by checking with Re1-d1.", "Step into discovery with Kd4.", listOf("e3d4", "e8d7", "e1d1")),
        RawLesson("File Cut-off Technique", "8/8/3k4/8/2R5/4K3/4P3/8 w - - 0 1", "Cut Black King off on the c-file! Centralize your King with Ke3-e4, then lock with Rc4-c6.", "Play Ke4 to seize the center.", listOf("e3e4", "d6e6", "c4c6")),
        RawLesson("Fourth Rank Cut-off", "8/8/4k3/8/R7/4K3/4P3/8 w - - 0 1", "Advance with Ke3-e4! When Black steps back, seal the rank with Ra4-a6.", "Advance Ke4 then Ra6.", listOf("e3e4", "e6f6", "a4a6")),
        RawLesson("Long Range Flank Defense", "8/8/4k3/8/8/8/4K3/R7 w - - 0 1", "Control the 5th rank with Ra1-a5! Cut off the enemy King from advancing.", "Lift the rook with Ra5.", listOf("a1a5", "e6d6", "e2e3")),
        RawLesson("Cutting Off the Enemy King (Var 8)", "8/8/4k3/R7/8/4K3/P3P3/8 w - - 0 1", "Cut the enemy King off along the rank! Step up with Ke3-e4, then pin with Ra5-a6.", "Coordinate your King and Rook.", listOf("e3e4", "e6d6", "a5a6")),
        RawLesson("Cutting Off the Enemy King (Var 9)", "8/8/4k3/R7/8/4K3/1P2P3/8 w - - 0 1", "Cut the enemy King off along the rank! Step up with Ke3-e4, then pin with Ra5-a6.", "Coordinate your King and Rook.", listOf("e3e4", "e6d6", "a5a6")),
        RawLesson("Cutting Off the Enemy King (Var 10)", "8/8/4k3/R7/8/4K3/2P1P3/8 w - - 0 1", "Cut the enemy King off along the rank! Step up with Ke3-e4, then pin with Ra5-a6.", "Coordinate your King and Rook.", listOf("e3e4", "e6d6", "a5a6")),
        RawLesson("Cutting Off the Enemy King (Var 11)", "8/8/4k3/R7/8/4K3/4P2P/8 w - - 0 1", "Cut the enemy King off along the rank! Step up with Ke3-e4, then pin with Ra5-a6.", "Coordinate your King and Rook.", listOf("e3e4", "e6d6", "a5a6")),
        RawLesson("Cutting Off the Enemy King (Var 12)", "8/8/4k3/R7/8/4K3/4P1P1/8 w - - 0 1", "Cut the enemy King off along the rank! Step up with Ke3-e4, then pin with Ra5-a6.", "Coordinate your King and Rook.", listOf("e3e4", "e6d6", "a5a6")),
        RawLesson("Cutting Off the Enemy King (Var 13)", "8/8/4k3/R7/8/4K3/4PP2/8 w - - 0 1", "Cut the enemy King off along the rank! Step up with Ke3-e4, then pin with Ra5-a6.", "Coordinate your King and Rook.", listOf("e3e4", "e6d6", "a5a6")),
        RawLesson("Cutting Off the Enemy King (Var 14)", "8/p7/4k3/R7/8/4K3/4P3/8 w - - 0 1", "Cut the enemy King off along the rank! Step up with Ke3-e4, then pin with Ra5-a6.", "Coordinate your King and Rook.", listOf("e3e4", "e6d6", "a5a6")),
        RawLesson("Cutting Off the Enemy King (Var 15)", "8/1p6/4k3/R7/8/4K3/4P3/8 w - - 0 1", "Cut the enemy King off along the rank! Step up with Ke3-e4, then pin with Ra5-a6.", "Coordinate your King and Rook.", listOf("e3e4", "e6d6", "a5a6")),
        RawLesson("Cutting Off the Enemy King (Var 16)", "8/2p5/4k3/R7/8/4K3/4P3/8 w - - 0 1", "Cut the enemy King off along the rank! Step up with Ke3-e4, then pin with Ra5-a6.", "Coordinate your King and Rook.", listOf("e3e4", "e6d6", "a5a6")),
        RawLesson("Cutting Off the Enemy King (Var 17)", "8/7p/4k3/R7/8/4K3/4P3/8 w - - 0 1", "Cut the enemy King off along the rank! Step up with Ke3-e4, then pin with Ra5-a6.", "Coordinate your King and Rook.", listOf("e3e4", "e6d6", "a5a6")),
        RawLesson("Cutting Off the Enemy King (Var 18)", "8/6p1/4k3/R7/8/4K3/4P3/8 w - - 0 1", "Cut the enemy King off along the rank! Step up with Ke3-e4, then pin with Ra5-a6.", "Coordinate your King and Rook.", listOf("e3e4", "e6d6", "a5a6")),
        RawLesson("Cutting Off the Enemy King (Var 19)", "8/5p2/4k3/R7/8/4K3/4P3/8 w - - 0 1", "Cut the enemy King off along the rank! Step up with Ke3-e4, then pin with Ra5-a6.", "Coordinate your King and Rook.", listOf("e3e4", "e6d6", "a5a6")),
        RawLesson("Cutting Off the Enemy King (Var 20)", "8/8/4k3/R7/8/P3K3/4P3/8 w - - 0 1", "Cut the enemy King off along the rank! Step up with Ke3-e4, then pin with Ra5-a6.", "Coordinate your King and Rook.", listOf("e3e4", "e6d6", "a5a6")),
        RawLesson("Cutting Off the Enemy King (Var 21)", "8/8/4k3/R7/8/4K2P/4P3/8 w - - 0 1", "Cut the enemy King off along the rank! Step up with Ke3-e4, then pin with Ra5-a6.", "Coordinate your King and Rook.", listOf("e3e4", "e6d6", "a5a6")),
        RawLesson("Cutting Off the Enemy King (Var 22)", "8/8/4k2p/R7/8/4K3/4P3/8 w - - 0 1", "Cut the enemy King off along the rank! Step up with Ke3-e4, then pin with Ra5-a6.", "Coordinate your King and Rook.", listOf("e3e4", "e6d6", "a5a6")),
        RawLesson("Cutting Off on c-file (Var 23)", "8/8/3k4/2R5/8/3K4/P2P4/8 w - - 0 1", "Cut the enemy King off on the c-file! Advance Ke3-e4, then lock with Rc5-c6.", "Advance Kd3-e4.", listOf("d3e4", "d6e6", "c5c6")),
        RawLesson("Cutting Off on c-file (Var 24)", "8/8/3k4/2R5/8/3K4/1P1P4/8 w - - 0 1", "Cut the enemy King off on the c-file! Advance Ke3-e4, then lock with Rc5-c6.", "Advance Kd3-e4.", listOf("d3e4", "d6e6", "c5c6")),
        RawLesson("Cutting Off on c-file (Var 25)", "8/8/3k4/2R5/8/3K4/2PP4/8 w - - 0 1", "Cut the enemy King off on the c-file! Advance Ke3-e4, then lock with Rc5-c6.", "Advance Kd3-e4.", listOf("d3e4", "d6e6", "c5c6")),
        RawLesson("Cutting Off on c-file (Var 26)", "8/8/3k4/2R5/8/3K4/3P3P/8 w - - 0 1", "Cut the enemy King off on the c-file! Advance Ke3-e4, then lock with Rc5-c6.", "Advance Kd3-e4.", listOf("d3e4", "d6e6", "c5c6")),
        RawLesson("Cutting Off on c-file (Var 27)", "8/8/3k4/2R5/8/3K4/3P2P1/8 w - - 0 1", "Cut the enemy King off on the c-file! Advance Ke3-e4, then lock with Rc5-c6.", "Advance Kd3-e4.", listOf("d3e4", "d6e6", "c5c6")),
        RawLesson("Cutting Off on c-file (Var 28)", "8/8/3k4/2R5/8/3K4/3P1P2/8 w - - 0 1", "Cut the enemy King off on the c-file! Advance Ke3-e4, then lock with Rc5-c6.", "Advance Kd3-e4.", listOf("d3e4", "d6e6", "c5c6")),
        RawLesson("Cutting Off on c-file (Var 29)", "8/p7/3k4/2R5/8/3K4/3P4/8 w - - 0 1", "Cut the enemy King off on the c-file! Advance Ke3-e4, then lock with Rc5-c6.", "Advance Kd3-e4.", listOf("d3e4", "d6e6", "c5c6")),
        RawLesson("Cutting Off on c-file (Var 30)", "8/1p6/3k4/2R5/8/3K4/3P4/8 w - - 0 1", "Cut the enemy King off on the c-file! Advance Ke3-e4, then lock with Rc5-c6.", "Advance Kd3-e4.", listOf("d3e4", "d6e6", "c5c6")),
        RawLesson("Cutting Off on c-file (Var 31)", "8/2p5/3k4/2R5/8/3K4/3P4/8 w - - 0 1", "Cut the enemy King off on the c-file! Advance Ke3-e4, then lock with Rc5-c6.", "Advance Kd3-e4.", listOf("d3e4", "d6e6", "c5c6")),
        RawLesson("Cutting Off on c-file (Var 32)", "8/7p/3k4/2R5/8/3K4/3P4/8 w - - 0 1", "Cut the enemy King off on the c-file! Advance Ke3-e4, then lock with Rc5-c6.", "Advance Kd3-e4.", listOf("d3e4", "d6e6", "c5c6")),
        RawLesson("Cutting Off on c-file (Var 33)", "8/6p1/3k4/2R5/8/3K4/3P4/8 w - - 0 1", "Cut the enemy King off on the c-file! Advance Ke3-e4, then lock with Rc5-c6.", "Advance Kd3-e4.", listOf("d3e4", "d6e6", "c5c6")),
        RawLesson("Cutting Off on c-file (Var 34)", "8/5p2/3k4/2R5/8/3K4/3P4/8 w - - 0 1", "Cut the enemy King off on the c-file! Advance Ke3-e4, then lock with Rc5-c6.", "Advance Kd3-e4.", listOf("d3e4", "d6e6", "c5c6")),
        RawLesson("Cutting Off on c-file (Var 35)", "8/8/3k4/2R5/8/P2K4/3P4/8 w - - 0 1", "Cut the enemy King off on the c-file! Advance Ke3-e4, then lock with Rc5-c6.", "Advance Kd3-e4.", listOf("d3e4", "d6e6", "c5c6")),
        RawLesson("Cutting Off on c-file (Var 36)", "8/8/3k4/2R5/8/3K3P/3P4/8 w - - 0 1", "Cut the enemy King off on the c-file! Advance Ke3-e4, then lock with Rc5-c6.", "Advance Kd3-e4.", listOf("d3e4", "d6e6", "c5c6")),
        RawLesson("Cutting Off on c-file (Var 37)", "8/8/p2k4/2R5/8/3K4/3P4/8 w - - 0 1", "Cut the enemy King off on the c-file! Advance Ke3-e4, then lock with Rc5-c6.", "Advance Kd3-e4.", listOf("d3e4", "d6e6", "c5c6")),
        RawLesson("Cutting Off on c-file (Var 38)", "8/8/3k3p/2R5/8/3K4/3P4/8 w - - 0 1", "Cut the enemy King off on the c-file! Advance Ke3-e4, then lock with Rc5-c6.", "Advance Kd3-e4.", listOf("d3e4", "d6e6", "c5c6")),
        RawLesson("Active Rook behind Passed Pawn (Var 39)", "8/8/8/8/4k3/4P3/P7/4K2R w - - 0 1", "Place your rook actively with Rh1-h4! Keep control of the rank while pushing your pawn.", "Check with Rh4.", listOf("h1h4", "e4d3", "h4a4")),
        RawLesson("Active Rook behind Passed Pawn (Var 40)", "8/8/8/8/4k3/4P3/1P6/4K2R w - - 0 1", "Place your rook actively with Rh1-h4! Keep control of the rank while pushing your pawn.", "Check with Rh4.", listOf("h1h4", "e4d3", "h4a4")),
        RawLesson("Active Rook behind Passed Pawn (Var 41)", "8/8/8/8/4k3/4P3/6P1/4K2R w - - 0 1", "Place your rook actively with Rh1-h4! Keep control of the rank while pushing your pawn.", "Check with Rh4.", listOf("h1h4", "e4d3", "h4a4")),
        RawLesson("Active Rook behind Passed Pawn (Var 42)", "8/8/8/8/4k3/4P3/5P2/4K2R w - - 0 1", "Place your rook actively with Rh1-h4! Keep control of the rank while pushing your pawn.", "Check with Rh4.", listOf("h1h4", "e4d3", "h4a4")),
        RawLesson("Active Rook behind Passed Pawn (Var 43)", "8/p7/8/8/4k3/4P3/8/4K2R w - - 0 1", "Place your rook actively with Rh1-h4! Keep control of the rank while pushing your pawn.", "Check with Rh4.", listOf("h1h4", "e4d3", "h4a4")),
        RawLesson("Active Rook behind Passed Pawn (Var 44)", "8/1p6/8/8/4k3/4P3/8/4K2R w - - 0 1", "Place your rook actively with Rh1-h4! Keep control of the rank while pushing your pawn.", "Check with Rh4.", listOf("h1h4", "e4d3", "h4a4")),
        RawLesson("Active Rook behind Passed Pawn (Var 45)", "8/2p5/8/8/4k3/4P3/8/4K2R w - - 0 1", "Place your rook actively with Rh1-h4! Keep control of the rank while pushing your pawn.", "Check with Rh4.", listOf("h1h4", "e4d3", "h4a4")),
        RawLesson("Active Rook behind Passed Pawn (Var 46)", "8/7p/8/8/4k3/4P3/8/4K2R w - - 0 1", "Place your rook actively with Rh1-h4! Keep control of the rank while pushing your pawn.", "Check with Rh4.", listOf("h1h4", "e4d3", "h4a4")),
        RawLesson("Active Rook behind Passed Pawn (Var 47)", "8/6p1/8/8/4k3/4P3/8/4K2R w - - 0 1", "Place your rook actively with Rh1-h4! Keep control of the rank while pushing your pawn.", "Check with Rh4.", listOf("h1h4", "e4d3", "h4a4")),
        RawLesson("Active Rook behind Passed Pawn (Var 48)", "8/5p2/8/8/4k3/4P3/8/4K2R w - - 0 1", "Place your rook actively with Rh1-h4! Keep control of the rank while pushing your pawn.", "Check with Rh4.", listOf("h1h4", "e4d3", "h4a4")),
        RawLesson("Active Rook behind Passed Pawn (Var 49)", "8/8/8/8/4k3/P3P3/8/4K2R w - - 0 1", "Place your rook actively with Rh1-h4! Keep control of the rank while pushing your pawn.", "Check with Rh4.", listOf("h1h4", "e4d3", "h4a4")),
        RawLesson("Active Rook behind Passed Pawn (Var 50)", "8/8/p7/8/4k3/4P3/8/4K2R w - - 0 1", "Place your rook actively with Rh1-h4! Keep control of the rank while pushing your pawn.", "Check with Rh4.", listOf("h1h4", "e4d3", "h4a4")),
        RawLesson("Active Rook behind Passed Pawn (Var 51)", "8/8/7p/8/4k3/4P3/8/4K2R w - - 0 1", "Place your rook actively with Rh1-h4! Keep control of the rank while pushing your pawn.", "Check with Rh4.", listOf("h1h4", "e4d3", "h4a4")),
        RawLesson("Vertical Checking Defense (Var 52)", "4k3/8/8/8/8/4K3/P7/4R3 w - - 0 1", "Activate your King and Rook with Ke3-d4, followed by checking with Re1-d1.", "Step into discovery with Kd4.", listOf("e3d4", "e8d7", "e1d1")),
        RawLesson("Vertical Checking Defense (Var 53)", "4k3/8/8/8/8/4K3/1P6/4R3 w - - 0 1", "Activate your King and Rook with Ke3-d4, followed by checking with Re1-d1.", "Step into discovery with Kd4.", listOf("e3d4", "e8d7", "e1d1")),
        RawLesson("Vertical Checking Defense (Var 54)", "4k3/8/8/8/8/4K3/2P5/4R3 w - - 0 1", "Activate your King and Rook with Ke3-d4, followed by checking with Re1-d1.", "Step into discovery with Kd4.", listOf("e3d4", "e8d7", "e1d1")),
        RawLesson("Vertical Checking Defense (Var 55)", "4k3/8/8/8/8/4K3/7P/4R3 w - - 0 1", "Activate your King and Rook with Ke3-d4, followed by checking with Re1-d1.", "Step into discovery with Kd4.", listOf("e3d4", "e8d7", "e1d1")),
        RawLesson("Vertical Checking Defense (Var 56)", "4k3/8/8/8/8/4K3/6P1/4R3 w - - 0 1", "Activate your King and Rook with Ke3-d4, followed by checking with Re1-d1.", "Step into discovery with Kd4.", listOf("e3d4", "e8d7", "e1d1")),
        RawLesson("Vertical Checking Defense (Var 57)", "4k3/8/8/8/8/4K3/5P2/4R3 w - - 0 1", "Activate your King and Rook with Ke3-d4, followed by checking with Re1-d1.", "Step into discovery with Kd4.", listOf("e3d4", "e8d7", "e1d1")),
        RawLesson("Vertical Checking Defense (Var 58)", "4k3/p7/8/8/8/4K3/8/4R3 w - - 0 1", "Activate your King and Rook with Ke3-d4, followed by checking with Re1-d1.", "Step into discovery with Kd4.", listOf("e3d4", "e8d7", "e1d1")),
        RawLesson("Vertical Checking Defense (Var 59)", "4k3/1p6/8/8/8/4K3/8/4R3 w - - 0 1", "Activate your King and Rook with Ke3-d4, followed by checking with Re1-d1.", "Step into discovery with Kd4.", listOf("e3d4", "e8d7", "e1d1")),
        RawLesson("Vertical Checking Defense (Var 60)", "4k3/2p5/8/8/8/4K3/8/4R3 w - - 0 1", "Activate your King and Rook with Ke3-d4, followed by checking with Re1-d1.", "Step into discovery with Kd4.", listOf("e3d4", "e8d7", "e1d1")),
        RawLesson("Vertical Checking Defense (Var 61)", "4k3/7p/8/8/8/4K3/8/4R3 w - - 0 1", "Activate your King and Rook with Ke3-d4, followed by checking with Re1-d1.", "Step into discovery with Kd4.", listOf("e3d4", "e8d7", "e1d1")),
        RawLesson("Vertical Checking Defense (Var 62)", "4k3/6p1/8/8/8/4K3/8/4R3 w - - 0 1", "Activate your King and Rook with Ke3-d4, followed by checking with Re1-d1.", "Step into discovery with Kd4.", listOf("e3d4", "e8d7", "e1d1")),
        RawLesson("Vertical Checking Defense (Var 63)", "4k3/5p2/8/8/8/4K3/8/4R3 w - - 0 1", "Activate your King and Rook with Ke3-d4, followed by checking with Re1-d1.", "Step into discovery with Kd4.", listOf("e3d4", "e8d7", "e1d1")),
        RawLesson("Vertical Checking Defense (Var 64)", "4k3/8/8/8/8/P3K3/8/4R3 w - - 0 1", "Activate your King and Rook with Ke3-d4, followed by checking with Re1-d1.", "Step into discovery with Kd4.", listOf("e3d4", "e8d7", "e1d1")),
        RawLesson("Vertical Checking Defense (Var 65)", "4k3/8/8/8/8/4K2P/8/4R3 w - - 0 1", "Activate your King and Rook with Ke3-d4, followed by checking with Re1-d1.", "Step into discovery with Kd4.", listOf("e3d4", "e8d7", "e1d1")),
        RawLesson("Vertical Checking Defense (Var 66)", "4k3/8/p7/8/8/4K3/8/4R3 w - - 0 1", "Activate your King and Rook with Ke3-d4, followed by checking with Re1-d1.", "Step into discovery with Kd4.", listOf("e3d4", "e8d7", "e1d1")),
        RawLesson("Vertical Checking Defense (Var 67)", "4k3/8/7p/8/8/4K3/8/4R3 w - - 0 1", "Activate your King and Rook with Ke3-d4, followed by checking with Re1-d1.", "Step into discovery with Kd4.", listOf("e3d4", "e8d7", "e1d1")),
        RawLesson("File Cut-off Technique (Var 68)", "8/8/3k4/8/2R5/4K3/P3P3/8 w - - 0 1", "Cut Black King off on the c-file! Centralize your King with Ke3-e4, then lock with Rc4-c6.", "Play Ke4 to seize the center.", listOf("e3e4", "d6e6", "c4c6")),
        RawLesson("File Cut-off Technique (Var 69)", "8/8/3k4/8/2R5/4K3/1P2P3/8 w - - 0 1", "Cut Black King off on the c-file! Centralize your King with Ke3-e4, then lock with Rc4-c6.", "Play Ke4 to seize the center.", listOf("e3e4", "d6e6", "c4c6")),
        RawLesson("File Cut-off Technique (Var 70)", "8/8/3k4/8/2R5/4K3/2P1P3/8 w - - 0 1", "Cut Black King off on the c-file! Centralize your King with Ke3-e4, then lock with Rc4-c6.", "Play Ke4 to seize the center.", listOf("e3e4", "d6e6", "c4c6")),
        RawLesson("File Cut-off Technique (Var 71)", "8/8/3k4/8/2R5/4K3/4P2P/8 w - - 0 1", "Cut Black King off on the c-file! Centralize your King with Ke3-e4, then lock with Rc4-c6.", "Play Ke4 to seize the center.", listOf("e3e4", "d6e6", "c4c6")),
        RawLesson("File Cut-off Technique (Var 72)", "8/8/3k4/8/2R5/4K3/4P1P1/8 w - - 0 1", "Cut Black King off on the c-file! Centralize your King with Ke3-e4, then lock with Rc4-c6.", "Play Ke4 to seize the center.", listOf("e3e4", "d6e6", "c4c6")),
        RawLesson("File Cut-off Technique (Var 73)", "8/8/3k4/8/2R5/4K3/4PP2/8 w - - 0 1", "Cut Black King off on the c-file! Centralize your King with Ke3-e4, then lock with Rc4-c6.", "Play Ke4 to seize the center.", listOf("e3e4", "d6e6", "c4c6")),
        RawLesson("File Cut-off Technique (Var 74)", "8/p7/3k4/8/2R5/4K3/4P3/8 w - - 0 1", "Cut Black King off on the c-file! Centralize your King with Ke3-e4, then lock with Rc4-c6.", "Play Ke4 to seize the center.", listOf("e3e4", "d6e6", "c4c6")),
        RawLesson("File Cut-off Technique (Var 75)", "8/1p6/3k4/8/2R5/4K3/4P3/8 w - - 0 1", "Cut Black King off on the c-file! Centralize your King with Ke3-e4, then lock with Rc4-c6.", "Play Ke4 to seize the center.", listOf("e3e4", "d6e6", "c4c6")),
        RawLesson("File Cut-off Technique (Var 76)", "8/2p5/3k4/8/2R5/4K3/4P3/8 w - - 0 1", "Cut Black King off on the c-file! Centralize your King with Ke3-e4, then lock with Rc4-c6.", "Play Ke4 to seize the center.", listOf("e3e4", "d6e6", "c4c6")),
        RawLesson("File Cut-off Technique (Var 77)", "8/7p/3k4/8/2R5/4K3/4P3/8 w - - 0 1", "Cut Black King off on the c-file! Centralize your King with Ke3-e4, then lock with Rc4-c6.", "Play Ke4 to seize the center.", listOf("e3e4", "d6e6", "c4c6")),
        RawLesson("File Cut-off Technique (Var 78)", "8/6p1/3k4/8/2R5/4K3/4P3/8 w - - 0 1", "Cut Black King off on the c-file! Centralize your King with Ke3-e4, then lock with Rc4-c6.", "Play Ke4 to seize the center.", listOf("e3e4", "d6e6", "c4c6")),
        RawLesson("File Cut-off Technique (Var 79)", "8/5p2/3k4/8/2R5/4K3/4P3/8 w - - 0 1", "Cut Black King off on the c-file! Centralize your King with Ke3-e4, then lock with Rc4-c6.", "Play Ke4 to seize the center.", listOf("e3e4", "d6e6", "c4c6")),
        RawLesson("File Cut-off Technique (Var 80)", "8/8/3k4/8/2R5/P3K3/4P3/8 w - - 0 1", "Cut Black King off on the c-file! Centralize your King with Ke3-e4, then lock with Rc4-c6.", "Play Ke4 to seize the center.", listOf("e3e4", "d6e6", "c4c6")),
        RawLesson("File Cut-off Technique (Var 81)", "8/8/3k4/8/2R5/4K2P/4P3/8 w - - 0 1", "Cut Black King off on the c-file! Centralize your King with Ke3-e4, then lock with Rc4-c6.", "Play Ke4 to seize the center.", listOf("e3e4", "d6e6", "c4c6")),
        RawLesson("File Cut-off Technique (Var 82)", "8/8/p2k4/8/2R5/4K3/4P3/8 w - - 0 1", "Cut Black King off on the c-file! Centralize your King with Ke3-e4, then lock with Rc4-c6.", "Play Ke4 to seize the center.", listOf("e3e4", "d6e6", "c4c6")),
        RawLesson("File Cut-off Technique (Var 83)", "8/8/3k3p/8/2R5/4K3/4P3/8 w - - 0 1", "Cut Black King off on the c-file! Centralize your King with Ke3-e4, then lock with Rc4-c6.", "Play Ke4 to seize the center.", listOf("e3e4", "d6e6", "c4c6")),
        RawLesson("Fourth Rank Cut-off (Var 84)", "8/8/4k3/8/R7/4K3/P3P3/8 w - - 0 1", "Advance with Ke3-e4! When Black steps back, seal the rank with Ra4-a6.", "Advance Ke4 then Ra6.", listOf("e3e4", "e6f6", "a4a6")),
        RawLesson("Fourth Rank Cut-off (Var 85)", "8/8/4k3/8/R7/4K3/1P2P3/8 w - - 0 1", "Advance with Ke3-e4! When Black steps back, seal the rank with Ra4-a6.", "Advance Ke4 then Ra6.", listOf("e3e4", "e6f6", "a4a6")),
        RawLesson("Fourth Rank Cut-off (Var 86)", "8/8/4k3/8/R7/4K3/2P1P3/8 w - - 0 1", "Advance with Ke3-e4! When Black steps back, seal the rank with Ra4-a6.", "Advance Ke4 then Ra6.", listOf("e3e4", "e6f6", "a4a6")),
        RawLesson("Fourth Rank Cut-off (Var 87)", "8/8/4k3/8/R7/4K3/4P2P/8 w - - 0 1", "Advance with Ke3-e4! When Black steps back, seal the rank with Ra4-a6.", "Advance Ke4 then Ra6.", listOf("e3e4", "e6f6", "a4a6")),
        RawLesson("Fourth Rank Cut-off (Var 88)", "8/8/4k3/8/R7/4K3/4P1P1/8 w - - 0 1", "Advance with Ke3-e4! When Black steps back, seal the rank with Ra4-a6.", "Advance Ke4 then Ra6.", listOf("e3e4", "e6f6", "a4a6")),
        RawLesson("Fourth Rank Cut-off (Var 89)", "8/8/4k3/8/R7/4K3/4PP2/8 w - - 0 1", "Advance with Ke3-e4! When Black steps back, seal the rank with Ra4-a6.", "Advance Ke4 then Ra6.", listOf("e3e4", "e6f6", "a4a6")),
        RawLesson("Fourth Rank Cut-off (Var 90)", "8/p7/4k3/8/R7/4K3/4P3/8 w - - 0 1", "Advance with Ke3-e4! When Black steps back, seal the rank with Ra4-a6.", "Advance Ke4 then Ra6.", listOf("e3e4", "e6f6", "a4a6")),
        RawLesson("Fourth Rank Cut-off (Var 91)", "8/1p6/4k3/8/R7/4K3/4P3/8 w - - 0 1", "Advance with Ke3-e4! When Black steps back, seal the rank with Ra4-a6.", "Advance Ke4 then Ra6.", listOf("e3e4", "e6f6", "a4a6")),
        RawLesson("Fourth Rank Cut-off (Var 92)", "8/2p5/4k3/8/R7/4K3/4P3/8 w - - 0 1", "Advance with Ke3-e4! When Black steps back, seal the rank with Ra4-a6.", "Advance Ke4 then Ra6.", listOf("e3e4", "e6f6", "a4a6")),
        RawLesson("Fourth Rank Cut-off (Var 93)", "8/7p/4k3/8/R7/4K3/4P3/8 w - - 0 1", "Advance with Ke3-e4! When Black steps back, seal the rank with Ra4-a6.", "Advance Ke4 then Ra6.", listOf("e3e4", "e6f6", "a4a6")),
        RawLesson("Fourth Rank Cut-off (Var 94)", "8/6p1/4k3/8/R7/4K3/4P3/8 w - - 0 1", "Advance with Ke3-e4! When Black steps back, seal the rank with Ra4-a6.", "Advance Ke4 then Ra6.", listOf("e3e4", "e6f6", "a4a6")),
        RawLesson("Fourth Rank Cut-off (Var 95)", "8/5p2/4k3/8/R7/4K3/4P3/8 w - - 0 1", "Advance with Ke3-e4! When Black steps back, seal the rank with Ra4-a6.", "Advance Ke4 then Ra6.", listOf("e3e4", "e6f6", "a4a6")),
        RawLesson("Fourth Rank Cut-off (Var 96)", "8/8/4k3/8/R7/P3K3/4P3/8 w - - 0 1", "Advance with Ke3-e4! When Black steps back, seal the rank with Ra4-a6.", "Advance Ke4 then Ra6.", listOf("e3e4", "e6f6", "a4a6")),
        RawLesson("Fourth Rank Cut-off (Var 97)", "8/8/4k3/8/R7/4K2P/4P3/8 w - - 0 1", "Advance with Ke3-e4! When Black steps back, seal the rank with Ra4-a6.", "Advance Ke4 then Ra6.", listOf("e3e4", "e6f6", "a4a6")),
        RawLesson("Fourth Rank Cut-off (Var 98)", "8/8/4k2p/8/R7/4K3/4P3/8 w - - 0 1", "Advance with Ke3-e4! When Black steps back, seal the rank with Ra4-a6.", "Advance Ke4 then Ra6.", listOf("e3e4", "e6f6", "a4a6")),
        RawLesson("Long Range Flank Defense (Var 99)", "8/8/4k3/8/8/8/1P2K3/R7 w - - 0 1", "Control the 5th rank with Ra1-a5! Cut off the enemy King from advancing.", "Lift the rook with Ra5.", listOf("a1a5", "e6d6", "e2e3")),
        RawLesson("Long Range Flank Defense (Var 100)", "8/8/4k3/8/8/8/2P1K3/R7 w - - 0 1", "Control the 5th rank with Ra1-a5! Cut off the enemy King from advancing.", "Lift the rook with Ra5.", listOf("a1a5", "e6d6", "e2e3"))
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
