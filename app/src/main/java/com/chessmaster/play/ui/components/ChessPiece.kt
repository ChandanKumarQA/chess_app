package com.chessmaster.play.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.chessmaster.play.model.Piece
import com.chessmaster.play.model.PieceColor
import com.chessmaster.play.model.PieceType

@Composable
fun ChessPiece(piece: Piece, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    
    val assetName = when (piece.color) {
        PieceColor.WHITE -> when (piece.type) {
            PieceType.PAWN -> "pieces/Chess_plt45.svg"
            PieceType.KNIGHT -> "pieces/Chess_nlt45.svg"
            PieceType.BISHOP -> "pieces/Chess_blt45.svg"
            PieceType.ROOK -> "pieces/Chess_rlt45.svg"
            PieceType.QUEEN -> "pieces/Chess_qlt45.svg"
            PieceType.KING -> "pieces/Chess_klt45.svg"
        }
        PieceColor.BLACK -> when (piece.type) {
            PieceType.PAWN -> "pieces/Chess_pdt45.svg"
            PieceType.KNIGHT -> "pieces/Chess_ndt45.svg"
            PieceType.BISHOP -> "pieces/Chess_bdt45.svg"
            PieceType.ROOK -> "pieces/Chess_rdt45.svg"
            PieceType.QUEEN -> "pieces/Chess_qdt45.svg"
            PieceType.KING -> "pieces/Chess_kdt45.svg"
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        // Shadow layer for 3D effect
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data("file:///android_asset/$assetName")
                .decoderFactory(SvgDecoder.Factory())
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .offset(x = 2.dp, y = 4.dp)
                .graphicsLayer { alpha = 0.5f },
            colorFilter = ColorFilter.tint(Color.Black),
            contentScale = ContentScale.Fit
        )
        // Actual piece layer
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data("file:///android_asset/$assetName")
                .decoderFactory(SvgDecoder.Factory())
                .crossfade(true)
                .build(),
            contentDescription = "${piece.color} ${piece.type}",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}
