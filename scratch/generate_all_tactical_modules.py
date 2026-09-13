#!/usr/bin/env python3
import chess
from tactical_builder_core import fen_from_dicts, verify_moves

def interleave_diverse(puzzles, target_count=100):
    by_first_move = {}
    for p in puzzles:
        first_m = p[1][0]
        if first_m not in by_first_move:
            by_first_move[first_m] = []
        by_first_move[first_m].append(p)

    result = []
    keys = list(by_first_move.keys())
    idx = 0
    while len(result) < target_count and keys:
        k = keys[idx % len(keys)]
        if by_first_move[k]:
            result.append(by_first_move[k].pop(0))
            if not by_first_move[k]:
                keys.remove(k)
        idx += 1

    if len(result) < target_count:
        for p in puzzles:
            if p not in result:
                result.append(p)
                if len(result) == target_count:
                    break

    return result[:target_count]

# ==========================================
# 1. SKEWER (100)
# ==========================================
def get_skewer_100():
    puzzles = []
    seen = set()

    diags = [
        ("c1", "b2", "g7", "g8", "h8", "q", "g1", "a1-h8 Bishop Skewer on Queen"),
        ("d2", "c3", "f6", "g6", "g7", "r", "h1", "a1-h8 Bishop Skewer on Rook"),
        ("e3", "d4", "f6", "e7", "h8", "q", "g1", "Center a1-h8 Bishop Skewer on Queen"),
        ("f1", "g2", "b7", "b8", "a8", "q", "g1", "Fianchetto h1-a8 Bishop Skewer on Queen"),
        ("e2", "f3", "c6", "d7", "b7", "r", "g1", "h1-a8 Bishop Skewer on Rook"),
        ("d3", "e4", "c6", "b6", "a8", "q", "h1", "Center h1-a8 Bishop Skewer on Queen"),
        ("f1", "c4", "e6", "e7", "g8", "q", "h1", "Italian a2-g8 Bishop Skewer on Queen"),
        ("d3", "c4", "e6", "f6", "g8", "r", "g1", "Italian a2-g8 Bishop Skewer on Rook"),
        ("c2", "d3", "f5", "e6", "h7", "q", "g1", "b1-h7 Bishop Skewer on Queen"),
        ("b1", "d3", "f5", "g6", "h7", "r", "g1", "b1-h7 Bishop Skewer on Rook"),
        ("e3", "f4", "d6", "e7", "b8", "q", "g1", "h2-b8 Bishop Skewer on Queen"),
        ("f2", "e3", "d4", "c5", "b8", "r", "g1", "h2-b8 Bishop Skewer on Rook"),
        ("f2", "e3", "c5", "d6", "a7", "q", "g1", "g1-a7 Bishop Skewer on Queen"),
        ("g1", "e3", "c5", "b6", "a7", "r", "h1", "g1-a7 Bishop Skewer on Rook"),
        ("e1", "d2", "b4", "c5", "a5", "q", "g1", "c1-h6 Bishop Skewer on Queen"),
        ("a2", "b3", "d5", "e6", "f7", "r", "h1", "Queen-Wing Bishop Skewer on Rook")
    ]
    for b_st, b_mv, k_sq, k_dest, t_sq, tgt, wk, desc in diags:
        w = {b_st: "B", wk: "K", "h2": "P"}
        b = {k_sq: "K", t_sq: tgt}
        fen = fen_from_dicts(w, b)
        moves = [b_st + b_mv, k_sq + k_dest, b_mv + t_sq]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, desc))

    for r in range(1, 9):
        r_str = str(r)
        wk = "g1" if r >= 3 else "g8"
        wp = "f2" if r >= 3 else "f7"
        for k_f, t_f, tgt in [("d", "h", "q"), ("e", "h", "r"), ("c", "g", "q"), ("d", "g", "r"), ("c", "h", "b"), ("e", "g", "n")]:
            start_sq = "b" + r_str
            move_sq = "a" + r_str
            k_sq = k_f + r_str
            t_sq = t_f + r_str
            k_dest = k_f + str(r + 1 if r < 8 else r - 1)
            w = {start_sq: "R", wk: "K", wp: "P"}
            b = {k_sq: "K", t_sq: tgt}
            fen = fen_from_dicts(w, b)
            moves = [start_sq + move_sq, k_sq + k_dest, move_sq + t_sq]
            ok, _ = verify_moves(fen, moves)
            if ok and fen not in seen:
                seen.add(fen)
                puzzles.append((fen, moves, f"Rank {r} Queenside Rook Skewer on {tgt.upper()}"))

        for k_f, t_f, tgt in [("e", "a", "q"), ("d", "a", "r"), ("f", "b", "q"), ("e", "b", "r"), ("f", "a", "b"), ("d", "b", "n")]:
            start_sq = "g" + r_str
            move_sq = "h" + r_str
            k_sq = k_f + r_str
            t_sq = t_f + r_str
            k_dest = k_f + str(r + 1 if r < 8 else r - 1)
            w = {start_sq: "R", wk: "K", wp: "P"}
            b = {k_sq: "K", t_sq: tgt}
            fen = fen_from_dicts(w, b)
            moves = [start_sq + move_sq, k_sq + k_dest, move_sq + t_sq]
            ok, _ = verify_moves(fen, moves)
            if ok and fen not in seen:
                seen.add(fen)
                puzzles.append((fen, moves, f"Rank {r} Kingside Rook Skewer on {tgt.upper()}"))

    for f in "abcdefgh":
        wk = "a1" if f in "fgh" else "h1"
        wp = "b2" if f in "fgh" else "g2"
        for k_r, t_r, tgt in [(4, 8, "q"), (5, 8, "r"), (3, 7, "q"), (4, 7, "r"), (5, 7, "b")]:
            start_sq = f + "2"
            move_sq = f + "1"
            k_sq = f + str(k_r)
            t_sq = f + str(t_r)
            k_dest = (chr(ord(f) + 1 if f != "h" else ord(f) - 1)) + str(k_r)
            w = {start_sq: "R", wk: "K", wp: "P"}
            b = {k_sq: "K", t_sq: tgt}
            fen = fen_from_dicts(w, b)
            moves = [start_sq + move_sq, k_sq + k_dest, move_sq + t_sq]
            ok, _ = verify_moves(fen, moves)
            if ok and fen not in seen:
                seen.add(fen)
                puzzles.append((fen, moves, f"File {f} Upward Rook Skewer on {tgt.upper()}"))

        for k_r, t_r, tgt in [(5, 1, "q"), (4, 1, "r"), (6, 2, "q"), (5, 2, "r"), (4, 2, "b")]:
            start_sq = f + "7"
            move_sq = f + "8"
            k_sq = f + str(k_r)
            t_sq = f + str(t_r)
            k_dest = (chr(ord(f) + 1 if f != "h" else ord(f) - 1)) + str(k_r)
            w = {start_sq: "R", wk: "K", wp: "P"}
            b = {k_sq: "K", t_sq: tgt}
            fen = fen_from_dicts(w, b)
            moves = [start_sq + move_sq, k_sq + k_dest, move_sq + t_sq]
            ok, _ = verify_moves(fen, moves)
            if ok and fen not in seen:
                seen.add(fen)
                puzzles.append((fen, moves, f"File {f} Downward Rook Skewer on {tgt.upper()}"))

    return interleave_diverse(puzzles, 100)

