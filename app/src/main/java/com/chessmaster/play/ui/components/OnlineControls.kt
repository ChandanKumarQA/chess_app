package com.chessmaster.play.ui.components

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chessmaster.play.viewmodel.ChessViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnlineControls(viewModel: ChessViewModel) {
    val connectionStatus by viewModel.connectionStatus.collectAsState()
    val chatMessages by viewModel.chatMessages.collectAsState()
    val latestEmoji by viewModel.latestEmoji.collectAsState()

    var showChatDialog by remember { mutableStateOf(false) }
    var showEmojiDialog by remember { mutableStateOf(false) }
    
    val context = LocalContext.current
    var remainingHints by remember { mutableIntStateOf(com.chessmaster.play.data.LocalLeaderboardManager.getRemainingHints(context)) }
    var showShopDialog by remember { mutableStateOf(false) }
    val rewardedAdManager = remember { com.chessmaster.play.RewardedAdManager(context) }
    
    LaunchedEffect(Unit) {
        rewardedAdManager.loadAd()
    }
    
    // Status and simple controls
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = connectionStatus,
                color = if (connectionStatus.contains("In Game")) Color(0xFF34C759) else Color.Yellow,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            
            if (latestEmoji != null && System.currentTimeMillis() - latestEmoji!!.timestamp < 5000) {
                Text(text = latestEmoji!!.emoji, fontSize = 24.sp)
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = { showChatDialog = true }) {
                Icon(Icons.Default.Chat, contentDescription = "Chat", tint = Color.White)
            }
            IconButton(onClick = { showEmojiDialog = true }) {
                Icon(Icons.Default.EmojiEmotions, contentDescription = "Emoji", tint = Color.White)
            }
            
            BadgedBox(
                badge = {
                    Badge { Text(remainingHints.toString()) }
                }
            ) {
                IconButton(onClick = { 
                    if (remainingHints > 0) {
                        remainingHints--
                        com.chessmaster.play.data.LocalLeaderboardManager.setRemainingHints(context, remainingHints)
                        viewModel.getHint()
                    } else {
                        showShopDialog = true
                    }
                }) {
                    Icon(Icons.Default.Lightbulb, contentDescription = "Hint", tint = Color(0xFFFFB300))
                }
            }
            
            IconButton(onClick = { viewModel.offerDraw() }) {
                Icon(Icons.Default.Handshake, contentDescription = "Offer Draw", tint = Color.White)
            }
            IconButton(onClick = { viewModel.resign() }) {
                Icon(Icons.Default.Flag, contentDescription = "Resign", tint = Color.Red)
            }
        }
    }

    if (showChatDialog) {
        var chatText by remember { mutableStateOf("") }
        AlertDialog(
            onDismissRequest = { showChatDialog = false },
            title = { Text("Chat") },
            text = {
                Column {
                    // Display last 5 messages
                    chatMessages.takeLast(5).forEach { msg ->
                        Text(text = msg.text, modifier = Modifier.padding(vertical = 4.dp))
                    }
                    OutlinedTextField(
                        value = chatText,
                        onValueChange = { chatText = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    if (chatText.isNotBlank()) {
                        viewModel.sendChatMessage(chatText)
                        chatText = ""
                        showChatDialog = false
                    }
                }) {
                    Text("Send")
                }
            },
            dismissButton = {
                TextButton(onClick = { showChatDialog = false }) {
                    Text("Close")
                }
            }
        )
    }

    if (showEmojiDialog) {
        val emojis = listOf("👍", "😂", "😲", "😡", "👏", "🎉")
        AlertDialog(
            onDismissRequest = { showEmojiDialog = false },
            title = { Text("Send Emoji") },
            text = {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    emojis.forEach { emoji ->
                        TextButton(onClick = {
                            viewModel.sendEmojiReaction(emoji)
                            showEmojiDialog = false
                        }) {
                            Text(emoji, fontSize = 24.sp)
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showEmojiDialog = false }) { Text("Close") }
            }
        )
    }
    
    if (showShopDialog) {
        com.chessmaster.play.ui.screens.ShopDialog(
            adManager = rewardedAdManager,
            onDismiss = { showShopDialog = false },
            onHintsEarned = { amount ->
                remainingHints += amount
                com.chessmaster.play.data.LocalLeaderboardManager.setRemainingHints(context, remainingHints)
                showShopDialog = false
            }
        )
    }
}
