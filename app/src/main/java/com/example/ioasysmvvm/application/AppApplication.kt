package com.example.ioasysmvvm.application

import android.app.Application
import com.example.ioasysmvvm.di.loginModule
import com.example.ioasysmvvm.di.enterpriseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(loginModule)
            modules(enterpriseModule)
        }
    }
}