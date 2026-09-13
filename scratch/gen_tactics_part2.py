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
# 5. DISCOVERED ATTACK DATABASE
# ==============================================================================
def build_discovered_attack_database():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    # Bishop unmasks Rook on Queen
    # White: Rook on d1, Bishop on d3/d4/d2/d5. Black: Queen on d8, King on g8/h8
    for r_f in ["d", "e", "c"]:
        r_sq = f"{r_f}1"
        q_sq = f"{r_f}8"
        for b_st_rank in [2, 3, 4, 5]:
            b_st = f"{r_f}{b_st_rank}"
            for b_dst, k_sq, esc_sq, blk_p in [
                ("h7", "g8", "g8h8", "f7"),
                ("g6", "f8", "f8g8", "e7"),
                ("b5", "c8", "c8b8", "d7"),
                ("a6", "b8", "b8c8", "c7"),
                ("f5", "g8", "g8f8", "h7"),
                ("e4", "f8", "f8e8", "g7"),
            ]:
                b = chess.Board(None)
                b.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
                b.set_piece_at(chess.parse_square(q_sq), chess.Piece(chess.QUEEN, chess.BLACK))
                b.set_piece_at(chess.parse_square(blk_p), chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.parse_square(r_sq), chess.Piece(chess.ROOK, chess.WHITE))
                b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
                try:
                    b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
                    b.turn = chess.WHITE
                    moves = [f"{b_st}{b_dst}", esc_sq, f"{r_sq}{q_sq}"]
                    verify_and_add(b, moves, f"Discovered Attack on Queen via {b_dst}", seen_moves, seen_fens, puzzles)
                except: pass

    # Knight unmasks Queen on Queen/Rook
    for q_f in ["e", "d", "c", "f"]:
        q_sq_w = f"{q_f}1"
        target_sq = f"{q_f}7"
        for n_st_rank in [3, 4, 5]:
            n_st = f"{q_f}{n_st_rank}"
            for n_dst, k_sq, esc_sq in [
                ("f6", "g8", "g8h8"),
                ("d6", "e8", "e8f8"),
                ("c6", "d8", "d8e8"),
                ("g5", "h8", "h8g8"),
                ("b6", "a8", "a8b8"),
                ("e5", "f7", "f7e8"),
            ]:
                b = chess.Board(None)
                b.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
                b.set_piece_at(chess.parse_square(target_sq), chess.Piece(chess.QUEEN, chess.BLACK))
                b.set_piece_at(chess.parse_square(q_sq_w), chess.Piece(chess.QUEEN, chess.WHITE))
                b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
                try:
                    b.set_piece_at(chess.parse_square(n_st), chess.Piece(chess.KNIGHT, chess.WHITE))
                    b.turn = chess.WHITE
                    moves = [f"{n_st}{n_dst}", esc_sq, f"{q_sq_w}{target_sq}"]
                    verify_and_add(b, moves, f"Knight Discovered Check unmasking Queen", seen_moves, seen_fens, puzzles)
                except: pass

    # Pawn unmasks Bishop on Rook/Queen
    for b_st, target_sq in [("c1", "g5"), ("f1", "b5"), ("b2", "f6"), ("g2", "d5"), ("a3", "e7"), ("h3", "d7")]:
        for p_st, p_dst in [("d2", "d4"), ("e2", "e4"), ("c2", "c4"), ("f2", "f4"), ("d3", "d5"), ("e3", "e5")]:
            b = chess.Board(None)
            b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.parse_square(target_sq), chess.Piece(chess.QUEEN, chess.BLACK))
            b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            b.set_piece_at(chess.A7, chess.Piece(chess.PAWN, chess.BLACK))
            try:
                b.set_piece_at(chess.parse_square(p_st), chess.Piece(chess.PAWN, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{p_st}{p_dst}", "a7a6", f"{b_st}{target_sq}"]
                verify_and_add(b, moves, f"Pawn Advance Discovered Attack on Queen", seen_moves, seen_fens, puzzles)
            except: pass

    # Additional Discovered Checks with King moves
    for k_st, k_dst, r_sq, target_sq, k_blk, esc_blk in [
        ("e2", "d2", "e1", "e7", "e8", "e8f8"),
        ("e2", "f2", "e1", "e7", "e8", "e8d8"),
        ("d2", "c2", "d1", "d7", "d8", "d8e8"),
        ("d2", "e2", "d1", "d7", "d8", "d8c8"),
        ("c2", "b2", "c1", "c7", "c8", "c8d8"),
        ("f2", "g2", "f1", "f7", "f8", "f8e8"),
    ]:
        b = chess.Board(None)
        b.set_piece_at(chess.parse_square(k_blk), chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.parse_square(target_sq), chess.Piece(chess.QUEEN, chess.BLACK))
        b.set_piece_at(chess.parse_square(r_sq), chess.Piece(chess.ROOK, chess.WHITE))
        b.set_piece_at(chess.parse_square(k_st), chess.Piece(chess.KING, chess.WHITE))
        b.turn = chess.WHITE
        moves = [f"{k_st}{k_dst}", esc_blk, f"{r_sq}{target_sq}"]
        verify_and_add(b, moves, "King Step Discovered Check", seen_moves, seen_fens, puzzles)

    assert len(puzzles) >= 100, f"Only generated {len(puzzles)} discovered attacks"
    write_tactical_kt("DiscoveredAttackDatabase", "Discovered Attack", puzzles[:100])

# ==============================================================================
# 6. SMOTHERED MATE DATABASE
# ==============================================================================
def build_smothered_mate_database():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    # 1-move Knight Smothered Mates in various corners and configurations
    # Corner h8
    for n_st in ["e5", "g5", "d6", "h6", "e7", "d8"]:
        for blk_blocker in [chess.ROOK, chess.QUEEN, chess.BISHOP]:
            b = chess.Board(None)
            b.set_piece_at(chess.H8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.G8, chess.Piece(blk_blocker, chess.BLACK))
            b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            try:
                b.set_piece_at(chess.parse_square(n_st), chess.Piece(chess.KNIGHT, chess.WHITE))
                b.turn = chess.WHITE
                verify_and_add(b, [f"{n_st}f7"], "Corner Smothered Mate on f7", seen_moves, seen_fens, puzzles)
            except: pass

    # Corner a8
    for n_st in ["b5", "d5", "a6", "e6", "b7", "d7"]:
        for blk_blocker in [chess.ROOK, chess.QUEEN, chess.BISHOP]:
            b = chess.Board(None)
            b.set_piece_at(chess.A8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.B8, chess.Piece(blk_blocker, chess.BLACK))
            b.set_piece_at(chess.A7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.B7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            try:
                b.set_piece_at(chess.parse_square(n_st), chess.Piece(chess.KNIGHT, chess.WHITE))
                b.turn = chess.WHITE
                verify_and_add(b, [f"{n_st}c7"], "Queenside Smothered Mate on c7", seen_moves, seen_fens, puzzles)
            except: pass

    # Corner f8
    for n_st in ["e5", "c5", "b6", "f6", "e7", "c7"]:
        b = chess.Board(None)
        b.set_piece_at(chess.F8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.E8, chess.Piece(chess.ROOK, chess.BLACK))
        b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.G8, chess.Piece(chess.BISHOP, chess.BLACK))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(n_st), chess.Piece(chess.KNIGHT, chess.WHITE))
            b.turn = chess.WHITE
            verify_and_add(b, [f"{n_st}d7"], "Smothered Mate on d7", seen_moves, seen_fens, puzzles)
        except: pass

    # Corner c8
    for n_st in ["a5", "d6", "c5", "a7", "d7", "e7"]:
        b = chess.Board(None)
        b.set_piece_at(chess.C8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.D8, chess.Piece(chess.ROOK, chess.BLACK))
        b.set_piece_at(chess.B7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.C7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.B8, chess.Piece(chess.BISHOP, chess.BLACK))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(n_st), chess.Piece(chess.KNIGHT, chess.WHITE))
            b.turn = chess.WHITE
            verify_and_add(b, [f"{n_st}b7"], "Smothered Mate on b7", seen_moves, seen_fens, puzzles)
        except: pass

    # Philidor 3-ply Combinations (Qg8+ Rxg8, Nf7#)
    for q_st in ["c4", "d5", "e4", "b3", "f3", "g4", "h5", "c2", "d3", "e2", "f2", "a2", "b1", "d1", "e1"]:
        b = chess.Board(None)
        b.set_piece_at(chess.H8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.F8, chess.Piece(chess.ROOK, chess.BLACK))
        b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.H6, chess.Piece(chess.KNIGHT, chess.WHITE))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
            b.turn = chess.WHITE
            moves = [f"{q_st}g8", "f8g8", "h6f7"]
            verify_and_add(b, moves, "Philidor Legacy Queen Sacrifice Smothered Mate", seen_moves, seen_fens, puzzles)
        except: pass

    # Philidor Queenside Combinations (Qb8+ Rxb8, Nc7#)
    for q_st in ["c5", "d6", "e5", "f4", "a4", "c3", "d4", "e3", "f3", "a3", "d2", "c1", "d1", "b2"]:
        b = chess.Board(None)
        b.set_piece_at(chess.A8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.C8, chess.Piece(chess.ROOK, chess.BLACK))
        b.set_piece_at(chess.A7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.B7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.A6, chess.Piece(chess.KNIGHT, chess.WHITE))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
            b.turn = chess.WHITE
            moves = [f"{q_st}b8", "c8b8", "a6c7"]
            verify_and_add(b, moves, "Queenside Philidor Queen Sacrifice Smothered Mate", seen_moves, seen_fens, puzzles)
        except: pass

    # Philidor E8 Combinations (Qe8+ Rxe8, Nf7#)
    for q_st in ["c6", "d7", "f7", "g6", "h5", "a4", "b5", "c4", "g4", "h4"]:
        b = chess.Board(None)
        b.set_piece_at(chess.F8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.D8, chess.Piece(chess.ROOK, chess.BLACK))
        b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.G8, chess.Piece(chess.BISHOP, chess.BLACK))
        b.set_piece_at(chess.D6, chess.Piece(chess.KNIGHT, chess.WHITE))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
            b.turn = chess.WHITE
            moves = [f"{q_st}e8", "d8e8", "d6f7"]
            verify_and_add(b, moves, "Center Philidor Queen Sacrifice Smothered Mate", seen_moves, seen_fens, puzzles)
        except: pass

    assert len(puzzles) >= 100, f"Only generated {len(puzzles)} smothered mates"
    write_tactical_kt("SmotheredMateDatabase", "Smothered Mate", puzzles[:100])

