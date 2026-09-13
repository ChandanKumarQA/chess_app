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

# 1. MATE IN 1
def gen_mate_in_1():
    from generate_all_13_databases import gen_mate_in_1 as g1
    return g1()

# 2. MATE IN 2
def gen_mate_in_2():
    from expand_tactics import gen_mate_in_2_expanded as g2
    return g2()

# 3. FORK
def gen_fork():
    from generate_all_13_databases import gen_fork as g3
    return g3()

# 4. PIN
def gen_pin():
    from expand_tactics import gen_pin_expanded as g4
    return g4()

# 5. SKEWER
def gen_skewer():
    puzzles = []
    seen = set()

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
        for wp in ["h2", "g2", "f2", "a2", "b2", "c2", "d2"]:
            w = {b_st: "B", "g1": "K", wp: "P"}
            b = {k_sq: "K", t_sq: tgt}
            moves = [b_st + b_mv, k_sq + k_dst, b_mv + t_sq]
            puz = make_puzzle(w, b, moves, desc, seen)
            if puz: puzzles.append(puz)

    for r in range(3, 8):
        r_str = str(r)
        for tgt in ["q", "r"]:
            for f_start in ["a", "b", "c", "f", "g", "h"]:
                for wp in ["h2", "g2", "b2", "a2"]:
                    w = {f_start + "1": "R", "g1": "K", wp: "P"}
                    b = {"d" + r_str: "K", "h" + r_str: tgt}
                    moves = [f_start + "1" + "a" + r_str, "d" + r_str + "d" + str(r - 1), "a" + r_str + "h" + r_str]
                    puz = make_puzzle(w, b, moves, f"Rank {r} Rook Skewer on {tgt.upper()} from {f_start}1", seen)
                    if puz: puzzles.append(puz)

                    w2 = {f_start + "1": "R", "g1": "K", wp: "P"}
                    b2 = {"e" + r_str: "K", "a" + r_str: tgt}
                    moves2 = [f_start + "1" + "h" + r_str, "e" + r_str + "e" + str(r - 1), "h" + r_str + "a" + r_str]
                    puz2 = make_puzzle(w2, b2, moves2, f"Rank {r} Kingside Rook Skewer on {tgt.upper()} from {f_start}1", seen)
                    if puz2: puzzles.append(puz2)

    return interleave_diverse(puzzles, 100)

# 6. DOUBLE ATTACK
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
        ("d1", "f3", "g8", "h8", "a8", "r", "Queen f3 Check & Attack on a8 Rook"),
        ("d1", "d5", "e8", "f8", "h5", "r", "Central Queen d5 Check & Attack on h5 Rook"),
        ("e1", "c3", "g8", "h8", "a5", "r", "Queen c3 Check & Attack on a5 Rook"),
        ("b1", "c2", "d7", "c8", "h7", "r", "Queen c2 Check & Attack on h7 Rook"),
        ("f1", "c4", "d6", "c6", "h4", "r", "Queen c4 Check & Attack on h4 Rook"),
    ]
    for q_st, q_dst, k_sq, k_dst, t_sq, tp, desc in q_attacks:
        for wp in ["g2", "h2", "f2", "b2", "a2", "c2", "d2"]:
            w = {q_st: "Q", "g1": "K", wp: "P"}
            b = {k_sq: "K", t_sq: tp}
            moves = [q_st + q_dst, k_sq + k_dst, q_dst + t_sq]
            puz = make_puzzle(w, b, moves, desc, seen)
            if puz: puzzles.append(puz)

    knight_doubles = [
        ("b5", "d6", "e8", "f8", "f7", "b", "Knight Infiltration Double Attack on King & Bishop"),
        ("c4", "e5", "d7", "c8", "g6", "r", "Knight Central Fork Double Attack on King & Rook"),
        ("d4", "f5", "e7", "f6", "d6", "r", "Knight Outpost Double Attack on King & Rook"),
        ("f3", "d4", "e6", "d7", "c6", "r", "Knight d4 Double Attack on King & Rook"),
        ("e2", "d4", "e6", "f7", "b5", "r", "Knight Central Double Attack on b5 Rook"),
        ("g3", "e4", "d6", "c6", "f6", "r", "Knight e4 Outpost Double Attack on f6 Rook"),
    ]
    for n_st, n_dst, k_sq, k_dst, t_sq, tp, desc in knight_doubles:
        for wp in ["g2", "h2", "a2", "b2", "f2", "c2"]:
            w = {n_st: "N", "g1": "K", wp: "P"}
            b = {k_sq: "K", t_sq: tp}
            moves = [n_st + n_dst, k_sq + k_dst, n_dst + t_sq]
            puz = make_puzzle(w, b, moves, desc, seen)
            if puz: puzzles.append(puz)

    return interleave_diverse(puzzles, 100)

