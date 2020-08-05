package com.test.kotlin.kotlintest.test.common.test

import android.text.TextUtils
import android.util.Log
import com.test.kotlin.kotlintest.test.common.Constants

class ClosureTest {

    companion object {

        fun test() {
            var classTest = ClosureTest()
            var closure = classTest.testClosure()
            Log.d(Constants.TAG, "test a :${closure()}")
            Log.d(Constants.TAG, "test a :${closure()}")
            Log.d(Constants.TAG, "test a :${closure()}")

            var result = classTest.action(closure(), 4) { first: Int, second: Int ->
                Log.d(Constants.TAG, "action callback first:$first,second:$second")
                first == second
            }
            Log.d(Constants.TAG, "test result:${result}")
            classTest.action(closure(), 4, classTest.test)
            Log.d(Constants.TAG, "test:${classTest.test}")
            classTest.setListener {
                TextUtils.equals(it, "aaa")
            }
        }
    }

    var test = { first: Int, second: Int ->
        Log.d(Constants.TAG, "action callback first: $first,second:$second")
        first == second
    }

    private inline fun action(first: Int, second: Int, callback: (Int, Int) -> Boolean): Boolean {
        var result = callback(first, second)
        Log.d(Constants.TAG, "action result:$result")
        return result
    }

    private fun testClosure(): () -> Int {
        var a = 0
        return fun(): Int {
            return ++a
        }
    }

    private fun setListener(l: (c: String) -> Boolean) {
        val b = l("aaa")
        Log.d(Constants.TAG, if (b) "当前是aaa" else "当前不是aaa")
    }
}