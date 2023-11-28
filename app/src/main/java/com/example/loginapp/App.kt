package com.example.loginapp

import android.app.Application
import com.example.loginapp.di.networkModule
import com.example.loginapp.di.dataModule
import com.example.loginapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(networkModule, dataModule, viewModelModule)
        }
    }

}