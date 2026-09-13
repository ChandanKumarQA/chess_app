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
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/8/8/k7/8/K7 w - - 0 1", "Take opposition with a1b1 to control key squares against the enemy King.", "Move king with a1b1.", listOf("a1b1", "a3b4", "b1c2")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/8/8/1k6/8/K7 w - - 0 1", "Take opposition with a1b1 to control key squares against the enemy King.", "Move king with a1b1.", listOf("a1b1", "b3c4", "b1c2")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/8/8/2k5/8/K7 w - - 0 1", "Take opposition with a1a2 to control key squares against the enemy King.", "Move king with a1a2.", listOf("a1a2", "c3d4", "a2b3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/8/8/3k4/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "d3e4", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/8/8/4k3/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "e3f4", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/8/8/5k2/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "f3g4", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/8/8/6k1/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "g3h4", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/8/8/7k/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "h3h4", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/8/k7/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "a4b5", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/8/1k6/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "b4c5", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/8/2k5/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "c4d5", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/8/3k4/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "d4e5", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/8/4k3/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "e4f5", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/8/5k2/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "f4g5", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/8/6k1/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "g4h5", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/8/7k/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "h4h5", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/k7/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "a5b6", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/1k6/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "b5c6", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/2k5/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "c5d6", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/3k4/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "d5e6", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/4k3/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "e5f6", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/5k2/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "f5g6", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/6k1/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "g5h6", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/8/7k/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "h5h6", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/k7/8/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "a6b7", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/1k6/8/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "b6c7", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/2k5/8/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "c6d7", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/3k4/8/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "d6e7", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/4k3/8/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "e6f7", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/5k2/8/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "f6g7", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/6k1/8/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "g6h7", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/8/7k/8/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "h6h7", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/k7/8/8/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "a7b8", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/1k6/8/8/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "b7c8", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/2k5/8/8/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "c7d8", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/3k4/8/8/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "d7e8", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/4k3/8/8/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "e7f8", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/5k2/8/8/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "f7g8", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/6k1/8/8/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "g7h8", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 1)", "8/7k/8/8/8/8/8/K7 w - - 0 1", "Take opposition with a1b2 to control key squares against the enemy King.", "Move king with a1b2.", listOf("a1b2", "h7h8", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/8/8/k7/8/K7/8 w - - 0 1", "Take opposition with a2b2 to control key squares against the enemy King.", "Move king with a2b2.", listOf("a2b2", "a4b5", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/8/8/1k6/8/K7/8 w - - 0 1", "Take opposition with a2b2 to control key squares against the enemy King.", "Move king with a2b2.", listOf("a2b2", "b4c5", "b2c3")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/8/8/2k5/8/K7/8 w - - 0 1", "Take opposition with a2a3 to control key squares against the enemy King.", "Move king with a2a3.", listOf("a2a3", "c4d5", "a3b4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/8/8/3k4/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "d4e5", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/8/8/4k3/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "e4f5", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/8/8/5k2/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "f4g5", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/8/8/6k1/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "g4h5", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/8/8/7k/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "h4h5", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/8/k7/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "a5b6", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/8/1k6/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "b5c6", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/8/2k5/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "c5d6", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/8/3k4/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "d5e6", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/8/4k3/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "e5f6", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/8/5k2/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "f5g6", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/8/6k1/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "g5h6", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/8/7k/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "h5h6", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/k7/8/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "a6b7", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/1k6/8/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "b6c7", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/2k5/8/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "c6d7", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/3k4/8/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "d6e7", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/4k3/8/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "e6f7", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/5k2/8/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "f6g7", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/6k1/8/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "g6h7", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/8/7k/8/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "h6h7", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/k7/8/8/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "a7b8", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/1k6/8/8/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "b7c8", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/2k5/8/8/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "c7d8", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/3k4/8/8/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "d7e8", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/4k3/8/8/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "e7f8", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/5k2/8/8/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "f7g8", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/6k1/8/8/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "g7h8", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 2)", "8/7k/8/8/8/8/K7/8 w - - 0 1", "Take opposition with a2b3 to control key squares against the enemy King.", "Move king with a2b3.", listOf("a2b3", "h7h8", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/8/8/k7/8/K7/8/8 w - - 0 1", "Take opposition with a3b3 to control key squares against the enemy King.", "Move king with a3b3.", listOf("a3b3", "a5b6", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/8/8/1k6/8/K7/8/8 w - - 0 1", "Take opposition with a3b3 to control key squares against the enemy King.", "Move king with a3b3.", listOf("a3b3", "b5c6", "b3c4")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/8/8/2k5/8/K7/8/8 w - - 0 1", "Take opposition with a3a4 to control key squares against the enemy King.", "Move king with a3a4.", listOf("a3a4", "c5d6", "a4b5")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/8/8/3k4/8/K7/8/8 w - - 0 1", "Take opposition with a3b4 to control key squares against the enemy King.", "Move king with a3b4.", listOf("a3b4", "d5e6", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/8/8/4k3/8/K7/8/8 w - - 0 1", "Take opposition with a3b4 to control key squares against the enemy King.", "Move king with a3b4.", listOf("a3b4", "e5f6", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/8/8/5k2/8/K7/8/8 w - - 0 1", "Take opposition with a3b4 to control key squares against the enemy King.", "Move king with a3b4.", listOf("a3b4", "f5g6", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/8/8/6k1/8/K7/8/8 w - - 0 1", "Take opposition with a3b4 to control key squares against the enemy King.", "Move king with a3b4.", listOf("a3b4", "g5h6", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/8/8/7k/8/K7/8/8 w - - 0 1", "Take opposition with a3b4 to control key squares against the enemy King.", "Move king with a3b4.", listOf("a3b4", "h5h6", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/8/k7/8/8/K7/8/8 w - - 0 1", "Take opposition with a3b4 to control key squares against the enemy King.", "Move king with a3b4.", listOf("a3b4", "a6b7", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/8/1k6/8/8/K7/8/8 w - - 0 1", "Take opposition with a3b4 to control key squares against the enemy King.", "Move king with a3b4.", listOf("a3b4", "b6c7", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/8/2k5/8/8/K7/8/8 w - - 0 1", "Take opposition with a3b4 to control key squares against the enemy King.", "Move king with a3b4.", listOf("a3b4", "c6d7", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/8/3k4/8/8/K7/8/8 w - - 0 1", "Take opposition with a3b4 to control key squares against the enemy King.", "Move king with a3b4.", listOf("a3b4", "d6e7", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/8/4k3/8/8/K7/8/8 w - - 0 1", "Take opposition with a3b4 to control key squares against the enemy King.", "Move king with a3b4.", listOf("a3b4", "e6f7", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/8/5k2/8/8/K7/8/8 w - - 0 1", "Take opposition with a3b4 to control key squares against the enemy King.", "Move king with a3b4.", listOf("a3b4", "f6g7", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/8/6k1/8/8/K7/8/8 w - - 0 1", "Take opposition with a3b4 to control key squares against the enemy King.", "Move king with a3b4.", listOf("a3b4", "g6h7", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/8/7k/8/8/K7/8/8 w - - 0 1", "Take opposition with a3b4 to control key squares against the enemy King.", "Move king with a3b4.", listOf("a3b4", "h6h7", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/k7/8/8/8/K7/8/8 w - - 0 1", "Take opposition with a3b4 to control key squares against the enemy King.", "Move king with a3b4.", listOf("a3b4", "a7b8", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/1k6/8/8/8/K7/8/8 w - - 0 1", "Take opposition with a3b4 to control key squares against the enemy King.", "Move king with a3b4.", listOf("a3b4", "b7c8", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/2k5/8/8/8/K7/8/8 w - - 0 1", "Take opposition with a3b4 to control key squares against the enemy King.", "Move king with a3b4.", listOf("a3b4", "c7d8", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/3k4/8/8/8/K7/8/8 w - - 0 1", "Take opposition with a3b4 to control key squares against the enemy King.", "Move king with a3b4.", listOf("a3b4", "d7e8", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/4k3/8/8/8/K7/8/8 w - - 0 1", "Take opposition with a3b4 to control key squares against the enemy King.", "Move king with a3b4.", listOf("a3b4", "e7f8", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/5k2/8/8/8/K7/8/8 w - - 0 1", "Take opposition with a3b4 to control key squares against the enemy King.", "Move king with a3b4.", listOf("a3b4", "f7g8", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/6k1/8/8/8/K7/8/8 w - - 0 1", "Take opposition with a3b4 to control key squares against the enemy King.", "Move king with a3b4.", listOf("a3b4", "g7h8", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 3)", "8/7k/8/8/8/K7/8/8 w - - 0 1", "Take opposition with a3b4 to control key squares against the enemy King.", "Move king with a3b4.", listOf("a3b4", "h7h8", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 4)", "8/8/k7/8/K7/8/8/8 w - - 0 1", "Take opposition with a4b4 to control key squares against the enemy King.", "Move king with a4b4.", listOf("a4b4", "a6b7", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 4)", "8/8/1k6/8/K7/8/8/8 w - - 0 1", "Take opposition with a4b4 to control key squares against the enemy King.", "Move king with a4b4.", listOf("a4b4", "b6c7", "b4c5")),
        RawLesson("King Opposition on A-file (Rank 4)", "8/8/2k5/8/K7/8/8/8 w - - 0 1", "Take opposition with a4a5 to control key squares against the enemy King.", "Move king with a4a5.", listOf("a4a5", "c6d7", "a5b6")),
        RawLesson("King Opposition on A-file (Rank 4)", "8/8/3k4/8/K7/8/8/8 w - - 0 1", "Take opposition with a4b5 to control key squares against the enemy King.", "Move king with a4b5.", listOf("a4b5", "d6e7", "b5c6"))
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
