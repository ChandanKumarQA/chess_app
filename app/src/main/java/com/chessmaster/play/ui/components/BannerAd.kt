package com.chessmaster.play.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun BannerAd(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                AdView(context).apply {
                    setAdSize(AdSize.BANNER)
                    // Use test Ad Unit ID during development (uncomment below for testing)
                    // adUnitId = "ca-app-pub-3940256099942544/6300978111" // TEST ID
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
                }
            }
        )
    }
}
