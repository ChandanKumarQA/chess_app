package com.chessmaster.play.ui.components.adventure

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

/**
 * Beautiful illustrative landscape landmarks for the Chess Adventure Journey Map.
 * Drawn entirely via vector Canvas for high resolution, zero asset weight, and fluid rendering.
 */

@Composable
fun ScenicAppleTree(modifier: Modifier = Modifier.size(72.dp)) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Ground shadow
        drawOval(
            color = Color(0x33000000),
            topLeft = Offset(w * 0.2f, h * 0.78f),
            size = Size(w * 0.6f, h * 0.2f)
        )

        // Trunk
        val trunkPath = Path().apply {
            moveTo(w * 0.42f, h * 0.5f)
            lineTo(w * 0.38f, h * 0.88f)
            quadraticTo(w * 0.5f, h * 0.85f, w * 0.62f, h * 0.88f)
            lineTo(w * 0.58f, h * 0.5f)
            close()
        }
        drawPath(trunkPath, color = Color(0xFF5D4037))
        // Trunk bark detail
        drawPath(
            Path().apply {
                moveTo(w * 0.46f, h * 0.55f)
                lineTo(w * 0.44f, h * 0.82f)
            },
            color = Color(0xFF3E2723),
            style = Stroke(width = w * 0.03f)
        )

        // Foliage layers (layered circles for 3D puffiness)
        // Dark background foliage
        drawCircle(color = Color(0xFF1B5E20), radius = w * 0.28f, center = Offset(w * 0.5f, h * 0.46f))
        drawCircle(color = Color(0xFF2E7D32), radius = w * 0.24f, center = Offset(w * 0.34f, h * 0.42f))
        drawCircle(color = Color(0xFF2E7D32), radius = w * 0.24f, center = Offset(w * 0.66f, h * 0.42f))
        drawCircle(color = Color(0xFF388E3C), radius = w * 0.26f, center = Offset(w * 0.5f, h * 0.32f))
        drawCircle(color = Color(0xFF4CAF50), radius = w * 0.2f, center = Offset(w * 0.44f, h * 0.28f))
        // Highlight patch
        drawCircle(color = Color(0xFF81C784), radius = w * 0.12f, center = Offset(w * 0.38f, h * 0.22f))

        // Red Apples
        val applePositions = listOf(
            Offset(w * 0.32f, h * 0.32f),
            Offset(w * 0.64f, h * 0.30f),
            Offset(w * 0.48f, h * 0.42f),
            Offset(w * 0.36f, h * 0.48f),
            Offset(w * 0.60f, h * 0.46f),
            Offset(w * 0.48f, h * 0.20f)
        )
        for (pos in applePositions) {
            drawCircle(color = Color(0xFFD32F2F), radius = w * 0.055f, center = pos)
            drawCircle(color = Color(0xFFFF5252), radius = w * 0.025f, center = Offset(pos.x - w * 0.015f, pos.y - h * 0.015f))
            // tiny leaf
            drawCircle(color = Color(0xFF8BC34A), radius = w * 0.02f, center = Offset(pos.x + w * 0.02f, pos.y - h * 0.035f))
        }
    }
}

