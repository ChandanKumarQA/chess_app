package com.chessmaster.play.ui.components.adventure

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FormatListBulleted
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.roundToInt
import kotlin.math.sin

/**
 * Adventure Journey Map representing 100 levels along a scenic serpentine S-curve.
 * Features:
 * - Isometric checkered lawn background
 * - Stepping stone trail connecting levels
 * - Scenic illustrative landmarks (ponds, cozy cabins, benches, apple trees, milestone chests)
 * - 3D hedge stepping stones with checkmarks & white pawn pin on the current level
 * - Floating bottom action dock with score, progress bar, "Solve Puzzles" CTA button,
 *   view switcher (Map <-> Grid), and quick jump-to-level button.
 */

@Composable
fun AdventureLevelMap(
    totalLevels: Int = 100,
    highestUnlockedLevel: Int,
    currentSelectedLevel: Int = highestUnlockedLevel,
    scoreOrXpText: String = "XP 1,450",
    ratingSubtitle: String = "Rating 1200",
    onSelectLevel: (Int) -> Unit,
    onPlayCurrentLevel: () -> Unit,
    onToggleGridView: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    val screenWidthDp = configuration.screenWidthDp.dp
    val stepHeightDp = 92.dp
    val bottomDockSpaceDp = 50.dp
    val topPeakSpaceDp = 70.dp
    val totalMapHeightDp = (stepHeightDp * totalLevels) + bottomDockSpaceDp + topPeakSpaceDp

    // Calculate Y scroll position for a given level (Level 1 near bottom, Level 100 near top)
    fun getLevelScrollPositionPx(level: Int): Int {
        val clamped = level.coerceIn(1, totalLevels)
        val levelYDp = totalMapHeightDp - bottomDockSpaceDp - (stepHeightDp * (clamped - 1))
        val targetScrollDp = levelYDp - 300.dp
        return with(density) { targetScrollDp.coerceAtLeast(0.dp).toPx().roundToInt() }
    }

    // Auto-scroll to active level on launch
    LaunchedEffect(highestUnlockedLevel) {
        val targetPx = getLevelScrollPositionPx(highestUnlockedLevel)
        scrollState.scrollTo(targetPx)
    }

    Box(modifier = modifier.fillMaxSize().background(Color(0xFF677C6A))) {
        // Scrollable Adventure Map Container
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Background Canvas: Meadow checkered tiles + trail stones
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(totalMapHeightDp)
            ) {
                drawMeadowCheckeredBackground(size.width, size.height)
                drawTrailConnectingStones(
                    totalLevels = totalLevels,
                    highestUnlockedLevel = highestUnlockedLevel,
                    screenWidth = size.width,
                    totalHeight = size.height,
                    stepHeight = stepHeightDp.toPx(),
                    bottomPadding = bottomDockSpaceDp.toPx()
                )
            }

            // Map Nodes & Scenic Landmarks Layout
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(totalMapHeightDp)
            ) {
                val screenWidthPx = with(density) { screenWidthDp.toPx() }
                val amplitudePx = screenWidthPx * 0.28f
                val period = 9.0 // Period of sin wave in levels

                for (level in 1..totalLevels) {
                    val isCompleted = level < highestUnlockedLevel
                    val isCurrent = level == highestUnlockedLevel
                    val state = when {
                        isCompleted -> LevelNodeState.COMPLETED
                        isCurrent -> LevelNodeState.CURRENT
                        else -> LevelNodeState.LOCKED
                    }

                    val levelT = (level - 1).toDouble()
                    val sinVal = sin((2.0 * PI * levelT) / period)
                    val nodeXPx = (screenWidthPx / 2f) + (amplitudePx * sinVal.toFloat())
                    val nodeYDp = totalMapHeightDp - bottomDockSpaceDp - (stepHeightDp * (level - 1))

                    val nodeXDp = with(density) { nodeXPx.toDp() }

                    // Place Scenic Landmark at outer curves & trees along path
                    LandmarkForLevel(level = level, nodeXDp = nodeXDp, nodeYDp = nodeYDp, screenWidthDp = screenWidthDp)

                    // Stepping Stone Level Node
                    AdventureLevelNode(
                        level = level,
                        state = state,
                        onClick = {
                            if (level <= highestUnlockedLevel) {
                                onSelectLevel(level)
                            }
                        },
                        modifier = Modifier
                            .offset(x = nodeXDp - 34.dp, y = nodeYDp - 40.dp)
                    )
                }

                // Summit Peak Banner at Level 100
                SummitPeakBanner(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 20.dp)
                )
            }
        }
    }
}

