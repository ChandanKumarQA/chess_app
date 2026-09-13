#!/usr/bin/env python3
import chess
import os

OUTPUT_DIR = "app/src/main/java/com/chessmaster/play/data"

def write_tactical_kt(class_name, theme_name, puzzles):
    assert len(puzzles) == 100, f"{class_name} has {len(puzzles)} puzzles, expected 100"
    fens = [p[0] for p in puzzles]
    moves = [tuple(p[1]) for p in puzzles]
    assert len(set(fens)) == 100, f"{class_name} duplicate FENs: {100 - len(set(fens))}"
    assert len(set(moves)) == 100, f"{class_name} duplicate moves: {100 - len(set(moves))}"

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
    print(f"[{class_name}] 100% Unique - 100 distinct puzzles written!")

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
# 1. FORK DATABASE
# ==============================================================================
def build_fork_database():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    # Knight Forks
    for n_st, dst, k_sq, target_sq, esc_sq, target_piece, label in [
        ("b5", "c7", "e8", "a8", "d8", chess.ROOK, "Knight Fork on c7 winning Rook a8"),
        ("d5", "c7", "e8", "a8", "f8", chess.ROOK, "Central Knight Fork on c7 winning Rook a8"),
        ("a6", "c7", "e8", "a8", "d7", chess.ROOK, "Flank Knight Fork on c7 winning Rook a8"),
        ("e6", "c7", "e8", "a8", "f7", chess.ROOK, "Infiltration Knight Fork on c7 winning Rook a8"),
        ("d5", "f7", "e8", "h8", "d8", chess.ROOK, "Knight Fork on f7 winning Rook h8"),
        ("g5", "f7", "e8", "h8", "d7", chess.ROOK, "Kingside Knight Fork on f7 winning Rook h8"),
        ("e5", "f7", "e8", "h8", "f8", chess.ROOK, "Central Knight Fork on f7 winning Rook h8"),
        ("h6", "f7", "e8", "h8", "e7", chess.ROOK, "Wing Knight Fork on f7 winning Rook h8"),
        ("c4", "d6", "e8", "b7", "f8", chess.ROOK, "Knight Fork on d6 winning Rook b7"),
        ("e4", "d6", "e8", "b7", "d8", chess.ROOK, "Knight Jump to d6 winning Rook b7"),
        ("f5", "d6", "e8", "b7", "f7", chess.ROOK, "Deep Knight Fork on d6 winning Rook b7"),
        ("b5", "d6", "e8", "b7", "d7", chess.ROOK, "Outpost Knight Fork on d6 winning Rook b7"),
        ("f4", "e6", "f8", "c7", "g8", chess.ROOK, "Knight Fork on e6 winning Rook c7"),
        ("d4", "e6", "f8", "c7", "e8", chess.ROOK, "Central Knight Fork on e6 winning Rook c7"),
        ("g5", "e6", "f8", "c7", "e7", chess.ROOK, "Kingside Knight Fork on e6 winning Rook c7"),
        ("c5", "e6", "f8", "c7", "g7", chess.ROOK, "Tactical Knight Fork on e6 winning Rook c7"),
        ("f5", "e7", "g8", "c8", "h8", chess.ROOK, "Knight Fork on e7 winning Rook c8"),
        ("d5", "e7", "g8", "c8", "f7", chess.ROOK, "Knight Outpost Fork on e7 winning Rook c8"),
        ("c6", "e7", "g8", "c8", "h7", chess.ROOK, "Knight Strike on e7 winning Rook c8"),
        ("g6", "e7", "g8", "c8", "f8", chess.ROOK, "Knight Royal Line Fork on e7 winning Rook c8"),
        ("c4", "b6", "c8", "a8", "d8", chess.ROOK, "Knight Fork on b6 winning Rook a8"),
        ("d5", "b6", "c8", "a8", "d7", chess.ROOK, "Knight Fork on b6 winning Corner Rook"),
        ("a4", "b6", "c8", "a8", "c7", chess.ROOK, "Edge Knight Fork on b6 winning Rook a8"),
        ("d7", "b6", "c8", "a8", "b8", chess.ROOK, "Knight Deep Fork on b6 winning Rook a8"),
        ("e4", "f6", "g8", "h7", "f8", chess.ROOK, "Knight Fork on f6 winning Rook h7"),
        ("g4", "f6", "g8", "h7", "h8", chess.ROOK, "Knight Outpost on f6 winning Rook h7"),
        ("d5", "f6", "g8", "h7", "g7", chess.ROOK, "Center Knight Fork on f6 winning Rook h7"),
        ("h5", "f6", "g8", "h7", "f7", chess.ROOK, "Flank Knight Fork on f6 winning Rook h7"),
        ("e6", "c7", "e8", "d5", "f8", chess.QUEEN, "Royal Knight Fork on c7 winning Queen d5"),
        ("a6", "c7", "e8", "d5", "d8", chess.QUEEN, "Knight Fork on c7 winning Queen d5"),
        ("b5", "c7", "e8", "d5", "e7", chess.QUEEN, "Wing Knight Fork on c7 winning Queen d5"),
        ("d5", "f7", "e8", "d8", "f8", chess.QUEEN, "Royal Knight Fork on f7 winning Queen d8"),
        ("g5", "f7", "e8", "d8", "e7", chess.QUEEN, "Knight Fork on f7 winning Queen d8"),
        ("e5", "f7", "e8", "d8", "d7", chess.QUEEN, "Knight Strike on f7 winning Queen d8"),
        ("c4", "d6", "e8", "f7", "f8", chess.QUEEN, "Knight Fork on d6 winning Queen f7"),
        ("b5", "d6", "e8", "f7", "e7", chess.QUEEN, "Knight Fork on d6 winning Queen on f7"),
        ("e4", "d6", "e8", "f7", "d7", chess.QUEEN, "Central Knight Fork on d6 winning Queen f7"),
        ("c6", "e7", "g8", "d5", "h8", chess.QUEEN, "Knight Fork on e7 winning Queen d5"),
        ("g6", "e7", "g8", "d5", "f7", chess.QUEEN, "Kingside Knight Fork on e7 winning Queen d5"),
        ("f5", "e7", "g8", "d5", "g7", chess.QUEEN, "Knight Attack on e7 winning Queen d5"),
        ("d5", "b6", "c8", "d7", "b8", chess.QUEEN, "Knight Fork on b6 winning Queen d7"),
        ("c4", "b6", "c8", "d7", "c7", chess.QUEEN, "Knight Infiltration on b6 winning Queen d7"),
        ("f4", "g6", "f8", "e7", "g8", chess.QUEEN, "Knight Fork on g6 winning Queen e7"),
        ("h4", "g6", "f8", "e7", "e8", chess.QUEEN, "Edge Knight Fork on g6 winning Queen e7"),
        ("e5", "g6", "f8", "e7", "f7", chess.QUEEN, "Center Knight Fork on g6 winning Queen e7"),
        ("a4", "c5", "d7", "e6", "c8", chess.QUEEN, "Knight Fork on c5 winning Queen e6"),
        ("b3", "c5", "d7", "e6", "d8", chess.QUEEN, "Knight Hop to c5 winning Queen e6"),
        ("d3", "c5", "d7", "e6", "c7", chess.QUEEN, "Knight Fork on c5 winning Black Queen"),
        ("e3", "f5", "e7", "d6", "e8", chess.QUEEN, "Knight Fork on f5 winning Queen d6"),
        ("g3", "f5", "e7", "d6", "f7", chess.QUEEN, "Knight Strike on f5 winning Queen d6"),
        ("h4", "f5", "e7", "d6", "d8", chess.QUEEN, "Flank Knight Fork on f5 winning Queen d6"),
        ("b3", "d4", "e6", "c6", "e7", chess.ROOK, "Knight Fork on d4 winning Rook c6"),
        ("f3", "e5", "d7", "c6", "e8", chess.ROOK, "Knight Central Fork on e5 winning Rook c6"),
        ("c3", "e4", "d6", "f6", "e7", chess.ROOK, "Knight Fork on e4 winning Rook f6"),
        ("a3", "b5", "c7", "a7", "c8", chess.ROOK, "Knight Flank Jump to b5 winning Rook a7"),
        ("h3", "g5", "f7", "h7", "f8", chess.ROOK, "Knight Flank Jump to g5 winning Rook h7"),
    ]:
        b = chess.Board(None)
        b.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.parse_square(target_sq), chess.Piece(target_piece, chess.BLACK))
        b.set_piece_at(chess.parse_square(n_st), chess.Piece(chess.KNIGHT, chess.WHITE))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        b.turn = chess.WHITE
        verify_and_add(b, [f"{n_st}{dst}", f"{k_sq}{esc_sq}", f"{dst}{target_sq}"], label, seen_moves, seen_fens, puzzles)

    # Pawn Forks
    for p_st, p_dst, p1_sq, p2_sq, p1_esc, p2_cap, p1_piece, p2_piece, label in [
        ("d4", "d5", "c6", "e6", "c6e7", "d5e6", chess.KNIGHT, chess.KNIGHT, "Central Pawn Fork on c6 and e6 Knights"),
        ("e4", "e5", "d6", "f6", "f6d5", "e5d6", chess.BISHOP, chess.KNIGHT, "Center Pawn Fork on Bishop & Knight"),
        ("c4", "c5", "b6", "d6", "b6c8", "c5d6", chess.KNIGHT, chess.BISHOP, "Queenside Pawn Fork on Knight & Bishop"),
        ("f4", "f5", "e6", "g6", "g6f8", "f5e6", chess.BISHOP, chess.KNIGHT, "Kingside Pawn Fork on Bishop & Knight"),
        ("g4", "g5", "f6", "h6", "f6h5", "g5h6", chess.KNIGHT, chess.BISHOP, "Wing Pawn Fork on f6 Knight & h6 Bishop"),
        ("b4", "b5", "a6", "c6", "a6c7", "b5c6", chess.KNIGHT, chess.BISHOP, "Flank Pawn Fork on a6 Knight & c6 Bishop"),
        ("d5", "d6", "c7", "e7", "c7b5", "d6e7", chess.KNIGHT, chess.BISHOP, "Advanced Pawn Fork on c7 & e7"),
        ("e5", "e6", "d7", "f7", "f7g8", "e6d7", chess.BISHOP, chess.KNIGHT, "Advanced Pawn Fork on d7 & f7"),
        ("c5", "c6", "b7", "d7", "b7a5", "c6d7", chess.KNIGHT, chess.KNIGHT, "Infiltrating Pawn Fork on b7 & d7"),
        ("f5", "f6", "e7", "g7", "e7c6", "f6g7", chess.BISHOP, chess.BISHOP, "Kingside Advanced Pawn Fork on Minor Pieces"),
        ("d3", "d4", "c5", "e5", "c5b6", "d4e5", chess.KNIGHT, chess.KNIGHT, "Pawn Push Fork on c5 & e5 Knights"),
        ("e3", "e4", "d5", "f5", "d5b6", "e4f5", chess.KNIGHT, chess.BISHOP, "Pawn Strike Fork on d5 Knight & f5 Bishop"),
        ("c3", "c4", "b5", "d5", "b5c7", "c4d5", chess.KNIGHT, chess.KNIGHT, "Queenside Pawn Thrust Fork"),
        ("f3", "f4", "e5", "g5", "e5c6", "f4g5", chess.BISHOP, chess.KNIGHT, "Kingside Pawn Thrust Fork"),
        ("b3", "b4", "a5", "c5", "a5b7", "b4c5", chess.KNIGHT, chess.BISHOP, "Flank Pawn Thrust Fork"),
        ("g3", "g4", "f5", "h5", "f5e7", "g4h5", chess.KNIGHT, chess.BISHOP, "G-pawn Thrust Fork on f5 & h5"),
        ("d2", "d4", "c5", "e5", "c5d6", "d4e5", chess.BISHOP, chess.KNIGHT, "Double Step Pawn Fork on c5 & e5"),
        ("e2", "e4", "d5", "f5", "d5c6", "e4f5", chess.BISHOP, chess.BISHOP, "Double Step Center Pawn Fork on d5 & f5"),
        ("c2", "c4", "b5", "d5", "b5a7", "c4d5", chess.KNIGHT, chess.BISHOP, "Double Step Queenside Pawn Fork"),
        ("f2", "f4", "e5", "g5", "e5d6", "f4g5", chess.KNIGHT, chess.KNIGHT, "Double Step Kingside Pawn Fork"),
    ]:
        b = chess.Board(None)
        b.set_piece_at(chess.parse_square(p_st), chess.Piece(chess.PAWN, chess.WHITE))
        b.set_piece_at(chess.parse_square(p1_sq), chess.Piece(p1_piece, chess.BLACK))
        b.set_piece_at(chess.parse_square(p2_sq), chess.Piece(p2_piece, chess.BLACK))
        b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        b.turn = chess.WHITE
        verify_and_add(b, [f"{p_st}{p_dst}", p1_esc, p2_cap], label, seen_moves, seen_fens, puzzles)

    # Queen, Bishop, Rook Forks
    for q_st, q_dst, k_sq, esc_sq, loose_sq, loose_piece, label in [
        ("d1", "a4", "e8", "d8", "c4", chess.BISHOP, "Queen Check Fork on a4 winning Bishop c4"),
        ("d1", "h5", "e8", "d7", "e5", chess.KNIGHT, "Queen Check Fork on h5 winning Knight e5"),
        ("b1", "b5", "e8", "d8", "e5", chess.KNIGHT, "Flank Queen Fork on b5 winning Knight e5"),
        ("c1", "a3", "e7", "e8", "f8", chess.BISHOP, "Diagonal Queen Fork on a3 winning Bishop f8"),
        ("e1", "e5", "d8", "c8", "b5", chess.ROOK, "Central Queen Fork on e5 winning Rook b5"),
        ("d1", "d5", "e8", "f8", "b7", chess.ROOK, "Queen Check on d5 winning Rook b7"),
        ("f1", "c4", "e8", "d7", "g8", chess.KNIGHT, "Queen Fork on c4 winning Knight g8"),
        ("e1", "h4", "e7", "d6", "c4", chess.BISHOP, "Queen Check Fork on h4 winning Bishop c4"),
        ("d1", "a4", "c6", "b7", "e4", chess.KNIGHT, "Queen Angle Fork on a4 winning Knight e4"),
        ("d1", "d7", "f5", "e4", "b7", chess.ROOK, "Queen Check on d7 winning Rook b7"),
        ("e1", "e7", "g5", "h6", "b4", chess.ROOK, "Queen Check on e7 winning Rook b4"),
        ("d1", "b3", "e6", "d7", "g8", chess.BISHOP, "Queen Fork on b3 winning Bishop g8"),
        ("c1", "c6", "e8", "f8", "a8", chess.ROOK, "Queen Invasion Fork on c6 winning Rook a8"),
        ("d1", "d8", "f6", "g5", "b6", chess.ROOK, "Queen Distance Fork on d8 winning Rook b6"),
        ("e1", "e8", "c6", "b7", "g6", chess.ROOK, "Queen Check on e8 winning Rook g6"),
        ("a1", "a4", "d7", "c8", "g4", chess.BISHOP, "Queen Flank Fork on a4 winning Bishop g4"),
        ("a1", "e5", "f7", "g8", "a5", chess.ROOK, "Queen Center Fork on e5 winning Rook a5"),
        ("h1", "h5", "d7", "e8", "b5", chess.BISHOP, "Queen Flank Check on h5 winning Bishop b5"),
        ("h1", "e4", "c6", "d7", "h7", chess.ROOK, "Queen Central Fork on e4 winning Rook h7"),
        ("f1", "f7", "d5", "c6", "b7", chess.BISHOP, "Queen 7th-Rank Fork on f7 winning Bishop b7"),
        ("b2", "f6", "e7", "d6", "h8", chess.ROOK, "Bishop Long Fork on f6 winning Rook h8"),
        ("c1", "a3", "e7", "d8", "f8", chess.ROOK, "Bishop Diagonal Fork on a3 winning Rook f8"),
        ("e3", "c5", "e7", "e8", "a7", chess.ROOK, "Bishop Fork on c5 winning Rook a7"),
        ("d2", "a5", "e7", "e6", "d8", chess.ROOK, "Bishop Attack on a5 winning Rook d8"),
        ("f4", "d6", "e7", "f7", "b8", chess.ROOK, "Bishop Royal Fork on d6 winning Rook b8"),
        ("g2", "d5", "e6", "e7", "a8", chess.ROOK, "Bishop Fianchetto Fork on d5 winning Rook a8"),
        ("a1", "a7", "e7", "d8", "g7", chess.KNIGHT, "Rook 7th-Rank Fork on a7 winning Knight g7"),
        ("b1", "b7", "e7", "f8", "c7", chess.KNIGHT, "Rook 7th-Rank Fork on b7 winning Knight c7"),
        ("c1", "c7", "e7", "d6", "g7", chess.BISHOP, "Rook Invasion Fork on c7 winning Bishop g7"),
        ("d1", "d7", "f7", "g8", "b7", chess.BISHOP, "Rook Central Fork on d7 winning Bishop b7"),
        ("e1", "e7", "g7", "h8", "c7", chess.KNIGHT, "Rook Deep Fork on e7 winning Knight c7"),
        ("f1", "f7", "d7", "e8", "b7", chess.KNIGHT, "Rook Check Fork on f7 winning Knight b7"),
        ("a1", "e1", "e6", "d5", "e8", chess.ROOK, "Rook Vertical Pin Fork on e1"),
        ("d1", "d8", "f6", "g5", "b8", chess.ROOK, "Rook Back-Rank Fork on d8 winning Rook"),
        ("b3", "d4", "e6", "c6", "e7", chess.ROOK, "Knight Fork on d4 winning Rook c6"),
        ("f3", "e5", "d7", "c6", "e8", chess.ROOK, "Knight Central Fork on e5 winning Rook c6"),
        ("c3", "e4", "d6", "f6", "e7", chess.ROOK, "Knight Fork on e4 winning Rook f6"),
        ("a3", "b5", "c7", "a7", "c8", chess.ROOK, "Knight Flank Jump to b5 winning Rook a7"),
        ("h3", "g5", "f7", "h7", "f8", chess.ROOK, "Knight Flank Jump to g5 winning Rook h7"),
        ("c3", "e4", "d6", "f6", "c7", chess.KNIGHT, "Knight Central Jump to e4 winning Knight"),
        ("d2", "e4", "f6", "g6", "d6", chess.KNIGHT, "Knight Outpost to e4 winning Knight d6"),
        ("f3", "d4", "c6", "b6", "e6", chess.BISHOP, "Knight Jump to d4 winning Bishop e6"),
        ("g3", "e4", "d6", "e7", "f6", chess.BISHOP, "Knight Fork on e4 winning Bishop f6"),
        ("e2", "c3", "d5", "e5", "b5", chess.BISHOP, "Knight Maneuver to c3 winning Bishop b5"),
        ("b1", "c3", "e4", "f5", "a4", chess.BISHOP, "Knight Developing Fork on c3 winning Bishop"),
        ("d3", "e5", "c6", "d7", "g6", chess.BISHOP, "Knight Infiltration on e5 winning Bishop g6"),
        ("f2", "e4", "d6", "c7", "f6", chess.ROOK, "Knight Fork on e4 winning Rook f6"),
        ("a3", "c4", "e5", "d6", "b6", chess.ROOK, "Knight Flank Hop to c4 winning Rook b6"),
        ("h3", "f4", "e6", "d7", "g6", chess.ROOK, "Knight Flank Hop to f4 winning Rook g6"),
    ]:
        b = chess.Board(None)
        b.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.parse_square(loose_sq), chess.Piece(loose_piece, chess.BLACK))
        piece_type = chess.QUEEN if q_st[0] in "abcdefgh" and "Queen" in label else (chess.BISHOP if "Bishop" in label else chess.ROOK)
        b.set_piece_at(chess.parse_square(q_st), chess.Piece(piece_type, chess.WHITE))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        b.turn = chess.WHITE
        verify_and_add(b, [f"{q_st}{q_dst}", f"{k_sq}{esc_sq}", f"{q_dst}{loose_sq}"], label, seen_moves, seen_fens, puzzles)

    assert len(puzzles) >= 100, f"Only generated {len(puzzles)} forks"
    write_tactical_kt("ForkDatabase", "Fork", puzzles[:100])

