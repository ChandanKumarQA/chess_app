package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object DeflectionDatabase {

    private data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val pool = listOf(
        RawTacticalPuzzle("3qk3/8/8/4n3/2B5/8/P7/4Q1K1 w - - 0 1", listOf("c4f7", "e8f7", "e1e5"), "Deflection of King from e5 Defense via Bc4"),
        RawTacticalPuzzle("3qk3/8/8/4n3/8/1B6/P7/4Q1K1 w - - 0 1", listOf("b3f7", "e8f7", "e1e5"), "Deflection of King from e5 Defense via Bb3"),
        RawTacticalPuzzle("3qk3/8/8/3Bn3/8/8/P7/4Q1K1 w - - 0 1", listOf("d5f7", "e8f7", "e1e5"), "Deflection of King from e5 Defense via Bd5"),
        RawTacticalPuzzle("1k1r4/ppp5/5n2/8/8/8/P7/3RQ1K1 w - - 0 1", listOf("e1e8", "f6e8", "d1d8"), "Queenside Deflection of Nf6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/2n5/8/8/8/P7/3QR1K1 w - - 0 1", listOf("d1d8", "c6d8", "e1e8"), "Kingside Deflection of Nc6 into e8 Mate"),
        RawTacticalPuzzle("3qk3/8/8/4n3/2B5/8/1P6/4Q1K1 w - - 0 1", listOf("c4f7", "e8f7", "e1e5"), "Deflection of King from e5 Defense via Bc4"),
        RawTacticalPuzzle("3qk3/8/8/4n3/8/1B6/1P6/4Q1K1 w - - 0 1", listOf("b3f7", "e8f7", "e1e5"), "Deflection of King from e5 Defense via Bb3"),
        RawTacticalPuzzle("3qk3/8/8/3Bn3/8/8/1P6/4Q1K1 w - - 0 1", listOf("d5f7", "e8f7", "e1e5"), "Deflection of King from e5 Defense via Bd5"),
        RawTacticalPuzzle("1k1r4/ppp5/5n2/8/8/8/1P6/3RQ1K1 w - - 0 1", listOf("e1e8", "f6e8", "d1d8"), "Queenside Deflection of Nf6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/2n5/8/8/8/1P6/3QR1K1 w - - 0 1", listOf("d1d8", "c6d8", "e1e8"), "Kingside Deflection of Nc6 into e8 Mate"),
        RawTacticalPuzzle("3qk3/8/8/4n3/2B5/8/2P5/4Q1K1 w - - 0 1", listOf("c4f7", "e8f7", "e1e5"), "Deflection of King from e5 Defense via Bc4"),
        RawTacticalPuzzle("3qk3/8/8/4n3/8/1B6/2P5/4Q1K1 w - - 0 1", listOf("b3f7", "e8f7", "e1e5"), "Deflection of King from e5 Defense via Bb3"),
        RawTacticalPuzzle("3qk3/8/8/3Bn3/8/8/2P5/4Q1K1 w - - 0 1", listOf("d5f7", "e8f7", "e1e5"), "Deflection of King from e5 Defense via Bd5"),
        RawTacticalPuzzle("1k1r4/ppp5/5n2/8/8/8/2P5/3RQ1K1 w - - 0 1", listOf("e1e8", "f6e8", "d1d8"), "Queenside Deflection of Nf6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/2n5/8/8/8/2P5/3QR1K1 w - - 0 1", listOf("d1d8", "c6d8", "e1e8"), "Kingside Deflection of Nc6 into e8 Mate"),
        RawTacticalPuzzle("3qk3/8/8/4n3/2B5/8/6P1/4Q1K1 w - - 0 1", listOf("c4f7", "e8f7", "e1e5"), "Deflection of King from e5 Defense via Bc4"),
        RawTacticalPuzzle("3qk3/8/8/4n3/8/1B6/6P1/4Q1K1 w - - 0 1", listOf("b3f7", "e8f7", "e1e5"), "Deflection of King from e5 Defense via Bb3"),
        RawTacticalPuzzle("3qk3/8/8/3Bn3/8/8/6P1/4Q1K1 w - - 0 1", listOf("d5f7", "e8f7", "e1e5"), "Deflection of King from e5 Defense via Bd5"),
        RawTacticalPuzzle("1k1r4/ppp5/5n2/8/8/8/5P2/3RQ1K1 w - - 0 1", listOf("e1e8", "f6e8", "d1d8"), "Queenside Deflection of Nf6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/2n5/8/8/8/5P2/3QR1K1 w - - 0 1", listOf("d1d8", "c6d8", "e1e8"), "Kingside Deflection of Nc6 into e8 Mate"),
        RawTacticalPuzzle("3qk3/8/8/4n3/2B5/8/7P/4Q1K1 w - - 0 1", listOf("c4f7", "e8f7", "e1e5"), "Deflection of King from e5 Defense via Bc4"),
        RawTacticalPuzzle("3qk3/8/8/4n3/8/1B6/7P/4Q1K1 w - - 0 1", listOf("b3f7", "e8f7", "e1e5"), "Deflection of King from e5 Defense via Bb3"),
        RawTacticalPuzzle("3qk3/8/8/3Bn3/8/8/7P/4Q1K1 w - - 0 1", listOf("d5f7", "e8f7", "e1e5"), "Deflection of King from e5 Defense via Bd5"),
        RawTacticalPuzzle("1k1r4/ppp5/5n2/8/8/8/6P1/3RQ1K1 w - - 0 1", listOf("e1e8", "f6e8", "d1d8"), "Queenside Deflection of Nf6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/2n5/8/8/8/6P1/3QR1K1 w - - 0 1", listOf("d1d8", "c6d8", "e1e8"), "Kingside Deflection of Nc6 into e8 Mate"),
        RawTacticalPuzzle("3qk3/8/8/4n3/2B5/8/5P2/4Q1K1 w - - 0 1", listOf("c4f7", "e8f7", "e1e5"), "Deflection of King from e5 Defense via Bc4"),
        RawTacticalPuzzle("1k1r4/ppp5/5n2/8/8/8/7P/3RQ1K1 w - - 0 1", listOf("e1e8", "f6e8", "d1d8"), "Queenside Deflection of Nf6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/2n5/8/8/8/7P/3QR1K1 w - - 0 1", listOf("d1d8", "c6d8", "e1e8"), "Kingside Deflection of Nc6 into e8 Mate"),
        RawTacticalPuzzle("3qk3/8/8/4n3/8/1B6/5P2/4Q1K1 w - - 0 1", listOf("b3f7", "e8f7", "e1e5"), "Deflection of King from e5 Defense via Bb3"),
        RawTacticalPuzzle("4r1k1/5ppp/4n3/8/8/8/P7/3QR1K1 w - - 0 1", listOf("d1d8", "e6d8", "e1e8"), "Kingside Deflection of Ne6 into e8 Mate"),
        RawTacticalPuzzle("3qk3/8/8/3Bn3/8/8/5P2/4Q1K1 w - - 0 1", listOf("d5f7", "e8f7", "e1e5"), "Deflection of King from e5 Defense via Bd5"),
        RawTacticalPuzzle("4r1k1/5ppp/4n3/8/8/8/1P6/3QR1K1 w - - 0 1", listOf("d1d8", "e6d8", "e1e8"), "Kingside Deflection of Ne6 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp5/3n4/8/8/8/P7/3RQ1K1 w - - 0 1", listOf("e1e8", "d6e8", "d1d8"), "Queenside Deflection of Nd6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/4n3/8/8/8/2P5/3QR1K1 w - - 0 1", listOf("d1d8", "e6d8", "e1e8"), "Kingside Deflection of Ne6 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp5/3n4/8/8/8/1P6/3RQ1K1 w - - 0 1", listOf("e1e8", "d6e8", "d1d8"), "Queenside Deflection of Nd6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/4n3/8/8/8/5P2/3QR1K1 w - - 0 1", listOf("d1d8", "e6d8", "e1e8"), "Kingside Deflection of Ne6 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp5/3n4/8/8/8/2P5/3RQ1K1 w - - 0 1", listOf("e1e8", "d6e8", "d1d8"), "Queenside Deflection of Nd6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/4n3/8/8/8/6P1/3QR1K1 w - - 0 1", listOf("d1d8", "e6d8", "e1e8"), "Kingside Deflection of Ne6 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp5/3n4/8/8/8/5P2/3RQ1K1 w - - 0 1", listOf("e1e8", "d6e8", "d1d8"), "Queenside Deflection of Nd6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/4n3/8/8/8/7P/3QR1K1 w - - 0 1", listOf("d1d8", "e6d8", "e1e8"), "Kingside Deflection of Ne6 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp5/3n4/8/8/8/6P1/3RQ1K1 w - - 0 1", listOf("e1e8", "d6e8", "d1d8"), "Queenside Deflection of Nd6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/1n3ppp/8/8/8/8/P7/3QR1K1 w - - 0 1", listOf("d1d8", "b7d8", "e1e8"), "Kingside Deflection of Nb7 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp5/3n4/8/8/8/7P/3RQ1K1 w - - 0 1", listOf("e1e8", "d6e8", "d1d8"), "Queenside Deflection of Nd6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/1n3ppp/8/8/8/8/1P6/3QR1K1 w - - 0 1", listOf("d1d8", "b7d8", "e1e8"), "Kingside Deflection of Nb7 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp3n1/8/8/8/8/P7/3RQ1K1 w - - 0 1", listOf("e1e8", "g7e8", "d1d8"), "Queenside Deflection of Ng7 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/1n3ppp/8/8/8/8/2P5/3QR1K1 w - - 0 1", listOf("d1d8", "b7d8", "e1e8"), "Kingside Deflection of Nb7 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp3n1/8/8/8/8/1P6/3RQ1K1 w - - 0 1", listOf("e1e8", "g7e8", "d1d8"), "Queenside Deflection of Ng7 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/1n3ppp/8/8/8/8/5P2/3QR1K1 w - - 0 1", listOf("d1d8", "b7d8", "e1e8"), "Kingside Deflection of Nb7 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp3n1/8/8/8/8/2P5/3RQ1K1 w - - 0 1", listOf("e1e8", "g7e8", "d1d8"), "Queenside Deflection of Ng7 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/1n3ppp/8/8/8/8/6P1/3QR1K1 w - - 0 1", listOf("d1d8", "b7d8", "e1e8"), "Kingside Deflection of Nb7 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp3n1/8/8/8/8/5P2/3RQ1K1 w - - 0 1", listOf("e1e8", "g7e8", "d1d8"), "Queenside Deflection of Ng7 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/1n3ppp/8/8/8/8/7P/3QR1K1 w - - 0 1", listOf("d1d8", "b7d8", "e1e8"), "Kingside Deflection of Nb7 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp3n1/8/8/8/8/6P1/3RQ1K1 w - - 0 1", listOf("e1e8", "g7e8", "d1d8"), "Queenside Deflection of Ng7 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/4bppp/8/8/8/8/P7/3QR1K1 w - - 0 1", listOf("d1d8", "e7d8", "e1e8"), "Kingside Deflection of Be7 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp3n1/8/8/8/8/7P/3RQ1K1 w - - 0 1", listOf("e1e8", "g7e8", "d1d8"), "Queenside Deflection of Ng7 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/4bppp/8/8/8/8/1P6/3QR1K1 w - - 0 1", listOf("d1d8", "e7d8", "e1e8"), "Kingside Deflection of Be7 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp2b2/8/8/8/8/P7/3RQ1K1 w - - 0 1", listOf("e1e8", "f7e8", "d1d8"), "Queenside Deflection of Bf7 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/4bppp/8/8/8/8/2P5/3QR1K1 w - - 0 1", listOf("d1d8", "e7d8", "e1e8"), "Kingside Deflection of Be7 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp2b2/8/8/8/8/1P6/3RQ1K1 w - - 0 1", listOf("e1e8", "f7e8", "d1d8"), "Queenside Deflection of Bf7 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/4bppp/8/8/8/8/5P2/3QR1K1 w - - 0 1", listOf("d1d8", "e7d8", "e1e8"), "Kingside Deflection of Be7 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp2b2/8/8/8/8/2P5/3RQ1K1 w - - 0 1", listOf("e1e8", "f7e8", "d1d8"), "Queenside Deflection of Bf7 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/4bppp/8/8/8/8/6P1/3QR1K1 w - - 0 1", listOf("d1d8", "e7d8", "e1e8"), "Kingside Deflection of Be7 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp2b2/8/8/8/8/5P2/3RQ1K1 w - - 0 1", listOf("e1e8", "f7e8", "d1d8"), "Queenside Deflection of Bf7 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/4bppp/8/8/8/8/7P/3QR1K1 w - - 0 1", listOf("d1d8", "e7d8", "e1e8"), "Kingside Deflection of Be7 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp2b2/8/8/8/8/6P1/3RQ1K1 w - - 0 1", listOf("e1e8", "f7e8", "d1d8"), "Queenside Deflection of Bf7 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/8/6b1/8/8/P7/3QR1K1 w - - 0 1", listOf("d1d8", "g5d8", "e1e8"), "Kingside Deflection of Bg5 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp2b2/8/8/8/8/7P/3RQ1K1 w - - 0 1", listOf("e1e8", "f7e8", "d1d8"), "Queenside Deflection of Bf7 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/8/6b1/8/8/1P6/3QR1K1 w - - 0 1", listOf("d1d8", "g5d8", "e1e8"), "Kingside Deflection of Bg5 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp5/6b1/8/8/8/P7/3RQ1K1 w - - 0 1", listOf("e1e8", "g6e8", "d1d8"), "Queenside Deflection of Bg6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/8/6b1/8/8/2P5/3QR1K1 w - - 0 1", listOf("d1d8", "g5d8", "e1e8"), "Kingside Deflection of Bg5 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp5/6b1/8/8/8/1P6/3RQ1K1 w - - 0 1", listOf("e1e8", "g6e8", "d1d8"), "Queenside Deflection of Bg6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/8/6b1/8/8/5P2/3QR1K1 w - - 0 1", listOf("d1d8", "g5d8", "e1e8"), "Kingside Deflection of Bg5 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp5/6b1/8/8/8/2P5/3RQ1K1 w - - 0 1", listOf("e1e8", "g6e8", "d1d8"), "Queenside Deflection of Bg6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/8/6b1/8/8/6P1/3QR1K1 w - - 0 1", listOf("d1d8", "g5d8", "e1e8"), "Kingside Deflection of Bg5 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp5/6b1/8/8/8/5P2/3RQ1K1 w - - 0 1", listOf("e1e8", "g6e8", "d1d8"), "Queenside Deflection of Bg6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/8/6b1/8/8/7P/3QR1K1 w - - 0 1", listOf("d1d8", "g5d8", "e1e8"), "Kingside Deflection of Bg5 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp5/6b1/8/8/8/6P1/3RQ1K1 w - - 0 1", listOf("e1e8", "g6e8", "d1d8"), "Queenside Deflection of Bg6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/1b6/8/8/8/5P2/3QR1K1 w - - 0 1", listOf("d1d8", "b6d8", "e1e8"), "Kingside Deflection of Bb6 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp5/6b1/8/8/8/7P/3RQ1K1 w - - 0 1", listOf("e1e8", "g6e8", "d1d8"), "Queenside Deflection of Bg6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/5b2/8/8/8/P7/3QR1K1 w - - 0 1", listOf("d1d8", "f6d8", "e1e8"), "Kingside Deflection of Bf6 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp5/2b5/8/8/8/P7/3RQ1K1 w - - 0 1", listOf("e1e8", "c6e8", "d1d8"), "Queenside Deflection of Bc6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/5b2/8/8/8/1P6/3QR1K1 w - - 0 1", listOf("d1d8", "f6d8", "e1e8"), "Kingside Deflection of Bf6 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp5/2b5/8/8/8/1P6/3RQ1K1 w - - 0 1", listOf("e1e8", "c6e8", "d1d8"), "Queenside Deflection of Bc6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/5b2/8/8/8/2P5/3QR1K1 w - - 0 1", listOf("d1d8", "f6d8", "e1e8"), "Kingside Deflection of Bf6 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp5/2b5/8/8/8/2P5/3RQ1K1 w - - 0 1", listOf("e1e8", "c6e8", "d1d8"), "Queenside Deflection of Bc6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/5b2/8/8/8/5P2/3QR1K1 w - - 0 1", listOf("d1d8", "f6d8", "e1e8"), "Kingside Deflection of Bf6 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp5/2b5/8/8/8/5P2/3RQ1K1 w - - 0 1", listOf("e1e8", "c6e8", "d1d8"), "Queenside Deflection of Bc6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/5b2/8/8/8/6P1/3QR1K1 w - - 0 1", listOf("d1d8", "f6d8", "e1e8"), "Kingside Deflection of Bf6 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp5/2b5/8/8/8/6P1/3RQ1K1 w - - 0 1", listOf("e1e8", "c6e8", "d1d8"), "Queenside Deflection of Bc6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/5ppp/5b2/8/8/8/7P/3QR1K1 w - - 0 1", listOf("d1d8", "f6d8", "e1e8"), "Kingside Deflection of Bf6 into e8 Mate"),
        RawTacticalPuzzle("1k1r4/ppp5/2b5/8/8/8/7P/3RQ1K1 w - - 0 1", listOf("e1e8", "c6e8", "d1d8"), "Queenside Deflection of Bc6 into d8 Mate"),
        RawTacticalPuzzle("4r1k1/2q2ppp/8/8/8/8/P7/3QR1K1 w - - 0 1", listOf("d1d8", "c7d8", "e1e8"), "Kingside Deflection of Qc7 into e8 Mate"),
        RawTacticalPuzzle("4r1k1/2q2ppp/8/8/8/8/1P6/3QR1K1 w - - 0 1", listOf("d1d8", "c7d8", "e1e8"), "Kingside Deflection of Qc7 into e8 Mate"),
        RawTacticalPuzzle("4r1k1/2q2ppp/8/8/8/8/2P5/3QR1K1 w - - 0 1", listOf("d1d8", "c7d8", "e1e8"), "Kingside Deflection of Qc7 into e8 Mate"),
        RawTacticalPuzzle("4r1k1/2q2ppp/8/8/8/8/5P2/3QR1K1 w - - 0 1", listOf("d1d8", "c7d8", "e1e8"), "Kingside Deflection of Qc7 into e8 Mate"),
        RawTacticalPuzzle("4r1k1/2q2ppp/8/8/8/8/6P1/3QR1K1 w - - 0 1", listOf("d1d8", "c7d8", "e1e8"), "Kingside Deflection of Qc7 into e8 Mate"),
        RawTacticalPuzzle("4r1k1/2q2ppp/8/8/8/8/7P/3QR1K1 w - - 0 1", listOf("d1d8", "c7d8", "e1e8"), "Kingside Deflection of Qc7 into e8 Mate"),
        RawTacticalPuzzle("4r1k1/4qppp/8/8/8/8/P7/3QR1K1 w - - 0 1", listOf("d1d8", "e7d8", "e1e8"), "Kingside Deflection of Qe7 into e8 Mate"),
        RawTacticalPuzzle("4r1k1/4qppp/8/8/8/8/1P6/3QR1K1 w - - 0 1", listOf("d1d8", "e7d8", "e1e8"), "Kingside Deflection of Qe7 into e8 Mate"),
        RawTacticalPuzzle("4r1k1/4qppp/8/8/8/8/2P5/3QR1K1 w - - 0 1", listOf("d1d8", "e7d8", "e1e8"), "Kingside Deflection of Qe7 into e8 Mate")
    )

    private var cachedPuzzles: List<Puzzle>? = null

    fun getPuzzles(): List<Puzzle> {
        cachedPuzzles?.let { return it }
        val puzzles = (1..100).map { i ->
            val base = pool[i - 1]
            val tierRating = when {
                i <= 35 -> 650 + (i * 10)
                i <= 70 -> 1100 + ((i - 35) * 15)
                else -> 1700 + ((i - 70) * 20)
            }
            val cleanTheme = "Deflection"
            val idPrefix = cleanTheme.lowercase().replace(" ", "_")
            Puzzle(
                id = "tac_${idPrefix}_$i",
                fen = base.fen,
                solutionMoves = base.solutionMoves,
                rating = tierRating,
                theme = "$cleanTheme - ${base.motif}",
                xpReward = 15 + (i / 10),
                coinsReward = 8 + (i / 20)
            )
        }
        cachedPuzzles = puzzles
        return puzzles
    }
}
