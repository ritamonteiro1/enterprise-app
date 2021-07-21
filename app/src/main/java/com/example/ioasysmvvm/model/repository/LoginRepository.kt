package com.example.ioasysmvvm.model.repository

import com.example.ioasysmvvm.model.domains.user.UserRequest

interface LoginRepository {
    suspend fun isAuthenticatedUser(userRequest: UserRequest): Boolean
}