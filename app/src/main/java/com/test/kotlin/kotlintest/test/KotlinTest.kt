package com.test.kotlin.kotlintest.test

import android.content.Context
import android.widget.Toast

open class KotlinTest {
    companion object {
        const val KEY = "static key"
        fun test(context: Context) {
            Toast.makeText(context, KEY, Toast.LENGTH_LONG).show()
        }
    }

    fun test(context: Context) {
       test(context)
    }
}
