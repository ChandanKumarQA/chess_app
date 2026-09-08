package com.chessmaster.play.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.chessmaster.play.RewardedAdManager
import com.chessmaster.play.data.PuzzleRepository
import com.chessmaster.play.model.EndgameLesson
import com.chessmaster.play.engine.NotationParser
import com.chessmaster.play.engine.GameEngine
import com.chessmaster.play.model.Move
import com.chessmaster.play.model.Square
import com.chessmaster.play.ui.components.ChessBoard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EndgameTrainingScreen(onBack: () -> Unit, onPlayPuzzle: () -> Unit) {
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var activeLesson by remember { mutableStateOf<EndgameLesson?>(null) }
    
    val darkBg = MaterialTheme.colorScheme.background
    val cardBg = MaterialTheme.colorScheme.surfaceVariant
    val textPrimary = MaterialTheme.colorScheme.onBackground
    val textSecondary = MaterialTheme.colorScheme.onSurfaceVariant
    val accentColor = Color(0xFF9C27B0)

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
                    if (activeLesson != null) {
                        activeLesson = null
                    } else if (selectedCategory != null) {
                        selectedCategory = null
                    } else {
                        onBack()
                    }
                }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = androidx.compose.ui.graphics.Color.White)
                }
                Text(
                    text = activeLesson?.title ?: selectedCategory ?: "Endgame Training",
                    color = textPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            when {
                activeLesson != null -> {
                    LessonViewer(
                        lesson = activeLesson!!,
                        onComplete = { activeLesson = null },
                        onNext = { 
                            val allInCategory = PuzzleRepository.getEndgameLessonsByCategory(activeLesson!!.category)
                            val index = allInCategory.indexOf(activeLesson!!)
                            if (index >= 0 && index < allInCategory.size - 1) {
                                activeLesson = allInCategory[index + 1]
                            } else {
                                val nextFirstLesson = PuzzleRepository.getFirstLessonOfNextEndgameCategory(activeLesson!!.category)
                                if (nextFirstLesson != null) {
                                    activeLesson = nextFirstLesson
                                    selectedCategory = nextFirstLesson.category
                                } else {
                                    activeLesson = null
                                }
                            }
                        },
                        darkBg = darkBg,
                        cardBg = cardBg,
                        textPrimary = textPrimary,
                        textSecondary = textSecondary,
                        accentColor = accentColor
                    )
                }
                selectedCategory != null -> {
                    val lessons = PuzzleRepository.getEndgameLessonsByCategory(selectedCategory!!)
                    if (lessons.isEmpty()) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("No endgames available in this category yet.", color = textSecondary)
                        }
                    } else {
                        val easyLessons = lessons.take(35)
                        val moderateLessons = lessons.drop(35).take(35)
                        val hardLessons = lessons.drop(70)

                        LazyColumn(
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            item {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(vertical = 6.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(width = 4.dp, height = 22.dp)
                                            .clip(RoundedCornerShape(2.dp))
                                            .background(Color(0xFF00C853))
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("EASY", color = Color(0xFF00C853), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("• Levels 1 - 35", color = Color.White.copy(alpha = 0.6f), fontSize = 13.sp)
                                }
                            }
                            items(easyLessons) { lesson ->
                                LessonItem(
                                    lesson = lesson,
                                    cardBg = cardBg,
                                    textPrimary = textPrimary,
                                    textSecondary = textSecondary,
                                    accentColor = Color(0xFF00C853),
                                    onClick = { activeLesson = lesson }
                                )
                            }
                            item {
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(vertical = 6.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(width = 4.dp, height = 22.dp)
                                            .clip(RoundedCornerShape(2.dp))
                                            .background(Color(0xFFFFB300))
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("MODERATE", color = Color(0xFFFFB300), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("• Levels 36 - 70", color = Color.White.copy(alpha = 0.6f), fontSize = 13.sp)
                                }
                            }
                            items(moderateLessons) { lesson ->
                                LessonItem(
                                    lesson = lesson,
                                    cardBg = cardBg,
                                    textPrimary = textPrimary,
                                    textSecondary = textSecondary,
                                    accentColor = Color(0xFFFFB300),
                                    onClick = { activeLesson = lesson }
                                )
                            }
                            item {
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(vertical = 6.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(width = 4.dp, height = 22.dp)
                                            .clip(RoundedCornerShape(2.dp))
                                            .background(Color(0xFFE53935))
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("HARD", color = Color(0xFFE53935), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("• Levels 71 - 100", color = Color.White.copy(alpha = 0.6f), fontSize = 13.sp)
                                }
                            }
                            items(hardLessons) { lesson ->
                                LessonItem(
                                    lesson = lesson,
                                    cardBg = cardBg,
                                    textPrimary = textPrimary,
                                    textSecondary = textSecondary,
                                    accentColor = Color(0xFFE53935),
                                    onClick = { activeLesson = lesson }
                                )
                            }
                        }
                    }
                }
                else -> {
                    val categories = PuzzleRepository.getAllEndgameCategories()
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(categories.size) { index ->
                            val category = categories[index]
                            val lessonsInCategory = PuzzleRepository.getEndgameLessonsByCategory(category)

                            CategoryItem(
                                title = category,
                                difficulty = "Easy • Moderate • Hard",
                                puzzleCount = lessonsInCategory.size,
                                completion = 0,
                                bestScore = 0,
                                cardBg = cardBg,
                                textPrimary = textPrimary,
                                textSecondary = textSecondary,
                                accentColor = accentColor,
                                isLocked = false,
                                onClick = { selectedCategory = category }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LessonItem(
    lesson: EndgameLesson,
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
                Icon(Icons.Default.Flag, contentDescription = null, tint = accentColor)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(lesson.title, color = textPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(lesson.explanation.take(30) + "...", color = textSecondary, fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun LessonViewer(
    lesson: EndgameLesson,
    onComplete: () -> Unit,
    onNext: () -> Unit,
    darkBg: Color,
    cardBg: Color,
    textPrimary: Color,
    textSecondary: Color,
    accentColor: Color
) {
    val context = LocalContext.current
    var showSolution by remember { mutableStateOf(false) }
    var showShopDialog by remember { mutableStateOf(false) }
    var remainingHints by remember { mutableIntStateOf(com.chessmaster.play.data.LocalLeaderboardManager.getRemainingHints(context)) }
    val adManager = remember { RewardedAdManager(context) }
    
    LaunchedEffect(Unit) {
        adManager.loadAd()
    }
    
    val initialBoardData = remember(lesson) {
        NotationParser.fenToBoardState(lesson.fen)
    }
    var boardState by remember(lesson) { mutableStateOf(initialBoardData.first) }
    var currentTurn by remember(lesson) { mutableStateOf(initialBoardData.second) }
    var solutionIndex by remember(lesson) { mutableStateOf(0) }
    var selectedSquare by remember { mutableStateOf<Square?>(null) }
    var legalMoves by remember { mutableStateOf<List<Move>>(emptyList()) }
    var hintMove by remember { mutableStateOf<com.chessmaster.play.model.Move?>(null) }
    var handPointer by remember { mutableStateOf<com.chessmaster.play.model.Square?>(null) }
    val engine = remember { GameEngine() }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = cardBg)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Introduction", color = accentColor, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(lesson.explanation, color = textPrimary)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))

        // Render actual ChessBoard for the endgame lesson
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            ChessBoard(
                boardState = boardState,
                selectedSquare = selectedSquare,
                legalMoves = legalMoves,
                lastMove = null,
                isCheck = false,
                currentTurn = currentTurn,
                hintMove = hintMove,
                handPointer = handPointer,
                onSquareClicked = { square ->
                    if (showSolution && solutionIndex >= lesson.solutionMoves.size) return@ChessBoard
                    val piece = boardState.getPiece(square)
                    if (selectedSquare == null) {
                        if (piece != null && piece.color == currentTurn) {
                            selectedSquare = square
                            legalMoves = engine.getLegalMoves(boardState, square)
                        }
                    } else {
                        val move = legalMoves.find { it.to == square }
                        if (move != null) {
                            val expectedStr = lesson.solutionMoves.getOrNull(solutionIndex)
                            val attemptedStr = "${move.from}${move.to}"
                            if (expectedStr == attemptedStr) {
                                boardState = boardState.copyWithMove(move)
                                currentTurn = currentTurn.opposite()
                                solutionIndex++
                                
                                if (solutionIndex >= lesson.solutionMoves.size) {
                                    Toast.makeText(context, "Endgame complete!", Toast.LENGTH_SHORT).show()
                                    showSolution = true
                                    val allInCategory = PuzzleRepository.getEndgameLessonsByCategory(lesson.category)
                                    val index = allInCategory.indexOf(lesson)
                                    if (index >= 0) {
                                        val currentLevelNum = index + 1
                                        com.chessmaster.play.data.LocalLeaderboardManager.unlockNextThemeLevel(context, lesson.category, currentLevelNum)
                                    }
                                } else {
                                    val oppStr = lesson.solutionMoves[solutionIndex]
                                    val fromSq = Square(oppStr[0] - 'a', oppStr[1] - '1')
                                    val toSq = Square(oppStr[2] - 'a', oppStr[3] - '1')
                                    coroutineScope.launch {
                                        delay(300)
                                        val oppMoves = engine.getLegalMoves(boardState, fromSq)
                                        val actualOpp = oppMoves.find { it.to == toSq }
                                        if (actualOpp != null) {
                                            boardState = boardState.copyWithMove(actualOpp)
                                            currentTurn = currentTurn.opposite()
                                            solutionIndex++
                                        }
                                    }
                                }
                            } else {
                                Toast.makeText(context, "Wrong move, try again!", Toast.LENGTH_SHORT).show()
                            }
                        }
                        selectedSquare = null
                        legalMoves = emptyList()
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (showSolution) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF2E7D32).copy(alpha = 0.2f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Solution", color = Color(0xFF4CAF50), fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Winning moves: ${lesson.solutionMoves.joinToString(", ")}", color = textPrimary)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Analysis: ${lesson.hint}", color = textSecondary)
                }
            }
        } else {
            Button(
                onClick = { 
                    if (remainingHints > 0) {
                        val expectedStr = lesson.solutionMoves.getOrNull(solutionIndex)
                        if (expectedStr != null) {
                            val fromSq = com.chessmaster.play.model.Square(expectedStr[0] - 'a', expectedStr[1] - '1')
                            val toSq = com.chessmaster.play.model.Square(expectedStr[2] - 'a', expectedStr[3] - '1')
                            val piece = boardState.getPiece(fromSq) ?: com.chessmaster.play.model.Piece(com.chessmaster.play.model.PieceType.PAWN, currentTurn)
                            hintMove = com.chessmaster.play.model.Move(piece, fromSq, toSq)
                            handPointer = fromSq
                            coroutineScope.launch {
                                kotlinx.coroutines.delay(1200)
                                hintMove = null
                                handPointer = null
                            }
                        }
                        remainingHints--
                        com.chessmaster.play.data.LocalLeaderboardManager.setRemainingHints(context, remainingHints)
                    } else {
                        showShopDialog = true
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Show Hint / Solution (${remainingHints})", color = MaterialTheme.colorScheme.onBackground)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            if (showSolution) {
                Button(
                    onClick = onNext,
                    colors = ButtonDefaults.buttonColors(containerColor = accentColor)
                ) {
                    Text("Next Level")
                }
            }
        }
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
}
