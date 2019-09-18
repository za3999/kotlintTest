package com.test.kotlin.kotlintest.test.common.test.util

import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.test.kotlin.kotlintest.test.common.Constants
import java.io.Serializable
import kotlin.reflect.KProperty

class ExtrasDelegate<out T>(private val extraName: String, private val defaultValue: T) {

    operator fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        return getExtra(extraName, thisRef) ?: defaultValue
    }

    operator fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return getExtra(extraName, thisRef) ?: defaultValue
    }

    private fun getExtra(extraName: String, thisRef: AppCompatActivity): T? = thisRef.intent?.extras?.get(extraName) as T?

    private fun getExtra(extraName: String, thisRef: Fragment): T? = thisRef.arguments?.get(extraName) as T?

    operator fun <T> setValue(thisRef: AppCompatActivity, property: KProperty<*>, value: T) {
        Log.d(Constants.TAG, "ExtrasDelegate setValue value:$value")
        when (value) {
            is Int -> thisRef.intent?.putExtra(extraName, value)
            is Float -> thisRef.intent?.putExtra(extraName, value)
            is Double -> thisRef.intent?.putExtra(extraName, value)
            is Long -> thisRef.intent?.putExtra(extraName, value)
            is Boolean -> thisRef.intent?.putExtra(extraName, value)
            is String -> thisRef.intent?.putExtra(extraName, value)
            is Char -> thisRef.intent?.putExtra(extraName, value)
            is Byte -> thisRef.intent?.putExtra(extraName, value)
            is Serializable -> thisRef.intent?.putExtra(extraName, value)
            is Parcelable -> thisRef.intent?.putExtra(extraName, value)
            is Array<*> -> thisRef.intent?.putExtra(extraName, value)
            is Byte -> thisRef.intent?.putExtra(extraName, value)
            is ByteArray -> thisRef.intent?.putExtra(extraName, value)
            is Bundle -> thisRef.intent?.putExtra(extraName, value)
            else -> throw IllegalArgumentException("Extras can't be save this type")
        }
    }

    operator fun <T> setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        when (value) {
            is Int -> thisRef.arguments?.putInt(extraName, value)
            is Float -> thisRef.arguments?.putFloat(extraName, value)
            is Long -> thisRef.arguments?.putLong(extraName, value)
            is Boolean -> thisRef.arguments?.putBoolean(extraName, value)
            is String -> thisRef.arguments?.putString(extraName, value)
            is IBinder -> thisRef.arguments?.putBinder(extraName, value)
            is Bundle -> thisRef.arguments?.putBundle(extraName, value)
            else -> throw IllegalArgumentException("Extras can't be save this type")
        }
    }
}



