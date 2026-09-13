#!/usr/bin/env python3
import chess
import os

OUTPUT_DIR = "app/src/main/java/com/chessmaster/play/data"

def write_tactical_kt(class_name, theme_name, puzzles):
    assert len(puzzles) == 100, f"{class_name} has {len(puzzles)} puzzles, expected 100"
    fens = [p[0] for p in puzzles]
    moves = [tuple(p[1]) for p in puzzles]
    assert len(set(fens)) == 100, f"{class_name} has duplicate FENs: {len(set(fens))}"
    assert len(set(moves)) == 100, f"{class_name} has duplicate moves: {len(set(moves))}"

    lines = [
        "package com.chessmaster.play.data",
        "",
        "import com.chessmaster.play.model.Puzzle",
        "",
        f"object {class_name} {{",
        "",
        "    private data class RawTacticalPuzzle(",
        "        val fen: String,",
        "        val solutionMoves: List<String>,",
        "        val motif: String",
        "    )",
        "",
        "    private val pool = listOf("
    ]

    for i, (fen, m_list, motif) in enumerate(puzzles):
        comma = "," if i < len(puzzles) - 1 else ""
        escaped_motif = motif.replace('"', '\\"')
        moves_str = ", ".join(f'"{m}"' for m in m_list)
        lines.append(f'        RawTacticalPuzzle("{fen}", listOf({moves_str}), "{escaped_motif}"){comma}')

    lines.extend([
        "    )",
        "",
        "    private var cachedPuzzles: List<Puzzle>? = null",
        "",
        "    fun getPuzzles(): List<Puzzle> {",
        "        cachedPuzzles?.let { return it }",
        "        val puzzles = (1..100).map { i ->",
        "            val base = pool[i - 1]",
        "            val tierRating = when {",
        "                i <= 35 -> 650 + (i * 10)",
        "                i <= 70 -> 1100 + ((i - 35) * 15)",
        "                else -> 1700 + ((i - 70) * 20)",
        "            }",
        f'            val cleanTheme = "{theme_name}"',
        '            val idPrefix = cleanTheme.lowercase().replace(" ", "_")',
        "            Puzzle(",
        f'                id = "tac_${{idPrefix}}_${{i}}",',
        "                fen = base.fen,",
        "                solutionMoves = base.solutionMoves,",
        "                rating = tierRating,",
        '                theme = "$cleanTheme - ${base.motif}",',
        "                xpReward = 15 + (i / 10),",
        "                coinsReward = 8 + (i / 20)",
        "            )",
        "        }",
        "        cachedPuzzles = puzzles",
        "        return puzzles",
        "    }",
        "}",
        ""
    ])

    file_path = os.path.join(OUTPUT_DIR, f"{class_name}.kt")
    with open(file_path, "w") as f:
        f.write("\n".join(lines))
    print(f"[{class_name}] Verified & Written! 100 distinct puzzles, 100 unique moves & 100 unique FENs.")

def verify_and_add(b, moves, motif, seen_moves, seen_fens, puzzles):
    move_tuple = tuple(moves)
    if move_tuple in seen_moves:
        return False
    try:
        test_b = b.copy()
        if test_b.status() & chess.STATUS_OPPOSITE_CHECK:
            return False
        for m_str in moves:
            m = chess.Move.from_uci(m_str)
            if m not in test_b.legal_moves:
                return False
            test_b.push(m)
        fen = b.fen()
        if fen in seen_fens:
            return False
        seen_moves.add(move_tuple)
        seen_fens.add(fen)
        puzzles.append((fen, moves, motif))
        return True
    except:
        return False

