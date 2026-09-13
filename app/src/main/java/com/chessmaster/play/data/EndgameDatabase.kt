package com.chessmaster.play.data

import com.chessmaster.play.model.EndgameLesson

object EndgameDatabase {

    fun getLessons(category: String): List<EndgameLesson> = when (category) {
        "King vs King" -> KingVsKingDatabase.getLessons()
        "King + Pawn" -> KingAndPawnDatabase.getLessons()
        "Rook Endgame" -> RookEndgameDatabase.getLessons()
        "Lucena" -> LucenaDatabase.getLessons()
        "Philidor" -> PhilidorDatabase.getLessons()
        "Queen Endgame" -> QueenEndgameDatabase.getLessons()
        "Bishop Endgame" -> BishopEndgameDatabase.getLessons()
        "Knight Endgame" -> KnightEndgameDatabase.getLessons()
        "Passed Pawns" -> PassedPawnsDatabase.getLessons()
        "Endgame Strategy" -> EndgameStrategyDatabase.getLessons()
        else -> KingVsKingDatabase.getLessons()
    }
}
