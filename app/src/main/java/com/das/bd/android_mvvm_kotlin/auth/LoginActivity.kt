package com.das.bd.android_mvvm_kotlin.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.das.bd.android_mvvm_kotlin.R
import com.das.bd.android_mvvm_kotlin.databinding.ActivityLoginBinding
import com.das.bd.android_mvvm_kotlin.util.toast

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set databinding information here
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        // view model class is also need to add here
        val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.viewModel = viewModel

        // current class contains our authLinseter
        viewModel.authListener = this
    }

    override fun onStarted() {
        toast("Started")
    }

    override fun onSuccess() {
        toast("Success")
    }

    override fun onFailure(message: String) {
        toast(message)
    }
}
