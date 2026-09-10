package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object TacticalDatabase {

    data class RawTacticalPuzzle(
        val fen: String,
        val solutionMoves: List<String>,
        val motif: String
    )

    private val mateIn1Pool = listOf(
        RawTacticalPuzzle("7k/6p1/7Q/4B3/8/8/8/6K1 w - - 0 1", listOf("h6g7"), "Bishop & Queen Mate"),
        RawTacticalPuzzle("3rkr2/8/8/8/4Q3/8/8/4K3 w - - 0 1", listOf("e4e6"), "Epaulette Queen Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/8/8/8/3R2K1 w - - 0 1", listOf("d1d8"), "Rook Back Rank Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/8/8/8/2R3K1 w - - 0 1", listOf("c1c8"), "c-file Rook Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/8/8/8/1R4K1 w - - 0 1", listOf("b1b8"), "b-file Rook Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/8/8/8/R5K1 w - - 0 1", listOf("a1a8"), "a-file Rook Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e8"), "e-file Rook Mate"),
        RawTacticalPuzzle("6k1/6p1/5p1p/8/3B4/8/5PPP/4Q1K1 w - - 0 1", listOf("e1e8"), "Long Diagonal Queen Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/8/8/4QPPP/6K1 w - - 0 1", listOf("e2e8"), "Queen Back Rank Strike"),
        RawTacticalPuzzle("k7/8/1K6/8/8/8/8/2Q5 w - - 0 1", listOf("c1c8"), "Kiss of Death"),
        RawTacticalPuzzle("7k/5Qpp/8/8/8/8/5PPP/6K1 w - - 0 1", listOf("f7f8"), "Back Rank Queen Mate"),
        RawTacticalPuzzle("6k1/5ppp/8/8/8/8/3Q1PPP/6K1 w - - 0 1", listOf("d2d8"), "d-file Back Rank Mate")
    )

    private val mateIn2Pool = listOf(
        RawTacticalPuzzle("3r2k1/5ppp/8/8/8/8/5PPP/1R2R1K1 w - - 0 1", listOf("b1b8", "d8b8", "e1e8"), "Rook Battery Mate"),
        RawTacticalPuzzle("7k/6p1/5bB1/8/8/8/5PPP/3Q2K1 w - - 0 1", listOf("d1h5", "h8g8", "h5h7"), "Queen & Bishop Battery"),
        RawTacticalPuzzle("1n1r2k1/5ppp/8/8/8/8/5PPP/1R1R2K1 w - - 0 1", listOf("b1b8", "d8b8", "d1d8"), "Back Rank Deflection Mate"),
        RawTacticalPuzzle("3r2k1/5ppp/8/8/8/8/5PPP/3QR1K1 w - - 0 1", listOf("d1d8"), "Queen & Rook Battery Mate"),
        RawTacticalPuzzle("6k1/6p1/5B2/8/4P3/8/5PPP/3Q2K1 w - - 0 1", listOf("d1d8", "g8f7", "d8e7"), "Bishop Assisted Queen Mate"),
        RawTacticalPuzzle("n5k1/5ppp/8/8/8/8/5PPP/1RR3K1 w - - 0 1", listOf("c1c8"), "Flank Deflection Mate"),
        RawTacticalPuzzle("7k/8/4PN1P/8/8/8/8/R5K1 w - - 0 1", listOf("a1a8"), "Knight & Rook Corridor Mate"),
        RawTacticalPuzzle("1n1r2k1/5ppp/8/8/3P4/8/5PPP/1R2R1K1 w - - 0 1", listOf("b1b8", "d8b8", "e1e8"), "Double Rook Deflection Mate")
    )

    private val forkPool = listOf(
        RawTacticalPuzzle("r3k2r/8/8/3N4/8/8/8/4K3 w kq - 0 1", listOf("d5c7", "e8d7", "c7a8"), "Knight Fork on King & Rook"),
        RawTacticalPuzzle("4k3/8/8/8/r7/8/6B1/4K3 w - - 0 1", listOf("g2c6", "e8d8", "c6a4"), "Bishop Fork on King & Rook"),
        RawTacticalPuzzle("8/8/3r1r2/8/4P3/8/8/4K3 w - - 0 1", listOf("e4e5", "d6d5", "e5f6"), "Pawn Fork on Two Rooks"),
        RawTacticalPuzzle("4k3/8/8/8/7r/8/1B6/4K3 w - - 0 1", listOf("b2f6", "e8d7", "f6h4"), "Long Range Bishop Fork"),
        RawTacticalPuzzle("8/8/2n1n3/8/3P4/8/8/4K3 w - - 0 1", listOf("d4d5", "c6e7", "d5e6"), "Central Pawn Fork on Knights"),
        RawTacticalPuzzle("r2qk2r/8/8/3N4/8/8/8/4K3 w kq - 0 1", listOf("d5c7", "e8f8", "c7a8"), "Royal Knight Fork"),
        RawTacticalPuzzle("8/8/1n1n4/8/2P5/8/8/4K3 w - - 0 1", listOf("c4c5", "b6c8", "c5d6"), "Pawn Wing Fork"),
        RawTacticalPuzzle("r1bqk2r/8/8/4N3/8/8/8/4K3 w kq - 0 1", listOf("e5f7", "e8e7", "f7h8"), "Knight Outpost Fork"),
        RawTacticalPuzzle("4k3/8/8/8/2r5/8/4B3/4K3 w - - 0 1", listOf("e2c4", "e8d8", "c4a6"), "Bishop Central Skewer Fork"),
        RawTacticalPuzzle("8/8/4k3/8/3N4/8/4K3/8 w - - 0 1", listOf("d4f5", "e6d5", "f5e3"), "Knight Central Jump Fork")
    )

    private val pinPool = listOf(
        RawTacticalPuzzle("7k/p7/8/4q3/8/8/8/2B3K1 w - - 0 1", listOf("c1b2", "a7a6", "b2e5"), "Diagonal Pin on Queen to King"),
        RawTacticalPuzzle("4k3/p7/8/4q3/8/8/8/R5K1 w - - 0 1", listOf("a1e1", "a7a6", "e1e5"), "File Pin on Queen to King"),
        RawTacticalPuzzle("3k4/p7/8/3q4/8/8/8/R5K1 w - - 0 1", listOf("a1d1", "a7a6", "d1d5"), "Rook Center Pin"),
        RawTacticalPuzzle("7k/p7/4q3/8/8/8/8/1B4K1 w - - 0 1", listOf("b1a2", "a7a6", "a2e6"), "Bishop Long Pin"),
        RawTacticalPuzzle("2k5/8/8/2q5/8/8/8/2R3K1 w - - 0 1", listOf("c1c5", "c8d7", "c5c8"), "c-file Pin on Queen"),
        RawTacticalPuzzle("1k6/8/8/1q6/8/8/8/1R4K1 w - - 0 1", listOf("b1b5", "b8c7", "b5b8"), "b-file Pin on Queen"),
        RawTacticalPuzzle("4k3/8/8/4r3/8/8/8/4R1K1 w - - 0 1", listOf("e1e5", "e8d7", "e5e8"), "e-file Pin on Rook"),
        RawTacticalPuzzle("3k4/8/8/3r4/8/8/8/3R2K1 w - - 0 1", listOf("d1d5", "d8c7", "d5d8"), "d-file Pin on Rook")
    )

    private val skewerPool = listOf(
        RawTacticalPuzzle("7q/6k1/p7/8/8/8/8/2B3K1 w - - 0 1", listOf("c1b2", "g7f7", "b2h8"), "Diagonal Skewer Winning Queen"),
        RawTacticalPuzzle("4q3/p7/8/4k3/8/8/8/R5K1 w - - 0 1", listOf("a1e1", "e5d6", "e1e8"), "Vertical Skewer Winning Queen"),
        RawTacticalPuzzle("3q4/p7/8/3k4/8/8/8/R5K1 w - - 0 1", listOf("a1d1", "d5c6", "d1d8"), "File Skewer Winning Queen"),
        RawTacticalPuzzle("r3k3/8/8/8/8/8/6K1/7R w - - 0 1", listOf("h1h8", "e8d7", "h8a8"), "Horizontal Skewer Winning Rook"),
        RawTacticalPuzzle("7r/6k1/p7/8/8/8/8/2B3K1 w - - 0 1", listOf("c1b2", "g7g8", "b2h8"), "Bishop Skewer on King & Rook"),
        RawTacticalPuzzle("2q5/p7/8/2k5/8/8/8/R5K1 w - - 0 1", listOf("a1c1", "c5d6", "c1c8"), "c-file Skewer Winning Queen"),
        RawTacticalPuzzle("1q6/p7/8/1k6/8/8/8/R5K1 w - - 0 1", listOf("a1b1", "b5c6", "b1b8"), "b-file Skewer Winning Queen"),
        RawTacticalPuzzle("6q1/p7/8/6k1/8/8/1K6/R7 w - - 0 1", listOf("a1g1", "g5f6", "g1g8"), "g-file Skewer Winning Queen")
    )

    private val doubleAttackPool = listOf(
        RawTacticalPuzzle("4k3/8/8/2n5/8/8/8/4R1K1 w - - 0 1", listOf("e1e5", "e8d7", "e5c5"), "Rook Double Attack on King & Knight"),
        RawTacticalPuzzle("4k3/8/8/r7/8/8/8/4Q1K1 w - - 0 1", listOf("e1e5", "e8d7", "e5a5"), "Queen Double Attack on King & Rook"),
        RawTacticalPuzzle("4k3/8/1r6/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e6", "e8d7", "e6b6"), "Rook Double Attack on King & Rook"),
        RawTacticalPuzzle("4k3/8/8/1r6/8/8/8/4Q1K1 w - - 0 1", listOf("e1e5", "e8d7", "e5b5"), "Queen Fork & Double Attack"),
        RawTacticalPuzzle("4k3/8/8/2n5/8/8/8/4Q1K1 w - - 0 1", listOf("e1e5", "e8d7", "e5c5"), "Rook Central Double Attack"),
        RawTacticalPuzzle("4k3/8/8/8/3r4/8/8/4Q1K1 w - - 0 1", listOf("e1e4", "e8d7", "e4d4"), "Queen Double Threat on d4"),
        RawTacticalPuzzle("4k3/8/3b4/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e6", "e8d7", "e6d6"), "Rook Double Attack on Bishop"),
        RawTacticalPuzzle("4k3/8/8/2r5/8/8/8/4Q1K1 w - - 0 1", listOf("e1e5", "e8d7", "e5c5"), "Queen Fork on King & Rook")
    )

    private val discoveredAttackPool = listOf(
        RawTacticalPuzzle("3q2k1/5ppp/8/8/8/3B4/5PPP/3R2K1 w - - 0 1", listOf("d3h7", "g8h7", "d1d8"), "Discovered Check Winning Queen"),
        RawTacticalPuzzle("2q2k2/5ppp/8/8/8/2B5/5PPP/2R3K1 w - - 0 1", listOf("c3g7", "f8g7", "c1c8"), "Discovered Queen Invasion"),
        RawTacticalPuzzle("3r2k1/5ppp/8/8/8/3B4/5PPP/3R2K1 w - - 0 1", listOf("d3h7", "g8h7", "d1d8"), "Discovered Attack Winning Rook"),
        RawTacticalPuzzle("2r2k2/5ppp/8/8/8/2B5/5PPP/2R3K1 w - - 0 1", listOf("c3g7", "f8g7", "c1c8"), "Bishop Discovered Strike on Rook"),
        RawTacticalPuzzle("4q1k1/5ppp/8/8/8/4B3/5PPP/4R1K1 w - - 0 1", listOf("e3c5", "g8h8", "e1e8"), "Center Discovered Attack on Queen"),
        RawTacticalPuzzle("3b2k1/5ppp/8/8/8/3B4/5PPP/3R2K1 w - - 0 1", listOf("d3h7", "g8h7", "d1d8"), "Discovered Attack Winning Bishop"),
        RawTacticalPuzzle("2b2k2/5ppp/8/8/8/2B5/5PPP/2R3K1 w - - 0 1", listOf("c3g7", "f8g7", "c1c8"), "Flank Discovered Strike on Bishop"),
        RawTacticalPuzzle("r2q2k1/5ppp/8/8/8/3B4/5PPP/3R2K1 w - - 0 1", listOf("d3h7", "g8h7", "d1d8"), "Rook & Queen Discovered Trophy")
    )

    private val smotheredMatePool = listOf(
        RawTacticalPuzzle("6rk/5ppp/8/4N3/8/8/8/6K1 w - - 0 1", listOf("e5f7"), "Classic Smothered Mate with Knight"),
        RawTacticalPuzzle("5r1k/6pp/5N1N/8/2Q5/8/5PPP/6K1 w - - 0 1", listOf("c4g8", "f8g8", "h6f7"), "Queen Sacrifice into Smothered Mate"),
        RawTacticalPuzzle("6rk/6pp/8/4N3/8/8/5PPP/6K1 w - - 0 1", listOf("e5f7"), "Corner Smothered Finish"),
        RawTacticalPuzzle("6rk/7p/8/4N3/8/8/5PPP/6K1 w - - 0 1", listOf("e5f7"), "Corner Smothered Single Pawn"),
        RawTacticalPuzzle("6rk/5p1p/8/4N3/8/8/5PPP/6K1 w - - 0 1", listOf("e5f7"), "Corner Smothered Variant"),
        RawTacticalPuzzle("6rk/5pp1/8/4N3/8/8/5PPP/6K1 w - - 0 1", listOf("e5f7"), "Corner Smothered Variant 2"),
        RawTacticalPuzzle("5rk1/5ppp/4N3/8/8/8/5PPP/6K1 w - - 0 1", listOf("e6f8"), "Back Rank Knight Net"),
        RawTacticalPuzzle("4rk2/5ppp/3N4/8/8/8/5PPP/6K1 w - - 0 1", listOf("d6e8"), "Center Knight Mate Net")
    )

    private val sacrificePool = listOf(
        RawTacticalPuzzle("r1bqk2r/pppp1ppp/2n2n2/2b1p3/2B1P3/2N2N2/PPPP1PPP/R1BQK2R w KQkq - 0 1", listOf("c4f7", "e8f7", "f3e5"), "Bishop Sacrifice on f7"),
        RawTacticalPuzzle("r1b2rk1/pp3ppp/2n5/2qp4/8/3B1N2/PPP2PPP/R2Q1RK1 w - - 0 1", listOf("d3h7", "g8h7", "f3g5"), "Greek Gift Sacrifice on h7"),
        RawTacticalPuzzle("r1bq1rk1/pppp1ppp/2n5/4p3/2B1n3/2N2N2/PPPP1PPP/R1BQR1K1 w - - 0 1", listOf("e1e4", "d7d5", "c4d5"), "Exchange Sacrifice in Center"),
        RawTacticalPuzzle("r1bq1rk1/pppp1ppp/2n5/4p3/2B1P3/5N2/PPPP1PPP/R1BQK2R w KQ - 0 1", listOf("c4f7", "f8f7", "f3e5"), "Attraction Sacrifice on f7"),
        RawTacticalPuzzle("r1b2rk1/pp3ppp/8/2qp4/8/3B1N2/PPP2PPP/R2Q1RK1 w - - 0 1", listOf("d3h7", "g8h8", "f3g5"), "Kingside Bishop Sacrifice"),
        RawTacticalPuzzle("r1bqk2r/pppp1ppp/2n5/4p3/2B1n3/3P1N2/PPP2PPP/RNBQK2R w KQkq - 0 1", listOf("d3e4", "d7d5", "c4d5"), "Center Pawn Capture Sacrifice"),
        RawTacticalPuzzle("r1bqkb1r/pppp1ppp/2n5/4p3/2B1n3/5Q2/PPPP1PPP/RNB1K1NR w KQkq - 0 1", listOf("f3f7"), "Early Queen Strike Sacrifice"),
        RawTacticalPuzzle("r1bqk2r/pppp1ppp/2n5/2b1p3/2B1P3/3P1Q2/PPP2PPP/RNB1K1NR w KQkq - 0 1", listOf("f3f7"), "Italian Queen Strike")
    )

    private val attractionPool = listOf(
        RawTacticalPuzzle("r1bqk2r/pppp1ppp/2n5/4p3/2B1P3/3P1N2/PPP2PPP/RNBQK2R w KQkq - 0 1", listOf("c4f7", "e8f7", "f3e5"), "Attraction into King Fork"),
        RawTacticalPuzzle("r1bq1rk1/pppp1ppp/2n5/4p3/2B1P3/3P1N2/PPP2PPP/R1BQK2R w KQ - 0 1", listOf("c4f7", "f8f7", "f3e5"), "Attraction to Rook Outpost"),
        RawTacticalPuzzle("r1bqk2r/pppp1ppp/2n2n2/2b1p3/2B1P3/2N2N1P/PPPP1PP1/R1BQK2R w KQkq - 0 1", listOf("c4f7", "e8f7", "f3e5"), "Attraction King to Exposed Square"),
        RawTacticalPuzzle("r1bqk2r/pp1p1ppp/2n2n2/2b1p3/2B1P3/2N2N2/PPPP1PPP/R1BQK2R w KQkq - 0 1", listOf("c4f7", "e8f7", "f3g5"), "Attraction Followed by Fork"),
        RawTacticalPuzzle("r1bq1rk1/ppp2ppp/2n5/4p3/2B1P3/5N2/PPPP1PPP/R1BQK2R w KQ - 0 1", listOf("c4f7", "f8f7", "f3e5"), "Attraction Capture on f7"),
        RawTacticalPuzzle("r1bq1rk1/pppp1ppp/2n5/4p3/2B1n3/P1N2N2/1PPP1PPP/R1BQR1K1 w - - 0 1", listOf("e1e4", "d7d5", "c4d5"), "Central Attraction Strike"),
        RawTacticalPuzzle("r1b2rk1/pp3ppp/2n2n2/2qp4/8/3B1N2/PPP2PPP/R2QR1K1 w - - 0 1", listOf("d3h7", "g8h7", "f3g5"), "Attraction of Black King to h7")
    )

    private val deflectionPool = listOf(
        RawTacticalPuzzle("1n1r2k1/5ppp/8/8/P7/8/5PPP/1R1R2K1 w - - 0 1", listOf("b1b8", "d8b8", "d1d8"), "Rook Deflection from Back Rank"),
        RawTacticalPuzzle("3r2k1/5ppp/8/8/P7/8/5PPP/3QR1K1 w - - 0 1", listOf("d1d8"), "Deflection Overloading Back Rank"),
        RawTacticalPuzzle("1n1r2k1/5ppp/8/8/P7/8/5PPP/1R2R1K1 w - - 0 1", listOf("b1b8", "d8b8", "e1e8"), "Deflection Removing Guard"),
        RawTacticalPuzzle("n5k1/5ppp/8/8/P7/8/5PPP/1RR3K1 w - - 0 1", listOf("c1c8"), "Flank Deflection Mate"),
        RawTacticalPuzzle("2r3k1/5ppp/8/8/P7/8/5PPP/3QR1K1 w - - 0 1", listOf("d1d8", "c8d8", "e1e8"), "Queen & Rook Deflection Combination"),
        RawTacticalPuzzle("6k1/6p1/5B2/8/P7/8/5PPP/3Q2K1 w - - 0 1", listOf("d1d8", "g8f7", "d8e7"), "Deflection Infiltration Mate")
    )

    private val clearancePool = listOf(
        RawTacticalPuzzle("r4rk1/5ppp/8/8/3P4/8/1Q3PPP/R5K1 w - - 0 1", listOf("a1a8", "f8a8", "b2b7"), "Rook Clearance for Queen Infiltration"),
        RawTacticalPuzzle("r3r1k1/5ppp/8/8/8/8/1Q3PPP/R5K1 w - - 0 1", listOf("a1a8", "e8a8", "b2b7"), "Clearance of 7th Rank Threat"),
        RawTacticalPuzzle("r2r2k1/5ppp/8/8/8/8/1Q3PPP/R5K1 w - - 0 1", listOf("a1a8", "d8a8", "b2b7"), "Rook Sacrifice Clearance"),
        RawTacticalPuzzle("r4rk1/5ppp/8/8/8/8/1Q3PPP/R4RK1 w - - 0 1", listOf("a1a8", "f8a8", "b2b7"), "Double Rook Clearance"),
        RawTacticalPuzzle("1r3rk1/5ppp/8/8/8/8/1Q3PPP/1R4K1 w - - 0 1", listOf("b2b8", "f8b8", "b1b8"), "b-file Clearance Mate"),
        RawTacticalPuzzle("2r2rk1/5ppp/8/8/8/8/2Q2PPP/2R3K1 w - - 0 1", listOf("c2c8", "f8c8", "c1c8"), "c-file Clearance Attack"),
        RawTacticalPuzzle("3r1rk1/5ppp/8/8/8/8/3Q1PPP/3R2K1 w - - 0 1", listOf("d2d8", "f8d8", "d1d8"), "d-file Clearance Strike"),
        RawTacticalPuzzle("4rrk1/5ppp/8/8/8/8/4QPPP/4R1K1 w - - 0 1", listOf("e2e8", "f8e8", "e1e8"), "e-file Clearance Finish")
    )

    private val winningMaterialPool = listOf(
        RawTacticalPuzzle("6k1/5ppp/8/8/4q3/8/8/4R1K1 w - - 0 1", listOf("e1e4"), "Winning Hanging Queen"),
        RawTacticalPuzzle("6k1/5ppp/8/8/3r4/8/8/3R2K1 w - - 0 1", listOf("d1d4"), "Winning Free Rook"),
        RawTacticalPuzzle("6k1/5ppp/8/8/2b5/8/8/2R3K1 w - - 0 1", listOf("c1c4"), "Winning Hanging Bishop"),
        RawTacticalPuzzle("6k1/5ppp/8/8/1n6/8/8/1R4K1 w - - 0 1", listOf("b1b4"), "Winning Trapped Knight"),
        RawTacticalPuzzle("6k1/5ppp/8/8/4b3/8/8/4R1K1 w - - 0 1", listOf("e1e4"), "Winning Free Bishop on e-file"),
        RawTacticalPuzzle("6k1/5ppp/8/8/3n4/8/8/3R2K1 w - - 0 1", listOf("d1d4"), "Winning Free Knight on d-file"),
        RawTacticalPuzzle("6k1/5ppp/8/8/2q5/8/8/2R3K1 w - - 0 1", listOf("c1c4"), "Winning Free Queen on c-file"),
        RawTacticalPuzzle("6k1/5ppp/8/8/1r6/8/8/1R4K1 w - - 0 1", listOf("b1b4"), "Winning Free Rook on b-file"),
        RawTacticalPuzzle("6k1/5ppp/8/8/4n3/8/8/4R1K1 w - - 0 1", listOf("e1e4"), "Winning Central Knight"),
        RawTacticalPuzzle("6k1/5ppp/8/8/3q4/8/8/3R2K1 w - - 0 1", listOf("d1d4"), "Winning Central Queen")
    )

    private val categoryCache = mutableMapOf<String, List<Puzzle>>()

    fun getAllCategories(): List<String> = listOf(
        "Mate in 1", "Mate in 2", "Fork", "Pin", "Skewer",
        "Double Attack", "Discovered Attack", "Smothered Mate",
        "Sacrifice", "Attraction", "Deflection", "Clearance", "Winning Material"
    )

    fun getPuzzles(category: String): List<Puzzle> = categoryCache.getOrPut(category) {
        val pool = when (category) {
            "Mate in 1" -> mateIn1Pool
            "Mate in 2" -> mateIn2Pool
            "Fork" -> forkPool
            "Pin" -> pinPool
            "Skewer" -> skewerPool
            "Double Attack" -> doubleAttackPool
            "Discovered Attack" -> discoveredAttackPool
            "Smothered Mate" -> smotheredMatePool
            "Sacrifice" -> sacrificePool
            "Attraction" -> attractionPool
            "Deflection" -> deflectionPool
            "Clearance" -> clearancePool
            "Winning Material" -> winningMaterialPool
            else -> forkPool
        }

        val catPrefix = category.lowercase().replace(" ", "_")
        (1..100).map { i ->
            val base = pool[(i - 1) % pool.size]
            val tierRating = when {
                i <= 35 -> 650 + (i * 10)
                i <= 70 -> 1100 + ((i - 35) * 15)
                else -> 1700 + ((i - 70) * 20)
            }
            Puzzle(
                id = "tac_${catPrefix}_$i",
                fen = base.fen,
                solutionMoves = base.solutionMoves,
                rating = tierRating,
                theme = "$category - ${base.motif}",
                xpReward = 15 + (i / 10),
                coinsReward = 8 + (i / 20)
            )
        }
    }
}