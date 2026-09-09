package com.chessmaster.play.data

import com.chessmaster.play.model.Puzzle
import kotlin.random.Random

object PuzzleRushDatabase {

    fun getRandomPuzzles(count: Int): List<Puzzle> {
        val allAvailable = mutableListOf<Puzzle>()
        allAvailable.addAll(PuzzleRepository.levelPuzzles)
        for (cat in TacticalDatabase.getAllCategories()) {
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
}
