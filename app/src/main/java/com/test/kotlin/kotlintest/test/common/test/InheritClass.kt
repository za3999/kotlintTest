package com.test.kotlin.kotlintest.test.common.test

import android.util.Log
import com.test.kotlin.kotlintest.test.common.Constants

class D(var number: String, name: String) : C(name) {

    var age: Int = 0

    constructor(number: String, name: String, age: Int) : this(number, name) {
        this.number = number
        this.age = age
    }

    override fun method() {
        super.method()
        Log.d(Constants.TAG, "D method:$number,$name,$age")
    }
}

class E : C {

    var age: Int = 0

    constructor(name: String, age: Int) : super(name) {
        this.age = age
    }

    override fun method() {
        super.method()
        Log.d(Constants.TAG, "E method:$name,$age")
    }
}