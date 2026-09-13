package com.chessmaster.play.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import com.chessmaster.play.model.TimeControl
import com.chessmaster.play.model.PieceColor
import com.chessmaster.play.ui.components.BannerAd
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.chessmaster.play.R

import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun StartScreen(
    onPlayOffline: (TimeControl, PieceColor?) -> Unit,
    onPlayOnline: () -> Unit,
    onPlayComputer: (TimeControl, PieceColor?) -> Unit,
    onPlayFriends: () -> Unit,
    onPlayWithBot: () -> Unit = {},
    onPuzzles: () -> Unit = {},
    onRankings: () -> Unit = {},
    onFriends: () -> Unit = {},
    onSettings: () -> Unit = {}
) {
    val context = LocalContext.current
    val backgroundColor = MaterialTheme.colorScheme.background
    val buttonColor = MaterialTheme.colorScheme.surfaceVariant
    var showOfflineTimeDialog by remember { mutableStateOf(false) }
    var showComputerOptions by remember { mutableStateOf(false) }
    var selectedTime by remember { mutableStateOf(TimeControl.MIN_10) }
    var selectedComputerTime by remember { mutableStateOf(TimeControl.MIN_10) }
    var selectedOfflineColor by remember { mutableStateOf<PieceColor?>(null) }
    var selectedComputerColor by remember { mutableStateOf<PieceColor?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo / Knight icon
            Box(
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .background(buttonColor),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = "Logo",
                    modifier = Modifier.size(130.dp).clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Title
            Text(
                text = "CHESS MASTER",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 32.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 2.sp
            )
            Text(
                text = "The Ultimate Chess Experience",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Buttons Grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                MenuButton(
                    text = "Play Against\nBot",
                    icon = Icons.Default.SmartToy,
                    onClick = onPlayWithBot,
                    modifier = Modifier.weight(1f),
                    buttonColor = buttonColor
                )
                MenuButton(
                    text = "Pass & Play\n(Offline)",
                    icon = Icons.Default.People,
                    onClick = { showOfflineTimeDialog = true },
                    modifier = Modifier.weight(1f),
                    buttonColor = buttonColor
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                MenuButton(
                    text = "Play With\nFriends",
                    icon = Icons.Default.Public,
                    onClick = onPlayFriends,
                    modifier = Modifier.weight(1f),
                    buttonColor = buttonColor
                )
                MenuButton(
                    text = "Chess\nPuzzles",
                    icon = Icons.Default.Extension,
                    onClick = onPuzzles,
                    modifier = Modifier.weight(1f),
                    buttonColor = buttonColor
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            BannerAd()
        }

        if (showOfflineTimeDialog) {
            AlertDialog(
                onDismissRequest = { showOfflineTimeDialog = false },
                title = { Text("Offline Game") },
                text = {
                    Column {
                        Text("Select Time", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
                        val options = listOf(TimeControl.MIN_3, TimeControl.MIN_5, TimeControl.MIN_10, TimeControl.MIN_15, TimeControl.UNLIMITED)
                        options.forEach { tc ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { selectedTime = tc }
                                    .padding(vertical = 4.dp)
                            ) {
                                RadioButton(
                                    selected = (tc == selectedTime),
                                    onClick = { selectedTime = tc }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(if (tc == TimeControl.UNLIMITED) "Unlimited" else "${tc.timeMinutes} min")
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        ColorSelectionRow(selectedColor = selectedOfflineColor, onColorSelected = { selectedOfflineColor = it })
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        showOfflineTimeDialog = false
                        onPlayOffline(selectedTime, selectedOfflineColor)
                    }) {
                        Text("Play")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showOfflineTimeDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }

        if (showComputerOptions) {
            AlertDialog(
                onDismissRequest = { showComputerOptions = false },
                title = { Text("Play with Computer") },
                text = {
                    Column {
                        Text("Select Time", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
                        val options = listOf(TimeControl.MIN_3, TimeControl.MIN_5, TimeControl.MIN_10, TimeControl.MIN_15, TimeControl.UNLIMITED)
                        options.forEach { tc ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { selectedComputerTime = tc }
                                    .padding(vertical = 4.dp)
                            ) {
                                RadioButton(
                                    selected = (tc == selectedComputerTime),
                                    onClick = { selectedComputerTime = tc }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(if (tc == TimeControl.UNLIMITED) "Unlimited" else "${tc.timeMinutes} min")
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        ColorSelectionRow(selectedColor = selectedComputerColor, onColorSelected = { selectedComputerColor = it })
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        showComputerOptions = false
                        onPlayComputer(selectedComputerTime, selectedComputerColor)
                    }) { Text("Play") }
                },
                dismissButton = {
                    TextButton(onClick = { showComputerOptions = false }) { Text("Cancel") }
                }
            )
        }
    }
}


@Composable
fun MenuButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonColor: Color,
    textColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    iconColor: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    val view = androidx.compose.ui.platform.LocalView.current
    val haptic = androidx.compose.ui.platform.LocalHapticFeedback.current

    Button(
        onClick = {
            haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.TextHandleMove)
            view.playSoundEffect(android.view.SoundEffectConstants.CLICK)
            onClick()
        },
        modifier = modifier.height(72.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
        contentPadding = PaddingValues(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                color = textColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 16.sp,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Composable
fun ColorSelectionRow(
    selectedColor: PieceColor?,
    onColorSelected: (PieceColor?) -> Unit
) {
    Column {
        Text("Play as", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ColorButton(
                text = "White",
                icon = "♔",
                isSelected = selectedColor == PieceColor.WHITE,
                onClick = { onColorSelected(PieceColor.WHITE) }
            )
            ColorButton(
                text = "Random",
                icon = "🎲",
                isSelected = selectedColor == null,
                onClick = { onColorSelected(null) }
            )
            ColorButton(
                text = "Black",
                icon = "♚",
                isSelected = selectedColor == PieceColor.BLACK,
                onClick = { onColorSelected(PieceColor.BLACK) }
            )
        }
    }
}

@Composable
fun ColorButton(text: String, icon: String, isSelected: Boolean, onClick: () -> Unit) {
    val bgColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
    val outlineColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = bgColor,
        border = androidx.compose.foundation.BorderStroke(2.dp, outlineColor),
        modifier = Modifier.clickable(onClick = onClick).padding(4.dp).size(72.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(icon, fontSize = 28.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}
