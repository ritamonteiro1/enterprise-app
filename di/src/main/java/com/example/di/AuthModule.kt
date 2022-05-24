package com.example.di

import com.example.core.constants.Constants
import com.example.datasource.api.LoginService
import com.example.datasource.remote.login.LoginRemoteDataSource
import com.example.datasource.remote.login.LoginRemoteDataSourceImpl
import com.example.datasource.repository.login.LoginRepository
import com.example.datasource.repository.login.LoginRepositoryImpl
import com.example.featureauth.domain.use_case.*
import com.example.featureauth.presentation.LoginViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val authModule = module {
    single<LoginService> {
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
            .create(LoginService::class.java)
    }

    single<LoginRemoteDataSource> {
        LoginRemoteDataSourceImpl(get())
    }

    single<LoginRepository> {
        LoginRepositoryImpl(get())
    }

    single<DoLoginUseCase> {
        DoLoginUseCaseImpl(get())
    }

    single<ValidateUserEmailUseCase> {
        ValidateUserEmailUseCaseImpl()
    }

    single<ValidateUserPasswordUseCase> {
        ValidateUserPasswordUseCaseImpl()
    }

    viewModel { LoginViewModel(get(), get(), get()) }
}