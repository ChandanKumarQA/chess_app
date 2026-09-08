package com.chessmaster.play.engine

import com.chessmaster.play.model.*

object NotationParser {

    fun boardStateToFen(
        boardState: BoardState,
        activeColor: PieceColor,
        fullMoveNumber: Int
    ): String {
        val fenBuilder = StringBuilder()

        // 1. Piece placement
        for (rank in 7 downTo 0) {
            var emptyCount = 0
            for (file in 0..7) {
                val piece = boardState.getPiece(Square(file, rank))
                if (piece == null) {
                    emptyCount++
                } else {
                    if (emptyCount > 0) {
                        fenBuilder.append(emptyCount)
                        emptyCount = 0
                    }
                    fenBuilder.append(getFenChar(piece))
                }
            }
            if (emptyCount > 0) {
                fenBuilder.append(emptyCount)
            }
            if (rank > 0) {
                fenBuilder.append('/')
            }
        }

        // 2. Active color
        fenBuilder.append(' ')
        fenBuilder.append(if (activeColor == PieceColor.WHITE) 'w' else 'b')

        // 3. Castling availability
        fenBuilder.append(' ')
        var castlingStr = ""
        val rights = boardState.castlingRights
        if (!rights.whiteKingMoved) {
            if (!rights.whiteKingsideRookMoved) castlingStr += "K"
            if (!rights.whiteQueensideRookMoved) castlingStr += "Q"
        }
        if (!rights.blackKingMoved) {
            if (!rights.blackKingsideRookMoved) castlingStr += "k"
            if (!rights.blackQueensideRookMoved) castlingStr += "q"
        }
        if (castlingStr.isEmpty()) castlingStr = "-"
        fenBuilder.append(castlingStr)

        // 4. En passant target square
        fenBuilder.append(' ')
        val ep = boardState.enPassantTarget
        if (ep != null) {
            fenBuilder.append(ep.toString())
        } else {
            fenBuilder.append("-")
        }

        // 5. Halfmove clock
        fenBuilder.append(' ')
        fenBuilder.append(boardState.halfMoveClock)

        // 6. Fullmove number
        fenBuilder.append(' ')
        fenBuilder.append(fullMoveNumber)

        return fenBuilder.toString()
    }

    fun fenToBoardState(fen: String): Pair<BoardState, PieceColor> {
        val parts = fen.split(" ")
        if (parts.size < 4) throw IllegalArgumentException("Invalid FEN string")

        val board = mutableMapOf<Square, Piece>()
        val ranks = parts[0].split("/")
        if (ranks.size != 8) throw IllegalArgumentException("Invalid FEN string: placement")

        for (rank in 0..7) {
            val rankStr = ranks[7 - rank]
            var file = 0
            for (char in rankStr) {
                if (char.isDigit()) {
                    file += char.digitToInt()
                } else {
                    board[Square(file, rank)] = getPieceFromFenChar(char)
                    file++
                }
            }
        }

        val activeColor = if (parts[1] == "w") PieceColor.WHITE else PieceColor.BLACK

        val castlingStr = parts[2]
        var rights = CastlingRights(
            whiteKingMoved = true,
            whiteKingsideRookMoved = true,
            whiteQueensideRookMoved = true,
            blackKingMoved = true,
            blackKingsideRookMoved = true,
            blackQueensideRookMoved = true
        )
        if (castlingStr != "-") {
            // If they can castle, the king and corresponding rook haven't moved
            if (castlingStr.contains("K") || castlingStr.contains("Q")) rights = rights.copy(whiteKingMoved = false)
            if (castlingStr.contains("k") || castlingStr.contains("q")) rights = rights.copy(blackKingMoved = false)
            if (castlingStr.contains("K")) rights = rights.copy(whiteKingsideRookMoved = false)
            if (castlingStr.contains("Q")) rights = rights.copy(whiteQueensideRookMoved = false)
            if (castlingStr.contains("k")) rights = rights.copy(blackKingsideRookMoved = false)
            if (castlingStr.contains("q")) rights = rights.copy(blackQueensideRookMoved = false)
        }

        var enPassantTarget: Square? = null
        val epStr = parts[3]
        if (epStr != "-") {
            val file = epStr[0] - 'a'
            val rank = epStr[1] - '1'
            enPassantTarget = Square(file, rank)
        }

        val halfMoveClock = if (parts.size > 4) parts[4].toIntOrNull() ?: 0 else 0
        // fullMoveNumber is not part of BoardState, it's tracked by the number of moves

        return Pair(BoardState(board, rights, enPassantTarget, halfMoveClock), activeColor)
    }

    private fun getFenChar(piece: Piece): Char {
        val char = when (piece.type) {
            PieceType.PAWN -> 'p'
            PieceType.KNIGHT -> 'n'
            PieceType.BISHOP -> 'b'
            PieceType.ROOK -> 'r'
            PieceType.QUEEN -> 'q'
            PieceType.KING -> 'k'
        }
        return if (piece.color == PieceColor.WHITE) char.uppercaseChar() else char
    }

    private fun getPieceFromFenChar(char: Char): Piece {
        val color = if (char.isUpperCase()) PieceColor.WHITE else PieceColor.BLACK
        val type = when (char.lowercaseChar()) {
            'p' -> PieceType.PAWN
            'n' -> PieceType.KNIGHT
            'b' -> PieceType.BISHOP
            'r' -> PieceType.ROOK
            'q' -> PieceType.QUEEN
            'k' -> PieceType.KING
            else -> throw IllegalArgumentException("Invalid FEN character: \$char")
        }
        return Piece(type, color)
    }
}
