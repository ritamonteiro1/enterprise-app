package com.example.ioasysmvvm.model.impl

import com.example.ioasysmvvm.model.api.LoginService
import com.example.ioasysmvvm.model.domains.user.User
import com.example.ioasysmvvm.model.domains.user.UserRequest
import com.example.ioasysmvvm.model.repository.LoginRepository

class LoginRepositoryImpl(private val loginService: LoginService): LoginRepository {
    override suspend fun isAuthenticatedUser(user: User): Boolean {
        val userRequest = UserRequest(user.email, user.password)
        return loginService.recoverVerifyLogin(userRequest)
    }
}