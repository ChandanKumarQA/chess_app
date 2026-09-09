package com.chessmaster.play.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chessmaster.play.model.BoardState
import com.chessmaster.play.model.PieceColor
import com.chessmaster.play.ui.components.ChessBoard

import androidx.activity.compose.BackHandler
import com.chessmaster.play.data.BoardPreferences
import com.chessmaster.play.viewmodel.ChessViewModel

enum class SettingsSubScreen {
    MAIN,
    BOARD_SETTINGS,
    BOARD_THEME_PICKER,
    PIECE_SET_PICKER
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: ChessViewModel,
    onBack: () -> Unit,
    onNavigateToAnalysis: () -> Unit = {},
    onNavigateToClock: () -> Unit = {}
) {
    var subScreen by remember { mutableStateOf(SettingsSubScreen.MAIN) }

    BackHandler(enabled = subScreen != SettingsSubScreen.MAIN) {
        subScreen = when (subScreen) {
            SettingsSubScreen.BOARD_THEME_PICKER, SettingsSubScreen.PIECE_SET_PICKER -> SettingsSubScreen.BOARD_SETTINGS
            SettingsSubScreen.BOARD_SETTINGS -> SettingsSubScreen.MAIN
            SettingsSubScreen.MAIN -> SettingsSubScreen.MAIN
        }
    }

    when (subScreen) {
        SettingsSubScreen.BOARD_SETTINGS -> {
            BoardSettingsScreen(
                onBack = { subScreen = SettingsSubScreen.MAIN },
                onNavigateToBoardPicker = { subScreen = SettingsSubScreen.BOARD_THEME_PICKER },
                onNavigateToPieceSetPicker = { subScreen = SettingsSubScreen.PIECE_SET_PICKER }
            )
        }
        SettingsSubScreen.BOARD_THEME_PICKER -> {
            BoardThemePickerScreen(
                onBack = { subScreen = SettingsSubScreen.BOARD_SETTINGS }
            )
        }
        SettingsSubScreen.PIECE_SET_PICKER -> {
            PieceSetPickerScreen(
                onBack = { subScreen = SettingsSubScreen.BOARD_SETTINGS }
            )
        }
        SettingsSubScreen.MAIN -> {
            val backgroundColor = MaterialTheme.colorScheme.background
            val topBarColor = MaterialTheme.colorScheme.surface
            val textColor = MaterialTheme.colorScheme.onBackground
            val secondaryTextColor = MaterialTheme.colorScheme.onSurfaceVariant
            val iconColor = MaterialTheme.colorScheme.onSurfaceVariant
            
            val themeDark by viewModel.isDarkMode.collectAsState()
            val sounds by viewModel.sounds.collectAsState()
            val vibrations by viewModel.vibrations.collectAsState()
            val currentTheme by BoardPreferences.boardTheme.collectAsState()
            val currentPieceSet by BoardPreferences.pieceSet.collectAsState()

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { 
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Settings, contentDescription = null, tint = textColor)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Settings", color = textColor, fontWeight = FontWeight.Bold)
                            }
                        },
                        navigationIcon = {
                            IconButton(onClick = onBack) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = androidx.compose.ui.graphics.Color.White)
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = topBarColor)
                    )
                },
                containerColor = backgroundColor
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                ) {
                    // Board Settings Section (Screenshots 1 & 3)
                    SectionHeader("Display & Board")

                    SettingsItemRow(
                        icon = Icons.Default.Palette,
                        title = "Board settings",
                        subtitle = "${currentTheme.name} • ${currentPieceSet.displayName}",
                        isToggle = false,
                        textColor = textColor,
                        secondaryTextColor = secondaryTextColor,
                        iconColor = iconColor,
                        onClick = { subScreen = SettingsSubScreen.BOARD_SETTINGS }
                    )

                    HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier.padding(vertical = 8.dp))

                    // Tools Section
                    SectionHeader("Tools")

                    SettingsItemRow(
                        icon = Icons.Default.Analytics,
                        title = "Analysis Board",
                        isToggle = false,
                        textColor = textColor,
                        secondaryTextColor = secondaryTextColor,
                        iconColor = iconColor,
                        onClick = onNavigateToAnalysis
                    )

                    SettingsItemRow(
                        icon = Icons.Default.Schedule,
                        title = "Clock",
                        isToggle = false,
                        textColor = textColor,
                        secondaryTextColor = secondaryTextColor,
                        iconColor = iconColor,
                        onClick = onNavigateToClock
                    )

                    HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier.padding(vertical = 8.dp))

                    // General Section
                    SectionHeader("General")
                    
                    SettingsItemRow(
                        icon = Icons.Default.BrightnessMedium,
                        title = "Theme",
                        subtitle = if (themeDark) "Dark" else "Light",
                        isToggle = false,
                        textColor = textColor,
                        secondaryTextColor = secondaryTextColor,
                        iconColor = iconColor,
                        onClick = { viewModel.setDarkMode(!themeDark) }
                    )
                    
                    SettingsToggleRow(
                        icon = Icons.Default.MusicNote,
                        title = "Sound",
                        checked = sounds,
                        textColor = textColor,
                        iconColor = iconColor,
                        onCheckedChange = { viewModel.setSounds(it) }
                    )
                    
                    SettingsToggleRow(
                        icon = Icons.Default.Vibration,
                        title = "Vibrations",
                        checked = vibrations,
                        textColor = textColor,
                        iconColor = iconColor,
                        onCheckedChange = { viewModel.setVibrations(it) }
                    )

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        color = Color(0xFF67A9E1),
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 8.dp)
    )
}

@Composable
fun SettingsItemRow(icon: ImageVector, title: String, subtitle: String? = null, isToggle: Boolean = false, textColor: Color = MaterialTheme.colorScheme.onBackground, secondaryTextColor: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), iconColor: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), onClick: () -> Unit = {}) {
    val view = androidx.compose.ui.platform.LocalView.current
    val haptic = androidx.compose.ui.platform.LocalHapticFeedback.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.TextHandleMove)
                onClick()
            }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = textColor, fontWeight = FontWeight.Bold)
            if (subtitle != null) {
                Text(subtitle, color = secondaryTextColor, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun SettingsToggleRow(icon: ImageVector, title: String, checked: Boolean, textColor: Color = MaterialTheme.colorScheme.onBackground, iconColor: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), onCheckedChange: (Boolean) -> Unit) {
    val view = androidx.compose.ui.platform.LocalView.current
    val haptic = androidx.compose.ui.platform.LocalHapticFeedback.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { 
                haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.TextHandleMove)
                onCheckedChange(!checked) 
            }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(title, color = textColor, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF67A9E1),
                uncheckedThumbColor = Color.LightGray,
                uncheckedTrackColor = Color(0xFF2C3E50)
            )
        )
    }
}
