package com.chessmaster.play.ui.screens

import android.app.Activity
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chessmaster.play.InterstitialAdManager
import com.chessmaster.play.RewardedAdManager
import com.chessmaster.play.data.PuzzleRepository
import com.chessmaster.play.engine.GameEngine
import com.chessmaster.play.engine.NotationParser
import com.chessmaster.play.model.BoardState
import com.chessmaster.play.model.Move
import com.chessmaster.play.model.PieceColor
import com.chessmaster.play.model.Puzzle
import com.chessmaster.play.model.Square
import com.chessmaster.play.ui.components.ChessBoard
import com.chessmaster.play.ui.components.coach.CoachAvatar
import com.chessmaster.play.ui.components.coach.CoachSpeechBubble
import com.chessmaster.play.ui.components.coach.CoachVoiceManager
import com.chessmaster.play.ui.components.coach.PuzzleHintGenerator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

enum class PuzzleDialogState {
    START, PLAYING, WON, LOST
}

data class PuzzleHistoryStep(
    val boardState: BoardState,
    val currentTurn: PieceColor,
    val lastMove: Move?,
    val solutionIndex: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PuzzleGameScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val activity = context as? Activity
    val isCustomPuzzle = remember { PuzzleRepository.activePlayPuzzle != null }
    var level by remember { mutableIntStateOf(if (isCustomPuzzle) 0 else PuzzleRepository.currentPuzzleLevel) }
    var puzzle by remember { mutableStateOf(PuzzleRepository.activePlayPuzzle ?: PuzzleRepository.getPuzzleForLevel(level)) }

    var showExitDialog by remember { mutableStateOf(false) }

    val handleBack = {
        showExitDialog = true
    }

    androidx.activity.compose.BackHandler {
        handleBack()
    }

    // Voice Coach Manager
    val voiceManager = remember { CoachVoiceManager(context) }
    DisposableEffect(Unit) {
        onDispose {
            voiceManager.shutdown()
        }
    }

    // Coach Speech Bubble State
    var coachBubbleVisible by remember { mutableStateOf(false) }
    var coachBubbleTitle by remember { mutableStateOf("Hint") }
    var coachBubbleMessage by remember { mutableStateOf("") }
    var coachBubbleIsError by remember { mutableStateOf(false) }

    // Board & Game State
    var boardState by remember { mutableStateOf<BoardState?>(null) }
    var currentTurn by remember { mutableStateOf(PieceColor.WHITE) }
    var selectedSquare by remember { mutableStateOf<Square?>(null) }
    var legalMoves by remember { mutableStateOf<List<Move>>(emptyList()) }
    var lastMove by remember { mutableStateOf<Move?>(null) }
    var solutionIndex by remember { mutableIntStateOf(0) }
    var dialogState by remember { mutableStateOf(PuzzleDialogState.START) }
    var remainingHints by remember { mutableIntStateOf(com.chessmaster.play.data.LocalLeaderboardManager.getRemainingHints(context)) }
    var hintMove by remember { mutableStateOf<Move?>(null) }
    var showShopDialog by remember { mutableStateOf(false) }
    var elapsedTime by remember { mutableIntStateOf(0) }

    // Undo / Redo Move History
    val history = remember { mutableStateListOf<PuzzleHistoryStep>() }
    val redoStack = remember { mutableStateListOf<PuzzleHistoryStep>() }

    LaunchedEffect(dialogState) {
        if (dialogState == PuzzleDialogState.PLAYING) {
            while (true) {
                delay(1000)
                elapsedTime++
            }
        }
    }

    val interstitialAdManager = remember { InterstitialAdManager(context) }
    val adManager = remember { RewardedAdManager(context) }
    LaunchedEffect(Unit) {
        adManager.loadAd()
    }

    val engine = remember { GameEngine() }
    val coroutineScope = rememberCoroutineScope()

    val initialBoardState = remember { mutableStateOf<BoardState?>(null) }
    val initialTurn = remember { mutableStateOf(PieceColor.WHITE) }

    fun loadPuzzle() {
        val (state, activeColor) = NotationParser.fenToBoardState(puzzle.fen)
        boardState = state
        currentTurn = activeColor
        initialBoardState.value = state
        initialTurn.value = activeColor
        solutionIndex = 0
        selectedSquare = null
        legalMoves = emptyList()
        lastMove = null
        elapsedTime = 0
        dialogState = PuzzleDialogState.START

        history.clear()
        redoStack.clear()
        history.add(PuzzleHistoryStep(state, activeColor, null, 0))

        coachBubbleVisible = false
        voiceManager.stop()
    }

    LaunchedEffect(puzzle) {
        loadPuzzle()
    }

    // Undo action
    val canUndo = history.size > 1 && dialogState != PuzzleDialogState.WON
    fun undoMove() {
        if (canUndo) {
            val popped = history.removeAt(history.lastIndex)
            redoStack.add(popped)
            val prev = history.last()
            boardState = prev.boardState
            currentTurn = prev.currentTurn
            lastMove = prev.lastMove
            solutionIndex = prev.solutionIndex
            selectedSquare = null
            legalMoves = emptyList()
            hintMove = null
            coachBubbleVisible = false
            voiceManager.stop()
            if (dialogState == PuzzleDialogState.LOST) {
                dialogState = PuzzleDialogState.PLAYING
            }
        }
    }

    // Redo action
    val canRedo = redoStack.isNotEmpty() && dialogState != PuzzleDialogState.WON
    fun redoMove() {
        if (canRedo) {
            val nextStep = redoStack.removeAt(redoStack.lastIndex)
            history.add(nextStep)
            boardState = nextStep.boardState
            currentTurn = nextStep.currentTurn
            lastMove = nextStep.lastMove
            solutionIndex = nextStep.solutionIndex
            selectedSquare = null
            legalMoves = emptyList()
            hintMove = null
            coachBubbleVisible = false
            voiceManager.stop()
        }
    }

    val handlePuzzleWon = {
        dialogState = PuzzleDialogState.WON
        if (isCustomPuzzle) {
            val category = (PuzzleRepository.currentTacticalCategory ?: puzzle.theme.substringBefore(" -")).trim()
            val allInCategory = PuzzleRepository.getPuzzlesByCategory(category)
            val index = allInCategory.indexOfFirst { it.id == puzzle.id }
            val currentLevelNum = if (index >= 0) index + 1 else 1
            com.chessmaster.play.data.LocalLeaderboardManager.unlockNextThemeLevel(context, category, currentLevelNum)
        } else {
            com.chessmaster.play.data.LocalLeaderboardManager.unlockNextPuzzleLevel(context, level)
        }
    }

    if (boardState == null) return

    val totalXp = remember(level) {
        com.chessmaster.play.data.LocalLeaderboardManager.getTotalXp(context)
    }
    val formattedXp = remember(totalXp) {
        NumberFormat.getNumberInstance(Locale.US).format(totalXp)
    }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF141414))) {
        // Exit Confirmation Dialog
        if (showExitDialog) {
            AlertDialog(
                onDismissRequest = { showExitDialog = false },
                title = { Text("Exit Puzzle?", color = Color.White, fontWeight = FontWeight.Bold) },
                text = { Text("Do you want to exit the puzzle? Your current progress will be lost.", color = Color.White.copy(alpha = 0.8f)) },
                confirmButton = {
                    TextButton(onClick = {
                        showExitDialog = false
                        voiceManager.stop()
                        if (activity != null) {
                            interstitialAdManager.showAd(activity) {
                                PuzzleRepository.activePlayPuzzle = null
                                onBack()
                            }
                        } else {
                            PuzzleRepository.activePlayPuzzle = null
                            onBack()
                        }
                    }) {
                        Text("Yes", color = Color(0xFFF44336), fontWeight = FontWeight.Bold)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showExitDialog = false }) {
                        Text("No", color = Color(0xFF4CAF50), fontWeight = FontWeight.Bold)
                    }
                },
                containerColor = Color(0xFF242424)
            )
        }

        Column(modifier = Modifier.fillMaxSize()) {
            val categoryName = if (isCustomPuzzle) (PuzzleRepository.currentTacticalCategory ?: puzzle.theme.substringBefore(" -")).trim() else ""
            val displayLevel = if (isCustomPuzzle) {
                val categoryPuzzles = PuzzleRepository.getPuzzlesByCategory(categoryName)
                val idx = categoryPuzzles.indexOfFirst { it.id == puzzle.id }
                if (idx >= 0) idx + 1 else 1
            } else {
                level
            }

            // Top Bar matching reference screenshot
            PuzzleTopBar(
                level = displayLevel,
                title = if (isCustomPuzzle) categoryName else "Puzzles",
                onBack = handleBack,
                onRestart = {
                    loadPuzzle()
                    dialogState = PuzzleDialogState.PLAYING
                }
            )

            // Coach Speech Bubble above the board (when Hint or Wrong Move is triggered)
            AnimatedVisibility(
                visible = coachBubbleVisible,
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut()
            ) {
                CoachSpeechBubble(
                    title = coachBubbleTitle,
                    message = coachBubbleMessage,
                    isWrongMove = coachBubbleIsError,
                    onDismiss = {
                        coachBubbleVisible = false
                        voiceManager.stop()
                    }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // The Chess Board
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .aspectRatio(1f)
            ) {
                ChessBoard(
                    boardState = boardState!!,
                    selectedSquare = selectedSquare,
                    legalMoves = legalMoves,
                    lastMove = lastMove,
                    hintMove = hintMove,
                    isCheck = engine.isKingInCheck(boardState!!, currentTurn),
                    currentTurn = currentTurn,
                    onSquareClicked = { square ->
                        if (dialogState != PuzzleDialogState.PLAYING && dialogState != PuzzleDialogState.LOST) return@ChessBoard

                        hintMove = null

                        val piece = boardState!!.getPiece(square)
                        if (selectedSquare == null) {
                            if (piece != null && piece.color == currentTurn) {
                                selectedSquare = square
                                legalMoves = engine.getLegalMoves(boardState!!, square)
                            }
                        } else {
                            val expectedMoveStr = puzzle.solutionMoves.getOrNull(solutionIndex)
                            // Find the move that matches the expected promotion if any, otherwise default to Queen
                            val move = legalMoves.find {
                                val promoChar = when (it.promotionTo) {
                                    PieceType.QUEEN -> "q"
                                    PieceType.ROOK -> "r"
                                    PieceType.BISHOP -> "b"
                                    PieceType.KNIGHT -> "n"
                                    else -> ""
                                }
                                val attempt = "${it.from}${it.to}$promoChar"
                                it.to == square && attempt == expectedMoveStr
                            } ?: legalMoves.find { 
                                it.to == square && (it.promotionTo == null || it.promotionTo == PieceType.QUEEN) 
                            } ?: legalMoves.find { it.to == square }

                            if (move != null) {
                                val promoChar = when (move.promotionTo) {
                                    PieceType.QUEEN -> "q"
                                    PieceType.ROOK -> "r"
                                    PieceType.BISHOP -> "b"
                                    PieceType.KNIGHT -> "n"
                                    else -> ""
                                }
                                val attemptedMoveStr = "${move.from}${move.to}$promoChar"

                                // If expectedMoveStr is 5 chars (promotion) and attempted matches it, or if it matches by start, we accept it.
                                if (expectedMoveStr == attemptedMoveStr || (expectedMoveStr != null && expectedMoveStr.startsWith("${move.from}${move.to}"))) {
                                    // Correct Move!
                                    coachBubbleVisible = false
                                    voiceManager.stop()

                                    val nextState = boardState!!.copyWithMove(move)
                                    boardState = nextState
                                    lastMove = move
                                    currentTurn = currentTurn.opposite()
                                    selectedSquare = null
                                    legalMoves = emptyList()
                                    val nextSolutionIndex = solutionIndex + 1
                                    solutionIndex = nextSolutionIndex

                                    // Push to history, clear redo
                                    redoStack.clear()
                                    history.add(PuzzleHistoryStep(nextState, currentTurn, move, nextSolutionIndex))

                                    if (nextSolutionIndex >= puzzle.solutionMoves.size) {
                                        handlePuzzleWon()
                                    } else {
                                        // Opponent Counter Move
                                        val oppMoveStr = puzzle.solutionMoves[nextSolutionIndex]
                                        val fromSq = Square(oppMoveStr[0] - 'a', oppMoveStr[1] - '1')
                                        val toSq = Square(oppMoveStr[2] - 'a', oppMoveStr[3] - '1')

                                        coroutineScope.launch {
                                            delay(500)
                                            val oppMoves = engine.getLegalMoves(boardState!!, fromSq)
                                            val actualOppMove = oppMoves.find { it.to == toSq }
                                            if (actualOppMove != null) {
                                                val stateAfterOpp = boardState!!.copyWithMove(actualOppMove)
                                                boardState = stateAfterOpp
                                                lastMove = actualOppMove
                                                currentTurn = currentTurn.opposite()
                                                val finalSolutionIndex = nextSolutionIndex + 1
                                                solutionIndex = finalSolutionIndex

                                                history.add(PuzzleHistoryStep(stateAfterOpp, currentTurn, actualOppMove, finalSolutionIndex))

                                                if (finalSolutionIndex >= puzzle.solutionMoves.size) {
                                                    handlePuzzleWon()
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    // Wrong Move!
                                    val nextState = boardState!!.copyWithMove(move)
                                    boardState = nextState
                                    lastMove = move
                                    selectedSquare = null
                                    legalMoves = emptyList()

                                    redoStack.clear()
                                    history.add(PuzzleHistoryStep(nextState, currentTurn, move, solutionIndex))

                                    // Coach voice & speech bubble reaction
                                    coachBubbleTitle = "Try Again"
                                    coachBubbleMessage = "That's not the right move. Use Back to undo and find a stronger move!"
                                    coachBubbleIsError = true
                                    coachBubbleVisible = true

                                    // Female coach speaks the wrong move advice out loud!
                                    voiceManager.speakWrongMove()

                                    coroutineScope.launch {
                                        delay(1200)
                                        if (dialogState == PuzzleDialogState.PLAYING) {
                                            dialogState = PuzzleDialogState.LOST
                                        }
                                    }
                                }
                            } else if (piece != null && piece.color == currentTurn) {
                                selectedSquare = square
                                legalMoves = engine.getLegalMoves(boardState!!, square)
                            } else {
                                selectedSquare = null
                                legalMoves = emptyList()
                            }
                        }
                    }
                )

                if (dialogState == PuzzleDialogState.START || dialogState == PuzzleDialogState.WON) {
                    Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.5f)))
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Score & Progress Bar matching reference screenshot
            PuzzleScoreProgressSection(
                scoreText = formattedXp,
                ratingText = "Rating ${puzzle.rating}",
                level = displayLevel
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Bottom Action Bar: Move/Hint, Back (Undo), Forward (Redo)
            PuzzleBottomBar(
                remainingHints = remainingHints,
                canUndo = canUndo,
                canRedo = canRedo,
                onHint = {
                    if (remainingHints > 0) {
                        val expectedMoveStr = puzzle.solutionMoves.getOrNull(solutionIndex)
                        if (expectedMoveStr != null) {
                            val fromSq = Square(expectedMoveStr[0] - 'a', expectedMoveStr[1] - '1')
                            val toSq = Square(expectedMoveStr[2] - 'a', expectedMoveStr[3] - '1')
                            selectedSquare = fromSq
                            legalMoves = engine.getLegalMoves(boardState!!, fromSq)
                            val pieceToMove = boardState!!.getPiece(fromSq)
                            if (pieceToMove != null) {
                                hintMove = Move(pieceToMove, fromSq, toSq)
                                coroutineScope.launch {
                                    delay(2000)
                                    hintMove = null
                                }
                            }
                            remainingHints--
                            com.chessmaster.play.data.LocalLeaderboardManager.setRemainingHints(context, remainingHints)

                            // Generate and display dynamic instructional hint
                            val hintText = PuzzleHintGenerator.generateHint(puzzle, expectedMoveStr, boardState!!)
                            coachBubbleTitle = "Hint"
                            coachBubbleMessage = hintText
                            coachBubbleIsError = false
                            coachBubbleVisible = true

                            // Female Coach speaks the hint!
                            voiceManager.speakHint(hintText)
                        }
                    } else {
                        showShopDialog = true
                    }
                },
                onUndo = { undoMove() },
                onRedo = { redoMove() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            )
        }

        // Start Puzzle Dialog
        if (dialogState == PuzzleDialogState.START) {
            val categoryName = if (isCustomPuzzle) (PuzzleRepository.currentTacticalCategory ?: puzzle.theme.substringBefore(" -")).trim() else ""
            val displayLevel = if (isCustomPuzzle) {
                val categoryPuzzles = PuzzleRepository.getPuzzlesByCategory(categoryName)
                val idx = categoryPuzzles.indexOfFirst { it.id == puzzle.id }
                if (idx >= 0) idx + 1 else 1
            } else {
                level
            }
            val title = if (isCustomPuzzle) "$categoryName #$displayLevel" else "LEVEL $displayLevel"
            StartDialog(
                title = title,
                color = initialTurn.value,
                rating = puzzle.rating,
                movesToWin = (puzzle.solutionMoves.size + 1) / 2,
                theme = puzzle.theme,
                onStart = { dialogState = PuzzleDialogState.PLAYING },
                onClose = { dialogState = PuzzleDialogState.PLAYING }
            )
        }

        // Won Puzzle Dialog
        if (dialogState == PuzzleDialogState.WON) {
            WonDialog(
                elapsedTime = elapsedTime,
                onNextLevel = {
                    val proceed = {
                        if (isCustomPuzzle) {
                            val category = (PuzzleRepository.currentTacticalCategory ?: puzzle.theme.substringBefore(" -")).trim()
                            val allInCategory = PuzzleRepository.getPuzzlesByCategory(category)
                            val index = allInCategory.indexOfFirst { it.id == puzzle.id }
                            if (index >= 0) {
                                val currentLevelNum = index + 1
                                com.chessmaster.play.data.LocalLeaderboardManager.unlockNextThemeLevel(context, category, currentLevelNum)
                            }

                            var nextPuzzle = PuzzleRepository.getNextPuzzleInCategory(puzzle)
                            if (nextPuzzle == null) {
                                nextPuzzle = PuzzleRepository.getFirstPuzzleOfNextCategory(category)
                            }

                            if (nextPuzzle != null) {
                                PuzzleRepository.activePlayPuzzle = nextPuzzle
                                puzzle = nextPuzzle
                                dialogState = PuzzleDialogState.PLAYING
                            } else {
                                PuzzleRepository.activePlayPuzzle = null
                                onBack()
                            }
                        } else {
                            com.chessmaster.play.data.LocalLeaderboardManager.unlockNextPuzzleLevel(context, level)
                            level++
                            PuzzleRepository.currentPuzzleLevel = level
                            puzzle = PuzzleRepository.getPuzzleForLevel(level)
                            dialogState = PuzzleDialogState.PLAYING
                        }
                    }
                    if (activity != null && level % 2 == 0 && !isCustomPuzzle) {
                        interstitialAdManager.showAd(activity) { proceed() }
                    } else {
                        proceed()
                    }
                },
                onRestart = {
                    val restartAction = {
                        loadPuzzle()
                        dialogState = PuzzleDialogState.PLAYING
                    }
                    if (activity != null) {
                        interstitialAdManager.showAd(activity) { restartAction() }
                    } else {
                        restartAction()
                    }
                }
            )
        }

        // Lost Puzzle Dialog (Can try again with Undo)
        if (dialogState == PuzzleDialogState.LOST) {
            LostDialog(
                onTryAgain = {
                    val tryAgainAction = {
                        undoMove()
                        dialogState = PuzzleDialogState.PLAYING
                    }
                    if (activity != null) {
                        interstitialAdManager.showAd(activity) { tryAgainAction() }
                    } else {
                        tryAgainAction()
                    }
                }
            )
        }

        if (showShopDialog) {
            com.chessmaster.play.ui.screens.ShopDialog(
                adManager = adManager,
                onDismiss = { showShopDialog = false },
                onHintsEarned = { amount ->
                    remainingHints += amount
                    com.chessmaster.play.data.LocalLeaderboardManager.setRemainingHints(context, remainingHints)
                    showShopDialog = false
                }
            )
        }
    }
}

/**
 * Top bar matching the reference screenshot:
 * - Back button
 * - Stepping stone level badge + "Puzzles"
 * - Settings / restart action icon
 */
@Composable
fun PuzzleTopBar(
    level: Int,
    title: String,
    onBack: () -> Unit,
    onRestart: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }

        // Stepping Stone Badge + Title
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF2E3D30))
                    .border(1.5.dp, Color(0xFFFFD700), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$level",
                    color = Color(0xFFFFD700),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Black
                )
            }

            Text(
                text = title,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
        }

        IconButton(onClick = onRestart) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Restart",
                tint = Color.White,
                modifier = Modifier.size(26.dp)
            )
        }
    }
}

