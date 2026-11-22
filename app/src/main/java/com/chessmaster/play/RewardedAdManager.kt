package com.chessmaster.play

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

private const val TAG = "RewardedAdManager"

/**
 * Manages Rewarded Ads with automatic fallback from AdMob to Google Ad Manager (GAM).
 *
 * Supports single rewarded ads as well as consecutive/back-to-back rewarded ads (e.g., 2 ads for 10 hints).
 *
 * GAM Ad Unit: /23374332010/chess_master_rewarded
 * Supported GAM Creative Sizes:
 * - (320, 480) - Mobile Portrait
 * - (480, 320) - Mobile Landscape
 * - (768, 1024) - Tablet Portrait
 * - (1024, 768) - Tablet Landscape
 */
class RewardedAdManager(
    private val context: Context,
    private val adMobAdUnitId: String = AdIds.rewarded(context),
    private val gamAdUnitId: String = DEFAULT_GAM_REWARDED_ID
) {
    companion object {
        const val DEFAULT_ADMOB_REWARDED_ID = "ca-app-pub-8940048544519183/2330590681" // REAL ID
        const val DEFAULT_GAM_REWARDED_ID = "/23374332010/chess_master_rewarded"
    }

    private var rewardedAd: RewardedAd? = null
    private var isLoading = false
    private val loadCallbacks = mutableListOf<(Boolean) -> Unit>()

    fun loadAd(onLoaded: ((Boolean) -> Unit)? = null) {
        if (rewardedAd == null && !ConsentManager.canRequestAds(context)) {
            onLoaded?.invoke(false)
            return
        }
        if (rewardedAd != null) {
            onLoaded?.invoke(true)
            return
        }
        if (onLoaded != null) {
            loadCallbacks.add(onLoaded)
        }
        if (isLoading) {
            return
        }
        isLoading = true
        loadAdMob()
    }

    private fun notifyLoadResult(success: Boolean) {
        isLoading = false
        val pending = ArrayList(loadCallbacks)
        loadCallbacks.clear()
        pending.forEach { it.invoke(success) }
    }

    private fun loadAdMob() {
        Log.d(TAG, "Attempting to load AdMob Rewarded Ad: $adMobAdUnitId")
        val adRequest = AdRequest.Builder().build()

        RewardedAd.load(
            context,
            adMobAdUnitId,
            adRequest,
            object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.w(TAG, "AdMob Rewarded Ad failed to load: ${adError.message} (Code: ${adError.code}). Falling back to GAM...")
                    loadGamFallback()
                }

                override fun onAdLoaded(ad: RewardedAd) {
                    Log.d(TAG, "AdMob Rewarded Ad loaded successfully ($adMobAdUnitId)")
                    rewardedAd = ad
                    notifyLoadResult(true)
                }
            }
        )
    }

    private fun loadGamFallback() {
        Log.d(TAG, "Attempting to load GAM Rewarded Ad fallback: $gamAdUnitId")
        val gamRequest = AdManagerAdRequest.Builder().build()

        RewardedAd.load(
            context,
            gamAdUnitId,
            gamRequest,
            object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(gamError: LoadAdError) {
                    Log.e(TAG, "GAM Rewarded Ad also failed to load: ${gamError.message} (Code: ${gamError.code})")
                    rewardedAd = null
                    notifyLoadResult(false)
                }

                override fun onAdLoaded(ad: RewardedAd) {
                    Log.d(TAG, "GAM Rewarded Ad loaded successfully ($gamAdUnitId)")
                    rewardedAd = ad
                    notifyLoadResult(true)
                }
            }
        )
    }

    /**
     * Show a single rewarded ad.
     */
    fun showAd(activity: Activity, onRewardEarned: (Int) -> Unit) {
        if (activity.isFinishing || activity.isDestroyed || AdState.isFullScreenAdShowing) {
            Log.d(TAG, "Cannot show ad: Activity invalid or another ad is showing.")
            return
        }

        if (rewardedAd != null) {
            val adToShow = rewardedAd
            rewardedAd = null
            AdState.notifyAdShowing()

            var userEarned = false

            adToShow?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d(TAG, "Rewarded ad was dismissed.")
                    AdState.notifyAdDismissed()
                    loadAd() // Preload the next ad
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    Log.e(TAG, "Rewarded ad failed to show: ${adError.message}")
                    AdState.notifyAdDismissed()
                    loadAd()
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d(TAG, "Rewarded ad showed fullscreen content. Preloading next ad in background...")
                    loadAd() // Preload next ad immediately in the background
                }
            }

            adToShow?.show(activity) { rewardItem ->
                userEarned = true
                onRewardEarned(rewardItem.amount)
                Log.d(TAG, "User earned reward: ${rewardItem.amount} ${rewardItem.type}")
            }
        } else {
            Log.d(TAG, "The rewarded ad wasn't ready yet.")
            Toast.makeText(
                activity,
                "Ad is loading, please try again in a few seconds...",
                Toast.LENGTH_SHORT
            ).show()
            loadAd()
        }
    }

    /**
     * Shows consecutive back-to-back rewarded ads (e.g. 2 ads in a sequence for 10 hints).
     *
     * @param targetAdCount Number of ads to display consecutively (defaults to 2).
     * @param onSingleAdWatched Invoked each time an individual ad is completed (1-indexed).
     * @param onAllAdsCompleted Invoked once all target ads have been watched.
     */
    fun showConsecutiveAds(
        activity: Activity,
        targetAdCount: Int = 2,
        onSingleAdWatched: (adIndex: Int) -> Unit = {},
        onAllAdsCompleted: () -> Unit
    ) {
        if (activity.isFinishing || activity.isDestroyed || AdState.isFullScreenAdShowing) {
            Log.d(TAG, "Cannot show consecutive ads: Activity invalid or another ad is showing.")
            return
        }

        var adsCompleted = 0

        fun playNext() {
            if (adsCompleted >= targetAdCount) {
                onAllAdsCompleted()
                return
            }

            if (rewardedAd != null) {
                val adToShow = rewardedAd
                rewardedAd = null
                AdState.notifyAdShowing()

                var earnedThisAd = false

                adToShow?.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        Log.d(TAG, "Rewarded ad dismissed. Completed: ${adsCompleted + (if (earnedThisAd) 1 else 0)} of $targetAdCount")
                        AdState.notifyAdDismissed()

                        if (earnedThisAd) {
                            adsCompleted++
                            onSingleAdWatched(adsCompleted)

                            if (adsCompleted < targetAdCount) {
                                // Check if next ad is already in cache
                                if (rewardedAd != null) {
                                    playNext()
                                } else {
                                    Toast.makeText(
                                        activity,
                                        "Ad $adsCompleted of $targetAdCount completed! Loading next ad...",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    loadAd { loaded ->
                                        if (loaded && !activity.isFinishing && !activity.isDestroyed) {
                                            playNext()
                                        } else {
                                            // Fallback if 2nd ad cannot load
                                            onAllAdsCompleted()
                                        }
                                    }
                                }
                            } else {
                                onAllAdsCompleted()
                                loadAd() // Preload for next time
                            }
                        } else {
                            Toast.makeText(
                                activity,
                                "Ad closed early. Watch full ad to earn rewards.",
                                Toast.LENGTH_SHORT
                            ).show()
                            loadAd()
                        }
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        Log.e(TAG, "Consecutive rewarded ad failed to show: ${adError.message}")
                        AdState.notifyAdDismissed()
                        loadAd()
                    }

                    override fun onAdShowedFullScreenContent() {
                        Log.d(TAG, "Rewarded ad $adsCompleted showed fullscreen. Preloading next ad in background...")
                        loadAd() // Preload next ad right away while user watches
                    }
                }

                adToShow?.show(activity) { rewardItem ->
                    earnedThisAd = true
                    Log.d(TAG, "User earned reward on ad index $adsCompleted: ${rewardItem.amount}")
                }
            } else {
                Toast.makeText(
                    activity,
                    "Ad is loading, please try again in a few seconds...",
                    Toast.LENGTH_SHORT
                ).show()
                loadAd()
            }
        }

        playNext()
    }
}
