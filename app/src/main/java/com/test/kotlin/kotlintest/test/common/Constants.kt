package com.test.kotlin.kotlintest.test.common

import android.util.Log

object Constants {
    const val TAG = "kotlinTest"
    const val PACKAGE_NAME = "test"

    fun javaTes1() {
        Log.d(TAG, "Constants call javaTes1")
    }

    @JvmStatic
    fun javaTes2() {
        Log.d(TAG, "Constants call javaTes2")
    }
}