package com.chessmaster.play.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Style
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chessmaster.play.data.BoardPreferences
import com.chessmaster.play.model.*
import com.chessmaster.play.ui.components.ChessBoard
import com.chessmaster.play.ui.components.ChessPiece

/**
 * Main "Board settings" screen matching Screenshot 3:
 * - Display section containing ONLY "Board" and "Piece set"
 * - Live Board Preview below
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoardSettingsScreen(
    onBack: () -> Unit,
    onNavigateToBoardPicker: () -> Unit,
    onNavigateToPieceSetPicker: () -> Unit
) {
    val currentTheme by BoardPreferences.boardTheme.collectAsState()
    val currentPieceSet by BoardPreferences.pieceSet.collectAsState()

    val bg = Color(0xFF121418)
    val cardBg = Color(0xFF1E222A)
    val textColor = Color.White
    val secondaryText = Color(0xFF8A93A0)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Board settings",
                        color = textColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = textColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = bg)
            )
        },
        containerColor = bg
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Display Section Header (Screenshot 3)
            Text(
                text = "Display",
                color = secondaryText,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 10.dp)
            )

            // Settings Card containing Board & Piece Set
            Card(
                colors = CardDefaults.cardColors(containerColor = cardBg),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    // Board setting row
                    BoardSettingItemRow(
                        title = "Board",
                        subtitle = currentTheme.name,
                        onClick = onNavigateToBoardPicker,
                        trailingPreview = {
                            BoardMiniStrip(
                                theme = currentTheme,
                                squareCount = 6,
                                height = 24.dp,
                                modifier = Modifier.width(90.dp)
                            )
                        }
                    )

                    HorizontalDivider(color = Color(0xFF2B313D), thickness = 0.8.dp)

                    // Piece set setting row
                    BoardSettingItemRow(
                        title = "Piece set",
                        subtitle = currentPieceSet.displayName,
                        onClick = onNavigateToPieceSetPicker,
                        trailingPreview = {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(currentTheme.lightColor),
                                contentAlignment = Alignment.Center
                            ) {
                                ChessPiece(
                                    piece = Piece(PieceType.KNIGHT, PieceColor.WHITE),
                                    pieceSet = currentPieceSet,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Live Board Preview Section
            Text(
                text = "Live Preview",
                color = secondaryText,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, Color(0xFF2C323E), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                ChessBoard(
                    boardState = BoardState.initial(),
                    selectedSquare = null,
                    legalMoves = emptyList(),
                    lastMove = null,
                    isCheck = false,
                    currentTurn = PieceColor.WHITE,
                    onSquareClicked = { },
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
private fun BoardSettingItemRow(
    title: String,
    subtitle: String,
    trailingPreview: @Composable () -> Unit,
    onClick: () -> Unit
) {
    val haptic = LocalHapticFeedback.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.TextHandleMove)
                onClick()
            }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                color = Color(0xFF8A93A0),
                fontSize = 13.sp,
                maxLines = 1
            )
        }

        trailingPreview()

        Spacer(modifier = Modifier.width(10.dp))

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = Color(0xFF636C7A),
            modifier = Modifier.size(22.dp)
        )
    }
}

/**
 * Board theme selection screen matching Screenshots 4 & 5:
 * Title "Board", with 15 themes displaying 6 alternating squares.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoardThemePickerScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val currentTheme by BoardPreferences.boardTheme.collectAsState()
    val haptic = LocalHapticFeedback.current

    val bg = Color(0xFF121418)
    val textColor = Color.White

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Board",
                        color = textColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = textColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = bg)
            )
        },
        containerColor = bg
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            items(BoardThemes.ALL_THEMES) { theme ->
                val isSelected = theme.id == currentTheme.id

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.TextHandleMove)
                            BoardPreferences.setBoardTheme(context, theme)
                        }
                        .padding(vertical = 12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = theme.name,
                            color = if (isSelected) Color(0xFF4FA4F4) else Color.White,
                            fontSize = 16.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        )

                        if (isSelected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Selected",
                                tint = Color(0xFF4FA4F4),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // 6-Square horizontal preview strip matching Screenshots 4 & 5
                    BoardMiniStrip(
                        theme = theme,
                        squareCount = 6,
                        height = 36.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(4.dp))
                    )
                }

                HorizontalDivider(color = Color(0xFF22262E), thickness = 0.5.dp)
            }
        }
    }
}

/**
 * 6-square preview strip for a board theme (light, dark, light, dark, light, dark)
 */
