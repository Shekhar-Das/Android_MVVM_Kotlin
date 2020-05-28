package com.das.bd.android_mvvm_kotlin.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.das.bd.android_mvvm_kotlin.data.repositories.UserRepository
import com.das.bd.android_mvvm_kotlin.util.ApiException
import com.das.bd.android_mvvm_kotlin.util.Coroutines
import com.das.bd.android_mvvm_kotlin.util.NoInternetExecption

class AuthViewModel( private val userRepository: UserRepository) : ViewModel() {

    val name:String? = null
    var email: String? = null
    var password: String? = null
    var passwordconfirm : String? = null

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

    //sign up user
    fun onSignupButtonClick(view: View){
        authListener?.onStarted()

        if (name.isNullOrEmpty()){
            authListener?.onFailure("Name is required")
            return
        }

        if (email.isNullOrEmpty()){
            authListener?.onFailure("Email is required")
            return
        }

        if (password.isNullOrEmpty()){
            authListener?.onFailure("Password is required")
            return
        }

        if (password != passwordconfirm){
            authListener?.onFailure("Password did not match")
        }

        Coroutines.main {
            try {
                val authResponse = userRepository.userSignup(name!! , email!! , password!!)
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

    fun onSignup(view: View){
        Intent(view.context , RegistrationsActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
}