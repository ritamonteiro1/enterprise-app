package com.example.datasource.remote.login


interface LoginRemoteDataSource {
    suspend fun doLogin(user: User): Result<UserTokens>
}