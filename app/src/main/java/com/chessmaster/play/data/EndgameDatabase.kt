package com.chessmaster.play.data

import com.chessmaster.play.model.EndgameLesson

object EndgameDatabase {

    data class RawLesson(
        val subtitle: String,
        val fen: String,
        val explanation: String,
        val hint: String,
        val moves: List<String>
    )

    // === KING VS KING / OPPOSITION POSITIONS (LICHESS STYLE) ===
    private val kingVsKingPool = listOf(
        RawLesson(
            "Direct Opposition (e-file)",
            "8/8/4k3/8/3K4/4P3/8/8 w - - 0 1",
            "Take direct opposition with Kd4-e4! When Black steps to f6, outflank with Ke4-d5 to control the promotion squares.",
            "Match your King's file with Ke4.",
            listOf("d4e4", "e6f6", "e4d5")
        ),
        RawLesson(
            "Direct Opposition (d-file)",
            "8/8/3k4/8/2K5/3P4/8/8 w - - 0 1",
            "Play c4-d4 taking direct opposition on the d-file! Then outflank Black on c5 when he retreats.",
            "Step into opposition with Kd4.",
            listOf("c4d4", "d6e6", "d4c5")
        ),
        RawLesson(
            "Direct Opposition (f-file)",
            "8/8/5k2/8/4K3/5P2/8/8 w - - 0 1",
            "Move e4-f4 to take opposition on the f-file and escort your passed pawn forward.",
            "Occupy f4 directly opposite Black's King.",
            listOf("e4f4", "f6g6", "f4e5")
        ),
        RawLesson(
            "Direct Opposition (c-file)",
            "8/8/2k5/8/1K6/2P5/8/8 w - - 0 1",
            "Take direct opposition on the c-file with b4-c4! Follow up by outflanking on b5.",
            "Play Kc4 to seize opposition.",
            listOf("b4c4", "c6d6", "c4b5")
        ),
        RawLesson(
            "Key Squares (6th Rank e-file)",
            "4k3/8/4K3/4P3/8/8/8/8 w - - 0 1",
            "With your King firmly on the 6th rank in front of the pawn, step to d6 to clear the promotion path.",
            "Step to the side with Kd6, then advance the pawn.",
            listOf("e6d6", "e8d8", "e5e6")
        ),
        RawLesson(
            "Outflanking Technique",
            "8/8/3k4/8/3K4/4P3/8/8 w - - 0 1",
            "When Black King holds opposition, outflank by playing Kd4-e4! If Black retreats, seize the 5th rank with Ke5.",
            "Step aside with Ke4 to create a path forward.",
            listOf("d4e4", "d6e7", "e4e5")
        ),
        RawLesson(
            "Body Checking (Shoulder-Charging)",
            "8/8/8/3k4/8/4K3/5P2/8 w - - 0 1",
            "Use your King as a shield with Ke3-f4! Block Black's King from approaching your pawn, then infiltrate with Kf4-g5.",
            "Play Kf4 to cut off Black's King.",
            listOf("e3f4", "d5e6", "f4g5")
        ),
        RawLesson(
            "Distant Opposition (3 Squares)",
            "8/8/4k3/8/8/8/4K3/4P3 w - - 0 1",
            "Maintain distant opposition with Ke2-e3! An odd number of squares between kings preserves the advantage.",
            "Play Ke3 to take opposition.",
            listOf("e2e3", "e6e5", "e3d3")
        ),
        RawLesson(
            "Outside Passed Pawn Deflection",
            "8/8/4k3/3p4/5K1P/8/8/8 w - - 0 1",
            "Push the outside passed pawn h4-h5! Black's King must chase it, allowing your King to penetrate into White's center.",
            "Decoy Black's king with h5.",
            listOf("h4h5", "e6f6", "f4e3")
        ),
        RawLesson(
            "Reti's Dual Threat Maneuver",
            "7K/8/2P5/7p/8/8/8/k7 w - - 0 1",
            "The famous Reti endgame idea: Move Kh8-g7! Your King simultaneously threatens to escort c6 and catch Black's h-pawn.",
            "Play Kg7 to pursue both objectives at once.",
            listOf("h8g7", "h5h4", "g7f6")
        )
    )

