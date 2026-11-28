package com.chessmaster.play.ui.components

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.chessmaster.play.AdIds

private const val TAG = "BannerAd"

private class AdViewHolder {
    var activeAdView: AdView? = null
}

/**
 * BannerAd displays an official AdMob banner ad.
 */
@Composable
fun BannerAd(
    modifier: Modifier = Modifier,
    adMobUnitId: String = AdIds.BANNER,
    adSize: AdSize = AdSize.BANNER
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val holder = remember { AdViewHolder() }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> holder.activeAdView?.pause()
                Lifecycle.Event.ON_RESUME -> holder.activeAdView?.resume()
                Lifecycle.Event.ON_DESTROY -> {
                    holder.activeAdView?.destroy()
                    holder.activeAdView = null
                }
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            holder.activeAdView?.destroy()
            holder.activeAdView = null
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            modifier = Modifier.wrapContentSize(),
            factory = { ctx ->
                val container = FrameLayout(ctx).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                }

                Log.d(TAG, "Loading AdMob banner: $adMobUnitId")
                val adView = AdView(ctx).apply {
                    setAdSize(adSize)
                    this.adUnitId = adMobUnitId
                    adListener = object : AdListener() {
                        override fun onAdLoaded() {
                            Log.d(TAG, "AdMob banner loaded successfully ($adMobUnitId)")
                            container.visibility = View.VISIBLE
                        }

                        override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                            Log.e(TAG, "AdMob banner failed to load: ${loadAdError.code} - ${loadAdError.message}")
                            container.visibility = View.GONE
                        }
                    }
                    loadAd(AdRequest.Builder().build())
                }

                container.addView(adView)
                holder.activeAdView = adView
                container
            }
        )
    }
}

/**
 * Optional composable for rendering a Medium Rectangle (300x250) banner.
 */
@Composable
fun MediumRectangleAd(
    modifier: Modifier = Modifier,
    adMobUnitId: String = AdIds.BANNER
) {
    BannerAd(
        modifier = modifier,
        adMobUnitId = adMobUnitId,
        adSize = AdSize.MEDIUM_RECTANGLE
    )
}
