package com.chessmaster.play.engine

import com.chessmaster.play.model.*
import kotlinx.coroutines.isActive
import kotlin.coroutines.coroutineContext

class AIEngine(private val gameEngine: GameEngine) {

    // Piece values
    private val PAWN_VALUE = 100
    private val KNIGHT_VALUE = 320
    private val BISHOP_VALUE = 330
    private val ROOK_VALUE = 500
    private val QUEEN_VALUE = 900
    private val KING_VALUE = 20000

    // Basic Piece-Square Tables for White (Flip for Black)
    private val pawnTable = arrayOf(
        0,  0,  0,  0,  0,  0,  0,  0,
        50, 50, 50, 50, 50, 50, 50, 50,
        10, 10, 20, 30, 30, 20, 10, 10,
         5,  5, 10, 25, 25, 10,  5,  5,
         0,  0,  0, 20, 20,  0,  0,  0,
         5, -5,-10,  0,  0,-10, -5,  5,
         5, 10, 10,-20,-20, 10, 10,  5,
         0,  0,  0,  0,  0,  0,  0,  0
    )

    private val knightTable = arrayOf(
        -50,-40,-30,-30,-30,-30,-40,-50,
        -40,-20,  0,  0,  0,  0,-20,-40,
        -30,  0, 10, 15, 15, 10,  0,-30,
        -30,  5, 15, 20, 20, 15,  5,-30,
        -30,  0, 15, 20, 20, 15,  0,-30,
        -30,  5, 10, 15, 15, 10,  5,-30,
        -40,-20,  0,  5,  5,  0,-20,-40,
        -50,-40,-30,-30,-30,-30,-40,-50
    )

    private val bishopTable = arrayOf(
        -20,-10,-10,-10,-10,-10,-10,-20,
        -10,  0,  0,  0,  0,  0,  0,-10,
        -10,  0,  5, 10, 10,  5,  0,-10,
        -10,  5,  5, 10, 10,  5,  5,-10,
        -10,  0, 10, 10, 10, 10,  0,-10,
        -10, 10, 10, 10, 10, 10, 10,-10,
        -10,  5,  0,  0,  0,  0,  5,-10,
        -20,-10,-10,-10,-10,-10,-10,-20
    )

    private val rookTable = arrayOf(
          0,  0,  0,  0,  0,  0,  0,  0,
          5, 10, 10, 10, 10, 10, 10,  5,
         -5,  0,  0,  0,  0,  0,  0, -5,
         -5,  0,  0,  0,  0,  0,  0, -5,
         -5,  0,  0,  0,  0,  0,  0, -5,
         -5,  0,  0,  0,  0,  0,  0, -5,
         -5,  0,  0,  0,  0,  0,  0, -5,
          0,  0,  0,  5,  5,  0,  0,  0
    )

    private val queenTable = arrayOf(
        -20,-10,-10, -5, -5,-10,-10,-20,
        -10,  0,  0,  0,  0,  0,  0,-10,
        -10,  0,  5,  5,  5,  5,  0,-10,
         -5,  0,  5,  5,  5,  5,  0, -5,
          0,  0,  5,  5,  5,  5,  0, -5,
        -10,  5,  5,  5,  5,  5,  0,-10,
        -10,  0,  5,  0,  0,  0,  0,-10,
        -20,-10,-10, -5, -5,-10,-10,-20
    )

    private val kingMidgameTable = arrayOf(
        -30,-40,-40,-50,-50,-40,-40,-30,
        -30,-40,-40,-50,-50,-40,-40,-30,
        -30,-40,-40,-50,-50,-40,-40,-30,
        -30,-40,-40,-50,-50,-40,-40,-30,
        -20,-30,-30,-40,-40,-30,-30,-20,
        -10,-20,-20,-20,-20,-20,-20,-10,
         20, 20,  0,  0,  0,  0, 20, 20,
         20, 30, 10,  0,  0, 10, 30, 20
    )

