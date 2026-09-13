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
# 1. SMOTHERED MATE (100 Unique Moves & FENs)
# ==============================================================================
def gen_smothered_mate():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    for k_sq in chess.SQUARES:
        if len(puzzles) >= 100: break
        neighbors = list(chess.SquareSet(chess.BB_KING_ATTACKS[k_sq]))
        if len(neighbors) not in [3, 5]: continue
        knight_attacks_to_k = list(chess.SquareSet(chess.BB_KNIGHT_ATTACKS[k_sq]))
        for n_dst in knight_attacks_to_k:
            if len(puzzles) >= 100: break
            if n_dst in neighbors: continue
            origins = list(chess.SquareSet(chess.BB_KNIGHT_ATTACKS[n_dst]))
            for n_st in origins:
                if len(puzzles) >= 100: break
                if n_st == k_sq or n_st in neighbors: continue
                b = chess.Board(None)
                b.set_piece_at(k_sq, chess.Piece(chess.KING, chess.BLACK))
                for i, nbor in enumerate(neighbors):
                    p_type = chess.PAWN if chess.square_rank(nbor) in [1, 2, 3, 4, 5, 6] else chess.ROOK
                    if p_type == chess.ROOK and chess.square_file(nbor) == chess.square_file(n_dst):
                        p_type = chess.BISHOP
                    b.set_piece_at(nbor, chess.Piece(p_type, chess.BLACK))
                w_k = chess.A1 if k_sq != chess.A1 and chess.A1 not in neighbors and chess.A1 != n_st else chess.H1
                b.set_piece_at(w_k, chess.Piece(chess.KING, chess.WHITE))
                try:
                    b.set_piece_at(n_st, chess.Piece(chess.KNIGHT, chess.WHITE))
                    b.turn = chess.WHITE
                    if not b.is_check():
                        mv = chess.Move(n_st, n_dst)
                        if mv in b.legal_moves:
                            tb = b.copy()
                            tb.push(mv)
                            if tb.is_checkmate():
                                verify_and_add(b, [mv.uci()], f"Pure Smothered Mate on {chess.square_name(k_sq)}", seen_moves, seen_fens, puzzles)
                except: pass

    assert len(puzzles) == 100
    write_tactical_kt("SmotheredMateDatabase", "Smothered Mate", puzzles)

# ==============================================================================
# 2. SACRIFICE (100 Unique Moves & FENs)
# ==============================================================================
def gen_sacrifice():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    for blk_r_file in ["a", "b", "c", "e", "f", "g", "h"]:
        blk_r_sq = f"{blk_r_file}8"
        for mate_file in ["d", "e", "c", "f"]:
            if mate_file == blk_r_file: continue
            r_w_sq = f"{mate_file}1"
            target_sq = f"{mate_file}8"
            for q_sq in chess.SQUARES:
                if len(puzzles) >= 100: break
                if q_sq in [chess.parse_square(r_w_sq), chess.parse_square(target_sq), chess.G8, chess.F7, chess.G7, chess.H7, chess.parse_square(blk_r_sq)]: continue
                b = chess.Board(None)
                b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
                b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.parse_square(blk_r_sq), chess.Piece(chess.ROOK, chess.BLACK))
                b.set_piece_at(chess.parse_square(r_w_sq), chess.Piece(chess.ROOK, chess.WHITE))
                b.set_piece_at(chess.A1 if chess.A1 not in [q_sq, chess.parse_square(r_w_sq)] else chess.B1, chess.Piece(chess.KING, chess.WHITE))
                try:
                    b.set_piece_at(q_sq, chess.Piece(chess.QUEEN, chess.WHITE))
                    b.turn = chess.WHITE
                    m1 = chess.Move(q_sq, chess.parse_square(target_sq))
                    if m1 in b.legal_moves:
                        moves = [m1.uci(), f"{blk_r_sq}{target_sq}", f"{r_w_sq}{target_sq}"]
                        verify_and_add(b, moves, f"Queen Back-Rank Sacrifice on {target_sq} from {chess.square_name(q_sq)}", seen_moves, seen_fens, puzzles)
                except: pass
            if len(puzzles) >= 100: break
        if len(puzzles) >= 100: break

    assert len(puzzles) == 100
    write_tactical_kt("SacrificeDatabase", "Sacrifice", puzzles)

