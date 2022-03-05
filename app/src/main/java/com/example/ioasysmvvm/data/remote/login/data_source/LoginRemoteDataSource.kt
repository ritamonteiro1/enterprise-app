package com.example.ioasysmvvm.data.remote.login.data_source

import com.example.ioasysmvvm.domain.model.result.Result
import com.example.ioasysmvvm.domain.model.user.UserTokens
import com.example.ioasysmvvm.domain.model.user.User

interface LoginRemoteDataSource {
    suspend fun doLogin(user: User): Result<UserTokens>
}