package com.test.kotlin.kotlintest.test.common.test

import android.util.Log
import com.test.kotlin.kotlintest.test.common.Constants

interface B {
    fun method() {
        Log.d(Constants.TAG, "B method")
    }
}