# ==============================================================================
# 3. ATTRACTION (100 Unique Moves & FENs)
# ==============================================================================
def gen_attraction():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    for b_st in ["c4", "d5", "e6", "b3", "a2", "e2", "f3", "g4", "d3", "e4", "f5"]:
        for n_st in ["f3", "c3", "g1", "d2", "e2", "h3", "d4", "f4", "g5"]:
            if b_st == n_st: continue
            for n_dst in ["e5", "g5", "d6", "e6", "d7", "e7"]:
                if len(puzzles) >= 100: break
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
                if len(puzzles) >= 100: break
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
                if len(puzzles) >= 100: break
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

    assert len(puzzles) == 100
    write_tactical_kt("AttractionDatabase", "Attraction", puzzles)

# ==============================================================================
# 4. DEFLECTION (100 Unique Moves & FENs)
# ==============================================================================
def gen_deflection():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    for def_f in ["a", "b", "c", "f", "g", "h"]:
        def_target = f"{def_f}8"
        for mate_f in ["d", "e", "c", "f"]:
            if mate_f == def_f: continue
            r_mate_sq = f"{mate_f}1"
            target_sq = f"{mate_f}8"
            for p_type in [chess.ROOK, chess.QUEEN]:
                for r_def_sq in chess.SQUARES:
                    if len(puzzles) >= 100: break
                    if r_def_sq in [chess.parse_square(target_sq), chess.parse_square(def_target), chess.G8, chess.F7, chess.G7, chess.H7, chess.parse_square(r_mate_sq)]: continue
                    b = chess.Board(None)
                    b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
                    b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
                    b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
                    b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
                    b.set_piece_at(chess.parse_square(target_sq), chess.Piece(chess.ROOK, chess.BLACK))
                    b.set_piece_at(chess.parse_square(r_mate_sq), chess.Piece(chess.ROOK, chess.WHITE))
                    b.set_piece_at(chess.A1 if chess.A1 not in [r_def_sq, chess.parse_square(r_mate_sq)] else chess.B1, chess.Piece(chess.KING, chess.WHITE))
                    try:
                        b.set_piece_at(r_def_sq, chess.Piece(p_type, chess.WHITE))
                        b.turn = chess.WHITE
                        m1 = chess.Move(r_def_sq, chess.parse_square(def_target))
                        if m1 in b.legal_moves:
                            moves = [m1.uci(), f"{target_sq}{def_target}", f"{r_mate_sq}{target_sq}"]
                            verify_and_add(b, moves, f"Deflection of Defender to {def_target}", seen_moves, seen_fens, puzzles)
                    except: pass
                if len(puzzles) >= 100: break
            if len(puzzles) >= 100: break
        if len(puzzles) >= 100: break

    assert len(puzzles) == 100
    write_tactical_kt("DeflectionDatabase", "Deflection", puzzles)