# 7. DISCOVERED ATTACK
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
        for wp in ["h2", "g2", "f2", "a2", "b2", "c2", "b3", "a3"]:
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
        for wp in ["h2", "g2", "a2", "b2", "f2", "c2"]:
            w = {n_st: "N", r_st: "R", "g1": "K", wp: "P"}
            b = {k_sq: "K", q_sq: "q"}
            moves = [n_st + n_dst, k_sq + k_dst, r_st + q_sq]
            puz = make_puzzle(w, b, moves, desc, seen)
            if puz: puzzles.append(puz)

    return interleave_diverse(puzzles, 100)

# 8. SMOTHERED MATE
def gen_smothered_mate():
    from generate_all_13_databases import gen_smothered_mate as g8
    return g8()

# 9. SACRIFICE
def gen_sacrifice():
    puzzles = []
    seen = set()

    for b_sq in ["d3", "c2", "b1", "e4", "d2"]:
        for q_sq in ["d1", "e1", "f3", "h5"]:
            for wp in ["a2", "b2", "f2", "c2"]:
                w = {b_sq: "B", q_sq: "Q", "g1": "K", wp: "P"}
                b = {"g8": "K", "f7": "p", "g7": "p"}
                moves = [b_sq + "h7", "g8h7", q_sq + "h5"]
                puz = make_puzzle(w, b, moves, f"Greek Gift Bishop Sacrifice from {b_sq}", seen)
                if puz: puzzles.append(puz)

    for r_start in ["h1", "h2", "h3", "h4", "h5"]:
        for q_pos in ["d1", "e1", "e2", "f3"]:
            for wp in ["a2", "b2", "c2", "d2"]:
                w = {r_start: "R", q_pos: "Q", "g1": "K", wp: "P"}
                b = {"g8": "K", "f7": "p", "g7": "p", "h7": "p"}
                moves = [r_start + "h8", "g8h8", q_pos + "h5"]
                puz = make_puzzle(w, b, moves, f"Rook Sacrifice on h8 with Q{q_pos}", seen)
                if puz: puzzles.append(puz)

    for q_diag in ["c4", "d5", "e6", "a2", "b3"]:
        for n_pos in ["e5", "g5", "h6", "d6"]:
            for wp in ["a2", "b2", "c2", "d2", "f2"]:
                w = {q_diag: "Q", n_pos: "N", "g1": "K", wp: "P"}
                b = {"h8": "K", "f8": "r", "g7": "p", "h7": "p"}
                moves = [q_diag + "g8", "f8g8", n_pos + "f7"]
                puz = make_puzzle(w, b, moves, f"Queen Sacrifice Smothered Mate with N{n_pos}", seen)
                if puz: puzzles.append(puz)

    return interleave_diverse(puzzles, 100)

