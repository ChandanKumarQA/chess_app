import chess

# Let's test generating 100 unique Fork puzzles
def generate_100_forks():
    puzzles = []
    seen_moves = set()
    seen_fens = set()

    # 1. Knight forks on various squares
    # We want Knight jumping from sq1 to sq2, checking King on k_sq and attacking piece on p_sq
    # Knight move deltas
    knight_deltas = [(-2, -1), (-2, 1), (-1, -2), (-1, 2), (1, -2), (1, 2), (2, -1), (2, 1)]

    for fork_f in range(1, 7):
        for fork_r in range(1, 7):
            fork_sq = chess.square(fork_f, fork_r)
            # Find two targets from fork_sq
            targets = []
            for df, dr in knight_deltas:
                tf, tr = fork_f + df, fork_r + dr
                if 0 <= tf < 8 and 0 <= tr < 8:
                    targets.append(chess.square(tf, tr))
            
            # Find a start square for knight
            for df, dr in knight_deltas:
                sf, sr = fork_f + df, fork_r + dr
                if 0 <= sf < 8 and 0 <= sr < 8:
                    start_sq = chess.square(sf, sr)
                    move_str = chess.square_name(start_sq) + chess.square_name(fork_sq)
                    
                    if len(targets) >= 2:
                        k_sq = targets[0]
                        p_sq = targets[1]
                        if start_sq not in (k_sq, p_sq):
                            # Set up board
                            board = chess.Board(None)
                            board.set_piece_at(chess.G1, chess.Piece(chess.KING, chess.WHITE))
                            board.set_piece_at(start_sq, chess.Piece(chess.KNIGHT, chess.WHITE))
                            board.set_piece_at(k_sq, chess.Piece(chess.KING, chess.BLACK))
                            board.set_piece_at(p_sq, chess.Piece(chess.ROOK, chess.BLACK))
                            board.turn = chess.WHITE

                            m = chess.Move(start_sq, fork_sq)
                            if m in board.legal_moves and not board.is_check():
                                board.push(m)
                                # Black King must escape check
                                b_moves = list(board.legal_moves)
                                if b_moves:
                                    # Pick king move that does not capture knight
                                    safe_k_moves = [bm for bm in b_moves if bm.to_square != fork_sq and bm.from_square == k_sq]
                                    if safe_k_moves:
                                        resp = safe_k_moves[0]
                                        board.push(resp)
                                        # White captures rook
                                        cap_m = chess.Move(fork_sq, p_sq)
                                        if cap_m in board.legal_moves:
                                            full_moves = f"{move_str},{resp.uci()},{cap_m.uci()}"
                                            if full_moves not in seen_moves:
                                                board.pop()
                                                board.pop()
                                                fen = board.fen()
                                                if fen not in seen_fens:
                                                    seen_moves.add(full_moves)
                                                    seen_fens.add(fen)
                                                    puzzles.append((fen, [move_str, resp.uci(), cap_m.uci()], f"Knight Fork on {chess.square_name(fork_sq)}"))
    print(f"Generated {len(puzzles)} knight forks with completely unique move sequences!")

generate_100_forks()
