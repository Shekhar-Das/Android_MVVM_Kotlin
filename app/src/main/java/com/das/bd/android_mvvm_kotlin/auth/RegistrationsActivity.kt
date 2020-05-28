package com.das.bd.android_mvvm_kotlin.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.das.bd.android_mvvm_kotlin.R
import com.das.bd.android_mvvm_kotlin.data.db.entities.User
import com.das.bd.android_mvvm_kotlin.databinding.ActivityRegistationBinding
import com.das.bd.android_mvvm_kotlin.util.hide
import com.das.bd.android_mvvm_kotlin.util.show
import com.das.bd.android_mvvm_kotlin.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class RegistrationsActivity : AppCompatActivity() , AuthListener , KodeinAware{

    override val kodein by kodein()
    private val factory by instance<AuthViewModelFactory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registation)

        val binding: ActivityRegistationBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_registation)
        // view model class is also need to add here
        val viewModel = ViewModelProvider(this,factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        // current class contains our authListener
        viewModel.authListener = this
        viewModel.getLoggInUser().observe(this, Observer {user ->
            if (user !=null){
                Intent(this , LoginActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }

        })
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user:User) {
        progress_bar.hide()
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        rootlayout.snackbar(message)
    }
}
