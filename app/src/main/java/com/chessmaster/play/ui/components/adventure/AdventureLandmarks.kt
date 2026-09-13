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
 * Faithfully recreates the vibrant 3D isometric scenery from the reference screenshots.
 */

// ==========================================
// 1. SCENIC COZY CABIN (Isometric Log House)
// ==========================================
@Composable
fun ScenicCozyCabin(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(width = 130.dp, height = 125.dp)) {
        val w = size.width
        val h = size.height

        // 1. Soft Isometric Ground Shadow
        drawOval(
            color = Color(0x38000000),
            topLeft = Offset(w * 0.08f, h * 0.72f),
            size = Size(w * 0.86f, h * 0.24f)
        )

        // 2. Chimney (Stone cobblestone on back-right roof slope)
        val chimX = w * 0.68f
        val chimY = h * 0.18f
        val chimW = w * 0.12f
        val chimH = h * 0.22f
        drawRect(
            color = Color(0xFF607D8B),
            topLeft = Offset(chimX, chimY),
            size = Size(chimW, chimH)
        )
        drawRect(
            color = Color(0xFF455A64),
            topLeft = Offset(chimX + chimW * 0.55f, chimY),
            size = Size(chimW * 0.45f, chimH)
        )
        // Chimney rim cap
        drawRoundRect(
            color = Color(0xFF37474F),
            topLeft = Offset(chimX - w * 0.02f, chimY - h * 0.03f),
            size = Size(chimW + w * 0.04f, h * 0.04f),
            cornerRadius = CornerRadius(2f)
        )
        // Flue opening
        drawOval(
            color = Color(0xFF212121),
            topLeft = Offset(chimX + chimW * 0.2f, chimY - h * 0.025f),
            size = Size(chimW * 0.6f, h * 0.02f)
        )
        // Chimney brick mortar lines
        drawLine(Color(0xFF78909C), Offset(chimX, chimY + chimH * 0.35f), Offset(chimX + chimW, chimY + chimH * 0.35f), strokeWidth = 1.5f)
        drawLine(Color(0xFF78909C), Offset(chimX, chimY + chimH * 0.7f), Offset(chimX + chimW, chimY + chimH * 0.7f), strokeWidth = 1.5f)

        // Chimney Smoke Puffs drifting up
        drawCircle(color = Color(0x55FFFFFF), radius = w * 0.045f, center = Offset(chimX + chimW * 0.5f, chimY - h * 0.06f))
        drawCircle(color = Color(0x40FFFFFF), radius = w * 0.06f, center = Offset(chimX + chimW * 0.8f, chimY - h * 0.11f))
        drawCircle(color = Color(0x25FFFFFF), radius = w * 0.075f, center = Offset(chimX + chimW * 1.1f, chimY - h * 0.16f))

        // 3. Cabin Body - Isometric Walls
        // Front Wall (Facing Front-Left)
        val frontWall = Path().apply {
            moveTo(w * 0.36f, h * 0.36f) // gable peak
            lineTo(w * 0.50f, h * 0.48f) // right edge under eave
            lineTo(w * 0.50f, h * 0.86f) // center bottom corner
            lineTo(w * 0.18f, h * 0.76f) // left bottom corner
            lineTo(w * 0.18f, h * 0.52f) // left corner under eave
            close()
        }
        drawPath(frontWall, color = Color(0xFFC68A4C)) // Warm timber front

        // Front Wall Log Lines (horizontal logs)
        for (i in 1..6) {
            val frac = i / 7f
            val ly = h * 0.52f + (h * 0.24f) * frac
            val ry = h * 0.48f + (h * 0.38f) * frac
            drawLine(
                color = Color(0xFFA06528),
                start = Offset(w * 0.18f, ly),
                end = Offset(w * 0.50f, ry),
                strokeWidth = 2f
            )
        }

        // Right Wall (Facing Right, in perspective shadow)
        val rightWall = Path().apply {
            moveTo(w * 0.50f, h * 0.48f)
            lineTo(w * 0.88f, h * 0.34f)
            lineTo(w * 0.88f, h * 0.68f)
            lineTo(w * 0.50f, h * 0.86f)
            close()
        }
        drawPath(rightWall, color = Color(0xFFA86A30)) // Shadowed timber side

        // Right Wall Log Lines (in isometric perspective)
        for (i in 1..6) {
            val frac = i / 7f
            val ly = h * 0.48f + (h * 0.38f) * frac
            val ry = h * 0.34f + (h * 0.34f) * frac
            drawLine(
                color = Color(0xFF8B501B),
                start = Offset(w * 0.50f, ly),
                end = Offset(w * 0.88f, ry),
                strokeWidth = 2f
            )
        }

        // 4. Front Door (with green chess pawn badge!)
        val doorL = w * 0.28f
        val doorR = w * 0.40f
        val doorTop = h * 0.58f
        val doorBotL = h * 0.81f
        val doorBotR = h * 0.83f

        val doorPath = Path().apply {
            moveTo(doorL, doorTop + h * 0.03f)
            quadraticTo(doorL + (doorR - doorL) * 0.5f, doorTop - h * 0.02f, doorR, doorTop + h * 0.03f)
            lineTo(doorR, doorBotR)
            lineTo(doorL, doorBotL)
            close()
        }
        drawPath(doorPath, color = Color(0xFF5D2E0C)) // Dark wood frame
        val doorInner = Path().apply {
            moveTo(doorL + 2f, doorTop + h * 0.03f + 2f)
            quadraticTo(doorL + (doorR - doorL) * 0.5f, doorTop - h * 0.02f + 2f, doorR - 2f, doorTop + h * 0.03f + 2f)
            lineTo(doorR - 2f, doorBotR - 2f)
            lineTo(doorL + 2f, doorBotL - 2f)
            close()
        }
        drawPath(doorInner, color = Color(0xFF7A3E1D))

        // Door handle (golden)
        drawCircle(color = Color(0xFFFFD54F), radius = w * 0.015f, center = Offset(doorR - w * 0.025f, doorTop + h * 0.12f))

        // Green Square Emblem on Door with White Pawn Symbol!
        val emblemCenter = Offset(doorL + (doorR - doorL) * 0.5f, doorTop + h * 0.07f)
        val emblemW = (doorR - doorL) * 0.55f
        drawRoundRect(
            color = Color(0xFF2E7D32),
            topLeft = Offset(emblemCenter.x - emblemW * 0.5f, emblemCenter.y - emblemW * 0.6f),
            size = Size(emblemW, emblemW * 1.2f),
            cornerRadius = CornerRadius(2f)
        )
        drawRoundRect(
            color = Color(0xFF81C784),
            topLeft = Offset(emblemCenter.x - emblemW * 0.5f, emblemCenter.y - emblemW * 0.6f),
            size = Size(emblemW, emblemW * 1.2f),
            cornerRadius = CornerRadius(2f),
            style = Stroke(width = 1.2f)
        )
        // White chess pawn inside green emblem
        val pawnHeadCenter = Offset(emblemCenter.x, emblemCenter.y - emblemW * 0.25f)
        drawCircle(color = Color.White, radius = emblemW * 0.20f, center = pawnHeadCenter)
        val pawnBody = Path().apply {
            moveTo(emblemCenter.x - emblemW * 0.12f, pawnHeadCenter.y + emblemW * 0.18f)
            lineTo(emblemCenter.x + emblemW * 0.12f, pawnHeadCenter.y + emblemW * 0.18f)
            lineTo(emblemCenter.x + emblemW * 0.28f, emblemCenter.y + emblemW * 0.45f)
            lineTo(emblemCenter.x - emblemW * 0.28f, emblemCenter.y + emblemW * 0.45f)
            close()
        }
        drawPath(pawnBody, color = Color.White)

        // 5. Window on Right Wall with Orange Cat Looking Out!
        val winL = w * 0.62f
        val winR = w * 0.77f
        val winTop = h * 0.50f
        val winBot = h * 0.64f

        val winPath = Path().apply {
            moveTo(winL, winTop + (winBot - winTop) * 0.05f)
            lineTo(winR, winTop - (winBot - winTop) * 0.05f)
            lineTo(winR, winBot - (winBot - winTop) * 0.05f)
            lineTo(winL, winBot + (winBot - winTop) * 0.05f)
            close()
        }
        drawPath(winPath, color = Color(0xFF4A2508))
        val winInner = Path().apply {
            moveTo(winL + 3f, winTop + 3f)
            lineTo(winR - 3f, winTop - 2f)
            lineTo(winR - 3f, winBot - 3f)
            lineTo(winL + 3f, winBot + 2f)
            close()
        }
        drawPath(winInner, color = Color(0xFF26180E))

        // Cute Orange Cat inside window!
        val catCenterX = (winL + winR) * 0.5f
        val catCenterY = winBot - h * 0.035f

        val leftEar = Path().apply {
            moveTo(catCenterX - w * 0.04f, catCenterY - h * 0.04f)
            lineTo(catCenterX - w * 0.05f, catCenterY - h * 0.08f)
            lineTo(catCenterX - w * 0.02f, catCenterY - h * 0.05f)
            close()
        }
        drawPath(leftEar, color = Color(0xFFE67E22))
        val rightEar = Path().apply {
            moveTo(catCenterX + w * 0.02f, catCenterY - h * 0.05f)
            lineTo(catCenterX + w * 0.05f, catCenterY - h * 0.08f)
            lineTo(catCenterX + w * 0.04f, catCenterY - h * 0.04f)
            close()
        }
        drawPath(rightEar, color = Color(0xFFE67E22))

        drawCircle(color = Color(0xFFE67E22), radius = w * 0.045f, center = Offset(catCenterX, catCenterY - h * 0.035f))
        drawCircle(color = Color(0xFFFFF8E7), radius = w * 0.022f, center = Offset(catCenterX, catCenterY - h * 0.025f))
        drawCircle(color = Color(0xFF1B5E20), radius = w * 0.008f, center = Offset(catCenterX - w * 0.016f, catCenterY - h * 0.042f))
        drawCircle(color = Color(0xFF1B5E20), radius = w * 0.008f, center = Offset(catCenterX + w * 0.016f, catCenterY - h * 0.042f))
        drawCircle(color = Color(0xFFFF8A80), radius = w * 0.005f, center = Offset(catCenterX, catCenterY - h * 0.030f))
        drawOval(color = Color(0xFFFFF8E7), topLeft = Offset(catCenterX - w * 0.035f, winBot - h * 0.012f), size = Size(w * 0.03f, h * 0.02f))
        drawOval(color = Color(0xFFFFF8E7), topLeft = Offset(catCenterX + w * 0.005f, winBot - h * 0.012f), size = Size(w * 0.03f, h * 0.02f))

        drawLine(color = Color(0xFF6D3B14), start = Offset(winL - w * 0.02f, winBot + h * 0.01f), end = Offset(winR + w * 0.02f, winBot - h * 0.01f), strokeWidth = 3f)

        // 6. 3D Isometric Pitched Roof with Brown Shingles
        val leftRoof = Path().apply {
            moveTo(w * 0.34f, h * 0.28f)
            lineTo(w * 0.12f, h * 0.54f)
            lineTo(w * 0.48f, h * 0.52f)
            close()
        }
        drawPath(leftRoof, color = Color(0xFF7A3E20))

        val mainRoof = Path().apply {
            moveTo(w * 0.34f, h * 0.28f)
            lineTo(w * 0.74f, h * 0.16f)
            lineTo(w * 0.94f, h * 0.34f)
            lineTo(w * 0.48f, h * 0.52f)
            close()
        }
        drawPath(mainRoof, color = Color(0xFF603117))

        for (i in 1..5) {
            val frac = i / 6f
            val startPt = Offset(
                w * 0.34f + (w * 0.14f) * frac,
                h * 0.28f + (h * 0.24f) * frac
            )
            val endPt = Offset(
                w * 0.74f + (w * 0.20f) * frac,
                h * 0.16f + (h * 0.18f) * frac
            )
            drawLine(color = Color(0xFF4A2510), start = startPt, end = endPt, strokeWidth = 2.5f)
            val count = 5
            for (j in 0 until count) {
                val subFrac = j / count.toFloat()
                val cx = startPt.x + (endPt.x - startPt.x) * subFrac
                val cy = startPt.y + (endPt.y - startPt.y) * subFrac
                drawLine(
                    color = Color(0xFF381B0B),
                    start = Offset(cx, cy),
                    end = Offset(cx - w * 0.015f, cy + h * 0.025f),
                    strokeWidth = 1.5f
                )
            }
        }

        val roofRidge = Path().apply {
            moveTo(w * 0.32f, h * 0.27f)
            lineTo(w * 0.76f, h * 0.15f)
        }
        drawPath(roofRidge, color = Color(0xFF8D4A27), style = Stroke(width = 3.5f))

        // 7. White Chicken / Rooster on the Roof Ridge!
        val chkX = w * 0.38f
        val chkY = h * 0.24f
        drawOval(color = Color.White, topLeft = Offset(chkX - w * 0.03f, chkY - h * 0.03f), size = Size(w * 0.06f, h * 0.045f))
        drawCircle(color = Color.White, radius = w * 0.02f, center = Offset(chkX - w * 0.025f, chkY - h * 0.025f))
        drawCircle(color = Color(0xFFE53935), radius = w * 0.01f, center = Offset(chkX - w * 0.028f, chkY - h * 0.045f))
        drawCircle(color = Color(0xFFFFC107), radius = w * 0.008f, center = Offset(chkX - w * 0.045f, chkY - h * 0.025f))
        drawCircle(color = Color(0xFFE53935), radius = w * 0.007f, center = Offset(chkX - w * 0.035f, chkY - h * 0.015f))
        drawCircle(color = Color(0xFFB0BEC5), radius = w * 0.015f, center = Offset(chkX + w * 0.03f, chkY - h * 0.025f))

        // 8. Foundation Green Bushes
        drawCircle(color = Color(0xFF1B5E20), radius = w * 0.09f, center = Offset(w * 0.15f, h * 0.78f))
        drawCircle(color = Color(0xFF2E7D32), radius = w * 0.08f, center = Offset(w * 0.17f, h * 0.76f))
        drawCircle(color = Color(0xFF4CAF50), radius = w * 0.06f, center = Offset(w * 0.19f, h * 0.74f))

        drawCircle(color = Color(0xFF1B5E20), radius = w * 0.08f, center = Offset(w * 0.48f, h * 0.88f))
        drawCircle(color = Color(0xFF388E3C), radius = w * 0.07f, center = Offset(w * 0.50f, h * 0.87f))
        drawCircle(color = Color(0xFF66BB6A), radius = w * 0.05f, center = Offset(w * 0.51f, h * 0.85f))

        drawCircle(color = Color(0xFF1B5E20), radius = w * 0.11f, center = Offset(w * 0.85f, h * 0.75f))
        drawCircle(color = Color(0xFF2E7D32), radius = w * 0.10f, center = Offset(w * 0.87f, h * 0.73f))
        drawCircle(color = Color(0xFF4CAF50), radius = w * 0.08f, center = Offset(w * 0.89f, h * 0.71f))
        drawCircle(color = Color(0xFF81C784), radius = w * 0.04f, center = Offset(w * 0.86f, h * 0.68f))

        drawCircle(color = Color.White, radius = 2.5f, center = Offset(w * 0.10f, h * 0.86f))
        drawCircle(color = Color(0xFFFFEB3B), radius = 2f, center = Offset(w * 0.12f, h * 0.88f))
        drawCircle(color = Color.White, radius = 2.5f, center = Offset(w * 0.58f, h * 0.90f))
    }
}