# 10. ATTRACTION
def gen_attraction():
    puzzles = []
    seen = set()

    # Attraction into King Fork: 1. Bxf7+ Kxf7 2. Ne5+
    attraction_forks = [
        ("c4", "f7", "e8", "f7", "f3", "e5", "Attraction King to f7 into e5 Fork"),
        ("c4", "f7", "e8", "f7", "d2", "e4", "Attraction King to f7 into e4 Knight Fork"),
        ("b3", "f7", "e8", "f7", "g5", "e6", "Attraction King to f7 into e6 Knight Fork"),
        ("d3", "f7", "e8", "f7", "g4", "e5", "Attraction King to f7 into e5 Central Fork"),
        ("f1", "f7", "e8", "f7", "c3", "e4", "Attraction King to f7 into e4 Knight Jump"),
        ("e2", "f7", "e8", "f7", "d4", "e6", "Attraction King to f7 into e6 Outpost Fork"),
        ("e3", "f7", "e8", "f7", "d5", "e7", "Attraction King to f7 into e7 Knight Fork"),
        ("c4", "f7", "e8", "f7", "e5", "d7", "Attraction King to f7 into d7 Knight Fork"),
        ("b3", "f7", "e8", "f7", "e4", "d6", "Attraction King to f7 into d6 Knight Fork"),
        ("d3", "f7", "e8", "f7", "f5", "e7", "Attraction King to f7 into e7 Flank Fork"),
        ("a4", "f7", "e8", "f7", "c4", "e5", "Attraction King to f7 into e5 Knight Jump"),
        ("c4", "f7", "e8", "f7", "f4", "e6", "Attraction King to f7 into e6 Knight Strike"),
    ]
    for b_st, b_dst, k_st, k_dst, n_st, n_dst, desc in attraction_forks:
        for wp in ["h2", "g2", "f2", "a2", "b2", "c2", "d2"]:
            w = {b_st: "B", n_st: "N", "g1": "K", wp: "P"}
            b = {k_st: "K", "d8": "q"}
            moves = [b_st + b_dst, k_st + k_dst, n_st + n_dst]
            puz = make_puzzle(w, b, moves, desc, seen)
            if puz: puzzles.append(puz)

    # Attraction onto Mating Square: Rh8+ Kxh8, Qh5#
    for r_start in ["h1", "h2", "h3", "h4", "h5"]:
        for q_pos in ["d1", "e1", "e2", "f3"]:
            for wp in ["a2", "b2", "c2", "d2"]:
                w = {r_start: "R", q_pos: "Q", "g1": "K", wp: "P"}
                b = {"g8": "K", "f7": "p", "g7": "p", "h7": "p"}
                moves = [r_start + "h8", "g8h8", q_pos + "h5"]
                puz = make_puzzle(w, b, moves, f"Attraction King to h8 Mating Square Q{q_pos}", seen)
                if puz: puzzles.append(puz)

    # Greek Gift Attraction: Bxh7+ Kxh7, Qh5+
    for b_sq in ["d3", "c2", "b1", "e4"]:
        for q_sq in ["d1", "e1", "f3"]:
            for wp in ["a2", "b2", "f2", "c2"]:
                w = {b_sq: "B", q_sq: "Q", "g1": "K", wp: "P"}
                b = {"g8": "K", "f7": "p", "g7": "p"}
                moves = [b_sq + "h7", "g8h7", q_sq + "h5"]
                puz = make_puzzle(w, b, moves, f"Attraction King to h7 via Bishop {b_sq}", seen)
                if puz: puzzles.append(puz)

    # Attraction into Queen Skewer: Ra8+ Kxa8, Qa1+
    for r_st in ["a1", "a2", "a3"]:
        for q_st in ["c1", "d1", "e1"]:
            for wp in ["h2", "g2", "f2", "c2", "b2"]:
                w = {r_st: "R", q_st: "Q", "g1": "K", wp: "P"}
                b = {"b8": "K", "h8": "q"}
                moves = [r_st + "a8", "b8a8", q_st + "a1"]
                puz = make_puzzle(w, b, moves, f"Attraction of King to a8 into Q{q_st} Skewer", seen)
                if puz: puzzles.append(puz)

    return interleave_diverse(puzzles, 100)

