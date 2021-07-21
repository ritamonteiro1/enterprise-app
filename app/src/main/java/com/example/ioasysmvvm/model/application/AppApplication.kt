package com.example.ioasysmvvm.model.application


import android.app.Application
import com.example.ioasysmvvm.model.module.loginModule


class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AppApplication)

            modules(loginModule)
        }
    }
}