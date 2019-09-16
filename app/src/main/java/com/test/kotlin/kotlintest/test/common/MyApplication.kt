package com.test.kotlin.kotlintest.test.common

import android.app.Application
import android.util.Log

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d(Constants.TAG, "MyApplication onCreate")
    }
}