# 11. DEFLECTION
def gen_deflection():
    puzzles = []
    seen = set()

    # Kingside Deflection:
    # Black King on g8, pawns f7, g7, h7, Rook on e8.
    # Defending piece can be Knight on c6, e6, b7, f7, or Bishop on e7, g5, b6, f6!
    # White Queen on d1 sacrifices on d8! (d1d8+!)
    # Black piece takes on d8!
    # White Rook on e1 delivers checkmate on e8! (e1e8#!)
    for def_piece, def_sq in [
        ("n", "c6"), ("n", "e6"), ("n", "b7"), ("n", "f7"),
        ("b", "e7"), ("b", "g5"), ("b", "b6"), ("b", "f6"),
        ("q", "c7"), ("q", "f7"), ("q", "e7")
    ]:
        for wp in ["a2", "b2", "c2", "f2", "g2", "h2"]:
            w = {"d1": "Q", "e1": "R", "g1": "K", wp: "P"}
            b = {"g8": "K", "e8": "r", def_sq: def_piece, "f7": "p", "g7": "p", "h7": "p"}
            moves = ["d1d8", def_sq + "d8", "e1e8"]
            puz = make_puzzle(w, b, moves, f"Kingside Deflection of {def_piece.upper()}{def_sq} into e8 Mate", seen)
            if puz: puzzles.append(puz)

    # Queenside Deflection:
    # Black King on b8, pawns a7, b7, c7, Rook on d8.
    # Defending piece on f6, d6, c7, e7, g7!
    # White Queen on e1 sacrifices on e8! (e1e8+!)
    # Black piece takes on e8!
    # White Rook on d1 delivers checkmate on d8! (d1d8#!)
    for def_piece, def_sq in [
        ("n", "f6"), ("n", "d6"), ("n", "c7"), ("n", "g7"),
        ("b", "f7"), ("b", "g6"), ("b", "c6"),
        ("q", "e7"), ("q", "d6"), ("q", "c7")
    ]:
        for wp in ["a2", "b2", "c2", "f2", "g2", "h2"]:
            w = {"e1": "Q", "d1": "R", "g1": "K", wp: "P"}
            b = {"b8": "K", "d8": "r", def_sq: def_piece, "a7": "p", "b7": "p", "c7": "p"}
            moves = ["e1e8", def_sq + "e8", "d1d8"]
            puz = make_puzzle(w, b, moves, f"Queenside Deflection of {def_piece.upper()}{def_sq} into d8 Mate", seen)
            if puz: puzzles.append(puz)

    # Deflection of King from piece defense: 1. Bxf7+ Kxf7, 2. Qxe5
    for b_sq in ["c4", "d3", "b3", "e2", "f1", "d5", "b5", "a4"]:
        for wp in ["a2", "b2", "c2", "g2", "h2", "f2"]:
            w = {b_sq: "B", "e1": "Q", "g1": "K", wp: "P"}
            b = {"e8": "K", "d8": "q", "e5": "n"}
            moves = [b_sq + "f7", "e8f7", "e1e5"]
            puz = make_puzzle(w, b, moves, f"Deflection of King from e5 Defense via B{b_sq}", seen)
            if puz: puzzles.append(puz)

    return interleave_diverse(puzzles, 100)

# 12. CLEARANCE
def gen_clearance():
    puzzles = []
    seen = set()

    # Line Clearance on all files for Queen Infiltration:
    for f in ["a", "b", "c", "d", "e", "f"]:
        for r2_col in ["a", "b", "c", "d", "e", "f"]:
            if r2_col == f: continue
            for q_st in ["a2", "b2", "c2", "d2", "e2", "f2"]:
                if q_st[0] == f or q_st[0] == r2_col: continue
                for wp in ["g2", "h2", "f2", "c2"]:
                    if wp == q_st: continue
                    w = {f + "1": "R", q_st: "Q", "g1": "K", wp: "P"}
                    b = {f + "8": "r", r2_col + "8": "r", "g8": "K", "f7": "p", "g7": "p", "h7": "p"}
                    moves = [f + "1" + f + "8", r2_col + "8" + f + "8", q_st + q_st[0] + "7"]
                    puz = make_puzzle(w, b, moves, f"Line Clearance on {f}-file for Q{q_st} Infiltration", seen)
                    if puz: puzzles.append(puz)

    # Square Clearance: Knight moves with check, clearing square for Queen
    for n_st, n_chk, q_st, q_fin, k_sq, k_dst, desc in [
        ("e5", "f7", "d1", "h5", "e8", "e8d7", "Knight Leap Clearance on f7 for Queen Attack"),
        ("d5", "e7", "d1", "d8", "g8", "g8h8", "Knight Central Clearance on e7 for Queen"),
        ("c4", "d6", "d1", "d8", "e8", "e8f8", "Knight Clearance on d6 for Central Queen Infiltration"),
        ("f4", "e6", "d1", "d8", "e8", "e8f7", "Knight Clearance on e6 for Queen Mate Threat"),
        ("g5", "f7", "d1", "h5", "g8", "g8h8", "Knight Clearance on f7 for Queen h5 Mate"),
    ]:
        for wp in ["a2", "b2", "h2", "g2", "c2", "f2"]:
            w = {n_st: "N", q_st: "Q", "g1": "K", wp: "P"}
            b = {k_sq: "K", "c7": "p"}
            moves = [n_st + n_chk, k_sq + k_dst, q_st + q_fin]
            puz = make_puzzle(w, b, moves, desc, seen)
            if puz: puzzles.append(puz)

    return interleave_diverse(puzzles, 100)