# ==============================================================================
# 7. SACRIFICE DATABASE
# ==============================================================================
def build_sacrifice_database():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    # Queen Back Rank Sacrifices (Qd8+ Rxd8, Rxd8#)
    for f in "abcdefgh":
        q_st_sqs = [f"{f}1", f"{f}2", f"{f}3", f"{f}4", f"{f}5"]
        for q_st in q_st_sqs:
            for blk_k, esc_k in [("g8", "h8"), ("c8", "b8"), ("e8", "d8"), ("f8", "g8")]:
                b = chess.Board(None)
                b.set_piece_at(chess.parse_square(blk_k), chess.Piece(chess.KING, chess.BLACK))
                b.set_piece_at(chess.parse_square(f"{f}8"), chess.Piece(chess.ROOK, chess.BLACK))
                # Add friendly blocking pawns
                p_rank = 7
                for p_col in "abcdefgh":
                    if p_col != f:
                        b.set_piece_at(chess.parse_square(f"{p_col}{p_rank}"), chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.H1 if f != "h" else chess.A1, chess.Piece(chess.KING, chess.WHITE))
                other_f = "a" if f != "a" else "b"
                b.set_piece_at(chess.parse_square(f"{other_f}1"), chess.Piece(chess.ROOK, chess.WHITE))
                try:
                    b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
                    b.turn = chess.WHITE
                    moves = [f"{q_st}{f}8", f"{f}8{f}8", f"{other_f}1{f}1"]
                except: pass

    # Direct Tactical Sacrifices
    # 1. Greek gift: Bxh7+ Kxh7 Ng5+
    for b_st in ["d3", "c2", "e4", "f5"]:
        for n_st in ["f3", "e2", "h3"]:
            b = chess.Board(None)
            b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            try:
                b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
                b.set_piece_at(chess.parse_square(n_st), chess.Piece(chess.KNIGHT, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{b_st}h7", "g8h7", f"{n_st}g5"]
                verify_and_add(b, moves, "Greek Gift Bishop Sacrifice on h7", seen_moves, seen_fens, puzzles)
            except: pass

    # 2. Bishop sacrifice on f7: Bxf7+ Kxf7 Ne5+
    for b_st in ["c4", "d5", "e6", "b3", "a2"]:
        for n_st in ["f3", "c3", "g1", "d2"]:
            b = chess.Board(None)
            b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.E7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.D7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            try:
                b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
                b.set_piece_at(chess.parse_square(n_st), chess.Piece(chess.KNIGHT, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{b_st}f7", "e8f7", f"{n_st}e5"]
                verify_and_add(b, moves, "Bishop Attraction Sacrifice on f7", seen_moves, seen_fens, puzzles)
            except: pass

    # 3. Rook Deflection Sacrifices on Back Rank:
    for r_f in ["a", "b", "c", "d", "e", "f", "g", "h"]:
        for r_st_rank in [1, 2, 3]:
            r_st = f"{r_f}{r_st_rank}"
            r_dst = f"{r_f}8"
            for q_st in ["d1", "e1", "f1", "c1"]:
                b = chess.Board(None)
                b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
                b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.parse_square(r_dst), chess.Piece(chess.ROOK, chess.BLACK))
                b.set_piece_at(chess.H1 if r_f != "h" else chess.A1, chess.Piece(chess.KING, chess.WHITE))
                try:
                    b.set_piece_at(chess.parse_square(r_st), chess.Piece(chess.ROOK, chess.WHITE))
                    b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
                    b.turn = chess.WHITE
                    moves = [f"{r_st}{r_dst}", f"{r_dst}{r_dst}", f"{q_st}{r_dst}"]
                except: pass

    # 4. Knight sacrifices on f7 / e6:
    for n_st in ["g5", "e5", "d4", "h4"]:
        for b_st in ["c4", "b3", "d3", "e2"]:
            b = chess.Board(None)
            b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.E7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.D7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            try:
                b.set_piece_at(chess.parse_square(n_st), chess.Piece(chess.KNIGHT, chess.WHITE))
                b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{n_st}f7", "e8f7", f"{b_st}e6"]
                verify_and_add(b, moves, "Knight Opening Sacrifice on f7", seen_moves, seen_fens, puzzles)
            except: pass

    # 5. Queen clearance sacrifices
    for q_st in ["h5", "h4", "f3", "g4", "f5", "e4", "d3", "c4", "b3"]:
        for r_st in ["f1", "e1", "d1", "a1", "c1"]:
            b = chess.Board(None)
            b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            try:
                b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
                b.set_piece_at(chess.parse_square(r_st), chess.Piece(chess.ROOK, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{q_st}f7", "g8f7", f"{r_st}f7"]
                verify_and_add(b, moves, "Queen Decisive Sacrifice on f7", seen_moves, seen_fens, puzzles)
            except: pass

    # Rook sacrifices on 7th rank
    for r_st in ["a7", "b7", "c7", "d7", "e7", "f7", "g7"]:
        for q_st in ["a1", "b1", "c1", "d1", "e1", "f1"]:
            b = chess.Board(None)
            b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.D7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            try:
                b.set_piece_at(chess.parse_square(r_st), chess.Piece(chess.ROOK, chess.WHITE))
                b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{r_st}e7", "e8e7", f"{q_st}e7"]
                verify_and_add(b, moves, "Rook Infiltration Sacrifice on e7", seen_moves, seen_fens, puzzles)
            except: pass

    assert len(puzzles) >= 100, f"Only generated {len(puzzles)} sacrifices"
    write_tactical_kt("SacrificeDatabase", "Sacrifice", puzzles[:100])

# ==============================================================================
# 8. ATTRACTION DATABASE
# ==============================================================================
def build_attraction_database():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    # Attracting King to h7 followed by Knight fork/check
    for b_st in ["d3", "c2", "b1", "e4", "f5", "g6", "e2", "f3"]:
        for n_st in ["f3", "e2", "h3", "f4", "e4", "d2"]:
            b = chess.Board(None)
            b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            try:
                b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
                b.set_piece_at(chess.parse_square(n_st), chess.Piece(chess.KNIGHT, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{b_st}h7", "g8h7", f"{n_st}g5"]
                verify_and_add(b, moves, "Attracting King to h7 followed by Knight Jump", seen_moves, seen_fens, puzzles)
            except: pass

    # Attracting King to f7 followed by Fork
    for b_st in ["c4", "d5", "e6", "b3", "a2", "e2", "f3", "g4"]:
        for n_st in ["f3", "c3", "g1", "d2", "e2", "h3"]:
            b = chess.Board(None)
            b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.E7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.D7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            try:
                b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
                b.set_piece_at(chess.parse_square(n_st), chess.Piece(chess.KNIGHT, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{b_st}f7", "e8f7", f"{n_st}e5"]
                verify_and_add(b, moves, "Attracting King to f7 with Bishop Sacrifice", seen_moves, seen_fens, puzzles)
            except: pass

    # Attracting King to b8 followed by Queen Check
    for r_st in ["b1", "b2", "b3", "b4", "b5", "b6", "b7"]:
        for q_st in ["a1", "c1", "d1", "e1", "f1", "g1"]:
            b = chess.Board(None)
            b.set_piece_at(chess.C8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.B8, chess.Piece(chess.ROOK, chess.BLACK))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            try:
                b.set_piece_at(chess.parse_square(r_st), chess.Piece(chess.ROOK, chess.WHITE))
                b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{r_st}b8", "c8b8", f"{q_st}b1"]
                verify_and_add(b, moves, "Attracting King to b8 via Rook Deflection", seen_moves, seen_fens, puzzles)
            except: pass

    # Attracting Queen to e4 followed by Knight fork
    for r_st in ["e1", "e2", "e3", "a4", "b4", "c4", "f4", "g4"]:
        for n_st in ["d2", "f3", "g3", "c3"]:
            b = chess.Board(None)
            b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.D5, chess.Piece(chess.QUEEN, chess.BLACK))
            b.set_piece_at(chess.E4, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            try:
                b.set_piece_at(chess.parse_square(r_st), chess.Piece(chess.ROOK, chess.WHITE))
                b.set_piece_at(chess.parse_square(n_st), chess.Piece(chess.KNIGHT, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{r_st}e4", "d5e4", f"{n_st}f6"]
                verify_and_add(b, moves, "Attracting Queen to e4 into Knight Fork", seen_moves, seen_fens, puzzles)
            except: pass

    assert len(puzzles) >= 100, f"Only generated {len(puzzles)} attraction puzzles"
    write_tactical_kt("AttractionDatabase", "Attraction", puzzles[:100])

if __name__ == "__main__":
    build_discovered_attack_database()
    build_smothered_mate_database()
    build_sacrifice_database()
    build_attraction_database()