@Composable
private fun BoardMiniStrip(
    theme: BoardTheme,
    squareCount: Int = 6,
    height: androidx.compose.ui.unit.Dp = 28.dp,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(height)
            .clip(RoundedCornerShape(4.dp))
            .border(1.dp, Color(0x33000000), RoundedCornerShape(4.dp))
    ) {
        for (i in 0 until squareCount) {
            val color = if (i % 2 == 0) theme.lightColor else theme.darkColor
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(color)
            )
        }
    }
}

/**
 * Piece set selection screen matching Screenshot 2:
 * Title "Piece set", with 12 piece sets displaying:
 * White King, Black Queen, White Rook, Black Bishop, White Knight, Black Pawn
 * on an alternating 6-square strip.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PieceSetPickerScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val currentTheme by BoardPreferences.boardTheme.collectAsState()
    val currentPieceSet by BoardPreferences.pieceSet.collectAsState()
    val haptic = LocalHapticFeedback.current

    val bg = Color(0xFF121418)
    val textColor = Color.White

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Piece set",
                        color = textColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = textColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = bg)
            )
        },
        containerColor = bg
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            items(PieceSetId.ALL_SETS) { set ->
                val isSelected = set == currentPieceSet

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.TextHandleMove)
                            BoardPreferences.setPieceSet(context, set)
                        }
                        .padding(vertical = 12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = set.displayName,
                            color = if (isSelected) Color(0xFF4FA4F4) else Color.White,
                            fontSize = 16.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        )

                        if (isSelected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Selected",
                                tint = Color(0xFF4FA4F4),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // 6-Piece preview strip matching Screenshot 4 & 5:
                    // 1: White King, 2: Black Queen, 3: White Rook, 4: Black Bishop, 5: White Knight, 6: Black Pawn
                    PieceSetPreviewRow(
                        pieceSet = set,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(4.dp))
                    )
                }

                HorizontalDivider(color = Color(0xFF22262E), thickness = 0.5.dp)
            }
        }
    }
}

/**
 * 6-piece preview strip matching Screenshot 4 & 5
 * on the standard preview board background
 */
@Composable
private fun PieceSetPreviewRow(
    pieceSet: PieceSetId,
    modifier: Modifier = Modifier
) {
    val pieces = listOf(
        Piece(PieceType.KING, PieceColor.WHITE),
        Piece(PieceType.QUEEN, PieceColor.BLACK),
        Piece(PieceType.ROOK, PieceColor.WHITE),
        Piece(PieceType.BISHOP, PieceColor.BLACK),
        Piece(PieceType.KNIGHT, PieceColor.WHITE),
        Piece(PieceType.PAWN, PieceColor.BLACK)
    )

    // Khaki / olive checkerboard background from screenshots 4 & 5
    val lightSquare = Color(0xFFB5A66E)
    val darkSquare = Color(0xFF8B7D46)

    Row(
        modifier = modifier
            .height(52.dp)
            .border(1.dp, Color(0x33000000), RoundedCornerShape(4.dp))
    ) {
        pieces.forEachIndexed { index, piece ->
            val squareColor = if (index % 2 == 0) lightSquare else darkSquare
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(squareColor)
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                ChessPiece(
                    piece = piece,
                    pieceSet = pieceSet,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
