package com.example.ioasysmvvm.domain.repository.login

import com.example.ioasysmvvm.domain.model.result.Result
import com.example.ioasysmvvm.domain.model.user.UserTokens
import com.example.ioasysmvvm.domain.model.user.User

interface LoginRepository {
    suspend fun doLogin(user: User): Result<UserTokens>
}