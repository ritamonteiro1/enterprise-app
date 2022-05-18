package com.example.datasource.remote.enterprise


interface EnterpriseRemoteDataSource {
    suspend fun getEnterpriseList(
        enterpriseName: String,
        accessToken: String,
        client: String,
        uid: String
    ): Result<List<Enterprise>>
}