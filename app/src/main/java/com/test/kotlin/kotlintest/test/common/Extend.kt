package com.test.kotlin.kotlintest.test.common

import android.util.Log
import com.test.kotlin.kotlintest.test.common.test.bean.C
import com.test.kotlin.kotlintest.test.common.test.ClassTest
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

fun ClassTest.Companion.extend() {
    Log.d(Constants.TAG, " ClassTest Companion Extend")
}

fun C.foo() {
    Log.d(Constants.TAG, "C foo Extend $name")
}

var C.reName: String
    get() = name
    set(value) {
        this.name = value
    }

fun Any?.toTest(): String {
    if (this == null) return "null"
    return toString()
}

inline fun <reified T : Any> T.description() = this.javaClass.kotlin.memberProperties.joinToString(";") {
    "${it.name}: ${it.get(this)}"
}

fun <T, R> KProperty1<T, R>.getUnsafed(receiver: Any): R {
    return get(receiver as T)
}

inline fun <reified T : Any> T.description1() = this::class.memberProperties.joinToString(";") {
    "${it.name}: ${it.getUnsafed(this)}"
}