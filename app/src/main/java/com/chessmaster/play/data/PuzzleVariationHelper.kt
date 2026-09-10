package com.chessmaster.play.data

object PuzzleVariationHelper {

    /**
     * Returns the base FEN without injecting synthetic spectator pieces.
     * All puzzles in databases are genuine, verified chess positions.
     */
    fun getUniqueVariations(baseFen: String, solutionMoves: List<String>, count: Int = 40): List<String> {
        return listOf(baseFen)
    }

    /**
     * Gets the variation for a base puzzle. Returns baseFen directly.
     */
    fun getVariation(baseFen: String, solutionMoves: List<String>, varIndex: Int): String {
        return baseFen
    }
}

