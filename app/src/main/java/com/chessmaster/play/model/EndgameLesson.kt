package com.chessmaster.play.model

data class EndgameLesson(
    val id: String,
    val title: String,
    val category: String,
    val fen: String,
    val explanation: String,
    val hint: String,
    val solutionMoves: List<String>
)
