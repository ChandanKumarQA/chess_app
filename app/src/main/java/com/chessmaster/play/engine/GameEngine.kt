package com.chessmaster.play.engine

import com.chessmaster.play.model.*
import kotlin.math.abs

enum class GameState {
    IN_PROGRESS, CHECKMATE, STALEMATE, DRAW_FIFTY_MOVES, DRAW_THREEFOLD, DRAW_INSUFFICIENT_MATERIAL, TIME_OUT
}

class GameEngine {

    fun getLegalMoves(boardState: BoardState, square: Square): List<Move> {
        val piece = boardState.getPiece(square) ?: return emptyList()
        return generatePseudoLegalMoves(boardState, square, piece.color).filter { move ->
            val boardAfterMove = boardState.copyWithMove(move)
            !isKingInCheck(boardAfterMove, piece.color)
        }
    }

    fun getAllLegalMoves(boardState: BoardState, color: PieceColor): List<Move> {
        val moves = mutableListOf<Move>()
        for (file in 0..7) {
            for (rank in 0..7) {
                val sq = Square(file, rank)
                val p = boardState.getPiece(sq)
                if (p != null && p.color == color) {
                    moves.addAll(getLegalMoves(boardState, sq))
                }
            }
        }
        return moves
    }

    fun checkGameState(boardState: BoardState, currentTurn: PieceColor, history: List<BoardState>): GameState {
        val legalMoves = getAllLegalMoves(boardState, currentTurn)
        
        if (legalMoves.isEmpty()) {
            return if (isKingInCheck(boardState, currentTurn)) {
                GameState.CHECKMATE
            } else {
                GameState.STALEMATE
            }
        }

        if (boardState.halfMoveClock >= 100) { // 50 full moves = 100 half moves
            return GameState.DRAW_FIFTY_MOVES
        }

        if (isInsufficientMaterial(boardState)) {
            return GameState.DRAW_INSUFFICIENT_MATERIAL
        }

        val occurrences = history.count { it == boardState }
        // Including current state means we need >= 3
        if (occurrences >= 3) {
            return GameState.DRAW_THREEFOLD
        }

        return GameState.IN_PROGRESS
    }

    fun isKingInCheck(boardState: BoardState, color: PieceColor): Boolean {
        var kingSquare: Square? = null
        for ((sq, p) in boardState.board) {
            if (p.type == PieceType.KING && p.color == color) {
                kingSquare = sq
                break
            }
        }
        if (kingSquare == null) return false // Should not happen in real games

        val oppositeColor = color.opposite()
        for ((sq, p) in boardState.board) {
            if (p.color == oppositeColor) {
                // Generate pseudo moves for opponent
                val pseudoLegalMoves = generatePseudoLegalMoves(boardState, sq, oppositeColor, checkCastling = false)
                if (pseudoLegalMoves.any { it.to == kingSquare }) {
                    return true
                }
            }
        }
        return false
    }

    private fun generatePseudoLegalMoves(boardState: BoardState, square: Square, color: PieceColor, checkCastling: Boolean = true): List<Move> {
        val piece = boardState.getPiece(square) ?: return emptyList()
        return when (piece.type) {
            PieceType.PAWN -> getPawnMoves(boardState, square, color)
            PieceType.KNIGHT -> getKnightMoves(boardState, square, color)
            PieceType.BISHOP -> getSlidingMoves(boardState, square, color, listOf(Pair(1, 1), Pair(1, -1), Pair(-1, 1), Pair(-1, -1)))
            PieceType.ROOK -> getSlidingMoves(boardState, square, color, listOf(Pair(1, 0), Pair(-1, 0), Pair(0, 1), Pair(0, -1)))
            PieceType.QUEEN -> getSlidingMoves(boardState, square, color, listOf(Pair(1, 1), Pair(1, -1), Pair(-1, 1), Pair(-1, -1), Pair(1, 0), Pair(-1, 0), Pair(0, 1), Pair(0, -1)))
            PieceType.KING -> getKingMoves(boardState, square, color, checkCastling)
        }
    }

    private fun getPawnMoves(boardState: BoardState, square: Square, color: PieceColor): List<Move> {
        val moves = mutableListOf<Move>()
        val direction = if (color == PieceColor.WHITE) 1 else -1
        val startRank = if (color == PieceColor.WHITE) 1 else 6
        val promotionRank = if (color == PieceColor.WHITE) 7 else 0
        val piece = Piece(PieceType.PAWN, color)

        val forward1 = Square(square.file, square.rank + direction)
        if (isValidSquare(forward1) && boardState.getPiece(forward1) == null) {
            if (forward1.rank == promotionRank) {
                addPromotions(moves, piece, square, forward1, isCapture = false)
            } else {
                moves.add(Move(piece, square, forward1))
                if (square.rank == startRank) {
                    val forward2 = Square(square.file, square.rank + 2 * direction)
                    if (boardState.getPiece(forward2) == null) {
                        moves.add(Move(piece, square, forward2, isPawnDoubleMove = true))
                    }
                }
            }
        }

        listOf(-1, 1).forEach { fileOffset ->
            val captureSquare = Square(square.file + fileOffset, square.rank + direction)
            if (isValidSquare(captureSquare)) {
                val targetPiece = boardState.getPiece(captureSquare)
                if (targetPiece != null && targetPiece.color != color) {
                    if (captureSquare.rank == promotionRank) {
                        addPromotions(moves, piece, square, captureSquare, isCapture = true)
                    } else {
                        moves.add(Move(piece, square, captureSquare, isCapture = true))
                    }
                } else if (captureSquare == boardState.enPassantTarget) {
                    moves.add(Move(piece, square, captureSquare, isCapture = true, isEnPassant = true))
                }
            }
        }
        return moves
    }

