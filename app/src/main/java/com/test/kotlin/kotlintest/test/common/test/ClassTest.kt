package com.test.kotlin.kotlintest.test.common.test

import android.util.Log
import com.test.kotlin.kotlintest.test.common.Constants
import com.test.kotlin.kotlintest.test.common.*
import com.test.kotlin.kotlintest.test.common.test.bean.*

class ClassTest {

    companion object {
        fun test() {
            var c = C("tesct")
            c.reName = "reName"
            c.method()
            var letTest = c?.let {
                Log.d(Constants.TAG, " let test: ${c.name}")
                "success"
            }
            Log.d(Constants.TAG, " letTest return: $letTest")
            c?.run { Log.d(Constants.TAG, " run test: $name") }
            var d = D("12345", "testD", 25)
            d.reName = "reName D"
            d.method()
            var e = E("testE", 20)
            e.method()

            /*******extend*******/
            Log.d(Constants.TAG, " lastIndex ${c.reName}")
            var any: Nothing? = null
            Log.d(Constants.TAG, " test  any ${any.toTest()}")
            var str = "str"
            Log.d(Constants.TAG, " test  str ${str.toTest()}")
            c.foo()

            /*******data class*******/
            var user = User("郑小才", 19)
            user.temp = "11111"
            user.temp = "22222"
            val olderUser = user.copy(age = 2)
            Log.d(Constants.TAG, " user$user,olderUser$olderUser")

            var student = Student("郑小才")
            with(student) { Log.d(Constants.TAG, "with test: $name,$age,$sex") }
            val (name, age, sex) = student
            Log.d(Constants.TAG, "val test: $name,$age,$sex")

            lazyExample(user)
            lazyExample(user)
            Log.d(Constants.TAG, "description: ${user.description1()}")

            /*******sealed class*******/
            Log.d(Constants.TAG, "const: ${eval(Const(1.0))}")
            Log.d(Constants.TAG, "sum: ${eval(Sum(1.0, 1.0))}")
            Log.d(Constants.TAG, "notANumber: ${eval(NotANumber)}")

            val box = object : Box<String, String>("test") {
                override fun consume(str: String): String = "$k,:$str"
                var name: String = "菜鸟教程"
                var url: String = "www.runoob.com"
            }
            Log.d(Constants.TAG, "box name: ${box.name}")
            Log.d(Constants.TAG, "box url: ${box.url}")
            Log.d(Constants.TAG, "box consume: ${box.consume("哈哈")}")

            val site = Site(mutableMapOf(
                    "name" to "菜鸟教程",
                    "url" to "www.runoob.com",
                    "key" to "test key"
            ))
            Log.d(Constants.TAG, "site  ${site.name},${site.url}, ${site.key}")
            site.name = "google"
            site.url = "www.google.com"
            site.key = "test key 2"
            Log.d(Constants.TAG, "site  ${site.name},${site.url}, ${site.key}")

            SimpleSingleton.test()
            LazySingleton.instance.test()
        }

        private fun lazyExample(user: User) {
            Log.d(Constants.TAG, "user lazyName: ${user.lazyName}")
        }

        private fun eval(expr: Expr): Double = when (expr) {
            is Const -> expr.number
            is Sum -> expr.e1 + expr.e2
            is NotANumber -> Double.NaN
        }
    }

}