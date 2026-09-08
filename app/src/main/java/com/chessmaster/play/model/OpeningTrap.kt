package com.chessmaster.play.model

data class OpeningTrap(
    val id: String,
    val name: String,
    val description: String,
    val moveSequence: List<String>, // Sequence of moves leading to the trap, e.g., ["e2e4", "e7e5", "g1f3"]
    val commonMistakeIndex: Int, // The index in moveSequence where the opponent makes the trap-falling mistake
    val explanation: String,
    val initialFen: String = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
)
