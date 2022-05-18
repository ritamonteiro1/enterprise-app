package com.example.datasource.repository.login

import com.example.datasource.remote.login.LoginRemoteDataSource

class LoginRepositoryImpl(private val loginRemoteDataSource: LoginRemoteDataSource) :
    LoginRepository {
    override suspend fun doLogin(user: User): Result<UserTokens> {
        return loginRemoteDataSource.doLogin(user)
    }
}