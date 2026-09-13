#!/usr/bin/env python3
import chess

def verify_puzzle(fen, moves):
    board = chess.Board(fen)
    for m in moves:
        move = chess.Move.from_uci(m)
        if move not in board.legal_moves:
            return False, f"Illegal move {m} in FEN {fen}"
        board.push(move)
    return True, "OK"

def generate_100_skewer_puzzles():
    puzzles = []
    seen_fens = set()
    seen_sols = set()

    # 1. BISHOP SKEWERS (King in front, Queen or Rook behind on diagonal)
    # We can place the skewer along multiple diagonals and rotate/shift them
    # Diagonals: a1-h8, h1-a8, a2-g8, b1-h7, h2-b8, g1-a7, c1-h6, f1-a6
    # Let us craft diverse, sound Bishop skewers
    b_specs = [
        # (White B start, White B target, Black King, Black King dest, Black Target sq, target piece, White King, extra pawns)
        # a1-h8 diagonal
        ("c1", "b2", "g7", "g8", "h8", "q", "g1", "f2,g2,h2"),
        ("d2", "c3", "f6", "g6", "g7", "r", "h1", "f2,g2,h3"),
        ("e3", "d4", "f6", "e7", "h8", "q", "g1", "f2,g3,h2"),
        ("e1", "d2", "f4", "e5", "g5", "r", "g1", "a2,b2"),
        ("f2", "e3", "g5", "f6", "h6", "q", "g1", "a2,b2,c2"),
        ("a1", "b2", "f6", "e7", "g7", "r", "g1", "a2,h2"),
        ("b1", "c2", "e4", "f5", "f5", "q", "g1", "a2,b2,c3"), # skip if invalid
        ("c3", "d4", "f6", "f7", "g7", "r", "h1", "a2,b2"),
        # h1-a8 diagonal
        ("f1", "g2", "b7", "b8", "a8", "q", "g1", "f2,g3,h2"),
        ("e2", "f3", "c6", "d7", "b7", "r", "g1", "g2,h2"),
        ("d3", "e4", "c6", "b6", "a8", "q", "h1", "f2,g2,h3"),
        ("d1", "e2", "b5", "c6", "a5", "r", "g1", "f2,g2"),
        ("c2", "d3", "b5", "c5", "a5", "q", "g1", "g2,h2"),
        ("h1", "g2", "c6", "b7", "b7", "r", "f1", "f2"),
        # a2-g8 diagonal
        ("f1", "c4", "e6", "e7", "g8", "q", "h1", "f2,g2,h2"),
        ("d3", "c4", "e6", "f6", "g8", "r", "g1", "f2,g2"),
        ("e2", "d3", "f5", "f6", "g6", "q", "g1", "a2,b2"),
        ("e1", "c3", "e5", "f6", "f6", "r", "g1", "a2,b2"),
        ("a2", "b3", "d5", "e6", "f7", "q", "h1", "a2,b2"),
        ("b1", "a2", "d5", "c6", "e6", "r", "g1", "f2,g2"),
        # h2-b8 diagonal
        ("e3", "f4", "d6", "e7", "b8", "q", "g1", "f2,g2,h2"),
        ("f2", "e3", "d4", "c5", "b8", "r", "g1", "g2,h3"),
        ("g1", "f2", "d4", "e5", "c5", "q", "h1", "a2,b2"),
        ("d1", "e2", "c4", "b5", "b5", "r", "g1", "f2,g2"),
        # b1-h7 diagonal
        ("c2", "d3", "f5", "e6", "h7", "q", "g1", "a2,b2,c2"),
        ("a2", "b3", "e6", "f7", "g7", "r", "h1", "a2,c2"),
        ("b1", "d3", "f5", "g6", "h7", "r", "g1", "f2,g2"),
        # g1-a7 diagonal
        ("f2", "e3", "c5", "d6", "a7", "q", "g1", "f2,g2,h2"),
        ("g1", "e3", "c5", "b6", "a7", "r", "h1", "f2,g3"),
        ("e1", "d2", "b4", "c5", "a5", "q", "g1", "f2,g2"),
    ]

    # Let us build valid board representations programmatically
    # We can write a clean, general generator function that places pieces on empty board + White King + pawns
    # and validates using chess.Board()!
    print("Testing generator logic...")

if __name__ == "__main__":
    generate_100_skewer_puzzles()