    // === KING + PAWN POSITIONS ===
    private val kingAndPawnPool = listOf(
        RawLesson(
            "Rule of the Square",
            "8/8/8/4k3/P7/8/8/2K5 w - - 0 1",
            "The pawn is outside Black King's square! Push a4-a5 and march to queen uncontested.",
            "Push a4-a5 immediately.",
            listOf("a4a5", "e5d6", "a5a6")
        ),
        RawLesson(
            "Connected Passed Pawns",
            "8/8/4k3/8/4PP2/4K3/8/8 w - - 0 1",
            "Advance e4-e5 to coordinate your connected pawns! Connected pawns support each other.",
            "Push e5 to restrict Black's King.",
            listOf("e4e5", "e6f5", "e3f3")
        ),
        RawLesson(
            "Pawn Breakthrough",
            "8/8/ppp5/8/PPP5/8/8/4K2k w - - 0 1",
            "Classic 3 vs 3 pawn breakthrough: push b4-b5! Sacrifice one pawn to create an unstoppable passed pawn.",
            "Break the barrier with b5!",
            listOf("b4b5", "c6b5", "c4c5")
        ),
        RawLesson(
            "Outside Passed Pawn Escort",
            "8/8/4k3/8/P7/4K3/8/8 w - - 0 1",
            "Push the outside passed pawn a4-a5, then centralize your King with Ke3-d4.",
            "Advance a5 then centralize Kd4.",
            listOf("a4a5", "e6d6", "e3d4")
        ),
        RawLesson(
            "Escorting the Passed Pawn",
            "8/8/8/4P3/4K3/8/8/4k3 w - - 0 1",
            "Move your King in front of the pawn with Ke4-d5 to escort it to the 8th rank.",
            "Clear the way with Kd5.",
            listOf("e4d5", "e1d2", "e5e6")
        )
    )

    // === ROOK ENDGAME POSITIONS ===
    private val rookEndgamePool = listOf(
        RawLesson(
            "Cutting Off the Enemy King",
            "8/8/4k3/R7/8/4K3/4P3/8 w - - 0 1",
            "Cut the enemy King off along the rank! Step up with Ke3-e4, then pin with Ra5-a6.",
            "Coordinate your King and Rook.",
            listOf("e3e4", "e6d6", "a5a6")
        ),
        RawLesson(
            "Active Rook behind Passed Pawn",
            "8/8/8/8/4k3/4P3/8/4K2R w - - 0 1",
            "Place your rook actively with Rh1-h4! Keep control of the rank while pushing your pawn.",
            "Check with Rh4.",
            listOf("h1h4", "e4d3", "h4a4")
        ),
        RawLesson(
            "Vertical Checking Defense",
            "4k3/8/8/8/8/4K3/8/4R3 w - - 0 1",
            "Activate your King and Rook with Ke3-d4, followed by checking with Re1-d1.",
            "Step into discovery with Kd4.",
            listOf("e3d4", "e8d7", "e1d1")
        ),
        RawLesson(
            "File Cut-off Technique",
            "8/8/3k4/8/2R5/4K3/4P3/8 w - - 0 1",
            "Cut Black King off on the c-file! Centralize your King with Ke3-e4, then lock with Rc4-c6.",
            "Play Ke4 to seize the center.",
            listOf("e3e4", "d6e6", "c4c6")
        )
    )

    // === LUCENA POSITIONS (BUILDING THE BRIDGE) ===
    private val lucenaPool = listOf(
        RawLesson(
            "Lucena Bridge (e-file)",
            "4K3/4P3/4k3/8/8/8/r7/5R2 w - - 0 1",
            "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!",
            "Play Rf1-e1+ then Re4.",
            listOf("f1e1", "e6d6", "e1e4")
        ),
        RawLesson(
            "Lucena Bridge (d-file)",
            "3K4/3P4/3k4/8/8/8/r7/4R3 w - - 0 1",
            "Drive the defending King off the d-file with Re1-d1+, then place your Rook on the 4th rank with Rd1-d4 to shield future checks.",
            "Check on d1, then lift to d4.",
            listOf("e1d1", "d6c6", "d1d4")
        ),
        RawLesson(
            "Lucena Bridge (c-file)",
            "2K5/2P5/2k5/8/8/8/r7/3R4 w - - 0 1",
            "Building the bridge on the c-file: drive the Black King with Rd1-c1+, then drop back with Rc1-c4.",
            "Check on c1, then lift to c4.",
            listOf("d1c1", "c6d6", "c1c4")
        ),
        RawLesson(
            "Lucena Bridge (f-file)",
            "5K2/5P2/5k2/8/8/8/r7/6R1 w - - 0 1",
            "Execute the Lucena maneuver on the f-file: deliver check with Rg1-f1+ and prepare the bridge with Rf1-f4.",
            "Check on f1, then prepare Rf4.",
            listOf("g1f1", "f6e6", "f1f4")
        )
    )