// ==========================================
// 2. SCENIC WOODEN BENCH WITH CHESSBOARD & PAWN
// ==========================================
@Composable
fun ScenicWoodenBench(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(width = 125.dp, height = 115.dp)) {
        val w = size.width
        val h = size.height

        // 1. Leafy Shade Tree (Rises behind the bench on the left)
        drawOval(
            color = Color(0x33000000),
            topLeft = Offset(w * 0.08f, h * 0.70f),
            size = Size(w * 0.38f, h * 0.18f)
        )
        val trunk = Path().apply {
            moveTo(w * 0.22f, h * 0.38f)
            lineTo(w * 0.19f, h * 0.74f)
            quadraticTo(w * 0.24f, h * 0.76f, w * 0.28f, h * 0.74f)
            lineTo(w * 0.27f, h * 0.38f)
            close()
        }
        drawPath(trunk, color = Color(0xFF5D4037))
        drawLine(color = Color(0xFF5D4037), start = Offset(w * 0.25f, h * 0.42f), end = Offset(w * 0.35f, h * 0.36f), strokeWidth = w * 0.04f)

        // Tree Foliage
        drawCircle(color = Color(0xFF1B5E20), radius = w * 0.18f, center = Offset(w * 0.24f, h * 0.28f))
        drawCircle(color = Color(0xFF2E7D32), radius = w * 0.16f, center = Offset(w * 0.16f, h * 0.24f))
        drawCircle(color = Color(0xFF2E7D32), radius = w * 0.16f, center = Offset(w * 0.32f, h * 0.24f))
        drawCircle(color = Color(0xFF388E3C), radius = w * 0.15f, center = Offset(w * 0.24f, h * 0.16f))
        drawCircle(color = Color(0xFF4CAF50), radius = w * 0.13f, center = Offset(w * 0.20f, h * 0.14f))
        drawCircle(color = Color(0xFF81C784), radius = w * 0.07f, center = Offset(w * 0.17f, h * 0.10f))

        // 2. Soft Bench Ground Shadow
        drawOval(
            color = Color(0x38000000),
            topLeft = Offset(w * 0.22f, h * 0.72f),
            size = Size(w * 0.65f, h * 0.20f)
        )

        // 3. Isometric Wooden Park Bench
        val legColor = Color(0xFF3E2723)
        drawLine(legColor, Offset(w * 0.30f, h * 0.58f), Offset(w * 0.28f, h * 0.78f), strokeWidth = w * 0.035f)
        drawLine(legColor, Offset(w * 0.36f, h * 0.68f), Offset(w * 0.34f, h * 0.88f), strokeWidth = w * 0.04f)
        drawLine(legColor, Offset(w * 0.74f, h * 0.48f), Offset(w * 0.72f, h * 0.68f), strokeWidth = w * 0.035f)
        drawLine(legColor, Offset(w * 0.80f, h * 0.58f), Offset(w * 0.78f, h * 0.78f), strokeWidth = w * 0.04f)

        // Backrest Slats
        val woodDark = Color(0xFF6D4C41)
        val woodLight = Color(0xFF8D6E63)
        for (i in 0..2) {
            val yOffset = i * (h * 0.05f)
            val slatPath = Path().apply {
                moveTo(w * 0.28f, h * 0.42f + yOffset)
                lineTo(w * 0.76f, h * 0.34f + yOffset)
                lineTo(w * 0.76f, h * 0.38f + yOffset)
                lineTo(w * 0.28f, h * 0.46f + yOffset)
                close()
            }
            drawPath(slatPath, color = if (i == 1) woodLight else woodDark)
        }

        // Seat Planks
        val seatPath = Path().apply {
            moveTo(w * 0.26f, h * 0.58f)
            lineTo(w * 0.76f, h * 0.48f)
            lineTo(w * 0.82f, h * 0.58f)
            lineTo(w * 0.34f, h * 0.70f)
            close()
        }
        drawPath(seatPath, color = Color(0xFFA1887F))
        val seatEdge = Path().apply {
            moveTo(w * 0.34f, h * 0.70f)
            lineTo(w * 0.82f, h * 0.58f)
            lineTo(w * 0.82f, h * 0.62f)
            lineTo(w * 0.34f, h * 0.74f)
            close()
        }
        drawPath(seatEdge, color = Color(0xFF5D4037))

        // Curved Armrests
        val leftArm = Path().apply {
            moveTo(w * 0.28f, h * 0.48f)
            quadraticTo(w * 0.34f, h * 0.52f, w * 0.34f, h * 0.68f)
        }
        drawPath(leftArm, color = legColor, style = Stroke(width = w * 0.035f))
        val rightArm = Path().apply {
            moveTo(w * 0.76f, h * 0.38f)
            quadraticTo(w * 0.82f, h * 0.42f, w * 0.82f, h * 0.58f)
        }
        drawPath(rightArm, color = legColor, style = Stroke(width = w * 0.035f))

        // 4. Mini Checkered Chessboard on Bench
        val boardL = w * 0.36f
        val boardT = h * 0.56f
        val boardW = w * 0.18f
        val boardH = h * 0.11f

        drawRoundRect(
            color = Color(0xFF4E342E),
            topLeft = Offset(boardL - 2f, boardT - 2f),
            size = Size(boardW + 4f, boardH + 4f),
            cornerRadius = CornerRadius(2f)
        )
        val sqW = boardW / 4f
        val sqH = boardH / 4f
        for (r in 0 until 4) {
            for (c in 0 until 4) {
                val isLight = (r + c) % 2 == 0
                drawRect(
                    color = if (isLight) Color(0xFFF5E6CC) else Color(0xFF4E7837),
                    topLeft = Offset(boardL + c * sqW, boardT + r * sqH),
                    size = Size(sqW, sqH)
                )
            }
        }

        // 5. White Pawn Spectator with Propeller Beanie Cap!
        val pawnX = w * 0.65f
        val pawnY = h * 0.54f

        drawOval(
            color = Color(0x33000000),
            topLeft = Offset(pawnX - w * 0.06f, pawnY + h * 0.06f),
            size = Size(w * 0.12f, h * 0.04f)
        )
        val pawnBody = Path().apply {
            moveTo(pawnX - w * 0.03f, pawnY - h * 0.01f)
            quadraticTo(pawnX - w * 0.06f, pawnY + h * 0.04f, pawnX - w * 0.07f, pawnY + h * 0.07f)
            lineTo(pawnX + w * 0.07f, pawnY + h * 0.07f)
            quadraticTo(pawnX + w * 0.06f, pawnY + h * 0.04f, pawnX + w * 0.03f, pawnY - h * 0.01f)
            close()
        }
        drawPath(pawnBody, color = Color(0xFFECEFF1))
        drawPath(pawnBody, color = Color(0xFFB0BEC5), style = Stroke(width = 1.2f))
        drawRoundRect(
            color = Color(0xFFCFD8DC),
            topLeft = Offset(pawnX - w * 0.04f, pawnY - h * 0.02f),
            size = Size(w * 0.08f, h * 0.02f),
            cornerRadius = CornerRadius(2f)
        )
        drawCircle(color = Color(0xFFECEFF1), radius = w * 0.052f, center = Offset(pawnX, pawnY - h * 0.065f))
        drawCircle(color = Color(0xFFCFD8DC), radius = w * 0.052f, center = Offset(pawnX, pawnY - h * 0.065f), style = Stroke(width = 1.2f))
        drawCircle(color = Color.White, radius = w * 0.02f, center = Offset(pawnX - w * 0.015f, pawnY - h * 0.085f))

        // Colorful Propeller Beanie Cap!
        val capCenter = Offset(pawnX, pawnY - h * 0.09f)
        drawArc(
            color = Color(0xFFE53935),
            startAngle = 180f,
            sweepAngle = 60f,
            useCenter = true,
            topLeft = Offset(capCenter.x - w * 0.05f, capCenter.y - h * 0.04f),
            size = Size(w * 0.10f, h * 0.06f)
        )
        drawArc(
            color = Color(0xFFFFEB3B),
            startAngle = 240f,
            sweepAngle = 60f,
            useCenter = true,
            topLeft = Offset(capCenter.x - w * 0.05f, capCenter.y - h * 0.04f),
            size = Size(w * 0.10f, h * 0.06f)
        )
        drawArc(
            color = Color(0xFF1E88E5),
            startAngle = 300f,
            sweepAngle = 60f,
            useCenter = true,
            topLeft = Offset(capCenter.x - w * 0.05f, capCenter.y - h * 0.04f),
            size = Size(w * 0.10f, h * 0.06f)
        )
        drawCircle(color = Color(0xFF212121), radius = 2f, center = Offset(capCenter.x, capCenter.y - h * 0.038f))
        drawLine(
            color = Color(0xFFFFB300),
            start = Offset(capCenter.x - w * 0.04f, capCenter.y - h * 0.045f),
            end = Offset(capCenter.x + w * 0.04f, capCenter.y - h * 0.045f),
            strokeWidth = 2.5f
        )
        drawCircle(color = Color(0xFFD81B60), radius = 1.5f, center = Offset(capCenter.x, capCenter.y - h * 0.045f))

        // 6. Scattered Daisies & Shrub at base
        drawCircle(color = Color(0xFF388E3C), radius = w * 0.07f, center = Offset(w * 0.85f, h * 0.72f))
        drawCircle(color = Color(0xFF4CAF50), radius = w * 0.055f, center = Offset(w * 0.87f, h * 0.70f))
        drawCircle(color = Color.White, radius = 3f, center = Offset(w * 0.42f, h * 0.82f))
        drawCircle(color = Color(0xFFFFCA28), radius = 1.5f, center = Offset(w * 0.42f, h * 0.82f))
        drawCircle(color = Color.White, radius = 3f, center = Offset(w * 0.88f, h * 0.84f))
        drawCircle(color = Color(0xFFFFCA28), radius = 1.5f, center = Offset(w * 0.88f, h * 0.84f))
    }
}