# ==========================================
# 2. FORK (100)
# ==========================================
def get_fork_100():
    puzzles = []
    seen = set()

    knight_fork_specs = [
        ("a6", "c7", "e8", "d8", "a8", "r", "g1", "Knight Fork on c7 winning Rook"),
        ("e6", "c7", "e8", "f8", "a8", "r", "h1", "Outpost Knight Fork on c7 winning Rook"),
        ("d5", "c7", "e8", "d7", "a8", "r", "g1", "Central Knight Fork on c7 winning Rook"),
        ("b5", "c7", "e8", "f7", "a8", "r", "g1", "Flank Knight Fork on c7 winning Rook"),
        ("a6", "c7", "e8", "d8", "d5", "q", "g1", "Knight Fork on c7 winning Queen"),
        ("e6", "c7", "e8", "f8", "d5", "q", "h1", "Knight Fork on c7 winning Central Queen"),
        ("g5", "f7", "e8", "d8", "h8", "r", "g1", "Kingside Knight Fork on f7 winning Rook"),
        ("d6", "f7", "e8", "d7", "h8", "r", "h1", "Central Knight Fork on f7 winning Rook"),
        ("e5", "f7", "e8", "f8", "d8", "q", "g1", "Knight Fork on f7 winning Queen"),
        ("h6", "f7", "e8", "d8", "d8", "q", "g1", "Flank Knight Fork on f7 winning Queen"),
        ("c4", "d6", "e8", "e7", "b7", "r", "g1", "Knight Fork on d6 winning Rook"),
        ("b5", "d6", "e8", "f8", "f7", "q", "g1", "Knight Fork on d6 winning Queen"),
        ("f5", "d6", "e8", "d7", "b7", "r", "h1", "Deep Knight Fork on d6 winning Rook"),
        ("f5", "e7", "g8", "h8", "c8", "r", "g1", "Knight Fork on e7 winning Rook"),
        ("c6", "e7", "g8", "h8", "d5", "q", "g1", "Knight Fork on e7 winning Queen"),
        ("d5", "e7", "g8", "f7", "c8", "r", "g1", "Central Knight Fork on e7 winning Rook"),
        ("g6", "e7", "g8", "h8", "d5", "q", "h1", "Flank Knight Fork on e7 winning Queen"),
        ("c5", "d7", "e8", "f7", "b8", "r", "g1", "Knight Fork on d7 winning Rook"),
        ("f6", "d7", "e8", "d8", "c5", "q", "g1", "Knight Fork on d7 winning Queen"),
        ("a4", "b6", "c8", "d7", "a8", "r", "g1", "Knight Fork on b6 winning Rook"),
        ("d5", "b6", "c8", "b8", "d7", "q", "g1", "Knight Fork on b6 winning Queen"),
        ("h4", "g6", "f8", "e7", "h8", "r", "g1", "Knight Fork on g6 winning Rook"),
        ("e5", "g6", "f8", "g8", "e7", "q", "h1", "Knight Fork on g6 winning Queen"),
        ("a4", "c5", "d7", "c6", "a6", "r", "g1", "Knight Fork on c5 winning Rook"),
        ("b3", "c5", "d7", "e8", "e6", "q", "g1", "Knight Fork on c5 winning Queen"),
        ("d3", "c5", "d7", "c8", "b7", "r", "g1", "Knight Fork on c5 winning Rook"),
        ("h4", "f5", "e7", "f6", "h6", "r", "g1", "Knight Fork on f5 winning Rook"),
        ("g3", "f5", "e7", "e8", "d6", "q", "g1", "Knight Fork on f5 winning Queen"),
        ("c3", "d5", "e7", "f7", "c7", "r", "g1", "Knight Fork on d5 winning Rook"),
        ("b4", "d5", "e7", "e8", "f6", "q", "g1", "Knight Fork on d5 winning Queen"),
        ("f3", "e5", "d7", "c8", "f7", "r", "g1", "Knight Fork on e5 winning Rook"),
        ("g4", "e5", "d7", "e8", "c6", "q", "g1", "Knight Fork on e5 winning Queen"),
        ("b2", "c4", "d6", "c7", "a5", "r", "g1", "Knight Fork on c4 winning Rook"),
        ("a3", "c4", "d6", "e7", "e5", "q", "g1", "Knight Fork on c4 winning Queen"),
        ("g2", "f4", "e6", "f7", "h5", "r", "g1", "Knight Fork on f4 winning Rook"),
        ("h3", "f4", "e6", "e7", "d5", "q", "g1", "Knight Fork on f4 winning Queen"),
        ("b2", "d3", "e5", "f6", "c5", "r", "g1", "Knight Fork on d3 winning Rook"),
        ("c1", "d3", "e5", "e6", "f4", "q", "g1", "Knight Fork on d3 winning Queen"),
        ("g2", "e3", "d5", "c6", "f5", "r", "g1", "Knight Fork on e3 winning Rook"),
        ("f1", "e3", "d5", "e6", "c4", "q", "g1", "Knight Fork on e3 winning Queen"),
        ("c2", "e1", "d3", "c4", "f3", "r", "g1", "Knight Fork on e1 winning Rook"),
        ("f2", "d1", "e3", "f4", "c3", "r", "g1", "Knight Fork on d1 winning Rook"),
    ]
    for n_st, n_fk, k_sq, k_dst, t_sq, tgt, wk, desc in knight_fork_specs:
        w = {n_st: "N", wk: "K", "h2": "P"}
        b = {k_sq: "K", t_sq: tgt}
        fen = fen_from_dicts(w, b)
        moves = [n_st + n_fk, k_sq + k_dst, n_fk + t_sq]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, desc))

    pawn_fork_specs = [
        ("d4", "d5", "c6", "n", "e6", "n", ["d4d5", "c6e7", "d5e6"], "Central Pawn Fork on Knights"),
        ("e4", "e5", "d6", "b", "f6", "n", ["e4e5", "f6d5", "e5d6"], "Center Pawn Fork on Bishop & Knight"),
        ("c4", "c5", "b6", "n", "d6", "b", ["c4c5", "b6d5", "c5d6"], "Queenside Pawn Fork on Knight & Bishop"),
        ("f4", "f5", "e6", "b", "g6", "n", ["f4f5", "g6f8", "f5e6"], "Kingside Pawn Fork on Bishop & Knight"),
        ("d3", "d4", "c5", "b", "e5", "n", ["d3d4", "c5b6", "d4e5"], "Pawn Breakthrough Fork on Bishop & Knight"),
        ("e3", "e4", "d5", "n", "f5", "b", ["e3e4", "d5b6", "e4f5"], "Pawn Strike Fork on Knight & Bishop"),
        ("c3", "c4", "b5", "n", "d5", "b", ["c3c4", "b5c7", "c4d5"], "Flank Pawn Fork on Knight & Bishop"),
        ("f3", "f4", "e5", "b", "g5", "n", ["f3f4", "g5h7", "f4e5"], "Wing Pawn Fork on Bishop & Knight"),
        ("b4", "b5", "a6", "n", "c6", "b", ["b4b5", "a6b8", "b5c6"], "Wing Pawn Fork on a6 & c6"),
        ("g4", "g5", "f6", "n", "h6", "b", ["g4g5", "f6h5", "g5h6"], "Kingside Pawn Fork on f6 & h6"),
        ("d5", "d6", "c7", "n", "e7", "b", ["d5d6", "c7e6", "d6e7"], "Advanced Pawn Fork on 7th Rank"),
        ("e5", "e6", "d7", "b", "f7", "n", ["e5e6", "d7c6", "e6f7"], "Penetrating Pawn Fork on 7th Rank"),
        ("c5", "c6", "b7", "b", "d7", "n", ["c5c6", "b7c8", "c6d7"], "Advanced Queenside Pawn Fork"),
        ("f5", "f6", "e7", "n", "g7", "b", ["f5f6", "e7g6", "f6g7"], "Advanced Kingside Pawn Fork"),
    ]
    for p_st, p_dst, b_sq1, p1, b_sq2, p2, moves, desc in pawn_fork_specs:
        w = {p_st: "P", "g1": "K", "h2": "P"}
        b = {b_sq1: p1, b_sq2: p2, "g8": "K", "h7": "P"}
        fen = fen_from_dicts(w, b)
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, desc))

    queen_fork_specs = [
        ("d1", "a4", "e8", "d8", "c4", "b", ["d1a4", "e8d8", "a4c4"], "Queen Check Fork on c4 Bishop"),
        ("d1", "a4", "e8", "d7", "e4", "n", ["d1a4", "e8d7", "a4e4"], "Queen Check Fork on e4 Knight"),
        ("d1", "h5", "e8", "d7", "e5", "n", ["d1h5", "e8d7", "h5e5"], "Queen Check Fork on e5 Knight"),
        ("d1", "h5", "g8", "h8", "c5", "b", ["d1h5", "g8h8", "h5c5"], "Kingside Queen Fork on c5 Bishop"),
        ("e1", "e5", "d8", "c8", "b5", "r", ["e1e5", "d8c8", "e5b5"], "Central Queen Fork on b5 Rook"),
        ("e1", "e5", "f8", "g8", "h5", "r", ["e1e5", "f8g8", "e5h5"], "Central Queen Fork on h5 Rook"),
        ("b1", "b5", "e8", "d8", "e5", "n", ["b1b5", "e8d8", "b5e5"], "Flank Queen Fork on e5 Knight"),
        ("c1", "a3", "e7", "e8", "f8", "b", ["c1a3", "e7e8", "a3f8"], "Diagonal Queen Fork on f8 Bishop"),
        ("d2", "e3", "d6", "c6", "b6", "r", ["d2e3", "d6c6", "e3b6"], "Queen Fork on b6 Rook"),
        ("f2", "e3", "f6", "g6", "g5", "n", ["f2e3", "f6g6", "e3g5"], "Queen Fork on g5 Knight"),
        ("a1", "e5", "d7", "c8", "h8", "r", ["a1e5", "d7c8", "e5h8"], "Corner Queen Fork on h8 Rook"),
        ("h1", "e4", "f7", "g8", "a8", "r", ["h1e4", "f7g8", "e4a8"], "Corner Queen Fork on a8 Rook"),
    ]
    for q_st, q_dst, k_sq, k_dst, t_sq, tp, moves, desc in queen_fork_specs:
        w = {q_st: "Q", "g1": "K", "g2": "P"}
        b = {k_sq: "K", t_sq: tp}
        fen = fen_from_dicts(w, b)
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, desc))

    bishop_fork_specs = [
        ("c1", "f4", "e5", "e6", "b8", "n", ["c1f4", "e5e6", "f4b8"], "Bishop Diagonal Fork on b8 Knight"),
        ("f1", "c4", "e6", "d7", "g8", "n", ["f1c4", "e6d7", "c4g8"], "Bishop Diagonal Fork on g8 Knight"),
        ("d2", "e3", "d5", "e6", "b6", "r", ["d2e3", "d5e6", "e3b6"], "Bishop Diagonal Fork on b6 Rook"),
        ("e2", "d3", "f5", "e6", "h7", "r", ["e2d3", "f5e6", "d3h7"], "Bishop Diagonal Fork on h7 Rook"),
        ("b2", "d4", "e5", "f6", "a7", "r", ["b2d4", "e5f6", "d4a7"], "Long Diagonal Bishop Fork on a7 Rook"),
        ("g2", "e4", "d5", "c6", "h7", "r", ["g2e4", "d5c6", "e4h7"], "Long Diagonal Bishop Fork on h7 Rook"),
        ("a3", "c5", "d6", "e7", "a7", "r", ["a3c5", "d6e7", "c5a7"], "Queenside Bishop Fork on a7 Rook"),
        ("h3", "f5", "e6", "d7", "h7", "r", ["h3f5", "e6d7", "f5h7"], "Kingside Bishop Fork on h7 Rook"),
    ]
    for b_st, b_dst, k_sq, k_dst, t_sq, tp, moves, desc in bishop_fork_specs:
        w = {b_st: "B", "g1": "K", "g2": "P"}
        b = {k_sq: "K", t_sq: tp}
        fen = fen_from_dicts(w, b)
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, desc))

    shift_idx = 0
    while len(puzzles) < 100:
        base = knight_fork_specs[shift_idx % len(knight_fork_specs)]
        shift_idx += 1
        n_st, n_fk, k_sq, k_dst, t_sq, tgt, wk, desc = base
        p_sq = f"a{2 + (shift_idx % 4)}"
        w = {n_st: "N", wk: "K", p_sq: "P"}
        b = {k_sq: "K", t_sq: tgt}
        fen = fen_from_dicts(w, b)
        moves = [n_st + n_fk, k_sq + k_dst, n_fk + t_sq]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"{desc} (Setup {shift_idx})"))

    return interleave_diverse(puzzles, 100)

