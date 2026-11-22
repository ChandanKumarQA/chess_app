package com.chessmaster.play.data

import android.content.Context
import android.content.SharedPreferences

object LocalLeaderboardManager {
    private const val PREFS_NAME = "ChessVerseLeaderboard"
    
    // Puzzle Rush Stats
    private const val PR_HIGH_SCORE = "PR_HIGH_SCORE"
    private const val PR_HIGH_COMBO = "PR_HIGH_COMBO"
    private const val PR_PUZZLES_SOLVED = "PR_PUZZLES_SOLVED"
    
    // Survival Mode Stats
    private const val SURV_HIGH_SCORE = "SURV_HIGH_SCORE"
    private const val SURV_PUZZLES_SOLVED = "SURV_PUZZLES_SOLVED"
    private const val SURV_HIGH_COMBO = "SURV_HIGH_COMBO"

    // General Stats
    private const val TOTAL_COINS = "TOTAL_COINS"
    private const val TOTAL_XP = "TOTAL_XP"
    private const val PUZZLE_HIGHEST_LEVEL = "PUZZLE_HIGHEST_LEVEL"
    private const val REMAINING_HINTS = "REMAINING_HINTS"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun savePuzzleRushStats(context: Context, score: Int, combo: Int, puzzlesSolved: Int) {
        val prefs = getPrefs(context)
        val currentHighScore = prefs.getInt(PR_HIGH_SCORE, 0)
        val currentHighCombo = prefs.getInt(PR_HIGH_COMBO, 0)
        
        with(prefs.edit()) {
            if (score > currentHighScore) putInt(PR_HIGH_SCORE, score)
            if (combo > currentHighCombo) putInt(PR_HIGH_COMBO, combo)
            val currentSolved = prefs.getInt(PR_PUZZLES_SOLVED, 0)
            putInt(PR_PUZZLES_SOLVED, currentSolved + puzzlesSolved)
            apply()
        }
    }

    fun saveSurvivalStats(context: Context, score: Int, combo: Int, puzzlesSolved: Int) {
        val prefs = getPrefs(context)
        val currentHighScore = prefs.getInt(SURV_HIGH_SCORE, 0)
        val currentHighCombo = prefs.getInt(SURV_HIGH_COMBO, 0)

        with(prefs.edit()) {
            if (score > currentHighScore) putInt(SURV_HIGH_SCORE, score)
            if (combo > currentHighCombo) putInt(SURV_HIGH_COMBO, combo)
            val currentSolved = prefs.getInt(SURV_PUZZLES_SOLVED, 0)
            putInt(SURV_PUZZLES_SOLVED, currentSolved + puzzlesSolved)
            apply()
        }
    }

    fun addRewards(context: Context, coins: Int, xp: Int) {
        val prefs = getPrefs(context)
        val currentCoins = prefs.getInt(TOTAL_COINS, 0)
        val currentXp = prefs.getInt(TOTAL_XP, 0)
        
        with(prefs.edit()) {
            putInt(TOTAL_COINS, currentCoins + coins)
            putInt(TOTAL_XP, currentXp + xp)
            apply()
        }
    }

    fun getPuzzleRushHighScore(context: Context): Int = getPrefs(context).getInt(PR_HIGH_SCORE, 0)
    fun getSurvivalHighScore(context: Context): Int = getPrefs(context).getInt(SURV_HIGH_SCORE, 0)
    fun getTotalCoins(context: Context): Int = getPrefs(context).getInt(TOTAL_COINS, 0)
    fun getTotalXp(context: Context): Int = getPrefs(context).getInt(TOTAL_XP, 0)
    
    fun getHighestUnlockedPuzzleLevel(context: Context): Int {
        return getPrefs(context).getInt(PUZZLE_HIGHEST_LEVEL, 1)
    }

    const val MAX_PUZZLE_LEVEL = 100

    // Highest value is MAX_PUZZLE_LEVEL + 1 ("all levels completed"), never beyond.
    fun unlockNextPuzzleLevel(context: Context, currentLevel: Int) {
        val prefs = getPrefs(context)
        val highest = getHighestUnlockedPuzzleLevel(context)
        if (currentLevel >= highest) {
            prefs.edit().putInt(PUZZLE_HIGHEST_LEVEL, (currentLevel + 1).coerceAtMost(MAX_PUZZLE_LEVEL + 1)).commit()
        }
    }

