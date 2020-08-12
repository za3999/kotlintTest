package com.test.kotlin.kotlintest.test.common

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d(Constants.TAG, "MyApplication onCreate")
    }
}