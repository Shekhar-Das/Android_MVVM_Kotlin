package com.das.bd.android_mvvm_kotlin

import android.annotation.SuppressLint
import android.app.Application
import com.das.bd.android_mvvm_kotlin.auth.AuthViewModelFactory
import com.das.bd.android_mvvm_kotlin.data.db.AppDatabase
import com.das.bd.android_mvvm_kotlin.data.network.ApiClicnt
import com.das.bd.android_mvvm_kotlin.data.network.NetworkConnectionInterceptor
import com.das.bd.android_mvvm_kotlin.data.repositories.UserRepository
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

@SuppressLint("Registered")
class KotlinMVVMApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@KotlinMVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ApiClicnt(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance() , instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }




    }
}