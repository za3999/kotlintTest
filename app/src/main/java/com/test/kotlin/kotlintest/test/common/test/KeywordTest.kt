package com.test.kotlin.kotlintest.test.common.test

import android.content.Context
import com.test.kotlin.kotlintest.test.common.test.keyword.Inline
import com.test.kotlin.kotlintest.test.common.test.keyword.Operator

class KeywordTest {

    companion object {
        fun test(context: Context) {
            Inline.test()
            Operator.test(context)
        }
    }
}