// ==========================================
// 3. SCENIC POND WITH FROG ON ROCK
// ==========================================
@Composable
fun ScenicPondWithFrog(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(width = 115.dp, height = 95.dp)) {
        val w = size.width
        val h = size.height

        // 1. Outer Grass / Moss Border
        drawOval(
            color = Color(0xFF2E5930),
            topLeft = Offset(w * 0.04f, h * 0.12f),
            size = Size(w * 0.92f, h * 0.78f)
        )
        drawOval(
            color = Color(0xFF4A7C59),
            topLeft = Offset(w * 0.07f, h * 0.15f),
            size = Size(w * 0.86f, h * 0.72f)
        )
        // Deep Water base
        drawOval(
            color = Color(0xFF0288D1),
            topLeft = Offset(w * 0.10f, h * 0.18f),
            size = Size(w * 0.80f, h * 0.66f)
        )
        // Shallow water shimmer
        drawOval(
            color = Color(0xFF29B6F6),
            topLeft = Offset(w * 0.15f, h * 0.22f),
            size = Size(w * 0.70f, h * 0.56f)
        )
        // Water ripples / highlight arcs
        drawOval(
            color = Color(0x66E1F5FE),
            topLeft = Offset(w * 0.22f, h * 0.28f),
            size = Size(w * 0.46f, h * 0.22f)
        )
        drawOval(
            color = Color(0x44FFFFFF),
            topLeft = Offset(w * 0.32f, h * 0.48f),
            size = Size(w * 0.35f, h * 0.14f)
        )

        // 2. Lily Pads
        val pad1 = Offset(w * 0.32f, h * 0.52f)
        val r1 = w * 0.12f
        drawCircle(color = Color(0xFF2E7D32), radius = r1, center = pad1)
        drawPath(
            Path().apply {
                moveTo(pad1.x, pad1.y)
                lineTo(pad1.x + r1, pad1.y - h * 0.04f)
                lineTo(pad1.x + r1, pad1.y + h * 0.04f)
                close()
            },
            color = Color(0xFF0288D1)
        )

        val pad2 = Offset(w * 0.52f, h * 0.62f)
        val r2 = w * 0.09f
        drawCircle(color = Color(0xFF388E3C), radius = r2, center = pad2)
        drawPath(
            Path().apply {
                moveTo(pad2.x, pad2.y)
                lineTo(pad2.x - r2, pad2.y - h * 0.03f)
                lineTo(pad2.x - r2, pad2.y + h * 0.03f)
                close()
            },
            color = Color(0xFF0288D1)
        )

        val pad3 = Offset(w * 0.28f, h * 0.35f)
        val r3 = w * 0.075f
        drawCircle(color = Color(0xFF2E7D32), radius = r3, center = pad3)

        // 3. Pink Lotus Flowers
        val lot1 = Offset(w * 0.22f, h * 0.50f)
        for (i in 0 until 6) {
            val angle = Math.toRadians((i * 60).toDouble())
            val px = lot1.x + (Math.cos(angle) * w * 0.04f).toFloat()
            val py = lot1.y + (Math.sin(angle) * h * 0.04f).toFloat()
            drawCircle(color = Color(0xFFEC407A), radius = w * 0.03f, center = Offset(px, py))
        }
        drawCircle(color = Color(0xFFF8BBD0), radius = w * 0.025f, center = lot1)
        drawCircle(color = Color(0xFFFFEB3B), radius = w * 0.015f, center = lot1)

        val lot2 = Offset(w * 0.44f, h * 0.40f)
        drawCircle(color = Color(0xFFE91E63), radius = w * 0.03f, center = lot2)
        drawCircle(color = Color(0xFFF48FB1), radius = w * 0.018f, center = lot2)
        drawCircle(color = Color(0xFFFFEB3B), radius = w * 0.01f, center = lot2)

        // 4. Smooth River Boulder on Top-Right Rim
        val rockCenter = Offset(w * 0.78f, h * 0.40f)
        val rockW = w * 0.24f
        val rockH = h * 0.24f
        drawOval(
            color = Color(0x44000000),
            topLeft = Offset(rockCenter.x - rockW * 0.45f, rockCenter.y),
            size = Size(rockW * 0.9f, rockH * 0.6f)
        )
        drawOval(
            color = Color(0xFFB0BEC5),
            topLeft = Offset(rockCenter.x - rockW * 0.5f, rockCenter.y - rockH * 0.5f),
            size = Size(rockW, rockH)
        )
        drawOval(
            color = Color(0xFFECEFF1),
            topLeft = Offset(rockCenter.x - rockW * 0.42f, rockCenter.y - rockH * 0.45f),
            size = Size(rockW * 0.75f, rockH * 0.55f)
        )

        // 5. Cute Bright Green Frog perched on top of the boulder!
        val frogX = rockCenter.x - w * 0.02f
        val frogY = rockCenter.y - rockH * 0.35f

        drawCircle(color = Color(0xFF388E3C), radius = w * 0.038f, center = Offset(frogX - w * 0.045f, frogY + h * 0.025f))
        drawCircle(color = Color(0xFF388E3C), radius = w * 0.038f, center = Offset(frogX + w * 0.045f, frogY + h * 0.025f))
        drawOval(
            color = Color(0xFF66BB6A),
            topLeft = Offset(frogX - w * 0.055f, frogY - h * 0.04f),
            size = Size(w * 0.11f, h * 0.08f)
        )
        drawOval(
            color = Color(0xFFC8E6C9),
            topLeft = Offset(frogX - w * 0.035f, frogY - h * 0.015f),
            size = Size(w * 0.07f, h * 0.05f)
        )
        val eyeLeft = Offset(frogX - w * 0.032f, frogY - h * 0.045f)
        val eyeRight = Offset(frogX + w * 0.032f, frogY - h * 0.045f)
        drawCircle(color = Color(0xFF66BB6A), radius = w * 0.028f, center = eyeLeft)
        drawCircle(color = Color(0xFF66BB6A), radius = w * 0.028f, center = eyeRight)
        drawCircle(color = Color.White, radius = w * 0.020f, center = eyeLeft)
        drawCircle(color = Color.White, radius = w * 0.020f, center = eyeRight)
        drawCircle(color = Color.Black, radius = w * 0.011f, center = eyeLeft)
        drawCircle(color = Color.Black, radius = w * 0.011f, center = eyeRight)
        drawCircle(color = Color.White, radius = w * 0.004f, center = Offset(eyeLeft.x - 1f, eyeLeft.y - 1f))
        drawCircle(color = Color.White, radius = w * 0.004f, center = Offset(eyeRight.x - 1f, eyeRight.y - 1f))
        drawArc(
            color = Color(0xFF2E7D32),
            startAngle = 30f,
            sweepAngle = 120f,
            useCenter = false,
            topLeft = Offset(frogX - w * 0.02f, frogY - h * 0.015f),
            size = Size(w * 0.04f, h * 0.02f),
            style = Stroke(width = 1.5f)
        )

        // 6. Smooth Pebbles and Wildflowers
        drawOval(color = Color(0xFFCFD8DC), topLeft = Offset(w * 0.10f, h * 0.72f), size = Size(w * 0.06f, h * 0.04f))
        drawOval(color = Color(0xFF90A4AE), topLeft = Offset(w * 0.18f, h * 0.78f), size = Size(w * 0.05f, h * 0.035f))
        drawOval(color = Color(0xFFCFD8DC), topLeft = Offset(w * 0.88f, h * 0.58f), size = Size(w * 0.05f, h * 0.035f))
        drawCircle(color = Color.White, radius = 2.5f, center = Offset(w * 0.12f, h * 0.28f))
        drawCircle(color = Color(0xFFFFEB3B), radius = 1.5f, center = Offset(w * 0.12f, h * 0.28f))
        drawCircle(color = Color(0xFFFFEB3B), radius = 2f, center = Offset(w * 0.76f, h * 0.70f))
    }
}