@Composable
fun ScenicPondWithFrog(modifier: Modifier = Modifier.size(80.dp)) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Shore / bank rim
        drawOval(
            color = Color(0xFF2E5930),
            topLeft = Offset(w * 0.05f, h * 0.15f),
            size = Size(w * 0.9f, h * 0.7f)
        )
        // Sandy / stone margin
        drawOval(
            color = Color(0xFF4A7C59),
            topLeft = Offset(w * 0.08f, h * 0.18f),
            size = Size(w * 0.84f, h * 0.64f)
        )
        // Water base
        drawOval(
            color = Color(0xFF0288D1),
            topLeft = Offset(w * 0.12f, h * 0.22f),
            size = Size(w * 0.76f, h * 0.56f)
        )
        // Shallow water shimmer
        drawOval(
            color = Color(0xFF29B6F6),
            topLeft = Offset(w * 0.18f, h * 0.26f),
            size = Size(w * 0.64f, h * 0.46f)
        )
        // Surface highlight arc
        drawOval(
            color = Color(0x66E1F5FE),
            topLeft = Offset(w * 0.25f, h * 0.32f),
            size = Size(w * 0.45f, h * 0.18f)
        )

        // Lily Pad 1 (Right)
        val padCenter = Offset(w * 0.62f, h * 0.48f)
        drawCircle(color = Color(0xFF2E7D32), radius = w * 0.13f, center = padCenter)
        // Slit in lily pad
        drawPath(
            Path().apply {
                moveTo(padCenter.x, padCenter.y)
                lineTo(padCenter.x + w * 0.13f, padCenter.y - h * 0.05f)
                lineTo(padCenter.x + w * 0.13f, padCenter.y + h * 0.05f)
                close()
            },
            color = Color(0xFF0288D1)
        )

        // Pink Lotus Flower on Lily Pad
        val lotusCenter = Offset(w * 0.62f, h * 0.46f)
        drawCircle(color = Color(0xFFEC407A), radius = w * 0.06f, center = lotusCenter)
        drawCircle(color = Color(0xFFF8BBD0), radius = w * 0.035f, center = lotusCenter)
        drawCircle(color = Color(0xFFFFEB3B), radius = w * 0.018f, center = lotusCenter)

        // Lily Pad 2 (Left with cute Frog)
        val frogPadCenter = Offset(w * 0.35f, h * 0.52f)
        drawCircle(color = Color(0xFF1B5E20), radius = w * 0.14f, center = frogPadCenter)

        // Cute Little Green Frog
        val frogCenter = Offset(w * 0.35f, h * 0.49f)
        // Body
        drawOval(
            color = Color(0xFF4CAF50),
            topLeft = Offset(frogCenter.x - w * 0.07f, frogCenter.y - h * 0.05f),
            size = Size(w * 0.14f, h * 0.11f)
        )
        // Back legs
        drawCircle(color = Color(0xFF388E3C), radius = w * 0.035f, center = Offset(frogCenter.x - w * 0.06f, frogCenter.y + h * 0.03f))
        drawCircle(color = Color(0xFF388E3C), radius = w * 0.035f, center = Offset(frogCenter.x + w * 0.06f, frogCenter.y + h * 0.03f))
        // Eyes
        val leftEye = Offset(frogCenter.x - w * 0.035f, frogCenter.y - h * 0.055f)
        val rightEye = Offset(frogCenter.x + w * 0.035f, frogCenter.y - h * 0.055f)
        drawCircle(color = Color(0xFF4CAF50), radius = w * 0.03f, center = leftEye)
        drawCircle(color = Color(0xFF4CAF50), radius = w * 0.03f, center = rightEye)
        drawCircle(color = Color.White, radius = w * 0.022f, center = leftEye)
        drawCircle(color = Color.White, radius = w * 0.022f, center = rightEye)
        drawCircle(color = Color.Black, radius = w * 0.012f, center = leftEye)
        drawCircle(color = Color.Black, radius = w * 0.012f, center = rightEye)
    }
}

