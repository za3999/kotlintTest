package com.test.kotlin.kotlintest.test.common.test.jetpack

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.kotlin.kotlintest.test.common.test.bean.User

class TestViewModel(application: Application) : AndroidViewModel(application) {

    private val users: MutableLiveData<List<User>> by lazy {
        MutableLiveData<List<User>>()
    }

    fun getUsers(): LiveData<List<User>> {
        return users
    }

    private fun loadUsers() {

    }
}