// ========================================================
// 4. SCENIC APPLE TREE WITH CYPRESS (Iconic Forest Duo)
// ========================================================
@Composable
fun ScenicAppleTreeWithCypress(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(width = 115.dp, height = 120.dp)) {
        val w = size.width
        val h = size.height

        drawOval(
            color = Color(0x33000000),
            topLeft = Offset(w * 0.10f, h * 0.78f),
            size = Size(w * 0.80f, h * 0.18f)
        )

        // 1. Tall Columnar Cypress / Topiary Tree
        val cypX = w * 0.30f
        drawRect(color = Color(0xFF4E342E), topLeft = Offset(cypX - w * 0.03f, h * 0.65f), size = Size(w * 0.06f, h * 0.20f))
        drawRoundRect(
            color = Color(0xFF1B5E20),
            topLeft = Offset(cypX - w * 0.12f, h * 0.44f),
            size = Size(w * 0.24f, h * 0.28f),
            cornerRadius = CornerRadius(w * 0.12f, w * 0.12f)
        )
        drawRoundRect(
            color = Color(0xFF2E7D32),
            topLeft = Offset(cypX - w * 0.10f, h * 0.28f),
            size = Size(w * 0.20f, h * 0.24f),
            cornerRadius = CornerRadius(w * 0.10f, w * 0.10f)
        )
        drawRoundRect(
            color = Color(0xFF388E3C),
            topLeft = Offset(cypX - w * 0.08f, h * 0.14f),
            size = Size(w * 0.16f, h * 0.20f),
            cornerRadius = CornerRadius(w * 0.08f, w * 0.08f)
        )
        drawLine(
            color = Color(0xFF81C784),
            start = Offset(cypX - w * 0.03f, h * 0.16f),
            end = Offset(cypX - w * 0.03f, h * 0.60f),
            strokeWidth = 2.5f
        )

        // 2. Main Apple Tree
        val appX = w * 0.68f
        val trunk = Path().apply {
            moveTo(appX - w * 0.05f, h * 0.48f)
            lineTo(appX - w * 0.08f, h * 0.84f)
            quadraticTo(appX, h * 0.82f, appX + w * 0.08f, h * 0.84f)
            lineTo(appX + w * 0.05f, h * 0.48f)
            close()
        }
        drawPath(trunk, color = Color(0xFF5D4037))
        drawLine(color = Color(0xFF3E2723), start = Offset(appX, h * 0.52f), end = Offset(appX - w * 0.02f, h * 0.78f), strokeWidth = 2.5f)

        drawCircle(color = Color(0xFF1B5E20), radius = w * 0.26f, center = Offset(appX, h * 0.42f))
        drawCircle(color = Color(0xFF2E7D32), radius = w * 0.22f, center = Offset(appX - w * 0.14f, h * 0.38f))
        drawCircle(color = Color(0xFF2E7D32), radius = w * 0.22f, center = Offset(appX + w * 0.14f, h * 0.38f))
        drawCircle(color = Color(0xFF388E3C), radius = w * 0.24f, center = Offset(appX, h * 0.26f))
        drawCircle(color = Color(0xFF4CAF50), radius = w * 0.18f, center = Offset(appX - w * 0.06f, h * 0.22f))
        drawCircle(color = Color(0xFF81C784), radius = w * 0.10f, center = Offset(appX - w * 0.10f, h * 0.16f))

        val apples = listOf(
            Offset(appX - w * 0.12f, h * 0.28f),
            Offset(appX + w * 0.14f, h * 0.26f),
            Offset(appX - w * 0.02f, h * 0.38f),
            Offset(appX - w * 0.14f, h * 0.44f),
            Offset(appX + w * 0.10f, h * 0.42f),
            Offset(appX + w * 0.02f, h * 0.16f)
        )
        for (pos in apples) {
            drawCircle(color = Color(0xFFD32F2F), radius = w * 0.045f, center = pos)
            drawCircle(color = Color(0xFFFF5252), radius = w * 0.020f, center = Offset(pos.x - 1.5f, pos.y - 1.5f))
            drawCircle(color = Color(0xFF8BC34A), radius = w * 0.015f, center = Offset(pos.x + 2f, pos.y - w * 0.045f))
        }

        drawCircle(color = Color(0xFFD32F2F), radius = w * 0.035f, center = Offset(appX - w * 0.16f, h * 0.82f))
        drawCircle(color = Color(0xFFD32F2F), radius = w * 0.030f, center = Offset(appX + w * 0.18f, h * 0.85f))

        drawCircle(color = Color.White, radius = 3f, center = Offset(w * 0.48f, h * 0.80f))
        drawCircle(color = Color(0xFFFFCA28), radius = 1.5f, center = Offset(w * 0.48f, h * 0.80f))
    }
}

