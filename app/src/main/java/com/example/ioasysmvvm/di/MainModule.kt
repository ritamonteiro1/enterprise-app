package com.example.ioasysmvvm.di

import com.example.ioasysmvvm.data.api.EnterpriseService
import com.example.ioasysmvvm.data.data.source.EnterpriseRemoteDataSource
import com.example.ioasysmvvm.data.data.source.EnterpriseRemoteDataSourceImpl
import com.example.ioasysmvvm.data.repository.EnterpriseRepository
import com.example.ioasysmvvm.data.repository.EnterpriseRepositoryImpl
import com.example.ioasysmvvm.presentation.enterprise.MainViewModel
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

    single<EnterpriseRemoteDataSource> {
        EnterpriseRemoteDataSourceImpl(get())
    }

    viewModel { MainViewModel(get()) }
}