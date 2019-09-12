package com.test.kotlin.kotlintest.test.common.test.keyword

import android.content.Context
import android.util.Log
import com.test.kotlin.kotlintest.test.common.Constants
import com.test.kotlin.kotlintest.test.common.test.util.PreferenceProxy

class Operator {

    companion object {
        fun test(context: Context) {
            var mGuideEnable by PreferenceProxy(context, "guide_enable", false)
            var testGuideEnable by PreferenceProxy(context, "guide_enable", false)
            mGuideEnable = true
            Log.d(Constants.TAG, "mGuideEnable:$mGuideEnable")
            Log.d(Constants.TAG, "testGuideEnable:$testGuideEnable")
            mGuideEnable = false
            Log.d(Constants.TAG, "mGuideEnable:$mGuideEnable")
            Log.d(Constants.TAG, "testGuideEnable:$testGuideEnable")
        }
    }
}