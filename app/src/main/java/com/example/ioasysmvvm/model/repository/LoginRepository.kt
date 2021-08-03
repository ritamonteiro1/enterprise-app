package com.example.ioasysmvvm.model.repository

import com.example.ioasysmvvm.model.domains.result.Result
import com.example.ioasysmvvm.model.domains.user.UserTokens
import com.example.ioasysmvvm.model.domains.user.User

interface LoginRepository {
    suspend fun doLogin(user: User): Result<UserTokens>
}