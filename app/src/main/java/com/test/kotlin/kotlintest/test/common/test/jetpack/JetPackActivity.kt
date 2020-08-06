package com.test.kotlin.kotlintest.test.common.test.jetpack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.kotlin.kotlintest.R

class JetPackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack)
    }
}
