package com.test.kotlin.kotlintest.test.common.test.jetpack

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.kotlin.kotlintest.R

class JetPackActivity : AppCompatActivity() {

    lateinit var model: TestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack)
        model = ViewModelProvider.AndroidViewModelFactory(application).create(TestViewModel::class.java)
        model.getUsers().observe(this, Observer {
            Log.d("TEST1", "size:${it.size}")
        })
    }
}
