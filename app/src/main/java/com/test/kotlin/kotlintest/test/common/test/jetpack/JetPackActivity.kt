package com.test.kotlin.kotlintest.test.common.test.jetpack

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.kotlin.kotlintest.R
import com.test.kotlin.kotlintest.test.common.Constants
import kotlinx.android.synthetic.main.activity_jetpack.*

class JetPackActivity : AppCompatActivity() {

    lateinit var model: TestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack)
        model = ViewModelProvider.AndroidViewModelFactory(application).create(TestViewModel::class.java)
        initLiveData()
        addUserTest.setOnClickListener { model.test() }
    }

    private fun initLiveData() {
        model.apply {
            userModels.addSource(users1, Observer {
                userModels.value = it
            })

            userModels.addSource(users2) {
                userModels.value = it
            }

            userModels.observe(this@JetPackActivity, Observer {
                Log.d(Constants.TAG, "size:${it}")
            })
        }
    }
}
