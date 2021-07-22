package com.example.ioasysmvvm.model.module

import com.example.ioasysmvvm.model.api.EnterpriseService
import com.example.ioasysmvvm.model.impl.MainRepositoryImpl
import com.example.ioasysmvvm.model.repository.MainRepository
import com.example.ioasysmvvm.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainModule = module {
    single<EnterpriseService> {
        Retrofit.Builder()
            .baseUrl("https://empresas.ioasys.com.br/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EnterpriseService::class.java)
    }

    single<MainRepository> {
        MainRepositoryImpl(get())
    }

    viewModel { MainViewModel(get()) }
}