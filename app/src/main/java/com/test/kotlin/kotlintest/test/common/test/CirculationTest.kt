package com.test.kotlin.kotlintest.test.common.test

import android.util.Log
import com.test.kotlin.kotlintest.test.common.Constants

class CirculationTest {
    companion object {
        fun test(mList: List<String>) {
            testFor(mList)
        }

        private fun testFor(mList: List<String>) {
            for (str in mList) {
                Log.d(Constants.TAG, "value:$str")
            }
            Log.d(Constants.TAG, "*************************")
            var list = mList.reversed()
            for ((index, str) in list.withIndex()) {
                Log.d(Constants.TAG, "index:$index value:$str")
            }
            Log.d(Constants.TAG, "*************************")
            list = mList.sorted()
            for (index in list.indices) {
                Log.d(Constants.TAG, "index$index value:${list[index]}")
            }
            Log.d(Constants.TAG, "*************************")
            list.forEach {
                Log.d(Constants.TAG, "forEach value:$it")
            }
            list.filterIndexed { index, _ -> index < 2 }.forEach {
                Log.d(Constants.TAG, "filter value:$it")
            }
            Log.d(Constants.TAG, "*************************")
            for (index in 0..50 step 9) {
                Log.d(Constants.TAG, "index:$index ")
            }
            Log.d(Constants.TAG, "*************************")
            for (index in 50..0) {
                Log.d(Constants.TAG, "index:$index ")
            }
        }
    }
}