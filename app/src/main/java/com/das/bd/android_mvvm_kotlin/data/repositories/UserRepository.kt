package com.das.bd.android_mvvm_kotlin.data.repositories

import com.das.bd.android_mvvm_kotlin.data.db.AppDatabase
import com.das.bd.android_mvvm_kotlin.data.db.entities.User
import com.das.bd.android_mvvm_kotlin.data.network.ApiClicnt
import com.das.bd.android_mvvm_kotlin.data.network.SafeApiRequest
import com.das.bd.android_mvvm_kotlin.data.network.responses.AuthResponse

class UserRepository(private val api :ApiClicnt,private val db:AppDatabase) : SafeApiRequest(){
   suspend fun userLogin(email: String, password: String) : AuthResponse {
       return apiRequest { api.userLogin(email , password)  }
    }

    suspend fun userSignup(name: String, email: String, password: String): AuthResponse {
        return apiRequest { api.ueserSignup(name, email, password) }
    }
    suspend fun saveUser(user : User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()

}