@Composable
fun ScenicWoodenBench(modifier: Modifier = Modifier.size(70.dp)) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Ground shadow
        drawOval(
            color = Color(0x33000000),
            topLeft = Offset(w * 0.1f, h * 0.72f),
            size = Size(w * 0.8f, h * 0.18f)
        )

        // Bench legs
        val legColor = Color(0xFF3E2723)
        drawRoundRect(
            color = legColor,
            topLeft = Offset(w * 0.22f, h * 0.5f),
            size = Size(w * 0.06f, h * 0.32f),
            cornerRadius = CornerRadius(w * 0.02f)
        )
        drawRoundRect(
            color = legColor,
            topLeft = Offset(w * 0.72f, h * 0.5f),
            size = Size(w * 0.06f, h * 0.32f),
            cornerRadius = CornerRadius(w * 0.02f)
        )

        // Seat Planks (Wooden slats)
        val woodDark = Color(0xFF6D4C41)
        val woodLight = Color(0xFF8D6E63)
        val woodHighlight = Color(0xFFA1887F)

        drawRoundRect(
            color = woodDark,
            topLeft = Offset(w * 0.12f, h * 0.52f),
            size = Size(w * 0.76f, h * 0.1f),
            cornerRadius = CornerRadius(w * 0.03f)
        )
        drawRoundRect(
            color = woodLight,
            topLeft = Offset(w * 0.12f, h * 0.44f),
            size = Size(w * 0.76f, h * 0.08f),
            cornerRadius = CornerRadius(w * 0.02f)
        )

        // Backrest Slats
        drawRoundRect(
            color = woodDark,
            topLeft = Offset(w * 0.14f, h * 0.26f),
            size = Size(w * 0.72f, h * 0.07f),
            cornerRadius = CornerRadius(w * 0.02f)
        )
        drawRoundRect(
            color = woodLight,
            topLeft = Offset(w * 0.14f, h * 0.34f),
            size = Size(w * 0.72f, h * 0.07f),
            cornerRadius = CornerRadius(w * 0.02f)
        )

        // Backrest upright posts
        drawRect(color = legColor, topLeft = Offset(w * 0.22f, h * 0.24f), size = Size(w * 0.05f, h * 0.22f))
        drawRect(color = legColor, topLeft = Offset(w * 0.73f, h * 0.24f), size = Size(w * 0.05f, h * 0.22f))

        // Mini Chessboard on bench
        val boardLeft = w * 0.38f
        val boardTop = h * 0.41f
        val boardSize = w * 0.24f
        drawRect(color = Color(0xFF4E342E), topLeft = Offset(boardLeft - w * 0.02f, boardTop - h * 0.015f), size = Size(boardSize + w * 0.04f, boardSize * 0.65f))
        val squareW = boardSize / 4f
        val squareH = (boardSize * 0.55f) / 4f
        for (r in 0 until 4) {
            for (c in 0 until 4) {
                val isLight = (r + c) % 2 == 0
                drawRect(
                    color = if (isLight) Color(0xFFF5E6CC) else Color(0xFF5D4037),
                    topLeft = Offset(boardLeft + c * squareW, boardTop + r * squareH),
                    size = Size(squareW, squareH)
                )
            }
        }

        // Cute companion snowman sitting on the right edge
        val snowCenterBottom = Offset(w * 0.75f, h * 0.42f)
        val snowCenterTop = Offset(w * 0.75f, h * 0.26f)
        // Bottom snowball
        drawCircle(color = Color(0xFFE0E0E0), radius = w * 0.11f, center = snowCenterBottom)
        drawCircle(color = Color.White, radius = w * 0.10f, center = Offset(snowCenterBottom.x - 1f, snowCenterBottom.y - 1f))
        // Top snowball (head)
        drawCircle(color = Color(0xFFE0E0E0), radius = w * 0.08f, center = snowCenterTop)
        drawCircle(color = Color.White, radius = w * 0.075f, center = Offset(snowCenterTop.x - 1f, snowCenterTop.y - 1f))
        // Carrot nose
        drawCircle(color = Color(0xFFFF9800), radius = w * 0.02f, center = Offset(snowCenterTop.x - w * 0.03f, snowCenterTop.y))
        // Coal eyes
        drawCircle(color = Color(0xFF212121), radius = w * 0.012f, center = Offset(snowCenterTop.x - w * 0.02f, snowCenterTop.y - h * 0.02f))
        drawCircle(color = Color(0xFF212121), radius = w * 0.012f, center = Offset(snowCenterTop.x + w * 0.02f, snowCenterTop.y - h * 0.02f))
        // Tiny scarf
        drawRoundRect(
            color = Color(0xFFD32F2F),
            topLeft = Offset(snowCenterTop.x - w * 0.06f, snowCenterTop.y + h * 0.045f),
            size = Size(w * 0.12f, h * 0.035f),
            cornerRadius = CornerRadius(w * 0.01f)
        )
    }
}

