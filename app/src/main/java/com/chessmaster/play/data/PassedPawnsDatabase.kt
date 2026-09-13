package com.chessmaster.play.data

import com.chessmaster.play.model.EndgameLesson

object PassedPawnsDatabase {

    private data class RawLesson(
        val subtitle: String,
        val fen: String,
        val explanation: String,
        val hint: String,
        val moves: List<String>
    )

    private val pool = listOf(
        RawLesson("Square of the Pawn (e-file)", "8/8/8/4P3/8/8/8/1k4K1 w - - 0 1", "The Black King is outside the square! Push e5-e6 and sprint to promotion with e6-e7.", "Push e6 then e7.", listOf("e5e6", "b1c2", "e6e7")),
        RawLesson("Square of the Pawn (d-file)", "8/8/8/3P4/8/8/8/1k4K1 w - - 0 1", "Outside the rule of the square: push d5-d6, Black King cannot catch up.", "Push d6 then d7.", listOf("d5d6", "b1c2", "d6d7")),
        RawLesson("Square of the Pawn (c-file)", "8/8/8/2P5/8/8/8/1k4K1 w - - 0 1", "Sprint down the c-file with c5-c6! Then advance c6-c7.", "Push c5-c6 then c7.", listOf("c5c6", "b1c2", "c6c7")),
        RawLesson("Square of the Pawn (f-file)", "8/8/8/5P2/8/8/8/1k4K1 w - - 0 1", "Sprint down the f-file with f5-f6! Continue to promotion with f6-f7.", "Push f6 then f7.", listOf("f5f6", "b1c2", "f6f7")),
        RawLesson("Outside Passed Pawn Decoy", "8/8/8/P7/8/4k3/4P3/4K3 w - - 0 1", "Use the outside passed pawn as a decoy: advance a5-a6, draw the King away.", "Push a6 then a7.", listOf("a5a6", "e3d4", "a6a7")),
        RawLesson("Connected Passed Pawns", "8/8/8/3PP3/8/4k3/8/4K3 w - - 0 1", "Advance the connected pawns in tandem: e5-e6, then d5-d6.", "Push e6 then d6.", listOf("e5e6", "e3e4", "d5d6")),
        RawLesson("Square of the Pawn (e-file) (Var 7)", "8/8/8/4P3/8/8/P7/1k4K1 w - - 0 1", "The Black King is outside the square! Push e5-e6 and sprint to promotion with e6-e7.", "Push e6 then e7.", listOf("e5e6", "b1c2", "e6e7")),
        RawLesson("Square of the Pawn (e-file) (Var 8)", "8/8/8/4P3/8/8/1P6/1k4K1 w - - 0 1", "The Black King is outside the square! Push e5-e6 and sprint to promotion with e6-e7.", "Push e6 then e7.", listOf("e5e6", "b1c2", "e6e7")),
        RawLesson("Square of the Pawn (e-file) (Var 9)", "8/8/8/4P3/8/8/7P/1k4K1 w - - 0 1", "The Black King is outside the square! Push e5-e6 and sprint to promotion with e6-e7.", "Push e6 then e7.", listOf("e5e6", "b1c2", "e6e7")),
        RawLesson("Square of the Pawn (e-file) (Var 10)", "8/8/8/4P3/8/8/6P1/1k4K1 w - - 0 1", "The Black King is outside the square! Push e5-e6 and sprint to promotion with e6-e7.", "Push e6 then e7.", listOf("e5e6", "b1c2", "e6e7")),
        RawLesson("Square of the Pawn (e-file) (Var 11)", "8/8/8/4P3/8/8/5P2/1k4K1 w - - 0 1", "The Black King is outside the square! Push e5-e6 and sprint to promotion with e6-e7.", "Push e6 then e7.", listOf("e5e6", "b1c2", "e6e7")),
        RawLesson("Square of the Pawn (e-file) (Var 12)", "8/p7/8/4P3/8/8/8/1k4K1 w - - 0 1", "The Black King is outside the square! Push e5-e6 and sprint to promotion with e6-e7.", "Push e6 then e7.", listOf("e5e6", "b1c2", "e6e7")),
        RawLesson("Square of the Pawn (e-file) (Var 13)", "8/1p6/8/4P3/8/8/8/1k4K1 w - - 0 1", "The Black King is outside the square! Push e5-e6 and sprint to promotion with e6-e7.", "Push e6 then e7.", listOf("e5e6", "b1c2", "e6e7")),
        RawLesson("Square of the Pawn (e-file) (Var 14)", "8/2p5/8/4P3/8/8/8/1k4K1 w - - 0 1", "The Black King is outside the square! Push e5-e6 and sprint to promotion with e6-e7.", "Push e6 then e7.", listOf("e5e6", "b1c2", "e6e7")),
        RawLesson("Square of the Pawn (e-file) (Var 15)", "8/7p/8/4P3/8/8/8/1k4K1 w - - 0 1", "The Black King is outside the square! Push e5-e6 and sprint to promotion with e6-e7.", "Push e6 then e7.", listOf("e5e6", "b1c2", "e6e7")),
        RawLesson("Square of the Pawn (e-file) (Var 16)", "8/6p1/8/4P3/8/8/8/1k4K1 w - - 0 1", "The Black King is outside the square! Push e5-e6 and sprint to promotion with e6-e7.", "Push e6 then e7.", listOf("e5e6", "b1c2", "e6e7")),
        RawLesson("Square of the Pawn (e-file) (Var 17)", "8/5p2/8/4P3/8/8/8/1k4K1 w - - 0 1", "The Black King is outside the square! Push e5-e6 and sprint to promotion with e6-e7.", "Push e6 then e7.", listOf("e5e6", "b1c2", "e6e7")),
        RawLesson("Square of the Pawn (e-file) (Var 18)", "8/8/8/4P3/8/P7/8/1k4K1 w - - 0 1", "The Black King is outside the square! Push e5-e6 and sprint to promotion with e6-e7.", "Push e6 then e7.", listOf("e5e6", "b1c2", "e6e7")),
        RawLesson("Square of the Pawn (e-file) (Var 19)", "8/8/8/4P3/8/7P/8/1k4K1 w - - 0 1", "The Black King is outside the square! Push e5-e6 and sprint to promotion with e6-e7.", "Push e6 then e7.", listOf("e5e6", "b1c2", "e6e7")),
        RawLesson("Square of the Pawn (e-file) (Var 20)", "8/8/p7/4P3/8/8/8/1k4K1 w - - 0 1", "The Black King is outside the square! Push e5-e6 and sprint to promotion with e6-e7.", "Push e6 then e7.", listOf("e5e6", "b1c2", "e6e7")),
        RawLesson("Square of the Pawn (e-file) (Var 21)", "8/8/7p/4P3/8/8/8/1k4K1 w - - 0 1", "The Black King is outside the square! Push e5-e6 and sprint to promotion with e6-e7.", "Push e6 then e7.", listOf("e5e6", "b1c2", "e6e7")),
        RawLesson("Square of the Pawn (d-file) (Var 22)", "8/8/8/3P4/8/8/P7/1k4K1 w - - 0 1", "Outside the rule of the square: push d5-d6, Black King cannot catch up.", "Push d6 then d7.", listOf("d5d6", "b1c2", "d6d7")),
        RawLesson("Square of the Pawn (d-file) (Var 23)", "8/8/8/3P4/8/8/1P6/1k4K1 w - - 0 1", "Outside the rule of the square: push d5-d6, Black King cannot catch up.", "Push d6 then d7.", listOf("d5d6", "b1c2", "d6d7")),
        RawLesson("Square of the Pawn (d-file) (Var 24)", "8/8/8/3P4/8/8/7P/1k4K1 w - - 0 1", "Outside the rule of the square: push d5-d6, Black King cannot catch up.", "Push d6 then d7.", listOf("d5d6", "b1c2", "d6d7")),
        RawLesson("Square of the Pawn (d-file) (Var 25)", "8/8/8/3P4/8/8/6P1/1k4K1 w - - 0 1", "Outside the rule of the square: push d5-d6, Black King cannot catch up.", "Push d6 then d7.", listOf("d5d6", "b1c2", "d6d7")),
        RawLesson("Square of the Pawn (d-file) (Var 26)", "8/8/8/3P4/8/8/5P2/1k4K1 w - - 0 1", "Outside the rule of the square: push d5-d6, Black King cannot catch up.", "Push d6 then d7.", listOf("d5d6", "b1c2", "d6d7")),
        RawLesson("Square of the Pawn (d-file) (Var 27)", "8/p7/8/3P4/8/8/8/1k4K1 w - - 0 1", "Outside the rule of the square: push d5-d6, Black King cannot catch up.", "Push d6 then d7.", listOf("d5d6", "b1c2", "d6d7")),
        RawLesson("Square of the Pawn (d-file) (Var 28)", "8/1p6/8/3P4/8/8/8/1k4K1 w - - 0 1", "Outside the rule of the square: push d5-d6, Black King cannot catch up.", "Push d6 then d7.", listOf("d5d6", "b1c2", "d6d7")),
        RawLesson("Square of the Pawn (d-file) (Var 29)", "8/2p5/8/3P4/8/8/8/1k4K1 w - - 0 1", "Outside the rule of the square: push d5-d6, Black King cannot catch up.", "Push d6 then d7.", listOf("d5d6", "b1c2", "d6d7")),
        RawLesson("Square of the Pawn (d-file) (Var 30)", "8/7p/8/3P4/8/8/8/1k4K1 w - - 0 1", "Outside the rule of the square: push d5-d6, Black King cannot catch up.", "Push d6 then d7.", listOf("d5d6", "b1c2", "d6d7")),
        RawLesson("Square of the Pawn (d-file) (Var 31)", "8/6p1/8/3P4/8/8/8/1k4K1 w - - 0 1", "Outside the rule of the square: push d5-d6, Black King cannot catch up.", "Push d6 then d7.", listOf("d5d6", "b1c2", "d6d7")),
        RawLesson("Square of the Pawn (d-file) (Var 32)", "8/5p2/8/3P4/8/8/8/1k4K1 w - - 0 1", "Outside the rule of the square: push d5-d6, Black King cannot catch up.", "Push d6 then d7.", listOf("d5d6", "b1c2", "d6d7")),
        RawLesson("Square of the Pawn (d-file) (Var 33)", "8/8/8/3P4/8/P7/8/1k4K1 w - - 0 1", "Outside the rule of the square: push d5-d6, Black King cannot catch up.", "Push d6 then d7.", listOf("d5d6", "b1c2", "d6d7")),
        RawLesson("Square of the Pawn (d-file) (Var 34)", "8/8/8/3P4/8/7P/8/1k4K1 w - - 0 1", "Outside the rule of the square: push d5-d6, Black King cannot catch up.", "Push d6 then d7.", listOf("d5d6", "b1c2", "d6d7")),
        RawLesson("Square of the Pawn (d-file) (Var 35)", "8/8/p7/3P4/8/8/8/1k4K1 w - - 0 1", "Outside the rule of the square: push d5-d6, Black King cannot catch up.", "Push d6 then d7.", listOf("d5d6", "b1c2", "d6d7")),
        RawLesson("Square of the Pawn (d-file) (Var 36)", "8/8/7p/3P4/8/8/8/1k4K1 w - - 0 1", "Outside the rule of the square: push d5-d6, Black King cannot catch up.", "Push d6 then d7.", listOf("d5d6", "b1c2", "d6d7")),
        RawLesson("Square of the Pawn (c-file) (Var 37)", "8/8/8/2P5/8/8/P7/1k4K1 w - - 0 1", "Sprint down the c-file with c5-c6! Then advance c6-c7.", "Push c5-c6 then c7.", listOf("c5c6", "b1c2", "c6c7")),
        RawLesson("Square of the Pawn (c-file) (Var 38)", "8/8/8/2P5/8/8/1P6/1k4K1 w - - 0 1", "Sprint down the c-file with c5-c6! Then advance c6-c7.", "Push c5-c6 then c7.", listOf("c5c6", "b1c2", "c6c7")),
        RawLesson("Square of the Pawn (c-file) (Var 39)", "8/8/8/2P5/8/8/7P/1k4K1 w - - 0 1", "Sprint down the c-file with c5-c6! Then advance c6-c7.", "Push c5-c6 then c7.", listOf("c5c6", "b1c2", "c6c7")),
        RawLesson("Square of the Pawn (c-file) (Var 40)", "8/8/8/2P5/8/8/6P1/1k4K1 w - - 0 1", "Sprint down the c-file with c5-c6! Then advance c6-c7.", "Push c5-c6 then c7.", listOf("c5c6", "b1c2", "c6c7")),
        RawLesson("Square of the Pawn (c-file) (Var 41)", "8/8/8/2P5/8/8/5P2/1k4K1 w - - 0 1", "Sprint down the c-file with c5-c6! Then advance c6-c7.", "Push c5-c6 then c7.", listOf("c5c6", "b1c2", "c6c7")),
        RawLesson("Square of the Pawn (c-file) (Var 42)", "8/p7/8/2P5/8/8/8/1k4K1 w - - 0 1", "Sprint down the c-file with c5-c6! Then advance c6-c7.", "Push c5-c6 then c7.", listOf("c5c6", "b1c2", "c6c7")),
        RawLesson("Square of the Pawn (c-file) (Var 43)", "8/1p6/8/2P5/8/8/8/1k4K1 w - - 0 1", "Sprint down the c-file with c5-c6! Then advance c6-c7.", "Push c5-c6 then c7.", listOf("c5c6", "b1c2", "c6c7")),
        RawLesson("Square of the Pawn (c-file) (Var 44)", "8/7p/8/2P5/8/8/8/1k4K1 w - - 0 1", "Sprint down the c-file with c5-c6! Then advance c6-c7.", "Push c5-c6 then c7.", listOf("c5c6", "b1c2", "c6c7")),
        RawLesson("Square of the Pawn (c-file) (Var 45)", "8/6p1/8/2P5/8/8/8/1k4K1 w - - 0 1", "Sprint down the c-file with c5-c6! Then advance c6-c7.", "Push c5-c6 then c7.", listOf("c5c6", "b1c2", "c6c7")),
        RawLesson("Square of the Pawn (c-file) (Var 46)", "8/5p2/8/2P5/8/8/8/1k4K1 w - - 0 1", "Sprint down the c-file with c5-c6! Then advance c6-c7.", "Push c5-c6 then c7.", listOf("c5c6", "b1c2", "c6c7")),
        RawLesson("Square of the Pawn (c-file) (Var 47)", "8/8/8/2P5/8/P7/8/1k4K1 w - - 0 1", "Sprint down the c-file with c5-c6! Then advance c6-c7.", "Push c5-c6 then c7.", listOf("c5c6", "b1c2", "c6c7")),
        RawLesson("Square of the Pawn (c-file) (Var 48)", "8/8/8/2P5/8/7P/8/1k4K1 w - - 0 1", "Sprint down the c-file with c5-c6! Then advance c6-c7.", "Push c5-c6 then c7.", listOf("c5c6", "b1c2", "c6c7")),
        RawLesson("Square of the Pawn (c-file) (Var 49)", "8/8/p7/2P5/8/8/8/1k4K1 w - - 0 1", "Sprint down the c-file with c5-c6! Then advance c6-c7.", "Push c5-c6 then c7.", listOf("c5c6", "b1c2", "c6c7")),
        RawLesson("Square of the Pawn (c-file) (Var 50)", "8/8/7p/2P5/8/8/8/1k4K1 w - - 0 1", "Sprint down the c-file with c5-c6! Then advance c6-c7.", "Push c5-c6 then c7.", listOf("c5c6", "b1c2", "c6c7")),
        RawLesson("Square of the Pawn (f-file) (Var 51)", "8/8/8/5P2/8/8/P7/1k4K1 w - - 0 1", "Sprint down the f-file with f5-f6! Continue to promotion with f6-f7.", "Push f6 then f7.", listOf("f5f6", "b1c2", "f6f7")),
        RawLesson("Square of the Pawn (f-file) (Var 52)", "8/8/8/5P2/8/8/1P6/1k4K1 w - - 0 1", "Sprint down the f-file with f5-f6! Continue to promotion with f6-f7.", "Push f6 then f7.", listOf("f5f6", "b1c2", "f6f7")),
        RawLesson("Square of the Pawn (f-file) (Var 53)", "8/8/8/5P2/8/8/7P/1k4K1 w - - 0 1", "Sprint down the f-file with f5-f6! Continue to promotion with f6-f7.", "Push f6 then f7.", listOf("f5f6", "b1c2", "f6f7")),
        RawLesson("Square of the Pawn (f-file) (Var 54)", "8/8/8/5P2/8/8/6P1/1k4K1 w - - 0 1", "Sprint down the f-file with f5-f6! Continue to promotion with f6-f7.", "Push f6 then f7.", listOf("f5f6", "b1c2", "f6f7")),
        RawLesson("Square of the Pawn (f-file) (Var 55)", "8/8/8/5P2/8/8/5P2/1k4K1 w - - 0 1", "Sprint down the f-file with f5-f6! Continue to promotion with f6-f7.", "Push f6 then f7.", listOf("f5f6", "b1c2", "f6f7")),
        RawLesson("Square of the Pawn (f-file) (Var 56)", "8/p7/8/5P2/8/8/8/1k4K1 w - - 0 1", "Sprint down the f-file with f5-f6! Continue to promotion with f6-f7.", "Push f6 then f7.", listOf("f5f6", "b1c2", "f6f7")),
        RawLesson("Square of the Pawn (f-file) (Var 57)", "8/1p6/8/5P2/8/8/8/1k4K1 w - - 0 1", "Sprint down the f-file with f5-f6! Continue to promotion with f6-f7.", "Push f6 then f7.", listOf("f5f6", "b1c2", "f6f7")),
        RawLesson("Square of the Pawn (f-file) (Var 58)", "8/2p5/8/5P2/8/8/8/1k4K1 w - - 0 1", "Sprint down the f-file with f5-f6! Continue to promotion with f6-f7.", "Push f6 then f7.", listOf("f5f6", "b1c2", "f6f7")),
        RawLesson("Square of the Pawn (f-file) (Var 59)", "8/7p/8/5P2/8/8/8/1k4K1 w - - 0 1", "Sprint down the f-file with f5-f6! Continue to promotion with f6-f7.", "Push f6 then f7.", listOf("f5f6", "b1c2", "f6f7")),
        RawLesson("Square of the Pawn (f-file) (Var 60)", "8/6p1/8/5P2/8/8/8/1k4K1 w - - 0 1", "Sprint down the f-file with f5-f6! Continue to promotion with f6-f7.", "Push f6 then f7.", listOf("f5f6", "b1c2", "f6f7")),
        RawLesson("Square of the Pawn (f-file) (Var 61)", "8/8/8/5P2/8/P7/8/1k4K1 w - - 0 1", "Sprint down the f-file with f5-f6! Continue to promotion with f6-f7.", "Push f6 then f7.", listOf("f5f6", "b1c2", "f6f7")),
        RawLesson("Square of the Pawn (f-file) (Var 62)", "8/8/8/5P2/8/7P/8/1k4K1 w - - 0 1", "Sprint down the f-file with f5-f6! Continue to promotion with f6-f7.", "Push f6 then f7.", listOf("f5f6", "b1c2", "f6f7")),
        RawLesson("Square of the Pawn (f-file) (Var 63)", "8/8/p7/5P2/8/8/8/1k4K1 w - - 0 1", "Sprint down the f-file with f5-f6! Continue to promotion with f6-f7.", "Push f6 then f7.", listOf("f5f6", "b1c2", "f6f7")),
        RawLesson("Square of the Pawn (f-file) (Var 64)", "8/8/7p/5P2/8/8/8/1k4K1 w - - 0 1", "Sprint down the f-file with f5-f6! Continue to promotion with f6-f7.", "Push f6 then f7.", listOf("f5f6", "b1c2", "f6f7")),
        RawLesson("Outside Passed Pawn Decoy (Var 65)", "8/8/8/P7/8/4k3/P3P3/4K3 w - - 0 1", "Use the outside passed pawn as a decoy: advance a5-a6, draw the King away.", "Push a6 then a7.", listOf("a5a6", "e3d4", "a6a7")),
        RawLesson("Outside Passed Pawn Decoy (Var 66)", "8/8/8/P7/8/4k3/1P2P3/4K3 w - - 0 1", "Use the outside passed pawn as a decoy: advance a5-a6, draw the King away.", "Push a6 then a7.", listOf("a5a6", "e3d4", "a6a7")),
        RawLesson("Outside Passed Pawn Decoy (Var 67)", "8/8/8/P7/8/4k3/2P1P3/4K3 w - - 0 1", "Use the outside passed pawn as a decoy: advance a5-a6, draw the King away.", "Push a6 then a7.", listOf("a5a6", "e3d4", "a6a7")),
        RawLesson("Outside Passed Pawn Decoy (Var 68)", "8/8/8/P7/8/4k3/4P2P/4K3 w - - 0 1", "Use the outside passed pawn as a decoy: advance a5-a6, draw the King away.", "Push a6 then a7.", listOf("a5a6", "e3d4", "a6a7")),
        RawLesson("Outside Passed Pawn Decoy (Var 69)", "8/8/8/P7/8/4k3/4P1P1/4K3 w - - 0 1", "Use the outside passed pawn as a decoy: advance a5-a6, draw the King away.", "Push a6 then a7.", listOf("a5a6", "e3d4", "a6a7")),
        RawLesson("Outside Passed Pawn Decoy (Var 70)", "8/8/8/P7/8/4k3/4PP2/4K3 w - - 0 1", "Use the outside passed pawn as a decoy: advance a5-a6, draw the King away.", "Push a6 then a7.", listOf("a5a6", "e3d4", "a6a7")),
        RawLesson("Outside Passed Pawn Decoy (Var 71)", "8/1p6/8/P7/8/4k3/4P3/4K3 w - - 0 1", "Use the outside passed pawn as a decoy: advance a5-a6, draw the King away.", "Push a6 then a7.", listOf("a5a6", "e3d4", "a6a7")),
        RawLesson("Outside Passed Pawn Decoy (Var 72)", "8/2p5/8/P7/8/4k3/4P3/4K3 w - - 0 1", "Use the outside passed pawn as a decoy: advance a5-a6, draw the King away.", "Push a6 then a7.", listOf("a5a6", "e3d4", "a6a7")),
        RawLesson("Outside Passed Pawn Decoy (Var 73)", "8/7p/8/P7/8/4k3/4P3/4K3 w - - 0 1", "Use the outside passed pawn as a decoy: advance a5-a6, draw the King away.", "Push a6 then a7.", listOf("a5a6", "e3d4", "a6a7")),
        RawLesson("Outside Passed Pawn Decoy (Var 74)", "8/6p1/8/P7/8/4k3/4P3/4K3 w - - 0 1", "Use the outside passed pawn as a decoy: advance a5-a6, draw the King away.", "Push a6 then a7.", listOf("a5a6", "e3d4", "a6a7")),
        RawLesson("Outside Passed Pawn Decoy (Var 75)", "8/5p2/8/P7/8/4k3/4P3/4K3 w - - 0 1", "Use the outside passed pawn as a decoy: advance a5-a6, draw the King away.", "Push a6 then a7.", listOf("a5a6", "e3d4", "a6a7")),
        RawLesson("Outside Passed Pawn Decoy (Var 76)", "8/8/8/P7/8/P3k3/4P3/4K3 w - - 0 1", "Use the outside passed pawn as a decoy: advance a5-a6, draw the King away.", "Push a6 then a7.", listOf("a5a6", "e3d4", "a6a7")),
        RawLesson("Outside Passed Pawn Decoy (Var 77)", "8/8/8/P7/8/4k2P/4P3/4K3 w - - 0 1", "Use the outside passed pawn as a decoy: advance a5-a6, draw the King away.", "Push a6 then a7.", listOf("a5a6", "e3d4", "a6a7")),
        RawLesson("Outside Passed Pawn Decoy (Var 78)", "8/8/7p/P7/8/4k3/4P3/4K3 w - - 0 1", "Use the outside passed pawn as a decoy: advance a5-a6, draw the King away.", "Push a6 then a7.", listOf("a5a6", "e3d4", "a6a7")),
        RawLesson("Connected Passed Pawns (Var 79)", "8/8/8/3PP3/8/4k3/P7/4K3 w - - 0 1", "Advance the connected pawns in tandem: e5-e6, then d5-d6.", "Push e6 then d6.", listOf("e5e6", "e3e4", "d5d6")),
        RawLesson("Connected Passed Pawns (Var 80)", "8/8/8/3PP3/8/4k3/1P6/4K3 w - - 0 1", "Advance the connected pawns in tandem: e5-e6, then d5-d6.", "Push e6 then d6.", listOf("e5e6", "e3e4", "d5d6")),
        RawLesson("Connected Passed Pawns (Var 81)", "8/8/8/3PP3/8/4k3/2P5/4K3 w - - 0 1", "Advance the connected pawns in tandem: e5-e6, then d5-d6.", "Push e6 then d6.", listOf("e5e6", "e3e4", "d5d6")),
        RawLesson("Connected Passed Pawns (Var 82)", "8/8/8/3PP3/8/4k3/7P/4K3 w - - 0 1", "Advance the connected pawns in tandem: e5-e6, then d5-d6.", "Push e6 then d6.", listOf("e5e6", "e3e4", "d5d6")),
        RawLesson("Connected Passed Pawns (Var 83)", "8/8/8/3PP3/8/4k3/6P1/4K3 w - - 0 1", "Advance the connected pawns in tandem: e5-e6, then d5-d6.", "Push e6 then d6.", listOf("e5e6", "e3e4", "d5d6")),
        RawLesson("Connected Passed Pawns (Var 84)", "8/8/8/3PP3/8/4k3/5P2/4K3 w - - 0 1", "Advance the connected pawns in tandem: e5-e6, then d5-d6.", "Push e6 then d6.", listOf("e5e6", "e3e4", "d5d6")),
        RawLesson("Connected Passed Pawns (Var 85)", "8/p7/8/3PP3/8/4k3/8/4K3 w - - 0 1", "Advance the connected pawns in tandem: e5-e6, then d5-d6.", "Push e6 then d6.", listOf("e5e6", "e3e4", "d5d6")),
        RawLesson("Connected Passed Pawns (Var 86)", "8/1p6/8/3PP3/8/4k3/8/4K3 w - - 0 1", "Advance the connected pawns in tandem: e5-e6, then d5-d6.", "Push e6 then d6.", listOf("e5e6", "e3e4", "d5d6")),
        RawLesson("Connected Passed Pawns (Var 87)", "8/2p5/8/3PP3/8/4k3/8/4K3 w - - 0 1", "Advance the connected pawns in tandem: e5-e6, then d5-d6.", "Push e6 then d6.", listOf("e5e6", "e3e4", "d5d6")),
        RawLesson("Connected Passed Pawns (Var 88)", "8/7p/8/3PP3/8/4k3/8/4K3 w - - 0 1", "Advance the connected pawns in tandem: e5-e6, then d5-d6.", "Push e6 then d6.", listOf("e5e6", "e3e4", "d5d6")),
        RawLesson("Connected Passed Pawns (Var 89)", "8/6p1/8/3PP3/8/4k3/8/4K3 w - - 0 1", "Advance the connected pawns in tandem: e5-e6, then d5-d6.", "Push e6 then d6.", listOf("e5e6", "e3e4", "d5d6")),
        RawLesson("Connected Passed Pawns (Var 90)", "8/5p2/8/3PP3/8/4k3/8/4K3 w - - 0 1", "Advance the connected pawns in tandem: e5-e6, then d5-d6.", "Push e6 then d6.", listOf("e5e6", "e3e4", "d5d6")),
        RawLesson("Connected Passed Pawns (Var 91)", "8/8/8/3PP3/8/P3k3/8/4K3 w - - 0 1", "Advance the connected pawns in tandem: e5-e6, then d5-d6.", "Push e6 then d6.", listOf("e5e6", "e3e4", "d5d6")),
        RawLesson("Connected Passed Pawns (Var 92)", "8/8/8/3PP3/8/4k2P/8/4K3 w - - 0 1", "Advance the connected pawns in tandem: e5-e6, then d5-d6.", "Push e6 then d6.", listOf("e5e6", "e3e4", "d5d6")),
        RawLesson("Connected Passed Pawns (Var 93)", "8/8/p7/3PP3/8/4k3/8/4K3 w - - 0 1", "Advance the connected pawns in tandem: e5-e6, then d5-d6.", "Push e6 then d6.", listOf("e5e6", "e3e4", "d5d6")),
        RawLesson("Connected Passed Pawns (Var 94)", "8/8/7p/3PP3/8/4k3/8/4K3 w - - 0 1", "Advance the connected pawns in tandem: e5-e6, then d5-d6.", "Push e6 then d6.", listOf("e5e6", "e3e4", "d5d6")),
        RawLesson("Square of the Pawn (e-file)", "8/8/8/4P3/8/8/8/1k4K1 w - - 0 1", "The Black King is outside the square! Push e5-e6 and sprint to promotion with e6-e7.", "Push e6 then e7.", listOf("e5e6", "b1c2", "e6e7")),
        RawLesson("Square of the Pawn (d-file)", "8/8/8/3P4/8/8/8/1k4K1 w - - 0 1", "Outside the rule of the square: push d5-d6, Black King cannot catch up.", "Push d6 then d7.", listOf("d5d6", "b1c2", "d6d7")),
        RawLesson("Square of the Pawn (c-file)", "8/8/8/2P5/8/8/8/1k4K1 w - - 0 1", "Sprint down the c-file with c5-c6! Then advance c6-c7.", "Push c5-c6 then c7.", listOf("c5c6", "b1c2", "c6c7")),
        RawLesson("Square of the Pawn (f-file)", "8/8/8/5P2/8/8/8/1k4K1 w - - 0 1", "Sprint down the f-file with f5-f6! Continue to promotion with f6-f7.", "Push f6 then f7.", listOf("f5f6", "b1c2", "f6f7")),
        RawLesson("Outside Passed Pawn Decoy", "8/8/8/P7/8/4k3/4P3/4K3 w - - 0 1", "Use the outside passed pawn as a decoy: advance a5-a6, draw the King away.", "Push a6 then a7.", listOf("a5a6", "e3d4", "a6a7")),
        RawLesson("Connected Passed Pawns", "8/8/8/3PP3/8/4k3/8/4K3 w - - 0 1", "Advance the connected pawns in tandem: e5-e6, then d5-d6.", "Push e6 then d6.", listOf("e5e6", "e3e4", "d5d6"))
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
                id = "e_passed_pawns_$level",
                title = "Level $level ($tier difficulty) • ${base.subtitle}",
                category = "Passed Pawns",
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
