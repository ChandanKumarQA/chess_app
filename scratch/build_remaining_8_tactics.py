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
            try:
                m = chess.Move.from_uci(m_str)
            except:
                return False
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
# 5. DISCOVERED ATTACK
# ==============================================================================
def build_discovered_attack():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    for r_file in ["c", "d", "e", "f"]:
        r_sq = f"{r_file}1"
        for q_rank in [6, 7, 8]:
            q_sq = f"{r_file}{q_rank}"
            for b_rank in [2, 3, 4, 5]:
                b_sq = f"{r_file}{b_rank}"
                for b_dst in ["h7", "g6", "f5", "b5", "a6", "c4", "e4", "d5", "b4", "a5", "g4", "h5", "c5", "f6", "c3", "f3", "a3", "b3", "g3", "h3", "a4", "h4"]:
                    for k_sq in ["g8", "h8", "f8", "e8", "d8", "c8", "b8", "a8"]:
                        if len(puzzles) >= 100: break
                        try:
                            bb = chess.Board(None)
                            bb.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
                            bb.set_piece_at(chess.parse_square(b_dst), chess.Piece(chess.BISHOP, chess.WHITE))
                            bb.set_piece_at(chess.A1, chess.Piece(chess.KING, chess.WHITE))
                            bb.turn = chess.BLACK
                            if not bb.is_check(): continue
                            esc_moves = [m.uci() for m in bb.legal_moves if m.from_square == chess.parse_square(k_sq)]
                            if not esc_moves: continue
                            esc = esc_moves[0]
                            b = chess.Board(None)
                            b.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
                            b.set_piece_at(chess.parse_square(q_sq), chess.Piece(chess.QUEEN, chess.BLACK))
                            b.set_piece_at(chess.parse_square(r_sq), chess.Piece(chess.ROOK, chess.WHITE))
                            b.set_piece_at(chess.parse_square(b_sq), chess.Piece(chess.BISHOP, chess.WHITE))
                            w_k = chess.G1 if chess.G1 not in [chess.parse_square(r_sq), chess.parse_square(b_sq), chess.parse_square(b_dst)] else chess.B1
                            b.set_piece_at(w_k, chess.Piece(chess.KING, chess.WHITE))
                            b.turn = chess.WHITE
                            moves = [f"{b_sq}{b_dst}", esc, f"{r_sq}{q_sq}"]
                            verify_and_add(b, moves, f"Bishop Discovered Attack on Queen via {b_dst}", seen_moves, seen_fens, puzzles)
                        except: pass
                    if len(puzzles) >= 100: break
                if len(puzzles) >= 100: break
            if len(puzzles) >= 100: break
        if len(puzzles) >= 100: break

    assert len(puzzles) >= 100, f"Only generated {len(puzzles)} discovered attacks"
    write_tactical_kt("DiscoveredAttackDatabase", "Discovered Attack", puzzles[:100])

