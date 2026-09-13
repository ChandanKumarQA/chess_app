package com.chessmaster.play.ui.components.bot

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.chessmaster.play.model.*

@Composable
fun BotAvatar(
    style: BotAvatarStyle,
    modifier: Modifier = Modifier,
    size: Dp = 64.dp
) {
    Box(modifier = modifier.size(size)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = this.size.width
            val h = this.size.height
            val skinColor = Color(style.skinColor)
            val hairColor = Color(style.hairColor)
            val shirtColor = Color(style.shirtColor)
            val glassesColor = Color(style.glassesColor)

            // 1. Shoulders / Shirt
            val shoulderPath = Path().apply {
                moveTo(w * 0.1f, h)
                cubicTo(
                    w * 0.15f, h * 0.72f,
                    w * 0.85f, h * 0.72f,
                    w * 0.9f, h
                )
                close()
            }
            drawPath(shoulderPath, color = shirtColor)

            // Collar / Shirt Secondary Accent
            if (style.shirtSecondaryColor != null) {
                drawCircle(
                    color = Color(style.shirtSecondaryColor),
                    radius = w * 0.15f,
                    center = Offset(w * 0.5f, h * 0.82f),
                    style = Stroke(width = w * 0.04f)
                )
            }

            // 2. Neck
            drawRoundRect(
                color = skinColor.copy(alpha = 0.9f),
                topLeft = Offset(w * 0.41f, h * 0.58f),
                size = Size(w * 0.18f, h * 0.22f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.05f, w * 0.05f)
            )

            // 3. Ears
            drawCircle(
                color = skinColor,
                radius = w * 0.065f,
                center = Offset(w * 0.26f, h * 0.44f)
            )
            drawCircle(
                color = skinColor,
                radius = w * 0.065f,
                center = Offset(w * 0.74f, h * 0.44f)
            )

            // 4. Head / Face
            drawRoundRect(
                color = skinColor,
                topLeft = Offset(w * 0.27f, h * 0.22f),
                size = Size(w * 0.46f, h * 0.48f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.22f, w * 0.24f)
            )

            // 5. Hair Back / Bun / Ponytail
            when (style.hairStyle) {
                HairType.BUN -> {
                    drawCircle(
                        color = hairColor,
                        radius = w * 0.14f,
                        center = Offset(w * 0.5f, h * 0.16f)
                    )
                }
                HairType.PONYTAIL -> {
                    drawCircle(
                        color = hairColor,
                        radius = w * 0.14f,
                        center = Offset(w * 0.22f, h * 0.35f)
                    )
                }
                HairType.AFRO -> {
                    drawCircle(
                        color = hairColor,
                        radius = w * 0.28f,
                        center = Offset(w * 0.5f, h * 0.32f)
                    )
                }
                else -> Unit
            }

            // 6. Facial Hair (Beard / Goatee)
            if (style.facialHair == FacialHairType.FULL_BEARD) {
                val beardPath = Path().apply {
                    moveTo(w * 0.28f, h * 0.46f)
                    cubicTo(w * 0.28f, h * 0.76f, w * 0.72f, h * 0.76f, w * 0.72f, h * 0.46f)
                    cubicTo(w * 0.65f, h * 0.55f, w * 0.35f, h * 0.55f, w * 0.28f, h * 0.46f)
                    close()
                }
                drawPath(beardPath, color = Color(style.facialHairColor))
            } else if (style.facialHair == FacialHairType.GOATEE) {
                drawCircle(
                    color = Color(style.facialHairColor),
                    radius = w * 0.08f,
                    center = Offset(w * 0.5f, h * 0.62f)
                )
            }

            // 7. Eyes
            val eyeRadius = w * 0.035f
            drawCircle(
                color = Color(0xFF212121),
                radius = eyeRadius,
                center = Offset(w * 0.40f, h * 0.42f)
            )
            drawCircle(
                color = Color(0xFF212121),
                radius = eyeRadius,
                center = Offset(w * 0.60f, h * 0.42f)
            )
            // Eye gleam
            drawCircle(
                color = Color.White,
                radius = eyeRadius * 0.35f,
                center = Offset(w * 0.39f, h * 0.41f)
            )
            drawCircle(
                color = Color.White,
                radius = eyeRadius * 0.35f,
                center = Offset(w * 0.59f, h * 0.41f)
            )

            // 8. Eyebrows
            drawLine(
                color = hairColor,
                start = Offset(w * 0.35f, h * 0.35f),
                end = Offset(w * 0.44f, h * 0.36f),
                strokeWidth = w * 0.025f,
                cap = StrokeCap.Round
            )
            drawLine(
                color = hairColor,
                start = Offset(w * 0.65f, h * 0.35f),
                end = Offset(w * 0.56f, h * 0.36f),
                strokeWidth = w * 0.025f,
                cap = StrokeCap.Round
            )

            // 9. Glasses (e.g. Cliff in screenshot!)
            if (style.hasGlasses) {
                // Left lens
                drawCircle(
                    color = glassesColor,
                    radius = w * 0.085f,
                    center = Offset(w * 0.40f, h * 0.42f),
                    style = Stroke(width = w * 0.03f)
                )
                // Right lens
                drawCircle(
                    color = glassesColor,
                    radius = w * 0.085f,
                    center = Offset(w * 0.60f, h * 0.42f),
                    style = Stroke(width = w * 0.03f)
                )
                // Bridge
                drawLine(
                    color = glassesColor,
                    start = Offset(w * 0.48f, h * 0.42f),
                    end = Offset(w * 0.52f, h * 0.42f),
                    strokeWidth = w * 0.03f
                )
            }

            // 10. Mustache
            if (style.facialHair == FacialHairType.MUSTACHE) {
                drawRoundRect(
                    color = Color(style.facialHairColor),
                    topLeft = Offset(w * 0.40f, h * 0.50f),
                    size = Size(w * 0.20f, h * 0.06f),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.03f, w * 0.03f)
                )
            }

            // 11. Smile / Mouth
            val smilePath = Path().apply {
                moveTo(w * 0.43f, h * 0.55f)
                quadraticBezierTo(w * 0.50f, h * 0.61f, w * 0.57f, h * 0.55f)
            }
            drawPath(
                smilePath,
                color = Color(0xFF5D4037),
                style = Stroke(width = w * 0.03f, cap = StrokeCap.Round)
            )

            // 12. Main Hair / Hat / Beanie
            when (style.hairStyle) {
                HairType.SHORT -> {
                    val hairPath = Path().apply {
                        moveTo(w * 0.26f, h * 0.32f)
                        cubicTo(w * 0.25f, h * 0.12f, w * 0.75f, h * 0.12f, w * 0.74f, h * 0.32f)
                        cubicTo(w * 0.68f, h * 0.24f, w * 0.32f, h * 0.24f, w * 0.26f, h * 0.32f)
                        close()
                    }
                    drawPath(hairPath, color = hairColor)
                }
                HairType.MESSY, HairType.CURLY -> {
                    val hairPath = Path().apply {
                        moveTo(w * 0.24f, h * 0.36f)
                        cubicTo(w * 0.20f, h * 0.10f, w * 0.80f, h * 0.10f, w * 0.76f, h * 0.36f)
                        cubicTo(w * 0.65f, h * 0.20f, w * 0.35f, h * 0.20f, w * 0.24f, h * 0.36f)
                        close()
                    }
                    drawPath(hairPath, color = hairColor)
                }
                HairType.BEANIE -> {
                    drawRoundRect(
                        color = Color(0xFF455A64),
                        topLeft = Offset(w * 0.22f, h * 0.12f),
                        size = Size(w * 0.56f, h * 0.22f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.15f, w * 0.15f)
                    )
                }
                HairType.CAP -> {
                    drawRoundRect(
                        color = Color(0xFFD32F2F),
                        topLeft = Offset(w * 0.24f, h * 0.14f),
                        size = Size(w * 0.52f, h * 0.18f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.12f, w * 0.12f)
                    )
                    // Visor
                    drawLine(
                        color = Color(0xFFB71C1C),
                        start = Offset(w * 0.22f, h * 0.28f),
                        end = Offset(w * 0.78f, h * 0.28f),
                        strokeWidth = w * 0.05f,
                        cap = StrokeCap.Round
                    )
                }
                HairType.BALD -> Unit
                else -> {
                    val hairPath = Path().apply {
                        moveTo(w * 0.26f, h * 0.30f)
                        cubicTo(w * 0.25f, h * 0.14f, w * 0.75f, h * 0.14f, w * 0.74f, h * 0.30f)
                        cubicTo(w * 0.65f, h * 0.22f, w * 0.35f, h * 0.22f, w * 0.26f, h * 0.30f)
                        close()
                    }
                    drawPath(hairPath, color = hairColor)
                }
            }

            // 13. Accessory overlay (Musical Instrument / Triangle / Jersey)
            when (style.accessory) {
                AccessoryType.TRIANGLE -> {
                    val trianglePath = Path().apply {
                        moveTo(w * 0.76f, h * 0.72f)
                        lineTo(w * 0.88f, h * 0.90f)
                        lineTo(w * 0.64f, h * 0.90f)
                        close()
                    }
                    drawPath(
                        trianglePath,
                        color = Color(0xFFFFD54F),
                        style = Stroke(width = w * 0.035f)
                    )
                }
                AccessoryType.BATON -> {
                    drawLine(
                        color = Color(0xFFFFE082),
                        start = Offset(w * 0.22f, h * 0.68f),
                        end = Offset(w * 0.14f, h * 0.92f),
                        strokeWidth = w * 0.03f,
                        cap = StrokeCap.Round
                    )
                }
                AccessoryType.SAXOPHONE, AccessoryType.VIOLIN, AccessoryType.DRUM -> {
                    drawCircle(
                        color = Color(0xFFFFD54F),
                        radius = w * 0.08f,
                        center = Offset(w * 0.80f, h * 0.82f)
                    )
                }
                else -> Unit
            }
        }
    }
}
