package com.test.kotlin.kotlintest.test.common.test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.test.kotlin.kotlintest.test.common.Constants
import com.test.kotlin.kotlintest.test.common.test.bean.User
import com.test.kotlin.kotlintest.test.common.test.util.ExtrasDelegate


class ExtrasActivity : AppCompatActivity() {
    private var user: User? by ExtrasDelegate("user", null)
    private var string: String? by ExtrasDelegate("string", "test")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(Constants.TAG, "ExtrasActivity user:$user,string:$string")
        string = "哈哈哈"
        Log.d(Constants.TAG, "ExtrasActivity user:$user,string:$string")

    }
}