# ==============================================================================
# 5. CLEARANCE (100 Unique Moves & FENs)
# ==============================================================================
def gen_clearance():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    for p_f in ["b", "c", "d", "e", "f", "g"]:
        for p_r in [2, 3, 4]:
            p_st = f"{p_f}{p_r}"
            p_dst = f"{p_f}{p_r+1}"
            for b_sq in ["b1", "c1", "d2", "c2", "f1", "g2", "b2", "a3", "h3", "d3", "e3"]:
                if b_sq == p_st or b_sq == p_dst: continue
                for target_sq in ["a7", "b8", "c8", "d8", "e8", "f8", "g8", "h8", "h7", "g7", "a6", "h6"]:
                    if len(puzzles) >= 100: break
                    b = chess.Board(None)
                    b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
                    b.set_piece_at(chess.parse_square(target_sq), chess.Piece(chess.QUEEN, chess.BLACK))
                    b.set_piece_at(chess.A7, chess.Piece(chess.PAWN, chess.BLACK))
                    b.set_piece_at(chess.A1, chess.Piece(chess.KING, chess.WHITE))
                    try:
                        b.set_piece_at(chess.parse_square(p_st), chess.Piece(chess.PAWN, chess.WHITE))
                        b.set_piece_at(chess.parse_square(b_sq), chess.Piece(chess.BISHOP, chess.WHITE))
                        b.turn = chess.WHITE
                        m1 = chess.Move(chess.parse_square(p_st), chess.parse_square(p_dst))
                        if m1 in b.legal_moves:
                            tb = b.copy()
                            tb.push(m1)
                            # Verify bishop can legally take target_sq
                            m2 = chess.Move.from_uci("a7a6")
                            if m2 in tb.legal_moves:
                                tb.push(m2)
                                m3 = chess.Move(chess.parse_square(b_sq), chess.parse_square(target_sq))
                                if m3 in tb.legal_moves:
                                    moves = [m1.uci(), "a7a6", f"{b_sq}{target_sq}"]
                                    verify_and_add(b, moves, f"Diagonal Clearance via {p_dst}", seen_moves, seen_fens, puzzles)
                    except: pass
                if len(puzzles) >= 100: break
            if len(puzzles) >= 100: break
        if len(puzzles) >= 100: break

    assert len(puzzles) == 100
    write_tactical_kt("ClearanceDatabase", "Clearance", puzzles)

# ==============================================================================
# 6. WINNING MATERIAL (100 Unique Moves & FENs)
# ==============================================================================
def gen_winning_material():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    for q_sq in ["h5", "a5", "h4", "a4", "h6", "a6", "h3", "a3"]:
        for att_sq, dst_sq, att_piece in [
            ("c1", "d2", chess.BISHOP), ("f1", "e2", chess.BISHOP), ("g1", "f3", chess.KNIGHT),
            ("b1", "c3", chess.KNIGHT), ("g2", "g3", chess.PAWN), ("b2", "b3", chess.PAWN),
            ("g2", "g4", chess.PAWN), ("b2", "b4", chess.PAWN), ("e1", "e2", chess.QUEEN),
            ("d1", "c2", chess.QUEEN), ("c1", "b2", chess.BISHOP), ("f1", "g2", chess.BISHOP),
            ("e1", "f2", chess.QUEEN), ("d1", "e2", chess.QUEEN)
        ]:
            if len(puzzles) >= 100: break
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

    for m_sq, m_piece in [
        ("a5", chess.KNIGHT), ("h5", chess.KNIGHT), ("a4", chess.KNIGHT), ("h4", chess.KNIGHT),
        ("a7", chess.BISHOP), ("h7", chess.BISHOP), ("b8", chess.KNIGHT), ("g8", chess.KNIGHT),
        ("a6", chess.KNIGHT), ("h6", chess.KNIGHT), ("b6", chess.KNIGHT), ("g6", chess.KNIGHT)
    ]:
        for p_st, p_dst in [
            ("b3", "b4"), ("g3", "g4"), ("a3", "a4"), ("h3", "h4"), ("c3", "c4"), ("f3", "f4"),
            ("b2", "b4"), ("g2", "g4"), ("c2", "c4"), ("f2", "f4"), ("d3", "d4"), ("e3", "e4")
        ]:
            if len(puzzles) >= 100: break
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

    assert len(puzzles) == 100
    write_tactical_kt("WinningMaterialDatabase", "Winning Material", puzzles)

