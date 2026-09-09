package com.chessmaster.play.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.chessmaster.play.data.BoardPreferences
import com.chessmaster.play.model.Piece
import com.chessmaster.play.model.PieceColor
import com.chessmaster.play.model.PieceSetId
import com.chessmaster.play.model.PieceType

@Composable
fun ChessPiece(
    piece: Piece,
    modifier: Modifier = Modifier,
    pieceSet: PieceSetId = BoardPreferences.pieceSet.collectAsState().value
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (pieceSet) {
            PieceSetId.LETTER -> LetterPiece(piece)
            PieceSetId.DISGUISED -> DisguisedPiece(piece)
            PieceSetId.SHAPES -> ShapesPiece(piece)
            PieceSetId.WOOD -> SvgPiece(piece, tintColor = if (piece.color == PieceColor.WHITE) Color(0xFFE5C494) else Color(0xFF4A2E1B))
            PieceSetId.NEO -> SvgPiece(piece, tintColor = if (piece.color == PieceColor.WHITE) Color(0xFFF0F6FC) else Color(0xFF1E232A))
            PieceSetId.ANARCANDY -> SvgPiece(piece, tintColor = if (piece.color == PieceColor.WHITE) Color(0xFFFF729F) else Color(0xFF3B1E54))
            PieceSetId.XKCD -> SvgPiece(piece, tintColor = if (piece.color == PieceColor.WHITE) Color(0xFFFDFEFE) else Color(0xFF2B2B2B))
            PieceSetId.CHESSNUT -> SvgPiece(piece, tintColor = if (piece.color == PieceColor.WHITE) Color(0xFFFAF0E6) else Color(0xFF2C241E))
            PieceSetId.CHESS7 -> SvgPiece(piece, tintColor = if (piece.color == PieceColor.WHITE) Color(0xFFFFFFFF) else Color(0xFF101010))
            PieceSetId.SYMMETRIC -> SvgPiece(piece, tintColor = if (piece.color == PieceColor.WHITE) Color(0xFFF8F9FA) else Color(0xFF343A40))
            PieceSetId.RIOHACHA -> SvgPiece(piece, tintColor = if (piece.color == PieceColor.WHITE) Color(0xFFF7F5F0) else Color(0xFF3E3B36))
            else -> SvgPiece(piece) // BURNETT, MERIDA, PIROUETTI, ALPHA, REILLYCRAIG, COMPANION, KOSAL, MONARCHY, COOKE, KIWEN_SUWI, HORSEY
        }
    }
}

/**
 * Standard / SVG Piece renderer using vector assets
 */
@Composable
private fun SvgPiece(
    piece: Piece,
    tintColor: Color? = null
) {
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

    Box(modifier = Modifier.fillMaxSize()) {
        // Drop Shadow layer for depth
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data("file:///android_asset/$assetName")
                .decoderFactory(SvgDecoder.Factory())
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .offset(x = 1.5.dp, y = 3.dp)
                .graphicsLayer { alpha = 0.35f },
            colorFilter = ColorFilter.tint(Color.Black),
            contentScale = ContentScale.Fit
        )

        // Main Piece layer
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data("file:///android_asset/$assetName")
                .decoderFactory(SvgDecoder.Factory())
                .crossfade(true)
                .build(),
            contentDescription = "${piece.color} ${piece.type}",
            modifier = Modifier.fillMaxSize(),
            colorFilter = tintColor?.let { ColorFilter.tint(it) },
            contentScale = ContentScale.Fit
        )
    }
}

/**
 * Letter Piece Set (Screenshot 2)
 * K, Q, R, B, N, • with clean typography
 */
@Composable
private fun LetterPiece(piece: Piece) {
    val letter = when (piece.type) {
        PieceType.KING -> "K"
        PieceType.QUEEN -> "Q"
        PieceType.ROOK -> "R"
        PieceType.BISHOP -> "B"
        PieceType.KNIGHT -> "N"
        PieceType.PAWN -> "•"
    }

    val isWhite = piece.color == PieceColor.WHITE
    val textColor = if (isWhite) Color(0xFFFFFFFF) else Color(0xFF141414)
    val outlineColor = if (isWhite) Color(0xFF1E1E1E) else Color(0xFFF0F0F0)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Shadow / Outline
        Text(
            text = letter,
            fontSize = if (letter == "•") 36.sp else 28.sp,
            fontWeight = FontWeight.Black,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center,
            color = outlineColor.copy(alpha = 0.7f),
            modifier = Modifier.offset(x = 1.dp, y = 1.5.dp)
        )
        // Foreground Text
        Text(
            text = letter,
            fontSize = if (letter == "•") 36.sp else 28.sp,
            fontWeight = FontWeight.Black,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center,
            color = textColor
        )
    }
}

/**
 * Disguised Piece Set (Screenshot 2)
 * Minimalist Zen stones (pure white vs pure black discs)
 */
