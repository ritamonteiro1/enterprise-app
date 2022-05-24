package com.example.di

import com.example.core.constants.Constants
import com.example.datasource.api.EnterpriseService
import com.example.datasource.remote.enterprise.EnterpriseRemoteDataSource
import com.example.datasource.remote.enterprise.EnterpriseRemoteDataSourceImpl
import com.example.datasource.repository.enterprise.EnterpriseRepository
import com.example.datasource.repository.enterprise.EnterpriseRepositoryImpl
import com.example.featurehome.domain.use_case.GetEnterpriseListUseCase
import com.example.featurehome.domain.use_case.GetEnterpriseListUseCaseImpl
import com.example.featurehome.presentation.EnterpriseListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val homeModule = module {
    single<EnterpriseService> {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EnterpriseService::class.java)
    }

    single<EnterpriseRemoteDataSource> {
        EnterpriseRemoteDataSourceImpl(get())
    }

    single<EnterpriseRepository> {
        EnterpriseRepositoryImpl(get())
    }

    single<GetEnterpriseListUseCase> {
        GetEnterpriseListUseCaseImpl(get())
    }

    viewModel { EnterpriseListViewModel(get()) }
}