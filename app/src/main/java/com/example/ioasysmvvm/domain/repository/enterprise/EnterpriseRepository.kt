package com.example.ioasysmvvm.domain.repository.enterprise

import com.example.ioasysmvvm.domain.model.result.Result
import com.example.ioasysmvvm.domain.model.enterprise.Enterprise

interface EnterpriseRepository {
    suspend fun getEnterpriseList(
        enterpriseName: String,
        accessToken: String,
        client: String,
        uid: String
    ): Result<List<Enterprise>>
}