/**
 * Places scenic landmarks, trees, pine trees, bushes, flowers, mushrooms, ponds, cabins, benches,
 * goats, castles, monuments, and flags according to EASY, MODERATE, and HARD world biomes.
 */
@Composable
private fun LandmarkForLevel(
    level: Int,
    nodeXDp: Dp,
    nodeYDp: Dp,
    screenWidthDp: Dp
) {
    val isLeftOfCenter = nodeXDp < (screenWidthDp / 2)
    val leftX = (nodeXDp - 75.dp).coerceAtLeast(10.dp)
    val rightX = (nodeXDp + 65.dp).coerceAtMost(screenWidthDp - 75.dp)
    val decorX = if (isLeftOfCenter) rightX else leftX
    val oppX = if (isLeftOfCenter) leftX else rightX

    when {
        // === EASY WORLD (Levels 1 - 35): Peaceful Friendly Forest ===
        level <= 35 -> {
            when (level) {
                5 -> ScenicDaisyFlowers(modifier = Modifier.offset(x = decorX, y = nodeYDp - 10.dp))
                10 -> ScenicMilestoneChest(modifier = Modifier.offset(x = oppX, y = nodeYDp - 22.dp))
                15 -> ScenicPondWithFrog(modifier = Modifier.offset(x = decorX, y = nodeYDp - 30.dp))
                21 -> ScenicWoodenBench(modifier = Modifier.offset(x = oppX, y = nodeYDp - 20.dp))
                28 -> ScenicCozyCabin(modifier = Modifier.offset(x = decorX, y = nodeYDp - 35.dp))
            }
            when (level % 4) {
                1 -> {
                    ScenicAppleTree(modifier = Modifier.offset(x = decorX, y = nodeYDp - 25.dp))
                    ScenicBushCluster(modifier = Modifier.offset(x = oppX, y = nodeYDp + 10.dp))
                }
                2 -> {
                    ScenicDaisyFlowers(modifier = Modifier.offset(x = oppX, y = nodeYDp - 15.dp))
                    ScenicMushroomCluster(modifier = Modifier.offset(x = decorX, y = nodeYDp + 5.dp))
                }
                3 -> {
                    ScenicAppleTree(modifier = Modifier.offset(x = oppX, y = nodeYDp - 25.dp))
                }
                0 -> {
                    ScenicBushCluster(modifier = Modifier.offset(x = decorX, y = nodeYDp - 10.dp))
                    ScenicDaisyFlowers(modifier = Modifier.offset(x = oppX, y = nodeYDp + 5.dp))
                }
            }
        }

        // === MODERATE WORLD (Levels 36 - 70): Adventurous Foothills & Pine Ruins ===
        level in 36..70 -> {
            when (level) {
                38 -> ScenicWhiteCastleTower(modifier = Modifier.offset(x = decorX, y = nodeYDp - 35.dp))
                45 -> ScenicWoodenBridgeOverStream(modifier = Modifier.offset(x = oppX, y = nodeYDp - 25.dp))
                52 -> ScenicAncientRuins(modifier = Modifier.offset(x = decorX, y = nodeYDp - 30.dp))
                60 -> ScenicWaterfallCliff(modifier = Modifier.offset(x = oppX, y = nodeYDp - 35.dp))
                65 -> ScenicMilestoneChest(modifier = Modifier.offset(x = decorX, y = nodeYDp - 22.dp))
            }
            when (level % 4) {
                1 -> {
                    ScenicPineTree(modifier = Modifier.offset(x = decorX, y = nodeYDp - 20.dp))
                    ScenicStoneMonumentWithFlag(modifier = Modifier.offset(x = oppX, y = nodeYDp + 10.dp))
                }
                2 -> {
                    ScenicStoneMonumentWithFlag(modifier = Modifier.offset(x = oppX, y = nodeYDp - 25.dp))
                    ScenicPineTree(modifier = Modifier.offset(x = decorX, y = nodeYDp + 5.dp))
                }
                3 -> {
                    ScenicPineTree(modifier = Modifier.offset(x = oppX, y = nodeYDp - 20.dp))
                }
                0 -> {
                    ScenicStoneMonumentWithFlag(modifier = Modifier.offset(x = decorX, y = nodeYDp - 25.dp))
                    ScenicPineTree(modifier = Modifier.offset(x = oppX, y = nodeYDp + 5.dp))
                }
            }
        }

        // === HARD WORLD (Levels 71 - 100): Dramatic Mountain Summit & Dragon Monuments ===
        else -> {
            when (level) {
                75 -> ScenicMilestoneChest(modifier = Modifier.offset(x = oppX, y = nodeYDp - 22.dp))
                80 -> ScenicRockyMountainWithGoat(modifier = Modifier.offset(x = decorX, y = nodeYDp - 40.dp))
                88 -> ScenicDragonStoneStatue(modifier = Modifier.offset(x = oppX, y = nodeYDp - 30.dp))
                95 -> ScenicRockyMountainWithGoat(modifier = Modifier.offset(x = decorX, y = nodeYDp - 40.dp))
                100 -> ScenicMilestoneChest(modifier = Modifier.offset(x = oppX, y = nodeYDp - 22.dp))
            }
            when (level % 4) {
                1 -> {
                    ScenicGlowingCrystalMushrooms(modifier = Modifier.offset(x = decorX, y = nodeYDp - 15.dp))
                    ScenicDragonStoneStatue(modifier = Modifier.offset(x = oppX, y = nodeYDp + 10.dp))
                }
                2 -> {
                    ScenicPineTree(modifier = Modifier.offset(x = oppX, y = nodeYDp - 20.dp))
                    ScenicGlowingCrystalMushrooms(modifier = Modifier.offset(x = decorX, y = nodeYDp + 5.dp))
                }
                3 -> {
                    ScenicDragonStoneStatue(modifier = Modifier.offset(x = oppX, y = nodeYDp - 25.dp))
                }
                0 -> {
                    ScenicGlowingCrystalMushrooms(modifier = Modifier.offset(x = decorX, y = nodeYDp - 15.dp))
                    ScenicPineTree(modifier = Modifier.offset(x = oppX, y = nodeYDp + 5.dp))
                }
            }
        }
    }
}

