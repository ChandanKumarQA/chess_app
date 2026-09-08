package com.chessmaster.play.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.border
import androidx.compose.foundation.verticalScroll
import com.chessmaster.play.RewardedAdManager
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.ChevronRight
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
import com.chessmaster.play.data.LocalLeaderboardManager
import com.chessmaster.play.data.PuzzleRepository
import com.chessmaster.play.engine.NotationParser
import com.chessmaster.play.engine.GameEngine
import com.chessmaster.play.model.OpeningTrap
import com.chessmaster.play.model.Square
import com.chessmaster.play.model.Move
import com.chessmaster.play.model.PieceColor
import com.chessmaster.play.ui.components.ChessBoard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpeningTrapsScreen(onBack: () -> Unit) {
    var activeTrap by remember { mutableStateOf<OpeningTrap?>(null) }
    
    val darkBg = MaterialTheme.colorScheme.background
    val cardBg = MaterialTheme.colorScheme.surfaceVariant
    val textPrimary = MaterialTheme.colorScheme.onBackground
    val textSecondary = MaterialTheme.colorScheme.onSurfaceVariant
    val accentColor = Color(0xFFFF9800)

    Scaffold(containerColor = darkBg) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    if (activeTrap != null) {
                        activeTrap = null
                    } else {
                        onBack()
                    }
                }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = androidx.compose.ui.graphics.Color.White)
                }
                Text(
                    text = activeTrap?.name ?: "Opening Traps",
                    color = textPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            if (activeTrap != null) {
                val traps = PuzzleRepository.getAllOpeningTraps()
                val activeIndex = traps.indexOf(activeTrap)
                androidx.compose.runtime.key(activeTrap) {
                    TrapViewer(
                        trap = activeTrap!!,
                        hasNext = activeIndex < traps.size - 1,
                        onNext = {
                            if (activeIndex < traps.size - 1) {
                                activeTrap = traps[activeIndex + 1]
                            }
                        },
                        onComplete = { activeTrap = null },
                        darkBg = darkBg,
                        cardBg = cardBg,
                        textPrimary = textPrimary,
                        textSecondary = textSecondary,
                        accentColor = accentColor
                    )
                }
            } else {
                val traps = PuzzleRepository.getAllOpeningTraps()
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(traps) { trap ->
                        TrapItem(
                            trap = trap,
                            cardBg = cardBg,
                            textPrimary = textPrimary,
                            textSecondary = textSecondary,
                            accentColor = accentColor,
                            onClick = { activeTrap = trap }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TrapItem(
    trap: OpeningTrap,
    cardBg: Color,
    textPrimary: Color,
    textSecondary: Color,
    accentColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = cardBg)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(accentColor.copy(alpha = 0.2f), RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.AutoStories, contentDescription = null, tint = accentColor)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(trap.name, color = textPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(trap.description.take(35) + "...", color = textSecondary, fontSize = 14.sp)
            }
            Icon(Icons.Default.ChevronRight, contentDescription = "Go", tint = textSecondary)
        }
    }
}

@Composable
fun TrapViewer(
    trap: OpeningTrap,
    hasNext: Boolean,
    onNext: () -> Unit,
    onComplete: () -> Unit,
    darkBg: Color,
    cardBg: Color,
    textPrimary: Color,
    textSecondary: Color,
    accentColor: Color
) {
    val context = LocalContext.current
    var currentMoveIndex by remember { mutableStateOf(0) }
    var showMistake by remember { mutableStateOf(false) }
    var isFinished by remember { mutableStateOf(false) }
    var showHint by remember { mutableStateOf(false) }
    var showWrongMoveDialog by remember { mutableStateOf(false) }
    var showShopDialog by remember { mutableStateOf(false) }
    var showSolvedPopup by remember { mutableStateOf(false) }
    var remainingHints by remember { mutableIntStateOf(com.chessmaster.play.data.LocalLeaderboardManager.getRemainingHints(context)) }
    
    val adManager = remember { RewardedAdManager(context) }
    LaunchedEffect(Unit) {
        adManager.loadAd()
    }
    
    var selectedSquare by remember { mutableStateOf<Square?>(null) }
    var legalMoves by remember { mutableStateOf<List<Move>>(emptyList()) }
    val engine = remember { GameEngine() }
    val coroutineScope = rememberCoroutineScope()
    
    val playerColor = remember(trap) {
        if (trap.moveSequence.size % 2 == 1) PieceColor.WHITE else PieceColor.BLACK
    }
    
    val initialFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
    val boardData = remember(currentMoveIndex, trap) {
        var state = NotationParser.fenToBoardState(initialFen).first
        var turn = PieceColor.WHITE
        var lastMove: Move? = null
        for (i in 0 until currentMoveIndex) {
            if (i < trap.moveSequence.size) {
                val moveStr = trap.moveSequence[i]
                if (moveStr.length >= 4) {
                    val fromSq = Square(moveStr[0] - 'a', moveStr[1] - '1')
                    val toSq = Square(moveStr[2] - 'a', moveStr[3] - '1')
                    val piece = state.getPiece(fromSq)
                    if (piece != null) {
                        val move = Move(piece, fromSq, toSq)
                        state = state.copyWithMove(move)
                        lastMove = move
                        turn = turn.opposite()
                    }
                }
            }
        }
        Triple(state, turn, lastMove)
    }
    val (boardState, currentTurn, lastMove) = boardData

    LaunchedEffect(currentMoveIndex, currentTurn, playerColor, isFinished) {
        if (!isFinished && currentTurn != playerColor) {
            val expectedStr = trap.moveSequence.getOrNull(currentMoveIndex)
            if (expectedStr != null) {
                kotlinx.coroutines.delay(500)
                currentMoveIndex++
                showHint = false
                if (currentMoveIndex == trap.commonMistakeIndex) {
                    showMistake = true
                }
                if (currentMoveIndex >= trap.moveSequence.size) {
                    isFinished = true
                    showSolvedPopup = true
                    LocalLeaderboardManager.addRewards(context, 25, 50)
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(androidx.compose.foundation.rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = cardBg)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Introduction", color = accentColor, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(trap.description, color = textPrimary)
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))

        val currentHintMove = remember(showHint, currentMoveIndex, trap, boardState) {
            if (showHint) {
                val expectedStr = trap.moveSequence.getOrNull(currentMoveIndex)
                if (expectedStr != null && expectedStr.length >= 4) {
                    val fromSq = Square(expectedStr[0] - 'a', expectedStr[1] - '1')
                    val toSq = Square(expectedStr[2] - 'a', expectedStr[3] - '1')
                    val piece = boardState.getPiece(fromSq)
                    if (piece != null) {
                        Move(piece, fromSq, toSq)
                    } else null
                } else null
            } else null
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            ChessBoard(
                boardState = boardState,
                selectedSquare = selectedSquare,
                legalMoves = legalMoves,
                lastMove = lastMove,
                isCheck = false,
                currentTurn = currentTurn,
                hintMove = currentHintMove,
                onSquareClicked = { square ->
                    if (isFinished) return@ChessBoard
                    if (currentTurn != playerColor) return@ChessBoard
                    
                    val piece = boardState.getPiece(square)
                    if (selectedSquare == null) {
                        if (piece != null && piece.color == currentTurn) {
                            selectedSquare = square
                            legalMoves = engine.getLegalMoves(boardState, square)
                        }
                    } else {
                        val move = legalMoves.find { it.to == square }
                        if (move != null) {
                            val expectedStr = trap.moveSequence.getOrNull(currentMoveIndex)
                            val attemptedStr = "${move.from}${move.to}"
                            if (expectedStr == attemptedStr) {
                                currentMoveIndex++
                                showHint = false
                                if (currentMoveIndex == trap.commonMistakeIndex) {
                                    showMistake = true
                                }
                                if (currentMoveIndex >= trap.moveSequence.size) {
                                    isFinished = true
                                    showSolvedPopup = true
                                    LocalLeaderboardManager.addRewards(context, 25, 50)
                                }
                            } else {
                                showWrongMoveDialog = true
                            }
                        }
                        selectedSquare = null
                        legalMoves = emptyList()
                    }
                }
            )
        }
        
        if (currentMoveIndex > 0) {
            val moveText = trap.moveSequence.getOrNull(currentMoveIndex - 1)
            if (moveText != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text("Move ${currentMoveIndex}: $moveText", color = textPrimary, fontWeight = FontWeight.Medium)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        if (isFinished) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF2E7D32).copy(alpha = 0.2f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Trap Completed!", color = Color(0xFF4CAF50), fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("+50 XP | +25 Coins", color = textPrimary)
                }
            }
        } else {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = cardBg)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    if (showMistake) {
                        Text("Common Mistake", color = Color(0xFFF44336), fontWeight = FontWeight.Bold)
                        Text(trap.explanation, color = textPrimary)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text("Best Defense", color = Color(0xFF4CAF50), fontWeight = FontWeight.Bold)
                        Text(trap.explanation.reversed().take(20) + "... (Placeholder)", color = textPrimary)
                    } else if (showHint) {
                        Text("Hint", color = accentColor, fontWeight = FontWeight.Bold)
                        val expectedStr = trap.moveSequence.getOrNull(currentMoveIndex)
                        Text("Try moving from ${expectedStr?.take(2)} to ${expectedStr?.takeLast(2)}", color = textPrimary)
                    } else {
                        Text("Play the correct trap moves!", color = accentColor, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

            } // closes inner Column (scrollable)

            Spacer(modifier = Modifier.height(16.dp))

            if (isFinished) {
                Button(
                    onClick = onComplete,
                    colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Back to Traps", color = MaterialTheme.colorScheme.onBackground)
                }
            } else {
                Button(
                    onClick = { 
                        if (remainingHints > 0) {
                            showHint = true 
                            remainingHints--
                            com.chessmaster.play.data.LocalLeaderboardManager.setRemainingHints(context, remainingHints)
                        } else {
                            showShopDialog = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Show Hint (${remainingHints})", color = MaterialTheme.colorScheme.onBackground)
                }
            }
        } // closes outer Column

    if (showWrongMoveDialog) {
        OpeningTrapLostDialog(
            onTryAgain = {
                showWrongMoveDialog = false
                currentMoveIndex = 0
                showHint = false
                showMistake = false
                isFinished = false
            }
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

    if (showSolvedPopup) {
        ResultDialog(
            title = "🎉 Trap Completed!",
            titleColor = Color(0xFFFFD700),
            subtitle = "+50 XP | +25 Coins",
            primaryButtonText = if (hasNext) "▶ NEXT TRAP" else "▶ MORE TRAPS",
            primaryButtonColor = Color(0xFF00C853),
            onPrimaryAction = {
                showSolvedPopup = false
                if (hasNext) {
                    onNext()
                } else {
                    onComplete()
                }
            },
            secondaryButtonText = "⟳ PLAY AGAIN",
            secondaryButtonColor = Color(0xFF1976D2),
            onSecondaryAction = {
                showSolvedPopup = false
                isFinished = false
                currentMoveIndex = 0
                showHint = false
                showMistake = false
            }
        )
    }
} // closes Box
} // closes TrapViewer

@Composable
fun OpeningTrapLostDialog(onTryAgain: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(androidx.compose.ui.graphics.Brush.verticalGradient(listOf(Color(0xFF004445), Color(0xFF002223)))),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            Text("♚♛", fontSize = 100.sp, color = Color(0xFFD4AF37))
            Spacer(modifier = Modifier.height(24.dp))
            Text("Wrong move!", color = Color(0xFFFF5252), fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "No worries. You will do better next\ntime.\nWant to try again?",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(modifier = Modifier.height(48.dp))
            Button(
                onClick = onTryAgain,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(androidx.compose.ui.graphics.Brush.verticalGradient(listOf(Color(0xFF56AB2F), Color(0xFFA8E063))))
                        .border(1.dp, Color(0xFF76FF03), androidx.compose.foundation.shape.RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("TRY AGAIN", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
