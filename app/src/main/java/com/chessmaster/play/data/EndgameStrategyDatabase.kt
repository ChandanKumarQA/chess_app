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
        RawLesson("Endgame Strategy & Centralization", "8/3k4/8/8/2P5/8/3K4/8 w - - 0 1", "Centralize and build strategic pressure with d2e3.", "Play d2e3.", listOf("d2e3", "d7e8", "e3f4")),
        RawLesson("Endgame Strategy & Centralization", "8/4k3/8/8/2P5/8/3K4/8 w - - 0 1", "Centralize and build strategic pressure with d2e3.", "Play d2e3.", listOf("d2e3", "e7f8", "e3f4")),
        RawLesson("Endgame Strategy & Centralization", "8/2k5/8/8/2P5/8/3K4/8 w - - 0 1", "Centralize and build strategic pressure with d2e3.", "Play d2e3.", listOf("d2e3", "c7d8", "e3f4")),
        RawLesson("Endgame Strategy & Centralization", "8/5k2/8/8/2P5/8/3K4/8 w - - 0 1", "Centralize and build strategic pressure with d2e3.", "Play d2e3.", listOf("d2e3", "f7g8", "e3f4")),
        RawLesson("Endgame Strategy & Centralization", "8/8/3k4/8/2P5/8/3K4/8 w - - 0 1", "Centralize and build strategic pressure with d2e3.", "Play d2e3.", listOf("d2e3", "d6e7", "e3f4")),
        RawLesson("Endgame Strategy & Centralization", "8/8/4k3/8/2P5/8/3K4/8 w - - 0 1", "Centralize and build strategic pressure with d2e3.", "Play d2e3.", listOf("d2e3", "e6f7", "e3f4")),
        RawLesson("Endgame Strategy & Centralization", "2k5/8/8/8/2P5/8/3K4/8 w - - 0 1", "Centralize and build strategic pressure with d2e3.", "Play d2e3.", listOf("d2e3", "c8d8", "e3f4")),
        RawLesson("Endgame Strategy & Centralization", "5k2/8/8/8/2P5/8/3K4/8 w - - 0 1", "Centralize and build strategic pressure with d2e3.", "Play d2e3.", listOf("d2e3", "f8g8", "e3f4")),
        RawLesson("Endgame Strategy & Centralization", "8/3k4/8/8/2P5/8/4K3/8 w - - 0 1", "Centralize and build strategic pressure with e2f3.", "Play e2f3.", listOf("e2f3", "d7e8", "f3g4")),
        RawLesson("Endgame Strategy & Centralization", "8/4k3/8/8/2P5/8/4K3/8 w - - 0 1", "Centralize and build strategic pressure with e2f3.", "Play e2f3.", listOf("e2f3", "e7f8", "f3g4")),
        RawLesson("Endgame Strategy & Centralization", "8/2k5/8/8/2P5/8/4K3/8 w - - 0 1", "Centralize and build strategic pressure with e2f3.", "Play e2f3.", listOf("e2f3", "c7d8", "f3g4")),
        RawLesson("Endgame Strategy & Centralization", "8/5k2/8/8/2P5/8/4K3/8 w - - 0 1", "Centralize and build strategic pressure with e2f3.", "Play e2f3.", listOf("e2f3", "f7g8", "f3g4")),
        RawLesson("Endgame Strategy & Centralization", "8/8/3k4/8/2P5/8/4K3/8 w - - 0 1", "Centralize and build strategic pressure with e2f3.", "Play e2f3.", listOf("e2f3", "d6e7", "f3g4")),
        RawLesson("Endgame Strategy & Centralization", "8/8/4k3/8/2P5/8/4K3/8 w - - 0 1", "Centralize and build strategic pressure with e2f3.", "Play e2f3.", listOf("e2f3", "e6f7", "f3g4")),
        RawLesson("Endgame Strategy & Centralization", "2k5/8/8/8/2P5/8/4K3/8 w - - 0 1", "Centralize and build strategic pressure with e2f3.", "Play e2f3.", listOf("e2f3", "c8d8", "f3g4")),
        RawLesson("Endgame Strategy & Centralization", "5k2/8/8/8/2P5/8/4K3/8 w - - 0 1", "Centralize and build strategic pressure with e2f3.", "Play e2f3.", listOf("e2f3", "f8g8", "f3g4")),
        RawLesson("Endgame Strategy & Centralization", "8/3k4/8/8/2P5/8/2K5/8 w - - 0 1", "Centralize and build strategic pressure with c2d3.", "Play c2d3.", listOf("c2d3", "d7e8", "d3e4")),
        RawLesson("Endgame Strategy & Centralization", "8/4k3/8/8/2P5/8/2K5/8 w - - 0 1", "Centralize and build strategic pressure with c2d3.", "Play c2d3.", listOf("c2d3", "e7f8", "d3e4")),
        RawLesson("Endgame Strategy & Centralization", "8/2k5/8/8/2P5/8/2K5/8 w - - 0 1", "Centralize and build strategic pressure with c2d3.", "Play c2d3.", listOf("c2d3", "c7d8", "d3e4")),
        RawLesson("Endgame Strategy & Centralization", "8/5k2/8/8/2P5/8/2K5/8 w - - 0 1", "Centralize and build strategic pressure with c2d3.", "Play c2d3.", listOf("c2d3", "f7g8", "d3e4")),
        RawLesson("Endgame Strategy & Centralization", "8/8/3k4/8/2P5/8/2K5/8 w - - 0 1", "Centralize and build strategic pressure with c2d3.", "Play c2d3.", listOf("c2d3", "d6e7", "d3e4")),
        RawLesson("Endgame Strategy & Centralization", "8/8/4k3/8/2P5/8/2K5/8 w - - 0 1", "Centralize and build strategic pressure with c2d3.", "Play c2d3.", listOf("c2d3", "e6f7", "d3e4")),
        RawLesson("Endgame Strategy & Centralization", "2k5/8/8/8/2P5/8/2K5/8 w - - 0 1", "Centralize and build strategic pressure with c2d3.", "Play c2d3.", listOf("c2d3", "c8d8", "d3e4")),
        RawLesson("Endgame Strategy & Centralization", "5k2/8/8/8/2P5/8/2K5/8 w - - 0 1", "Centralize and build strategic pressure with c2d3.", "Play c2d3.", listOf("c2d3", "f8g8", "d3e4")),
        RawLesson("Endgame Strategy & Centralization", "8/3k4/8/8/2P5/8/5K2/8 w - - 0 1", "Centralize and build strategic pressure with f2g3.", "Play f2g3.", listOf("f2g3", "d7e8", "g3h4")),
        RawLesson("Endgame Strategy & Centralization", "8/4k3/8/8/2P5/8/5K2/8 w - - 0 1", "Centralize and build strategic pressure with f2g3.", "Play f2g3.", listOf("f2g3", "e7f8", "g3h4")),
        RawLesson("Endgame Strategy & Centralization", "8/2k5/8/8/2P5/8/5K2/8 w - - 0 1", "Centralize and build strategic pressure with f2g3.", "Play f2g3.", listOf("f2g3", "c7d8", "g3h4")),
        RawLesson("Endgame Strategy & Centralization", "8/5k2/8/8/2P5/8/5K2/8 w - - 0 1", "Centralize and build strategic pressure with f2g3.", "Play f2g3.", listOf("f2g3", "f7g8", "g3h4")),
        RawLesson("Endgame Strategy & Centralization", "8/8/3k4/8/2P5/8/5K2/8 w - - 0 1", "Centralize and build strategic pressure with f2g3.", "Play f2g3.", listOf("f2g3", "d6e7", "g3h4")),
        RawLesson("Endgame Strategy & Centralization", "8/8/4k3/8/2P5/8/5K2/8 w - - 0 1", "Centralize and build strategic pressure with f2g3.", "Play f2g3.", listOf("f2g3", "e6f7", "g3h4")),
        RawLesson("Endgame Strategy & Centralization", "2k5/8/8/8/2P5/8/5K2/8 w - - 0 1", "Centralize and build strategic pressure with f2g3.", "Play f2g3.", listOf("f2g3", "c8d8", "g3h4")),
        RawLesson("Endgame Strategy & Centralization", "5k2/8/8/8/2P5/8/5K2/8 w - - 0 1", "Centralize and build strategic pressure with f2g3.", "Play f2g3.", listOf("f2g3", "f8g8", "g3h4")),
        RawLesson("Endgame Strategy & Centralization", "8/3k4/8/8/2P5/3K4/8/8 w - - 0 1", "Centralize and build strategic pressure with d3e4.", "Play d3e4.", listOf("d3e4", "d7e8", "e4f5")),
        RawLesson("Endgame Strategy & Centralization", "8/4k3/8/8/2P5/3K4/8/8 w - - 0 1", "Centralize and build strategic pressure with d3e4.", "Play d3e4.", listOf("d3e4", "e7f8", "e4f5")),
        RawLesson("Endgame Strategy & Centralization", "8/2k5/8/8/2P5/3K4/8/8 w - - 0 1", "Centralize and build strategic pressure with d3e4.", "Play d3e4.", listOf("d3e4", "c7d8", "e4f5")),
        RawLesson("Endgame Strategy & Centralization", "8/5k2/8/8/2P5/3K4/8/8 w - - 0 1", "Centralize and build strategic pressure with d3e4.", "Play d3e4.", listOf("d3e4", "f7g8", "e4f5")),
        RawLesson("Endgame Strategy & Centralization", "8/8/3k4/8/2P5/3K4/8/8 w - - 0 1", "Centralize and build strategic pressure with d3e4.", "Play d3e4.", listOf("d3e4", "d6e7", "e4f5")),
        RawLesson("Endgame Strategy & Centralization", "8/8/4k3/8/2P5/3K4/8/8 w - - 0 1", "Centralize and build strategic pressure with d3e4.", "Play d3e4.", listOf("d3e4", "e6f7", "e4f5")),
        RawLesson("Endgame Strategy & Centralization", "2k5/8/8/8/2P5/3K4/8/8 w - - 0 1", "Centralize and build strategic pressure with d3e4.", "Play d3e4.", listOf("d3e4", "c8d8", "e4f5")),
        RawLesson("Endgame Strategy & Centralization", "5k2/8/8/8/2P5/3K4/8/8 w - - 0 1", "Centralize and build strategic pressure with d3e4.", "Play d3e4.", listOf("d3e4", "f8g8", "e4f5")),
        RawLesson("Endgame Strategy & Centralization", "8/3k4/8/8/2P5/4K3/8/8 w - - 0 1", "Centralize and build strategic pressure with e3f4.", "Play e3f4.", listOf("e3f4", "d7e8", "f4g5")),
        RawLesson("Endgame Strategy & Centralization", "8/4k3/8/8/2P5/4K3/8/8 w - - 0 1", "Centralize and build strategic pressure with e3f4.", "Play e3f4.", listOf("e3f4", "e7f8", "f4g5")),
        RawLesson("Endgame Strategy & Centralization", "8/2k5/8/8/2P5/4K3/8/8 w - - 0 1", "Centralize and build strategic pressure with e3f4.", "Play e3f4.", listOf("e3f4", "c7d8", "f4g5")),
        RawLesson("Endgame Strategy & Centralization", "8/5k2/8/8/2P5/4K3/8/8 w - - 0 1", "Centralize and build strategic pressure with e3f4.", "Play e3f4.", listOf("e3f4", "f7g8", "f4g5")),
        RawLesson("Endgame Strategy & Centralization", "8/8/3k4/8/2P5/4K3/8/8 w - - 0 1", "Centralize and build strategic pressure with e3f4.", "Play e3f4.", listOf("e3f4", "d6e7", "f4g5")),
        RawLesson("Endgame Strategy & Centralization", "8/8/4k3/8/2P5/4K3/8/8 w - - 0 1", "Centralize and build strategic pressure with e3f4.", "Play e3f4.", listOf("e3f4", "e6f7", "f4g5")),
        RawLesson("Endgame Strategy & Centralization", "2k5/8/8/8/2P5/4K3/8/8 w - - 0 1", "Centralize and build strategic pressure with e3f4.", "Play e3f4.", listOf("e3f4", "c8d8", "f4g5")),
        RawLesson("Endgame Strategy & Centralization", "5k2/8/8/8/2P5/4K3/8/8 w - - 0 1", "Centralize and build strategic pressure with e3f4.", "Play e3f4.", listOf("e3f4", "f8g8", "f4g5")),
        RawLesson("Endgame Strategy & Centralization", "8/3k4/8/8/2P5/8/8/2K5 w - - 0 1", "Centralize and build strategic pressure with c1d2.", "Play c1d2.", listOf("c1d2", "d7e8", "d2e3")),
        RawLesson("Endgame Strategy & Centralization", "8/4k3/8/8/2P5/8/8/2K5 w - - 0 1", "Centralize and build strategic pressure with c1d2.", "Play c1d2.", listOf("c1d2", "e7f8", "d2e3")),
        RawLesson("Endgame Strategy & Centralization", "8/2k5/8/8/2P5/8/8/2K5 w - - 0 1", "Centralize and build strategic pressure with c1d2.", "Play c1d2.", listOf("c1d2", "c7d8", "d2e3")),
        RawLesson("Endgame Strategy & Centralization", "8/5k2/8/8/2P5/8/8/2K5 w - - 0 1", "Centralize and build strategic pressure with c1d2.", "Play c1d2.", listOf("c1d2", "f7g8", "d2e3")),
        RawLesson("Endgame Strategy & Centralization", "8/8/3k4/8/2P5/8/8/2K5 w - - 0 1", "Centralize and build strategic pressure with c1d2.", "Play c1d2.", listOf("c1d2", "d6e7", "d2e3")),
        RawLesson("Endgame Strategy & Centralization", "8/8/4k3/8/2P5/8/8/2K5 w - - 0 1", "Centralize and build strategic pressure with c1d2.", "Play c1d2.", listOf("c1d2", "e6f7", "d2e3")),
        RawLesson("Endgame Strategy & Centralization", "2k5/8/8/8/2P5/8/8/2K5 w - - 0 1", "Centralize and build strategic pressure with c1d2.", "Play c1d2.", listOf("c1d2", "c8d8", "d2e3")),
        RawLesson("Endgame Strategy & Centralization", "5k2/8/8/8/2P5/8/8/2K5 w - - 0 1", "Centralize and build strategic pressure with c1d2.", "Play c1d2.", listOf("c1d2", "f8g8", "d2e3")),
        RawLesson("Endgame Strategy & Centralization", "8/3k4/8/8/2P5/8/8/5K2 w - - 0 1", "Centralize and build strategic pressure with f1g2.", "Play f1g2.", listOf("f1g2", "d7e8", "g2h3")),
        RawLesson("Endgame Strategy & Centralization", "8/4k3/8/8/2P5/8/8/5K2 w - - 0 1", "Centralize and build strategic pressure with f1g2.", "Play f1g2.", listOf("f1g2", "e7f8", "g2h3")),
        RawLesson("Endgame Strategy & Centralization", "8/2k5/8/8/2P5/8/8/5K2 w - - 0 1", "Centralize and build strategic pressure with f1g2.", "Play f1g2.", listOf("f1g2", "c7d8", "g2h3")),
        RawLesson("Endgame Strategy & Centralization", "8/5k2/8/8/2P5/8/8/5K2 w - - 0 1", "Centralize and build strategic pressure with f1g2.", "Play f1g2.", listOf("f1g2", "f7g8", "g2h3")),
        RawLesson("Endgame Strategy & Centralization", "8/8/3k4/8/2P5/8/8/5K2 w - - 0 1", "Centralize and build strategic pressure with f1g2.", "Play f1g2.", listOf("f1g2", "d6e7", "g2h3")),
        RawLesson("Endgame Strategy & Centralization", "8/8/4k3/8/2P5/8/8/5K2 w - - 0 1", "Centralize and build strategic pressure with f1g2.", "Play f1g2.", listOf("f1g2", "e6f7", "g2h3")),
        RawLesson("Endgame Strategy & Centralization", "2k5/8/8/8/2P5/8/8/5K2 w - - 0 1", "Centralize and build strategic pressure with f1g2.", "Play f1g2.", listOf("f1g2", "c8d8", "g2h3")),
        RawLesson("Endgame Strategy & Centralization", "5k2/8/8/8/2P5/8/8/5K2 w - - 0 1", "Centralize and build strategic pressure with f1g2.", "Play f1g2.", listOf("f1g2", "f8g8", "g2h3")),
        RawLesson("Endgame Strategy & Centralization", "8/3k4/8/8/3P4/8/3K4/8 w - - 0 1", "Centralize and build strategic pressure with d2e3.", "Play d2e3.", listOf("d2e3", "d7e8", "e3e4")),
        RawLesson("Endgame Strategy & Centralization", "8/4k3/8/8/3P4/8/3K4/8 w - - 0 1", "Centralize and build strategic pressure with d2e3.", "Play d2e3.", listOf("d2e3", "e7f8", "e3e4")),
        RawLesson("Endgame Strategy & Centralization", "8/2k5/8/8/3P4/8/3K4/8 w - - 0 1", "Centralize and build strategic pressure with d2e3.", "Play d2e3.", listOf("d2e3", "c7d8", "e3e4")),
        RawLesson("Endgame Strategy & Centralization", "8/5k2/8/8/3P4/8/3K4/8 w - - 0 1", "Centralize and build strategic pressure with d2e3.", "Play d2e3.", listOf("d2e3", "f7g8", "e3e4")),
        RawLesson("Endgame Strategy & Centralization", "8/8/3k4/8/3P4/8/3K4/8 w - - 0 1", "Centralize and build strategic pressure with d2e3.", "Play d2e3.", listOf("d2e3", "d6e7", "e3e4")),
        RawLesson("Endgame Strategy & Centralization", "8/8/4k3/8/3P4/8/3K4/8 w - - 0 1", "Centralize and build strategic pressure with d2e3.", "Play d2e3.", listOf("d2e3", "e6f7", "e3e4")),
        RawLesson("Endgame Strategy & Centralization", "2k5/8/8/8/3P4/8/3K4/8 w - - 0 1", "Centralize and build strategic pressure with d2e3.", "Play d2e3.", listOf("d2e3", "c8d8", "e3e4")),
        RawLesson("Endgame Strategy & Centralization", "5k2/8/8/8/3P4/8/3K4/8 w - - 0 1", "Centralize and build strategic pressure with d2e3.", "Play d2e3.", listOf("d2e3", "f8g8", "e3e4")),
        RawLesson("Endgame Strategy & Centralization", "8/3k4/8/8/3P4/8/4K3/8 w - - 0 1", "Centralize and build strategic pressure with e2f3.", "Play e2f3.", listOf("e2f3", "d7e8", "f3f4")),
        RawLesson("Endgame Strategy & Centralization", "8/4k3/8/8/3P4/8/4K3/8 w - - 0 1", "Centralize and build strategic pressure with e2f3.", "Play e2f3.", listOf("e2f3", "e7f8", "f3f4")),
        RawLesson("Endgame Strategy & Centralization", "8/2k5/8/8/3P4/8/4K3/8 w - - 0 1", "Centralize and build strategic pressure with e2f3.", "Play e2f3.", listOf("e2f3", "c7d8", "f3f4")),
        RawLesson("Endgame Strategy & Centralization", "8/5k2/8/8/3P4/8/4K3/8 w - - 0 1", "Centralize and build strategic pressure with e2f3.", "Play e2f3.", listOf("e2f3", "f7g8", "f3f4")),
        RawLesson("Endgame Strategy & Centralization", "8/8/3k4/8/3P4/8/4K3/8 w - - 0 1", "Centralize and build strategic pressure with e2f3.", "Play e2f3.", listOf("e2f3", "d6e7", "f3f4")),
        RawLesson("Endgame Strategy & Centralization", "8/8/4k3/8/3P4/8/4K3/8 w - - 0 1", "Centralize and build strategic pressure with e2f3.", "Play e2f3.", listOf("e2f3", "e6f7", "f3f4")),
        RawLesson("Endgame Strategy & Centralization", "2k5/8/8/8/3P4/8/4K3/8 w - - 0 1", "Centralize and build strategic pressure with e2f3.", "Play e2f3.", listOf("e2f3", "c8d8", "f3f4")),
        RawLesson("Endgame Strategy & Centralization", "5k2/8/8/8/3P4/8/4K3/8 w - - 0 1", "Centralize and build strategic pressure with e2f3.", "Play e2f3.", listOf("e2f3", "f8g8", "f3f4")),
        RawLesson("Endgame Strategy & Centralization", "8/3k4/8/8/3P4/8/2K5/8 w - - 0 1", "Centralize and build strategic pressure with c2d3.", "Play c2d3.", listOf("c2d3", "d7e8", "d3c4")),
        RawLesson("Endgame Strategy & Centralization", "8/4k3/8/8/3P4/8/2K5/8 w - - 0 1", "Centralize and build strategic pressure with c2d3.", "Play c2d3.", listOf("c2d3", "e7f8", "d3c4")),
        RawLesson("Endgame Strategy & Centralization", "8/2k5/8/8/3P4/8/2K5/8 w - - 0 1", "Centralize and build strategic pressure with c2d3.", "Play c2d3.", listOf("c2d3", "c7d8", "d3c4")),
        RawLesson("Endgame Strategy & Centralization", "8/5k2/8/8/3P4/8/2K5/8 w - - 0 1", "Centralize and build strategic pressure with c2d3.", "Play c2d3.", listOf("c2d3", "f7g8", "d3c4")),
        RawLesson("Endgame Strategy & Centralization", "8/8/3k4/8/3P4/8/2K5/8 w - - 0 1", "Centralize and build strategic pressure with c2d3.", "Play c2d3.", listOf("c2d3", "d6e7", "d3c4")),
        RawLesson("Endgame Strategy & Centralization", "8/8/4k3/8/3P4/8/2K5/8 w - - 0 1", "Centralize and build strategic pressure with c2d3.", "Play c2d3.", listOf("c2d3", "e6f7", "d3c4")),
        RawLesson("Endgame Strategy & Centralization", "2k5/8/8/8/3P4/8/2K5/8 w - - 0 1", "Centralize and build strategic pressure with c2d3.", "Play c2d3.", listOf("c2d3", "c8d8", "d3c4")),
        RawLesson("Endgame Strategy & Centralization", "5k2/8/8/8/3P4/8/2K5/8 w - - 0 1", "Centralize and build strategic pressure with c2d3.", "Play c2d3.", listOf("c2d3", "f8g8", "d3c4")),
        RawLesson("Endgame Strategy & Centralization", "8/3k4/8/8/3P4/8/5K2/8 w - - 0 1", "Centralize and build strategic pressure with f2g3.", "Play f2g3.", listOf("f2g3", "d7e8", "g3g4")),
        RawLesson("Endgame Strategy & Centralization", "8/4k3/8/8/3P4/8/5K2/8 w - - 0 1", "Centralize and build strategic pressure with f2g3.", "Play f2g3.", listOf("f2g3", "e7f8", "g3g4")),
        RawLesson("Endgame Strategy & Centralization", "8/2k5/8/8/3P4/8/5K2/8 w - - 0 1", "Centralize and build strategic pressure with f2g3.", "Play f2g3.", listOf("f2g3", "c7d8", "g3g4")),
        RawLesson("Endgame Strategy & Centralization", "8/5k2/8/8/3P4/8/5K2/8 w - - 0 1", "Centralize and build strategic pressure with f2g3.", "Play f2g3.", listOf("f2g3", "f7g8", "g3g4")),
        RawLesson("Endgame Strategy & Centralization", "8/8/3k4/8/3P4/8/5K2/8 w - - 0 1", "Centralize and build strategic pressure with f2g3.", "Play f2g3.", listOf("f2g3", "d6e7", "g3g4")),
        RawLesson("Endgame Strategy & Centralization", "8/8/4k3/8/3P4/8/5K2/8 w - - 0 1", "Centralize and build strategic pressure with f2g3.", "Play f2g3.", listOf("f2g3", "e6f7", "g3g4")),
        RawLesson("Endgame Strategy & Centralization", "2k5/8/8/8/3P4/8/5K2/8 w - - 0 1", "Centralize and build strategic pressure with f2g3.", "Play f2g3.", listOf("f2g3", "c8d8", "g3g4")),
        RawLesson("Endgame Strategy & Centralization", "5k2/8/8/8/3P4/8/5K2/8 w - - 0 1", "Centralize and build strategic pressure with f2g3.", "Play f2g3.", listOf("f2g3", "f8g8", "g3g4")),
        RawLesson("Endgame Strategy & Centralization", "8/3k4/8/8/3P4/3K4/8/8 w - - 0 1", "Centralize and build strategic pressure with d3e4.", "Play d3e4.", listOf("d3e4", "d7e8", "e4e5")),
        RawLesson("Endgame Strategy & Centralization", "8/4k3/8/8/3P4/3K4/8/8 w - - 0 1", "Centralize and build strategic pressure with d3e4.", "Play d3e4.", listOf("d3e4", "e7f8", "e4e5")),
        RawLesson("Endgame Strategy & Centralization", "8/2k5/8/8/3P4/3K4/8/8 w - - 0 1", "Centralize and build strategic pressure with d3e4.", "Play d3e4.", listOf("d3e4", "c7d8", "e4e5")),
        RawLesson("Endgame Strategy & Centralization", "8/5k2/8/8/3P4/3K4/8/8 w - - 0 1", "Centralize and build strategic pressure with d3e4.", "Play d3e4.", listOf("d3e4", "f7g8", "e4e5"))
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
