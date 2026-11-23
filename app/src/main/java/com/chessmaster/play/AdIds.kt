package com.chessmaster.play

import android.content.Context

/**
 * Official AdMob Unit IDs for the app.
 * App ID: ca-app-pub-8940048544519183~6773865241
 */
object AdIds {
    const val APP_ID = "ca-app-pub-8940048544519183~6773865241"
    const val BANNER = "ca-app-pub-8940048544519183/3068957289"
    const val INTERSTITIAL = "ca-app-pub-8940048544519183/3260528972"
    const val REWARDED = "ca-app-pub-8940048544519183/2330590681"
    const val REWARDED_INTERSTITIAL = "ca-app-pub-8940048544519183/4409959112"
    const val APP_OPEN = "ca-app-pub-8940048544519183/4566530926"

    fun banner(context: Context? = null): String = BANNER
    fun interstitial(context: Context? = null): String = INTERSTITIAL
    fun rewarded(context: Context? = null): String = REWARDED
    fun rewardedInterstitial(context: Context? = null): String = REWARDED_INTERSTITIAL
    fun appOpen(context: Context? = null): String = APP_OPEN
}
