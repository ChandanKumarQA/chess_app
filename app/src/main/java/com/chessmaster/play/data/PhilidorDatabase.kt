package com.chessmaster.play.data

import com.chessmaster.play.model.EndgameLesson

object PhilidorDatabase {

    private data class RawLesson(
        val subtitle: String,
        val fen: String,
        val explanation: String,
        val hint: String,
        val moves: List<String>
    )

    private val pool = listOf(
        RawLesson("Philidor Defense (e-file)", "8/8/r7/4P3/4K3/8/8/1k6 w - - 0 1", "Classic Philidor Defense: as soon as the pawn pushes to e6, drop the Rook back to a1 to deliver perpetual checks from behind!", "Push e6, Black drops to a1, then Kd5.", listOf("e5e6", "a6a1", "e4d5")),
        RawLesson("Philidor Defense (d-file)", "8/8/r7/3P4/3K4/8/8/1k6 w - - 0 1", "Third-rank defense on the d-file: pawn advances to d6, and the attacking king moves to c5.", "Push d6, then step to c5.", listOf("d5d6", "a6a1", "d4c5")),
        RawLesson("Philidor Defense (c-file)", "8/8/r7/2P5/2K5/8/8/1k6 w - - 0 1", "Third-rank defense on the c-file: push c6, black rook retreats to a1, white king steps to b5.", "Advance c6, then Kb5.", listOf("c5c6", "a6a1", "c4b5")),
        RawLesson("Philidor Defense (f-file)", "8/8/r7/5P2/5K2/8/8/1k6 w - - 0 1", "Third-rank defense on the f-file: push f6, Black seeks rear checks, White king advances to e5.", "Push f6, then Ke5.", listOf("f5f6", "a6a1", "f4e5")),
        RawLesson("Philidor Defense (b-file)", "8/8/r7/1P6/1K6/8/8/1k6 w - - 0 1", "Third-rank defense on the b-file: push b6, Black rook retreats, White king moves to c5.", "Push b6, then Kc5.", listOf("b5b6", "a6a1", "b4c5")),
        RawLesson("Philidor Defense (e-file) (Var 6)", "8/8/r7/4P3/4K3/8/1P6/1k6 w - - 0 1", "Classic Philidor Defense: as soon as the pawn pushes to e6, drop the Rook back to a1 to deliver perpetual checks from behind!", "Push e6, Black drops to a1, then Kd5.", listOf("e5e6", "a6a1", "e4d5")),
        RawLesson("Philidor Defense (e-file) (Var 7)", "8/8/r7/4P3/4K3/8/2P5/1k6 w - - 0 1", "Classic Philidor Defense: as soon as the pawn pushes to e6, drop the Rook back to a1 to deliver perpetual checks from behind!", "Push e6, Black drops to a1, then Kd5.", listOf("e5e6", "a6a1", "e4d5")),
        RawLesson("Philidor Defense (e-file) (Var 8)", "8/8/r7/4P3/4K3/8/7P/1k6 w - - 0 1", "Classic Philidor Defense: as soon as the pawn pushes to e6, drop the Rook back to a1 to deliver perpetual checks from behind!", "Push e6, Black drops to a1, then Kd5.", listOf("e5e6", "a6a1", "e4d5")),
        RawLesson("Philidor Defense (e-file) (Var 9)", "8/8/r7/4P3/4K3/8/6P1/1k6 w - - 0 1", "Classic Philidor Defense: as soon as the pawn pushes to e6, drop the Rook back to a1 to deliver perpetual checks from behind!", "Push e6, Black drops to a1, then Kd5.", listOf("e5e6", "a6a1", "e4d5")),
        RawLesson("Philidor Defense (e-file) (Var 10)", "8/8/r7/4P3/4K3/8/5P2/1k6 w - - 0 1", "Classic Philidor Defense: as soon as the pawn pushes to e6, drop the Rook back to a1 to deliver perpetual checks from behind!", "Push e6, Black drops to a1, then Kd5.", listOf("e5e6", "a6a1", "e4d5")),
        RawLesson("Philidor Defense (e-file) (Var 11)", "8/p7/r7/4P3/4K3/8/8/1k6 w - - 0 1", "Classic Philidor Defense: as soon as the pawn pushes to e6, drop the Rook back to a1 to deliver perpetual checks from behind!", "Push e6, Black drops to a1, then Kd5.", listOf("e5e6", "a6a1", "e4d5")),
        RawLesson("Philidor Defense (e-file) (Var 12)", "8/1p6/r7/4P3/4K3/8/8/1k6 w - - 0 1", "Classic Philidor Defense: as soon as the pawn pushes to e6, drop the Rook back to a1 to deliver perpetual checks from behind!", "Push e6, Black drops to a1, then Kd5.", listOf("e5e6", "a6a1", "e4d5")),
        RawLesson("Philidor Defense (e-file) (Var 13)", "8/2p5/r7/4P3/4K3/8/8/1k6 w - - 0 1", "Classic Philidor Defense: as soon as the pawn pushes to e6, drop the Rook back to a1 to deliver perpetual checks from behind!", "Push e6, Black drops to a1, then Kd5.", listOf("e5e6", "a6a1", "e4d5")),
        RawLesson("Philidor Defense (e-file) (Var 14)", "8/7p/r7/4P3/4K3/8/8/1k6 w - - 0 1", "Classic Philidor Defense: as soon as the pawn pushes to e6, drop the Rook back to a1 to deliver perpetual checks from behind!", "Push e6, Black drops to a1, then Kd5.", listOf("e5e6", "a6a1", "e4d5")),
        RawLesson("Philidor Defense (e-file) (Var 15)", "8/6p1/r7/4P3/4K3/8/8/1k6 w - - 0 1", "Classic Philidor Defense: as soon as the pawn pushes to e6, drop the Rook back to a1 to deliver perpetual checks from behind!", "Push e6, Black drops to a1, then Kd5.", listOf("e5e6", "a6a1", "e4d5")),
        RawLesson("Philidor Defense (e-file) (Var 16)", "8/5p2/r7/4P3/4K3/8/8/1k6 w - - 0 1", "Classic Philidor Defense: as soon as the pawn pushes to e6, drop the Rook back to a1 to deliver perpetual checks from behind!", "Push e6, Black drops to a1, then Kd5.", listOf("e5e6", "a6a1", "e4d5")),
        RawLesson("Philidor Defense (e-file) (Var 17)", "8/8/r7/4P3/4K3/7P/8/1k6 w - - 0 1", "Classic Philidor Defense: as soon as the pawn pushes to e6, drop the Rook back to a1 to deliver perpetual checks from behind!", "Push e6, Black drops to a1, then Kd5.", listOf("e5e6", "a6a1", "e4d5")),
        RawLesson("Philidor Defense (e-file) (Var 18)", "8/8/r6p/4P3/4K3/8/8/1k6 w - - 0 1", "Classic Philidor Defense: as soon as the pawn pushes to e6, drop the Rook back to a1 to deliver perpetual checks from behind!", "Push e6, Black drops to a1, then Kd5.", listOf("e5e6", "a6a1", "e4d5")),
        RawLesson("Philidor Defense (d-file) (Var 19)", "8/8/r7/3P4/3K4/8/1P6/1k6 w - - 0 1", "Third-rank defense on the d-file: pawn advances to d6, and the attacking king moves to c5.", "Push d6, then step to c5.", listOf("d5d6", "a6a1", "d4c5")),
        RawLesson("Philidor Defense (d-file) (Var 20)", "8/8/r7/3P4/3K4/8/2P5/1k6 w - - 0 1", "Third-rank defense on the d-file: pawn advances to d6, and the attacking king moves to c5.", "Push d6, then step to c5.", listOf("d5d6", "a6a1", "d4c5")),
        RawLesson("Philidor Defense (d-file) (Var 21)", "8/8/r7/3P4/3K4/8/7P/1k6 w - - 0 1", "Third-rank defense on the d-file: pawn advances to d6, and the attacking king moves to c5.", "Push d6, then step to c5.", listOf("d5d6", "a6a1", "d4c5")),
        RawLesson("Philidor Defense (d-file) (Var 22)", "8/8/r7/3P4/3K4/8/6P1/1k6 w - - 0 1", "Third-rank defense on the d-file: pawn advances to d6, and the attacking king moves to c5.", "Push d6, then step to c5.", listOf("d5d6", "a6a1", "d4c5")),
        RawLesson("Philidor Defense (d-file) (Var 23)", "8/8/r7/3P4/3K4/8/5P2/1k6 w - - 0 1", "Third-rank defense on the d-file: pawn advances to d6, and the attacking king moves to c5.", "Push d6, then step to c5.", listOf("d5d6", "a6a1", "d4c5")),
        RawLesson("Philidor Defense (d-file) (Var 24)", "8/p7/r7/3P4/3K4/8/8/1k6 w - - 0 1", "Third-rank defense on the d-file: pawn advances to d6, and the attacking king moves to c5.", "Push d6, then step to c5.", listOf("d5d6", "a6a1", "d4c5")),
        RawLesson("Philidor Defense (d-file) (Var 25)", "8/1p6/r7/3P4/3K4/8/8/1k6 w - - 0 1", "Third-rank defense on the d-file: pawn advances to d6, and the attacking king moves to c5.", "Push d6, then step to c5.", listOf("d5d6", "a6a1", "d4c5")),
        RawLesson("Philidor Defense (d-file) (Var 26)", "8/2p5/r7/3P4/3K4/8/8/1k6 w - - 0 1", "Third-rank defense on the d-file: pawn advances to d6, and the attacking king moves to c5.", "Push d6, then step to c5.", listOf("d5d6", "a6a1", "d4c5")),
        RawLesson("Philidor Defense (d-file) (Var 27)", "8/7p/r7/3P4/3K4/8/8/1k6 w - - 0 1", "Third-rank defense on the d-file: pawn advances to d6, and the attacking king moves to c5.", "Push d6, then step to c5.", listOf("d5d6", "a6a1", "d4c5")),
        RawLesson("Philidor Defense (d-file) (Var 28)", "8/6p1/r7/3P4/3K4/8/8/1k6 w - - 0 1", "Third-rank defense on the d-file: pawn advances to d6, and the attacking king moves to c5.", "Push d6, then step to c5.", listOf("d5d6", "a6a1", "d4c5")),
        RawLesson("Philidor Defense (d-file) (Var 29)", "8/5p2/r7/3P4/3K4/8/8/1k6 w - - 0 1", "Third-rank defense on the d-file: pawn advances to d6, and the attacking king moves to c5.", "Push d6, then step to c5.", listOf("d5d6", "a6a1", "d4c5")),
        RawLesson("Philidor Defense (d-file) (Var 30)", "8/8/r7/3P4/3K4/7P/8/1k6 w - - 0 1", "Third-rank defense on the d-file: pawn advances to d6, and the attacking king moves to c5.", "Push d6, then step to c5.", listOf("d5d6", "a6a1", "d4c5")),
        RawLesson("Philidor Defense (d-file) (Var 31)", "8/8/r6p/3P4/3K4/8/8/1k6 w - - 0 1", "Third-rank defense on the d-file: pawn advances to d6, and the attacking king moves to c5.", "Push d6, then step to c5.", listOf("d5d6", "a6a1", "d4c5")),
        RawLesson("Philidor Defense (c-file) (Var 32)", "8/8/r7/2P5/2K5/8/1P6/1k6 w - - 0 1", "Third-rank defense on the c-file: push c6, black rook retreats to a1, white king steps to b5.", "Advance c6, then Kb5.", listOf("c5c6", "a6a1", "c4b5")),
        RawLesson("Philidor Defense (c-file) (Var 33)", "8/8/r7/2P5/2K5/8/2P5/1k6 w - - 0 1", "Third-rank defense on the c-file: push c6, black rook retreats to a1, white king steps to b5.", "Advance c6, then Kb5.", listOf("c5c6", "a6a1", "c4b5")),
        RawLesson("Philidor Defense (c-file) (Var 34)", "8/8/r7/2P5/2K5/8/7P/1k6 w - - 0 1", "Third-rank defense on the c-file: push c6, black rook retreats to a1, white king steps to b5.", "Advance c6, then Kb5.", listOf("c5c6", "a6a1", "c4b5")),
        RawLesson("Philidor Defense (c-file) (Var 35)", "8/8/r7/2P5/2K5/8/6P1/1k6 w - - 0 1", "Third-rank defense on the c-file: push c6, black rook retreats to a1, white king steps to b5.", "Advance c6, then Kb5.", listOf("c5c6", "a6a1", "c4b5")),
        RawLesson("Philidor Defense (c-file) (Var 36)", "8/8/r7/2P5/2K5/8/5P2/1k6 w - - 0 1", "Third-rank defense on the c-file: push c6, black rook retreats to a1, white king steps to b5.", "Advance c6, then Kb5.", listOf("c5c6", "a6a1", "c4b5")),
        RawLesson("Philidor Defense (c-file) (Var 37)", "8/p7/r7/2P5/2K5/8/8/1k6 w - - 0 1", "Third-rank defense on the c-file: push c6, black rook retreats to a1, white king steps to b5.", "Advance c6, then Kb5.", listOf("c5c6", "a6a1", "c4b5")),
        RawLesson("Philidor Defense (c-file) (Var 38)", "8/1p6/r7/2P5/2K5/8/8/1k6 w - - 0 1", "Third-rank defense on the c-file: push c6, black rook retreats to a1, white king steps to b5.", "Advance c6, then Kb5.", listOf("c5c6", "a6a1", "c4b5")),
        RawLesson("Philidor Defense (c-file) (Var 39)", "8/2p5/r7/2P5/2K5/8/8/1k6 w - - 0 1", "Third-rank defense on the c-file: push c6, black rook retreats to a1, white king steps to b5.", "Advance c6, then Kb5.", listOf("c5c6", "a6a1", "c4b5")),
        RawLesson("Philidor Defense (c-file) (Var 40)", "8/7p/r7/2P5/2K5/8/8/1k6 w - - 0 1", "Third-rank defense on the c-file: push c6, black rook retreats to a1, white king steps to b5.", "Advance c6, then Kb5.", listOf("c5c6", "a6a1", "c4b5")),
        RawLesson("Philidor Defense (c-file) (Var 41)", "8/6p1/r7/2P5/2K5/8/8/1k6 w - - 0 1", "Third-rank defense on the c-file: push c6, black rook retreats to a1, white king steps to b5.", "Advance c6, then Kb5.", listOf("c5c6", "a6a1", "c4b5")),
        RawLesson("Philidor Defense (c-file) (Var 42)", "8/5p2/r7/2P5/2K5/8/8/1k6 w - - 0 1", "Third-rank defense on the c-file: push c6, black rook retreats to a1, white king steps to b5.", "Advance c6, then Kb5.", listOf("c5c6", "a6a1", "c4b5")),
        RawLesson("Philidor Defense (c-file) (Var 43)", "8/8/r7/2P5/2K5/7P/8/1k6 w - - 0 1", "Third-rank defense on the c-file: push c6, black rook retreats to a1, white king steps to b5.", "Advance c6, then Kb5.", listOf("c5c6", "a6a1", "c4b5")),
        RawLesson("Philidor Defense (c-file) (Var 44)", "8/8/r6p/2P5/2K5/8/8/1k6 w - - 0 1", "Third-rank defense on the c-file: push c6, black rook retreats to a1, white king steps to b5.", "Advance c6, then Kb5.", listOf("c5c6", "a6a1", "c4b5")),
        RawLesson("Philidor Defense (f-file) (Var 45)", "8/8/r7/5P2/5K2/8/1P6/1k6 w - - 0 1", "Third-rank defense on the f-file: push f6, Black seeks rear checks, White king advances to e5.", "Push f6, then Ke5.", listOf("f5f6", "a6a1", "f4e5")),
        RawLesson("Philidor Defense (f-file) (Var 46)", "8/8/r7/5P2/5K2/8/2P5/1k6 w - - 0 1", "Third-rank defense on the f-file: push f6, Black seeks rear checks, White king advances to e5.", "Push f6, then Ke5.", listOf("f5f6", "a6a1", "f4e5")),
        RawLesson("Philidor Defense (f-file) (Var 47)", "8/8/r7/5P2/5K2/8/7P/1k6 w - - 0 1", "Third-rank defense on the f-file: push f6, Black seeks rear checks, White king advances to e5.", "Push f6, then Ke5.", listOf("f5f6", "a6a1", "f4e5")),
        RawLesson("Philidor Defense (f-file) (Var 48)", "8/8/r7/5P2/5K2/8/6P1/1k6 w - - 0 1", "Third-rank defense on the f-file: push f6, Black seeks rear checks, White king advances to e5.", "Push f6, then Ke5.", listOf("f5f6", "a6a1", "f4e5")),
        RawLesson("Philidor Defense (f-file) (Var 49)", "8/8/r7/5P2/5K2/8/5P2/1k6 w - - 0 1", "Third-rank defense on the f-file: push f6, Black seeks rear checks, White king advances to e5.", "Push f6, then Ke5.", listOf("f5f6", "a6a1", "f4e5")),
        RawLesson("Philidor Defense (f-file) (Var 50)", "8/p7/r7/5P2/5K2/8/8/1k6 w - - 0 1", "Third-rank defense on the f-file: push f6, Black seeks rear checks, White king advances to e5.", "Push f6, then Ke5.", listOf("f5f6", "a6a1", "f4e5")),
        RawLesson("Philidor Defense (f-file) (Var 51)", "8/1p6/r7/5P2/5K2/8/8/1k6 w - - 0 1", "Third-rank defense on the f-file: push f6, Black seeks rear checks, White king advances to e5.", "Push f6, then Ke5.", listOf("f5f6", "a6a1", "f4e5")),
        RawLesson("Philidor Defense (f-file) (Var 52)", "8/2p5/r7/5P2/5K2/8/8/1k6 w - - 0 1", "Third-rank defense on the f-file: push f6, Black seeks rear checks, White king advances to e5.", "Push f6, then Ke5.", listOf("f5f6", "a6a1", "f4e5")),
        RawLesson("Philidor Defense (f-file) (Var 53)", "8/7p/r7/5P2/5K2/8/8/1k6 w - - 0 1", "Third-rank defense on the f-file: push f6, Black seeks rear checks, White king advances to e5.", "Push f6, then Ke5.", listOf("f5f6", "a6a1", "f4e5")),
        RawLesson("Philidor Defense (f-file) (Var 54)", "8/6p1/r7/5P2/5K2/8/8/1k6 w - - 0 1", "Third-rank defense on the f-file: push f6, Black seeks rear checks, White king advances to e5.", "Push f6, then Ke5.", listOf("f5f6", "a6a1", "f4e5")),
        RawLesson("Philidor Defense (f-file) (Var 55)", "8/5p2/r7/5P2/5K2/8/8/1k6 w - - 0 1", "Third-rank defense on the f-file: push f6, Black seeks rear checks, White king advances to e5.", "Push f6, then Ke5.", listOf("f5f6", "a6a1", "f4e5")),
        RawLesson("Philidor Defense (f-file) (Var 56)", "8/8/r7/5P2/5K2/7P/8/1k6 w - - 0 1", "Third-rank defense on the f-file: push f6, Black seeks rear checks, White king advances to e5.", "Push f6, then Ke5.", listOf("f5f6", "a6a1", "f4e5")),
        RawLesson("Philidor Defense (f-file) (Var 57)", "8/8/r6p/5P2/5K2/8/8/1k6 w - - 0 1", "Third-rank defense on the f-file: push f6, Black seeks rear checks, White king advances to e5.", "Push f6, then Ke5.", listOf("f5f6", "a6a1", "f4e5")),
        RawLesson("Philidor Defense (b-file) (Var 58)", "8/8/r7/1P6/1K6/8/1P6/1k6 w - - 0 1", "Third-rank defense on the b-file: push b6, Black rook retreats, White king moves to c5.", "Push b6, then Kc5.", listOf("b5b6", "a6a1", "b4c5")),
        RawLesson("Philidor Defense (b-file) (Var 59)", "8/8/r7/1P6/1K6/8/2P5/1k6 w - - 0 1", "Third-rank defense on the b-file: push b6, Black rook retreats, White king moves to c5.", "Push b6, then Kc5.", listOf("b5b6", "a6a1", "b4c5")),
        RawLesson("Philidor Defense (b-file) (Var 60)", "8/8/r7/1P6/1K6/8/7P/1k6 w - - 0 1", "Third-rank defense on the b-file: push b6, Black rook retreats, White king moves to c5.", "Push b6, then Kc5.", listOf("b5b6", "a6a1", "b4c5")),
        RawLesson("Philidor Defense (b-file) (Var 61)", "8/8/r7/1P6/1K6/8/6P1/1k6 w - - 0 1", "Third-rank defense on the b-file: push b6, Black rook retreats, White king moves to c5.", "Push b6, then Kc5.", listOf("b5b6", "a6a1", "b4c5")),
        RawLesson("Philidor Defense (b-file) (Var 62)", "8/8/r7/1P6/1K6/8/5P2/1k6 w - - 0 1", "Third-rank defense on the b-file: push b6, Black rook retreats, White king moves to c5.", "Push b6, then Kc5.", listOf("b5b6", "a6a1", "b4c5")),
        RawLesson("Philidor Defense (b-file) (Var 63)", "8/p7/r7/1P6/1K6/8/8/1k6 w - - 0 1", "Third-rank defense on the b-file: push b6, Black rook retreats, White king moves to c5.", "Push b6, then Kc5.", listOf("b5b6", "a6a1", "b4c5")),
        RawLesson("Philidor Defense (b-file) (Var 64)", "8/1p6/r7/1P6/1K6/8/8/1k6 w - - 0 1", "Third-rank defense on the b-file: push b6, Black rook retreats, White king moves to c5.", "Push b6, then Kc5.", listOf("b5b6", "a6a1", "b4c5")),
        RawLesson("Philidor Defense (b-file) (Var 65)", "8/2p5/r7/1P6/1K6/8/8/1k6 w - - 0 1", "Third-rank defense on the b-file: push b6, Black rook retreats, White king moves to c5.", "Push b6, then Kc5.", listOf("b5b6", "a6a1", "b4c5")),
        RawLesson("Philidor Defense (b-file) (Var 66)", "8/7p/r7/1P6/1K6/8/8/1k6 w - - 0 1", "Third-rank defense on the b-file: push b6, Black rook retreats, White king moves to c5.", "Push b6, then Kc5.", listOf("b5b6", "a6a1", "b4c5")),
        RawLesson("Philidor Defense (b-file) (Var 67)", "8/6p1/r7/1P6/1K6/8/8/1k6 w - - 0 1", "Third-rank defense on the b-file: push b6, Black rook retreats, White king moves to c5.", "Push b6, then Kc5.", listOf("b5b6", "a6a1", "b4c5")),
        RawLesson("Philidor Defense (b-file) (Var 68)", "8/5p2/r7/1P6/1K6/8/8/1k6 w - - 0 1", "Third-rank defense on the b-file: push b6, Black rook retreats, White king moves to c5.", "Push b6, then Kc5.", listOf("b5b6", "a6a1", "b4c5")),
        RawLesson("Philidor Defense (b-file) (Var 69)", "8/8/r7/1P6/1K6/7P/8/1k6 w - - 0 1", "Third-rank defense on the b-file: push b6, Black rook retreats, White king moves to c5.", "Push b6, then Kc5.", listOf("b5b6", "a6a1", "b4c5")),
        RawLesson("Philidor Defense (b-file) (Var 70)", "8/8/r6p/1P6/1K6/8/8/1k6 w - - 0 1", "Third-rank defense on the b-file: push b6, Black rook retreats, White king moves to c5.", "Push b6, then Kc5.", listOf("b5b6", "a6a1", "b4c5")),
        RawLesson("Philidor Defense (e-file)", "8/8/r7/4P3/4K3/8/8/1k6 w - - 0 1", "Classic Philidor Defense: as soon as the pawn pushes to e6, drop the Rook back to a1 to deliver perpetual checks from behind!", "Push e6, Black drops to a1, then Kd5.", listOf("e5e6", "a6a1", "e4d5")),
        RawLesson("Philidor Defense (d-file)", "8/8/r7/3P4/3K4/8/8/1k6 w - - 0 1", "Third-rank defense on the d-file: pawn advances to d6, and the attacking king moves to c5.", "Push d6, then step to c5.", listOf("d5d6", "a6a1", "d4c5")),
        RawLesson("Philidor Defense (c-file)", "8/8/r7/2P5/2K5/8/8/1k6 w - - 0 1", "Third-rank defense on the c-file: push c6, black rook retreats to a1, white king steps to b5.", "Advance c6, then Kb5.", listOf("c5c6", "a6a1", "c4b5")),
        RawLesson("Philidor Defense (f-file)", "8/8/r7/5P2/5K2/8/8/1k6 w - - 0 1", "Third-rank defense on the f-file: push f6, Black seeks rear checks, White king advances to e5.", "Push f6, then Ke5.", listOf("f5f6", "a6a1", "f4e5")),
        RawLesson("Philidor Defense (b-file)", "8/8/r7/1P6/1K6/8/8/1k6 w - - 0 1", "Third-rank defense on the b-file: push b6, Black rook retreats, White king moves to c5.", "Push b6, then Kc5.", listOf("b5b6", "a6a1", "b4c5")),
        RawLesson("Philidor Defense (e-file)", "8/8/r7/4P3/4K3/8/8/1k6 w - - 0 1", "Classic Philidor Defense: as soon as the pawn pushes to e6, drop the Rook back to a1 to deliver perpetual checks from behind!", "Push e6, Black drops to a1, then Kd5.", listOf("e5e6", "a6a1", "e4d5")),
        RawLesson("Philidor Defense (d-file)", "8/8/r7/3P4/3K4/8/8/1k6 w - - 0 1", "Third-rank defense on the d-file: pawn advances to d6, and the attacking king moves to c5.", "Push d6, then step to c5.", listOf("d5d6", "a6a1", "d4c5")),
        RawLesson("Philidor Defense (c-file)", "8/8/r7/2P5/2K5/8/8/1k6 w - - 0 1", "Third-rank defense on the c-file: push c6, black rook retreats to a1, white king steps to b5.", "Advance c6, then Kb5.", listOf("c5c6", "a6a1", "c4b5")),
        RawLesson("Philidor Defense (f-file)", "8/8/r7/5P2/5K2/8/8/1k6 w - - 0 1", "Third-rank defense on the f-file: push f6, Black seeks rear checks, White king advances to e5.", "Push f6, then Ke5.", listOf("f5f6", "a6a1", "f4e5")),
        RawLesson("Philidor Defense (b-file)", "8/8/r7/1P6/1K6/8/8/1k6 w - - 0 1", "Third-rank defense on the b-file: push b6, Black rook retreats, White king moves to c5.", "Push b6, then Kc5.", listOf("b5b6", "a6a1", "b4c5")),
        RawLesson("Philidor Defense (e-file)", "8/8/r7/4P3/4K3/8/8/1k6 w - - 0 1", "Classic Philidor Defense: as soon as the pawn pushes to e6, drop the Rook back to a1 to deliver perpetual checks from behind!", "Push e6, Black drops to a1, then Kd5.", listOf("e5e6", "a6a1", "e4d5")),
        RawLesson("Philidor Defense (d-file)", "8/8/r7/3P4/3K4/8/8/1k6 w - - 0 1", "Third-rank defense on the d-file: pawn advances to d6, and the attacking king moves to c5.", "Push d6, then step to c5.", listOf("d5d6", "a6a1", "d4c5")),
        RawLesson("Philidor Defense (c-file)", "8/8/r7/2P5/2K5/8/8/1k6 w - - 0 1", "Third-rank defense on the c-file: push c6, black rook retreats to a1, white king steps to b5.", "Advance c6, then Kb5.", listOf("c5c6", "a6a1", "c4b5")),
        RawLesson("Philidor Defense (f-file)", "8/8/r7/5P2/5K2/8/8/1k6 w - - 0 1", "Third-rank defense on the f-file: push f6, Black seeks rear checks, White king advances to e5.", "Push f6, then Ke5.", listOf("f5f6", "a6a1", "f4e5")),
        RawLesson("Philidor Defense (b-file)", "8/8/r7/1P6/1K6/8/8/1k6 w - - 0 1", "Third-rank defense on the b-file: push b6, Black rook retreats, White king moves to c5.", "Push b6, then Kc5.", listOf("b5b6", "a6a1", "b4c5")),
        RawLesson("Philidor Defense (e-file)", "8/8/r7/4P3/4K3/8/8/1k6 w - - 0 1", "Classic Philidor Defense: as soon as the pawn pushes to e6, drop the Rook back to a1 to deliver perpetual checks from behind!", "Push e6, Black drops to a1, then Kd5.", listOf("e5e6", "a6a1", "e4d5")),
        RawLesson("Philidor Defense (d-file)", "8/8/r7/3P4/3K4/8/8/1k6 w - - 0 1", "Third-rank defense on the d-file: pawn advances to d6, and the attacking king moves to c5.", "Push d6, then step to c5.", listOf("d5d6", "a6a1", "d4c5")),
        RawLesson("Philidor Defense (c-file)", "8/8/r7/2P5/2K5/8/8/1k6 w - - 0 1", "Third-rank defense on the c-file: push c6, black rook retreats to a1, white king steps to b5.", "Advance c6, then Kb5.", listOf("c5c6", "a6a1", "c4b5")),
        RawLesson("Philidor Defense (f-file)", "8/8/r7/5P2/5K2/8/8/1k6 w - - 0 1", "Third-rank defense on the f-file: push f6, Black seeks rear checks, White king advances to e5.", "Push f6, then Ke5.", listOf("f5f6", "a6a1", "f4e5")),
        RawLesson("Philidor Defense (b-file)", "8/8/r7/1P6/1K6/8/8/1k6 w - - 0 1", "Third-rank defense on the b-file: push b6, Black rook retreats, White king moves to c5.", "Push b6, then Kc5.", listOf("b5b6", "a6a1", "b4c5")),
        RawLesson("Philidor Defense (e-file)", "8/8/r7/4P3/4K3/8/8/1k6 w - - 0 1", "Classic Philidor Defense: as soon as the pawn pushes to e6, drop the Rook back to a1 to deliver perpetual checks from behind!", "Push e6, Black drops to a1, then Kd5.", listOf("e5e6", "a6a1", "e4d5")),
        RawLesson("Philidor Defense (d-file)", "8/8/r7/3P4/3K4/8/8/1k6 w - - 0 1", "Third-rank defense on the d-file: pawn advances to d6, and the attacking king moves to c5.", "Push d6, then step to c5.", listOf("d5d6", "a6a1", "d4c5")),
        RawLesson("Philidor Defense (c-file)", "8/8/r7/2P5/2K5/8/8/1k6 w - - 0 1", "Third-rank defense on the c-file: push c6, black rook retreats to a1, white king steps to b5.", "Advance c6, then Kb5.", listOf("c5c6", "a6a1", "c4b5")),
        RawLesson("Philidor Defense (f-file)", "8/8/r7/5P2/5K2/8/8/1k6 w - - 0 1", "Third-rank defense on the f-file: push f6, Black seeks rear checks, White king advances to e5.", "Push f6, then Ke5.", listOf("f5f6", "a6a1", "f4e5")),
        RawLesson("Philidor Defense (b-file)", "8/8/r7/1P6/1K6/8/8/1k6 w - - 0 1", "Third-rank defense on the b-file: push b6, Black rook retreats, White king moves to c5.", "Push b6, then Kc5.", listOf("b5b6", "a6a1", "b4c5")),
        RawLesson("Philidor Defense (e-file)", "8/8/r7/4P3/4K3/8/8/1k6 w - - 0 1", "Classic Philidor Defense: as soon as the pawn pushes to e6, drop the Rook back to a1 to deliver perpetual checks from behind!", "Push e6, Black drops to a1, then Kd5.", listOf("e5e6", "a6a1", "e4d5")),
        RawLesson("Philidor Defense (d-file)", "8/8/r7/3P4/3K4/8/8/1k6 w - - 0 1", "Third-rank defense on the d-file: pawn advances to d6, and the attacking king moves to c5.", "Push d6, then step to c5.", listOf("d5d6", "a6a1", "d4c5")),
        RawLesson("Philidor Defense (c-file)", "8/8/r7/2P5/2K5/8/8/1k6 w - - 0 1", "Third-rank defense on the c-file: push c6, black rook retreats to a1, white king steps to b5.", "Advance c6, then Kb5.", listOf("c5c6", "a6a1", "c4b5")),
        RawLesson("Philidor Defense (f-file)", "8/8/r7/5P2/5K2/8/8/1k6 w - - 0 1", "Third-rank defense on the f-file: push f6, Black seeks rear checks, White king advances to e5.", "Push f6, then Ke5.", listOf("f5f6", "a6a1", "f4e5")),
        RawLesson("Philidor Defense (b-file)", "8/8/r7/1P6/1K6/8/8/1k6 w - - 0 1", "Third-rank defense on the b-file: push b6, Black rook retreats, White king moves to c5.", "Push b6, then Kc5.", listOf("b5b6", "a6a1", "b4c5"))
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
                id = "e_philidor_$level",
                title = "Level $level ($tier difficulty) • ${base.subtitle}",
                category = "Philidor",
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
