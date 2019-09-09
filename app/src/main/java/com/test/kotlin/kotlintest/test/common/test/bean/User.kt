package com.test.kotlin.kotlintest.test.common.test.bean

import android.util.Log
import com.test.kotlin.kotlintest.test.common.Constants
import kotlin.properties.Delegates

data class User(var name: String, var age: Int) {

    var temp: String by Delegates.observable("初始值") { prop, old, new ->
        Log.d(Constants.TAG, "旧值：$old -> 新值：$new")
    }

    private fun loadLazy(): String {
        Log.d(Constants.TAG, "lazyLoadName")
        return "lazyLoadName"
    }

    val lazyName: String by lazy {
        loadLazy()
    }
}

