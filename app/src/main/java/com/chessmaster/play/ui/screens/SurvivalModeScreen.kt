package com.chessmaster.play.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.app.Activity
import com.chessmaster.play.InterstitialAdManager
import com.chessmaster.play.RewardedAdManager
import com.chessmaster.play.data.LocalLeaderboardManager
import com.chessmaster.play.data.PuzzleRepository
import com.chessmaster.play.engine.GameEngine
import com.chessmaster.play.engine.NotationParser
import com.chessmaster.play.model.BoardState
import com.chessmaster.play.model.Move
import com.chessmaster.play.model.PieceColor
import com.chessmaster.play.model.Square
import com.chessmaster.play.ui.components.ChessBoard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class SurvivalState { LEVEL_SELECTION, PLAYING, GAME_OVER }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurvivalModeScreen(onBack: () -> Unit) {
    var gameState by remember { mutableStateOf(SurvivalState.LEVEL_SELECTION) }
    var currentLevelIndex by remember { mutableIntStateOf(0) }

    val darkBg = MaterialTheme.colorScheme.background
    val context = LocalContext.current
    val activity = context as? Activity
    val interstitialAdManager = remember { InterstitialAdManager(context) }
    val highestUnlocked = com.chessmaster.play.data.LocalLeaderboardManager.getHighestUnlockedSurvivalLevel(context)
    val cardBg = MaterialTheme.colorScheme.surfaceVariant
    val textPrimary = MaterialTheme.colorScheme.onBackground
    val textSecondary = MaterialTheme.colorScheme.onSurfaceVariant
    val accentColor = Color(0xFFF44336) // Red for survival

    Scaffold(containerColor = darkBg) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            when (gameState) {
                SurvivalState.LEVEL_SELECTION -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = onBack) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = androidx.compose.ui.graphics.Color.White)
                            }
                            Text("Survival Mode", color = textPrimary, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 8.dp))
                        }
                        
                        LazyColumn(
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(10) { index ->
                                val levelNum = index + 1
                                val isLocked = levelNum > highestUnlocked
                                
                                CategoryItem(
                                    title = "Level $levelNum",
                                    difficulty = "Survival",
                                    puzzleCount = 1,
                                    completion = if (isLocked) 0 else 100,
                                    bestScore = 0,
                                    cardBg = cardBg,
                                    textPrimary = textPrimary,
                                    textSecondary = textSecondary,
                                    accentColor = accentColor,
                                    isLocked = isLocked,
                                    onClick = { 
                                        currentLevelIndex = index
                                        gameState = SurvivalState.PLAYING 
                                    }
                                )
                            }
                        }
                    }
                }
                SurvivalState.PLAYING -> SurvivalGame(
                    levelIndex = currentLevelIndex,
                    onGameOver = { gameState = SurvivalState.GAME_OVER },
                    onLevelComplete = {
                        val nextLevel = currentLevelIndex + 2
                        com.chessmaster.play.data.LocalLeaderboardManager.unlockNextSurvivalLevel(context, nextLevel - 1)
                        gameState = SurvivalState.LEVEL_SELECTION
                    },
                    onNextLevel = {
                        val nextLevel = currentLevelIndex + 2
                        com.chessmaster.play.data.LocalLeaderboardManager.unlockNextSurvivalLevel(context, nextLevel - 1)
                        if (currentLevelIndex < 9) {
                            currentLevelIndex++
                        } else {
                            gameState = SurvivalState.LEVEL_SELECTION
                        }
                    },
                    onBack = { gameState = SurvivalState.LEVEL_SELECTION },
                    activity = activity,
                    interstitialAdManager = interstitialAdManager
                )
                SurvivalState.GAME_OVER -> SurvivalGameOver(
                    onRetry = { gameState = SurvivalState.PLAYING },
                    onHome = { gameState = SurvivalState.LEVEL_SELECTION },
                    activity = activity,
                    interstitialAdManager = interstitialAdManager
                )
            }
        }
    }
}

