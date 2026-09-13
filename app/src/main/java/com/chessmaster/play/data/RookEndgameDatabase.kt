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
        RawLesson("Rook Activity on A-file", "6k1/r7/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "g8h8", "h2h3")),
        RawLesson("Rook Activity on A-file", "6k1/1r6/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "g8h8", "h2g3")),
        RawLesson("Rook Activity on A-file", "6k1/2r5/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "g8h8", "h2g2")),
        RawLesson("Rook Activity on A-file", "6k1/3r4/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "g8h8", "h2h1")),
        RawLesson("Rook Activity on A-file", "6k1/4r3/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "g8h8", "h2g1")),
        RawLesson("Rook Activity on A-file", "6k1/5r2/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "g8h8", "a1a8")),
        RawLesson("Rook Activity on A-file", "6k1/6r1/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "g8h8", "a1a7")),
        RawLesson("Rook Activity on A-file", "6k1/7r/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1g2 controlling key lines.", "Play g1g2.", listOf("g1g2", "g8h8", "g2g3")),
        RawLesson("Rook Activity on A-file", "5k2/r7/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "f8g8", "h2h3")),
        RawLesson("Rook Activity on A-file", "5k2/1r6/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "f8g8", "h2g3")),
        RawLesson("Rook Activity on A-file", "5k2/2r5/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "f8g8", "h2g2")),
        RawLesson("Rook Activity on A-file", "5k2/3r4/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "f8g8", "h2h1")),
        RawLesson("Rook Activity on A-file", "5k2/4r3/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "f8g8", "h2g1")),
        RawLesson("Rook Activity on A-file", "5k2/5r2/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "f8g8", "a1a8")),
        RawLesson("Rook Activity on A-file", "5k2/6r1/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "f8g8", "a1a7")),
        RawLesson("Rook Activity on A-file", "5k2/7r/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1g2 controlling key lines.", "Play g1g2.", listOf("g1g2", "f8g8", "g2g3")),
        RawLesson("Rook Activity on A-file", "4k3/r7/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "e8f8", "h2h3")),
        RawLesson("Rook Activity on A-file", "4k3/1r6/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "e8f8", "h2g3")),
        RawLesson("Rook Activity on A-file", "4k3/2r5/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "e8f8", "h2g2")),
        RawLesson("Rook Activity on A-file", "4k3/3r4/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "e8f8", "h2h1")),
        RawLesson("Rook Activity on A-file", "4k3/4r3/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "e8f8", "h2g1")),
        RawLesson("Rook Activity on A-file", "4k3/5r2/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "e8f8", "a1a8")),
        RawLesson("Rook Activity on A-file", "4k3/6r1/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "e8f8", "a1a7")),
        RawLesson("Rook Activity on A-file", "4k3/7r/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1g2 controlling key lines.", "Play g1g2.", listOf("g1g2", "e8f8", "g2g3")),
        RawLesson("Rook Activity on A-file", "3k4/r7/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "d8e8", "h2h3")),
        RawLesson("Rook Activity on A-file", "3k4/1r6/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "d8e8", "h2g3")),
        RawLesson("Rook Activity on A-file", "3k4/2r5/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "d8e8", "h2g2")),
        RawLesson("Rook Activity on A-file", "3k4/3r4/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "d8e8", "h2h1")),
        RawLesson("Rook Activity on A-file", "3k4/4r3/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "d8e8", "h2g1")),
        RawLesson("Rook Activity on A-file", "3k4/5r2/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "d8e8", "a1a8")),
        RawLesson("Rook Activity on A-file", "3k4/6r1/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "d8e8", "a1a7")),
        RawLesson("Rook Activity on A-file", "3k4/7r/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1g2 controlling key lines.", "Play g1g2.", listOf("g1g2", "d8e8", "g2g3")),
        RawLesson("Rook Activity on A-file", "2k5/r7/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "c8d8", "h2h3")),
        RawLesson("Rook Activity on A-file", "2k5/1r6/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "c8d8", "h2g3")),
        RawLesson("Rook Activity on A-file", "2k5/2r5/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "c8d8", "h2g2")),
        RawLesson("Rook Activity on A-file", "2k5/3r4/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "c8d8", "h2h1")),
        RawLesson("Rook Activity on A-file", "2k5/4r3/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "c8d8", "h2g1")),
        RawLesson("Rook Activity on A-file", "2k5/5r2/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "c8d8", "a1a8")),
        RawLesson("Rook Activity on A-file", "2k5/6r1/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "c8d8", "a1a7")),
        RawLesson("Rook Activity on A-file", "2k5/7r/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1g2 controlling key lines.", "Play g1g2.", listOf("g1g2", "c8d8", "g2g3")),
        RawLesson("Rook Activity on A-file", "1k6/r7/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "b8c8", "h2h3")),
        RawLesson("Rook Activity on A-file", "1k6/1r6/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "b8c8", "h2g3")),
        RawLesson("Rook Activity on A-file", "1k6/2r5/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "b8c8", "h2g2")),
        RawLesson("Rook Activity on A-file", "1k6/3r4/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "b8c8", "h2h1")),
        RawLesson("Rook Activity on A-file", "1k6/4r3/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "b8c8", "h2g1")),
        RawLesson("Rook Activity on A-file", "1k6/5r2/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "b8c8", "a1a8")),
        RawLesson("Rook Activity on A-file", "1k6/6r1/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "b8c8", "a1a7")),
        RawLesson("Rook Activity on A-file", "1k6/7r/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1g2 controlling key lines.", "Play g1g2.", listOf("g1g2", "b8c8", "g2g3")),
        RawLesson("Rook Activity on A-file", "k7/r7/8/8/8/8/8/R5K1 w - - 0 1", "Activate your rook with g1h2 controlling key lines.", "Play g1h2.", listOf("g1h2", "a8b8", "h2h3")),
        RawLesson("Rook Activity on A-file", "6k1/r7/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "g8h8", "g2h3")),
        RawLesson("Rook Activity on A-file", "6k1/1r6/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "g8h8", "g2g3")),
        RawLesson("Rook Activity on A-file", "6k1/2r5/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "g8h8", "g2f3")),
        RawLesson("Rook Activity on A-file", "6k1/3r4/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "g8h8", "g2h2")),
        RawLesson("Rook Activity on A-file", "6k1/4r3/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "g8h8", "g2f2")),
        RawLesson("Rook Activity on A-file", "6k1/5r2/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "g8h8", "g2h1")),
        RawLesson("Rook Activity on A-file", "6k1/6r1/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1f2 controlling key lines.", "Play f1f2.", listOf("f1f2", "g8h8", "f2f3")),
        RawLesson("Rook Activity on A-file", "6k1/7r/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "g8h8", "g2g1")),
        RawLesson("Rook Activity on A-file", "5k2/r7/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "f8g8", "g2h3")),
        RawLesson("Rook Activity on A-file", "5k2/1r6/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "f8g8", "g2g3")),
        RawLesson("Rook Activity on A-file", "5k2/2r5/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "f8g8", "g2f3")),
        RawLesson("Rook Activity on A-file", "5k2/3r4/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "f8g8", "g2h2")),
        RawLesson("Rook Activity on A-file", "5k2/4r3/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "f8g8", "g2f2")),
        RawLesson("Rook Activity on A-file", "5k2/5r2/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "f8g8", "g2h1")),
        RawLesson("Rook Activity on A-file", "5k2/6r1/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1f2 controlling key lines.", "Play f1f2.", listOf("f1f2", "f8g8", "f2f3")),
        RawLesson("Rook Activity on A-file", "5k2/7r/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "f8g8", "g2g1")),
        RawLesson("Rook Activity on A-file", "4k3/r7/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "e8f8", "g2h3")),
        RawLesson("Rook Activity on A-file", "4k3/1r6/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "e8f8", "g2g3")),
        RawLesson("Rook Activity on A-file", "4k3/2r5/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "e8f8", "g2f3")),
        RawLesson("Rook Activity on A-file", "4k3/3r4/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "e8f8", "g2h2")),
        RawLesson("Rook Activity on A-file", "4k3/4r3/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "e8f8", "g2f2")),
        RawLesson("Rook Activity on A-file", "4k3/5r2/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "e8f8", "g2h1")),
        RawLesson("Rook Activity on A-file", "4k3/6r1/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1f2 controlling key lines.", "Play f1f2.", listOf("f1f2", "e8f8", "f2f3")),
        RawLesson("Rook Activity on A-file", "4k3/7r/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "e8f8", "g2g1")),
        RawLesson("Rook Activity on A-file", "3k4/r7/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "d8e8", "g2h3")),
        RawLesson("Rook Activity on A-file", "3k4/1r6/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "d8e8", "g2g3")),
        RawLesson("Rook Activity on A-file", "3k4/2r5/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "d8e8", "g2f3")),
        RawLesson("Rook Activity on A-file", "3k4/3r4/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "d8e8", "g2h2")),
        RawLesson("Rook Activity on A-file", "3k4/4r3/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "d8e8", "g2f2")),
        RawLesson("Rook Activity on A-file", "3k4/5r2/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "d8e8", "g2h1")),
        RawLesson("Rook Activity on A-file", "3k4/6r1/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1f2 controlling key lines.", "Play f1f2.", listOf("f1f2", "d8e8", "f2f3")),
        RawLesson("Rook Activity on A-file", "3k4/7r/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "d8e8", "g2g1")),
        RawLesson("Rook Activity on A-file", "2k5/r7/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "c8d8", "g2h3")),
        RawLesson("Rook Activity on A-file", "2k5/1r6/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "c8d8", "g2g3")),
        RawLesson("Rook Activity on A-file", "2k5/2r5/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "c8d8", "g2f3")),
        RawLesson("Rook Activity on A-file", "2k5/3r4/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "c8d8", "g2h2")),
        RawLesson("Rook Activity on A-file", "2k5/4r3/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "c8d8", "g2f2")),
        RawLesson("Rook Activity on A-file", "2k5/5r2/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "c8d8", "g2h1")),
        RawLesson("Rook Activity on A-file", "2k5/6r1/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1f2 controlling key lines.", "Play f1f2.", listOf("f1f2", "c8d8", "f2f3")),
        RawLesson("Rook Activity on A-file", "2k5/7r/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "c8d8", "g2g1")),
        RawLesson("Rook Activity on A-file", "1k6/r7/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "b8c8", "g2h3")),
        RawLesson("Rook Activity on A-file", "1k6/1r6/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "b8c8", "g2g3")),
        RawLesson("Rook Activity on A-file", "1k6/2r5/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "b8c8", "g2f3")),
        RawLesson("Rook Activity on A-file", "1k6/3r4/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "b8c8", "g2h2")),
        RawLesson("Rook Activity on A-file", "1k6/4r3/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "b8c8", "g2f2")),
        RawLesson("Rook Activity on A-file", "1k6/5r2/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "b8c8", "g2h1")),
        RawLesson("Rook Activity on A-file", "1k6/6r1/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1f2 controlling key lines.", "Play f1f2.", listOf("f1f2", "b8c8", "f2f3")),
        RawLesson("Rook Activity on A-file", "1k6/7r/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "b8c8", "g2g1")),
        RawLesson("Rook Activity on A-file", "k7/r7/8/8/8/8/8/R4K2 w - - 0 1", "Activate your rook with f1g2 controlling key lines.", "Play f1g2.", listOf("f1g2", "a8b8", "g2h3")),
        RawLesson("Rook Activity on A-file", "6k1/r7/8/8/8/8/8/R3K3 w - - 0 1", "Activate your rook with e1f2 controlling key lines.", "Play e1f2.", listOf("e1f2", "g8h8", "f2g3")),
        RawLesson("Rook Activity on A-file", "6k1/1r6/8/8/8/8/8/R3K3 w - - 0 1", "Activate your rook with e1f2 controlling key lines.", "Play e1f2.", listOf("e1f2", "g8h8", "f2f3"))
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
