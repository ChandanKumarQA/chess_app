package com.chessmaster.play.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material.icons.filled.Redo
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.activity.compose.BackHandler
import android.app.Activity
import androidx.compose.ui.platform.LocalContext
import com.chessmaster.play.viewmodel.ChessViewModel
import com.chessmaster.play.ui.components.ChessBoard
import com.chessmaster.play.ui.components.BannerAd
import com.chessmaster.play.ui.components.CapturedPiecesPanel
import com.chessmaster.play.model.PieceColor
import com.chessmaster.play.model.TimeControl
import com.chessmaster.play.model.GameMode
import com.chessmaster.play.ui.components.OnlineControls
import androidx.compose.ui.graphics.Color
import androidx.compose.animation.core.*
import com.chessmaster.play.InterstitialAdManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: ChessViewModel, onBack: () -> Unit) {
    BackHandler {
        onBack()
    }
    
    val boardState by viewModel.boardState.collectAsState()
    val currentTurn by viewModel.currentTurn.collectAsState()
    val selectedSquare by viewModel.selectedSquare.collectAsState()
    val legalMoves by viewModel.legalMoves.collectAsState()
    val isCheck by viewModel.isCheck.collectAsState()
    val gameState by viewModel.gameState.collectAsState()
    
    val context = LocalContext.current
    val activity = context as? Activity
    val interstitialAdManager = remember { InterstitialAdManager(context) }
    val rewardedAdManager = remember { com.chessmaster.play.RewardedAdManager(context) }
    
    LaunchedEffect(Unit) {
        rewardedAdManager.loadAd()
    }
    
    val gameId by viewModel.gameId.collectAsState()
    val connectionStatus by viewModel.connectionStatus.collectAsState()
    val playerColor by viewModel.playerColor.collectAsState()
    val chessboardStyleIndex by viewModel.chessboardStyleIndex.collectAsState()
    val vibrationsEnabled by viewModel.vibrations.collectAsState()
    
    val lastMove by viewModel.lastMove.collectAsState()
    val soundsEnabled by viewModel.sounds.collectAsState()
    val view = androidx.compose.ui.platform.LocalView.current
    
    LaunchedEffect(lastMove) {
        if (lastMove != null && soundsEnabled) {
            val mediaPlayer = android.media.MediaPlayer.create(context, com.chessmaster.play.R.raw.chess_move)
            mediaPlayer?.setOnCompletionListener { it.release() }
            mediaPlayer?.start()
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            val isLandscape = maxWidth > maxHeight
            val whiteCaptured by viewModel.capturedWhitePieces.collectAsState()
            val blackCaptured by viewModel.capturedBlackPieces.collectAsState()
            val lastMove by viewModel.lastMove.collectAsState()
            val haptic = androidx.compose.ui.platform.LocalHapticFeedback.current
            val view = androidx.compose.ui.platform.LocalView.current
            
            val gameMode by viewModel.gameMode.collectAsState()
            val cpuLevel by viewModel.cpuLevel.collectAsState()
            val isThinking by viewModel.isThinking.collectAsState()
            val clock by viewModel.clock.collectAsState()
            
            var showTimeControlMenu by remember { mutableStateOf(false) }
            
            var remainingHints by remember { mutableIntStateOf(com.chessmaster.play.data.LocalLeaderboardManager.getRemainingHints(context)) }
            var showShopDialog by remember { mutableStateOf(false) }

            val topControls = @Composable {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    // Back button and Level
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = onBack,
                            modifier = Modifier
                                .background(Color(0xFFFFB300), shape = androidx.compose.foundation.shape.CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = androidx.compose.ui.graphics.Color.White
                            )
                        }
                        
                        Spacer(modifier = Modifier.weight(1f))
                        
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val category = when (cpuLevel) {
                                in 1..5 -> "EASY"
                                in 6..10 -> "MODERATE"
                                in 11..15 -> "HARD"
                                else -> "EXPERT"
                            }
                            Text(
                                text = "LEVEL $cpuLevel",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFFFFB300)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Surface(
                                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                                color = Color(0xFF4CAF50),
                                modifier = Modifier.padding(top = 2.dp)
                            ) {
                                Text(
                                    text = category,
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                                )
                            }
                        }
                        
                        Spacer(modifier = Modifier.weight(1f))
                        // Placeholder for symmetry
                        Box(modifier = Modifier.size(48.dp))
                    }

                    // Clocks and Captured Pieces
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.weight(1f).padding(end = 8.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                if (clock.timeControl != TimeControl.UNLIMITED) {
                                    ClockDisplay(timeMs = clock.whiteTimeMs, active = currentTurn == PieceColor.WHITE)
                                }
                                CapturedPiecesPanel(color = PieceColor.WHITE, pieces = whiteCaptured)
                            }
                        }
                        
                        Surface(
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.weight(1f).padding(start = 8.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                if (clock.timeControl != TimeControl.UNLIMITED) {
                                    ClockDisplay(timeMs = clock.blackTimeMs, active = currentTurn == PieceColor.BLACK)
                                }
                                CapturedPiecesPanel(color = PieceColor.BLACK, pieces = blackCaptured)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    if (gameState != com.chessmaster.play.engine.GameState.IN_PROGRESS) {
                        Text(
                            text = "Game Over: ${gameState.name}",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    } else {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 8.dp)) {
                            Text(
                                text = "— ${currentTurn.name}'s Turn —",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            if (isThinking) {
                                Spacer(modifier = Modifier.width(16.dp))
                                CircularProgressIndicator(modifier = Modifier.size(20.dp), color = MaterialTheme.colorScheme.onBackground)
                            }
                        }
                    }

                    if (gameState == com.chessmaster.play.engine.GameState.IN_PROGRESS) {
                        val infiniteTransition = rememberInfiniteTransition()
                        val blinkAlpha by infiniteTransition.animateFloat(
                            initialValue = 0.2f,
                            targetValue = 1f,
                            animationSpec = infiniteRepeatable(
                                animation = tween(700),
                                repeatMode = RepeatMode.Reverse
                            ), label = "blink"
                        )
                        
                        val statusText = when (gameMode) {
                            GameMode.PVP -> "Waiting for ${currentTurn.name.lowercase()}..."
                            GameMode.PVCPU -> if (currentTurn == PieceColor.WHITE) "Your move..." else "Thinking..."
                            GameMode.ONLINE -> if (currentTurn == playerColor) "Your move..." else "Waiting for opponent..."
                        }
                        val statusColor = if (gameMode == GameMode.PVP) Color.LightGray 
                                          else Color(0xFF34C759)
                                          
                        if (statusText.isNotEmpty()) {
                            Text(
                                text = statusText,
                                color = statusColor.copy(alpha = blinkAlpha),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }

            val bottomControls = @Composable {
                if (gameMode != GameMode.ONLINE) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp, top = 4.dp)
                    ) {
                        // Restart
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                            IconButton(
                                onClick = { viewModel.resetGame() },
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(Color(0xFFFFB300), shape = CircleShape)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = "Restart",
                                    tint = Color.DarkGray,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text("RESTART", color = Color.Gray, fontWeight = FontWeight.Bold, fontSize = 10.sp)
                        }

                        // Redo
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                            IconButton(
                                onClick = { viewModel.redo() },
                                enabled = viewModel.canRedo,
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(if (viewModel.canRedo) Color(0xFFE0E0E0) else Color(0xFFF5F5F5), shape = CircleShape)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Redo,
                                    contentDescription = "Redo",
                                    tint = if (viewModel.canRedo) Color.DarkGray else Color.LightGray,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text("REDO", color = if (viewModel.canRedo) Color.Gray else Color.LightGray, fontWeight = FontWeight.Bold, fontSize = 10.sp)
                        }

                        // Undo
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                            IconButton(
                                onClick = { viewModel.undo() },
                                enabled = viewModel.canUndo,
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(if (viewModel.canUndo) Color(0xFFE0E0E0) else Color(0xFFF5F5F5), shape = CircleShape)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Undo,
                                    contentDescription = "Undo",
                                    tint = if (viewModel.canUndo) Color.DarkGray else Color.LightGray,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text("UNDO", color = if (viewModel.canUndo) Color.Gray else Color.LightGray, fontWeight = FontWeight.Bold, fontSize = 10.sp)
                        }

                        // Hint
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                            BadgedBox(
                                badge = {
                                    Badge { Text(remainingHints.toString()) }
                                }
                            ) {
                                IconButton(
                                    onClick = { 
                                        if (remainingHints > 0) {
                                            remainingHints--
                                            com.chessmaster.play.data.LocalLeaderboardManager.setRemainingHints(context, remainingHints)
                                            viewModel.getHint()
                                        } else {
                                            showShopDialog = true
                                        }
                                    },
                                    modifier = Modifier
                                        .size(48.dp)
                                        .background(Color(0xFFFFB300), shape = CircleShape)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Lightbulb,
                                        contentDescription = "Hint",
                                        tint = Color.DarkGray,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text("HINT", color = Color.Gray, fontWeight = FontWeight.Bold, fontSize = 10.sp)
                        }
                    }
                } else {
                    OnlineControls(viewModel = viewModel)
                }
            }

            val winner by viewModel.winner.collectAsState()
            val endReason by viewModel.endReason.collectAsState()
            val rematchRequestedBy by viewModel.rematchRequestedBy.collectAsState()
            val drawOfferedBy by viewModel.drawOfferedBy.collectAsState()
            val currentUserId = viewModel.roomCode.value // We just need something to trigger recomposition, wait we can just check if not null

            if (drawOfferedBy != null && drawOfferedBy != "handled") {
                AlertDialog(
                    onDismissRequest = { /* ignore */ },
                    title = { Text("Draw Offered") },
                    text = { Text("The opponent has offered a draw.") },
                    confirmButton = {
                        Button(onClick = { viewModel.acceptDraw() }) { Text("Accept") }
                    },
                    dismissButton = {
                        TextButton(onClick = { /* Decline logic */ }) { Text("Decline") }
                    }
                )
            }

            if (gameState != com.chessmaster.play.engine.GameState.IN_PROGRESS && gameMode == GameMode.PVCPU) {
                val userWon = (gameState == com.chessmaster.play.engine.GameState.CHECKMATE && currentTurn == PieceColor.BLACK) 
                
                AlertDialog(
                    onDismissRequest = { },
                    title = { Text(if (userWon) "You Won!" else "Game Over") },
                    text = {
                        Text(if (userWon) "Congratulations! You beat Level $cpuLevel." else "You were defeated by Level $cpuLevel. Try again!")
                    },
                    confirmButton = {
                        Button(onClick = {
                            val proceed = {
                                if (userWon && cpuLevel < 20) {
                                    viewModel.setCpuLevel(cpuLevel + 1)
                                    viewModel.resetGame()
                                } else {
                                    viewModel.resetGame()
                                }
                            }
                            if (activity != null) {
                                interstitialAdManager.showAd(activity) { proceed() }
                            } else {
                                proceed()
                            }
                        }) {
                            Text(if (userWon && cpuLevel < 20) "Next Level" else "Play Again")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { 
                            onBack()
                        }) {
                            Text("Exit")
                        }
                    }
                )
            }

            if (gameState != com.chessmaster.play.engine.GameState.IN_PROGRESS && gameMode == GameMode.ONLINE) {
                AlertDialog(
                    onDismissRequest = { },
                    title = { Text("Game Over") },
                    text = {
                        Column {
                            Text("Result: $winner")
                            Text("Reason: $endReason")
                            if (rematchRequestedBy != null) {
                                Text("Opponent wants a rematch!", color = Color(0xFF34C759))
                            }
                        }
                    },
                    confirmButton = {
                        Button(onClick = { 
                            if (rematchRequestedBy != null) {
                                viewModel.acceptRematch()
                            } else {
                                viewModel.requestRematch()
                            }
                        }) {
                            Text(if (rematchRequestedBy != null) "Accept Rematch" else "Request Rematch")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { 
                            val proceed = {
                                viewModel.exitRoom()
                                onBack()
                            }
                            if (activity != null) {
                                interstitialAdManager.showAd(activity) { proceed() }
                            } else {
                                proceed()
                            }
                        }) {
                            Text("Exit Room")
                        }
                    }
                )
            }

            val board = @Composable {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        ChessBoard(
                            boardState = boardState,
                            selectedSquare = selectedSquare,
                            legalMoves = legalMoves,
                            lastMove = lastMove,
                            isCheck = isCheck,
                            currentTurn = currentTurn,
                            boardStyleIndex = chessboardStyleIndex,
                            onSquareClicked = { 
                                viewModel.onSquareClicked(it) 
                                if (vibrationsEnabled) {
                                    haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.TextHandleMove)
                                }
                            },
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val levelText = when (cpuLevel) {
                            in 1..5 -> "BEGINNER LEVEL"
                            in 6..10 -> "MODERATE LEVEL"
                            in 11..15 -> "HARD LEVEL"
                            else -> "EXPERT LEVEL"
                        }
                        
                        Surface(
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                            color = Color(0xFFFFF8E1),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFFE082))
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "👑",
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(end = 4.dp)
                                )
                                Text(
                                    text = levelText,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.DarkGray,
                                    fontSize = 10.sp
                                )
                            }
                        }
                        
                        val historyIndex by viewModel.historyIndex.collectAsState(initial = 0)
                        val moveNumber = (historyIndex / 2) + 1
                        val turnName = currentTurn.name.lowercase().replaceFirstChar { it.uppercase() }
                        
                        Surface(
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                            color = Color(0xFFFFF8E1),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFFE082))
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "♟️",
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(end = 4.dp)
                                )
                                Text(
                                    text = "$moveNumber. $turnName's move",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.DarkGray,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                }
            }

            if (isLandscape) {
                Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            topControls()
                            bottomControls()
                            BannerAd(modifier = Modifier.padding(top = 4.dp))
                        }
                    }
                    Box(modifier = Modifier.weight(1.5f).fillMaxHeight(), contentAlignment = Alignment.Center) {
                        board()
                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(), 
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                        topControls()
                    }
                    Box(modifier = Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                        board()
                    }
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomCenter) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            bottomControls()
                            BannerAd(modifier = Modifier.padding(top = 4.dp))
                        }
                    }
                }
            }

            if (showShopDialog) {
                com.chessmaster.play.ui.screens.ShopDialog(
                    adManager = rewardedAdManager,
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
}

@Composable
fun ClockDisplay(timeMs: Long, active: Boolean, modifier: Modifier = Modifier) {
    val totalSeconds = timeMs / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.padding(vertical = 4.dp)) {
        Icon(
            imageVector = androidx.compose.material.icons.Icons.Default.Refresh,
            contentDescription = "Timer",
            tint = Color(0xFFFFB300),
            modifier = Modifier.size(20.dp).padding(end = 4.dp)
        )
        Text(
            text = String.format("%02d:%02d", minutes, seconds),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = if (timeMs < 10000) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onBackground
        )
    }
}
