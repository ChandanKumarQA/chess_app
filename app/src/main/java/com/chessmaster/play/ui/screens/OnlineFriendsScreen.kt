package com.chessmaster.play.ui.screens
import android.content.Intent
import android.app.Activity
import android.widget.Toast
import com.chessmaster.play.data.RoomCreditManager
import com.chessmaster.play.RewardedAdManager
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chessmaster.play.model.TimeControl
import com.chessmaster.play.viewmodel.ChessViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnlineFriendsScreen(
    viewModel: ChessViewModel,
    onBack: () -> Unit,
    onNavigateToGame: () -> Unit
) {
    val backgroundColor = MaterialTheme.colorScheme.background
    val topBarColor = MaterialTheme.colorScheme.surfaceVariant
    var joinCode by remember { mutableStateOf("") }
    
    val context = LocalContext.current
    val activity = context as? Activity
    val roomCreditManager = remember { RoomCreditManager(context) }
    
    var showBuyRoomsDialog by remember { mutableStateOf(false) }
    val adManager = remember { RewardedAdManager(context) }
    
    val roomCode by viewModel.roomCode.collectAsState()
    val connectionStatus by viewModel.connectionStatus.collectAsState()
    val isHost by viewModel.isHost.collectAsState()
    val hostReady by viewModel.hostReady.collectAsState()
    val guestReady by viewModel.guestReady.collectAsState()
    val guestJoined by viewModel.guestJoined.collectAsState()
    val colorPreference by viewModel.colorPreference.collectAsState()
    val clock by viewModel.clock.collectAsState()

    var showStartingCountdown by remember { mutableStateOf(false) }
    var countdown by remember { mutableStateOf(3) }

    LaunchedEffect(connectionStatus) {
        if (connectionStatus == "Starting") {
            showStartingCountdown = true
            for (i in 3 downTo 1) {
                countdown = i
                delay(1000)
            }
            if (isHost) {
                viewModel.proceedToGame()
            }
        } else if (connectionStatus == "In Game") {
            showStartingCountdown = false
            onNavigateToGame()
        }
    }

    if (showStartingCountdown) {
        Box(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Game starts in...", color = MaterialTheme.colorScheme.onBackground, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(32.dp))
                Text(countdown.toString(), color = Color(0xFF34C759), fontSize = 72.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(32.dp))
                Text("Loading Chess Board...", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), fontSize = 16.sp)
            }
        }
        return
    }

    if (showBuyRoomsDialog) {
        BuyRoomsDialog(
            adManager = adManager,
            activity = activity,
            onDismiss = { showBuyRoomsDialog = false },
            onRoomsEarned = { amount ->
                roomCreditManager.addPurchasedRooms(amount)
                Toast.makeText(context, "Earned $amount rooms!", Toast.LENGTH_SHORT).show()
                showBuyRoomsDialog = false
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (roomCode != null) "Room Created" else "Play with Friends", color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.exitRoom()
                        onBack()
                    }) {
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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (roomCode != null) {
                LobbyScreen(
                    roomCode = roomCode!!,
                    isHost = isHost,
                    hostReady = hostReady,
                    guestReady = guestReady,
                    guestJoined = guestJoined,
                    colorPreference = colorPreference,
                    timeControl = clock.timeControl,
                    onToggleReady = { viewModel.toggleReady(it) },
                    onSettingsChange = { tc, cp -> viewModel.updateLobbySettings(tc.name, cp) },
                    onStartGame = { viewModel.startGame() },
                    onCancel = {
                        viewModel.exitRoom()
                        onBack()
                    }
                )
            } else {
                CreateJoinScreen(
                    joinCode = joinCode,
                    onJoinCodeChange = { joinCode = it },
                    onCreateClick = { tc, colorPref ->
                        if (roomCreditManager.canCreateRoom()) {
                            roomCreditManager.consumeCreateRoomCredit()
                            viewModel.setTimeControl(tc)
                            viewModel.createRoom(colorPref)
                        } else {
                            showBuyRoomsDialog = true
                        }
                    },
                    onJoinClick = { 
                        if (roomCreditManager.canJoinRoom()) {
                            roomCreditManager.consumeJoinRoomCredit()
                            viewModel.joinRoom(joinCode) 
                        } else {
                            showBuyRoomsDialog = true
                        }
                    },
                    connectionStatus = connectionStatus
                )
            }
        }
    }
}

