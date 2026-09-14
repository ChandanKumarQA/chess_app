package com.chessmaster.play.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.chessmaster.play.ui.components.adventure.AdventureLevelMap

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

    var highestUnlockedLevel by remember { mutableIntStateOf(1) }
    var isGridView by remember { mutableStateOf(false) }
    var showOnlySpecialModes by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        highestUnlockedLevel = LocalLeaderboardManager.getHighestUnlockedPuzzleLevel(context)
    }

    val totalXp = remember(highestUnlockedLevel) {
        LocalLeaderboardManager.getTotalXp(context)
    }
    val estimatedRating = 600 + (highestUnlockedLevel * 18).coerceAtMost(1800)

    val darkBg = MaterialTheme.colorScheme.background
    val completedBg = Color(0xFF4E342E)

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
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = if (isGridView) "PUZZLE LEVELS (100)" else "PUZZLES JOURNEY",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Level $highestUnlockedLevel / 100 • Rating ~$estimatedRating",
                        color = Color(0xFF81C784),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                // Special Modes Toggle Button
                IconButton(
                    onClick = { showOnlySpecialModes = !showOnlySpecialModes },
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (showOnlySpecialModes) Color(0xFF2E7D32) else Color(0xFF1E2A20))
                ) {
                    Icon(
                        imageVector = Icons.Default.Explore,
                        contentDescription = "Special Modes",
                        tint = if (showOnlySpecialModes) Color.White else Color(0xFFFFD54F),
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Toggle View (Map <-> Grid) Button
                if (!showOnlySpecialModes) {
                    IconButton(
                        onClick = { isGridView = !isGridView },
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFF1E2A20))
                    ) {
                        Icon(
                            imageVector = if (isGridView) Icons.Default.Map else Icons.Default.GridView,
                            contentDescription = "Toggle View",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            // Main Content: Special Modes, Adventure Map, or Grid View
            if (showOnlySpecialModes) {
                androidx.compose.foundation.lazy.LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(top = 8.dp, bottom = 24.dp)
                ) {
                    item {
                        Text(
                            text = "Special Modes",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }

                    item {
                        SpecialModeItem(
                            icon = Icons.Default.Category,
                            title = "Tactical Themes",
                            subtitle = "Fork, Pin, Skewer...",
                            iconBgColor = Color(0xFF1E3A5F),
                            iconColor = Color(0xFF42A5F5),
                            onClick = onTacticalThemes
                        )
                    }

                    item {
                        SpecialModeItem(
                            icon = Icons.Default.Flag,
                            title = "Endgame Training",
                            subtitle = "King, Pawn, Rook...",
                            iconBgColor = Color(0xFF3B1E5F),
                            iconColor = Color(0xFFAB47BC),
                            onClick = onEndgameTraining
                        )
                    }

                    item {
                        SpecialModeItem(
                            icon = Icons.Default.MenuBook,
                            title = "Opening Traps",
                            subtitle = "Scholar's Mate, Fried Liver...",
                            iconBgColor = Color(0xFF3E2723),
                            iconColor = Color(0xFFFFB300),
                            onClick = onOpeningTraps
                        )
                    }

                    item {
                        SpecialModeItem(
                            icon = Icons.Default.Timer,
                            title = "Puzzle Rush",
                            subtitle = "3 Minute Challenge",
                            iconBgColor = Color(0xFF3E1E2F),
                            iconColor = Color(0xFFE53935),
                            onClick = onPuzzleRush
                        )
                    }

                    item {
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
            } else if (!isGridView) {
                AdventureLevelMap(
                    totalLevels = 100,
                    highestUnlockedLevel = highestUnlockedLevel,
                    scoreOrXpText = "XP $totalXp",
                    ratingSubtitle = "Rating ~$estimatedRating",
                    onSelectLevel = { level ->
                        PuzzleRepository.currentPuzzleLevel = level
                        onPlayPuzzle()
                    },
                    onPlayCurrentLevel = {
                        PuzzleRepository.currentPuzzleLevel = highestUnlockedLevel
                        onPlayPuzzle()
                    },
                    onToggleGridView = { isGridView = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            } else {
                // Classic 5-Column Grid Container
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
                                    .border(
                                        1.dp,
                                        if (isCurrent) activeColor else Color.White.copy(alpha = 0.15f),
                                        RoundedCornerShape(8.dp)
                                    )
                                    .clickable {
                                        if (level <= highestUnlockedLevel) {
                                            PuzzleRepository.currentPuzzleLevel = level
                                            onPlayPuzzle()
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                if (isCompleted) {
                                    Icon(
                                        Icons.Default.Check,
                                        contentDescription = "Completed",
                                        tint = Color(0xFF00C853),
                                        modifier = Modifier.size(24.dp)
                                    )
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
                                    .border(
                                        1.dp,
                                        if (isCurrent) activeColor else Color.White.copy(alpha = 0.15f),
                                        RoundedCornerShape(8.dp)
                                    )
                                    .clickable {
                                        if (level <= highestUnlockedLevel) {
                                            PuzzleRepository.currentPuzzleLevel = level
                                            onPlayPuzzle()
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                if (isCompleted) {
                                    Icon(
                                        Icons.Default.Check,
                                        contentDescription = "Completed",
                                        tint = Color(0xFFFFB300),
                                        modifier = Modifier.size(24.dp)
                                    )
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
                                    .border(
                                        1.dp,
                                        if (isCurrent) activeColor else Color.White.copy(alpha = 0.15f),
                                        RoundedCornerShape(8.dp)
                                    )
                                    .clickable {
                                        if (level <= highestUnlockedLevel) {
                                            PuzzleRepository.currentPuzzleLevel = level
                                            onPlayPuzzle()
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                if (isCompleted) {
                                    Icon(
                                        Icons.Default.Check,
                                        contentDescription = "Completed",
                                        tint = Color(0xFFE53935),
                                        modifier = Modifier.size(24.dp)
                                    )
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
