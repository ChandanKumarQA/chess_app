import chess
import os

def check_puzzle(fen, moves):
    try:
        b = chess.Board(fen)
        if b.is_check(): # White in check at start is ok if legal, but let's avoid opponent already checked
            pass
        for m_str in moves:
            m = chess.Move.from_uci(m_str)
            if m not in b.legal_moves:
                return False
            b.push(m)
        return True
    except:
        return False

print("Helper ready")
