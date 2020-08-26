package com.test.kotlin.kotlintest.test.common.motionlayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.kotlin.kotlintest.R

class MotionLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.motion_layout)
    }
}