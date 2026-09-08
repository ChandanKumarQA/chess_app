package com.chessmaster.play.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Person
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
import com.chessmaster.play.ui.components.BannerAd

data class PlayerRank(
    val rank: Int,
    val name: String,
    val flag: String,
    val elo: Int,
    val wins: Int,
    val puzzles: Int
)

val dummyRankings = listOf(
    PlayerRank(1, "M_Karlsen", "🇳🇴", 2878, 564, 8758),
    PlayerRank(2, "hikaru_murakami", "🇺🇸", 2877, 625, 4583),
    PlayerRank(3, "KristofDuda", "🇵🇱", 2865, 684, 3201),
    PlayerRank(4, "a_chevalier", "🇫🇷", 2787, 873, 8473),
    PlayerRank(5, "ArrigoScacchi", "🇮🇹", 2764, 1450, 12394),
    PlayerRank(6, "matt_seifert", "🇩🇪", 2734, 456, 14847),
    PlayerRank(7, "Baratashvili", "🇬🇪", 2708, 128, 9048),
    PlayerRank(8, "GijsDeBoer", "🇳🇱", 2689, 685, 24856),
    PlayerRank(9, "Quang_Phan", "🇻🇳", 2664, 934, 2459),
    PlayerRank(10, "khariRajesh", "🇮🇳", 2633, 876, 783)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RankingsScreen(onBack: () -> Unit) {
    val backgroundColor = MaterialTheme.colorScheme.background
    val topBarColor = MaterialTheme.colorScheme.surfaceVariant
    var selectedRegion by remember { mutableStateOf("United States") }
    var selectedCategory by remember { mutableStateOf("Leaders") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Leaderboard, contentDescription = null, tint = MaterialTheme.colorScheme.onBackground)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Rankings", color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight.Bold)
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
        ) {
            // Tabs Row 1
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterTab(
                    text = "United States", 
                    icon = "🇺🇸", 
                    isSelected = selectedRegion == "United States",
                    modifier = Modifier.weight(1f)
                ) { selectedRegion = "United States" }
                FilterTab(
                    text = "World", 
                    vectorIcon = Icons.Default.Language, 
                    isSelected = selectedRegion == "World",
                    modifier = Modifier.weight(1f)
                ) { selectedRegion = "World" }
            }
            
            // Tabs Row 2
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterTab(
                    text = "Your ranking", 
                    vectorIcon = Icons.Default.Person, 
                    isSelected = selectedCategory == "Your ranking",
                    modifier = Modifier.weight(1f)
                ) { selectedCategory = "Your ranking" }
                FilterTab(
                    text = "Leaders", 
                    vectorIcon = Icons.Default.EmojiEvents, 
                    isSelected = selectedCategory == "Leaders",
                    modifier = Modifier.weight(1f)
                ) { selectedCategory = "Leaders" }
            }

            // Info Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Last update", color = Color.Gray, fontSize = 12.sp)
                    Text("Sep 28, 2025 10:03 PM", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), fontSize = 12.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Info, contentDescription = null, tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Ranking rules", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), fontSize = 12.sp)
                }
            }

            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Spacer(modifier = Modifier.width(32.dp))
                Text("Player", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), fontSize = 12.sp, modifier = Modifier.weight(1f))
                Text("ELO", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), fontSize = 12.sp, modifier = Modifier.width(48.dp))
                Text("WINS", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), fontSize = 12.sp, modifier = Modifier.width(48.dp))
                Text("PUZZ", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), fontSize = 12.sp, modifier = Modifier.width(48.dp))
            }

            // List
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(dummyRankings) { player ->
                    RankingRow(player)
                }
            }
            BannerAd(modifier = Modifier.padding(top = 8.dp))
        }
    }
}

@Composable
fun FilterTab(
    text: String, 
    icon: String? = null,
    vectorIcon: ImageVector? = null,
    isSelected: Boolean, 
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val bgColor = if (isSelected) MaterialTheme.colorScheme.surfaceVariant else Color(0xFF000000)
    val borderColor = if (isSelected) Color(0xFF67A9E1) else MaterialTheme.colorScheme.surfaceVariant
    
    Surface(
        onClick = onClick,
        color = bgColor,
        shape = RoundedCornerShape(24.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, borderColor),
        modifier = modifier.height(40.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                Text(icon, fontSize = 16.sp)
                Spacer(modifier = Modifier.width(4.dp))
            } else if (vectorIcon != null) {
                Icon(vectorIcon, contentDescription = null, tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
            }
            Text(text, color = MaterialTheme.colorScheme.onBackground, fontSize = 14.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal)
        }
    }
}

@Composable
fun RankingRow(player: PlayerRank) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val rankColor = when (player.rank) {
            1 -> Color(0xFFFFD700)
            2 -> Color(0xFFC0C0C0)
            3 -> Color(0xFFCD7F32)
            else -> Color.Gray
        }
        
        Icon(Icons.Default.Star, contentDescription = null, tint = rankColor, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text("${player.rank}.", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), modifier = Modifier.width(24.dp))
        
        Text(player.name, color = MaterialTheme.colorScheme.onBackground, modifier = Modifier.weight(1f))
        
        Text(player.flag, fontSize = 16.sp, modifier = Modifier.width(24.dp))
        
        Text("${player.elo}", color = MaterialTheme.colorScheme.onBackground, fontSize = 14.sp, modifier = Modifier.width(48.dp))
        Text("${player.wins}", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), fontSize = 14.sp, modifier = Modifier.width(48.dp))
        Text("${player.puzzles}", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), fontSize = 14.sp, modifier = Modifier.width(48.dp))
    }
}
