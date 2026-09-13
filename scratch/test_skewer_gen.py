#!/usr/bin/env python3
import chess

def make_fen(white_dict, black_dict, turn=chess.WHITE):
    board = chess.Board(None)
    for sq, p in white_dict.items():
        board.set_piece_at(chess.parse_square(sq), chess.Piece.from_symbol(p.upper()))
    for sq, p in black_dict.items():
        board.set_piece_at(chess.parse_square(sq), chess.Piece.from_symbol(p.lower()))
    board.turn = turn
    return board.fen()

def test_skewer_gen():
    puzzles = []
    seen_fens = set()
    seen_first_moves = []

    # 1. ROOK SKEWERS ON RANKS (ranks 1 to 8)
    for r in range(1, 9):
        # We can create skewers from the left (a-file) and from the right (h-file)
        # Left: R moves from (b/c, r) to (a, r), checks K on (c/d/e, r), skewering target on (g/h, r)
        r_str = str(r)
        wk_sq = "g1" if r >= 3 else "g8"
        wp_sq = "f2" if r >= 3 else "f7"

        # Left to right skewers
        for k_file, t_file, target in [("d", "h", "q"), ("e", "h", "r"), ("c", "g", "q"), ("d", "g", "r"), ("e", "g", "b"), ("c", "h", "n")]:
            start_f = "b"
            start_sq = start_f + r_str
            move_sq = "a" + r_str
            k_sq = k_file + r_str
            t_sq = t_file + r_str
            k_dest_r = str(r + 1 if r < 8 else r - 1)
            k_dest = k_file + k_dest_r

            w = {start_sq: "R", wk_sq: "K", wp_sq: "P"}
            b = {k_sq: "K", t_sq: target}
            fen = make_fen(w, b)
            moves = [start_sq + move_sq, k_sq + k_dest, move_sq + t_sq]

            # verify
            try:
                board = chess.Board(fen)
                valid = True
                for m in moves:
                    mv = chess.Move.from_uci(m)
                    if mv not in board.legal_moves:
                        valid = False
                        break
                    board.push(mv)
                if valid and fen not in seen_fens:
                    seen_fens.add(fen)
                    puzzles.append((fen, moves, f"Rank {r} Rook Skewer winning {target.upper()}"))
            except Exception:
                pass

        # Right to left skewers
        for k_file, t_file, target in [("e", "a", "q"), ("d", "a", "r"), ("f", "b", "q"), ("e", "b", "r"), ("f", "a", "b"), ("d", "b", "n")]:
            start_f = "g"
            start_sq = start_f + r_str
            move_sq = "h" + r_str
            k_sq = k_file + r_str
            t_sq = t_file + r_str
            k_dest_r = str(r + 1 if r < 8 else r - 1)
            k_dest = k_file + k_dest_r

            w = {start_sq: "R", wk_sq: "K", wp_sq: "P"}
            b = {k_sq: "K", t_sq: target}
            fen = make_fen(w, b)
            moves = [start_sq + move_sq, k_sq + k_dest, move_sq + t_sq]

            try:
                board = chess.Board(fen)
                valid = True
                for m in moves:
                    mv = chess.Move.from_uci(m)
                    if mv not in board.legal_moves:
                        valid = False
                        break
                    board.push(mv)
                if valid and fen not in seen_fens:
                    seen_fens.add(fen)
                    puzzles.append((fen, moves, f"Rank {r} Reverse Rook Skewer winning {target.upper()}"))
            except Exception:
                pass

    print(f"Rank skewers: {len(puzzles)}")

    # 2. ROOK SKEWERS ON FILES (files a to h)
    for f in "abcdefgh":
        # Rook moves from rank 2/3 to rank 1 or 8
        wk_sq = "a1" if f in "fgh" else "h1"
        wp_sq = "b2" if f in "fgh" else "g2"

        # Upward file skewer: Rook moves to rank 1, checks King on rank 4/5, target on rank 8
        for k_r, t_r, target in [(4, 8, "q"), (5, 8, "r"), (3, 7, "q"), (4, 7, "r"), (5, 7, "b")]:
            start_sq = f + "2"
            move_sq = f + "1"
            k_sq = f + str(k_r)
            t_sq = f + str(t_r)
            k_dest_f = chr(ord(f) + 1 if f != "h" else ord(f) - 1)
            k_dest = k_dest_f + str(k_r)

            w = {start_sq: "R", wk_sq: "K", wp_sq: "P"}
            b = {k_sq: "K", t_sq: target}
            fen = make_fen(w, b)
            moves = [start_sq + move_sq, k_sq + k_dest, move_sq + t_sq]

            try:
                board = chess.Board(fen)
                valid = True
                for m in moves:
                    mv = chess.Move.from_uci(m)
                    if mv not in board.legal_moves:
                        valid = False
                        break
                    board.push(mv)
                if valid and fen not in seen_fens:
                    seen_fens.add(fen)
                    puzzles.append((fen, moves, f"{f}-file Upward Rook Skewer winning {target.upper()}"))
            except Exception:
                pass

        # Downward file skewer: Rook moves to rank 8, checks King on rank 5/4, target on rank 1
        for k_r, t_r, target in [(5, 1, "q"), (4, 1, "r"), (6, 2, "q"), (5, 2, "r"), (4, 2, "b")]:
            start_sq = f + "7"
            move_sq = f + "8"
            k_sq = f + str(k_r)
            t_sq = f + str(t_r)
            k_dest_f = chr(ord(f) + 1 if f != "h" else ord(f) - 1)
            k_dest = k_dest_f + str(k_r)

            w = {start_sq: "R", wk_sq: "K", wp_sq: "P"}
            b = {k_sq: "K", t_sq: target}
            fen = make_fen(w, b)
            moves = [start_sq + move_sq, k_sq + k_dest, move_sq + t_sq]

            try:
                board = chess.Board(fen)
                valid = True
                for m in moves:
                    mv = chess.Move.from_uci(m)
                    if mv not in board.legal_moves:
                        valid = False
                        break
                    board.push(mv)
                if valid and fen not in seen_fens:
                    seen_fens.add(fen)
                    puzzles.append((fen, moves, f"{f}-file Downward Rook Skewer winning {target.upper()}"))
            except Exception:
                pass

    print(f"Total with file skewers: {len(puzzles)}")

test_skewer_gen()
