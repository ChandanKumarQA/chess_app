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
# 2. MATE IN 2
# ==============================================================================
def gen_mate_in_2():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    # Pattern 1: Queen sacrifice into smothered mate
    # e.g. [q_st + "g8", "f8g8", "h6f7"] or other knight start
    for q_st in ["c4", "d5", "e6", "b3", "a2", "h5", "e4", "f3", "d4", "c3", "g4", "f5", "b2", "a3"]:
        for n_st, n_dst in [("h6", "f7"), ("e5", "f7"), ("g5", "f7"), ("d6", "f7")]:
            b = chess.Board(None)
            b.set_piece_at(chess.H8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.F8, chess.Piece(chess.ROOK, chess.BLACK))
            b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            try:
                b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
                b.set_piece_at(chess.parse_square(n_st), chess.Piece(chess.KNIGHT, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{q_st}g8", "f8g8", f"{n_st}{n_dst}"]
                verify_and_add(b, moves, "Philidor Queen Sacrifice into Smothered Mate", seen_moves, seen_fens, puzzles)
            except: pass

    # Pattern 2: Back-rank double rook mates
    # e.g. [r1_st + "d8", "a8d8", r2_st + "d8"]
    for dst_col in ["d", "e", "c", "f"]:
        for r1_st in ["a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1", "a2", "b2", "h2"]:
            for r2_st in ["a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1", "a2", "b2", "h2"]:
                if r1_st == r2_st or r1_st == f"{dst_col}1" or r2_st == f"{dst_col}1": continue
                b = chess.Board(None)
                k_sq = chess.G8 if dst_col in ["d", "e", "c"] else chess.B8
                b.set_piece_at(k_sq, chess.Piece(chess.KING, chess.BLACK))
                if k_sq == chess.G8:
                    b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
                    b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
                    b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
                else:
                    b.set_piece_at(chess.A7, chess.Piece(chess.PAWN, chess.BLACK))
                    b.set_piece_at(chess.B7, chess.Piece(chess.PAWN, chess.BLACK))
                    b.set_piece_at(chess.C7, chess.Piece(chess.PAWN, chess.BLACK))
                def_rook = chess.A8 if dst_col != "a" else chess.H8
                b.set_piece_at(def_rook, chess.Piece(chess.ROOK, chess.BLACK))
                b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
                try:
                    b.set_piece_at(chess.parse_square(r1_st), chess.Piece(chess.ROOK, chess.WHITE))
                    b.set_piece_at(chess.parse_square(r2_st), chess.Piece(chess.ROOK, chess.WHITE))
                    b.turn = chess.WHITE
                    dst_sq = f"{dst_col}8"
                    def_move = f"{chess.square_name(def_rook)}{dst_sq}"
                    moves = [f"{r1_st}{dst_sq}", def_move, f"{r2_st}{dst_sq}"]
                    verify_and_add(b, moves, f"Double Rook Deflection Mate on {dst_sq}", seen_moves, seen_fens, puzzles)
                except: pass
                if len(puzzles) >= 100: break
            if len(puzzles) >= 100: break
        if len(puzzles) >= 100: break

    # Pattern 3: Greek gift into Queen mate
    for b_st in ["d3", "e4", "c2", "f5", "b1", "a2"]:
        for q_st in ["f3", "h5", "d1", "e2", "g4", "f4"]:
            b = chess.Board(None)
            b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            try:
                b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
                b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{b_st}h7", "g8h8", f"{q_st}h7"]
                verify_and_add(b, moves, "Greek Gift Bishop Decoy into Queen Mate", seen_moves, seen_fens, puzzles)
            except: pass

    # Pattern 4: Arabian mate 2-movers
    for r_st in ["a7", "h1", "e7", "d7", "c7", "f7", "b7"]:
        for n_sq in ["f6", "c6", "f3", "c3"]:
            b = chess.Board(None)
            b.set_piece_at(chess.H8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            try:
                b.set_piece_at(chess.parse_square(r_st), chess.Piece(chess.ROOK, chess.WHITE))
                b.set_piece_at(chess.parse_square(n_sq), chess.Piece(chess.KNIGHT, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{r_st}h7", "h8g8", f"{r_st[0]}7g7"] # wait, let's verify legal
                verify_and_add(b, [f"{r_st}h7", "h8g8", f"h7h8"], "Arabian Rook Maneuver Mate", seen_moves, seen_fens, puzzles)
            except: pass

    print(f"Mate in 2 generated: {len(puzzles)}")
    return puzzles[:100]

# ==============================================================================
# 3. FORK
# ==============================================================================
def gen_fork():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    knight_deltas = [(-2, -1), (-2, 1), (-1, -2), (-1, 2), (1, -2), (1, 2), (2, -1), (2, 1)]

    # 1. Knight forks
    for fork_f in range(1, 7):
        for fork_r in range(1, 7):
            fork_sq = chess.square(fork_f, fork_r)
            targets = []
            for df, dr in knight_deltas:
                tf, tr = fork_f + df, fork_r + dr
                if 0 <= tf < 8 and 0 <= tr < 8:
                    targets.append(chess.square(tf, tr))
            for df, dr in knight_deltas:
                sf, sr = fork_f + df, fork_r + dr
                if 0 <= sf < 8 and 0 <= sr < 8:
                    start_sq = chess.square(sf, sr)
                    move_str = chess.square_name(start_sq) + chess.square_name(fork_sq)
                    if len(targets) >= 2:
                        k_sq = targets[0]
                        p_sq = targets[1]
                        if start_sq not in (k_sq, p_sq):
                            b = chess.Board(None)
                            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
                            b.set_piece_at(start_sq, chess.Piece(chess.KNIGHT, chess.WHITE))
                            b.set_piece_at(k_sq, chess.Piece(chess.KING, chess.BLACK))
                            b.set_piece_at(p_sq, chess.Piece(chess.ROOK, chess.BLACK))
                            b.turn = chess.WHITE
                            m = chess.Move(start_sq, fork_sq)
                            if m in b.legal_moves and not b.is_check():
                                b.push(m)
                                safe_k = [bm for bm in b.legal_moves if bm.from_square == k_sq and bm.to_square != fork_sq]
                                if safe_k:
                                    resp = safe_k[0]
                                    b.push(resp)
                                    cap = chess.Move(fork_sq, p_sq)
                                    if cap in b.legal_moves:
                                        b.pop(); b.pop()
                                        verify_and_add(b, [move_str, resp.uci(), cap.uci()], f"Knight Fork on {chess.square_name(fork_sq)}", seen_moves, seen_fens, puzzles)

    # 2. Pawn forks
    for f in range(1, 7):
        for r in range(2, 6): # ranks 3 to 6
            start_sq = chess.square(f, r)
            push_sq = chess.square(f, r + 1)
            left_t = chess.square(f - 1, r + 2)
            right_t = chess.square(f + 1, r + 2)
            if 0 <= f - 1 < 8 and 0 <= f + 1 < 8 and r + 2 < 8:
                b = chess.Board(None)
                b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
                b.set_piece_at(start_sq, chess.Piece(chess.PAWN, chess.WHITE))
                b.set_piece_at(left_t, chess.Piece(chess.KNIGHT, chess.BLACK))
                b.set_piece_at(right_t, chess.Piece(chess.BISHOP, chess.BLACK))
                b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
                b.turn = chess.WHITE
                m1 = chess.square_name(start_sq) + chess.square_name(push_sq)
                # Black retreats knight
                # White captures bishop
                cap = chess.square_name(push_sq) + chess.square_name(right_t)
                verify_and_add(b, [m1, f"{chess.square_name(left_t)}c7", cap], f"Pawn Fork on {chess.square_name(push_sq)}", seen_moves, seen_fens, puzzles)

    # 3. Queen forks
    for q_st in ["d1", "e1", "c1", "f1", "b1", "g1", "a1", "h1", "d2", "e2", "f3", "d3", "c2"]:
        for q_dst, k_sq, p_sq, k_esc in [
            ("a4", "e8", "c4", "e8d8"), ("h5", "e8", "e5", "e8d7"), ("e5", "e8", "a5", "e8f8"),
            ("b5", "e8", "e5", "e8d8"), ("a3", "e7", "f8", "e7e8"), ("d5", "e8", "a8", "e8d7")
        ]:
            if q_st == q_dst: continue
            b = chess.Board(None)
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            b.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.parse_square(p_sq), chess.Piece(chess.BISHOP, chess.BLACK))
            try:
                b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
                b.turn = chess.WHITE
                moves = [f"{q_st}{q_dst}", k_esc, f"{q_dst}{p_sq}"]
                verify_and_add(b, moves, f"Queen Check Fork on {p_sq}", seen_moves, seen_fens, puzzles)
            except: pass

    print(f"Fork generated: {len(puzzles)}")
    return puzzles[:100]

# Run tests
res1 = gen_mate_in_2()
write_tactical_kt("MateIn2Database", "Mate in 2", res1)

res2 = gen_fork()
write_tactical_kt("ForkDatabase", "Fork", res2)
