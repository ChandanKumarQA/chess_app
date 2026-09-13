#!/usr/bin/env python3
import chess
import random
import os
from tactical_master_builder import fen_from_dicts, verify_puzzle, interleave_diverse, format_database_kt

def make_puzzle(white_pieces, black_pieces, moves, motif, seen_fens):
    fen = fen_from_dicts(white_pieces, black_pieces)
    if fen in seen_fens:
        return None
    ok, err = verify_puzzle(fen, moves)
    if not ok:
        # print(f"Rejected puzzle: {err} | fen: {fen} | moves: {moves}")
        return None
    seen_fens.add(fen)
    return (fen, moves, motif)

# ==============================================================================
# 1. MATE IN 1
# ==============================================================================
def gen_mate_in_1():
    puzzles = []
    seen = set()

    # A. Back-Rank Mates across files a to h with various king and pawn shields
    # Files c to f typical castled or center kings
    back_rank_specs = [
        # (King file/rank, pawns, rook start, rook mate sq, desc)
        ("g8", ["f7", "g7", "h7"], "d1", "d8", "d-file Back-Rank Mate"),
        ("g8", ["f7", "g7", "h7"], "e1", "e8", "e-file Back-Rank Mate"),
        ("g8", ["f7", "g7", "h7"], "a1", "a8", "a-file Long Back-Rank Mate"),
        ("g8", ["f7", "g7", "h7"], "c1", "c8", "c-file Back-Rank Mate"),
        ("g8", ["f7", "g6", "h7"], "f1", "f8", "f-file Pinpoint Back-Rank Mate"),
        ("c8", ["a7", "b7", "c7"], "d1", "d8", "Queenside Castled Back-Rank Mate"),
        ("b8", ["a7", "b7", "c7"], "e1", "e8", "b8 King Corridor Back-Rank Mate"),
        ("b8", ["a7", "b6", "c7"], "d1", "d8", "b8 King Back-Rank Mate"),
        ("h8", ["f7", "g7", "h6"], "a8", "a8", "Corner Trapped Back-Rank Mate"), # will use start a1
        ("e8", ["c7", "d7", "f7"], "e1", "e8", "Center Back-Rank Mate"),
        ("d8", ["b7", "c7", "e7"], "d1", "d8", "Central File Back-Rank Mate"),
        ("f8", ["e7", "f7", "g7"], "f1", "f8", "f-file Center Back-Rank Mate"),
    ]
    for k_sq, pawns, r_st, r_dst, desc in back_rank_specs:
        for extra_pawn in ["a2", "b2", "c2", "f2", "g2", "h2"]:
            w = {r_st: "R", "g1": "K", extra_pawn: "P"}
            b = {k_sq: "K"}
            for p in pawns:
                b[p] = "p"
            puz = make_puzzle(w, b, [r_st + r_dst], desc, seen)
            if puz: puzzles.append(puz)

    # B. Queen Kiss of Death / Mating Net on f7, g7, h7, b7, c7
    queen_specs = [
        # (Queen start, Queen mate sq, helper piece, helper sq, black king sq, desc)
        ("f3", "f7", "B", "c4", "e8", "Italian Scholar Mate Pattern on f7"),
        ("h5", "f7", "B", "c4", "e8", "Classic Wayward Queen Mate on f7"),
        ("f3", "f7", "N", "g5", "e8", "Knight Supported Queen Mate on f7"),
        ("d1", "d7", "B", "g4", "e8", "Queen & Bishop Central Mate on d7"),
        ("d1", "d8", "B", "e7", "e8", "Bishop Supported Queen Mate on d8"),
        ("h5", "h7", "N", "g5", "g8", "Greek Gift Queen & Knight Mate on h7"),
        ("e2", "h7", "B", "d3", "g8", "Queen & Bishop Battery Mate on h7"),
        ("c2", "h7", "B", "d3", "g8", "Long Diagonal Battery Mate on h7"),
        ("f3", "g7", "B", "b2", "g8", "Fianchetto Diagonal Queen Mate on g7"),
        ("e2", "g7", "B", "c3", "g8", "Long Diagonal Queen Mate on g7"),
        ("f3", "g7", "P", "f6", "g8", "Pawn Supported Queen Mate on g7"),
        ("h4", "g7", "P", "h6", "g8", "h-Pawn Supported Queen Mate on g7"),
        ("d4", "g7", "N", "f5", "g8", "Knight Outpost Queen Mate on g7"),
        ("f4", "g7", "B", "h6", "g8", "Bishop Pincer Queen Mate on g7"),
        ("e4", "b7", "B", "c6", "c8", "Queenside Queen & Bishop Mate on b7"),
        ("d4", "b7", "P", "a6", "c8", "Queenside Pawn Supported Mate on b7"),
        ("e3", "a7", "B", "c5", "b8", "Flank Queen & Bishop Mate on a7"),
        ("a4", "a7", "N", "c6", "b8", "Knight Supported Queen Mate on a7"),
        ("e5", "e7", "B", "c5", "e8", "Queen Central Strike Mate on e7"),
        ("d3", "e7", "N", "d5", "e8", "Knight Assisted Central Mate on e7"),
    ]
    for q_st, q_dst, h_type, h_sq, k_sq, desc in queen_specs:
        for def_pawn in ["a7", "b7", "c7", "f7", "g7", "h7"]:
            if def_pawn == q_dst or def_pawn == k_sq or def_pawn == h_sq:
                continue
            w = {q_st: "Q", h_sq: h_type, "g1": "K"}
            b = {k_sq: "K", def_pawn: "p"}
            # add pawn cover for white king
            w["g2"] = "P"
            puz = make_puzzle(w, b, [q_st + q_dst], desc, seen)
            if puz: puzzles.append(puz)

    # C. Smothered Mates in 1 (Knight finishes)
    smothered_specs = [
        ("g5", "f7", "h8", ["g7", "h7"], "f8", "Corner Knight Smothered Mate on f7"),
        ("e5", "f7", "h8", ["g7", "h7"], "f8", "Central Knight Smothered Mate on f7"),
        ("h6", "f7", "h8", ["g7", "h7"], "f8", "Edge Knight Smothered Mate on f7"),
        ("d6", "f7", "h8", ["g7", "h7"], "f8", "d6 Knight Smothered Mate on f7"),
        ("d5", "e7", "g8", ["g7", "h7"], "f8", "Central Knight Smothered Mate on e7"),
        ("f5", "e7", "g8", ["g7", "h7"], "f8", "f5 Knight Smothered Mate on e7"),
        ("b5", "c7", "a8", ["a7", "b7"], "c8", "Queenside Knight Smothered Mate on c7"),
        ("d5", "c7", "a8", ["a7", "b7"], "c8", "d5 Knight Smothered Mate on c7"),
        ("a5", "b7", "a8", ["a7", "b7"], "c8", "Wing Knight Smothered Mate on b7"),
        ("c5", "b7", "a8", ["a7", "b7"], "c8", "c5 Knight Smothered Mate on b7"),
    ]
    for n_st, n_dst, k_sq, pawns, r_sq, desc in smothered_specs:
        for wp in ["c2", "d2", "f2", "g2", "h2"]:
            w = {n_st: "N", "g1": "K", wp: "P"}
            b = {k_sq: "K", r_sq: "r"}
            for p in pawns: b[p] = "p"
            puz = make_puzzle(w, b, [n_st + n_dst], desc, seen)
            if puz: puzzles.append(puz)

    # D. Anastasia's & Arabian Mates in 1
    anastasia_arabian = [
        ("a5", "h5", "e7", "h7", "g7", "Anastasia Rook Mate on h5"),
        ("d5", "h5", "e7", "h7", "g7", "Anastasia Central Rook Mate on h5"),
        ("h1", "h7", "f6", "h8", "g8", "Arabian Rook Mate on h7"), # corner
        ("e7", "h7", "f6", "h8", "g7", "Arabian 7th Rank Rook Mate on h7"),
        ("b7", "a7", "c6", "a8", "b8", "Arabian Queenside Mate on a7"),
        ("a1", "a7", "c6", "a8", "b7", "Arabian Queenside Rook Mate on a7"),
    ]
    for r_st, r_dst, n_sq, k_sq, p_sq, desc in anastasia_arabian:
        for wp in ["f2", "g2", "h2", "c2", "b2"]:
            w = {r_st: "R", n_sq: "N", "g1": "K", wp: "P"}
            b = {k_sq: "K", p_sq: "p"}
            puz = make_puzzle(w, b, [r_st + r_dst], desc, seen)
            if puz: puzzles.append(puz)

    # E. Boden's & Epaulette Mates in 1
    bodens_epaulette = [
        ("c4", "a6", "f4", "B", "c8", "d7", "b8", "Boden Cross-Bishop Mate on a6"),
        ("f1", "a6", "e3", "B", "c8", "d7", "b8", "Classic Boden Mate on a6"),
        ("e3", "e6", "d1", "Q", "e8", "d8", "f8", "Epaulette Queen Mate on e6"),
        ("d3", "d6", "c1", "Q", "d8", "c8", "e8", "Epaulette Queen Mate on d6"),
    ]
    for att_st, att_dst, sup_sq, sup_t, k_sq, blk1, blk2, desc in bodens_epaulette:
        for wp in ["g2", "h2", "a2", "b2"]:
            w = {att_st: sup_t, sup_sq: "B", "g1": "K", wp: "P"}
            b = {k_sq: "K", blk1: "r", blk2: "r"}
            puz = make_puzzle(w, b, [att_st + att_dst], desc, seen)
            if puz: puzzles.append(puz)

    # F. Pawn Promotion & Push Mates in 1
    pawn_mates = [
        ("e7", "e8q", "d6", "K", "e8", ["d8", "f8"], "Pawn Promotion Queen Mate on e8"),
        ("f7", "f8q", "e6", "K", "f8", ["e8", "g8"], "f-Pawn Promotion Queen Mate on f8"),
        ("c7", "c8q", "b6", "K", "c8", ["b8", "d8"], "c-Pawn Promotion Queen Mate on c8"),
        ("g7", "g8q", "f6", "K", "g8", ["f8", "h8"], "g-Pawn Promotion Queen Mate on g8"),
        ("f6", "f7", "g6", "K", "e8", ["d7", "f8"], "Pawn March Mate on f7"),
        ("g6", "g7", "h6", "K", "f8", ["e8", "g8"], "Kingside Pawn Strangle Mate on g7"),
    ]
    for p_st, p_dst, k_w, kt, k_b, blks, desc in pawn_mates:
        for extra in ["a2", "b2", "h2"]:
            w = {p_st: "P", k_w: "K", extra: "P"}
            b = {k_b: "K"}
            for blk in blks: b[blk] = "r"
            puz = make_puzzle(w, b, [p_st + p_dst], desc, seen)
            if puz: puzzles.append(puz)

    return interleave_diverse(puzzles, 100)

print("Mate in 1 generator ready")