// ==========================================
// 5. SCENIC APPLE TREE (Standalone)
// ==========================================
@Composable
fun ScenicAppleTree(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(76.dp)) {
        val w = size.width
        val h = size.height

        drawOval(
            color = Color(0x33000000),
            topLeft = Offset(w * 0.2f, h * 0.78f),
            size = Size(w * 0.6f, h * 0.2f)
        )

        val trunkPath = Path().apply {
            moveTo(w * 0.42f, h * 0.5f)
            lineTo(w * 0.38f, h * 0.88f)
            quadraticTo(w * 0.5f, h * 0.85f, w * 0.62f, h * 0.88f)
            lineTo(w * 0.58f, h * 0.5f)
            close()
        }
        drawPath(trunkPath, color = Color(0xFF5D4037))
        drawPath(
            Path().apply {
                moveTo(w * 0.46f, h * 0.55f)
                lineTo(w * 0.44f, h * 0.82f)
            },
            color = Color(0xFF3E2723),
            style = Stroke(width = w * 0.03f)
        )

        drawCircle(color = Color(0xFF1B5E20), radius = w * 0.28f, center = Offset(w * 0.5f, h * 0.46f))
        drawCircle(color = Color(0xFF2E7D32), radius = w * 0.24f, center = Offset(w * 0.34f, h * 0.42f))
        drawCircle(color = Color(0xFF2E7D32), radius = w * 0.24f, center = Offset(w * 0.66f, h * 0.42f))
        drawCircle(color = Color(0xFF388E3C), radius = w * 0.26f, center = Offset(w * 0.5f, h * 0.32f))
        drawCircle(color = Color(0xFF4CAF50), radius = w * 0.2f, center = Offset(w * 0.44f, h * 0.28f))
        drawCircle(color = Color(0xFF81C784), radius = w * 0.12f, center = Offset(w * 0.38f, h * 0.22f))

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
            drawCircle(color = Color(0xFF8BC34A), radius = w * 0.02f, center = Offset(pos.x + w * 0.02f, pos.y - h * 0.035f))
        }
    }
}

// ==========================================
// 6. SCENIC WHITE CASTLE TOWER WITH IVY
// ==========================================
@Composable
fun ScenicWhiteCastleTower(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(width = 110.dp, height = 125.dp)) {
        val w = size.width
        val h = size.height

        drawOval(
            color = Color(0x38000000),
            topLeft = Offset(w * 0.12f, h * 0.78f),
            size = Size(w * 0.76f, h * 0.20f)
        )
        drawOval(color = Color(0xFF388E3C), topLeft = Offset(w * 0.10f, h * 0.74f), size = Size(w * 0.80f, h * 0.16f))

        val towL = w * 0.26f
        val towT = h * 0.30f
        val towW = w * 0.48f
        val towH = h * 0.50f

        drawRoundRect(
            color = Color(0xFFF1F3F5),
            topLeft = Offset(towL, towT),
            size = Size(towW, towH),
            cornerRadius = CornerRadius(w * 0.04f, w * 0.04f)
        )
        drawRoundRect(
            color = Color(0xFFCFD8DC),
            topLeft = Offset(towL + towW * 0.55f, towT),
            size = Size(towW * 0.45f, towH),
            cornerRadius = CornerRadius(0f, w * 0.04f)
        )
        for (i in 1..4) {
            val y = towT + (towH / 5f) * i
            drawLine(color = Color(0xFFB0BEC5), start = Offset(towL, y), end = Offset(towL + towW, y), strokeWidth = 1.5f)
        }

        val winCenter = Offset(towL + towW * 0.4f, towT + towH * 0.35f)
        drawRoundRect(
            color = Color(0xFF37474F),
            topLeft = Offset(winCenter.x - w * 0.035f, winCenter.y - h * 0.05f),
            size = Size(w * 0.07f, h * 0.10f),
            cornerRadius = CornerRadius(w * 0.035f, w * 0.035f)
        )

        val batT = h * 0.22f
        val batH = h * 0.10f
        val batW = towW + w * 0.08f
        val batL = towL - w * 0.04f

        drawRoundRect(
            color = Color(0xFFE0E0E0),
            topLeft = Offset(batL, batT),
            size = Size(batW, batH),
            cornerRadius = CornerRadius(w * 0.02f)
        )
        drawRoundRect(
            color = Color(0xFFB0BEC5),
            topLeft = Offset(batL + batW * 0.55f, batT),
            size = Size(batW * 0.45f, batH),
            cornerRadius = CornerRadius(0f, w * 0.02f)
        )
        val gapW = w * 0.07f
        drawRect(color = Color.Transparent, topLeft = Offset(batL + w * 0.08f, batT), size = Size(gapW, batH * 0.55f))
        drawRect(color = Color.Transparent, topLeft = Offset(batL + batW * 0.50f, batT), size = Size(gapW, batH * 0.55f))

        val poleX = towL + towW * 0.48f
        drawLine(color = Color(0xFF37474F), start = Offset(poleX, batT), end = Offset(poleX, h * 0.05f), strokeWidth = 3f)
        drawCircle(color = Color(0xFFFFD54F), radius = 3f, center = Offset(poleX, h * 0.05f))

        val flag = Path().apply {
            moveTo(poleX, h * 0.06f)
            lineTo(poleX + w * 0.28f, h * 0.11f)
            lineTo(poleX + w * 0.22f, h * 0.15f)
            lineTo(poleX + w * 0.28f, h * 0.19f)
            lineTo(poleX, h * 0.22f)
            close()
        }
        drawPath(flag, color = Color(0xFFF59E0B))

        val vine1 = Path().apply {
            moveTo(towL + w * 0.04f, towT + towH)
            quadraticTo(towL + towW * 0.6f, towT + towH * 0.65f, towL + w * 0.08f, towT + towH * 0.40f)
            quadraticTo(towL + towW * 0.75f, towT + towH * 0.25f, towL + towW * 0.90f, towT + towH * 0.10f)
        }
        drawPath(vine1, color = Color(0xFF2E7D32), style = Stroke(width = w * 0.045f))

        val leaves = listOf(
            Offset(towL + w * 0.16f, towT + towH * 0.72f),
            Offset(towL + w * 0.32f, towT + towH * 0.58f),
            Offset(towL + w * 0.14f, towT + towH * 0.38f),
            Offset(towL + w * 0.42f, towT + towH * 0.32f),
            Offset(towL + w * 0.65f, towT + towH * 0.20f),
            Offset(towL + towW * 0.85f, towT + towH * 0.12f)
        )
        for (leaf in leaves) {
            drawCircle(color = Color(0xFF4CAF50), radius = w * 0.038f, center = leaf)
            drawCircle(color = Color(0xFF81C784), radius = w * 0.018f, center = leaf)
        }

        val berries = listOf(
            Offset(towL - w * 0.05f, h * 0.78f),
            Offset(towL - w * 0.02f, h * 0.74f),
            Offset(towL - w * 0.07f, h * 0.73f),
            Offset(towL + w * 0.01f, h * 0.78f)
        )
        for (b in berries) {
            drawCircle(color = Color(0xFFFB8C00), radius = w * 0.025f, center = b)
        }
        drawCircle(color = Color.White, radius = 4f, center = Offset(towL + towW * 0.78f, h * 0.82f))
        drawCircle(color = Color(0xFFFFCA28), radius = 2f, center = Offset(towL + towW * 0.78f, h * 0.82f))
    }
}