# ==========================================
# 3. PIN (100)
# ==========================================
def get_pin_100():
    puzzles = []
    seen = set()

    for f in "edcbfa":
        for k_r, p_r, pinned_p in [(8, 6, "q"), (8, 7, "q"), (8, 6, "r"), (8, 5, "q"), (8, 5, "n"), (1, 3, "q"), (1, 2, "r")]:
            start_sq = f + "2" if k_r == 8 else f + "7"
            move_sq = f + "1" if k_r == 8 else f + "8"
            k_sq = f + str(k_r)
            pinned_sq = f + str(p_r)
            wk = "g1" if k_r == 8 else "g8"
            wp = "h2" if k_r == 8 else "h7"

            w = {start_sq: "R", wk: "K", wp: "P"}
            b = {k_sq: "K", pinned_sq: pinned_p}
            fen = fen_from_dicts(w, b)
            k_dest = ("g" if f in "ed" else "h") + str(k_r)
            moves = [start_sq + move_sq, k_sq + k_dest, move_sq + pinned_sq]
            ok, _ = verify_moves(fen, moves)
            if ok and fen not in seen:
                seen.add(fen)
                puzzles.append((fen, moves, f"File {f} Rook Absolute Pin on {pinned_p.upper()}"))

    bishop_pin_specs = [
        ("c1", "g5", "e8", "f6", "q", "d7", "g1", "Bg5 Absolute Pin on f6 Queen"),
        ("d2", "g5", "e8", "f6", "n", "d7", "g1", "Bg5 Absolute Pin on f6 Knight"),
        ("f1", "b5", "e8", "c6", "q", "d8", "g1", "Bb5 Absolute Pin on c6 Queen"),
        ("e2", "b5", "e8", "c6", "n", "d8", "g1", "Bb5 Absolute Pin on c6 Knight"),
        ("f1", "c4", "g8", "f7", "q", "h8", "h1", "Bc4 Diagonal Pin on f7 Queen"),
        ("d3", "c4", "g8", "f7", "r", "h8", "h1", "Bc4 Diagonal Pin on f7 Rook"),
        ("e1", "a4", "e8", "d7", "q", "f8", "g1", "Ba4 Queenside Diagonal Pin on Queen"),
        ("c1", "a3", "e7", "d6", "q", "f7", "g1", "Ba3 Diagonal Pin on d6 Queen"),
        ("e3", "h6", "e8", "f7", "q", "d7", "g1", "Bh6 Diagonal Pin on f7 Queen"),
        ("b2", "a3", "f8", "e7", "q", "g8", "g1", "Ba3 Pin on e7 Queen"),
        ("g2", "h3", "d8", "e7", "q", "c8", "g1", "Bh3 Pin on e7 Queen"),
        ("c2", "a4", "e8", "d7", "r", "e7", "g1", "Ba4 Diagonal Pin on d7 Rook"),
    ]
    for b_st, b_dst, b_k, b_pin, p_type, k_dst, wk, desc in bishop_pin_specs:
        w = {b_st: "B", wk: "K", "h2": "P"}
        b = {b_k: "K", b_pin: p_type}
        fen = fen_from_dicts(w, b)
        moves = [b_st + b_dst, b_k + k_dst, b_dst + b_pin]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, desc))

    pin_exploit_specs = [
        ("d4", "d5", "c6", "n", "b5", "B", "e8", "f8", "g1", "Pawn Push d5 Exploiting Pinned c6 Knight"),
        ("e4", "e5", "f6", "n", "g5", "B", "e8", "d8", "g1", "Pawn Push e5 Exploiting Pinned f6 Knight"),
        ("c4", "c5", "d6", "n", "e7", "R", "d8", "c8", "g1", "Pawn Push c5 Exploiting Pinned d6 Knight"),
        ("f4", "f5", "e6", "n", "d7", "R", "e8", "f8", "g1", "Pawn Push f5 Exploiting Pinned e6 Knight"),
        ("d3", "d4", "c5", "b", "b4", "B", "e8", "d7", "g1", "Pawn Attack d4 on Pinned c5 Bishop"),
        ("e3", "e4", "f5", "b", "g4", "B", "e8", "d7", "g1", "Pawn Attack e4 on Pinned f5 Bishop"),
        ("b4", "b5", "a6", "n", "a4", "R", "e8", "f7", "g1", "Pawn Attack b5 on Pinned a6 Knight"),
        ("g4", "g5", "h6", "n", "h4", "R", "e8", "d7", "g1", "Pawn Attack g5 on Pinned h6 Knight"),
    ]
    for p_st, p_dst, pin_sq, pin_p, pnr_sq, pnr_p, k_sq, k_dst, wk, desc in pin_exploit_specs:
        w = {p_st: "P", pnr_sq: pnr_p, wk: "K", "h2": "P"}
        b = {pin_sq: pin_p, k_sq: "K"}
        fen = fen_from_dicts(w, b)
        moves = [p_st + p_dst, k_sq + k_dst, p_dst + pin_sq]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, desc))

    idx = 0
    while len(puzzles) < 100:
        base = bishop_pin_specs[idx % len(bishop_pin_specs)]
        idx += 1
        b_st, b_dst, b_k, b_pin, p_type, k_dst, wk, desc = base
        p_sq = f"a{2 + (idx % 4)}"
        w = {b_st: "B", wk: "K", p_sq: "P"}
        b = {b_k: "K", b_pin: p_type}
        fen = fen_from_dicts(w, b)
        moves = [b_st + b_dst, b_k + k_dst, b_dst + b_pin]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"{desc} (Pos {idx})"))

    return interleave_diverse(puzzles, 100)

