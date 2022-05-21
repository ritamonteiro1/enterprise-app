package com.example.featureauth.domain.use_case

import com.example.datasource.repository.login.LoginRepository
import com.example.ioasysmvvm.domain.model.user.User
import com.example.datasource.model.user.UserTokens
import com.example.datasource.result.Result


interface DoLoginUseCase {
    suspend fun call(user: User): Result<UserTokens>
}

class DoLoginUseCaseImpl(private val loginRepository: LoginRepository) : DoLoginUseCase {
    override suspend fun call(user: User): Result<UserTokens> {
        return loginRepository.doLogin(user)
    }
}