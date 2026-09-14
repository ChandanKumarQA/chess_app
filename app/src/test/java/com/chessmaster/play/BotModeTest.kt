package com.chessmaster.play

import com.chessmaster.play.data.BotDatabase
import com.chessmaster.play.engine.AIEngine
import com.chessmaster.play.engine.GameEngine
import com.chessmaster.play.engine.NotationParser
import com.chessmaster.play.model.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.chessmaster.play.data.BotGameManager
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
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
        assertEquals(13, BotDatabase.getBotsByCategory(BotCategory.BEGINNER).size)
        assertEquals(13, BotDatabase.getBotsByCategory(BotCategory.INTERMEDIATE).size)
        assertEquals(23, BotDatabase.getBotsByCategory(BotCategory.PRO).size)
        assertEquals(6, BotDatabase.getBotsByCategory(BotCategory.MASTER).size)
        assertEquals(10, BotDatabase.getBotsByCategory(BotCategory.GRANDMASTER).size)
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

    @Test
    fun testNextBotProgression() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val gameManager = BotGameManager(context)

        // 1. Within Band Class category: Cliff -> Leo
        val nextAfterCliff = gameManager.getNextBot("band_cliff")
        assertNotNull(nextAfterCliff)
        assertEquals("band_leo", nextAfterCliff?.id)

        // 2. End of Band Class category: Maestro -> Beginner Martin
        val nextAfterMaestro = gameManager.getNextBot("band_maestro")
        assertNotNull(nextAfterMaestro)
        assertEquals("new_martin", nextAfterMaestro?.id)

        // 3. End of Beginner category: Amir -> Intermediate Marcus
        val nextAfterAmir = gameManager.getNextBot("beg_amir")
        assertNotNull(nextAfterAmir)
        assertEquals("beg_marcus", nextAfterAmir?.id)

        // 4. End of Intermediate category: Hassan -> Pro Kareem
        val nextAfterHassan = gameManager.getNextBot("int_hassan")
        assertNotNull(nextAfterHassan)
        assertEquals("int_kareem", nextAfterHassan?.id)

        // 5. Last bot in entire roster: Novak -> null
        val nextAfterNovak = gameManager.getNextBot("ath_novak")
        assertNull(nextAfterNovak)
    }

    @Test
    fun testBotDefeatUnlockingAndProgression() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val gameManager = BotGameManager(context)

        // Check initial state: band_cliff unlocked, band_leo locked
        assertTrue("Cliff should be unlocked by default", gameManager.isBotUnlocked("band_cliff"))
        // Clear prefs for fresh test
        context.getSharedPreferences("chess_bot_prefs", Context.MODE_PRIVATE).edit().clear().apply()

        // Defeat Cliff
        val nextBot = gameManager.onBotDefeated("band_cliff")
        assertNotNull("Defeating Cliff should return next bot", nextBot)
        assertEquals("band_leo", nextBot?.id)

        // Verify Leo is now unlocked
        assertTrue("Leo should be unlocked after Cliff is defeated", gameManager.isBotUnlocked("band_leo"))
        // Verify selected bot is now Leo
        assertEquals("band_leo", gameManager.getSelectedBotId())
        // Verify Cliff earned 3 crowns
        assertEquals(3, gameManager.getBotCrowns("band_cliff"))

        // Defeat Leo
        val nextAfterLeo = gameManager.onBotDefeated("band_leo")
        assertEquals("band_nora", nextAfterLeo?.id)
        assertTrue(gameManager.isBotUnlocked("band_nora"))
        assertEquals("band_nora", gameManager.getSelectedBotId())
    }

    @Test
    fun testSequentialUnlockingWithinTier() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val gameManager = BotGameManager(context)
        context.getSharedPreferences("chess_bot_prefs", Context.MODE_PRIVATE).edit().clear().apply()

        // Beginner tier:
        // 1st: new_martin (250) -> unlocked by default
        // 2nd: new_elani (400) -> locked initially
        // 3rd: new_olivia (550) -> locked initially
        assertTrue("Martin should be unlocked by default", gameManager.isBotUnlocked("new_martin"))
        assertFalse("Elani should be locked initially", gameManager.isBotUnlocked("new_elani"))
        assertFalse("Olivia should be locked initially", gameManager.isBotUnlocked("new_olivia"))

        // Defeat Martin
        val nextAfterMartin = gameManager.onBotDefeated("new_martin")
        assertEquals("new_elani", nextAfterMartin?.id)
        assertTrue("Elani should now be unlocked after defeating Martin", gameManager.isBotUnlocked("new_elani"))
        assertFalse("Olivia should still be locked", gameManager.isBotUnlocked("new_olivia"))

        // Defeat Elani
        val nextAfterElani = gameManager.onBotDefeated("new_elani")
        assertEquals("new_olivia", nextAfterElani?.id)
        assertTrue("Olivia should now be unlocked after defeating Elani", gameManager.isBotUnlocked("new_olivia"))
    }
}
