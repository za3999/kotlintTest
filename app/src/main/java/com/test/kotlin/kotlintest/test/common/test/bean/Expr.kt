package com.test.kotlin.kotlintest.test.common.test.bean

sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Double, val e2: Double) : Expr()
object NotANumber : Expr()