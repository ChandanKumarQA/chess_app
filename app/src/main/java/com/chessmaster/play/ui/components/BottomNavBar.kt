package com.chessmaster.play.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chessmaster.play.Screen

@Composable
fun ChessBottomBar(
    currentScreen: Screen,
    onNavigate: (Screen) -> Unit
) {
    val cardBg = Color(0xFF1E1E1E)
    val cardBgLight = Color(0xFF2A2A2A)
    val gold = Color(0xFFFFD700)
    val textSecondary = Color(0xFFAAAAAA)
    val view = androidx.compose.ui.platform.LocalView.current
    val haptic = androidx.compose.ui.platform.LocalHapticFeedback.current

    NavigationBar(
        containerColor = cardBg,
        contentColor = textSecondary,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = currentScreen == Screen.START,
            onClick = { 
                haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.TextHandleMove)

                onNavigate(Screen.START) 
            },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = gold,
                selectedTextColor = gold,
                indicatorColor = cardBgLight,
                unselectedIconColor = textSecondary,
                unselectedTextColor = textSecondary
            )
        )

        NavigationBarItem(
            selected = currentScreen == Screen.PUZZLES,
            onClick = { 
                haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.TextHandleMove)

                onNavigate(Screen.PUZZLES) 
            },
            icon = { Icon(Icons.Default.Extension, contentDescription = "Puzzles") },
            label = { Text("Puzzles") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = gold,
                selectedTextColor = gold,
                indicatorColor = cardBgLight,
                unselectedIconColor = textSecondary,
                unselectedTextColor = textSecondary
            )
        )
        NavigationBarItem(
            selected = currentScreen == Screen.LEARN,
            onClick = { 
                haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.TextHandleMove)

                onNavigate(Screen.LEARN) 
            },
            icon = { Icon(Icons.Default.MenuBook, contentDescription = "Learn") },
            label = { Text("Learn") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = gold,
                selectedTextColor = gold,
                indicatorColor = cardBgLight,
                unselectedIconColor = textSecondary,
                unselectedTextColor = textSecondary
            )
        )
        NavigationBarItem(
            selected = currentScreen == Screen.SETTINGS,
            onClick = { 
                haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.TextHandleMove)

                onNavigate(Screen.SETTINGS) 
            },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = gold,
                selectedTextColor = gold,
                indicatorColor = cardBgLight,
                unselectedIconColor = textSecondary,
                unselectedTextColor = textSecondary
            )
        )
    }
}
