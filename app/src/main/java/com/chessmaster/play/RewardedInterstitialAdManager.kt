package com.chessmaster.play

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback

private const val TAG = "RewardedInterstitial"

/**
 * Manages Rewarded Interstitial Ads (ad unit: ca-app-pub-8940048544519183/4409959112).
 */
class RewardedInterstitialAdManager(private val context: Context) {

    companion object {
        const val AD_UNIT_ID = "ca-app-pub-8940048544519183/4409959112"
    }

    private var rewardedInterstitialAd: RewardedInterstitialAd? = null
    private var isLoading = false

    init {
        loadAd()
    }

    fun loadAd(onLoaded: ((Boolean) -> Unit)? = null) {
        if (rewardedInterstitialAd == null && !ConsentManager.canRequestAds(context)) {
            onLoaded?.invoke(false)
            return
        }
        if (rewardedInterstitialAd != null) {
            onLoaded?.invoke(true)
            return
        }
        if (isLoading) {
            return
        }
        isLoading = true

        val request = AdRequest.Builder().build()
        RewardedInterstitialAd.load(
            context,
            AdIds.rewardedInterstitial(context),
            request,
            object : RewardedInterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedInterstitialAd) {
                    Log.d(TAG, "Rewarded Interstitial Ad loaded successfully.")
                    rewardedInterstitialAd = ad
                    isLoading = false
                    onLoaded?.invoke(true)
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    Log.e(TAG, "Rewarded Interstitial Ad failed to load: ${loadAdError.message}")
                    rewardedInterstitialAd = null
                    isLoading = false
                    onLoaded?.invoke(false)
                }
            }
        )
    }

    fun showAd(activity: Activity, onRewardEarned: ((Int) -> Unit)? = null) {
        if (activity.isFinishing || activity.isDestroyed || AdState.isFullScreenAdShowing) {
            Log.d(TAG, "Cannot show ad: Activity invalid or another ad is showing.")
            return
        }

        if (rewardedInterstitialAd != null) {
            val adToShow = rewardedInterstitialAd
            rewardedInterstitialAd = null
            AdState.notifyAdShowing()

            adToShow?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d(TAG, "Rewarded Interstitial dismissed.")
                    AdState.notifyAdDismissed()
                    loadAd()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    Log.e(TAG, "Rewarded Interstitial failed to show: ${adError.message}")
                    AdState.notifyAdDismissed()
                    loadAd()
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d(TAG, "Rewarded Interstitial showed fullscreen.")
                }
            }

            adToShow?.show(activity) { rewardItem ->
                Log.d(TAG, "User earned reward from Rewarded Interstitial: ${rewardItem.amount}")
                onRewardEarned?.invoke(rewardItem.amount)
            }
        } else {
            Log.d(TAG, "Rewarded Interstitial wasn't ready yet.")
            loadAd()
        }
    }
}
