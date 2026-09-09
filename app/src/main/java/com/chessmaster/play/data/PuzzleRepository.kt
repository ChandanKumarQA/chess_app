package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle
import com.chessmaster.play.model.EndgameLesson
import com.chessmaster.play.model.OpeningTrap
import kotlin.random.Random

object PuzzleRepository {

    var activePlayPuzzle: Puzzle? = null
    var currentPuzzleLevel: Int = 1

    // 100 UNIQUE PUZZLES PROGRESSING FROM EASY (1-35) -> MODERATE (36-70) -> HARD (71-100)
    val levelPuzzles: List<Puzzle> = buildList {
        // === TIER 1: EASY (Levels 1 - 35) • Rating 600 - 1050 • Mate in 1 & Simple Captures ===
        add(Puzzle("level_1", "6k1/5ppp/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e8"), 650, "Mate in 1", 10, 5))
        add(Puzzle("level_2", "6rk/5ppp/8/4N3/8/8/8/6K1 w - - 0 1", listOf("e5f7"), 680, "Smothered Mate", 10, 5))
        add(Puzzle("level_3", "rnbqkbnr/ppppp2p/8/5pp1/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 0 1", listOf("d1h5"), 700, "Mate in 1", 10, 5))
        add(Puzzle("level_4", "k7/8/1K6/8/8/8/8/2Q5 w - - 0 1", listOf("c1c8"), 720, "Mate in 1", 10, 5))
        add(Puzzle("level_5", "8/8/8/8/8/2Q5/5K2/7k w - - 0 1", listOf("c3h3"), 740, "Mate in 1", 10, 5))
        add(Puzzle("level_6", "8/4N1pk/8/R7/8/8/8/6K1 w - - 0 1", listOf("a5h5"), 760, "Mate in 1", 10, 5))
        add(Puzzle("level_7", "7k/R7/5N2/8/8/8/8/6K1 w - - 0 1", listOf("a7h7"), 780, "Mate in 1", 10, 5))
        add(Puzzle("level_8", "7k/6p1/7Q/4B3/8/8/8/6K1 w - - 0 1", listOf("h6g7"), 800, "Mate in 1", 10, 5))
        add(Puzzle("level_9", "3rkr2/8/8/4Q3/8/8/8/4K3 w - - 0 1", listOf("e5e7"), 820, "Mate in 1", 10, 5))
        add(Puzzle("level_10", "6k1/5ppp/8/8/4q3/8/8/4R1K1 w - - 0 1", listOf("e1e4"), 840, "Winning Material", 10, 5))
        add(Puzzle("level_11", "3r2k1/5ppp/8/8/8/8/8/3R2K1 w - - 0 1", listOf("d1d8"), 860, "Mate in 1", 10, 5))
        add(Puzzle("level_12", "2r3k1/5ppp/8/8/8/8/8/2R3K1 w - - 0 1", listOf("c1c8"), 880, "Mate in 1", 10, 5))
        add(Puzzle("level_13", "1r4k1/5ppp/8/8/8/8/8/1R4K1 w - - 0 1", listOf("b1b8"), 900, "Mate in 1", 10, 5))
        add(Puzzle("level_14", "r5k1/5ppp/8/8/8/8/8/R5K1 w - - 0 1", listOf("a1a8"), 910, "Mate in 1", 10, 5))
        add(Puzzle("level_15", "5rk1/5ppp/8/8/8/8/8/5RK1 w - - 0 1", listOf("f1f8"), 920, "Mate in 1", 10, 5))
        add(Puzzle("level_16", "6k1/5ppp/8/8/3r4/8/8/3R2K1 w - - 0 1", listOf("d1d4"), 930, "Winning Material", 10, 5))
        add(Puzzle("level_17", "6k1/5ppp/8/8/2b5/8/8/2B3K1 w - - 0 1", listOf("c1c4"), 940, "Winning Material", 10, 5))
        add(Puzzle("level_18", "6k1/5ppp/8/8/1n6/8/8/1R4K1 w - - 0 1", listOf("b1b4"), 950, "Winning Material", 10, 5))
        add(Puzzle("level_19", "r1bqkb1r/pppp1ppp/2n5/4p3/2B1n3/5Q2/PPPP1PPP/RNB1K1NR w KQkq - 0 1", listOf("f3f7"), 960, "Mate in 1", 10, 5))
        add(Puzzle("level_20", "r1bqk2r/pppp1ppp/2n2n2/2b1p3/2B1P3/3P1Q2/PPP2PPP/RNB1K1NR w KQkq - 0 1", listOf("f3f7"), 970, "Mate in 1", 10, 5))
        add(Puzzle("level_21", "6k1/6p1/5p1p/8/3B4/8/5PPP/4Q1K1 w - - 0 1", listOf("e1e8"), 980, "Mate in 1", 10, 5))
        add(Puzzle("level_22", "5k2/5ppp/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e8"), 990, "Mate in 1", 10, 5))
        add(Puzzle("level_23", "4k3/4pppp/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e7"), 1000, "Mate in 1", 10, 5))
        add(Puzzle("level_24", "6k1/5ppp/8/8/8/5Q2/5PPP/6K1 w - - 0 1", listOf("f3a8"), 1010, "Mate in 1", 10, 5))
        add(Puzzle("level_25", "k7/8/K7/8/8/8/8/1Q6 w - - 0 1", listOf("b1b7"), 1020, "Mate in 1", 10, 5))
        add(Puzzle("level_26", "k7/8/1K6/8/8/8/8/Q7 w - - 0 1", listOf("a1h8"), 1025, "Mate in 1", 10, 5))
        add(Puzzle("level_27", "7k/5Qpp/8/8/8/8/5PPP/6K1 w - - 0 1", listOf("f7f8"), 1030, "Mate in 1", 10, 5))
        add(Puzzle("level_28", "6k1/5p1p/6p1/8/8/5Q2/5PPP/6K1 w - - 0 1", listOf("f3a8"), 1035, "Mate in 1", 10, 5))
        add(Puzzle("level_29", "7k/6pp/8/8/8/8/5PPP/4R1K1 w - - 0 1", listOf("e1e8"), 1040, "Mate in 1", 10, 5))
        add(Puzzle("level_30", "r1b1k2r/pppp1ppp/8/4p3/1bB1n3/2N2Q2/PPPP1PPP/R1B1K1NR w KQkq - 0 1", listOf("f3f7"), 1045, "Mate in 1", 10, 5))
        add(Puzzle("level_31", "6k1/5ppp/8/8/8/8/4QPPP/6K1 w - - 0 1", listOf("e2e8"), 1048, "Mate in 1", 10, 5))
        add(Puzzle("level_32", "6k1/5ppp/8/8/8/8/3Q1PPP/6K1 w - - 0 1", listOf("d2d8"), 1050, "Mate in 1", 10, 5))
        add(Puzzle("level_33", "6k1/5ppp/8/8/8/8/2Q2PPP/6K1 w - - 0 1", listOf("c2c8"), 1050, "Mate in 1", 10, 5))
        add(Puzzle("level_34", "6k1/5ppp/8/8/8/8/1Q3PPP/6K1 w - - 0 1", listOf("b2b8"), 1050, "Mate in 1", 10, 5))
        add(Puzzle("level_35", "7k/5ppp/8/8/8/8/5PPP/R5K1 w - - 0 1", listOf("a1a8"), 1050, "Mate in 1", 10, 5))

        // === TIER 2: MODERATE (Levels 36 - 70) • Rating 1100 - 1650 • Tactics & Mate in 2 ===
        add(Puzzle("level_36", "r3k2r/8/8/3N4/8/8/8/4K3 w kq - 0 1", listOf("d5c7", "e8d7", "c7a8"), 1120, "Fork", 15, 8))
        add(Puzzle("level_37", "4k3/8/8/8/r7/8/6B1/4K3 w - - 0 1", listOf("g2c6", "e8d8", "c6a4"), 1140, "Fork", 15, 8))
        add(Puzzle("level_38", "7k/p7/8/4q3/8/8/8/2B3K1 w - - 0 1", listOf("c1b2", "a7a6", "b2e5"), 1160, "Pin", 15, 8))
        add(Puzzle("level_39", "7q/6k1/p7/8/8/8/8/2B3K1 w - - 0 1", listOf("c1b2", "g7f7", "b2h8"), 1180, "Skewer", 15, 8))
        add(Puzzle("level_40", "2r3k1/5ppp/8/8/8/8/5PPP/3RR1K1 w - - 0 1", listOf("d1d8", "c8d8", "e1e8"), 1200, "Mate in 2", 15, 8))
        add(Puzzle("level_41", "7k/6p1/6B1/8/8/8/5PPP/3Q2K1 w - - 0 1", listOf("d1h5", "h8g8", "h5h7"), 1220, "Mate in 2", 15, 8))
        add(Puzzle("level_42", "k7/8/8/8/8/1R6/2R5/7K w - - 0 1", listOf("c2a2", "a8b8", "b3b8"), 1240, "Mate in 2", 15, 8))
        add(Puzzle("level_43", "8/8/3r1r2/8/4P3/8/8/4K3 w - - 0 1", listOf("e4e5", "d6d5", "e5f6"), 1260, "Fork", 15, 8))
        add(Puzzle("level_44", "4k3/8/2n5/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e6", "e8d7", "e6c6"), 1280, "Double Attack", 15, 8))
        add(Puzzle("level_45", "4k3/8/8/8/r7/8/8/4Q1K1 w - - 0 1", listOf("e1e4", "e8d7", "e4a4"), 1300, "Double Attack", 15, 8))
        add(Puzzle("level_46", "r3k2r/8/8/4N3/8/8/8/4K3 w kq - 0 1", listOf("e5c6", "e8d7", "c6a7"), 1320, "Fork", 15, 8))
        add(Puzzle("level_47", "4k3/8/8/8/7r/8/1B6/4K3 w - - 0 1", listOf("b2f6", "e8d7", "f6h4"), 1340, "Fork", 15, 8))
        add(Puzzle("level_48", "4k3/p7/8/4q3/8/8/8/R5K1 w - - 0 1", listOf("a1e1", "a7a6", "e1e5"), 1360, "Pin", 15, 8))
        add(Puzzle("level_49", "8/8/p7/8/4k3/4q3/8/R5K1 w - - 0 1", listOf("a1e1", "e4d4", "e1e3"), 1380, "Skewer", 15, 8))
        add(Puzzle("level_50", "1r4k1/5ppp/8/8/8/8/5PPP/2RR2K1 w - - 0 1", listOf("c1c8", "b8c8", "d1d8"), 1400, "Deflection", 15, 8))
        add(Puzzle("level_51", "6k1/6p1/5p1p/8/3B4/8/5PPP/3Q2K1 w - - 0 1", listOf("d1d8", "g8f7", "d8e7"), 1420, "Mate in 2", 15, 8))
        add(Puzzle("level_52", "k7/8/8/8/8/R7/1R6/7K w - - 0 1", listOf("b2b7", "a8a7", "a3a7"), 1440, "Mate in 2", 15, 8))
        add(Puzzle("level_53", "8/8/2n1n3/8/3P4/8/8/4K3 w - - 0 1", listOf("d4d5", "c6e7", "d5e6"), 1460, "Fork", 15, 8))
        add(Puzzle("level_54", "4k3/8/1r6/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e6", "e8d7", "e6b6"), 1480, "Double Attack", 15, 8))
        add(Puzzle("level_55", "4k3/8/8/8/1r6/8/8/4Q1K1 w - - 0 1", listOf("e1e4", "e8d7", "e4b4"), 1500, "Double Attack", 15, 8))
        add(Puzzle("level_56", "r2qk2r/8/8/3N4/8/8/8/4K3 w kq - 0 1", listOf("d5c7", "e8e7", "c7a8"), 1520, "Fork", 15, 8))
        add(Puzzle("level_57", "4k3/8/8/8/2r5/8/4B3/4K3 w - - 0 1", listOf("e2c4", "e8d8", "c4a6"), 1540, "Fork", 15, 8))
        add(Puzzle("level_58", "7k/8/8/4q3/8/8/8/3B2K1 w - - 0 1", listOf("d1b3", "a7a6", "b3e6"), 1560, "Pin", 15, 8))
        add(Puzzle("level_59", "7q/5k2/8/8/8/8/8/2B3K1 w - - 0 1", listOf("c1b2", "f7g8", "b2h8"), 1580, "Skewer", 15, 8))
        add(Puzzle("level_60", "3r2k1/5ppp/8/8/8/8/5PPP/4R1K1 w - - 0 1", listOf("e1e8", "d8e8", "e1e8"), 1600, "Mate in 2", 15, 8))
        add(Puzzle("level_61", "6k1/6p1/5B2/8/8/8/5PPP/3Q2K1 w - - 0 1", listOf("d1d8", "g8f7", "d8e7"), 1610, "Mate in 2", 15, 8))
        add(Puzzle("level_62", "k7/8/8/8/8/8/1R6/R6K w - - 0 1", listOf("a1a2", "a8b8", "b2b8"), 1620, "Ladder", 15, 8))
        add(Puzzle("level_63", "8/8/1n1n4/8/2P5/8/8/4K3 w - - 0 1", listOf("c4c5", "b6c8", "c5d6"), 1630, "Pawn Fork", 15, 8))
        add(Puzzle("level_64", "4k3/8/8/2n5/8/8/8/4R1K1 w - - 0 1", listOf("e1e5", "e8d7", "e5c5"), 1640, "Double Attack", 15, 8))
        add(Puzzle("level_65", "4k3/8/8/8/3r4/8/8/4Q1K1 w - - 0 1", listOf("e1e4", "e8d7", "e4d4"), 1645, "Queen Fork", 15, 8))
        add(Puzzle("level_66", "r4rk1/8/8/3N4/8/8/8/4K3 w - - 0 1", listOf("d5e7", "g8f7", "e7c8"), 1650, "Fork", 15, 8))
        add(Puzzle("level_67", "4k3/8/8/8/3r4/8/5B2/4K3 w - - 0 1", listOf("f2c5", "e8d7", "c5d4"), 1650, "Skewer", 15, 8))
        add(Puzzle("level_68", "6k1/p7/8/4q3/8/8/8/4R1K1 w - - 0 1", listOf("e1e5", "a7a6", "e5e8"), 1650, "Pin", 15, 8))
        add(Puzzle("level_69", "6q1/6k1/8/8/8/8/8/1B4K1 w - - 0 1", listOf("b1a2", "g7f8", "a2g8"), 1650, "Skewer", 15, 8))
        add(Puzzle("level_70", "4r1k1/5ppp/8/8/8/8/5PPP/3R2K1 w - - 0 1", listOf("d1d8", "e8d8", "d1d8"), 1650, "Mate in 2", 15, 8))

        // === TIER 3: HARD (Levels 71 - 100) • Rating 1700 - 2400 • Multi-Move & Sacrifices ===
        add(Puzzle("level_71", "5r1k/6pp/7N/8/2Q5/8/5PPP/6K1 w - - 0 1", listOf("c4g8", "f8g8", "h6f7"), 1720, "Smothered Mate", 20, 10))
        add(Puzzle("level_72", "7k/8/4PN2/8/8/8/8/R1B3K1 w - - 0 1", listOf("a1a8", "h8g7", "a8g8"), 1740, "Mate in 2", 20, 10))
        add(Puzzle("level_73", "k7/8/2K5/8/8/8/8/5Q2 w - - 0 1", listOf("f1b5", "a8a7", "b5b7"), 1760, "Mate in 2", 20, 10))
        add(Puzzle("level_74", "r1bqk2r/pppp1ppp/2n2n2/2b1p3/2B1P3/2N2N2/PPPP1PPP/R1BQK2R w KQkq - 0 1", listOf("c4f7", "e8f7", "f3e5"), 1780, "Sacrifice", 20, 10))
        add(Puzzle("level_75", "6k1/5ppp/8/7Q/2B5/8/5PPP/6K1 w - - 0 1", listOf("c4f7", "g8h8", "h5h7"), 1800, "Attraction", 20, 10))
        add(Puzzle("level_76", "3r2k1/5ppp/8/8/8/8/5PPP/1R2R1K1 w - - 0 1", listOf("b1b8", "d8b8", "e1e8"), 1820, "Deflection", 20, 10))
        add(Puzzle("level_77", "7k/5ppB/8/8/8/8/2Q2PPP/6K1 w - - 0 1", listOf("h7g6", "h8g8", "c2h7"), 1850, "Discovered Attack", 20, 10))
        add(Puzzle("level_78", "5k2/4p1p1/8/8/8/5Q2/5PPP/4R1K1 w - - 0 1", listOf("f3a8", "f8f7", "a8e8"), 1880, "Mate in 2", 20, 10))
        add(Puzzle("level_79", "r4rk1/5ppp/8/8/8/8/1Q3PPP/R5K1 w - - 0 1", listOf("a1a8", "f8a8", "b2b7"), 1900, "Clearance", 20, 10))
        add(Puzzle("level_80", "r1b2rk1/pp3ppp/2n5/2qp4/8/3B1N2/PPP2PPP/R2QR1K1 w - - 0 1", listOf("d3h7", "g8h7", "f3g5"), 1930, "Sacrifice", 20, 10))
        add(Puzzle("level_81", "5r1k/7p/6pN/8/2Q5/8/5PPP/6K1 w - - 0 1", listOf("c4d4", "f8f6", "d4f6"), 1960, "Queen Sacrifice", 20, 10))
        add(Puzzle("level_82", "6k1/R5p1/5N2/8/8/8/8/6K1 w - - 0 1", listOf("a7g7", "g8h8", "g7h7"), 1990, "Arabian Mate", 20, 10))
        add(Puzzle("level_83", "k7/1r6/1K6/8/8/8/8/2Q5 w - - 0 1", listOf("c1c8", "b7b8", "c8a6"), 2020, "Mating Net", 20, 10))
        add(Puzzle("level_84", "r1bq1rk1/pppp1ppp/2n5/4p3/2B1n3/2N2N2/PPPP1PPP/R1BQR1K1 w - - 0 1", listOf("e1e4", "d7d5", "c4d5"), 2050, "Center Sacrifice", 20, 10))
        add(Puzzle("level_85", "6k1/5p1p/6p1/7Q/2B5/8/5PPP/6K1 w - - 0 1", listOf("c4f7", "g8g7", "h5e5"), 2080, "Attraction", 20, 10))
        add(Puzzle("level_86", "2r3k1/5ppp/8/8/8/8/5PPP/1R1R2K1 w - - 0 1", listOf("b1b8", "c8b8", "d1d8"), 2110, "Deflection", 20, 10))
        add(Puzzle("level_87", "7k/6pB/5p2/8/8/8/2Q2PPP/6K1 w - - 0 1", listOf("h7g6", "h8g8", "c2c8"), 2140, "Discovered Attack", 20, 10))
        add(Puzzle("level_88", "4k3/4p1p1/8/8/8/4Q3/5PPP/4R1K1 w - - 0 1", listOf("e3e7", "e8f8", "e7f7"), 2170, "Queen Mate", 20, 10))
        add(Puzzle("level_89", "r3r1k1/5ppp/8/8/8/8/1Q3PPP/R5K1 w - - 0 1", listOf("a1a8", "e8a8", "b2b7"), 2200, "Clearance", 20, 10))
        add(Puzzle("level_90", "r1b1k2r/pppp1ppp/8/2b1p3/2B1n3/2N2Q2/PPPP1PPP/R1B1K1NR w KQkq - 0 1", listOf("f3f7", "e8d8", "c3e4"), 2230, "Attack", 20, 10))
        add(Puzzle("level_91", "5rk1/6pp/7N/8/8/8/5PPP/4Q1K1 w - - 0 1", listOf("e1e6", "g8h8", "e6g8"), 2260, "Smothered Setup", 20, 10))
        add(Puzzle("level_92", "7k/6N1/8/8/8/8/8/R1B3K1 w - - 0 1", listOf("a1a8", "h8h7", "a8h8"), 2290, "Mate in 2", 20, 10))
        add(Puzzle("level_93", "k7/8/1K6/8/8/8/8/1Q6 w - - 0 1", listOf("b1h7", "a8b8", "h7h8"), 2310, "Queen Sweep", 20, 10))
        add(Puzzle("level_94", "r1bqk2r/pp1p1ppp/2n2n2/2b1p3/2B1P3/2N2N2/PPPP1PPP/R1BQK2R w KQkq - 0 1", listOf("c4f7", "e8f7", "f3g5"), 2330, "Sacrifice", 20, 10))
        add(Puzzle("level_95", "6k1/5ppp/8/8/2B4Q/8/5PPP/6K1 w - - 0 1", listOf("h4d8", "g8f7", "d8e7"), 2350, "Queen Entrance", 20, 10))
        add(Puzzle("level_96", "3r2k1/p4ppp/8/8/8/8/5PPP/1R2R1K1 w - - 0 1", listOf("b1b8", "d8b8", "e1e8"), 2370, "Back Rank", 20, 10))
        add(Puzzle("level_97", "7k/5ppB/6P1/8/8/8/2Q2PPP/6K1 w - - 0 1", listOf("c2c8", "f7g6", "c8g8"), 2380, "Mate", 20, 10))
        add(Puzzle("level_98", "5k2/8/8/8/8/5Q2/5PPP/4R1K1 w - - 0 1", listOf("f3f7", "f8g8", "e1e8"), 2390, "Checkmate", 20, 10))
        add(Puzzle("level_99", "r5k1/5ppp/8/8/8/8/1Q3PPP/R5K1 w - - 0 1", listOf("a1a8", "g8f7", "b2b7"), 2395, "Clearance", 20, 10))
        add(Puzzle("level_100", "r1b2rk1/pp3ppp/8/2qp4/8/3B1N2/PPP2PPP/R2QR1K1 w - - 0 1", listOf("d3h7", "g8h8", "f3g5"), 2400, "Grandmaster Finish", 25, 12))
    }

    private val allTraps = listOf(
        OpeningTrap("ot_scholar_1", "Scholar's Mate", "A quick 4-move checkmate targeting f7.", listOf("e2e4", "e7e5", "d1h5", "b8c6", "f1c4", "g8f6", "h5f7"), 5, "Nf6 is a mistake when Bc4 and Qh5 are attacking f7."),
        OpeningTrap("ot_fried_1", "Fried Liver", "An aggressive attack starting from the Italian Game.", listOf("e2e4", "e7e5", "g1f3", "b8c6", "f1c4", "g8f6", "f3g5", "d7d5", "e4d5", "f6d5", "g5f7"), 9, "Nxd5 allows the powerful Knight sacrifice on f7."),
        OpeningTrap("ot_legal_1", "Legal's Trap", "A beautiful Queen sacrifice.", listOf("e2e4", "e7e5", "g1f3", "b8c6", "f1c4", "d7d6", "b1c3", "c8g4", "h2h3", "g4h5", "f3e5", "h5d1", "c4f7", "e8e7", "c3d5"), 11, "Taking the Queen leads to a forced mate."),
        OpeningTrap("ot_noah_1", "Noah's Ark", "Trap the bishop.", listOf("e2e4", "e7e5", "g1f3", "b8c6", "f1b5", "a7a6", "b5a4", "d7d6", "d2d4", "b7b5", "a4b3", "c6d4", "f3d4", "e5d4", "d1d4", "c7c5"), 5, "Trap the bishop."),
        OpeningTrap("ot_greek_1", "Greek Gift", "Sacrifice on h7.", listOf("e2e4", "e7e6", "d2d4", "d7d5", "b1c3", "g8f6", "e4e5", "f6d7", "f1d3", "c7c5", "g1f3", "b8c6", "e1g1", "c5d4", "c3b5", "d7e5", "f3e5", "c6e5", "c1f4", "e5d3", "b5c7"), 5, "Sacrifice on h7."),
        OpeningTrap("ot_stafford_1", "Stafford Gambit", "Tricky knight moves.", listOf("e2e4", "e7e5", "g1f3", "g8f6", "f3e5", "b8c6", "e5c6", "d7c6"), 5, "Develop the pieces."),
        OpeningTrap("ot_vienna_1", "Vienna Trap", "Copycat trap.", listOf("e2e4", "e7e5", "b1c3", "g8f6", "f1c4", "f6e4", "c3e4", "d7d5"), 5, "Fork the pieces."),
        OpeningTrap("ot_sicilian_1", "Sicilian Smith-Morra", "Smith-Morra trick.", listOf("e2e4", "c7c5", "d2d4", "c5d4", "c2c3", "d4c3", "b1c3", "b8c6", "g1f3", "d7d6", "f1c4", "e7e6", "e1g1", "g8f6", "d1e2", "f8e7", "f1d1", "e6e5", "c1g5", "c8g4"), 5, "Watch out for d5."),
        OpeningTrap("ot_elephant_1", "Elephant Trap", "Black traps White into taking on d5 in the QGD.", listOf("d2d4", "d7d5", "c2c4", "e7e6", "b1c3", "g8f6", "c1g5", "b8d7", "c4d5", "e6d5", "c3d5", "f6d5", "g5d8", "f8b4", "d1d2", "b4d2", "e1d2", "e8d8"), 9, "Bxd8 allows the devastating Bb4+ check."),
        OpeningTrap("ot_blackburne_1", "Blackburne Shilling Gambit", "A sneaky knight move sets up a smothered mate.", listOf("e2e4", "e7e5", "g1f3", "b8c6", "f1c4", "c6d4", "f3e5", "d8g5", "e5f7", "g5g2", "h1f1", "g2e4", "c4e2", "d4f3"), 7, "Nd4 leaves e5 as poisoned bait."),
        OpeningTrap("ot_fishing_1", "Fishing Pole Trap", "Opening the h-file with a piece sacrifice.", listOf("e2e4", "e7e5", "g1f3", "b8c6", "f1b5", "g8f6", "e1g1", "f6g4", "h2h3", "h7h5", "h3g4", "h5g4", "f3e1", "d8h4"), 7, "Taking on g4 opens the lethal h-file."),
        OpeningTrap("ot_halosar_1", "Halosar Trap", "Blackmar-Diemer gambit tactical surprise.", listOf("d2d4", "d7d5", "e2e4", "d5e4", "b1c3", "g8f6", "f2f3", "e4f3", "d1f3", "d8d4", "c1e3", "d4b4", "e1c1", "c8g4", "c3b5"), 7, "Nb5 creates an unstoppable mate threat on c7."),
        OpeningTrap("ot_lasker_1", "Lasker Trap", "Historic underpromotion in the Albin Countergambit.", listOf("d2d4", "d7d5", "c2c4", "e7e5", "d4e5", "d5d4", "e2e3", "f8b4", "c1d2", "d4e3", "d2b4", "e3f2", "e1e2", "f2g1n"), 7, "Promoting to Knight with check wins the Queen."),
        OpeningTrap("ot_budapest_1", "Budapest Trap", "Smothered checkmate in the Budapest Gambit.", listOf("d2d4", "g8f6", "c2c4", "e7e5", "d4e5", "f6g4", "c1f4", "b8c6", "g1f3", "f8b4", "b1d2", "d8e7", "a2a3", "g4e5", "a3b4", "e5d3"), 7, "Nd3 checkmate because the pawn is pinned!"),
        OpeningTrap("ot_mortimer_1", "Mortimer Trap", "Deceptive Knight maneuver in the Berlin Defense.", listOf("e2e4", "e7e5", "g1f3", "b8c6", "f1b5", "g8f6", "d2d3", "c6e7", "f3e5", "c7c6", "b5a4", "d8a5"), 6, "Qa5+ forks the King on e1 and Knight on e5."),
        OpeningTrap("ot_rubinstein_1", "Rubinstein Trap", "A classic Queen's Gambit Declined maneuver.", listOf("d2d4", "d7d5", "c2c4", "e7e6", "b1c3", "g8f6", "c1g5", "b8d7", "e2e3", "c7c6", "g1f3", "d8a5", "f3d2", "f8b4", "d1c2", "e8g8", "g5f4"), 8, "Sharp positional trap in the Cambridge Springs."),
        OpeningTrap("ot_magnus_smith_1", "Magnus Smith Trap", "A sharp tactic in the Sicilian Sozin punishing an early g6.", listOf("e2e4", "c7c5", "g1f3", "d7d6", "d2d4", "c5d4", "f3d4", "g8f6", "b1c3", "b8c6", "f1c4", "g7g6", "d4c6", "b7c6", "e4e5", "d6e5", "c4f7", "e8f7", "d1d8"), 15, "8... dxe5 allows 9. Bxf7+ winning the Queen on d8."),
        OpeningTrap("ot_tarrasch_1", "Tarrasch Trap", "Exploiting the pinned d-pawn in the Open Ruy Lopez.", listOf("e2e4", "e7e5", "g1f3", "b8c6", "f1b5", "a7a6", "b5a4", "g8f6", "e1g1", "f6e4", "d2d4", "b7b5", "a4b3", "d7d5", "d4e5", "c8e6", "c2c3", "f8e7", "f1e1", "e8g8", "f3d4", "d8d7", "d4e6", "f7e6", "e1e4"), 23, "13. Rxe4 wins a piece because Black's d-pawn is pinned to the Queen on d7.")
    )

    fun getDailyPuzzle(): Puzzle = levelPuzzles.first()

    fun getPuzzleForLevel(level: Int): Puzzle {
        val index = (level - 1).coerceAtLeast(0) % levelPuzzles.size
        return levelPuzzles[index]
    }

    // RETURNS 100 THEME-AUTHENTIC PUZZLES FOR EACH TACTICAL THEME
    fun getPuzzlesByCategory(category: String): List<Puzzle> {
        return TacticalDatabase.getPuzzles(category)
    }

    fun getNextPuzzleInCategory(currentPuzzle: Puzzle): Puzzle? {
        val puzzles = getPuzzlesByCategory(currentPuzzle.theme.substringBefore(" -"))
        val index = puzzles.indexOfFirst { it.id == currentPuzzle.id }
        if (index >= 0 && index < puzzles.size - 1) {
            return puzzles[index + 1]
        }
        return null
    }

    fun getFirstPuzzleOfNextCategory(currentCategory: String): Puzzle? {
        val categories = getAllTacticalCategories()
        val index = categories.indexOf(currentCategory)
        if (index >= 0 && index < categories.size - 1) {
            val nextCategory = categories[index + 1]
            return getPuzzlesByCategory(nextCategory).firstOrNull()
        }
        return null
    }

    fun getAllTacticalCategories(): List<String> {
        return listOf(
            "Mate in 1", "Mate in 2", "Fork", "Pin", "Skewer",
            "Double Attack", "Discovered Attack", "Smothered Mate",
            "Sacrifice", "Attraction", "Deflection", "Clearance",
            "Winning Material"
        )
    }

    fun getAllEndgameCategories(): List<String> {
        return listOf(
            "King vs King",
            "King + Pawn",
            "Rook Endgame",
            "Queen Endgame",
            "Bishop Endgame",
            "Knight Endgame",
            "Passed Pawns",
            "Endgame Strategy"
        )
    }

    // RETURNS 100 ENDGAME LESSONS FOR EACH CATEGORY (1-35 Easy, 36-70 Moderate, 71-100 Hard)
    // USES AUTHENTIC LICHESS-STYLE PRACTICE POSITIONS
    fun getEndgameLessonsByCategory(category: String): List<EndgameLesson> {
        return EndgameDatabase.getLessons(category)
    }

    fun getFirstLessonOfNextEndgameCategory(currentCategory: String): EndgameLesson? {
        val categories = getAllEndgameCategories()
        val index = categories.indexOf(currentCategory)
        if (index >= 0 && index < categories.size - 1) {
            val nextCategory = categories[index + 1]
            return getEndgameLessonsByCategory(nextCategory).firstOrNull()
        }
        return null
    }

    fun getAllOpeningTraps(): List<OpeningTrap> {
        return allTraps
    }

    fun getRandomPuzzles(count: Int): List<Puzzle> {
        val allAvailable = mutableListOf<Puzzle>()
        allAvailable.addAll(levelPuzzles)
        for (cat in getAllTacticalCategories()) {
            allAvailable.addAll(TacticalDatabase.getPuzzles(cat))
        }
        val distinct = allAvailable.distinctBy { it.fen }
        val random = Random(System.currentTimeMillis())

        val easy = distinct.filter { it.rating <= 1100 }.shuffled(random)
        val moderate = distinct.filter { it.rating in 1101..1700 }.shuffled(random)
        val hard = distinct.filter { it.rating > 1700 }.shuffled(random)

        val easyCount = (count * 0.35).toInt().coerceAtLeast(1)
        val moderateCount = (count * 0.35).toInt().coerceAtLeast(1)
        val hardCount = (count - easyCount - moderateCount).coerceAtLeast(1)

        val selected = mutableListOf<Puzzle>()
        selected.addAll(easy.take(easyCount))
        selected.addAll(moderate.take(moderateCount))
        selected.addAll(hard.take(hardCount))

        if (selected.size < count) {
            val remaining = distinct.filterNot { p -> selected.any { it.id == p.id } }.shuffled(random)
            selected.addAll(remaining.take(count - selected.size))
        }

        return selected.sortedBy { it.rating }
    }

    fun getAllSurvivalCategories(): List<String> {
        return listOf(
            "Classic Survival",
            "Checkmate Survival",
            "Fork & Pin Survival",
            "Sacrifice & Attack",
            "Endgame Survival",
            "Grandmaster Survival"
        )
    }

    // RETURNS 100 THEME-MATCHED PUZZLES SORTED ASCENDING BY RATING FOR SURVIVAL MODE
    fun getSurvivalPuzzlesByCategory(category: String): List<Puzzle> {
        val themeCategories = when (category) {
            "Checkmate Survival" -> listOf("Mate in 1", "Mate in 2", "Smothered Mate")
            "Fork & Pin Survival" -> listOf("Fork", "Pin", "Skewer", "Double Attack")
            "Sacrifice & Attack" -> listOf("Sacrifice", "Attraction", "Deflection", "Clearance", "Discovered Attack")
            "Endgame Survival" -> listOf("Winning Material")
            "Grandmaster Survival" -> getAllTacticalCategories()
            else -> getAllTacticalCategories()
        }

        val pool = mutableListOf<Puzzle>()
        for (theme in themeCategories) {
            pool.addAll(TacticalDatabase.getPuzzles(theme))
        }
        val extra = when (category) {
            "Checkmate Survival" -> levelPuzzles.filter { it.theme.contains("Mate", ignoreCase = true) || it.theme.contains("Smothered", ignoreCase = true) }
            "Fork & Pin Survival" -> levelPuzzles.filter { it.theme.contains("Fork", ignoreCase = true) || it.theme.contains("Pin", ignoreCase = true) }
            "Sacrifice & Attack" -> levelPuzzles.filter { it.theme.contains("Sacrifice", ignoreCase = true) || it.theme.contains("Attraction", ignoreCase = true) }
            "Endgame Survival" -> levelPuzzles.filter { it.theme.contains("Material", ignoreCase = true) }
            else -> levelPuzzles
        }
        pool.addAll(extra)

        val distinctPool = pool.distinctBy { it.fen }
        val random = Random(System.currentTimeMillis())

        val easyPool = distinctPool.filter { it.rating <= 1050 }.shuffled(random)
        val moderatePool = distinctPool.filter { it.rating in 1051..1650 }.shuffled(random)
        val hardPool = distinctPool.filter { it.rating > 1650 }.shuffled(random)

        val catPrefix = category.lowercase().replace(" & ", "_").replace(" ", "_")

        return (1..100).map { i ->
            val (tierRating, base) = when {
                i <= 35 -> {
                    val r = 650 + (i * 10)
                    val p = if (easyPool.isNotEmpty()) easyPool[(i - 1) % easyPool.size] else distinctPool[(i - 1) % distinctPool.size]
                    r to p
                }
                i <= 70 -> {
                    val r = 1100 + ((i - 35) * 15)
                    val p = if (moderatePool.isNotEmpty()) moderatePool[(i - 36) % moderatePool.size] else distinctPool[(i - 1) % distinctPool.size]
                    r to p
                }
                else -> {
                    val r = 1700 + ((i - 70) * 20)
                    val p = if (hardPool.isNotEmpty()) hardPool[(i - 71) % hardPool.size] else distinctPool[(i - 1) % distinctPool.size]
                    r to p
                }
            }

            Puzzle(
                id = "survival_${catPrefix}_$i",
                fen = base.fen,
                solutionMoves = base.solutionMoves,
                rating = tierRating,
                theme = "$category - ${base.theme.substringAfter(" - ", base.theme)}",
                xpReward = 15 + (i / 10),
                coinsReward = 8 + (i / 20)
            )
        }.sortedBy { it.rating }
    }

    fun getSurvivalPuzzles(): List<Puzzle> {
        return getSurvivalPuzzlesByCategory("Classic Survival")
    }
}
