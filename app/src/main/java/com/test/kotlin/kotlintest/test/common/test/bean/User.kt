package com.test.kotlin.kotlintest.test.common.test.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import android.util.Log
import com.test.kotlin.kotlintest.test.common.Constants
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject
import kotlin.properties.Delegates

@SuppressLint("ParcelCreator")
@ActivityScoped
@Parcelize
data class User(var name: String, var age: Int) : Parcelable, Comparable<User> {

    @Inject
    constructor() : this("小王吧", 10)

    override fun compareTo(other: User): Int {
        return age - other.age
    }

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

