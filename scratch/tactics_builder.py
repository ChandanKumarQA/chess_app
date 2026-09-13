import chess

def verify_moves(fen, moves):
    b = chess.Board(fen)
    for m_str in moves:
        m = chess.Move.from_uci(m_str)
        if m not in b.legal_moves:
            return False
        b.push(m)
    return True

print("Tactics builder core ready")
