package com.chessmaster.play

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.android.play.core.review.ReviewManagerFactory

/**
 * Manages Google Play In-App Review prompts strictly following Google Play policies.
 * 
 * Prompts are only triggered at "Peak Positive Moments":
 * - Solving a daily puzzle
 * - Achieving a 3+ day puzzle streak
 * - Winning against a chess bot
 * - Completing a tactical milestone
 * 
 * Frequency is capped to prevent spamming the user.
 */
object InAppReviewManager {
    private const val TAG = "InAppReviewManager"
    private const val PREFS_NAME = "chess_in_app_review_prefs"
    private const val KEY_LAST_REVIEW_TIME = "last_review_prompt_time"
    private const val KEY_POSITIVE_ACTIONS_COUNT = "positive_actions_count"
    
    // Minimum 5 positive actions (wins, streaks, puzzles) before first prompt
    private const val MIN_POSITIVE_ACTIONS = 4
    // Minimum 7 days between prompts
    private const val MIN_INTERVAL_MS = 7L * 24L * 60L * 60L * 1000L

    private fun getPrefs(context: Context): SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    /**
     * Records a positive user milestone (e.g., puzzle solved, bot defeated)
     * and triggers the in-app review flow if criteria are met.
     */
    fun recordMilestoneAndRequestReview(activity: Activity?) {
        if (activity == null || activity.isFinishing || activity.isDestroyed) return
        
        val prefs = getPrefs(activity)
        val currentCount = prefs.getInt(KEY_POSITIVE_ACTIONS_COUNT, 0) + 1
        prefs.edit().putInt(KEY_POSITIVE_ACTIONS_COUNT, currentCount).apply()

        val lastPromptTime = prefs.getLong(KEY_LAST_REVIEW_TIME, 0L)
        val now = System.currentTimeMillis()

        if (currentCount >= MIN_POSITIVE_ACTIONS && (now - lastPromptTime) > MIN_INTERVAL_MS) {
            launchReviewFlow(activity)
        }
    }

    /**
     * Directly triggers review flow if enough time has elapsed.
     */
    fun launchReviewFlow(activity: Activity) {
        if (activity.isFinishing || activity.isDestroyed) return

        val prefs = getPrefs(activity)
        val now = System.currentTimeMillis()
        val lastPromptTime = prefs.getLong(KEY_LAST_REVIEW_TIME, 0L)

        if ((now - lastPromptTime) < MIN_INTERVAL_MS) {
            Log.d(TAG, "Review prompt skipped: interval not met.")
            return
        }

        try {
            val manager = ReviewManagerFactory.create(activity)
            val request = manager.requestReviewFlow()
            request.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val reviewInfo = task.result
                    manager.launchReviewFlow(activity, reviewInfo).addOnCompleteListener {
                        Log.d(TAG, "In-app review flow finished.")
                        prefs.edit()
                            .putLong(KEY_LAST_REVIEW_TIME, System.currentTimeMillis())
                            .putInt(KEY_POSITIVE_ACTIONS_COUNT, 0)
                            .apply()
                    }
                } else {
                    Log.d(TAG, "Review flow request failed: ${task.exception?.message}")
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error launching in-app review: ${e.message}")
        }
    }
}
