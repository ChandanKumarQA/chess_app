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
import com.google.android.gms.ads.BaseAdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.admanager.AdManagerAdView

private const val TAG = "BannerAd"

// AdMob and GAM configuration
const val DEFAULT_ADMOB_BANNER_ID = "ca-app-pub-8940048544519183/3068957289"
const val DEFAULT_GAM_BANNER_ID = "/23374332010/chess_master"

val DEFAULT_BANNER_SIZES = listOf(
    AdSize(320, 50),
    AdSize(320, 100),
    AdSize(300, 250)
)

private class AdViewHolder {
    var activeAdView: BaseAdView? = null
}

/**
 * BannerAd displays an AdMob banner ad by default, with automatic fallback
 * to Google Ad Manager (GAM) with supported sizes (300x250, 320x50, 320x100) if AdMob fails to load.
 */
@Composable
fun BannerAd(
    modifier: Modifier = Modifier,
    adMobUnitId: String = com.chessmaster.play.AdIds.banner(androidx.compose.ui.platform.LocalContext.current),
    gamUnitId: String = DEFAULT_GAM_BANNER_ID,
    adSizes: List<AdSize> = DEFAULT_BANNER_SIZES
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

                fun loadGamFallback() {
                    Log.d(TAG, "Initiating GAM fallback ad request for: $gamUnitId")
                    val gamView = AdManagerAdView(ctx).apply {
                        adUnitId = gamUnitId
                        setAdSizes(*adSizes.toTypedArray())
                        adListener = object : AdListener() {
                            override fun onAdLoaded() {
                                Log.d(TAG, "GAM Ad loaded successfully ($gamUnitId)")
                                container.visibility = View.VISIBLE
                            }

                            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                                Log.e(TAG, "GAM Ad failed to load: ${loadAdError.message} (code: ${loadAdError.code})")
                                container.visibility = View.GONE
                            }
                        }
                    }

                    container.removeAllViews()
                    container.addView(gamView)
                    holder.activeAdView = gamView
                    gamView.loadAd(AdManagerAdRequest.Builder().build())
                }

                fun loadAdMob() {
                    Log.d(TAG, "Initiating AdMob banner load: $adMobUnitId")
                    val adMobSize = when {
                        adSizes.contains(AdSize.BANNER) -> AdSize.BANNER
                        adSizes.contains(AdSize.LARGE_BANNER) -> AdSize.LARGE_BANNER
                        adSizes.contains(AdSize.MEDIUM_RECTANGLE) -> AdSize.MEDIUM_RECTANGLE
                        else -> adSizes.firstOrNull() ?: AdSize.BANNER
                    }

                    val adView = AdView(ctx).apply {
                        setAdSize(adMobSize)
                        adUnitId = adMobUnitId
                        adListener = object : AdListener() {
                            override fun onAdLoaded() {
                                Log.d(TAG, "AdMob banner loaded successfully ($adMobUnitId)")
                                container.visibility = View.VISIBLE
                            }

                            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                                Log.w(TAG, "AdMob banner failed to load: ${loadAdError.message} (code: ${loadAdError.code}). Falling back to GAM...")
                                destroy()
                                loadGamFallback()
                            }
                        }
                    }

                    container.removeAllViews()
                    container.addView(adView)
                    holder.activeAdView = adView
                    adView.loadAd(AdRequest.Builder().build())
                }

                loadAdMob()
                container
            }
        )
    }
}

/**
 * Optional convenience composable for explicitly rendering a Medium Rectangle (300x250) banner
 * with AdMob to GAM fallback.
 */
@Composable
fun MediumRectangleAd(
    modifier: Modifier = Modifier,
    adMobUnitId: String = com.chessmaster.play.AdIds.banner(androidx.compose.ui.platform.LocalContext.current),
    gamUnitId: String = DEFAULT_GAM_BANNER_ID
) {
    BannerAd(
        modifier = modifier,
        adMobUnitId = adMobUnitId,
        gamUnitId = gamUnitId,
        adSizes = listOf(AdSize(300, 250))
    )
}
