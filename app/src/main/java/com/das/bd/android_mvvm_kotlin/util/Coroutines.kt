package com.das.bd.android_mvvm_kotlin.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//static funtion similler to java
object Coroutines {
    fun main(work :suspend (() ->Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }

}