#!/usr/bin/env python3
import chess
import os
import sys
from tactical_master_builder import fen_from_dicts, verify_puzzle, interleave_diverse

def make_puzzle(white_pieces, black_pieces, moves, motif, seen_fens):
    fen = fen_from_dicts(white_pieces, black_pieces)
    if fen in seen_fens:
        return None
    ok, err = verify_puzzle(fen, moves)
    if not ok:
        return None
    seen_fens.add(fen)
    return (fen, moves, motif)

# ----------------------------------------------------------------------
# EXPANDED MATE IN 2
# ----------------------------------------------------------------------
def gen_mate_in_2_expanded():
    puzzles = []
    seen = set()

    # 1. Anastasia Mate in 2 (N moves with check to e7, King moves to h8, R moves to h5#)
    # White R on ranks 3..6 or files a..f, N jumping to e7 from various squares
    for r_sq in ["a5", "b5", "c5", "d5", "e5", "f5", "a4", "b4", "c4", "d4", "e4", "f4"]:
        for n_sq in ["g6", "g4", "f5", "d5", "c6", "c8"]:
            for wp in ["a2", "b2", "c2", "f2"]:
                if wp in (r_sq, n_sq): continue
                w = {r_sq: "R", n_sq: "N", "g1": "K", wp: "P"}
                b = {"h7": "K", "g7": "p"}
                moves = [n_sq + "e7", "h7h8", r_sq[0] + ("5h5" if r_sq[1]=="5" else "4h4")] # rook along rank
                # rook slides along rank to h5 or h4
                rank = r_sq[1]
                moves = [n_sq + "e7", "h7h8", r_sq + "h" + rank]
                puz = make_puzzle(w, b, moves, f"Anastasia Mate in 2 (R{r_sq} & N{n_sq})", seen)
                if puz: puzzles.append(puz)

    # 2. Arabian Mate in 2 (N jumps to f6 with check, King to g8 or h8, R mates on h7)
    for r_sq in ["a7", "b7", "c7", "d7", "e7", "f7"]:
        for n_sq in ["e4", "d5", "g4", "d7", "e5", "h5"]:
            if n_sq == r_sq: continue
            for wp in ["a2", "b2", "c2", "f2"]:
                w = {r_sq: "R", n_sq: "N", "g1": "K", wp: "P"}
                b = {"h8": "K", "g7": "p"}
                moves = [n_sq + "f6", "h8g8", r_sq + "h7"]
                puz = make_puzzle(w, b, moves, f"Arabian Mate in 2 (R{r_sq} & N{n_sq})", seen)
                if puz: puzzles.append(puz)

    # 3. Philidor Smothered in 2 (Q sac on g8, Rook takes, Knight mates on f7)
    for q_diag in ["c4", "d5", "e6", "a2", "b3"]:
        for n_pos in ["e5", "g5", "h6", "d6"]:
            for wp in ["a2", "b2", "c2", "f2", "d2"]:
                if wp in (q_diag, n_pos): continue
                w = {q_diag: "Q", n_pos: "N", "g1": "K", wp: "P"}
                b = {"h8": "K", "f8": "r", "g7": "p", "h7": "p"}
                moves = [q_diag + "g8", "f8g8", n_pos + "f7"]
                puz = make_puzzle(w, b, moves, f"Philidor Mate in 2 (Q{q_diag} & N{n_pos})", seen)
                if puz: puzzles.append(puz)

    # 4. Corner Rook Sacrifice Decoy into Queen Mate (Rh8+ Kxh8, Qh5#)
    for r_st in ["h1", "h2", "h3", "h4", "h5"]:
        for q_pos in ["d1", "e1", "e2", "f3"]:
            for wp in ["a2", "b2", "c2", "d2"]:
                w = {r_st: "R", q_pos: "Q", "g1": "K", wp: "P"}
                b = {"g8": "K", "f7": "p", "g7": "p", "h7": "p"}
                moves = [r_st + "h8", "g8h8", q_pos + "h5"]
                puz = make_puzzle(w, b, moves, f"Rook Corner Decoy Mate with Q{q_pos}", seen)
                if puz: puzzles.append(puz)

    # 5. Greek Gift Sacrifice Mate in 2 (Bxh7+ Kxh7, Qh5#)
    for b_sq in ["d3", "c2", "b1", "e4"]:
        for q_sq in ["d1", "e1", "f3"]:
            for wp in ["a2", "b2", "c2", "f2"]:
                w = {b_sq: "B", q_sq: "Q", "g1": "K", wp: "P"}
                b = {"g8": "K", "f7": "p", "g7": "p"}
                moves = [b_sq + "h7", "g8h7", q_sq + "h5"]
                puz = make_puzzle(w, b, moves, f"Greek Gift Mate in 2 (B{b_sq} & Q{q_sq})", seen)
                if puz: puzzles.append(puz)

    return interleave_diverse(puzzles, 100)

