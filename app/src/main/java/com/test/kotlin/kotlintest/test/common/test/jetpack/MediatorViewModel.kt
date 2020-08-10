package com.test.kotlin.kotlintest.test.common.test.jetpack

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.kotlin.kotlintest.test.common.test.bean.User
import kotlinx.coroutines.*

class MediatorViewModel(application: Application) : AndroidViewModel(application) {

    val users1: MutableLiveData<MutableList<User>> by lazy {
        var liveData = MutableLiveData<MutableList<User>>()
        liveData.value = mutableListOf()
        liveData
    }
    val users2: MutableLiveData<MutableList<User>> by lazy {
        var liveData = MutableLiveData<MutableList<User>>()
        liveData.value = mutableListOf()
        liveData
    }

    var userModels = MediatorLiveData<MutableList<User>>()

    fun test() {
        users1.value = users1.value.also {
            it?.add(User("user1:小王", 20))
        }

        users2.value = users2.value.also {
            it?.add(User("user2:小李", 20))
        }
    }

    fun makeNetworkRequest() {
        viewModelScope.launch {
            testRequest()
        }
    }

    private suspend fun testRequest() {
        withContext(Dispatchers.IO) {
            delay(1000)
            val test = "test2:${Thread.currentThread().name}"
            users1.postValue(users1.value.also { it?.add(User(test, 90)) })
        }
    }
}