# 13. WINNING MATERIAL
def gen_winning_material():
    puzzles = []
    seen = set()

    # Trapping Queen on ranks 4, 5, 6
    for f in "abcdefgh":
        for rank in ["4", "5"]:
            for wp in ["a2", "b2", "h2", "g2", "c2"]:
                if f + "1" == wp: continue
                w = {f + "1": "R", "g1": "K", wp: "P"}
                b = {"g8": "K", f + rank: "q", "g7": "p", "h7": "p"}
                moves = [f + "1" + f + rank]
                puz = make_puzzle(w, b, moves, f"Winning Hanging Queen on {f}{rank} with Rook", seen)
                if puz: puzzles.append(puz)

    # Bishop Fork winning Rook
    for b_st in ["b2", "c1", "d2", "e1", "f2", "a3", "d3", "e3", "c4", "f4"]:
        for wp in ["a2", "g2", "h2", "f2", "b2", "c2"]:
            if b_st == wp: continue
            w = {b_st: "B", "e1": "K", wp: "P"}
            b = {"e8": "K", "h4": "r"}
            moves = [b_st + "f6", "e8d7", "f6h4"]
            puz = make_puzzle(w, b, moves, f"Bishop Fork from {b_st} Winning Hanging Rook", seen)
            if puz: puzzles.append(puz)

    # Queen Fork winning Rook
    for q_st in ["e1", "d1", "c1", "f1", "b1", "a1", "e2", "d2"]:
        for wp in ["g2", "h2", "a2", "b2", "c2", "f2"]:
            if q_st == wp: continue
            w = {q_st: "Q", "g1": "K", wp: "P"}
            b = {"e8": "K", "b5": "r"}
            moves = [q_st + "e5", "e8d7", "e5b5"]
            puz = make_puzzle(w, b, moves, f"Queen Fork from {q_st} Winning b5 Rook", seen)
            if puz: puzzles.append(puz)

    # Knight Fork winning Rook
    for n_st in ["e5", "d5", "c4", "f4", "g5", "b5", "c6", "f6", "e4", "d4"]:
        for wp in ["a2", "b2", "h2", "g2", "c2", "f2"]:
            w = {n_st: "N", "e1": "K", wp: "P"}
            b = {"e8": "K", "h8": "r"}
            moves = [n_st + "f7", "e8e7", "f7h8"]
            puz = make_puzzle(w, b, moves, f"Knight Fork from {n_st} Winning Rook on h8", seen)
            if puz: puzzles.append(puz)

    # Hanging Knight capture with check:
    for f in "cdef":
        for wp in ["a2", "b2", "g2", "h2"]:
            w = {f + "1": "R", "g1": "K", wp: "P"}
            b = {"e8": "K", f + "5": "n", "a7": "p", "b7": "p"}
            moves = [f + "1" + f + "5", "e8d8", f + "5" + f + "8"] # test
            # 1. Rxe5+ Kd8 2. Rd5 (pin/threat)
            w2 = {f + "1": "R", "g1": "K", wp: "P"}
            b2 = {"e8": "K", f + "5": "n"}
            moves2 = [f + "1" + f + "5"]
            puz2 = make_puzzle(w2, b2, moves2, f"Rook Capture on {f}5 Winning Free Knight", seen)
            if puz2: puzzles.append(puz2)

    return interleave_diverse(puzzles, 100)

print("Master perfect 13 ready")
