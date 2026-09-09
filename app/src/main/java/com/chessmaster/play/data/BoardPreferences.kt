package com.chessmaster.play.data

import android.content.Context
import android.content.SharedPreferences
import com.chessmaster.play.model.BoardTheme
import com.chessmaster.play.model.BoardThemes
import com.chessmaster.play.model.PieceSetId
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object BoardPreferences {
    private const val PREFS_NAME = "ChessVerseBoardSettings"
    private const val KEY_BOARD_THEME_ID = "board_theme_id"
    private const val KEY_PIECE_SET_ID = "piece_set_id"

    private val _boardTheme = MutableStateFlow(BoardThemes.GREEN)
    val boardTheme: StateFlow<BoardTheme> = _boardTheme.asStateFlow()

    private val _pieceSet = MutableStateFlow(PieceSetId.BURNETT)
    val pieceSet: StateFlow<PieceSetId> = _pieceSet.asStateFlow()

    private var isInitialized = false

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun init(context: Context) {
        if (isInitialized) return
        val prefs = getPrefs(context)
        val savedThemeId = prefs.getString(KEY_BOARD_THEME_ID, BoardThemes.GREEN.id) ?: BoardThemes.GREEN.id
        val savedPieceSetId = prefs.getString(KEY_PIECE_SET_ID, PieceSetId.BURNETT.id) ?: PieceSetId.BURNETT.id

        _boardTheme.value = BoardThemes.getById(savedThemeId)
        _pieceSet.value = PieceSetId.fromId(savedPieceSetId)
        isInitialized = true
    }

    fun setBoardTheme(context: Context, theme: BoardTheme) {
        _boardTheme.value = theme
        getPrefs(context).edit().putString(KEY_BOARD_THEME_ID, theme.id).apply()
    }

    fun setPieceSet(context: Context, set: PieceSetId) {
        _pieceSet.value = set
        getPrefs(context).edit().putString(KEY_PIECE_SET_ID, set.id).apply()
    }
}
