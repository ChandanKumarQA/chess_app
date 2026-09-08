package com.chessmaster.play.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.chessmaster.play.data.LearnRepository
import com.chessmaster.play.engine.GameEngine
import com.chessmaster.play.engine.NotationParser
import com.chessmaster.play.model.*
import com.chessmaster.play.model.learn.Lesson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class LessonPhase {
    EXPLANATION, PRACTICE, QUIZ, COMPLETED
}

class LearnViewModel(application: Application) : AndroidViewModel(application) {

    val repository = LearnRepository(application)
    private val engine = GameEngine()

    private val _currentLesson = MutableStateFlow<Lesson?>(null)
    val currentLesson: StateFlow<Lesson?> = _currentLesson.asStateFlow()

    private val _lessonPhase = MutableStateFlow(LessonPhase.EXPLANATION)
    val lessonPhase: StateFlow<LessonPhase> = _lessonPhase.asStateFlow()

    private val _currentStepIndex = MutableStateFlow(0)
    val currentStepIndex: StateFlow<Int> = _currentStepIndex.asStateFlow()

    // Board State
    private val _boardState = MutableStateFlow(BoardState())
    val boardState: StateFlow<BoardState> = _boardState.asStateFlow()

    private val _selectedSquare = MutableStateFlow<Square?>(null)
    val selectedSquare: StateFlow<Square?> = _selectedSquare.asStateFlow()

    private val _legalMoves = MutableStateFlow<List<Move>>(emptyList())
    val legalMoves: StateFlow<List<Move>> = _legalMoves.asStateFlow()

    private val _currentTurn = MutableStateFlow(PieceColor.WHITE)
    val currentTurn: StateFlow<PieceColor> = _currentTurn.asStateFlow()

    // Practice specific
    private val _practiceTargetMoves = MutableStateFlow<List<String>>(emptyList())
    private val _practiceTargetCaptures = MutableStateFlow<List<Square>>(emptyList())
    private val _practiceCompleted = MutableStateFlow(false)
    val practiceCompleted: StateFlow<Boolean> = _practiceCompleted.asStateFlow()

    private val _practiceMoveError = MutableStateFlow(false)
    val practiceMoveError: StateFlow<Boolean> = _practiceMoveError.asStateFlow()

    // Quiz specific
    private val _quizQuestionIndex = MutableStateFlow(0)
    val quizQuestionIndex: StateFlow<Int> = _quizQuestionIndex.asStateFlow()
    
    private val _quizScore = MutableStateFlow(0)
    val quizScore: StateFlow<Int> = _quizScore.asStateFlow()

    fun loadLesson(lessonId: String) {
        val lesson = repository.getLessonById(lessonId)
        _currentLesson.value = lesson
        if (lesson != null) {
            _lessonPhase.value = LessonPhase.EXPLANATION
            _currentStepIndex.value = 0
            _quizQuestionIndex.value = 0
            _quizScore.value = 0
            _practiceCompleted.value = false
            loadBoardFromFen(lesson.initialFen)
        }
    }

    fun nextStep() {
        val lesson = _currentLesson.value ?: return
        if (_lessonPhase.value == LessonPhase.EXPLANATION) {
            if (_currentStepIndex.value < lesson.steps.size - 1) {
                _currentStepIndex.value += 1
                val fen = lesson.steps[_currentStepIndex.value].fen
                if (fen != null) {
                    loadBoardFromFen(fen)
                }
            } else {
                if (lesson.practiceConfig != null) {
                    startPractice()
                } else {
                    completeLesson()
                }
            }
        }
    }

    fun previousStep() {
        if (_lessonPhase.value == LessonPhase.EXPLANATION && _currentStepIndex.value > 0) {
            _currentStepIndex.value -= 1
            // Simple approach: re-apply the last explicitly set fen (since some steps inherit fen)
            // A more robust approach would scan backwards. For now, reload initial and apply up to step
            val lesson = _currentLesson.value ?: return
            var activeFen = lesson.initialFen
            for (i in 0.._currentStepIndex.value) {
                activeFen = lesson.steps[i].fen ?: activeFen
            }
            loadBoardFromFen(activeFen)
        }
    }

    fun startPractice() {
        val lesson = _currentLesson.value ?: return
        val config = lesson.practiceConfig ?: return
        _lessonPhase.value = LessonPhase.PRACTICE
        _practiceTargetMoves.value = config.targetMoves
        _practiceTargetCaptures.value = config.targetCaptures
        _practiceCompleted.value = false
        loadBoardFromFen(config.startFen)
    }

