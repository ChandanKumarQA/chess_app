import chess

def gen_unique_mate_in_1():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    # 1. Rook back rank & rank/file mates
    # King on g8 (pawns f7, g7, h7)
    for start_col, dst_col in [('a', 'a'), ('b', 'b'), ('c', 'c'), ('d', 'd'), ('e', 'e'), ('f', 'f')]:
        for start_rank in ['1', '2', '3', '4', '5']:
            mv = f"{start_col}{start_rank}{dst_col}8"
            if mv not in seen_moves:
                b = chess.Board(None)
                b.set_piece_at(chess.G8, chess.Piece(chess.KING, chess.BLACK))
                b.set_piece_at(chess.F7, chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.G7, chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.H7, chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
                start_sq = chess.parse_square(f"{start_col}{start_rank}")
                b.set_piece_at(start_sq, chess.Piece(chess.ROOK, chess.WHITE))
                b.turn = chess.WHITE
                m = chess.Move.from_uci(mv)
                if m in b.legal_moves:
                    b.push(m)
                    if b.is_checkmate():
                        b.pop()
                        fen = b.fen()
                        if fen not in seen_fens:
                            seen_moves.add(mv)
                            seen_fens.add(fen)
                            puzzles.append((fen, [mv], f"Back-Rank Rook Mate via {mv}"))

    # King on c8 (pawns a7, b7, c7)
    for start_col, dst_col in [('d', 'd'), ('e', 'e'), ('f', 'f'), ('g', 'g'), ('h', 'h')]:
        for start_rank in ['1', '2', '3', '4', '5']:
            mv = f"{start_col}{start_rank}{dst_col}8"
            if mv not in seen_moves:
                b = chess.Board(None)
                b.set_piece_at(chess.C8, chess.Piece(chess.KING, chess.BLACK))
                b.set_piece_at(chess.A7, chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.B7, chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.C7, chess.Piece(chess.PAWN, chess.BLACK))
                b.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
                start_sq = chess.parse_square(f"{start_col}{start_rank}")
                b.set_piece_at(start_sq, chess.Piece(chess.ROOK, chess.WHITE))
                b.turn = chess.WHITE
                m = chess.Move.from_uci(mv)
                if m in b.legal_moves:
                    b.push(m)
                    if b.is_checkmate():
                        b.pop()
                        fen = b.fen()
                        if fen not in seen_fens:
                            seen_moves.add(mv)
                            seen_fens.add(fen)
                            puzzles.append((fen, [mv], f"Queenside Back-Rank Rook Mate via {mv}"))

    print(f"Generated {len(puzzles)} mate in 1 puzzles so far, {len(seen_moves)} unique moves!")

gen_unique_mate_in_1()