    suspend fun getBestMove(boardState: BoardState, color: PieceColor, depth: Int): Move? {
        var bestMove: Move? = null
        var maxEval = Int.MIN_VALUE
        var alpha = Int.MIN_VALUE
        var beta = Int.MAX_VALUE

        val legalMoves = gameEngine.getAllLegalMoves(boardState, color)
        if (legalMoves.isEmpty()) return null

        // Move ordering: put captures first for better pruning
        val orderedMoves = legalMoves.sortedByDescending { if (it.isCapture) 10 else 0 }

        for (move in orderedMoves) {
            if (!coroutineContext.isActive) return null
            val newBoard = boardState.copyWithMove(move)
            val eval = minimax(newBoard, depth - 1, alpha, beta, false, color)
            
            if (eval > maxEval) {
                maxEval = eval
                bestMove = move
            }
            alpha = maxOf(alpha, eval)
        }
        return bestMove ?: legalMoves.firstOrNull()
    }

    private suspend fun minimax(boardState: BoardState, depth: Int, alpha: Int, beta: Int, isMaximizing: Boolean, aiColor: PieceColor): Int {
        if (!coroutineContext.isActive) return 0
        if (depth == 0) {
            return evaluateBoard(boardState, aiColor)
        }

        val currentColor = if (isMaximizing) aiColor else aiColor.opposite()
        val legalMoves = gameEngine.getAllLegalMoves(boardState, currentColor)

        if (legalMoves.isEmpty()) {
            return if (gameEngine.isKingInCheck(boardState, currentColor)) {
                if (isMaximizing) -100000 + (4 - depth) else 100000 - (4 - depth) // Checkmate
            } else {
                0 // Stalemate
            }
        }

        val orderedMoves = legalMoves.sortedByDescending { if (it.isCapture) 10 else 0 }
        var currentAlpha = alpha
        var currentBeta = beta

        if (isMaximizing) {
            var maxEval = Int.MIN_VALUE
            for (move in orderedMoves) {
                if (!coroutineContext.isActive) return 0
                val eval = minimax(boardState.copyWithMove(move), depth - 1, currentAlpha, currentBeta, false, aiColor)
                maxEval = maxOf(maxEval, eval)
                currentAlpha = maxOf(currentAlpha, eval)
                if (currentBeta <= currentAlpha) break
            }
            return maxEval
        } else {
            var minEval = Int.MAX_VALUE
            for (move in orderedMoves) {
                if (!coroutineContext.isActive) return 0
                val eval = minimax(boardState.copyWithMove(move), depth - 1, currentAlpha, currentBeta, true, aiColor)
                minEval = minOf(minEval, eval)
                currentBeta = minOf(currentBeta, eval)
                if (currentBeta <= currentAlpha) break
            }
            return minEval
        }
    }

    private fun evaluateBoard(boardState: BoardState, aiColor: PieceColor): Int {
        var score = 0
        for ((square, piece) in boardState.board) {
            val isAI = piece.color == aiColor
            val pieceValue = getPieceValue(piece.type)
            val pstValue = getPSTValue(piece.type, square, piece.color)
            
            val totalValue = pieceValue + pstValue
            if (isAI) {
                score += totalValue
            } else {
                score -= totalValue
            }
        }
        return score
    }

    private fun getPieceValue(type: PieceType): Int {
        return when (type) {
            PieceType.PAWN -> PAWN_VALUE
            PieceType.KNIGHT -> KNIGHT_VALUE
            PieceType.BISHOP -> BISHOP_VALUE
            PieceType.ROOK -> ROOK_VALUE
            PieceType.QUEEN -> QUEEN_VALUE
            PieceType.KING -> KING_VALUE
        }
    }

    private fun getPSTValue(type: PieceType, square: Square, color: PieceColor): Int {
        // Ranks are 0-7, files 0-7.
        // Tables are defined from Black's perspective (index 0 is a8, 63 is h1) assuming White starts at bottom.
        // Let's standardize: array index = (7 - rank) * 8 + file for White
        val index = if (color == PieceColor.WHITE) {
            (7 - square.rank) * 8 + square.file
        } else {
            square.rank * 8 + square.file
        }
        
        return when (type) {
            PieceType.PAWN -> pawnTable[index]
            PieceType.KNIGHT -> knightTable[index]
            PieceType.BISHOP -> bishopTable[index]
            PieceType.ROOK -> rookTable[index]
            PieceType.QUEEN -> queenTable[index]
            PieceType.KING -> kingMidgameTable[index]
        }
    }
}
