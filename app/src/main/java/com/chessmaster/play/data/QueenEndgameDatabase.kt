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
        RawLesson("Queen Centralization from d4", "4k3/p7/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "e8f7", "h8g8")),
        RawLesson("Queen Centralization from d4", "4k3/1p6/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "e8f7", "h8f8")),
        RawLesson("Queen Centralization from d4", "4k3/2p5/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "e8f7", "h8e8")),
        RawLesson("Queen Centralization from d4", "4k3/5p2/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "e8e7", "h8g8")),
        RawLesson("Queen Centralization from d4", "4k3/6p1/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4d8.", "Play d4d8.", listOf("d4d8", "e8d8", "a1b2")),
        RawLesson("Queen Centralization from d4", "4k3/7p/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "e8f7", "h8d8")),
        RawLesson("Queen Centralization from d4", "4k3/3p4/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "e8f7", "h8c8")),
        RawLesson("Queen Centralization from d4", "4k3/4p3/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "e8f7", "h8b8")),
        RawLesson("Queen Centralization from d4", "4k3/8/p7/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "e8f7", "h8a8")),
        RawLesson("Queen Centralization from d4", "4k3/8/7p/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "e8f7", "h8h7")),
        RawLesson("Queen Centralization from d4", "3k4/3p4/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "d8e7", "h8g8")),
        RawLesson("Queen Centralization from d4", "5k2/p7/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "f8f7", "h8g8")),
        RawLesson("Queen Centralization from d4", "5k2/1p6/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "f8f7", "h8f8")),
        RawLesson("Queen Centralization from d4", "5k2/2p5/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "f8f7", "h8e8")),
        RawLesson("Queen Centralization from d4", "5k2/5p2/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "f8e7", "h8g8")),
        RawLesson("Queen Centralization from d4", "5k2/6p1/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4d8.", "Play d4d8.", listOf("d4d8", "f8f7", "d8h8")),
        RawLesson("Queen Centralization from d4", "5k2/7p/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "f8f7", "h8d8")),
        RawLesson("Queen Centralization from d4", "5k2/3p4/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "f8f7", "h8c8")),
        RawLesson("Queen Centralization from d4", "5k2/4p3/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "f8f7", "h8b8")),
        RawLesson("Queen Centralization from d4", "5k2/8/p7/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "f8f7", "h8a8")),
        RawLesson("Queen Centralization from d4", "5k2/8/7p/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "f8f7", "h8h7")),
        RawLesson("Queen Centralization from d4", "6k1/p7/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "g8h8", "a1b2")),
        RawLesson("Queen Centralization from d4", "6k1/1p6/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "g8h8", "a1a2")),
        RawLesson("Queen Centralization from d4", "6k1/2p5/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "g8h8", "a1b1")),
        RawLesson("Queen Centralization from d4", "6k1/5p2/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4d8.", "Play d4d8.", listOf("d4d8", "g8h7", "d8h8")),
        RawLesson("Queen Centralization from d4", "6k1/6p1/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4d8.", "Play d4d8.", listOf("d4d8", "g8h7", "d8g8")),
        RawLesson("Queen Centralization from d4", "6k1/7p/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "g8f7", "h8g8")),
        RawLesson("Queen Centralization from d4", "6k1/3p4/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "g8f7", "h8f8")),
        RawLesson("Queen Centralization from d4", "6k1/4p3/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "g8f7", "h8e8")),
        RawLesson("Queen Centralization from d4", "6k1/8/p7/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "g8f7", "h8d8")),
        RawLesson("Queen Centralization from d4", "6k1/8/7p/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "g8f7", "h8c8")),
        RawLesson("Queen Centralization from d4", "2k5/p7/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "c8d7", "h8g8")),
        RawLesson("Queen Centralization from d4", "2k5/1p6/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "c8d7", "h8f8")),
        RawLesson("Queen Centralization from d4", "2k5/2p5/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "c8d7", "h8e8")),
        RawLesson("Queen Centralization from d4", "2k5/5p2/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "c8d7", "h8d8")),
        RawLesson("Queen Centralization from d4", "2k5/6p1/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4d8.", "Play d4d8.", listOf("d4d8", "c8d8", "a1b2")),
        RawLesson("Queen Centralization from d4", "2k5/7p/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "c8d7", "h8c8")),
        RawLesson("Queen Centralization from d4", "2k5/3p4/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "c8c7", "h8g8")),
        RawLesson("Queen Centralization from d4", "2k5/4p3/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "c8d7", "h8b8")),
        RawLesson("Queen Centralization from d4", "2k5/8/p7/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "c8d7", "h8a8")),
        RawLesson("Queen Centralization from d4", "2k5/8/7p/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "c8d7", "h8h7")),
        RawLesson("Queen Centralization from d4", "1k6/p7/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "b8c7", "h8g8")),
        RawLesson("Queen Centralization from d4", "1k6/1p6/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "b8c7", "h8f8")),
        RawLesson("Queen Centralization from d4", "1k6/2p5/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "b8b7", "h8g8")),
        RawLesson("Queen Centralization from d4", "1k6/5p2/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "b8c7", "h8e8")),
        RawLesson("Queen Centralization from d4", "1k6/6p1/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4d8.", "Play d4d8.", listOf("d4d8", "b8b7", "d8h8")),
        RawLesson("Queen Centralization from d4", "1k6/7p/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "b8c7", "h8d8")),
        RawLesson("Queen Centralization from d4", "1k6/3p4/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "b8c7", "h8c8")),
        RawLesson("Queen Centralization from d4", "1k6/4p3/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "b8c7", "h8b8")),
        RawLesson("Queen Centralization from d4", "1k6/8/p7/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "b8c7", "h8a8")),
        RawLesson("Queen Centralization from d4", "1k6/8/7p/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "b8c7", "h8h7")),
        RawLesson("Queen Centralization from d4", "k7/p7/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "a8b7", "h8g8")),
        RawLesson("Queen Centralization from d4", "k7/1p6/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "a8a7", "h8g8")),
        RawLesson("Queen Centralization from d4", "k7/2p5/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "a8b7", "h8f8")),
        RawLesson("Queen Centralization from d4", "k7/5p2/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "a8b7", "h8e8")),
        RawLesson("Queen Centralization from d4", "k7/6p1/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4d8.", "Play d4d8.", listOf("d4d8", "a8b7", "d8h8")),
        RawLesson("Queen Centralization from d4", "k7/7p/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "a8b7", "h8d8")),
        RawLesson("Queen Centralization from d4", "k7/3p4/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "a8b7", "h8c8")),
        RawLesson("Queen Centralization from d4", "k7/4p3/8/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "a8b7", "h8b8")),
        RawLesson("Queen Centralization from d4", "k7/8/p7/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "a8b7", "h8a8")),
        RawLesson("Queen Centralization from d4", "k7/8/7p/8/3Q4/8/8/K7 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "a8b7", "h8h7")),
        RawLesson("Queen Centralization from d4", "4k3/p7/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "e8f7", "h8g7")),
        RawLesson("Queen Centralization from d4", "4k3/1p6/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "e8f7", "h8h6")),
        RawLesson("Queen Centralization from d4", "4k3/2p5/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "e8f7", "h8f6")),
        RawLesson("Queen Centralization from d4", "4k3/5p2/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "e8e7", "h8f8")),
        RawLesson("Queen Centralization from d4", "4k3/6p1/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4d8.", "Play d4d8.", listOf("d4d8", "e8d8", "b1c2")),
        RawLesson("Queen Centralization from d4", "4k3/7p/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "e8f7", "h8e5")),
        RawLesson("Queen Centralization from d4", "4k3/3p4/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "e8f7", "h8h5")),
        RawLesson("Queen Centralization from d4", "4k3/4p3/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "e8f7", "h8h4")),
        RawLesson("Queen Centralization from d4", "4k3/8/p7/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "e8f7", "h8d4")),
        RawLesson("Queen Centralization from d4", "4k3/8/7p/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "e8f7", "h8c3")),
        RawLesson("Queen Centralization from d4", "3k4/3p4/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "d8e7", "h8f8")),
        RawLesson("Queen Centralization from d4", "5k2/p7/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "f8f7", "h8g7")),
        RawLesson("Queen Centralization from d4", "5k2/1p6/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "f8f7", "h8h6")),
        RawLesson("Queen Centralization from d4", "5k2/2p5/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "f8f7", "h8f6")),
        RawLesson("Queen Centralization from d4", "5k2/5p2/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "f8e7", "h8f8")),
        RawLesson("Queen Centralization from d4", "5k2/6p1/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4d8.", "Play d4d8.", listOf("d4d8", "f8f7", "d8g8")),
        RawLesson("Queen Centralization from d4", "5k2/7p/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "f8f7", "h8e5")),
        RawLesson("Queen Centralization from d4", "5k2/3p4/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "f8f7", "h8h5")),
        RawLesson("Queen Centralization from d4", "5k2/4p3/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "f8f7", "h8h4")),
        RawLesson("Queen Centralization from d4", "5k2/8/p7/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "f8f7", "h8d4")),
        RawLesson("Queen Centralization from d4", "5k2/8/7p/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "f8f7", "h8c3")),
        RawLesson("Queen Centralization from d4", "6k1/p7/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "g8h8", "b1c2")),
        RawLesson("Queen Centralization from d4", "6k1/1p6/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "g8h8", "b1b2")),
        RawLesson("Queen Centralization from d4", "6k1/2p5/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "g8h8", "b1a2")),
        RawLesson("Queen Centralization from d4", "6k1/5p2/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "g8h8", "b1c1")),
        RawLesson("Queen Centralization from d4", "6k1/6p1/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4d8.", "Play d4d8.", listOf("d4d8", "g8h7", "d8f8")),
        RawLesson("Queen Centralization from d4", "6k1/7p/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "g8h8", "b1a1")),
        RawLesson("Queen Centralization from d4", "6k1/3p4/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "g8f7", "h8b8")),
        RawLesson("Queen Centralization from d4", "6k1/4p3/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "g8f7", "h8a8")),
        RawLesson("Queen Centralization from d4", "6k1/8/p7/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "g8f7", "h8h7")),
        RawLesson("Queen Centralization from d4", "6k1/8/7p/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "g8f7", "h8g7")),
        RawLesson("Queen Centralization from d4", "2k5/p7/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "c8d7", "h8g7")),
        RawLesson("Queen Centralization from d4", "2k5/1p6/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "c8d7", "h8h6")),
        RawLesson("Queen Centralization from d4", "2k5/2p5/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "c8d7", "h8f6")),
        RawLesson("Queen Centralization from d4", "2k5/5p2/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "c8d7", "h8h5")),
        RawLesson("Queen Centralization from d4", "2k5/6p1/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4d8.", "Play d4d8.", listOf("d4d8", "c8d8", "b1c2")),
        RawLesson("Queen Centralization from d4", "2k5/7p/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "c8d7", "h8e5")),
        RawLesson("Queen Centralization from d4", "2k5/3p4/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "c8c7", "h8f8")),
        RawLesson("Queen Centralization from d4", "2k5/4p3/8/8/3Q4/8/8/1K6 w - - 0 1", "Centralize and pressure the enemy position with d4h8.", "Play d4h8.", listOf("d4h8", "c8d7", "h8h4"))
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
