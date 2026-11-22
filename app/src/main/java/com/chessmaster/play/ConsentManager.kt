package com.chessmaster.play

import android.app.Activity
import android.content.Context
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform

/**
 * Google UMP consent (required for EEA/UK users). Ads must only be requested after
 * [canRequestAds] is true. Needs: implementation("com.google.android.ump:user-messaging-platform:3.0.0")
 * (skip if your play-services-ads version already pulls it in).
 */
object ConsentManager {

    fun canRequestAds(context: Context): Boolean =
        UserMessagingPlatform.getConsentInformation(context).canRequestAds()

    /** Shows the consent form when required, then calls [onDone] (always, even on errors). */
    fun gatherConsent(activity: Activity, onDone: () -> Unit) {
        val info = UserMessagingPlatform.getConsentInformation(activity)
        info.requestConsentInfoUpdate(
            activity,
            ConsentRequestParameters.Builder().build(),
            {
                UserMessagingPlatform.loadAndShowConsentFormIfRequired(activity) { onDone() }
            },
            { onDone() }
        )
    }
}
