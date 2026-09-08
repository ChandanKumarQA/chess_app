package com.chessmaster.play.model

enum class PieceColor {
    WHITE, BLACK;
    fun opposite(): PieceColor = if (this == WHITE) BLACK else WHITE
}

enum class GameMode {
    PVP, PVCPU, ONLINE
}



enum class PieceType {
    PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING
}

data class Piece(val type: PieceType, val color: PieceColor) {
    val id: String = java.util.UUID.randomUUID().toString()
}

data class Square(val file: Int, val rank: Int) { // file 0..7 (a-h), rank 0..7 (1-8)
    override fun toString(): String {
        val f = ('a' + file).toString()
        val r = (rank + 1).toString()
        return "$f$r"
    }
}

data class Move(
    val piece: Piece,
    val from: Square,
    val to: Square,
    val isCapture: Boolean = false,
    val isEnPassant: Boolean = false,
    val isCastling: Boolean = false,
    val isPawnDoubleMove: Boolean = false,
    val promotionTo: PieceType? = null
)

data class CastlingRights(
    val whiteKingMoved: Boolean = false,
    val whiteKingsideRookMoved: Boolean = false,
    val whiteQueensideRookMoved: Boolean = false,
    val blackKingMoved: Boolean = false,
    val blackKingsideRookMoved: Boolean = false,
    val blackQueensideRookMoved: Boolean = false
) {
    fun update(move: Move): CastlingRights {
        var newRights = this
        if (move.piece.type == PieceType.KING) {
            if (move.piece.color == PieceColor.WHITE) newRights = newRights.copy(whiteKingMoved = true)
            else newRights = newRights.copy(blackKingMoved = true)
        } else if (move.piece.type == PieceType.ROOK) {
            if (move.from == Square(7, 0)) newRights = newRights.copy(whiteKingsideRookMoved = true)
            if (move.from == Square(0, 0)) newRights = newRights.copy(whiteQueensideRookMoved = true)
            if (move.from == Square(7, 7)) newRights = newRights.copy(blackKingsideRookMoved = true)
            if (move.from == Square(0, 7)) newRights = newRights.copy(blackQueensideRookMoved = true)
        }
        // If a rook is captured
        if (move.to == Square(7, 0)) newRights = newRights.copy(whiteKingsideRookMoved = true)
        if (move.to == Square(0, 0)) newRights = newRights.copy(whiteQueensideRookMoved = true)
        if (move.to == Square(7, 7)) newRights = newRights.copy(blackKingsideRookMoved = true)
        if (move.to == Square(0, 7)) newRights = newRights.copy(blackQueensideRookMoved = true)
        return newRights
    }
}

class BoardState(
    val board: Map<Square, Piece> = emptyMap(),
    val castlingRights: CastlingRights = CastlingRights(),
    val enPassantTarget: Square? = null,
    val halfMoveClock: Int = 0 // For 50-move rule
) {
    fun getPiece(square: Square): Piece? = board[square]
    
    fun copyWithMove(move: Move): BoardState {
        val newBoard = board.toMutableMap()
        newBoard.remove(move.from)
        newBoard[move.to] = move.promotionTo?.let { Piece(it, move.piece.color) } ?: move.piece
        
        var nextEnPassantTarget: Square? = null
        var nextHalfMoveClock = halfMoveClock + 1

        if (move.piece.type == PieceType.PAWN || move.isCapture) {
            nextHalfMoveClock = 0
        }

        if (move.isPawnDoubleMove) {
            val direction = if (move.piece.color == PieceColor.WHITE) 1 else -1
            nextEnPassantTarget = Square(move.from.file, move.from.rank + direction)
        } else if (move.isEnPassant) {
            val capturedPawnSquare = Square(move.to.file, move.from.rank)
            newBoard.remove(capturedPawnSquare)
        } else if (move.isCastling) {
            val isKingside = move.to.file == 6
            val rookFileFrom = if (isKingside) 7 else 0
            val rookFileTo = if (isKingside) 5 else 3
            val rookSquareFrom = Square(rookFileFrom, move.from.rank)
            val rookSquareTo = Square(rookFileTo, move.from.rank)
            val rook = newBoard.remove(rookSquareFrom)
            if (rook != null) {
                newBoard[rookSquareTo] = rook
            }
        }
        
        return BoardState(
            board = newBoard,
            castlingRights = castlingRights.update(move),
            enPassantTarget = nextEnPassantTarget,
            halfMoveClock = nextHalfMoveClock
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BoardState) return false
        return board == other.board &&
               castlingRights == other.castlingRights &&
               enPassantTarget == other.enPassantTarget
    }

    override fun hashCode(): Int {
        var result = board.hashCode()
        result = 31 * result + castlingRights.hashCode()
        result = 31 * result + (enPassantTarget?.hashCode() ?: 0)
        return result
    }

    companion object {
        fun initial(): BoardState {
            val board = mutableMapOf<Square, Piece>()
            for (i in 0..7) {
                board[Square(i, 1)] = Piece(PieceType.PAWN, PieceColor.WHITE)
                board[Square(i, 6)] = Piece(PieceType.PAWN, PieceColor.BLACK)
            }
            val backRank = listOf(
                PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN,
                PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK
            )
            for (i in 0..7) {
                board[Square(i, 0)] = Piece(backRank[i], PieceColor.WHITE)
                board[Square(i, 7)] = Piece(backRank[i], PieceColor.BLACK)
            }
            return BoardState(board)
        }
    }
}

enum class TimeControl(val timeMinutes: Int, val incrementSeconds: Int) {
    BULLET(1, 0),
    BLITZ(3, 2),
    RAPID(10, 5),
    CLASSICAL(30, 0),
    UNLIMITED(0, 0),
    MIN_3(3, 0),
    MIN_5(5, 0),
    MIN_10(10, 0),
    MIN_15(15, 0)
}

data class ChessClock(
    val timeControl: TimeControl = TimeControl.UNLIMITED,
    val whiteTimeMs: Long = timeControl.timeMinutes * 60 * 1000L,
    val blackTimeMs: Long = timeControl.timeMinutes * 60 * 1000L,
    val isWhiteTurn: Boolean = true,
    val lastMoveTimestamp: Long? = null
)
