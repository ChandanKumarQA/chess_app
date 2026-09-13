package com.chessmaster.play

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.material3.Scaffold
import com.chessmaster.play.ui.components.ChessBottomBar
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chessmaster.play.model.GameMode
import com.chessmaster.play.theme.ChessTheme
import com.chessmaster.play.ui.MainScreen
import com.chessmaster.play.ui.screens.StartScreen
import com.chessmaster.play.ui.screens.PuzzlesScreen
import com.chessmaster.play.ui.screens.RankingsScreen
import com.chessmaster.play.ui.screens.OnlineFriendsScreen
import com.chessmaster.play.ui.screens.SettingsScreen
import com.chessmaster.play.ui.screens.AnalysisBoardScreen
import com.chessmaster.play.ui.screens.ClockScreen
import com.chessmaster.play.ui.screens.BotSelectionScreen
import com.chessmaster.play.ui.screens.BotGameScreen
import com.chessmaster.play.model.BotProfile
import com.chessmaster.play.viewmodel.ChessViewModel

enum class Screen {
    START, GAME, PUZZLES, RANKINGS, FRIENDS, SETTINGS, LEARN, PUZZLE_GAME,
    TACTICAL_THEMES, ENDGAME_TRAINING, OPENING_TRAPS, PUZZLE_RUSH, SURVIVAL_MODE,
    LESSON_DETAIL, ANALYSIS_BOARD, CLOCK,
    BOT_SELECTION, BOT_GAME
}

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    enableEdgeToEdge()
    setContent {
        val viewModel: ChessViewModel = viewModel()
        val learnViewModel: com.chessmaster.play.viewmodel.LearnViewModel = viewModel()
        val isDarkMode by viewModel.isDarkMode.collectAsState()
        
        ChessTheme(darkTheme = isDarkMode) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                    var currentScreen by remember { mutableStateOf(Screen.START) }
                    var selectedLessonId by remember { mutableStateOf<String?>(null) }
                    var puzzleSourceScreen by remember { mutableStateOf(Screen.PUZZLES) }
                    var selectedBot by remember { mutableStateOf<BotProfile?>(null) }
                    var isNewBotGame by remember { mutableStateOf(true) }
                    
                    androidx.activity.compose.BackHandler(enabled = currentScreen != Screen.START) {
                        currentScreen = when (currentScreen) {
                            Screen.PUZZLE_GAME -> puzzleSourceScreen
                            Screen.TACTICAL_THEMES, 
                            Screen.ENDGAME_TRAINING, Screen.OPENING_TRAPS, 
                            Screen.PUZZLE_RUSH, Screen.SURVIVAL_MODE -> Screen.PUZZLES
                            Screen.LESSON_DETAIL -> Screen.LEARN
                            Screen.ANALYSIS_BOARD, Screen.CLOCK -> Screen.SETTINGS
                            Screen.BOT_SELECTION -> Screen.START
                            Screen.BOT_GAME -> Screen.BOT_SELECTION
                            else -> Screen.START
                        }
                    }
                    
                    val showBottomBar = currentScreen in listOf(Screen.START, Screen.PUZZLES, Screen.RANKINGS, Screen.SETTINGS, Screen.LEARN)
                    
                Scaffold(
                    bottomBar = {
                        if (showBottomBar) {
                            ChessBottomBar(
                                currentScreen = currentScreen,
                                onNavigate = { screen -> currentScreen = screen }
                            )
                        }
                    }
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues).consumeWindowInsets(paddingValues).fillMaxSize()) {
                        when (currentScreen) {
                            Screen.START -> {
                                StartScreen(
                                    onPlayOffline = { timeControl, color ->
                                        viewModel.setPlayerColor(color)
                                        viewModel.setTimeControl(timeControl)
                                        viewModel.setGameMode(GameMode.PVP)
                                        currentScreen = Screen.GAME
                                    },
                                    onPlayOnline = {
                                        viewModel.setGameMode(GameMode.ONLINE)
                                        currentScreen = Screen.GAME
                                    },
                                    onPlayComputer = { timeControl, color ->
                                        viewModel.setPlayerColor(color)
                                        viewModel.setTimeControl(timeControl)
                                        viewModel.setGameMode(GameMode.PVCPU)
                                        currentScreen = Screen.GAME
                                    },
                                    onPlayWithBot = { currentScreen = Screen.BOT_SELECTION },
                                    onPlayFriends = { currentScreen = Screen.FRIENDS },
                                    onPuzzles = { currentScreen = Screen.PUZZLES },
                                    onRankings = { currentScreen = Screen.RANKINGS },
                                    onFriends = { currentScreen = Screen.FRIENDS },
                                    onSettings = { currentScreen = Screen.SETTINGS }
                                )
                            }
                            Screen.BOT_SELECTION -> {
                                BotSelectionScreen(
                                    onBack = { currentScreen = Screen.START },
                                    onStartGame = { bot, isNewGame ->
                                        selectedBot = bot
                                        isNewBotGame = isNewGame
                                        currentScreen = Screen.BOT_GAME
                                    }
                                )
                            }
                            Screen.BOT_GAME -> {
                                val botToPlay = selectedBot ?: com.chessmaster.play.data.BotDatabase.bandBots.first()
                                BotGameScreen(
                                    bot = botToPlay,
                                    isNewGame = isNewBotGame,
                                    onBack = { currentScreen = Screen.BOT_SELECTION }
                                )
                            }
                            Screen.GAME -> {
                                MainScreen(
                                    viewModel = viewModel,
                                    onBack = { currentScreen = Screen.START }
                                )
                            }
                            Screen.PUZZLES -> PuzzlesScreen(
                                onBack = { currentScreen = Screen.START },
                                onPlayPuzzle = { 
                                    puzzleSourceScreen = Screen.PUZZLES
                                    currentScreen = Screen.PUZZLE_GAME 
                                },
                                onTacticalThemes = { currentScreen = Screen.TACTICAL_THEMES },
                                onEndgameTraining = { currentScreen = Screen.ENDGAME_TRAINING },
                                onOpeningTraps = { currentScreen = Screen.OPENING_TRAPS },
                                onPuzzleRush = { currentScreen = Screen.PUZZLE_RUSH },
                                onSurvivalMode = { currentScreen = Screen.SURVIVAL_MODE }
                            )
                            Screen.RANKINGS -> RankingsScreen(onBack = { currentScreen = Screen.START })
                            Screen.FRIENDS -> OnlineFriendsScreen(
                                viewModel = viewModel,
                                onBack = { currentScreen = Screen.START },
                                onNavigateToGame = { currentScreen = Screen.GAME }
                            )
                            Screen.SETTINGS -> SettingsScreen(
                                viewModel = viewModel,
                                onBack = { currentScreen = Screen.START },
                                onNavigateToAnalysis = { currentScreen = Screen.ANALYSIS_BOARD },
                                onNavigateToClock = { currentScreen = Screen.CLOCK }
                            )
                            Screen.ANALYSIS_BOARD -> AnalysisBoardScreen(
                                onBack = { currentScreen = Screen.SETTINGS }
                            )
                            Screen.CLOCK -> ClockScreen(
                                onBack = { currentScreen = Screen.SETTINGS }
                            )
                            Screen.LEARN -> com.chessmaster.play.ui.screens.LearnScreen(
                                onBack = { currentScreen = Screen.START },
                                onLessonSelected = { lessonId -> 
                                    selectedLessonId = lessonId
                                    currentScreen = Screen.LESSON_DETAIL 
                                },
                                viewModel = learnViewModel
                            )
                            Screen.LESSON_DETAIL -> {
                                selectedLessonId?.let { lessonId ->
                                    com.chessmaster.play.ui.screens.LessonDetailScreen(
                                        lessonId = lessonId,
                                        viewModel = learnViewModel,
                                        onBack = { currentScreen = Screen.LEARN }
                                    )
                                }
                            }
                            Screen.PUZZLE_GAME -> com.chessmaster.play.ui.screens.PuzzleGameScreen(onBack = { currentScreen = puzzleSourceScreen })
                            Screen.TACTICAL_THEMES -> com.chessmaster.play.ui.screens.TacticalThemesScreen(
                                onBack = { currentScreen = Screen.PUZZLES }, 
                                onPlayPuzzle = { 
                                    puzzleSourceScreen = Screen.TACTICAL_THEMES
                                    currentScreen = Screen.PUZZLE_GAME 
                                }
                            )
                            Screen.ENDGAME_TRAINING -> com.chessmaster.play.ui.screens.EndgameTrainingScreen(
                                onBack = { currentScreen = Screen.PUZZLES }, 
                                onPlayPuzzle = { 
                                    puzzleSourceScreen = Screen.ENDGAME_TRAINING
                                    currentScreen = Screen.PUZZLE_GAME 
                                }
                            )
                            Screen.OPENING_TRAPS -> com.chessmaster.play.ui.screens.OpeningTrapsScreen(onBack = { currentScreen = Screen.PUZZLES })
                            Screen.PUZZLE_RUSH -> com.chessmaster.play.ui.screens.PuzzleRushScreen(onBack = { currentScreen = Screen.PUZZLES })
                            Screen.SURVIVAL_MODE -> com.chessmaster.play.ui.screens.SurvivalModeScreen(onBack = { currentScreen = Screen.PUZZLES })
                        }
                    }
                }
            }
        }
    }
  }
}
