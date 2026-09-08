package com.chessmaster.play.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chessmaster.play.engine.AIEngine
import com.chessmaster.play.engine.GameEngine
import com.chessmaster.play.engine.GameState
import com.chessmaster.play.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import com.chessmaster.play.data.FirebaseGame
import com.chessmaster.play.data.FirebaseManager
import com.chessmaster.play.data.MatchmakingService
import com.chessmaster.play.engine.NotationParser
import com.google.firebase.firestore.ListenerRegistration
import android.util.Log

class ChessViewModel : ViewModel() {
    private val engine = GameEngine()
    private val aiEngine = AIEngine(engine)

    private val firebaseManager = FirebaseManager()
    private val matchmakingService = MatchmakingService(firebaseManager.firestore)

    private val _gameId = MutableStateFlow<String?>(null)
    val gameId: StateFlow<String?> = _gameId.asStateFlow()

    private val _playerColor = MutableStateFlow<PieceColor?>(null)
    val playerColor: StateFlow<PieceColor?> = _playerColor.asStateFlow()

    fun setPlayerColor(color: PieceColor?) {
        val actualColor = color ?: if (kotlin.random.Random.nextBoolean()) PieceColor.WHITE else PieceColor.BLACK
        _playerColor.value = actualColor
    }

    private val _connectionStatus = MutableStateFlow("Disconnected")
    val connectionStatus: StateFlow<String> = _connectionStatus.asStateFlow()

    private var listenerRegistration: ListenerRegistration? = null

    private val _boardState = MutableStateFlow(BoardState.initial())
    val boardState: StateFlow<BoardState> = _boardState.asStateFlow()

    private val _history = MutableStateFlow(listOf(Pair(BoardState.initial(), null as Move?)))
    private val _historyIndex = MutableStateFlow(0)
    val historyIndex: StateFlow<Int> = _historyIndex.asStateFlow()

    val canUndo: Boolean get() = _historyIndex.value > 0
    val canRedo: Boolean get() = _historyIndex.value < _history.value.lastIndex

    private val _currentTurn = MutableStateFlow(PieceColor.WHITE)
    val currentTurn: StateFlow<PieceColor> = _currentTurn.asStateFlow()

    private val _gameState = MutableStateFlow(GameState.IN_PROGRESS)
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    private val _gameMode = MutableStateFlow(GameMode.PVP)
    val gameMode: StateFlow<GameMode> = _gameMode.asStateFlow()
    
    private val _isDarkMode = MutableStateFlow(true)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()
    fun setDarkMode(isDark: Boolean) { _isDarkMode.value = isDark }

    private val _sounds = MutableStateFlow(true)
    val sounds: StateFlow<Boolean> = _sounds.asStateFlow()
    fun setSounds(value: Boolean) { _sounds.value = value }

    private val _soundsPieceMove = MutableStateFlow(false)
    val soundsPieceMove: StateFlow<Boolean> = _soundsPieceMove.asStateFlow()
    fun setSoundsPieceMove(value: Boolean) { _soundsPieceMove.value = value }

    private val _music = MutableStateFlow(true)
    val music: StateFlow<Boolean> = _music.asStateFlow()
    fun setMusic(value: Boolean) { _music.value = value }

    private val _musicTrack = MutableStateFlow(1)
    val musicTrack: StateFlow<Int> = _musicTrack.asStateFlow()
    fun setMusicTrack(value: Int) { _musicTrack.value = value }

    private val _musicGame = MutableStateFlow(false)
    val musicGame: StateFlow<Boolean> = _musicGame.asStateFlow()
    fun setMusicGame(value: Boolean) { _musicGame.value = value }

    private val _musicPuzzles = MutableStateFlow(true)
    val musicPuzzles: StateFlow<Boolean> = _musicPuzzles.asStateFlow()
    fun setMusicPuzzles(value: Boolean) { _musicPuzzles.value = value }

