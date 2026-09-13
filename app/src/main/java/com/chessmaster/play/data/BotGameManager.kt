package com.chessmaster.play.data

import android.content.Context
import android.content.SharedPreferences
import com.chessmaster.play.model.BotProfile
import com.chessmaster.play.model.PieceColor

data class SavedBotGame(
    val botId: String,
    val fen: String,
    val isWhiteTurn: Boolean,
    val playerColor: PieceColor,
    val movesSan: List<String>
)

class BotGameManager(private val context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("chess_bot_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_SELECTED_BOT_ID = "selected_bot_id"
        private const val KEY_SOUND_ENABLED = "bot_sound_enabled"
        private const val KEY_SAVED_BOT_ID = "saved_bot_id"
        private const val KEY_SAVED_FEN = "saved_bot_fen"
        private const val KEY_SAVED_WHITE_TURN = "saved_bot_white_turn"
        private const val KEY_SAVED_PLAYER_COLOR = "saved_bot_player_color"
        private const val KEY_SAVED_MOVES = "saved_bot_moves"
        private const val PREFIX_CROWNS = "bot_crowns_"
        private const val PREFIX_UNLOCKED = "bot_unlocked_"
    }

    fun getSelectedBotId(): String {
        return prefs.getString(KEY_SELECTED_BOT_ID, "band_cliff") ?: "band_cliff"
    }

    fun setSelectedBotId(botId: String) {
        prefs.edit().putString(KEY_SELECTED_BOT_ID, botId).apply()
    }

    fun isSoundEnabled(): Boolean {
        return prefs.getBoolean(KEY_SOUND_ENABLED, true)
    }

    fun setSoundEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_SOUND_ENABLED, enabled).apply()
    }

    fun getBotCrowns(botId: String): Int {
        // By default, match the screenshots: some initial bots already have 3 crowns
        if (botId in listOf("beg_sven", "beg_chloe", "int_wendy", "int_antonio", "int_isabel", "adv_li", "adp_jake")) {
            return prefs.getInt(PREFIX_CROWNS + botId, 3)
        }
        return prefs.getInt(PREFIX_CROWNS + botId, 0)
    }

    fun isBotUnlocked(bot: BotProfile): Boolean {
        if (bot.isUnlockedByDefault) return true
        return prefs.getBoolean(PREFIX_UNLOCKED + bot.id, false)
    }

    fun unlockBot(botId: String) {
        prefs.edit().putBoolean(PREFIX_UNLOCKED + botId, true).apply()
    }

    fun onBotDefeated(botId: String) {
        prefs.edit()
            .putInt(PREFIX_CROWNS + botId, 3)
            .apply()

        // Unlock next bot in category if available
        val currentBot = BotDatabase.getBotById(botId)
        val categoryBots = BotDatabase.getBotsByCategory(currentBot.category)
        val currentIndex = categoryBots.indexOfFirst { it.id == botId }
        if (currentIndex != -1 && currentIndex + 1 < categoryBots.size) {
            unlockBot(categoryBots[currentIndex + 1].id)
        }

        // Clear finished saved game
        if (getSavedBotGame()?.botId == botId) {
            clearSavedGame()
        }
    }

    fun saveGame(
        botId: String,
        fen: String,
        isWhiteTurn: Boolean,
        playerColor: PieceColor,
        movesSan: List<String>
    ) {
        prefs.edit()
            .putString(KEY_SAVED_BOT_ID, botId)
            .putString(KEY_SAVED_FEN, fen)
            .putBoolean(KEY_SAVED_WHITE_TURN, isWhiteTurn)
            .putString(KEY_SAVED_PLAYER_COLOR, playerColor.name)
            .putString(KEY_SAVED_MOVES, movesSan.joinToString(";"))
            .apply()
    }

    fun getSavedBotGame(): SavedBotGame? {
        val botId = prefs.getString(KEY_SAVED_BOT_ID, null) ?: return null
        val fen = prefs.getString(KEY_SAVED_FEN, null) ?: return null
        val isWhiteTurn = prefs.getBoolean(KEY_SAVED_WHITE_TURN, true)
        val playerColorStr = prefs.getString(KEY_SAVED_PLAYER_COLOR, PieceColor.WHITE.name)
        val playerColor = try {
            PieceColor.valueOf(playerColorStr ?: PieceColor.WHITE.name)
        } catch (e: Exception) {
            PieceColor.WHITE
        }
        val movesStr = prefs.getString(KEY_SAVED_MOVES, "") ?: ""
        val moves = if (movesStr.isEmpty()) emptyList() else movesStr.split(";")

        return SavedBotGame(
            botId = botId,
            fen = fen,
            isWhiteTurn = isWhiteTurn,
            playerColor = playerColor,
            movesSan = moves
        )
    }

    fun hasSavedGame(): Boolean {
        return prefs.getString(KEY_SAVED_BOT_ID, null) != null &&
               prefs.getString(KEY_SAVED_FEN, null) != null
    }

    fun clearSavedGame() {
        prefs.edit()
            .remove(KEY_SAVED_BOT_ID)
            .remove(KEY_SAVED_FEN)
            .remove(KEY_SAVED_WHITE_TURN)
            .remove(KEY_SAVED_PLAYER_COLOR)
            .remove(KEY_SAVED_MOVES)
            .apply()
    }
}
