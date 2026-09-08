package com.chessmaster.play.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Timer
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

enum class RushState { SELECTION, PLAYING, GAME_OVER }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PuzzleRushScreen(onBack: () -> Unit) {
    var rushState by remember { mutableStateOf(RushState.SELECTION) }
    var selectedTimeMinutes by remember { mutableStateOf(3) } // 0 for endless

    val darkBg = MaterialTheme.colorScheme.background
    val textPrimary = MaterialTheme.colorScheme.onBackground

    Scaffold(containerColor = darkBg) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            when (rushState) {
                RushState.SELECTION -> RushSelection(
                    onBack = onBack,
                    onStart = { minutes ->
                        selectedTimeMinutes = minutes
                        rushState = RushState.PLAYING
                    }
                )
                RushState.PLAYING -> RushGame(
                    minutes = selectedTimeMinutes,
                    onGameOver = { rushState = RushState.GAME_OVER },
                    onBack = { rushState = RushState.SELECTION }
                )
                RushState.GAME_OVER -> RushGameOver(
                    onRetry = { rushState = RushState.PLAYING },
                    onHome = onBack
                )
            }
        }
    }
}

@Composable
fun RushSelection(onBack: () -> Unit, onStart: (Int) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = androidx.compose.ui.graphics.Color.White)
            }
            Text("Puzzle Rush", color = MaterialTheme.colorScheme.onBackground, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(32.dp))
        Icon(Icons.Default.Timer, contentDescription = null, tint = Color(0xFFE91E63), modifier = Modifier.size(80.dp))
        Spacer(modifier = Modifier.height(32.dp))

        RushOptionBtn("3 Minutes", Color(0xFFE91E63)) { onStart(3) }
        Spacer(modifier = Modifier.height(16.dp))
        RushOptionBtn("5 Minutes", Color(0xFFF44336)) { onStart(5) }
        Spacer(modifier = Modifier.height(16.dp))
        RushOptionBtn("10 Minutes", Color(0xFFFF9800)) { onStart(10) }
        Spacer(modifier = Modifier.height(16.dp))
        RushOptionBtn("Endless", Color(0xFF9C27B0)) { onStart(0) }
    }
}

@Composable
fun RushOptionBtn(text: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        modifier = Modifier.fillMaxWidth(0.8f).height(56.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
    }
}

@Composable
fun RushGame(minutes: Int, onGameOver: () -> Unit, onBack: () -> Unit) {
    val context = LocalContext.current
    var timeLeft by remember { mutableStateOf(minutes * 60) }
    var score by remember { mutableStateOf(0) }
    var combo by remember { mutableStateOf(0) }
    var highestCombo by remember { mutableStateOf(0) }
    var puzzlesSolved by remember { mutableStateOf(0) }

    val puzzles = remember { PuzzleRepository.getRandomPuzzles(100) }
    var puzzleIndex by remember { mutableStateOf(0) }
    
    val currentPuzzle = puzzles.getOrNull(puzzleIndex)

    var boardState by remember { mutableStateOf<BoardState?>(null) }
    var currentTurn by remember { mutableStateOf(PieceColor.WHITE) }
    var solutionIndex by remember { mutableStateOf(0) }
    
    var selectedSquare by remember { mutableStateOf<Square?>(null) }
    var legalMoves by remember { mutableStateOf<List<Move>>(emptyList()) }
    val engine = remember { GameEngine() }
    val coroutineScope = rememberCoroutineScope()
    
    val adManager = remember { RewardedAdManager(context) }
    var remainingHints by remember { mutableStateOf(LocalLeaderboardManager.getRemainingHints(context)) }
    var showShopDialog by remember { mutableStateOf(false) }
    var hintMove by remember { mutableStateOf<Move?>(null) }
    
    LaunchedEffect(Unit) {
        adManager.loadAd()
    }

    var showSolvedPopup by remember { mutableStateOf(false) }
    var currentPuzzleTime by remember { mutableIntStateOf(0) }

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
            // End of puzzles fallback
            onGameOver()
        }
    }

    LaunchedEffect(minutes) {
        while (true) {
            delay(1000)
            if (!showSolvedPopup) {
                currentPuzzleTime++
                if (minutes > 0) {
                    if (timeLeft > 0) {
                        timeLeft--
                    } else {
                        // Save stats
                        LocalLeaderboardManager.savePuzzleRushStats(context, score, highestCombo, puzzlesSolved)
                        LocalLeaderboardManager.addRewards(context, score / 2, score)
                        onGameOver()
                        break
                    }
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(), 
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack, 
                        contentDescription = "Back",
                        tint = androidx.compose.ui.graphics.Color.White
                    )
                }
                if (minutes > 0) {
                    val min = timeLeft / 60
                    val sec = timeLeft % 60
                    Text(String.format("%02d:%02d", min, sec), color = Color(0xFFE91E63), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                } else {
                    Text("Endless", color = Color(0xFF9C27B0), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (boardState != null) {
                Box(modifier = Modifier.fillMaxWidth().aspectRatio(1f).padding(8.dp)) {
                    ChessBoard(
                        boardState = boardState!!,
                        selectedSquare = selectedSquare,
                        legalMoves = legalMoves,
                        lastMove = null,
                        isCheck = false,
                        currentTurn = currentTurn,
                        hintMove = hintMove,
                        onSquareClicked = { square ->
                            if (currentPuzzle == null) return@ChessBoard
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
                                        // Correct
                                        boardState = boardState!!.copyWithMove(move)
                                        currentTurn = currentTurn.opposite()
                                        solutionIndex++
                                        
                                        if (solutionIndex >= currentPuzzle.solutionMoves.size) {
                                            // Solved
                                            combo++
                                            if (combo > highestCombo) highestCombo = combo
                                            score += 10 + (combo * 2)
                                            puzzlesSolved++
                                            showSolvedPopup = true
                                        } else {
                                            // Next opponent move
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
                                        // Wrong
                                        combo = 0
                                        Toast.makeText(context, "Wrong move!", Toast.LENGTH_SHORT).show()
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
                                        remainingHints--
                                        LocalLeaderboardManager.setRemainingHints(context, remainingHints)
                                    }
                                }
                            } else {
                                showShopDialog = true
                            }
                        }
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
            }
            
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
            
            if (minutes == 0) {
                Button(
                    onClick = { 
                        LocalLeaderboardManager.savePuzzleRushStats(context, score, highestCombo, puzzlesSolved)
                        LocalLeaderboardManager.addRewards(context, score / 2, score)
                        onGameOver() 
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("End Game")
                }
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
                    puzzleIndex++
                },
                secondaryButtonText = "⟳ PLAY AGAIN",
                secondaryButtonColor = Color(0xFF1976D2),
                onSecondaryAction = {
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
            )
        }
    }
}
@Composable
fun RushGameOver(onRetry: () -> Unit, onHome: () -> Unit) {
    val context = LocalContext.current
    val highScore = LocalLeaderboardManager.getPuzzleRushHighScore(context)

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Time's Up!", color = Color(0xFFE91E63), fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(32.dp))
        
        Text("High Score: $highScore", color = MaterialTheme.colorScheme.onBackground, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onRetry, modifier = Modifier.fillMaxWidth().height(50.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))) {
            Text("Retry")
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(onClick = onHome, modifier = Modifier.fillMaxWidth().height(50.dp)) {
            Text("Home", color = MaterialTheme.colorScheme.onBackground)
        }
    }
}
