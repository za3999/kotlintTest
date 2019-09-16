package com.test.kotlin.kotlintest.test.common.test.bean

data class Student(var name: String, var sex: String = "男", var number: Int = 1, var age: Int = 19) {
    companion object {
        fun test() {
            var student1 = Student("郑小才")
            var student2 = Student("郑小才才", age = 5)
        }
    }
}
