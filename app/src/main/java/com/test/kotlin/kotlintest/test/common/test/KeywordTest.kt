package com.test.kotlin.kotlintest.test.common.test

import android.content.Context
import com.test.kotlin.kotlintest.test.common.test.keyword.Inline
import com.test.kotlin.kotlintest.test.common.test.keyword.Operator
import com.test.kotlin.kotlintest.test.common.test.keyword.Suspend
import com.test.kotlin.kotlintest.test.common.test.keyword.With

class KeywordTest {

    companion object {
        fun test(context: Context) {
            Inline.test()
            Operator.test(context)
            With.test()
            Suspend.test()
        }
    }
}