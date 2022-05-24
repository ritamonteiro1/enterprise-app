package com.example.ioasysmvvm.domain.repository.login

import com.example.ioasysmvvm.domain.model.result.Result
import com.example.ioasysmvvm.domain.model.user.User
import com.example.ioasysmvvm.domain.model.user.UserTokens

interface LoginRepository {
    suspend fun doLogin(user: User): Result<UserTokens>
}