    private val _vibrations = MutableStateFlow(true)
    val vibrations: StateFlow<Boolean> = _vibrations.asStateFlow()
    fun setVibrations(value: Boolean) { _vibrations.value = value }

    private val _chessboardStyleIndex = MutableStateFlow(0)
    val chessboardStyleIndex: StateFlow<Int> = _chessboardStyleIndex.asStateFlow()
    fun setChessboardStyleIndex(value: Int) { _chessboardStyleIndex.value = value }

    private val _pieceStyleIndex = MutableStateFlow(0)
    val pieceStyleIndex: StateFlow<Int> = _pieceStyleIndex.asStateFlow()
    fun setPieceStyleIndex(value: Int) { _pieceStyleIndex.value = value }

    private val _cpuLevel = MutableStateFlow(1)
    val cpuLevel: StateFlow<Int> = _cpuLevel.asStateFlow()

    private val _isThinking = MutableStateFlow(false)
    val isThinking: StateFlow<Boolean> = _isThinking.asStateFlow()

    private var aiJob: Job? = null
    private var clockJob: Job? = null

    private val _isCheck = MutableStateFlow(false)
    val isCheck: StateFlow<Boolean> = _isCheck.asStateFlow()

    private val _lastMove = MutableStateFlow<Move?>(null)
    val lastMove: StateFlow<Move?> = _lastMove.asStateFlow()

    private val _selectedSquare = MutableStateFlow<Square?>(null)
    val selectedSquare: StateFlow<Square?> = _selectedSquare.asStateFlow()

    private val _legalMoves = MutableStateFlow<List<Move>>(emptyList())
    val legalMoves: StateFlow<List<Move>> = _legalMoves.asStateFlow()

    private val _roomCode = MutableStateFlow<String?>(null)
    val roomCode: StateFlow<String?> = _roomCode.asStateFlow()

    private val _chatMessages = MutableStateFlow<List<com.chessmaster.play.data.ChatMessage>>(emptyList())
    val chatMessages: StateFlow<List<com.chessmaster.play.data.ChatMessage>> = _chatMessages.asStateFlow()

    private val _latestEmoji = MutableStateFlow<com.chessmaster.play.data.EmojiReaction?>(null)
    val latestEmoji: StateFlow<com.chessmaster.play.data.EmojiReaction?> = _latestEmoji.asStateFlow()

    private val _drawOfferedBy = MutableStateFlow<String?>(null)
    val drawOfferedBy: StateFlow<String?> = _drawOfferedBy.asStateFlow()

    private val _rematchRequestedBy = MutableStateFlow<String?>(null)
    val rematchRequestedBy: StateFlow<String?> = _rematchRequestedBy.asStateFlow()

    private val _winner = MutableStateFlow<String?>(null)
    val winner: StateFlow<String?> = _winner.asStateFlow()

    private val _endReason = MutableStateFlow<String?>(null)
    val endReason: StateFlow<String?> = _endReason.asStateFlow()

    private val _capturedWhitePieces = MutableStateFlow<List<PieceType>>(emptyList())

    val capturedWhitePieces: StateFlow<List<PieceType>> = _capturedWhitePieces.asStateFlow()

    private val _capturedBlackPieces = MutableStateFlow<List<PieceType>>(emptyList())
    val capturedBlackPieces: StateFlow<List<PieceType>> = _capturedBlackPieces.asStateFlow()

    private val _clock = MutableStateFlow(ChessClock())
    val clock: StateFlow<ChessClock> = _clock.asStateFlow()

    private val _isHost = MutableStateFlow(false)
    val isHost: StateFlow<Boolean> = _isHost.asStateFlow()

    private val _hostReady = MutableStateFlow(false)
    val hostReady: StateFlow<Boolean> = _hostReady.asStateFlow()

    private val _guestReady = MutableStateFlow(false)
    val guestReady: StateFlow<Boolean> = _guestReady.asStateFlow()

