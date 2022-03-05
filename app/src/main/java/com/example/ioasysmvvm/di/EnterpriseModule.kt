package com.example.ioasysmvvm.di

import com.example.ioasysmvvm.constants.Constants
import com.example.ioasysmvvm.data.api.EnterpriseService
import com.example.ioasysmvvm.data.remote.enterprise.data_source.EnterpriseRemoteDataSource
import com.example.ioasysmvvm.data.remote.enterprise.data_source.EnterpriseRemoteDataSourceImpl
import com.example.ioasysmvvm.data.repository.enterprise.EnterpriseRepositoryImpl
import com.example.ioasysmvvm.domain.repository.enterprise.EnterpriseRepository
import com.example.ioasysmvvm.domain.use_case.GetEnterpriseList
import com.example.ioasysmvvm.domain.use_case.GetEnterpriseListImpl
import com.example.ioasysmvvm.presentation.enterprise.enterprise_list.EnterpriseListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val enterpriseModule = module {
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

    single<GetEnterpriseList> {
        GetEnterpriseListImpl(get())
    }

    viewModel { EnterpriseListViewModel(get()) }
}