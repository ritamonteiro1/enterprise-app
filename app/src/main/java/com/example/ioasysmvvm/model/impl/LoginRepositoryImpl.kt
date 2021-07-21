package com.example.ioasysmvvm.model.impl

import com.example.ioasysmvvm.model.api.DataService
import com.example.ioasysmvvm.model.domains.user.UserRequest
import com.example.ioasysmvvm.model.repository.LoginRepository

class LoginRepositoryImpl(private val dataService: DataService): LoginRepository {
    override suspend fun isAuthenticatedUser(userRequest: UserRequest): Boolean {
        return dataService.recoverVerifyLogin(userRequest)
    }
}