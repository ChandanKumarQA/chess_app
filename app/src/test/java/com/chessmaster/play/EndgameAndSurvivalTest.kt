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
    fun testSurvivalCategoriesAnd100Levels() {
        val categories = PuzzleRepository.getAllSurvivalCategories()
        assertTrue("Categories should not be empty", categories.isNotEmpty())
        assertEquals(6, categories.size)

        for (cat in categories) {
            val puzzles = PuzzleRepository.getSurvivalPuzzlesByCategory(cat)
            assertEquals("Category $cat must have 100 puzzles", 100, puzzles.size)

            for (i in 1..100) {
                val puzzle = puzzles[i - 1]
                assertTrue("Puzzle id must match level", puzzle.id.endsWith("_$i"))
                assertTrue("Solution moves must not be empty", puzzle.solutionMoves.isNotEmpty())
                when {
                    i <= 35 -> assertTrue("Level $i in $cat must be Easy rating", puzzle.rating in 600..1050)
                    i <= 70 -> assertTrue("Level $i in $cat must be Moderate rating", puzzle.rating in 1100..1650)
                    else -> assertTrue("Level $i in $cat must be Hard rating", puzzle.rating in 1700..2400)
                }
            }
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

            // Test first 15 lessons for each category to ensure move legality
            for (lesson in lessons.take(15)) {
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

            // Verify King vs King has diverse non-identical positions across levels
            if (cat == "King vs King") {
                val uniqueFens = lessons.map { it.fen }.distinct()
                assertTrue(
                    "King vs King should have multiple distinct endgame positions, found ${uniqueFens.size}",
                    uniqueFens.size > 1
                )
                assertNotEquals(
                    "King vs King should not be just identical dummy position",
                    lessons[0].fen,
                    lessons[1].fen
                )
            }
        }
    }
}