@Composable
fun SurvivalGame(
    levelIndex: Int,
    onGameOver: () -> Unit,
    onLevelComplete: () -> Unit,
    onNextLevel: () -> Unit,
    onBack: () -> Unit,
    activity: Activity?,
    interstitialAdManager: InterstitialAdManager
) {
    val context = LocalContext.current
    var hearts by remember { mutableStateOf(3) }
    var score by remember { mutableStateOf(0) }
    var combo by remember { mutableStateOf(0) }
    var highestCombo by remember { mutableStateOf(0) }
    var showShopDialog by remember { mutableStateOf(false) }
    var remainingHints by remember { mutableIntStateOf(LocalLeaderboardManager.getRemainingHints(context)) }
    
    val adManager = remember { RewardedAdManager(context) }
    LaunchedEffect(Unit) {
        adManager.loadAd()
    }

    val puzzles = remember { PuzzleRepository.getSurvivalPuzzles() }
    
    var showSolvedPopup by remember { mutableStateOf(false) }
    var currentPuzzleTime by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            if (!showSolvedPopup) {
                currentPuzzleTime++
            }
        }
    }

    val currentPuzzle = puzzles.getOrNull(levelIndex)

    var boardState by remember { mutableStateOf<BoardState?>(null) }
    var currentTurn by remember { mutableStateOf(PieceColor.WHITE) }
    var solutionIndex by remember { mutableStateOf(0) }
    
    var selectedSquare by remember { mutableStateOf<Square?>(null) }
    var legalMoves by remember { mutableStateOf<List<Move>>(emptyList()) }
    var hintMove by remember { mutableStateOf<Move?>(null) }
    val engine = remember { GameEngine() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(currentPuzzle) {
        if (currentPuzzle != null) {
            val (initialState, activeColor) = NotationParser.fenToBoardState(currentPuzzle.fen)
            boardState = initialState
            currentTurn = activeColor
            solutionIndex = 0
            selectedSquare = null
            legalMoves = emptyList()
            currentPuzzleTime = 0
        } else {
            onGameOver()
        }
    }

    LaunchedEffect(hearts) {
        if (hearts <= 0) {
            LocalLeaderboardManager.saveSurvivalStats(context, score, highestCombo, 0)
            onGameOver()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (showShopDialog) {
        ShopDialog(
            adManager = adManager,
            onDismiss = { showShopDialog = false },
            onHintsEarned = { amount -> 
                remainingHints += amount
                LocalLeaderboardManager.setRemainingHints(context, remainingHints)
                showShopDialog = false
            }
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(), 
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = androidx.compose.ui.graphics.Color.White)
                }
                Text("Survival Mode", color = MaterialTheme.colorScheme.onBackground, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Row {
                for (i in 1..3) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = null,
                        tint = if (i <= hearts) Color(0xFFF44336) else Color.Gray,
                        modifier = Modifier.size(24.dp).padding(horizontal = 2.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (boardState != null) {
            Box(modifier = Modifier.fillMaxWidth().aspectRatio(1f).padding(8.dp)) {
                ChessBoard(
                    boardState = boardState!!,
                    selectedSquare = selectedSquare,
                    legalMoves = legalMoves,
                    lastMove = hintMove,
                    isCheck = false,
                    currentTurn = currentTurn,
                    onSquareClicked = { square ->
                        if (currentPuzzle == null || hearts <= 0) return@ChessBoard
                        if (solutionIndex >= currentPuzzle.solutionMoves.size) return@ChessBoard
                        
                        val piece = boardState!!.getPiece(square)
                        if (selectedSquare == null) {
                            if (piece != null && piece.color == currentTurn) {
                                selectedSquare = square
                                legalMoves = engine.getLegalMoves(boardState!!, square)
                            }
                        } else {
                            val move = legalMoves.find { it.to == square }
                            if (move != null) {
                                val expectedStr = currentPuzzle.solutionMoves.getOrNull(solutionIndex)
                                val attemptedStr = "${move.from}${move.to}"
                                
                                if (expectedStr == attemptedStr) {
                                    boardState = boardState!!.copyWithMove(move)
                                    currentTurn = currentTurn.opposite()
                                    solutionIndex++
                                    
                                    if (solutionIndex >= currentPuzzle.solutionMoves.size) {
                                        combo++
                                        if (combo > highestCombo) highestCombo = combo
                                        score += 20 + (combo * 5)
                                        showSolvedPopup = true
                                    } else {
                                        val oppStr = currentPuzzle.solutionMoves[solutionIndex]
                                        val fromSq = Square(oppStr[0] - 'a', oppStr[1] - '1')
                                        val toSq = Square(oppStr[2] - 'a', oppStr[3] - '1')
                                        coroutineScope.launch {
                                            delay(300)
                                            val oppMoves = engine.getLegalMoves(boardState!!, fromSq)
                                            val actualOpp = oppMoves.find { it.to == toSq }
                                            if (actualOpp != null) {
                                                boardState = boardState!!.copyWithMove(actualOpp)
                                                currentTurn = currentTurn.opposite()
                                                solutionIndex++
                                            }
                                        }
                                    }
                                } else {
                                    combo = 0
                                    hearts--
                                    Toast.makeText(context, "Wrong move!", Toast.LENGTH_SHORT).show()
                                    if (hearts <= 0) {
                                        LocalLeaderboardManager.saveSurvivalStats(context, score, highestCombo, levelIndex)
                                        LocalLeaderboardManager.addRewards(context, score / 2, score)
                                        onGameOver()
                                    }
                                }
                            }
                            selectedSquare = null
                            legalMoves = emptyList()
                        }
                    }
                )
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CircularBottomButton(
                    icon = Icons.Default.Lightbulb, 
                    label = "HINT", 
                    badgeCount = remainingHints,
                    onClick = {
                        if (remainingHints > 0) {
                            if (currentPuzzle != null && selectedSquare == null) {
                                val expectedStr = currentPuzzle.solutionMoves.getOrNull(solutionIndex)
                                if (expectedStr != null) {
                                    val fromSq = Square(expectedStr[0] - 'a', expectedStr[1] - '1')
                                    val toSq = Square(expectedStr[2] - 'a', expectedStr[3] - '1')
                                    val piece = boardState?.getPiece(fromSq) ?: com.chessmaster.play.model.Piece(com.chessmaster.play.model.PieceType.PAWN, currentTurn)
                                    val move = Move(piece, fromSq, toSq)
                                    hintMove = move
                                    coroutineScope.launch {
                                        delay(1200)
                                        hintMove = null
                                    }
                                }
                                remainingHints--
                                LocalLeaderboardManager.setRemainingHints(context, remainingHints)
                            }
                        } else {
                            showShopDialog = true
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
        }

        if (showSolvedPopup) {
            val timeString = String.format("%02d:%02d", currentPuzzleTime / 60, currentPuzzleTime % 60)
            ResultDialog(
                title = "🎉 Puzzle Solved!",
                titleColor = Color(0xFFFFD700),
                subtitle = "⏱ Time Taken: $timeString",
                primaryButtonText = "▶ NEXT PUZZLE",
                primaryButtonColor = Color(0xFF00C853),
                onPrimaryAction = {
                    showSolvedPopup = false
                    currentPuzzleTime = 0
                    onNextLevel()
                },
                secondaryButtonText = "⟳ PLAY AGAIN",
                secondaryButtonColor = Color(0xFF1976D2),
                onSecondaryAction = {
                    val retryAction = {
                        showSolvedPopup = false
                        currentPuzzleTime = 0
                        if (currentPuzzle != null) {
                            val (initialState, activeColor) = NotationParser.fenToBoardState(currentPuzzle!!.fen)
                            boardState = initialState
                            currentTurn = activeColor
                            solutionIndex = 0
                            selectedSquare = null
                            legalMoves = emptyList()
                        }
                    }
                    if (activity != null) {
                        interstitialAdManager.showAd(activity) { retryAction() }
                    } else {
                        retryAction()
                    }
                }
            )
        }
    }
}

@Composable
fun SurvivalGameOver(onRetry: () -> Unit, onHome: () -> Unit, activity: Activity?, interstitialAdManager: InterstitialAdManager) {
    val context = LocalContext.current
    val highScore = LocalLeaderboardManager.getSurvivalHighScore(context)

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(Icons.Default.FavoriteBorder, contentDescription = null, tint = Color(0xFFF44336), modifier = Modifier.size(64.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Text("Game Over", color = Color(0xFFF44336), fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(32.dp))
        
        Text("High Score: $highScore", color = MaterialTheme.colorScheme.onBackground, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (activity != null) {
                    interstitialAdManager.showAd(activity) { onRetry() }
                } else {
                    onRetry()
                }
            }, 
            modifier = Modifier.fillMaxWidth().height(50.dp), 
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text("Try Again")
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(onClick = onHome, modifier = Modifier.fillMaxWidth().height(50.dp)) {
            Text("Home", color = MaterialTheme.colorScheme.onBackground)
        }
    }
}
