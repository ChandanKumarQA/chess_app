package com.chessmaster.play.data

import com.chessmaster.play.model.OpeningTrap

object OpeningTrapsDatabase {

    private val traps = listOf(
        OpeningTrap(
            id = "ot_scholar_1",
            name = "Scholar's Mate",
            description = "A quick 4-move checkmate targeting f7.",
            moveSequence = listOf("e2e4", "e7e5", "d1h5", "b8c6", "f1c4", "g8f6", "h5f7"),
            commonMistakeIndex = 5,
            explanation = "Nf6 is a mistake when Bc4 and Qh5 are attacking f7."
        ),
        OpeningTrap(
            id = "ot_fried_1",
            name = "Fried Liver",
            description = "An aggressive attack starting from the Italian Game.",
            moveSequence = listOf("e2e4", "e7e5", "g1f3", "b8c6", "f1c4", "g8f6", "f3g5", "d7d5", "e4d5", "f6d5", "g5f7"),
            commonMistakeIndex = 9,
            explanation = "Nxd5 allows the powerful Knight sacrifice on f7."
        ),
        OpeningTrap(
            id = "ot_legal_1",
            name = "Legal's Trap",
            description = "A beautiful Queen sacrifice.",
            moveSequence = listOf("e2e4", "e7e5", "g1f3", "b8c6", "f1c4", "d7d6", "b1c3", "c8g4", "h2h3", "g4h5", "f3e5", "h5d1", "c4f7", "e8e7", "c3d5"),
            commonMistakeIndex = 11,
            explanation = "Taking the Queen leads to a forced mate."
        ),
        OpeningTrap(
            id = "ot_noah_1",
            name = "Noah's Ark",
            description = "Trap the bishop.",
            moveSequence = listOf("e2e4", "e7e5", "g1f3", "b8c6", "f1b5", "a7a6", "b5a4", "d7d6", "d2d4", "b7b5", "a4b3", "c6d4", "f3d4", "e5d4", "d1d4", "c7c5"),
            commonMistakeIndex = 5,
            explanation = "Trap the bishop."
        ),
        OpeningTrap(
            id = "ot_greek_1",
            name = "Greek Gift",
            description = "Sacrifice on h7.",
            moveSequence = listOf("e2e4", "e7e6", "d2d4", "d7d5", "b1c3", "g8f6", "e4e5", "f6d7", "f1d3", "c7c5", "g1f3", "b8c6", "e1g1", "c5d4", "c3b5", "d7e5", "f3e5", "c6e5", "c1f4", "e5d3", "b5c7"),
            commonMistakeIndex = 5,
            explanation = "Sacrifice on h7."
        ),
        OpeningTrap(
            id = "ot_stafford_1",
            name = "Stafford Gambit",
            description = "Tricky knight moves.",
            moveSequence = listOf("e2e4", "e7e5", "g1f3", "g8f6", "f3e5", "b8c6", "e5c6", "d7c6"),
            commonMistakeIndex = 5,
            explanation = "Develop the pieces."
        ),
        OpeningTrap(
            id = "ot_vienna_1",
            name = "Vienna Trap",
            description = "Copycat trap.",
            moveSequence = listOf("e2e4", "e7e5", "b1c3", "g8f6", "f1c4", "f6e4", "c3e4", "d7d5"),
            commonMistakeIndex = 5,
            explanation = "Fork the pieces."
        ),
        OpeningTrap(
            id = "ot_sicilian_1",
            name = "Sicilian Smith-Morra",
            description = "Smith-Morra trick.",
            moveSequence = listOf("e2e4", "c7c5", "d2d4", "c5d4", "c2c3", "d4c3", "b1c3", "b8c6", "g1f3", "d7d6", "f1c4", "e7e6", "e1g1", "g8f6", "d1e2", "f8e7", "f1d1", "e6e5", "c1g5", "c8g4"),
            commonMistakeIndex = 5,
            explanation = "Watch out for d5."
        ),
        OpeningTrap(
            id = "ot_elephant_1",
            name = "Elephant Trap",
            description = "Black traps White into taking on d5 in the QGD.",
            moveSequence = listOf("d2d4", "d7d5", "c2c4", "e7e6", "b1c3", "g8f6", "c1g5", "b8d7", "c4d5", "e6d5", "c3d5", "f6d5", "g5d8", "f8b4", "d1d2", "b4d2", "e1d2", "e8d8"),
            commonMistakeIndex = 9,
            explanation = "Bxd8 allows the devastating Bb4+ check."
        ),
        OpeningTrap(
            id = "ot_blackburne_1",
            name = "Blackburne Shilling Gambit",
            description = "A sneaky knight move sets up a smothered mate.",
            moveSequence = listOf("e2e4", "e7e5", "g1f3", "b8c6", "f1c4", "c6d4", "f3e5", "d8g5", "e5f7", "g5g2", "h1f1", "g2e4", "c4e2", "d4f3"),
            commonMistakeIndex = 7,
            explanation = "Nd4 leaves e5 as poisoned bait."
        ),
        OpeningTrap(
            id = "ot_fishing_1",
            name = "Fishing Pole Trap",
            description = "Opening the h-file with a piece sacrifice.",
            moveSequence = listOf("e2e4", "e7e5", "g1f3", "b8c6", "f1b5", "g8f6", "e1g1", "f6g4", "h2h3", "h7h5", "h3g4", "h5g4", "f3e1", "d8h4"),
            commonMistakeIndex = 7,
            explanation = "Taking on g4 opens the lethal h-file."
        ),
        OpeningTrap(
            id = "ot_halosar_1",
            name = "Halosar Trap",
            description = "Blackmar-Diemer gambit tactical surprise.",
            moveSequence = listOf("d2d4", "d7d5", "e2e4", "d5e4", "b1c3", "g8f6", "f2f3", "e4f3", "d1f3", "d8d4", "c1e3", "d4b4", "e1c1", "c8g4", "c3b5"),
            commonMistakeIndex = 7,
            explanation = "Nb5 creates an unstoppable mate threat on c7."
        ),
        OpeningTrap(
            id = "ot_lasker_1",
            name = "Lasker Trap",
            description = "Historic underpromotion in the Albin Countergambit.",
            moveSequence = listOf("d2d4", "d7d5", "c2c4", "e7e5", "d4e5", "d5d4", "e2e3", "f8b4", "c1d2", "d4e3", "d2b4", "e3f2", "e1e2", "f2g1n"),
            commonMistakeIndex = 7,
            explanation = "Promoting to Knight with check wins the Queen."
        ),
        OpeningTrap(
            id = "ot_budapest_1",
            name = "Budapest Trap",
            description = "Smothered checkmate in the Budapest Gambit.",
            moveSequence = listOf("d2d4", "g8f6", "c2c4", "e7e5", "d4e5", "f6g4", "c1f4", "b8c6", "g1f3", "f8b4", "b1d2", "d8e7", "a2a3", "g4e5", "a3b4", "e5d3"),
            commonMistakeIndex = 7,
            explanation = "Nd3 checkmate because the pawn is pinned!"
        ),
        OpeningTrap(
            id = "ot_mortimer_1",
            name = "Mortimer Trap",
            description = "Deceptive Knight maneuver in the Berlin Defense.",
            moveSequence = listOf("e2e4", "e7e5", "g1f3", "b8c6", "f1b5", "g8f6", "d2d3", "c6e7", "f3e5", "c7c6", "b5a4", "d8a5"),
            commonMistakeIndex = 6,
            explanation = "Qa5+ forks the King on e1 and Knight on e5."
        ),
        OpeningTrap(
            id = "ot_rubinstein_1",
            name = "Rubinstein Trap",
            description = "A classic Queen's Gambit Declined maneuver.",
            moveSequence = listOf("d2d4", "d7d5", "c2c4", "e7e6", "b1c3", "g8f6", "c1g5", "b8d7", "e2e3", "c7c6", "g1f3", "d8a5", "f3d2", "f8b4", "d1c2", "e8g8", "g5f4"),
            commonMistakeIndex = 8,
            explanation = "Sharp positional trap in the Cambridge Springs."
        ),
        OpeningTrap(
            id = "ot_magnus_smith_1",
            name = "Magnus Smith Trap",
            description = "A sharp tactic in the Sicilian Sozin punishing an early g6.",
            moveSequence = listOf("e2e4", "c7c5", "g1f3", "d7d6", "d2d4", "c5d4", "f3d4", "g8f6", "b1c3", "b8c6", "f1c4", "g7g6", "d4c6", "b7c6", "e4e5", "d6e5", "c4f7", "e8f7", "d1d8"),
            commonMistakeIndex = 15,
            explanation = "8... dxe5 allows 9. Bxf7+ winning the Queen on d8."
        ),
        OpeningTrap(
            id = "ot_tarrasch_1",
            name = "Tarrasch Trap",
            description = "Exploiting the pinned d-pawn in the Open Ruy Lopez.",
            moveSequence = listOf("e2e4", "e7e5", "g1f3", "b8c6", "f1b5", "a7a6", "b5a4", "g8f6", "e1g1", "f6e4", "d2d4", "b7b5", "a4b3", "d7d5", "d4e5", "c8e6", "c2c3", "f8e7", "f1e1", "e8g8", "f3d4", "d8d7", "d4e6", "f7e6", "e1e4"),
            commonMistakeIndex = 23,
            explanation = "13. Rxe4 wins a piece because Black's d-pawn is pinned to the Queen on d7."
        )
    )

    fun getAllTraps(): List<OpeningTrap> = traps

    fun getTrapById(id: String): OpeningTrap? = traps.find { it.id == id }
}