@Composable
private fun DisguisedPiece(piece: Piece) {
    val isWhite = piece.color == PieceColor.WHITE
    val fillColor = if (isWhite) Color(0xFFFFFFFF) else Color(0xFF151515)
    val borderColor = if (isWhite) Color(0xFFB0B0B0) else Color(0xFF4A4A4A)

    Box(
        modifier = Modifier
            .fillMaxSize(0.72f)
            .clip(CircleShape)
            .background(fillColor)
            .border(2.dp, borderColor, CircleShape)
    )
}

/**
 * Shapes Piece Set (Screenshot 2)
 * Geometric symbols:
 * King = ⨁ (Cross in circle)
 * Queen = ⚙ (Gear / Star)
 * Rook = ✚ (Bold Cross / Tower)
 * Bishop = ⋈ (Angle Cross)
 * Knight = < (Angle)
 * Pawn = ▣ (Square)
 */
@Composable
private fun ShapesPiece(piece: Piece) {
    val isWhite = piece.color == PieceColor.WHITE
    val fillColor = if (isWhite) Color(0xFFFFFFFF) else Color(0xFF1A1A1A)
    val strokeColor = if (isWhite) Color(0xFF1A1A1A) else Color(0xFF888888)

    Canvas(modifier = Modifier.fillMaxSize(0.75f)) {
        val w = size.width
        val h = size.height
        val cx = w / 2f
        val cy = h / 2f

        when (piece.type) {
            PieceType.KING -> {
                // Cross in circle
                drawCircle(color = fillColor, radius = w * 0.42f)
                drawCircle(color = strokeColor, radius = w * 0.42f, style = Stroke(width = 4f))
                drawLine(strokeColor, Offset(cx, cy - w * 0.35f), Offset(cx, cy + w * 0.35f), strokeWidth = 5f)
                drawLine(strokeColor, Offset(cx - w * 0.35f, cy), Offset(cx + w * 0.35f, cy), strokeWidth = 5f)
            }
            PieceType.QUEEN -> {
                // Gear / Multi-point star
                drawCircle(color = fillColor, radius = w * 0.42f)
                drawCircle(color = strokeColor, radius = w * 0.42f, style = Stroke(width = 4f))
                for (i in 0 until 8) {
                    val angle = (i * Math.PI / 4).toFloat()
                    val dx = (Math.cos(angle.toDouble()) * w * 0.38f).toFloat()
                    val dy = (Math.sin(angle.toDouble()) * h * 0.38f).toFloat()
                    drawLine(strokeColor, Offset(cx, cy), Offset(cx + dx, cy + dy), strokeWidth = 4f)
                }
                drawCircle(color = fillColor, radius = w * 0.18f)
                drawCircle(color = strokeColor, radius = w * 0.18f, style = Stroke(width = 3f))
            }
            PieceType.ROOK -> {
                // Bold Tower Cross
                val path = Path().apply {
                    val r = w * 0.42f
                    val t = w * 0.16f
                    moveTo(cx - t, cy - r)
                    lineTo(cx + t, cy - r)
                    lineTo(cx + t, cy - t)
                    lineTo(cx + r, cy - t)
                    lineTo(cx + r, cy + t)
                    lineTo(cx + t, cy + t)
                    lineTo(cx + t, cy + r)
                    lineTo(cx - t, cy + r)
                    lineTo(cx - t, cy + t)
                    lineTo(cx - r, cy + t)
                    lineTo(cx - r, cy - t)
                    lineTo(cx - t, cy - t)
                    close()
                }
                drawPath(path, color = fillColor)
                drawPath(path, color = strokeColor, style = Stroke(width = 4f))
            }
            PieceType.BISHOP -> {
                // Diagonal hour-glass / angle cross
                val r = w * 0.4f
                val path = Path().apply {
                    moveTo(cx - r, cy - r)
                    lineTo(cx + r, cy + r)
                    lineTo(cx + r, cy - r)
                    lineTo(cx - r, cy + r)
                    close()
                }
                drawPath(path, color = fillColor)
                drawPath(path, color = strokeColor, style = Stroke(width = 4f))
            }
            PieceType.KNIGHT -> {
                // Angle / Chevron bracket <
                val path = Path().apply {
                    moveTo(cx + w * 0.32f, cy - h * 0.38f)
                    lineTo(cx - w * 0.32f, cy)
                    lineTo(cx + w * 0.32f, cy + h * 0.38f)
                    lineTo(cx + w * 0.12f, cy)
                    close()
                }
                drawPath(path, color = fillColor)
                drawPath(path, color = strokeColor, style = Stroke(width = 4f))
            }
            PieceType.PAWN -> {
                // Small square inside square
                val s = w * 0.55f
                val left = cx - s / 2f
                val top = cy - s / 2f
                drawRect(color = fillColor, topLeft = Offset(left, top), size = Size(s, s))
                drawRect(color = strokeColor, topLeft = Offset(left, top), size = Size(s, s), style = Stroke(width = 4f))
                val innerS = s * 0.45f
                val innerLeft = cx - innerS / 2f
                val innerTop = cy - innerS / 2f
                drawRect(color = strokeColor, topLeft = Offset(innerLeft, innerTop), size = Size(innerS, innerS))
            }
        }
    }
}
