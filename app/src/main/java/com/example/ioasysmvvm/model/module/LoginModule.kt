package com.example.ioasysmvvm.model.module

import com.example.ioasysmvvm.model.api.LoginService
import com.example.ioasysmvvm.model.impl.LoginRepositoryImpl
import com.example.ioasysmvvm.model.repository.LoginRepository
import com.example.ioasysmvvm.viewmodel.LoginViewModel
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