package com.test.kotlin.kotlintest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.test.kotlin.kotlintest.test.common.Constants
import com.test.kotlin.kotlintest.test.common.extend
import com.test.kotlin.kotlintest.test.common.test.*
import com.test.kotlin.kotlintest.test.common.test.bean.User
import com.test.kotlin.kotlintest.test.common.test.java.JavaTest
import com.test.kotlin.kotlintest.test.common.test.java.JavaUser
import com.test.kotlin.kotlintest.test.common.test.jetpack.JetPackActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var isPositive: Boolean = true

    @Inject
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rotationIv.setOnClickListener {
            it.animate().rotation(if (isPositive) 90f else 0f).setDuration(200).setInterpolator(OvershootInterpolator()).start()
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
            user?.let {
                Log.d(Constants.TAG, "test java 2 kotlin : ${it.name},${it.age}")
            }

            var user = JavaUser("caifu", 5)
            user?.let {
                Log.d(Constants.TAG, "test java 2 kotlin : ${it.name},${it.age}")
            }
//            JavaTest().test()
        }

        extrasTest.setOnClickListener {
            val intent = Intent(it.context, ExtrasActivity::class.java)
            val u = User("Tony", 19)
            intent.putExtra("user", u)
            intent.putExtra("string", "just a test")
            startActivity(intent)
        }

        genericityTest.setOnClickListener {
            GenericityTest.test()
        }
        jetpackTest.setOnClickListener {
            startActivity(Intent(it.context, JetPackActivity::class.java))
        }

        listTest.setOnClickListener {
            startActivity(Intent(it.context, ListActivity::class.java))
        }
    }
}
