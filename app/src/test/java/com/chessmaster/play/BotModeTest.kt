package com.chessmaster.play

import com.chessmaster.play.data.BotDatabase
import com.chessmaster.play.engine.AIEngine
import com.chessmaster.play.engine.GameEngine
import com.chessmaster.play.engine.NotationParser
import com.chessmaster.play.model.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class BotModeTest {

    @Test
    fun testBotDatabaseRoster() {
        val allBots = BotDatabase.allBots
        assertTrue("Total bots should be at least 88", allBots.size >= 88)

        // Verify Cliff from screenshot exists
        val cliff = BotDatabase.getBotById("band_cliff")
        assertNotNull(cliff)
        assertEquals("Cliff", cliff.name)
        assertEquals("Triangle", cliff.title)
        assertEquals(300, cliff.rating)
        assertEquals("🇩🇪", cliff.flag)
        assertEquals("Ding! Ding! Ding! Time for a game!", cliff.catchphrase)
        assertEquals(5, cliff.coinCost)
        assertTrue(cliff.isUnlockedByDefault)
    }

    @Test
    fun testCategoryCountsMatchScreenshots() {
        assertEquals(5, BotDatabase.getBotsByCategory(BotCategory.BAND_CLASS).size)
        assertEquals(5, BotDatabase.getBotsByCategory(BotCategory.NEW_TO_CHESS).size)
        assertEquals(15, BotDatabase.getBotsByCategory(BotCategory.BEGINNER).size)
        assertEquals(15, BotDatabase.getBotsByCategory(BotCategory.INTERMEDIATE).size)
        assertEquals(20, BotDatabase.getBotsByCategory(BotCategory.ADVANCED).size)
        assertEquals(10, BotDatabase.getBotsByCategory(BotCategory.MASTER).size)
        assertEquals(5, BotDatabase.getBotsByCategory(BotCategory.ADAPTIVE).size)
        assertEquals(13, BotDatabase.getBotsByCategory(BotCategory.ATHLETES).size)
    }

    @Test
    fun testBotProfilesIntegrity() {
        for (bot in BotDatabase.allBots) {
            assertTrue("Bot id must not be blank", bot.id.isNotBlank())
            assertTrue("Bot name must not be blank", bot.name.isNotBlank())
            assertTrue("Bot flag must not be blank", bot.flag.isNotBlank())
            assertTrue("Bot catchphrase must not be blank", bot.catchphrase.isNotBlank())
            assertTrue("Bot rating must be > 0", bot.rating > 0)
        }
    }

    @Test
    fun testAIEngineCalculatesLegalMoves() = runBlocking {
        val engine = GameEngine()
        val aiEngine = AIEngine(engine)

        val initialBoard = BoardState.initial()
        val whiteMove = aiEngine.getBestMove(initialBoard, PieceColor.WHITE, depth = 1)
        assertNotNull("AI should find a legal White opening move", whiteMove)

        val legalWhiteMoves = engine.getAllLegalMoves(initialBoard, PieceColor.WHITE)
        assertTrue("Calculated move must be in legal moves", legalWhiteMoves.contains(whiteMove))

        val blackBoard = initialBoard.copyWithMove(whiteMove!!)
        val blackMove = aiEngine.getBestMove(blackBoard, PieceColor.BLACK, depth = 1)
        assertNotNull("AI should find a legal Black response move", blackMove)

        val legalBlackMoves = engine.getAllLegalMoves(blackBoard, PieceColor.BLACK)
        assertTrue("Calculated black move must be in legal moves", legalBlackMoves.contains(blackMove))
    }

    @Test
    fun testNotationParserRoundtripForBotGame() {
        val initialBoard = BoardState.initial()
        val fen = NotationParser.boardStateToFen(initialBoard, PieceColor.WHITE, 1)
        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", fen)

        val (parsedBoard, activeColor) = NotationParser.fenToBoardState(fen)
        assertEquals(PieceColor.WHITE, activeColor)
        assertEquals(initialBoard.board.size, parsedBoard.board.size)
        for ((square, piece) in initialBoard.board) {
            val parsedPiece = parsedBoard.getPiece(square)
            assertEquals("Piece at $square must match", piece, parsedPiece)
        }
    }
}
