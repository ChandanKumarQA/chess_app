package com.chessmaster.play.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import android.app.Activity
import com.chessmaster.play.RewardedAdManager
import com.chessmaster.play.InterstitialAdManager
enum class PuzzleDialogState {
    START, PLAYING, WON, LOST
}

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
    
    var boardState by remember { mutableStateOf<BoardState?>(null) }
    var currentTurn by remember { mutableStateOf(PieceColor.WHITE) }
    var selectedSquare by remember { mutableStateOf<Square?>(null) }
    var legalMoves by remember { mutableStateOf<List<Move>>(emptyList()) }
    var lastMove by remember { mutableStateOf<Move?>(null) }
    var solutionIndex by remember { mutableStateOf(0) }
    var dialogState by remember { mutableStateOf(PuzzleDialogState.START) }
    var remainingHints by remember { mutableIntStateOf(com.chessmaster.play.data.LocalLeaderboardManager.getRemainingHints(context)) }
    var hintMove by remember { mutableStateOf<Move?>(null) }
    var showShopDialog by remember { mutableStateOf(false) }
    var elapsedTime by remember { mutableIntStateOf(0) }
    
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
    }

    LaunchedEffect(puzzle) {
        loadPuzzle()
    }

    if (boardState == null) return

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        if (showExitDialog) {
            androidx.compose.material3.AlertDialog(
                onDismissRequest = { showExitDialog = false },
                title = { androidx.compose.material3.Text("Exit Puzzle?", color = Color.Black) },
                text = { androidx.compose.material3.Text("Do you want to exit the puzzle? Your progress will be lost.", color = Color.DarkGray) },
                confirmButton = {
                    androidx.compose.material3.TextButton(onClick = {
                        if (activity != null) {
                            interstitialAdManager.showAd(activity) {
                                showExitDialog = false
                                PuzzleRepository.activePlayPuzzle = null
                                onBack()
                            }
                        } else {
                            showExitDialog = false
                            PuzzleRepository.activePlayPuzzle = null
                            onBack()
                        }
                    }) {
                        androidx.compose.material3.Text("Yes", color = Color(0xFFF44336))
                    }
                },
                dismissButton = {
                    androidx.compose.material3.TextButton(onClick = { showExitDialog = false }) {
                        androidx.compose.material3.Text("No", color = Color(0xFF4CAF50))
                    }
                },
                containerColor = Color.White
            )
        }
        
        Column(modifier = Modifier.fillMaxSize()) {
            val displayLevel = if (isCustomPuzzle) {
                PuzzleRepository.getPuzzlesByCategory(puzzle.theme).indexOf(puzzle) + 1
            } else {
                level
            }
            val bannerTitle = if (isCustomPuzzle) "${puzzle.theme} - Level $displayLevel" else "LEVEL $displayLevel"
            val difficultyText = when {
                puzzle.rating <= 1000 -> "Easy"
                puzzle.rating <= 1650 -> "Moderate"
                else -> "Hard"
            }
            val difficultyColor = when (difficultyText) {
                "Easy" -> Color(0xFF00C853)
                "Moderate" -> Color(0xFFFFB300)
                else -> Color(0xFFE53935)
            }
            
            WoodenTopBanner(
                title = bannerTitle, 
                movesToWin = (puzzle.solutionMoves.size + 1) / 2, 
                difficulty = difficultyText,
                difficultyColor = difficultyColor,
                rating = puzzle.rating,
                onBack = handleBack
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
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
                        if (dialogState != PuzzleDialogState.PLAYING) return@ChessBoard
                        
                        hintMove = null
                        
                        val piece = boardState!!.getPiece(square)
                        if (selectedSquare == null) {
                            if (piece != null && piece.color == currentTurn) {
                                selectedSquare = square
                                legalMoves = engine.getLegalMoves(boardState!!, square)
                            }
                        } else {
                            val move = legalMoves.find { it.to == square }
                            if (move != null) {
                                val expectedMoveStr = puzzle.solutionMoves.getOrNull(solutionIndex)
                                val attemptedMoveStr = "${move.from}${move.to}"
                                
                                if (expectedMoveStr == attemptedMoveStr) {
                                    boardState = boardState!!.copyWithMove(move)
                                    lastMove = move
                                    currentTurn = currentTurn.opposite()
                                    selectedSquare = null
                                    legalMoves = emptyList()
                                    solutionIndex++
                                    
                                    if (solutionIndex >= puzzle.solutionMoves.size) {
                                        dialogState = PuzzleDialogState.WON
                                    } else {
                                        val oppMoveStr = puzzle.solutionMoves[solutionIndex]
                                        val fromSq = Square(oppMoveStr[0] - 'a', oppMoveStr[1] - '1')
                                        val toSq = Square(oppMoveStr[2] - 'a', oppMoveStr[3] - '1')
                                        
                                        coroutineScope.launch {
                                            delay(500)
                                            val oppMoves = engine.getLegalMoves(boardState!!, fromSq)
                                            val actualOppMove = oppMoves.find { it.to == toSq }
                                            if (actualOppMove != null) {
                                                boardState = boardState!!.copyWithMove(actualOppMove)
                                                lastMove = actualOppMove
                                                currentTurn = currentTurn.opposite()
                                                solutionIndex++
                                                
                                                if (solutionIndex >= puzzle.solutionMoves.size) {
                                                    dialogState = PuzzleDialogState.WON
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    boardState = boardState!!.copyWithMove(move)
                                    lastMove = move
                                    selectedSquare = null
                                    legalMoves = emptyList()
                                    coroutineScope.launch {
                                        delay(300)
                                        dialogState = PuzzleDialogState.LOST
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
                
                if (dialogState == PuzzleDialogState.START || dialogState == PuzzleDialogState.WON || dialogState == PuzzleDialogState.LOST) {
                    Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.5f)))
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            if (dialogState == PuzzleDialogState.PLAYING) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularBottomButton(
                        icon = Icons.Default.Refresh, 
                        label = "RESTART", 
                        onClick = { 
                            boardState = initialBoardState.value
                            currentTurn = initialTurn.value
                            solutionIndex = 0
                            lastMove = null
                            dialogState = PuzzleDialogState.PLAYING
                        }
                    )
                    
                    CircularBottomButton(
                        icon = Icons.Default.Lightbulb, 
                        label = "HINT", 
                        badgeCount = remainingHints,
                        onClick = { 
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
                                            kotlinx.coroutines.delay(1200)
                                            hintMove = null
                                        }
                                    }
                                    remainingHints--
                                    com.chessmaster.play.data.LocalLeaderboardManager.setRemainingHints(context, remainingHints)
                                }
                            } else {
                                showShopDialog = true
                            }
                        }
                    )
                }
            } else {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
        
        if (dialogState == PuzzleDialogState.START) {
            val displayLevel = if (isCustomPuzzle) {
                PuzzleRepository.getPuzzlesByCategory(puzzle.theme).indexOf(puzzle) + 1
            } else {
                level
            }
            val title = if (isCustomPuzzle) "LEVEL $displayLevel" else "LEVEL $displayLevel"
            StartDialog(
                title = title, 
                color = initialTurn.value, 
                movesToWin = (puzzle.solutionMoves.size + 1) / 2,
                onStart = { dialogState = PuzzleDialogState.PLAYING },
                onClose = { dialogState = PuzzleDialogState.PLAYING }
            )
        }
        
        if (showShopDialog) {
            ShopDialog(
                adManager = adManager,
                onDismiss = { showShopDialog = false },
                onHintsEarned = { amount -> 
                    remainingHints += amount
                    com.chessmaster.play.data.LocalLeaderboardManager.setRemainingHints(context, remainingHints)
                    showShopDialog = false
                }
            )
        }
        
        if (dialogState == PuzzleDialogState.WON) {
            WonDialog(
                elapsedTime = elapsedTime,
                onNextLevel = { 
                    val proceed = {
                        if (isCustomPuzzle) {
                            val category = puzzle.theme
                            val allInCategory = PuzzleRepository.getPuzzlesByCategory(category)
                            val index = allInCategory.indexOf(puzzle)
                            if (index >= 0) {
                                val currentLevelNum = index + 1
                                com.chessmaster.play.data.LocalLeaderboardManager.unlockNextThemeLevel(context, category, currentLevelNum)
                            }

                            var nextPuzzle = PuzzleRepository.getNextPuzzleInCategory(puzzle)
                            if (nextPuzzle == null) {
                                // Category completed, advance to next category
                                nextPuzzle = PuzzleRepository.getFirstPuzzleOfNextCategory(category)
                            }
                            
                            if (nextPuzzle != null) {
                                PuzzleRepository.activePlayPuzzle = nextPuzzle
                                puzzle = nextPuzzle
                            } else {
                                PuzzleRepository.activePlayPuzzle = null
                                onBack()
                            }
                        } else {
                            com.chessmaster.play.data.LocalLeaderboardManager.unlockNextPuzzleLevel(context, level)
                            level++
                            PuzzleRepository.currentPuzzleLevel = level
                            puzzle = PuzzleRepository.getPuzzleForLevel(level)
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
                        boardState = initialBoardState.value
                        currentTurn = initialTurn.value
                        solutionIndex = 0
                        lastMove = null
                        elapsedTime = 0
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
        
        if (dialogState == PuzzleDialogState.LOST) {
            LostDialog(
                onTryAgain = { 
                    val tryAgainAction = {
                        boardState = initialBoardState.value
                        currentTurn = initialTurn.value
                        solutionIndex = 0
                        lastMove = null
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
    }
}

@Composable
fun WoodenTopBanner(
    title: String, 
    movesToWin: Int, 
    difficulty: String = "",
    difficultyColor: Color = Color(0xFF00C853),
    rating: Int = 0,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(84.dp)
            .background(Color.Black)
    ) {
        IconButton(
            onClick = onBack,
            modifier = Modifier.align(Alignment.CenterStart).padding(start = 8.dp)
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack, 
                contentDescription = "Back", 
                tint = Color.White,
                modifier = Modifier.size(36.dp)
            )
        }
        
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                if (difficulty.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(difficultyColor.copy(alpha = 0.2f))
                            .border(1.dp, difficultyColor, RoundedCornerShape(6.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = difficulty.uppercase(),
                            color = difficultyColor,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Win in $movesToWin move${if(movesToWin > 1) "s" else ""}",
                    color = Color.White.copy(alpha = 0.85f),
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp
                )
                if (rating > 0) {
                    Text(
                        text = "• Rating $rating",
                        color = Color.White.copy(alpha = 0.6f),
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}

@Composable
fun CircularBottomButton(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, badgeCount: Int = 0, onClick: () -> Unit) {
    val buttonBg = Brush.verticalGradient(
        colors = listOf(Color(0xFFA0522D), Color(0xFF4E2411))
    )
    val gold = Color(0xFFFFD700)
    
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.size(74.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(buttonBg)
                    .border(2.dp, gold, CircleShape)
                    .clickable(onClick = onClick),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = label, tint = gold, modifier = Modifier.size(36.dp))
            }
            
            if (badgeCount > 0) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.TopEnd)
                        .background(Color.Red, CircleShape)
                        .border(1.dp, Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(badgeCount.toString(), color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(label, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
    }
}

@Composable
fun StartDialog(title: String, color: PieceColor, movesToWin: Int, onStart: () -> Unit, onClose: () -> Unit) {
    val woodGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF8B4513), Color(0xFF5D2906))
    )
    val gold = Color(0xFFFFD700)
    
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .width(300.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(woodGradient)
                .border(3.dp, gold, RoundedCornerShape(16.dp))
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.fillMaxWidth().background(Color(0xFF3E1C00)).padding(vertical = 12.dp)) {
                    Text(title, color = gold, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Center))
                    Icon(
                        Icons.Default.Close, 
                        contentDescription = "Close", 
                        tint = Color.Gray, 
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 12.dp)
                            .clickable { onClose() }
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text("You are ${color.name.lowercase()}", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                Text("Win in $movesToWin move${if(movesToWin > 1) "s" else ""}", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                
                Spacer(modifier = Modifier.height(32.dp))
                
                Button(
                    onClick = onStart,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00C853)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.width(180.dp).height(50.dp).border(2.dp, Color(0xFFB2FF59), RoundedCornerShape(8.dp))
                ) {
                    Text("START", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                }
            }
        }
    }
}

@Composable
fun WonDialog(elapsedTime: Int, onNextLevel: () -> Unit, onRestart: () -> Unit) {
    val timeString = String.format("%02d:%02d", elapsedTime / 60, elapsedTime % 60)
    ResultDialog(
        title = "\uD83C\uDF89 Puzzle Solved!",
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
        title = "Wrong move!",
        titleColor = Color(0xFFFF5252),
        subtitle = "No worries. You will do better next\ntime.\nWant to try again?",
        primaryButtonText = "TRY AGAIN",
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
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF1E1E1E)), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(24.dp)) {
            Text("♚♛", fontSize = 80.sp, color = Color.White)
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(title, color = titleColor, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(subtitle, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium, textAlign = TextAlign.Center)
            
            Spacer(modifier = Modifier.height(48.dp))
            
            Button(
                onClick = onPrimaryAction,
                colors = ButtonDefaults.buttonColors(containerColor = primaryButtonColor),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(0.8f).height(50.dp)
            ) {
                Text(primaryButtonText, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            
            if (secondaryButtonText != null && onSecondaryAction != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onSecondaryAction,
                    colors = ButtonDefaults.buttonColors(containerColor = secondaryButtonColor),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth(0.8f).height(50.dp)
                ) {
                    Text(secondaryButtonText, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
