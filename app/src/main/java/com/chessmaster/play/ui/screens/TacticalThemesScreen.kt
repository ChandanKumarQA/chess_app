package com.chessmaster.play.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import com.chessmaster.play.data.PuzzleRepository
import com.chessmaster.play.model.Puzzle
import com.chessmaster.play.ui.components.adventure.AdventureLevelMap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TacticalThemesScreen(onBack: () -> Unit, onPlayPuzzle: () -> Unit) {
    val context = LocalContext.current
    var selectedCategory by remember { mutableStateOf<String?>(PuzzleRepository.currentTacticalCategory) }
    val highestUnlockedLevel = if (selectedCategory != null) {
        com.chessmaster.play.data.LocalLeaderboardManager.getHighestUnlockedThemeLevel(context, selectedCategory!!)
    } else 1
    var isGridView by remember { mutableStateOf(false) }
    
    val darkBg = MaterialTheme.colorScheme.background
    val cardBg = MaterialTheme.colorScheme.surfaceVariant
    val textPrimary = MaterialTheme.colorScheme.onBackground
    val textSecondary = MaterialTheme.colorScheme.onSurfaceVariant
    val accentColor = Color(0xFF2196F3)

    Scaffold(containerColor = darkBg) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Header Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    if (selectedCategory != null) {
                        selectedCategory = null
                        PuzzleRepository.currentTacticalCategory = null
                    } else {
                        onBack()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = selectedCategory ?: "Tactical Themes",
                        color = textPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if (selectedCategory != null) {
                        Text(
                            text = "Level $highestUnlockedLevel / 100 • Tactical Journey",
                            color = Color(0xFF81C784),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                // If in category view, allow toggling Map <-> Grid
                if (selectedCategory != null) {
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

            if (selectedCategory == null) {
                // Category List View
                val categories = PuzzleRepository.getAllTacticalCategories()
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(categories.size) { index ->
                        val category = categories[index]
                        val categoryPuzzles = PuzzleRepository.getPuzzlesByCategory(category)
                        val unlockedLvl = com.chessmaster.play.data.LocalLeaderboardManager.getHighestUnlockedThemeLevel(context, category)

                        CategoryItem(
                            title = category,
                            difficulty = "Easy • Moderate • Hard",
                            puzzleCount = categoryPuzzles.size,
                            completion = ((unlockedLvl - 1).coerceAtLeast(0) * 100) / categoryPuzzles.size.coerceAtLeast(1),
                            bestScore = unlockedLvl,
                            cardBg = cardBg,
                            textPrimary = textPrimary,
                            textSecondary = textSecondary,
                            accentColor = accentColor,
                            isLocked = false,
                            onClick = { 
                                selectedCategory = category
                                PuzzleRepository.currentTacticalCategory = category
                            }
                        )
                    }
                }
            } else {
                // Selected Category Level View
                val puzzles = PuzzleRepository.getPuzzlesByCategory(selectedCategory!!)
                if (puzzles.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No puzzles available in this category yet.", color = textSecondary)
                    }
                } else {
                    val totalLevels = puzzles.size.coerceAtLeast(1)

                    if (!isGridView) {
                        // Adventure Journey Map View
                        AdventureLevelMap(
                            totalLevels = totalLevels,
                            highestUnlockedLevel = highestUnlockedLevel,
                            scoreOrXpText = selectedCategory ?: "Tactics",
                            ratingSubtitle = "Level $highestUnlockedLevel / $totalLevels",
                            onSelectLevel = { level ->
                                val targetPuzzle = puzzles.getOrNull(level - 1)
                                if (targetPuzzle != null) {
                                    PuzzleRepository.currentTacticalCategory = selectedCategory
                                    PuzzleRepository.activePlayPuzzle = targetPuzzle
                                    onPlayPuzzle()
                                }
                            },
                            onPlayCurrentLevel = {
                                val currentPuzzle = puzzles.getOrNull(highestUnlockedLevel - 1) ?: puzzles.firstOrNull()
                                if (currentPuzzle != null) {
                                    PuzzleRepository.currentTacticalCategory = selectedCategory
                                    PuzzleRepository.activePlayPuzzle = currentPuzzle
                                    onPlayPuzzle()
                                }
                            },
                            onToggleGridView = { isGridView = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        )
                    } else {
                        // Classic Grid View
                        val completedBg = Color(0xFF4E342E)
                        val easyPuzzles = puzzles.take(35)
                        val moderatePuzzles = puzzles.drop(35).take(35)
                        val hardPuzzles = puzzles.drop(70)

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
                                itemsIndexed(easyPuzzles) { index, puzzle ->
                                    val level = index + 1
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
                                                    PuzzleRepository.currentTacticalCategory = selectedCategory
                                                    PuzzleRepository.activePlayPuzzle = puzzle
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
                                itemsIndexed(moderatePuzzles) { index, puzzle ->
                                    val level = 35 + index + 1
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
                                                    PuzzleRepository.currentTacticalCategory = selectedCategory
                                                    PuzzleRepository.activePlayPuzzle = puzzle
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
                                itemsIndexed(hardPuzzles) { index, puzzle ->
                                    val level = 70 + index + 1
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
                                                    PuzzleRepository.currentTacticalCategory = selectedCategory
                                                    PuzzleRepository.activePlayPuzzle = puzzle
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

                                item(span = { GridItemSpan(maxLineSpan) }) {
                                    Spacer(modifier = Modifier.height(28.dp))
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
fun CategoryItem(
    title: String,
    difficulty: String,
    puzzleCount: Int,
    completion: Int,
    bestScore: Int,
    cardBg: Color,
    textPrimary: Color,
    textSecondary: Color,
    accentColor: Color,
    isLocked: Boolean = false,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(enabled = !isLocked, onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = if (isLocked) cardBg.copy(alpha = 0.5f) else cardBg)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(accentColor.copy(alpha = 0.2f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Category, contentDescription = null, tint = accentColor)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, color = textPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text("Diff: $difficulty • $puzzleCount Puzzles", color = textSecondary, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(4.dp))
                LinearProgressIndicator(
                    progress = { completion / 100f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp)),
                    color = accentColor,
                    trackColor = Color.DarkGray
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            if (isLocked) {
                Icon(Icons.Default.Lock, contentDescription = "Locked", tint = textSecondary)
            } else {
                Icon(Icons.Default.ChevronRight, contentDescription = "Go", tint = textSecondary)
            }
        }
    }
}

@Composable
fun PuzzleItem(
    puzzle: Puzzle,
    title: String? = null,
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
                Icon(Icons.Default.PlayArrow, contentDescription = null, tint = accentColor)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title ?: "Puzzle ${puzzle.id}", color = textPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text("Rating: ${puzzle.rating} • ${puzzle.xpReward} XP", color = textSecondary, fontSize = 14.sp)
            }
        }
    }
}