# ----------------------------------------------------------------------
# EXPANDED SKEWER
# ----------------------------------------------------------------------
def gen_skewer_expanded():
    puzzles = []
    seen = set()

    # Bishop Skewers on Diagonals
    diag_skewers = [
        ("c1", "b2", "g7", "g8", "h8", "q", "a1-h8 Bishop Skewer on Queen"),
        ("d2", "c3", "f6", "g6", "g7", "r", "a1-h8 Bishop Skewer on Rook"),
        ("e3", "d4", "f6", "e7", "h8", "q", "Center a1-h8 Bishop Skewer on Queen"),
        ("f1", "c4", "e6", "e7", "g8", "q", "Italian a2-g8 Bishop Skewer on Queen"),
        ("d3", "c4", "e6", "f6", "g8", "r", "Italian a2-g8 Bishop Skewer on Rook"),
        ("c2", "d3", "f5", "e6", "h7", "q", "b1-h7 Bishop Skewer on Queen"),
        ("b1", "d3", "f5", "g6", "h7", "r", "b1-h7 Bishop Skewer on Rook"),
        ("e3", "f4", "d6", "e7", "b8", "q", "h2-b8 Bishop Skewer on Queen"),
        ("f2", "e3", "c5", "d6", "a7", "q", "g1-a7 Bishop Skewer on Queen"),
        ("e1", "d2", "b4", "c5", "a5", "q", "c1-h6 Bishop Skewer on Queen"),
        ("a2", "b3", "d5", "e6", "f7", "r", "Queen-Wing Bishop Skewer on Rook"),
        ("f1", "g2", "b7", "b8", "a8", "q", "Fianchetto h1-a8 Bishop Skewer on Queen"),
        ("e2", "f3", "c6", "d7", "b7", "r", "h1-a8 Bishop Skewer on Rook"),
        ("d3", "e4", "c6", "b6", "a8", "q", "Center h1-a8 Bishop Skewer on Queen"),
        ("g1", "e3", "c5", "b6", "a7", "r", "g1-a7 Bishop Skewer on Rook"),
    ]
    for b_st, b_mv, k_sq, k_dst, t_sq, tgt, desc in diag_skewers:
        for wp in ["h2", "g2", "f2", "a2", "b2", "c2"]:
            w = {b_st: "B", "g1": "K", wp: "P"}
            b = {k_sq: "K", t_sq: tgt}
            moves = [b_st + b_mv, k_sq + k_dst, b_mv + t_sq]
            puz = make_puzzle(w, b, moves, desc, seen)
            if puz: puzzles.append(puz)

    # Rank Rook Skewers from both flanks
    for r in range(1, 9):
        r_str = str(r)
        # Queenside Rook skewer on Queen/Rook on h-file
        for tgt in ["q", "r"]:
            k_dst = "d" + str(r + 1 if r < 8 else r - 1)
            # Queenside rook
            w = {"b" + r_str: "R", "g1": "K", "c2" if r != 2 else "c7": "P"}
            b = {"d" + r_str: "K", "h" + r_str: tgt}
            moves = ["b" + r_str + "a" + r_str, "d" + r_str + k_dst, "a" + r_str + "h" + r_str]
            puz = make_puzzle(w, b, moves, f"Rank {r} Queenside Rook Skewer on {tgt.upper()}", seen)
            if puz: puzzles.append(puz)

            # Kingside rook
            k_dst2 = "e" + str(r + 1 if r < 8 else r - 1)
            w = {"g" + r_str: "R", "g1": "K", "c2" if r != 2 else "c7": "P"}
            b = {"e" + r_str: "K", "a" + r_str: tgt}
            moves = ["g" + r_str + "h" + r_str, "e" + r_str + k_dst2, "h" + r_str + "a" + r_str]
            puz = make_puzzle(w, b, moves, f"Rank {r} Kingside Rook Skewer on {tgt.upper()}", seen)
            if puz: puzzles.append(puz)

    # File Rook Skewers from top and bottom
    for f in "abcdefgh":
        for tgt in ["q", "r"]:
            # From 1st rank
            w = {f + "2": "R", "h1": "K", "b2" if f != "b" else "b7": "P"}
            b = {f + "4": "K", f + "8": tgt}
            k_dst = (chr(ord(f) + 1) if f != "h" else "g") + "4"
            moves = [f + "2" + f + "1", f + "4" + k_dst, f + "1" + f + "8"]
            puz = make_puzzle(w, b, moves, f"File {f} Upward Rook Skewer on {tgt.upper()}", seen)
            if puz: puzzles.append(puz)

            # From 8th rank
            w = {f + "7": "R", "h1": "K", "b2" if f != "b" else "b7": "P"}
            b = {f + "5": "K", f + "1": tgt}
            k_dst2 = (chr(ord(f) + 1) if f != "h" else "g") + "5"
            moves = [f + "7" + f + "8", f + "5" + k_dst2, f + "8" + f + "1"]
            puz = make_puzzle(w, b, moves, f"File {f} Downward Rook Skewer on {tgt.upper()}", seen)
            if puz: puzzles.append(puz)

    return interleave_diverse(puzzles, 100)

