package com.chessmaster.play

import com.chessmaster.play.engine.GameEngine
import com.chessmaster.play.engine.NotationParser
import com.chessmaster.play.model.Square
import com.chessmaster.play.model.PieceType
import com.chessmaster.play.model.Move
import org.junit.Test
import org.junit.Assert.assertTrue

class ScratchTest {
    
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
        return "${move.from.toString()}${move.to.toString()}$prom"
    }

    @Test
    fun testPromotionViewModelLogic() {
        val fen = "8/4P3/8/8/8/8/8/4K3 w - - 0 1"
        val targetMoveStr = "e7e8q"
        
        val (boardState, turn) = NotationParser.fenToBoardState(fen)
        val engine = GameEngine()
        
        // 1. User selects e7
        val selectedSquare = Square(4, 6)
        val legalMoves = engine.getLegalMoves(boardState, selectedSquare)
        
        // 2. User clicks e8
        val clickSquare = Square(4, 7)
        val move = legalMoves.find { it.to == clickSquare }
        
        println("Move found: \$move")
        
        if (move != null) {
            val moveStr = moveString(move)
            println("Move string: '\$moveStr'")
            println("Target string: '\$targetMoveStr'")
            println("Is equal: \${moveStr == targetMoveStr}")
            assertTrue(moveStr == targetMoveStr)
        } else {
            println("Move not found!")
            assertTrue(false)
        }
    }
}
