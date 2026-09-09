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
        ),
        RawLesson(
            "Trebuchet / Mutual Zugzwang",
            "8/8/3k4/3p4/5K2/4P3/8/8 w - - 0 1",
            "Penetrate with Kf4-f5! Attack the base of Black's pawn chain and force Black's King to step back.",
            "Invade with Kf5.",
            listOf("f4f5", "d6e7", "f5e5")
        ),
        RawLesson(
            "Diagonal Infiltration",
            "8/8/5k2/8/8/2K5/2P5/8 w - - 0 1",
            "Infiltrate with Kc3-d4! Control the center and step into c5.",
            "Centralize with Kd4.",
            listOf("c3d4", "f6e6", "d4c5")
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
        ),
        RawLesson(
            "Protected Passed Pawn",
            "8/8/4k3/4p3/4P3/4KP2/8/8 w - - 0 1",
            "Challenge Black's center with f3-f4! Create an active passed pawn.",
            "Strike with f4.",
            listOf("f3f4", "e6f6", "f4e5")
        ),
        RawLesson(
            "Pawn Breakthrough (2 vs 2)",
            "8/8/pp6/8/PP6/8/8/4K2k w - - 0 1",
            "Sacrifice with b4-b5! Create an outside runner with a4-a5.",
            "Push b5 then a5.",
            listOf("b4b5", "a6b5", "a4a5")
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
        ),
        RawLesson(
            "Fourth Rank Cut-off",
            "8/8/4k3/8/R7/4K3/4P3/8 w - - 0 1",
            "Advance with Ke3-e4! When Black steps back, seal the rank with Ra4-a6.",
            "Advance Ke4 then Ra6.",
            listOf("e3e4", "e6f6", "a4a6")
        ),
        RawLesson(
            "Long Range Flank Defense",
            "8/8/4k3/8/8/8/4K3/R7 w - - 0 1",
            "Control the 5th rank with Ra1-a5! Cut off the enemy King from advancing.",
            "Lift the rook with Ra5.",
            listOf("a1a5", "e6d6", "e2e3")
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
        ),
        RawLesson(
            "Central Queen Dominance",
            "8/8/8/8/4k3/3Q4/4K3/8 w - - 0 1",
            "Centralize your Queen with Qd3-e3+! Confine the Black King to d5.",
            "Check with Qe3, then centralize Kd3.",
            listOf("d3e3", "e4d5", "e2d3")
        ),
        RawLesson(
            "Queen vs c-pawn",
            "8/8/8/8/8/2k5/2p5/K2Q4 w - - 0 1",
            "Control the promotion square: Qd1-c1, Black King to b3, then Qe3+.",
            "Play Qc1 then Qe3.",
            listOf("d1c1", "c3b3", "c1e3")
        ),
        RawLesson(
            "Queen Staircase Check",
            "8/8/8/8/4k3/8/4p3/2KQ4 w - - 0 1",
            "Execute the staircase maneuver with Qd1-d2+! Then blockade on e1.",
            "Play Qd2 then Qe1.",
            listOf("d1d2", "e4f3", "d2e1")
        ),
        RawLesson(
            "Queen King Drive",
            "8/8/8/8/2k5/4Q3/4K3/8 w - - 0 1",
            "Drive the opposing King back with Qe3-e4+! Follow up with Ke2-d3.",
            "Deliver check with Qe4, then Kd3.",
            listOf("e3e4", "c4c5", "e2d3")
        ),
        RawLesson(
            "Queen Escort Pass",
            "8/8/8/8/5k2/3Q4/4K3/8 w - - 0 1",
            "Confine the King with Qd3-e3+! Step forward with Ke2-f2.",
            "Play Qe3 then Kf2.",
            listOf("d3e3", "f4g4", "e2f2")
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
        ),
        RawLesson(
            "Bishop Central Blockade",
            "8/8/4k3/8/8/2B5/4K3/8 w - - 0 1",
            "Blockade the central squares with Bc3-d4! Coordinate King and Bishop on dark squares.",
            "Centralize with Bd4, then Kd3.",
            listOf("c3d4", "e6d5", "e2d3")
        ),
        RawLesson(
            "Bishop Wing Domination",
            "8/8/2k5/8/8/3B4/2K5/8 w - - 0 1",
            "Deliver check with Bd3-c4+! Lock the Black King on the queenside.",
            "Play Bc4 then Kc3.",
            listOf("d3c4", "c6c5", "c2c3")
        ),
        RawLesson(
            "Bishop Long Diagonal Control",
            "8/8/8/8/8/5B2/4K3/6k1 w - - 0 1",
            "Lock the opponent King in the corner with Bf3-e4! Prepare Ke2-f3.",
            "Play Be4 then Kf3.",
            listOf("f3e4", "g1h2", "e2f3")
        ),
        RawLesson(
            "Opposite Colored Bishop Draw",
            "8/8/4k3/8/3B4/8/4K3/5b2 w - - 0 1",
            "Neutralize threats with Ke2xf1! White secures the drawn opposite bishop position.",
            "Take the bishop with Kxf1.",
            listOf("e2f1", "e6d5", "f1f2")
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
        ),
        RawLesson(
            "Knight Central Dominance",
            "8/8/4k3/8/4N3/8/4K3/8 w - - 0 1",
            "Reposition the Knight to c5! Take key squares and retreat to b3.",
            "Play Nc5 then Nb3.",
            listOf("e4c5", "e6d5", "c5b3")
        ),
        RawLesson(
            "Knight File Barrier",
            "8/8/3k4/8/8/3N4/4K3/8 w - - 0 1",
            "Deploy the Knight to f4! Guard e6 and maintain a solid wall with Ke2-e3.",
            "Play Nf4 then Ke3.",
            listOf("d3f4", "d6e5", "e2e3")
        ),
        RawLesson(
            "Knight Corner Domination",
            "8/8/8/8/8/3N4/4K3/7k w - - 0 1",
            "Confine the King to the h1 corner with Nd3-f2+! Step forward with Ke2-f3.",
            "Play Nf2+ then Kf3.",
            listOf("d3f2", "h1g1", "e2f3")
        ),
        RawLesson(
            "Knight Outpost Jump",
            "8/8/4k3/8/8/4N3/4K3/8 w - - 0 1",
            "Jump into the center with Ne3-d5! Support your Knight with Ke2-d3.",
            "Play Nd5 then Kd3.",
            listOf("e3d5", "e6d6", "e2d3")
        )
    )

    // === PASSED PAWNS POSITIONS ===
    private val passedPawnsPool = listOf(
        RawLesson(
            "Square of the Pawn (e-file)",
            "8/8/8/4P3/8/8/8/1k4K1 w - - 0 1",
            "The Black King is outside the square! Push e5-e6 and sprint to promotion with e6-e7.",
            "Push e6 then e7.",
            listOf("e5e6", "b1c2", "e6e7")
        ),
        RawLesson(
            "Square of the Pawn (d-file)",
            "8/8/8/3P4/8/8/8/1k4K1 w - - 0 1",
            "Outside the rule of the square: push d5-d6, Black King cannot catch up.",
            "Push d6 then d7.",
            listOf("d5d6", "b1c2", "d6d7")
        ),
        RawLesson(
            "Square of the Pawn (c-file)",
            "8/8/8/2P5/8/8/8/1k4K1 w - - 0 1",
            "Sprint down the c-file with c5-c6! Then advance c6-c7.",
            "Push c6 then c7.",
            listOf("c5c6", "b1c2", "c6c7")
        ),
        RawLesson(
            "Square of the Pawn (f-file)",
            "8/8/8/5P2/8/8/8/1k4K1 w - - 0 1",
            "Sprint down the f-file with f5-f6! Continue to promotion with f6-f7.",
            "Push f6 then f7.",
            listOf("f5f6", "b1c2", "f6f7")
        ),
        RawLesson(
            "Outside Passed Pawn Decoy",
            "8/8/8/P7/8/4k3/4P3/4K3 w - - 0 1",
            "Use the outside passed pawn as a decoy: advance a5-a6, draw the King away.",
            "Push a6 then a7.",
            listOf("a5a6", "e3d4", "a6a7")
        ),
        RawLesson(
            "Connected Passed Pawns",
            "8/8/8/3PP3/8/4k3/8/4K3 w - - 0 1",
            "Advance the connected pawns in tandem: e5-e6, then d5-d6.",
            "Push e6 then d6.",
            listOf("e5e6", "e3e4", "d5d6")
        )
    )

    // === ENDGAME STRATEGY POSITIONS ===
    private val endgameStrategyPool = listOf(
        RawLesson(
            "Mutual Zugzwang (Trebuchet)",
            "8/8/8/4p3/3kP3/8/4K3/8 w - - 0 1",
            "Defend your pawn with Ke2-f3! When Black retreats, step forward with Kf3-f2.",
            "Play Kf3 then Kf2.",
            listOf("e2f3", "d4d3", "f3f2")
        ),
        RawLesson(
            "Shoulder-Charging Infiltration",
            "8/8/8/8/3k4/4P3/8/3K4 w - - 0 1",
            "Use your King to shoulder-charge the opponent King: Kd1-d2, then Ke2.",
            "Play Kd2 then Ke2.",
            listOf("d1d2", "d4e4", "d2e2")
        ),
        RawLesson(
            "Triangulation Technique",
            "8/8/8/8/3K4/4P3/8/4k3 w - - 0 1",
            "Triangulate with your King to pass the move: Kd4-d3, then Kd3-e4.",
            "Step Kd3 then Ke4.",
            listOf("d4d3", "e1d1", "d3e4")
        ),
        RawLesson(
            "Center King Penetration",
            "8/8/8/3k4/8/3K4/4P3/8 w - - 0 1",
            "Penetrate the center with Kd3-e3! Follow up by marching forward with Ke3-f3.",
            "Play Ke3 then Kf3.",
            listOf("d3e3", "d5e5", "e3f3")
        ),
        RawLesson(
            "Rook Pawn Blockade",
            "8/8/8/P7/8/k7/8/K7 w - - 0 1",
            "Keep the passed pawn moving with a5-a6! When Black attacks, push a6-a7.",
            "Advance a6 then a7.",
            listOf("a5a6", "a3b4", "a6a7")
        ),
        RawLesson(
            "Passed Pawn Support",
            "8/8/8/4k3/8/4K3/8/8 w - - 0 1",
            "Seize the opposition with Ke3-f3! Step to g3 to control the key flank.",
            "Play Kf3 then Kg3.",
            listOf("e3f3", "e5f5", "f3g3")
        )
    )

    private val lessonCache = mutableMapOf<String, List<EndgameLesson>>()

    fun getLessons(category: String): List<EndgameLesson> = lessonCache.getOrPut(category) {
        val pool = when (category) {
            "King vs King" -> kingVsKingPool
            "King + Pawn" -> kingAndPawnPool
            "Rook Endgame" -> rookEndgamePool
            "Lucena" -> lucenaPool
            "Philidor" -> philidorPool
            "Queen Endgame" -> queenEndgamePool
            "Bishop Endgame" -> bishopEndgamePool
            "Knight Endgame" -> knightEndgamePool
            "Passed Pawns" -> passedPawnsPool
            "Endgame Strategy" -> endgameStrategyPool
            else -> kingVsKingPool
        }

        val seenFens = mutableSetOf<String>()
        val catPrefix = category.replace("+", "p").replace(" ", "_").lowercase()
        (1..100).map { level ->
            val tier = when {
                level <= 35 -> "Easy"
                level <= 70 -> "Moderate"
                else -> "Hard"
            }
            val baseIndex = (level - 1) % pool.size
            val varIndex = (level - 1) / pool.size
            val raw = pool[baseIndex]
            var uniqueFen = PuzzleVariationHelper.getVariation(raw.fen, raw.moves, varIndex)
            if (uniqueFen in seenFens) {
                val variations = PuzzleVariationHelper.getUniqueVariations(raw.fen, raw.moves, 25)
                uniqueFen = variations.firstOrNull { it !in seenFens } ?: uniqueFen
            }
            seenFens.add(uniqueFen)

            EndgameLesson(
                id = "e_${catPrefix}_$level",
                title = "${raw.subtitle} • Level $level ($tier)",
                category = category,
                fen = uniqueFen,
                explanation = "Level $level ($tier difficulty): ${raw.explanation}",
                hint = raw.hint,
                solutionMoves = raw.moves
            )
        }
    }
}
