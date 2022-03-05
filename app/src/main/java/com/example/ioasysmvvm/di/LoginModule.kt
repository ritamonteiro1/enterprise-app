package com.example.ioasysmvvm.di

import com.example.ioasysmvvm.data.api.LoginService
import com.example.ioasysmvvm.data.repository.login.LoginRepositoryImpl
import com.example.ioasysmvvm.domain.repository.login.LoginRepository
import com.example.ioasysmvvm.presentation.login.LoginViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val loginModule = module {
    single<LoginService> {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        Retrofit.Builder()
            .baseUrl("https://empresas.ioasys.com.br/api/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginService::class.java)
    }

    single<LoginRepository> {
        LoginRepositoryImpl(get())
    }

    viewModel { LoginViewModel(get()) }
}