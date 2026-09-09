package com.chessmaster.play.ui.components.coach

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Illustrated female chess coach avatar matching the reference screenshot.
 * Drawn via Compose Canvas for sharp rendering on all screen resolutions.
 */
@Composable
fun CoachAvatar(modifier: Modifier = Modifier.size(56.dp)) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Ground/Bust Soft Shadow
        drawOval(
            color = Color(0x33000000),
            topLeft = Offset(w * 0.1f, h * 0.85f),
            size = Size(w * 0.8f, h * 0.15f)
        )

        // 1. Back Hair Volume (Dark Rich Brunette)
        drawCircle(
            color = Color(0xFF3E2015),
            radius = w * 0.40f,
            center = Offset(w * 0.5f, h * 0.42f)
        )
        drawCircle(
            color = Color(0xFF4E2A1E),
            radius = w * 0.28f,
            center = Offset(w * 0.22f, h * 0.50f)
        )
        drawCircle(
            color = Color(0xFF4E2A1E),
            radius = w * 0.28f,
            center = Offset(w * 0.78f, h * 0.50f)
        )

        // 2. Torso & White Blouse / V-Neck
        val torsoPath = Path().apply {
            moveTo(w * 0.15f, h)
            quadraticTo(w * 0.25f, h * 0.70f, w * 0.35f, h * 0.68f)
            lineTo(w * 0.65f, h * 0.68f)
            quadraticTo(w * 0.75f, h * 0.70f, w * 0.85f, h)
            close()
        }
        drawPath(torsoPath, color = Color(0xFFF8F9FA))
        // Collar & V-neck cutout
        val vNeckPath = Path().apply {
            moveTo(w * 0.40f, h * 0.68f)
            lineTo(w * 0.50f, h * 0.82f)
            lineTo(w * 0.60f, h * 0.68f)
            close()
        }
        drawPath(vNeckPath, color = Color(0xFFE0A97E)) // Neck shadow

        // 3. Neck
        drawRoundRect(
            color = Color(0xFFEBB08A),
            topLeft = Offset(w * 0.41f, h * 0.52f),
            size = Size(w * 0.18f, h * 0.20f),
            cornerRadius = CornerRadius(w * 0.05f)
        )

        // 4. Gold Necklace
        val necklacePath = Path().apply {
            moveTo(w * 0.42f, h * 0.69f)
            quadraticTo(w * 0.50f, h * 0.77f, w * 0.58f, h * 0.69f)
        }
        drawPath(necklacePath, color = Color(0xFFFFD700), style = Stroke(width = w * 0.03f))
        drawCircle(color = Color(0xFFFFC107), radius = w * 0.035f, center = Offset(w * 0.50f, h * 0.76f))

        // 5. Face Oval
        val faceTop = h * 0.22f
        val faceH = h * 0.44f
        val faceW = w * 0.48f
        val faceLeft = w * 0.26f
        drawOval(
            brush = Brush.verticalGradient(listOf(Color(0xFFF3C09F), Color(0xFFE7AD87))),
            topLeft = Offset(faceLeft, faceTop),
            size = Size(faceW, faceH)
        )

        // Cheeks Blush
        drawCircle(color = Color(0x33E57373), radius = w * 0.07f, center = Offset(w * 0.35f, h * 0.46f))
        drawCircle(color = Color(0x33E57373), radius = w * 0.07f, center = Offset(w * 0.65f, h * 0.46f))

        // 6. Eyes & Eyebrows
        // Eyebrows
        drawLine(
            color = Color(0xFF3E2015),
            start = Offset(w * 0.33f, h * 0.34f),
            end = Offset(w * 0.44f, h * 0.33f),
            strokeWidth = w * 0.03f
        )
        drawLine(
            color = Color(0xFF3E2015),
            start = Offset(w * 0.56f, h * 0.33f),
            end = Offset(w * 0.67f, h * 0.34f),
            strokeWidth = w * 0.03f
        )
        // Eyes
        val leftEyeCenter = Offset(w * 0.38f, h * 0.38f)
        val rightEyeCenter = Offset(w * 0.62f, h * 0.38f)
        drawOval(color = Color.White, topLeft = Offset(leftEyeCenter.x - w * 0.06f, leftEyeCenter.y - h * 0.035f), size = Size(w * 0.12f, h * 0.07f))
        drawOval(color = Color.White, topLeft = Offset(rightEyeCenter.x - w * 0.06f, rightEyeCenter.y - h * 0.035f), size = Size(w * 0.12f, h * 0.07f))
        // Iris (warm hazel-brown)
        drawCircle(color = Color(0xFF4E2A1E), radius = w * 0.035f, center = leftEyeCenter)
        drawCircle(color = Color(0xFF4E2A1E), radius = w * 0.035f, center = rightEyeCenter)
        // Pupil
        drawCircle(color = Color.Black, radius = w * 0.02f, center = leftEyeCenter)
        drawCircle(color = Color.Black, radius = w * 0.02f, center = rightEyeCenter)
        // Shine
        drawCircle(color = Color.White, radius = w * 0.012f, center = Offset(leftEyeCenter.x - 1f, leftEyeCenter.y - 1f))
        drawCircle(color = Color.White, radius = w * 0.012f, center = Offset(rightEyeCenter.x - 1f, rightEyeCenter.y - 1f))

        // Cute Nose
        drawCircle(color = Color(0xFFD49772), radius = w * 0.022f, center = Offset(w * 0.50f, h * 0.44f))

        // Warm Smiling Mouth
        val mouthPath = Path().apply {
            moveTo(w * 0.42f, h * 0.51f)
            quadraticTo(w * 0.50f, h * 0.58f, w * 0.58f, h * 0.51f)
            close()
        }
        drawPath(mouthPath, color = Color(0xFFC25353))
        // White smile line (teeth)
        drawLine(
            color = Color.White,
            start = Offset(w * 0.45f, h * 0.52f),
            end = Offset(w * 0.55f, h * 0.52f),
            strokeWidth = w * 0.02f
        )

        // 7. Front Hair (Wavy Curls & Bangs)
        val frontHairLeft = Path().apply {
            moveTo(w * 0.50f, h * 0.18f)
            quadraticTo(w * 0.28f, h * 0.16f, w * 0.22f, h * 0.38f)
            quadraticTo(w * 0.16f, h * 0.55f, w * 0.20f, h * 0.68f)
            quadraticTo(w * 0.28f, h * 0.55f, w * 0.30f, h * 0.32f)
            close()
        }
        drawPath(frontHairLeft, color = Color(0xFF5D3322))

        val frontHairRight = Path().apply {
            moveTo(w * 0.50f, h * 0.18f)
            quadraticTo(w * 0.72f, h * 0.16f, w * 0.78f, h * 0.38f)
            quadraticTo(w * 0.84f, h * 0.55f, w * 0.80f, h * 0.68f)
            quadraticTo(w * 0.72f, h * 0.55f, w * 0.70f, h * 0.32f)
            close()
        }
        drawPath(frontHairRight, color = Color(0xFF5D3322))

        // Crown volume
        drawCircle(color = Color(0xFF5D3322), radius = w * 0.24f, center = Offset(w * 0.50f, h * 0.20f))
        // Hair highlight shine
        drawCircle(color = Color(0xFF7A452F), radius = w * 0.12f, center = Offset(w * 0.44f, h * 0.16f))
    }
}

/**
 * Animated speech bubble for Coach hints and wrong-move feedback,
 * matching the design from the reference screenshot.
 */
@Composable
fun CoachSpeechBubble(
    title: String,
    message: String,
    isWrongMove: Boolean = false,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Coach Avatar
        Box(
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
                .background(Color(0xFF261D1A)),
            contentAlignment = Alignment.Center
        ) {
            CoachAvatar(modifier = Modifier.size(52.dp))
        }

        Spacer(modifier = Modifier.width(10.dp))

        // Speech Bubble with Pointer
        Box(modifier = Modifier.weight(1f)) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                shadowElevation = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
                ) {
                    // Header with Icon & Dismiss
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = if (isWrongMove) Icons.Default.Warning else Icons.Default.Lightbulb,
                                contentDescription = null,
                                tint = if (isWrongMove) Color(0xFFE53935) else Color(0xFFFFB300),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = title,
                                color = Color(0xFF212121),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Dismiss",
                            tint = Color.Gray,
                            modifier = Modifier
                                .size(18.dp)
                                .clickable(onClick = onDismiss)
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Message Text
                    Text(
                        text = message,
                        color = Color(0xFF37474F),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }
}
