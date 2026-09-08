import os

content = """package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle
import com.chessmaster.play.model.EndgameLesson
import com.chessmaster.play.model.OpeningTrap
import kotlin.random.Random

object PuzzleRepository {

    var activePlayPuzzle: Puzzle? = null

    private val allPuzzles = listOf(
        // General categories
        Puzzle("c_m1_1", "4k3/8/4K3/8/8/8/8/5R2 w - - 0 1", listOf("f1f8"), 1000, "Mate in 1", 10, 5),
        Puzzle("c_m2_1", "r1bqkb1r/pppp1ppp/2n2n2/4p2Q/2B1P3/8/PPPP1PPP/RNB1K1NR w KQkq - 0 1", listOf("h5f7"), 1100, "Mate in 2", 10, 5), // Not actual mate in 2 FEN, just dummy
        Puzzle("c_m3_1", "6k1/5ppp/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e8"), 1200, "Mate in 3", 10, 5), // dummy
        Puzzle("c_m4_1", "6k1/5ppp/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e8"), 1300, "Mate in 4", 10, 5), // dummy
        Puzzle("c_bm_1", "6k1/5ppp/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e8"), 1400, "Best Move", 10, 5),
        Puzzle("c_sac_1", "6k1/5ppp/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e8"), 1500, "Sacrifice", 10, 5),
        Puzzle("c_end_1", "6k1/5ppp/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e8"), 1500, "Endgame", 10, 5),
        Puzzle("c_ot_1", "6k1/5ppp/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e8"), 1500, "Opening Trap", 10, 5),
        Puzzle("c_pro_1", "6k1/5ppp/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e8"), 1500, "Promotion", 10, 5),
        Puzzle("c_def_1", "6k1/5ppp/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e8"), 1500, "Defensive Move", 10, 5),
        Puzzle("c_win_1", "6k1/5ppp/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e8"), 1500, "Winning Material", 10, 5),

        // Tactical Themes
        Puzzle("t_fork_1", "1r1r1k2/p3ppp1/8/2N5/8/8/4PPP1/5K2 w - - 0 1", listOf("c5d7", "f8e8", "d7b8"), 1200, "Fork", 10, 5),
        Puzzle("t_pin_1", "8/5k2/8/3q4/8/3P4/B7/4K3 w - - 0 1", listOf("a2c4", "f7e7", "c4d5"), 1200, "Pin", 10, 5),
        Puzzle("t_skewer_1", "q7/8/2k5/5B2/8/8/8/6K1 w - - 0 1", listOf("f5e4", "c6d6", "e4a8"), 1200, "Skewer", 10, 5),
        Puzzle("t_da_1", "6k1/5ppp/8/r7/8/8/8/3Q2K1 w - - 0 1", listOf("d1d5", "g8h8", "d5a5"), 1200, "Double Attack", 10, 5),
        Puzzle("t_disc_1", "6k1/5ppp/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e8"), 1200, "Discovered Attack", 10, 5),
        Puzzle("t_defl_1", "6k1/5ppp/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e8"), 1200, "Deflection", 10, 5),
        Puzzle("t_clear_1", "6k1/5ppp/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e8"), 1200, "Clearance", 10, 5),
        Puzzle("t_attr_1", "6k1/5ppp/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e8"), 1200, "Attraction", 10, 5),
        Puzzle("t_rem_1", "6k1/5ppp/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e8"), 1200, "Remove Defender", 10, 5),
        
        // Ensure at least 30 random puzzles for level matching
        Puzzle("t_gen_1", "2k5/1ppp4/8/8/8/8/8/R1K5 w - - 0 1", listOf("a1a8"), 1200, "Back Rank Mate", 10, 5),
        Puzzle("t_gen_2", "3k4/2ppp3/8/8/8/8/8/1R1K4 w - - 0 1", listOf("b1b8"), 1200, "Back Rank Mate", 10, 5),
        Puzzle("t_gen_3", "2r1k3/3pp3/4N3/Q7/8/8/8/3K4 w - - 0 1", listOf("a5d8", "c8d8", "e6c7"), 1200, "Smothered Mate", 10, 5),
        Puzzle("t_gen_4", "8/5k2/8/3q4/8/3P4/B7/4K3 w - - 0 1", listOf("a2c4", "f7e7", "c4d5"), 1200, "Pin", 10, 5),
        Puzzle("t_gen_5", "4k3/3ppp2/8/8/8/8/8/2R1K3 w - - 0 1", listOf("c1c8"), 1200, "Back Rank Mate", 10, 5),
        Puzzle("t_gen_6", "3r1k2/4pp2/5N2/1Q6/8/8/8/4K3 w - - 0 1", listOf("b5e8", "d8e8", "f6d7"), 1200, "Smothered Mate", 10, 5),
        Puzzle("t_gen_7", "5k2/8/3q4/8/3P4/B7/4K3/8 w - - 0 1", listOf("a3c5", "f8e8", "c5d6"), 1200, "Pin", 10, 5),
        Puzzle("t_gen_8", "1r1r1k2/p3ppp1/8/2N5/8/8/4PPP1/5K2 w - - 0 1", listOf("c5d7", "f8e8", "d7b8"), 1200, "Fork", 10, 5),
        Puzzle("t_gen_9", "8/6k1/8/4q3/8/4P3/1B6/5K2 w - - 0 1", listOf("b2d4", "g7f7", "d4e5"), 1200, "Pin", 10, 5),
        Puzzle("t_gen_10", "5k2/4ppp1/8/8/8/8/8/3R1K2 w - - 0 1", listOf("d1d8"), 1200, "Back Rank Mate", 10, 5),
        Puzzle("t_gen_11", "4r1k1/5pp1/6N1/2Q5/8/8/8/5K2 w - - 0 1", listOf("c5f8", "e8f8", "g6e7"), 1200, "Smothered Mate", 10, 5),
        Puzzle("t_gen_12", "6k1/8/4q3/8/4P3/1B6/5K2/8 w - - 0 1", listOf("b3d5", "g8f8", "d5e6"), 1200, "Pin", 10, 5),
        Puzzle("t_gen_13", "2r1r1k1/1p3ppp/8/3N4/8/8/5PPP/6K1 w - - 0 1", listOf("d5e7", "g8f8", "e7c8"), 1200, "Fork", 10, 5),
        Puzzle("t_gen_14", "r3k2r/ppp2ppp/8/3N4/8/8/5PPP/6K1 w - - 0 1", listOf("d5c7", "e8f8", "c7a8"), 1200, "Fork", 10, 5),
        Puzzle("t_gen_15", "8/7k/8/5q2/8/5P2/2B5/6K1 w - - 0 1", listOf("c2e4", "h7g7", "e4f5"), 1200, "Pin", 10, 5),
        Puzzle("t_gen_16", "q7/8/2k5/5B2/8/8/8/6K1 w - - 0 1", listOf("f5e4", "c6d6", "e4a8"), 1200, "Skewer", 10, 5),
        Puzzle("t_gen_17", "6k1/5ppp/8/8/8/8/8/4R1K1 w - - 0 1", listOf("e1e8"), 1200, "Back Rank Mate", 10, 5),
        Puzzle("t_gen_18", "5r1k/6pp/7N/3Q4/8/8/8/6K1 w - - 0 1", listOf("d5g8", "f8g8", "h6f7"), 1200, "Smothered Mate", 10, 5),
        Puzzle("t_gen_19", "k1r5/pp6/N7/4Q3/8/8/8/1K6 w - - 0 1", listOf("e5b8", "c8b8", "a6c7"), 1200, "Smothered Mate", 10, 5),
        Puzzle("t_gen_20", "6k1/5ppp/8/r7/8/8/8/3Q2K1 w - - 0 1", listOf("d1d5", "g8h8", "d5a5"), 1200, "Double Attack", 10, 5)
    )

    private val allEndgames = listOf(
        EndgameLesson("e_kvsk_1", "King vs King", "Basic opposition.", "8/8/8/4k3/4K3/8/8/8 w - - 0 1", "Kings cannot move to adjacent squares.", "Move away.", listOf("e4d3")),
        EndgameLesson("e_kvp_1", "King + Pawn", "Promote the pawn.", "8/8/8/4k3/4P3/4K3/8/8 w - - 0 1", "Keep your King in front of your pawn.", "Take the opposition.", listOf("e3d3")),
        EndgameLesson("e_rook_1", "Rook Endgame", "Basic Rook checkmate.", "8/8/8/4k3/8/4K3/8/R7 w - - 0 1", "Cut off the enemy King using your Rook.", "Limit the King.", listOf("a1a5")),
        EndgameLesson("e_queen_1", "Queen Endgame", "Queen vs Pawn.", "8/8/8/4k3/8/4K3/8/Q7 w - - 0 1", "Use the Queen.", "Check.", listOf("a1a5")),
        EndgameLesson("e_bishop_1", "Bishop Endgame", "Bishop endgame.", "8/8/8/4k3/8/4K3/8/B7 w - - 0 1", "Use the Bishop.", "Check.", listOf("a1b2")),
        EndgameLesson("e_knight_1", "Knight Endgame", "Knight endgame.", "8/8/8/4k3/8/4K3/8/N7 w - - 0 1", "Use the Knight.", "Check.", listOf("a1b3")),
        EndgameLesson("e_lucena_1", "Lucena", "Lucena Position", "1K6/1P1k4/8/8/8/8/8/2R5 w - - 0 1", "Build a bridge to protect your King.", "Move the rook to the 4th rank.", listOf("c1c4")),
        EndgameLesson("e_philidor_1", "Philidor", "Philidor Position", "8/8/8/4k3/8/4K3/8/R7 w - - 0 1", "Keep rook on 3rd rank.", "Move rook.", listOf("a1a3"))
    )

    private val allTraps = listOf(
        OpeningTrap("ot_scholar_1", "Scholar's Mate", "A quick 4-move checkmate targeting f7.", listOf("e2e4", "e7e5", "d1h5", "b8c6", "f1c4", "g8f6", "h5f7"), 5, "Nf6 is a mistake when Bc4 and Qh5 are attacking f7."),
        OpeningTrap("ot_fried_1", "Fried Liver", "An aggressive attack starting from the Italian Game.", listOf("e2e4", "e7e5", "g1f3", "b8c6", "f1c4", "g8f6", "f3g5", "d7d5", "e4d5", "f6d5", "g5f7"), 9, "Nxd5 allows the powerful Knight sacrifice on f7."),
        OpeningTrap("ot_legal_1", "Legal Trap", "A beautiful Queen sacrifice.", listOf("e2e4", "e7e5", "g1f3", "b8c6", "f1c4", "d7d6", "b1c3", "c8g4", "h2h3", "g4h5", "f3e5", "h5d1", "c4f7", "e8e7", "c3d5"), 11, "Taking the Queen leads to a forced mate."),
        OpeningTrap("ot_noah_1", "Noah's Ark", "Trap the bishop.", listOf("e2e4", "e7e5", "g1f3", "b8c6", "f1b5", "a7a6", "b5a4", "d7d6", "d2d4", "b7b5", "a4b3", "c6d4", "f3d4", "e5d4", "d1d4", "c7c5"), 5, "Trap the bishop."),
        OpeningTrap("ot_greek_1", "Greek Gift", "Sacrifice on h7.", listOf("e2e4", "e7e6", "d2d4", "d7d5", "b1c3", "g8f6", "e4e5", "f6d7", "f1d3", "c7c5", "g1f3", "b8c6", "e1g1", "c5d4", "c3b5", "d7e5", "f3e5", "c6e5", "c1f4", "e5d3", "b5c7"), 5, "Sacrifice on h7."),
        OpeningTrap("ot_stafford_1", "Stafford Gambit", "Tricky knight moves.", listOf("e2e4", "e7e5", "g1f3", "g8f6", "f3e5", "b8c6", "e5c6", "d7c6"), 5, "Develop the pieces."),
        OpeningTrap("ot_vienna_1", "Vienna Trap", "Copycat trap.", listOf("e2e4", "e7e5", "b1c3", "g8f6", "f1c4", "f6e4", "c3e4", "d7d5"), 5, "Fork the pieces."),
        OpeningTrap("ot_sicilian_1", "Sicilian Trap", "Smith-Morra trick.", listOf("e2e4", "c7c5", "d2d4", "c5d4", "c2c3", "d4c3", "b1c3", "b8c6", "g1f3", "d7d6", "f1c4", "e7e6", "e1g1", "g8f6", "d1e2", "f8e7", "f1d1", "e6e5", "c1g5", "c8g4"), 5, "Watch out for d5.")
    )

    fun getDailyPuzzle(): Puzzle = allPuzzles.first()
    
    var currentPuzzleLevel: Int = 1
    
    fun getPuzzleForLevel(level: Int): Puzzle {
        val index = (level - 1) % allPuzzles.size
        return allPuzzles[index]
    }
    
    fun getPuzzlesByCategory(category: String): List<Puzzle> {
        return allPuzzles.filter { it.theme == category }
    }

    fun getAllTacticalCategories(): List<String> {
        return listOf(
            "Mate in 1", "Mate in 2", "Mate in 3", "Mate in 4", "Best Move",
            "Fork", "Pin", "Skewer", "Double Attack", "Discovered Attack",
            "Deflection", "Clearance", "Attraction", "Remove Defender",
            "Sacrifice", "Endgame", "Opening Trap", "Promotion", "Defensive Move", "Winning Material"
        )
    }

    fun getAllEndgameCategories(): List<String> {
        return listOf(
            "King vs King", "King + Pawn", "Rook Endgame", "Queen Endgame",
            "Bishop Endgame", "Knight Endgame", "Lucena", "Philidor"
        )
    }

    fun getEndgameLessonsByCategory(category: String): List<EndgameLesson> {
        return allEndgames.filter { it.category == category }
    }

    fun getAllOpeningTraps(): List<OpeningTrap> {
        return allTraps
    }

    fun getRandomPuzzles(count: Int): List<Puzzle> {
        val shuffled = allPuzzles.shuffled(Random(System.currentTimeMillis()))
        return if (shuffled.size >= count) shuffled.take(count) else {
            List(count) { shuffled[it % shuffled.size] }
        }
    }
}
"""

with open("app/src/main/java/com/chessmaster/play/data/PuzzleRepository.kt", "w") as f:
    f.write(content)

