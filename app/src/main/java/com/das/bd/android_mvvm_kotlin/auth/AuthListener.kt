package com.das.bd.android_mvvm_kotlin.auth

import com.das.bd.android_mvvm_kotlin.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user :User)
    fun onFailure(message: String)
}