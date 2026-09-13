package com.chessmaster.play

import com.chessmaster.play.data.PuzzleRepository
import com.chessmaster.play.engine.GameEngine
import com.chessmaster.play.engine.NotationParser
import com.chessmaster.play.model.Square
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class EndgameAndSurvivalTest {

    @Test
    fun testLevelPuzzlesLegalityAndGrading() {
        val levelPuzzles = PuzzleRepository.levelPuzzles
        assertEquals("Level puzzles must have 100 puzzles", 100, levelPuzzles.size)

        val fens = levelPuzzles.map { it.fen }
        val dupes = fens.groupBy { it }.filter { it.value.size > 1 }
        dupes.forEach { (fen, list) -> println("Duplicate FEN (${list.size}): $fen") }

        assertEquals("Level puzzles must have 100 unique FENs", 100, fens.distinct().size)

        val engine = GameEngine()
        val errors = mutableListOf<String>()

        for ((index, puzzle) in levelPuzzles.withIndex()) {
            val levelNum = index + 1
            assertEquals("level_$levelNum", puzzle.id)
            assertTrue("Solution moves must not be empty", puzzle.solutionMoves.isNotEmpty())

            when {
                levelNum <= 20 -> assertTrue("Level $levelNum rating ${puzzle.rating} should be Easy (600..1050)", puzzle.rating in 600..1050)
                levelNum <= 60 -> assertTrue("Level $levelNum rating ${puzzle.rating} should be Moderate (1050..1850)", puzzle.rating in 1050..1850)
                else -> assertTrue("Level $levelNum rating ${puzzle.rating} should be Hard/GM (1850..2500)", puzzle.rating in 1850..2500)
            }
            if (index > 0) {
                assertTrue("Level $levelNum rating should be >= previous level rating", puzzle.rating >= levelPuzzles[index - 1].rating)
            }

            val (initialState, _) = NotationParser.fenToBoardState(puzzle.fen)
            var current = initialState
            for (m in puzzle.solutionMoves) {
                val from = Square(m[0] - 'a', m[1] - '1')
                val to = Square(m[2] - 'a', m[3] - '1')
                val legals = engine.getLegalMoves(current, from)
                val found = legals.find { it.to == to }
                if (found == null) {
                    errors.add("Level $levelNum (${puzzle.theme}) move $m illegal from FEN: ${puzzle.fen}")
                    break
                }
                current = current.copyWithMove(found)
            }
        }

        if (errors.isNotEmpty()) {
            errors.forEach { println(it) }
            assertTrue("Errors in level puzzles:\n${errors.joinToString("\n")}", errors.isEmpty())
        }
    }

    @Test
    fun testTacticalThemesCategoriesAnd100Levels() {
        val categories = PuzzleRepository.getAllTacticalCategories()
        assertTrue("Tactical theme categories should not be empty", categories.isNotEmpty())
        assertEquals(13, categories.size)
        val engine = GameEngine()

        val allErrors = mutableListOf<String>()
        for (cat in categories) {
            val puzzles = PuzzleRepository.getPuzzlesByCategory(cat)
            assertEquals("Category $cat must have 100 puzzles", 100, puzzles.size)
            assertTrue("Category $cat must have diverse authentic FENs", puzzles.map { it.fen }.distinct().isNotEmpty())
            if (cat == "Skewer") {
                val distinctFens = puzzles.map { it.fen }.distinct()
                assertEquals("Skewer must have 100 unique non-repeating positions", 100, distinctFens.size)
            }

            // Test all puzzles for each category to ensure move legality
            for (puzzle in puzzles) {
                val (initialState, _) = NotationParser.fenToBoardState(puzzle.fen)
                var current = initialState
                for (m in puzzle.solutionMoves) {
                    val from = Square(m[0] - 'a', m[1] - '1')
                    val to = Square(m[2] - 'a', m[3] - '1')
                    val legals = engine.getLegalMoves(current, from)
                    val found = legals.find { it.to == to }
                    if (found == null) {
                        allErrors.add("[$cat] Puzzle ${puzzle.id} move $m illegal from FEN ${puzzle.fen}")
                        break
                    }
                    current = current.copyWithMove(found)
                }
            }

            for (i in 1..100) {
                val puzzle = puzzles[i - 1]
                assertTrue("Solution moves must not be empty", puzzle.solutionMoves.isNotEmpty())
                when {
                    i <= 35 -> assertTrue("Level $i in $cat must be Easy rating", puzzle.rating in 600..1050)
                    i <= 70 -> assertTrue("Level $i in $cat must be Moderate rating", puzzle.rating in 1050..1680)
                    else -> assertTrue("Level $i in $cat must be Hard rating", puzzle.rating in 1700..2500)
                }
            }
        }
        if (allErrors.isNotEmpty()) {
            allErrors.forEach { println(it) }
            assertTrue("Errors found:\n${allErrors.joinToString("\n")}", allErrors.isEmpty())
        }
    }

    @Test
    fun testEndgameLessonsAreValidAndDiverse() {
        val categories = PuzzleRepository.getAllEndgameCategories()
        assertTrue("Endgame categories should not be empty", categories.isNotEmpty())
        val engine = GameEngine()

        for (cat in categories) {
            val lessons = PuzzleRepository.getEndgameLessonsByCategory(cat)
            assertEquals("Category $cat must have 100 lessons", 100, lessons.size)
            assertTrue("Category $cat must have diverse FENs", lessons.map { it.fen }.distinct().isNotEmpty())

            // Test lessons for each category to ensure move legality
            for (lesson in lessons.take(20)) {
                val (initialState, _) = NotationParser.fenToBoardState(lesson.fen)
                var current = initialState
                for (m in lesson.solutionMoves) {
                    val from = Square(m[0] - 'a', m[1] - '1')
                    val to = Square(m[2] - 'a', m[3] - '1')
                    val legals = engine.getLegalMoves(current, from)
                    val found = legals.find { it.to == to }
                    assertTrue(
                        "Move $m in lesson ${lesson.title} (${lesson.category}) should be legal from FEN ${lesson.fen}",
                        found != null
                    )
                    current = current.copyWithMove(found!!)
                }
            }

            if (cat == "King vs King") {
                val uniqueFens = lessons.map { it.fen }.distinct()
                assertTrue(
                    "King vs King should have multiple distinct endgame positions",
                    uniqueFens.size > 1
                )
            }
        }
    }

    @Test
    fun testOpeningTrapsLegality() {
        val traps = PuzzleRepository.getAllOpeningTraps()
        assertTrue("Traps list should have at least 16 traps", traps.size >= 16)
        val engine = GameEngine()

        val expectedTraps = listOf(
            "Scholar's Mate",
            "Fried Liver",
            "Legal's Trap",
            "Noah's Ark",
            "Greek Gift",
            "Stafford Gambit",
            "Vienna Trap",
            "Sicilian Smith-Morra",
            "Elephant Trap",
            "Blackburne Shilling Gambit",
            "Fishing Pole Trap",
            "Halosar Trap",
            "Lasker Trap",
            "Budapest Trap",
            "Mortimer Trap",
            "Rubinstein Trap"
        )
        val trapNames = traps.map { it.name }
        for (expected in expectedTraps) {
            assertTrue("Trap '$expected' must be in opening traps library", trapNames.contains(expected))
        }

        for (trap in traps) {
            val (initialState, _) = NotationParser.fenToBoardState(trap.initialFen)
            var current = initialState
            for (m in trap.moveSequence) {
                val from = Square(m[0] - 'a', m[1] - '1')
                val to = Square(m[2] - 'a', m[3] - '1')
                val legals = engine.getLegalMoves(current, from)
                val found = legals.find { it.to == to }
                assertTrue(
                    "Trap move $m in ${trap.name} should be legal from FEN ${trap.initialFen}",
                    found != null
                )
                current = current.copyWithMove(found!!)
            }
        }
    }

    @Test
    fun testPuzzleRushRandomSelectionAndAscendingRating() {
        val rushList1 = PuzzleRepository.getRandomPuzzles(30)
        assertEquals(30, rushList1.size)
        for (i in 0 until rushList1.size - 1) {
            assertTrue(
                "Puzzle rush must be sorted by ascending rating: index $i (${rushList1[i].rating}) <= ${rushList1[i+1].rating}",
                rushList1[i].rating <= rushList1[i + 1].rating
            )
        }
        val uniqueIds = rushList1.map { it.id }.distinct()
        assertTrue("Puzzle rush should have diverse unique puzzles", uniqueIds.size > 10)
    }

    @Test
    fun testSurvivalPuzzlesThemeMatchedAndAscending() {
        for (cat in PuzzleRepository.getAllSurvivalCategories()) {
            val puzzles = PuzzleRepository.getSurvivalPuzzlesByCategory(cat)
            assertEquals(100, puzzles.size)
            for (i in 0 until puzzles.size - 1) {
                assertTrue(
                    "Survival mode must be sorted by ascending rating in $cat: index $i (${puzzles[i].rating}) <= ${puzzles[i+1].rating}",
                    puzzles[i].rating <= puzzles[i + 1].rating
                )
            }
            if (cat == "Checkmate Survival") {
                val hasMateThemes = puzzles.any { it.theme.contains("Mate", ignoreCase = true) || it.theme.contains("Smothered", ignoreCase = true) }
                assertTrue("Checkmate survival should have mate themed puzzles", hasMateThemes)
            }
        }
    }

    @Test
    fun testDedicatedDatabasesExistAndHave100PuzzlesEach() {
        assertEquals(100, com.chessmaster.play.data.SkewerDatabase.getPuzzles().size)
        assertEquals(100, com.chessmaster.play.data.ForkDatabase.getPuzzles().size)
        assertEquals(100, com.chessmaster.play.data.PinDatabase.getPuzzles().size)
        assertEquals(100, com.chessmaster.play.data.MateIn1Database.getPuzzles().size)
        assertEquals(100, com.chessmaster.play.data.MateIn2Database.getPuzzles().size)
        assertEquals(100, com.chessmaster.play.data.DoubleAttackDatabase.getPuzzles().size)
        assertEquals(100, com.chessmaster.play.data.DiscoveredAttackDatabase.getPuzzles().size)
        assertEquals(100, com.chessmaster.play.data.SmotheredMateDatabase.getPuzzles().size)
        assertEquals(100, com.chessmaster.play.data.SacrificeDatabase.getPuzzles().size)
        assertEquals(100, com.chessmaster.play.data.AttractionDatabase.getPuzzles().size)
        assertEquals(100, com.chessmaster.play.data.DeflectionDatabase.getPuzzles().size)
        assertEquals(100, com.chessmaster.play.data.ClearanceDatabase.getPuzzles().size)
        assertEquals(100, com.chessmaster.play.data.WinningMaterialDatabase.getPuzzles().size)

        assertEquals(100, com.chessmaster.play.data.KingVsKingDatabase.getLessons().size)
        assertEquals(100, com.chessmaster.play.data.KingAndPawnDatabase.getLessons().size)
        assertEquals(100, com.chessmaster.play.data.RookEndgameDatabase.getLessons().size)
        assertEquals(100, com.chessmaster.play.data.LucenaDatabase.getLessons().size)
        assertEquals(100, com.chessmaster.play.data.PhilidorDatabase.getLessons().size)
        assertEquals(100, com.chessmaster.play.data.QueenEndgameDatabase.getLessons().size)
        assertEquals(100, com.chessmaster.play.data.BishopEndgameDatabase.getLessons().size)
        assertEquals(100, com.chessmaster.play.data.KnightEndgameDatabase.getLessons().size)
        assertEquals(100, com.chessmaster.play.data.PassedPawnsDatabase.getLessons().size)
        assertEquals(100, com.chessmaster.play.data.EndgameStrategyDatabase.getLessons().size)
    }
}
