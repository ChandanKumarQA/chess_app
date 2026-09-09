package com.chessmaster.play

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.android.gms.ads.MobileAds

class ChessApplication : Application(), Application.ActivityLifecycleCallbacks, DefaultLifecycleObserver {

    private lateinit var appOpenAdManager: AppOpenAdManager
    private var currentActivity: Activity? = null

    override fun onCreate() {
        super<Application>.onCreate()
        registerActivityLifecycleCallbacks(this)
        
        // Initialize the Google Mobile Ads SDK here
        MobileAds.initialize(this) {}
        
        appOpenAdManager = AppOpenAdManager(this)
        appOpenAdManager.loadAd()

        // Initialize Board Preferences
        com.chessmaster.play.data.BoardPreferences.init(this)

        // Register default lifecycle observer
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    /** LifecycleObserver method that shows the app open ad when the app moves to foreground. */
    override fun onStart(owner: LifecycleOwner) {
        // Show the ad (if available) only when app moves to foreground and no full-screen ad was recently dismissed
        if (AdState.canShowAppOpenAd()) {
            currentActivity?.let {
                if (!it.isFinishing && !it.isDestroyed && !it.javaClass.name.contains("AdActivity")) {
                    appOpenAdManager.showAdIfAvailable(it)
                }
            }
        }
    }

    // ActivityLifecycleCallbacks methods
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

    override fun onActivityStarted(activity: Activity) {
        if (!activity.javaClass.name.contains("AdActivity") && !appOpenAdManager.isShowingAd) {
            currentActivity = activity
        }
    }

    override fun onActivityResumed(activity: Activity) {
        if (!activity.javaClass.name.contains("AdActivity")) {
            currentActivity = activity
        }
    }

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {
        if (currentActivity == activity) {
            currentActivity = null
        }
    }
}
