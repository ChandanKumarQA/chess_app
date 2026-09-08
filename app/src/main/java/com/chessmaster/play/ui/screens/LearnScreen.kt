package com.chessmaster.play.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chessmaster.play.ui.components.BannerAd
import com.chessmaster.play.model.BoardState
import com.chessmaster.play.model.Piece
import com.chessmaster.play.model.PieceColor
import com.chessmaster.play.model.PieceType
import com.chessmaster.play.model.Square
import com.chessmaster.play.ui.components.ChessBoard
import com.chessmaster.play.viewmodel.LearnViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnScreen(
    onBack: () -> Unit,
    onLessonSelected: (String) -> Unit,
    viewModel: LearnViewModel = viewModel()
) {
    var selectedPieceType by remember { mutableStateOf(PieceType.KNIGHT) }
    
    val darkBg = MaterialTheme.colorScheme.background
    val textPrimary = MaterialTheme.colorScheme.onBackground
    val textSecondary = MaterialTheme.colorScheme.onSurfaceVariant
    val gold = Color(0xFFFFD700)
    
    // Generate board state
    val centerSquare = Square(3, 3) // d4
    val board = mapOf(centerSquare to Piece(selectedPieceType, PieceColor.WHITE))
    val boardState = BoardState(board = board)
    
    // Generate arrows based on piece type
    val arrows = remember(selectedPieceType) {
        getArrowsForPiece(selectedPieceType, centerSquare)
    }
    
    val description = when (selectedPieceType) {
        PieceType.KNIGHT -> "The Knight (Ghoda) moves in an 'L' shape: two squares in one direction and one square at a 90-degree angle. It is the only piece that can jump over others."
        PieceType.KING -> "The King (Raja) can move exactly one square in any direction: horizontally, vertically, or diagonally."
        PieceType.QUEEN -> "The Queen (Mantri/Wazir) is the most powerful piece. It can move any number of squares in any direction: horizontally, vertically, or diagonally."
        PieceType.ROOK -> "The Rook (Haathi) moves any number of squares horizontally or vertically."
        PieceType.BISHOP -> "The Bishop (Oont) moves any number of squares diagonally. It always stays on its starting color."
        PieceType.PAWN -> "The Pawn (Pyada) moves forward one square, but captures diagonally. On its first move, it can move forward two squares."
    }

    val progress by viewModel.repository.progress.collectAsState()
    val categories = viewModel.repository.getAllCategories()

    Scaffold(
        containerColor = darkBg
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = androidx.compose.ui.graphics.Color.White)
                }
                Text("Learn Chess", color = textPrimary, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
            
            // Piece Selector
            val pieces = PieceType.values().toList()
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 24.dp)
            ) {
                items(pieces) { type ->
                    val isSelected = selectedPieceType == type
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isSelected) gold else MaterialTheme.colorScheme.surfaceVariant)
                            .clickable { selectedPieceType = type }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = type.name.lowercase().replaceFirstChar { it.uppercase() },
                            color = if (isSelected) Color.Black else textSecondary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            
            // Board
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
                    lastMove = null,
                    isCheck = false,
                    currentTurn = PieceColor.WHITE,
                    onSquareClicked = {},
                    arrows = arrows,
                    modifier = Modifier.fillMaxSize()
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Description
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "How it moves",
                        color = gold,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = description,
                        color = textPrimary,
                        fontSize = 16.sp,
                        lineHeight = 24.sp
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))

            // Categories & Lessons
            categories.forEach { category ->
                Text(
                    text = category.title,
                    color = textPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
                
                category.lessons.forEach { lesson ->
                    val isCompleted = progress.completedLessonIds.contains(lesson.id)
                    LessonItem(
                        title = lesson.title,
                        description = lesson.difficulty + " • " + lesson.estimatedTime,
                        isCompleted = isCompleted,
                        onClick = { onLessonSelected(lesson.id) }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            BannerAd(modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun LessonItem(title: String, description: String, isCompleted: Boolean, onClick: () -> Unit) {
    val bgColor = if (isCompleted) Color(0xFF1B5E20).copy(alpha = 0.3f) else MaterialTheme.colorScheme.surfaceVariant
    
    Card(
        colors = CardDefaults.cardColors(containerColor = bgColor),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(title, color = MaterialTheme.colorScheme.onBackground, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(description, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
            }
            SquareCheckbox(isChecked = isCompleted)
        }
    }
}

@Composable
fun SquareCheckbox(isChecked: Boolean, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(28.dp)
            .background(Color(0xFF5D2906), RoundedCornerShape(4.dp)) // Dark wood color
            .border(2.dp, Color(0xFF3E1A04), RoundedCornerShape(4.dp)), // Even darker border
        contentAlignment = Alignment.Center
    ) {
        if (isChecked) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Completed",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

fun getArrowsForPiece(type: PieceType, from: Square): List<Pair<Square, Square>> {
    val arrows = mutableListOf<Pair<Square, Square>>()
    when (type) {
        PieceType.KING -> {
            val directions = listOf(
                Pair(0, 1), Pair(1, 1), Pair(1, 0), Pair(1, -1),
                Pair(0, -1), Pair(-1, -1), Pair(-1, 0), Pair(-1, 1)
            )
            for (dir in directions) {
                val f = from.file + dir.first
                val r = from.rank + dir.second
                if (f in 0..7 && r in 0..7) arrows.add(Pair(from, Square(f, r)))
            }
        }
        PieceType.KNIGHT -> {
            val jumps = listOf(
                Pair(1, 2), Pair(2, 1), Pair(2, -1), Pair(1, -2),
                Pair(-1, -2), Pair(-2, -1), Pair(-2, 1), Pair(-1, 2)
            )
            for (jump in jumps) {
                val f = from.file + jump.first
                val r = from.rank + jump.second
                if (f in 0..7 && r in 0..7) arrows.add(Pair(from, Square(f, r)))
            }
        }
        PieceType.ROOK -> {
            arrows.add(Pair(from, Square(from.file, 7)))
            arrows.add(Pair(from, Square(from.file, 0)))
            arrows.add(Pair(from, Square(7, from.rank)))
            arrows.add(Pair(from, Square(0, from.rank)))
        }
        PieceType.BISHOP -> {
            val diff1 = minOf(7 - from.file, 7 - from.rank)
            arrows.add(Pair(from, Square(from.file + diff1, from.rank + diff1)))
            val diff2 = minOf(from.file, from.rank)
            arrows.add(Pair(from, Square(from.file - diff2, from.rank - diff2)))
            val diff3 = minOf(from.file, 7 - from.rank)
            arrows.add(Pair(from, Square(from.file - diff3, from.rank + diff3)))
            val diff4 = minOf(7 - from.file, from.rank)
            arrows.add(Pair(from, Square(from.file + diff4, from.rank - diff4)))
        }
        PieceType.QUEEN -> {
            arrows.add(Pair(from, Square(from.file, 7)))
            arrows.add(Pair(from, Square(from.file, 0)))
            arrows.add(Pair(from, Square(7, from.rank)))
            arrows.add(Pair(from, Square(0, from.rank)))
            
            val diff1 = minOf(7 - from.file, 7 - from.rank)
            arrows.add(Pair(from, Square(from.file + diff1, from.rank + diff1)))
            val diff2 = minOf(from.file, from.rank)
            arrows.add(Pair(from, Square(from.file - diff2, from.rank - diff2)))
            val diff3 = minOf(from.file, 7 - from.rank)
            arrows.add(Pair(from, Square(from.file - diff3, from.rank + diff3)))
            val diff4 = minOf(7 - from.file, from.rank)
            arrows.add(Pair(from, Square(from.file + diff4, from.rank - diff4)))
        }
        PieceType.PAWN -> {
            arrows.add(Pair(from, Square(from.file, from.rank + 1)))
            arrows.add(Pair(from, Square(from.file, from.rank + 2)))
        }
    }
    return arrows.distinct().filter { it.first != it.second }
}