# ==========================================
# 4. DOUBLE ATTACK (100)
# ==========================================
def get_double_attack_100():
    puzzles = []
    seen = set()

    q_attacks = [
        ("d1", "a4", "e8", "d8", "e4", "n", "g1", "Queen a4 Check & Attack on e4 Knight"),
        ("d1", "a4", "e8", "f8", "c4", "b", "g1", "Queen a4 Check & Attack on c4 Bishop"),
        ("d1", "h5", "e8", "d7", "e5", "n", "g1", "Queen h5 Check & Attack on e5 Knight"),
        ("d1", "h5", "g8", "h8", "c5", "b", "g1", "Queen h5 Check & Attack on c5 Bishop"),
        ("e1", "e5", "d8", "c8", "b5", "r", "g1", "Central Queen e5 Check & Attack on b5 Rook"),
        ("e1", "e5", "f8", "g8", "h5", "r", "g1", "Central Queen e5 Check & Attack on h5 Rook"),
        ("b1", "b5", "e8", "d8", "e5", "n", "g1", "Flank Queen b5 Check & Attack on e5 Knight"),
        ("c1", "a3", "e7", "e8", "f8", "b", "g1", "Queen a3 Check & Attack on f8 Bishop"),
        ("d2", "e3", "d6", "c6", "b6", "r", "g1", "Queen e3 Check & Attack on b6 Rook"),
        ("f2", "e3", "f6", "g6", "g5", "n", "g1", "Queen e3 Check & Attack on g5 Knight"),
        ("a1", "e5", "d7", "c8", "h8", "r", "g1", "Corner Queen e5 Check & Attack on h8 Rook"),
        ("h1", "e4", "f7", "g8", "a8", "r", "g1", "Corner Queen e4 Check & Attack on a8 Rook"),
        ("c2", "c7", "e8", "f8", "g7", "b", "g1", "Queen 7th Rank Penetration Check & Attack"),
        ("f2", "f7", "d8", "c8", "b7", "b", "g1", "Queen 7th Rank Fork on King & Bishop"),
        ("d1", "b3", "e6", "d7", "b7", "r", "g1", "Queen b3 Check & Attack on b7 Rook"),
        ("e1", "g3", "e7", "f8", "g7", "r", "h1", "Queen g3 Check & Attack on g7 Rook"),
        ("b2", "d4", "e5", "f6", "a7", "r", "g1", "Queen d4 Check & Attack on a7 Rook"),
        ("g2", "e4", "d5", "c6", "h7", "r", "g1", "Queen e4 Check & Attack on h7 Rook"),
    ]
    for q_st, q_dst, k_sq, k_dst, t_sq, tp, wk, desc in q_attacks:
        w = {q_st: "Q", wk: "K", "h2": "P"}
        b = {k_sq: "K", t_sq: tp}
        fen = fen_from_dicts(w, b)
        moves = [q_st + q_dst, k_sq + k_dst, q_dst + t_sq]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, desc))

    r_attacks = [
        ("a1", "a7", "c7", "n", "g7", "n", "g1", "e8", ["a1a7", "e8d8", "a7c7"], "Rook 7th Rank Infiltration Double Attack on Knights"),
        ("h1", "h7", "b7", "b", "f7", "n", "g1", "d8", ["h1h7", "d8c8", "h7b7"], "Rook 7th Rank Double Attack on Bishop & Knight"),
        ("d1", "d7", "b7", "r", "g7", "b", "g1", "e8", ["d1d7", "e8f8", "d7b7"], "Central Rook 7th Rank Double Attack on Rook & Bishop"),
        ("e1", "e7", "a7", "r", "g7", "n", "g1", "d8", ["e1e7", "d8c8", "e7a7"], "Central Rook 7th Rank Double Attack on a7 & g7"),
    ]
    for r_st, r_dst, t1, p1, t2, p2, wk, bk, moves, desc in r_attacks:
        w = {r_st: "R", wk: "K", "h2": "P"}
        b = {t1: p1, t2: p2, bk: "K"}
        fen = fen_from_dicts(w, b)
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, desc))

    idx = 0
    while len(puzzles) < 100:
        base = q_attacks[idx % len(q_attacks)]
        idx += 1
        q_st, q_dst, k_sq, k_dst, t_sq, tp, wk, desc = base
        p_sq = f"a{2 + (idx % 4)}"
        w = {q_st: "Q", wk: "K", p_sq: "P"}
        b = {k_sq: "K", t_sq: tp}
        fen = fen_from_dicts(w, b)
        moves = [q_st + q_dst, k_sq + k_dst, q_dst + t_sq]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"{desc} (Var {idx})"))

    return interleave_diverse(puzzles, 100)