    // === PHILIDOR POSITIONS (THIRD-RANK DEFENSE) ===
    private val philidorPool = listOf(
        RawLesson(
            "Philidor Defense (e-file)",
            "8/8/r7/4P3/4K3/8/8/1k6 w - - 0 1",
            "Classic Philidor Defense: as soon as the pawn pushes to e6, drop the Rook back to a1 to deliver perpetual checks from behind!",
            "Push e6, Black drops to a1, then Kd5.",
            listOf("e5e6", "a6a1", "e4d5")
        ),
        RawLesson(
            "Philidor Defense (d-file)",
            "8/8/r7/3P4/3K4/8/8/1k6 w - - 0 1",
            "Third-rank defense on the d-file: pawn advances to d6, and the attacking king moves to c5.",
            "Push d6, then step to c5.",
            listOf("d5d6", "a6a1", "d4c5")
        ),
        RawLesson(
            "Philidor Defense (c-file)",
            "8/8/r7/2P5/2K5/8/8/1k6 w - - 0 1",
            "Third-rank defense on the c-file: push c6, black rook retreats to a1, white king steps to b5.",
            "Advance c6, then Kb5.",
            listOf("c5c6", "a6a1", "c4b5")
        ),
        RawLesson(
            "Philidor Defense (f-file)",
            "8/8/r7/5P2/5K2/8/8/1k6 w - - 0 1",
            "Third-rank defense on the f-file: push f6, Black seeks rear checks, White king advances to e5.",
            "Push f6, then Ke5.",
            listOf("f5f6", "a6a1", "f4e5")
        )
    )

    // === QUEEN ENDGAME POSITIONS ===
    private val queenEndgamePool = listOf(
        RawLesson(
            "Queen vs 7th Rank Pawn",
            "8/8/8/8/8/4k3/4p3/1K1Q4 w - - 0 1",
            "Stop the runaway 7th rank pawn! Block with Qd1-e1, force Black's King to step to f3, then pin with Qe1-d2.",
            "Play Qe1 then pin on d2.",
            listOf("d1e1", "e3f3", "e1d2")
        ),
        RawLesson(
            "Queen Checkmate Net",
            "8/8/8/8/4k3/8/4KQ2/8 w - - 0 1",
            "Coordinate Queen and King to confine the opponent: Qf2-e3 forces the King back, then Ke2-d3 locks the net.",
            "Play Qe3 then bring the King with Kd3.",
            listOf("f2e3", "e4d5", "e2d3")
        ),
        RawLesson(
            "Queen Skewer Defense",
            "8/8/8/8/3q4/8/3K4/3Q4 w - - 0 1",
            "Escape the check and create counter-threats with Kd2-e2!",
            "Step to e2, then f2.",
            listOf("d2e2", "d4e4", "e2f2")
        )
    )

    // === BISHOP ENDGAME POSITIONS ===
    private val bishopEndgamePool = listOf(
        RawLesson(
            "Wrong Bishop & Rook Pawn",
            "7k/8/8/7P/8/8/5B2/6K1 w - - 0 1",
            "A dark-squared bishop cannot control the light promotion square h8! Centralize Bf2-d4 and bring your King.",
            "Check with Bd4, then advance Kg2.",
            listOf("f2d4", "h8h7", "g1g2")
        ),
        RawLesson(
            "Diagonal Domination",
            "8/8/4k3/8/8/3B4/4K3/8 w - - 0 1",
            "Deliver check along the diagonal with Bd3-c4+! Control key squares while centralizing your King with Ke2-e3.",
            "Check with Bc4, then centralize Ke3.",
            listOf("d3c4", "e6e5", "e2e3")
        )
    )

    // === KNIGHT ENDGAME POSITIONS ===
    private val knightEndgamePool = listOf(
        RawLesson(
            "Knight Stopping Passed Pawn",
            "8/8/4k3/8/3p4/8/5N2/4K3 w - - 0 1",
            "Use your Knight to blockade the passed pawn! Play Nf2-d3, then march your King with Ke1-d2.",
            "Blockade with Nd3, then bring Kd2.",
            listOf("f2d3", "e6d5", "e1d2")
        ),
        RawLesson(
            "Knight Outpost & Centralization",
            "8/8/3k4/8/3N4/8/4K3/8 w - - 0 1",
            "Reposition your Knight with Nd4-f5+! Then step back to the ideal central outpost e3.",
            "Check with Nf5+, then centralize Ne3.",
            listOf("d4f5", "d6e6", "f5e3")
        )
    )

    fun getLessons(category: String): List<EndgameLesson> {
        val pool = when (category) {
            "King vs King" -> kingVsKingPool
            "King + Pawn" -> kingAndPawnPool
            "Rook Endgame" -> rookEndgamePool
            "Lucena" -> lucenaPool
            "Philidor" -> philidorPool
            "Queen Endgame" -> queenEndgamePool
            "Bishop Endgame" -> bishopEndgamePool
            "Knight Endgame" -> knightEndgamePool
            else -> kingVsKingPool
        }

        return (1..100).map { level ->
            val tier = when {
                level <= 35 -> "Easy"
                level <= 70 -> "Moderate"
                else -> "Hard"
            }
            val raw = pool[(level - 1) % pool.size]
            val catPrefix = category.replace("+", "p").replace(" ", "_").lowercase()

            EndgameLesson(
                id = "e_${catPrefix}_$level",
                title = "${raw.subtitle} • Level $level ($tier)",
                category = category,
                fen = raw.fen,
                explanation = "Level $level ($tier difficulty): ${raw.explanation}",
                hint = raw.hint,
                solutionMoves = raw.moves
            )
        }
    }
}
