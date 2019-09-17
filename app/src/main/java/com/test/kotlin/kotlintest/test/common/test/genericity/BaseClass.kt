package com.test.kotlin.kotlintest.test.common.test.genericity

import android.util.Log
import com.test.kotlin.kotlintest.test.common.Constants

//当Dog是Animal的子类型，那么AnimalBox<Animal>也是AnimalBox<Dog>的子类型，这种继承关系就是逆变(in)。
//当Dog是Animal的子类型，AnimalBox<Dog>也是AnimalBox<Animal>的子类型，这种继承关系就是协变(out)

open class Animal {
    open fun speak() {
        Log.d(Constants.TAG, "我是一只动物")
    }

    override fun toString(): String {
        return "动物一只"
    }
}

open class Dog(var name: String) : Animal() {
    override fun speak() {
        Log.d(Constants.TAG, "我是小狗 $name")
    }

    override fun toString(): String {
        return "小狗$name"
    }
}

class WhiteDog(name: String) : Dog(name) {
    override fun speak() {
        Log.d(Constants.TAG, "我是白色小狗 $name")
    }

    override fun toString(): String {
        return "白色小狗$name"
    }
}

open class AnimalBox<T>(val elements: MutableList<T>) {
    fun add(t: T) = elements.add(t)
    fun first(): T = elements.first()
}

class DogAnimalBox(dogs: MutableList<Dog>) : AnimalBox<Dog>(dogs)

class InAnimaBox<in T>(private val elements: MutableList<in T>) {
    fun add(t: T) = elements.add(t)
//    fun first(): T = elements.first() // 编译错误,逆变不支持输出
}

class OutAnimaBox<out T>(private val elements: MutableList<out T>) {
    //    fun add(t: T) = elements.add(t) // 编译错误,协变不支持输入
    fun first(): T = elements.first()
}

//val dog = Dog::class.java
//val animal: Class<Animal> = dog //编译不通过

//val dog = Dog::class.java
//val animal: Class<out Animal> = dog

val dog = Dog::class.java
val animal: Class<out Any> = dog

//val animal = Animal::class.java
//val dog: Class<in Dog> = animal

//val animal = Any::class.java
//val dog: Class<in Dog> = animal