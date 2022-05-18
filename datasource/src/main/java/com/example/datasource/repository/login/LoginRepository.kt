package com.example.datasource.repository.login


interface LoginRepository {
    suspend fun doLogin(user: User): Result<UserTokens>
}