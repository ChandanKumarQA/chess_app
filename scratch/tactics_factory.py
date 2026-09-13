#!/usr/bin/env python3
import chess

def verify_and_add(b, moves, motif, seen_moves, seen_fens, puzzles):
    move_tuple = tuple(moves)
    if move_tuple in seen_moves:
        return False
    try:
        test_b = b.copy()
        if test_b.status() & chess.STATUS_OPPOSITE_CHECK:
            return False
        for m_str in moves:
            m = chess.Move.from_uci(m_str)
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

print("Tactics factory core loaded")