/**
 * Draws the checkered forest meadow canvas background with dynamic biome transitions:
 * - EASY WORLD (Levels 1-35): Bright soft sage grass
 * - MODERATE WORLD (Levels 36-70): Deep pine forest green
 * - HARD WORLD (Levels 71-100): Dark mountain slate green
 */
private fun DrawScope.drawMeadowCheckeredBackground(width: Float, height: Float) {
    val tileSize = 70f
    val grassDot = Color(0x33FFFFFF)

    val rows = (height / tileSize).toInt() + 1
    val cols = (width / tileSize).toInt() + 1

    for (r in 0 until rows) {
        val yFrac = 1.0f - (r.toFloat() / rows.toFloat()) // 0.0 at bottom (Level 1), 1.0 at top (Level 100)

        val (color1, color2) = when {
            yFrac < 0.35f -> Color(0xFF6B806E) to Color(0xFF7A9380) // EASY WORLD: Bright soft green
            yFrac < 0.70f -> Color(0xFF4A5F4E) to Color(0xFF58705C) // MODERATE WORLD: Deep pine forest green
            else -> Color(0xFF2C3B32) to Color(0xFF37493E)          // HARD WORLD: Dark mountain slate green
        }

        for (c in 0 until cols) {
            val isEven = (r + c) % 2 == 0
            val x = c * tileSize
            val y = r * tileSize
            drawRect(
                color = if (isEven) color1 else color2,
                topLeft = Offset(x, y),
                size = Size(tileSize, tileSize)
            )

            // Occasional grass blade dots
            if ((r * 7 + c * 13) % 5 == 0) {
                drawCircle(
                    color = grassDot,
                    radius = 2.5f,
                    center = Offset(x + tileSize * 0.4f, y + tileSize * 0.5f)
                )
                drawCircle(
                    color = grassDot,
                    radius = 2.5f,
                    center = Offset(x + tileSize * 0.6f, y + tileSize * 0.45f)
                )
            }
        }
    }
}

