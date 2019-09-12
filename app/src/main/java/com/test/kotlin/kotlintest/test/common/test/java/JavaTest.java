package com.test.kotlin.kotlintest.test.common.test.java;

import android.util.Log;

import com.test.kotlin.kotlintest.test.common.Constants;
import com.test.kotlin.kotlintest.test.common.test.bean.User;

public class JavaTest {

    public void test() {
        User user = new User("caifu", 16);
        Log.d(Constants.TAG, "test kotlin 2 java:" + user.getName() + "," + user.getAge());
        Constants.INSTANCE.javaTes1();
        Constants.javaTes2();
    }
}
