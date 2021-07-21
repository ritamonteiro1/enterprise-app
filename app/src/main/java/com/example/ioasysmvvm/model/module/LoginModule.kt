package com.example.ioasysmvvm.model.module

import com.example.ioasysmvvm.model.api.LoginService
import com.example.ioasysmvvm.model.impl.LoginRepositoryImpl
import com.example.ioasysmvvm.model.repository.LoginRepository
import com.example.ioasysmvvm.viewmodel.LoginViewModel

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val loginModule = module {
    single<LoginService> {
        Retrofit.Builder()
            .baseUrl("https://empresas.ioasys.com.br/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginService::class.java)
    }

    single<LoginRepository> {
        LoginRepositoryImpl(get())
    }

    viewModel { LoginViewModel(get()) }
}