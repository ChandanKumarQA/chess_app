#!/usr/bin/env python3
import chess
from tactical_builder_core import fen_from_dicts, verify_moves, format_kt_file

def gen_skewer_100():
    puzzles = []
    seen_fens = set()
    seen_first_moves = []

    # 1. Rank skewers (Rooks): Left-to-right & Right-to-left
    for r in range(1, 9):
        r_str = str(r)
        wk = "g1" if r >= 3 else "g8"
        wp = "f2" if r >= 3 else "f7"

        # Left to right
        for k_f, t_f, tgt in [("d", "h", "q"), ("e", "h", "r"), ("c", "g", "q"), ("d", "g", "r"), ("c", "h", "b"), ("e", "g", "n")]:
            start_sq = "b" + r_str
            move_sq = "a" + r_str
            k_sq = k_f + r_str
            t_sq = t_f + r_str
            k_dest_r = str(r + 1 if r < 8 else r - 1)
            k_dest = k_f + k_dest_r

            w = {start_sq: "R", wk: "K", wp: "P"}
            b = {k_sq: "K", t_sq: tgt}
            fen = fen_from_dicts(w, b)
            moves = [start_sq + move_sq, k_sq + k_dest, move_sq + t_sq]

            ok, _ = verify_moves(fen, moves)
            if ok and fen not in seen_fens:
                seen_fens.add(fen)
                puzzles.append((fen, moves, f"Rook Skewer on Rank {r} winning {tgt.upper()}"))

        # Right to left
        for k_f, t_f, tgt in [("e", "a", "q"), ("d", "a", "r"), ("f", "b", "q"), ("e", "b", "r"), ("f", "a", "b"), ("d", "b", "n")]:
            start_sq = "g" + r_str
            move_sq = "h" + r_str
            k_sq = k_f + r_str
            t_sq = t_f + r_str
            k_dest_r = str(r + 1 if r < 8 else r - 1)
            k_dest = k_f + k_dest_r

            w = {start_sq: "R", wk: "K", wp: "P"}
            b = {k_sq: "K", t_sq: tgt}
            fen = fen_from_dicts(w, b)
            moves = [start_sq + move_sq, k_sq + k_dest, move_sq + t_sq]

            ok, _ = verify_moves(fen, moves)
            if ok and fen not in seen_fens:
                seen_fens.add(fen)
                puzzles.append((fen, moves, f"Flank Rook Skewer on Rank {r} winning {tgt.upper()}"))

    # 2. File skewers (Rooks)
    for f in "abcdefgh":
        wk = "a1" if f in "fgh" else "h1"
        wp = "b2" if f in "fgh" else "g2"

        # Upward
        for k_r, t_r, tgt in [(4, 8, "q"), (5, 8, "r"), (3, 7, "q"), (4, 7, "r"), (5, 7, "b")]:
            start_sq = f + "2"
            move_sq = f + "1"
            k_sq = f + str(k_r)
            t_sq = f + str(t_r)
            k_dest_f = chr(ord(f) + 1 if f != "h" else ord(f) - 1)
            k_dest = k_dest_f + str(k_r)

            w = {start_sq: "R", wk: "K", wp: "P"}
            b = {k_sq: "K", t_sq: tgt}
            fen = fen_from_dicts(w, b)
            moves = [start_sq + move_sq, k_sq + k_dest, move_sq + t_sq]

            ok, _ = verify_moves(fen, moves)
            if ok and fen not in seen_fens:
                seen_fens.add(fen)
                puzzles.append((fen, moves, f"Rook Skewer on {f}-file winning {tgt.upper()}"))

        # Downward
        for k_r, t_r, tgt in [(5, 1, "q"), (4, 1, "r"), (6, 2, "q"), (5, 2, "r"), (4, 2, "b")]:
            start_sq = f + "7"
            move_sq = f + "8"
            k_sq = f + str(k_r)
            t_sq = f + str(t_r)
            k_dest_f = chr(ord(f) + 1 if f != "h" else ord(f) - 1)
            k_dest = k_dest_f + str(k_r)

            w = {start_sq: "R", wk: "K", wp: "P"}
            b = {k_sq: "K", t_sq: tgt}
            fen = fen_from_dicts(w, b)
            moves = [start_sq + move_sq, k_sq + k_dest, move_sq + t_sq]

            ok, _ = verify_moves(fen, moves)
            if ok and fen not in seen_fens:
                seen_fens.add(fen)
                puzzles.append((fen, moves, f"Backrank Rook Skewer on {f}-file winning {tgt.upper()}"))

    # 3. Bishop Skewers on diagonals
    bishop_templates = [
        # (w_b_start, w_b_move, b_k, b_k_dest, b_tgt, tgt_piece, wk, desc)
        ("c1", "b2", "g7", "g8", "h8", "q", "g1", "Long Diagonal a1-h8 Bishop Skewer on Queen"),
        ("d2", "c3", "f6", "g6", "g7", "r", "h1", "Central Diagonal a1-h8 Bishop Skewer on Rook"),
        ("e3", "d4", "f6", "e7", "h8", "q", "g1", "Center-Control Bishop Skewer on Queen"),
        ("f1", "g2", "b7", "b8", "a8", "q", "g1", "Fianchetto Bishop Skewer on Queen"),
        ("e2", "f3", "c6", "d7", "b7", "r", "g1", "Long Diagonal h1-a8 Bishop Skewer on Rook"),
        ("d3", "e4", "c6", "b6", "a8", "q", "h1", "Central Bishop Skewer along h1-a8 on Queen"),
        ("f1", "c4", "e6", "e7", "g8", "q", "h1", "Italian Diagonal a2-g8 Bishop Skewer on Queen"),
        ("d3", "c4", "e6", "f6", "g8", "r", "g1", "Italian Diagonal a2-g8 Bishop Skewer on Rook"),
        ("c2", "d3", "f5", "e6", "h7", "q", "g1", "b1-h7 Diagonal Bishop Skewer on Queen"),
        ("b1", "d3", "f5", "g6", "h7", "r", "g1", "b1-h7 Diagonal Bishop Skewer on Rook"),
        ("e3", "f4", "d6", "e7", "b8", "q", "g1", "h2-b8 Diagonal Bishop Skewer on Queen"),
        ("f2", "e3", "d4", "c5", "b8", "r", "g1", "h2-b8 Diagonal Bishop Skewer on Rook"),
        ("f2", "e3", "c5", "d6", "a7", "q", "g1", "g1-a7 Diagonal Bishop Skewer on Queen"),
        ("g1", "e3", "c5", "b6", "a7", "r", "h1", "g1-a7 Diagonal Bishop Skewer on Rook"),
        ("e2", "d3", "f5", "f6", "h7", "q", "g1", "King Hunt Bishop Skewer on Queen"),
        ("a2", "b3", "d5", "e6", "f7", "r", "h1", "Queen-Wing Bishop Skewer on Rook"),
    ]

    for start_sq, move_sq, k_sq, k_dest, t_sq, tgt, wk, desc in bishop_templates:
        w = {start_sq: "B", wk: "K", "h2": "P"}
        b = {k_sq: "K", t_sq: tgt}
        fen = fen_from_dicts(w, b)
        moves = [start_sq + move_sq, k_sq + k_dest, move_sq + t_sq]
        ok, _ = verify_moves(fen, moves)
        if ok and fen not in seen_fens:
            seen_fens.add(fen)
            puzzles.append((fen, moves, desc))

    # Shuffle / interleave so consecutive puzzles differ completely
    # Split by type (Bishop, Rank, File) and interleave
    bishops = [p for p in puzzles if "Bishop" in p[2]]
    ranks = [p for p in puzzles if "Rank" in p[2]]
    files = [p for p in puzzles if "file" in p[2]]

    interleaved = []
    while len(interleaved) < 100:
        if bishops:
            interleaved.append(bishops.pop(0))
        if len(interleaved) < 100 and ranks:
            interleaved.append(ranks.pop(0))
        if len(interleaved) < 100 and files:
            interleaved.append(files.pop(0))
        if not bishops and not ranks and not files:
            break

    # If short of 100, add remaining
    while len(interleaved) < 100:
        for p in puzzles:
            if p not in interleaved:
                interleaved.append(p)
                if len(interleaved) == 100:
                    break

    return interleaved[:100]

res = gen_skewer_100()
print(f"Total Skewers generated: {len(res)}")
for i in range(10):
    print(f"Level {i+1}: {res[i][1]} - {res[i][2]}")