    private val _colorPreference = MutableStateFlow("Random")
    val colorPreference: StateFlow<String> = _colorPreference.asStateFlow()

    private val _guestJoined = MutableStateFlow(false)
    val guestJoined: StateFlow<Boolean> = _guestJoined.asStateFlow()

    init {
        startClock()
        viewModelScope.launch {
            val uid = firebaseManager.signInAnonymously()
            if (uid != null) {
                _connectionStatus.value = "Connected"
            } else {
                _connectionStatus.value = "Auth Failed"
            }
        }
    }

    private fun startClock() {
        clockJob?.cancel()
        clockJob = viewModelScope.launch {
            while (true) {
                delay(100) // Update every 100ms
                if (_gameState.value == GameState.IN_PROGRESS && _clock.value.timeControl != TimeControl.UNLIMITED) {
                    val currentClock = _clock.value
                    if (currentClock.lastMoveTimestamp != null) {
                        val now = System.currentTimeMillis()
                        val elapsed = now - currentClock.lastMoveTimestamp
                        
                        val newWhiteTime = if (currentClock.isWhiteTurn) Math.max(0L, currentClock.whiteTimeMs - elapsed) else currentClock.whiteTimeMs
                        val newBlackTime = if (!currentClock.isWhiteTurn) Math.max(0L, currentClock.blackTimeMs - elapsed) else currentClock.blackTimeMs

                        _clock.value = currentClock.copy(
                            whiteTimeMs = newWhiteTime,
                            blackTimeMs = newBlackTime,
                            lastMoveTimestamp = now
                        )
                        
                        if (newWhiteTime == 0L || newBlackTime == 0L) {
                            _gameState.value = GameState.TIME_OUT 
                        }
                    }
                }
            }
        }
    }

    fun setTimeControl(timeControl: TimeControl) {
        _clock.value = ChessClock(timeControl = timeControl)
        resetGame()
    }

    fun undo() {
        if (canUndo) {
            _historyIndex.value -= 1
            restoreStateFromHistory(_historyIndex.value)
        }
    }

    fun createRoom(colorPref: String = "Random") {
        viewModelScope.launch {
            var uid = firebaseManager.currentUserId
            if (uid == null) {
                _connectionStatus.value = "Authenticating..."
                uid = firebaseManager.signInAnonymously()
                if (uid == null) {
                    _connectionStatus.value = "Error: ${firebaseManager.lastAuthError ?: "Authentication failed."}"
                    return@launch
                }
            }
            _connectionStatus.value = "Creating room..."
            try {
                _colorPreference.value = colorPref
                val game = matchmakingService.createRoomWithCode(uid, _clock.value.timeControl.name, colorPref)
                _gameId.value = game.id
                _roomCode.value = game.roomCode
                _isHost.value = true
                _gameMode.value = GameMode.ONLINE
                _connectionStatus.value = "Lobby"
                resetGame()
                listenToGame(game.id)
            } catch (e: Exception) {
                _connectionStatus.value = "Error: ${e.message}"
            }
        }
    }

    fun joinRoom(code: String) {
        viewModelScope.launch {
            var uid = firebaseManager.currentUserId
            if (uid == null) {
                _connectionStatus.value = "Authenticating..."
                uid = firebaseManager.signInAnonymously()
                if (uid == null) {
                    _connectionStatus.value = "Error: ${firebaseManager.lastAuthError ?: "Authentication failed."}"
                    return@launch
                }
            }
            _connectionStatus.value = "Joining room..."
            try {
                val game = matchmakingService.joinRoomWithCode(code, uid)
                _gameId.value = game.id
                _roomCode.value = game.roomCode
                _isHost.value = false
                _gameMode.value = GameMode.ONLINE
                _connectionStatus.value = "Lobby"
                resetGame()
                listenToGame(game.id)
            } catch (e: Exception) {
                _connectionStatus.value = "Error: ${e.message}"
            }
        }
    }

