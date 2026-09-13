package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle

object TacticalDatabase {

    fun getAllCategories(): List<String> = listOf(
        "Mate in 1", "Mate in 2", "Fork", "Pin", "Skewer",
        "Double Attack", "Discovered Attack", "Smothered Mate",
        "Sacrifice", "Attraction", "Deflection", "Clearance", "Winning Material"
    )

    fun getPuzzles(category: String): List<Puzzle> = when (category) {
        "Mate in 1" -> MateIn1Database.getPuzzles()
        "Mate in 2" -> MateIn2Database.getPuzzles()
        "Fork" -> ForkDatabase.getPuzzles()
        "Pin" -> PinDatabase.getPuzzles()
        "Skewer" -> SkewerDatabase.getPuzzles()
        "Double Attack" -> DoubleAttackDatabase.getPuzzles()
        "Discovered Attack" -> DiscoveredAttackDatabase.getPuzzles()
        "Smothered Mate" -> SmotheredMateDatabase.getPuzzles()
        "Sacrifice" -> SacrificeDatabase.getPuzzles()
        "Attraction" -> AttractionDatabase.getPuzzles()
        "Deflection" -> DeflectionDatabase.getPuzzles()
        "Clearance" -> ClearanceDatabase.getPuzzles()
        "Winning Material" -> WinningMaterialDatabase.getPuzzles()
        else -> ForkDatabase.getPuzzles()
    }
}