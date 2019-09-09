package com.test.kotlin.kotlintest.test.common.test.bean

class Site(map: MutableMap<String, String>) {
    var name: String by map
    var url: String  by map
    var key:String by map
}