    fun toggleReady(isReady: Boolean) {
        viewModelScope.launch {
            val gid = _gameId.value ?: return@launch
            matchmakingService.toggleReady(gid, _isHost.value, isReady)
        }
    }

    fun updateLobbySettings(timeControlName: String, colorPref: String) {
        viewModelScope.launch {
            val gid = _gameId.value ?: return@launch
            matchmakingService.updateLobbySettings(gid, timeControlName, colorPref)
        }
    }

    fun startGame() {
        viewModelScope.launch {
            val gid = _gameId.value ?: return@launch
            matchmakingService.startGame(gid, _colorPreference.value)
        }
    }

    fun proceedToGame() {
        viewModelScope.launch {
            val gid = _gameId.value ?: return@launch
            firebaseManager.firestore.collection("games").document(gid)
                .update("status", "in_progress").await()
        }
    }

    fun exitRoom() {
        listenerRegistration?.remove()
        listenerRegistration = null
        _gameId.value = null
        _roomCode.value = null
        _gameMode.value = GameMode.PVP
        _playerColor.value = null
        _connectionStatus.value = "Disconnected"
    }

    fun redo() {
        if (canRedo) {
            _historyIndex.value += 1
            restoreStateFromHistory(_historyIndex.value)
        }
    }

    private fun restoreStateFromHistory(index: Int) {
        val (state, move) = _history.value[index]
        _boardState.value = state
        _lastMove.value = move
        _currentTurn.value = if (index % 2 == 0) PieceColor.WHITE else PieceColor.BLACK
        _isCheck.value = engine.isKingInCheck(state, _currentTurn.value)
        _selectedSquare.value = null
        _legalMoves.value = emptyList()
        // We pause the clock when navigating history
        _clock.value = _clock.value.copy(lastMoveTimestamp = null)
        recalculateCapturedPieces(index)
    }

    private fun recalculateCapturedPieces(index: Int) {
        val white = mutableListOf<PieceType>()
        val black = mutableListOf<PieceType>()
        
        for (i in 1..index) {
            val move = _history.value[i].second
            if (move != null && (move.isCapture || move.isEnPassant)) {
                val turn = if (i % 2 == 1) PieceColor.WHITE else PieceColor.BLACK
                val capturedType = if (move.isEnPassant) PieceType.PAWN else _history.value[i-1].first.getPiece(move.to)?.type
                if (capturedType != null) {
                    if (turn == PieceColor.WHITE) black.add(capturedType) else white.add(capturedType)
                }
            }
        }
        _capturedWhitePieces.value = white
        _capturedBlackPieces.value = black
    }

    fun onSquareClicked(square: Square) {
        if (_gameState.value != GameState.IN_PROGRESS) return
        if (_isThinking.value) return
        val cpuColor = _playerColor.value?.opposite() ?: PieceColor.BLACK
        if (_gameMode.value == GameMode.PVCPU && _currentTurn.value == cpuColor) return
        if (_gameMode.value == GameMode.ONLINE) {
            val pColor = _playerColor.value
            if (pColor == null || _currentTurn.value != pColor) return
        }
        if (_historyIndex.value < _history.value.lastIndex) {
            // Cannot play move if we are in undo state
            return
        }

        val selected = _selectedSquare.value
        val pieceOnSquare = _boardState.value.getPiece(square)

        if (selected == null) {
            if (pieceOnSquare != null && pieceOnSquare.color == _currentTurn.value) {
                _selectedSquare.value = square
                _legalMoves.value = engine.getLegalMoves(_boardState.value, square)
            }
        } else {
            if (pieceOnSquare != null && pieceOnSquare.color == _currentTurn.value) {
                _selectedSquare.value = square
                _legalMoves.value = engine.getLegalMoves(_boardState.value, square)
            } else {
                val move = _legalMoves.value.find { 
                    it.to == square && (it.promotionTo == null || it.promotionTo == PieceType.QUEEN) 
                }
                if (move != null) {
                    executeMove(move)
                } else {
                    _selectedSquare.value = null
                    _legalMoves.value = emptyList()
                }
            }
        }
    }

