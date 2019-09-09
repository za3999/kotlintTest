package com.test.kotlin.kotlintest.test.common.test

import android.util.Log
import com.test.kotlin.kotlintest.test.common.Constants

class C(var name: String) : A(), B {

    override fun method() {
        super<A>.method()
        super<B>.method()
        Log.d(Constants.TAG, "C method")
    }
}