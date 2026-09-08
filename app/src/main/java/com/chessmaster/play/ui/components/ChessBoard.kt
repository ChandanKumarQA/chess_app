package com.chessmaster.play.ui.components

import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.border
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.chessmaster.play.model.BoardState
import com.chessmaster.play.model.Move
import com.chessmaster.play.model.PieceColor
import com.chessmaster.play.model.Square
import kotlin.math.roundToInt
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Fill

@Composable
fun ChessBoard(
    boardState: BoardState,
    selectedSquare: Square?,
    legalMoves: List<Move>,
    lastMove: Move?,
    isCheck: Boolean,
    currentTurn: PieceColor,
    onSquareClicked: (Square) -> Unit,
    modifier: Modifier = Modifier,
    boardStyleIndex: Int = 0,
    arrows: List<Pair<Square, Square>> = emptyList(),
    hintMove: Move? = null,
    handPointer: Square? = null,
    isFlipped: Boolean = false
) {
    val (lightColor, darkColor) = when (boardStyleIndex) {
        0 -> Pair(Color(0xFFF0D9B5), Color(0xFFB58863)) // Cappuccino
        1 -> Pair(Color(0xFFE4D5B7), Color(0xFF8B5A2B)) // Walnut
        2 -> Pair(Color(0xFFF3F3ED), Color(0xFF6F8F72)) // Classic (Greyish/Green)
        3 -> Pair(Color(0xFFFFFFDD), Color(0xFF86A666)) // Green
        else -> Pair(Color(0xFFF0D9B5), Color(0xFFB58863))
    }
    val selectedColor = Color(0x66000000)
    val legalMoveColor = Color(0x33000000)
    val checkColor = Color(0x88F44336)
    val lastMoveColor = Color(0x88F7F659)

    BoxWithConstraints(modifier = modifier, contentAlignment = Alignment.Center) {
        val boardSize = minOf(maxWidth, maxHeight)
        val squareSize = boardSize / 8
        val squareSizePx = with(LocalDensity.current) { squareSize.toPx() }

        Box(modifier = Modifier.size(boardSize)) {
            // Draw grid
        val rankList = if (isFlipped) (0..7).toList() else (7 downTo 0).toList()
        val fileList = if (isFlipped) (7 downTo 0).toList() else (0..7).toList()
        Column(modifier = Modifier.fillMaxSize()) {
            for (rank in rankList) {
                Row(modifier = Modifier.weight(1f)) {
                    for (file in fileList) {
                        val square = Square(file, rank)
                        val isLight = (file + rank) % 2 != 0
                        val baseColor = if (isLight) lightColor else darkColor
                        
                        val isSelected = square == selectedSquare
                        val isLegalMove = legalMoves.any { it.to == square }
                        val isLastMove = lastMove?.from == square || lastMove?.to == square
                        val piece = boardState.getPiece(square)
                        val isKingInCheck = isCheck && piece?.type == com.chessmaster.play.model.PieceType.KING && piece.color == currentTurn

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .clickable { onSquareClicked(square) }
                        ) {
                            // Background color
                            Box(modifier = Modifier.fillMaxSize().background(baseColor))
                            
                            if (isLastMove) {
                                Box(modifier = Modifier.fillMaxSize().background(lastMoveColor))
                            }
                            if (isSelected) {
                                Box(modifier = Modifier.fillMaxSize().background(selectedColor))
                            }
                            if (isKingInCheck) {
                                Box(modifier = Modifier.fillMaxSize().background(checkColor))
                            }
                            if (isLegalMove) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    val isCapture = piece != null
                                    if (isCapture) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(2.dp)
                                                .clip(CircleShape)
                                                .background(Color.Transparent)
                                                .border(6.dp, legalMoveColor, CircleShape)
                                        )
                                    } else {
                                        Box(
                                            modifier = Modifier
                                                .size(squareSize * 0.3f)
                                                .clip(CircleShape)
                                                .background(legalMoveColor)
                                        )
                                    }
                                }
                            }

                            // Rank Label
                            if (file == if (isFlipped) 7 else 0) {
                                Text(
                                    text = "${rank + 1}",
                                    color = if (isLight) darkColor else lightColor,
                                    fontSize = with(LocalDensity.current) { (squareSizePx * 0.25f).toSp() },
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .align(Alignment.TopStart)
                                        .padding(start = 2.dp, top = 2.dp)
                                )
                            }
                            // File Label
                            if (rank == if (isFlipped) 7 else 0) {
                                Text(
                                    text = "${(file + 'a'.code).toChar()}",
                                    color = if (isLight) darkColor else lightColor,
                                    fontSize = with(LocalDensity.current) { (squareSizePx * 0.25f).toSp() },
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .align(Alignment.BottomEnd)
                                        .padding(end = 2.dp, bottom = 2.dp)
                                )
                            }
                        }
                    }
                }
            }
        } // Close Column

        Canvas(modifier = Modifier.fillMaxSize()) {
                val sqSize = size.width / 8f
                for ((from, to) in arrows) {
                    val fromFile = if (isFlipped) 7 - from.file else from.file
                    val fromRank = if (isFlipped) from.rank else 7 - from.rank
                    val toFile = if (isFlipped) 7 - to.file else to.file
                    val toRank = if (isFlipped) to.rank else 7 - to.rank

                    val startX = fromFile * sqSize + sqSize / 2
                    val startY = fromRank * sqSize + sqSize / 2
                    val endX = toFile * sqSize + sqSize / 2
                    val endY = toRank * sqSize + sqSize / 2
                    
                    val angle = atan2(endY - startY, endX - startX)
                    // Shorten the line so it doesn't overlap the arrowhead tip
                    val arrowHeadLength = sqSize * 0.35f
                    val arrowAngle = Math.PI / 6
                    
                    val lineEndX = endX - arrowHeadLength * cos(angle) * 0.5f
                    val lineEndY = endY - arrowHeadLength * sin(angle) * 0.5f

                    val path = Path().apply {
                        moveTo(startX, startY)
                        lineTo(lineEndX.toFloat(), lineEndY.toFloat())
                    }
                    val arrowColor = Color(0xCC6BB03F) // Chess.com green
                    
                    drawPath(
                        path = path,
                        color = arrowColor,
                        style = Stroke(width = sqSize * 0.15f, cap = StrokeCap.Round, join = StrokeJoin.Round)
                    )
                    
                    val p1X = endX - arrowHeadLength * cos(angle - arrowAngle)
                    val p1Y = endY - arrowHeadLength * sin(angle - arrowAngle)
                    val p2X = endX - arrowHeadLength * cos(angle + arrowAngle)
                    val p2Y = endY - arrowHeadLength * sin(angle + arrowAngle)
                    
                    val headPath = Path().apply {
                        moveTo(endX, endY)
                        lineTo(p1X.toFloat(), p1Y.toFloat())
                        lineTo(p2X.toFloat(), p2Y.toFloat())
                        close()
                    }
                    drawPath(
                        path = headPath,
                        color = arrowColor,
                        style = Fill
                    )
                }
            }

        // Draw pieces absolutely positioned
        boardState.board.forEach { (square, piece) ->
            key(piece.id) {
                // Check if this piece is the hint piece
                val isHintPiece = hintMove != null && square == hintMove.from
                
                var hintPhase by remember { mutableStateOf(0f) }
                LaunchedEffect(isHintPiece) {
                    if (isHintPiece) {
                        while (true) {
                            androidx.compose.animation.core.animate(
                                initialValue = 0f,
                                targetValue = 1f,
                                animationSpec = androidx.compose.animation.core.tween(800, easing = androidx.compose.animation.core.LinearOutSlowInEasing)
                            ) { value, _ -> hintPhase = value }
                            androidx.compose.animation.core.animate(
                                initialValue = 1f,
                                targetValue = 0f,
                                animationSpec = androidx.compose.animation.core.tween(800, easing = androidx.compose.animation.core.FastOutLinearInEasing)
                            ) { value, _ -> hintPhase = value }
                        }
                    } else {
                        hintPhase = 0f
                    }
                }
                
                val displayFile = if (isFlipped) 7 - square.file else square.file
                val displayRank = if (isFlipped) square.rank else 7 - square.rank
                val baseOffset = Offset(displayFile * squareSizePx, displayRank * squareSizePx)
                
                val animatedOffset by animateOffsetAsState(
                    targetValue = baseOffset,
                    animationSpec = spring(stiffness = androidx.compose.animation.core.Spring.StiffnessMediumLow),
                    label = "piece_offset"
                )

                val finalOffset = if (isHintPiece) {
                    val toDisplayFile = if (isFlipped) 7 - hintMove!!.to.file else hintMove!!.to.file
                    val toDisplayRank = if (isFlipped) hintMove!!.to.rank else 7 - hintMove!!.to.rank
                    val toOffset = Offset(toDisplayFile * squareSizePx, toDisplayRank * squareSizePx)
                    Offset(
                        x = baseOffset.x + (toOffset.x - baseOffset.x) * hintPhase,
                        y = baseOffset.y + (toOffset.y - baseOffset.y) * hintPhase
                    )
                } else {
                    animatedOffset
                }

                Box(
                    modifier = Modifier
                        .size(squareSize)
                        .offset { IntOffset(finalOffset.x.roundToInt(), finalOffset.y.roundToInt()) }
                        .padding(4.dp)
                ) {
                    ChessPiece(piece = piece, modifier = Modifier.fillMaxSize())
                }
            }
        }

        // Draw hand pointer
        if (handPointer != null) {
            val handDisplayFile = if (isFlipped) 7 - handPointer.file else handPointer.file
            val handDisplayRank = if (isFlipped) handPointer.rank else 7 - handPointer.rank
            val offset = Offset(handDisplayFile * squareSizePx, handDisplayRank * squareSizePx)
            Box(
                modifier = Modifier
                    .size(squareSize)
                    .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
                    .padding(4.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Default.TouchApp,
                    contentDescription = "Tap here",
                    tint = Color.White,
                    modifier = Modifier.size(36.dp).offset(x = 18.dp, y = 18.dp)
                )
            }
        }
        }
    }
}
