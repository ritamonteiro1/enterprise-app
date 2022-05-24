package com.example.ioasysmvvm.domain.use_case

import com.example.ioasysmvvm.domain.model.result.Result
import com.example.ioasysmvvm.domain.model.user.User
import com.example.ioasysmvvm.domain.model.user.UserTokens
import com.example.ioasysmvvm.domain.repository.login.LoginRepository

class DoLoginImpl(private val loginRepository: LoginRepository) : DoLogin {
    override suspend fun call(user: User): Result<UserTokens> {
        return loginRepository.doLogin(user)
    }
}