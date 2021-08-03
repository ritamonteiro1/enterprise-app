package com.example.ioasysmvvm.model.module

import com.example.ioasysmvvm.model.api.EnterpriseService
import com.example.ioasysmvvm.model.impl.EnterpriseRepositoryImpl
import com.example.ioasysmvvm.model.repository.EnterpriseRepository
import com.example.ioasysmvvm.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainModule = module {
    single<EnterpriseService> {
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
            .create(EnterpriseService::class.java)
    }

    single<EnterpriseRepository> {
        EnterpriseRepositoryImpl(get())
    }

    viewModel { MainViewModel(get()) }
}