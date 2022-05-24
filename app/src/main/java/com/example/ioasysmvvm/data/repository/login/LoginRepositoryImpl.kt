package com.example.ioasysmvvm.data.repository.login

import com.example.ioasysmvvm.data.remote.login.data_source.LoginRemoteDataSource
import com.example.ioasysmvvm.domain.model.result.Result
import com.example.ioasysmvvm.domain.model.user.User
import com.example.ioasysmvvm.domain.model.user.UserTokens
import com.example.ioasysmvvm.domain.repository.login.LoginRepository

class LoginRepositoryImpl(private val loginRemoteDataSource: LoginRemoteDataSource) :
    LoginRepository {
    override suspend fun doLogin(user: User): Result<UserTokens> {
        return loginRemoteDataSource.doLogin(user)
    }
}