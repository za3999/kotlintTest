package com.test.kotlin.kotlintest.test.common.test.keyword

import android.util.Log
import com.test.kotlin.kotlintest.test.common.Constants
import com.test.kotlin.kotlintest.test.common.test.bean.User

class Inline {

    companion object {
        fun test() {
            Log.d(Constants.TAG, "************** Inline **************")
            var inline = Inline()
            inline.outerFun()
            inline.reifiedFun()
        }
    }

    /******************* crossinline notInlined *******************/
    private fun outerFun() {
        innerFun {
            Log.d(Constants.TAG, "innerFun1 call")
            //return  //错误，不支持直接return
            //只支持通过标签，返回innerFun
            return@innerFun 1
        }

        innerFun2 {
            Log.d(Constants.TAG, "innerFun2 call return outerFun")
            2
            //            return  //正确 内联函数可以代替外部函数返回
        }

        innerFun3 {
            Log.d(Constants.TAG, "innerFun2 call return outerFun")
            3
            //            return  //错误 内联函数参数如果带crossinline不可以代替外部函数返回
        }

        foo({
            Log.d(Constants.TAG, "inlined call ")
            return  //正确 内联函数可以代替外部函数返回
        }) {
            Log.d(Constants.TAG, "notInlined call ")
//            return  //错误 noinline 为内联函数增加非内联部分
        }

        Log.d(Constants.TAG, "outerFun finish")
    }

    private fun innerFun(a: () -> Int) {
        var test = a()
        Log.d(Constants.TAG, "innerFun a:$test")
    }

    private inline fun innerFun2(a: () -> Int) {
        var test = a()
        Log.d(Constants.TAG, "innerFun2 a:$test")
    }

    //内联函数参数如果带crossinline不可以代替外部函数返回
    private inline fun innerFun3(crossinline a: () -> Int) {
        var test = a()
        Log.d(Constants.TAG, "innerFun2 a:$test")
    }

    //noinline 为内联函数增加非内联部分
    private inline fun foo(a: () -> Unit, noinline b: () -> Unit) {
        a()
        b()
    }

    /******************* reified *******************/
    private fun reifiedFun() {
        var isType = User("test", 4).isTypeOf(User::class.java)
        Log.d(Constants.TAG, "isTypeOf :$isType")
        isType = User("test", 4).isTypeOfByReified<User>()
        Log.d(Constants.TAG, "isTypeOfByReified :$isType")
    }

    //调用处参数,可以用，但是不是很优雅 User::class.java
    private fun <T> User.isTypeOf(clazz: Class<T>): Boolean {
        return clazz.isInstance(this)
    }

    //内联函数支持具体化的类型参数，我们可以这样写
    private inline fun <reified T> User.isTypeOfByReified(): Boolean {
        Log.d(Constants.TAG, "T class is:" + T::class.java) //T可以当做普通类型使用
        return this is T
    }

}