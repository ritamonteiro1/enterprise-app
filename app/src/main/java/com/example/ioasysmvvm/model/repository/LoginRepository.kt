package com.example.ioasysmvvm.model.repository

import com.example.ioasysmvvm.model.domains.user.User


interface LoginRepository {
    suspend fun isAuthenticatedUser(user: User): Boolean
}