/**
 * Draws the stepping stone connector path between consecutive levels with biome color transitions.
 */
private fun DrawScope.drawTrailConnectingStones(
    totalLevels: Int,
    highestUnlockedLevel: Int,
    screenWidth: Float,
    totalHeight: Float,
    stepHeight: Float,
    bottomPadding: Float
) {
    val amplitude = screenWidth * 0.28f
    val period = 9.0

    for (level in 1 until totalLevels) {
        val t1 = (level - 1).toDouble()
        val t2 = level.toDouble()

        val x1 = (screenWidth / 2f) + (amplitude * sin((2.0 * PI * t1) / period).toFloat())
        val y1 = totalHeight - bottomPadding - (stepHeight * (level - 1))

        val x2 = (screenWidth / 2f) + (amplitude * sin((2.0 * PI * t2) / period).toFloat())
        val y2 = totalHeight - bottomPadding - (stepHeight * level)

        val isPathUnlocked = level < highestUnlockedLevel

        // Biome-specific connector stone colors
        val (stoneFill, stoneRim) = when {
            !isPathUnlocked -> Color(0xFF2A3D2F) to Color(0xFF1E2B21)
            level <= 35 -> Color(0xFF4CAF50) to Color(0xFF81C784)    // EASY: Bright green
            level <= 70 -> Color(0xFF388E3C) to Color(0xFF66BB6A)    // MODERATE: Forest green
            else -> Color(0xFF1B5E20) to Color(0xFF4CAF50)           // HARD: Emerald green
        }

        // Draw 3 stepping stones along the curve connecting level to level+1
        val numStones = 3
        for (s in 1..numStones) {
            val frac = s.toFloat() / (numStones + 1)
            val sx = x1 + (x2 - x1) * frac
            val sy = y1 + (y2 - y1) * frac

            // Shadow
            drawOval(
                color = Color(0x33000000),
                topLeft = Offset(sx - 7f, sy - 2f),
                size = Size(14f, 8f)
            )
            // Stone base
            drawOval(
                color = stoneFill,
                topLeft = Offset(sx - 6f, sy - 5f),
                size = Size(12f, 7f)
            )
            // Highlight rim
            drawOval(
                color = stoneRim,
                topLeft = Offset(sx - 6f, sy - 5f),
                size = Size(12f, 7f),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1f)
            )
        }
    }
}

/**
 * Top Summit Peak Banner indicating the completion of the 100-level journey.
 */
@Composable
private fun SummitPeakBanner(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFF2E1C0A),
        border = androidx.compose.foundation.BorderStroke(2.dp, Color(0xFFFFD700)),
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.EmojiEvents,
                contentDescription = "Grandmaster Peak",
                tint = Color(0xFFFFD700),
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "GRANDMASTER SUMMIT",
                    color = Color(0xFFFFD700),
                    fontWeight = FontWeight.Black,
                    fontSize = 14.sp,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Level 100 • Ultimate Challenge",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 11.sp
                )
            }
        }
    }
}

/**
 * Floating Bottom Action Dock matching the reference screenshot:
 * - Rating/XP score pill
 * - Progress bar
 * - View toggle (Map vs Grid)
 * - Big vibrant green "Solve Puzzles" button
 */

