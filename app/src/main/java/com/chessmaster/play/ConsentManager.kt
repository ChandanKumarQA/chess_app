package com.chessmaster.play

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform

object ConsentManager {
    private const val TAG = "ConsentManager"

    /**
     * Always allows ads to request so that ads are never blocked on startup
     * or in regions outside EEA/UK.
     */
    fun canRequestAds(context: Context? = null): Boolean = true

    /**
     * Gathers Google UMP consent asynchronously in the background without blocking ads.
     */
    fun gatherConsent(activity: Activity, onDone: () -> Unit = {}) {
        try {
            val info = UserMessagingPlatform.getConsentInformation(activity)
            info.requestConsentInfoUpdate(
                activity,
                ConsentRequestParameters.Builder().build(),
                {
                    UserMessagingPlatform.loadAndShowConsentFormIfRequired(activity) {
                        onDone()
                    }
                },
                { error ->
                    Log.d(TAG, "Consent info update error (ignored to not block ads): ${error.message}")
                    onDone()
                }
            )
        } catch (e: Exception) {
            Log.e(TAG, "Exception during consent gather: ${e.message}")
            onDone()
        }
    }
}