/**
 * Score & Progress bar section matching the reference screenshot:
 * Score on left ("14,031"), rating on right, progress bar with milestone stone.
 */
@Composable
fun PuzzleScoreProgressSection(
    scoreText: String,
    ratingText: String,
    level: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = scoreText,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Black
            )
            Text(
                text = ratingText,
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Progress bar with milestone stone
        val progress = ((level % 25).toFloat() / 25f).coerceIn(0.05f, 1f)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF2A2A2A))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFF616161))
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Milestone Badge at end of bar
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF333333))
                    .border(1.dp, Color(0xFF777777), RoundedCornerShape(6.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$level",
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

/**
 * Bottom action bar with Move/Hint, Back (Undo), and Forward (Redo)
 * matching the layout and icons of the reference screenshot.
 */
@Composable
fun PuzzleBottomBar(
    remainingHints: Int,
    canUndo: Boolean,
    canRedo: Boolean,
    onHint: () -> Unit,
    onUndo: () -> Unit,
    onRedo: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Move / Hint Action
        PuzzleActionButton(
            icon = Icons.Default.Lightbulb,
            label = "Hint",
            badgeCount = remainingHints,
            enabled = true,
            onClick = onHint
        )

        // Back / Undo Action
        PuzzleActionButton(
            icon = Icons.Default.ChevronLeft,
            label = "Back",
            enabled = canUndo,
            onClick = onUndo
        )

        // Forward / Redo Action
        PuzzleActionButton(
            icon = Icons.Default.ChevronRight,
            label = "Forward",
            enabled = canRedo,
            onClick = onRedo
        )
    }
}

@Composable
fun PuzzleActionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    badgeCount: Int = 0,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val alpha = if (enabled) 1.0f else 0.35f

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(
                enabled = enabled,
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier.size(38.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color.White.copy(alpha = alpha),
                modifier = Modifier.size(30.dp)
            )

            if (badgeCount > 0) {
                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .align(Alignment.TopEnd)
                        .background(Color(0xFFE53935), CircleShape)
                        .border(1.dp, Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$badgeCount",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            color = Color.White.copy(alpha = alpha),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun CircularBottomButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    badgeCount: Int = 0,
    onClick: () -> Unit
) {
    PuzzleActionButton(
        icon = icon,
        label = label,
        badgeCount = badgeCount,
        enabled = true,
        onClick = onClick
    )
}

@Composable
fun StartDialog(
    title: String,
    color: PieceColor,
    rating: Int,
    movesToWin: Int,
    theme: String,
    onStart: () -> Unit,
    onClose: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.75f)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(if (color == PieceColor.WHITE) Color.White else Color.Black)
                            .border(1.dp, Color.Gray, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${color.name} TO MOVE",
                        color = Color(0xFFFFD700),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Goal: Win in $movesToWin move${if (movesToWin > 1) "s" else ""}",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp
                )

                if (theme.isNotEmpty()) {
                    Text(
                        text = "Theme: $theme",
                        color = Color(0xFF81C784),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onStart,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF43A047)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(
                        text = "SOLVE PUZZLE",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun WonDialog(elapsedTime: Int, onNextLevel: () -> Unit, onRestart: () -> Unit) {
    val timeString = String.format(Locale.US, "%02d:%02d", elapsedTime / 60, elapsedTime % 60)
    ResultDialog(
        title = "🎉 Puzzle Solved!",
        titleColor = Color(0xFFFFD700),
        subtitle = "⏱ Time Taken: $timeString",
        primaryButtonText = "▶ NEXT PUZZLE",
        primaryButtonColor = Color(0xFF00C853),
        onPrimaryAction = onNextLevel,
        secondaryButtonText = "⟳ PLAY AGAIN",
        secondaryButtonColor = Color(0xFF1976D2),
        onSecondaryAction = onRestart
    )
}

@Composable
fun LostDialog(onTryAgain: () -> Unit) {
    ResultDialog(
        title = "Wrong Move!",
        titleColor = Color(0xFFFF5252),
        subtitle = "That's not the right move.\nWant to try again?",
        primaryButtonText = "UNDO & TRY AGAIN",
        primaryButtonColor = Color(0xFF00C853),
        onPrimaryAction = onTryAgain
    )
}

@Composable
fun ResultDialog(
    title: String,
    titleColor: Color,
    subtitle: String,
    primaryButtonText: String,
    primaryButtonColor: Color,
    onPrimaryAction: () -> Unit,
    secondaryButtonText: String? = null,
    secondaryButtonColor: Color = Color.Gray,
    onSecondaryAction: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF141414)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            Text("♚♛", fontSize = 72.sp, color = Color.White)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = title,
                color = titleColor,
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = subtitle,
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(36.dp))

            Button(
                onClick = onPrimaryAction,
                colors = ButtonDefaults.buttonColors(containerColor = primaryButtonColor),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(50.dp)
            ) {
                Text(
                    text = primaryButtonText,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (secondaryButtonText != null && onSecondaryAction != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = onSecondaryAction,
                    colors = ButtonDefaults.buttonColors(containerColor = secondaryButtonColor),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(50.dp)
                ) {
                    Text(
                        text = secondaryButtonText,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
