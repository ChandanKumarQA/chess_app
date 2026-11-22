package com.chessmaster.play

import android.content.Context
import android.content.pm.ApplicationInfo

/**
 * Picks real AdMob unit IDs for release builds and Google's official TEST IDs for debug builds,
 * so you never click/impress your own live ads while developing (that can get the AdMob account banned).
 */
object AdIds {
    fun isDebug(context: Context): Boolean =
        (context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0

    fun banner(context: Context) =
        if (isDebug(context)) "ca-app-pub-3940256099942544/6300978111" else "ca-app-pub-8940048544519183/3068957289"

    fun interstitial(context: Context) =
        if (isDebug(context)) "ca-app-pub-3940256099942544/1033173712" else "ca-app-pub-8940048544519183/3260528972"

    fun rewarded(context: Context) =
        if (isDebug(context)) "ca-app-pub-3940256099942544/5224354917" else "ca-app-pub-8940048544519183/2330590681"

    fun rewardedInterstitial(context: Context) =
        if (isDebug(context)) "ca-app-pub-3940256099942544/5354046379" else "ca-app-pub-8940048544519183/4409959112"

    fun appOpen(context: Context) =
        if (isDebug(context)) "ca-app-pub-3940256099942544/9257395921" else "ca-app-pub-8940048544519183/4566530926"
}