    /**
     * Gives XP + coins for a puzzle only the FIRST time it is solved (no farming by replaying).
     * Returns true if the reward was granted now.
     */
    fun claimPuzzleReward(context: Context, puzzleId: String, xp: Int, coins: Int): Boolean {
        val prefs = getPrefs(context)
        val key = "puzzle_rewarded_$puzzleId"
        if (prefs.getBoolean(key, false)) return false
        prefs.edit()
            .putBoolean(key, true)
            .putInt(TOTAL_COINS, prefs.getInt(TOTAL_COINS, 0) + coins)
            .putInt(TOTAL_XP, prefs.getInt(TOTAL_XP, 0) + xp)
            .apply()
        return true
    }

    fun getHighestUnlockedThemeLevel(context: Context, theme: String): Int {
        val cleanTheme = theme.substringBefore(" -").trim().replace(" ", "_").lowercase()
        return getPrefs(context).getInt("theme_level_$cleanTheme", 1)
    }

    fun unlockNextThemeLevel(context: Context, theme: String, currentLevel: Int) {
        val cleanTheme = theme.substringBefore(" -").trim().replace(" ", "_").lowercase()
        val prefs = getPrefs(context)
        val highest = getHighestUnlockedThemeLevel(context, cleanTheme)
        if (currentLevel >= highest) {
            prefs.edit().putInt("theme_level_$cleanTheme", currentLevel + 1).commit()
        }
    }

    // ---------- Daily puzzle + streak ----------
    private const val STREAK_LAST_DAY = "streak_last_day"
    private const val STREAK_COUNT = "streak_count"
    private const val STREAK_BEST = "streak_best"
    private const val DAILY_SOLVED_DAY = "daily_solved_day"

    /** Days since 1970 in the device's local time zone. */
    fun todayEpochDay(): Int {
        val now = System.currentTimeMillis()
        val offset = java.util.TimeZone.getDefault().getOffset(now)
        return Math.floorDiv(now + offset, 86_400_000L).toInt()
    }

    /** Current streak; 0 if the player missed yesterday. */
    fun getCurrentStreak(context: Context): Int {
        val prefs = getPrefs(context)
        val last = prefs.getInt(STREAK_LAST_DAY, -1)
        return if (last >= todayEpochDay() - 1) prefs.getInt(STREAK_COUNT, 0) else 0
    }

    fun getBestStreak(context: Context): Int = getPrefs(context).getInt(STREAK_BEST, 0)

    /** Call when the player solves any puzzle. Returns the updated streak (days in a row). */
    fun recordPuzzleDay(context: Context): Int {
        val prefs = getPrefs(context)
        val today = todayEpochDay()
        val last = prefs.getInt(STREAK_LAST_DAY, -1)
        if (last == today) return prefs.getInt(STREAK_COUNT, 1)
        val streak = if (last == today - 1) prefs.getInt(STREAK_COUNT, 0) + 1 else 1
        prefs.edit()
            .putInt(STREAK_LAST_DAY, today)
            .putInt(STREAK_COUNT, streak)
            .putInt(STREAK_BEST, maxOf(streak, prefs.getInt(STREAK_BEST, 0)))
            .apply()
        return streak
    }

    fun isDailySolvedToday(context: Context): Boolean =
        getPrefs(context).getInt(DAILY_SOLVED_DAY, -1) == todayEpochDay()

    /** Returns true only the first time today's daily puzzle is solved. */
    fun markDailySolved(context: Context): Boolean {
        if (isDailySolvedToday(context)) return false
        getPrefs(context).edit().putInt(DAILY_SOLVED_DAY, todayEpochDay()).apply()
        return true
    }

    fun getRemainingHints(context: Context): Int = getPrefs(context).getInt(REMAINING_HINTS, 10)
    
    fun setRemainingHints(context: Context, hints: Int) {
        getPrefs(context).edit().putInt(REMAINING_HINTS, hints).apply()
    }

    fun getHighestUnlockedSurvivalLevel(context: Context): Int {
        return getPrefs(context).getInt("survival_highest_level", 1)
    }

    fun unlockNextSurvivalLevel(context: Context, currentLevel: Int) {
        val prefs = getPrefs(context)
        val highest = getHighestUnlockedSurvivalLevel(context)
        if (currentLevel >= highest) {
            prefs.edit().putInt("survival_highest_level", currentLevel + 1).apply()
        }
    }
}