    private fun executeMove(move: Move) {
        if (move.isCapture || move.isEnPassant) {
            val capturedPieceType = if (move.isEnPassant) {
                PieceType.PAWN
            } else {
                _boardState.value.getPiece(move.to)?.type
            }
            if (capturedPieceType != null) {
                if (_currentTurn.value == PieceColor.WHITE) {
                    _capturedBlackPieces.value = _capturedBlackPieces.value + capturedPieceType
                } else {
                    _capturedWhitePieces.value = _capturedWhitePieces.value + capturedPieceType
                }
            }
        }

        val nextState = _boardState.value.copyWithMove(move)
        _boardState.value = nextState
        _lastMove.value = move
        
        val newHistory = _history.value.take(_historyIndex.value + 1) + Pair(nextState, move)
        _history.value = newHistory
        _historyIndex.value = newHistory.lastIndex
        
        _currentTurn.value = _currentTurn.value.opposite()
        
        _selectedSquare.value = null
        _legalMoves.value = emptyList()

        // Handle clock increment and turn swap
        if (_clock.value.timeControl != TimeControl.UNLIMITED) {
            val increment = _clock.value.timeControl.incrementSeconds * 1000L
            val currentClock = _clock.value
            _clock.value = currentClock.copy(
                whiteTimeMs = if (currentClock.isWhiteTurn) currentClock.whiteTimeMs + increment else currentClock.whiteTimeMs,
                blackTimeMs = if (!currentClock.isWhiteTurn) currentClock.blackTimeMs + increment else currentClock.blackTimeMs,
                isWhiteTurn = _currentTurn.value == PieceColor.WHITE,
                lastMoveTimestamp = System.currentTimeMillis()
            )
        }

        val historyStates = newHistory.map { it.first }
        val state = engine.checkGameState(nextState, _currentTurn.value, historyStates)
        _gameState.value = state
        _isCheck.value = engine.isKingInCheck(nextState, _currentTurn.value)

        if (_gameMode.value == GameMode.ONLINE && _gameId.value != null && _playerColor.value != _currentTurn.value) {
            val fen = NotationParser.boardStateToFen(nextState, _currentTurn.value, (_historyIndex.value / 2) + 1)
            
            val updates = mutableMapOf<String, Any>(
                "fen" to fen,
                "lastMoveTimestamp" to System.currentTimeMillis(),
                "whiteTimeRemaining" to _clock.value.whiteTimeMs,
                "blackTimeRemaining" to _clock.value.blackTimeMs,
                "status" to if (state == GameState.IN_PROGRESS) "in_progress" else "finished"
            )
            
            if (state != GameState.IN_PROGRESS) {
                updates["reason"] = state.name.replace("_", " ")
                updates["winner"] = when (state) {
                    GameState.CHECKMATE -> if (_currentTurn.value == PieceColor.WHITE) "Black" else "White"
                    GameState.TIME_OUT -> if (_currentTurn.value == PieceColor.WHITE) "Black" else "White"
                    else -> "Draw"
                }
            }

            firebaseManager.firestore.collection("games").document(_gameId.value!!)
                .update(updates)
                .addOnFailureListener { Log.e("ChessViewModel", "Error syncing move", it) }
        }

        triggerAIMoveIfNecessary()
    }

