package com.example.ioasysmvvm.model.impl

import com.example.ioasysmvvm.model.api.LoginService
import com.example.ioasysmvvm.model.domains.user.UserRequest
import com.example.ioasysmvvm.model.repository.LoginRepository

class LoginRepositoryImpl(private val loginService: LoginService): LoginRepository {
    override suspend fun isAuthenticatedUser(userRequest: UserRequest): Boolean {
        return loginService.recoverVerifyLogin(userRequest)
    }
}