// ==========================================
// 7. SCENIC ROCKY MOUNTAIN WITH GOAT
// ==========================================
@Composable
fun ScenicRockyMountainWithGoat(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(width = 115.dp, height = 130.dp)) {
        val w = size.width
        val h = size.height

        drawOval(
            color = Color(0x38000000),
            topLeft = Offset(w * 0.08f, h * 0.82f),
            size = Size(w * 0.84f, h * 0.16f)
        )

        val peak1 = Path().apply {
            moveTo(w * 0.28f, h * 0.32f)
            lineTo(w * 0.44f, h * 0.34f)
            lineTo(w * 0.52f, h * 0.86f)
            lineTo(w * 0.16f, h * 0.86f)
            close()
        }
        drawPath(peak1, color = Color(0xFF90A4AE))

        val peak1Light = Path().apply {
            moveTo(w * 0.28f, h * 0.32f)
            lineTo(w * 0.38f, h * 0.33f)
            lineTo(w * 0.34f, h * 0.86f)
            lineTo(w * 0.16f, h * 0.86f)
            close()
        }
        drawPath(peak1Light, color = Color(0xFFCFD8DC))

        val peak2 = Path().apply {
            moveTo(w * 0.48f, h * 0.44f)
            lineTo(w * 0.72f, h * 0.48f)
            lineTo(w * 0.82f, h * 0.86f)
            lineTo(w * 0.44f, h * 0.86f)
            close()
        }
        drawPath(peak2, color = Color(0xFF78909C))

        val peak2Light = Path().apply {
            moveTo(w * 0.48f, h * 0.44f)
            lineTo(w * 0.60f, h * 0.46f)
            lineTo(w * 0.56f, h * 0.86f)
            lineTo(w * 0.44f, h * 0.86f)
            close()
        }
        drawPath(peak2Light, color = Color(0xFFB0BEC5))

        drawLine(color = Color(0xFF546E7A), start = Offset(w * 0.34f, h * 0.45f), end = Offset(w * 0.38f, h * 0.65f), strokeWidth = 2f)
        drawLine(color = Color(0xFF546E7A), start = Offset(w * 0.56f, h * 0.55f), end = Offset(w * 0.62f, h * 0.75f), strokeWidth = 2f)

        drawCircle(color = Color(0xFF388E3C), radius = w * 0.08f, center = Offset(w * 0.22f, h * 0.84f))
        drawCircle(color = Color(0xFF4CAF50), radius = w * 0.06f, center = Offset(w * 0.48f, h * 0.86f))
        drawCircle(color = Color(0xFF2E7D32), radius = w * 0.09f, center = Offset(w * 0.75f, h * 0.84f))
        drawOval(color = Color(0xFFECEFF1), topLeft = Offset(w * 0.35f, h * 0.85f), size = Size(w * 0.09f, h * 0.05f))

        val goatX = w * 0.34f
        val goatY = h * 0.26f

        val legColor = Color(0xFF4E342E)
        drawLine(legColor, Offset(goatX + w * 0.06f, goatY + h * 0.02f), Offset(goatX + w * 0.06f, goatY + h * 0.08f), strokeWidth = 2.5f)
        drawLine(legColor, Offset(goatX + w * 0.08f, goatY + h * 0.02f), Offset(goatX + w * 0.09f, goatY + h * 0.08f), strokeWidth = 2.5f)
        drawLine(legColor, Offset(goatX - w * 0.04f, goatY + h * 0.02f), Offset(goatX - w * 0.04f, goatY + h * 0.08f), strokeWidth = 2.5f)
        drawLine(legColor, Offset(goatX - w * 0.02f, goatY + h * 0.02f), Offset(goatX - w * 0.01f, goatY + h * 0.08f), strokeWidth = 2.5f)

        drawOval(
            color = Color(0xFFC08A4E),
            topLeft = Offset(goatX - w * 0.06f, goatY - h * 0.02f),
            size = Size(w * 0.15f, h * 0.06f)
        )

        val neckPath = Path().apply {
            moveTo(goatX - w * 0.04f, goatY + h * 0.01f)
            lineTo(goatX - w * 0.08f, goatY - h * 0.05f)
            lineTo(goatX - w * 0.04f, goatY - h * 0.07f)
            lineTo(goatX - w * 0.01f, goatY - h * 0.01f)
            close()
        }
        drawPath(neckPath, color = Color(0xFFC08A4E))

        val headCenter = Offset(goatX - w * 0.06f, goatY - h * 0.06f)
        drawOval(
            color = Color(0xFFD4A373),
            topLeft = Offset(headCenter.x - w * 0.04f, headCenter.y - h * 0.025f),
            size = Size(w * 0.07f, h * 0.045f)
        )
        drawCircle(color = Color(0xFF5D4037), radius = 2f, center = Offset(headCenter.x - w * 0.038f, headCenter.y))

        val beard = Path().apply {
            moveTo(headCenter.x - w * 0.035f, headCenter.y + h * 0.015f)
            lineTo(headCenter.x - w * 0.045f, headCenter.y + h * 0.04f)
            lineTo(headCenter.x - w * 0.025f, headCenter.y + h * 0.02f)
            close()
        }
        drawPath(beard, color = Color.White)

        val horn1 = Path().apply {
            moveTo(headCenter.x - w * 0.01f, headCenter.y - h * 0.02f)
            quadraticTo(headCenter.x + w * 0.04f, headCenter.y - h * 0.08f, headCenter.x + w * 0.06f, headCenter.y - h * 0.04f)
        }
        drawPath(horn1, color = Color(0xFF3E2723), style = Stroke(width = 3.5f))

        val horn2 = Path().apply {
            moveTo(headCenter.x + w * 0.005f, headCenter.y - h * 0.02f)
            quadraticTo(headCenter.x + w * 0.055f, headCenter.y - h * 0.075f, headCenter.x + w * 0.075f, headCenter.y - h * 0.035f)
        }
        drawPath(horn2, color = Color(0xFF4E342E), style = Stroke(width = 2.5f))
    }
}

// ========================================================
// 8. SCENIC ANCIENT ALTAR WITH PIECES (Mountain Dais)
// ========================================================
@Composable
fun ScenicAncientAltarWithPieces(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(width = 110.dp, height = 110.dp)) {
        val w = size.width
        val h = size.height

        drawOval(
            color = Color(0x38000000),
            topLeft = Offset(w * 0.10f, h * 0.70f),
            size = Size(w * 0.80f, h * 0.22f)
        )
        drawOval(
            color = Color(0xFF388E3C),
            topLeft = Offset(w * 0.08f, h * 0.58f),
            size = Size(w * 0.84f, h * 0.32f)
        )

        val cx = w * 0.50f
        val cy = h * 0.60f
        val rx = w * 0.38f
        val ry = h * 0.18f

        val baseOct = Path().apply {
            val numPts = 8
            for (i in 0 until numPts) {
                val angle = (i * 2.0 * Math.PI / numPts) + (Math.PI / 8.0)
                val px = cx + (Math.cos(angle) * rx).toFloat()
                val py = cy + (Math.sin(angle) * ry).toFloat() + h * 0.08f
                if (i == 0) moveTo(px, py) else lineTo(px, py)
            }
            close()
        }
        drawPath(baseOct, color = Color(0xFF90A4AE))

        val topOct = Path().apply {
            val numPts = 8
            for (i in 0 until numPts) {
                val angle = (i * 2.0 * Math.PI / numPts) + (Math.PI / 8.0)
                val px = cx + (Math.cos(angle) * rx).toFloat()
                val py = cy + (Math.sin(angle) * ry).toFloat()
                if (i == 0) moveTo(px, py) else lineTo(px, py)
            }
            close()
        }
        drawPath(topOct, color = Color(0xFFECEFF1))
        drawPath(topOct, color = Color(0xFFCFD8DC), style = Stroke(width = 2f))

        drawOval(
            color = Color(0xFFB0BEC5),
            topLeft = Offset(cx - rx * 0.65f, cy - ry * 0.65f),
            size = Size(rx * 1.3f, ry * 1.3f),
            style = Stroke(width = 1.5f)
        )

        val monW = w * 0.16f
        val monH = h * 0.28f
        drawRoundRect(
            color = Color(0xFFCFD8DC),
            topLeft = Offset(cx - monW * 0.5f, cy - monH * 0.85f),
            size = Size(monW, monH),
            cornerRadius = CornerRadius(4f)
        )
        drawCircle(color = Color(0xFFFFD54F), radius = w * 0.035f, center = Offset(cx, cy - monH * 0.85f))
        drawCircle(color = Color(0xFFFFA000), radius = w * 0.020f, center = Offset(cx, cy - monH * 0.85f))

        val numPawns = 6
        for (i in 0 until numPawns) {
            val angle = i * (2.0 * Math.PI / numPawns)
            val px = cx + (Math.cos(angle) * rx * 0.62f).toFloat()
            val py = cy + (Math.sin(angle) * ry * 0.62f).toFloat()

            drawCircle(color = Color(0xFF78909C), radius = w * 0.022f, center = Offset(px, py - h * 0.025f))
            drawRoundRect(
                color = Color(0xFF607D8B),
                topLeft = Offset(px - w * 0.025f, py - h * 0.015f),
                size = Size(w * 0.05f, h * 0.035f),
                cornerRadius = CornerRadius(2f)
            )
        }
    }
}

