package com.chessmaster.play.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Friend(
    val name: String,
    val elo: Int,
    val rank: Int,
    val rankWorld: Int,
    val puzzles: Int,
    val flag: String,
    val isOnline: Boolean,
    val lastSeen: String? = null
)

val dummyFriends = listOf(
    Friend("elizabeth", 1322, 149, 7482, 1892, "🇺🇸", true),
    Friend("catalina", 876, 912, 45612, 148, "🇪🇸", false, "1 hour ago"),
    Friend("demetrius", 1650, 8, 865, 4539, "🇬🇷", false, "4 hours ago"),
    Friend("sade", 950, 356, 23174, 1410, "🇬🇧", false, "Yesterday"),
    Friend("bruno44", 1250, 85, 8264, 5420, "🇵🇱", false, "2 days ago")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendsScreen(onBack: () -> Unit) {
    val backgroundColor = MaterialTheme.colorScheme.background
    val topBarColor = MaterialTheme.colorScheme.surfaceVariant
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Group, contentDescription = null, tint = MaterialTheme.colorScheme.onBackground)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Play with Friends", color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight.Bold)
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
        containerColor = backgroundColor,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { /* Send Invite */ },
                containerColor = Color(0xFF81B8E8),
                contentColor = Color(0xFF121212),
                shape = RoundedCornerShape(24.dp)
            ) {
                Icon(Icons.Default.PersonAdd, contentDescription = "Send Invite")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Send Invite", fontWeight = FontWeight.Bold)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Search Bar
            Text(
                "Find Friends",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Enter username", color = Color.Gray) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF000000),
                        unfocusedContainerColor = Color(0xFF000000),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = MaterialTheme.colorScheme.onBackground,
                        unfocusedTextColor = MaterialTheme.colorScheme.onBackground
                    ),
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { /* Search */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF67A9E1)),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text("Search", color = Color(0xFF121212), fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Friends List Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Your Friends (5)",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )
                Icon(Icons.Default.Block, contentDescription = "Blocked", tint = Color.Gray)
            }
            
            Divider(color = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier.padding(vertical = 12.dp))

            // Friends List
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(dummyFriends) { friend ->
                    FriendCard(friend)
                }
            }
        }
    }
}

@Composable
fun FriendCard(friend: Friend) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar Placeholder
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), CircleShape)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = null, tint = MaterialTheme.colorScheme.onBackground, modifier = Modifier.size(32.dp))
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Info
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(friend.name, color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("(${friend.elo})", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), fontSize = 14.sp)
                }
                
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
                    Text(friend.flag, fontSize = 12.sp)
                    Text("#${friend.rank}", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), fontSize = 12.sp, modifier = Modifier.padding(start = 4.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.Language, contentDescription = null, tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), modifier = Modifier.size(12.dp))
                    Text("#${friend.rankWorld}", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), fontSize = 12.sp, modifier = Modifier.padding(start = 4.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.Extension, contentDescription = null, tint = Color(0xFF67A9E1), modifier = Modifier.size(12.dp))
                    Text("${friend.puzzles}", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), fontSize = 12.sp, modifier = Modifier.padding(start = 4.dp))
                }
                
                if (friend.isOnline) {
                    Text("Active", color = Color(0xFF34C759), fontSize = 12.sp)
                } else {
                    Text("Last seen: ${friend.lastSeen}", color = Color.Gray, fontSize = 12.sp)
                }
            }
            
            // Actions
            Row {
                IconButton(onClick = { /* Play */ }) {
                    Icon(Icons.Default.GridOn, contentDescription = "Play", tint = MaterialTheme.colorScheme.onBackground)
                }
                IconButton(onClick = { /* Options */ }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "Options", tint = MaterialTheme.colorScheme.onBackground)
                }
            }
        }
    }
}