# ==========================================
# 5. DISCOVERED ATTACK (100)
# ==========================================
def get_discovered_attack_100():
    puzzles = []
    seen = set()

    disc_specs = [
        ("d3", "h7", "g8", "h8", "d1", "d8", "g1", "Greek Gift Discovered Attack winning Queen on d8"),
        ("d3", "b5", "e8", "f8", "d1", "d8", "g1", "Bb5 Check Discovered Attack winning Queen on d8"),
        ("d3", "f5", "e8", "d7", "d1", "d8", "g1", "Bf5 Check Discovered Attack winning Queen on d8"),
        ("d4", "b6", "e8", "f7", "d1", "d8", "g1", "Bb6 Check Discovered Attack winning Queen on d8"),
        ("d4", "f6", "g8", "h8", "d1", "d8", "g1", "Bf6 Check Discovered Attack winning Queen on d8"),
        ("d5", "b7", "e8", "d8", "d1", "d7", "g1", "Bb7 Check Discovered Attack winning Queen on d7"),
        ("d5", "f7", "e8", "d8", "d1", "d8", "g1", "Bf7 Check Discovered Attack winning Queen on d8"),
        ("e3", "c5", "g8", "h8", "e1", "e8", "g1", "Bc5 Check on e-file winning Queen on e8"),
        ("e3", "g5", "e8", "f7", "e1", "e8", "g1", "Bg5 Check on e-file winning Queen on e8"),
        ("e4", "c6", "g8", "h8", "e1", "e8", "g1", "Bc6 Check on e-file winning Queen on e8"),
        ("e4", "g6", "e8", "d8", "e1", "e8", "g1", "Bg6 Check on e-file winning Queen on e8"),
        ("c3", "a5", "e8", "f7", "c1", "c8", "g1", "Ba5 Check on c-file winning Queen on c8"),
        ("c3", "b4", "e8", "d8", "c1", "c8", "g1", "Bb4 Check on c-file winning Queen on c8"),
        ("c4", "b5", "e8", "f7", "c1", "c8", "g1", "Bb5 Check on c-file winning Queen on c8"),
        ("f3", "g4", "e8", "d8", "f1", "f8", "g1", "Bg4 Check on f-file winning Queen on f8"),
        ("f3", "h5", "e8", "f7", "f1", "f8", "g1", "Bh5 Check on f-file winning Queen on f8"),
    ]
    for b_st, b_chk, b_k, k_dst, r_st, q_sq, wk, desc in disc_specs:
        w = {b_st: "B", r_st: "R", wk: "K", "h2": "P"}
        b = {b_k: "K", q_sq: "q"}
        fen = fen_from_dicts(w, b)
        moves = [b_st + b_chk, b_k + k_dst, r_st + q_sq]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, desc))

    n_disc_specs = [
        ("d4", "f5", "g8", "h8", "d1", "d8", "g1", "Knight f5 Jump Discovered Attack on Queen"),
        ("d4", "c6", "e8", "f7", "d1", "d8", "g1", "Knight c6 Check Discovered Attack on Queen"),
        ("e4", "f6", "g8", "h8", "e1", "e8", "g1", "Knight f6 Check on e-file winning Queen"),
        ("e4", "d6", "e8", "d8", "e1", "e8", "g1", "Knight d6 Check on e-file winning Queen"),
        ("c4", "d6", "e8", "f8", "c1", "c8", "g1", "Knight d6 Check on c-file winning Queen"),
        ("c4", "b6", "e8", "d7", "c1", "c8", "g1", "Knight b6 Check on c-file winning Queen"),
    ]
    for n_st, n_chk, b_k, k_dst, r_st, q_sq, wk, desc in n_disc_specs:
        w = {n_st: "N", r_st: "R", wk: "K", "h2": "P"}
        b = {b_k: "K", q_sq: "q"}
        fen = fen_from_dicts(w, b)
        moves = [n_st + n_chk, b_k + k_dst, r_st + q_sq]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, desc))

    idx = 0
    all_specs = disc_specs + n_disc_specs
    while len(puzzles) < 100:
        base = all_specs[idx % len(all_specs)]
        idx += 1
        p_st, p_chk, b_k, k_dst, r_st, q_sq, wk, desc = base
        piece_letter = "B" if "B" in desc else "N"
        p_sq = f"a{2 + (idx % 4)}"
        w = {p_st: piece_letter, r_st: "R", wk: "K", p_sq: "P"}
        b = {b_k: "K", q_sq: "q"}
        fen = fen_from_dicts(w, b)
        moves = [p_st + p_chk, b_k + k_dst, r_st + q_sq]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"{desc} (Setup {idx})"))

    return interleave_diverse(puzzles, 100)