// ==========================================
// 9. SCENIC MILESTONE CHEST
// ==========================================
@Composable
fun ScenicMilestoneChest(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(68.dp)) {
        val w = size.width
        val h = size.height

        drawCircle(
            color = Color(0x33FFD700),
            radius = w * 0.44f,
            center = Offset(w * 0.5f, h * 0.55f)
        )
        drawOval(
            color = Color(0x44000000),
            topLeft = Offset(w * 0.15f, h * 0.72f),
            size = Size(w * 0.7f, h * 0.2f)
        )

        val bodyColor = Color(0xFF6D4C41)
        val bodyTop = h * 0.48f
        val bodyHeight = h * 0.32f
        drawRoundRect(
            color = bodyColor,
            topLeft = Offset(w * 0.2f, bodyTop),
            size = Size(w * 0.6f, bodyHeight),
            cornerRadius = CornerRadius(w * 0.04f)
        )

        val lidPath = Path().apply {
            moveTo(w * 0.18f, bodyTop)
            quadraticTo(w * 0.5f, h * 0.24f, w * 0.82f, bodyTop)
            close()
        }
        drawPath(lidPath, color = Color(0xFF795548))

        val gold = Color(0xFFFFD54F)
        val darkGold = Color(0xFFFFA000)

        drawRect(color = gold, topLeft = Offset(w * 0.30f, bodyTop), size = Size(w * 0.08f, bodyHeight))
        drawRect(color = gold, topLeft = Offset(w * 0.62f, bodyTop), size = Size(w * 0.08f, bodyHeight))

        val lockCenter = Offset(w * 0.5f, bodyTop + h * 0.04f)
        drawCircle(color = darkGold, radius = w * 0.09f, center = lockCenter)
        drawCircle(color = gold, radius = w * 0.075f, center = lockCenter)
        drawCircle(color = Color(0xFF261914), radius = w * 0.022f, center = Offset(lockCenter.x, lockCenter.y - 1f))
        drawRect(
            color = Color(0xFF261914),
            topLeft = Offset(lockCenter.x - w * 0.012f, lockCenter.y),
            size = Size(w * 0.024f, h * 0.035f)
        )

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

// ==========================================
// 10. SCENIC DAISY FLOWERS
// ==========================================
@Composable
fun ScenicDaisyFlowers(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(36.dp)) {
        val w = size.width
        val h = size.height

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

// ==========================================
// 11. SCENIC PINE TREE
// ==========================================
@Composable
fun ScenicPineTree(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(64.dp)) {
        val w = size.width
        val h = size.height

        drawOval(
            color = Color(0x33000000),
            topLeft = Offset(w * 0.2f, h * 0.80f),
            size = Size(w * 0.6f, h * 0.18f)
        )

        drawRect(
            color = Color(0xFF4E342E),
            topLeft = Offset(w * 0.44f, h * 0.65f),
            size = Size(w * 0.12f, h * 0.22f)
        )

        val p1 = Path().apply {
            moveTo(w * 0.5f, h * 0.40f)
            lineTo(w * 0.85f, h * 0.70f)
            lineTo(w * 0.15f, h * 0.70f)
            close()
        }
        drawPath(p1, color = Color(0xFF1B5E20))

        val p2 = Path().apply {
            moveTo(w * 0.5f, h * 0.22f)
            lineTo(w * 0.78f, h * 0.50f)
            lineTo(w * 0.22f, h * 0.50f)
            close()
        }
        drawPath(p2, color = Color(0xFF2E7D32))

        val p3 = Path().apply {
            moveTo(w * 0.5f, h * 0.08f)
            lineTo(w * 0.70f, h * 0.32f)
            lineTo(w * 0.30f, h * 0.32f)
            close()
        }
        drawPath(p3, color = Color(0xFF388E3C))

        drawLine(
            color = Color(0xFF81C784),
            start = Offset(w * 0.5f, h * 0.08f),
            end = Offset(w * 0.30f, h * 0.32f),
            strokeWidth = w * 0.03f
        )
    }
}

// ==========================================
// 12. SCENIC STONE MONUMENT WITH FLAG
// ==========================================
@Composable
fun ScenicStoneMonumentWithFlag(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(70.dp)) {
        val w = size.width
        val h = size.height

        drawOval(
            color = Color(0x33000000),
            topLeft = Offset(w * 0.15f, h * 0.80f),
            size = Size(w * 0.7f, h * 0.16f)
        )

        drawRoundRect(
            color = Color(0xFF616161),
            topLeft = Offset(w * 0.25f, h * 0.65f),
            size = Size(w * 0.50f, h * 0.20f),
            cornerRadius = CornerRadius(w * 0.03f)
        )

        val p = Path().apply {
            moveTo(w * 0.38f, h * 0.22f)
            lineTo(w * 0.62f, h * 0.22f)
            lineTo(w * 0.70f, h * 0.65f)
            lineTo(w * 0.30f, h * 0.65f)
            close()
        }
        drawPath(p, color = Color(0xFF757575))

        val pFacet = Path().apply {
            moveTo(w * 0.50f, h * 0.22f)
            lineTo(w * 0.62f, h * 0.22f)
            lineTo(w * 0.70f, h * 0.65f)
            lineTo(w * 0.50f, h * 0.65f)
            close()
        }
        drawPath(pFacet, color = Color(0xFF9E9E9E))

        drawCircle(color = Color(0xFFFFD54F), radius = w * 0.06f, center = Offset(w * 0.50f, h * 0.40f))

        val poleX = w * 0.50f
        drawLine(color = Color(0xFF212121), start = Offset(poleX, h * 0.22f), end = Offset(poleX, h * 0.06f), strokeWidth = w * 0.03f)

        val flag = Path().apply {
            moveTo(poleX, h * 0.06f)
            lineTo(poleX + w * 0.24f, h * 0.12f)
            lineTo(poleX, h * 0.18f)
            close()
        }
        drawPath(flag, color = Color(0xFFE53935))
    }
}

// ==========================================
// 13. SCENIC MUSHROOM CLUSTER
// ==========================================
@Composable
fun ScenicMushroomCluster(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(44.dp)) {
        val w = size.width
        val h = size.height

        drawRect(color = Color(0xFFF5F5F5), topLeft = Offset(w * 0.28f, h * 0.50f), size = Size(w * 0.12f, h * 0.35f))
        drawRect(color = Color(0xFFF5F5F5), topLeft = Offset(w * 0.58f, h * 0.45f), size = Size(w * 0.10f, h * 0.40f))

        drawArc(
            color = Color(0xFFE53935),
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(w * 0.10f, h * 0.20f),
            size = Size(w * 0.48f, h * 0.35f)
        )
        drawCircle(color = Color.White, radius = w * 0.04f, center = Offset(w * 0.26f, h * 0.28f))
        drawCircle(color = Color.White, radius = w * 0.03f, center = Offset(w * 0.42f, h * 0.32f))
        drawCircle(color = Color.White, radius = w * 0.03f, center = Offset(w * 0.18f, h * 0.34f))

        drawArc(
            color = Color(0xFFFB8C00),
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(w * 0.48f, h * 0.25f),
            size = Size(w * 0.36f, h * 0.26f)
        )
        drawCircle(color = Color.White, radius = w * 0.03f, center = Offset(w * 0.64f, h * 0.32f))
        drawCircle(color = Color.White, radius = w * 0.025f, center = Offset(w * 0.74f, h * 0.36f))

        drawLine(color = Color(0xFF7CB342), start = Offset(w * 0.15f, h * 0.85f), end = Offset(w * 0.10f, h * 0.65f), strokeWidth = w * 0.05f)
        drawLine(color = Color(0xFF8BC34A), start = Offset(w * 0.80f, h * 0.85f), end = Offset(w * 0.85f, h * 0.62f), strokeWidth = w * 0.05f)
    }
}