# ----------------------------------------------------------------------
# EXPANDED PIN
# ----------------------------------------------------------------------
def gen_pin_expanded():
    puzzles = []
    seen = set()

    # Bishop Pins
    bishop_pins = [
        ("c1", "g5", "e8", "f6", "n", "e8d7", "Bg5 Absolute Pin on f6 Knight"),
        ("c1", "g5", "e8", "f6", "q", "e8d7", "Bg5 Absolute Pin on f6 Queen"),
        ("d2", "g5", "e8", "f6", "n", "e8f7", "Bg5 Pin on f6 Knight from d2"),
        ("f1", "b5", "e8", "c6", "n", "e8d8", "Bb5 Absolute Pin on c6 Knight"),
        ("f1", "b5", "e8", "c6", "q", "e8d8", "Bb5 Absolute Pin on c6 Queen"),
        ("e2", "b5", "e8", "c6", "n", "e8f7", "Bb5 Pin on c6 Knight from e2"),
        ("f1", "c4", "g8", "f7", "q", "g8h8", "Bc4 Diagonal Pin on f7 Queen"),
        ("d3", "c4", "g8", "f7", "r", "g8h8", "Bc4 Diagonal Pin on f7 Rook"),
        ("e1", "a4", "e8", "d7", "q", "e8f8", "Ba4 Queenside Diagonal Pin on Queen"),
        ("c1", "a3", "e7", "d6", "q", "e7f7", "Ba3 Diagonal Pin on d6 Queen"),
        ("e3", "h6", "e8", "f7", "q", "e8d7", "Bh6 Diagonal Pin on f7 Queen"),
        ("b2", "a3", "f8", "e7", "q", "f8g8", "Ba3 Pin on e7 Queen"),
        ("g2", "h3", "d8", "e7", "q", "d8c8", "Bh3 Pin on e7 Queen"),
        ("c2", "a4", "e8", "d7", "r", "e8e7", "Ba4 Diagonal Pin on d7 Rook"),
    ]
    for b_st, b_dst, b_k, b_pin, p_type, k_mv, desc in bishop_pins:
        for wp in ["h2", "g2", "f2", "b2", "a2", "c2", "d2"]:
            w = {b_st: "B", "g1": "K", wp: "P"}
            b = {b_k: "K", b_pin: p_type}
            moves = [b_st + b_dst, k_mv, b_dst + b_pin]
            puz = make_puzzle(w, b, moves, desc, seen)
            if puz: puzzles.append(puz)

    # Exploiting Pinned pieces with pawn advance
    pawn_pin_exploits = [
        ("d4", "d5", "c6", "n", "b5", "B", "e8", "e8f8", "Pawn Push d5 Exploiting Pinned c6 Knight"),
        ("e4", "e5", "f6", "n", "g5", "B", "e8", "e8d8", "Pawn Push e5 Exploiting Pinned f6 Knight"),
        ("c4", "c5", "d6", "n", "e7", "R", "d8", "d8c8", "Pawn Push c5 Exploiting Pinned d6 Knight"),
        ("f4", "f5", "e6", "n", "d7", "R", "e8", "e8f8", "Pawn Push f5 Exploiting Pinned e6 Knight"),
        ("d3", "d4", "c5", "b", "b4", "B", "e8", "e8d7", "Pawn Attack d4 on Pinned c5 Bishop"),
        ("e3", "e4", "f5", "b", "g4", "B", "e8", "e8d7", "Pawn Attack e4 on Pinned f5 Bishop"),
        ("b4", "b5", "a6", "n", "a4", "R", "e8", "e8f7", "Pawn Attack b5 on Pinned a6 Knight"),
        ("g4", "g5", "h6", "n", "h4", "R", "e8", "e8d7", "Pawn Attack g5 on Pinned h6 Knight"),
    ]
    for p_st, p_dst, pin_sq, pin_p, pnr_sq, pnr_p, k_sq, k_mv, desc in pawn_pin_exploits:
        for wp in ["h2", "g2", "a2", "b2", "f2"]:
            w = {p_st: "P", pnr_sq: pnr_p, "g1": "K", wp: "P"}
            b = {pin_sq: pin_p, k_sq: "K"}
            moves = [p_st + p_dst, k_mv, p_dst + pin_sq]
            puz = make_puzzle(w, b, moves, desc, seen)
            if puz: puzzles.append(puz)

    # Rook Absolute Pins along Files a to h
    for f in "abcdef":
        for pinned in ["q", "r", "n"]:
            for r_st in [f + "2", f + "3"]:
                for wp in ["a2", "b2", "h2", "g2"]:
                    if wp == r_st: continue
                    w = {r_st: "R", "g1": "K", wp: "P"}
                    b = {f + "8": "K", f + "6": pinned}
                    k_esc = ("g8" if f in "abcde" else "e8")
                    moves = [r_st + f + "1", f + "8" + k_esc, f + "1" + f + "6"]
                    puz = make_puzzle(w, b, moves, f"File {f} Absolute Rook Pin on {pinned.upper()}", seen)
                    if puz: puzzles.append(puz)

    return interleave_diverse(puzzles, 100)

print("Expansion module ready")
