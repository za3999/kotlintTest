package com.test.kotlin.kotlintest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import com.test.kotlin.kotlintest.test.common.Constants
import com.test.kotlin.kotlintest.test.common.extend
import com.test.kotlin.kotlintest.test.common.test.*
import com.test.kotlin.kotlintest.test.common.test.bean.User
import com.test.kotlin.kotlintest.test.common.test.java.JavaTest
import com.test.kotlin.kotlintest.test.common.test.java.JavaUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var isPositive: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val test = Person(this, Constants.PACKAGE_NAME + "小王", 1234)
        test.sex = "男"
        rotationIv.setOnClickListener {
            it.animate().rotation(if (isPositive) {
                90f
            } else {
                0f
            }).setDuration(200).setInterpolator(OvershootInterpolator()).start()
            isPositive = !isPositive
        }

        circulationTest.setOnClickListener {
            val mList: List<String> = listOf("aaa", "ccccc", "bbbbb", "ddddd")
            CirculationTest.test(mList)
        }

        classTest.setOnClickListener {
            ClassTest.test()
            ClassTest.extend()
        }

        closureTest.setOnClickListener {
            ClosureTest.test()
        }

        keywordTest.setOnClickListener {
            KeywordTest.test(it.context)
        }

        javaTest.setOnClickListener {
            var user = JavaUser("caifu", 5)
            user.run {
                Log.d(Constants.TAG, "test java 2 kotlin : $name,$age")
            }
            JavaTest().test()
        }

        extrasTest.setOnClickListener {
            val intent = Intent(this@MainActivity, ExtrasActivity::class.java)
            val u = User("Tony", 19)
            intent.putExtra("user", u)
            intent.putExtra("string", "just a test")
            startActivity(intent)
        }

        genericityTest.setOnClickListener {
            GenericityTest.test()
        }
    }
}

open class Test(var context: Context, var name: String, var number: Int) {

    lateinit var sex: String

    open fun hello() {
        Toast.makeText(context, "Hello $name$number$sex", Toast.LENGTH_SHORT).show()
        Toast.makeText(context, "test1", Toast.LENGTH_SHORT).show()
        Toast.makeText(context, "test2", Toast.LENGTH_SHORT).show()
    }
}

class Person(context: Context, name: String, number: Int) : Test(context, name, number) {
    override fun hello() {
        Toast.makeText(context, "Person $name$number$sex", Toast.LENGTH_SHORT).show()
    }
}