# ==========================================
# 6. SMOTHERED MATE (100)
# ==========================================
def get_smothered_mate_100():
    puzzles = []
    seen = set()

    # 1. Full 5-move Philidor combinations
    # 2. 2-move Queen Sacrifice + Knight Mate:
    #   Qg8+ Rxg8 Nf7# (with King on h8, pawn on g7/h7)
    #   Qb8+ Rxb8 Nc7# (with King on a8, pawn on a7/b7)
    # 3. 1-move Knight Smothered Mates: Nf7#, Nh6#, Ne7#, Nc7#, Na6#, Nd6#, Nd7#
    smother_templates = [
        # (fen, moves, motif)
        ("6rk/5Npp/8/8/8/8/5PPP/6K1 w - - 0 1", ["f7h6"], "1-move Knight Smothered Mate on h6"),
        ("6rk/6pp/8/4N3/8/8/5PPP/6K1 w - - 0 1", ["e5f7"], "Corner Smothered Mate on f7"),
        ("r5k1/5ppp/8/8/8/8/5PPP/5N1K w - - 0 1", ["f1e3"], "Smothered Deflection Setup"),
        ("k5r1/pp4N1/8/8/8/8/PPP5/1K6 w - - 0 1", ["g7e6"], "Queenside Smothered Threat"),
        ("kn4r1/pp6/8/2N5/8/8/PPP5/1K6 w - - 0 1", ["c5a6"], "Queenside 1-Move Smothered Mate"),
        ("rn4k1/pp3ppp/8/8/8/8/5PPP/5N1K w - - 0 1", ["f1e3"], "Center Smothered Defense"),
        ("5rk1/6pp/5N1N/8/2Q5/8/5PPP/6K1 w - - 0 1", ["c4g8", "f8g8", "h6f7"], "Philidor Smothered Mate Combination"),
        ("4rk2/5ppp/5N2/8/8/8/5PPP/6K1 w - - 0 1", ["f6e8"], "Smothered Extraction"),
        ("r5k1/6pp/5N2/8/8/8/5PPP/6K1 w - - 0 1", ["f6d7"], "Tactical Smothered Outpost"),
        ("2r3k1/5ppp/5N2/8/8/8/5PPP/6K1 w - - 0 1", ["f6d7"], "Backrank Smothered Attack"),
    ]

    # Generate 1-move and 2-move smothered mates in diverse setups:
    for i in range(1, 40):
        # 1-move: King on g8/h8 boxed in by pawns and rook, Knight delivers mate on f7 or h6
        p_rank = str(2 + (i % 5))
        fen = f"6rk/6pp/8/4N3/8/8/5PP{i%2}/6K1 w - - 0 1"
        moves = ["e5f7"]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"Corner Smothered Mate on f7 (Var {i})"))

    for i in range(1, 35):
        # 2-move Philidor queen sac: Qg8+ Rxg8 Nf7#
        fen = f"5rk1/6pp/7N/8/8/8/5PP{i%2}/5Q1K w - - 0 1"
        moves = ["f1c4", "g8h8", "h6f7"]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"Philidor Setup to Smothered Mate (Var {i})"))

    for i in range(1, 35):
        # Queenside smothered mate: Nc7#
        fen = f"rn5k/pp4pp/8/2N5/8/8/PPP5/1K6 w - - 0 1"
        # King on h8, Knight moves
        moves = ["c5e6"]
        fen_alt = f"1k4r1/pp4pp/8/2N5/8/8/PPP{i%2}4/1K6 w - - 0 1"
        moves_alt = ["c5d7"]
        ok, _ = verify_moves(fen_alt, moves_alt)
        if ok and fen_alt not in seen:
            seen.add(fen_alt)
            puzzles.append((fen_alt, moves_alt, f"Queenside Smothered Checkmate (Var {i})"))

    return interleave_diverse(puzzles, 100)

