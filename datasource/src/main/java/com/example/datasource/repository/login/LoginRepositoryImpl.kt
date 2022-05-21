package com.example.datasource.repository.login

import com.example.datasource.model.user.UserTokens
import com.example.datasource.remote.login.LoginRemoteDataSource
import com.example.ioasysmvvm.domain.model.user.User
import com.example.datasource.result.Result

class LoginRepositoryImpl(private val loginRemoteDataSource: LoginRemoteDataSource) :
    LoginRepository {
    override suspend fun doLogin(user: User): Result<UserTokens> {
        return loginRemoteDataSource.doLogin(user)
    }
}