# ==============================================================================
# 6. SMOTHERED MATE
# ==============================================================================
def build_smothered_mate():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    # 1. Philidor Queen Sacrifices on g8: Qg8+ Rxg8, Nf7#
    for q_st in [
        "c4", "d5", "e4", "b3", "f3", "g4", "h5", "a2", "c2", "d3", "e2", "f2", "d1", "e1", "f1",
        "h4", "f5", "g6", "h7", "e6", "d7", "c6", "b5", "a4", "b7", "a8"
    ]:
        b = chess.Board(None)
        b.set_piece_at(chess.H8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.F8, chess.Piece(chess.ROOK, chess.BLACK))
        b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.H6, chess.Piece(chess.KNIGHT, chess.WHITE))
        b.set_piece_at(chess.A1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
            b.turn = chess.WHITE
            verify_and_add(b, [f"{q_st}g8", "f8g8", "h6f7"], f"Philidor Queen Sacrifice from {q_st} on g8", seen_moves, seen_fens, puzzles)
        except: pass

    # 2. Philidor Queen Sacrifices on b8: Qb8+ Rxb8, Nc7#
    for q_st in [
        "c5", "d6", "e5", "f4", "a4", "c3", "d4", "e3", "f3", "a3", "d2", "c1", "d1", "b2",
        "b3", "b4", "b5", "b6", "b7", "a5", "a6", "c6", "c7", "d7", "e7", "f7"
    ]:
        b = chess.Board(None)
        b.set_piece_at(chess.A8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.C8, chess.Piece(chess.ROOK, chess.BLACK))
        b.set_piece_at(chess.A7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.B7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.A6, chess.Piece(chess.KNIGHT, chess.WHITE))
        b.set_piece_at(chess.H1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
            b.turn = chess.WHITE
            verify_and_add(b, [f"{q_st}b8", "c8b8", "a6c7"], f"Queenside Philidor Queen Sacrifice from {q_st}", seen_moves, seen_fens, puzzles)
        except: pass

    # 3. Direct 1-move mates
    for n_st, dst, k_sq, b1, b2, b3, label in [
        ("e5", "f7", "h8", "g8", "g7", "h7", "e5"),
        ("g5", "f7", "h8", "g8", "g7", "h7", "g5"),
        ("d6", "f7", "h8", "g8", "g7", "h7", "d6"),
        ("h6", "f7", "h8", "g8", "g7", "h7", "h6"),
        ("b5", "c7", "a8", "b8", "a7", "b7", "b5"),
        ("d5", "c7", "a8", "b8", "a7", "b7", "d5"),
        ("a6", "c7", "a8", "b8", "a7", "b7", "a6"),
        ("e6", "c7", "a8", "b8", "a7", "b7", "e6"),
        ("c5", "d7", "f8", "e8", "f7", "g7", "c5"),
        ("e5", "d7", "f8", "e8", "f7", "g7", "e5"),
        ("b6", "d7", "f8", "e8", "f7", "g7", "b6"),
        ("f6", "d7", "f8", "e8", "f7", "g7", "f6"),
    ]:
        b = chess.Board(None)
        b.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.parse_square(b1), chess.Piece(chess.ROOK, chess.BLACK))
        b.set_piece_at(chess.parse_square(b2), chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.parse_square(b3), chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.A1 if "a" not in n_st else chess.H1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(n_st), chess.Piece(chess.KNIGHT, chess.WHITE))
            b.turn = chess.WHITE
            verify_and_add(b, [f"{n_st}{dst}"], f"Direct Smothered Mate on {dst}", seen_moves, seen_fens, puzzles)
        except: pass

    # 4. Philidor Variations on e8, d8, f8, c8
    for q_st in ["a4", "b5", "c6", "d7", "f7", "g6", "h5", "e2", "e3", "e4", "e5", "e6", "d1", "f1", "c1", "c2", "c3", "c4", "g4", "h4", "d2", "d3"]:
        b = chess.Board(None)
        b.set_piece_at(chess.F8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.D8, chess.Piece(chess.ROOK, chess.BLACK))
        b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.D6, chess.Piece(chess.KNIGHT, chess.WHITE))
        b.set_piece_at(chess.A1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
            b.turn = chess.WHITE
            verify_and_add(b, [f"{q_st}e8", "d8e8", "d6f7"], f"Center Smothered Mate from {q_st}", seen_moves, seen_fens, puzzles)
        except: pass

    for q_st in ["a5", "b6", "c7", "e7", "f6", "g5", "h4", "d2", "d3", "d4", "d5", "d6", "e1", "c1", "b1", "b2", "b3", "b4", "f4", "g4", "e2", "e3"]:
        b = chess.Board(None)
        b.set_piece_at(chess.C8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.E8, chess.Piece(chess.ROOK, chess.BLACK))
        b.set_piece_at(chess.B7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.C7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.E6, chess.Piece(chess.KNIGHT, chess.WHITE))
        b.set_piece_at(chess.H1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
            b.turn = chess.WHITE
            verify_and_add(b, [f"{q_st}d8", "e8d8", "e6c7"], f"Queenside Infiltration Smothered Mate from {q_st}", seen_moves, seen_fens, puzzles)
        except: pass

    assert len(puzzles) >= 100, f"Only generated {len(puzzles)} smothered mates"
    write_tactical_kt("SmotheredMateDatabase", "Smothered Mate", puzzles[:100])

# ==============================================================================
# 7. SACRIFICE
# ==============================================================================
def build_sacrifice():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    # Queen sacrifice on d8: Qd1-d8+ Ke8xd8 Bg5+
    for q_st in ["d1", "d2", "d3", "d4", "d5", "d6", "a5", "b6", "c7", "f6", "g5", "h4", "e7", "f8", "c8", "b8", "a8"]:
        for b_st in ["g5", "f4", "e3", "a3", "b2", "c1", "h6", "e7"]:
            if q_st == b_st: continue
            b = chess.Board(None)
            b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
            b.set_piece_at(chess.A1, chess.Piece(chess.KING, chess.WHITE))
            try:
                b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{q_st}d8", "e8d8", f"{b_st}e7" if b_st in ["g5", "f6", "d6", "c5"] else f"{b_st}g5"]
                verify_and_add(b, moves, f"Queen Sacrifice on d8 from {q_st}", seen_moves, seen_fens, puzzles)
            except: pass

    # Queen sacrifice on e8: Qe1-e8+ Kd8xe8 Bg5+
    for q_st in ["e1", "e2", "e3", "e4", "e5", "e6", "h5", "g6", "f7", "c6", "b5", "a4", "d7", "c8", "f8", "g8"]:
        for b_st in ["g5", "f4", "c4", "b3", "a2", "h4", "d2", "c1"]:
            if q_st == b_st: continue
            b = chess.Board(None)
            b.set_piece_at(chess.D8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
            b.set_piece_at(chess.A1, chess.Piece(chess.KING, chess.WHITE))
            try:
                b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{q_st}e8", "d8e8", f"{b_st}g5" if b_st != "g5" else f"{b_st}f6"]
                verify_and_add(b, moves, f"Queen Sacrifice on e8 from {q_st}", seen_moves, seen_fens, puzzles)
            except: pass

    # Greek Gift Bishop Sacrifice: Bxh7+ Kxh7 Ng5+
    for b_st in ["d3", "c2", "b1", "e4", "f5", "g6", "e2", "f3", "g4"]:
        for n_st in ["f3", "e2", "h3", "f4", "e4", "d2", "e5"]:
            if b_st == n_st: continue
            b = chess.Board(None)
            b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.D8, chess.Piece(chess.QUEEN, chess.BLACK))
            b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
            b.set_piece_at(chess.parse_square(n_st), chess.Piece(chess.KNIGHT, chess.WHITE))
            b.set_piece_at(chess.A1, chess.Piece(chess.KING, chess.WHITE))
            b.turn = chess.WHITE
            moves = [f"{b_st}h7", "g8h7", f"{n_st}g5"]
            verify_and_add(b, moves, f"Greek Gift Bishop Sacrifice from {b_st}", seen_moves, seen_fens, puzzles)

    assert len(puzzles) >= 100, f"Only generated {len(puzzles)} sacrifices"
    write_tactical_kt("SacrificeDatabase", "Sacrifice", puzzles[:100])

# ==============================================================================
# 8. ATTRACTION
# ==============================================================================
def build_attraction():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    for b_st in ["c4", "d5", "e6", "b3", "a2", "e2", "f3", "g4", "d3", "e4", "f5"]:
        for n_st in ["f3", "c3", "g1", "d2", "e2", "h3", "d4", "f4", "g5"]:
            if b_st == n_st: continue
            for n_dst in ["e5", "g5", "d6", "e6", "d7", "e7"]:
                b = chess.Board(None)
                b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
                b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.D8, chess.Piece(chess.QUEEN, chess.BLACK))
                b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
                b.set_piece_at(chess.parse_square(n_st), chess.Piece(chess.KNIGHT, chess.WHITE))
                b.set_piece_at(chess.A1, chess.Piece(chess.KING, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{b_st}f7", "e8f7", f"{n_st}{n_dst}"]
                verify_and_add(b, moves, f"Attraction Sacrifice on f7 into {n_dst} Knight Jump", seen_moves, seen_fens, puzzles)

    for b_st in ["d3", "c2", "b1", "e4", "f5", "g6", "e2", "f3", "g4"]:
        for n_st in ["f3", "e2", "h3", "f4", "e4", "d2", "e5"]:
            if b_st == n_st: continue
            for n_dst in ["g5", "f6"]:
                b = chess.Board(None)
                b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
                b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.D8, chess.Piece(chess.QUEEN, chess.BLACK))
                b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
                b.set_piece_at(chess.parse_square(n_st), chess.Piece(chess.KNIGHT, chess.WHITE))
                b.set_piece_at(chess.A1, chess.Piece(chess.KING, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{b_st}h7", "g8h7", f"{n_st}{n_dst}"]
                verify_and_add(b, moves, f"Attraction Sacrifice on h7 into {n_dst} Knight Jump", seen_moves, seen_fens, puzzles)

    for r_st in ["b1", "b2", "b3", "b4", "b5", "b6", "b7"]:
        for q_st in ["a1", "c1", "d1", "e1", "f1", "g1", "h1", "a4", "d4", "e4", "f4", "a5", "c5", "d5", "e5"]:
            if r_st == q_st: continue
            for q_dst in ["b1", "a4", "b4", "c5", "d6", "e7"]:
                if q_st == q_dst: continue
                b = chess.Board(None)
                b.set_piece_at(chess.C8, chess.Piece(chess.KING, chess.BLACK))
                b.set_piece_at(chess.B8, chess.Piece(chess.ROOK, chess.BLACK))
                b.set_piece_at(chess.parse_square(r_st), chess.Piece(chess.ROOK, chess.WHITE))
                b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
                b.set_piece_at(chess.H1, chess.Piece(chess.KING, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{r_st}b8", "c8b8", f"{q_st}{q_dst}"]
                verify_and_add(b, moves, f"Attraction Rook Sacrifice on b8", seen_moves, seen_fens, puzzles)

    assert len(puzzles) >= 100, f"Only generated {len(puzzles)} attraction puzzles"
    write_tactical_kt("AttractionDatabase", "Attraction", puzzles[:100])

# ==============================================================================
# 9. DEFLECTION
# ==============================================================================
def build_deflection():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    # Deflection of back rank rook: White has Rook on b1 and Rook on d1. Black Rook on d8.
    # White plays b1-b8+ forcing Rd8xb8, then White plays Rd1xd8# mate!
    for r_st_f in ["a", "b", "c", "e", "f", "g", "h"]:
        for r_st_r in [1, 2, 3, 4]:
            r_st = f"{r_st_f}{r_st_r}"
            r_dst = f"{r_st_f}8"
            for target_file in ["d", "e", "c"]:
                if target_file == r_st_f: continue
                r_main = f"{target_file}1"
                r_blk = f"{target_file}8"
                b = chess.Board(None)
                b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
                b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.parse_square(r_blk), chess.Piece(chess.ROOK, chess.BLACK))
                b.set_piece_at(chess.parse_square(r_main), chess.Piece(chess.ROOK, chess.WHITE))
                b.set_piece_at(chess.parse_square(r_st), chess.Piece(chess.ROOK, chess.WHITE))
                b.set_piece_at(chess.H1 if "h1" not in [r_st, r_main] else chess.A1, chess.Piece(chess.KING, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{r_st}{r_dst}", f"{r_blk}{r_dst}", f"{r_main}{r_blk}"]
                verify_and_add(b, moves, f"Back-Rank Deflection on {r_dst}", seen_moves, seen_fens, puzzles)

    # Deflection of Queen defender:
    for q_st in ["a4", "b5", "c6", "e7", "f6", "g5", "h4", "d2", "d3", "d4", "d5", "d6", "c1", "e1", "f1"]:
        b = chess.Board(None)
        b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.D7, chess.Piece(chess.QUEEN, chess.BLACK))
        b.set_piece_at(chess.D1, chess.Piece(chess.ROOK, chess.WHITE))
        b.set_piece_at(chess.A1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
            b.turn = chess.WHITE
            moves = [f"{q_st}e8", "d7e8", "d1d8"]
            verify_and_add(b, moves, f"Queen Deflection on e8 from {q_st}", seen_moves, seen_fens, puzzles)
        except: pass

    assert len(puzzles) >= 100, f"Only generated {len(puzzles)} deflection puzzles"
    write_tactical_kt("DeflectionDatabase", "Deflection", puzzles[:100])

# ==============================================================================
# 10. CLEARANCE
# ==============================================================================
def build_clearance():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    # Square clearance: Knight jumps away with check, clearing square for Queen
    for n_st in ["e5", "f6", "e7", "d6", "c5", "b6", "c7", "d5", "f5", "g5"]:
        for n_dst in ["f7", "g6", "c6", "d7", "b7", "a6", "h6", "e7", "d6"]:
            for q_st in ["d1", "e1", "c1", "f1", "a4", "b3", "h5"]:
                if n_st in [q_st, n_dst]: continue
                b = chess.Board(None)
                b.set_piece_at(chess.H8, chess.Piece(chess.KING, chess.BLACK))
                b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.parse_square(n_st), chess.Piece(chess.KNIGHT, chess.WHITE))
                b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
                b.set_piece_at(chess.A1, chess.Piece(chess.KING, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{n_st}{n_dst}", "h8g8", f"{q_st}{n_st}"]
                verify_and_add(b, moves, f"Square Clearance of {n_st} for Queen", seen_moves, seen_fens, puzzles)

    # Diagonal clearance: Pawn advance clearing diagonal for Bishop/Queen
    for p_st, p_dst in [
        ("e4", "e5"), ("d4", "d5"), ("c4", "c5"), ("f4", "f5"), ("b4", "b5"), ("g4", "g5"),
        ("e3", "e4"), ("d3", "d4"), ("c3", "c4"), ("f3", "f4"), ("b3", "b4"), ("g3", "g4"),
        ("e5", "e6"), ("d5", "d6"), ("c5", "c6"), ("f5", "f6")
    ]:
        for b_st, b_dst in [("c1", "g5"), ("f1", "b5"), ("b2", "f6"), ("g2", "d5"), ("a3", "e7"), ("h3", "d7"), ("d2", "g5"), ("e2", "b5")]:
            b = chess.Board(None)
            b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.D8, chess.Piece(chess.QUEEN, chess.BLACK))
            b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
            b.set_piece_at(chess.A7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.A1, chess.Piece(chess.KING, chess.WHITE))
            try:
                b.set_piece_at(chess.parse_square(p_st), chess.Piece(chess.PAWN, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{p_st}{p_dst}", "a7a6", f"{b_st}{b_dst}"]
                verify_and_add(b, moves, f"Diagonal Clearance via {p_dst}", seen_moves, seen_fens, puzzles)
            except: pass

    assert len(puzzles) >= 100, f"Only generated {len(puzzles)} clearance puzzles"
    write_tactical_kt("ClearanceDatabase", "Clearance", puzzles[:100])

# ==============================================================================
# 11. WINNING MATERIAL
# ==============================================================================
def build_winning_material():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    # Trapping Queen / Rook on edge with Bishop or Pawn
    for q_sq in ["h5", "a5", "h4", "a4", "h6", "a6", "h3", "a3"]:
        for att_sq, dst_sq, att_piece in [
            ("c1", "d2", chess.BISHOP), ("f1", "e2", chess.BISHOP), ("g1", "f3", chess.KNIGHT),
            ("b1", "c3", chess.KNIGHT), ("g2", "g3", chess.PAWN), ("b2", "b3", chess.PAWN),
            ("g2", "g4", chess.PAWN), ("b2", "b4", chess.PAWN), ("e1", "e2", chess.QUEEN)
        ]:
            b = chess.Board(None)
            b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.parse_square(q_sq), chess.Piece(chess.QUEEN, chess.BLACK))
            b.set_piece_at(chess.D7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.A1, chess.Piece(chess.KING, chess.WHITE))
            try:
                b.set_piece_at(chess.parse_square(att_sq), chess.Piece(att_piece, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{att_sq}{dst_sq}", "d7d6", f"{dst_sq}{q_sq}"]
                verify_and_add(b, moves, f"Trapping Queen on {q_sq}", seen_moves, seen_fens, puzzles)
            except: pass

    # Trapping minor pieces (Bishops and Knights on the rim)
    for m_sq, m_piece in [
        ("a5", chess.KNIGHT), ("h5", chess.KNIGHT), ("a4", chess.KNIGHT), ("h4", chess.KNIGHT),
        ("a7", chess.BISHOP), ("h7", chess.BISHOP), ("b8", chess.KNIGHT), ("g8", chess.KNIGHT)
    ]:
        for p_st, p_dst in [
            ("b3", "b4"), ("g3", "g4"), ("a3", "a4"), ("h3", "h4"), ("c3", "c4"), ("f3", "f4"),
            ("b2", "b4"), ("g2", "g4"), ("c2", "c4"), ("f2", "f4")
        ]:
            b = chess.Board(None)
            b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.parse_square(m_sq), chess.Piece(m_piece, chess.BLACK))
            b.set_piece_at(chess.D7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.A1, chess.Piece(chess.KING, chess.WHITE))
            try:
                b.set_piece_at(chess.parse_square(p_st), chess.Piece(chess.PAWN, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{p_st}{p_dst}", "d7d6", f"{p_dst}{m_sq}"]
                verify_and_add(b, moves, f"Trapping Piece on {m_sq}", seen_moves, seen_fens, puzzles)
            except: pass

    assert len(puzzles) >= 100, f"Only generated {len(puzzles)} winning material puzzles"
    write_tactical_kt("WinningMaterialDatabase", "Winning Material", puzzles[:100])

# ==============================================================================
# 12. MATE IN 2
# ==============================================================================
def build_mate_in_2():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    # 1. Queen + Bishop battery mates: Qd3-h7+ Kh8, Qh7-h8#
    for q_st in ["d3", "c2", "b1", "e4", "f5", "g6", "e2", "f3", "g4", "h5", "d1", "e1", "f1", "c1", "b2", "a3"]:
        b = chess.Board(None)
        b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.D3 if q_st != "d3" else "c2", chess.Piece(chess.BISHOP, chess.WHITE))
        b.set_piece_at(chess.A1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
            b.turn = chess.WHITE
            moves = [f"{q_st}h7", "g8h8", "h7h8"]
            verify_and_add(b, moves, f"Battery Mate in 2 from {q_st}", seen_moves, seen_fens, puzzles)
        except: pass

    # 2. Arabian Mates: Rook + Knight (Rh7+ Kh8 Rxh7# or Ra7+...)
    for r_st in ["a7", "b7", "c7", "d7", "e7", "f7", "g7", "h1", "h2", "h3", "h4", "h5", "h6"]:
        b = chess.Board(None)
        b.set_piece_at(chess.H8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.F6, chess.Piece(chess.KNIGHT, chess.WHITE))
        b.set_piece_at(chess.A1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(r_st), chess.Piece(chess.ROOK, chess.WHITE))
            b.turn = chess.WHITE
            moves = [f"{r_st}h7", "h8g8", "h7h8"]
            verify_and_add(b, moves, f"Arabian Mate in 2 from {r_st}", seen_moves, seen_fens, puzzles)
        except: pass

    # 3. Two Rooks Ladder Mates on Rank 7 & 8
    for r1_st in ["a7", "b7", "c7", "d7", "e7", "f7"]:
        for r2_st in ["a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1", "a2", "b2", "c2", "d2"]:
            b = chess.Board(None)
            b.set_piece_at(chess.B8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.A7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.B7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.C7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.H1, chess.Piece(chess.KING, chess.WHITE))
            try:
                b.set_piece_at(chess.parse_square(r1_st), chess.Piece(chess.ROOK, chess.WHITE))
                b.set_piece_at(chess.parse_square(r2_st), chess.Piece(chess.ROOK, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{r2_st}h8", "b8c7", f"{r1_st}c7"]
            except: pass

    # 4. Queen + Knight Kiss of death
    for q_st in ["e7", "f7", "d6", "e5", "f5", "g5", "c4", "d5", "e4", "f4", "g4", "h5", "b3", "c3", "d3"]:
        b = chess.Board(None)
        b.set_piece_at(chess.H8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.F6, chess.Piece(chess.KNIGHT, chess.WHITE))
        b.set_piece_at(chess.A1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
            b.turn = chess.WHITE
            moves = [f"{q_st}g7", "h8g8", "g7g8"]
        except: pass

    # 5. Epaulette Mates
    for q_st in ["e4", "e5", "e3", "d4", "f4", "c4", "g4", "d3", "f3", "c3", "g3", "d5", "f5", "b4", "h4"]:
        b = chess.Board(None)
        b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.D8, chess.Piece(chess.ROOK, chess.BLACK))
        b.set_piece_at(chess.F8, chess.Piece(chess.ROOK, chess.BLACK))
        b.set_piece_at(chess.E6, chess.Piece(chess.PAWN, chess.WHITE))
        b.set_piece_at(chess.A1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
            b.turn = chess.WHITE
            moves = [f"{q_st}e7", "e8d8", "e7d7"]
            verify_and_add(b, moves, f"Epaulette Mate in 2 from {q_st}", seen_moves, seen_fens, puzzles)
        except: pass

    # 6. Pawn promotion mates
    for p_file in ["c", "d", "e", "f"]:
        p_st = f"{p_file}7"
        p_dst = f"{p_file}8q"
        for r_st in ["a1", "b1", "g1", "h1", "a2", "h2"]:
            b = chess.Board(None)
            b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.parse_square(p_st), chess.Piece(chess.PAWN, chess.WHITE))
            b.set_piece_at(chess.parse_square(r_st), chess.Piece(chess.ROOK, chess.WHITE))
            b.set_piece_at(chess.A1 if "a1" not in [p_st, r_st] else chess.B1, chess.Piece(chess.KING, chess.WHITE))
            b.turn = chess.WHITE
            moves = [f"{p_st}{p_dst}", "g8h8", f"{r_st}h1"]
            verify_and_add(b, moves, f"Promotion Mate in 2 on {p_file}8", seen_moves, seen_fens, puzzles)

    # 7. Additional 2-move checkmates with Rooks on files
    for r_f in ["a", "b", "c", "d", "e", "f", "g", "h"]:
        for r1_rank in [1, 2, 3]:
            for r2_rank in [1, 2, 3]:
                if r1_rank == r2_rank: continue
                r1_st = f"{r_f}{r1_rank}"
                r2_st = f"h{r2_rank}" if r_f != "h" else f"a{r2_rank}"
                b = chess.Board(None)
                b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
                b.set_piece_at(chess.D8, chess.Piece(chess.ROOK, chess.BLACK))
                b.set_piece_at(chess.F8, chess.Piece(chess.BISHOP, chess.BLACK))
                b.set_piece_at(chess.D7, chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.parse_square(r1_st), chess.Piece(chess.ROOK, chess.WHITE))
                b.set_piece_at(chess.parse_square(r2_st), chess.Piece(chess.ROOK, chess.WHITE))
                b.set_piece_at(chess.B1, chess.Piece(chess.KING, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{r1_st}e{r1_rank}", "e8f8", f"{r2_st}f8"]

    assert len(puzzles) >= 100, f"Only generated {len(puzzles)} mate in 2s"
    write_tactical_kt("MateIn2Database", "Mate in 2", puzzles[:100])

if __name__ == "__main__":
    build_discovered_attack()
    build_smothered_mate()
    build_sacrifice()
    build_attraction()
    build_deflection()
    build_clearance()
    build_winning_material()
    build_mate_in_2()
