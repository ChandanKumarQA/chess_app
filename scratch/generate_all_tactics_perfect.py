#!/usr/bin/env python3
import chess
import os

OUTPUT_DIR = "app/src/main/java/com/chessmaster/play/data"

def write_kt(class_name, theme_name, puzzles):
    assert len(puzzles) == 100, f"{class_name} has {len(puzzles)} puzzles"
    moves = [tuple(p[1]) for p in puzzles]
    fens = [p[0] for p in puzzles]
    assert len(set(moves)) == 100, f"{class_name} has duplicate moves: {len(set(moves))}"
    assert len(set(fens)) == 100, f"{class_name} has duplicate FENs: {len(set(fens))}"

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
    print(f"[{class_name}] Successfully wrote 100 distinct puzzles!")

def verify(fen, moves):
    try:
        b = chess.Board(fen)
        if b.is_check():
            pass
        for m in moves:
            mv = chess.Move.from_uci(m)
            if mv not in b.legal_moves:
                return False
            b.push(mv)
        return True
    except:
        return False

# ==========================================
# 1. MATE IN 1
# ==========================================
def build_mate_in_1():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    def add(b, move_str, motif):
        if move_str in seen_moves: return
        m = chess.Move.from_uci(move_str)
        if m in b.legal_moves:
            b.push(m)
            if b.is_checkmate():
                b.pop()
                fen = b.fen()
                if fen not in seen_fens:
                    seen_moves.add(move_str)
                    seen_fens.add(fen)
                    puzzles.append((fen, [move_str], motif))
            else:
                b.pop()

    # Rooks on g8 back-rank
    for start_file in "abcdefh":
        for start_rank in range(1, 6):
            b = chess.Board(None)
            b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            sq = chess.parse_square(f"{start_file}{start_rank}")
            b.set_piece_at(sq, chess.Piece(chess.ROOK, chess.WHITE))
            b.turn = chess.WHITE
            add(b, f"{start_file}{start_rank}{start_file}8", f"{start_file}-file Back-Rank Mate")

    # Rooks on c8 back-rank
    for start_file in "defgh":
        for start_rank in range(1, 6):
            b = chess.Board(None)
            b.set_piece_at(chess.C8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.A7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.B7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.C7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            sq = chess.parse_square(f"{start_file}{start_rank}")
            b.set_piece_at(sq, chess.Piece(chess.ROOK, chess.WHITE))
            b.turn = chess.WHITE
            add(b, f"{start_file}{start_rank}{start_file}8", f"Queenside Back-Rank Mate on c8")

    # Queens on f7 with various helpers
    for q_st in ["f3", "h5", "e2", "d5", "c4", "b3", "g4", "f2"]:
        for helper, h_sq in [("B", "c4"), ("N", "g5"), ("B", "b3"), ("N", "d5")]:
            if q_st == h_sq: continue
            b = chess.Board(None)
            b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.D7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
            b.set_piece_at(chess.parse_square(h_sq), chess.Piece(chess.BISHOP if helper=="B" else chess.KNIGHT, chess.WHITE))
            b.turn = chess.WHITE
            add(b, f"{q_st}f7", f"Scholar Pattern Queen Mate on f7")

    # Queens on g7 with various helpers
    for q_st in ["f3", "h6", "e5", "d4", "c3", "b2", "f6", "g4", "f5"]:
        for helper, h_sq in [("B", "b2"), ("B", "c3"), ("B", "h6"), ("N", "f5"), ("P", "f6")]:
            if q_st == h_sq: continue
            b = chess.Board(None)
            b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
            hp = chess.PAWN if helper=="P" else (chess.BISHOP if helper=="B" else chess.KNIGHT)
            b.set_piece_at(chess.parse_square(h_sq), chess.Piece(hp, chess.WHITE))
            b.turn = chess.WHITE
            add(b, f"{q_st}g7", f"Battery Queen Mate on g7")

    # Queens on h7
    for q_st in ["h5", "h4", "e2", "c2", "d3", "f3", "g5", "h6"]:
        b = chess.Board(None)
        b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
        b.set_piece_at(chess.D3, chess.Piece(chess.BISHOP, chess.WHITE))
        b.turn = chess.WHITE
        add(b, f"{q_st}h7", f"Diagonal Battery Queen Mate on h7")

    # Knights (Smothered & Arabian)
    for n_st in ["e5", "g5", "d6", "h6", "e7", "d5", "f5", "b5", "c7", "a5", "c5", "f6"]:
        for dst, k_sq in [("f7", "h8"), ("e7", "f8"), ("c7", "a8"), ("b7", "c8")]:
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
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            try:
                sq = chess.parse_square(n_st)
                b.set_piece_at(sq, chess.Piece(chess.KNIGHT, chess.WHITE))
                b.turn = chess.WHITE
                add(b, f"{n_st}{dst}", f"Knight Smothered Mate on {dst}")
            except: pass

    # Bishops (Boden's & Diagonal cutoffs)
    for b_st, b_dst, k_sq in [
        ("c4", "a6", "c8"), ("f1", "a6", "c8"), ("e2", "a6", "c8"),
        ("c1", "g5", "e7"), ("d2", "g5", "e7"), ("e3", "g5", "e7"),
        ("c1", "f4", "e7"), ("b2", "a3", "d6"), ("f1", "b5", "e8")
    ]:
        b = chess.Board(None)
        b.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.D7, chess.Piece(chess.ROOK, chess.BLACK))
        b.set_piece_at(chess.B8, chess.Piece(chess.ROOK, chess.BLACK))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
        b.set_piece_at(chess.F4, chess.Piece(chess.BISHOP, chess.WHITE))
        b.turn = chess.WHITE
        add(b, f"{b_st}{b_dst}", f"Boden Diagonal Bishop Mate on {b_dst}")

    # Pawns
    for p_st, p_dst, k_sq, h_sq in [
        ("f6", "f7", "h8", "g6"), ("g6", "g7", "h8", "f6"),
        ("e6", "e7", "e8", "d6"), ("d6", "d7", "d8", "c6"),
        ("c6", "c7", "a8", "b6"), ("b6", "b7", "a8", "c6"),
        ("f5", "f6", "h7", "g5"), ("e5", "e6", "e7", "d5")
    ]:
        b = chess.Board(None)
        b.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        b.set_piece_at(chess.parse_square(p_st), chess.Piece(chess.PAWN, chess.WHITE))
        b.set_piece_at(chess.parse_square(h_sq), chess.Piece(chess.BISHOP, chess.WHITE))
        b.turn = chess.WHITE
        add(b, f"{p_st}{p_dst}", f"Pawn Checkmate via {p_dst}")

    print(f"Total Mate in 1 puzzles: {len(puzzles)}, unique moves: {len(seen_moves)}")
    return puzzles[:100]

res = build_mate_in_1()
print(f"Mate in 1: {len(res)}")
