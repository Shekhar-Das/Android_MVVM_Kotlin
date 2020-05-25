package com.das.bd.android_mvvm_kotlin.auth

interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message : String)
}