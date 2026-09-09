package com.chessmaster.play

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class RewardedAdManager(private val context: Context) {
    private var rewardedAd: RewardedAd? = null
    // Test Ad Unit ID:
    // private val adUnitId = "ca-app-pub-3940256099942544/5224354917"
    private val adUnitId = "ca-app-pub-8940048544519183/4897026421" // REAL ID
    private var isLoading = false

    fun loadAd() {
        if (rewardedAd != null || isLoading) {
            return
        }
        isLoading = true
        val adRequest = AdRequest.Builder().build()

        RewardedAd.load(
            context,
            adUnitId,
            adRequest,
            object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d("RewardedAdManager", adError.toString())
                    rewardedAd = null
                    isLoading = false
                }

                override fun onAdLoaded(ad: RewardedAd) {
                    Log.d("RewardedAdManager", "Ad was loaded.")
                    rewardedAd = ad
                    isLoading = false
                }
            }
        )
    }

    fun showAd(activity: Activity, onRewardEarned: (Int) -> Unit) {
        if (activity.isFinishing || activity.isDestroyed || AdState.isFullScreenAdShowing) {
            Log.d("RewardedAdManager", "Cannot show ad: Activity invalid or another ad is showing.")
            return
        }

        if (rewardedAd != null) {
            val adToShow = rewardedAd
            rewardedAd = null
            AdState.notifyAdShowing()

            adToShow?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d("RewardedAdManager", "Ad was dismissed.")
                    AdState.notifyAdDismissed()
                    loadAd() // Preload the next ad
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    Log.d("RewardedAdManager", "Ad failed to show: $adError")
                    AdState.notifyAdDismissed()
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d("RewardedAdManager", "Ad showed fullscreen content.")
                }
            }

            adToShow?.show(activity) { rewardItem ->
                onRewardEarned(rewardItem.amount)
                Log.d("RewardedAdManager", "User earned the reward.")
            }
        } else {
            Log.d("RewardedAdManager", "The rewarded ad wasn't ready yet.")
            android.widget.Toast.makeText(activity, "Ad is loading, please try again in a few seconds...", android.widget.Toast.LENGTH_SHORT).show()
            loadAd()
        }
    }
}
