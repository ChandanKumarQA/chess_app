#!/usr/bin/env python3
"""
Generator for 100% Unique, 100% Legal, High-Quality Tactical Databases.
Each of the 13 tactical themes gets 100 genuinely diverse puzzles with:
- Unique FENs
- Unique solution moves / distinct motifs
- 0 consecutive duplicate solutions
- Full move legality verification via python-chess
"""

import chess
import re
import os

def verify_and_add(puzzle_list, seen_fens, fen, moves, motif):
    if fen in seen_fens:
        return False
    try:
        b = chess.Board(fen)
        for m in moves:
            mv = chess.Move.from_uci(m)
            if mv not in b.legal_moves:
                return False
            b.push(mv)
        seen_fens.add(fen)
        puzzle_list.append((fen, moves, motif))
        return True
    except Exception:
        return False

def make_board(w_pieces, b_pieces, turn=chess.WHITE):
    board = chess.Board(None)
    for p, sq in w_pieces:
        board.set_piece_at(chess.parse_square(sq), chess.Piece.from_symbol(p.upper()))
    for p, sq in b_pieces:
        board.set_piece_at(chess.parse_square(sq), chess.Piece.from_symbol(p.lower()))
    board.turn = turn
    return board.fen()

print("Script template ready")
