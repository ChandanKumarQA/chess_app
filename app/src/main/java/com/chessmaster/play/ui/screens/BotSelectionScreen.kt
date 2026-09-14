package com.chessmaster.play.ui.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chessmaster.play.data.BotDatabase
import com.chessmaster.play.data.BotGameManager
import com.chessmaster.play.model.BotCategory
import com.chessmaster.play.model.BotProfile
import com.chessmaster.play.ui.components.bot.BotAvatar

@Composable
fun BotSelectionScreen(
    onBack: () -> Unit,
    onStartGame: (BotProfile, Boolean) -> Unit // (bot, isNewGame)
) {
    BackHandler { onBack() }

    val context = LocalContext.current
    val gameManager = remember { BotGameManager(context) }
    var selectedBotId by remember { mutableStateOf(gameManager.getSelectedBotId()) }
    var isSoundOn by remember { mutableStateOf(gameManager.isSoundEnabled()) }

    var unlockedBotIds by remember {
        mutableStateOf(BotDatabase.allBots.filter { gameManager.isBotUnlocked(it) }.map { it.id }.toSet())
    }
    var crownsMap by remember {
        mutableStateOf(BotDatabase.allBots.associate { it.id to gameManager.getBotCrowns(it.id) })
    }

    // Refresh reactive states whenever this screen is active or recomposed
    LaunchedEffect(Unit) {
        selectedBotId = gameManager.getSelectedBotId()
        unlockedBotIds = BotDatabase.allBots.filter { gameManager.isBotUnlocked(it) }.map { it.id }.toSet()
        crownsMap = BotDatabase.allBots.associate { it.id to gameManager.getBotCrowns(it.id) }
    }

    val selectedBot = remember(selectedBotId) { BotDatabase.getBotById(selectedBotId) }
    val hasSavedGame = remember { gameManager.hasSavedGame() }

    val darkBgColor = Color(0xFF161E27)
    val cardBgColor = Color(0xFF222B37)
    val greenAccentColor = Color(0xFF81B64C)
    val darkButtonColor = Color(0xFF383F48)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBgColor)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // 1. TOP APP BAR
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White.copy(alpha = 0.85f),
                        modifier = Modifier.size(26.dp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "🤖",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(end = 6.dp)
                    )
                    Text(
                        text = "Bots",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                IconButton(onClick = {
                    val newState = !isSoundOn
                    isSoundOn = newState
                    gameManager.setSoundEnabled(newState)
                }) {
                    Icon(
                        imageVector = if (isSoundOn) Icons.Default.VolumeUp else Icons.Default.VolumeOff,
                        contentDescription = "Toggle Sound",
                        tint = Color.White.copy(alpha = 0.85f),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // 2. SCROLLABLE CONTENT (Top banner + all categories)
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 120.dp) // Leave space for floating bottom buttons
            ) {
                // FEATURED / SELECTED BOT BANNER
                FeaturedBotBanner(
                    bot = selectedBot,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // BAND CLASS SECTION
                BandClassSection(
                    selectedBotId = selectedBotId,
                    unlockedBotIds = unlockedBotIds,
                    crownsMap = crownsMap,
                    onSelectBot = { bot ->
                        if (unlockedBotIds.contains(bot.id) || gameManager.isBotUnlocked(bot)) {
                            selectedBotId = bot.id
                            gameManager.setSelectedBotId(bot.id)
                        } else {
                            val categoryBots = BotDatabase.getBotsByCategory(bot.category)
                            val catIndex = categoryBots.indexOfFirst { it.id == bot.id }
                            val prevBot = if (catIndex > 0) categoryBots[catIndex - 1] else null
                            val msg = if (prevBot != null) {
                                "Defeat ${prevBot.name} (${prevBot.rating}) to unlock ${bot.name}!"
                            } else {
                                "Defeat earlier bots to unlock ${bot.name}!"
                            }
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                // STANDARD BOT CATEGORIES (Beginner, Intermediate, Pro, Master, Grandmaster, etc.)
                val standardCategories = listOf(
                    BotCategory.BEGINNER,
                    BotCategory.INTERMEDIATE,
                    BotCategory.PRO,
                    BotCategory.MASTER,
                    BotCategory.GRANDMASTER,
                    BotCategory.ADAPTIVE,
                    BotCategory.ATHLETES
                )

                standardCategories.forEach { category ->
                    StandardCategorySection(
                        category = category,
                        selectedBotId = selectedBotId,
                        unlockedBotIds = unlockedBotIds,
                        crownsMap = crownsMap,
                        onSelectBot = { bot ->
                            if (unlockedBotIds.contains(bot.id) || gameManager.isBotUnlocked(bot)) {
                                selectedBotId = bot.id
                                gameManager.setSelectedBotId(bot.id)
                            } else {
                                val categoryBots = BotDatabase.getBotsByCategory(bot.category)
                                val catIndex = categoryBots.indexOfFirst { it.id == bot.id }
                                val prevBot = if (catIndex > 0) categoryBots[catIndex - 1] else null
                                val msg = if (prevBot != null) {
                                    "Defeat ${prevBot.name} (${prevBot.rating}) to unlock ${bot.name}!"
                                } else {
                                    "Defeat earlier bots to unlock ${bot.name}!"
                                }
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }

        // 3. FIXED BOTTOM ACTION BAR
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            darkBgColor.copy(alpha = 0.92f),
                            darkBgColor
                        )
                    )
                )
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // "New Game" Button
                Button(
                    onClick = { onStartGame(selectedBot, true) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = darkButtonColor,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "New Game",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // "Continue Game" Button
                Button(
                    onClick = {
                        // If has saved game, resume it; else start game with selected bot
                        onStartGame(selectedBot, !hasSavedGame)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = greenAccentColor,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Continue Game",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun FeaturedBotBanner(
    bot: BotProfile,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Large Avatar on left
            BotAvatar(
                style = bot.avatarStyle,
                modifier = Modifier.size(76.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // White Speech Bubble with Tail
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 16.dp, bottomEnd = 16.dp, bottomStart = 16.dp))
                    .background(Color.White)
                    .padding(horizontal = 14.dp, vertical = 12.dp)
            ) {
                Text(
                    text = bot.catchphrase,
                    color = Color(0xFF1E232A),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Bot Name, Rating, and Country Flag
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = bot.fullDisplayName,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${bot.rating}",
                color = Color.White.copy(alpha = 0.65f),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = bot.flag,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun BandClassSection(
    selectedBotId: String,
    unlockedBotIds: Set<String>,
    crownsMap: Map<String, Int>,
    onSelectBot: (BotProfile) -> Unit
) {
    val bots = remember { BotDatabase.bandBots }

    Column(modifier = Modifier.fillMaxWidth()) {
        // Section Header with ⏱ 18D badge
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Band Class",
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Info",
                    tint = Color.White.copy(alpha = 0.5f),
                    modifier = Modifier.size(16.dp)
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                // Rating Range Pill
                Surface(
                    color = Color(0xFF1B232D),
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(1.dp, Color(0xFF81B64C).copy(alpha = 0.45f))
                ) {
                    Text(
                        text = "⚡ 300 - 1200",
                        color = Color(0xFF81B64C),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                Spacer(modifier = Modifier.width(6.dp))

                // Green Timer Badge
                Surface(
                    color = Color(0xFF81B64C),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "⏱ 18D",
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // 5 Band Bot Cards
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            bots.forEach { bot ->
                val isSelected = bot.id == selectedBotId
                val isUnlocked = unlockedBotIds.contains(bot.id)
                val crowns = crownsMap[bot.id] ?: 0

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(0.88f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF222B37))
                        .then(
                            if (isSelected) Modifier.border(2.5.dp, Color(0xFF81B64C), RoundedCornerShape(8.dp))
                            else Modifier
                        )
                        .clickable { onSelectBot(bot) }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            BotAvatar(
                                style = bot.avatarStyle,
                                size = 48.dp
                            )
                        }

                        // Coin Badge or Lock/Crown indicator
                        Surface(
                            color = Color(0xFF1B232D),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .padding(bottom = 6.dp)
                                .padding(horizontal = 4.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                            ) {
                                if (!isUnlocked) {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = "Locked",
                                        tint = Color.White.copy(alpha = 0.75f),
                                        modifier = Modifier.size(11.dp)
                                    )
                                    Spacer(modifier = Modifier.width(2.dp))
                                } else if (crowns >= 3) {
                                    Text(text = "👑", fontSize = 9.sp)
                                    Spacer(modifier = Modifier.width(2.dp))
                                } else {
                                    Text(text = "🪙", fontSize = 9.sp)
                                    Spacer(modifier = Modifier.width(2.dp))
                                }
                                Text(
                                    text = "${bot.coinCost}",
                                    color = if (isUnlocked) Color(0xFFFFD54F) else Color.White.copy(alpha = 0.6f),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Milestone Reward Track
        MilestoneRewardTrack()
    }
}

@Composable
fun MilestoneRewardTrack() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF1E2632))
            .padding(10.dp)
    ) {
        // Track Line with 4 Milestone Icons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "🥁", fontSize = 16.sp)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(2.dp)
                    .background(Color.White.copy(alpha = 0.2f))
            )
            Text(text = "🏁", fontSize = 16.sp)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(2.dp)
                    .background(Color.White.copy(alpha = 0.2f))
            )
            Text(text = "👑", fontSize = 16.sp)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(2.dp)
                    .background(Color.White.copy(alpha = 0.2f))
            )
            Text(text = "🎺", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Text: Unlock Rewards by Collecting 🪙 ... 0/180
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Unlock Rewards by Collecting 🪙",
                color = Color.White.copy(alpha = 0.75f),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "0/180",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 11.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
fun StandardCategorySection(
    category: BotCategory,
    selectedBotId: String,
    unlockedBotIds: Set<String>,
    crownsMap: Map<String, Int>,
    onSelectBot: (BotProfile) -> Unit
) {
    val bots = remember(category) { BotDatabase.getBotsByCategory(category) }

    Column(modifier = Modifier.fillMaxWidth()) {
        // Section Header: Category Name, Count, and Rating Range Pill
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = category.displayName,
                    color = Color.White.copy(alpha = 0.95f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(6.dp))
                Surface(
                    color = Color(0xFF242E3B),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "${category.count}",
                        color = Color.White.copy(alpha = 0.65f),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                    )
                }
            }

            // Rating Range Pill
            Surface(
                color = Color(0xFF1B232D),
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(1.dp, Color(0xFF81B64C).copy(alpha = 0.45f))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "⚡ ${category.ratingRange}",
                        color = Color(0xFF81B64C),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }

        if (category.subtitle.isNotEmpty()) {
            Text(
                text = category.subtitle,
                color = Color.White.copy(alpha = 0.45f),
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 2.dp, bottom = 8.dp)
            )
        } else {
            Spacer(modifier = Modifier.height(8.dp))
        }

        // 5-Column Grid of Avatar Cards
        val rows = bots.chunked(5)
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            rows.forEach { rowBots ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    for (i in 0 until 5) {
                        if (i < rowBots.size) {
                            val bot = rowBots[i]
                            val isSelected = bot.id == selectedBotId
                            val isUnlocked = unlockedBotIds.contains(bot.id)
                            val crowns = crownsMap[bot.id] ?: 0

                            BotCard(
                                bot = bot,
                                isSelected = isSelected,
                                isUnlocked = isUnlocked,
                                crowns = crowns,
                                modifier = Modifier.weight(1f),
                                onClick = { onSelectBot(bot) }
                            )
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BotCard(
    bot: BotProfile,
    isSelected: Boolean,
    isUnlocked: Boolean,
    crowns: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .aspectRatio(0.85f)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isUnlocked) Color(0xFF222B37) else Color(0xFF1A212B))
            .then(
                if (isSelected) Modifier.border(2.5.dp, Color(0xFF81B64C), RoundedCornerShape(8.dp))
                else Modifier
            )
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Bot Avatar
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                BotAvatar(
                    style = bot.avatarStyle,
                    size = 46.dp,
                    modifier = if (!isUnlocked) Modifier.alpha(0.55f) else Modifier
                )
            }

            // Bottom state: Locked (🔒) or Crowned (👑👑👑) or Rating
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .padding(bottom = 3.dp),
                contentAlignment = Alignment.Center
            ) {
                when {
                    !isUnlocked -> {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Locked",
                            tint = Color.White.copy(alpha = 0.65f),
                            modifier = Modifier.size(12.dp)
                        )
                    }
                    crowns >= 3 -> {
                        Text(
                            text = "👑👑👑",
                            fontSize = 8.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    else -> {
                        Text(
                            text = "${bot.rating}",
                            color = Color.White.copy(alpha = 0.65f),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
