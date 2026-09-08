package com.chessmaster.play.ui.screens

import android.media.AudioManager
import android.media.ToneGenerator
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.util.Locale
import kotlin.math.roundToInt

enum class ClockPlayer {
    TOP, BOTTOM
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClockScreen(onBack: () -> Unit) {
    BackHandler(onBack = onBack)

    val haptic = LocalHapticFeedback.current
    var toneGenerator by remember { mutableStateOf<ToneGenerator?>(null) }
    DisposableEffect(Unit) {
        try {
            toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 70)
        } catch (_: Exception) { }
        onDispose {
            toneGenerator?.release()
            toneGenerator = null
        }
    }

    var minutesPerSide by remember { mutableIntStateOf(10) }
    var incrementSeconds by remember { mutableIntStateOf(0) }

    var topTimeMs by remember { mutableLongStateOf(10 * 60 * 1000L) }
    var bottomTimeMs by remember { mutableLongStateOf(10 * 60 * 1000L) }
    var topMoves by remember { mutableIntStateOf(0) }
    var bottomMoves by remember { mutableIntStateOf(0) }
    var activePlayer by remember { mutableStateOf<ClockPlayer?>(null) }
    var isRunning by remember { mutableStateOf(false) }
    var soundEnabled by remember { mutableStateOf(true) }
    var showSettingsSheet by remember { mutableStateOf(false) }
    var isFlipped by remember { mutableStateOf(false) }

