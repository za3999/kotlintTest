package com.test.kotlin.kotlintest.test.common.test

import android.util.Log
import com.test.kotlin.kotlintest.test.common.Constants

class CirculationTest {
    companion object {
        fun test(mList: List<String>) {
            CirculationTest().testFor(mList)
        }
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
        list.asSequence().filterIndexed { index, _ -> index < 2 }.forEach {
            Log.d(Constants.TAG, "filter value:$it")
        }
        Log.d(Constants.TAG, "*************************")
        for (index in 0..50 step 9) {
            Log.d(Constants.TAG, "index:$index ")
        }
        Log.d(Constants.TAG, "*************************")
        for (index in 50 downTo 0 step 2) {
            Log.d(Constants.TAG, "index:$index ")
        }
        list = mList.asSequence().filterIndexed { index, str: String -> index != 2 && !str.contains("aa") }.toList()
        list.forEach { Log.d(Constants.TAG, "filter:$it") }

        mList.asSequence().map { "test:$it" }.filter { !it.contains("aa") }
                .forEach { Log.d(Constants.TAG, "asSequence value:$it") }
    }
}