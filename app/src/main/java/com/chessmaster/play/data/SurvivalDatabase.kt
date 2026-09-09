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

    fun getPuzzles(category: String): List<Puzzle> {
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

        return (1..100).map { i ->
            val (tierRating, base) = when {
                i <= 35 -> {
                    val r = 650 + (i * 10)
                    val p = if (easyPool.isNotEmpty()) easyPool[(i - 1) % easyPool.size] else distinctPool[(i - 1) % distinctPool.size]
                    r to p
                }
                i <= 70 -> {
                    val r = 1100 + ((i - 35) * 15)
                    val p = if (moderatePool.isNotEmpty()) moderatePool[(i - 36) % moderatePool.size] else distinctPool[(i - 1) % distinctPool.size]
                    r to p
                }
                else -> {
                    val r = 1700 + ((i - 70) * 20)
                    val p = if (hardPool.isNotEmpty()) hardPool[(i - 71) % hardPool.size] else distinctPool[(i - 1) % distinctPool.size]
                    r to p
                }
            }

            Puzzle(
                id = "survival_${catPrefix}_$i",
                fen = base.fen,
                solutionMoves = base.solutionMoves,
                rating = tierRating,
                theme = "$category - ${base.theme.substringAfter(" - ", base.theme)}",
                xpReward = 15 + (i / 10),
                coinsReward = 8 + (i / 20)
            )
        }.sortedBy { it.rating }
    }
}
