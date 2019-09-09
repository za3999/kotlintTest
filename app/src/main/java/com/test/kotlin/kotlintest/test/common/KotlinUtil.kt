package com.test.kotlin.kotlintest.test.common

import android.content.Context
import android.widget.Toast

open class KotlinUtil {
    companion object {
        private const val KEY = "static key"
        fun test(context: Context) {
            Toast.makeText(context, KEY, Toast.LENGTH_LONG).show()
        }
    }
}
