package com.test.kotlin.kotlintest

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hello.setText("哈哈")
        val test = Test(this, "小王", 1234)
        test.name = "aaa"
        test.sex = "男"
        hello.setOnClickListener {
            test.hello()
        }
    }
}

open class Test(var context: Context, var name: String, var number: Int) {

    lateinit var sex: String

    val getSex: String
        get() = this.sex

    fun constructor(sex: String) {
        this.sex = sex
    }

    open fun hello() {
        Toast.makeText(context, "Hello " + name + number + sex, Toast.LENGTH_SHORT).show()
    }
}

class Person(
        context: Context,
        name: String,
        number: Int) : Test(context, name, number) {
    override fun hello() {
        Toast.makeText(context, "Person " + name + number, Toast.LENGTH_SHORT).show()
    }
}

