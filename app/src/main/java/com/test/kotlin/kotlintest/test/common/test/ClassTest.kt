package com.test.kotlin.kotlintest.test.common.test

import android.util.Log
import com.test.kotlin.kotlintest.test.common.Constants
import com.test.kotlin.kotlintest.test.common.*
import com.test.kotlin.kotlintest.test.common.test.bean.*

class ClassTest {

    companion object {
        fun test() {
            var c = C("test")
            c.reName = "reName"
            c.method()

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
            var student = Student("郑小才", 19, 0)
            val (name, age, sex) = student
            Log.d(Constants.TAG, " $name,$age,$sex")
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

            var closure = testClosure()
            Log.d(Constants.TAG, "test a :${closure()}")
            Log.d(Constants.TAG, "test a :${closure()}")
            Log.d(Constants.TAG, "test a :${closure()}")
            action(closure(), 4) { first: Int, second: Int ->
                Log.d(Constants.TAG, "action callback first is $first,$second")
                first == second
            }
            action(closure(), 4) { first: Int, second: Int ->
                Log.d(Constants.TAG, "action callback first:$first,second:$second")
                first == second
            }
        }

        private inline fun action(first: Int, second: Int, callback: (Int, Int) -> Boolean): Boolean {
            var result = callback(first, second)
            Log.d(Constants.TAG, "action result:$result")
            return result
        }

        private fun testClosure(): () -> Int {
            var a = 0
            return fun(): Int {
                return ++a
            }
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