    private fun triggerAIMoveIfNecessary() {
        if (_gameState.value != GameState.IN_PROGRESS) return
        val cpuColor = _playerColor.value?.opposite() ?: PieceColor.BLACK
        if (_gameMode.value == GameMode.PVCPU && _currentTurn.value == cpuColor) {
            _isThinking.value = true
            aiJob?.cancel()
            aiJob = viewModelScope.launch(Dispatchers.Default) {
                // Calculate a dynamic delay based on level to make it feel more realistic
                val baseDelay = when (_cpuLevel.value) {
                    in 1..5 -> 1000L
                    in 6..10 -> 1500L
                    in 11..15 -> 2000L
                    else -> 2500L
                }
                val randomJitter = (0..1000).random().toLong()
                delay(baseDelay + randomJitter) // Wait to simulate human thinking
                
                val depth = when (_cpuLevel.value) {
                    in 1..5 -> 1
                    in 6..10 -> 2
                    in 11..15 -> 3
                    else -> 4
                }
                val bestMove = aiEngine.getBestMove(_boardState.value, _currentTurn.value, depth)
                launch(Dispatchers.Main) {
                    _isThinking.value = false
                    if (bestMove != null) {
                        executeMove(bestMove)
                    }
                }
            }
        }
    }

    fun getHint() {
        if (_gameState.value != GameState.IN_PROGRESS) return
        if (_isThinking.value) return
        val cpuColor = _playerColor.value?.opposite() ?: PieceColor.BLACK
        if (_gameMode.value == GameMode.PVCPU && _currentTurn.value == cpuColor) return
        
        _isThinking.value = true
        aiJob?.cancel()
        aiJob = viewModelScope.launch(Dispatchers.Default) {
            val depth = when (_cpuLevel.value) {
                in 1..5 -> 2
                in 6..10 -> 3
                else -> 4
            }
            val bestMove = aiEngine.getBestMove(_boardState.value, _currentTurn.value, depth)
            launch(Dispatchers.Main) {
                _isThinking.value = false
                if (bestMove != null) {
                    _selectedSquare.value = bestMove.from
                    _legalMoves.value = listOf(bestMove)
                }
            }
        }
    }

    fun setGameMode(mode: GameMode) {
        _gameMode.value = mode
        resetGame()
    }

    fun setCpuLevel(level: Int) {
        _cpuLevel.value = level
    }

    fun startRandomMatchmaking() {
        if (_gameMode.value != GameMode.ONLINE) return
        val tc = _clock.value.timeControl
        viewModelScope.launch {
            var uid = firebaseManager.currentUserId
            if (uid == null) {
                _connectionStatus.value = "Authenticating..."
                uid = firebaseManager.signInAnonymously()
                if (uid == null) {
                    _connectionStatus.value = "Error: ${firebaseManager.lastAuthError ?: "Authentication failed."}"
                    return@launch
                }
            }
            _connectionStatus.value = "Searching for game..."
            val existingGame = matchmakingService.findRandomGame(uid)
            if (existingGame != null) {
                _gameId.value = existingGame.id
                _playerColor.value = PieceColor.BLACK
                listenToGame(existingGame.id)
            } else {
                val newGame = matchmakingService.createGame(uid, tc.name)
                if (newGame != null) {
                    _gameId.value = newGame.id
                    _playerColor.value = PieceColor.WHITE
                    listenToGame(newGame.id)
                }
            }
        }
    }

    fun createInviteGame() {
        if (_gameMode.value != GameMode.ONLINE) return
        val tc = _clock.value.timeControl
        viewModelScope.launch {
            var uid = firebaseManager.currentUserId
            if (uid == null) {
                _connectionStatus.value = "Authenticating..."
                uid = firebaseManager.signInAnonymously()
                if (uid == null) {
                    _connectionStatus.value = "Error: ${firebaseManager.lastAuthError ?: "Authentication failed."}"
                    return@launch
                }
            }
            _connectionStatus.value = "Creating game..."
            val newGame = matchmakingService.createGame(uid, tc.name)
            if (newGame != null) {
                _gameId.value = newGame.id
                _playerColor.value = PieceColor.WHITE
                listenToGame(newGame.id)
            }
        }
    }

