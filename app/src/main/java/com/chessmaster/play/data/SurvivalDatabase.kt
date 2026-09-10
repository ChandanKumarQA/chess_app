package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle
import kotlin.random.Random

object SurvivalDatabase {

    fun getAllCategories(): List<String> {
        return listOf(
            "Classic Survival",
            "Checkmate Survival",
            "Fork & Pin Survival",
            "Sacrifice & Attack",
            "Defensive Survival",
            "Grandmaster Survival"
        )
    }

    private val survivalCache = mutableMapOf<String, List<Puzzle>>()

    fun getPuzzles(category: String): List<Puzzle> = survivalCache.getOrPut(category) {
        val themeCategories = when (category) {
            "Checkmate Survival" -> listOf("Mate in 1", "Mate in 2", "Smothered Mate")
            "Fork & Pin Survival" -> listOf("Fork", "Pin", "Skewer", "Double Attack")
            "Sacrifice & Attack" -> listOf("Sacrifice", "Attraction", "Deflection", "Clearance", "Discovered Attack")
            "Defensive Survival" -> listOf("Winning Material", "Pin", "Fork")
            "Grandmaster Survival" -> TacticalDatabase.getAllCategories()
            else -> TacticalDatabase.getAllCategories()
        }

        val pool = mutableListOf<Puzzle>()
        for (theme in themeCategories) {
            pool.addAll(TacticalDatabase.getPuzzles(theme))
        }
        val extra = when (category) {
            "Checkmate Survival" -> PuzzleRepository.levelPuzzles.filter { it.theme.contains("Mate", ignoreCase = true) || it.theme.contains("Smothered", ignoreCase = true) }
            "Fork & Pin Survival" -> PuzzleRepository.levelPuzzles.filter { it.theme.contains("Fork", ignoreCase = true) || it.theme.contains("Pin", ignoreCase = true) }
            "Sacrifice & Attack" -> PuzzleRepository.levelPuzzles.filter { it.theme.contains("Sacrifice", ignoreCase = true) || it.theme.contains("Attraction", ignoreCase = true) }
            "Defensive Survival" -> PuzzleRepository.levelPuzzles.filter { it.theme.contains("Material", ignoreCase = true) || it.theme.contains("Defense", ignoreCase = true) }
            else -> PuzzleRepository.levelPuzzles
        }
        pool.addAll(extra)

        val distinctPool = pool.distinctBy { it.fen }
        val random = Random(System.currentTimeMillis())

        val easyPool = distinctPool.filter { it.rating <= 1050 }.shuffled(random)
        val moderatePool = distinctPool.filter { it.rating in 1051..1650 }.shuffled(random)
        val hardPool = distinctPool.filter { it.rating > 1650 }.shuffled(random)

        val catPrefix = category.lowercase().replace(" & ", "_").replace(" ", "_")
        val usedFens = mutableSetOf<String>()

        return (1..100).map { i ->
            val (tierRating, poolForTier) = when {
                i <= 35 -> (650 + (i * 10)) to easyPool
                i <= 70 -> (1100 + ((i - 35) * 15)) to moderatePool
                else -> (1700 + ((i - 70) * 20)) to hardPool
            }

            val candidate = poolForTier.firstOrNull { !usedFens.contains(it.fen) }
                ?: distinctPool.firstOrNull { !usedFens.contains(it.fen) }
                ?: distinctPool[(i - 1) % distinctPool.size]

            val fen = candidate.fen
            usedFens.add(fen)

            Puzzle(
                id = "survival_${catPrefix}_$i",
                fen = fen,
                solutionMoves = candidate.solutionMoves,
                rating = tierRating,
                theme = "$category - ${candidate.theme.substringAfter(" - ", candidate.theme)}",
                xpReward = 15 + (i / 10),
                coinsReward = 8 + (i / 20)
            )
        }.sortedBy { it.rating }
    }
}
