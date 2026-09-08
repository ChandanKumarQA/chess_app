package com.chessmaster.play.model

data class Puzzle(
    val id: String,
    val fen: String,
    val solutionMoves: List<String>, // format: "e2e4", "g1f3"
    val rating: Int,
    val theme: String,
    val xpReward: Int,
    val coinsReward: Int
)
