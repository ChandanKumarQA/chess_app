package com.chessmaster.play.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chessmaster.play.data.ExplorerData
import com.chessmaster.play.data.OpeningExplorerApi
import com.chessmaster.play.data.OpeningMove
import com.chessmaster.play.engine.AIEngine
import com.chessmaster.play.engine.GameEngine
import com.chessmaster.play.engine.NotationParser
import com.chessmaster.play.model.*
import com.chessmaster.play.ui.components.ChessBoard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

data class AnalysisHistoryItem(
    val move: Move?,
    val san: String,
    val boardState: BoardState,
    val turn: PieceColor,
    val evalScore: Float = 0f,
    val isCheck: Boolean = false
)

enum class AnalysisTab {
    EXPLORER, MOVE_TREE
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalysisBoardScreen(initialFen: String = "", onBack: () -> Unit = {}) {
    BackHandler(onBack = onBack)

    val gameEngine = remember { GameEngine() }
    val aiEngine = remember { AIEngine(gameEngine) }
    val openingApi = remember { OpeningExplorerApi() }
    val coroutineScope = rememberCoroutineScope()

    // Move history
    var history by remember {
        val initial = if (initialFen.isNotEmpty()) {
            try {
                val (state, turn) = NotationParser.fenToBoardState(initialFen)
                AnalysisHistoryItem(null, "", state, turn, evaluatePosition(state))
            } catch (_: Exception) {
                AnalysisHistoryItem(null, "", BoardState.initial(), PieceColor.WHITE, 0f)
            }
        } else {
            AnalysisHistoryItem(null, "", BoardState.initial(), PieceColor.WHITE, 0f)
        }
        mutableStateOf(listOf(initial))
    }
    var currentMoveIndex by remember { mutableIntStateOf(0) }

    val currentItem = history.getOrElse(currentMoveIndex) { history.last() }
    val boardState = currentItem.boardState
    val currentTurn = currentItem.turn
    val isCheck = currentItem.isCheck
    val lastMove = currentItem.move

    var selectedSquare by remember { mutableStateOf<Square?>(null) }
    var legalMoves by remember { mutableStateOf<List<Move>>(emptyList()) }
    var isBoardFlipped by remember { mutableStateOf(false) }

    // Tabs: Explorer vs Move Tree
    var activeTab by remember { mutableStateOf(AnalysisTab.MOVE_TREE) }

    // Engine evaluation toggle & state
    var isEngineEnabled by remember { mutableStateOf(true) }
    var bestMoveArrow by remember { mutableStateOf<List<Pair<Square, Square>>>(emptyList()) }
    var engineLineText by remember { mutableStateOf("") }

    // Opening Explorer state
    var explorerData by remember { mutableStateOf(ExplorerData(null, emptyList(), emptyList())) }
    var isFetchingExplorer by remember { mutableStateOf(false) }

    // Top-right dropdown menu
    var showMenu by remember { mutableStateOf(false) }

    // Fetch opening explorer data and engine analysis when position changes
    LaunchedEffect(boardState, currentTurn) {
        val fen = NotationParser.boardStateToFen(boardState, currentTurn, (currentMoveIndex / 2) + 1)

        // Opening explorer
        isFetchingExplorer = true
        coroutineScope.launch {
            val data = openingApi.getExplorerData(fen)
            explorerData = data
            isFetchingExplorer = false
        }

        // Engine evaluation & best move
        if (isEngineEnabled) {
            coroutineScope.launch(Dispatchers.Default) {
                val best = aiEngine.getBestMove(boardState, currentTurn, depth = 2)
                withContext(Dispatchers.Main) {
                    if (best != null) {
                        bestMoveArrow = listOf(Pair(best.from, best.to))
                        val bestSan = moveToSan(best)
                        val eval = evaluatePosition(boardState)
                        val evalPrefix = if (eval >= 0) "+${String.format(Locale.getDefault(), "%.1f", eval)}" else String.format(Locale.getDefault(), "%.1f", eval)
                        val moveNum = (currentMoveIndex / 2) + 1
                        val moveIndicator = if (currentTurn == PieceColor.WHITE) "$moveNum. " else "$moveNum... "
                        engineLineText = "$evalPrefix  $moveIndicator$bestSan"
                    } else {
                        bestMoveArrow = emptyList()
                        val eval = evaluatePosition(boardState)
                        val evalPrefix = if (eval >= 0) "+${String.format(Locale.getDefault(), "%.1f", eval)}" else String.format(Locale.getDefault(), "%.1f", eval)
                        engineLineText = "$evalPrefix"
                    }
                }
            }
        } else {
            bestMoveArrow = emptyList()
            engineLineText = ""
        }
    }

    fun makeMove(move: Move) {
        val nextState = boardState.copyWithMove(move)
        val nextTurn = currentTurn.opposite()
        val check = gameEngine.isKingInCheck(nextState, nextTurn)
        val checkmate = check && gameEngine.getAllLegalMoves(nextState, nextTurn).isEmpty()
        val san = moveToSan(move, check, checkmate)
        val eval = evaluatePosition(nextState)

        val newItem = AnalysisHistoryItem(
            move = move,
            san = san,
            boardState = nextState,
            turn = nextTurn,
            evalScore = eval,
            isCheck = check
        )

        // Branching: keep history up to currentMoveIndex and append new move
        val updatedHistory = history.take(currentMoveIndex + 1) + newItem
        history = updatedHistory
        currentMoveIndex = updatedHistory.size - 1

        selectedSquare = null
        legalMoves = emptyList()
    }

    val onSquareClicked: (Square) -> Unit = { square ->
        val selected = selectedSquare
        val pieceOnSquare = boardState.getPiece(square)

        if (selected == null) {
            if (pieceOnSquare != null && pieceOnSquare.color == currentTurn) {
                selectedSquare = square
                legalMoves = gameEngine.getLegalMoves(boardState, square)
            }
        } else {
            if (pieceOnSquare != null && pieceOnSquare.color == currentTurn) {
                selectedSquare = square
                legalMoves = gameEngine.getLegalMoves(boardState, square)
            } else {
                val move = legalMoves.find {
                    it.to == square && (it.promotionTo == null || it.promotionTo == PieceType.QUEEN)
                }
                if (move != null) {
                    makeMove(move)
                } else {
                    selectedSquare = null
                    legalMoves = emptyList()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Analysis board", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    Box {
                        IconButton(onClick = { showMenu = true }) {
                            Icon(Icons.Default.MoreVert, contentDescription = "Options")
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Clear moves") },
                                onClick = {
                                    val startItem = AnalysisHistoryItem(null, "", BoardState.initial(), PieceColor.WHITE, 0f)
                                    history = listOf(startItem)
                                    currentMoveIndex = 0
                                    selectedSquare = null
                                    legalMoves = emptyList()
                                    showMenu = false
                                },
                                leadingIcon = { Icon(Icons.Default.Clear, contentDescription = null) }
                            )
                            DropdownMenuItem(
                                text = { Text("Flip board") },
                                onClick = {
                                    isBoardFlipped = !isBoardFlipped
                                    showMenu = false
                                },
                                leadingIcon = { Icon(Icons.Default.Sync, contentDescription = null) }
                            )
                            DropdownMenuItem(
                                text = { Text("Variant: Standard") },
                                onClick = { showMenu = false },
                                leadingIcon = { Icon(Icons.Default.Settings, contentDescription = null) }
                            )
                            DropdownMenuItem(
                                text = { Text("Continue from here") },
                                onClick = { showMenu = false },
                                leadingIcon = { Icon(Icons.Default.PlayArrow, contentDescription = null) }
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1B1E22),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        },
        bottomBar = {
            // Bottom control bar (matches Lichess reference screenshots)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .background(Color(0xFF161A1D))
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Menu button
                IconButton(onClick = { showMenu = true }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = Color.White.copy(alpha = 0.7f),
                        modifier = Modifier.size(22.dp)
                    )
                }

                // Flip board
                IconButton(onClick = { isBoardFlipped = !isBoardFlipped }) {
                    Icon(
                        imageVector = Icons.Default.Sync,
                        contentDescription = "Flip board",
                        tint = if (isBoardFlipped) Color(0xFF80C8F8) else Color.White.copy(alpha = 0.7f),
                        modifier = Modifier.size(22.dp)
                    )
                }

                // Engine toggle chip (SF 18)
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { isEngineEnabled = !isEngineEnabled }
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Memory,
                            contentDescription = "Engine",
                            tint = if (isEngineEnabled) Color(0xFF80C8F8) else Color.White.copy(alpha = 0.4f),
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "SF 18",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isEngineEnabled) Color(0xFF80C8F8) else Color.White.copy(alpha = 0.4f)
                        )
                    }
                }