# ==============================================================================
# 2. PIN DATABASE
# ==============================================================================
def build_pin_database():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    # Bishop Pins
    for b_st in ["f1", "d3", "e2", "a4", "c4"]:
        for blk_p in ["a7", "h7", "g7", "b7"]:
            for blk_m in [f"{blk_p}{blk_p[0]}6", f"{blk_p}{blk_p[0]}5"]:
                b = chess.Board(None)
                b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
                b.set_piece_at(chess.C6, chess.Piece(chess.KNIGHT, chess.BLACK))
                b.set_piece_at(chess.parse_square(blk_p), chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
                try:
                    b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
                    b.turn = chess.WHITE
                    verify_and_add(b, [f"{b_st}b5", blk_m, "b5c6"], f"Bishop Pin on c6 Knight from {b_st}", seen_moves, seen_fens, puzzles)
                except: pass

    for b_st in ["c1", "d2", "e3", "f4", "h4"]:
        for blk_p in ["a7", "h7", "g7", "b7"]:
            for blk_m in [f"{blk_p}{blk_p[0]}6", f"{blk_p}{blk_p[0]}5"]:
                b = chess.Board(None)
                b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
                b.set_piece_at(chess.F6, chess.Piece(chess.KNIGHT, chess.BLACK))
                b.set_piece_at(chess.parse_square(blk_p), chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
                try:
                    b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
                    b.turn = chess.WHITE
                    verify_and_add(b, [f"{b_st}g5", blk_m, "g5f6"], f"Bishop Pin on f6 Knight from {b_st}", seen_moves, seen_fens, puzzles)
                except: pass

    # Additional Bishop Pins on d7/e7
    for b_st in ["b5", "c4", "f3"]:
        for blk_p in ["a7", "h7"]:
            for blk_m in [f"{blk_p}{blk_p[0]}6", f"{blk_p}{blk_p[0]}5"]:
                b = chess.Board(None)
                b.set_piece_at(chess.D8, chess.Piece(chess.KING, chess.BLACK))
                b.set_piece_at(chess.E7, chess.Piece(chess.KNIGHT, chess.BLACK))
                b.set_piece_at(chess.parse_square(blk_p), chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
                try:
                    b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
                    b.turn = chess.WHITE
                    verify_and_add(b, [f"{b_st}g4", blk_m, "g4e7"], f"Bishop Pin on e7 Knight from {b_st}", seen_moves, seen_fens, puzzles)
                except: pass

    # Rook Pins
    for r_st in ["a1", "b1", "c1", "d1", "f1", "g1", "h1"]:
        for blk_p in ["a7", "h7", "g7", "b7"]:
            for blk_m in [f"{blk_p}{blk_p[0]}6", f"{blk_p}{blk_p[0]}5"]:
                b = chess.Board(None)
                b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
                b.set_piece_at(chess.E7, chess.Piece(chess.QUEEN, chess.BLACK))
                b.set_piece_at(chess.parse_square(blk_p), chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
                try:
                    b.set_piece_at(chess.parse_square(r_st), chess.Piece(chess.ROOK, chess.WHITE))
                    b.turn = chess.WHITE
                    verify_and_add(b, [f"{r_st}e1", blk_m, "e1e7"], f"Rook Pin on e7 Queen from {r_st}", seen_moves, seen_fens, puzzles)
                except: pass

    for r_st in ["a1", "b1", "c1", "e1", "f1", "g1", "h1"]:
        for blk_p in ["a7", "h7", "g7", "f7"]:
            for blk_m in [f"{blk_p}{blk_p[0]}6", f"{blk_p}{blk_p[0]}5"]:
                b = chess.Board(None)
                b.set_piece_at(chess.D8, chess.Piece(chess.KING, chess.BLACK))
                b.set_piece_at(chess.D7, chess.Piece(chess.QUEEN, chess.BLACK))
                b.set_piece_at(chess.parse_square(blk_p), chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
                try:
                    b.set_piece_at(chess.parse_square(r_st), chess.Piece(chess.ROOK, chess.WHITE))
                    b.turn = chess.WHITE
                    verify_and_add(b, [f"{r_st}d1", blk_m, "d1d7"], f"Rook Pin on d7 Queen from {r_st}", seen_moves, seen_fens, puzzles)
                except: pass

    # Pawn Pin Advances
    for p_st, p_dst, pin_sq, pin_att_sq, blk_p, blk_m, cap_move, label in [
        ("d4", "d5", "c6", "b5", "h7", "h7h6", "d5c6", "Pawn Advance on Pinned c6 Knight"),
        ("e4", "e5", "f6", "g5", "a7", "a7a6", "e5f6", "Pawn Thrust on Pinned f6 Knight"),
        ("c4", "c5", "b6", "a4", "h7", "h7h6", "c5b6", "Pawn Push on Pinned b6 Knight"),
        ("f4", "f5", "e6", "h3", "a7", "a7a6", "f5e6", "Pawn Advance on Pinned e6 Knight"),
        ("d3", "d4", "c5", "a3", "h7", "h7h6", "d4c5", "Pawn Strike on Pinned c5 Knight"),
        ("e3", "e4", "d5", "b3", "a7", "a7a6", "e4d5", "Pawn Strike on Pinned d5 Knight"),
        ("c3", "c4", "b5", "a3", "h7", "h7h6", "c4b5", "Pawn Push on Pinned b5 Knight"),
        ("f3", "f4", "e5", "h3", "a7", "a7a6", "f4e5", "Pawn Push on Pinned e5 Knight"),
        ("b4", "b5", "a6", "a4", "h7", "h7h6", "b5a6", "Flank Pawn Push on Pinned a6 Knight"),
        ("g4", "g5", "h6", "h4", "a7", "a7a6", "g5h6", "Flank Pawn Push on Pinned h6 Knight"),
    ]:
        b = chess.Board(None)
        b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.parse_square(pin_sq), chess.Piece(chess.KNIGHT, chess.BLACK))
        b.set_piece_at(chess.parse_square(pin_att_sq), chess.Piece(chess.BISHOP, chess.WHITE))
        b.set_piece_at(chess.parse_square(blk_p), chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.parse_square(p_st), chess.Piece(chess.PAWN, chess.WHITE))
        b.set_piece_at(chess.H1, chess.Piece(chess.KING, chess.WHITE))
        b.turn = chess.WHITE
        verify_and_add(b, [f"{p_st}{p_dst}", blk_m, cap_move], label, seen_moves, seen_fens, puzzles)

    assert len(puzzles) >= 100, f"Only generated {len(puzzles)} pins"
    write_tactical_kt("PinDatabase", "Pin", puzzles[:100])

# ==============================================================================
# 3. SKEWER DATABASE
# ==============================================================================
def build_skewer_database():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    # Vertical Rook Skewers (Rook attacks file, King in front, Rook/Queen behind)
    for r_file in "abcdefgh":
        for r_st_file in "abcdefgh":
            if r_st_file == r_file: continue
            for k_rank in [4, 5]:
                k_sq = f"{r_file}{k_rank}"
                behind_sq = f"{r_file}8"
                r_st_1 = f"{r_st_file}1"
                r_dst_1 = f"{r_file}1"
                esc_file = chr(ord(r_file) + 1) if r_file != "h" else chr(ord(r_file) - 1)
                esc_sq = f"{esc_file}{k_rank+1}"
                b = chess.Board(None)
                b.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
                b.set_piece_at(chess.parse_square(behind_sq), chess.Piece(chess.ROOK, chess.BLACK))
                b.set_piece_at(chess.parse_square(r_st_1), chess.Piece(chess.ROOK, chess.WHITE))
                b.set_piece_at(chess.H1 if r_file != "h" else chess.A1, chess.Piece(chess.KING, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{r_st_1}{r_dst_1}", f"{k_sq}{esc_sq}", f"{r_dst_1}{behind_sq}"]
                verify_and_add(b, moves, f"Vertical Rook Skewer on {r_file.upper()}-file", seen_moves, seen_fens, puzzles)

    # Horizontal Rook Skewers on rank 8
    for r_st_rank in [1, 2, 3]:
        for k_file in ["d", "e", "f"]:
            k_sq = f"{k_file}8"
            behind_sq = "h8"
            for r_file in ["a", "b", "c"]:
                r_st = f"{r_file}{r_st_rank}"
                r_dst = f"{r_file}8"
                b = chess.Board(None)
                b.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
                b.set_piece_at(chess.parse_square(behind_sq), chess.Piece(chess.ROOK, chess.BLACK))
                b.set_piece_at(chess.parse_square(r_st), chess.Piece(chess.ROOK, chess.WHITE))
                b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
                b.turn = chess.WHITE
                esc_sq = f"{k_file}7"
                moves = [f"{r_st}{r_dst}", f"{k_sq}{esc_sq}", f"{r_dst}{behind_sq}"]
                verify_and_add(b, moves, "Horizontal Rook Skewer on 8th Rank", seen_moves, seen_fens, puzzles)

    # Bishop Diagonal Skewers
    bishop_skewer_specs = [
        ("b2", "f6", "e7", "d6", "h8", chess.ROOK, "Bishop Diagonal Skewer winning Rook h8"),
        ("a1", "e5", "f6", "e7", "h8", chess.ROOK, "Bishop Skewer on Long Diagonal"),
        ("c1", "g5", "e7", "d7", "h6", chess.ROOK, "Bishop Skewer on g5 winning Rook"),
        ("f1", "c4", "d5", "e6", "b3", chess.ROOK, "Bishop Skewer on a2-g8 diagonal"),
        ("h3", "c8", "e6", "d5", "a6", chess.ROOK, "Bishop Deep Skewer winning Rook a6"),
        ("g2", "d5", "e6", "f7", "a8", chess.ROOK, "Bishop Fianchetto Skewer winning Rook a8"),
        ("e3", "b6", "c7", "d8", "a7", chess.ROOK, "Bishop Flank Skewer winning Rook a7"),
        ("d2", "a5", "c7", "b8", "d8", chess.ROOK, "Bishop Queenside Skewer on c7"),
    ]
    for b_st, b_dst, k_sq, esc_sq, behind_sq, behind_piece, label in bishop_skewer_specs:
        b = chess.Board(None)
        b.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.parse_square(behind_sq), chess.Piece(behind_piece, chess.BLACK))
        b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        b.turn = chess.WHITE
        moves = [f"{b_st}{b_dst}", f"{k_sq}{esc_sq}", f"{b_dst}{behind_sq}"]
        verify_and_add(b, moves, label, seen_moves, seen_fens, puzzles)

    assert len(puzzles) >= 100, f"Only generated {len(puzzles)} skewers"
    write_tactical_kt("SkewerDatabase", "Skewer", puzzles[:100])

# ==============================================================================
# 4. DOUBLE ATTACK DATABASE
# ==============================================================================
def build_double_attack_database():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    for q_st in ["d1", "e1", "c1", "f1", "a1", "h1", "b1", "g1"]:
        for q_dst in ["a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4", "d5", "e5", "f5", "c5", "d7", "e7", "f7", "b5", "g5", "a5", "h5"]:
            for k_sq, esc_sq in [("e8", "d8"), ("e8", "f8"), ("d8", "c8"), ("d8", "e7"), ("f8", "g8"), ("c8", "b8"), ("c8", "d7")]:
                for t_sq in ["a8", "b8", "c8", "g8", "h8", "a7", "b7", "g7", "h7", "a6", "h6"]:
                    if len(puzzles) >= 100: break
                    b = chess.Board(None)
                    b.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
                    b.set_piece_at(chess.parse_square(t_sq), chess.Piece(chess.ROOK, chess.BLACK))
                    b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
                    try:
                        b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
                        b.turn = chess.WHITE
                        moves = [f"{q_st}{q_dst}", f"{k_sq}{esc_sq}", f"{q_dst}{t_sq}"]
                        verify_and_add(b, moves, f"Queen Double Attack from {q_st} to {q_dst}", seen_moves, seen_fens, puzzles)
                    except: pass
                if len(puzzles) >= 100: break
            if len(puzzles) >= 100: break
        if len(puzzles) >= 100: break

    assert len(puzzles) >= 100, f"Only generated {len(puzzles)} double attacks"
    write_tactical_kt("DoubleAttackDatabase", "Double Attack", puzzles[:100])

if __name__ == "__main__":
    build_fork_database()
    build_pin_database()
    build_skewer_database()
    build_double_attack_database()
