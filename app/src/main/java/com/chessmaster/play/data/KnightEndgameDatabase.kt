package com.chessmaster.play.data

import com.chessmaster.play.model.EndgameLesson

object KnightEndgameDatabase {

    private data class RawLesson(
        val subtitle: String,
        val fen: String,
        val explanation: String,
        val hint: String,
        val moves: List<String>
    )

    private val pool = listOf(
        RawLesson("Knight Outpost at c3", "4k3/8/8/8/8/2NP4/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "e8f8", "d5e7")),
        RawLesson("Knight Outpost at c3", "4k3/8/8/8/8/2N1P3/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "e8f8", "d5c7")),
        RawLesson("Knight Outpost at c3", "4k3/8/8/8/8/2N5/2P5/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "e8f8", "d5f6")),
        RawLesson("Knight Outpost at c3", "4k3/8/8/8/8/2N5/5P2/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "e8f8", "d5b6")),
        RawLesson("Knight Outpost at c3", "4k3/8/8/8/8/2N5/1P6/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "e8f8", "d5f4")),
        RawLesson("Knight Outpost at c3", "4k3/8/8/8/8/2N5/6P1/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "e8f8", "d5b4")),
        RawLesson("Knight Outpost at c3", "4k3/8/8/8/8/2N5/P7/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "e8f8", "d5e3")),
        RawLesson("Knight Outpost at c3", "4k3/8/8/8/8/2N5/7P/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "e8f8", "d5c3")),
        RawLesson("Knight Outpost at c3", "4k3/8/8/8/3P4/2N5/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "e8f8", "e1f2")),
        RawLesson("Knight Outpost at c3", "4k3/8/8/8/4P3/2N5/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "e8f8", "e1e2")),
        RawLesson("Knight Outpost at c3", "3k4/8/8/8/8/2NP4/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "d8e8", "d5e7")),
        RawLesson("Knight Outpost at c3", "3k4/8/8/8/8/2N1P3/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "d8e8", "d5c7")),
        RawLesson("Knight Outpost at c3", "3k4/8/8/8/8/2N5/2P5/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "d8e8", "d5f6")),
        RawLesson("Knight Outpost at c3", "3k4/8/8/8/8/2N5/5P2/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "d8e8", "d5b6")),
        RawLesson("Knight Outpost at c3", "3k4/8/8/8/8/2N5/1P6/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "d8e8", "d5f4")),
        RawLesson("Knight Outpost at c3", "3k4/8/8/8/8/2N5/6P1/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "d8e8", "d5b4")),
        RawLesson("Knight Outpost at c3", "3k4/8/8/8/8/2N5/P7/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "d8e8", "d5e3")),
        RawLesson("Knight Outpost at c3", "3k4/8/8/8/8/2N5/7P/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "d8e8", "d5c3")),
        RawLesson("Knight Outpost at c3", "3k4/8/8/8/3P4/2N5/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "d8e8", "e1f2")),
        RawLesson("Knight Outpost at c3", "3k4/8/8/8/4P3/2N5/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "d8e8", "e1e2")),
        RawLesson("Knight Outpost at c3", "5k2/8/8/8/8/2NP4/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "f8g8", "d5e7")),
        RawLesson("Knight Outpost at c3", "5k2/8/8/8/8/2N1P3/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "f8g8", "d5c7")),
        RawLesson("Knight Outpost at c3", "5k2/8/8/8/8/2N5/2P5/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "f8g8", "d5f6")),
        RawLesson("Knight Outpost at c3", "5k2/8/8/8/8/2N5/5P2/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "f8g8", "d5b6")),
        RawLesson("Knight Outpost at c3", "5k2/8/8/8/8/2N5/1P6/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "f8g8", "d5f4")),
        RawLesson("Knight Outpost at c3", "5k2/8/8/8/8/2N5/6P1/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "f8g8", "d5b4")),
        RawLesson("Knight Outpost at c3", "5k2/8/8/8/8/2N5/P7/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "f8g8", "d5e3")),
        RawLesson("Knight Outpost at c3", "5k2/8/8/8/8/2N5/7P/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "f8g8", "d5c3")),
        RawLesson("Knight Outpost at c3", "5k2/8/8/8/3P4/2N5/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "f8g8", "e1f2")),
        RawLesson("Knight Outpost at c3", "5k2/8/8/8/4P3/2N5/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "f8g8", "e1e2")),
        RawLesson("Knight Outpost at c3", "2k5/8/8/8/8/2NP4/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "c8d8", "d5e7")),
        RawLesson("Knight Outpost at c3", "2k5/8/8/8/8/2N1P3/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "c8d8", "d5c7")),
        RawLesson("Knight Outpost at c3", "2k5/8/8/8/8/2N5/2P5/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "c8d8", "d5f6")),
        RawLesson("Knight Outpost at c3", "2k5/8/8/8/8/2N5/5P2/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "c8d8", "d5b6")),
        RawLesson("Knight Outpost at c3", "2k5/8/8/8/8/2N5/1P6/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "c8d8", "d5f4")),
        RawLesson("Knight Outpost at c3", "2k5/8/8/8/8/2N5/6P1/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "c8d8", "d5b4")),
        RawLesson("Knight Outpost at c3", "2k5/8/8/8/8/2N5/P7/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "c8d8", "d5e3")),
        RawLesson("Knight Outpost at c3", "2k5/8/8/8/8/2N5/7P/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "c8d8", "d5c3")),
        RawLesson("Knight Outpost at c3", "2k5/8/8/8/3P4/2N5/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "c8d8", "e1f2")),
        RawLesson("Knight Outpost at c3", "2k5/8/8/8/4P3/2N5/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "c8d8", "e1e2")),
        RawLesson("Knight Outpost at c3", "6k1/8/8/8/8/2NP4/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "g8h8", "d5e7")),
        RawLesson("Knight Outpost at c3", "6k1/8/8/8/8/2N1P3/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "g8h8", "d5c7")),
        RawLesson("Knight Outpost at c3", "6k1/8/8/8/8/2N5/2P5/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "g8h8", "d5f6")),
        RawLesson("Knight Outpost at c3", "6k1/8/8/8/8/2N5/5P2/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "g8h8", "d5b6")),
        RawLesson("Knight Outpost at c3", "6k1/8/8/8/8/2N5/1P6/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "g8h8", "d5f4")),
        RawLesson("Knight Outpost at c3", "6k1/8/8/8/8/2N5/6P1/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "g8h8", "d5b4")),
        RawLesson("Knight Outpost at c3", "6k1/8/8/8/8/2N5/P7/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "g8h8", "d5e3")),
        RawLesson("Knight Outpost at c3", "6k1/8/8/8/8/2N5/7P/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "g8h8", "d5c3")),
        RawLesson("Knight Outpost at c3", "6k1/8/8/8/3P4/2N5/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "g8h8", "e1f2")),
        RawLesson("Knight Outpost at c3", "6k1/8/8/8/4P3/2N5/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "g8h8", "e1e2")),
        RawLesson("Knight Outpost at c3", "1k6/8/8/8/8/2NP4/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "b8c8", "d5e7")),
        RawLesson("Knight Outpost at c3", "1k6/8/8/8/8/2N1P3/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "b8c8", "d5c7")),
        RawLesson("Knight Outpost at c3", "1k6/8/8/8/8/2N5/2P5/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "b8c8", "d5f6")),
        RawLesson("Knight Outpost at c3", "1k6/8/8/8/8/2N5/5P2/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "b8c8", "d5b6")),
        RawLesson("Knight Outpost at c3", "1k6/8/8/8/8/2N5/1P6/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "b8c8", "d5f4")),
        RawLesson("Knight Outpost at c3", "1k6/8/8/8/8/2N5/6P1/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "b8c8", "d5b4")),
        RawLesson("Knight Outpost at c3", "1k6/8/8/8/8/2N5/P7/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "b8c8", "d5e3")),
        RawLesson("Knight Outpost at c3", "1k6/8/8/8/8/2N5/7P/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "b8c8", "d5c3")),
        RawLesson("Knight Outpost at c3", "1k6/8/8/8/3P4/2N5/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "b8c8", "e1f2")),
        RawLesson("Knight Outpost at c3", "1k6/8/8/8/4P3/2N5/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "b8c8", "e1e2")),
        RawLesson("Knight Outpost at c3", "k7/8/8/8/8/2NP4/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "a8b8", "d5e7")),
        RawLesson("Knight Outpost at c3", "k7/8/8/8/8/2N1P3/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "a8b8", "d5c7")),
        RawLesson("Knight Outpost at c3", "k7/8/8/8/8/2N5/2P5/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "a8b8", "d5f6")),
        RawLesson("Knight Outpost at c3", "k7/8/8/8/8/2N5/5P2/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "a8b8", "d5b6")),
        RawLesson("Knight Outpost at c3", "k7/8/8/8/8/2N5/1P6/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "a8b8", "d5f4")),
        RawLesson("Knight Outpost at c3", "k7/8/8/8/8/2N5/6P1/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "a8b8", "d5b4")),
        RawLesson("Knight Outpost at c3", "k7/8/8/8/8/2N5/P7/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "a8b8", "d5e3")),
        RawLesson("Knight Outpost at c3", "k7/8/8/8/8/2N5/7P/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "a8b8", "d5c3")),
        RawLesson("Knight Outpost at c3", "k7/8/8/8/3P4/2N5/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "a8b8", "e1f2")),
        RawLesson("Knight Outpost at c3", "k7/8/8/8/4P3/2N5/8/4K3 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "a8b8", "e1e2")),
        RawLesson("Knight Outpost at c3", "4k3/8/8/8/8/2NP4/8/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "e8f8", "d1e2")),
        RawLesson("Knight Outpost at c3", "4k3/8/8/8/8/2N1P3/8/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "e8f8", "d1d2")),
        RawLesson("Knight Outpost at c3", "4k3/8/8/8/8/2N5/2P5/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "e8f8", "d1e1")),
        RawLesson("Knight Outpost at c3", "4k3/8/8/8/8/2N5/5P2/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "e8f8", "d1c2")),
        RawLesson("Knight Outpost at c3", "4k3/8/8/8/8/2N5/1P6/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "e8f8", "d1c1")),
        RawLesson("Knight Outpost at c3", "4k3/8/8/8/8/2N5/6P1/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "e8f8", "g2g3")),
        RawLesson("Knight Outpost at c3", "4k3/8/8/8/8/2N5/P7/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "e8f8", "a2a3")),
        RawLesson("Knight Outpost at c3", "4k3/8/8/8/8/2N5/7P/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "e8f8", "h2h3")),
        RawLesson("Knight Outpost at c3", "4k3/8/8/8/3P4/2N5/8/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "e8d8", "d5e7")),
        RawLesson("Knight Outpost at c3", "4k3/8/8/8/4P3/2N5/8/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "e8f8", "e4e5")),
        RawLesson("Knight Outpost at c3", "3k4/8/8/8/8/2NP4/8/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "d8e8", "d1e2")),
        RawLesson("Knight Outpost at c3", "3k4/8/8/8/8/2N1P3/8/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "d8e8", "d1d2")),
        RawLesson("Knight Outpost at c3", "3k4/8/8/8/8/2N5/2P5/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "d8e8", "d1e1")),
        RawLesson("Knight Outpost at c3", "3k4/8/8/8/8/2N5/5P2/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "d8e8", "d1c2")),
        RawLesson("Knight Outpost at c3", "3k4/8/8/8/8/2N5/1P6/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "d8e8", "d1c1")),
        RawLesson("Knight Outpost at c3", "3k4/8/8/8/8/2N5/6P1/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "d8e8", "g2g3")),
        RawLesson("Knight Outpost at c3", "3k4/8/8/8/8/2N5/P7/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "d8e8", "a2a3")),
        RawLesson("Knight Outpost at c3", "3k4/8/8/8/8/2N5/7P/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "d8e8", "h2h3")),
        RawLesson("Knight Outpost at c3", "3k4/8/8/8/3P4/2N5/8/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "d8c8", "d5e7")),
        RawLesson("Knight Outpost at c3", "3k4/8/8/8/4P3/2N5/8/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "d8e8", "e4e5")),
        RawLesson("Knight Outpost at c3", "5k2/8/8/8/8/2NP4/8/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "f8g8", "d1e2")),
        RawLesson("Knight Outpost at c3", "5k2/8/8/8/8/2N1P3/8/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "f8g8", "d1d2")),
        RawLesson("Knight Outpost at c3", "5k2/8/8/8/8/2N5/2P5/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "f8g8", "d1e1")),
        RawLesson("Knight Outpost at c3", "5k2/8/8/8/8/2N5/5P2/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "f8g8", "d1c2")),
        RawLesson("Knight Outpost at c3", "5k2/8/8/8/8/2N5/1P6/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "f8g8", "d1c1")),
        RawLesson("Knight Outpost at c3", "5k2/8/8/8/8/2N5/6P1/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "f8g8", "g2g3")),
        RawLesson("Knight Outpost at c3", "5k2/8/8/8/8/2N5/P7/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "f8g8", "a2a3")),
        RawLesson("Knight Outpost at c3", "5k2/8/8/8/8/2N5/7P/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "f8g8", "h2h3")),
        RawLesson("Knight Outpost at c3", "5k2/8/8/8/3P4/2N5/8/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "f8e8", "d5e7")),
        RawLesson("Knight Outpost at c3", "5k2/8/8/8/4P3/2N5/8/3K4 w - - 0 1", "Maneuver the knight with c3d5 securing control.", "Play c3d5.", listOf("c3d5", "f8g8", "e4e5"))
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
                id = "e_knight_endgame_$level",
                title = "Level $level ($tier difficulty) • ${base.subtitle}",
                category = "Knight Endgame",
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
