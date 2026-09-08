package com.chessmaster.play.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chessmaster.play.data.FirebaseGame
import com.chessmaster.play.data.FirebaseManager
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchHistoryScreen(onGameSelected: (FirebaseGame) -> Unit) {
    val firebaseManager = remember { FirebaseManager() }
    val coroutineScope = rememberCoroutineScope()
    var games by remember { mutableStateOf<List<FirebaseGame>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        val uid = firebaseManager.currentUserId
        if (uid != null) {
            try {
                // Fetch games where user is white
                val whiteGamesSnapshot = firebaseManager.firestore.collection("games")
                    .whereEqualTo("whitePlayerId", uid)
                    .get().await()
                
                // Fetch games where user is black
                val blackGamesSnapshot = firebaseManager.firestore.collection("games")
                    .whereEqualTo("blackPlayerId", uid)
                    .get().await()

                val whiteGames = whiteGamesSnapshot.toObjects(FirebaseGame::class.java)
                val blackGames = blackGamesSnapshot.toObjects(FirebaseGame::class.java)
                
                games = (whiteGames + blackGames)
                    .distinctBy { it.id }
                    .sortedByDescending { it.lastMoveTimestamp }
            } catch (e: Exception) {
                // Handle error
            } finally {
                isLoading = false
            }
        } else {
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Match History") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (games.isEmpty()) {
                Text("No games found.", modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(games) { game ->
                        GameHistoryItem(game, firebaseManager.currentUserId, onClick = { onGameSelected(game) })
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
fun GameHistoryItem(game: FirebaseGame, currentUserId: String?, onClick: () -> Unit) {
    val isWhite = game.whitePlayerId == currentUserId
    val opponent = if (isWhite) game.blackPlayerId else game.whitePlayerId
    val dateFormat = SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault())
    val dateString = if (game.lastMoveTimestamp > 0) dateFormat.format(Date(game.lastMoveTimestamp)) else "Unknown Date"
    val opponentText = opponent ?: "Unknown"
    val roleText = if (isWhite) "White" else "Black"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Text(
            text = "vs $opponentText",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Played as: $roleText",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = dateString,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Status: ${game.status}",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
