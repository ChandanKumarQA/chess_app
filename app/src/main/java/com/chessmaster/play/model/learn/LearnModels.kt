package com.chessmaster.play.model.learn

import com.chessmaster.play.model.Square

data class LearnCategory(
    val id: String,
    val title: String,
    val lessons: List<Lesson>
)

data class Lesson(
    val id: String,
    val title: String,
    val difficulty: String, // e.g. "Beginner", "Intermediate"
    val estimatedTime: String, // e.g. "5 min"
    val description: String,
    val initialFen: String,
    val steps: List<LessonStep>,
    val practiceConfig: PracticeConfig?,
    val quizConfig: QuizConfig?
)

data class LessonStep(
    val explanation: String,
    val fen: String? = null, // Optional. If null, use previous FEN
    val arrows: List<ArrowConfig> = emptyList(),
    val highlights: List<Square> = emptyList(),
    val movePiece: MoveAnimationConfig? = null,
    val handPointer: Square? = null
)

data class ArrowConfig(
    val from: Square,
    val to: Square,
    val color: String = "green" // "green", "red", "blue"
)

data class MoveAnimationConfig(
    val from: Square,
    val to: Square
)

data class PracticeConfig(
    val startFen: String,
    val instructions: String,
    val targetMoves: List<String> = emptyList(), // e.g. ["e2e4", "e7e5"], empty means let user explore or capture all
    val targetCaptures: List<Square> = emptyList() // User needs to capture all pieces on these squares
)

data class QuizConfig(
    val questions: List<QuizQuestion>
)

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctIndex: Int
)

data class LearnProgress(
    val xp: Int,
    val coins: Int,
    val streak: Int,
    val completedLessonIds: Set<String>
)
