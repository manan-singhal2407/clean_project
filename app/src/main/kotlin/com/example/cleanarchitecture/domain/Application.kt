package com.example.cleanarchitecture.domain

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application : Application() {
    override fun onCreate() {
//        ActivityLifecycleCallback.register(this)
        super.onCreate()
        FirebaseApp.initializeApp(this)
//        if (BuildConfig.DEBUG) {
//            Firebase.crashlytics.setCrashlyticsCollectionEnabled(false)
//            CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.DEBUG)
//        }
    }
}