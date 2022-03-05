package com.example.ioasysmvvm.data.repository

import com.example.ioasysmvvm.domain.result.Result
import com.example.ioasysmvvm.domain.user.UserTokens
import com.example.ioasysmvvm.domain.user.User

interface LoginRepository {
    suspend fun doLogin(user: User): Result<UserTokens>
}