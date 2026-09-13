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
        '                id = "tac_${idPrefix}_$i",',
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
# 1. MATE IN 1
# ==============================================================================
def gen_mate_in_1():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    # Rooks on back ranks
    for start_file in "abcdefh":
        for start_rank in range(1, 8):
            b = chess.Board(None)
            b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            sq = chess.parse_square(f"{start_file}{start_rank}")
            b.set_piece_at(sq, chess.Piece(chess.ROOK, chess.WHITE))
            b.turn = chess.WHITE
            verify_and_add(b, [f"{start_file}{start_rank}{start_file}8"], f"{start_file.upper()}-file Back-Rank Mate", seen_moves, seen_fens, puzzles)

    for start_file in "cdefgh":
        for start_rank in range(1, 8):
            b = chess.Board(None)
            b.set_piece_at(chess.B8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.A7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.B7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.C7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            sq = chess.parse_square(f"{start_file}{start_rank}")
            b.set_piece_at(sq, chess.Piece(chess.ROOK, chess.WHITE))
            b.turn = chess.WHITE
            verify_and_add(b, [f"{start_file}{start_rank}{start_file}8"], f"Queenside Back-Rank Mate on b8", seen_moves, seen_fens, puzzles)

    # Queen kiss of death on f7
    for q_st in ["f3", "h5", "e2", "d5", "c4", "b3", "g4", "f2", "d1", "e1", "f4", "g3", "h3"]:
        b = chess.Board(None)
        b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.D7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
            b.set_piece_at(chess.C4, chess.Piece(chess.BISHOP, chess.WHITE))
            b.turn = chess.WHITE
            verify_and_add(b, [f"{q_st}f7"], f"Scholar Pattern Queen Mate on f7", seen_moves, seen_fens, puzzles)
        except: pass

    # Queen kiss of death on g7
    for q_st in ["f3", "h6", "e5", "d4", "c3", "b2", "f6", "g4", "f5", "h5", "e4", "d3", "c2"]:
        b = chess.Board(None)
        b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
            b.set_piece_at(chess.B2, chess.Piece(chess.BISHOP, chess.WHITE))
            b.turn = chess.WHITE
            verify_and_add(b, [f"{q_st}g7"], f"Fianchetto Battery Queen Mate on g7", seen_moves, seen_fens, puzzles)
        except: pass

    # Queen kiss of death on h7
    for q_st in ["h5", "h4", "e2", "c2", "d3", "f3", "g5", "h6", "e4", "f5", "d2"]:
        b = chess.Board(None)
        b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
            b.set_piece_at(chess.D3, chess.Piece(chess.BISHOP, chess.WHITE))
            b.turn = chess.WHITE
            verify_and_add(b, [f"{q_st}h7"], f"Diagonal Battery Queen Mate on h7", seen_moves, seen_fens, puzzles)
        except: pass

    # Queen kiss of death on b7
    for q_st in ["e4", "d4", "c3", "b4", "a4", "d5", "c4", "b5", "e5", "f3", "a3"]:
        b = chess.Board(None)
        b.set_piece_at(chess.C8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.D7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
            b.set_piece_at(chess.C6, chess.Piece(chess.BISHOP, chess.WHITE))
            b.turn = chess.WHITE
            verify_and_add(b, [f"{q_st}b7"], f"Queenside Battery Queen Mate on b7", seen_moves, seen_fens, puzzles)
        except: pass

    # Queen kiss of death on a7
    for q_st in ["e3", "d2", "f4", "b3", "c5", "d4", "e5", "c3", "d3", "b4"]:
        b = chess.Board(None)
        b.set_piece_at(chess.B8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.C7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
            b.set_piece_at(chess.C5, chess.Piece(chess.BISHOP, chess.WHITE))
            b.turn = chess.WHITE
            verify_and_add(b, [f"{q_st}a7"], f"Flank Queen Mate on a7", seen_moves, seen_fens, puzzles)
        except: pass

    # Knight smothered & Arabian
    for n_st, dst, k_sq in [
        ("e5", "f7", "h8"), ("g5", "f7", "h8"), ("d6", "f7", "h8"), ("h6", "f7", "h8"),
        ("d5", "e7", "f8"), ("f5", "e7", "f8"), ("c6", "e7", "f8"), ("g6", "e7", "f8"),
        ("b5", "c7", "a8"), ("d5", "c7", "a8"), ("a6", "c7", "a8"), ("e6", "c7", "a8"),
        ("a5", "b7", "c8"), ("c5", "b7", "c8"), ("d6", "b7", "c8"), ("f6", "h7", "h8"),
        ("g5", "h7", "h8"), ("f7", "h6", "g8")
    ]:
        b = chess.Board(None)
        b.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
        if k_sq == "h8":
            b.set_piece_at(chess.G8, chess.Piece(chess.ROOK, chess.BLACK))
            b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
        elif k_sq == "f8":
            b.set_piece_at(chess.E8, chess.Piece(chess.ROOK, chess.BLACK))
            b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
        elif k_sq == "a8":
            b.set_piece_at(chess.B8, chess.Piece(chess.ROOK, chess.BLACK))
            b.set_piece_at(chess.A7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.B7, chess.Piece(chess.PAWN, chess.BLACK))
        elif k_sq == "c8":
            b.set_piece_at(chess.D8, chess.Piece(chess.ROOK, chess.BLACK))
            b.set_piece_at(chess.B7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.C7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(n_st), chess.Piece(chess.KNIGHT, chess.WHITE))
            b.turn = chess.WHITE
            verify_and_add(b, [f"{n_st}{dst}"], f"Knight Smothered Mate on {dst}", seen_moves, seen_fens, puzzles)
        except: pass

    # Bishops
    for b_st, b_dst in [
        ("c4", "a6"), ("f1", "a6"), ("e2", "a6"), ("d3", "a6"),
        ("c1", "g5"), ("d2", "g5"), ("e3", "g5"), ("f4", "g5"),
        ("c1", "f4"), ("d2", "f4"), ("e3", "f4"),
        ("b2", "g7"), ("c3", "g7"), ("d4", "g7")
    ]:
        b = chess.Board(None)
        b.set_piece_at(chess.C8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.D7, chess.Piece(chess.ROOK, chess.BLACK))
        b.set_piece_at(chess.B8, chess.Piece(chess.ROOK, chess.BLACK))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
            b.set_piece_at(chess.F4, chess.Piece(chess.BISHOP, chess.WHITE))
            b.turn = chess.WHITE
            verify_and_add(b, [f"{b_st}{b_dst}"], f"Boden Diagonal Bishop Mate on {b_dst}", seen_moves, seen_fens, puzzles)
        except: pass

    # Pawns
    for p_st, p_dst, k_sq, h_sq in [
        ("f6", "f7", "h8", "g6"), ("g6", "g7", "h8", "f6"),
        ("e6", "e7", "e8", "d6"), ("d6", "d7", "d8", "c6"),
        ("c6", "c7", "a8", "b6"), ("b6", "b7", "a8", "c6"),
        ("f5", "f6", "h7", "g5"), ("e5", "e6", "e7", "d5"),
        ("d5", "d6", "d7", "c5"), ("c5", "c6", "c7", "b5")
    ]:
        b = chess.Board(None)
        b.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(p_st), chess.Piece(chess.PAWN, chess.WHITE))
            b.set_piece_at(chess.parse_square(h_sq), chess.Piece(chess.BISHOP, chess.WHITE))
            b.turn = chess.WHITE
            verify_and_add(b, [f"{p_st}{p_dst}"], f"Pawn Checkmate via {p_dst}", seen_moves, seen_fens, puzzles)
        except: pass

    return puzzles[:100]

res = gen_mate_in_1()
print(f"Mate in 1 count: {len(res)}")
write_tactical_kt("MateIn1Database", "Mate in 1", res)
