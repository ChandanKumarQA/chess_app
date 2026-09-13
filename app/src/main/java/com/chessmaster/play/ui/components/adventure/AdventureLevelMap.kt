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
 * - True isometric diamond checkered lawn background
 * - Stepping stone trail connecting levels
 * - 3D isometric landmarks: Cozy Log Cabin (with cat & chicken), Park Bench (with chessboard & propeller pawn),
 *   Pond (with lily pads & frog on rock), Apple & Cypress Trees, White Castle Watchtower,
 *   Mountain Goat on granite cliff, and Ancient Altar with chess pieces.
 * - 3D hedge stepping stones with checkmarks & white pawn pin on current level.
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

    Box(modifier = modifier.fillMaxSize().background(Color(0xFF204526))) {
        // Scrollable Adventure Map Container
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Background Canvas: Isometric diamond checkered meadow + trail stones
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
                    // Negative sine to curve left towards Level 2, 3, 4, matching Screenshot 2
                    val sinVal = -sin((2.0 * PI * levelT) / period)
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
 * goats, castles, monuments, and flags faithfully matching the reference screenshots.
 */
@Composable
private fun LandmarkForLevel(
    level: Int,
    nodeXDp: Dp,
    nodeYDp: Dp,
    screenWidthDp: Dp
) {
    val leftX = 12.dp
    val leftAccentX = 22.dp
    val rightX = (screenWidthDp - 138.dp).coerceAtLeast(180.dp)
    val rightAccentX = (screenWidthDp - 65.dp).coerceAtLeast(260.dp)

    when {
        // === EASY WORLD (Levels 1 - 35): Peaceful Friendly Forest ===
        level <= 35 -> {
            when (level) {
                // Major Landmarks (faithfully placed as seen in Screenshot 2):
                3 -> ScenicCozyCabin(modifier = Modifier.offset(x = rightX, y = nodeYDp - 40.dp).size(width = 130.dp, height = 125.dp))
                6 -> ScenicWoodenBench(modifier = Modifier.offset(x = leftX, y = nodeYDp - 35.dp).size(width = 125.dp, height = 115.dp))
                10 -> ScenicPondWithFrog(modifier = Modifier.offset(x = rightX + 8.dp, y = nodeYDp - 28.dp).size(width = 115.dp, height = 95.dp))
                14 -> ScenicAppleTreeWithCypress(modifier = Modifier.offset(x = leftX, y = nodeYDp - 40.dp).size(width = 115.dp, height = 120.dp))

                // Repeating scenery along the forest journey
                19 -> ScenicCozyCabin(modifier = Modifier.offset(x = rightX, y = nodeYDp - 40.dp).size(width = 130.dp, height = 125.dp))
                23 -> ScenicWoodenBench(modifier = Modifier.offset(x = leftX, y = nodeYDp - 35.dp).size(width = 125.dp, height = 115.dp))
                27 -> ScenicPondWithFrog(modifier = Modifier.offset(x = rightX + 8.dp, y = nodeYDp - 28.dp).size(width = 115.dp, height = 95.dp))
                31 -> ScenicAppleTreeWithCypress(modifier = Modifier.offset(x = leftX, y = nodeYDp - 40.dp).size(width = 115.dp, height = 120.dp))
                35 -> ScenicMilestoneChest(modifier = Modifier.offset(x = rightAccentX - 10.dp, y = nodeYDp - 25.dp).size(68.dp))

                // Nature accents along the trail
                1 -> ScenicDaisyFlowers(modifier = Modifier.offset(x = leftAccentX + 10.dp, y = nodeYDp - 10.dp).size(36.dp))
                5 -> ScenicBushCluster(modifier = Modifier.offset(x = rightAccentX, y = nodeYDp - 10.dp).size(52.dp))
                8 -> ScenicDaisyFlowers(modifier = Modifier.offset(x = leftAccentX, y = nodeYDp - 15.dp).size(36.dp))
                12 -> ScenicMushroomCluster(modifier = Modifier.offset(x = rightAccentX, y = nodeYDp + 5.dp).size(44.dp))
                16 -> ScenicDaisyFlowers(modifier = Modifier.offset(x = rightAccentX, y = nodeYDp - 10.dp).size(36.dp))
                21 -> ScenicBushCluster(modifier = Modifier.offset(x = rightAccentX, y = nodeYDp - 10.dp).size(52.dp))
                25 -> ScenicMushroomCluster(modifier = Modifier.offset(x = leftAccentX, y = nodeYDp + 5.dp).size(44.dp))
                29 -> ScenicDaisyFlowers(modifier = Modifier.offset(x = rightAccentX, y = nodeYDp - 10.dp).size(36.dp))
                33 -> ScenicBushCluster(modifier = Modifier.offset(x = leftAccentX, y = nodeYDp - 10.dp).size(52.dp))
            }
        }

        // === MODERATE WORLD (Levels 36 - 70): Adventurous Foothills & Castle Ruins ===
        level in 36..70 -> {
            when (level) {
                // Major Landmarks (faithfully placed as seen in Screenshot 1):
                38 -> ScenicWhiteCastleTower(modifier = Modifier.offset(x = rightX + 10.dp, y = nodeYDp - 45.dp).size(width = 110.dp, height = 125.dp))
                42 -> ScenicRockyMountainWithGoat(modifier = Modifier.offset(x = leftX, y = nodeYDp - 45.dp).size(width = 115.dp, height = 130.dp))
                47 -> ScenicAncientAltarWithPieces(modifier = Modifier.offset(x = rightX + 5.dp, y = nodeYDp - 38.dp).size(width = 110.dp, height = 110.dp))
                51 -> ScenicWaterfallCliff(modifier = Modifier.offset(x = leftX, y = nodeYDp - 40.dp).size(88.dp))
                55 -> ScenicWhiteCastleTower(modifier = Modifier.offset(x = rightX + 10.dp, y = nodeYDp - 45.dp).size(width = 110.dp, height = 125.dp))
                59 -> ScenicRockyMountainWithGoat(modifier = Modifier.offset(x = leftX, y = nodeYDp - 45.dp).size(width = 115.dp, height = 130.dp))
                63 -> ScenicMilestoneChest(modifier = Modifier.offset(x = rightAccentX - 10.dp, y = nodeYDp - 25.dp).size(68.dp))
                67 -> ScenicAncientRuins(modifier = Modifier.offset(x = leftX + 8.dp, y = nodeYDp - 30.dp).size(80.dp))
                70 -> ScenicWoodenBridgeOverStream(modifier = Modifier.offset(x = rightX + 8.dp, y = nodeYDp - 25.dp).size(86.dp))

                // Foothill nature & monuments
                36 -> ScenicPineTree(modifier = Modifier.offset(x = leftAccentX, y = nodeYDp - 20.dp).size(64.dp))
                40 -> ScenicStoneMonumentWithFlag(modifier = Modifier.offset(x = leftAccentX, y = nodeYDp - 25.dp).size(70.dp))
                44 -> ScenicPineTree(modifier = Modifier.offset(x = rightAccentX, y = nodeYDp - 20.dp).size(64.dp))
                49 -> ScenicStoneMonumentWithFlag(modifier = Modifier.offset(x = leftAccentX, y = nodeYDp - 25.dp).size(70.dp))
                53 -> ScenicPineTree(modifier = Modifier.offset(x = rightAccentX, y = nodeYDp - 20.dp).size(64.dp))
                57 -> ScenicStoneMonumentWithFlag(modifier = Modifier.offset(x = leftAccentX, y = nodeYDp - 25.dp).size(70.dp))
                61 -> ScenicPineTree(modifier = Modifier.offset(x = rightAccentX, y = nodeYDp - 20.dp).size(64.dp))
                65 -> ScenicStoneMonumentWithFlag(modifier = Modifier.offset(x = leftAccentX, y = nodeYDp - 25.dp).size(70.dp))
                69 -> ScenicPineTree(modifier = Modifier.offset(x = rightAccentX, y = nodeYDp - 20.dp).size(64.dp))
            }
        }

        // === HARD WORLD (Levels 71 - 100): Dramatic Mountain Summit & Dragon Monuments ===
        else -> {
            when (level) {
                // Major Summit Landmarks
                74 -> ScenicAncientAltarWithPieces(modifier = Modifier.offset(x = rightX + 5.dp, y = nodeYDp - 38.dp).size(width = 110.dp, height = 110.dp))
                78 -> ScenicRockyMountainWithGoat(modifier = Modifier.offset(x = leftX, y = nodeYDp - 45.dp).size(width = 115.dp, height = 130.dp))
                83 -> ScenicWhiteCastleTower(modifier = Modifier.offset(x = rightX + 10.dp, y = nodeYDp - 45.dp).size(width = 110.dp, height = 125.dp))
                87 -> ScenicDragonStoneStatue(modifier = Modifier.offset(x = leftX + 8.dp, y = nodeYDp - 25.dp).size(76.dp))
                91 -> ScenicRockyMountainWithGoat(modifier = Modifier.offset(x = rightX, y = nodeYDp - 45.dp).size(width = 115.dp, height = 130.dp))
                95 -> ScenicGlowingCrystalMushrooms(modifier = Modifier.offset(x = leftAccentX, y = nodeYDp - 20.dp).size(54.dp))
                98 -> ScenicMilestoneChest(modifier = Modifier.offset(x = rightAccentX - 10.dp, y = nodeYDp - 25.dp).size(68.dp))

                // Crystal & mountain accents
                72 -> ScenicGlowingCrystalMushrooms(modifier = Modifier.offset(x = leftAccentX, y = nodeYDp - 15.dp).size(54.dp))
                76 -> ScenicPineTree(modifier = Modifier.offset(x = rightAccentX, y = nodeYDp - 20.dp).size(64.dp))
                81 -> ScenicGlowingCrystalMushrooms(modifier = Modifier.offset(x = leftAccentX, y = nodeYDp - 15.dp).size(54.dp))
                85 -> ScenicPineTree(modifier = Modifier.offset(x = rightAccentX, y = nodeYDp - 20.dp).size(64.dp))
                89 -> ScenicDragonStoneStatue(modifier = Modifier.offset(x = leftX + 8.dp, y = nodeYDp - 25.dp).size(76.dp))
                93 -> ScenicPineTree(modifier = Modifier.offset(x = rightAccentX, y = nodeYDp - 20.dp).size(64.dp))
            }
        }
    }
}

/**
 * Draws the checkered meadow / stone canvas background with dynamic biome transitions
 * using true isometric diamond tiles tilted at 45 degrees:
 * - EASY WORLD (Levels 1-35): Deep rich forest lawn green diamonds (matching Screenshot 2)
 * - MODERATE WORLD (Levels 36-70): Transition forest green to slate
 * - HARD WORLD (Levels 71-100): Light grey granite mountain checker diamonds (matching Screenshot 1)
 */
private fun DrawScope.drawMeadowCheckeredBackground(width: Float, height: Float) {
    val tileW = 160f
    val tileH = 92f // True isometric ratio

    val rows = ((height + tileH) / (tileH / 2f)).toInt() + 2
    val cols = ((width + tileW) / (tileW / 2f)).toInt() + 2

    val path = Path()

    for (r in -1..rows) {
        val yFrac = 1.0f - (r.toFloat() / rows.toFloat()).coerceIn(0f, 1f)
        val cy = r * (tileH / 2f)

        val (color1, color2) = when {
            yFrac < 0.35f -> Color(0xFF2A572F) to Color(0xFF366B3D) // Lush dark green diamonds
            yFrac < 0.68f -> Color(0xFF435C4A) to Color(0xFF54705C) // Transition slate green
            else -> Color(0xFF8D9992) to Color(0xFFA4B0A9)          // Mountain light grey granite diamonds
        }

        val isRowEven = ((r % 2) + 2) % 2 == 0
        val rowColor = if (isRowEven) color1 else color2

        val startCol = if (Math.abs(r) % 2 == 1) -1 else -2
        for (c in startCol..cols step 2) {
            val cx = c * (tileW / 2f)

            path.reset()
            path.moveTo(cx, cy - tileH / 2f)
            path.lineTo(cx + tileW / 2f, cy)
            path.lineTo(cx, cy + tileH / 2f)
            path.lineTo(cx - tileW / 2f, cy)
            path.close()

            drawPath(path, color = rowColor)

            // Occasional organic grass blade dots on lawn
            if ((r * 11 + c * 7) % 17 == 0 && yFrac < 0.68f) {
                drawCircle(color = Color(0x22FFFFFF), radius = 2.5f, center = Offset(cx, cy))
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

        val x1 = (screenWidth / 2f) - (amplitude * sin((2.0 * PI * t1) / period).toFloat())
        val y1 = totalHeight - bottomPadding - (stepHeight * (level - 1))

        val x2 = (screenWidth / 2f) - (amplitude * sin((2.0 * PI * t2) / period).toFloat())
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
