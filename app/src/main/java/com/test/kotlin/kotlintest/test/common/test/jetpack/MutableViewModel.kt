package com.test.kotlin.kotlintest.test.common.test.jetpack

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.test.kotlin.kotlintest.test.common.test.bean.User

class MutableViewModel(application: Application) : AndroidViewModel(application) {

    val users: MutableLiveData<MutableList<User>> by lazy {
        var liveData = MutableLiveData<MutableList<User>>()
        liveData.value = mutableListOf()
        liveData
    }

    fun test() {
        users.value = users.value.also {
            it?.add(User("user:小王", 20))
        }
    }
}
