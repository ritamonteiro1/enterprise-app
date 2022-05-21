package com.example.datasource.repository.login

import com.example.datasource.model.user.UserTokens
import com.example.ioasysmvvm.domain.model.user.User
import com.example.datasource.result.Result


interface LoginRepository {
    suspend fun doLogin(user: User): Result<UserTokens>
}