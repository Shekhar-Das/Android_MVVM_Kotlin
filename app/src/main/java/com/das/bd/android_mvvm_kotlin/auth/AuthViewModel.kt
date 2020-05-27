package com.das.bd.android_mvvm_kotlin.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.das.bd.android_mvvm_kotlin.data.repositories.UserRepository
import com.das.bd.android_mvvm_kotlin.util.ApiException
import com.das.bd.android_mvvm_kotlin.util.Coroutines
import com.das.bd.android_mvvm_kotlin.util.NoInternetExecption

class AuthViewModel( private val userRepository: UserRepository) : ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun getLoggInUser() = userRepository.getUser()


    fun onLoginButtonClick(view: View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid email or password")
            return
        }
        Coroutines.main {
            try {
                val authResponse = userRepository.userLogin(email!! , password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    userRepository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            }catch (e : ApiException){
                authListener?.onFailure(e.message!!)
            }catch (e: NoInternetExecption){
                authListener?.onFailure(e.message!!)
            }
        }

    }
}