    private fun addPromotions(moves: MutableList<Move>, piece: Piece, from: Square, to: Square, isCapture: Boolean) {
        listOf(PieceType.QUEEN, PieceType.ROOK, PieceType.BISHOP, PieceType.KNIGHT).forEach { type ->
            moves.add(Move(piece, from, to, isCapture = isCapture, promotionTo = type))
        }
    }

    private fun getKnightMoves(boardState: BoardState, square: Square, color: PieceColor): List<Move> {
        val moves = mutableListOf<Move>()
        val piece = Piece(PieceType.KNIGHT, color)
        val offsets = listOf(Pair(1, 2), Pair(2, 1), Pair(-1, 2), Pair(-2, 1), Pair(1, -2), Pair(2, -1), Pair(-1, -2), Pair(-2, -1))
        for (offset in offsets) {
            val targetSquare = Square(square.file + offset.first, square.rank + offset.second)
            if (isValidSquare(targetSquare)) {
                val targetPiece = boardState.getPiece(targetSquare)
                if (targetPiece == null || targetPiece.color != color) {
                    moves.add(Move(piece, square, targetSquare, isCapture = targetPiece != null))
                }
            }
        }
        return moves
    }

    private fun getKingMoves(boardState: BoardState, square: Square, color: PieceColor, checkCastling: Boolean): List<Move> {
        val moves = mutableListOf<Move>()
        val piece = Piece(PieceType.KING, color)
        for (fileOffset in -1..1) {
            for (rankOffset in -1..1) {
                if (fileOffset == 0 && rankOffset == 0) continue
                val targetSquare = Square(square.file + fileOffset, square.rank + rankOffset)
                if (isValidSquare(targetSquare)) {
                    val targetPiece = boardState.getPiece(targetSquare)
                    if (targetPiece == null || targetPiece.color != color) {
                        moves.add(Move(piece, square, targetSquare, isCapture = targetPiece != null))
                    }
                }
            }
        }
        
        if (checkCastling && !isKingInCheck(boardState, color)) {
            val rank = if (color == PieceColor.WHITE) 0 else 7
            val kingMoved = if (color == PieceColor.WHITE) boardState.castlingRights.whiteKingMoved else boardState.castlingRights.blackKingMoved
            val ksRookMoved = if (color == PieceColor.WHITE) boardState.castlingRights.whiteKingsideRookMoved else boardState.castlingRights.blackKingsideRookMoved
            val qsRookMoved = if (color == PieceColor.WHITE) boardState.castlingRights.whiteQueensideRookMoved else boardState.castlingRights.blackQueensideRookMoved

            if (!kingMoved) {
                // Kingside
                if (!ksRookMoved && boardState.getPiece(Square(5, rank)) == null && boardState.getPiece(Square(6, rank)) == null) {
                    if (!isSquareAttacked(boardState, Square(5, rank), color) && !isSquareAttacked(boardState, Square(6, rank), color)) {
                        moves.add(Move(piece, square, Square(6, rank), isCastling = true))
                    }
                }
                // Queenside
                if (!qsRookMoved && boardState.getPiece(Square(3, rank)) == null && boardState.getPiece(Square(2, rank)) == null && boardState.getPiece(Square(1, rank)) == null) {
                    if (!isSquareAttacked(boardState, Square(3, rank), color) && !isSquareAttacked(boardState, Square(2, rank), color)) {
                        moves.add(Move(piece, square, Square(2, rank), isCastling = true))
                    }
                }
            }
        }
        return moves
    }

    private fun isSquareAttacked(boardState: BoardState, square: Square, color: PieceColor): Boolean {
        val oppositeColor = color.opposite()
        for ((sq, p) in boardState.board) {
            if (p.color == oppositeColor) {
                val pseudoLegalMoves = generatePseudoLegalMoves(boardState, sq, oppositeColor, checkCastling = false)
                if (pseudoLegalMoves.any { it.to == square }) {
                    return true
                }
            }
        }
        return false
    }

    private fun getSlidingMoves(boardState: BoardState, square: Square, color: PieceColor, directions: List<Pair<Int, Int>>): List<Move> {
        val moves = mutableListOf<Move>()
        val piece = boardState.getPiece(square)!!
        for (dir in directions) {
            var currFile = square.file + dir.first
            var currRank = square.rank + dir.second
            while (isValidSquare(Square(currFile, currRank))) {
                val targetSquare = Square(currFile, currRank)
                val targetPiece = boardState.getPiece(targetSquare)
                if (targetPiece == null) {
                    moves.add(Move(piece, square, targetSquare))
                } else {
                    if (targetPiece.color != color) {
                        moves.add(Move(piece, square, targetSquare, isCapture = true))
                    }
                    break 
                }
                currFile += dir.first
                currRank += dir.second
            }
        }
        return moves
    }

    private fun isValidSquare(square: Square): Boolean = square.file in 0..7 && square.rank in 0..7

    private fun isInsufficientMaterial(boardState: BoardState): Boolean {
        val pieces = boardState.board.values.toList()
        if (pieces.size == 2) return true // Only Kings
        if (pieces.size == 3) {
            // King and Bishop or King and Knight
            return pieces.any { it.type == PieceType.BISHOP || it.type == PieceType.KNIGHT }
        }
        // Simplified insufficient material check.
        return false
    }
}
