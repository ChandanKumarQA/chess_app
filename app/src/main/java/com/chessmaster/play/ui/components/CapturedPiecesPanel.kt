package com.chessmaster.play.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chessmaster.play.model.Piece
import com.chessmaster.play.model.PieceColor
import com.chessmaster.play.model.PieceType

@Composable
fun CapturedPiecesPanel(color: PieceColor, pieces: List<PieceType>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "${color.name} Captured", 
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Row(
            modifier = Modifier
                .padding(top = 4.dp)
                .background(
                    if (color == PieceColor.BLACK) 
                        Color.White.copy(alpha = 0.2f) 
                    else 
                        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f), 
                    RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 6.dp, vertical = 4.dp), 
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (pieces.isEmpty()) {
                Spacer(modifier = Modifier.size(24.dp))
            } else {
                pieces.forEach { type ->
                    Box(modifier = Modifier.size(24.dp)) {
                        ChessPiece(piece = Piece(type, color))
                    }
                }
            }
        }
    }
}