                // Previous move
                IconButton(
                    onClick = {
                        if (currentMoveIndex > 0) {
                            currentMoveIndex--
                            selectedSquare = null
                            legalMoves = emptyList()
                        }
                    },
                    enabled = currentMoveIndex > 0
                ) {
                    Icon(
                        imageVector = Icons.Default.ChevronLeft,
                        contentDescription = "Previous move",
                        tint = if (currentMoveIndex > 0) Color.White else Color.White.copy(alpha = 0.25f),
                        modifier = Modifier.size(28.dp)
                    )
                }

                // Next move
                IconButton(
                    onClick = {
                        if (currentMoveIndex < history.size - 1) {
                            currentMoveIndex++
                            selectedSquare = null
                            legalMoves = emptyList()
                        }
                    },
                    enabled = currentMoveIndex < history.size - 1
                ) {
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Next move",
                        tint = if (currentMoveIndex < history.size - 1) Color.White else Color.White.copy(alpha = 0.25f),
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        },
        containerColor = Color(0xFF1B1E22)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Engine Evaluation Bar / Header (matches screenshot 1 & 5)
            if (isEngineEnabled && engineLineText.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF22262B))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val evalScore = currentItem.evalScore
                    val evalBg = if (evalScore >= 0) Color(0xFF38434D) else Color(0xFF4A3838)
                    val evalText = if (evalScore >= 0) "+${String.format(Locale.getDefault(), "%.1f", evalScore)}" else String.format(Locale.getDefault(), "%.1f", evalScore)

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(evalBg)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = evalText,
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = engineLineText,
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1
                    )
                }
            }

            // Interactive Chessboard
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(Color(0xFF1B1E22)),
                contentAlignment = Alignment.Center
            ) {
                ChessBoard(
                    boardState = boardState,
                    selectedSquare = selectedSquare,
                    legalMoves = legalMoves,
                    lastMove = lastMove,
                    isCheck = isCheck,
                    currentTurn = currentTurn,
                    onSquareClicked = onSquareClicked,
                    arrows = if (isEngineEnabled) bestMoveArrow else emptyList(),
                    isFlipped = isBoardFlipped,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Two Sub-Tabs: Opening Explorer vs Move List/Tree View (matches screenshots 1, 2, 3, 4, 5)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .background(Color(0xFF1E2226)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tab 1: Opening Explorer
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable { activeTab = AnalysisTab.EXPLORER },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Explore,
                            contentDescription = "Opening explorer",
                            tint = if (activeTab == AnalysisTab.EXPLORER) Color(0xFF80C8F8) else Color.White.copy(alpha = 0.5f),
                            modifier = Modifier.size(22.dp)
                        )
                        if (activeTab == AnalysisTab.EXPLORER) {
                            Box(
                                modifier = Modifier
                                    .padding(top = 4.dp)
                                    .width(28.dp)
                                    .height(2.dp)
                                    .background(Color(0xFF80C8F8))
                            )
                        }
                    }
                }

                // Tab 2: Move Tree View
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable { activeTab = AnalysisTab.MOVE_TREE },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountTree,
                            contentDescription = "Move list",
                            tint = if (activeTab == AnalysisTab.MOVE_TREE) Color(0xFF80C8F8) else Color.White.copy(alpha = 0.5f),
                            modifier = Modifier.size(22.dp)
                        )
                        if (activeTab == AnalysisTab.MOVE_TREE) {
                            Box(
                                modifier = Modifier
                                    .padding(top = 4.dp)
                                    .width(28.dp)
                                    .height(2.dp)
                                    .background(Color(0xFF80C8F8))
                            )
                        }
                    }
                }
            }

            // Tab Content
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color(0xFF121417))
            ) {
                when (activeTab) {
                    AnalysisTab.EXPLORER -> {
                        OpeningExplorerView(
                            explorerData = explorerData,
                            isLoading = isFetchingExplorer,
                            onMoveSelected = { openingMove ->
                                // Play the opening move if legal
                                val allLegals = gameEngine.getAllLegalMoves(boardState, currentTurn)
                                val matched = allLegals.find {
                                    val s = moveToSan(it).replace("+", "").replace("#", "")
                                    s == openingMove.san.replace("+", "").replace("#", "")
                                }
                                if (matched != null) {
                                    makeMove(matched)
                                }
                            }
                        )
                    }
                    AnalysisTab.MOVE_TREE -> {
                        MoveListView(
                            history = history,
                            currentIndex = currentMoveIndex,
                            onMoveClick = { targetIndex ->
                                currentMoveIndex = targetIndex
                                selectedSquare = null
                                legalMoves = emptyList()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MoveListView(
    history: List<AnalysisHistoryItem>,
    currentIndex: Int,
    onMoveClick: (Int) -> Unit
) {
    if (history.size <= 1) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "Make a move on the board to start analysis.",
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 14.sp
            )
        }
        return
    }

    val listState = rememberLazyListState()

    LaunchedEffect(currentIndex) {
        val row = (currentIndex - 1) / 2
        if (row >= 0) {
            listState.animateScrollToItem(row)
        }
    }

    // Moves grouped in pairs (White, Black)
    val playedMoves = history.drop(1)
    val movePairs = playedMoves.chunked(2)

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        items(movePairs.size) { rowIndex ->
            val moveNumber = rowIndex + 1
            val pair = movePairs[rowIndex]
            val whiteHistoryIndex = rowIndex * 2 + 1
            val blackHistoryIndex = rowIndex * 2 + 2

            val isWhiteSelected = currentIndex == whiteHistoryIndex
            val isBlackSelected = currentIndex == blackHistoryIndex

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Move Number
                Text(
                    text = "$moveNumber",
                    color = Color.White.copy(alpha = 0.45f),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.width(36.dp)
                )

                // White Move
                val whiteMove = pair.getOrNull(0)
                if (whiteMove != null) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (isWhiteSelected) Color(0xFF2E343A) else Color.Transparent)
                            .clickable { onMoveClick(whiteHistoryIndex) }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = whiteMove.san,
                            color = if (isWhiteSelected) Color.White else Color.White.copy(alpha = 0.85f),
                            fontWeight = if (isWhiteSelected) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 15.sp
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Black Move
                val blackMove = pair.getOrNull(1)
                if (blackMove != null) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (isBlackSelected) Color(0xFF2E343A) else Color.Transparent)
                            .clickable { onMoveClick(blackHistoryIndex) }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = blackMove.san,
                                color = if (isBlackSelected) Color.White else Color.White.copy(alpha = 0.85f),
                                fontWeight = if (isBlackSelected) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 15.sp
                            )
                            if (blackMove.evalScore != 0f) {
                                val evalStr = if (blackMove.evalScore >= 0) "+${String.format(Locale.getDefault(), "%.1f", blackMove.evalScore)}" else String.format(Locale.getDefault(), "%.1f", blackMove.evalScore)
                                Text(
                                    text = evalStr,
                                    color = Color.White.copy(alpha = 0.4f),
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun OpeningExplorerView(
    explorerData: ExplorerData,
    isLoading: Boolean,
    onMoveSelected: (OpeningMove) -> Unit
) {
    if (isLoading && explorerData.moves.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color(0xFF80C8F8))
        }
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {
        // Opening Name / Header (matches screenshot 4)
        item {
            Text(
                text = explorerData.openingName ?: "Starting position",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Table Header: Move | Games | White / Draw / Black
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Move",
                    color = Color.White.copy(alpha = 0.6f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.width(50.dp)
                )
                Text(
                    text = "Games",
                    color = Color.White.copy(alpha = 0.6f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f).padding(start = 8.dp)
                )
                Text(
                    text = "White / Draw / Black",
                    color = Color.White.copy(alpha = 0.6f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.width(140.dp)
                )
            }
            HorizontalDivider(color = Color.White.copy(alpha = 0.1f), modifier = Modifier.padding(bottom = 6.dp))
        }

        // Moves list (matches screenshot 3 & 4)
        val totalMovesGames = explorerData.moves.sumOf { it.totalGames }.coerceAtLeast(1)

        items(explorerData.moves) { move ->
            val moveTotal = move.totalGames.coerceAtLeast(1)
            val whitePct = (move.white * 100) / moveTotal
            val drawPct = (move.draws * 100) / moveTotal
            val blackPct = (move.black * 100) / moveTotal
            val sharePct = (move.totalGames * 100) / totalMovesGames

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .clickable { onMoveSelected(move) }
                    .padding(vertical = 7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Move SAN
                Text(
                    text = move.san,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.width(50.dp)
                )

                // Games count & %
                Text(
                    text = "${formatNumber(move.totalGames)} ($sharePct%)",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 13.sp,
                    modifier = Modifier.weight(1f).padding(start = 8.dp)
                )

                // Win/Draw/Loss Bar
                Row(
                    modifier = Modifier
                        .width(140.dp)
                        .height(22.dp)
                        .clip(RoundedCornerShape(4.dp))
                ) {
                    // White
                    Box(
                        modifier = Modifier
                            .weight(whitePct.toFloat().coerceAtLeast(1f))
                            .fillMaxHeight()
                            .background(Color(0xFFEFEFEF)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$whitePct%",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                    // Draw
                    Box(
                        modifier = Modifier
                            .weight(drawPct.toFloat().coerceAtLeast(1f))
                            .fillMaxHeight()
                            .background(Color(0xFF949494)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$drawPct%",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    // Black
                    Box(
                        modifier = Modifier
                            .weight(blackPct.toFloat().coerceAtLeast(1f))
                            .fillMaxHeight()
                            .background(Color(0xFF1E1E1E)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$blackPct%",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }

        // Top games section (matches screenshot 2 & 3)
        if (explorerData.topGames.isNotEmpty()) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Top games",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(explorerData.topGames) { game ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "${game.whiteRating} ",
                                color = Color.White.copy(alpha = 0.55f),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = game.whiteName,
                                color = Color.White,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "${game.blackRating} ",
                                color = Color.White.copy(alpha = 0.55f),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = game.blackName,
                                color = Color.White,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    // Result Pill
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFF2E3338))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = game.result,
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    // Date
                    Text(
                        text = game.date,
                        color = Color.White.copy(alpha = 0.5f),
                        fontSize = 12.sp
                    )
                }
                HorizontalDivider(color = Color.White.copy(alpha = 0.05f))
            }
        }
    }
}

// Convert Move to standard algebraic notation (SAN)
private fun moveToSan(move: Move, isCheck: Boolean = false, isCheckmate: Boolean = false): String {
    if (move.isCastling) {
        return if (move.to.file > move.from.file) "O-O" else "O-O-O"
    }
    val piecePrefix = when (move.piece.type) {
        PieceType.PAWN -> if (move.isCapture) "${('a' + move.from.file)}x" else ""
        PieceType.KNIGHT -> "N" + (if (move.isCapture) "x" else "")
        PieceType.BISHOP -> "B" + (if (move.isCapture) "x" else "")
        PieceType.ROOK -> "R" + (if (move.isCapture) "x" else "")
        PieceType.QUEEN -> "Q" + (if (move.isCapture) "x" else "")
        PieceType.KING -> "K" + (if (move.isCapture) "x" else "")
    }
    val dest = move.to.toString()
    val promo = if (move.promotionTo != null) "=${when(move.promotionTo) {
        PieceType.QUEEN -> "Q"
        PieceType.ROOK -> "R"
        PieceType.BISHOP -> "B"
        PieceType.KNIGHT -> "N"
        else -> ""
    }}" else ""
    val checkSuffix = if (isCheckmate) "#" else if (isCheck) "+" else ""
    return "$piecePrefix$dest$promo$checkSuffix"
}

// Simple fast evaluation of position in centipawns / pawns
private fun evaluatePosition(boardState: BoardState): Float {
    var score = 0
    for ((_, piece) in boardState.board) {
        val mult = if (piece.color == PieceColor.WHITE) 1 else -1
        val valPiece = when (piece.type) {
            PieceType.PAWN -> 100
            PieceType.KNIGHT -> 320
            PieceType.BISHOP -> 330
            PieceType.ROOK -> 500
            PieceType.QUEEN -> 900
            PieceType.KING -> 0
        }
        score += mult * valPiece
    }
    return score / 100f
}

private fun formatNumber(number: Int): String {
    return String.format(Locale.US, "%,d", number)
}
