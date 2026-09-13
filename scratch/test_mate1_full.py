import chess

def gen_unique_mate_in_1():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    def add(b, move_str, motif):
        if move_str in seen_moves: return
        try:
            m = chess.Move.from_uci(move_str)
            if m in b.legal_moves:
                b.push(m)
                if b.is_checkmate():
                    b.pop()
                    fen = b.fen()
                    if fen not in seen_fens:
                        seen_moves.add(move_str)
                        seen_fens.add(fen)
                        puzzles.append((fen, [move_str], motif))
                else:
                    b.pop()
        except:
            pass

    # Rooks on g8 back-rank
    for start_file in "abcdef":
        for start_rank in range(1, 8):
            b = chess.Board(None)
            b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            sq = chess.parse_square(f"{start_file}{start_rank}")
            b.set_piece_at(sq, chess.Piece(chess.ROOK, chess.WHITE))
            b.turn = chess.WHITE
            add(b, f"{start_file}{start_rank}{start_file}8", f"{start_file.upper()}-file Back-Rank Mate")

    # Rooks on b8 back-rank
    for start_file in "cdefgh":
        for start_rank in range(1, 8):
            b = chess.Board(None)
            b.set_piece_at(chess.B8, chess.Piece(chess.KING, chess.BLACK))
            b.set_piece_at(chess.A7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.B7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.C7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
            sq = chess.parse_square(f"{start_file}{start_rank}")
            b.set_piece_at(sq, chess.Piece(chess.ROOK, chess.WHITE))
            b.turn = chess.WHITE
            add(b, f"{start_file}{start_rank}{start_file}8", f"Queenside Back-Rank Mate on b8")

    # Queen kiss of death on f7
    for q_st in ["f3", "h5", "e2", "d5", "c4", "b3", "g4", "f2", "d1", "e1", "f4", "g3", "h3"]:
        b = chess.Board(None)
        b.set_piece_at(chess.E8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.D7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
            b.set_piece_at(chess.C4, chess.Piece(chess.BISHOP, chess.WHITE))
            b.turn = chess.WHITE
            add(b, f"{q_st}f7", f"Scholar Pattern Queen Mate on f7")
        except: pass

    # Queen kiss of death on g7
    for q_st in ["f3", "h6", "e5", "d4", "c3", "b2", "f6", "g4", "f5", "h5", "e4", "d3", "c2"]:
        b = chess.Board(None)
        b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
            b.set_piece_at(chess.B2, chess.Piece(chess.BISHOP, chess.WHITE))
            b.turn = chess.WHITE
            add(b, f"{q_st}g7", f"Fianchetto Battery Queen Mate on g7")
        except: pass

    # Queen kiss of death on h7
    for q_st in ["h5", "h4", "e2", "c2", "d3", "f3", "g5", "h6", "e4", "f5", "d2"]:
        b = chess.Board(None)
        b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(q_st), chess.Piece(chess.QUEEN, chess.WHITE))
            b.set_piece_at(chess.D3, chess.Piece(chess.BISHOP, chess.WHITE))
            b.turn = chess.WHITE
            add(b, f"{q_st}h7", f"Diagonal Battery Queen Mate on h7")
        except: pass

    # Knight smothered & Arabian
    for n_st, dst, k_sq in [
        ("e5", "f7", "h8"), ("g5", "f7", "h8"), ("d6", "f7", "h8"), ("h6", "f7", "h8"),
        ("d5", "e7", "f8"), ("f5", "e7", "f8"), ("c6", "e7", "f8"), ("g6", "e7", "f8"),
        ("b5", "c7", "a8"), ("d5", "c7", "a8"), ("a6", "c7", "a8"), ("e6", "c7", "a8"),
        ("a5", "b7", "c8"), ("c5", "b7", "c8"), ("d6", "b7", "c8"), ("f6", "h7", "h8"),
        ("g5", "h7", "h8"), ("f7", "h6", "g8")
    ]:
        b = chess.Board(None)
        b.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
        if k_sq == "h8":
            b.set_piece_at(chess.G8, chess.Piece(chess.ROOK, chess.BLACK))
            b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
        elif k_sq == "f8":
            b.set_piece_at(chess.E8, chess.Piece(chess.ROOK, chess.BLACK))
            b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
        elif k_sq == "a8":
            b.set_piece_at(chess.B8, chess.Piece(chess.ROOK, chess.BLACK))
            b.set_piece_at(chess.A7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.B7, chess.Piece(chess.PAWN, chess.BLACK))
        elif k_sq == "c8":
            b.set_piece_at(chess.D8, chess.Piece(chess.ROOK, chess.BLACK))
            b.set_piece_at(chess.B7, chess.Piece(chess.PAWN, chess.BLACK))
            b.set_piece_at(chess.C7, chess.Piece(chess.PAWN, chess.BLACK))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(n_st), chess.Piece(chess.KNIGHT, chess.WHITE))
            b.turn = chess.WHITE
            add(b, f"{n_st}{dst}", f"Knight Smothered Mate on {dst}")
        except: pass

    # Bishop Boden mates
    for b_st, b_dst in [
        ("c4", "a6"), ("f1", "a6"), ("e2", "a6"), ("d3", "a6"),
        ("c1", "g5"), ("d2", "g5"), ("e3", "g5"), ("f4", "g5"),
        ("c1", "f4"), ("d2", "f4"), ("e3", "f4"),
        ("b2", "g7"), ("c3", "g7"), ("d4", "g7")
    ]:
        b = chess.Board(None)
        b.set_piece_at(chess.C8, chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.D7, chess.Piece(chess.ROOK, chess.BLACK))
        b.set_piece_at(chess.B8, chess.Piece(chess.ROOK, chess.BLACK))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(b_st), chess.Piece(chess.BISHOP, chess.WHITE))
            b.set_piece_at(chess.F4, chess.Piece(chess.BISHOP, chess.WHITE))
            b.turn = chess.WHITE
            add(b, f"{b_st}{b_dst}", f"Boden Diagonal Bishop Mate on {b_dst}")
        except: pass

    # Pawn checkmates
    for p_st, p_dst, k_sq, h_sq in [
        ("f6", "f7", "h8", "g6"), ("g6", "g7", "h8", "f6"),
        ("e6", "e7", "e8", "d6"), ("d6", "d7", "d8", "c6"),
        ("c6", "c7", "a8", "b6"), ("b6", "b7", "a8", "c6"),
        ("f5", "f6", "h7", "g5"), ("e5", "e6", "e7", "d5"),
        ("d5", "d6", "d7", "c5"), ("c5", "c6", "c7", "b5")
    ]:
        b = chess.Board(None)
        b.set_piece_at(chess.parse_square(k_sq), chess.Piece(chess.KING, chess.BLACK))
        b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
        try:
            b.set_piece_at(chess.parse_square(p_st), chess.Piece(chess.PAWN, chess.WHITE))
            b.set_piece_at(chess.parse_square(h_sq), chess.Piece(chess.BISHOP, chess.WHITE))
            b.turn = chess.WHITE
            add(b, f"{p_st}{p_dst}", f"Pawn Checkmate via {p_dst}")
        except: pass

    print(f"Generated {len(puzzles)} Mate in 1 puzzles, with {len(seen_moves)} UNIQUE moves!")
    return puzzles[:100]

res = gen_unique_mate_in_1()
print(f"Result count: {len(res)}")