    fun joinGameByCode(code: String) {
        if (_gameMode.value != GameMode.ONLINE) return
        viewModelScope.launch {
            var uid = firebaseManager.currentUserId
            if (uid == null) {
                _connectionStatus.value = "Authenticating..."
                uid = firebaseManager.signInAnonymously()
                if (uid == null) {
                    _connectionStatus.value = "Error: ${firebaseManager.lastAuthError ?: "Authentication failed."}"
                    return@launch
                }
            }
            _connectionStatus.value = "Joining game..."
            val game = matchmakingService.joinGameByCode(code, uid)
            if (game != null) {
                _gameId.value = game.id
                _playerColor.value = PieceColor.BLACK
                listenToGame(game.id)
                
                // Set time control
                try {
                    val tc = TimeControl.valueOf(game.timeControlName)
                    setTimeControl(tc)
                } catch (e: Exception) {}
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
                    Log.w("ChessViewModel", "Listen failed.", error)
                    return@addSnapshotListener
                }

                if (snapshot != null && snapshot.exists()) {
                    val game = snapshot.toObject(FirebaseGame::class.java)
                    if (game != null) {
                        _hostReady.value = game.hostReady
                        _guestReady.value = game.guestReady
                        _colorPreference.value = game.colorPreference
                        _guestJoined.value = game.guestId != null

                        // If time control changed in lobby
                        try {
                            if (game.status == "lobby") {
                                val tc = TimeControl.valueOf(game.timeControlName)
                                if (_clock.value.timeControl != tc) {
                                    setTimeControl(tc)
                                }
                            }
                        } catch (e: Exception) {}

                        if (game.status == "lobby") {
                            _connectionStatus.value = "Lobby"
                            _chatMessages.value = game.chatMessages
                        } else if (game.status == "starting") {
                            _connectionStatus.value = "Starting"
                            _playerColor.value = if (game.whitePlayerId == firebaseManager.currentUserId) PieceColor.WHITE else PieceColor.BLACK
                        } else if (game.status == "waiting") {
                            _connectionStatus.value = "Waiting for opponent..."
                        } else if (game.status == "in_progress") {
                            _connectionStatus.value = "In Game"
                            _chatMessages.value = game.chatMessages
                            _latestEmoji.value = game.emojiReactions.maxByOrNull { it.timestamp }
                            _drawOfferedBy.value = game.drawOfferedBy
                            _rematchRequestedBy.value = game.rematchRequestedBy
                            
                            if (game.fen.isNotEmpty()) {
                                try {
                                    val (state, turn) = NotationParser.fenToBoardState(game.fen)
                                    // If we receive a new state that is not our current state
                                    if (state != _boardState.value) {
                                        _boardState.value = state
                                        _currentTurn.value = turn
                                        _isCheck.value = engine.isKingInCheck(state, turn)
                                        _legalMoves.value = emptyList()
                                        _selectedSquare.value = null
                                        
                                        // Push to history
                                        val newHistory = _history.value.take(_historyIndex.value + 1) + Pair(state, null)
                                        _history.value = newHistory
                                        _historyIndex.value = newHistory.lastIndex
                                        
                                        // Sync clock
                                        _clock.value = _clock.value.copy(
                                            whiteTimeMs = game.whiteTimeRemaining,
                                            blackTimeMs = game.blackTimeRemaining,
                                            isWhiteTurn = turn == PieceColor.WHITE,
                                            lastMoveTimestamp = game.lastMoveTimestamp
                                        )
                                    }
                                } catch (e: Exception) {
                                    Log.e("ChessViewModel", "Error parsing FEN", e)
                                }
                            }
                        } else {
                            _connectionStatus.value = "Game Over"
                            _gameState.value = GameState.CHECKMATE // Or other end state
                            _winner.value = game.winner
                            _endReason.value = game.reason
                            _chatMessages.value = game.chatMessages
                            _latestEmoji.value = game.emojiReactions.maxByOrNull { it.timestamp }
                            _rematchRequestedBy.value = game.rematchRequestedBy
                        }
                    }
                }
            }
    }

