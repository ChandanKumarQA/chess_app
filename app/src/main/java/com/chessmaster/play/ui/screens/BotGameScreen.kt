package com.chessmaster.play.ui.screens

import android.media.MediaPlayer
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chessmaster.play.R
import com.chessmaster.play.data.BotDatabase
import com.chessmaster.play.data.BotGameManager
import com.chessmaster.play.engine.AIEngine
import com.chessmaster.play.engine.GameEngine
import com.chessmaster.play.engine.GameState
import com.chessmaster.play.engine.NotationParser
import com.chessmaster.play.model.*
import com.chessmaster.play.ui.components.CapturedPiecesPanel
import com.chessmaster.play.ui.components.ChessBoard
import com.chessmaster.play.ui.components.bot.BotAvatar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun BotGameScreen(
    bot: BotProfile,
    isNewGame: Boolean,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val gameManager = remember { BotGameManager(context) }
    val gameEngine = remember { GameEngine() }
    val aiEngine = remember { AIEngine(gameEngine) }
    val coroutineScope = rememberCoroutineScope()

    var currentBot by remember { mutableStateOf(bot) }
    val humanColor = remember { PieceColor.WHITE }
    val botColor = remember { PieceColor.BLACK }

    var boardState by remember { mutableStateOf(BoardState.initial()) }
    var currentTurn by remember { mutableStateOf(PieceColor.WHITE) }
    var selectedSquare by remember { mutableStateOf<Square?>(null) }
    var legalMoves by remember { mutableStateOf<List<Move>>(emptyList()) }
    var lastMove by remember { mutableStateOf<Move?>(null) }
    var isCheck by remember { mutableStateOf(false) }
    var gameState by remember { mutableStateOf(GameState.IN_PROGRESS) }
    var isBotThinking by remember { mutableStateOf(false) }
    var moveHistorySan by remember { mutableStateOf<List<String>>(emptyList()) }
    var botDialogue by remember { mutableStateOf(bot.catchphrase) }

    // Sound effect player
    fun playMoveSound() {
        if (gameManager.isSoundEnabled()) {
            try {
                val mp = MediaPlayer.create(context, R.raw.chess_move)
                mp?.setOnCompletionListener { it.release() }
                mp?.start()
            } catch (e: Exception) {
                // Ignore audio errors gracefully
            }
        }
    }

    // Auto-save game state
    fun persistCurrentGame() {
        if (gameState == GameState.IN_PROGRESS) {
            val fen = NotationParser.boardStateToFen(
                boardState,
                currentTurn,
                (moveHistorySan.size / 2) + 1
            )
            gameManager.saveGame(
                botId = currentBot.id,
                fen = fen,
                isWhiteTurn = currentTurn == PieceColor.WHITE,
                playerColor = humanColor,
                movesSan = moveHistorySan
            )
        }
    }

    // Initialize or restore game
    LaunchedEffect(isNewGame) {
        if (!isNewGame && gameManager.hasSavedGame()) {
            val saved = gameManager.getSavedBotGame()
            if (saved != null) {
                try {
                    val (savedBoard, activeColor) = NotationParser.fenToBoardState(saved.fen)
                    boardState = savedBoard
                    currentTurn = activeColor
                    moveHistorySan = saved.movesSan
                    currentBot = BotDatabase.getBotById(saved.botId)
                    isCheck = gameEngine.isKingInCheck(savedBoard, activeColor)
                    gameState = gameEngine.checkGameState(savedBoard, activeColor, emptyList())
                    botDialogue = "Welcome back! Let's finish our game."
                } catch (e: Exception) {
                    // Fallback to fresh game if parse failed
                    boardState = BoardState.initial()
                    currentTurn = PieceColor.WHITE
                }
            }
        } else {
            // Fresh game
            boardState = BoardState.initial()
            currentTurn = PieceColor.WHITE
            moveHistorySan = emptyList()
            lastMove = null
            isCheck = false
            gameState = GameState.IN_PROGRESS
            botDialogue = currentBot.catchphrase
            gameManager.clearSavedGame()
        }
    }

    // Save on back
    BackHandler {
        persistCurrentGame()
        onBack()
    }

    // Bot move calculation and trigger
    fun triggerBotMove() {
        if (gameState != GameState.IN_PROGRESS) return
        if (currentTurn != botColor) return

        isBotThinking = true
        coroutineScope.launch {
            // 1. Realistic thinking delay according to bot rating/difficulty
            val delayMs = when {
                currentBot.rating < 1000 -> (600L..1000L).random()
                currentBot.rating < 1600 -> (900L..1500L).random()
                else -> (1200L..2000L).random()
            }
            delay(delayMs)

            // 2. Compute legal move
            val computedMove = withContext(Dispatchers.Default) {
                val allLegalMoves = gameEngine.getAllLegalMoves(boardState, botColor)
                if (allLegalMoves.isEmpty()) null
                else if (currentBot.rating < 600 && (1..100).random() <= 30) {
                    // Beginner bots make occasional natural mistakes/fun moves
                    allLegalMoves.random()
                } else {
                    val searchDepth = when {
                        currentBot.rating < 1000 -> 1
                        currentBot.rating < 1600 -> 2
                        currentBot.rating < 2200 -> 3
                        else -> 4
                    }
                    aiEngine.getBestMove(boardState, botColor, searchDepth) ?: allLegalMoves.random()
                }
            }

            // 3. Execute move safely on main thread
            isBotThinking = false
            if (computedMove != null && gameState == GameState.IN_PROGRESS) {
                val nextBoard = boardState.copyWithMove(computedMove)
                boardState = nextBoard
                lastMove = computedMove
                val nextTurn = botColor.opposite()
                currentTurn = nextTurn
                moveHistorySan = moveHistorySan + computedMove.toString()
                playMoveSound()

                val checked = gameEngine.isKingInCheck(nextBoard, nextTurn)
                isCheck = checked
                val nextState = gameEngine.checkGameState(nextBoard, nextTurn, emptyList())
                gameState = nextState

                // Bot dialogue response
                botDialogue = when {
                    nextState == GameState.CHECKMATE -> "Checkmate! A good game!"
                    checked -> "Check! Be careful."
                    computedMove.isCapture -> "I'll take that piece!"
                    else -> "Your turn now."
                }

                persistCurrentGame()
            }
        }
    }

    // Human move execution
    fun executeHumanMove(move: Move) {
        if (gameState != GameState.IN_PROGRESS) return
        if (isBotThinking) return
        if (currentTurn != humanColor) return

        val nextBoard = boardState.copyWithMove(move)
        boardState = nextBoard
        lastMove = move
        val nextTurn = humanColor.opposite()
        currentTurn = nextTurn
        moveHistorySan = moveHistorySan + move.toString()
        selectedSquare = null
        legalMoves = emptyList()
        playMoveSound()

        val checked = gameEngine.isKingInCheck(nextBoard, nextTurn)
        isCheck = checked
        val nextState = gameEngine.checkGameState(nextBoard, nextTurn, emptyList())
        gameState = nextState

        if (nextState == GameState.CHECKMATE) {
            botDialogue = "Ah, checkmate! You defeated me!"
            gameManager.onBotDefeated(currentBot.id)
        } else if (checked) {
            botDialogue = "Ouch, check! Let me defend."
        }

        persistCurrentGame()

        if (nextState == GameState.IN_PROGRESS) {
            triggerBotMove()
        }
    }

    // Square click handler
    fun onSquareClicked(square: Square) {
        if (gameState != GameState.IN_PROGRESS) return
        if (isBotThinking) return
        if (currentTurn != humanColor) return

        val clickedPiece = boardState.getPiece(square)
        val selected = selectedSquare

        if (selected == null) {
            // First click: select human piece
            if (clickedPiece != null && clickedPiece.color == humanColor) {
                selectedSquare = square
                legalMoves = gameEngine.getLegalMoves(boardState, square)
            }
        } else {
            // Second click: either make move or change selection
            val move = legalMoves.find { it.to == square }
            if (move != null) {
                executeHumanMove(move)
            } else if (clickedPiece != null && clickedPiece.color == humanColor) {
                // Select different piece
                selectedSquare = square
                legalMoves = gameEngine.getLegalMoves(boardState, square)
            } else {
                // Deselect
                selectedSquare = null
                legalMoves = emptyList()
            }
        }
    }

    // Theme colors matching reference screenshots
    val darkBg = Color(0xFF161E27)
    val cardBg = Color(0xFF222B37)
    val greenAccent = Color(0xFF81B64C)

    Scaffold(
        containerColor = darkBg
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TOP HEADER & BOT INFO PANEL
            Column(modifier = Modifier.fillMaxWidth()) {
                // Top App Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = {
                        persistCurrentGame()
                        onBack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }

                    Text(
                        text = "Play vs Bot",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    IconButton(onClick = {
                        // Restart fresh game
                        boardState = BoardState.initial()
                        currentTurn = PieceColor.WHITE
                        moveHistorySan = emptyList()
                        lastMove = null
                        isCheck = false
                        gameState = GameState.IN_PROGRESS
                        botDialogue = currentBot.catchphrase
                        gameManager.clearSavedGame()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Restart",
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                // BOT CARD (Avatar, Name, Rating, Dialogue)
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = cardBg,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BotAvatar(
                            style = currentBot.avatarStyle,
                            size = 54.dp
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = currentBot.fullDisplayName,
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "${currentBot.rating}",
                                    color = Color(0xFFFFD54F),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = currentBot.flag, fontSize = 13.sp)
                            }

                            Spacer(modifier = Modifier.height(2.dp))

                            // Thinking indicator or Dialogue
                            if (isBotThinking) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(12.dp),
                                        color = greenAccent,
                                        strokeWidth = 2.dp
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Bot is thinking...",
                                        color = greenAccent,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            } else {
                                Text(
                                    text = botDialogue,
                                    color = Color.White.copy(alpha = 0.8f),
                                    fontSize = 12.sp,
                                    maxLines = 2
                                )
                            }
                        }
                    }
                }
            }

            // MIDDLE: CHESS BOARD
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                ChessBoard(
                    boardState = boardState,
                    selectedSquare = selectedSquare,
                    legalMoves = legalMoves,
                    lastMove = lastMove,
                    isCheck = isCheck,
                    currentTurn = currentTurn,
                    onSquareClicked = { square ->
                        onSquareClicked(square)
                    },
                    modifier = Modifier.fillMaxSize()
                )

                // Input lock scrim when bot is thinking
                if (isBotThinking) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Transparent)
                    )
                }
            }

            // BOTTOM: HUMAN PLAYER PANEL & STATUS
            Column(modifier = Modifier.fillMaxWidth()) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = cardBg,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF37474F)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Human Player",
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "You",
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "(White)",
                                    color = Color.White.copy(alpha = 0.6f),
                                    fontSize = 13.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(2.dp))

                            Text(
                                text = if (currentTurn == humanColor && !isBotThinking) "Your Turn to Move"
                                       else if (isBotThinking) "Opponent is thinking..."
                                       else "Waiting...",
                                color = if (currentTurn == humanColor && !isBotThinking) greenAccent else Color.Gray,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // Crown indicator
                        val crowns = remember(currentBot.id, gameState) { gameManager.getBotCrowns(currentBot.id) }
                        if (crowns >= 3) {
                            Text(text = "👑👑👑", fontSize = 14.sp)
                        }
                    }
                }
            }
        }

        // GAME OVER DIALOG
        if (gameState != GameState.IN_PROGRESS) {
            val userWon = gameState == GameState.CHECKMATE && currentTurn == botColor
            val botWon = gameState == GameState.CHECKMATE && currentTurn == humanColor

            AlertDialog(
                onDismissRequest = { /* ignore */ },
                containerColor = Color(0xFF1E2632),
                titleContentColor = Color.White,
                textContentColor = Color.White.copy(alpha = 0.85f),
                title = {
                    Text(
                        text = when {
                            userWon -> "👑 Victory!"
                            botWon -> "Checkmate - Defeat"
                            else -> "Game Drawn"
                        },
                        fontWeight = FontWeight.Bold
                    )
                },
                text = {
                    Column {
                        Text(
                            text = when {
                                userWon -> "Outstanding! You defeated ${currentBot.fullDisplayName} and earned 3 Gold Crowns 👑👑👑!"
                                botWon -> "${currentBot.name} checkmated you. Don't give up, try again!"
                                else -> "The game ended in a draw (${gameState.name.lowercase().replace('_', ' ')})."
                            }
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            // Start fresh game against this bot
                            boardState = BoardState.initial()
                            currentTurn = PieceColor.WHITE
                            moveHistorySan = emptyList()
                            lastMove = null
                            isCheck = false
                            gameState = GameState.IN_PROGRESS
                            botDialogue = currentBot.catchphrase
                            gameManager.clearSavedGame()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = greenAccent)
                    ) {
                        Text("Play Again", fontWeight = FontWeight.Bold)
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        gameManager.clearSavedGame()
                        onBack()
                    }) {
                        Text("Exit", color = Color.White.copy(alpha = 0.7f))
                    }
                }
            )
        }
    }
}
