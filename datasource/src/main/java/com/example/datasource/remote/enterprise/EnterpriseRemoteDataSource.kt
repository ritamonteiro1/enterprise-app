package com.example.datasource.remote.enterprise

import com.example.datasource.model.enterprise.Enterprise
import com.example.datasource.result.Result

interface EnterpriseRemoteDataSource {
    suspend fun getEnterpriseList(
        enterpriseName: String,
        accessToken: String,
        client: String,
        uid: String
    ): Result<List<Enterprise>>
}