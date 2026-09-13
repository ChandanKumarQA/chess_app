#!/usr/bin/env python3
import chess
import os
import sys
from tactical_master_builder import fen_from_dicts, verify_puzzle, interleave_diverse, format_database_kt

def make_puzzle(white_pieces, black_pieces, moves, motif, seen_fens):
    fen = fen_from_dicts(white_pieces, black_pieces)
    if fen in seen_fens:
        return None
    ok, err = verify_puzzle(fen, moves)
    if not ok:
        return None
    seen_fens.add(fen)
    return (fen, moves, motif)

# ==============================================================================
# 1. MATE IN 1
# ==============================================================================
def gen_mate_in_1():
    puzzles = []
    seen = set()

    back_rank_specs = [
        ("g8", ["f7", "g7", "h7"], "d1", "d8", "d-file Back-Rank Mate"),
        ("g8", ["f7", "g7", "h7"], "e1", "e8", "e-file Back-Rank Mate"),
        ("g8", ["f7", "g7", "h7"], "a1", "a8", "a-file Long Back-Rank Mate"),
        ("g8", ["f7", "g7", "h7"], "c1", "c8", "c-file Back-Rank Mate"),
        ("g8", ["f7", "g6", "h7"], "f1", "f8", "f-file Pinpoint Back-Rank Mate"),
        ("c8", ["a7", "b7", "c7"], "d1", "d8", "Queenside Castled Back-Rank Mate"),
        ("b8", ["a7", "b7", "c7"], "e1", "e8", "b8 King Corridor Back-Rank Mate"),
        ("b8", ["a7", "b6", "c7"], "d1", "d8", "b8 King Back-Rank Mate"),
        ("e8", ["c7", "d7", "f7"], "e1", "e8", "Center Back-Rank Mate"),
        ("d8", ["b7", "c7", "e7"], "d1", "d8", "Central File Back-Rank Mate"),
        ("f8", ["e7", "f7", "g7"], "f1", "f8", "f-file Center Back-Rank Mate"),
    ]
    for k_sq, pawns, r_st, r_dst, desc in back_rank_specs:
        for extra_pawn in ["a2", "b2", "c2", "f2", "g2", "h2"]:
            w = {r_st: "R", "g1": "K", extra_pawn: "P"}
            b = {k_sq: "K"}
            for p in pawns: b[p] = "p"
            puz = make_puzzle(w, b, [r_st + r_dst], desc, seen)
            if puz: puzzles.append(puz)

    queen_specs = [
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
            if def_pawn in (q_dst, k_sq, h_sq): continue
            w = {q_st: "Q", h_sq: h_type, "g1": "K", "g2": "P"}
            b = {k_sq: "K", def_pawn: "p"}
            puz = make_puzzle(w, b, [q_st + q_dst], desc, seen)
            if puz: puzzles.append(puz)

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

    anastasia_arabian = [
        ("a5", "h5", "e7", "h7", "g7", "Anastasia Rook Mate on h5"),
        ("d5", "h5", "e7", "h7", "g7", "Anastasia Central Rook Mate on h5"),
        ("h1", "h7", "f6", "h8", "g8", "Arabian Rook Mate on h7"),
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

    pawn_mates = [
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

# ==============================================================================
# 2. MATE IN 2
# ==============================================================================
def gen_mate_in_2():
    puzzles = []
    seen = set()

    # Queen sacrifice into back-rank mate: Qe8+ Rxe8, Rxe8#
    back_rank_m2 = [
        ("d1", "d8", "c8", ["a7", "b7", "c7"], "d1", "d8", "Queen Sacrifice Back Rank Mate on d8"),
        ("e1", "e8", "g8", ["f7", "g7", "h7"], "e1", "e8", "Queen Sacrifice Back Rank Mate on e8"),
        ("f1", "f8", "g8", ["f7", "g7", "h7"], "f1", "f8", "f-file Queen Sacrifice Back Rank Mate"),
        ("c1", "c8", "c8", ["a7", "b7", "c7"], "c1", "c8", "c-file Queen Sacrifice Back Rank Mate"),
    ]
    for q_st, q_sac, k_sq, pawns, r_st, r_fin, desc in back_rank_m2:
        for rk_sq in ["a1", "b1", "h1"]:
            for wp in ["a2", "b2", "h2"]:
                w = {"d1": "Q", rk_sq: "R", "g1": "K", wp: "P"}
                b = {k_sq: "K", q_sac: "r"}
                for p in pawns: b[p] = "p"
                moves = ["d1" + q_sac, q_sac + q_sac, rk_sq + q_sac] # test
                # build specific
    
    # Specific forced mate in 2 combinations
    m2_templates = [
        # (w_dict, b_dict, moves, desc)
        # Anastasia sequence: 1. Ne7+ Kh8 2. Rxh7#
        ({"a5": "R", "g5": "N", "g1": "K", "h2": "P"}, {"h7": "K", "g7": "p"}, ["g5e7", "h7h8", "a5h5"], "Anastasia Knight Check & Rook Mate"),
        ({"b5": "R", "f5": "N", "g1": "K", "h2": "P"}, {"h7": "K", "g7": "p"}, ["f5e7", "h7h8", "b5h5"], "Anastasia Knight Outpost Mate"),
        ({"c5": "R", "d5": "N", "g1": "K", "h2": "P"}, {"h7": "K", "g7": "p"}, ["d5e7", "h7h8", "c5h5"], "Central Anastasia Mate in 2"),
        ({"d5": "R", "c5": "N", "g1": "K", "h2": "P"}, {"h7": "K", "g7": "p"}, ["c5e7", "h7h8", "d5h5"], "Rank 5 Anastasia Mate in 2"),
        ({"e5": "R", "f4": "N", "g1": "K", "h2": "P"}, {"h7": "K", "g7": "p"}, ["f4e7", "h7h8", "e5h5"], "e-file Anastasia Mate in 2"),
        # Arabian Mate sequence: 1. Nf6+ Kh8 2. Rh7#
        ({"e7": "R", "e5": "N", "g1": "K", "h2": "P"}, {"h8": "K", "g7": "p"}, ["e5f6", "h8g8", "e7h7"], "Arabian Knight Check & Rook Mate"),
        ({"d7": "R", "d5": "N", "g1": "K", "h2": "P"}, {"h8": "K", "g7": "p"}, ["d5f6", "h8g8", "d7h7"], "Central Arabian Mate in 2"),
        ({"c7": "R", "g4": "N", "g1": "K", "h2": "P"}, {"h8": "K", "g7": "p"}, ["g4f6", "h8g8", "c7h7"], "Flank Arabian Mate in 2"),
        ({"b7": "R", "h5": "N", "g1": "K", "h2": "P"}, {"h8": "K", "g7": "p"}, ["h5f6", "h8g8", "b7h7"], "Long-Range Arabian Mate in 2"),
        # Philidor Smothered sequence: 1. Qg8+ Rxg8 2. Nf7#
        ({"c4": "Q", "e5": "N", "g1": "K", "h2": "P"}, {"h8": "K", "f8": "r", "g7": "p", "h7": "p"}, ["c4g8", "f8g8", "e5f7"], "Classic Philidor Smothered Mate in 2"),
        ({"d5": "Q", "g5": "N", "g1": "K", "h2": "P"}, {"h8": "K", "f8": "r", "g7": "p", "h7": "p"}, ["d5g8", "f8g8", "g5f7"], "Philidor Knight Leap Mate in 2"),
        ({"b3": "Q", "h6": "N", "g1": "K", "h2": "P"}, {"h8": "K", "f8": "r", "g7": "p", "h7": "p"}, ["b3g8", "f8g8", "h6f7"], "Fianchetto Philidor Mate in 2"),
        ({"e4": "Q", "d6": "N", "g1": "K", "h2": "P"}, {"h8": "K", "f8": "r", "g7": "p", "h7": "p"}, ["e4g8", "f8g8", "d6f7"], "Central Philidor Mate in 2"),
        ({"a2": "Q", "e5": "N", "g1": "K", "h2": "P"}, {"h8": "K", "f8": "r", "g7": "p", "h7": "p"}, ["a2g8", "f8g8", "e5f7"], "Long Diagonal Philidor Mate in 2"),
        # Greek Gift Checkmate in 2: 1. Bxh7+ Kxh7 2. Qh5#
        ({"d3": "B", "d1": "Q", "g1": "K", "f2": "P"}, {"g8": "K", "f7": "p", "g7": "p"}, ["d3h7", "g8h7", "d1h5"], "Greek Gift Sacrifice Mate in 2"),
        ({"c2": "B", "e1": "Q", "g1": "K", "f2": "P"}, {"g8": "K", "f7": "p", "g7": "p"}, ["c2h7", "g8h7", "e1h4"], "c2 Battery Greek Gift Mate in 2"),
        ({"b1": "B", "d1": "Q", "g1": "K", "f2": "P"}, {"g8": "K", "f7": "p", "g7": "p"}, ["b1h7", "g8h7", "d1h5"], "Long Diagonal Greek Gift Mate in 2"),
        # Queen Sac Back-Rank: 1. Qd8+ Rxd8 2. Rxd8#
        ({"d1": "Q", "a1": "R", "g1": "K", "h2": "P"}, {"c8": "K", "d8": "r", "a7": "p", "b7": "p", "c7": "p"}, ["d1d8", "d8d8", "a1d1"], "d-file Queen Deflection Mate in 2"), # wait, check moves
        # Back-Rank Double Rook Deflection: 1. Re8+ Rxe8 2. Rxe8#
        ({"e1": "R", "a1": "R", "g1": "K", "h2": "P"}, {"g8": "K", "e8": "r", "f7": "p", "g7": "p", "h7": "p"}, ["e1e8", "e8e8", "a1e1"], "e-file Double Rook Battery Mate in 2"),
        ({"e1": "R", "b1": "R", "g1": "K", "h2": "P"}, {"g8": "K", "e8": "r", "f7": "p", "g7": "p", "h7": "p"}, ["e1e8", "e8e8", "b1e1"], "Queenside Rook Battery Mate in 2"),
        ({"d1": "R", "a1": "R", "g1": "K", "h2": "P"}, {"c8": "K", "d8": "r", "a7": "p", "b7": "p", "c7": "p"}, ["d1d8", "d8d8", "a1d1"], "Central Rook Battery Mate in 2"),
        ({"d1": "R", "h1": "R", "g1": "K", "h2": "P"}, {"c8": "K", "d8": "r", "a7": "p", "b7": "p", "c7": "p"}, ["d1d8", "d8d8", "h1d1"], "Kingside Rook Battery Mate in 2"),
        ({"f1": "R", "a1": "R", "g1": "K", "h2": "P"}, {"g8": "K", "f8": "r", "f7": "p", "g7": "p", "h7": "p"}, ["f1f8", "f8f8", "a1f1"], "f-file Rook Battery Mate in 2"),
        ({"c1": "R", "a1": "R", "g1": "K", "h2": "P"}, {"c8": "K", "c7": "r", "a7": "p", "b7": "p"}, ["c1c7", "c8b8", "c7c8"], "c-file Infiltration Mate in 2"), # will verify
        # Opera box Queen Sac: 1. Qb8+ Nxb8 2. Rd8#
        ({"d6": "Q", "d1": "R", "g1": "K", "h2": "P"}, {"c8": "K", "c7": "p", "b8": "n"}, ["d6c7", "c8d8", "c7d7"], "Opera Infiltration Mate"),
        # Corner Rook Sac into Queen Mate: 1. Rh8+ Kxh8 2. Qh5#
        ({"h4": "R", "e2": "Q", "g1": "K", "f2": "P"}, {"g8": "K", "f7": "p", "g7": "p", "h7": "p"}, ["h4h8", "g8h8", "e2h5"], "Rook Corner Decoy Sacrifice Mate"),
        ({"h3": "R", "d1": "Q", "g1": "K", "f2": "P"}, {"g8": "K", "f7": "p", "g7": "p", "h7": "p"}, ["h3h8", "g8h8", "d1h5"], "Rook Lift Decoy Sacrifice Mate"),
    ]
    for w, b, moves, desc in m2_templates:
        puz = make_puzzle(w, b, moves, desc, seen)
        if puz: puzzles.append(puz)

    # Generate rich variations with different piece coordinates
    # Anastasia generator
    for r_col in "abcdef":
        for n_start in ["g4", "f5", "d5", "c4", "g6"]:
            w = {r_col + "5": "R", n_start: "N", "g1": "K", "a2": "P"}
            b = {"h7": "K", "g7": "p"}
            moves = [n_start + "e7", "h7h8", r_col + "5h5"]
            puz = make_puzzle(w, b, moves, f"Anastasia Mate from {r_col}-file with N{n_start}", seen)
            if puz: puzzles.append(puz)

    # Arabian generator
    for r_col in "abcdef":
        for n_start in ["e4", "d5", "g4", "d7", "e5"]:
            w = {r_col + "7": "R", n_start: "N", "g1": "K", "a2": "P"}
            b = {"h8": "K", "g8": "k"} # black king on h8
            b = {"h8": "K", "g7": "p"}
            moves = [n_start + "f6", "h8g8", r_col + "7h7"]
            puz = make_puzzle(w, b, moves, f"Arabian Mate {r_col}-file Rook with N{n_start}", seen)
            if puz: puzzles.append(puz)

    # Philidor Smothered generator
    for q_diag in ["c4", "d5", "e6", "a2", "b3"]:
        for n_pos in ["e5", "g5", "h6", "d6"]:
            w = {q_diag: "Q", n_pos: "N", "g1": "K", "a2": "P"}
            b = {"h8": "K", "f8": "r", "g7": "p", "h7": "p"}
            moves = [q_diag + "g8", "f8g8", n_pos + "f7"]
            puz = make_puzzle(w, b, moves, f"Smothered Queen Sac from {q_diag} with N{n_pos}", seen)
            if puz: puzzles.append(puz)

    # Double Rook Back-Rank Batteries across various files
    for f in "abcdef":
        for r2_col in "gh":
            w = {f + "1": "R", r2_col + "1": "R", "g1": "K", "b2": "P"}
            b = {"g8": "K", f + "8": "r", "f7": "p", "g7": "p", "h7": "p"}
            moves = [f + "1" + f + "8", f + "8" + f + "8", r2_col + "1" + f + "1"] # wait, second move is capture
            # Rook on f1 takes f8, rook f8 takes f8, other rook to f1... wait other rook to f8:
            moves = [f + "1" + f + "8", f + "8" + f + "8", r2_col + "1" + f + "8"]
            puz = make_puzzle(w, b, moves, f"{f}-file Double Rook Deflection Mate in 2", seen)
            if puz: puzzles.append(puz)

    # Rook Corner Sacrifice Decoy into Queen Mate
    for r_start in ["h1", "h2", "h3", "h4", "h5"]:
        for q_pos in ["d1", "e1", "e2", "f3"]:
            w = {r_start: "R", q_pos: "Q", "g1": "K", "a2": "P"}
            b = {"g8": "K", "f7": "p", "g7": "p", "h7": "p"}
            moves = [r_start + "h8", "g8h8", q_pos + "h5"]
            puz = make_puzzle(w, b, moves, f"Rook Sacrifice on h8 Decoy Mate with Q{q_pos}", seen)
            if puz: puzzles.append(puz)

    # Queen Sac Back Rank Mate: Qd8+ Rxd8, Rxd8#
    for r_col in ["a1", "b1", "c1", "e1", "f1"]:
        w = {"d1": "Q", r_col: "R", "g1": "K", "a2": "P"}
        b = {"c8": "K", "d8": "r", "a7": "p", "b7": "p", "c7": "p"}
        moves = ["d1d8", "d8d8", r_col + "d1"]
        puz = make_puzzle(w, b, moves, f"Queen Deflection on d8 followed by R{r_col} Mate", seen)
        if puz: puzzles.append(puz)

    return interleave_diverse(puzzles, 100)

# ==============================================================================
# 3. FORK
# ==============================================================================
def gen_fork():
    puzzles = []
    seen = set()

    # Knight Forks
    knight_specs = [
        ("b5", "c7", "e8", "d8", "a8", "r", "g1", "Knight Fork on c7 winning Rook on a8"),
        ("d5", "c7", "e8", "d8", "a8", "r", "g1", "Central Knight Fork on c7 winning Rook"),
        ("a6", "c7", "e8", "f8", "d5", "q", "g1", "Knight Fork on c7 winning Queen on d5"),
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
    ]
    for n_st, n_fk, k_sq, k_dst, t_sq, tgt, wk, desc in knight_specs:
        for wp in ["h2", "g2", "f2", "a2", "b2"]:
            w = {n_st: "N", wk: "K", wp: "P"}
            b = {k_sq: "K", t_sq: tgt}
            moves = [n_st + n_fk, k_sq + k_dst, n_fk + t_sq]
            puz = make_puzzle(w, b, moves, desc, seen)
            if puz: puzzles.append(puz)

    # Pawn Forks
    pawn_specs = [
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
    ]
    for p_st, p_dst, b_sq1, p1, b_sq2, p2, moves, desc in pawn_specs:
        for extra in ["a2", "h2", "b2", "g2"]:
            w = {p_st: "P", "g1": "K", extra: "P"}
            b = {b_sq1: p1, b_sq2: p2, "g8": "K", "h7": "p"}
            puz = make_puzzle(w, b, moves, desc, seen)
            if puz: puzzles.append(puz)

    # Queen Forks
    queen_forks = [
        ("d1", "a4", "e8", "d8", "c4", "b", ["d1a4", "e8d8", "a4c4"], "Queen Check Fork on c4 Bishop"),
        ("d1", "a4", "e8", "d7", "e4", "n", ["d1a4", "e8d7", "a4e4"], "Queen Check Fork on e4 Knight"),
        ("d1", "h5", "e8", "d7", "e5", "n", ["d1h5", "e8d7", "h5e5"], "Queen Check Fork on e5 Knight"),
        ("d1", "h5", "g8", "h8", "c5", "b", ["d1h5", "g8h8", "h5c5"], "Kingside Queen Fork on c5 Bishop"),
        ("e1", "e5", "d8", "c8", "b5", "r", ["e1e5", "d8c8", "e5b5"], "Central Queen Fork on b5 Rook"),
        ("e1", "e5", "f8", "g8", "h5", "r", ["e1e5", "f8g8", "e5h5"], "Central Queen Fork on h5 Rook"),
        ("b1", "b5", "e8", "d8", "e5", "n", ["b1b5", "e8d8", "b5e5"], "Flank Queen Fork on e5 Knight"),
        ("c1", "a3", "e7", "e8", "f8", "b", ["c1a3", "e7e8", "a3f8"], "Diagonal Queen Fork on f8 Bishop"),
    ]
    for q_st, q_dst, k_sq, k_dst, t_sq, tp, moves, desc in queen_forks:
        for wp in ["g2", "h2", "f2"]:
            w = {q_st: "Q", "g1": "K", wp: "P"}
            b = {k_sq: "K", t_sq: tp}
            puz = make_puzzle(w, b, moves, desc, seen)
            if puz: puzzles.append(puz)

    return interleave_diverse(puzzles, 100)

# ==============================================================================
# 4. PIN
# ==============================================================================
def gen_pin():
    puzzles = []
    seen = set()

    # Absolute Bishop Pins to King on e8, c8, g8
    bishop_pins = [
        ("c1", "g5", "e8", "f6", "n", "e8d7", "Bg5 Absolute Pin on f6 Knight"),
        ("c1", "g5", "e8", "f6", "q", "e8d7", "Bg5 Absolute Pin on f6 Queen"),
        ("f1", "b5", "e8", "c6", "n", "e8d8", "Bb5 Absolute Pin on c6 Knight"),
        ("f1", "b5", "e8", "c6", "q", "e8d8", "Bb5 Absolute Pin on c6 Queen"),
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
        for wp in ["h2", "g2", "f2", "b2", "a2"]:
            w = {b_st: "B", "g1": "K", wp: "P"}
            b = {b_k: "K", b_pin: p_type}
            moves = [b_st + b_dst, k_mv, b_dst + b_pin]
            puz = make_puzzle(w, b, moves, desc, seen)
            if puz: puzzles.append(puz)

    # Exploiting Pinned Pieces with Pawn Pushes
    pawn_pin_exploits = [
        ("d4", "d5", "c6", "n", "b5", "B", "e8", "e8f8", "Pawn Push d5 Exploiting Pinned c6 Knight"),
        ("e4", "e5", "f6", "n", "g5", "B", "e8", "e8d8", "Pawn Push e5 Exploiting Pinned f6 Knight"),
        ("c4", "c5", "d6", "n", "e7", "R", "d8", "d8c8", "Pawn Push c5 Exploiting Pinned d6 Knight"),
        ("f4", "f5", "e6", "n", "d7", "R", "e8", "e8f8", "Pawn Push f5 Exploiting Pinned e6 Knight"),
        ("d3", "d4", "c5", "b", "b4", "B", "e8", "e8d7", "Pawn Attack d4 on Pinned c5 Bishop"),
        ("e3", "e4", "f5", "b", "g4", "B", "e8", "e8d7", "Pawn Attack e4 on Pinned f5 Bishop"),
    ]
    for p_st, p_dst, pin_sq, pin_p, pnr_sq, pnr_p, k_sq, k_mv, desc in pawn_pin_exploits:
        for wp in ["h2", "g2", "a2", "b2"]:
            w = {p_st: "P", pnr_sq: pnr_p, "g1": "K", wp: "P"}
            b = {pin_sq: pin_p, k_sq: "K"}
            moves = [p_st + p_dst, k_mv, p_dst + pin_sq]
            puz = make_puzzle(w, b, moves, desc, seen)
            if puz: puzzles.append(puz)

    # Rook Absolute Pins along Files a to h
    for f in "abcdef":
        for pinned in ["q", "r", "n"]:
            for r_st in [f + "1", f + "2"]:
                w = {r_st: "R", "g1": "K", "h2": "P"}
                b = {f + "8": "K", f + "6": pinned}
                k_esc = ("g8" if f in "abcde" else "e8")
                moves = [r_st + f + "1", f + "8" + k_esc, f + "1" + f + "6"]
                puz = make_puzzle(w, b, moves, f"File {f} Absolute Rook Pin on {pinned.upper()}", seen)
                if puz: puzzles.append(puz)

    return interleave_diverse(puzzles, 100)

# ==============================================================================
# 5. SKEWER
# ==============================================================================
def gen_skewer():
    puzzles = []
    seen = set()

    # Bishop Skewers on Diagonals
    diag_skewers = [
        ("c1", "b2", "g7", "g8", "h8", "q", "a1-h8 Bishop Skewer on Queen"),
        ("e3", "d4", "f6", "e7", "h8", "q", "Center a1-h8 Bishop Skewer on Queen"),
        ("f1", "c4", "e6", "e7", "g8", "q", "Italian a2-g8 Bishop Skewer on Queen"),
        ("d3", "c4", "e6", "f6", "g8", "r", "Italian a2-g8 Bishop Skewer on Rook"),
        ("c2", "d3", "f5", "e6", "h7", "q", "b1-h7 Bishop Skewer on Queen"),
        ("e3", "f4", "d6", "e7", "b8", "q", "h2-b8 Bishop Skewer on Queen"),
        ("f2", "e3", "c5", "d6", "a7", "q", "g1-a7 Bishop Skewer on Queen"),
        ("e1", "d2", "b4", "c5", "a5", "q", "c1-h6 Bishop Skewer on Queen"),
        ("a2", "b3", "d5", "e6", "f7", "r", "Queen-Wing Bishop Skewer on Rook"),
        ("f1", "g2", "b7", "b8", "a8", "q", "Fianchetto h1-a8 Bishop Skewer on Queen"),
        ("e2", "f3", "c6", "d7", "b7", "r", "h1-a8 Bishop Skewer on Rook"),
    ]
    for b_st, b_mv, k_sq, k_dst, t_sq, tgt, desc in diag_skewers:
        for wp in ["h2", "g2", "f2", "a2", "b2"]:
            w = {b_st: "B", "g1": "K", wp: "P"}
            b = {k_sq: "K", t_sq: tgt}
            moves = [b_st + b_mv, k_sq + k_dst, b_mv + t_sq]
            puz = make_puzzle(w, b, moves, desc, seen)
            if puz: puzzles.append(puz)

    # Rank Rook Skewers
    for r in range(2, 8):
        r_str = str(r)
        for t_f, tgt in [("h", "q"), ("h", "r"), ("a", "q"), ("a", "r")]:
            start_sq = "b" + r_str if t_f == "h" else "g" + r_str
            move_sq = "a" + r_str if t_f == "h" else "h" + r_str
            k_sq = "d" + r_str if t_f == "h" else "e" + r_str
            k_dst = "d" + str(r + 1 if r < 7 else r - 1) if t_f == "h" else "e" + str(r + 1 if r < 7 else r - 1)
            t_sq = t_f + r_str
            w = {start_sq: "R", "g1": "K", "c2": "P"}
            b = {k_sq: "K", t_sq: tgt}
            moves = [start_sq + move_sq, k_sq + k_dst, move_sq + t_sq]
            puz = make_puzzle(w, b, moves, f"Rank {r} Rook Skewer on {tgt.upper()}", seen)
            if puz: puzzles.append(puz)

    # File Rook Skewers
    for f in "bcde":
        for t_r, tgt in [(8, "q"), (8, "r"), (1, "q"), (1, "r")]:
            start_sq = f + "2" if t_r == 8 else f + "7"
            move_sq = f + "1" if t_r == 8 else f + "8"
            k_sq = f + ("4" if t_r == 8 else "5")
            k_dst = chr(ord(f) + 1) + ("4" if t_r == 8 else "5")
            t_sq = f + str(t_r)
            w = {start_sq: "R", "h1": "K", "a2": "P"}
            b = {k_sq: "K", t_sq: tgt}
            moves = [start_sq + move_sq, k_sq + k_dst, move_sq + t_sq]
            puz = make_puzzle(w, b, moves, f"File {f} Rook Skewer on {tgt.upper()}", seen)
            if puz: puzzles.append(puz)

    return interleave_diverse(puzzles, 100)

# ==============================================================================
# 6. DOUBLE ATTACK
# ==============================================================================
def gen_double_attack():
    puzzles = []
    seen = set()

    q_attacks = [
        ("d1", "a4", "e8", "d8", "e4", "n", "Queen a4 Check & Attack on e4 Knight"),
        ("d1", "a4", "e8", "f8", "c4", "b", "Queen a4 Check & Attack on c4 Bishop"),
        ("d1", "h5", "e8", "d7", "e5", "n", "Queen h5 Check & Attack on e5 Knight"),
        ("d1", "h5", "g8", "h8", "c5", "b", "Queen h5 Check & Attack on c5 Bishop"),
        ("e1", "e5", "d8", "c8", "b5", "r", "Central Queen e5 Check & Attack on b5 Rook"),
        ("e1", "e5", "f8", "g8", "h5", "r", "Central Queen e5 Check & Attack on h5 Rook"),
        ("b1", "b5", "e8", "d8", "e5", "n", "Flank Queen b5 Check & Attack on e5 Knight"),
        ("c1", "a3", "e7", "e8", "f8", "b", "Queen a3 Check & Attack on f8 Bishop"),
        ("d2", "e3", "d6", "c6", "b6", "r", "Queen e3 Check & Attack on b6 Rook"),
        ("f2", "e3", "f6", "g6", "g5", "n", "Queen e3 Check & Attack on g5 Knight"),
        ("a1", "e5", "d7", "c8", "h8", "r", "Corner Queen e5 Check & Attack on h8 Rook"),
        ("h1", "e4", "f7", "g8", "a8", "r", "Corner Queen e4 Check & Attack on a8 Rook"),
    ]
    for q_st, q_dst, k_sq, k_dst, t_sq, tp, desc in q_attacks:
        for wp in ["g2", "h2", "f2", "b2", "a2"]:
            w = {q_st: "Q", "g1": "K", wp: "P"}
            b = {k_sq: "K", t_sq: tp}
            moves = [q_st + q_dst, k_sq + k_dst, q_dst + t_sq]
            puz = make_puzzle(w, b, moves, desc, seen)
            if puz: puzzles.append(puz)

    # Knight double attacks
    knight_doubles = [
        ("b5", "d6", "e8", "f8", "f7", "b", "Knight Infiltration Double Attack on King & Bishop"),
        ("c4", "e5", "d7", "c8", "g6", "r", "Knight Central Fork Double Attack on King & Rook"),
        ("d4", "f5", "e7", "f6", "d6", "r", "Knight Outpost Double Attack on King & Rook"),
    ]
    for n_st, n_dst, k_sq, k_dst, t_sq, tp, desc in knight_doubles:
        for wp in ["g2", "h2", "a2"]:
            w = {n_st: "N", "g1": "K", wp: "P"}
            b = {k_sq: "K", t_sq: tp}
            moves = [n_st + n_dst, k_sq + k_dst, n_dst + t_sq]
            puz = make_puzzle(w, b, moves, desc, seen)
            if puz: puzzles.append(puz)

    # 7th rank Rook Double Attacks
    for r_st in ["a1", "b1", "c1", "d1", "e1", "f1"]:
        w = {r_st: "R", "g1": "K", "h2": "P"}
        b = {"e8": "K", "c7": "n", "g7": "n"}
        moves = [r_st + r_st[0] + "7", "e8d8", r_st[0] + "7c7"]
        puz = make_puzzle(w, b, moves, f"Rook 7th Rank Double Attack from {r_st}", seen)
        if puz: puzzles.append(puz)

    return interleave_diverse(puzzles, 100)

# ==============================================================================
# 7. DISCOVERED ATTACK
# ==============================================================================
def gen_discovered_attack():
    puzzles = []
    seen = set()

    disc_specs = [
        ("d3", "h7", "g8", "h8", "d1", "d8", "Greek Gift Discovered Attack winning Queen on d8"),
        ("d3", "b5", "e8", "f8", "d1", "d8", "Bb5 Check Discovered Attack winning Queen on d8"),
        ("d3", "f5", "e8", "d7", "d1", "d8", "Bf5 Check Discovered Attack winning Queen on d8"),
        ("d4", "b6", "e8", "f7", "d1", "d8", "Bb6 Check Discovered Attack winning Queen on d8"),
        ("d4", "f6", "g8", "h8", "d1", "d8", "Bf6 Check Discovered Attack winning Queen on d8"),
        ("e3", "c5", "g8", "h8", "e1", "e8", "Bc5 Check on e-file winning Queen on e8"),
        ("e3", "g5", "e8", "f7", "e1", "e8", "Bg5 Check on e-file winning Queen on e8"),
        ("e4", "c6", "g8", "h8", "e1", "e8", "Bc6 Check on e-file winning Queen on e8"),
        ("e4", "g6", "e8", "d8", "e1", "e8", "Bg6 Check on e-file winning Queen on e8"),
        ("c3", "a5", "e8", "f7", "c1", "c8", "Ba5 Check on c-file winning Queen on c8"),
        ("c3", "b4", "e8", "d8", "c1", "c8", "Bb4 Check on c-file winning Queen on c8"),
        ("c4", "b5", "e8", "f7", "c1", "c8", "Bb5 Check on c-file winning Queen on c8"),
        ("f3", "g4", "e8", "d8", "f1", "f8", "Bg4 Check on f-file winning Queen on f8"),
        ("f3", "h5", "e8", "f7", "f1", "f8", "Bh5 Check on f-file winning Queen on f8"),
    ]
    for b_st, b_chk, b_k, k_dst, r_st, q_sq, desc in disc_specs:
        for wp in ["h2", "g2", "f2", "a2", "b2"]:
            w = {b_st: "B", r_st: "R", "g1": "K", wp: "P"}
            b = {b_k: "K", q_sq: "q"}
            moves = [b_st + b_chk, b_k + k_dst, r_st + q_sq]
            puz = make_puzzle(w, b, moves, desc, seen)
            if puz: puzzles.append(puz)

    n_disc_specs = [
        ("d4", "f5", "g8", "h8", "d1", "d8", "Knight f5 Jump Discovered Attack on Queen"),
        ("d4", "c6", "e8", "f7", "d1", "d8", "Knight c6 Check Discovered Attack on Queen"),
        ("e4", "f6", "g8", "h8", "e1", "e8", "Knight f6 Check on e-file winning Queen"),
        ("e4", "d6", "e8", "d8", "e1", "e8", "Knight d6 Check on e-file winning Queen"),
        ("c4", "d6", "e8", "f8", "c1", "c8", "Knight d6 Check on c-file winning Queen"),
        ("c4", "b6", "e8", "d7", "c1", "c8", "Knight b6 Check on c-file winning Queen"),
    ]
    for n_st, n_dst, k_sq, k_dst, r_st, q_sq, desc in n_disc_specs:
        for wp in ["h2", "g2", "a2", "b2"]:
            w = {n_st: "N", r_st: "R", "g1": "K", wp: "P"}
            b = {k_sq: "K", q_sq: "q"}
            moves = [n_st + n_dst, k_sq + k_dst, r_st + q_sq]
            puz = make_puzzle(w, b, moves, desc, seen)
            if puz: puzzles.append(puz)

    return interleave_diverse(puzzles, 100)

# ==============================================================================
# 8. SMOTHERED MATE
# ==============================================================================
def gen_smothered_mate():
    puzzles = []
    seen = set()

    # Philidor Smothered combinations
    for q_diag in ["c4", "d5", "e6", "a2", "b3"]:
        for n_pos in ["e5", "g5", "h6", "d6"]:
            for wp in ["a2", "b2", "c2", "f2"]:
                if q_diag == wp or n_pos == wp: continue
                w = {q_diag: "Q", n_pos: "N", "g1": "K", wp: "P"}
                b = {"h8": "K", "f8": "r", "g7": "p", "h7": "p"}
                moves = [q_diag + "g8", "f8g8", n_pos + "f7"]
                puz = make_puzzle(w, b, moves, f"Philidor Queen Sac from {q_diag} with N{n_pos}", seen)
                if puz: puzzles.append(puz)

    # Direct 1-move Smothered finishes
    smothered_direct = [
        ("g5", "f7", "h8", ["g7", "h7"], "f8", "Corner Smothered Mate on f7"),
        ("e5", "f7", "h8", ["g7", "h7"], "f8", "Central Smothered Mate on f7"),
        ("h6", "f7", "h8", ["g7", "h7"], "f8", "Edge Smothered Mate on f7"),
        ("d6", "f7", "h8", ["g7", "h7"], "f8", "d6 Smothered Mate on f7"),
        ("d5", "e7", "g8", ["g7", "h7"], "f8", "Central Smothered Mate on e7"),
        ("f5", "e7", "g8", ["g7", "h7"], "f8", "f5 Smothered Mate on e7"),
        ("b5", "c7", "a8", ["a7", "b7"], "c8", "Queenside Smothered Mate on c7"),
        ("d5", "c7", "a8", ["a7", "b7"], "c8", "d5 Smothered Mate on c7"),
        ("a5", "b7", "a8", ["a7", "b7"], "c8", "Wing Smothered Mate on b7"),
        ("c5", "b7", "a8", ["a7", "b7"], "c8", "c5 Smothered Mate on b7"),
    ]
    for n_st, n_dst, k_sq, pawns, r_sq, desc in smothered_direct:
        for wp in ["c2", "d2", "f2", "g2", "h2", "a2", "b2"]:
            w = {n_st: "N", "g1": "K", wp: "P"}
            b = {k_sq: "K", r_sq: "r"}
            for p in pawns: b[p] = "p"
            puz = make_puzzle(w, b, [n_st + n_dst], desc, seen)
            if puz: puzzles.append(puz)

    return interleave_diverse(puzzles, 100)

# ==============================================================================
# 9. SACRIFICE
# ==============================================================================
def gen_sacrifice():
    puzzles = []
    seen = set()

    # Greek gift sacrifice
    for b_sq in ["d3", "c2", "b1", "e4"]:
        for q_sq in ["d1", "e1", "f3"]:
            w = {b_sq: "B", q_sq: "Q", "g1": "K", "f2": "P"}
            b = {"g8": "K", "f7": "p", "g7": "p", "h7": "p"}
            moves = [b_sq + "h7", "g8h7", q_sq + "h5"]
            puz = make_puzzle(w, b, moves, f"Greek Gift Bishop Sacrifice from {b_sq}", seen)
            if puz: puzzles.append(puz)

    # Queen sacrifice for back rank mate
    for r_col in ["a1", "b1", "c1", "e1", "f1"]:
        w = {"d1": "Q", r_col: "R", "g1": "K", "a2": "P"}
        b = {"c8": "K", "d8": "r", "a7": "p", "b7": "p", "c7": "p"}
        moves = ["d1d8", "d8d8", r_col + "d1"]
        puz = make_puzzle(w, b, moves, f"Queen Deflection Sacrifice on d8 with R{r_col}", seen)
        if puz: puzzles.append(puz)

    # Rook Sacrifice Decoy
    for r_start in ["h1", "h2", "h3", "h4", "h5"]:
        for q_pos in ["d1", "e1", "e2", "f3"]:
            w = {r_start: "R", q_pos: "Q", "g1": "K", "a2": "P"}
            b = {"g8": "K", "f7": "p", "g7": "p", "h7": "p"}
            moves = [r_start + "h8", "g8h8", q_pos + "h5"]
            puz = make_puzzle(w, b, moves, f"Rook Sacrifice on h8 with Q{q_pos}", seen)
            if puz: puzzles.append(puz)

    # Exchange sacrifice
    for r_col in "cdef":
        w = {r_col + "1": "R", "d1": "Q", "g1": "K", "h2": "P"}
        b = {r_col + "6": "n", "e8": "K", "d8": "q"}
        moves = [r_col + "1" + r_col + "6", "b7" + r_col + "6", "d1" + r_col + "6"] # test
        # We can also add Anastasia / Philidor sacrifices
    for q_diag in ["c4", "d5", "a2", "b3"]:
        for n_pos in ["e5", "g5", "h6"]:
            w = {q_diag: "Q", n_pos: "N", "g1": "K", "b2": "P"}
            b = {"h8": "K", "f8": "r", "g7": "p", "h7": "p"}
            moves = [q_diag + "g8", "f8g8", n_pos + "f7"]
            puz = make_puzzle(w, b, moves, f"Queen Sacrifice Smothered Mate with N{n_pos}", seen)
            if puz: puzzles.append(puz)

    return interleave_diverse(puzzles, 100)

# ==============================================================================
# 10. ATTRACTION
# ==============================================================================
def gen_attraction():
    puzzles = []
    seen = set()

    # Attraction into King Fork: 1. Bxf7+ Kxf7 2. Ne5+
    attraction_forks = [
        ("c4", "f7", "e8", "f7", "f3", "e5", "Attraction King to f7 into e5 Fork"),
        ("c4", "f7", "e8", "f7", "d2", "e4", "Attraction King to f7 into e4 Knight Fork"),
        ("b3", "f7", "e8", "f7", "g5", "e6", "Attraction King to f7 into e6 Knight Fork"),
    ]
    for b_st, b_dst, k_st, k_dst, n_st, n_dst, desc in attraction_forks:
        for wp in ["h2", "g2", "f2", "a2", "b2", "c2"]:
            w = {b_st: "B", n_st: "N", "g1": "K", wp: "P"}
            b = {k_st: "K", "d8": "q"}
            moves = [b_st + b_dst, k_st + k_dst, n_st + n_dst]
            puz = make_puzzle(w, b, moves, desc, seen)
            if puz: puzzles.append(puz)

    # Attraction onto Mating Square: Rh8+ Kxh8, Qh5#
    for r_start in ["h1", "h2", "h3", "h4", "h5"]:
        for q_pos in ["d1", "e1", "e2", "f3"]:
            w = {r_start: "R", q_pos: "Q", "g1": "K", "a2": "P"}
            b = {"g8": "K", "f7": "p", "g7": "p", "h7": "p"}
            moves = [r_start + "h8", "g8h8", q_pos + "h5"]
            puz = make_puzzle(w, b, moves, f"Attraction of King to h8 Corner Mate with Q{q_pos}", seen)
            if puz: puzzles.append(puz)

    # Greek Gift Attraction: Bxh7+ Kxh7, Qh5+
    for b_sq in ["d3", "c2", "b1", "e4"]:
        for q_sq in ["d1", "e1", "f3"]:
            w = {b_sq: "B", q_sq: "Q", "g1": "K", "f2": "P"}
            b = {"g8": "K", "f7": "p", "g7": "p", "h7": "p"}
            moves = [b_sq + "h7", "g8h7", q_sq + "h5"]
            puz = make_puzzle(w, b, moves, f"Attraction of King to h7 by Bishop {b_sq}", seen)
            if puz: puzzles.append(puz)

    # Attraction into Skewer: Ra8+ Kxa8, Qa1+
    for r_st in ["a1", "a2", "a3"]:
        w = {r_st: "R", "c1": "Q", "g1": "K", "h2": "P"}
        b = {"b8": "K", "h8": "q"}
        moves = [r_st + "a8", "b8a8", "c1a1"]
        puz = make_puzzle(w, b, moves, "Attraction of King to a8 into Queen Skewer", seen)
        if puz: puzzles.append(puz)

    return interleave_diverse(puzzles, 100)

# ==============================================================================
# 11. DEFLECTION
# ==============================================================================
def gen_deflection():
    puzzles = []
    seen = set()

    # Back-rank deflection: Double rook deflection
    for f in "abcdef":
        for r2_col in "gh":
            w = {f + "1": "R", r2_col + "1": "R", "g1": "K", "b2": "P"}
            b = {"g8": "K", f + "8": "r", "f7": "p", "g7": "p", "h7": "p"}
            moves = [f + "1" + f + "8", f + "8" + f + "8", r2_col + "1" + f + "8"]
            puz = make_puzzle(w, b, moves, f"Deflection of Defender on {f}-file into Back-Rank Mate", seen)
            if puz: puzzles.append(puz)

    # Queen deflection on d8
    for r_col in ["a1", "b1", "c1", "e1", "f1"]:
        w = {"d1": "Q", r_col: "R", "g1": "K", "a2": "P"}
        b = {"c8": "K", "d8": "r", "a7": "p", "b7": "p", "c7": "p"}
        moves = ["d1d8", "d8d8", r_col + "d1"]
        puz = make_puzzle(w, b, moves, f"Deflection of Rook with Queen Sac on d8", seen)
        if puz: puzzles.append(puz)

    # Deflection of Defender from Guard: Bxf7+ Qxf7, Qxe5
    for b_st in ["c4", "d3", "e2"]:
        for wp in ["a2", "b2", "g2", "h2"]:
            w = {b_st: "B", "e1": "Q", "g1": "K", wp: "P"}
            b = {"e8": "K", "d8": "q", "e5": "n"}
            moves = [b_st + "f7", "d8f7", "e1e5"]
            puz = make_puzzle(w, b, moves, f"Deflection of Queen from e5 Defense via Bishop {b_st}", seen)
            if puz: puzzles.append(puz)

    return interleave_diverse(puzzles, 100)

# ==============================================================================
# 12. CLEARANCE
# ==============================================================================
def gen_clearance():
    puzzles = []
    seen = set()

    # Clearance of rank/file for Queen/Rook infiltration
    for r_col in ["a", "b", "c", "d", "e"]:
        for wp in ["f2", "g2", "h2"]:
            w = {r_col + "1": "R", "b2": "Q", "g1": "K", wp: "P"}
            b = {"g8": "K", r_col + "8": "r", "f8": "r", "f7": "p", "g7": "p", "h7": "p"}
            moves = [r_col + "1" + r_col + "8", "f8" + r_col + "8", "b2b7"]
            puz = make_puzzle(w, b, moves, f"Rook Clearance on {r_col}-file for Queen Infiltration", seen)
            if puz: puzzles.append(puz)

    # Diagonal Clearance: Pawn moves clearing diagonal for Bishop
    for p_st, p_dst, b_st, b_dst, k_sq, k_dst, desc in [
        ("e4", "e5", "c4", "f7", "e8", "e8e7", "Pawn Push Clearance on e5 for Italian Bishop"),
        ("d4", "d5", "f4", "c7", "e8", "e8d7", "Pawn Push Clearance on d5 for Queen Bishop"),
    ]:
        for wp in ["a2", "b2", "g2", "h2"]:
            w = {p_st: "P", b_st: "B", "g1": "K", wp: "P"}
            b = {k_sq: "K", "d7": "p"}
            moves = [p_st + p_dst, "d7" + p_dst, b_st + b_dst] # verify
            puz = make_puzzle(w, b, moves, desc, seen)
            if puz: puzzles.append(puz)

    # Square Clearance: Knight moves with check to let Queen occupy square
    for n_st, n_chk, q_st, q_fin, k_sq, k_dst, desc in [
        ("e5", "f7", "d1", "h5", "e8", "e8d7", "Knight Leap Clearance on f7 for Queen Attack"),
        ("d5", "e7", "d1", "d8", "g8", "g8h8", "Knight Central Clearance on e7 for Queen"),
    ]:
        for wp in ["a2", "b2", "h2"]:
            w = {n_st: "N", q_st: "Q", "g1": "K", wp: "P"}
            b = {k_sq: "K", "c7": "p"}
            moves = [n_st + n_chk, k_sq + k_dst, q_st + q_fin]
            puz = make_puzzle(w, b, moves, desc, seen)
            if puz: puzzles.append(puz)

    # Add more file clearance variants
    for f in "abcdef":
        w = {f + "1": "R", "a2": "Q", "g1": "K", "h2": "P"}
        b = {"g8": "K", f + "8": "r", "g7": "p", "h7": "p"}
        moves = [f + "1" + f + "8", "g8" + f + "8", "a2a7"]
        puz = make_puzzle(w, b, moves, f"File Clearance on {f}8 for Queen 7th Rank Invasion", seen)
        if puz: puzzles.append(puz)

    return interleave_diverse(puzzles, 100)

# ==============================================================================
# 13. WINNING MATERIAL
# ==============================================================================
def gen_winning_material():
    puzzles = []
    seen = set()

    # Trapping Queen / Winning Hanging Queen
    for f in "cdef":
        for wp in ["a2", "b2", "h2", "g2"]:
            w = {f + "1": "R", "g1": "K", wp: "P"}
            b = {"g8": "K", f + "4": "q", "g7": "p", "h7": "p"}
            moves = [f + "1" + f + "4"]
            puz = make_puzzle(w, b, moves, f"Winning Hanging Queen on {f}4 with Rook", seen)
            if puz: puzzles.append(puz)

    # Bishop Fork winning Rook
    for b_st in ["b2", "c1", "d2", "e1", "f2"]:
        for wp in ["a2", "g2", "h2"]:
            w = {b_st: "B", "e1": "K", wp: "P"}
            b = {"e8": "K", "h4": "r"}
            moves = [b_st + "f6", "e8d7", "f6h4"]
            puz = make_puzzle(w, b, moves, f"Bishop Fork from {b_st} Winning Hanging Rook", seen)
            if puz: puzzles.append(puz)

    # Queen Fork winning Rook
    for q_st in ["e1", "d1", "c1", "f1"]:
        for wp in ["g2", "h2", "a2"]:
            w = {q_st: "Q", "g1": "K", wp: "P"}
            b = {"e8": "K", "b5": "r"}
            moves = [q_st + "e5", "e8d7", "e5b5"]
            puz = make_puzzle(w, b, moves, f"Queen Fork from {q_st} Winning b5 Rook", seen)
            if puz: puzzles.append(puz)

    # Knight Fork winning Rook
    for n_st in ["e5", "d5", "c4", "f4"]:
        for wp in ["a2", "b2", "h2"]:
            w = {n_st: "N", "e1": "K", wp: "P"}
            b = {"e8": "K", "h8": "r"}
            moves = [n_st + "f7", "e8e7", "f7h8"]
            puz = make_puzzle(w, b, moves, f"Knight Fork from {n_st} Winning Rook on h8", seen)
            if puz: puzzles.append(puz)

    # Intermediate Move (Zwischenzug) winning piece
    for r_st in ["d1", "e1", "f1"]:
        w = {r_st: "R", "g1": "K", "b2": "P"}
        b = {"d8": "r", "e8": "K", "h7": "p"}
        moves = [r_st + "d8", "e8d8"] # test
        # add clean capture
        w = {r_st: "R", "g1": "K", "h2": "P"}
        b = {"e8": "K", "d5": "n"}
        moves = [r_st + "d5"]
        puz = make_puzzle(w, b, moves, f"Rook Capture Winning Free Knight on d5", seen)
        if puz: puzzles.append(puz)

    return interleave_diverse(puzzles, 100)

print("All 13 generators defined in generate_all_13_databases.py")
