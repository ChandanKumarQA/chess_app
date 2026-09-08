package com.chessmaster.play.data

import android.content.Context
import android.content.SharedPreferences
import com.chessmaster.play.model.Square
import com.chessmaster.play.model.learn.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LearnRepository(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("learn_progress", Context.MODE_PRIVATE)

    private val _progress = MutableStateFlow(loadProgress())
    val progress: StateFlow<LearnProgress> = _progress.asStateFlow()

    fun getAllCategories(): List<LearnCategory> {
        return listOf(
            LearnCategory(
                id = "basics",
                title = "Basic Concepts",
                lessons = listOf(getNotationLesson(), getCapturingLesson())
            ),
            LearnCategory(
                id = "check_mate",
                title = "Check & Checkmate",
                lessons = listOf(getCheckLesson(), getOutOfCheckLesson(), getCheckmateLesson(), getStalemateLesson())
            ),
            LearnCategory(
                id = "special_moves",
                title = "Special Moves",
                lessons = listOf(getCastleKingLesson(), getCastleQueenLesson(), getPromotionLesson(), getEnPassantLesson())
            )
        )
    }

    fun getLessonById(id: String): Lesson? {
        return getAllCategories().flatMap { it.lessons }.find { it.id == id }
    }

    fun markLessonCompleted(lessonId: String, earnedXp: Int, earnedCoins: Int) {
        val current = _progress.value
        val newCompleted = current.completedLessonIds + lessonId
        val newXp = current.xp + earnedXp
        val newCoins = current.coins + earnedCoins
        // Simple streak logic: just increment for now if they complete something.
        val newStreak = current.streak + 1

        val newProgress = LearnProgress(newXp, newCoins, newStreak, newCompleted)
        _progress.value = newProgress
        saveProgress(newProgress)
    }

    private fun loadProgress(): LearnProgress {
        val xp = prefs.getInt("xp", 0)
        val coins = prefs.getInt("coins", 0)
        val streak = prefs.getInt("streak", 0)
        val completedStr = prefs.getString("completed", "") ?: ""
        val completed = if (completedStr.isEmpty()) emptySet() else completedStr.split(",").toSet()
        return LearnProgress(xp, coins, streak, completed)
    }

    private fun saveProgress(progress: LearnProgress) {
        prefs.edit()
            .putInt("xp", progress.xp)
            .putInt("coins", progress.coins)
            .putInt("streak", progress.streak)
            .putString("completed", progress.completedLessonIds.joinToString(","))
            .apply()
    }

    // --- Hardcoded Lessons based on requirements ---

    private fun getCapturingLesson(): Lesson = Lesson(
        id = "capturing",
        title = "Capturing",
        difficulty = "Beginner",
        estimatedTime = "3 min",
        description = "Learn how pieces capture opponent pieces.",
        initialFen = "8/8/8/8/3Nq3/3P4/8/8 w - - 0 1",
        steps = listOf(
            LessonStep(
                explanation = "Capturing opponent's pieces is the main goal in chess. When a move you make lands on a square where the opponent's piece is standing, that piece is captured by you."
            ),
            LessonStep(
                explanation = "A pawn can only capture the opponent's piece on either of the two squares diagonally in the front but cannot move to those squares if they are empty."
            ),
            LessonStep(
                explanation = "Now let's capture the opponent's Queen using your Pawn.",
                handPointer = Square(3, 2)
            )
        ),
        practiceConfig = PracticeConfig(
            startFen = "8/8/8/8/3Nq3/3P4/8/8 w - - 0 1",
            instructions = "Capture the Queen using your Pawn.",
            targetMoves = listOf("d3e4")
        ),
        quizConfig = QuizConfig(
            questions = listOf(
                QuizQuestion("Can you capture your own pieces?", listOf("Yes", "No", "Sometimes"), 1)
            )
        )
    )

    private fun getNotationLesson(): Lesson = Lesson(
        id = "notation",
        title = "Notation",
        difficulty = "Beginner",
        estimatedTime = "4 min",
        description = "Learn how to read and write chess moves.",
        initialFen = "8/8/8/8/8/4Q3/8/8 w - - 0 1",
        steps = listOf(
            LessonStep(
                explanation = "Each square in chess is uniquely identified by a set of coordinates, a-h for the files (horizontal) followed by 1-8 for the ranks (vertical)."
            ),
            LessonStep(
                explanation = "Your Queen is currently on e3, let's move it to c5. Try to find c5 using the letters on the border.",
                handPointer = Square(4, 2) // e3
            )
        ),
        practiceConfig = PracticeConfig(
            startFen = "8/8/8/8/8/4Q3/8/8 w - - 0 1",
            instructions = "Your Queen is currently on e3, let's move it to c5. Try to find c5 using the letters on the border.",
            targetMoves = listOf("e3c5")
        ),
        quizConfig = QuizConfig(
            questions = listOf(
                QuizQuestion("What square does the White King start on?", listOf("d1", "e1", "e8"), 1)
            )
        )
    )

    private fun getCheckLesson(): Lesson = Lesson(
        id = "check",
        title = "Check",
        difficulty = "Beginner",
        estimatedTime = "2 min",
        description = "When a King is under attack, it is in 'Check'.",
        initialFen = "8/8/8/5k2/8/8/3R4/2K5 w - - 0 1",
        steps = listOf(
            LessonStep(
                explanation = "The King is in check when it is under attack by an enemy piece."
            ),
            LessonStep(
                explanation = "Now let's check the opponent's King by moving your Rook from d2 to f2",
                handPointer = Square(3, 1)
            )
        ),
        practiceConfig = PracticeConfig(
            startFen = "8/8/8/5k2/8/8/3R4/2K5 w - - 0 1",
            instructions = "Now let's check the opponent's King by moving your Rook from d2 to f2",
            targetMoves = listOf("d2f2")
        ),
        quizConfig = QuizConfig(
            questions = listOf(
                QuizQuestion("What is 'Check'?", listOf("The game is over", "The King is under attack", "A pawn is promoted"), 1)
            )
        )
    )

    private fun getOutOfCheckLesson(): Lesson = Lesson(
        id = "out_of_check",
        title = "Getting out of check",
        difficulty = "Intermediate",
        estimatedTime = "4 min",
        description = "Learn how to escape check.",
        initialFen = "2k5/8/8/2P5/7q/8/4N1P1/5Q1K w - - 0 1",
        steps = listOf(
            LessonStep(
                explanation = "Black's Queen has your King in check. Can you find the move to get out of check and go to a safe place?",
                handPointer = Square(7, 0)
            )
        ),
        practiceConfig = PracticeConfig(
            startFen = "2k5/8/8/2P5/7q/8/4N1P1/5Q1K w - - 0 1",
            instructions = "Black's Queen has your King in check. Can you find the move to get out of check and go to a safe place?",
            targetMoves = listOf("h1g1")
        ),
        quizConfig = QuizConfig(
            questions = listOf(
                QuizQuestion("Which is NOT a way to escape check?", listOf("Move the King", "Block the attack", "Castle"), 2)
            )
        )
    )

    private fun getCheckmateLesson(): Lesson = Lesson(
        id = "checkmate",
        title = "Checkmate",
        difficulty = "Beginner",
        estimatedTime = "3 min",
        description = "Checkmate ends the game.",
        initialFen = "5n1k/6pp/3Q4/8/8/8/5P2/3R1K2 w - - 0 1",
        steps = listOf(
            LessonStep(
                explanation = "Black's King is hiding safely behind the Pawns. Let's checkmate while also capturing the opponent's Knight by moving your Queen from d6 to f8.",
                handPointer = Square(3, 5)
            )
        ),
        practiceConfig = PracticeConfig(
            startFen = "5n1k/6pp/3Q4/8/8/8/5P2/3R1K2 w - - 0 1",
            instructions = "Black's King is hiding safely behind the Pawns. Let's checkmate while also capturing the opponent's Knight by moving your Queen from d6 to f8.",
            targetMoves = listOf("d6f8")
        ),
        quizConfig = QuizConfig(
            questions = listOf(
                QuizQuestion("What happens when it's Checkmate?", listOf("The game is a draw", "The attacking player wins", "The King is captured"), 1)
            )
        )
    )

    private fun getStalemateLesson(): Lesson = Lesson(
        id = "stalemate",
        title = "Stalemate",
        difficulty = "Intermediate",
        estimatedTime = "3 min",
        description = "Stalemate is a draw where a player has no legal moves but is not in check.",
        initialFen = "7k/8/5K2/8/7p/6RP/8/8 w - - 0 1",
        steps = listOf(
            LessonStep(
                explanation = "The game ends in a draw and no one wins, even if the other player has many more pieces left."
            ),
            LessonStep(
                explanation = "In the current situation, moving the Rook from g3 to g7 will result in a Stalemate.",
                handPointer = Square(6, 2)
            )
        ),
        practiceConfig = PracticeConfig(
            startFen = "7k/8/5K2/8/7p/6RP/8/8 w - - 0 1",
            instructions = "In the current situation, moving the Rook from g3 to g7 will result in a Stalemate.",
            targetMoves = listOf("g3g7")
        ),
        quizConfig = QuizConfig(
            questions = listOf(
                QuizQuestion("Is Stalemate a win or a draw?", listOf("Win", "Draw", "Loss"), 1)
            )
        )
    )

    private fun getCastleKingLesson(): Lesson = Lesson(
        id = "castle_king",
        title = "Castling - King side",
        difficulty = "Intermediate",
        estimatedTime = "2 min",
        description = "Protect your King and develop your Rook in one move.",
        initialFen = "8/8/8/8/8/8/8/4K2R w K - 0 1",
        steps = listOf(
            LessonStep(
                explanation = "Once in every game, you can make a special move involving the King and one of the Rooks. It is called Castling. It is the only time in chess you can move two pieces in one move."
            ),
            LessonStep(
                explanation = "The King moves two squares towards any of the Rooks, and that Rook moves directly to the other side of the King. But there are certain conditions, continue to learn more."
            ),
            LessonStep(
                explanation = "The King and Rook involved in Castling must not have previously moved and there are no pieces in the way."
            ),
            LessonStep(
                explanation = "Also, you cannot castle if the King is in check, moves into check or goes through a square that will put the King in check.",
                handPointer = Square(4, 0)
            )
        ),
        practiceConfig = PracticeConfig(
            startFen = "8/8/8/8/8/8/8/4K2R w K - 0 1",
            instructions = "Castle Kingside.",
            targetMoves = listOf("e1g1")
        ),
        quizConfig = QuizConfig(
            questions = listOf(
                QuizQuestion("Can you castle while in check?", listOf("Yes", "No", "Only Queenside"), 1)
            )
        )
    )

    private fun getCastleQueenLesson(): Lesson = Lesson(
        id = "castle_queen",
        title = "Castling - Queen side",
        difficulty = "Intermediate",
        estimatedTime = "2 min",
        description = "Castling on the longer side.",
        initialFen = "8/8/8/8/8/8/8/R3K3 w Q - 0 1",
        steps = listOf(
            LessonStep(
                explanation = "Similarly, Castling on the left side is called Queenside Castling."
            ),
            LessonStep(
                explanation = "Now let's move the King two squares towards the left Rook to perform Queenside Castling.",
                handPointer = Square(4, 0)
            )
        ),
        practiceConfig = PracticeConfig(
            startFen = "8/8/8/8/8/8/8/R3K3 w Q - 0 1",
            instructions = "Now let's move the King two squares towards the left Rook to perform Queenside Castling.",
            targetMoves = listOf("e1c1")
        ),
        quizConfig = QuizConfig(
            questions = listOf(
                QuizQuestion("How many squares does the King move when castling queenside?", listOf("1", "2", "3"), 1)
            )
        )
    )

    private fun getPromotionLesson(): Lesson = Lesson(
        id = "promotion",
        title = "Promotion",
        difficulty = "Beginner",
        estimatedTime = "2 min",
        description = "When a pawn reaches the other side, it can become any piece.",
        initialFen = "8/5P2/8/8/8/8/8/8 w - - 0 1",
        steps = listOf(
            LessonStep(
                explanation = "Let's move the Pawn to the end, then you can choose any piece you would like to replace the Pawn with.",
                handPointer = Square(5, 6)
            )
        ),
        practiceConfig = PracticeConfig(
            startFen = "8/5P2/8/8/8/8/8/8 w - - 0 1",
            instructions = "Let's move the Pawn to the end, then you can choose any piece you would like to replace the Pawn with.",
            targetMoves = listOf("f7f8q", "f7f8Q")
        ),
        quizConfig = QuizConfig(
            questions = listOf(
                QuizQuestion("Which piece CANNOT a pawn promote to?", listOf("King", "Queen", "Knight"), 0)
            )
        )
    )

    private fun getEnPassantLesson(): Lesson = Lesson(
        id = "en_passant",
        title = "En Passant",
        difficulty = "Advanced",
        estimatedTime = "3 min",
        description = "A special pawn capture rule.",
        initialFen = "8/8/8/2Pp4/4Q3/8/8/8 w - d6 0 1",
        steps = listOf(
            LessonStep(
                explanation = "It is a special Pawn capture move. If your opponent moves a Pawn two squares from its starting position, you can capture it with one of your Pawn as if it had moved just one square."
            ),
            LessonStep(
                explanation = "This move is possible on the very next move. Now the opponent's Pawn is threatening your Queen on e4. Let's make the En Passant move by moving your Pawn diagonally c5 to d6.",
                handPointer = Square(2, 4)
            )
        ),
        practiceConfig = PracticeConfig(
            startFen = "8/8/8/2Pp4/4Q3/8/8/8 w - d6 0 1",
            instructions = "Make the En Passant move by moving your Pawn diagonally c5 to d6.",
            targetMoves = listOf("c5d6")
        ),
        quizConfig = QuizConfig(
            questions = listOf(
                QuizQuestion("When can you capture en passant?", listOf("Immediately", "Anytime", "Never"), 0)
            )
        )
    )
}
