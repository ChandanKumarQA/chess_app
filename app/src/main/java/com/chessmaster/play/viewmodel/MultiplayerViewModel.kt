package com.chessmaster.play.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chessmaster.play.data.FirebaseGame
import com.chessmaster.play.data.FirebaseManager
import com.chessmaster.play.data.MatchmakingService
import com.chessmaster.play.engine.NotationParser
import com.chessmaster.play.model.*
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.util.Log

class MultiplayerViewModel : ViewModel() {
    private val firebaseManager = FirebaseManager()
    private val matchmakingService = MatchmakingService(firebaseManager.firestore)

    private val _gameId = MutableStateFlow<String?>(null)
    val gameId: StateFlow<String?> = _gameId.asStateFlow()

    private val _playerColor = MutableStateFlow<PieceColor?>(null)
    val playerColor: StateFlow<PieceColor?> = _playerColor.asStateFlow()

    private val _boardState = MutableStateFlow(BoardState.initial())
    val boardState: StateFlow<BoardState> = _boardState.asStateFlow()

    private val _currentTurn = MutableStateFlow(PieceColor.WHITE)
    val currentTurn: StateFlow<PieceColor> = _currentTurn.asStateFlow()

    private val _connectionStatus = MutableStateFlow("Disconnected")
    val connectionStatus: StateFlow<String> = _connectionStatus.asStateFlow()

    private var listenerRegistration: ListenerRegistration? = null

    init {
        viewModelScope.launch {
            val uid = firebaseManager.signInAnonymously()
            if (uid != null) {
                _connectionStatus.value = "Connected"
            } else {
                _connectionStatus.value = "Auth Failed"
            }
        }
    }

    fun startRandomMatchmaking(timeControlName: String) {
        viewModelScope.launch {
            val uid = firebaseManager.currentUserId ?: return@launch
            _connectionStatus.value = "Searching for game..."
            val existingGame = matchmakingService.findRandomGame(uid)
            if (existingGame != null) {
                _gameId.value = existingGame.id
                _playerColor.value = PieceColor.BLACK
                listenToGame(existingGame.id)
            } else {
                val newGame = matchmakingService.createGame(uid, timeControlName)
                if (newGame != null) {
                    _gameId.value = newGame.id
                    _playerColor.value = PieceColor.WHITE
                    listenToGame(newGame.id)
                }
            }
        }
    }

    fun createInviteGame(timeControlName: String) {
        viewModelScope.launch {
            val uid = firebaseManager.currentUserId ?: return@launch
            _connectionStatus.value = "Creating game..."
            val newGame = matchmakingService.createGame(uid, timeControlName)
            if (newGame != null) {
                _gameId.value = newGame.id
                _playerColor.value = PieceColor.WHITE
                listenToGame(newGame.id)
            }
        }
    }

    fun joinGameByCode(code: String) {
        viewModelScope.launch {
            val uid = firebaseManager.currentUserId ?: return@launch
            _connectionStatus.value = "Joining game..."
            val game = matchmakingService.joinGameByCode(code, uid)
            if (game != null) {
                _gameId.value = game.id
                _playerColor.value = PieceColor.BLACK
                listenToGame(game.id)
            } else {
                _connectionStatus.value = "Invalid or full game"
            }
        }
    }

    private fun listenToGame(id: String) {
        listenerRegistration?.remove()
        listenerRegistration = firebaseManager.firestore.collection("games").document(id)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.w("MultiplayerViewModel", "Listen failed.", error)
                    return@addSnapshotListener
                }

                if (snapshot != null && snapshot.exists()) {
                    val game = snapshot.toObject(FirebaseGame::class.java)
                    if (game != null) {
                        if (game.status == "waiting") {
                            _connectionStatus.value = "Waiting for opponent..."
                        } else if (game.status == "in_progress") {
                            _connectionStatus.value = "In Game"
                            if (game.fen.isNotEmpty()) {
                                try {
                                    val (state, turn) = NotationParser.fenToBoardState(game.fen)
                                    _boardState.value = state
                                    _currentTurn.value = turn
                                } catch (e: Exception) {
                                    Log.e("MultiplayerViewModel", "Error parsing FEN", e)
                                }
                            }
                        } else {
                            _connectionStatus.value = "Game Over"
                        }
                    }
                }
            }
    }

    fun makeMove(move: Move, nextState: BoardState, activeColor: PieceColor, fullMoveNumber: Int) {
        val gid = _gameId.value ?: return
        val fen = NotationParser.boardStateToFen(nextState, activeColor, fullMoveNumber)
        
        firebaseManager.firestore.collection("games").document(gid)
            .update("fen", fen, "lastMoveTimestamp", System.currentTimeMillis())
            .addOnSuccessListener {
                Log.d("MultiplayerViewModel", "Move sent to server")
            }
            .addOnFailureListener { e ->
                Log.e("MultiplayerViewModel", "Error sending move", e)
            }
    }

    override fun onCleared() {
        super.onCleared()
        listenerRegistration?.remove()
    }
}