@Composable
fun CreateJoinScreen(
    joinCode: String,
    onJoinCodeChange: (String) -> Unit,
    onCreateClick: (TimeControl, String) -> Unit,
    onJoinClick: () -> Unit,
    connectionStatus: String
) {
    var showCreateDialog by remember { mutableStateOf(false) }

    Button(
        onClick = { showCreateDialog = true },
        modifier = Modifier.fillMaxWidth().height(60.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81B8E8)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text("Create New Room", color = Color(0xFF121212), fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
    
    Spacer(modifier = Modifier.height(48.dp))
    
    Text("OR", color = Color.Gray, fontWeight = FontWeight.Bold)
    
    Spacer(modifier = Modifier.height(48.dp))
    
    OutlinedTextField(
        value = joinCode,
        onValueChange = { if (it.length <= 6) onJoinCodeChange(it.uppercase()) },
        label = { Text("Enter 6-Character Room Code", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF81B8E8),
            unfocusedBorderColor = Color.Gray,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground
        ),
        modifier = Modifier.fillMaxWidth()
    )
    
    Spacer(modifier = Modifier.height(16.dp))
    
    Button(
        onClick = onJoinClick,
        modifier = Modifier.fillMaxWidth().height(60.dp),
        enabled = joinCode.length == 6,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF67A9E1),
            disabledContainerColor = Color.Gray
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text("Join Room", color = Color(0xFF121212), fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
    
    Spacer(modifier = Modifier.height(24.dp))
    
    if (connectionStatus == "Authenticating..." || connectionStatus == "Creating room..." || connectionStatus == "Joining room...") {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = Color(0xFF81B8E8))
            Spacer(modifier = Modifier.height(16.dp))
            Text(connectionStatus, color = Color(0xFF81B8E8), fontWeight = FontWeight.Bold)
        }
    } else if (connectionStatus.startsWith("Error")) {
        Text(connectionStatus, color = Color.Red, fontWeight = FontWeight.Bold)
    }

    if (showCreateDialog) {
        var selectedTime by remember { mutableStateOf(TimeControl.BLITZ) }
        var selectedColor by remember { mutableStateOf("Random") }
        
        AlertDialog(
            onDismissRequest = { showCreateDialog = false },
            title = { Text("Create New Room") },
            text = {
                Column {
                    Text("Select Time Control:")
                    Spacer(modifier = Modifier.height(8.dp))
                    TimeControl.values().filter { it != TimeControl.UNLIMITED }.forEach { tc ->
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
                            val timeText = if (tc == TimeControl.UNLIMITED) {
                                "Unlimited"
                            } else {
                                val incText = if (tc.incrementSeconds > 0) " + ${tc.incrementSeconds} sec" else ""
                                "${tc.name} (${tc.timeMinutes} min$incText)"
                            }
                            Text(text = timeText)
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Play as:")
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        listOf("White", "Random", "Black").forEach { color ->
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { selectedColor = color }) {
                                RadioButton(
                                    selected = (color == selectedColor),
                                    onClick = { selectedColor = color }
                                )
                                Text(text = color, modifier = Modifier.padding(start = 4.dp))
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(onClick = {
                    showCreateDialog = false
                    onCreateClick(selectedTime, selectedColor)
                }) {
                    Text("Create")
                }
            },
            dismissButton = {
                TextButton(onClick = { showCreateDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun LobbyScreen(
    roomCode: String,
    isHost: Boolean,
    hostReady: Boolean,
    guestReady: Boolean,
    guestJoined: Boolean,
    colorPreference: String,
    timeControl: TimeControl,
    onToggleReady: (Boolean) -> Unit,
    onSettingsChange: (TimeControl, String) -> Unit,
    onStartGame: () -> Unit,
    onCancel: () -> Unit
) {
    val clipboardManager = LocalClipboardManager.current

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            // Room Code Section
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
                    Text("Room Code", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), fontSize = 14.sp)
                    Text(roomCode, color = MaterialTheme.colorScheme.onBackground, fontSize = 36.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 8.dp))
                    
                    val context = LocalContext.current
                    Row {
                        Button(
                            onClick = { clipboardManager.setText(AnnotatedString(roomCode)) },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81B8E8)),
                            modifier = Modifier.height(40.dp)
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = "Copy", tint = Color(0xFF121212), modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Copy", color = Color(0xFF121212), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        }
                        
                        Spacer(modifier = Modifier.width(16.dp))
                        
                        Button(
                            onClick = {
                                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                    type = "text/plain"
                                    putExtra(Intent.EXTRA_SUBJECT, "ChessVerse Room Code")
                                    putExtra(Intent.EXTRA_TEXT, "Join my ChessVerse game! Room code: $roomCode")
                                }
                                context.startActivity(Intent.createChooser(shareIntent, "Share Room Code"))
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81B8E8)),
                            modifier = Modifier.height(40.dp)
                        ) {
                            Icon(Icons.Default.Share, contentDescription = "Share", tint = Color(0xFF121212), modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Share", color = Color(0xFF121212), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        }
                    }
                }
            }

            Divider(color = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier.padding(vertical = 8.dp))
        }

        item {
            // Game Settings
            Text("Game Settings", color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
            
            val timeControls = listOf(TimeControl.BULLET, TimeControl.BLITZ, TimeControl.RAPID)
            val colorOptions = listOf("White", "Black", "Random")

            if (isHost) {
                Text("Time Control", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), fontSize = 14.sp, modifier = Modifier.padding(top = 8.dp))
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                    timeControls.forEach { tc ->
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { onSettingsChange(tc, colorPreference) }) {
                            RadioButton(selected = tc == timeControl, onClick = { onSettingsChange(tc, colorPreference) }, modifier = Modifier.size(24.dp))
                            Text(tc.name, color = MaterialTheme.colorScheme.onBackground, fontSize = 12.sp, modifier = Modifier.padding(start = 4.dp))
                        }
                    }
                }
                
                Text("Color Preference", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), fontSize = 14.sp, modifier = Modifier.padding(top = 16.dp))
                Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                    colorOptions.forEach { cp ->
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { onSettingsChange(timeControl, cp) }.padding(end = 16.dp)) {
                            RadioButton(selected = cp == colorPreference, onClick = { onSettingsChange(timeControl, cp) }, modifier = Modifier.size(24.dp))
                            Text(cp, color = MaterialTheme.colorScheme.onBackground, fontSize = 12.sp, modifier = Modifier.padding(start = 4.dp))
                        }
                    }
                }
            } else {
                // View only for guest
                Text("Time Control: ${timeControl.name}", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), modifier = Modifier.padding(top = 4.dp))
                Text("Color Preference: $colorPreference", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f), modifier = Modifier.padding(top = 4.dp))
            }

            Divider(color = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier.padding(vertical = 16.dp))
        }

        item {
            // Players Section
            Text("Players", color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
            
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(if (isHost) "👤 You (Host)" else "👤 Host", color = MaterialTheme.colorScheme.onBackground)
                Text(if (hostReady) "✅ Ready" else "⏳ Not Ready", color = if (hostReady) Color(0xFF34C759) else Color.Gray)
            }
            
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                if (guestJoined) {
                    Text(if (!isHost) "👤 You (Guest)" else "👤 Opponent", color = MaterialTheme.colorScheme.onBackground)
                    Text(if (guestReady) "✅ Ready" else "⏳ Not Ready", color = if (guestReady) Color(0xFF34C759) else Color.Gray)
                } else {
                    Text("👤 Waiting for Player...", color = Color.Gray)
                }
            }

            Divider(color = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier.padding(vertical = 16.dp))
        }

        item {
            // Actions
            Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(
                    onClick = onCancel,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color.Gray)
                ) {
                    Text(if (isHost) "Cancel Room" else "Leave Room", color = MaterialTheme.colorScheme.onBackground)
                }

                if (isHost) {
                    Button(
                        onClick = onStartGame,
                        enabled = guestJoined && guestReady && hostReady,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF34C759), disabledContainerColor = Color.Gray)
                    ) {
                        Text("Start Game", color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight.Bold)
                    }
                } else {
                    Button(
                        onClick = { onToggleReady(!guestReady) },
                        colors = ButtonDefaults.buttonColors(containerColor = if (guestReady) Color.Gray else Color(0xFF34C759))
                    ) {
                        Text(if (guestReady) "Unready" else "Ready", color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
