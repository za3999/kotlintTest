package com.test.kotlin.kotlintest.test.common.test

import android.os.Parcelable
import android.util.Log
import com.test.kotlin.kotlintest.test.common.Constants
import com.test.kotlin.kotlintest.test.common.test.bean.User
import com.test.kotlin.kotlintest.test.common.test.genericity.*

class GenericityTest {

    companion object {
        fun test() {
            var user1 = User("郑小才", 19)
            var user2 = User("小小才", 18)
            var minUser = GenericityTest().min(user1, user2)
            with(minUser) {
                Log.d(Constants.TAG, "min user:$name,$age")
            }

            var mult = MultipleBoundedClass<User>()
            mult.addItem(user1)
            mult.addItem(user2)
            mult.mList.forEach {
                Log.d(Constants.TAG, "mult item:${it.name},${it.age}")
            }
            val boxAnimal = AnimalBox(mutableListOf(Animal()))
            boxAnimal.add(Dog("一号"))
            boxAnimal.add(WhiteDog("一号"))
            speak(boxAnimal)
            speakIn(boxAnimal)
//            speakOut(boxAnimal) // 编译错误

            val boxDog = AnimalBox(mutableListOf(Dog("一号"), Dog("二号")))
            boxDog.add(WhiteDog("三号"))
//            speak(boxDog)// 编译错误
            speakIn(boxDog)
            speakOut(boxDog)

            val whiteDogBox = AnimalBox(mutableListOf(WhiteDog("一号"), WhiteDog("二号")))
//            speakOut(boxAnimal) // 编译错误
            speakOut(boxDog)
            speakOut(whiteDogBox)

            speakIn(boxAnimal)
            speakIn(boxDog)
//            speakIn(whiteDogBox) // 编译错误
        }

        // 只能传入Animal类型的封装对象，子类会报错
        private fun speak(box: AnimalBox<Animal>) {
            box.elements.forEach {
                it.speak()
            }
        }

        //当WhiteDog是Dog的子类型，AnimalBox<WhiteDog>也是AnimalBox<Dog>的子类型，这种继承关系就是协变(out)
        // out(协变)可以传入子类的封装对象，不能传入父类的封装对象(上界)
        private fun speakOut(box: AnimalBox<out Dog>) {
            //test out(协变)
            box.elements.forEach {
                it.speak()
            }
        }

        //当WhiteDog是Dog的子类型，那么AnimalBox<Dog>也是AnimalBox<WhiteDog>的子类型，这种继承关系就是逆变(in)。
        //in(逆变)可以传入Dog父类的封装对象，不能传入子类的封装对象(下界)
        private fun speakIn(box: AnimalBox<in Dog>) {
            //test in(逆变)
            box.elements.forEach {
                Log.d(Constants.TAG, "speakIn:${it.toString()}")
            }
        }
    }

    fun <T : Comparable<T>> min(first: T, second: T): T {
        val k = first.compareTo(second)
        return if (k <= 0) first else second
    }

    class MultipleBoundedClass<T> where T : Comparable<T>, T : Parcelable {

        var mList: MutableList<T> = mutableListOf()

        fun addItem(t: T) {
            mList.add(t)
        }

    }
}