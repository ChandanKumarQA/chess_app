package com.chessmaster.play.ui.components.adventure

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * 3D Isometric Hedge Stepping Stone Node for the Adventure Map.
 * Matches the visual design from the Chess.com adventure map:
 * - 3D extruded stone with bevel highlights
 * - Completed: Rich green hedge with clean white checkmark
 * - Current: Stepping stone with golden halo & 3D White Pawn Pin on top
 * - Locked: Slate/moss stone with subtle lock
 * - Milestone: Golden border & star accent
 */

enum class LevelNodeState {
    COMPLETED,
    CURRENT,
    LOCKED
}

@Composable
fun AdventureLevelNode(
    level: Int,
    state: LevelNodeState,
    isMilestone: Boolean = (level % 25 == 0 || level == 10 || level == 50 || level == 100),
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = if (state == LevelNodeState.CURRENT) 1.08f else 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseScale"
    )

    val stoneSize = if (isMilestone) 64.dp else 56.dp

    Box(
        modifier = modifier
            .size(width = stoneSize + 12.dp, height = stoneSize + 28.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        // Current Level Pawn sitting on top of stone
        if (state == LevelNodeState.CURRENT) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-2).dp)
                    .scale(pulseScale)
            ) {
                WhitePawnPin(
                    modifier = Modifier.size(36.dp)
                )
            }
        }

        // Stepping Stone Base Canvas (3D Extruded Stone)
        Canvas(
            modifier = Modifier
                .size(width = stoneSize, height = stoneSize * 0.85f)
                .align(Alignment.BottomCenter)
        ) {
            val w = size.width
            val h = size.height
            val depth = h * 0.22f
            val topH = h - depth

            // Ground Drop Shadow
            drawOval(
                color = Color(0x55000000),
                topLeft = Offset(w * 0.08f, h * 0.45f),
                size = Size(w * 0.84f, h * 0.55f)
            )

            // Colors based on state & Biome World (Easy, Moderate, Hard)
            val (baseDark, topGradient, borderColor) = when (state) {
                LevelNodeState.COMPLETED -> when {
                    level <= 35 -> Triple( // EASY: Bright soft green
                        Color(0xFF488424),
                        Brush.verticalGradient(listOf(Color(0xFF86CC4C), Color(0xFF6BBB36))),
                        Color(0xFFA2E06A)
                    )
                    level <= 70 -> Triple( // MODERATE: Richer forest green
                        Color(0xFF2E6316),
                        Brush.verticalGradient(listOf(Color(0xFF58A336), Color(0xFF438A2A))),
                        Color(0xFF81C784)
                    )
                    else -> Triple(       // HARD: Deep emerald slate
                        Color(0xFF1B4510),
                        Brush.verticalGradient(listOf(Color(0xFF2E7D32), Color(0xFF1B5E20))),
                        Color(0xFF66BB6A)
                    )
                }
                LevelNodeState.CURRENT -> Triple(
                    Color(0xFF38701F),
                    Brush.verticalGradient(listOf(Color(0xFF95DB56), Color(0xFF74C23B))),
                    Color(0xFFB4F27C)
                )
                LevelNodeState.LOCKED -> Triple(
                    Color(0xFF2A3D2C),
                    Brush.verticalGradient(listOf(Color(0xFF4B614D), Color(0xFF384B3A))),
                    Color(0xFF5E7860)
                )
            }

            // Milestone Golden Trim
            val finalBorderColor = if (isMilestone && state != LevelNodeState.LOCKED) Color(0xFFFFD700) else borderColor

            // 1. Darker 3D Extrusion (Bottom sides)
            drawRoundRect(
                color = baseDark,
                topLeft = Offset(0f, depth),
                size = Size(w, topH),
                cornerRadius = CornerRadius(w * 0.35f, topH * 0.5f)
            )

            // 2. Top Stepping Surface
            drawRoundRect(
                brush = topGradient,
                topLeft = Offset(0f, 0f),
                size = Size(w, topH),
                cornerRadius = CornerRadius(w * 0.35f, topH * 0.5f)
            )

            // 3. Top Rim Highlight / Border
            drawRoundRect(
                color = finalBorderColor,
                topLeft = Offset(1f, 1f),
                size = Size(w - 2f, topH - 2f),
                cornerRadius = CornerRadius(w * 0.35f, topH * 0.5f),
                style = Stroke(width = if (isMilestone) 2.5f else 1.5f)
            )

            // Inner specular bevel arc
            drawOval(
                color = Color(0x33FFFFFF),
                topLeft = Offset(w * 0.15f, topH * 0.08f),
                size = Size(w * 0.7f, topH * 0.35f)
            )
        }

        // Center Content Overlay (Checkmark, Level Number, Lock, or Star)
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = (stoneSize * 0.85f * 0.18f))
                .size(stoneSize, stoneSize * 0.65f),
            contentAlignment = Alignment.Center
        ) {
            when (state) {
                LevelNodeState.COMPLETED -> {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Completed",
                        tint = Color.White,
                        modifier = Modifier.size(if (isMilestone) 28.dp else 24.dp)
                    )
                }
                LevelNodeState.CURRENT -> {
                    // Level number in a neat pill/badge
                    Text(
                        text = "$level",
                        color = Color(0xFF2E1B00),
                        fontSize = if (isMilestone) 16.sp else 14.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                LevelNodeState.LOCKED -> {
                    if (isMilestone) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Locked Milestone",
                            tint = Color.White.copy(alpha = 0.45f),
                            modifier = Modifier.size(16.dp)
                        )
                    } else {
                        Text(
                            text = "$level",
                            color = Color.White.copy(alpha = 0.45f),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

/**
 * 3D White Chess Pawn Pin sitting on top of the active level stepping stone.
 * Beautifully shaded vector pawn with base, collar, and spherical head.
 */
@Composable
fun WhitePawnPin(modifier: Modifier = Modifier.size(36.dp)) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Ground shadow beneath pawn
        drawOval(
            color = Color(0x44000000),
            topLeft = Offset(w * 0.22f, h * 0.78f),
            size = Size(w * 0.56f, h * 0.2f)
        )

        // Pedestal Base
        val basePath = Path().apply {
            moveTo(w * 0.22f, h * 0.86f)
            quadraticTo(w * 0.5f, h * 0.90f, w * 0.78f, h * 0.86f)
            lineTo(w * 0.72f, h * 0.76f)
            quadraticTo(w * 0.5f, h * 0.78f, w * 0.28f, h * 0.76f)
            close()
        }
        drawPath(
            basePath,
            brush = Brush.verticalGradient(listOf(Color(0xFFFFFFFF), Color(0xFFCFD8DC)))
        )
        drawPath(
            basePath,
            color = Color(0xFFB0BEC5),
            style = Stroke(width = 1.2f)
        )

        // Pawn Body (waist curve)
        val bodyPath = Path().apply {
            moveTo(w * 0.32f, h * 0.76f)
            cubicTo(w * 0.36f, h * 0.60f, w * 0.40f, h * 0.48f, w * 0.38f, h * 0.42f)
            lineTo(w * 0.62f, h * 0.42f)
            cubicTo(w * 0.60f, h * 0.48f, w * 0.64f, h * 0.60f, w * 0.68f, h * 0.76f)
            close()
        }
        drawPath(
            bodyPath,
            brush = Brush.horizontalGradient(
                listOf(Color(0xFFF5F5F5), Color(0xFFFFFFFF), Color(0xFFCFD8DC))
            )
        )
        drawPath(
            bodyPath,
            color = Color(0xFFB0BEC5),
            style = Stroke(width = 1.2f)
        )

        // Collar Ring
        drawOval(
            brush = Brush.verticalGradient(listOf(Color(0xFFFFFFFF), Color(0xFFB0BEC5))),
            topLeft = Offset(w * 0.32f, h * 0.38f),
            size = Size(w * 0.36f, h * 0.10f)
        )
        drawOval(
            color = Color(0xFF90A4AE),
            topLeft = Offset(w * 0.32f, h * 0.38f),
            size = Size(w * 0.36f, h * 0.10f),
            style = Stroke(width = 1.0f)
        )

        // Spherical Head
        val headCenter = Offset(w * 0.5f, h * 0.26f)
        val headRadius = w * 0.20f
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color.White, Color(0xFFECEFF1), Color(0xFFB0BEC5)),
                center = Offset(headCenter.x - headRadius * 0.3f, headCenter.y - headRadius * 0.3f),
                radius = headRadius * 1.2f
            ),
            radius = headRadius,
            center = headCenter
        )
        drawCircle(
            color = Color(0xFF90A4AE),
            radius = headRadius,
            center = headCenter,
            style = Stroke(width = 1.2f)
        )

        // Specular Shine spot on head
        drawCircle(
            color = Color(0xCCFFFFFF),
            radius = headRadius * 0.28f,
            center = Offset(headCenter.x - headRadius * 0.35f, headCenter.y - headRadius * 0.35f)
        )
    }
}
