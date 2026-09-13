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
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/k7/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "a5b6", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/k7/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "a6b7", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/k7/8/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "a7b8", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "k7/8/8/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "a8b8", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/1k6/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "b5c6", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/1k6/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "b6c7", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/1k6/8/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "b7c8", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "1k6/8/8/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "b8c8", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/2k5/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "c5d6", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/2k5/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "c6d7", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/2k5/8/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "c7d8", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "2k5/8/8/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "c8d8", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/3k4/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "d5e6", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/3k4/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "d6e7", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/3k4/8/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "d7e8", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "3k4/8/8/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "d8e8", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/4k3/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "e5f6", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/4k3/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "e6f7", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/4k3/8/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "e7f8", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "4k3/8/8/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "e8f8", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/5k2/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "f5g6", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/5k2/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "f6g7", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/5k2/8/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "f7g8", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "5k2/8/8/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "f8g8", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/6k1/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "g5h6", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/6k1/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "g6h7", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/6k1/8/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "g7h8", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "6k1/8/8/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "g8h8", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/7k/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "h5h6", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/7k/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "h6h7", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/7k/8/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "h7h8", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "7k/8/8/8/8/8/P7/K7 w - - 0 1", "Escort the passed pawn with a1b2 securing promotion.", "Play a1b2.", listOf("a1b2", "h8g8", "b2c3")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/k7/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b3 securing promotion.", "Play a3b3.", listOf("a3b3", "a5b6", "b3c4")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/k7/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "a6b7", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/k7/8/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "a7b8", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "k7/8/8/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "a8b8", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/1k6/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b3 securing promotion.", "Play a3b3.", listOf("a3b3", "b5c6", "b3c4")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/1k6/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "b6c7", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/1k6/8/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "b7c8", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "1k6/8/8/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "b8c8", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/2k5/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3a4 securing promotion.", "Play a3a4.", listOf("a3a4", "c5d6", "a4b5")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/2k5/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "c6d7", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/2k5/8/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "c7d8", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "2k5/8/8/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "c8d8", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/3k4/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "d5e6", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/3k4/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "d6e7", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/3k4/8/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "d7e8", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "3k4/8/8/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "d8e8", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/4k3/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "e5f6", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/4k3/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "e6f7", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/4k3/8/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "e7f8", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "4k3/8/8/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "e8f8", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/5k2/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "f5g6", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/5k2/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "f6g7", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/5k2/8/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "f7g8", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "5k2/8/8/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "f8g8", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/6k1/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "g5h6", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/6k1/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "g6h7", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/6k1/8/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "g7h8", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "6k1/8/8/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "g8h8", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/7k/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "h5h6", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/7k/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "h6h7", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/7k/8/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "h7h8", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "7k/8/8/8/8/K7/P7/8 w - - 0 1", "Escort the passed pawn with a3b4 securing promotion.", "Play a3b4.", listOf("a3b4", "h8g8", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/k7/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b4 securing promotion.", "Play a4b4.", listOf("a4b4", "a6b7", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/k7/8/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "a7b8", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "k7/8/8/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "a8b8", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/1k6/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b4 securing promotion.", "Play a4b4.", listOf("a4b4", "b6c7", "b4c5")),
        RawLesson("Pawn Escort on A-file from a2", "8/1k6/8/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "b7c8", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "1k6/8/8/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "b8c8", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/2k5/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4a5 securing promotion.", "Play a4a5.", listOf("a4a5", "c5d6", "a5b6")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/2k5/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4a5 securing promotion.", "Play a4a5.", listOf("a4a5", "c6d7", "a5b6")),
        RawLesson("Pawn Escort on A-file from a2", "8/2k5/8/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "c7d8", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "2k5/8/8/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "c8d8", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/3k4/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "d5e6", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/3k4/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "d6e7", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "8/3k4/8/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "d7e8", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "3k4/8/8/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "d8e8", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/4k3/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "e5f6", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/4k3/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "e6f7", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "8/4k3/8/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "e7f8", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "4k3/8/8/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "e8f8", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/5k2/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "f5g6", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/5k2/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "f6g7", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "8/5k2/8/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "f7g8", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "5k2/8/8/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "f8g8", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/6k1/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "g5h6", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/6k1/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "g6h7", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "8/6k1/8/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "g7h8", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "6k1/8/8/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "g8h8", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/7k/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "h5h6", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/7k/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "h6h7", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "8/7k/8/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "h7h8", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "7k/8/8/8/K7/8/P7/8 w - - 0 1", "Escort the passed pawn with a4b5 securing promotion.", "Play a4b5.", listOf("a4b5", "h8g8", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "8/k7/8/K7/8/8/P7/8 w - - 0 1", "Escort the passed pawn with a5b5 securing promotion.", "Play a5b5.", listOf("a5b5", "a7b8", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "k7/8/8/K7/8/8/P7/8 w - - 0 1", "Escort the passed pawn with a5b6 securing promotion.", "Play a5b6.", listOf("a5b6", "a8b8", "b6c6")),
        RawLesson("Pawn Escort on A-file from a2", "8/1k6/8/K7/8/8/P7/8 w - - 0 1", "Escort the passed pawn with a5b5 securing promotion.", "Play a5b5.", listOf("a5b5", "b7c8", "b5c6")),
        RawLesson("Pawn Escort on A-file from a2", "1k6/8/8/K7/8/8/P7/8 w - - 0 1", "Escort the passed pawn with a5b6 securing promotion.", "Play a5b6.", listOf("a5b6", "b8c8", "b6a7")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/8/K1k5/8/8/P7/8 w - - 0 1", "Escort the passed pawn with a5a6 securing promotion.", "Play a5a6.", listOf("a5a6", "c5d6", "a6b7")),
        RawLesson("Pawn Escort on A-file from a2", "8/8/2k5/K7/8/8/P7/8 w - - 0 1", "Escort the passed pawn with a5a6 securing promotion.", "Play a5a6.", listOf("a5a6", "c6d7", "a6b7"))
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