    fun resetGame() {
        aiJob?.cancel()
        _isThinking.value = false
        _boardState.value = BoardState.initial()
        _history.value = listOf(Pair(BoardState.initial(), null))
        _historyIndex.value = 0
        _currentTurn.value = PieceColor.WHITE
        _gameState.value = GameState.IN_PROGRESS
        _isCheck.value = false
        _lastMove.value = null
        _selectedSquare.value = null
        _legalMoves.value = emptyList()
        _capturedWhitePieces.value = emptyList()
        _capturedBlackPieces.value = emptyList()
        
        val tc = _clock.value.timeControl
        _clock.value = ChessClock(
            timeControl = tc,
            whiteTimeMs = tc.timeMinutes * 60 * 1000L,
            blackTimeMs = tc.timeMinutes * 60 * 1000L,
            isWhiteTurn = true,
            lastMoveTimestamp = if (tc != TimeControl.UNLIMITED) System.currentTimeMillis() else null
        )
        
        triggerAIMoveIfNecessary()
    }

    fun sendChatMessage(text: String) {
        val uid = firebaseManager.currentUserId ?: return
        val gameId = _gameId.value ?: return
        val message = com.chessmaster.play.data.ChatMessage(senderId = uid, text = text, timestamp = System.currentTimeMillis())
        val docRef = firebaseManager.firestore.collection("games").document(gameId)
        docRef.update("chatMessages", com.google.firebase.firestore.FieldValue.arrayUnion(message))
            .addOnFailureListener { Log.e("ChessViewModel", "Error sending chat", it) }
    }

    fun sendEmojiReaction(emoji: String) {
        val uid = firebaseManager.currentUserId ?: return
        val gameId = _gameId.value ?: return
        val reaction = com.chessmaster.play.data.EmojiReaction(senderId = uid, emoji = emoji, timestamp = System.currentTimeMillis())
        val docRef = firebaseManager.firestore.collection("games").document(gameId)
        docRef.update("emojiReactions", com.google.firebase.firestore.FieldValue.arrayUnion(reaction))
            .addOnFailureListener { Log.e("ChessViewModel", "Error sending emoji", it) }
    }

    fun offerDraw() {
        val uid = firebaseManager.currentUserId ?: return
        val gameId = _gameId.value ?: return
        firebaseManager.firestore.collection("games").document(gameId)
            .update("drawOfferedBy", uid)
    }

    fun acceptDraw() {
        val gameId = _gameId.value ?: return
        firebaseManager.firestore.collection("games").document(gameId)
            .update("status", "finished", "reason", "Draw by agreement", "winner", "Draw")
    }

    fun resign() {
        val uid = firebaseManager.currentUserId ?: return
        val gameId = _gameId.value ?: return
        val winnerColor = if (_playerColor.value == PieceColor.WHITE) "Black" else "White"
        firebaseManager.firestore.collection("games").document(gameId)
            .update("status", "finished", "reason", "Resignation", "winner", winnerColor)
    }

    fun requestRematch() {
        val uid = firebaseManager.currentUserId ?: return
        val gameId = _gameId.value ?: return
        firebaseManager.firestore.collection("games").document(gameId)
            .update("rematchRequestedBy", uid)
    }

    fun acceptRematch() {
        val gameId = _gameId.value ?: return
        val currentWhite = _playerColor.value == PieceColor.WHITE
        firebaseManager.firestore.collection("games").document(gameId)
            .update(
                "status", "starting",
                "rematchRequestedBy", null,
                "fen", "",
                "winner", null,
                "reason", null,
                "drawOfferedBy", null,
                "lastMoveTimestamp", System.currentTimeMillis()
            )
    }

    override fun onCleared() {
        super.onCleared()
        listenerRegistration?.remove()
    }
}
