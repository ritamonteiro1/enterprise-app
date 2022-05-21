package com.example.ioasysmvvm

import android.app.Application
import com.example.di.authModule
import com.example.di.boundaryModule
import com.example.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(authModule)
            modules(homeModule)
            modules(boundaryModule)
        }
    }
}