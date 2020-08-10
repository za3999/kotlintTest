package com.test.kotlin.kotlintest.test.common.test.jetpack

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.kotlin.kotlintest.R
import com.test.kotlin.kotlintest.test.common.Constants
import kotlinx.android.synthetic.main.activity_jetpack.*

class JetPackActivity : AppCompatActivity() {

    private lateinit var mediatorModel: MediatorViewModel
    private lateinit var mutableModel: MutableViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack)
        mediatorModel = ViewModelProvider.AndroidViewModelFactory(application).create(MediatorViewModel::class.java)
        initLiveData()
        mutableModel = ViewModelProvider.AndroidViewModelFactory(application).create(MutableViewModel::class.java)
        mutableModel.users.observe(this, Observer { Log.d(Constants.TAG, "mutableModel size:${it}") })
        mediatorTest.setOnClickListener {
            mediatorModel.test()
            mediatorModel.makeNetworkRequest()
            Toast.makeText(applicationContext, "run Main after", Toast.LENGTH_LONG).show()
        }
        mutableTest.setOnClickListener {
            mutableModel.test()
        }
    }

    private fun initLiveData() {
        mediatorModel.apply {
            userModels.addSource(users1, Observer {
                userModels.value = it
            })

            userModels.addSource(users2) {
                userModels.value = it
            }

            userModels.observe(this@JetPackActivity, Observer {
                Log.d(Constants.TAG, "mediatorModel size:${it}")
            })
        }
    }
}
