package com.example.datasource.remote.login

import com.example.datasource.model.user.UserTokens
import com.example.ioasysmvvm.domain.model.user.User
import com.example.datasource.result.Result


interface LoginRemoteDataSource {
    suspend fun doLogin(user: User): Result<UserTokens>
}