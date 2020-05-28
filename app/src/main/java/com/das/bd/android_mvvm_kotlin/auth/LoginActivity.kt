package com.das.bd.android_mvvm_kotlin.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.das.bd.android_mvvm_kotlin.R
import com.das.bd.android_mvvm_kotlin.data.db.AppDatabase
import com.das.bd.android_mvvm_kotlin.data.db.entities.User
import com.das.bd.android_mvvm_kotlin.data.network.ApiClicnt
import com.das.bd.android_mvvm_kotlin.data.network.NetworkConnectionInterceptor
import com.das.bd.android_mvvm_kotlin.data.repositories.UserRepository
import com.das.bd.android_mvvm_kotlin.databinding.ActivityLoginBinding
import com.das.bd.android_mvvm_kotlin.util.hide
import com.das.bd.android_mvvm_kotlin.util.show
import com.das.bd.android_mvvm_kotlin.util.snackbar
import com.das.bd.android_mvvm_kotlin.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.Kodein
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener , KodeinAware {

    override val kodein by kodein()
    private val factory by instance<AuthViewModelFactory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // titet couplling remove instance of we use android dependency
       /* val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = ApiClicnt(networkConnectionInterceptor)
        val db = AppDatabase(this)
        val userRepository = UserRepository(api ,db)
        val factory = AuthViewModelFactory(userRepository)*/

        // set databinding information here
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        // view model class is also need to add here
        val viewModel = ViewModelProvider(this,factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        // current class contains our authListener
        viewModel.authListener = this
        viewModel.getLoggInUser().observe(this, Observer {user ->
            if (user !=null){
               Intent(this , RegistrationsActivity::class.java).also {
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
      //  rootlayout.snackbar("${user.name} is Logged In")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        rootlayout.snackbar(message)
    }
}
