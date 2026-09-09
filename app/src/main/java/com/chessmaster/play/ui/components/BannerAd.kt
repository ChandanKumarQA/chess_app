package com.chessmaster.play.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun BannerAd(modifier: Modifier = Modifier) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var adViewRef by remember { mutableStateOf<AdView?>(null) }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> adViewRef?.pause()
                Lifecycle.Event.ON_RESUME -> adViewRef?.resume()
                Lifecycle.Event.ON_DESTROY -> adViewRef?.destroy()
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            adViewRef?.destroy()
            adViewRef = null
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { ctx ->
                AdView(ctx).apply {
                    setAdSize(AdSize.BANNER)
                    adUnitId = "ca-app-pub-8940048544519183/9957781417" // REAL ID
                    
                    adListener = object : com.google.android.gms.ads.AdListener() {
                        override fun onAdFailedToLoad(loadAdError: com.google.android.gms.ads.LoadAdError) {
                            android.util.Log.e("BannerAd", "Ad failed to load: ${loadAdError.message}")
                        }
                        override fun onAdLoaded() {
                            android.util.Log.d("BannerAd", "Ad loaded successfully")
                        }
                    }
                    
                    loadAd(AdRequest.Builder().build())
                    adViewRef = this
                }
            }
        )
    }
}
