package com.test.kotlin.kotlintest.test.common.test.keyword

import android.util.Log
import com.test.kotlin.kotlintest.test.common.Constants
import com.test.kotlin.kotlintest.test.common.test.bean.User

class With {

    companion object {

        fun test() {
            Log.d(Constants.TAG, "************** With **************")
            With().testWith(User("郑小才", 19)) {
                Log.d(Constants.TAG, "testWith:$name,$age")
                "'aaa"
            }

            var test = With().testWithReturn(User("郑小才", 19)) {
                Log.d(Constants.TAG, "testWithReturn:$name,$age")
                "aaa"
            }
            Log.d(Constants.TAG, "testWithReturn result:$test")

            With().testLet(User("郑小才", 19)) {
                Log.d(Constants.TAG, "testLet:${it.name},${it.age}")
            }

            User("郑小才", 19).testApply {
                Log.d(Constants.TAG, "testApply:$name,$age")
            }
            var result1 = User("郑小才", 19).testBlockReturn {
                "aaa"
            }
            Log.d(Constants.TAG, "result1:$result1")
            var result2 = User("郑小才", 19).testBlockReturn {
                111
            }
            Log.d(Constants.TAG, "result2:$result2")
        }

        private fun <T> T.testApply(block: T.() -> Unit): T {
            block()
            return this
        }

        private fun <T, R> T.testBlockReturn(block: (T) -> R): R {
            return block(this)
        }
    }

    //T.() -> Unit 相当于把T解开，可以直接用T属性
    inline fun <T> testWith(receiver: T, block: T.() -> Unit) {
        receiver.block()
    }

    inline fun <T, R> testWithReturn(receiver: T, block: T.() -> R): R {
        return receiver.block()
    }

    inline fun <T> testLet(receiver: T, block: (T) -> Unit) {
        block(receiver)
    }

}