    fun playClickSound() {
        if (soundEnabled) {
            try {
                toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP, 30)
            } catch (_: Exception) { }
        }
    }

    fun resetClock(minutes: Int = minutesPerSide, increment: Int = incrementSeconds) {
        minutesPerSide = minutes
        incrementSeconds = increment
        val initialMs = minutes * 60 * 1000L
        topTimeMs = initialMs
        bottomTimeMs = initialMs
        topMoves = 0
        bottomMoves = 0
        activePlayer = null
        isRunning = false
    }

    // Timer loop
    LaunchedEffect(isRunning, activePlayer) {
        var lastTime = System.currentTimeMillis()
        while (isRunning && activePlayer != null) {
            delay(50L)
            val now = System.currentTimeMillis()
            val elapsed = now - lastTime
            lastTime = now

            if (activePlayer == ClockPlayer.TOP) {
                val nextTime = topTimeMs - elapsed
                if (nextTime <= 0) {
                    topTimeMs = 0
                    isRunning = false
                    if (soundEnabled) {
                        try {
                            toneGenerator?.startTone(ToneGenerator.TONE_PROP_NACK, 500)
                        } catch (_: Exception) { }
                    }
                } else {
                    topTimeMs = nextTime
                }
            } else if (activePlayer == ClockPlayer.BOTTOM) {
                val nextTime = bottomTimeMs - elapsed
                if (nextTime <= 0) {
                    bottomTimeMs = 0
                    isRunning = false
                    if (soundEnabled) {
                        try {
                            toneGenerator?.startTone(ToneGenerator.TONE_PROP_NACK, 500)
                        } catch (_: Exception) { }
                    }
                } else {
                    bottomTimeMs = nextTime
                }
            }
        }
    }

    val activeColor = Color(0xFF6794A7)
    val inactiveColor = Color(0xFF33373B)
    val flagColor = Color(0xFF8B2525)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1B1E22))
    ) {
        // TOP PLAYER (Opponent, rotated 180 degrees)
        val isTopActive = isRunning && activePlayer == ClockPlayer.TOP
        val topBg = when {
            topTimeMs <= 0 -> flagColor
            isTopActive -> activeColor
            else -> inactiveColor
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(topBg)
                .graphicsLayer { rotationZ = 180f }
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    if (topTimeMs <= 0 || bottomTimeMs <= 0) return@clickable
                    if (activePlayer == ClockPlayer.TOP || activePlayer == null) {
                        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                        playClickSound()
                        if (activePlayer == ClockPlayer.TOP && incrementSeconds > 0) {
                            topTimeMs += incrementSeconds * 1000L
                        }
                        topMoves++
                        activePlayer = ClockPlayer.BOTTOM
                        isRunning = true
                    }
                }
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Top row with tune icon
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    IconButton(
                        onClick = { showSettingsSheet = true },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Tune,
                            contentDescription = "Settings",
                            tint = Color.White.copy(alpha = 0.5f),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                // Main Clock Display
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = formatClockTime(topTimeMs),
                        color = if (isTopActive || topTimeMs <= 0) Color.White else Color.White.copy(alpha = 0.85f),
                        fontSize = 80.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = (-2).sp
                    )
                }

                // Bottom row near center toolbar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Moves: $topMoves",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = formatClockTime(topTimeMs),
                        color = Color.White.copy(alpha = 0.6f),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // MIDDLE CONTROL BAR
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color(0xFF1B1E22))
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Play / Pause
            IconButton(
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    if (isRunning) {
                        isRunning = false
                    } else {
                        if (topTimeMs > 0 && bottomTimeMs > 0) {
                            if (activePlayer == null) {
                                activePlayer = ClockPlayer.BOTTOM
                            }
                            isRunning = true
                        }
                    }
                }
            ) {
                Icon(
                    imageVector = if (isRunning) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = if (isRunning) "Pause" else "Play",
                    tint = Color.White.copy(alpha = 0.85f),
                    modifier = Modifier.size(28.dp)
                )
            }

            // Reset
            IconButton(
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    resetClock()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Reset",
                    tint = Color.White.copy(alpha = 0.85f),
                    modifier = Modifier.size(26.dp)
                )
            }

            // Settings (Time control sliders sheet)
            IconButton(
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    showSettingsSheet = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = Color.White.copy(alpha = 0.85f),
                    modifier = Modifier.size(26.dp)
                )
            }

            // Sound Toggle
            IconButton(
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    soundEnabled = !soundEnabled
                }
            ) {
                Icon(
                    imageVector = if (soundEnabled) Icons.Default.VolumeUp else Icons.Default.VolumeOff,
                    contentDescription = "Sound",
                    tint = if (soundEnabled) Color.White.copy(alpha = 0.85f) else Color.White.copy(alpha = 0.4f),
                    modifier = Modifier.size(26.dp)
                )
            }

            // Swap Sides ("clock upar niche krne bala")
            IconButton(
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    // Swap clock times upar-niche
                    val tempTime = topTimeMs
                    topTimeMs = bottomTimeMs
                    bottomTimeMs = tempTime

                    // Swap move counts
                    val tempMoves = topMoves
                    topMoves = bottomMoves
                    bottomMoves = tempMoves

                    // If clock was running at the bottom, move to top; if running at top, move to bottom!
                    activePlayer = when (activePlayer) {
                        ClockPlayer.BOTTOM -> ClockPlayer.TOP
                        ClockPlayer.TOP -> ClockPlayer.BOTTOM
                        null -> null
                    }

                    // Swap white/black designation
                    isFlipped = !isFlipped
                }
            ) {
                Icon(
                    imageVector = Icons.Default.SwapVert,
                    contentDescription = "Swap",
                    tint = Color.White.copy(alpha = 0.85f),
                    modifier = Modifier.size(28.dp)
                )
            }

            // Home / Exit
            IconButton(
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    onBack()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = Color.White.copy(alpha = 0.85f),
                    modifier = Modifier.size(26.dp)
                )
            }
        }

        // BOTTOM PLAYER (User, normal orientation)
        val isBottomActive = isRunning && activePlayer == ClockPlayer.BOTTOM
        val bottomBg = when {
            bottomTimeMs <= 0 -> flagColor
            isBottomActive -> activeColor
            else -> inactiveColor
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(bottomBg)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    if (topTimeMs <= 0 || bottomTimeMs <= 0) return@clickable
                    if (activePlayer == ClockPlayer.BOTTOM || activePlayer == null) {
                        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                        playClickSound()
                        if (activePlayer == ClockPlayer.BOTTOM && incrementSeconds > 0) {
                            bottomTimeMs += incrementSeconds * 1000L
                        }
                        bottomMoves++
                        activePlayer = ClockPlayer.TOP
                        isRunning = true
                    }
                }
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Top row near center toolbar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = formatClockTime(bottomTimeMs),
                        color = Color.White.copy(alpha = 0.6f),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Moves: $bottomMoves",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                // Main Clock Display
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = formatClockTime(bottomTimeMs),
                        color = if (isBottomActive || bottomTimeMs <= 0) Color.White else Color.White.copy(alpha = 0.85f),
                        fontSize = 80.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = (-2).sp
                    )
                }

                // Bottom row with tune icon
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    IconButton(
                        onClick = { showSettingsSheet = true },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Tune,
                            contentDescription = "Settings",
                            tint = Color.White.copy(alpha = 0.5f),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }

    // Time Control Settings Bottom Sheet (matches Lichess reference screenshot)
    if (showSettingsSheet) {
        var sliderMinutes by remember { mutableFloatStateOf(minutesPerSide.toFloat()) }
        var sliderIncrement by remember { mutableFloatStateOf(incrementSeconds.toFloat()) }

        ModalBottomSheet(
            onDismissRequest = { showSettingsSheet = false },
            containerColor = Color(0xFF222629),
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            dragHandle = null
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            ) {
                // Header row: "Time control" and "Increment"
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Time control",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Increment",
                        color = Color.White.copy(alpha = 0.6f),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Minutes per side slider
                val currentMinutes = sliderMinutes.roundToInt()
                Text(
                    text = "Minutes per side: $currentMinutes",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Slider(
                    value = sliderMinutes,
                    onValueChange = { sliderMinutes = it },
                    valueRange = 1f..60f,
                    colors = SliderDefaults.colors(
                        thumbColor = Color(0xFF80C8F8),
                        activeTrackColor = Color(0xFF80C8F8),
                        inactiveTrackColor = Color(0xFF384046)
                    )
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Increment in seconds slider
                val currentIncrement = sliderIncrement.roundToInt()
                Text(
                    text = "Increment in seconds: $currentIncrement",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Slider(
                    value = sliderIncrement,
                    onValueChange = { sliderIncrement = it },
                    valueRange = 0f..60f,
                    colors = SliderDefaults.colors(
                        thumbColor = Color(0xFF80C8F8),
                        activeTrackColor = Color(0xFF80C8F8),
                        inactiveTrackColor = Color(0xFF384046)
                    )
                )

                Spacer(modifier = Modifier.height(28.dp))

                // OK Button
                Button(
                    onClick = {
                        val finalMins = sliderMinutes.roundToInt().coerceAtLeast(1)
                        val finalInc = sliderIncrement.roundToInt().coerceAtLeast(0)
                        resetClock(finalMins, finalInc)
                        showSettingsSheet = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF80C8F8),
                        contentColor = Color(0xFF1B232A)
                    )
                ) {
                    Text(
                        text = "OK",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

private fun formatClockTime(ms: Long): String {
    if (ms <= 0) return "0:00"
    val totalSeconds = (ms + 999) / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return if (minutes >= 60) {
        val hours = minutes / 60
        val remMinutes = minutes % 60
        String.format(Locale.getDefault(), "%d:%02d:%02d", hours, remMinutes, seconds)
    } else {
        String.format(Locale.getDefault(), "%d:%02d", minutes, seconds)
    }
}
