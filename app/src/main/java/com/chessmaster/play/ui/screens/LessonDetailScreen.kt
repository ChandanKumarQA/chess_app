package com.chessmaster.play.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.border
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.platform.LocalContext
import android.app.Activity
import com.chessmaster.play.R
import com.chessmaster.play.InterstitialAdManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chessmaster.play.model.Square
import com.chessmaster.play.ui.components.ChessBoard
import com.chessmaster.play.viewmodel.LearnViewModel
import com.chessmaster.play.viewmodel.LessonPhase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonDetailScreen(
    lessonId: String,
    viewModel: LearnViewModel,
    onBack: () -> Unit
) {
    LaunchedEffect(lessonId) {
        viewModel.loadLesson(lessonId)
    }

    val lesson by viewModel.currentLesson.collectAsState()
    val phase by viewModel.lessonPhase.collectAsState()
    
    val textPrimary = MaterialTheme.colorScheme.onBackground
    val darkBg = MaterialTheme.colorScheme.background
    val context = LocalContext.current
    val activity = context as? Activity
    val interstitialAdManager = remember { InterstitialAdManager(context) }

    if (lesson == null || lesson?.id != lessonId) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    var showExitDialog by remember { mutableStateOf(false) }

    val handleBack = {
        if (phase == LessonPhase.COMPLETED) {
            onBack()
        } else {
            showExitDialog = true
        }
    }

    androidx.activity.compose.BackHandler {
        handleBack()
    }

    Scaffold(
        containerColor = darkBg
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (showExitDialog) {
                androidx.compose.material3.AlertDialog(
                    onDismissRequest = { showExitDialog = false },
                    title = { androidx.compose.material3.Text("Exit Lesson?", color = Color.Black) },
                    text = { androidx.compose.material3.Text("Do you want to exit the lesson? Your progress will be lost.", color = Color.DarkGray) },
                    confirmButton = {
                        androidx.compose.material3.TextButton(onClick = {
                            if (activity != null) {
                                interstitialAdManager.showAd(activity) {
                                    showExitDialog = false
                                    onBack()
                                }
                            } else {
                                showExitDialog = false
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
            if (phase != LessonPhase.COMPLETED) {
                // Top Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = handleBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = androidx.compose.ui.graphics.Color.White)
                    }
                    Text(lesson!!.title, color = textPrimary, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }

            when (phase) {
                LessonPhase.EXPLANATION -> ExplanationPhase(viewModel)
                LessonPhase.PRACTICE -> PracticePhase(viewModel)
                LessonPhase.COMPLETED -> CompletedPhase(
                    onNext = onBack,
                    onRestart = { 
                        val restartAction = { viewModel.loadLesson(lesson!!.id) }
                        if (activity != null) {
                            interstitialAdManager.showAd(activity) { restartAction() }
                        } else {
                            restartAction()
                        }
                    }
                )
                else -> {}
            }
        }
    }
}

@Composable
fun ExplanationPhase(viewModel: LearnViewModel) {
    val lesson by viewModel.currentLesson.collectAsState()
    val stepIndex by viewModel.currentStepIndex.collectAsState()
    val boardState by viewModel.boardState.collectAsState()
    
    val currentStep = lesson!!.steps[stepIndex]

    // Convert ArrowConfig to Pairs for ChessBoard
    val arrows = currentStep.arrows.map { Pair(it.from, it.to) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
        ) {
            ChessBoard(
                boardState = boardState,
                selectedSquare = null,
                legalMoves = emptyList(),
                lastMove = null, // Could map movePiece to lastMove
                isCheck = false,
                currentTurn = com.chessmaster.play.model.PieceColor.WHITE,
                onSquareClicked = {},
                arrows = arrows,
                handPointer = currentStep.handPointer,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = currentStep.explanation,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 18.sp,
                        lineHeight = 26.sp
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            IconButton(
                onClick = { viewModel.nextStep() },
                modifier = Modifier
                    .size(56.dp)
                    .background(Color(0xFF8B4513), androidx.compose.foundation.shape.CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.FastForward,
                    contentDescription = "Next",
                    tint = Color(0xFFFFD700)
                )
            }
        }
    }
}

@Composable
fun PracticePhase(viewModel: LearnViewModel) {
    val lesson by viewModel.currentLesson.collectAsState()
    val config = lesson!!.practiceConfig!!
    val boardState by viewModel.boardState.collectAsState()
    val selectedSquare by viewModel.selectedSquare.collectAsState()
    val legalMoves by viewModel.legalMoves.collectAsState()
    val currentTurn by viewModel.currentTurn.collectAsState()
    val isError by viewModel.practiceMoveError.collectAsState()
    val isCompleted by viewModel.practiceCompleted.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
        ) {
            val targetStr = config.targetMoves.firstOrNull()
            val handPointer = if (targetStr != null && targetStr.length >= 4) {
                if (selectedSquare == null) {
                    Square(targetStr[0] - 'a', targetStr[1] - '1')
                } else {
                    Square(targetStr[2] - 'a', targetStr[3] - '1')
                }
            } else null

            ChessBoard(
                boardState = boardState,
                selectedSquare = selectedSquare,
                legalMoves = legalMoves,
                lastMove = null,
                isCheck = false, // Simplified
                currentTurn = currentTurn,
                onSquareClicked = { viewModel.onSquareClicked(it) },
                handPointer = handPointer,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (isCompleted) {
            Text(
                "Success!",
                color = Color(0xFF4CAF50),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        } else {
            Card(
                colors = CardDefaults.cardColors(containerColor = if (isError) Color(0xFF5D4037) else MaterialTheme.colorScheme.surfaceVariant),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = if (isError) "Incorrect move, try again." else config.instructions,
                    color = if (isError) Color(0xFFFF5252) else MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun QuizPhase(viewModel: LearnViewModel) {
    val lesson by viewModel.currentLesson.collectAsState()
    val config = lesson!!.quizConfig!!
    val qIndex by viewModel.quizQuestionIndex.collectAsState()
    val question = config.questions[qIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Quiz \${qIndex + 1} / \${config.questions.size}",
            color = Color.Gray,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = question.question,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        question.options.forEachIndexed { index, option ->
            Button(
                onClick = { viewModel.answerQuiz(index) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = option,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun CompletedPhase(onNext: () -> Unit, onRestart: () -> Unit) {
    com.chessmaster.play.ui.screens.ResultDialog(
        title = "🎉 Lesson Completed!",
        titleColor = Color(0xFFFFD700),
        subtitle = "+100 XP | +50 Coins",
        primaryButtonText = "▶ NEXT LESSON",
        primaryButtonColor = Color(0xFF00C853),
        onPrimaryAction = onNext,
        secondaryButtonText = "⟳ PLAY AGAIN",
        secondaryButtonColor = Color(0xFF1976D2),
        onSecondaryAction = onRestart
    )
}
