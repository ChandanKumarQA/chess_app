package com.chessmaster.play.data

import com.chessmaster.play.model.EndgameLesson

object BishopEndgameDatabase {

    private data class RawLesson(
        val subtitle: String,
        val fen: String,
        val explanation: String,
        val hint: String,
        val moves: List<String>
    )

    private val pool = listOf(
        RawLesson("Bishop Diagonals from c4", "4k3/8/8/8/2BP4/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "e8f8", "g8h7")),
        RawLesson("Bishop Diagonals from c4", "4k3/8/8/8/2B1P3/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "e8f8", "g8f7")),
        RawLesson("Bishop Diagonals from c4", "4k3/8/8/8/2B5/2P5/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "e8f8", "g8e6")),
        RawLesson("Bishop Diagonals from c4", "4k3/8/8/8/2B5/5P2/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "e8f8", "g8d5")),
        RawLesson("Bishop Diagonals from c4", "4k3/8/8/8/1PB5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "e8f8", "g8c4")),
        RawLesson("Bishop Diagonals from c4", "4k3/8/8/8/2B3P1/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "e8f8", "g8b3")),
        RawLesson("Bishop Diagonals from c4", "4k3/8/8/8/P1B5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "e8f8", "g8a2")),
        RawLesson("Bishop Diagonals from c4", "4k3/8/8/8/2B4P/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "e8f8", "e1f2")),
        RawLesson("Bishop Diagonals from c4", "4k3/8/8/3P4/2B5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4a6.", "Play c4a6.", listOf("c4a6", "e8f8", "a6c8")),
        RawLesson("Bishop Diagonals from c4", "4k3/8/8/4P3/2B5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "e8f8", "e1e2")),
        RawLesson("Bishop Diagonals from c4", "3k4/8/8/8/2BP4/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "d8e8", "g8h7")),
        RawLesson("Bishop Diagonals from c4", "3k4/8/8/8/2B1P3/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "d8e8", "g8f7")),
        RawLesson("Bishop Diagonals from c4", "3k4/8/8/8/2B5/2P5/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "d8e8", "g8e6")),
        RawLesson("Bishop Diagonals from c4", "3k4/8/8/8/2B5/5P2/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "d8e8", "g8d5")),
        RawLesson("Bishop Diagonals from c4", "3k4/8/8/8/1PB5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "d8e8", "g8c4")),
        RawLesson("Bishop Diagonals from c4", "3k4/8/8/8/2B3P1/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "d8e8", "g8b3")),
        RawLesson("Bishop Diagonals from c4", "3k4/8/8/8/P1B5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "d8e8", "g8a2")),
        RawLesson("Bishop Diagonals from c4", "3k4/8/8/8/2B4P/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "d8e8", "e1f2")),
        RawLesson("Bishop Diagonals from c4", "3k4/8/8/3P4/2B5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4a6.", "Play c4a6.", listOf("c4a6", "d8e8", "a6c8")),
        RawLesson("Bishop Diagonals from c4", "3k4/8/8/4P3/2B5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "d8e8", "e1e2")),
        RawLesson("Bishop Diagonals from c4", "5k2/8/8/8/2BP4/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "f8g8", "e1f2")),
        RawLesson("Bishop Diagonals from c4", "5k2/8/8/8/2B1P3/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "f8g8", "e1e2")),
        RawLesson("Bishop Diagonals from c4", "5k2/8/8/8/2B5/2P5/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "f8g8", "e1d2")),
        RawLesson("Bishop Diagonals from c4", "5k2/8/8/8/2B5/5P2/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "f8g8", "e1f1")),
        RawLesson("Bishop Diagonals from c4", "5k2/8/8/8/1PB5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "f8g8", "e1d1")),
        RawLesson("Bishop Diagonals from c4", "5k2/8/8/8/2B3P1/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "f8g8", "g4g5")),
        RawLesson("Bishop Diagonals from c4", "5k2/8/8/8/P1B5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "f8g8", "a4a5")),
        RawLesson("Bishop Diagonals from c4", "5k2/8/8/8/2B4P/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "f8g8", "h4h5")),
        RawLesson("Bishop Diagonals from c4", "5k2/8/8/3P4/2B5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4a6.", "Play c4a6.", listOf("c4a6", "f8g8", "a6c8")),
        RawLesson("Bishop Diagonals from c4", "5k2/8/8/4P3/2B5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "f8g8", "e5e6")),
        RawLesson("Bishop Diagonals from c4", "2k5/8/8/8/2BP4/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "c8d8", "g8h7")),
        RawLesson("Bishop Diagonals from c4", "2k5/8/8/8/2B1P3/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "c8d8", "g8f7")),
        RawLesson("Bishop Diagonals from c4", "2k5/8/8/8/2B5/2P5/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "c8d8", "g8e6")),
        RawLesson("Bishop Diagonals from c4", "2k5/8/8/8/2B5/5P2/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "c8d8", "g8d5")),
        RawLesson("Bishop Diagonals from c4", "2k5/8/8/8/1PB5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "c8d8", "g8c4")),
        RawLesson("Bishop Diagonals from c4", "2k5/8/8/8/2B3P1/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "c8d8", "g8b3")),
        RawLesson("Bishop Diagonals from c4", "2k5/8/8/8/P1B5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "c8d8", "g8a2")),
        RawLesson("Bishop Diagonals from c4", "2k5/8/8/8/2B4P/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "c8d8", "e1f2")),
        RawLesson("Bishop Diagonals from c4", "2k5/8/8/3P4/2B5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4a6.", "Play c4a6.", listOf("c4a6", "c8d8", "a6c8")),
        RawLesson("Bishop Diagonals from c4", "2k5/8/8/4P3/2B5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "c8d8", "e1e2")),
        RawLesson("Bishop Diagonals from c4", "6k1/8/8/3P4/2B5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4a6.", "Play c4a6.", listOf("c4a6", "g8h8", "a6c8")),
        RawLesson("Bishop Diagonals from c4", "1k6/8/8/8/2BP4/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "b8c8", "g8h7")),
        RawLesson("Bishop Diagonals from c4", "1k6/8/8/8/2B1P3/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "b8c8", "g8f7")),
        RawLesson("Bishop Diagonals from c4", "1k6/8/8/8/2B5/2P5/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "b8c8", "g8e6")),
        RawLesson("Bishop Diagonals from c4", "1k6/8/8/8/2B5/5P2/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "b8c8", "g8d5")),
        RawLesson("Bishop Diagonals from c4", "1k6/8/8/8/1PB5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "b8c8", "g8c4")),
        RawLesson("Bishop Diagonals from c4", "1k6/8/8/8/2B3P1/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "b8c8", "g8b3")),
        RawLesson("Bishop Diagonals from c4", "1k6/8/8/8/P1B5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "b8c8", "g8a2")),
        RawLesson("Bishop Diagonals from c4", "1k6/8/8/8/2B4P/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "b8c8", "e1f2")),
        RawLesson("Bishop Diagonals from c4", "1k6/8/8/3P4/2B5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4a6.", "Play c4a6.", listOf("c4a6", "b8a8", "a6c8")),
        RawLesson("Bishop Diagonals from c4", "1k6/8/8/4P3/2B5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "b8c8", "e1e2")),
        RawLesson("Bishop Diagonals from c4", "k7/8/8/8/2BP4/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "a8b8", "g8h7")),
        RawLesson("Bishop Diagonals from c4", "k7/8/8/8/2B1P3/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "a8b8", "g8f7")),
        RawLesson("Bishop Diagonals from c4", "k7/8/8/8/2B5/2P5/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "a8b8", "g8e6")),
        RawLesson("Bishop Diagonals from c4", "k7/8/8/8/2B5/5P2/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "a8b8", "g8d5")),
        RawLesson("Bishop Diagonals from c4", "k7/8/8/8/1PB5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "a8b8", "g8c4")),
        RawLesson("Bishop Diagonals from c4", "k7/8/8/8/2B3P1/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "a8b8", "g8b3")),
        RawLesson("Bishop Diagonals from c4", "k7/8/8/8/P1B5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "a8b8", "g8a2")),
        RawLesson("Bishop Diagonals from c4", "k7/8/8/8/2B4P/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "a8b8", "e1f2")),
        RawLesson("Bishop Diagonals from c4", "k7/8/8/3P4/2B5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4a6.", "Play c4a6.", listOf("c4a6", "a8b8", "a6c8")),
        RawLesson("Bishop Diagonals from c4", "k7/8/8/4P3/2B5/8/8/4K3 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "a8b8", "e1e2")),
        RawLesson("Bishop Diagonals from c4", "4k3/8/8/8/2BP4/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "e8f8", "d1e2")),
        RawLesson("Bishop Diagonals from c4", "4k3/8/8/8/2B1P3/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "e8f8", "d1d2")),
        RawLesson("Bishop Diagonals from c4", "4k3/8/8/8/2B5/2P5/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "e8f8", "d1c2")),
        RawLesson("Bishop Diagonals from c4", "4k3/8/8/8/2B5/5P2/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "e8f8", "d1e1")),
        RawLesson("Bishop Diagonals from c4", "4k3/8/8/8/1PB5/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "e8f8", "d1c1")),
        RawLesson("Bishop Diagonals from c4", "4k3/8/8/8/2B3P1/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "e8f8", "g4g5")),
        RawLesson("Bishop Diagonals from c4", "4k3/8/8/8/P1B5/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "e8f8", "a4a5")),
        RawLesson("Bishop Diagonals from c4", "4k3/8/8/8/2B4P/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "e8f8", "h4h5")),
        RawLesson("Bishop Diagonals from c4", "4k3/8/8/3P4/2B5/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4a6.", "Play c4a6.", listOf("c4a6", "e8f8", "a6b7")),
        RawLesson("Bishop Diagonals from c4", "4k3/8/8/4P3/2B5/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "e8f8", "e5e6")),
        RawLesson("Bishop Diagonals from c4", "3k4/8/8/8/2BP4/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "d8e8", "d1e2")),
        RawLesson("Bishop Diagonals from c4", "3k4/8/8/8/2B1P3/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "d8e8", "d1d2")),
        RawLesson("Bishop Diagonals from c4", "3k4/8/8/8/2B5/2P5/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "d8e8", "d1c2")),
        RawLesson("Bishop Diagonals from c4", "3k4/8/8/8/2B5/5P2/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "d8e8", "d1e1")),
        RawLesson("Bishop Diagonals from c4", "3k4/8/8/8/1PB5/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "d8e8", "d1c1")),
        RawLesson("Bishop Diagonals from c4", "3k4/8/8/8/2B3P1/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "d8e8", "g4g5")),
        RawLesson("Bishop Diagonals from c4", "3k4/8/8/8/P1B5/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "d8e8", "a4a5")),
        RawLesson("Bishop Diagonals from c4", "3k4/8/8/8/2B4P/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "d8e8", "h4h5")),
        RawLesson("Bishop Diagonals from c4", "3k4/8/8/3P4/2B5/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4a6.", "Play c4a6.", listOf("c4a6", "d8e8", "a6b7")),
        RawLesson("Bishop Diagonals from c4", "3k4/8/8/4P3/2B5/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "d8e8", "e5e6")),
        RawLesson("Bishop Diagonals from c4", "5k2/8/8/8/2BP4/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "f8g8", "d1e2")),
        RawLesson("Bishop Diagonals from c4", "5k2/8/8/8/2B1P3/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "f8g8", "d1d2")),
        RawLesson("Bishop Diagonals from c4", "5k2/8/8/8/2B5/2P5/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "f8g8", "d1c2")),
        RawLesson("Bishop Diagonals from c4", "5k2/8/8/8/2B5/5P2/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "f8g8", "d1e1")),
        RawLesson("Bishop Diagonals from c4", "5k2/8/8/8/1PB5/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "f8g8", "d1c1")),
        RawLesson("Bishop Diagonals from c4", "5k2/8/8/8/2B3P1/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "f8e8", "g8h7")),
        RawLesson("Bishop Diagonals from c4", "5k2/8/8/8/P1B5/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "f8e8", "g8f7")),
        RawLesson("Bishop Diagonals from c4", "5k2/8/8/8/2B4P/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "f8e8", "g8e6")),
        RawLesson("Bishop Diagonals from c4", "5k2/8/8/3P4/2B5/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4a6.", "Play c4a6.", listOf("c4a6", "f8g8", "a6b7")),
        RawLesson("Bishop Diagonals from c4", "5k2/8/8/4P3/2B5/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "f8e8", "g8d5")),
        RawLesson("Bishop Diagonals from c4", "2k5/8/8/8/2BP4/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "c8d8", "d1e2")),
        RawLesson("Bishop Diagonals from c4", "2k5/8/8/8/2B1P3/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "c8d8", "d1d2")),
        RawLesson("Bishop Diagonals from c4", "2k5/8/8/8/2B5/2P5/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "c8d8", "d1c2")),
        RawLesson("Bishop Diagonals from c4", "2k5/8/8/8/2B5/5P2/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "c8d8", "d1e1")),
        RawLesson("Bishop Diagonals from c4", "2k5/8/8/8/1PB5/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "c8d8", "d1c1")),
        RawLesson("Bishop Diagonals from c4", "2k5/8/8/8/2B3P1/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "c8d8", "g4g5")),
        RawLesson("Bishop Diagonals from c4", "2k5/8/8/8/P1B5/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "c8d8", "a4a5")),
        RawLesson("Bishop Diagonals from c4", "2k5/8/8/8/2B4P/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4g8.", "Play c4g8.", listOf("c4g8", "c8d8", "h4h5")),
        RawLesson("Bishop Diagonals from c4", "2k5/8/8/3P4/2B5/8/8/3K4 w - - 0 1", "Dominate key diagonals with c4a6.", "Play c4a6.", listOf("c4a6", "c8d8", "a6b7"))
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
                id = "e_bishop_endgame_$level",
                title = "Level $level ($tier difficulty) • ${base.subtitle}",
                category = "Bishop Endgame",
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
