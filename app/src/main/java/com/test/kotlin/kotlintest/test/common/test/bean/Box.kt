package com.test.kotlin.kotlintest.test.common.test.bean

abstract class Box<in T : CharSequence, out K>(val k: K) {

    abstract fun consume(item: T): String

    fun readK(): K {
        return k
    }
}