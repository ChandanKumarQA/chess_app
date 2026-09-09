package com.chessmaster.play

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class InterstitialAdManager(private val context: Context) {
    private var interstitialAd: InterstitialAd? = null
    private var isAdLoading = false

    // Using Google's test ad unit ID during development
    // private val adUnitId = "ca-app-pub-3940256099942544/1033173712"
    private val adUnitId = "ca-app-pub-8940048544519183/8091729072" // REAL ID

    init {
        loadAd()
    }

    private fun loadAd() {
        if (interstitialAd != null || isAdLoading) {
            return
        }

        isAdLoading = true
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            context,
            adUnitId,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d("InterstitialAdManager", adError.toString())
                    interstitialAd = null
                    isAdLoading = false
                }

                override fun onAdLoaded(ad: InterstitialAd) {
                    Log.d("InterstitialAdManager", "Ad was loaded.")
                    interstitialAd = ad
                    isAdLoading = false
                }
            }
        )
    }

    fun showAd(activity: Activity, onAdDismissed: () -> Unit) {
        if (activity.isFinishing || activity.isDestroyed || AdState.isFullScreenAdShowing) {
            Log.d("InterstitialAdManager", "Activity is finishing, destroyed, or another ad is showing.")
            onAdDismissed()
            return
        }

        if (interstitialAd != null) {
            val adToShow = interstitialAd
            interstitialAd = null
            AdState.notifyAdShowing()

            adToShow?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d("InterstitialAdManager", "Ad was dismissed.")
                    AdState.notifyAdDismissed()
                    loadAd()
                    onAdDismissed()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    Log.d("InterstitialAdManager", "Ad failed to show: $adError")
                    AdState.notifyAdDismissed()
                    onAdDismissed()
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d("InterstitialAdManager", "Ad showed fullscreen content.")
                }
            }
            adToShow?.show(activity)
        } else {
            Log.d("InterstitialAdManager", "The interstitial ad wasn't ready yet.")
            onAdDismissed()
            loadAd()
        }
    }
}
