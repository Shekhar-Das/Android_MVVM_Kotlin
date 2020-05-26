package com.das.bd.android_mvvm_kotlin.data.network.responses

import com.das.bd.android_mvvm_kotlin.data.db.entities.User

//store the json response
data class AuthResponse(
    val isSuccessful : Boolean?,
    val message : String?,
    val user :User?

)
