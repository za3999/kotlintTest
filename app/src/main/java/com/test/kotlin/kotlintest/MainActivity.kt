package com.test.kotlin.kotlintest

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import com.test.kotlin.kotlintest.test.KotlinTest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isPositive: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hello.text = "哈哈"
        val test = Person(this, "小王", 1234)
        test.name = "aaa"
        test.sex = "男"
        hello.setOnClickListener {
            Toast.makeText(this, KotlinTest.KEY, Toast.LENGTH_SHORT).show()
            KotlinTest.test(this)
        }
        rotationIv.setOnClickListener {
            it.animate().rotation(if (isPositive) {
                90f
            } else {
                0f
            }).setDuration(200).setInterpolator(OvershootInterpolator()).start()
            isPositive = !isPositive
        }
    }
}

open class Test(var context: Context, var name: String, var number: Int) {

    lateinit var sex: String

    open fun hello() {
        Toast.makeText(context, "Hello $name$number$sex", Toast.LENGTH_SHORT).show()
        Toast.makeText(context, "test1", Toast.LENGTH_SHORT).show()
    }
}

class Person(context: Context, name: String, number: Int) : Test(context, name, number) {
    override fun hello() {
        Toast.makeText(context, "Person $name$number$sex", Toast.LENGTH_SHORT).show()
    }
}