# ==============================================================================
# 2. PIN DATABASE (Bishop, Rook, Queen, Pawn)
# ==============================================================================
def gen_pins():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    # 1. Bishop diagonal pins against King
    bishop_pins = [
        # (b_st, b_dst, pin_sq, k_sq, blk_reply, cap_sq, pin_piece, label)
        ("f1", "b5", "c6", "e8", "a7a6", "c6", chess.KNIGHT, "Ruy Lopez Bishop Pin on c6 Knight"),
        ("a4", "b5", "c6", "e8", "d7d6", "c6", chess.KNIGHT, "Bishop Pin on c6 against King e8"),
        ("d3", "b5", "c6", "e8", "h7h6", "c6", chess.KNIGHT, "Bishop Flank Pin on c6 Knight"),
        ("e2", "b5", "c6", "e8", "g7g6", "c6", chess.KNIGHT, "Bishop Developing Pin on c6"),
        ("c1", "g5", "f6", "e8", "h7h6", "f6", chess.KNIGHT, "Classical Bishop Pin on f6 Knight"),
        ("d2", "g5", "f6", "e8", "a7a6", "f6", chess.KNIGHT, "Bishop Pin on f6 against King e8"),
        ("e3", "g5", "f6", "e8", "d7d6", "f6", chess.KNIGHT, "Bishop Outpost Pin on f6 Knight"),
        ("f4", "g5", "f6", "e8", "c7c6", "f6", chess.KNIGHT, "Bishop Tactical Pin on f6 Knight"),
        ("b1", "a3", "c5", "e7", "d7d6", "c5", chess.KNIGHT, "Bishop Wing Pin on c5 against King e7"),
        ("c1", "a3", "d6", "f8", "g7g6", "d6", chess.KNIGHT, "Bishop Pin on d6 against King f8"),
        ("d2", "b4", "d6", "e7", "a7a6", "d6", chess.KNIGHT, "Bishop Flank Pin on d6 Knight"),
        ("f1", "c4", "e6", "g8", "h7h6", "e6", chess.KNIGHT, "Italian Diagonal Pin on e6 Knight"),
        ("e1", "g4", "e6", "d7", "c7c6", "e6", chess.KNIGHT, "Bishop Diagonal Pin on e6"),
        ("g1", "h3", "f5", "d7", "a7a6", "f5", chess.KNIGHT, "Bishop Wing Pin on f5 against King d7"),
        ("h2", "f4", "e5", "c7", "b7b6", "e5", chess.KNIGHT, "Bishop Long Pin on e5 against King c7"),
        ("b2", "a3", "c5", "e7", "h7h6", "c5", chess.BISHOP, "Bishop Long Pin on c5 Bishop"),
        ("c1", "f4", "e5", "g7", "h7h6", "e5", chess.BISHOP, "Bishop Diagonal Pin on e5 Bishop"),
        ("d2", "e3", "f4", "h6", "g7g5", "f4", chess.BISHOP, "Bishop Pin on f4 Bishop"),
        ("g2", "h3", "g4", "e6", "d7d6", "g4", chess.BISHOP, "Bishop Flank Pin on g4 Bishop"),
        ("f1", "e2", "d3", "b5", "a7a6", "d3", chess.BISHOP, "Bishop Reverse Pin on d3"),
        ("e2", "f3", "e4", "g6", "h7h6", "e4", chess.KNIGHT, "Bishop Central Pin on e4 Knight"),
        ("d1", "e2", "d3", "b5", "c7c6", "d3", chess.KNIGHT, "Bishop Defensive Pin on d3"),
        ("a3", "b4", "c5", "d6", "e7e6", "c5", chess.KNIGHT, "Bishop Outpost Pin on c5"),
        ("h3", "g4", "f5", "e6", "d7d6", "f5", chess.KNIGHT, "Bishop Outpost Pin on f5"),
        ("b3", "a4", "b5", "c6", "d7d6", "b5", chess.KNIGHT, "Bishop Edge Pin on b5"),
    ]

    for (b_st, b_dst, pin_sq, k_sq, blk_reply, cap_sq, pin_piece, label) in bishop_pins:
        b = chess.Board(None)
        b.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.parse_square(pin_sq), chess.Piece(pin_piece, chess.BLACK))
        b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        b.turn = chess.WHITE
        moves = [f"{b_st}{b_dst}", blk_reply, f"{b_dst}{cap_sq}"]
        verify_and_add(b, moves, label, seen_moves, seen_fens, puzzles)

    # 2. Rook vertical & horizontal pins
    rook_pins = [
        # (r_st, r_dst, pin_sq, k_sq, blk_reply, cap_sq, pin_piece, label)
        ("a1", "e1", "e7", "e8", "d7d6", "e7", chess.QUEEN, "Central File Pin on Queen e7"),
        ("b1", "e1", "e6", "e8", "c7c6", "e6", chess.QUEEN, "Rook File Pin on Queen e6"),
        ("c1", "e1", "e5", "e8", "a7a6", "e5", chess.QUEEN, "Rook Pin on Central Queen e5"),
        ("f1", "e1", "e4", "e8", "h7h6", "e4", chess.QUEEN, "Rook File Pin on Queen e4"),
        ("g1", "e1", "e3", "e8", "b7b6", "e3", chess.QUEEN, "Deep Rook File Pin on Queen e3"),
        ("a1", "d1", "d7", "d8", "a7a6", "d7", chess.QUEEN, "Queen File Pin on d7 Queen"),
        ("b1", "d1", "d6", "d8", "c7c6", "d6", chess.QUEEN, "Rook D-File Pin on Queen d6"),
        ("c1", "d1", "d5", "d8", "e7e6", "d5", chess.QUEEN, "Central D-File Pin on Queen d5"),
        ("e1", "d1", "d4", "d8", "f7f6", "d4", chess.QUEEN, "Rook Infiltration Pin on d4 Queen"),
        ("f1", "d1", "d3", "d8", "g7g6", "d3", chess.QUEEN, "Rook Pin on d3 Queen"),
        ("a1", "c1", "c7", "c8", "d7d6", "c7", chess.QUEEN, "Rook C-File Pin on Queen c7"),
        ("b1", "c1", "c6", "c8", "e7e6", "c6", chess.QUEEN, "Rook C-File Pin on Queen c6"),
        ("d1", "c1", "c5", "c8", "a7a6", "c5", chess.QUEEN, "Rook C-File Pin on Queen c5"),
        ("e1", "c1", "c4", "c8", "f7f6", "c4", chess.QUEEN, "Rook Pressure Pin on c4 Queen"),
        ("a1", "f1", "f7", "f8", "e7e6", "f7", chess.QUEEN, "Rook F-File Pin on Queen f7"),
        ("b1", "f1", "f6", "f8", "d7d6", "f6", chess.QUEEN, "Rook F-File Pin on Queen f6"),
        ("c1", "f1", "f5", "f8", "c7c6", "f5", chess.QUEEN, "Rook Attack Pin on f5 Queen"),
        ("d1", "f1", "f4", "f8", "a7a6", "f4", chess.QUEEN, "Rook Kingside Pin on f4 Queen"),
        # Rook pins on minor pieces
        ("a1", "e1", "e7", "e8", "d7d6", "e7", chess.BISHOP, "Rook Pin on e7 Bishop"),
        ("b1", "e1", "e6", "e8", "c7c6", "e6", chess.BISHOP, "Rook Pin on e6 Bishop"),
        ("c1", "e1", "e5", "e8", "a7a6", "e5", chess.BISHOP, "Rook Pin on e5 Bishop"),
        ("f1", "e1", "e4", "e8", "h7h6", "e4", chess.BISHOP, "Rook Pin on e4 Bishop"),
        ("a1", "d1", "d7", "d8", "a7a6", "d7", chess.BISHOP, "Rook Pin on d7 Bishop"),
        ("b1", "d1", "d6", "d8", "c7c6", "d6", chess.BISHOP, "Rook Pin on d6 Bishop"),
        ("c1", "d1", "d5", "d8", "e7e6", "d5", chess.BISHOP, "Rook Pin on d5 Bishop"),
        ("a1", "c1", "c7", "c8", "d7d6", "c7", chess.BISHOP, "Rook Pin on c7 Bishop"),
        ("b1", "c1", "c6", "c8", "e7e6", "c6", chess.BISHOP, "Rook Pin on c6 Bishop"),
        ("a1", "e1", "e7", "e8", "d7d6", "e7", chess.KNIGHT, "Rook Pin on e7 Knight"),
        ("b1", "e1", "e6", "e8", "c7c6", "e6", chess.KNIGHT, "Rook Pin on e6 Knight"),
        ("c1", "e1", "e5", "e8", "a7a6", "e5", chess.KNIGHT, "Rook Pin on e5 Knight"),
        ("f1", "e1", "e4", "e8", "h7h6", "e4", chess.KNIGHT, "Rook Pin on e4 Knight"),
        ("a1", "d1", "d7", "d8", "a7a6", "d7", chess.KNIGHT, "Rook Pin on d7 Knight"),
        ("b1", "d1", "d6", "d8", "c7c6", "d6", chess.KNIGHT, "Rook Pin on d6 Knight"),
        ("c1", "d1", "d5", "d8", "e7e6", "d5", chess.KNIGHT, "Rook Pin on d5 Knight"),
    ]

    for (r_st, r_dst, pin_sq, k_sq, blk_reply, cap_sq, pin_piece, label) in rook_pins:
        b = chess.Board(None)
        b.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.parse_square(pin_sq), chess.Piece(pin_piece, chess.BLACK))
        b.set_piece_at(chess.parse_square(r_st), chess.Piece(chess.ROOK, chess.WHITE))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        b.turn = chess.WHITE
        moves = [f"{r_st}{r_dst}", blk_reply, f"{r_dst}{cap_sq}"]
        verify_and_add(b, moves, label, seen_moves, seen_fens, puzzles)

    # 3. Queen pins
    queen_pins = [
        ("d1", "e2", "e7", "e8", "a7a6", "e7", chess.BISHOP, "Queen File Pin on e7 Bishop"),
        ("d1", "e2", "e6", "e8", "b7b6", "e6", chess.KNIGHT, "Queen File Pin on e6 Knight"),
        ("c1", "d2", "d7", "d8", "c7c6", "d7", chess.BISHOP, "Queen File Pin on d7 Bishop"),
        ("c1", "d2", "d6", "d8", "e7e6", "d6", chess.KNIGHT, "Queen File Pin on d6 Knight"),
        ("b1", "c2", "c7", "c8", "d7d6", "c7", chess.BISHOP, "Queen File Pin on c7 Bishop"),
        ("b1", "c2", "c6", "c8", "a7a6", "c6", chess.KNIGHT, "Queen File Pin on c6 Knight"),
        ("e1", "f2", "f7", "f8", "g7g6", "f7", chess.BISHOP, "Queen File Pin on f7 Bishop"),
        ("e1", "f2", "f6", "f8", "e7e6", "f6", chess.KNIGHT, "Queen File Pin on f6 Knight"),
        ("d1", "a4", "c6", "e8", "d7d6", "c6", chess.KNIGHT, "Queen Diagonal Pin on c6 Knight"),
        ("d1", "h5", "f7", "e8", "d7d6", "f7", chess.KNIGHT, "Queen Kingside Pin on f7 Knight"),
        ("c1", "a3", "d6", "e7", "a7a6", "d6", chess.KNIGHT, "Queen Diagonal Pin on d6 Knight"),
        ("e1", "h4", "f6", "g7", "h7h6", "f6", chess.KNIGHT, "Queen Diagonal Pin on f6 Knight"),
    ]

    for (q_st, q_dst, pin_sq, k_sq, blk_reply, cap_sq, pin_piece, label) in queen_pins:
        b = chess.Board(None)
        b.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.parse_square(pin_sq), chess.Piece(pin_piece, chess.BLACK))
        b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        b.turn = chess.WHITE
        moves = [f"{q_st}{q_dst}", blk_reply, f"{q_dst}{cap_sq}"]
        verify_and_add(b, moves, label, seen_moves, seen_fens, puzzles)

    # 4. Exploiting Pinned Pieces with Pawns
    pawn_pin_exploits = [
        ("d4", "d5", "c6", "e8", "b5", chess.BISHOP, "a7a6", "d5c6", chess.KNIGHT, "Pawn Attack on Pinned c6 Knight"),
        ("e4", "e5", "f6", "e8", "g5", chess.BISHOP, "h7h6", "e5f6", chess.KNIGHT, "Pawn Advance on Pinned f6 Knight"),
        ("c4", "c5", "b6", "c7", "a4", chess.BISHOP, "a7a6", "c5b6", chess.KNIGHT, "Pawn Advance on Pinned b6 Knight"),
        ("f4", "f5", "e6", "d7", "h3", chess.BISHOP, "h7h6", "f5e6", chess.KNIGHT, "Pawn Thrust on Pinned e6 Knight"),
        ("d3", "d4", "c5", "e7", "a3", chess.BISHOP, "a7a6", "d4c5", chess.KNIGHT, "Pawn Strike on Pinned c5 Knight"),
        ("e3", "e4", "d5", "f7", "b3", chess.BISHOP, "h7h6", "e4d5", chess.KNIGHT, "Pawn Strike on Pinned d5 Knight"),
        ("c3", "c4", "b5", "c6", "a3", chess.BISHOP, "a7a6", "c4b5", chess.KNIGHT, "Pawn Push on Pinned b5 Knight"),
        ("f3", "f4", "e5", "g7", "h3", chess.BISHOP, "h7h6", "f4e5", chess.KNIGHT, "Pawn Push on Pinned e5 Knight"),
        ("b4", "b5", "a6", "c8", "a4", chess.BISHOP, "h7h6", "b5a6", chess.KNIGHT, "Flank Pawn Push on Pinned a6 Knight"),
        ("g4", "g5", "h6", "f8", "h4", chess.BISHOP, "a7a6", "g5h6", chess.KNIGHT, "Flank Pawn Push on Pinned h6 Knight"),
        ("d5", "d6", "e7", "e8", "e1", chess.ROOK, "a7a6", "d6e7", chess.KNIGHT, "Advanced Pawn on Pinned e7 Knight"),
        ("e5", "e6", "f7", "f8", "f1", chess.ROOK, "h7h6", "e6f7", chess.KNIGHT, "Advanced Pawn on Pinned f7 Knight"),
        ("c5", "c6", "d7", "d8", "d1", chess.ROOK, "b7b6", "c6d7", chess.KNIGHT, "Advanced Pawn on Pinned d7 Knight"),
        ("b5", "b6", "c7", "c8", "c1", chess.ROOK, "a7a6", "b6c7", chess.KNIGHT, "Advanced Pawn on Pinned c7 Knight"),
        ("f5", "f6", "g7", "g8", "g1", chess.ROOK, "h7h6", "f6g7", chess.KNIGHT, "Advanced Pawn on Pinned g7 Knight"),
    ]

    for (p_st, p_dst, pin_sq, k_sq, pin_att_sq, pin_att_piece, blk_reply, cap_move, pin_piece, label) in pawn_pin_exploits:
        b = chess.Board(None)
        b.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.parse_square(pin_sq), chess.Piece(pin_piece, chess.BLACK))
        b.set_piece_at(chess.parse_square(p_st), chess.Piece(chess.PAWN, chess.WHITE))
        b.set_piece_at(chess.parse_square(pin_att_sq), chess.Piece(pin_att_piece, chess.WHITE))
        b.set_piece_at(chess.H1, chess.Piece(chess.KING, chess.WHITE))
        b.turn = chess.WHITE
        moves = [f"{p_st}{p_dst}", blk_reply, cap_move]
        verify_and_add(b, moves, label, seen_moves, seen_fens, puzzles)

    # 5. More Diverse Bishop Pins to ensure 100
    extra_pins = [
        ("b3", "c4", "d5", "g8", "a7a6", "d5", chess.KNIGHT, "Bishop Diagonal Pin on d5"),
        ("c2", "d3", "e4", "h7", "b7b6", "e4", chess.KNIGHT, "Bishop Diagonal Pin on e4"),
        ("a2", "b3", "c4", "f7", "g7g6", "c4", chess.KNIGHT, "Bishop Long Pin on c4"),
        ("g3", "f4", "e5", "b8", "a7a6", "e5", chess.KNIGHT, "Bishop Diagonal Pin on e5"),
        ("f2", "e3", "d4", "a7", "b7b6", "d4", chess.KNIGHT, "Bishop Diagonal Pin on d4"),
        ("h2", "g3", "f4", "c7", "d7d6", "f4", chess.KNIGHT, "Bishop Diagonal Pin on f4"),
        ("e3", "d4", "c5", "a7", "h7h6", "c5", chess.KNIGHT, "Bishop Central Pin on c5"),
        ("d3", "c4", "b5", "a6", "h7h6", "b5", chess.KNIGHT, "Bishop Flank Pin on b5"),
        ("c3", "b4", "a5", "a8", "h7h6", "a5", chess.KNIGHT, "Bishop Edge Pin on a5"),
        ("f3", "g4", "h5", "h8", "a7a6", "h5", chess.KNIGHT, "Bishop Edge Pin on h5"),
    ]

    for (b_st, b_dst, pin_sq, k_sq, blk_reply, cap_sq, pin_piece, label) in extra_pins:
        if len(puzzles) >= 100: break
        b = chess.Board(None)
        b.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.parse_square(pin_sq), chess.Piece(pin_piece, chess.BLACK))
        b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        b.turn = chess.WHITE
        moves = [f"{b_st}{b_dst}", blk_reply, f"{b_dst}{cap_sq}"]
        verify_and_add(b, moves, label, seen_moves, seen_fens, puzzles)

    assert len(puzzles) >= 100, f"Only generated {len(puzzles)} pins"
    return puzzles[:100]

pins = gen_pins()
write_tactical_kt("PinDatabase", "Pin", pins)
