package com.test.kotlin.kotlintest.test.common.test.bean

import android.util.Log
import com.test.kotlin.kotlintest.test.common.Constants

object SimpleSingleton {

    fun test() {
        Log.d(Constants.TAG, "SimpleSingleton test")
    }
}

class LazySingleton  {

    private constructor(){
        Log.d(Constants.TAG, "LazySingleton constructor")
    }

    companion object {
        val instance: LazySingleton by lazy { LazySingleton() }
    }

    fun test() {
        Log.d(Constants.TAG, "LazySingleton test")
    }
}