@Composable
fun ScenicCozyCabin(modifier: Modifier = Modifier.size(80.dp)) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Ground shadow
        drawOval(
            color = Color(0x33000000),
            topLeft = Offset(w * 0.1f, h * 0.8f),
            size = Size(w * 0.8f, h * 0.18f)
        )

        // Chimney (behind roof)
        drawRect(
            color = Color(0xFF757575),
            topLeft = Offset(w * 0.22f, h * 0.18f),
            size = Size(w * 0.12f, h * 0.26f)
        )
        drawRect(
            color = Color(0xFF616161),
            topLeft = Offset(w * 0.20f, h * 0.15f),
            size = Size(w * 0.16f, h * 0.05f)
        )
        // Chimney smoke puffs
        drawCircle(color = Color(0x66FFFFFF), radius = w * 0.05f, center = Offset(w * 0.28f, h * 0.10f))
        drawCircle(color = Color(0x44FFFFFF), radius = w * 0.07f, center = Offset(w * 0.32f, h * 0.04f))

        // Log Walls
        val wallColor = Color(0xFF6D4C41)
        drawRoundRect(
            color = wallColor,
            topLeft = Offset(w * 0.18f, h * 0.48f),
            size = Size(w * 0.64f, h * 0.42f),
            cornerRadius = CornerRadius(w * 0.02f)
        )
        // Horizontal log grooves
        for (i in 1..4) {
            val y = h * (0.48f + i * 0.08f)
            drawLine(
                color = Color(0xFF4E342E),
                start = Offset(w * 0.18f, y),
                end = Offset(w * 0.82f, y),
                strokeWidth = w * 0.02f
            )
        }

        // Triangular Gabled Roof
        val roofPath = Path().apply {
            moveTo(w * 0.5f, h * 0.18f)
            lineTo(w * 0.9f, h * 0.52f)
            lineTo(w * 0.1f, h * 0.52f)
            close()
        }
        drawPath(roofPath, color = Color(0xFF8D2A2A))
        // Roof overhang trim
        val roofTrim = Path().apply {
            moveTo(w * 0.5f, h * 0.16f)
            lineTo(w * 0.94f, h * 0.54f)
            lineTo(w * 0.9f, h * 0.54f)
            lineTo(w * 0.5f, h * 0.20f)
            lineTo(w * 0.1f, h * 0.54f)
            lineTo(w * 0.06f, h * 0.54f)
            close()
        }
        drawPath(roofTrim, color = Color(0xFFA73C3C))

        // Cozy Door
        val doorLeft = w * 0.42f
        val doorTop = h * 0.62f
        val doorW = w * 0.18f
        val doorH = h * 0.28f
        drawRoundRect(
            color = Color(0xFF3E2723),
            topLeft = Offset(doorLeft, doorTop),
            size = Size(doorW, doorH),
            cornerRadius = CornerRadius(w * 0.04f, w * 0.04f)
        )
        // Golden door knob
        drawCircle(color = Color(0xFFFFD54F), radius = w * 0.02f, center = Offset(doorLeft + doorW * 0.8f, doorTop + doorH * 0.55f))

        // Warm Glowing Window
        val winLeft = w * 0.65f
        val winTop = h * 0.56f
        val winSize = w * 0.14f
        drawRoundRect(
            color = Color(0xFFFFF176),
            topLeft = Offset(winLeft, winTop),
            size = Size(winSize, winSize),
            cornerRadius = CornerRadius(w * 0.02f)
        )
        // Window frame
        drawRect(
            color = Color(0xFF3E2723),
            topLeft = Offset(winLeft, winTop),
            size = Size(winSize, winSize),
            style = Stroke(width = w * 0.02f)
        )
        drawLine(
            color = Color(0xFF3E2723),
            start = Offset(winLeft + winSize / 2, winTop),
            end = Offset(winLeft + winSize / 2, winTop + winSize),
            strokeWidth = w * 0.015f
        )
        drawLine(
            color = Color(0xFF3E2723),
            start = Offset(winLeft, winTop + winSize / 2),
            end = Offset(winLeft + winSize, winTop + winSize / 2),
            strokeWidth = w * 0.015f
        )

        // Cute Chicken in the yard
        val chkCenter = Offset(w * 0.28f, h * 0.80f)
        drawCircle(color = Color.White, radius = w * 0.045f, center = chkCenter)
        // Red comb
        drawCircle(color = Color(0xFFE53935), radius = w * 0.015f, center = Offset(chkCenter.x, chkCenter.y - h * 0.045f))
        // Yellow beak
        drawCircle(color = Color(0xFFFFB300), radius = w * 0.012f, center = Offset(chkCenter.x + w * 0.04f, chkCenter.y))
        // Red tail
        drawCircle(color = Color(0xFF757575), radius = w * 0.02f, center = Offset(chkCenter.x - w * 0.035f, chkCenter.y - h * 0.01f))
    }
}

