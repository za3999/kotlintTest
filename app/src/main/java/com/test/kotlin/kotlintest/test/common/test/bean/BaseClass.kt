package com.test.kotlin.kotlintest.test.common.test.bean

import android.util.Log
import com.test.kotlin.kotlintest.test.common.Constants

open class A {
    open fun method() {
        Log.d(Constants.TAG, "A method")
    }
}

interface B {
    fun method() {
        Log.d(Constants.TAG, "B method")
    }
}

open class C(var name: String) : A(), B {

    open var sex: String = "c sex"

    override fun method() {
        super<A>.method()
        super<B>.method()
        Log.d(Constants.TAG, "C method:$name")
    }
}