// ==========================================
// 14. SCENIC BUSH CLUSTER
// ==========================================
@Composable
fun ScenicBushCluster(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(52.dp)) {
        val w = size.width
        val h = size.height

        drawCircle(color = Color(0xFF2E7D32), radius = w * 0.28f, center = Offset(w * 0.35f, h * 0.55f))
        drawCircle(color = Color(0xFF388E3C), radius = w * 0.28f, center = Offset(w * 0.65f, h * 0.55f))
        drawCircle(color = Color(0xFF4CAF50), radius = w * 0.26f, center = Offset(w * 0.50f, h * 0.40f))
        drawCircle(color = Color(0xFF81C784), radius = w * 0.12f, center = Offset(w * 0.42f, h * 0.32f))

        drawCircle(color = Color(0xFFFF4081), radius = w * 0.045f, center = Offset(w * 0.30f, h * 0.45f))
        drawCircle(color = Color(0xFFFFEB3B), radius = w * 0.04f, center = Offset(w * 0.68f, h * 0.42f))
        drawCircle(color = Color.White, radius = w * 0.04f, center = Offset(w * 0.52f, h * 0.60f))
    }
}

// ==========================================
// 15. SCENIC ANCIENT RUINS
// ==========================================
@Composable
fun ScenicAncientRuins(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(80.dp)) {
        val w = size.width
        val h = size.height

        drawOval(color = Color(0x44000000), topLeft = Offset(w * 0.1f, h * 0.8f), size = Size(w * 0.8f, h * 0.18f))

        val pColor = Color(0xFF78909C)
        val pDark = Color(0xFF546E7A)
        drawRoundRect(color = pColor, topLeft = Offset(w * 0.18f, h * 0.32f), size = Size(w * 0.16f, h * 0.52f), cornerRadius = CornerRadius(w * 0.02f))
        drawRect(color = pDark, topLeft = Offset(w * 0.26f, h * 0.32f), size = Size(w * 0.08f, h * 0.52f))

        drawRoundRect(color = pColor, topLeft = Offset(w * 0.66f, h * 0.32f), size = Size(w * 0.16f, h * 0.52f), cornerRadius = CornerRadius(w * 0.02f))
        drawRect(color = pDark, topLeft = Offset(w * 0.74f, h * 0.32f), size = Size(w * 0.08f, h * 0.52f))

        drawRoundRect(color = Color(0xFF90A4AE), topLeft = Offset(w * 0.12f, h * 0.22f), size = Size(w * 0.76f, h * 0.14f), cornerRadius = CornerRadius(w * 0.03f))

        val moss = Path().apply {
            moveTo(w * 0.14f, h * 0.22f)
            quadraticTo(w * 0.30f, h * 0.40f, w * 0.22f, h * 0.55f)
            quadraticTo(w * 0.28f, h * 0.70f, w * 0.24f, h * 0.84f)
        }
        drawPath(moss, color = Color(0xFF388E3C), style = Stroke(width = w * 0.04f))
    }
}

// ==========================================
// 16. SCENIC WOODEN BRIDGE OVER STREAM
// ==========================================
@Composable
fun ScenicWoodenBridgeOverStream(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(86.dp)) {
        val w = size.width
        val h = size.height

        val water = Path().apply {
            moveTo(0f, h * 0.35f)
            quadraticTo(w * 0.5f, h * 0.45f, w, h * 0.35f)
            lineTo(w, h * 0.65f)
            quadraticTo(w * 0.5f, h * 0.75f, 0f, h * 0.65f)
            close()
        }
        drawPath(water, color = Color(0xFF0288D1))
        drawPath(water, color = Color(0xFF29B6F6), style = Stroke(width = w * 0.02f))

        val wood = Color(0xFF6D4C41)
        val woodDark = Color(0xFF4E342E)
        for (i in 0 until 5) {
            val px = w * (0.15f + i * 0.15f)
            drawRoundRect(color = wood, topLeft = Offset(px, h * 0.25f), size = Size(w * 0.12f, h * 0.50f), cornerRadius = CornerRadius(w * 0.02f))
            drawRect(color = woodDark, topLeft = Offset(px + w * 0.08f, h * 0.25f), size = Size(w * 0.04f, h * 0.50f))
        }

        drawLine(color = Color(0xFF8D6E63), start = Offset(w * 0.10f, h * 0.25f), end = Offset(w * 0.90f, h * 0.25f), strokeWidth = w * 0.04f)
        drawLine(color = Color(0xFF8D6E63), start = Offset(w * 0.10f, h * 0.75f), end = Offset(w * 0.90f, h * 0.75f), strokeWidth = w * 0.04f)
    }
}

// ==========================================
// 17. SCENIC WATERFALL CLIFF
// ==========================================
@Composable
fun ScenicWaterfallCliff(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(88.dp)) {
        val w = size.width
        val h = size.height

        val cliff = Path().apply {
            moveTo(w * 0.1f, h * 0.1f)
            lineTo(w * 0.9f, h * 0.1f)
            lineTo(w * 0.85f, h * 0.85f)
            lineTo(w * 0.15f, h * 0.85f)
            close()
        }
        drawPath(cliff, color = Color(0xFF455A64))

        val fall = Path().apply {
            moveTo(w * 0.38f, h * 0.10f)
            lineTo(w * 0.62f, h * 0.10f)
            lineTo(w * 0.66f, h * 0.85f)
            lineTo(w * 0.34f, h * 0.85f)
            close()
        }
        drawPath(fall, color = Color(0xFF00E5FF))
        drawPath(fall, color = Color.White, style = Stroke(width = w * 0.03f))

        drawCircle(color = Color(0xCCFFFFFF), radius = w * 0.10f, center = Offset(w * 0.38f, h * 0.82f))
        drawCircle(color = Color(0xCCFFFFFF), radius = w * 0.12f, center = Offset(w * 0.50f, h * 0.85f))
        drawCircle(color = Color(0xCCFFFFFF), radius = w * 0.10f, center = Offset(w * 0.62f, h * 0.82f))
    }
}

// ==========================================
// 18. SCENIC GLOWING CRYSTAL MUSHROOMS
// ==========================================
@Composable
fun ScenicGlowingCrystalMushrooms(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(54.dp)) {
        val w = size.width
        val h = size.height

        drawCircle(color = Color(0x4400E5FF), radius = w * 0.45f, center = Offset(w * 0.5f, h * 0.45f))

        val c1 = Path().apply {
            moveTo(w * 0.35f, h * 0.78f)
            lineTo(w * 0.25f, h * 0.35f)
            lineTo(w * 0.35f, h * 0.15f)
            lineTo(w * 0.45f, h * 0.35f)
            close()
        }
        drawPath(c1, color = Color(0xFF00E5FF))
        drawPath(c1, color = Color.White, style = Stroke(width = w * 0.02f))

        val c2 = Path().apply {
            moveTo(w * 0.62f, h * 0.78f)
            lineTo(w * 0.52f, h * 0.45f)
            lineTo(w * 0.62f, h * 0.25f)
            lineTo(w * 0.72f, h * 0.45f)
            close()
        }
        drawPath(c2, color = Color(0xFFD500F9))
        drawPath(c2, color = Color.White, style = Stroke(width = w * 0.02f))
    }
}

// ==========================================
// 19. SCENIC DRAGON STONE STATUE
// ==========================================
@Composable
fun ScenicDragonStoneStatue(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(76.dp)) {
        val w = size.width
        val h = size.height

        drawRoundRect(color = Color(0xFF37474F), topLeft = Offset(w * 0.2f, h * 0.70f), size = Size(w * 0.6f, h * 0.20f), cornerRadius = CornerRadius(w * 0.03f))

        val body = Path().apply {
            moveTo(w * 0.35f, h * 0.70f)
            quadraticTo(w * 0.25f, h * 0.45f, w * 0.40f, h * 0.30f)
            lineTo(w * 0.30f, h * 0.25f)
            lineTo(w * 0.50f, h * 0.15f)
            lineTo(w * 0.55f, h * 0.30f)
            quadraticTo(w * 0.75f, h * 0.45f, w * 0.65f, h * 0.70f)
            close()
        }
        drawPath(body, color = Color(0xFF263238))
        drawPath(body, color = Color(0xFF546E7A), style = Stroke(width = w * 0.025f))

        drawCircle(color = Color(0xFFFFD54F), radius = w * 0.05f, center = Offset(w * 0.48f, h * 0.45f))
    }
}
