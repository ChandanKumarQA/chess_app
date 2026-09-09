package com.chessmaster.play.ui.components.coach

import com.chessmaster.play.model.BoardState
import com.chessmaster.play.model.PieceColor
import com.chessmaster.play.model.PieceType
import com.chessmaster.play.model.Puzzle
import com.chessmaster.play.model.Square

/**
 * Generates natural instructional hints for chess puzzles based on the tactical theme,
 * active piece, target square, and board situation.
 */
object PuzzleHintGenerator {

    fun generateHint(
        puzzle: Puzzle,
        expectedMoveStr: String?,
        boardState: BoardState
    ): String {
        if (expectedMoveStr == null || expectedMoveStr.length < 4) {
            return "Look for the most forcing move on the board."
        }

        val fromFile = expectedMoveStr[0] - 'a'
        val fromRank = expectedMoveStr[1] - '1'
        val toFile = expectedMoveStr[2] - 'a'
        val toRank = expectedMoveStr[3] - '1'

        val fromSq = Square(fromFile, fromRank)
        val toSq = Square(toFile, toRank)
        val piece = boardState.getPiece(fromSq)
        val targetPiece = boardState.getPiece(toSq)

        val pieceName = when (piece?.type) {
            PieceType.QUEEN -> "queen"
            PieceType.ROOK -> "rook"
            PieceType.BISHOP -> "bishop"
            PieceType.KNIGHT -> "knight"
            PieceType.PAWN -> "pawn"
            PieceType.KING -> "king"
            null -> "piece"
        }

        val themeLower = puzzle.theme.lowercase()

        return when {
            themeLower.contains("fork") -> {
                if (piece?.type == PieceType.KNIGHT) {
                    "Look for a knight fork attacking two valuable targets at once."
                } else {
                    "Fork multiple enemy pieces with a double attack."
                }
            }
            themeLower.contains("pin") -> {
                "Pin the defender to restrict its movement or win material."
            }
            themeLower.contains("skewer") -> {
                "Attack a valuable piece in front, forcing it to expose the piece behind it."
            }
            themeLower.contains("deflection") || themeLower.contains("attraction") -> {
                "Force my king or defender to abandon the piece it's defending."
            }
            themeLower.contains("back rank") -> {
                "Infiltrate the back rank for a devastating attack."
            }
            themeLower.contains("checkmate") || themeLower.contains("mate") -> {
                "Deliver a decisive check that leaves the enemy king with no escape."
            }
            themeLower.contains("discovered") -> {
                "Unleash a discovered attack by moving your $pieceName."
            }
            targetPiece != null -> {
                "Capture the unprotected ${targetPiece.type.name.lowercase()} on $toSq."
            }
            else -> {
                "Move your $pieceName towards $toSq to create an unstoppable threat."
            }
        }
    }
}
