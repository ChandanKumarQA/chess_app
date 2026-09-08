package com.chessmaster.play.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Category
import androidx.compose.foundation.lazy.grid.GridItemSpan
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
import com.chessmaster.play.data.LocalLeaderboardManager
import com.chessmaster.play.data.PuzzleRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PuzzlesScreen(
    onBack: () -> Unit,
    onPlayPuzzle: () -> Unit,
    onTacticalThemes: () -> Unit = {},
    onEndgameTraining: () -> Unit = {},
    onOpeningTraps: () -> Unit = {},
    onPuzzleRush: () -> Unit = {},
    onSurvivalMode: () -> Unit = {}
) {
    val context = LocalContext.current
    
    // Get highest unlocked level
    var highestUnlockedLevel by remember { mutableIntStateOf(1) }
    
    LaunchedEffect(Unit) {
        highestUnlockedLevel = LocalLeaderboardManager.getHighestUnlockedPuzzleLevel(context)
    }

    val darkBg = MaterialTheme.colorScheme.background
    
    // Wooden theme colors
    val woodBg = Color(0xFF3E2723)
    val boardBg = Color(0xFF5D4037)
    val goldBorder = Color(0xFFFFD700)
    
    // Level colors
    val completedBg = Color(0xFF4E342E) // Dark brown
    val unlockedBg = Color(0xFF00C853) // Bright green
    val lockedBg = Color(0xFF039BE5) // Bright cyan-blue
    
    val easyLevels = (1..35).toList()
    val moderateLevels = (36..70).toList()
    val hardLevels = (71..100).toList()

    Scaffold(
        containerColor = darkBg
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(darkBg),
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
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = androidx.compose.ui.graphics.Color.White, modifier = Modifier.size(32.dp))
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    "PUZZLES (100 LEVELS)",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(48.dp)) // Balance the back button
            }

            // Grid Container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(5),
                    contentPadding = PaddingValues(bottom = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    // === EASY SECTION ===
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        PuzzleTierHeader(
                            title = "EASY",
                            subtitle = "Levels 1 - 35 • Rating 600 - 1050",
                            badgeColor = Color(0xFF00C853)
                        )
                    }
                    items(easyLevels) { level ->
                        val isCompleted = level < highestUnlockedLevel
                        val isCurrent = level == highestUnlockedLevel
                        val activeColor = Color(0xFF00C853)

                        val bgColor = when {
                            isCompleted -> completedBg
                            isCurrent -> activeColor
                            else -> Color(0xFF1E293B)
                        }

                        Box(
                            modifier = Modifier
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(bgColor)
                                .border(1.dp, if (isCurrent) activeColor else Color.White.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                                .clickable {
                                    PuzzleRepository.currentPuzzleLevel = level
                                    onPlayPuzzle()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (isCompleted) {
                                Icon(Icons.Default.Check, contentDescription = "Completed", tint = Color(0xFF00C853), modifier = Modifier.size(24.dp))
                            } else {
                                Text(
                                    text = level.toString(),
                                    color = if (isCurrent) Color.White else Color.White.copy(alpha = 0.9f),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    // === MODERATE SECTION ===
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Spacer(modifier = Modifier.height(16.dp))
                        PuzzleTierHeader(
                            title = "MODERATE",
                            subtitle = "Levels 36 - 70 • Rating 1100 - 1650",
                            badgeColor = Color(0xFFFFB300)
                        )
                    }
                    items(moderateLevels) { level ->
                        val isCompleted = level < highestUnlockedLevel
                        val isCurrent = level == highestUnlockedLevel
                        val activeColor = Color(0xFFFFB300)

                        val bgColor = when {
                            isCompleted -> completedBg
                            isCurrent -> activeColor
                            else -> Color(0xFF2A2415)
                        }

                        Box(
                            modifier = Modifier
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(bgColor)
                                .border(1.dp, if (isCurrent) activeColor else Color.White.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                                .clickable {
                                    PuzzleRepository.currentPuzzleLevel = level
                                    onPlayPuzzle()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (isCompleted) {
                                Icon(Icons.Default.Check, contentDescription = "Completed", tint = Color(0xFFFFB300), modifier = Modifier.size(24.dp))
                            } else {
                                Text(
                                    text = level.toString(),
                                    color = if (isCurrent) Color.Black else Color.White.copy(alpha = 0.9f),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    // === HARD SECTION ===
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Spacer(modifier = Modifier.height(16.dp))
                        PuzzleTierHeader(
                            title = "HARD",
                            subtitle = "Levels 71 - 100 • Rating 1700 - 2400",
                            badgeColor = Color(0xFFE53935)
                        )
                    }
                    items(hardLevels) { level ->
                        val isCompleted = level < highestUnlockedLevel
                        val isCurrent = level == highestUnlockedLevel
                        val activeColor = Color(0xFFE53935)

                        val bgColor = when {
                            isCompleted -> completedBg
                            isCurrent -> activeColor
                            else -> Color(0xFF2D1619)
                        }

                        Box(
                            modifier = Modifier
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(bgColor)
                                .border(1.dp, if (isCurrent) activeColor else Color.White.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                                .clickable {
                                    PuzzleRepository.currentPuzzleLevel = level
                                    onPlayPuzzle()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (isCompleted) {
                                Icon(Icons.Default.Check, contentDescription = "Completed", tint = Color(0xFFE53935), modifier = Modifier.size(24.dp))
                            } else {
                                Text(
                                    text = level.toString(),
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Spacer(modifier = Modifier.height(28.dp))
                    }
                    
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Text(
                            text = "Special Modes",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                    
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        SpecialModeItem(
                            icon = Icons.Default.Category,
                            title = "Tactical Themes",
                            subtitle = "Fork, Pin, Skewer...",
                            iconBgColor = Color(0xFF1E3A5F),
                            iconColor = Color(0xFF42A5F5),
                            onClick = onTacticalThemes
                        )
                    }
                    
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        SpecialModeItem(
                            icon = Icons.Default.Flag,
                            title = "Endgame Training",
                            subtitle = "King, Pawn, Rook...",
                            iconBgColor = Color(0xFF3B1E5F),
                            iconColor = Color(0xFFAB47BC),
                            onClick = onEndgameTraining
                        )
                    }
                    
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        SpecialModeItem(
                            icon = Icons.Default.MenuBook,
                            title = "Opening Traps",
                            subtitle = "Scholar's Mate, Fried Liver...",
                            iconBgColor = Color(0xFF3E2723),
                            iconColor = Color(0xFFFFB300),
                            onClick = onOpeningTraps
                        )
                    }
                    
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        SpecialModeItem(
                            icon = Icons.Default.Timer,
                            title = "Puzzle Rush",
                            subtitle = "3 Minute Challenge",
                            iconBgColor = Color(0xFF3E1E2F),
                            iconColor = Color(0xFFE53935),
                            onClick = onPuzzleRush
                        )
                    }
                    
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        SpecialModeItem(
                            icon = Icons.Default.Favorite,
                            title = "Survival Mode",
                            subtitle = "3 Lives • Endless puzzles",
                            iconBgColor = Color(0xFF4E2020),
                            iconColor = Color(0xFFEF5350),
                            onClick = onSurvivalMode
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SpecialModeItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    iconBgColor: Color,
    iconColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF1A1A1A))
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(iconBgColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = title, tint = iconColor, modifier = Modifier.size(24.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(subtitle, color = Color.Gray, fontSize = 12.sp)
        }
        Icon(Icons.Default.ChevronRight, contentDescription = "Go", tint = Color.White)
    }
}

@Composable
fun PuzzleTierHeader(
    title: String,
    subtitle: String,
    badgeColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(width = 4.dp, height = 24.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(badgeColor)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            color = badgeColor,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "• $subtitle",
            color = Color.White.copy(alpha = 0.6f),
            fontSize = 13.sp
        )
    }
}