    fun startQuiz() {
        _lessonPhase.value = LessonPhase.QUIZ
        _quizQuestionIndex.value = 0
    }

    fun answerQuiz(selectedIndex: Int) {
        val lesson = _currentLesson.value ?: return
        val config = lesson.quizConfig ?: return
        val q = config.questions[_quizQuestionIndex.value]
        
        if (selectedIndex == q.correctIndex) {
            _quizScore.value += 1
        }
        
        viewModelScope.launch {
            delay(500)
            if (_quizQuestionIndex.value < config.questions.size - 1) {
                _quizQuestionIndex.value += 1
            } else {
                completeLesson()
            }
        }
    }

    private fun completeLesson() {
        val lesson = _currentLesson.value ?: return
        _lessonPhase.value = LessonPhase.COMPLETED
        val xp = 100 // Example reward
        val coins = 50
        repository.markLessonCompleted(lesson.id, xp, coins)
    }

    private fun loadBoardFromFen(fen: String) {
        try {
            val (state, turn) = NotationParser.fenToBoardState(fen)
            _boardState.value = state
            _currentTurn.value = turn
            _selectedSquare.value = null
            _legalMoves.value = emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun onSquareClicked(square: Square) {
        if (_lessonPhase.value != LessonPhase.PRACTICE || _practiceCompleted.value) return

        val currentSelected = _selectedSquare.value
        val pieceAtSquare = _boardState.value.getPiece(square)

        _practiceMoveError.value = false // Reset error state

        if (currentSelected == null) {
            if (pieceAtSquare != null && pieceAtSquare.color == _currentTurn.value) {
                _selectedSquare.value = square
                _legalMoves.value = engine.getLegalMoves(_boardState.value, square)
            }
        } else {
            val move = _legalMoves.value.find { it.to == square }
            if (move != null) {
                handlePracticeMove(move)
            } else {
                if (pieceAtSquare != null && pieceAtSquare.color == _currentTurn.value) {
                    _selectedSquare.value = square
                    _legalMoves.value = engine.getLegalMoves(_boardState.value, square)
                } else {
                    _selectedSquare.value = null
                    _legalMoves.value = emptyList()
                }
            }
        }
    }

    private fun handlePracticeMove(move: Move) {
        val isCorrect = checkMoveCorrectness(move)
        if (isCorrect) {
            // Apply move
            _boardState.value = _boardState.value.copyWithMove(move)
            _selectedSquare.value = null
            _legalMoves.value = emptyList()
            _currentTurn.value = _currentTurn.value.opposite()
            
            // Consume target or check completion
            val targets = _practiceTargetMoves.value
            if (targets.isNotEmpty()) {
                // If the move is correct, it means the user played one of the valid alternatives.
                // We clear the targets and finish practice.
                _practiceTargetMoves.value = emptyList()
                finishPracticeSuccess()
            } else if (_practiceTargetCaptures.value.isNotEmpty()) {
                val captures = _practiceTargetCaptures.value.toMutableList()
                if (move.isCapture) {
                    captures.remove(move.to)
                    _practiceTargetCaptures.value = captures
                    if (captures.isEmpty()) {
                        finishPracticeSuccess()
                    }
                }
            } else {
                // Free practice?
                finishPracticeSuccess()
            }
        } else {
            // Error handling (flash red)
            _practiceMoveError.value = true
            _selectedSquare.value = null
            _legalMoves.value = emptyList()
        }
    }

    private fun checkMoveCorrectness(move: Move): Boolean {
        val targets = _practiceTargetMoves.value
        if (targets.isNotEmpty()) {
            val moveStr = moveString(move)
            val baseMove = "${move.from}${move.to}"
            
            return targets.any { target ->
                target == moveStr || target.startsWith(baseMove)
            }
        }
        val captures = _practiceTargetCaptures.value
        if (captures.isNotEmpty()) {
            return move.isCapture && captures.contains(move.to)
        }
        return true // Any valid move is correct if no specific targets
    }

    private fun moveString(move: Move): String {
        val p = move.promotionTo
        val prom = if (p != null) {
            when(p) {
                PieceType.QUEEN -> "q"
                PieceType.ROOK -> "r"
                PieceType.BISHOP -> "b"
                PieceType.KNIGHT -> "n"
                else -> ""
            }
        } else ""
        return "\${move.from.toString()}\${move.to.toString()}\$prom"
    }

    private fun finishPracticeSuccess() {
        _practiceCompleted.value = true
        viewModelScope.launch {
            delay(1500)
            val lesson = _currentLesson.value ?: return@launch
            completeLesson()
        }
    }

    fun restartPractice() {
        startPractice()
    }
}
