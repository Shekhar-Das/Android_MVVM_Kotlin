package com.das.bd.android_mvvm_kotlin.util

import android.content.Context
import android.widget.Toast

fun Context.toast(message : String){
    Toast.makeText(this, message , Toast.LENGTH_LONG).show()
}