@Composable
fun ScenicMilestoneChest(modifier: Modifier = Modifier.size(68.dp)) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Golden aura / glow
        drawCircle(
            color = Color(0x33FFD700),
            radius = w * 0.44f,
            center = Offset(w * 0.5f, h * 0.55f)
        )

        // Ground shadow
        drawOval(
            color = Color(0x44000000),
            topLeft = Offset(w * 0.15f, h * 0.72f),
            size = Size(w * 0.7f, h * 0.2f)
        )

        // Chest Body
        val bodyColor = Color(0xFF6D4C41)
        val bodyTop = h * 0.48f
        val bodyHeight = h * 0.32f
        drawRoundRect(
            color = bodyColor,
            topLeft = Offset(w * 0.2f, bodyTop),
            size = Size(w * 0.6f, bodyHeight),
            cornerRadius = CornerRadius(w * 0.04f)
        )

        // Chest Lid (Curved Arch)
        val lidPath = Path().apply {
            moveTo(w * 0.18f, bodyTop)
            quadraticTo(w * 0.5f, h * 0.24f, w * 0.82f, bodyTop)
            close()
        }
        drawPath(lidPath, color = Color(0xFF795548))

        // Gold Trim Bands
        val gold = Color(0xFFFFD54F)
        val darkGold = Color(0xFFFFA000)

        // Left gold band
        drawRect(color = gold, topLeft = Offset(w * 0.30f, bodyTop), size = Size(w * 0.08f, bodyHeight))
        // Right gold band
        drawRect(color = gold, topLeft = Offset(w * 0.62f, bodyTop), size = Size(w * 0.08f, bodyHeight))

        // Center Gold Clasp / Lock
        val lockCenter = Offset(w * 0.5f, bodyTop + h * 0.04f)
        drawCircle(color = darkGold, radius = w * 0.09f, center = lockCenter)
        drawCircle(color = gold, radius = w * 0.075f, center = lockCenter)
        // Keyhole
        drawCircle(color = Color(0xFF261914), radius = w * 0.022f, center = Offset(lockCenter.x, lockCenter.y - 1f))
        drawRect(
            color = Color(0xFF261914),
            topLeft = Offset(lockCenter.x - w * 0.012f, lockCenter.y),
            size = Size(w * 0.024f, h * 0.035f)
        )

        // Sparkle Stars
        drawStar(this, Offset(w * 0.18f, h * 0.30f), w * 0.06f, Color(0xFFFFF59D))
        drawStar(this, Offset(w * 0.82f, h * 0.34f), w * 0.05f, Color(0xFFFFF59D))
        drawStar(this, Offset(w * 0.50f, h * 0.18f), w * 0.07f, Color(0xFFFFEB3B))
    }
}

private fun drawStar(
    scope: androidx.compose.ui.graphics.drawscope.DrawScope,
    center: Offset,
    size: Float,
    color: Color
) {
    scope.drawLine(
        color = color,
        start = Offset(center.x, center.y - size),
        end = Offset(center.x, center.y + size),
        strokeWidth = size * 0.3f
    )
    scope.drawLine(
        color = color,
        start = Offset(center.x - size, center.y),
        end = Offset(center.x + size, center.y),
        strokeWidth = size * 0.3f
    )
    scope.drawCircle(color = Color.White, radius = size * 0.3f, center = center)
}

@Composable
fun ScenicDaisyFlowers(modifier: Modifier = Modifier.size(36.dp)) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Grass blades
        drawLine(
            color = Color(0xFF689F38),
            start = Offset(w * 0.3f, h * 0.8f),
            end = Offset(w * 0.2f, h * 0.4f),
            strokeWidth = w * 0.06f
        )
        drawLine(
            color = Color(0xFF7CB342),
            start = Offset(w * 0.5f, h * 0.85f),
            end = Offset(w * 0.6f, h * 0.35f),
            strokeWidth = w * 0.06f
        )

        // White daisies with yellow center
        val flower1 = Offset(w * 0.35f, h * 0.45f)
        for (angle in 0 until 6) {
            val rad = Math.toRadians((angle * 60).toDouble())
            val px = flower1.x + (Math.cos(rad) * w * 0.12f).toFloat()
            val py = flower1.y + (Math.sin(rad) * h * 0.12f).toFloat()
            drawCircle(color = Color.White, radius = w * 0.06f, center = Offset(px, py))
        }
        drawCircle(color = Color(0xFFFFCA28), radius = w * 0.065f, center = flower1)

        val flower2 = Offset(w * 0.72f, h * 0.65f)
        drawCircle(color = Color.White, radius = w * 0.09f, center = flower2)
        drawCircle(color = Color(0xFFFFCA28), radius = w * 0.045f, center = flower2)
    }
}
