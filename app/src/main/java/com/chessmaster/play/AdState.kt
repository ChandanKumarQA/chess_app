package com.chessmaster.play

/**
 * Global coordinator for all AdMob ads across the application.
 * Prevents ad collision, double playback, background audio execution,
 * and suppresses AppOpenAds immediately after Interstitial/Rewarded ads.
 */
object AdState {
    @Volatile
    var isFullScreenAdShowing: Boolean = false

    @Volatile
    var lastAdDismissedTimestamp: Long = 0L

    fun notifyAdShowing() {
        isFullScreenAdShowing = true
    }

    fun notifyAdDismissed() {
        isFullScreenAdShowing = false
        lastAdDismissedTimestamp = System.currentTimeMillis()
    }

    fun canShowAppOpenAd(): Boolean {
        if (isFullScreenAdShowing) return false
        val timeSinceLastAd = System.currentTimeMillis() - lastAdDismissedTimestamp
        // Suppress AppOpenAd if an ad was dismissed within the last 6 seconds
        return timeSinceLastAd > 6000L
    }
}
