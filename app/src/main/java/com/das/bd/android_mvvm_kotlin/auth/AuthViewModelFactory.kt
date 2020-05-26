package com.das.bd.android_mvvm_kotlin.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.das.bd.android_mvvm_kotlin.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(private val userRepository: UserRepository ): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(userRepository) as T
    }
}