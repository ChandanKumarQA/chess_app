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
    val coroutineScope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    val screenWidthDp = configuration.screenWidthDp.dp
    val stepHeightDp = 92.dp
    val bottomDockSpaceDp = 150.dp
    val topPeakSpaceDp = 120.dp
    val totalMapHeightDp = (stepHeightDp * totalLevels) + bottomDockSpaceDp + topPeakSpaceDp

    // Calculate Y scroll position for a given level (Level 1 is near bottom, Level 100 near top)
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

                    // Place Scenic Landmark at outer curves
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
                        .padding(top = 70.dp)
                )
            }
        }

        // Top Header Translucent Pill ({17} Puzzles)
        Surface(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp),
            shape = RoundedCornerShape(20.dp),
            color = Color(0xCC2A342C)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF4A5A4D)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Extension,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(14.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text("Puzzles", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
            }
        }

        // Floating Overlays above Bottom Dock:
        // Bottom Left Score & Progress Bar
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 125.dp)
        ) {
            Text(
                text = "14,031",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .width(160.dp)
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFF223024))
                ) {
                    val frac = (highestUnlockedLevel.toFloat() / totalLevels.toFloat()).coerceIn(0.08f, 1f)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(frac)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF86CC4C))
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                // Badge {18}
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color(0xFF2A362D),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF4A5A4D))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Extension,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "$highestUnlockedLevel",
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // Floating Jump-To-Current-Level Button (Bottom Right)
        FloatingActionButton(
            onClick = {
                coroutineScope.launch {
                    val targetPx = getLevelScrollPositionPx(highestUnlockedLevel)
                    scrollState.animateScrollTo(targetPx)
                }
            },
            containerColor = Color(0xFF2D3830),
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 125.dp)
                .size(46.dp)
                .shadow(8.dp, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Scroll to Active Level",
                modifier = Modifier.size(28.dp)
            )
        }

        // Floating Bottom Action Dock & Bottom Nav
        AdventureBottomDock(
            scoreOrXpText = scoreOrXpText,
            ratingSubtitle = ratingSubtitle,
            currentLevel = highestUnlockedLevel,
            totalLevels = totalLevels,
            onPlay = onPlayCurrentLevel,
            onToggleGridView = onToggleGridView,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

/**
 * Checks if a scenic landmark should be placed near this level and positions it on the outer bend.
 */
@Composable
private fun LandmarkForLevel(
    level: Int,
    nodeXDp: Dp,
    nodeYDp: Dp,
    screenWidthDp: Dp
) {
    val isLeftOfCenter = nodeXDp < (screenWidthDp / 2)

    when (level) {
        5 -> {
            ScenicDaisyFlowers(
                modifier = Modifier.offset(
                    x = if (isLeftOfCenter) nodeXDp + 60.dp else nodeXDp - 70.dp,
                    y = nodeYDp - 10.dp
                )
            )
        }
        7 -> {
            ScenicWhiteCastleTower(
                modifier = Modifier.offset(
                    x = if (isLeftOfCenter) nodeXDp + 65.dp else nodeXDp - 95.dp,
                    y = nodeYDp - 35.dp
                )
            )
        }
        9 -> {
            ScenicAppleTree(
                modifier = Modifier.offset(
                    x = if (isLeftOfCenter) nodeXDp + 68.dp else nodeXDp - 88.dp,
                    y = nodeYDp - 25.dp
                )
            )
        }
        12 -> {
            ScenicRockyMountainWithGoat(
                modifier = Modifier.offset(
                    x = if (isLeftOfCenter) nodeXDp + 70.dp else nodeXDp - 98.dp,
                    y = nodeYDp - 40.dp
                )
            )
        }
        15 -> {
            ScenicPondWithFrog(
                modifier = Modifier.offset(
                    x = if (isLeftOfCenter) nodeXDp + 65.dp else nodeXDp - 95.dp,
                    y = nodeYDp - 30.dp
                )
            )
        }
        21 -> {
            ScenicWoodenBench(
                modifier = Modifier.offset(
                    x = if (isLeftOfCenter) nodeXDp + 65.dp else nodeXDp - 88.dp,
                    y = nodeYDp - 20.dp
                )
            )
        }
        25 -> {
            ScenicMilestoneChest(
                modifier = Modifier.offset(
                    x = if (isLeftOfCenter) nodeXDp + 64.dp else nodeXDp - 84.dp,
                    y = nodeYDp - 22.dp
                )
            )
        }
        33 -> {
            ScenicCozyCabin(
                modifier = Modifier.offset(
                    x = if (isLeftOfCenter) nodeXDp + 68.dp else nodeXDp - 96.dp,
                    y = nodeYDp - 35.dp
                )
            )
        }
        42 -> {
            ScenicWhiteCastleTower(
                modifier = Modifier.offset(
                    x = if (isLeftOfCenter) nodeXDp + 68.dp else nodeXDp - 88.dp,
                    y = nodeYDp - 25.dp
                )
            )
        }
        50 -> {
            ScenicMilestoneChest(
                modifier = Modifier.offset(
                    x = if (isLeftOfCenter) nodeXDp + 64.dp else nodeXDp - 84.dp,
                    y = nodeYDp - 22.dp
                )
            )
        }
        59 -> {
            ScenicPondWithFrog(
                modifier = Modifier.offset(
                    x = if (isLeftOfCenter) nodeXDp + 65.dp else nodeXDp - 95.dp,
                    y = nodeYDp - 30.dp
                )
            )
        }
        68 -> {
            ScenicWoodenBench(
                modifier = Modifier.offset(
                    x = if (isLeftOfCenter) nodeXDp + 65.dp else nodeXDp - 88.dp,
                    y = nodeYDp - 20.dp
                )
            )
        }
        75 -> {
            ScenicMilestoneChest(
                modifier = Modifier.offset(
                    x = if (isLeftOfCenter) nodeXDp + 64.dp else nodeXDp - 84.dp,
                    y = nodeYDp - 22.dp
                )
            )
        }
        84 -> {
            ScenicCozyCabin(
                modifier = Modifier.offset(
                    x = if (isLeftOfCenter) nodeXDp + 68.dp else nodeXDp - 96.dp,
                    y = nodeYDp - 35.dp
                )
            )
        }
        93 -> {
            ScenicRockyMountainWithGoat(
                modifier = Modifier.offset(
                    x = if (isLeftOfCenter) nodeXDp + 68.dp else nodeXDp - 88.dp,
                    y = nodeYDp - 25.dp
                )
            )
        }
        100 -> {
            ScenicMilestoneChest(
                modifier = Modifier.offset(
                    x = if (isLeftOfCenter) nodeXDp + 64.dp else nodeXDp - 84.dp,
                    y = nodeYDp - 22.dp
                )
            )
        }
    }
}

/**
 * Draws the checkered forest meadow canvas background with subtle grass variations.
 */
private fun DrawScope.drawMeadowCheckeredBackground(width: Float, height: Float) {
    val tileSize = 70f
    val color1 = Color(0xFF6B806E)
    val color2 = Color(0xFF7A9380)
    val grassDot = Color(0x33FFFFFF)

    val rows = (height / tileSize).toInt() + 1
    val cols = (width / tileSize).toInt() + 1

    for (r in 0 until rows) {
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
 * Draws the stepping stone connector path between consecutive levels.
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
        val stoneFill = if (isPathUnlocked) Color(0xFF4CAF50) else Color(0xFF2A3D2F)
        val stoneRim = if (isPathUnlocked) Color(0xFF81C784) else Color(0xFF1E2B21)

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
@Composable
fun AdventureBottomDock(
    scoreOrXpText: String,
    ratingSubtitle: String,
    currentLevel: Int,
    totalLevels: Int,
    onPlay: () -> Unit,
    onToggleGridView: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color(0xDD121A14), Color(0xFF121A14))
                )
            )
            .padding(top = 8.dp)
    ) {
        // Dock Row: [≡] List Button + Solve Puzzles CTA Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Square List / Grid View Switcher Button [≡]
            IconButton(
                onClick = onToggleGridView,
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFF2A362D))
                    .border(1.dp, Color(0xFF3B4D3F), RoundedCornerShape(14.dp))
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.FormatListBulleted,
                    contentDescription = "View Modes",
                    tint = Color.White,
                    modifier = Modifier.size(26.dp)
                )
            }

            // Big Vibrant Green CTA Button ("Solve Puzzles")
            Button(
                onClick = onPlay,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7CB342),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp, pressedElevation = 2.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Text(
                    text = "Solve Puzzles",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Bottom Navigation Bar (5 Items: Home, Puzzles, Learn, Watch, More)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF141C16))
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(icon = Icons.Default.Person, label = "Home", isSelected = false)
            BottomNavItem(icon = Icons.Default.Extension, label = "Puzzles", isSelected = true)
            BottomNavItem(icon = Icons.Default.School, label = "Learn", isSelected = false)
            BottomNavItem(icon = Icons.Default.Visibility, label = "Watch", isSelected = false)
            BottomNavItem(icon = Icons.Default.Menu, label = "More", isSelected = false)
        }
    }
}

@Composable
private fun BottomNavItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) Color.White else Color(0xFF7E8F81),
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            color = if (isSelected) Color.White else Color(0xFF7E8F81),
            fontSize = 10.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}
