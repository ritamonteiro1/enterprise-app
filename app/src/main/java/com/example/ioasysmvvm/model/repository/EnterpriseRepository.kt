package com.example.ioasysmvvm.model.repository

import com.example.ioasysmvvm.model.domains.result.Result
import com.example.ioasysmvvm.model.domains.enterprise.Enterprise

interface EnterpriseRepository {
    suspend fun getEnterpriseList(
        enterpriseName: String,
        accessToken: String,
        client: String,
        uid: String
    ): Result<List<Enterprise>>
}