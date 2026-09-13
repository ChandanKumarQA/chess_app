package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle
import com.chessmaster.play.model.EndgameLesson
import com.chessmaster.play.model.OpeningTrap
import kotlin.random.Random

object PuzzleRepository {

    var activePlayPuzzle: Puzzle? = null
    var currentPuzzleLevel: Int = 1
    var currentTacticalCategory: String? = null

    val levelPuzzles: List<Puzzle> get() = LevelPuzzlesDatabase.levelPuzzles

    fun getDailyPuzzle(): Puzzle = LevelPuzzlesDatabase.levelPuzzles.first()

    fun getPuzzleForLevel(level: Int): Puzzle = LevelPuzzlesDatabase.getPuzzleForLevel(level)

    // RETURNS 100 THEME-AUTHENTIC PUZZLES FOR EACH TACTICAL THEME
    fun getPuzzlesByCategory(category: String): List<Puzzle> {
        return TacticalDatabase.getPuzzles(category)
    }

    fun getNextPuzzleInCategory(currentPuzzle: Puzzle): Puzzle? {
        val cat = (currentTacticalCategory ?: currentPuzzle.theme.substringBefore(" -")).trim()
        val puzzles = getPuzzlesByCategory(cat)
        val index = puzzles.indexOfFirst { it.id == currentPuzzle.id }
        if (index >= 0 && index < puzzles.size - 1) {
            return puzzles[index + 1]
        }
        return null
    }

    fun getFirstPuzzleOfNextCategory(currentCategory: String): Puzzle? {
        val cat = currentCategory.substringBefore(" -").trim()
        val categories = getAllTacticalCategories()
        val index = categories.indexOf(cat)
        if (index >= 0 && index < categories.size - 1) {
            val nextCategory = categories[index + 1]
            return getPuzzlesByCategory(nextCategory).firstOrNull()
        }
        return null
    }

    fun getAllTacticalCategories(): List<String> {
        return TacticalDatabase.getAllCategories()
    }

    fun getAllEndgameCategories(): List<String> {
        return listOf(
            "King vs King",
            "King + Pawn",
            "Rook Endgame",
            "Queen Endgame",
            "Bishop Endgame",
            "Knight Endgame",
            "Passed Pawns",
            "Endgame Strategy"
        )
    }

    // RETURNS 100 ENDGAME LESSONS FOR EACH CATEGORY (1-35 Easy, 36-70 Moderate, 71-100 Hard)
    // USES AUTHENTIC LICHESS-STYLE PRACTICE POSITIONS
    fun getEndgameLessonsByCategory(category: String): List<EndgameLesson> {
        return EndgameDatabase.getLessons(category)
    }

    fun getFirstLessonOfNextEndgameCategory(currentCategory: String): EndgameLesson? {
        val categories = getAllEndgameCategories()
        val index = categories.indexOf(currentCategory)
        if (index >= 0 && index < categories.size - 1) {
            val nextCategory = categories[index + 1]
            return getEndgameLessonsByCategory(nextCategory).firstOrNull()
        }
        return null
    }

    fun getAllOpeningTraps(): List<OpeningTrap> {
        return OpeningTrapsDatabase.getAllTraps()
    }

    fun getRandomPuzzles(count: Int): List<Puzzle> {
        return PuzzleRushDatabase.getRandomPuzzles(count)
    }

    fun getAllSurvivalCategories(): List<String> {
        return SurvivalDatabase.getAllCategories()
    }

    // RETURNS 100 THEME-MATCHED PUZZLES SORTED ASCENDING BY RATING FOR SURVIVAL MODE
    fun getSurvivalPuzzlesByCategory(category: String): List<Puzzle> {
        return SurvivalDatabase.getPuzzles(category)
    }

    fun getSurvivalPuzzles(): List<Puzzle> {
        return getSurvivalPuzzlesByCategory("Classic Survival")
    }
}