# ==============================================================================
# 7. MATE IN 2 (100 Unique Moves & FENs)
# ==============================================================================
def gen_mate_in_2():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    # Epaulette mates: King on e8 flanked by Rooks on d8 & f8, white pawn on e6 defending d7 & f7
    # Queen from various squares checks on e7 or e6 or d6
    for q_st in chess.SQUARES:
        if len(puzzles) >= 100: break
        if q_st in [chess.E8, chess.D8, chess.F8, chess.E6, chess.A1]: continue
        b = chess.Board(None)
        b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.D8, chess.Piece(chess.ROOK, chess.BLACK))
        b.set_piece_at(chess.F8, chess.Piece(chess.ROOK, chess.BLACK))
        b.set_piece_at(chess.E6, chess.Piece(chess.PAWN, chess.WHITE))
        b.set_piece_at(chess.A1, chess.Piece(chess.KING, chess.WHITE))
        b.set_piece_at(q_st, chess.Piece(chess.QUEEN, chess.WHITE))
        b.turn = chess.WHITE
        for m1 in list(b.legal_moves):
            if m1.to_square in [chess.D7, chess.F7]: continue # wait, want 2 moves
            # Can Queen play check?
            b.push(m1)
            if b.is_check() and len(list(b.legal_moves)) == 1:
                m2 = list(b.legal_moves)[0]
                b.push(m2)
                for m3 in list(b.legal_moves):
                    b.push(m3)
                    if b.is_checkmate():
                        verify_and_add(b, [m1.uci(), m2.uci(), m3.uci()], "Epaulette Mate in 2", seen_moves, seen_fens, puzzles)
                    b.pop()
                    if len(puzzles) >= 100: break
                b.pop()
            b.pop()
            if len(puzzles) >= 100: break

    # Ladder mates and Back-Rank 2-move checkmates
    # King on g8 (pawns f7, g7, h7), White Rook on file 1 deflecting Black Rook on d8, White second Rook mates on d8
    for def_f in ["a", "b", "c", "f", "g", "h"]:
        def_target = f"{def_f}8"
        for mate_f in ["d", "e"]:
            r_mate_sq = f"{mate_f}1"
            target_sq = f"{mate_f}8"
            for r_st_f in ["a", "b", "c", "f", "g", "h"]:
                for r_st_r in [1, 2, 3]:
                    if len(puzzles) >= 100: break
                    r_def_sq = f"{r_st_f}{r_st_r}"
                    if r_def_sq in [target_sq, def_target, "g8", "f7", "g7", "h7", r_mate_sq]: continue
                    b = chess.Board(None)
                    b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
                    b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
                    b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
                    b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
                    b.set_piece_at(chess.parse_square(target_sq), chess.Piece(chess.ROOK, chess.BLACK))
                    b.set_piece_at(chess.parse_square(r_mate_sq), chess.Piece(chess.ROOK, chess.WHITE))
                    b.set_piece_at(chess.A1 if chess.A1 not in [chess.parse_square(r_def_sq), chess.parse_square(r_mate_sq)] else chess.B1, chess.Piece(chess.KING, chess.WHITE))
                    try:
                        b.set_piece_at(chess.parse_square(r_def_sq), chess.Piece(chess.ROOK, chess.WHITE))
                        b.turn = chess.WHITE
                        m1 = chess.Move(chess.parse_square(r_def_sq), chess.parse_square(def_target))
                        if m1 in b.legal_moves:
                            moves = [m1.uci(), f"{target_sq}{def_target}", f"{r_mate_sq}{target_sq}"]
                            verify_and_add(b, moves, f"Back-Rank Mate in 2 via {def_target}", seen_moves, seen_fens, puzzles)
                    except: pass
                if len(puzzles) >= 100: break
            if len(puzzles) >= 100: break
        if len(puzzles) >= 100: break

    assert len(puzzles) == 100
    write_tactical_kt("MateIn2Database", "Mate in 2", puzzles)

if __name__ == "__main__":
    gen_smothered_mate()
    gen_sacrifice()
    gen_attraction()
    gen_deflection()
    gen_clearance()
    gen_winning_material()
    gen_mate_in_2()