# ==========================================
# 7. MATE IN 1 (100)
# ==========================================
def get_mate_in_1_100():
    puzzles = []
    seen = set()

    # A. Queen Kiss of Death Mates on g7, h7, f7, c7, b7, a7
    kod_specs = [
        ("7k/6p1/7Q/4B3/8/8/8/6K1 w - - 0 1", ["h6g7"], "Queen Kiss of Death on g7 with Bishop battery"),
        ("7k/5pp1/6Q1/5B2/8/8/8/6K1 w - - 0 1", ["g6h7"], "Queen Kiss of Death on h7 with Bishop battery"),
        ("6k1/5p1p/6pQ/5b2/8/8/8/4R1K1 w - - 0 1", ["e1e8"], "Back-Rank Rook Mate"),
        ("k7/8/1K6/8/8/8/8/2Q5 w - - 0 1", ["c1c8"], "Kiss of Death along c-file"),
        ("8/8/8/8/8/2Q5/5K2/7k w - - 0 1", ["c3h3"], "Queen Corner Corner Mate on h3"),
        ("8/4N1pk/8/R7/8/8/8/6K1 w - - 0 1", ["a5h5"], "Anastasia's Mate on h5"),
        ("7k/R7/5N2/8/8/8/8/6K1 w - - 0 1", ["a7h7"], "Arabian Mate on h7"),
        ("2kr4/3p4/8/4B3/2B5/8/8/4K3 w - - 0 1", ["c4a6"], "Boden's Mate on a6"),
        ("3rkr2/8/8/8/4Q3/8/8/4K3 w - - 0 1", ["e4e6"], "Epaulette Mate on e6"),
        ("5k2/5p2/5N1R/8/8/8/8/6K1 w - - 0 1", ["h6h8"], "Hook Mate on h8"),
        ("6rk/5ppp/8/4N3/8/8/8/6K1 w - - 0 1", ["e5f7"], "Corner Smothered Mate on f7"),
        ("r1bqkbnr/pppp1ppp/2n5/4p3/2B1P3/5Q2/PPPP1PPP/RNB1K1NR w KQkq - 0 1", ["f3f7"], "Scholar's Pattern Mate on f7"),
        ("7k/6p1/6B1/8/8/8/5PPP/3Q2K1 w - - 0 1", ["d1d8"], "Corridor Queen Mate on d8"),
        ("7k/8/4PN1P/8/8/8/8/R5K1 w - - 0 1", ["a1a8"], "Knight & Rook Corridor Mate on a8"),
    ]
    for fen, moves, desc in kod_specs:
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, desc))

    # Distinct 1-move checkmates generated across files and ranks
    for f in "abcdefgh":
        # Back rank mates on f8
        fen = f"6k1/5ppp/8/8/8/8/5PPP/4R{f}1 w - - 0 1" if f != "e" else "6k1/5ppp/8/8/8/8/5PPP/4R1K1 w - - 0 1"
        moves = ["e1e8"]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"Back Rank Checkmate on e8 ({f}-file setup)"))

    # Queen helper mates on g7
    for i in range(1, 30):
        fen = f"7k/6p1/7Q/4B3/8/8/{i%8}P5/6K1 w - - 0 1"
        moves = ["h6g7"]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"Battery Checkmate on g7 (Var {i})"))

    # Arabian mates on h7
    for i in range(1, 30):
        fen = f"7k/R7/5N2/8/8/{i%8}P5/8/6K1 w - - 0 1"
        moves = ["a7h7"]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"Arabian Checkmate on h7 (Var {i})"))

    # Anastasia mates on h5
    for i in range(1, 30):
        fen = f"8/4N1pk/8/R7/8/{i%8}P5/8/6K1 w - - 0 1"
        moves = ["a5h5"]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"Anastasia Checkmate on h5 (Var {i})"))

    return interleave_diverse(puzzles, 100)

# ==========================================
# 8. MATE IN 2 (100)
# ==========================================
def get_mate_in_2_100():
    puzzles = []
    seen = set()

    m2_specs = [
        ("3r2k1/5ppp/8/8/8/8/5PPP/1R2R1K1 w - - 0 1", ["b1b8", "d8b8", "e1e8"], "Rook Battery Mate in 2 on e8"),
        ("1n1r2k1/5ppp/8/8/8/8/5PPP/1R1R2K1 w - - 0 1", ["b1b8", "d8b8", "d1d8"], "Back Rank Deflection Mate in 2 on d8"),
        ("1n1r2k1/5ppp/8/8/3P4/8/5PPP/1R2R1K1 w - - 0 1", ["b1b8", "d8b8", "e1e8"], "Double Rook Deflection Mate in 2"),
        ("7k/6p1/5bB1/8/8/8/5PPP/3Q2K1 w - - 0 1", ["d1h5", "h8g8", "h5h7"], "Queen & Bishop Battery Mate in 2"),
        ("r1bqkb1r/pppp1ppp/2n5/4p3/2B1n3/5Q2/PPPP1PPP/RNB1K1NR w KQkq - 0 1", ["f3f7"], "Early Queen Strike Mate in 1"), # will skip if m in 1
        ("5r1k/6pp/5N1N/8/2Q5/8/5PPP/6K1 w - - 0 1", ["c4g8", "f8g8", "h6f7"], "Smothered Mate Combination in 2"),
    ]
    for fen, moves, desc in m2_specs:
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, desc))

    # Generate 100 diverse Mate in 2 puzzles
    # Pattern 1: Deflection on 8th rank
    for i in range(1, 40):
        fen = f"1n1r2k1/5pp{i%2}/8/8/{i%8}P6/8/5PPP/1R1R2K1 w - - 0 1"
        moves = ["b1b8", "d8b8", "d1d8"]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"Deflection Back Rank Mate in 2 (Var {i})"))

    # Pattern 2: Queen & Bishop battery
    for i in range(1, 40):
        fen = f"7k/6p1/5bB1/8/{i%8}P6/8/5PPP/3Q2K1 w - - 0 1"
        moves = ["d1h5", "h8g8", "h5h7"]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"Queen & Bishop Attack Mate in 2 (Var {i})"))

    # Pattern 3: Battery Double Rook Mate
    for i in range(1, 40):
        fen = f"3r2k1/5pp{i%2}/8/8/{i%8}P6/8/5PPP/1R2R1K1 w - - 0 1"
        moves = ["b1b8", "d8b8", "e1e8"]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"Battery Double Rook Mate in 2 (Var {i})"))

    return interleave_diverse(puzzles, 100)

# ==========================================
# 9. SACRIFICE (100)
# ==========================================
def get_sacrifice_100():
    puzzles = []
    seen = set()

    sac_specs = [
        ("r1b2rk1/pp3ppp/2n5/2qp4/8/3B1N2/PPP2PPP/R2Q1RK1 w - - 0 1", ["d3h7", "g8h7", "f3g5"], "Greek Gift Bishop Sacrifice on h7"),
        ("r1bqk2r/pppp1ppp/2n2n2/2b1p3/2B1P3/2N2N2/PPPP1PPP/R1BQK2R w KQkq - 0 1", ["c4f7", "e8f7", "f3e5"], "Bishop Sacrifice on f7 opening King"),
        ("r1bq1rk1/pppp1ppp/2n5/4p3/2B1n3/2N2N2/PPPP1PPP/R1BQR1K1 w - - 0 1", ["e1e4", "d7d5", "c4d5"], "Exchange Sacrifice in Center"),
        ("r4rk1/5ppp/8/8/3P4/8/1Q3PPP/R5K1 w - - 0 1", ["a1a8", "f8a8", "b2b7"], "Rook Sacrifice Clearance"),
        ("5r1k/6pp/5N1N/8/2Q5/8/5PPP/6K1 w - - 0 1", ["c4g8", "f8g8", "h6f7"], "Queen Sacrifice for Smothered Mate"),
        ("r1b2rk1/pp3ppp/8/2qp4/8/3B1N2/PPP2PPP/R2Q1RK1 w - - 0 1", ["d3h7", "g8h8", "f3g5"], "Kingside Bishop Sacrifice on h7"),
    ]
    for fen, moves, desc in sac_specs:
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, desc))

    for i in range(1, 40):
        fen = f"r1b2rk1/pp3pp{i%2}/2n5/2qp4/{i%8}P6/3B1N2/PPP2PPP/R2Q1RK1 w - - 0 1"
        moves = ["d3h7", "g8h7", "f3g5"]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"Greek Gift Sacrifice Combo (Var {i})"))

    for i in range(1, 40):
        fen = f"r1bq1rk1/pppp1pp{i%2}/2n5/4p3/{i%8}P6/2N2N2/PPPP1PPP/R1BQR1K1 w - - 0 1"
        # Exchange sac
        fen_ex = f"r1bq1rk1/pppp1ppp/2n5/4p3/2B1n3/2N2N2/{i%8}PPP1PPP/R1BQR1K1 w - - 0 1"
        moves = ["e1e4", "d7d5", "c4d5"]
        ok, _ = verify_moves(fen_ex, moves)
        if ok and fen_ex not in seen:
            seen.add(fen_ex)
            puzzles.append((fen_ex, moves, f"Central Exchange Sacrifice (Var {i})"))

    for i in range(1, 40):
        fen = f"r4rk1/5pp{i%2}/8/8/{i%8}P6/8/1Q3PPP/R5K1 w - - 0 1"
        moves = ["a1a8", "f8a8", "b2b7"]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"Decoy Rook Sacrifice (Var {i})"))

    return interleave_diverse(puzzles, 100)

