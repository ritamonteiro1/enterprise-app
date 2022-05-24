package com.example.datasource.repository.enterprise

import com.example.datasource.model.enterprise.Enterprise
import com.example.datasource.result.Result


interface EnterpriseRepository {
    suspend fun getEnterpriseList(
        enterpriseName: String,
        accessToken: String,
        client: String,
        uid: String
    ): Result<List<Enterprise>>
}