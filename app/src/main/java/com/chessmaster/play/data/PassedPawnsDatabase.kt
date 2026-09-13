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
        RawLesson("Connected Passed Pawns (A & B)", "3k4/8/8/8/PP6/8/8/3K4 w - - 0 1", "Advance the connected pawns with d1e2.", "Push d1e2.", listOf("d1e2", "d8e8", "e2f3")),
        RawLesson("Connected Passed Pawns (A & B)", "4k3/8/8/8/PP6/8/8/3K4 w - - 0 1", "Advance the connected pawns with d1e2.", "Push d1e2.", listOf("d1e2", "e8f8", "e2f3")),
        RawLesson("Connected Passed Pawns (A & B)", "2k5/8/8/8/PP6/8/8/3K4 w - - 0 1", "Advance the connected pawns with d1e2.", "Push d1e2.", listOf("d1e2", "c8d8", "e2f3")),
        RawLesson("Connected Passed Pawns (A & B)", "5k2/8/8/8/PP6/8/8/3K4 w - - 0 1", "Advance the connected pawns with d1e2.", "Push d1e2.", listOf("d1e2", "f8g8", "e2f3")),
        RawLesson("Connected Passed Pawns (A & B)", "1k6/8/8/8/PP6/8/8/3K4 w - - 0 1", "Advance the connected pawns with d1e2.", "Push d1e2.", listOf("d1e2", "b8c8", "e2f3")),
        RawLesson("Connected Passed Pawns (A & B)", "6k1/8/8/8/PP6/8/8/3K4 w - - 0 1", "Advance the connected pawns with d1e2.", "Push d1e2.", listOf("d1e2", "g8h8", "e2f3")),
        RawLesson("Connected Passed Pawns (A & B)", "k7/8/8/8/PP6/8/8/3K4 w - - 0 1", "Advance the connected pawns with d1e2.", "Push d1e2.", listOf("d1e2", "a8b8", "e2f3")),
        RawLesson("Connected Passed Pawns (A & B)", "7k/8/8/8/PP6/8/8/3K4 w - - 0 1", "Advance the connected pawns with d1e2.", "Push d1e2.", listOf("d1e2", "h8g8", "e2f3")),
        RawLesson("Connected Passed Pawns (A & B)", "3k4/8/8/8/PP6/8/8/4K3 w - - 0 1", "Advance the connected pawns with e1f2.", "Push e1f2.", listOf("e1f2", "d8e8", "f2g3")),
        RawLesson("Connected Passed Pawns (A & B)", "4k3/8/8/8/PP6/8/8/4K3 w - - 0 1", "Advance the connected pawns with e1f2.", "Push e1f2.", listOf("e1f2", "e8f8", "f2g3")),
        RawLesson("Connected Passed Pawns (A & B)", "2k5/8/8/8/PP6/8/8/4K3 w - - 0 1", "Advance the connected pawns with e1f2.", "Push e1f2.", listOf("e1f2", "c8d8", "f2g3")),
        RawLesson("Connected Passed Pawns (A & B)", "5k2/8/8/8/PP6/8/8/4K3 w - - 0 1", "Advance the connected pawns with e1f2.", "Push e1f2.", listOf("e1f2", "f8g8", "f2g3")),
        RawLesson("Connected Passed Pawns (A & B)", "1k6/8/8/8/PP6/8/8/4K3 w - - 0 1", "Advance the connected pawns with e1f2.", "Push e1f2.", listOf("e1f2", "b8c8", "f2g3")),
        RawLesson("Connected Passed Pawns (A & B)", "6k1/8/8/8/PP6/8/8/4K3 w - - 0 1", "Advance the connected pawns with e1f2.", "Push e1f2.", listOf("e1f2", "g8h8", "f2g3")),
        RawLesson("Connected Passed Pawns (A & B)", "k7/8/8/8/PP6/8/8/4K3 w - - 0 1", "Advance the connected pawns with e1f2.", "Push e1f2.", listOf("e1f2", "a8b8", "f2g3")),
        RawLesson("Connected Passed Pawns (A & B)", "7k/8/8/8/PP6/8/8/4K3 w - - 0 1", "Advance the connected pawns with e1f2.", "Push e1f2.", listOf("e1f2", "h8g8", "f2g3")),
        RawLesson("Connected Passed Pawns (A & B)", "3k4/8/8/8/PP6/8/8/2K5 w - - 0 1", "Advance the connected pawns with c1d2.", "Push c1d2.", listOf("c1d2", "d8e8", "d2e3")),
        RawLesson("Connected Passed Pawns (A & B)", "4k3/8/8/8/PP6/8/8/2K5 w - - 0 1", "Advance the connected pawns with c1d2.", "Push c1d2.", listOf("c1d2", "e8f8", "d2e3")),
        RawLesson("Connected Passed Pawns (A & B)", "2k5/8/8/8/PP6/8/8/2K5 w - - 0 1", "Advance the connected pawns with c1d2.", "Push c1d2.", listOf("c1d2", "c8d8", "d2e3")),
        RawLesson("Connected Passed Pawns (A & B)", "5k2/8/8/8/PP6/8/8/2K5 w - - 0 1", "Advance the connected pawns with c1d2.", "Push c1d2.", listOf("c1d2", "f8g8", "d2e3")),
        RawLesson("Connected Passed Pawns (A & B)", "1k6/8/8/8/PP6/8/8/2K5 w - - 0 1", "Advance the connected pawns with c1d2.", "Push c1d2.", listOf("c1d2", "b8c8", "d2e3")),
        RawLesson("Connected Passed Pawns (A & B)", "6k1/8/8/8/PP6/8/8/2K5 w - - 0 1", "Advance the connected pawns with c1d2.", "Push c1d2.", listOf("c1d2", "g8h8", "d2e3")),
        RawLesson("Connected Passed Pawns (A & B)", "k7/8/8/8/PP6/8/8/2K5 w - - 0 1", "Advance the connected pawns with c1d2.", "Push c1d2.", listOf("c1d2", "a8b8", "d2e3")),
        RawLesson("Connected Passed Pawns (A & B)", "7k/8/8/8/PP6/8/8/2K5 w - - 0 1", "Advance the connected pawns with c1d2.", "Push c1d2.", listOf("c1d2", "h8g8", "d2e3")),
        RawLesson("Connected Passed Pawns (A & B)", "3k4/8/8/8/PP6/8/8/5K2 w - - 0 1", "Advance the connected pawns with f1g2.", "Push f1g2.", listOf("f1g2", "d8e8", "g2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "4k3/8/8/8/PP6/8/8/5K2 w - - 0 1", "Advance the connected pawns with f1g2.", "Push f1g2.", listOf("f1g2", "e8f8", "g2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "2k5/8/8/8/PP6/8/8/5K2 w - - 0 1", "Advance the connected pawns with f1g2.", "Push f1g2.", listOf("f1g2", "c8d8", "g2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "5k2/8/8/8/PP6/8/8/5K2 w - - 0 1", "Advance the connected pawns with f1g2.", "Push f1g2.", listOf("f1g2", "f8g8", "g2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "1k6/8/8/8/PP6/8/8/5K2 w - - 0 1", "Advance the connected pawns with f1g2.", "Push f1g2.", listOf("f1g2", "b8c8", "g2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "6k1/8/8/8/PP6/8/8/5K2 w - - 0 1", "Advance the connected pawns with f1g2.", "Push f1g2.", listOf("f1g2", "g8h8", "g2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "k7/8/8/8/PP6/8/8/5K2 w - - 0 1", "Advance the connected pawns with f1g2.", "Push f1g2.", listOf("f1g2", "a8b8", "g2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "7k/8/8/8/PP6/8/8/5K2 w - - 0 1", "Advance the connected pawns with f1g2.", "Push f1g2.", listOf("f1g2", "h8g8", "g2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "3k4/8/8/8/PP6/8/8/1K6 w - - 0 1", "Advance the connected pawns with b1c2.", "Push b1c2.", listOf("b1c2", "d8e8", "c2d3")),
        RawLesson("Connected Passed Pawns (A & B)", "4k3/8/8/8/PP6/8/8/1K6 w - - 0 1", "Advance the connected pawns with b1c2.", "Push b1c2.", listOf("b1c2", "e8f8", "c2d3")),
        RawLesson("Connected Passed Pawns (A & B)", "2k5/8/8/8/PP6/8/8/1K6 w - - 0 1", "Advance the connected pawns with b1c2.", "Push b1c2.", listOf("b1c2", "c8d8", "c2d3")),
        RawLesson("Connected Passed Pawns (A & B)", "5k2/8/8/8/PP6/8/8/1K6 w - - 0 1", "Advance the connected pawns with b1c2.", "Push b1c2.", listOf("b1c2", "f8g8", "c2d3")),
        RawLesson("Connected Passed Pawns (A & B)", "1k6/8/8/8/PP6/8/8/1K6 w - - 0 1", "Advance the connected pawns with b1c2.", "Push b1c2.", listOf("b1c2", "b8c8", "c2d3")),
        RawLesson("Connected Passed Pawns (A & B)", "6k1/8/8/8/PP6/8/8/1K6 w - - 0 1", "Advance the connected pawns with b1c2.", "Push b1c2.", listOf("b1c2", "g8h8", "c2d3")),
        RawLesson("Connected Passed Pawns (A & B)", "k7/8/8/8/PP6/8/8/1K6 w - - 0 1", "Advance the connected pawns with b1c2.", "Push b1c2.", listOf("b1c2", "a8b8", "c2d3")),
        RawLesson("Connected Passed Pawns (A & B)", "7k/8/8/8/PP6/8/8/1K6 w - - 0 1", "Advance the connected pawns with b1c2.", "Push b1c2.", listOf("b1c2", "h8g8", "c2d3")),
        RawLesson("Connected Passed Pawns (A & B)", "3k4/8/8/8/PP6/8/8/6K1 w - - 0 1", "Advance the connected pawns with g1h2.", "Push g1h2.", listOf("g1h2", "d8e8", "h2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "4k3/8/8/8/PP6/8/8/6K1 w - - 0 1", "Advance the connected pawns with g1h2.", "Push g1h2.", listOf("g1h2", "e8f8", "h2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "2k5/8/8/8/PP6/8/8/6K1 w - - 0 1", "Advance the connected pawns with g1h2.", "Push g1h2.", listOf("g1h2", "c8d8", "h2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "5k2/8/8/8/PP6/8/8/6K1 w - - 0 1", "Advance the connected pawns with g1h2.", "Push g1h2.", listOf("g1h2", "f8g8", "h2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "1k6/8/8/8/PP6/8/8/6K1 w - - 0 1", "Advance the connected pawns with g1h2.", "Push g1h2.", listOf("g1h2", "b8c8", "h2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "6k1/8/8/8/PP6/8/8/6K1 w - - 0 1", "Advance the connected pawns with g1h2.", "Push g1h2.", listOf("g1h2", "g8h8", "h2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "k7/8/8/8/PP6/8/8/6K1 w - - 0 1", "Advance the connected pawns with g1h2.", "Push g1h2.", listOf("g1h2", "a8b8", "h2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "7k/8/8/8/PP6/8/8/6K1 w - - 0 1", "Advance the connected pawns with g1h2.", "Push g1h2.", listOf("g1h2", "h8g8", "h2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "3k4/8/8/8/PP6/8/8/K7 w - - 0 1", "Advance the connected pawns with a1b2.", "Push a1b2.", listOf("a1b2", "d8e8", "b2c3")),
        RawLesson("Connected Passed Pawns (A & B)", "4k3/8/8/8/PP6/8/8/K7 w - - 0 1", "Advance the connected pawns with a1b2.", "Push a1b2.", listOf("a1b2", "e8f8", "b2c3")),
        RawLesson("Connected Passed Pawns (A & B)", "2k5/8/8/8/PP6/8/8/K7 w - - 0 1", "Advance the connected pawns with a1b2.", "Push a1b2.", listOf("a1b2", "c8d8", "b2c3")),
        RawLesson("Connected Passed Pawns (A & B)", "5k2/8/8/8/PP6/8/8/K7 w - - 0 1", "Advance the connected pawns with a1b2.", "Push a1b2.", listOf("a1b2", "f8g8", "b2c3")),
        RawLesson("Connected Passed Pawns (A & B)", "1k6/8/8/8/PP6/8/8/K7 w - - 0 1", "Advance the connected pawns with a1b2.", "Push a1b2.", listOf("a1b2", "b8c8", "b2c3")),
        RawLesson("Connected Passed Pawns (A & B)", "6k1/8/8/8/PP6/8/8/K7 w - - 0 1", "Advance the connected pawns with a1b2.", "Push a1b2.", listOf("a1b2", "g8h8", "b2c3")),
        RawLesson("Connected Passed Pawns (A & B)", "k7/8/8/8/PP6/8/8/K7 w - - 0 1", "Advance the connected pawns with a1b2.", "Push a1b2.", listOf("a1b2", "a8b8", "b2c3")),
        RawLesson("Connected Passed Pawns (A & B)", "7k/8/8/8/PP6/8/8/K7 w - - 0 1", "Advance the connected pawns with a1b2.", "Push a1b2.", listOf("a1b2", "h8g8", "b2c3")),
        RawLesson("Connected Passed Pawns (A & B)", "3k4/8/8/8/PP6/8/8/7K w - - 0 1", "Advance the connected pawns with h1h2.", "Push h1h2.", listOf("h1h2", "d8e8", "h2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "4k3/8/8/8/PP6/8/8/7K w - - 0 1", "Advance the connected pawns with h1h2.", "Push h1h2.", listOf("h1h2", "e8f8", "h2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "2k5/8/8/8/PP6/8/8/7K w - - 0 1", "Advance the connected pawns with h1h2.", "Push h1h2.", listOf("h1h2", "c8d8", "h2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "5k2/8/8/8/PP6/8/8/7K w - - 0 1", "Advance the connected pawns with h1h2.", "Push h1h2.", listOf("h1h2", "f8g8", "h2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "1k6/8/8/8/PP6/8/8/7K w - - 0 1", "Advance the connected pawns with h1h2.", "Push h1h2.", listOf("h1h2", "b8c8", "h2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "6k1/8/8/8/PP6/8/8/7K w - - 0 1", "Advance the connected pawns with h1h2.", "Push h1h2.", listOf("h1h2", "g8h8", "h2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "k7/8/8/8/PP6/8/8/7K w - - 0 1", "Advance the connected pawns with h1h2.", "Push h1h2.", listOf("h1h2", "a8b8", "h2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "7k/8/8/8/PP6/8/8/7K w - - 0 1", "Advance the connected pawns with h1h2.", "Push h1h2.", listOf("h1h2", "h8g8", "h2h3")),
        RawLesson("Connected Passed Pawns (A & B)", "3k4/8/8/PP6/8/8/8/3K4 w - - 0 1", "Advance the connected pawns with d1e2.", "Push d1e2.", listOf("d1e2", "d8e8", "e2e3")),
        RawLesson("Connected Passed Pawns (A & B)", "4k3/8/8/PP6/8/8/8/3K4 w - - 0 1", "Advance the connected pawns with d1e2.", "Push d1e2.", listOf("d1e2", "e8f8", "e2e3")),
        RawLesson("Connected Passed Pawns (A & B)", "2k5/8/8/PP6/8/8/8/3K4 w - - 0 1", "Advance the connected pawns with d1e2.", "Push d1e2.", listOf("d1e2", "c8d8", "e2e3")),
        RawLesson("Connected Passed Pawns (A & B)", "5k2/8/8/PP6/8/8/8/3K4 w - - 0 1", "Advance the connected pawns with d1e2.", "Push d1e2.", listOf("d1e2", "f8g8", "e2e3")),
        RawLesson("Connected Passed Pawns (A & B)", "1k6/8/8/PP6/8/8/8/3K4 w - - 0 1", "Advance the connected pawns with d1e2.", "Push d1e2.", listOf("d1e2", "b8c8", "e2e3")),
        RawLesson("Connected Passed Pawns (A & B)", "6k1/8/8/PP6/8/8/8/3K4 w - - 0 1", "Advance the connected pawns with d1e2.", "Push d1e2.", listOf("d1e2", "g8h8", "e2e3")),
        RawLesson("Connected Passed Pawns (A & B)", "k7/8/8/PP6/8/8/8/3K4 w - - 0 1", "Advance the connected pawns with d1e2.", "Push d1e2.", listOf("d1e2", "a8b8", "e2e3")),
        RawLesson("Connected Passed Pawns (A & B)", "7k/8/8/PP6/8/8/8/3K4 w - - 0 1", "Advance the connected pawns with d1e2.", "Push d1e2.", listOf("d1e2", "h8g8", "e2e3")),
        RawLesson("Connected Passed Pawns (A & B)", "3k4/8/8/PP6/8/8/8/4K3 w - - 0 1", "Advance the connected pawns with e1f2.", "Push e1f2.", listOf("e1f2", "d8e8", "f2f3")),
        RawLesson("Connected Passed Pawns (A & B)", "4k3/8/8/PP6/8/8/8/4K3 w - - 0 1", "Advance the connected pawns with e1f2.", "Push e1f2.", listOf("e1f2", "e8f8", "f2f3")),
        RawLesson("Connected Passed Pawns (A & B)", "2k5/8/8/PP6/8/8/8/4K3 w - - 0 1", "Advance the connected pawns with e1f2.", "Push e1f2.", listOf("e1f2", "c8d8", "f2f3")),
        RawLesson("Connected Passed Pawns (A & B)", "5k2/8/8/PP6/8/8/8/4K3 w - - 0 1", "Advance the connected pawns with e1f2.", "Push e1f2.", listOf("e1f2", "f8g8", "f2f3")),
        RawLesson("Connected Passed Pawns (A & B)", "1k6/8/8/PP6/8/8/8/4K3 w - - 0 1", "Advance the connected pawns with e1f2.", "Push e1f2.", listOf("e1f2", "b8c8", "f2f3")),
        RawLesson("Connected Passed Pawns (A & B)", "6k1/8/8/PP6/8/8/8/4K3 w - - 0 1", "Advance the connected pawns with e1f2.", "Push e1f2.", listOf("e1f2", "g8h8", "f2f3")),
        RawLesson("Connected Passed Pawns (A & B)", "k7/8/8/PP6/8/8/8/4K3 w - - 0 1", "Advance the connected pawns with e1f2.", "Push e1f2.", listOf("e1f2", "a8b8", "f2f3")),
        RawLesson("Connected Passed Pawns (A & B)", "7k/8/8/PP6/8/8/8/4K3 w - - 0 1", "Advance the connected pawns with e1f2.", "Push e1f2.", listOf("e1f2", "h8g8", "f2f3")),
        RawLesson("Connected Passed Pawns (A & B)", "3k4/8/8/PP6/8/8/8/2K5 w - - 0 1", "Advance the connected pawns with c1d2.", "Push c1d2.", listOf("c1d2", "d8e8", "d2d3")),
        RawLesson("Connected Passed Pawns (A & B)", "4k3/8/8/PP6/8/8/8/2K5 w - - 0 1", "Advance the connected pawns with c1d2.", "Push c1d2.", listOf("c1d2", "e8f8", "d2d3")),
        RawLesson("Connected Passed Pawns (A & B)", "2k5/8/8/PP6/8/8/8/2K5 w - - 0 1", "Advance the connected pawns with c1d2.", "Push c1d2.", listOf("c1d2", "c8d8", "d2d3")),
        RawLesson("Connected Passed Pawns (A & B)", "5k2/8/8/PP6/8/8/8/2K5 w - - 0 1", "Advance the connected pawns with c1d2.", "Push c1d2.", listOf("c1d2", "f8g8", "d2d3")),
        RawLesson("Connected Passed Pawns (A & B)", "1k6/8/8/PP6/8/8/8/2K5 w - - 0 1", "Advance the connected pawns with c1d2.", "Push c1d2.", listOf("c1d2", "b8c8", "d2d3")),
        RawLesson("Connected Passed Pawns (A & B)", "6k1/8/8/PP6/8/8/8/2K5 w - - 0 1", "Advance the connected pawns with c1d2.", "Push c1d2.", listOf("c1d2", "g8h8", "d2d3")),
        RawLesson("Connected Passed Pawns (A & B)", "k7/8/8/PP6/8/8/8/2K5 w - - 0 1", "Advance the connected pawns with c1d2.", "Push c1d2.", listOf("c1d2", "a8b8", "d2d3")),
        RawLesson("Connected Passed Pawns (A & B)", "7k/8/8/PP6/8/8/8/2K5 w - - 0 1", "Advance the connected pawns with c1d2.", "Push c1d2.", listOf("c1d2", "h8g8", "d2d3")),
        RawLesson("Connected Passed Pawns (A & B)", "3k4/8/8/PP6/8/8/8/5K2 w - - 0 1", "Advance the connected pawns with f1g2.", "Push f1g2.", listOf("f1g2", "d8e8", "g2g3")),
        RawLesson("Connected Passed Pawns (A & B)", "4k3/8/8/PP6/8/8/8/5K2 w - - 0 1", "Advance the connected pawns with f1g2.", "Push f1g2.", listOf("f1g2", "e8f8", "g2g3")),
        RawLesson("Connected Passed Pawns (A & B)", "2k5/8/8/PP6/8/8/8/5K2 w - - 0 1", "Advance the connected pawns with f1g2.", "Push f1g2.", listOf("f1g2", "c8d8", "g2g3")),
        RawLesson("Connected Passed Pawns (A & B)", "5k2/8/8/PP6/8/8/8/5K2 w - - 0 1", "Advance the connected pawns with f1g2.", "Push f1g2.", listOf("f1g2", "f8g8", "g2g3")),
        RawLesson("Connected Passed Pawns (A & B)", "1k6/8/8/PP6/8/8/8/5K2 w - - 0 1", "Advance the connected pawns with f1g2.", "Push f1g2.", listOf("f1g2", "b8c8", "g2g3")),
        RawLesson("Connected Passed Pawns (A & B)", "6k1/8/8/PP6/8/8/8/5K2 w - - 0 1", "Advance the connected pawns with f1g2.", "Push f1g2.", listOf("f1g2", "g8h8", "g2g3")),
        RawLesson("Connected Passed Pawns (A & B)", "k7/8/8/PP6/8/8/8/5K2 w - - 0 1", "Advance the connected pawns with f1g2.", "Push f1g2.", listOf("f1g2", "a8b8", "g2g3")),
        RawLesson("Connected Passed Pawns (A & B)", "7k/8/8/PP6/8/8/8/5K2 w - - 0 1", "Advance the connected pawns with f1g2.", "Push f1g2.", listOf("f1g2", "h8g8", "g2g3")),
        RawLesson("Connected Passed Pawns (A & B)", "3k4/8/8/PP6/8/8/8/1K6 w - - 0 1", "Advance the connected pawns with b1c2.", "Push b1c2.", listOf("b1c2", "d8e8", "c2c3")),
        RawLesson("Connected Passed Pawns (A & B)", "4k3/8/8/PP6/8/8/8/1K6 w - - 0 1", "Advance the connected pawns with b1c2.", "Push b1c2.", listOf("b1c2", "e8f8", "c2c3")),
        RawLesson("Connected Passed Pawns (A & B)", "2k5/8/8/PP6/8/8/8/1K6 w - - 0 1", "Advance the connected pawns with b1c2.", "Push b1c2.", listOf("b1c2", "c8d8", "c2c3")),
        RawLesson("Connected Passed Pawns (A & B)", "5k2/8/8/PP6/8/8/8/1K6 w - - 0 1", "Advance the connected pawns with b1c2.", "Push b1c2.", listOf("b1c2", "f8g8", "c2c3"))
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