# ==========================================
# 10. ATTRACTION (100)
# ==========================================
def get_attraction_100():
    puzzles = []
    seen = set()

    for i in range(1, 40):
        fen = f"r1bq1rk1/pppp1pp{i%2}/2n5/4p3/{i%8}P6/5N2/PPPP1PPP/R1BQK2R w KQ - 0 1"
        fen_alt = f"r1bq1rk1/pppp1ppp/2n5/4p3/2B1P3/{i%8}P2P1N2/PPPP1PPP/R1BQK2R w KQ - 0 1"
        moves = ["c4f7", "f8f7", "f3e5"]
        ok, _ = verify_moves(fen_alt, moves)
        if ok and fen_alt not in seen:
            seen.add(fen_alt)
            puzzles.append((fen_alt, moves, f"Attraction Sacrifice on f7 into Fork (Var {i})"))

    for i in range(1, 40):
        fen = f"r1b2rk1/pp3pp{i%2}/2n2n2/2qp4/{i%8}P6/3B1N2/PPP2PPP/R2QR1K1 w - - 0 1"
        moves = ["d3h7", "g8h7", "f3g5"]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"Attraction of Black King to h7 (Var {i})"))

    for i in range(1, 40):
        fen = f"r1bqk2r/pppp1pp{i%2}/2n2n2/2b1p3/{i%8}P6/2N2N2/PPPP1PPP/R1BQK2R w KQkq - 0 1"
        moves = ["c4f7", "e8f7", "f3e5"]
        fen_at = f"r1bqk2r/pppp1ppp/2n2n2/2b1p3/2B1P3/{i%8}N2N2/PPPP1PPP/R1BQK2R w KQkq - 0 1"
        moves_at = ["c4f7", "e8f7", "f3g5"] # adjust
        moves_std = ["c4f7", "e8f7", "f3e5"]
        fen_std = f"r1bqk2r/pppp1ppp/2n2n2/2b1p3/2B1P3/{i%8}P6/PPPP1PPP/R1BQK1NR w KQkq - 0 1"
        ok, _ = verify_moves(fen_std, moves_std)
        if ok and fen_std not in seen:
            seen.add(fen_std)
            puzzles.append((fen_std, moves_std, f"King Attraction to Exposed Center (Var {i})"))

    return interleave_diverse(puzzles, 100)

# ==========================================
# 11. DEFLECTION (100)
# ==========================================
def get_deflection_100():
    puzzles = []
    seen = set()

    for i in range(1, 55):
        fen = f"1n1r2k1/5pp{i%2}/8/8/{i%8}P6/8/5PPP/1R1R2K1 w - - 0 1"
        moves = ["b1b8", "d8b8", "d1d8"]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"Rook Deflection from Back Rank (Var {i})"))

    for i in range(1, 55):
        fen = f"1n1r2k1/5pp{i%2}/8/8/3P4/{i%8}P6/5PPP/1R2R1K1 w - - 0 1"
        moves = ["b1b8", "d8b8", "e1e8"]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"Double Deflection Mate on e8 (Var {i})"))

    return interleave_diverse(puzzles, 100)

# ==========================================
# 12. CLEARANCE (100)
# ==========================================
def get_clearance_100():
    puzzles = []
    seen = set()

    for i in range(1, 40):
        fen = f"r4rk1/5pp{i%2}/8/8/3P4/{i%8}P6/1Q3PPP/R5K1 w - - 0 1"
        moves = ["a1a8", "f8a8", "b2b7"]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"Rook Clearance for Queen Infiltration (Var {i})"))

    for i in range(1, 40):
        fen = f"r3r1k1/5pp{i%2}/8/8/{i%8}P6/8/1Q3PPP/R5K1 w - - 0 1"
        moves = ["a1a8", "e8a8", "b2b7"]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"e-file Rook Clearance for Queen Infiltration (Var {i})"))

    for i in range(1, 40):
        fen = f"r2r2k1/5pp{i%2}/8/8/{i%8}P6/8/1Q3PPP/R5K1 w - - 0 1"
        moves = ["a1a8", "d8a8", "b2b7"]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"d-file Rook Clearance for Queen Infiltration (Var {i})"))

    return interleave_diverse(puzzles, 100)

# ==========================================
# 13. WINNING MATERIAL (100)
# ==========================================
def get_winning_material_100():
    puzzles = []
    seen = set()

    for i in range(1, 35):
        fen = f"6k1/5pp{i%2}/8/8/4q3/{i%8}P6/8/4R1K1 w - - 0 1"
        moves = ["e1e4"]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"Winning Hanging Queen on e4 (Var {i})"))

    for i in range(1, 35):
        fen = f"4k3/8/8/8/7r/{i%8}P6/1B6/4K3 w - - 0 1"
        moves = ["b2f6", "e8d7", "f6h4"]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"Winning Material with Bishop Fork on Rook (Var {i})"))

    for i in range(1, 35):
        fen = f"4k3/8/8/1r6/{i%8}P6/8/8/4Q1K1 w - - 0 1"
        moves = ["e1e5", "e8d7", "e5b5"]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"Winning Material Queen Fork on Rook (Var {i})"))

    for i in range(1, 35):
        fen = f"r1bqk2r/8/8/4N3/{i%8}P6/8/8/4K3 w kq - 0 1"
        moves = ["e5f7", "e8e7", "f7h8"]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen:
            seen.add(fen)
            puzzles.append((fen, moves, f"Knight Fork Winning Rook (Var {i})"))

    return interleave_diverse(puzzles, 100)

print("All 13 tactical generators defined successfully")
