package com.chessmaster.play

import android.app.Activity
import android.content.Context
import android.util.Log
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
 * GAM Ad Unit: /23374332010/chess_master_rewarded
 * Supported GAM Creative Sizes:
 * - (320, 480) - Mobile Portrait
 * - (480, 320) - Mobile Landscape
 * - (768, 1024) - Tablet Portrait
 * - (1024, 768) - Tablet Landscape
 */
class RewardedAdManager(
    private val context: Context,
    private val adMobAdUnitId: String = DEFAULT_ADMOB_REWARDED_ID,
    private val gamAdUnitId: String = DEFAULT_GAM_REWARDED_ID
) {
    companion object {
        const val DEFAULT_ADMOB_REWARDED_ID = "ca-app-pub-8940048544519183/4897026421" // REAL ID
        const val DEFAULT_GAM_REWARDED_ID = "/23374332010/chess_master_rewarded"
    }

    private var rewardedAd: RewardedAd? = null
    private var isLoading = false

    fun loadAd() {
        if (rewardedAd != null || isLoading) {
            return
        }
        isLoading = true
        loadAdMob()
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
                    isLoading = false
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
                    isLoading = false
                }

                override fun onAdLoaded(ad: RewardedAd) {
                    Log.d(TAG, "GAM Rewarded Ad loaded successfully ($gamAdUnitId)")
                    rewardedAd = ad
                    isLoading = false
                }
            }
        )
    }

    fun showAd(activity: Activity, onRewardEarned: (Int) -> Unit) {
        if (activity.isFinishing || activity.isDestroyed || AdState.isFullScreenAdShowing) {
            Log.d(TAG, "Cannot show ad: Activity invalid or another ad is showing.")
            return
        }

        if (rewardedAd != null) {
            val adToShow = rewardedAd
            rewardedAd = null
            AdState.notifyAdShowing()

            adToShow?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d(TAG, "Rewarded ad was dismissed.")
                    AdState.notifyAdDismissed()
                    loadAd() // Preload the next ad
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    Log.e(TAG, "Rewarded ad failed to show: ${adError.message}")
                    AdState.notifyAdDismissed()
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d(TAG, "Rewarded ad showed fullscreen content.")
                }
            }

            adToShow?.show(activity) { rewardItem ->
                onRewardEarned(rewardItem.amount)
                Log.d(TAG, "User earned reward: ${rewardItem.amount} ${rewardItem.type}")
            }
        } else {
            Log.d(TAG, "The rewarded ad wasn't ready yet.")
            android.widget.Toast.makeText(
                activity,
                "Ad is loading, please try again in a few seconds...",
                android.widget.Toast.LENGTH_SHORT
            ).show()
            loadAd()
        }
    }
}
