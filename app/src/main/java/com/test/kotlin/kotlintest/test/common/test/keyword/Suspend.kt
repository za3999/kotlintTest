package com.test.kotlin.kotlintest.test.common.test.keyword

import android.util.Log
import com.test.kotlin.kotlintest.test.common.Constants
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import java.text.SimpleDateFormat
import java.util.*

class Suspend {
    val time = SimpleDateFormat("hh:MM:ss")

    companion object {
        fun test() {
            Log.d(Constants.TAG, "************** Suspend **************")
            Suspend().testCancel()
            Suspend().testTimeOut()
            Suspend().testContext()
            Suspend().testWithContext()
            Suspend().testAsync()
            Suspend().testSuspendAsync()
        }
    }

    fun testCancel() {
        val job = launch {
            var i = 0
            while (i < 1000 && isActive) {
                Log.d(Constants.TAG, "${time.format(Date())}  I'm sleeping $i ...")
                i++
            }
        }
        runBlocking {
            delay(2)
            Log.d(Constants.TAG, "${time.format(Date())}  I'm tired of waiting!")
            job.cancel()
            job.join()
            Log.d(Constants.TAG, "${time.format(Date())}  Now I can quit.")
        }
    }

    fun testTimeOut() {
        runBlocking {
            try {
                withTimeout(1300) {
                    repeat(1000) {
                        Log.d(Constants.TAG, "${time.format(Date())} I'm  testTimeOut sleeping $it ...")
                        delay(50)
                    }
                }
            } catch (e: Exception) {
                Log.d(Constants.TAG, "exception:$e")
            }
        }
    }

    //    CommonPool协程调度器使用的是ForkJoinPool线程池。
//    Unconfined协程调度器是一种无限制上下文，协程会在当前调用栈中执行直到第一次挂起。恢复运行后线程由被调用的挂起函数决定。
//    当协程没有耗费CPU时间或者没有更新任何局限在特定线程内的共享数据（例如 UI），无限制的调度器是合适的。
//    coroutineContext继承父调用器的协程调度器，这里由于runBlocking的协程调度器EmptyCoroutineContext运行在主线程，所以结果返回的线程也是在主线程
//    newSingleThreadContext这个没什么好说的了，它是在单进程中运行
    fun testContext() {
        runBlocking {
            val jobs = arrayListOf<Job>()
            jobs.add(launch(Unconfined) {
                Log.d(Constants.TAG, "'Unconfined': I'm working in thread ${Thread.currentThread()}")
                delay(500)
                Log.d(Constants.TAG, "'Unconfined': after I'm working in thread ${Thread.currentThread()}")
            })
            jobs.add(launch(coroutineContext) {
                Log.d(Constants.TAG, "'coroutineContext': I'm working in thread ${Thread.currentThread()}")
                delay(500)
                Log.d(Constants.TAG, "'coroutineContext': after I'm working in thread ${Thread.currentThread()}")
            })
            jobs.add(launch(CommonPool) {
                Log.d(Constants.TAG, "'CommonPool': I'm working in thread ${Thread.currentThread()}")
                delay(500)
                Log.d(Constants.TAG, "'CommonPool': after I'm working in thread ${Thread.currentThread()}")
            })
            jobs.add(launch(newSingleThreadContext("MyOwnThread")) {
                Log.d(Constants.TAG, "'newSingleThreadContext': I'm working in thread ${Thread.currentThread()}")
                delay(500)
                Log.d(Constants.TAG, "'newSingleThreadContext': after I'm working in thread ${Thread.currentThread()}")
            })
            jobs.forEach {
                it.join()
            }
        }
    }

    fun testWithContext() {
        val job = launch(newSingleThreadContext("ctx1")) {
            Log.d(Constants.TAG, "'ctx1 ${Thread.currentThread()}")
            withContext(CommonPool) {
                Log.d(Constants.TAG, "'ctx2 ${Thread.currentThread()}")
            }
            Log.d(Constants.TAG, "'ctx3 ${Thread.currentThread()}")
        }

        runBlocking {
            job.join()
        }
    }

    fun testAsync() {
        runBlocking {
            val job = async(CommonPool) {
                Log.d(Constants.TAG, "testAsync job")
                delay(500)
                "result"
            }
            Log.d(Constants.TAG, "testAsync finish:${job.await()}")
        }
    }

    fun testSuspendAsync() {
        launch(UI) {
            async {
                Log.d(Constants.TAG, "async finish:$it, ${Thread.currentThread()}")
            }
        }
    }

    suspend fun async(callback: (String) -> Unit) {
        val job = async(CommonPool) {
            Log.d(Constants.TAG, "async job start,${Thread.currentThread()}")
            delay(500)
            Log.d(Constants.TAG, "async job end,${Thread.currentThread()}")
            "result"
        }
        callback(job.await())
    }

}