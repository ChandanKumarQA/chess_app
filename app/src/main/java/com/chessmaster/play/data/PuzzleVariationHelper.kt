package com.chessmaster.play.data

import com.chessmaster.play.engine.GameEngine
import com.chessmaster.play.engine.NotationParser
import com.chessmaster.play.model.*
import kotlin.math.abs

object PuzzleVariationHelper {

    private val engine = GameEngine()

    // Memoization cache: (baseFen + movesKey) -> List<String> of unique valid FENs
    private val variationsCache = mutableMapOf<String, List<String>>()

    private data class PiecePlacement(val square: Square, val piece: Piece)

    // Pre-defined safe spectator configurations:
    // Pawns placed on ranks 3, 4 for White and ranks 6, 5 for Black across files 0..7.
    // Pawns on ranks 3..6 never promote, cannot give check to rank 1 or rank 8, and are far from back ranks.
    private val candidateConfigurations: List<List<PiecePlacement>> = buildList {
        val wp = Piece(PieceType.PAWN, PieceColor.WHITE)
        val bp = Piece(PieceType.PAWN, PieceColor.BLACK)

        // Single White pawns (ranks 3, 4: 0-based 2, 3)
        val whiteSingles = (0..7).flatMap { f ->
            listOf(Square(f, 2), Square(f, 3))
        }

        // Single Black pawns (ranks 6, 5: 0-based 5, 4)
        val blackSingles = (0..7).flatMap { f ->
            listOf(Square(f, 5), Square(f, 4))
        }

        for (ws in whiteSingles) {
            add(listOf(PiecePlacement(ws, wp)))
        }
        for (bs in blackSingles) {
            add(listOf(PiecePlacement(bs, bp)))
        }

        // Paired pawns (White + Black)
        for (ws in whiteSingles) {
            for (bs in blackSingles) {
                if (ws.file != bs.file || ws.rank < bs.rank) {
                    add(listOf(PiecePlacement(ws, wp), PiecePlacement(bs, bp)))
                }
            }
        }
    }

    /**
     * Returns a list of up to [count] unique valid FENs for the given [baseFen] and [solutionMoves].
     * Index 0 is always the [baseFen] itself.
     */
    fun getUniqueVariations(baseFen: String, solutionMoves: List<String>, count: Int = 40): List<String> {
        val cacheKey = baseFen + ":" + solutionMoves.joinToString(",")
        variationsCache[cacheKey]?.let { cached ->
            if (cached.size >= count) return cached
        }

        val result = mutableListOf(baseFen)
        val (initialBoard, activeColor) = NotationParser.fenToBoardState(baseFen)

        // Identify occupied squares
        val occupiedSquares = initialBoard.board.keys.toSet()

        // Identify solution move squares and straight/diagonal paths
        val solutionSquares = mutableSetOf<Square>()
        for (m in solutionMoves) {
            val from = Square(m[0] - 'a', m[1] - '1')
            val to = Square(m[2] - 'a', m[3] - '1')
            solutionSquares.add(from)
            solutionSquares.add(to)

            // If move is on a straight line or exact diagonal, mark intermediate squares
            val df = to.file - from.file
            val dr = to.rank - from.rank
            if (df == 0 || dr == 0 || abs(df) == abs(dr)) {
                val stepF = df.coerceIn(-1, 1)
                val stepR = dr.coerceIn(-1, 1)
                var curF = from.file + stepF
                var curR = from.rank + stepR
                while (curF != to.file || curR != to.rank) {
                    solutionSquares.add(Square(curF, curR))
                    curF += stepF
                    curR += stepR
                }
            }
        }

        // Find Kings
        val kings = initialBoard.board.filter { it.value.type == PieceType.KING }

        for (config in candidateConfigurations) {
            if (result.size >= count) break

            // Quick check 1: Are candidate squares occupied or in solution?
            if (config.any { it.square in occupiedSquares || it.square in solutionSquares }) {
                continue
            }

            // Quick check 2: King proximity / check
            var violatesKing = false
            for (p in config) {
                for ((kSq, kPiece) in kings) {
                    // Do not place adjacent to either king
                    if (abs(p.square.file - kSq.file) <= 1 && abs(p.square.rank - kSq.rank) <= 1) {
                        violatesKing = true
                        break
                    }
                    // Check pawn attack on opponent King
                    if (p.piece.color != kPiece.color) {
                        val attackRank = if (p.piece.color == PieceColor.WHITE) p.square.rank + 1 else p.square.rank - 1
                        if (kSq.rank == attackRank && (kSq.file == p.square.file - 1 || kSq.file == p.square.file + 1)) {
                            violatesKing = true
                            break
                        }
                    }
                }
                if (violatesKing) break
            }
            if (violatesKing) continue

            // Board with spectator config
            val newBoardMap = initialBoard.board.toMutableMap()
            for (p in config) {
                newBoardMap[p.square] = p.piece
            }
            val newBoard = BoardState(board = newBoardMap, castlingRights = initialBoard.castlingRights)

            if (verifyMoves(newBoard, solutionMoves)) {
                val newFen = NotationParser.boardStateToFen(newBoard, activeColor, 1)
                if (newFen !in result) {
                    result.add(newFen)
                }
            }
        }

        variationsCache[cacheKey] = result
        return result
    }

    /**
     * Gets the [varIndex]-th variation for a base puzzle.
     * varIndex == 0 returns the baseFen.
     */
    fun getVariation(baseFen: String, solutionMoves: List<String>, varIndex: Int): String {
        if (varIndex <= 0) return baseFen
        val variations = getUniqueVariations(baseFen, solutionMoves, varIndex + 1)
        return if (varIndex < variations.size) {
            variations[varIndex]
        } else {
            // Fallback: cycle modulo available variations (offset by 1 to skip base if possible)
            if (variations.size > 1) {
                variations[1 + ((varIndex - 1) % (variations.size - 1))]
            } else {
                baseFen
            }
        }
    }

    private fun verifyMoves(initialBoard: BoardState, solutionMoves: List<String>): Boolean {
        var current = initialBoard
        for (m in solutionMoves) {
            val from = Square(m[0] - 'a', m[1] - '1')
            val to = Square(m[2] - 'a', m[3] - '1')
            val legals = engine.getLegalMoves(current, from)
            val found = legals.find { it.to == to } ?: return false
            current = current.copyWithMove(found)
        }
        return true
    }
}
