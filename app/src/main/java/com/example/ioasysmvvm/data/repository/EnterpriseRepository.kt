package com.example.ioasysmvvm.data.repository

import com.example.ioasysmvvm.domain.result.Result
import com.example.ioasysmvvm.domain.enterprise.Enterprise

interface EnterpriseRepository {
    suspend fun getEnterpriseList(
        enterpriseName: String,
        accessToken: String,
        client: String,
        uid: String
    ): Result<List<Enterprise>>
}