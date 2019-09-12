package com.test.kotlin.kotlintest.test.common.test.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.test.kotlin.kotlintest.test.common.Constants
import kotlin.reflect.KProperty

class PreferenceProxy<T>(private val context: Context, val name: String, private val default: T) {

    private val prefs: SharedPreferences by lazy { context.getSharedPreferences("SP", Context.MODE_PRIVATE) }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = getSharedPreferences(name, default)

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = putSharedPreferences(name, value)

    private fun putSharedPreferences(name: String, value: T) = with(prefs.edit()) {
        Log.d(Constants.TAG, "PreferenceProxy putSharedPreferences:$name,$value")
        when (value) {
            is Int -> putInt(name, value)
            is Float -> putFloat(name, value)
            is Long -> putLong(name, value)
            is Boolean -> putBoolean(name, value)
            is String -> putString(name, value)
            else -> throw IllegalArgumentException("SharedPreference can't be save this type")
        }.apply()
    }

    private fun getSharedPreferences(name: String, default: T): T = with(prefs) {
        val res: Any = when (default) {
            is Int -> getInt(name, default)
            is Float -> getFloat(name, default)
            is Long -> getLong(name, default)
            is Boolean -> getBoolean(name, default)
            is String -> getString(name, default)
            else -> throw IllegalArgumentException("SharedPreference can't be get this type")
        }
        Log.d(Constants.TAG, "PreferenceProxy getSharedPreferences:$name,$res")
        return res as T
    }
}